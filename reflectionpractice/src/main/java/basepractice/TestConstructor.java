package basepractice;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author luzj
 * @description: Class获取构造器对象
 * @date 2018/7/31
 */
public class TestConstructor {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        //获取所有的Constructor对象
        Class<String> clazz = String.class;
        Constructor<?>[] constructor = clazz.getConstructors();
       /* for (Constructor<?> c : constructor) {
            System.out.println(c);
        }*/

        //获取特定构造器对象，并且实例化出对象
        Constructor<String> constructor1 = String.class.getConstructor(StringBuffer.class);
        String str = constructor1.newInstance(new StringBuffer("abc"));
        System.out.println(str.toString());

        Constructor<?>[] constructor2 = String.class.getDeclaredConstructors();
        for (Constructor<?> cons : constructor2) {
            System.out.println(cons);

        }

        //直接调用默认构造器
        String str2 = String.class.newInstance();
        str2 = "bcd";
        System.out.println(str2);
    }
}
