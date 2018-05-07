package java8;

/**
 * @author luzj
 * @description:函数式接口： 只含有一个抽象方法
 * 使用@FunctionInterface注解接口，使之成为函数式接口，并接受java函数式接口规则检查
 * @date 2018/4/22
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);

    default String mistake(T ff) {
        if (ff instanceof String)
            return ((String) ff).toUpperCase();
        else
            return "none";
    }
}
