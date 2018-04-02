package common_toolkits.multi_thread;

/**
 * @author luzj
 * @description:
 * 1.通过匿名类实现Runnable接口，使得代码更加简洁、紧凑
 * @date 2018/4/2
 */
public class RunnableExampleAnonymous {

    public static void main(String[] args) {
        System.out.println("Inside : "+Thread.currentThread().getName());

        //todo 匿名类实现Runnable接口
        System.out.println("创建Runnable...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside : "+Thread.currentThread().getName());
            }
        };

        System.out.println("创建线程 ...");
        Thread thread = new Thread(runnable);

        System.out.println("开启线程...");
        thread.start();
    }
}
