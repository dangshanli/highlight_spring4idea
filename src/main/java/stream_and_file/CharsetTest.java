package stream_and_file;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 * @author luzj
 * @description: 字符集api的使用
 * @date 2018/6/27
 */
public class CharsetTest {

    /**
     * 使用Charset进行字符串的编解码
     */
    static void enDecodeStr(){
        String s = "明月几时有，把酒问青天";
        Charset charset1 = Charset.forName("utf-8");
        Charset charset2 = Charset.forName("GBK");

        //使用特定Charset编码字符串,转化成字节数组
        ByteBuffer bb = charset2.encode(s);
        byte[] bytes = bb.array();

        //字节数组转换成ByteBuffer,然后特定字符集解码
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        CharBuffer charBuffer = charset2.decode(byteBuffer);
        System.out.println(charBuffer.toString());

    }

    public static void main(String[] args) {
        enDecodeStr();
        System.out.println("===========================================");
        //todo 查看某个字符编码的别名
        Charset cset = Charset.forName("ISO-8859-1");
        Set<String> aliases = cset.aliases();
        for (String s : aliases) {
            System.out.println(s);
        }
        System.out.println("============================================");

        //todo 可用字符集名称
        Map<String, Charset> charsetMap = Charset.availableCharsets();
        for (String s : charsetMap.keySet()) {
            System.out.println(s);

        }
    }
}
