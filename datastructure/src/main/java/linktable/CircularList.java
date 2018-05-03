package linktable;

/**
 * @author luzj
 * @description: 单向循环链表
 * @date 2018/5/3
 */
public class CircularList<T> {

    /**
     * 返回链表长度，时间复杂度->O(n)
     *
     * @param headNode 链表头节点
     * @return
     */
    int length(CLLNode<T> headNode) {
        int length = 0;
        CLLNode currentNode = headNode;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.getNext();
            if (currentNode == headNode)//再次循环到头部节点时，终结遍历
                break;
        }
        return length;
    }

    /**
     * 遍历打印节点数据,时间复杂度->O(n)
     *
     * @param headNode
     */
    void printData(CLLNode<T> headNode) {
        CLLNode currentNode = headNode;
        while (currentNode != null) {
            System.out.println(currentNode.getData());
            currentNode = currentNode.getNext();
            if (currentNode == headNode)
                break;
        }
        System.out.println("(" + currentNode.getData() + ")headNode");
    }

    /**
     * 在链表尾部插入节点,时间复杂度->O(n)
     *
     * @param headNode     链表表头
     * @param nodeToInsert 待插入节点
     */
    void insertAtEnd(CLLNode<T> headNode, CLLNode<T> nodeToInsert) {
        CLLNode currentNode = headNode;

        while (currentNode.getNext() != headNode)//currentNode节点更新为尾部节点
            currentNode = currentNode.getNext();

        nodeToInsert.setNext(nodeToInsert);//新插节点指向自身

        if (headNode == null)//实际上几乎不可能放生
            headNode = nodeToInsert;
        else {
            nodeToInsert.setNext(headNode);
            currentNode.setNext(nodeToInsert);
        }
    }

    /**
     * 在链表的表头插入，与表尾插入不通的是，需要更新待插入节点为表头节点
     * 时间复杂度->O(n)
     *
     * @param headNode     链表表头
     * @param nodeToInsert 待插入的节点
     */
    void insertAtBegin(CLLNode<T> headNode, CLLNode<T> nodeToInsert) {
        CLLNode currentNode = headNode;

        while (currentNode.getNext() != headNode)
            currentNode = currentNode.getNext();

        nodeToInsert.setNext(nodeToInsert);

        if (headNode == null)
            headNode = nodeToInsert;
        else {
            nodeToInsert.setNext(headNode);
            currentNode.setNext(nodeToInsert);
            headNode = nodeToInsert;//更新待插入节点为表头节点
        }
    }

    /**
     * 节点插入链表中间
     *
     * @param headNode
     * @param nodeToInsert
     * @param position
     */
    void insertAtMid(CLLNode<T> headNode, CLLNode<T> nodeToInsert, int position) {
        int size = length(headNode);
        if (position < 1 || position > (size + 1)) {
            System.out.println("position越界，应为1->" + (size + 1));
            return;
        }

        if (position == 1) {//position == 1,插入头部
            insertAtBegin(headNode, nodeToInsert);
            return;
        }

        if (position == (size + 1)) {//position == size+1,插入尾部
            insertAtEnd(headNode, nodeToInsert);
            return;
        }


        int count = 0;
        CLLNode previousNode = headNode;
        while (count < position - 1) {//拿到插入位置的前驱节点
            previousNode = previousNode.getNext();
            count++;
        }

        CLLNode currentNode = previousNode.getNext();
        previousNode.setNext(nodeToInsert);
        nodeToInsert.setNext(currentNode);
    }

    /**
     * 删除尾部节点
     *
     * @param headNode
     */
    void deleteLastNode(CLLNode<T> headNode) {
        CLLNode currentNode = headNode;//要删除的节点
        CLLNode previousNode = headNode;//被删除节点的前置节点

        if (headNode == null) {
            System.out.println("空链表!!!");
            return;
        }

        //拿到要删除的节点和他的前置节点
        while (currentNode.getNext() != headNode) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        previousNode.setNext(headNode);
        currentNode = null;
        return;
    }

    /**
     * 删除头部节点
     * @param headNode 链表表头节点
     */
    void deleteFrontNode(CLLNode<T> headNode){
        CLLNode temp = headNode;//持有头部节点
        CLLNode current = headNode;

        if (headNode == null){
            System.out.println("空链表!!!");
            return;
        }

        while (current.getNext() != headNode)
            current = current.getNext();

        current.setNext(headNode.getNext());//修改尾部节点的后继节点为第二节点
        headNode = headNode.getNext();//重置头部节点
        temp = null;
        return;
    }

    public static void main(String[] args) {


    }


}
