package three_hundred_examples.seven_chapter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author luzj
 * @description:利用反射创建类的实例
 * @date 2018/3/6
 */
public class RefInstanceCreate {

    /**
     * 使用反射创建对象，利用Constructor类可以用带参数的构造器创建对象
     */
    public void createInstance() {
        Constructor<File> constructor;
        try {
            constructor = File.class.getDeclaredConstructor(String.class);
            //todo 使用反射创建File对象
            File file = constructor.newInstance("E:\\明日科技.txt");
            System.out.println("使用File对象创建文件：明日科技.txt");
            //todo 创建新文件
            file.createNewFile();
            System.out.println("文件是否创建成功：" + file.exists());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RefInstanceCreate instanceCreate = new RefInstanceCreate();
        instanceCreate.createInstance();
    }


}
