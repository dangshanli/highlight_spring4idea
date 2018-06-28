package stream_and_file;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author luzj
 * @description:
 * @date 2018/6/7
 */
public class Test2 {
    public static void main(String[] args) throws IOException {
        String path = "C:\\sftp_test\\test.txt";
        PrintWriter printWriter = new PrintWriter(new FileWriter(path));
        printWriter.print("苟利国家生死以,");
        printWriter.println("岂因福祸避趋之");
        printWriter.println(2.5567);
        printWriter.println('c');
        printWriter.println(222);
        printWriter.println(true);
        Person p = new Person();
        p.setName("王源");
        printWriter.println(p);
        printWriter.flush();

    }
}
