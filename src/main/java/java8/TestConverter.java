package java8;

/**
 * @author luzj
 * @description:
 * @date 2018/4/22
 */
public class TestConverter {
    public static void main(String[] args) {
        Converter<String,Integer> converter = (from -> Integer.valueOf(from));
        System.out.println(converter.convert("245"));
        System.out.println(converter.mistake(22));
    }
}
