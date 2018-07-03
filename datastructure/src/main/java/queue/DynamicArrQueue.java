package queue;

/**
 * @author luzj
 * @description: 动态数组队列实现
 * 1 相比于普通循环数组，动态数组在入队的时候，一旦大小不够会重置数组大小
 * @date 2018/7/2
 */
public class DynamicArrQueue {
    private int front;
    private int rear;
    private int capacity;
    private int[] arr;

    public DynamicArrQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = rear = -1;
    }

    public DynamicArrQueue() {
        capacity = 1;
        front = rear = -1;
        arr = new int[1];
    }

    /**
     * front索引被置为-1，则为空
     *
     * @return
     */
    public boolean isEmpty() {
        return (front == -1);
    }

    /**
     * 尾部下一步就要走到front位置了，则判为溢出
     *
     * @return
     */
    public boolean isFull() {
        return ((rear + 1) % capacity == front);
    }

    /**
     * 1 空队列，为0
     * 2 通过 容量-前后索引相差元素 ->元素个数
     * 3 如果公式计算容量为0，则大小为capacity
     *
     * @return
     */
    public int size() {
        if (front == -1) return 0;
        int size = (capacity - front + rear + 1) % capacity;
        if (size == 0)
            return capacity;
        else
            return size;
    }

    /**
     * 重置数组大小
     * 1 创建新数组，大小为cap * 2
     * 2 将原数组元素复制过去
     * 3 如果rear在front后面，则需要将front之后的元素移动到新数组的尾部，这样保持整个链条不断
     * 3-1 另外一种方案是，将 [0-front) 的尾部元素，迁移到后面去，这里采用的就是这种方案
     * 4 在纸上画一个整个过程示意图就会很明了,最后注意调整front或者rear的值
     */
    private void resizeQueue() {
        int init = capacity;
        capacity *= 2;
        int[] old = arr;
        arr = new int[capacity];
        for (int i = 0; i < old.length; i++) {//复制旧数组元素
            arr[i] = old[i];
        }

        if (rear < front) {//调整尾部元素的位置，当尾部在front之前的时候
            for (int i = 0; i < front; i++) {
                arr[i + init] = arr[i];
            }
            rear = rear + init;
        }
    }

    /**
     * 入队
     *
     * @param data
     */
    public void enQueue(int data) {
        if (isFull())//满则扩容
            resizeQueue();
        rear = (rear + 1) % capacity;//计算下一个尾部索引
        arr[rear] = data;
        if (front == -1)//空队列则重置front索引
            front = rear;
    }

    /**
     * 出队
     * @return
     * @throws QueueUnderFlowException 尝试对空队列取值
     */
    public Integer deQueue() throws QueueUnderFlowException {
        Integer data = null;
        if (isEmpty())
            throw new QueueUnderFlowException("队列下溢！！！");
        else {
            data = arr[front];
            if (front == rear)//当首尾索引重合时，下一步为空队列，front置为-1
                front = -1;
            else
                front = (front + 1) % capacity;
        }
        return data;
    }


}
