package common_toolkits.multi_thread.callable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author luzj
 * @description:
 * 1.将多个Callable任务装进一个list集合，然后通过executorService.invokeAll(List)一齐执行所有的任务
 * 2.执行结果：返回一个Future的List集合
 * 3.对list元素调用future.get()的时候，需要等到所有的future都完成，否则list会处于阻塞状态
 * 4.executorService.invokeAny(List)也会提交一个Callable任务集合，但是返回最先完成的task的结果(result)，不返回future
 * @date 2018/4/9
 */
public class InvokeAllExample {

    /**
     * 使用invokeAll()提交Callable任务集合
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void example() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //todo 定义多个任务
        Callable<String> task1 = () -> {
            Thread.sleep(2000);
            return "result of task1";
        };

        Callable<String> task2 = () -> {
            Thread.sleep(1000);
            return "result of task2";
        };

        Callable<String> task3 = () -> {
            Thread.sleep(5000);
            return "result of task3";
        };

        List<Callable<String>> taskList = Arrays.asList(task1, task2, task3);

        List<Future<String>> futures = executorService.invokeAll(taskList);

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        executorService.shutdown();
    }

    /**
     * invokeAny()解决调用tasks集合
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void example2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //todo 定义多个任务
        Callable<String> task1 = () -> {
            Thread.sleep(2000);
            return "result of task1";
        };

        Callable<String> task2 = () -> {
            Thread.sleep(1000);
            return "result of task2";
        };

        Callable<String> task3 = () -> {
            Thread.sleep(5000);
            return "result of task3";
        };

        String result = executorService.invokeAny(Arrays.asList(task1, task2, task3));

        System.out.println(result);
        executorService.shutdown();
    }

    public static void main(String[] args) {
        try {
//            example();
            example2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
