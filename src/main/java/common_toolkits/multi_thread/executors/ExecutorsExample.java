package common_toolkits.multi_thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luzj
 * @description: 通过executorService创建、管理线程，提交任务
 * 1.Executors框架功能：线程创建 线程管理 任务提交、执行
 * 2.线程的创建是开销很大的行为，一个线程资源完成一个task就销毁时莫大的浪费，Executor可以做到线程资源可复用
 * 3.主要API：Executor接口 executorService接口(继承executor) scheduledExecutorService(继承executorService)
 * 4.shutdown() 和 shutdownNow()关闭ExecutorService
 * @date 2018/4/2
 */
public class ExecutorsExample {
    public static void main(String[] args) {
        System.out.println("Inside ： "+ Thread.currentThread().getName());

        //todo Executors是一个工厂类，可以创建不通类型的ExecutorService
        System.out.println("创建Executor服务...");
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        System.out.println("创建一个Runnable...");
        Runnable runnable = ()->{
            System.out.println("Inside : "+Thread.currentThread().getName());
        };

        System.out.println("通过executorService服务提交任务");
        executorService.submit(runnable);

        System.out.println("关闭executor");
        executorService.shutdown();
    }

}
