package common_toolkits.multi_thread;

/**
 * @author luzj
 * @description:
 * 1.使用lambda表达式实现Runnable接口的实现
 * 2.lambda使得代码更加的简短
 * @date 2018/4/2
 */
public class RunnableExampleLambda {
    public static void main(String[] args) {
        System.out.println("Inside : "+Thread.currentThread().getName());

        //todo 使用lambda表达式匿名实现Runnable接口
        System.out.println("创建Runnable...");
        Runnable runnable = ()->{
            System.out.println("Inside : "+Thread.currentThread().getName());
        };

        System.out.println("创建线程...");

        Thread thread =  new Thread(runnable);
        System.out.println("开启线程...");
        thread.start();


    }

}
