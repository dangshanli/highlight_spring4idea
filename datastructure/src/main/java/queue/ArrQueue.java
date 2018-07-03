package queue;

/**
 * @author luzj
 * @description: 书上范例实现
 * 1 对比我个人的实现版本，是在处理指针前推上面处理的非常简洁
 * 2 直接使用 (front+1)%capacity来计算下一个指针下标
 * @date 2018/7/2
 */
public class ArrQueue {
    private int front;
    private int rear;
    private int[] arr;
    private int capacity;

    public ArrQueue(int capacity) {
        arr = new int[capacity];
        this.capacity = capacity;
        front = rear = -1;
    }

    public boolean isEmpty() {//是否空
        return (front == -1);
    }

    public boolean isFull() {//是否满，队尾指针下一步就要移到队首就是满了
        return ((rear + 1) % capacity == front);
    }

    public int size() {//队列元素个数
        return (capacity - front + rear + 1) % capacity;
    }

    public void enQueue(int data) throws QueueOverFlowException {//入队
        if (isFull())
            throw new QueueOverFlowException("队列溢出！！！");
        rear = (rear + 1) % capacity;
        arr[rear] = data;
        if (front == -1)//空队列首次入队
            front = rear;
    }

    public Integer deQueue() throws QueueUnderFlowException {//出队
        Integer data = null;
        if (isEmpty())
            throw new QueueUnderFlowException("队列下溢！！！");
        data = arr[front];
        if (front == rear)//如果已经只剩下最后一个元素
            front = -1;
        else
            front = (front + 1) % capacity;

        return data;
    }


}
