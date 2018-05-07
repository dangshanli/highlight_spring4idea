package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author luzj
 * @description:
 * 1.一个lambda可以匹配一个接口，需要实现该接口时候，可以直接使用一个lambda表达式
 * 2.匹配的接口必须只有一个抽象方法声明，可以有多个默认方法(default function)
 * 3.使用@FunctionInterface注解，可以声明是函数式使用接口。声明该注解后，当你定义的函数不符合函数式接口时就会编译报错
 * @date 2018/4/21
 */
public class TasteLambda {
    static List<String> names1 = Arrays.asList("peter","ana","76","kasa");
    static List<String> names2 = Arrays.asList("peter","ana","76","kasa");

    /**
     * 传统方式匿名接口
     */
    public static void sortTradition(){
        Collections.sort(names1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(names1);
    }

    /**
     * 使用lambda表达式写函数
     */
    public static void sortLambda(){
        Collections.sort(names2,(String s1,String s2)-> s1.compareTo(s2));
        System.out.println(names2);
    }

    public static void main(String[] args) {
        sortTradition();
        System.out.println("=====================================");
        sortLambda();
    }
}
