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

    /**
     * 判定是否有环
     * @param head
     * @return
     */
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

    /**
     * 问题11，拓展Floyd算法，找出交叉点
     *- 当两点交合时，slow重置为head，fast保持原点
     *- 两者一起同步前移，当再次两会的时候，交点就是环的初始点
     * @param head
     * @return
     */
    public static SingleNode findBeginOfLoop(SingleNode<Integer> head) {
        if (head == null)
            return null;
        boolean isLoop = false;
        SingleNode<Integer> ptrSlow = head, ptrFast = head;

        while (ptrFast.getNext() != null && ptrFast.getNext().getNext() != null) {
            ptrSlow = ptrSlow.getNext();
            ptrFast = ptrFast.getNext().getNext();
            if (ptrSlow == ptrFast) {
                System.err.println("交汇点："+ptrSlow.getDate());
                isLoop = true;
                break;
            }
        }

        if (isLoop) {
            ptrSlow = head;
            while (ptrSlow != ptrFast) {
                ptrFast = ptrFast.getNext();
                ptrSlow = ptrSlow.getNext();
            }
            return ptrSlow;
        }
        return null;
    }

    /**
     * 问题14：
     * 计算环的长度
     * @param head
     * @return
     */
    public static int findLoopLength(SingleNode<Integer> head){
        if (head == null)
            return 0;
        boolean isLoop = false;
        SingleNode<Integer> ptrSlow = head, ptrFast = head;

        while (ptrFast.getNext() != null && ptrFast.getNext().getNext() != null) {
            ptrSlow = ptrSlow.getNext();
            ptrFast = ptrFast.getNext().getNext();
            if (ptrSlow == ptrFast) {
                isLoop = true;
                break;
            }
        }
        if (isLoop){
            int count = 1;
            ptrFast = ptrFast.getNext();
            while (ptrSlow != ptrFast){
                ptrFast = ptrFast.getNext();
                count++;
            }
            return count;
        }
        return 0;
    }

    public static void main(String[] args) {
        ListUtils utils = new ListUtils();

        if (isCircular(utils.headNode)) {
            System.out.println("循环!!!");
            System.out.println(findBeginOfLoop(utils.headNode).getDate());
            System.out.println("环的长度:"+findLoopLength(utils.headNode));
        } else {
            System.out.println("不循环!!!");
        }


    }
}
