package ist.meic.pava.MultipleDispatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.Stream;

/**
 * This class attempts to implement multiple dispatch in Java.
 *
 */
public class UsingMultipleDispatch {

    public static Object invoke(Object receiver, String methodName, Object... varArgs){

        Stream<Object> parameters = Stream.of(varArgs);

        try {
            Method method = bestMethod(receiver.getClass(), methodName, getParameterTypes(parameters));
            return method.invoke(receiver, varArgs);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<Method> filterMethods(Stream<Method> methods, Class parameterType, int parameterIndex){
        Stream<Method> filteredMethods = methods.filter(m -> m.getParameterTypes()[parameterIndex] == parameterType);
    }

    private static Method bestMethod(Class receiverType, String name, Stream<Class> parameterTypes) throws NoSuchMethodException {

        int orderOfDispatch = (int)(parameterTypes.count());
        Stream<Method> allReceiverMethods = Stream.of(receiverType.getDeclaredMethods()).filter(m -> m.getName() == name);
        Class[] parameterTypesArray = parameterTypes.toArray(Class[]::new);

        return null;

    }

    private static Stream<Class> getParameterTypes(Stream<Object> parameters){
        return parameters.map(object -> object.getClass());
    }
}
