package three_hundred_examples.six_chapter.serialization;

import three_hundred_examples.XOREncrypt;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author luzj
 * @description:
 * 1.自定义writeObject()和readObject()方法控制序列化过程
 * 2.这里在自定义方法里面模拟加密
 * 3.通过PutFiled和GetFiled对，对流进行字段的写、读操作
 * @date 2018/3/3
 */
public class EncryptSerial implements Serializable {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EncryptSerial(String password) {
        this.password = password;
    }

    /**
     * 1.对序列化过程记性控制
     * 2.加密某个字段
     *
     *
     * @param objectOutputStream
     */
    private void writeObject(ObjectOutputStream objectOutputStream) {
        System.err.println("序列化：");
        ObjectOutputStream.PutField putField;
        try {
            putField = objectOutputStream.putFields();
            System.out.println("原密码是：" + this.password);
            password = XOREncrypt.xOREnc(password);
            putField.put("password", password);
            System.out.println("加密后的密码：" + password);
            objectOutputStream.writeFields();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.反序列化控制过程
     * 2.解密password
     *
     * @param inputStream
     */
    private void readObject(ObjectInputStream inputStream) {
        System.err.println("反序列化：");
        ObjectInputStream.GetField getField;
        try {
            getField = inputStream.readFields();
            password = (String) getField.get("password", "");
            System.out.println("加密密码：" + password);
            password = XOREncrypt.xOREnc(password);
            System.out.println("解密密码：" + password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String path = "src/main/resource/encrypt.ser";
        String password = "fuck ada wong";
        SerialUtil<EncryptSerial> serialSerialUtil = new SerialUtil<>(path);
        EncryptSerial encryptSerial = new EncryptSerial(password);

        serialSerialUtil.serialObj(encryptSerial);
        System.out.println("============================================");
        serialSerialUtil.deSerialObj();
    }


}
