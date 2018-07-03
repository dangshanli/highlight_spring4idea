package queue;

/**
 * @author luzj
 * @description: 固定循环数组实现队列
 * 1 未使用泛型，默认元素为int类型
 * @date 2018/7/2
 */
public class ArrayQueue {
    private int front; //头部
    private int rear; //尾部
    private int[] arr; //数组容器，持有元素
    private int capacity;//容量

    public ArrayQueue(int capacity) {
        this.arr = new int[capacity];
        this.capacity = capacity;
        front = -1;
        rear = -1;
    }

    public void enQueue(int element) throws QueueOverFlowException {//入队
        if (rearMove() == front)//队尾指针下一步将移动到队首，则队列已满
            throw new QueueOverFlowException("队列溢出！！！");
        else {
            rear = rearMove();//一般情况的入队
            arr[rear] = element;
            if (front == -1)//空队列入队
                front = rear;
        }
    }

    private int rearMove() {//rear前推，队尾入队
        if (rear + 1 == capacity)
            return 0;
        else
            return rear + 1;
    }

    private int frontMove() {//队首出队，front前推
        if (front + 1 == capacity)
            return 0;
        else
            return front + 1;
    }

    public Integer deQueue() throws QueueUnderFlowException {
        if (isEmpty())
            throw new QueueUnderFlowException("队列已空！！！");
        int result = arr[front];//一般情况出队
        if (front == rear) {//如果队首已经退到队尾，则是最后一个元素，下一步置空队列
            front = -1;
            return result;
        }
        front = frontMove();
        return result;
    }

    public boolean isEmpty() {
        if (front == -1)//如果队首位置被置为-1，则为空队列
            return true;
        return false;
    }

    public int size() {//队列大小
        if (front == -1) {
            return 0;
        } else if (front == rear) {
            return 1;
        } else if (front < rear)
            return rear - front + 1;
        else
            return capacity - (front - rear - 1);
    }

    public Integer front() {//弹出队首
        if (front == -1)
            return null;
        return arr[front];
    }

    public static void main(String[] args) throws QueueOverFlowException, QueueUnderFlowException {
        ArrayQueue queue = new ArrayQueue(5);
        for (int i = 0; i < 5; i++) {
            queue.enQueue(i);
        }

        synchronized (ArrayQueue.class) {
            for (int i = 0; i < 3; i++) {
                System.out.println(queue.deQueue());
            }
        }

        for (int i = 0; i < 3; i++) {
            queue.enQueue(i + 10);
        }

        synchronized (ArrayQueue.class) {
            for (int i = 0; i < 5; i++) {
                System.err.println(queue.deQueue());
            }
        }

        for (int i = 20; i < 25; i++) {
            queue.enQueue(i);
        }

        synchronized (ArrayQueue.class) {
            for (int i = 0; i < 5; i++) {
                System.out.println(queue.deQueue() + "\tkkk");
            }
        }
    }
}
