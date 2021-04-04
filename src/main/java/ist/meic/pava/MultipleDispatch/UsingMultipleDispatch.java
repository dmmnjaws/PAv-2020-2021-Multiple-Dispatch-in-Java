package ist.meic.pava.MultipleDispatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * This class attempts to implement multiple dispatch in Java.
 *
 */
public class UsingMultipleDispatch {

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

    private static Method bestMethod(Class receiverType, String methodName, Class[] parameterTypes) throws NoSuchMethodException {

        int orderOfDispatch = parameterTypes.length;
        Method[] acceptableReceiverMethods = getAcceptableReceiverMethods(receiverType, methodName, parameterTypes, orderOfDispatch);

        for (int i = 0; i < orderOfDispatch; i++){
            acceptableReceiverMethods = filterMethods(acceptableReceiverMethods, parameterTypes[i], i);
        }
        return acceptableReceiverMethods[0];
    }

    private static Method[] filterMethods(Method[] methods, Class parameterType, int parameterIndex) throws NoSuchMethodException {

        Method[] filteredMethods = Arrays.stream(methods)
                .filter(m -> m.getParameterTypes()[parameterIndex] == parameterType)
                .toArray(Method[]::new);

        try {
            if (filteredMethods.length == 0){
                throw new NoSuchMethodException();
            }else{
                return filteredMethods;
            }
        } catch (NoSuchMethodException e) {
            if(parameterType == Object.class) {
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

    private static ArrayList<Class> getAllSuperclasses(Class parameterTypes){

        ArrayList<Class> allSuperClasses = new ArrayList<>();

        while(parameterTypes.getSuperclass() != Object.class){
            allSuperClasses.add(parameterTypes.getSuperclass());
            parameterTypes = parameterTypes.getSuperclass();
        }

        allSuperClasses.add(Object.class);

        return allSuperClasses;
    }
}
