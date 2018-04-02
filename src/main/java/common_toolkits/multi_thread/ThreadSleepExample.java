package common_toolkits.multi_thread;

/**
 * @author luzj
 * @description:
 * 1.通过Thread.sleep()让当前执行的线程暂停指定的时间
 * @date 2018/4/2
 */
public class ThreadSleepExample {
    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        String[] messages = {"If I can stop one heart from breaking,",
                "I shall not live in vain.",
                "If I can ease one life the aching,",
                "Or cool one pain,",
                "Or help one fainting robin",
                "Unto his nest again,",
                "I shall not live in vain"};

        //todo 使用sleep(int s)暂停当前线程s时长
        Runnable runnable = ()->{
            System.out.println("Inside : "+Thread.currentThread().getName());
            for (String message: messages) {
                System.out.println(message);
                try {
                    Thread.sleep(2000);
                    //todo 当前线程被其他线程中断，则抛出InterruptedException异常
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
