package linktable.practice;

import linktable.CLLNode;
import linktable.CircularList;
import linktable.SList;
import linktable.SingleNode;
import org.apache.poi.ss.formula.functions.T;

import javax.swing.plaf.SliderUI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luzj
 * @description: 判断链表是否是循环链表
 * @date 2018/5/5
 */
public class ListUtils {
    static SingleNode<Integer> myHead = null;

    static {
        //TODO 构建链表
        SList<Integer> sList = new SList<>();
        myHead = new SingleNode<>();//链表1
        myHead.setDate(1);
        for (int i = 2; i < 11; i++) {
            /*if (i % 2 == 0)
                continue;*/
            SingleNode<Integer> node = new SingleNode<>();
            node.setDate(i);
            sList.insertInLinkedList(myHead, node, sList.listLength(myHead) + 1);
        }
    }

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
            Integer code = Arrays.binarySearch(hashCode1, current2.hashCode());//数组二分查找
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
     * 问题25:查找链表的中间节点
     * 1.先遍历一遍求出链表长度count
     * 2.再遍历到count/2这个节点，就是中间节点
     *
     * @param head
     * @return 中间节点
     */
    SingleNode<Integer> getMidNode(SingleNode<Integer> head) {
        int count = 0;
        SingleNode current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }

        int mid = count / 2;
        count = 0;
        current = head;
        while (current != null) {
            if (count == mid)
                return current;
            count++;
            current = current.getNext();
        }

