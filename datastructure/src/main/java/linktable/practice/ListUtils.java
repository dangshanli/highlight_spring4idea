package linktable.practice;

import linktable.SList;
import linktable.SingleNode;
import org.apache.poi.ss.formula.functions.T;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luzj
 * @description: 判断链表是否是循环链表
 * @date 2018/5/5
 */
public class ListUtils {

    /**
     * 问题6：判断一个链表是否为循环链表，比如：A->B->C->D->E->F->C
     * 蛮力法：
     * - 从表头开始遍历链表，持有当前节点，然后遍历整个链表和看是否有节点也指向当前节点，是则循环
     * 缺点：无法确定循环终止条件，循环链表可能无线循环，不适用
     * <p>
     * 问题7：
     * 判断链表是否有环
     * 方法：
     * 1.遍历链表，每遍历过一个就放进散列表中去
     * 2.每遍历一个新的节点，就在散列中检索是有已有
     * 3.知道结束：有，则循环链表；没有，则不是循环链表
     * <p>
     * 改进散列取值后，时间仅为测试循环开销O(n)，之前是O(n^2)
     * <p>
     * 问题8：使用排序法是否可以测试环的问题？
     * - 遍历每个节点，他的后继节点地址保存到数组中去
     * - 将节点保存到数组中去
     * - 排序数组，之后检测有相邻节点地址相同即可得出此为循环链表
     * 缺点：一旦遇到循环链表，无法判定终结位置，因此盲目遍历可能死循环。必须要先确定链表长度。
     * 因此，不适用。
     *
     * @return
     */
    boolean isCircularList(SingleNode<Integer> head) {
        Map<Integer, SingleNode<Integer>> map = new HashMap<>();
        SingleNode<Integer> current = head;

        while (current != null) {
            if (isInMap(map, current)) {
                System.out.println("交叉节点data:" + current.getDate());
                return true;
            }
            map.put(current.hashCode(), current);
            current = current.getNext();
        }
        return false;
    }


    /**
     * 检测是否在map中节点
     *
     * @param map
     * @param node
     * @return
     */
    boolean isInMap(Map<Integer, SingleNode<Integer>> map, SingleNode<Integer> node) {
//        for (Map.Entry<Integer, SingleNode<Integer>> entry : map.entrySet()) {//遍历检测，O(n)级消耗，取决于散列的大小
//            if (node == entry.getValue())
//                return true;
//        }

        Integer key = node.hashCode();
        if (map.get(key) != null)//由于是散列取值，常数级消耗
            return true;

        return false;
    }

    static SingleNode<Integer> headNode = new SingleNode<>();
    static SingleNode<Integer> crossing = null;

    //构建链表
    static {
        SingleNode<Integer> current = headNode;

        int count = 0;
        while (count < 50) {
            SingleNode<Integer> node = new SingleNode<>();
            node.setDate(count);
            if (headNode == null) {
                headNode = node;
                continue;
            }
            if (current != null)
                current.setNext(node);
            current = current.getNext();
            if (count == 30)
                crossing = current;
            count++;
        }
        current.setNext(crossing);//构建循环链表，注掉则是线性链表
    }


    public static boolean isCircularFloyd(SingleNode<Integer> head) {
        SingleNode<Integer> ptrSlow = head,
                ptrFast = head;
        if (head == null)
            return false;

        while (ptrFast.getNext() != null && ptrFast.getNext().getNext() != null) {
            ptrSlow = ptrSlow.getNext();
            ptrFast = ptrFast.getNext().getNext();
            if (ptrFast == ptrSlow)
                return true;
        }
        return false;
    }

    /**
     * 问题15：
     * 在有序链表中插入一个节点
     *
     * @param head
     * @param newNode
     * @return
     */
    SingleNode<Integer> insertInSortedList(SingleNode<Integer> head, SingleNode<Integer> newNode) {
        SingleNode<Integer> current = head;
        if (head == null)
            return newNode;
        SingleNode<Integer> temp = null;

        while (current != null && current.getDate() < newNode.getDate()) {
            temp = current;
            current = current.getNext();
        }
        newNode.setNext(current);
        temp.setNext(newNode);
        return head;
    }

    /**
     * 问题16：
     * 逆置单向链表
     *
     * @param head
     * @return
     */
    SingleNode<Integer> reverseSList(SingleNode<Integer> head) {
        SingleNode<Integer> nextNode = null, temp = null;
        while (head != null) {
            nextNode = head.getNext();
            head.setNext(temp);
            temp = head;
            head = nextNode;
        }
        return temp;
    }

    /**
     * 问题17：
     * 求出两个交汇链表的汇合节点
     *
     * @param head1 链表1，长度为m
     * @param head2 链表2,长度为n
     * @return 两个链表的交汇点
     * <p>
     * 蛮力法，时间为O(mn),空间为O(1)
     * 针对每一个链表1的节点，从头遍历链表2，寻找相同的节点，找到的第一个既是交汇点
     */
    SingleNode<Integer> findJoint(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        SingleNode<Integer> current1 = head1;
        SingleNode<Integer> current2 = head2;

        while (current1 != null) {
            current2 = head2;
            while (current2 != null) {
                if (current1 == current2)
                    return current1;
                current2 = current2.getNext();
            }
            current1 = current1.getNext();
        }
        return null;
    }

