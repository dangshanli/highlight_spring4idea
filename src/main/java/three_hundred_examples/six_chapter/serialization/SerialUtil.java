package three_hundred_examples.six_chapter.serialization;

import java.io.*;

/**
 * @author luzj
 * @description:
 * 1. 处理对象的序列化以及发怒系列化的工具
 * 2. T:待序列化对象的对象的类型
 * @date 2018/3/2
 */
public class SerialUtil<T> {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public SerialUtil(String path) {
        this.path = path;
    }

    public void serialObj(T obj) {
        FileOutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            outputStream = new FileOutputStream(this.getPath());
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(obj);
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deSerialObj() {
        FileInputStream in = null;
        ObjectInputStream objectInputStream = null;
        T obj = null;
        try {
            in = new FileInputStream(this.getPath());
            objectInputStream = new ObjectInputStream(in);
            obj = (T) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null)
                    objectInputStream.close();
                if (in != null)
                    in.close();
                if (obj != null)
                    System.out.println(obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
