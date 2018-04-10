package common_toolkits.multi_thread.lockAndAtomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luzj
 * @description:
 * 1.使用可重入锁的api,进行更细粒度的控制
 * 2.使用tryLock()尝试获取锁，如果没能获取则不会等待，立马返回线程
 * 3.tryLock(t,Unit)，则在不能得到锁时，先暂停等待t个时间单位，仍不能则返回线程
 * @date 2018/4/10
 */
public class ReentrantLockMethodsCounter {
    private final ReentrantLock lock = new ReentrantLock();

    private int count = 0;

    /**
     * 使用tryLock()获取锁
     * @return
     * @throws InterruptedException
     */
    public int incrementAndGet() throws InterruptedException {
        System.out.println("是否锁定："+lock.isLocked());
        System.out.println("是否被当前线程持有："+lock.isHeldByCurrentThread());
        boolean isAcquired = lock.tryLock(1, TimeUnit.SECONDS);

        System.out.println("获取锁："+isAcquired+"\n");

        if (isAcquired){
            try{
                Thread.sleep(2000);
                count++;
            }finally {
                lock.unlock();
            }
        }
        return count;
    }


}
