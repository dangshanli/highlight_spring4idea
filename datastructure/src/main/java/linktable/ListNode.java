package linktable;

/**
 * @author luzj
 * @description: 定义链表节点对象
 * @date 2018/4/26
 */
public class ListNode<T> {
    private T date; //节点数据
    private ListNode next;//指向下一个的指针


    public ListNode() {
    }

    public ListNode(T date, ListNode next) {
        this.date = date;
        this.next = next;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