        return null;
    }

    /**
     * 问题27：一次扫描链表解决问题25
     * 使用连个指针获取中间节点
     * 1.两个指针同时指向头部
     * 2.p1移动两步，p2移动一步
     * 3.p1移动结束以后，p2的位置就是中点
     *
     * @param head
     * @return
     */
    SingleNode<Integer> getMidNodeIn2Pointer(SingleNode<Integer> head) {
        SingleNode p1 = head, p2 = head;

        int count = 0;
        while (p1 != null) {
            p1 = p1.getNext();
            if (count % 2 == 1)
                p2 = p2.getNext();
            count++;
        }
        return p2;
    }

    /**
     * 问题28：倒着打印单向链表
     * 1.使用递归，直到最后一个节点才释放
     *
     * @param head
     */
    void printNodeFromEnd(SingleNode<Integer> head) {

        if (head == null)
            return;
        printNodeFromEnd(head.getNext());
        System.out.print(head.getDate() + "\t");
    }

    /**
     * 问题29：测试链表长度是奇数还是偶数
     * 1.每次走两步
     * 2.最后一次节点 == null，就是偶，否则奇数
     *
     * @param head
     * @return
     */
    int isListEven(SingleNode<Integer> head) {
        SingleNode p1 = head;
        while (p1 != null && p1.getNext() != null) {
            p1 = p1.getNext().getNext();
        }
        if (p1 == null) return 0;
        return 1;
    }

    //问题29解法2：计算总节点，然后看能否整除2
    int isListEven2(SingleNode<Integer> head) {
        int count = 1;
        SingleNode p1 = head;
        while (p1.getNext() != null) {
            count++;
            p1 = p1.getNext();
        }
        System.err.println("count:" + count);
        if (count % 2 == 0) return 0;
        return 1;
    }

    /**
     * 问题31：
     * 对p1和p2两个有序链表整合成一个有序链表
     * 1.使用递归对比p1和p2的当前节点，选择小的加入result
     * 2.然后递归调用下个节点作为头部和另一个节点作为参数
     * 3.出口：任何一个为空，返回另一个链表
     *
     * @param p1
     * @param p2
     * @return
     */
    SingleNode merge2List(SingleNode<Integer> p1, SingleNode<Integer> p2) {
        SingleNode result = null;

        //todo 出口
        if (p1 == null) return p2;
        if (p2 == null) return p1;

        if (p1.getDate() <= p2.getDate()) {
            result = p1;
            result.setNext(merge2List(p1.getNext(), p2));
        } else {
            result = p2;
            result.setNext(merge2List(p2.getNext(), p1));
        }
        return result;
    }

    /**
     * 问题32：
     * 对链表进行成对逆置，如1-2-3-4-x 转成 2-1-4-3-x
     * 1.使用递归，通用情形下：当前链表前两个节点逆置，然后递归调用第三个节点作为表头
     * 2.基本情形：当前节点为空或者只剩当前一个节点
     *
     * @param head
     * @return
     */
    SingleNode<Integer> reversePairedList(SingleNode<Integer> head) {
        if (head == null || head.getNext() == null)
            return head;

        SingleNode<Integer> p1 = head;
        SingleNode<Integer> p2 = p1.getNext();
        SingleNode<Integer> p3 = p1.getNext().getNext();

        p2.setNext(p1);
        p1.setNext(reversePairedList(p3));

        return p2;
    }

    /**
     * 问题36：将一个循环单向链表拆成相等两个，若是奇数个，则前一个多一个节点
     * 使用Floyd算法（即双指针一快一慢的算法解决这个问题）
     * 1.两个指针，p1 p2
     * 2.p2是p1的两倍移速
     * 3.p2结束后，p1正好中间，最后沿着p1和head两个位置切割，然后重组成循环链表
     *
     * @param head
     */
    void cutCircuitList(CLLNode<Integer> head) {
        CLLNode<Integer> p1 = head, p2 = head;
        //      奇                       偶
        while (p2.getNext() != head && p2.getNext().getNext() != head) {
            p1 = p1.getNext();
            p2 = p2.getNext().getNext();//
        }
        CLLNode p3 = p1.getNext();
        p1.setNext(head);
        if (p2.getNext().getNext() == head) {
            p2.getNext().setNext(p3);
        }

        if (p2.getNext() == head) {
            p2.setNext(p3);
        }

        CircularList<Integer> circularList = new CircularList<>();
        circularList.printData(head);
        circularList.printData(p3);
    }

    /**
     * 问题37：验证链表是否是回文，顺读和倒读一样
     * 1.检索出中点
     * 2.后半段进行逆序
     * 3.前后半段进行对比
     * 4.逆序后半段，重组回原来的（该方法没那么做）
     *
     * @param head
     * @return
     */
    boolean isPalindrome(SingleNode<Integer> head) {
        //TODO 如果单节点或者为空
        if (head == null || head.getNext() == null)
            return false;

        //TODO 计算中间节点
        SList<Integer> sList = new SList<>();
        int length = sList.listLength(head);
        SingleNode<Integer> midNode = getMidNodeIn2Pointer(head);


        //TODO 奇偶有别，奇数个需要向后移一位，并逆序
        SingleNode<Integer> sortedHead = null;
        if (length % 2 == 0) {//偶数长度
            sortedHead = reverseSList(midNode);
        } else {//奇数长度
            sortedHead = reverseSList(midNode.getNext());
        }

        //TODO 对比前半段
        SingleNode<Integer> p1 = head;
        while (sortedHead != null) {
            if (!sortedHead.getDate().equals(p1.getDate()))
                return false;
            sortedHead = sortedHead.getNext();
            p1 = p1.getNext();
        }

        return true;
    }

    /**
     * 问题39：逆置相邻的K个节点块
     * 1.使用递归
     * 2.首先截出前k个节点，如果不足k节点直接返回当前链表
     * 3.对当前k节点链表逆序，然后对k+1节点递归调用之前的方法
     * 4.直到剩下的节点不足k个为止，递归回溯
     *
     * @param head
     */
    SingleNode<Integer> reverseKNode(SingleNode<Integer> head, int k) {

        SingleNode<Integer> kthNode = null;
        if ((kthNode = kthNode(k, head)) != null) {
            SingleNode<Integer> p1 = kthNode.getNext();
            kthNode.setNext(null);
            head = reverseSList(head);

            SingleNode<Integer> p2 = head;
            while (p2.getNext() != null) {
                p2 = p2.getNext();
            }

            p2.setNext(reverseKNode(p1, k));
        }
        return head;
    }

    //获取链表第k个节点
    private SingleNode<Integer> kthNode(int k, SingleNode<Integer> head) {
        int count = 0;
        SingleNode<Integer> p1 = head;
        while (p1 != null) {
            count = count + 1;
            if (count == k)
                return p1;
            p1 = p1.getNext();
        }
        return null;
    }

    //测试问题39
    void testQue39() {
        SingleNode<Integer> head = reverseKNode(myHead, 7);
        SList<Integer> sList = new SList<>();

        sList.traverse(head);
    }


    //测试问题37
    void testQue37() {
        SingleNode<Integer> head = new SingleNode<>();
        head.setDate(0);

        SList<Integer> sList = new SList<>();
        for (int i = 1; i < 6; i++) {
            SingleNode<Integer> node = new SingleNode<>();
            node.setDate(i);
            sList.insertInLinkedList(head, node, sList.listLength(head) + 1);
        }
        for (int i = 3; i >= 0; i--) {
            SingleNode<Integer> node = new SingleNode<>();
            node.setDate(i);
            sList.insertInLinkedList(head, node, sList.listLength(head) + 1);
        }

        boolean b = isPalindrome(head);
        if (b)
            System.err.println("回文");
        else
            System.out.println("非回文");
    }


    //测试问题36
    void testQue36() {
        CLLNode<Integer> head = new CLLNode<>();
        head.setData(0);
        head.setNext(head);

        CircularList<Integer> circularList = new CircularList<>();
        for (int i = 1; i < 31; i++) {
            CLLNode<Integer> node = new CLLNode<>();
            node.setData(i);
            circularList.insertAtEnd(head, node);
        }

        cutCircuitList(head);
    }


    //测试问题32
    void testQue32() {
        SingleNode<Integer> p = reversePairedList(myHead);
        SList<Integer> sList = new SList<>();
        sList.traverse(p);
    }


    //测试问题31
    void testQue31() {
        SList<Integer> sList = new SList<>();

        SingleNode<Integer> head2 = new SingleNode<>();
        head2.setDate(0);

        for (int i = 1; i < 60; i++) {
            if (i % 2 == 1)
                continue;
            SingleNode<Integer> node = new SingleNode<>();
            node.setDate(i);
            sList.insertInLinkedList(head2, node, sList.listLength(head2) + 1);
        }

        SingleNode<Integer> result = merge2List(myHead, head2);
        sList.traverse(result);
    }

    //测试问题29
    void testQue29() {
        System.out.println(isListEven(myHead));
        System.out.println(isListEven2(myHead));
    }

    //测试问题28
    void testQuestion28() {
        printNodeFromEnd(myHead);
    }

    /**
     * 测试问题25/27
     */
    void testQuestion25() {
        SingleNode<Integer> node1 = getMidNode(myHead);
        SingleNode<Integer> node2 = getMidNodeIn2Pointer(myHead);

        System.err.println("node1:" + node1.getDate());
        System.err.println("node2:" + node2.getDate());
    }

    /**
     * 测试问题17-23的各个解法正确性
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
        while (current2 != null) {
            if (current2.getNext() == null) {
                current2.setNext(joint);
                break;
            }
            current2 = current2.getNext();
        }
//        sList.traverse(head2);
//        sList.traverse(head);
        long start = System.nanoTime();
        SingleNode<Integer> joinNode = findJoint(head, head2);
        long end = System.nanoTime();
        long time = end - start;
        System.err.println("交汇点：" + joinNode.getDate());
        System.err.println("花费时长： " + time + " nano");
    }


    public static void main(String[] args) {
        ListUtils utils = new ListUtils();
//        utils.testQuestion();
//        utils.testQuestion25();
//        utils.testQuestion28();
//        utils.testQue29();
//        utils.testQue31();

//        utils.testQue32();
//        utils.testQue36();
//        utils.testQue37();
        utils.testQue39();
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
