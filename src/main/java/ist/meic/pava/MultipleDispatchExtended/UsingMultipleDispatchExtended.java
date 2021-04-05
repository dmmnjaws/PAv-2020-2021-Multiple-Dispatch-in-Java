package ist.meic.pava.MultipleDispatchExtended;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * This class is an extension of ist.meic.pava.MultipleDispatch.UsingMultipleDispatch.class and additionally, it implements:
 * - Support for Variable Arity methods.
 * - Support for Memoization of Previous Invocations
 */
public class UsingMultipleDispatchExtended {

    public static Object invoke(Object receiver, String methodName, Object... varArgs) {

        Class[] parameterTypes = Stream.of(varArgs)
                .map(object -> object.getClass())
                .toArray(Class[]::new);

        Class receiverType = receiver.getClass();

        // get method cache
        String invocationString = invocationStringBuilder(receiverType, methodName, parameterTypes);
        MultipleDispatchCacheSingleton multipleDispatchCacheSingleton = MultipleDispatchCacheSingleton.getInstance();
        boolean isCached = multipleDispatchCacheSingleton.isCached(invocationString);

        try {
            // if method in cache, return it
            // if method in cache but null, it's known there's no suitable method in the receiver, throw NoSuchMethodException()
            if(isCached){
                Method method = multipleDispatchCacheSingleton.getCachedMethod(invocationString);
                if (method == null){
                    throw new NoSuchMethodException();
                }

                Object result = (method.isVarArgs()) ? invokeVarArgsMethod(method, receiver, varArgs) : method.invoke(receiver, varArgs);
                return result;
            }

            // if method not in cache, do the usual thing, go check non variable arity methods first
            // if method found, store it in cache
            Method method = bestMethod(receiverType, methodName, parameterTypes);
            multipleDispatchCacheSingleton.putUncachedMethod(invocationString, method);
            return method.invoke(receiver, varArgs);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e1) {
            if (e1.getClass().equals(NoSuchMethodException.class)) {
                try {
                    // if NoSuchMethodException() came from null method in cache, rethrow exception, there's no suitable method.
                    if(isCached) {
                        throw new NoSuchMethodException();
                    }

                    // if NoSuchMethodException() came from non variable arity search, go look in the variable arity methods
                    // if method found, store it in cache
                    Method method = bestVariableArityMethod(receiverType, methodName, parameterTypes);
                    multipleDispatchCacheSingleton.putUncachedMethod(invocationString, method);
                    return invokeVarArgsMethod(method, receiver, varArgs);

                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e2) {

                    // if no method found, store in cache with null method (redundant in current implementation, it's here for future considerations)
                    multipleDispatchCacheSingleton.putUncachedMethod(invocationString, null);
                    throw new RuntimeException(e2);
                }

            }
            throw new RuntimeException(e1);
        }
    }

    private static Method bestMethod(Class receiverType, String methodName, Class[] parameterTypes) throws NoSuchMethodException {

        int orderOfDispatch = parameterTypes.length;
        Method[] acceptableReceiverMethods = getAcceptableReceiverMethods(receiverType, methodName, parameterTypes, orderOfDispatch);

        for (int i = 0; i < orderOfDispatch; i++) {
            acceptableReceiverMethods = filterMethods(acceptableReceiverMethods, parameterTypes[i], i);
        }

        return acceptableReceiverMethods[0];
    }

    private static Method[] filterMethods(Method[] methods, Class parameterType, int parameterIndex) throws NoSuchMethodException {

        Method[] filteredMethods = Arrays.stream(methods)
                .filter(m -> m.getParameterTypes()[parameterIndex] == parameterType)
                .toArray(Method[]::new);

        try {
            if (filteredMethods.length == 0) {
                throw new NoSuchMethodException();
            } else {
                return filteredMethods;
            }

        } catch (NoSuchMethodException e) {
            if (parameterType == Object.class) {
                throw e;
            } else {
                return filterMethods(methods, parameterType.getSuperclass(), parameterIndex);
            }

        }
    }

    private static Method[] getAcceptableReceiverMethods(Class receiverType, String methodName, Class[] parameterTypes, int orderOfDispatch) {

        Method[] acceptableReceiverMethods = Stream.of(receiverType.getMethods())
                .filter(m -> m.getName() == methodName && m.getParameterCount() == orderOfDispatch && !m.isVarArgs())
                .toArray(Method[]::new);

        for (int i = 0; i < orderOfDispatch; i++){
            int finalI = i;
            ArrayList<Class> superClasses = getAllSuperclasses(parameterTypes[i]);
            superClasses.add(parameterTypes[i]);
            acceptableReceiverMethods = Stream.of(acceptableReceiverMethods)
                    .filter(m -> superClasses.contains(m.getParameterTypes()[finalI]))
                    .toArray(Method[]::new);
        }

        return acceptableReceiverMethods;
    }

    private static ArrayList<Class> getAllSuperclasses(Class parameterType){

        ArrayList<Class> allSuperClasses = new ArrayList<>();

        while(parameterType.getSuperclass() != Object.class){
            allSuperClasses.add(parameterType.getSuperclass());
            parameterType = parameterType.getSuperclass();
        }

        allSuperClasses.add(Object.class);

        return allSuperClasses;
    }

    // VARIABLE ARITY

