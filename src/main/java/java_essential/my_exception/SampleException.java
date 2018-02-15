package java_essential.my_exception;

/**
 * @author: luzj
 * @date: 2018/2/14
 * @description: 自定义异常，用于异常链
 */
public class SampleException extends Exception{
    public SampleException(String message,Throwable cause){
        super(message,cause);
    }
}
