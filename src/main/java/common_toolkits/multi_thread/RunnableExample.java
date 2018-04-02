package common_toolkits.multi_thread;

/**
 * @author luzj
 * @description:
 * 1.通过实现Runnable接口，创建一个task
 * 2.通过new Thread(Runnable...)创建一个线程
 * 3.thread.start() 运行线程
 * @date 2018/4/2
 */
public class RunnableExample implements Runnable {
    @Override
    public void run() {
        System.out.println("Inside : "+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("Inside : "+Thread.currentThread().getName());

        System.out.println("创建Runnable...");
        Runnable runnable = new RunnableExample();

        System.out.println("创建线程...");
        Thread thread = new Thread(runnable);

        System.out.println("开启线程...");
        thread.start();
    }
}
