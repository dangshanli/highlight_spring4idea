package java_essential.my_exception;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: luzj
 * @date: 2018/2/13
 * @description:
 * 1.演示try-catch-finally工具的使用
 * 2.演示抛出异常
 */
public class ListOfNumbers {
    private List<Integer> list;
    private static final int SIZE = 10;

    public ListOfNumbers() {
        list = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++)
            list.add(new Integer(i));
    }

    /**
     * 使用try-catch-finally工具处理异常
     */
    public void writeList() {
        PrintWriter out = null;
        try {//todo 代码块
            out = new PrintWriter(new FileWriter("E:\\outFile.txt"));
            for (int i = 0; i < SIZE; i++) {
                out.println("Value at: " + i + "=" + list.get(i));
            }
        } catch (IOException e) { //todo 捕获异常
            e.printStackTrace();//todo 异常处理
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {//todo 关闭资源
            if (out != null) {
                System.out.println("closing printWriter");
                out.close();
            } else {
                System.out.println("printWriter is not open");
            }
        }
    }

    /**
     * 通过方法声明异常抛出：
     * 如果抛出的异常想留给用户解决，让他们根据自身情况特殊处理，就直接throws Exception,...
     * @param pathName
     * @throws IOException
     * @throws IndexOutOfBoundsException
     */
    public void writeListWithThrow(String pathName) throws IOException, IndexOutOfBoundsException {
        PrintWriter out = new PrintWriter(new FileWriter(pathName));
        for (int i = 0; i < SIZE; i++) {
            out.println("Value at: " + i + "=" + list.get(i));
        }
        out.close();
    }

}
