package linktable.practice;

import linktable.SingleNode;

/**
 * @author luzj
 * @description: 问题2：
 * 1.单向链表
 * 2.找到链表的倒数第k个节点
 * @date 2018/5/4
 */
public class SingleList<T> {
    private SingleNode<T> head;//持有维护链表表头

    /**
     * 获取当前单链表的长度
     *
     * @return
     */
    int size() {
        SingleNode current = head;
        int size = 0;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    /**
     * 单向链表的插入(头部插入、中部插入、尾部插入)
     *
     * @param nodeToInsert 待插入节点
     * @param position     位置
     * @return 查完后链表
     */
    public SingleNode<T> insertInLinkedList(SingleNode<T> nodeToInsert, int position) {
        // 待插入链表为空
        if (head == null)
            return nodeToInsert;
        //带插入节点为空
        if (nodeToInsert == null)
            return head;

        int size = size();
        // 无效插入位置
        if (position > size + 1 || position < 1) {
            System.out.println("节点插入位置无效,有效位置为：1——" + ("" + 1));
            return head;
        }

        if (position == 1) {// 插在首位
            SingleNode<T> cu = head;
            nodeToInsert.setNext(cu);
            head = nodeToInsert;
            return head;
        } else {//插在中间或者尾部
            SingleNode<T> previousNode = head;
            int count = 1;
            while (count < position - 1) {
                previousNode = previousNode.getNext();
                count++;
            }

            SingleNode<T> currentNode = previousNode.getNext();
            nodeToInsert.setNext(currentNode);
            previousNode.setNext(nodeToInsert);
        }

        return head;
    }

    /**
     * 问题2：
     * 获取倒数第n个节点
     * 蛮力法
     *
     * @param n
     * @return
     */
    SingleNode<T> getReversedNode1(int n) {
        int size = size();
        if (size <= (n - 1)) {
            System.err.println("链表节点数不足!!!");
            return null;
        }
        SingleNode<T> current = head;

        //从头遍历链表，直到当前节点到结尾的节点数为n，即当前节点为倒数第n个节点
        while (current != null) {
            int length = getNodeNum(current);
            if (length == n)
                break;
            current = current.getNext();
        }

        return current;
    }

    /**
     * 问题4：
     * 获取倒数第n节点，另外的实现方法
     *
     * @param n
     * @return
     */
    SingleNode<T> getReversedNode2(int n) {
        int size = size();
        if (size <= (n - 1)) {
            System.err.println("链表节点数不足!!!");
            return null;
        }

        SingleNode<T> current = head;
        int k = size - (n - 1);
        return getNodeAtPosition(k);
    }

    /**
     * 问题5：
     * 只一次扫描，求出倒数第n个节点,时间复杂度->O(n)
     * 使用双指针，始终保持指针距离为n，后面的指针移到尾部，前面的指针指向倒数第n个指针
     * @param n
     * @return
     */
    SingleNode<T> getReversedNode3(int n) {
        //保持pTemp 和 pNthNode节点
        SingleNode<T> pTemp = head,//先行节点
                      pNthNode = head;//后续节点

        int count = 1;
        while (pTemp.getNext() != null) {
            pTemp = pTemp.getNext();
            count++;
            if (count >= n)
                pNthNode = pNthNode.getNext();
        }

        if (count < n){
            System.err.println("链表大小小于n!!!");
            return null;
        }


        return pNthNode;
    }

    /**
     * 获取第n个位置的节点
     *
     * @param n
     * @return
     */
    public SingleNode<T> getNodeAtPosition(int n) {
        SingleNode<T> current = head;
        int count = 0;
        while (current != null) {
            count++;
            if (count == n)
                return current;
            current = current.getNext();
        }
        return null;
    }


    /**
     * 链表中某个节点后续节点数
     *
     * @param node
     * @return
     */
    private int getNodeNum(SingleNode node) {
        SingleNode current = node;
        int count = 0;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public static void main(String[] args) {

    }


}
