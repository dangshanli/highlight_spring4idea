package common_toolkits.multi_thread.lockAndAtomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luzj
 * @description:
 * 1.java的并发包里面提供一些原子类型，基本对应几个基本类型，这些类型作为共享变量时可以实现原子操作
 * 2.即通过现代CPU的比较-交换指令，支持完成同步
 * 3.这些指令比我们一般使用的锁快很多
 * 4.这里演示的是AtomicInteger
 * @date 2018/4/11
 */
public class AtomicCounter {
    private AtomicInteger count = new AtomicInteger();

    public int incrementAndGet(){
        return count.incrementAndGet();
    }

    public int getCount(){
        return count.get();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        AtomicCounter counter = new AtomicCounter();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(()->{
                counter.incrementAndGet();
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("最终结果："+counter.getCount());
    }
}
