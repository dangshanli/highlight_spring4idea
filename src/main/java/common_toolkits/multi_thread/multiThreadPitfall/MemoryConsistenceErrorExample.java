package common_toolkits.multi_thread.multiThreadPitfall;

/**
 * @author luzj
 * @description: 1.内存不一致性错误
 * 2.一把情况下编译器会自作主张的对代码进行一些优化，
 * 或者指令顺序调整打到优化的效果，
 * 有时会优先调用寄存器的数据而不是到主内存（寄存器更快），
 * 在一般情况下是很好的优化，但是在多线程的情况下，
 * 这些都可能导致内存不一致的问题，即主内存的数据其实已经改了，但是其他线程就是没有及时意识到，
 * 这都导致了程序没有按照预期运行
 * @date 2018/4/10
 */
public class MemoryConsistenceErrorExample {
    private static boolean sayHello = false;
    private static volatile boolean sayHello2 = false;

    /**
     * 示例，内存不一致错误
     * 理想输出：
     * Say Hello..
     * Hello World!
     * Say Bye..
     * Good Bye!
     * 真是输出：
     * say hello....
     * say bye...
     * <p>
     * 为什么：
     * 因为子线程根本没有意识到主线程已经将sayHello变量改变，所以一直在死循环，这就是内存错误导致的
     * 解决方案：使用volatile关键字
     *
     * @throws InterruptedException
     */
    public static void example() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!sayHello) {
            }

            System.out.println("hello world！");

            while (sayHello) {
            }

            System.out.println("good bye!");
        });
        thread.start();
        Thread.sleep(1000);

        System.out.println("say hello....");
        sayHello = true;

        Thread.sleep(1000);
        System.out.println("say bye....");
        sayHello = false;
    }

    /**
     * 调用使用volatile修饰的变量，表示：1.编译器不要擅自优化 2.不要调整指令顺序 3.只从主内存中读取数据
     * @throws InterruptedException
     */
    public static void example2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!sayHello2) {
            }

            System.out.println("hello world!！");

            while (sayHello2) {
            }

            System.out.println("good bye!");
        });
        thread.start();
        Thread.sleep(1000);

        System.out.println("say hello....");
        sayHello2 = true;

        Thread.sleep(1000);
        System.out.println("say bye....");
        sayHello2 = false;
    }

    public static void main(String[] args) {
        try {
//            example();
            example2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
