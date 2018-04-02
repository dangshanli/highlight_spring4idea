package common_toolkits.multi_thread;

/**
 * @author luzj
 * @description:
 * 1.通过继承Thread创建一个新线程
 * 2.每一个线程都有一个名称，可以在构造器里自定义，也可以使用默认的Thread-0、Thread-1等
 * 3.Thread.currentThread()会获取当前正在执行的线程
 * @date 2018/4/2
 */
public class ThreadExample extends Thread {
    public ThreadExample() {
        super();
    }

    public ThreadExample(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Inside : "+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("Inside : "+Thread.currentThread().getName());

        System.out.println("创建新线程");
        Thread thread = new ThreadExample("mika");
        System.out.println("开始线程...");
        thread.start();
    }
}
