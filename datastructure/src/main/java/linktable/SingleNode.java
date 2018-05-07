package linktable;

/**
 * @author luzj
 * @description: 定义链表节点对象
 * @date 2018/4/26
 */
public class SingleNode<T> {
    private T date; //节点数据
    private SingleNode next;//指向下一个的指针


    public SingleNode() {
    }

    public SingleNode(T date, SingleNode next) {
        this.date = date;
        this.next = next;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public SingleNode getNext() {
        return next;
    }

    public void setNext(SingleNode next) {
        this.next = next;
    }
}
