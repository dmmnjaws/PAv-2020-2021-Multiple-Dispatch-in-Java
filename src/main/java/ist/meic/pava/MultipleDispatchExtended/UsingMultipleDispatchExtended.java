package ist.meic.pava.MultipleDispatchExtended;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * This class is an extention of ist.meic.pava.MultipleDispatch.UsingMultipleDispatch.class and
 * implements
 *
 */
public class UsingMultipleDispatchExtended {

    public static Object invoke(Object receiver, String methodName, Object... varArgs){

        Stream<Object> parameters = Stream.of(varArgs);

        try {
            Method method = bestMethod(receiver.getClass(), methodName, getParameterTypes(parameters));
            return method.invoke(receiver, varArgs);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
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

    private static Method bestMethod(Class receiverType, String methodName, Class[] parameterTypes) throws NoSuchMethodException {

        int orderOfDispatch = parameterTypes.length;
        Method[] acceptableReceiverMethods = getAcceptableReceiverMethods(receiverType, methodName, orderOfDispatch);

        for (int i = 0; i < orderOfDispatch; i++){
            acceptableReceiverMethods = filterMethods(acceptableReceiverMethods, parameterTypes[i], i);
        }

        return acceptableReceiverMethods[0];

    }

    private static Class[] getParameterTypes(Stream<Object> parameters){
        return parameters
                .map(object -> object.getClass())
                .toArray(Class[]::new);
    }

    private static Method[] getAcceptableReceiverMethods(Class receiverType, String methodName, int orderOfDispatch) {
        return Stream.of(receiverType.getMethods())
                .filter(m -> m.getName() == methodName && m.getParameterTypes().length == orderOfDispatch)
                .toArray(Method[]::new);
    }
}

