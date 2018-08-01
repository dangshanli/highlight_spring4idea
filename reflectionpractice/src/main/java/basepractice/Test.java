package basepractice;

/**
 * @author luzj
 * @description:
 * @date 2018/7/30
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        String s = "darling";

        Class<? extends String> c1 = s.getClass();
        Class<String> c2 = String.class;

        Class<?> c3 = Class.forName("java.lang.String");

        System.out.println(c1 == c2);
        System.out.println(c2 == c3);

    }
}
