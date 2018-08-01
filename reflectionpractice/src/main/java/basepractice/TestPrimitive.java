package basepractice;

/**
 * @author luzj
 * @description: 基本Class类型
 * @date 2018/7/31
 */
public class TestPrimitive {
    public static void main(String[] args) {
        System.out.println(String.class.isPrimitive());
        System.out.println(int.class.isPrimitive());
        System.out.println(int.class == Integer.class);
        System.out.println(int[].class.isPrimitive());
        System.out.println(int[].class.isArray());
        System.out.println(int.class == Integer.TYPE);
    }
}
