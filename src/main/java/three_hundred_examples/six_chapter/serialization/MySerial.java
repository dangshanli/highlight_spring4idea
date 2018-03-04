package three_hundred_examples.six_chapter.serialization;

import java.io.*;

/**
 * @author luzj
 * @description: 1.对象序列化的演示
 * <p>
 * 2.为什么需要对象序列化：
 * - 对象在IO或者Socket传输过程需要将信息转化成binary文件
 * - 这个binary文件保存着对象的变量状态、类型信息、引用类型信息
 * - 反序列化：即将binary文件转化成对象，放在内存中
 * <p>
 * 3.序列化一般见之于IO或者网络传输，即在传输中binary字节必须按照序列化的方式传输过去
 * <p>
 * 4.在使用ObjectInputStream等装饰类时，也必须要求传输对象是 implements Serializable 的类
 * <p>
 * 5.对象序列化几点关键备注：
 *      - 在client(C)---server（S）模型中，C将对象O序列化到S中，C和S的对应的类要保持
 *      1)类路径一样 2）类功能一样  3）serialVersionUID一样，否则会反序列化失败
 *      - transient变量 和 static变量不参与序列化，一个只是暂时变量，一个是属于类的变量，而不属于对象
 *      - 父类未实现序列化接口，子类实现序列化接口。进行子类序列化时，从父类继承的属性，在反序列化时得到的值域序列化不同，属于初始值。
 *      如要，全部属性都序列化，父类也必须进行序列化。
 *
 *  6.对象序列化的前处理：
 *          - 在对象序列化过程中，虚拟机会有限调用序列化对象的writeObject()和readObject()
 *          - 如果序列化对象未定义这些方法，就会调用ObjectOutputStream的defaultWriteObject()方法
 *          - 根据这一原理，可以在序列化对象内部定义一个writeObject(...)和readObject(...)方法控制序列化过程，简称前处理
 *
 *  7.对此写入同一个对象到流里面，磁盘只会保存一次对象，第二次以后只会增加一些引用信息。虚拟机会无视第一次存储后对象状态的变化，只保存第一次对象的状态
 * @date 2018/3/2
 */
public class MySerial implements Serializable {
    private static final long serialVersionUID = 1L;
    public String name;
    public int code;

    public transient int SNN;

    public MySerial(String name) {
        this.name = name;
    }

    public MySerial(String name, int code) {
        this.name = name;
        this.code = code;
        this.SNN = 40;
    }

    @Override
    public String toString() {
        return "name:" + name + "code:" + code + "SNN:" + SNN + ",hashcode:" + this.hashCode();
    }


    /**
     * 序列化MySerial对象
     */
    public static void serialObj() {
        MySerial mySerial = new MySerial("你的名字", 30);
        mySerial.SNN = 50;
        FileOutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            outputStream = new FileOutputStream("src/main/resource/myserial.ser");
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(mySerial);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(mySerial);
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

    public static void deSerialObj() {
        FileInputStream in = null;
        ObjectInputStream objectInputStream = null;
        MySerial mySerial2 = null;
        try {
            in = new FileInputStream("src/main/resource/myserial.ser");
            objectInputStream = new ObjectInputStream(in);
            mySerial2 = (MySerial) objectInputStream.readObject();
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
                if (mySerial2 != null)
                    System.out.println(mySerial2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        serialObj();
        System.out.println("==========================================");
        deSerialObj();
    }

}
