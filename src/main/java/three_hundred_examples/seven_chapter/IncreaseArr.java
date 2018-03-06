package three_hundred_examples.seven_chapter;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author luzj
 * @description: 利用反射创建使数组可变长的工具类
 * 2.java中数组不管几维，都属于Object类型
 * @date 2018/3/6
 */
public class IncreaseArr {

    /**
     * 工具方法，调用一次可以让数组长度增加5
     *
     * @param array
     * @return
     */
    public static Object increaseArray(Object array) {
        //todo 数组的类类型
        Class<?> clazz = array.getClass();
        if (clazz.isArray()) {
            //todo 获取数组元素类型
            Class<?> componentType = clazz.getComponentType();
            int length = Array.getLength(array);
            //todo 创建新数组
            Object newArray = Array.newInstance(componentType, length + 5);
            //todo 复制数据
            System.arraycopy(array, 0, newArray, 0, length);
            return newArray;
        }
        return null;
    }

    /**
     * 测试工具类
     * @param args
     */
    public static void main(String[] args) {
        int[] intArray = new int[10];
        System.out.println("原始长度："+intArray.length);
        Arrays.fill(intArray,8);
        System.out.println(Arrays.toString(intArray));
        int[] newArray = (int[]) increaseArray(intArray);
        System.out.println("拓展后长度："+newArray.length);
        System.out.println("拓展后内容：");
        System.out.println(Arrays.toString(newArray));
    }
}
