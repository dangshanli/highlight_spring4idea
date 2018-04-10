package common_toolkits.multi_thread.multiThreadPitfall;

/**
 * @author luzj
 * @description: 使用同步方法的Counter
 *
 * @date 2018/4/10
 */
public class SyncCounter {
    private int count = 0;

    /**
     * increment()涉及访问共享变量，使用synchronize关键字修饰方法，
     * 使得每个线程都能在执行该方法期间独自占有共享变量
     */
    public synchronized void increment() {
        count++;
    }

    /**
     * synchronized持有对象，同步代码块，优先访问到这个方法的线程持有该对象的内部锁
     * 同样synchronize方法也是锁住当前对象
     *
     * synchronize修饰静态方法：如果synchronize锁住的是静态的方法，他持有的当前类的Class对象的锁
     */
    public void increment2() {
        synchronized (this) {
            count++;
        }
    }

    /**
     * 这个synchronize持有的是SyncCounter的Class对象锁，可以说能够锁住所有调用SyncCounter类型的代码，谨慎使用，且用且珍惜
     */
    public void increment3() {
        synchronized (SyncCounter.class) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
