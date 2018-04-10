package common_toolkits.multi_thread.lockAndAtomic;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author luzj
 * @description: 1.读写锁，是读写分离的锁，在大量读少量写的代码片段上非常有用
 * 2.当没有线程持有写锁时，可以多线程并发读取
 * 3.当一旦有任意一个线程持有写对象时，所有的持有读线程暂停
 * @date 2018/4/11
 */
public class ReadWriteCount {
    ReadWriteLock lock = new ReentrantReadWriteLock();

    private int count = 0;

    /**
     * 上写锁
     *
     * @return
     */
    public int incrementAndGetCount() {
        lock.writeLock().lock();
        try {
            count++;
            return count;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 上读锁
     *
     * @return
     */
    public int getCount() {
        lock.readLock().lock();
        try {
            return count;
        } finally {
            lock.readLock().unlock();
        }
    }

}
