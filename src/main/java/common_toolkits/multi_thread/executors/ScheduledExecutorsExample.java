package common_toolkits.multi_thread.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author luzj
 * @description:
 * @date 2018/4/2
 */
public class ScheduledExecutorsExample {

    /**
     * 调度executor服务，指定时间后延迟执行
     */
    public static void delaySchedule(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = ()->{
            System.out.println("执行任务在"+System.nanoTime());
        };

        System.out.println("提交任务在"+System.nanoTime());

        scheduledExecutorService.schedule(task,5, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }

    /**
     * 定时周期执行线程
     */
    public static void fixedRateSchedule(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable task = ()->{
            System.out.println("执行任务在"+System.nanoTime());
        };

        System.out.println("0秒延迟执行，2秒周期执行");
        scheduledExecutorService.scheduleAtFixedRate(task,0,2,TimeUnit.SECONDS);
//        scheduledExecutorService.shutdown();

    }

    public static void main(String[] args) {
//        delaySchedule();
        fixedRateSchedule();
    }
}
