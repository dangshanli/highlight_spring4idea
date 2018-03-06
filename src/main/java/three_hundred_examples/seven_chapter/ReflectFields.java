package three_hundred_examples.seven_chapter;

import java.lang.reflect.Field;

/**
 * @author luzj
 * @description:
 * 1.利用反射动态设置类的私有域，即使没有对外开放set/get接口的私有变量
 * @date 2018/3/6
 */
public class ReflectFields {

    /**
     * 反射，运行时设置私有变量的值
     */
    public void dynamicSetFields(){
        Student stu = new Student();
        Class<?> clazz = stu.getClass();
        System.out.println("类的名称："+clazz.getCanonicalName());
        System.out.println("修改前：");
        System.out.println("\t"+stu);
        try {
            //todo 获取变量的Field
            Field id = clazz.getDeclaredField("id");
            Field name = clazz.getDeclaredField("name");
            Field gender = clazz.getDeclaredField("gender");
            Field account = clazz.getDeclaredField("account");

            //todo 设置可访问权限
            id.setAccessible(true);
            name.setAccessible(true);
            gender.setAccessible(true);
            account.setAccessible(true);

            id.setInt(stu,10);
            name.set(stu,"玉藻前");
            gender.setBoolean(stu,false);
            account.setDouble(stu,500);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("修改后：");
        System.out.println("\t"+stu);

    }

    public static void main(String[] args) {
        ReflectFields reflectFields = new ReflectFields();
        reflectFields.dynamicSetFields();
    }
}
