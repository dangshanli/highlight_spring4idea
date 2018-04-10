package common_toolkits.multi_thread.lockAndAtomic;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luzj
 * @description:
 * 1.使用可重入锁，演示线程同步
 * 2.可重入锁基本功能类似synchronize，使用锁来锁定某个代码片段，使得他在执行的时候同步
 * 3.当锁空闲的时候，一个线程执行lock()，获取锁，直到返回，持有数到达1
 * 4.当锁被占有，同一个线程又去获取锁，则锁保持不变，持有数保持为1
 * 5.当一个线程持有锁，另一个线程想要获取，则处于等待状态
 * 6.相比synchronize，reentrantLock提供更多的api——功能使用
 * @date 2018/4/10
 */
public class ReentrantLockCounter {
    private final ReentrantLock lock = new ReentrantLock();

    private int count = 0;

    public void increment(){
        lock.lock();//上锁
        try{
            count++;
        }finally {
            lock.unlock();//释放锁
        }

    }
}
