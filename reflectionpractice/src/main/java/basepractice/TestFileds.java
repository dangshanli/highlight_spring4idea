package basepractice;

import basepractice.ReflectionPoint;

import java.lang.reflect.Field;

/**
 * @author luzj
 * @description:
 * @date 2018/7/31
 */
public class TestFileds {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ReflectionPoint reflectionPoint = new ReflectionPoint(3, 5);

        Class<? extends ReflectionPoint> clazz = reflectionPoint.getClass();
        Field y = clazz.getField("y");
        System.out.println("filedX的值：" + y.getInt(reflectionPoint));

        Field[] fields = clazz.getFields();
        System.out.println("fields长度:"+fields.length);
        for (Field f : fields) {
            System.out.println(f.toString());
        }

        Field fieldX = clazz.getDeclaredField("x");
        fieldX.setAccessible(true);
        System.out.println("filedX的值:"+fieldX.getInt(reflectionPoint));

    }
}
