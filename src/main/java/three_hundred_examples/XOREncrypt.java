package three_hundred_examples;

import java.util.Scanner;

/**
 * @author luzj
 * @description: 1.使用简单的异或运算加密字符串
 * 2.x XOR m -->y ; y XOR m --> x
 * 3.可以用于简单的加密运算，"塑料加密"
 * @date 2018/2/28
 */
public class XOREncrypt {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("输入英文字符串：");
        String password = in.nextLine();
        String enc = xOREnc(password);
        System.out.println("加密结果如下：");
        System.err.println(enc);
        System.out.println("解密还原：");
        System.out.println(xOREnc(enc));
    }

    /**
     * 对输入的字符串进行加密/解密
     * 使用XOR 20000 方式
     *
     * @param password
     * @return
     */
    public static String xOREnc(String password) {
        char arr[] = password.toCharArray();

        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = (char) (arr[i] ^ 20000);
        }
        return new String(arr);
    }
}
