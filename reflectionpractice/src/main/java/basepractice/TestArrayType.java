package basepractice;

/**
 * @author luzj
 * @description:
 * @date 2018/7/31
 */
public class TestArrayType {
    public static void main(String[] args) throws ClassNotFoundException {
        int[] a = new int[]{1,2,3};
        Integer[] b = new Integer[]{4,5,6,7};
        int[][] c = new int[3][5];
        String[] d = new String[]{"www","https","sss"};
        int[] e = new int[5];

        System.out.println(a.getClass() == e.getClass());

        /*System.out.println(a.getClass() == b.getClass());
        System.out.println(a.getClass() == c.getClass());
        System.out.println(a.getClass() == d.getClass());
        System.out.println(b.getClass() == d.getClass());*/

        System.out.println(a.getClass().getName());//int[] 的名称
        System.out.println(int.class.getName()); //int 的名称

        System.out.println(d.getClass().getName());//String[] 的名称
        System.out.println(String.class.getName());//String 的名称

        System.out.println(Integer[].class.getName());//Integer[]的名称

        Class<?> clazz = Class.forName("[I");//[I表示int 数组
        Class<?> clazz1 = Class.forName("[Ljava.lang.String;");//[表示数组
    }
}
