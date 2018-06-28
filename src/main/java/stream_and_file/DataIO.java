package stream_and_file;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author luzj
 * @description: 读写固定字符串工具类
 * 1 以固定的大小(size)，写入字符串
 * 2 读取固定大小(size)的码元位
 * @date 2018/6/28
 */
public class DataIO {

    /**
     * 将字符串以固定大小写入到输出流中
     *
     * @param s    待写入的字符串
     * @param size 写入的固定位数，如果size > s.length,则补0;否则,丢弃部分字符码元(一般要留足够大的size防止这种情况)
     * @param out  输出流，一般锁定某个本地文件
     */
    public static void writeFixedString(String s, int size, DataOutput out) throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if (i < s.length())
                ch = s.charAt(i);
            out.writeChar(ch);
        }
    }

    /**
     * 从输入流中读取固定长度的字符，使用StringBuilder持有，形成字符串
     *
     * @param size 读取的固定大小，如果size < s.length则信息丢失
     * @param in   输入流
     * @return
     */
    public static String readFixedString(int size, DataInput in) throws IOException {
        StringBuilder sb = new StringBuilder(size);
        int i = 0;
        boolean more = true;

        while (more && i < size){
            char ch = in.readChar();
            i++;
            if (ch == 0)//直到ch为0，则停止读取
                more = false;
            else
                sb.append(ch);
        }

        in.skipBytes((size - i) * 2);//输入流跳过剩下的字节位数
        return sb.toString();
    }
}
