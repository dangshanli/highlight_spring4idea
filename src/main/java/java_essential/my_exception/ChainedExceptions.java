package java_essential.my_exception;

import java.io.*;

/**
 * @author: luzj
 * @date: 2018/2/14
 * @description:
 * 1.抛出异常链
 * 2.打印异常相关的方法调用栈
 */
public class ChainedExceptions {

    /**
     * 异常链的抛出
     * 在异常处理中，抛出异常，形成异常链
     * 帮助用户知道什么时候一个异常导致另外一个异常
     * @param path
     * @throws SampleException
     */
    public void testChained(String path) throws SampleException {
        try{
            InputStream in = new FileInputStream(path);
        }catch (IOException e){
            throw new SampleException("other IOException",e);
        }
    }

    /**
     * 异常处理中，访问堆栈跟踪信息
     * @param path
     * @param pathTo
     */
    public void accessStack(String path,String pathTo){
        BufferedOutputStream bufferedOut = null;
        OutputStream out = null;
        BufferedInputStream buffer = null;
        InputStream in = null;

        try {
            in = new FileInputStream(path);
            buffer = new BufferedInputStream(in);
            out = new FileOutputStream(pathTo);
            bufferedOut = new BufferedOutputStream(out);
            byte[] bytes = new byte[1000];
            while (buffer.read(bytes) > 0){
                bufferedOut.write(bytes);
            }
            bufferedOut.flush();
            //todo 打印触发该Exception的方法调用栈
        } catch (FileNotFoundException cause) {
            StackTraceElement[] elements = cause.getStackTrace();
            for (int i = 0; i <elements.length ; i++) {
                System.err.println(elements[i].getFileName()+":"+
                        elements[i].getLineNumber()+">>"+
                        elements[i].getMethodName()+"()");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception cause){
            StackTraceElement[] elements = cause.getStackTrace();
            for (int i = 0; i <elements.length ; i++) {
                System.err.println(elements[i].getFileName()+":"+
                elements[i].getLineNumber()+">>"+
                elements[i].getMethodName()+"()");
            }
        }finally {
                try {
                    if (bufferedOut != null)
                        bufferedOut.close();
                    if (out != null)
                        out.close();
                    if (buffer != null)
                        buffer.close();
                    if (in !=  null)
                        in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }





    public static void main(String[] args) {
        new ChainedExceptions().accessStack("E:\\壁纸\\gina.jpg","E:\\yyy.jpg");
    }

}
