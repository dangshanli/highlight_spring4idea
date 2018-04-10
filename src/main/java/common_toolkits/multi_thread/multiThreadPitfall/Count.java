package common_toolkits.multi_thread.multiThreadPitfall;

/**
 * @author luzj
 * @description: 简单的计数器，count作为成员变量
 * @date 2018/4/10
 */
public class Count {
    public int count;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
