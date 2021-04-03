package ist.meic.pava.MultipleDispatchExtended;

import javassist.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

/**
 * This class is an extension of ist.meic.pava.MultipleDispatch.UsingMultipleDispatch.class and
 * implements the following extensions:
 * - Multiple Dispatch with support for Variable Arity methods.
 *
 */
public class UsingMultipleDispatchExtended {

    public static Object invoke(Object receiver, String methodName, Object... varArgs) {

        Class[] parameterTypes = Stream.of(varArgs)
                .map(object -> object.getClass())
                .toArray(Class[]::new);

        try {
            Method method = bestMethod(receiver.getClass(), methodName, parameterTypes);
            return method.invoke(receiver, varArgs);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e1) {
            if (e1.getClass().equals(NoSuchMethodException.class)) {
                try {
                    Method method = bestVariableArityMethod(receiver.getClass(), methodName, parameterTypes);
                    return invokeVarArgsMethod(method, receiver, varArgs);

                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e2) {
                    throw new RuntimeException(e2);
                }

            }
            throw new RuntimeException(e1);
        }
    }

    private static Method bestMethod(Class receiverType, String methodName, Class[] parameterTypes) throws NoSuchMethodException {

        int orderOfDispatch = parameterTypes.length;
        Method[] acceptableReceiverMethods = getAcceptableReceiverMethods(receiverType, methodName, orderOfDispatch);

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

    private static Method[] getAcceptableReceiverMethods(Class receiverType, String methodName, int orderOfDispatch) {

        return Stream.of(receiverType.getMethods())
                .filter(m -> m.getName() == methodName && m.getParameterCount() == orderOfDispatch)
                .toArray(Method[]::new);
    }

    // VARIABLE ARITY

    private static Method bestVariableArityMethod(Class receiverType, String methodName, Class[] parameterTypes) throws NoSuchMethodException {

        int orderOfDispatch = parameterTypes.length;
        Method[] acceptableReceiverMethods = getVarArgsReceiverMethods(receiverType, methodName);
        acceptableReceiverMethods = Arrays.stream(acceptableReceiverMethods)
                .filter(m -> m.getParameterCount() <= orderOfDispatch+1)
                .toArray(Method[]::new);

        for (int i = 0; i < orderOfDispatch; i++) {
            acceptableReceiverMethods = filterVariableArityMethods(acceptableReceiverMethods, parameterTypes[i], i);
        }

        Comparator<Method> byParameterCount = Comparator.comparingInt(Method::getParameterCount);
        return Arrays.stream(acceptableReceiverMethods).max(byParameterCount).get();
    }

    private static Method[] filterVariableArityMethods(Method[] methods, Class parameterType, int parameterIndex) throws NoSuchMethodException {

        boolean needClimbClassHierarchy;

        Method[] sameParameterTypeMethods = Arrays.stream(methods)
                .filter(m -> m.getParameterCount() >= parameterIndex+1 && m.getParameterTypes()[parameterIndex] == parameterType)
                .toArray(Method[]::new);

        needClimbClassHierarchy = (sameParameterTypeMethods.length == 0);

        Method[] variableArityMethods = Arrays.stream(methods)
                .filter(m -> m.getParameterCount() <= parameterIndex + 1)
                .toArray(Method[]::new);

        ArrayList<Method> filteredMethodsMutable = new ArrayList<>();
        Collections.addAll(filteredMethodsMutable, sameParameterTypeMethods);
        Collections.addAll(filteredMethodsMutable, variableArityMethods);
        Method[] filteredMethods = filteredMethodsMutable.toArray(new Method[0]);

        try {
            if (needClimbClassHierarchy) {
                throw new NoSuchMethodException();
            } else {
                return filteredMethods;
            }
        } catch (NoSuchMethodException e) {
            if (parameterType == Object.class) {
                if (variableArityMethods.length != 0){
                    return variableArityMethods;}
                throw e;
            } else {
                return filterVariableArityMethods(methods, parameterType.getSuperclass(), parameterIndex);
            }
        }
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

        ArrayList<Object> argumentsArrayList = new ArrayList<>();
        for(int i = 0; i < numberOfNonVarParameters; i++){
            argumentsArrayList.add(arguments[i]);
        }

        argumentsArrayList.add(variableArguments);

        return varArgsMethod.invoke(receiver, argumentsArrayList.toArray());
    }

    // BOXING AND UNBOXING

    private static void boxingTest(Object... bar) {

        System.err.println("bar instanceof Integer[]:\t" + (bar instanceof Integer[]));
        System.err.println("bar[0] instanceof Integer:\t" + (bar[0] instanceof Integer));
        System.err.println("bar.getClass().isArray():\t" + bar.getClass().isArray());
    }
}
