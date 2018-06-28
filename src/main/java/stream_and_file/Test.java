package stream_and_file;

import java.io.*;

/**
 * @author luzj
 * @description:
 * @date 2018/6/7
 */
public class Test {
    public static void main(String[] args) throws IOException {
        String path = "C:\\sftp_test\\mingyue.txt";
        FileInputStream in = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(in,"utf-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String s ;
        while (( s = bufferedReader.readLine()) != null){
            System.out.println(s);
        }
    }
}
