package three_hundred_examples.seven_chapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Date;

/**
 * @author luzj
 * @description: 1.关于如何使用Class类型获取引用类型的相关信息
 * <p>
 * 2.获取对象的Class类型
 * <p>
 * 3.获取类的全局信息大观：标准名称 修饰符 泛型参数 实现接口 超类 注解
 * <p>
 * 4.获取类成员的详情： 构造函数 非继承域变量  非继承方法
 *
 * 5.获取所有内部类的信息详情
 * @date 2018/3/4
 */
public class ClassInfo {

    /**
     * 获取对象的Class类型
     */
    public void printClassName() throws ClassNotFoundException {
        System.out.println("第一种方法：obj.getClass()-->");
        Class c1 = new Date().getClass();
        System.out.println(c1.getName());

        System.out.println("第二种方法：.class语法-->");
        Class c2 = boolean.class;
        System.out.println(c2.getName());

        System.out.println("第三种方法：Class.firName()-->");
        Class c3 = Class.forName("java.lang.String");
        System.out.println(c3.getName());

        //包装类：Double Float Integer Boolean等
        System.out.println("第四种方法：包装类TYPE域-->");
        Class c4 = Double.TYPE;
        System.out.println(c4.getName());
    }

    /**
     * 获取某个类全局信息大观
     * 1.标准名称 2.修饰符 3.泛型参数 4.实现接口 5.超类 6.注解
     */
    public void classDeclarationViewer() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.util.ArrayList");
        System.out.println("类的标准名称：" + clazz.getCanonicalName());
        System.out.println("类的修饰符：" + Modifier.toString(clazz.getModifiers()));

        //todo 类的泛型参数
        TypeVariable<?>[] typeVariables = clazz.getTypeParameters();
        System.out.println("类的泛型参数：");
        if (typeVariables.length != 0)
            for (TypeVariable<?> typeVariable : typeVariables) {
                System.out.println(typeVariable + "\t");
            }
        else
            System.out.println("类泛型参数空");

        //todo 所有实现的接口
        Type[] interfaces = clazz.getGenericInterfaces();
        System.out.println("类实现的接口：");
        if (interfaces.length != 0)
            for (Type type : interfaces) {
                System.out.println("\t" + type);
            }
        else
            System.out.println("\t" + "实现接口为空");

        //todo 输出类的直接超类，继承自object返回空
        Type superClass = clazz.getGenericSuperclass();
        System.out.println("超类：");
        if (superClass != null)
            System.out.println(superClass);
        else
            System.out.println("超类为空");

        //todo 获取注解信息
        Annotation[] annotations = clazz.getAnnotations();
        System.out.println("类的注解：");
        if (annotations.length != 0)
            for (Annotation annotation : annotations)
                System.out.println("\t" + annotation);
        else
            System.out.println("注解为空");
    }

    /**
     * 查看类的成员变量信息
     */
    public void classMembers() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.util.ArrayList");
        System.out.println("类的标准名称：" + clazz.getCanonicalName());

        //todo 获取构造方法
        Constructor[] constructors = clazz.getConstructors();
        System.out.println("类的构造方法：");
        if (constructors.length > 0)
            for (Constructor constructor : constructors)
                System.out.println("\t" + constructor);
        else
            System.out.println("\t构造函数空");

        //todo 获取对象的所有非继承域
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("类的非继承域变量：");
        if (fields.length != 0)
            for (Field field : fields)
                System.out.println("\t" + field);
        else
            System.out.println("\t域为空");

        //todo 该对象的所有非继承方法
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("类的非继承方法：");
        if (methods.length > 0)
            for (Method method : methods)
                System.out.println(method);
        else
            System.out.println("方法为空");
    }

    /**
     * 打印内部类信息
     *
     * @throws ClassNotFoundException Class.forName()的类不存在
     */
    public void printNestClassInfo() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.awt.geom.Path2D");
        //todo 获取所有的内部类组成的数组
        Class<?>[] classes = clazz.getDeclaredClasses();

        if (classes.length == 0)
            return;

        //todo 打印内部类信息：名称 修饰符 实现接口 超类
        for (Class cls : classes) {
            System.out.println("类的名称：" + cls.getCanonicalName());

            System.out.println("类的修饰符：" + Modifier.toString(cls.getModifiers()));

            Type[] interfaces = cls.getGenericInterfaces();
            System.out.println("类的所有实现接口：");
            if (interfaces.length > 0)
                for (Type type : interfaces)
                    System.out.println("\t" + type);
            else
                System.out.println("空");

            Type type = cls.getGenericSuperclass();
            System.out.println("类的直接继承类：");
            if (type != null)
                System.out.println("\t" + type);
            else
                System.out.println("空");
            System.out.println("=========================================");
        }
    }


    public static void main(String[] args) {
        ClassInfo classInfo = new ClassInfo();
        try {
//            classInfo.printClassName();
//            classInfo.classDeclarationViewer();
//            classInfo.classMembers();
            classInfo.printNestClassInfo();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
