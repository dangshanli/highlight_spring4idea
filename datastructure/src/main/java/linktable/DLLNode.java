package linktable;

/**
 * @author luzj
 * @description: 双向链表节点
 * @date 2018/4/27
 */
public class DLLNode<T> {
    private T data;
    private DLLNode<T> next;
    private DLLNode<T> previous;

    public DLLNode() {
    }

    public DLLNode(T data, DLLNode<T> next, DLLNode<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DLLNode<T> getNext() {
        return next;
    }

    public void setNext(DLLNode<T> next) {
        this.next = next;
    }

    public DLLNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DLLNode<T> previous) {
        this.previous = previous;
    }
}
