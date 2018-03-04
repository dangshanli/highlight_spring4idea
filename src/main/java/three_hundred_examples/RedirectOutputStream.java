package three_hundred_examples;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author luzj
 * @description:
 * 1.标准输出流重定向输出
 * 2.可应用于业务中某些日志输出标准输出的重定向
 * @date 2018/2/27
 */
public class RedirectOutputStream {
    public static void main(String[] args) {
        try {
            //todo 暂存标准输出流
            PrintStream out = System.out;
            //todo 重定向输出流
            PrintStream ps = new PrintStream("src/main/resource/log.txt");
            System.setOut(ps);

            //todo 此时，System.out.println输出到 ps这个对象的流中
            int age = 18;
            System.out.println("年龄："+age);
            String sex = "女";
            System.out.println("性别："+sex);
            String info = "性别："+sex+",年龄："+age;
            System.out.println(info);

            //todo 恢复输出流
            System.setOut(out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
