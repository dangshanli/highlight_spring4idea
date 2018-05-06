package linktable.practice;

import linktable.SingleNode;

/**
 * @author luzj
 * @description: 问题9：使用Floyd方法判定一个链表是否有环，
 * - 设置两个指针从头开始
 * - 两个指针一快(fast)一慢(slow)
 * - 如果存在环或者子环的话，两个指针必定会在某个时候相遇
 * - 但是他不想散列技术那样，能够判定交汇点在哪,但是比起散列更加节省空间
 * @date 2018/5/6
 */
public class FloydRing {

    public static boolean isCircular(SingleNode<Integer> head) {
        if (head == null)
            return false;
        SingleNode<Integer> ptrSlow = head,
                ptrFast = head;

        while (ptrFast.getNext() != null && ptrFast.getNext().getNext() != null) {
            for (int i = 0; i < 2; i++) {
                ptrFast = ptrFast.getNext();
                if (ptrFast == ptrSlow)
                    return true;
            }
            ptrSlow = ptrSlow.getNext();
        }
        return false;
    }

    public static void main(String[] args) {
        ListUtils utils = new ListUtils();

        if (isCircular(utils.headNode)) {
            System.out.println("循环!!!");
        } else {
            System.out.println("不循环!!!");
        }

    }
}
