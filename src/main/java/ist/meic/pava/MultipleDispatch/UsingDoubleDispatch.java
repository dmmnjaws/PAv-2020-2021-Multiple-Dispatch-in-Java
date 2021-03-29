package ist.meic.pava.MultipleDispatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class was imported straight from the theoretical class of PAv for study.
 */
public class UsingDoubleDispatch {

    public static Object invoke(Object receiver, String methodName, Object arg){
        try {
            Method method = bestMethod(receiver.getClass(), methodName, arg.getClass());
            return method.invoke(receiver, arg);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Method bestMethod(Class type, String name, Class argType) throws NoSuchMethodException {
        try {
            return type.getMethod(name, argType);
        } catch (NoSuchMethodException e) {
            if(argType == Object.class) {
                throw e;
            } else {
                return bestMethod(type, name, argType.getSuperclass());
            }
        }
    }

}
