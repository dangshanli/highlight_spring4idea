package common_toolkits.multi_thread;

/**
 * @author luzj
 * @description:
 * 1.join(int s)方法把当前线程变成wait,直到执行join的线程执行了s时长或者执行完毕
 * 2.当前例子，主线程wait,执行thread1 2秒，之后再还给主线程，主线程开启线程2，然后3线程并发执行
 * @date 2018/4/2
 */
public class ThreadJoinExample {
    public static void main(String[] args) {
        //todo 创建线程1
        Thread thread1 = new Thread(()->{
            System.out.println("进入线程1");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("退出线程1");
        });

        //todo 创建线程2
        Thread thread2 = new Thread(()->{
            System.out.println("进入贤臣2");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("退出线程2");
        });

        System.out.println("开启线程1");
        thread1.start();

        System.out.println("等待线程1完成");
        try {
            thread1.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("等待完毕,开启线程2");
        thread2.start();
    }

}



