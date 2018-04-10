package common_toolkits.multi_thread.lockAndAtomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luzj
 * @description:
 * 1.调用可重入锁示例
 * @date 2018/4/10
 */
public class ReentrantLockMethodsExample {
    public static void example(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ReentrantLockMethodsCounter lockMethodsCounter = new ReentrantLockMethodsCounter();

        executorService.submit(()->{
            try {
                System.out.println("第一个线程："+lockMethodsCounter.incrementAndGet()+"\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(()->{
            try {
                System.out.println("第二个线程："+lockMethodsCounter.incrementAndGet()+"\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

    public static void main(String[] args) {
        example();
    }
}
