package java_web;

import java.io.*;

/**
 * Created by Administrator on 2018/1/27.
 */
public class TestInput {

    public static void transfer(String from,String to) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(from);
            out = new FileOutputStream(to);
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("transfer over");
        }
    }

    public void testStreamAPI(){
        File f = new File("F:\\壁纸\\dancer.jpg");
        try {
            new BufferedInputStream(new ObjectInputStream(new FileInputStream(f)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        transfer("E://learning.py","F://my_thanks.py");
        transfer("F:\\壁纸\\01.jpg","F://kiss_love.jpg");
    }


}
