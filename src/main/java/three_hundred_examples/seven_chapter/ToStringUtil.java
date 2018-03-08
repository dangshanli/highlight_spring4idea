package three_hundred_examples.seven_chapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * @author luzj
 * @description: 1.创建一个给对象重写toString()方法的工具类
 * @date 2018/3/8
 */
public class ToStringUtil {

    /**
     * 工具方法
     * 1.打印包名 类名 公共构造函数 公共域 公共方法
     *
     * @param object 待获取信息的对象
     * @return
     */
    public String toString(Object object) {
        Class clazz = object.getClass();
        StringBuilder sb = new StringBuilder();
        Package packageName = clazz.getPackage();
        sb.append("包名：" + packageName.getName() + "\t");

        String className = clazz.getSimpleName();
        sb.append("类名：" + className + "\n");

        sb.append("公共构造方法：\n");
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            String modifiers = Modifier.toString(constructor.getModifiers());
            if (modifiers.contains("public"))
                sb.append(constructor.toGenericString() + "\n");
        }

        sb.append("公共域：\n");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String modifier = Modifier.toString(field.getModifiers());
            if (modifier.contains("public"))
                sb.append(field.toGenericString() + "\n");
        }

        sb.append("公共方法：\n");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String modifier = Modifier.toString(method.getModifiers());
            if (modifier.contains("public"))
                sb.append(method.toGenericString() + "\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ToStringUtil().toString(new Date()));
    }
}
