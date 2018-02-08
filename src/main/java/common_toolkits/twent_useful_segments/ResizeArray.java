package common_toolkits.twent_useful_segments;

import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * @author luzj
 * @package common_toolkits.twent_useful_segments
 * @description:
 * 1.重设数组的大小
 * 2.将二维数组转成Map
 * @date 2018/2/8 11:14
 */
public class ResizeArray {

    /**
     * 创建新大小的数组，其实是利用反射创建一个新的数组，并获取旧数组的值
     *
     * @param oldArr  源数组
     * @param newSize 新大小
     * @return
     */
    public static Object resizeArr(Object oldArr, int newSize) {
        int oldSize = Array.getLength(oldArr);
        //todo 使用反射创建数组类型的实例
        Class elementType = oldArr.getClass().getComponentType();
        Object newArr = Array.newInstance(elementType, newSize);

        //todo 将旧的数组复制给新的数组
        int preserveLength = Math.min(oldSize, newSize);
        if (preserveLength > 0)
            System.arraycopy(oldArr, 0, newArr, 0, preserveLength);
        return newArr;
    }

    /**
     * 将二位数组转化成HashMap
     * 使用Apache的ArrayUtils工具类
     * @return 转化成的数组
     */
    public static Map arrToMap() {
        String[][] countries = {{"United States", "New York"}, {"United Kingdom", "London"},
                {"Netherland", "Amsterdam"}, {"Japan", "Tokyo"}, {"France", "Paris"}};

        //todo 使用Apache工具类将数组转换成HashMap
        //todo 数组的元素必须是Map.Entry 或者 Array，否则抛出IllegalArgumentException
        Map countryCap = ArrayUtils.toMap(countries);
        return countryCap;
    }


    public static void main(String[] args) {
        Map caps = arrToMap();
        System.out.println("United States: "+caps.get("United States"));
        System.out.println("France: "+caps.get("France"));
    }

}
