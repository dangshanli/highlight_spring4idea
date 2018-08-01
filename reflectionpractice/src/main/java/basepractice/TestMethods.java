package basepractice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author luzj
 * @description: method 与 反射
 * @date 2018/7/31
 */
public class TestMethods {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String s = "abc";
        Class<?> clazz = s.getClass();

        Method m = clazz.getMethod("charAt", int.class);
        System.out.println(m.invoke(s, 1));

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
