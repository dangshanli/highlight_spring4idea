package queue;

import linktable.SingleNode;

/**
 * @author luzj
 * @description: 链表实现队列
 * @date 2018/7/3
 */
public class LinkedQueue {
    private SingleNode<Integer> front;//
    private SingleNode<Integer> rear;

    public LinkedQueue() {
        front = null;
        rear = null;
    }

    public boolean isEmpty(){
        return (front == null);
    }

    public void enQueue(Integer data) {//入队
        SingleNode<Integer> node = new SingleNode<>(data);

        if (front == null) {//空队列入队
            front = node;
            rear = node;
        }

        rear.setNext(node);
        rear = node;
    }

    public Integer deQueue() throws QueueUnderFlowException {//出队
        if (front == null)
            throw new QueueUnderFlowException("队列下溢！！！");
        else {
            Integer data = front.getDate();
            front = front.getNext();
            return data;
        }
    }
}
