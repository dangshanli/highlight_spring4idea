package linktable;

/**
 * @author luzj
 * @description: 松散单向链表：
 * 将n个节点的数据组成 n^(1/2)个块节点，节点间组成单向链表
 * 每个节点内部持有一个 n^(1/2)个大小的循环链表作为data，和一个指向下一个节点块的引用
 * @date 2018/5/3
 */
public class BlockSNode<T> {
    private CLLNode<T> cllNode;//循环链表的表头节点
    private BlockSNode<T> next;//下一个节点块的引用

    public BlockSNode() {
    }

    public BlockSNode(CLLNode<T> cllNode, BlockSNode<T> next) {
        this.cllNode = cllNode;
        this.next = next;
    }

    public CLLNode<T> getCllNode() {
        return cllNode;
    }

    public void setCllNode(CLLNode<T> cllNode) {
        this.cllNode = cllNode;
    }

    public BlockSNode<T> getNext() {
        return next;
    }

    public void setNext(BlockSNode<T> next) {
        this.next = next;
    }
}
