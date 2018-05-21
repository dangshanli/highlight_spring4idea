package linktable.practice;

import linktable.SingleNode;
import org.omg.CORBA.INTERNAL;

/**
 * @author luzj
 * @description: 1.单向链表的节点编号为[1, 2..., n], 节点编号是node的数据
 * 2..从表头开始寻找最后一个满足 i mod k == 0的节点
 * @date 2018/5/21
 */
public class ModNode {


    /**
     * 问题44：求出最后一个模节点, 时间复杂度为O(n)
     *
     * @param head
     * @param k
     * @return
     */
    SingleNode<Integer> findModNode(SingleNode<Integer> head, int k) {
        SingleNode<Integer> current = head, temp = null;

        while (current != null) {
            int num = current.getDate();
            if (num % k == 0)
                temp = current;

            current = current.getNext();
        }

        return temp;
    }

    /**
     * 问题45：从表尾开始计算的为 num % k == 0的节点 ，比如n = 19,k = 3,那么倒数的话就是17
     *
     * @param head
     * @param k
     * @return
     */
    SingleNode<Integer> findModNodeFromEnd(SingleNode<Integer> head, int k) {
        SingleNode<Integer> current = head, modeNode = head;

        int count = 1;
        while (current != null) {
            if (count == k)
                break;
            count++;
            current = current.getNext();
        }

        while (current.getNext() != null) {
            current = current.getNext();
            modeNode = modeNode.getNext();
        }

        return modeNode;
    }

    /**
     * 问题46： 寻找第 n/k 个节点
     * 使用双指针
     *
     * @param head
     * @param k
     * @return
     */
    SingleNode<Integer> findFractionNode(SingleNode<Integer> head, int k) {
        SingleNode<Integer> p1 = head, p2 = null;
        int count = 1;
        while (p1.getNext() != null) {
            if (count % k == 0) {//p1每走k步，p2走一步
                if (p2 == null)//首次赋值给p2，相当于p2走一步
                    p2 = head;
                else
                    p2 = p2.getNext();//p2走一步
            }
            p1 = p1.getNext();
            count++;
        }

        return p2;
    }

    static SingleNode<Integer> myHead;

    static {
        //todo 构建单向链表
        myHead = new SingleNode<>();
        myHead.setDate(1);
        SingleNode<Integer> current = myHead;
        for (int i = 2; i <= 25; i++) {
            SingleNode<Integer> node = new SingleNode<>();
            node.setDate(i);

            current.setNext(node);
            current = current.getNext();
        }
    }

    void testQue46() {
        SingleNode<Integer> s = findFractionNode(myHead, 3);
        System.out.println(s.getDate());
    }

    void testQue45() {
        SingleNode<Integer> node = findModNodeFromEnd(myHead, 3);
        System.out.println(node.getDate());
    }

    void testQue44() {
        int k = 9;
        SingleNode<Integer> node = findModNode(myHead, k);
        System.out.println(node.getDate());
    }

    public static void main(String[] args) {
        ModNode modNode = new ModNode();
//        modNode.testQue44();
//        modNode.testQue45();
        modNode.testQue46();


    }
}
