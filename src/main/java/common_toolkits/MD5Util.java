package common_toolkits;

import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/1/18.
 * MD5加密工具
 */
public class MD5Util {

    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 进行MD5加密
     *
     * @param originStr
     * @return
     */
    public static String encodeByMD5(String originStr) {
        String hexResult = null;
        if (originStr != null && !"".equals(originStr)) {
            try {
                //创建指定算法的信息类
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] resultBytes = md.digest(originStr.getBytes());
                hexResult = byteArrayToHex(resultBytes).toUpperCase();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return hexResult;
    }


    /**
     * 字节数组转成16进制字符串
     *
     * @param b
     * @return
     */
    private static String byteArrayToHex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(byteToHex(b[i]));
        }
        return sb.toString();
    }

    /**
     * 将byte转换成16进制表示
     *
     * @param b
     * @return
     */
    private static String byteToHex(byte b) {
        int n = b;//转成整数
        if (n < 0)
            n += 256;
        int b1 = n / 16;//十位
        int b2 = n % 16;//个位
        return hexDigits[b1] + hexDigits[b2];
    }

    public static void main(String[] args) {
        /*Byte b = new Byte("101");
        int a = b;
        System.out.println(b.byteValue());
        System.out.println(a);
        System.out.println(byteToHex(b));*/
        String a = MD5Util.encodeByMD5("karas");
        System.out.println(a);
        System.out.println();
    }
}
