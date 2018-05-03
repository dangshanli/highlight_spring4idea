package linktable;

/**
 * @author luzj
 * @description: 单向循环链表节点
 * @date 2018/5/2
 */
public class CLLNode<T> {
    private T data;
    private CLLNode<T> next;

    public CLLNode() {
    }

    public CLLNode(T data, CLLNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CLLNode<T> getNext() {
        return next;
    }

    public void setNext(CLLNode<T> next) {
        this.next = next;
    }
}
