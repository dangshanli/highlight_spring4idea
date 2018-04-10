package common_toolkits.multi_thread.callable;

import java.util.concurrent.*;

/**
 * @author luzj
 * @description: 1.使用Callable接口，创建任务
 * 2.Callable几乎等同于Runnable，不同的是他可以在call()方法中返回结果
 * 3.Callable<T>通过泛型T指定返回类型
 * 4.Callable返回的是Future类型，里面包装的是我们的泛型类型的结果对象
 * 5.future.get()获取返回值，在task结束之前这个方法处于阻塞状态
 * 6.Future对象是可以及时得到的，但是里面的值是异步得到的，他要等到task执行完毕
 * 7.future.get(time,TimeUnit)，设定task阻塞超时时间，一般task依赖远程服务或者其他的服务挂掉，
 * 会一直线程阻塞，这时设定一个超时时间，超时后会自动中断任务，抛出TimeoutException异常
 * @date 2018/4/3
 */
public class FutureAndCallableExample {

    /**
     * Callable的基础应用
     */
    public static void example1() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            System.out.println("进入Callable");
            Thread.sleep(2000);
            return "Hello from Callable";
        };

        System.out.println("提交Callable");
        Future<String> future = executorService.submit(callable);

        System.out.println("做其他事情，在Callable的结果出来之前");

        try {
            //todo 在Callable结束之前，这个方法处于阻塞状态
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    /**
     * 例子2，使用future.isDone()检测任务是否完成
     */
    public static void example2() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "hello is done";
        });

        //TODO 任务检测，不完成会一直卡在这
        //TODO main主线程和executor的新线程不断交织运行，直到executor线程结束
        while (!future.isDone()) {
            System.out.println("任务仍然在进行...");
            Thread.sleep(200);
        }

        System.out.println("任务完成");
        String result = future.get();
        System.out.println(result);

        executorService.shutdown();
    }

    public static void example3() throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        long startTime = System.nanoTime();
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(3000);
            return "hello from is cancelled";
        });

        while (!future.isDone()) {
            System.out.println("任务仍在进行...");
            Thread.sleep(200);
            double elapsedTimeInSec = (System.nanoTime() - startTime) / (1000 * 1000 * 1000.0);

            if (elapsedTimeInSec > 4)
                future.cancel(true);
        }

        if (!future.isCancelled()) {
            System.out.println("任务完成！检验结果");
            //todo 设定任务超时
            String result = future.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        } else
            System.out.println("任务被取消");

        executorService.shutdown();
    }

    public static void main(String[] args) {

        try {
            //        example1();
//            example2();
            example3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }

}