    /**
     * 问题19：
     * 使用散列表求解问题17
     * <p>
     * 1.遍历链表1将所有元素加入散列表O(m)
     * 2.遍历链表2，检查散列表是否有该节点O(1)*n
     * 3.如果在散列中找到了，就是第一个交汇点
     *
     * @param head1
     * @param head2
     * @return
     */
    SingleNode<Integer> findJointInHash(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        SingleNode<Integer> current1 = head1;
        SingleNode<Integer> current2 = head2;

        //todo head1加入散列中去， O(m),m为链表1长度
        Map<Integer, SingleNode<Integer>> hashHead1 = new HashMap<>();
        while (current1 != null) {
            int hashCode = current1.hashCode();
            hashHead1.put(hashCode, current1);
            current1 = current1.getNext();
        }

        //todo 在head2中检索，O(n)，n为链表2长度
        while (current2 != null) {
            int hashCode = current2.hashCode();
            if (hashHead1.get(hashCode) != null)
                return current2;
            current2 = current2.getNext();
        }
        return null;
    }

    /**
     * 问题20，使用栈解决问题17
     * 1.将链表1和2分别装进两个栈中
     * 2.同时弹出两个栈，知道弹出的节点不相同，那么前一个节点就是交汇点
     *
     * @param head1
     * @param head2
     * @return
     */
    SingleNode<Integer> findJointInStack(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        SingleNode<Integer> current1 = head1,
                current2 = head2;

        while (current1 != null) {
            stack1.add(current1);
            current1 = current1.getNext();
        }

        while (current2 != null) {
            stack2.add(current2);
            current2 = current2.getNext();
        }

        SingleNode<Integer> temp = null;
        SingleNode<Integer> t = null;

        while ((t = stack1.popNode()) == stack2.popNode()) {
            temp = t;
        }

        return temp;
    }

    /**
     * 问题22： 使用二分法解决问题17
     * 使用数组，做二分查找
     * 1.将第一个链表的指针放到数组中去，排序
     * 2.对第二个链表遍历每一个节点
     * 3.从头开始，使用二分查找，知道找到第一个重复的数据为止，O(n*log(n))
     *
     * @param head1
     * @param head2
     * @return
     */
    SingleNode<Integer> findJointInArray(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        SList<Integer> slist = new SList<>();
        int[] hashCode1 = new int[slist.listLength(head1)];
        SingleNode<Integer> current1 = head1;
        SingleNode<Integer> current2 = head2;

        int count = 0;
        while (current1 != null) {
            hashCode1[count] = current1.hashCode();
            current1 = current1.getNext();
            count++;
        }

        Arrays.sort(hashCode1);

        while (current2 != null) {
            Integer code = Arrays.binarySearch(hashCode1,current2.hashCode());//数组二分查找
            if (code > 0)
                return current2;
            current2 = current2.getNext();
        }

        return null;
    }


    /**
     * 问题23： 以O(max(m,n))时间解决问题17
     * 1.计算两个链表长度 size1,size2
     * 2.计算链表长度差距：size1-size2 = diff
     * 3.长的链表新移动diff长度，之后两个一起移动
     * 4.直到第一个交汇点，两个节点相同
     *
     * @param head1
     * @param head2
     * @return
     */
    SingleNode<Integer> findJointNodeLessTime(SingleNode<Integer> head1, SingleNode<Integer> head2) {
        SingleNode current1 = head1,
                current2 = head2;
        int size1 = 0, size2 = 0, diff = 0;

        while (current1 != null) {
            size1++;
            current1 = current1.getNext();
        }

        while (current2 != null) {
            size2++;
            current2 = current2.getNext();
        }

        diff = Math.abs(size1 - size2);
        if (size1 > size2) {
            current1 = head1;
            current2 = head2;
        } else {
            current1 = head2;
            current2 = head1;
        }

        for (int i = 0; i < diff; i++)
            current1 = current1.getNext();

        while (current1 != null && current2 != null) {
            if (current1 == current2)
                return current1;
            current1 = current1.getNext();
            current2 = current2.getNext();
        }

        return null;
    }


    /**
     * 测试问题17的各个解法正确性
     */
    void testQuestion() {
        SList<Integer> sList = new SList<>();

        SingleNode<Integer> head = new SingleNode<>();//链表1
        head.setDate(8888);
        SingleNode<Integer> joint = null;
        for (int i = 0; i < 50; i++) {
            SingleNode<Integer> node = new SingleNode<>();
            node.setDate(i);

            if (i == 30)
                joint = node;

            sList.insertInLinkedList(head, node, sList.listLength(head) + 1);
        }

        SingleNode<Integer> head2 = new SingleNode<>();//链表2
        head2.setDate(9999);

        for (int i = 100; i < 133; i++) {
            SingleNode<Integer> node = new SingleNode<>();
            node.setDate(i);

            sList.insertInLinkedList(head2, node, sList.listLength(head2) + 1);
        }

        //todo 续上交汇点
        SingleNode<Integer> current2 = head2;
        while (current2 != null){
            if (current2.getNext() == null){
                current2.setNext(joint);
                break;
            }
            current2 = current2.getNext();
        }
//        sList.traverse(head2);
//        sList.traverse(head);
        long start  = System.nanoTime();
        SingleNode<Integer> joinNode = findJoint(head,head2);
        long end = System.nanoTime();
        long time = end-start;
        System.err.println("交汇点："+joinNode.getDate());
        System.err.println("话费 "+time+" nano");





    }


    public static void main(String[] args) {
        ListUtils utils = new ListUtils();
        utils.testQuestion();







       /* if (utils.isCircularList(headNode))
            System.out.println("循环链表!!!");
        else
            System.out.println("不循环!!!");

        if (isCircularFloyd(headNode))
            System.out.println("循环!!!");
        else
            System.out.println("不循环!!!");*/
    }
}