    private static Method bestVariableArityMethod(Class receiverType, String methodName, Class[] parameterTypes) throws NoSuchMethodException {

        int orderOfDispatch = parameterTypes.length;

        Method[] acceptableReceiverMethods = getVarArgsReceiverMethods(receiverType, methodName);
        acceptableReceiverMethods = Arrays.stream(acceptableReceiverMethods)
                .filter(m -> m.getParameterCount() <= orderOfDispatch+1)
                .toArray(Method[]::new);

        Class minimumCommonSuperClass = getMinimumCommonSuperclass(parameterTypes, orderOfDispatch);

        boolean safeMode = false;
        for (int i = 0; i < orderOfDispatch; i++) {
            acceptableReceiverMethods = filterVariableArityMethods(acceptableReceiverMethods, parameterTypes[i], i, safeMode);
            if (!safeMode && i == orderOfDispatch - 1){
                i = 0;
                safeMode = true; }
        }

        if(acceptableReceiverMethods.length == 0){
            throw new NoSuchMethodException();
        }

        Method[] lastCandidateMethods = new Method[]{};

        while(lastCandidateMethods.length == 0){
            Class finalMinimumCommonSuperClass = minimumCommonSuperClass;
            lastCandidateMethods = Arrays.stream(acceptableReceiverMethods)
                .filter(m -> m.getParameterTypes()[m.getParameterCount()-1].getComponentType() == finalMinimumCommonSuperClass)
                .toArray(Method[]::new);
            minimumCommonSuperClass = minimumCommonSuperClass.getSuperclass();
            if (minimumCommonSuperClass == null){ break; }
        }

        Comparator<Method> byParameterCount = Comparator.comparingInt(Method::getParameterCount);
        return Arrays.stream(lastCandidateMethods).max(byParameterCount).get();
    }

    /**
     * Filters out all methods than can't absolutely receive the parameterType in the "parameter slot" number parameterIndex
     *
     */
    private static Method[] filterVariableArityMethods(Method[] methods, Class parameterType, int parameterIndex, boolean safeMode) {

        ArrayList<Method> filteredMethodsMutable = new ArrayList<>();

        while((!safeMode) || (safeMode && filteredMethodsMutable.isEmpty())){
            Class finalParameterType = parameterType;
            Method[] sameParameterTypeMethods = Arrays.stream(methods)
                    .filter(m -> m.getParameterCount() >= parameterIndex+1 && m.getParameterTypes()[parameterIndex] == finalParameterType)
                    .toArray(Method[]::new);
            Collections.addAll(filteredMethodsMutable, sameParameterTypeMethods);
            parameterType = parameterType.getSuperclass();
            if(parameterType == null){ break; }
        }

        Method[] variableArityMethods = Arrays.stream(methods)
                .filter(m -> m.getParameterCount() <= parameterIndex + 1)
                .toArray(Method[]::new);

        Collections.addAll(filteredMethodsMutable, variableArityMethods);

        return filteredMethodsMutable.toArray(new Method[0]);

    }

    private static Class getMinimumCommonSuperclass(Class[] parameterTypes, int orderOfDispatch){

        ArrayList<Class> allSuperClasses = new ArrayList<>();

        for (Class parameterType : parameterTypes){
            allSuperClasses.addAll(getAllSuperclasses(parameterType));
        }

        Map<Class, Integer> counter = new HashMap<>();

        for (Class superClass : allSuperClasses) {
            if(!counter.containsKey(superClass)){
                counter.put(superClass, 0);
            }
            counter.put(superClass, counter.get(superClass) + 1);
            if(counter.get(superClass) == orderOfDispatch){
                return superClass;
            }
        }

        return Object.class;
    }

    private static Method[] getVarArgsReceiverMethods(Class receiverType, String methodName) {

        return Stream.of(receiverType.getMethods())
                .filter(m -> m.getName() == methodName && m.isVarArgs())
                .toArray(Method[]::new);
    }

    private static Object invokeVarArgsMethod(Method varArgsMethod, Object receiver, Object... varArgs) throws InvocationTargetException, IllegalAccessException {

        int numberOfNonVarParameters = varArgsMethod.getParameterCount() - 1;

        Object[] arguments = Arrays.copyOfRange(varArgs, 0, numberOfNonVarParameters);
        Object[] variableArguments = Arrays.copyOfRange(varArgs, numberOfNonVarParameters, varArgs.length);

        Class<?> varArgsType = varArgsMethod.getParameterTypes()[varArgsMethod.getParameterCount() - 1].getComponentType();

        ArrayList<Object> argumentsArrayList = new ArrayList<>();
        for(int i = 0; i < numberOfNonVarParameters; i++){
            argumentsArrayList.add(arguments[i]);
        }

        argumentsArrayList.add(Array.newInstance(varArgsType, variableArguments.length));

        return varArgsMethod.invoke(receiver, argumentsArrayList.toArray());
    }

    // MEMOIZATION

    private static String invocationStringBuilder(Class receiverType, String methodName, Class[] parameterTypes){
        String invocationString = receiverType.getName() + methodName;

        for (Class parameterType : parameterTypes) {
            invocationString = invocationString + parameterType.getName();
        }

        return invocationString;
    }

    // BOXING AND UNBOXING

    private static void boxingTest(Object... bar) {

        System.err.println("bar instanceof Integer[]:\t" + (bar instanceof Integer[]));
        System.err.println("bar[0] instanceof Integer:\t" + (bar[0] instanceof Integer));
        System.err.println("bar.getClass().isArray():\t" + bar.getClass().isArray());
    }
}
