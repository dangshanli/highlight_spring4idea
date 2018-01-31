package common_toolkits.twent_useful_segments;

import org.slf4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author luzj
 * @package common_toolkits.twent_useful_segments
 * @description: java中常用的20个代码片段
 * @date 2018/1/31 15:12
 */
public class JavaSegments {

    /**
     * 数值转换成字符串，
     *
     * @param x int，
     * @return
     */
    public String transferIntToStr(int x) {
        //todo valueOf可以转换double、float、Boolean等类型
        return String.valueOf(x);
    }

    /**
     * 整字符串转成整数
     *
     * @param str
     * @return
     */
    public int transferStrToInt(String str) {
        //todo 字符串转double类型
        /*double d = Double.parseDouble("2.6778");
        System.out.println(d*10);*/

        return Integer.parseInt(str);
    }

    /**
     * 通过字符流向本地文本文件追加字符串
     *
     * @param fileName 本地文件目录名
     */
    public void appendStrToFile(String fileName) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(fileName, true));
            out.write("\n我爱萝莉");
        } catch (IOException e) {
            System.err.println("打开文件流异常");
            e.printStackTrace();
        } finally {
            this.printMethodTrace();
            if (out != null)
                try {
                    out.close();
                    System.out.println("流关闭写入完毕");
                } catch (IOException e) {
                    System.err.println("文件流未正常关闭");
                    e.printStackTrace();
                }
        }
    }

    /**
     * 获取当前运行的方法名，这里是"getCurrentMethodName"
     *谁掉下面的代码片段，显示谁的方法签名
     * @return
     */
    public String getCurrentMethodName() {
        //todo 获取当前线程方法栈最上层的方法名
        return Thread.currentThread().getStackTrace()[1].
                getMethodName();
    }

    /**
     * 打印方法调用栈
      */
    private void printMethodTrace() {
        int i = 0;
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            System.out.println("method" + (i++) + ": " + element.getMethodName());
        }
    }


    public static void main(String[] args) {
        JavaSegments javaSegments = new JavaSegments();
//        System.out.println(javaSegments.transferIntToStr(2222));
//        System.out.println(javaSegments.transferStrToInt("234.567"));
        javaSegments.appendStrToFile("F:\\testio\\my.txt");
//        System.out.println(javaSegments.getCurrentMethodName());
//        String name = Thread.currentThread().getStackTrace()[1].getMethodName();
//        System.out.println(name);
    }


}
