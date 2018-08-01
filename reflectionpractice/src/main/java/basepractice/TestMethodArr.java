package basepractice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author luzj
 * @description: 调用某个类的main方法，传入String[] 作为参数
 * @date 2018/7/31
 */
public class TestMethodArr {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String name = "basepractice/TestArgs";
        Class<?> clazz = Class.forName(name);
        System.out.println(clazz.getName());
        Method m = clazz.getMethod("main",String[].class);
        System.out.println(m);
        String[] s = new String[]{"darling","darling","darling","stay","side","me"};
        m.invoke(null,(Object) s);



    }




}
