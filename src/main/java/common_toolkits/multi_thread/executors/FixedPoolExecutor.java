package common_toolkits.multi_thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author luzj
 * @description:
 * 1.使用executors工厂创建固定大小的线程池
 * 2.当任务多于线程时，等待的任务就会加入阻塞队列中去，等线程池有空闲的线程资源，就加入进去执行
 * @date 2018/4/2
 */
public class FixedPoolExecutor {
    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        //todo 固定线程池
        System.out.println("创建固定线程池，size=2");
        ExecutorService executorService = Executors.newFixedThreadPool(2);


        //todo task1 task2 task3
        Runnable task1 = ()->{
            System.out.println("task1 Inside :"+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task2 = ()->{
            System.out.println("task2 Inside :"+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task3 = ()->{
            System.out.println("task3 Inside :"+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        System.out.println("提交tasks到Executor");
        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);

        executorService.shutdown();
    }
}
