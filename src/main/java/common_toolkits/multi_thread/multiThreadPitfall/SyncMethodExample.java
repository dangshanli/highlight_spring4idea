package common_toolkits.multi_thread.multiThreadPitfall;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author luzj
 * @description:
 * 1.将共享变量进行同步，解除线程间的竞争
 * 2.使用synchronize就是将并发访问该方法的多线程，变成了序列执行，也就是强制不在并发了
 * 所以他是牺牲了并发的性能优势换来的安全性
 *
 * 3.因此，synchronize锁定的代码粒度一定要尽量小，能锁代码块，不要锁方法，能锁一句表达式，不要锁一个代码块
 * @date 2018/4/10
 */
public class SyncMethodExample {
    public static void example() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        SyncCounter syncCounter = new SyncCounter();

        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                syncCounter.increment();
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println("最终count:" + syncCounter.getCount());
    }

    public static void main(String[] args) {
        try {
            example();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
