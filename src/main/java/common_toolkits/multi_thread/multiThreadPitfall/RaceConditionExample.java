package common_toolkits.multi_thread.multiThreadPitfall;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author luzj
 * @description:
 * 1.存在条件竞争的并发线程
 * 2.之前的每个thread的执行各自的task，task之间毫不相干，现在每一个task的操作都涉及到
 * 同一个Count对象的公共变量count
 * 3.由于线程之间的信息没有同步，因此，最后导致执行1000次，却没有得到count==1000
 *
 * @date 2018/4/10
 */
public class RaceConditionExample {

    /**
     * 条件竞争陷阱的示例，多个线程竞争对Count对象的count变量竞争写入
     * @throws InterruptedException
     */
    public static void example() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Count counter = new Count();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(()->{
                counter.increment();
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println("最终count:"+counter.getCount());
    }

    public static void main(String[] args) {
        try {
            example();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
