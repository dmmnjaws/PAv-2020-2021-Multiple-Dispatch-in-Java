package ist.meic.pava.MultipleDispatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class UsingMultipleDispatch {

    /**
     * This method is called to use Multiple Dispatch.
     * @param receiver is the receiver object of the method to be invoked
     * @param methodName is the name of the method to be invoked
     * @param varArgs are the parameter objects to be passed to the method to be invoked
     * @return the return of the call to the most suitable method
     */
    public static Object invoke(Object receiver, String methodName, Object... varArgs){

        Class[] parametersTypes = Stream.of(varArgs)
                .map(object -> object.getClass())
                .toArray(Class[]::new);

        try {
            Method method = bestMethod(receiver.getClass(), methodName, parametersTypes);
            return method.invoke(receiver, varArgs);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);

        }
    }

    /**
     * This method is the entry point to the Multiple Dispatch algorithm.
     * @param receiverType is the type of the receiver of the method to be invoked
     * @param methodName is the name of the method to be invoked
     * @param parameterTypes are the types of the parameters to be passed to the method to be invoked
     * @return the most suitable method to be invoked
     * @throws NoSuchMethodException if no suitable method is found
     */
    private static Method bestMethod(Class receiverType, String methodName, Class[] parameterTypes) throws NoSuchMethodException {

        int orderOfDispatch = parameterTypes.length;
        Method[] acceptableReceiverMethods = getAcceptableReceiverMethods(receiverType, methodName, parameterTypes, orderOfDispatch);

        for (int i = 0; i < orderOfDispatch; i++){
            acceptableReceiverMethods = filterMethods(acceptableReceiverMethods, parameterTypes[i], i);
        }

        return acceptableReceiverMethods[0];
    }

    /**
     * This method implements part of the Multiple Dispatch algorithm. It has three functions:
     * - Filter an array of methods, keeping only methods whose parameter number parameterIndex is of type parameterType;
     * - If it finds none, recursively call itself with the superclass of the parameter type, climb class hierarchy of the parameter, until it reaches Object.class.
     * - If array of methods is empty at the end of the class hierarchy of a parameter (Object.class), throw NoSuchMethodException
     * @param methods is the array of methods to filter (by now assumed to have only applicable methods)
     * @param parameterType is the desired parameter type
     * @param parameterIndex is the desired parameter index
     * @return filtered array of methods
     * @throws NoSuchMethodException if array of methods is empty and it reached the end of the class hierarchy
     */
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

    /**
     * This method gets all applicable methods of a receiver's hierarchy, with a given name, and a given number of parameters.
     * @param receiverType is the desired type of the receiver
     * @param methodName is the desired method name
     * @param parameterTypes are the desired parameter types (top of the class hierarchy)
     * @param orderOfDispatch are the number of parameters
     * @return an array of applicable methods.
     */
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

    /**
     * This method climbs up the class hierarchy of a given parameter type and gets all of it's superclasses.
     * @param parameterType the desired parameter type
     * @return an ArrayList with all superclasses of parameterType.
     */
    private static ArrayList<Class> getAllSuperclasses(Class parameterType){

        ArrayList<Class> allSuperClasses = new ArrayList<>();

        while(parameterType.getSuperclass() != Object.class){
            allSuperClasses.add(parameterType.getSuperclass());
            parameterType = parameterType.getSuperclass();
        }

        allSuperClasses.add(Object.class);

        return allSuperClasses;
    }
}
