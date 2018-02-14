package some_skill.exception_best_practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author: luzj
 * @date: 2018/2/12
 * @description: 关于异常处理的最佳实践与建议，9条
 */
public class ExceptionPractice {

    Logger logger = LoggerFactory.getLogger(this.getClass());//slf4j记录日志

    /**
     * 最佳实践1：
     *
     * 1.关闭资源(xxx.close)的代码不要放在try代码块中-->因为当执行到异常时可能根本没有执行到关闭资源的代码，
     * 就跳到异常部分
     * <p>
     * 2.解决方案：
     * **a.在finally中关闭
     * **b.使用try-with-resource语法
     * <p>
     * 当前closeResourceInFinally(pathname)使用的是finally关闭资源方案
     *
     * @param pathName
     */
    public void closeResourceInFinally(String pathName) {
        FileInputStream inputStream = null;

        try {
            File file = new File(pathName);
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    /**
     * 最佳实践2：
     *
     * 这就是上面的b方案，采用try-with-resource方案
     * <p>
     * 要求：
     * 1.你的资源实现了AutoCloseable标记接口，大部分标准Java资源都实现了这个接口
     * 2.使用try()子句括住你要获取的资源，然后再抛出异常
     * <p>
     * 结果：
     * 1.当try运行完毕或者遇到了异常，处理完毕后自动关闭try子句的资源
     * 2.在try代码块中一般写的就是怎么利用try()中打开的资源进行读写的
     *
     * @param pathName
     */
    public void automaticallyCloseResource(String pathName) {
        File file = new File(pathName);
        try (FileInputStream inputStream = new FileInputStream(file);) {
            //使用输入流读取之类的操作
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 最佳实践3：
     *
     * 1.抛出异常越明确越好，比如抛出NumberFormatException就比抛出IllegalArgumentException要好
     * 因为更加的指定精确，前者表示是输入的数值格式不对，后者表示只是参数异常，更加笼统
     * <p>
     * 2.使用@throws在javadoc注释中明确说明异常状况
     *
     * @throws NumberFormatException 如果发生这种异常，说明...
     */
    public void doNotDoThis() throws NumberFormatException {
        //...
    }


    /**
     * 最佳实践4：
     *
     * 抛出异常时可以使用logger简要的打印出一些提示短语
     */
    public void userLogger() {
        try {
            new Long("xyz");
        } catch (NumberFormatException e) {
            logger.error("new long的时候出错了,数据格式不对!!!", e);
        }

    }

    /**
     *最佳实践5：
     *
     * catch异常的时候，优先catch最具体的异常，
     * 比如NumberFormatException优先于IllegalArgumentException，
     * 因此优先具体的在后面追加不具体的异常。
     *
     * 代码执行的时候会优先匹配第一个捕获的异常，如果不具体的异常被匹配，后面就不会执行
     *
     */
    public void catchMostSpecificExceptionFirst(){
        try{
            new ExceptionPractice().userLogger();
        }catch (NumberFormatException e){
            logger.error("数值格式不对",e);
        }catch (IllegalArgumentException e){
            logger.error("非法参数",e);
        }
    }

    /**
     * 最佳实践6：
     *
     * Throwable是所有异常和错误的超类，可以catch他，但不要这么做
     * 因此，他还能捕获错误，典型的是栈溢出、内存溢出等。这种问题是无法在应用中解决的，
     * 他应该在应用控制之外解决。
     */
    public void doNotCatchThrowable(){
        try{
            new ExceptionPractice().userLogger();
        }catch (Throwable t){
        //不要捕获Throwable类
        }
    }


    /**
     * 最佳实践7：
     *
     * 不要忽视任何一个抛出的异常，即使你觉得他可能永远不会发生。
     *
     * 因为你的代码块可能还要依赖其他的对象，他们的状态你是不可能预料，
     * 你永远不知别人拿你的类做什么事。
     *
     * 此时，你最好写一条Logger日志，告诉别人最不可能的事件发生了
     */
    public void doNotIgmoreException(){
        try{
            //do something
        }catch (NumberFormatException e){
            logger.error("this will never happen:"+e);
        }
    }

    /**
     * 最佳实践8：
     *
     * 即使一些代码库都可能会这么做，但是请不要这么做。
     * 因为，不断重复抛出会使同一个异常重复写多个错误消息。
     */
    public void doNotThrowExceptionRepeat(){
        try{
            new Long("xyz");
        }catch (NumberFormatException e){
            logger.error("数值格式不对",e);
            //throw e;  //重新抛出该异常
        }
    }

    /**
     * 最佳实践9：
     *可以再标准异常处理中抛出自定义异常，一般是个应用业务相关的异常
     *
     *一般使用这个异常包装异常信息
     * @param input
     * @throws MyBusinessException
     */
    public void wrapException(String input) throws MyBusinessException {
        try{
            //do something
        }catch (NumberFormatException e){
            throw new MyBusinessException("描述错误的信息",e);
        }
    }

    public static void main(String[] args) {
        new ExceptionPractice().userLogger();
    }


}
