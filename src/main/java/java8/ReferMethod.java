package java8;

/**
 * @author luzj
 * @description: 1.使用  :: 关键字引用方法或者构造器
 * 2.注意：使用方法引用的接口，必须是函数式接口，否则会编译错误
 * 3.使用lambda表达式，编译器会检查他所匹配的实现的方法，入参类型和返回值类型一定要匹配上
 * 4.lambda深层次上就是利用匿名类，他可以访问表达式之外的局部变量，这个变量隐式的声明final
 * 5.lambda可以访问成员变量和静态变量
 * @date 2018/4/22
 */
public class ReferMethod {


    static void refFunction() {
        //Integer::valueOf是指向Integer.valueOf(String)函数的一个引用，
        // 这里相当于直接用这个函数实现了Converter的抽象类
        Converter<String, Integer> converter = Integer::valueOf;
        System.out.println(converter.convert("233"));

    }

    static void refFunction2() {
        SomeThing someThing = new SomeThing();
        Converter<String, String> converter = someThing::startWith;
        System.out.println(converter.convert("Java"));
    }

    static void refConstructor() {
        PersonFactory<Person> personPersonFactory = Person::new;
        Person p = personPersonFactory.create("nire","ergou");
    }

    public static void main(String[] args) {
        refFunction2();
//        refFunction();
    }

}
