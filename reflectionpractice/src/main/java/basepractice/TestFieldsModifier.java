package basepractice;

import basepractice.ReflectionPoint;

import java.lang.reflect.Field;

/**
 * @author luzj
 * @description: field与反射
 * @date 2018/7/31
 */
public class TestFieldsModifier {
    public static void main(String[] args) throws IllegalAccessException {
        ReflectionPoint ref = new ReflectionPoint("bad", "bill", "boland");

        System.out.println("修改前：");
        System.out.println(ref);

        Class<? extends ReflectionPoint> clazz = ref.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            Class<?> c = f.getType();
            if (c == String.class){
                f.setAccessible(true);
                String s = (String) f.get(ref);
                f.set(ref,s+"_misa");
            }
        }

        System.out.println("修改后：");
        System.out.println(ref);
    }

}
