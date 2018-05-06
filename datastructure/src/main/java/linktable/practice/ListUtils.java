package linktable.practice;

import linktable.SingleNode;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luzj
 * @description: 判断链表是否是循环链表
 * @date 2018/5/5
 */
public class ListUtils {

    /**
     *问题6：判断一个链表是否为循环链表，比如：A->B->C->D->E->F->C
     * 蛮力法：
     * - 从表头开始遍历链表，持有当前节点，然后遍历整个链表和看是否有节点也指向当前节点，是则循环
     * 缺点：无法确定循环终止条件，循环链表可能无线循环，不适用
     *
     * 问题7：
     * 判断链表是否有环
     * 方法：
     * 1.遍历链表，每遍历过一个就放进散列表中去
     * 2.每遍历一个新的节点，就在散列中检索是有已有
     * 3.知道结束：有，则循环链表；没有，则不是循环链表
     * <p>
     * 改进散列取值后，时间仅为测试循环开销O(n)，之前是O(n^2)
     *
     * 问题8：使用排序法是否可以测试环的问题？
     * - 遍历每个节点，他的后继节点地址保存到数组中去
     * - 将节点保存到数组中去
     * - 排序数组，之后检测有相邻节点地址相同即可得出此为循环链表
     * 缺点：一旦遇到循环链表，无法判定终结位置，因此盲目遍历可能死循环。必须要先确定链表长度。
     * 因此，不适用。
     *
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
        while (count < 10) {
            SingleNode<Integer> node = new SingleNode<>();
            node.setDate(count);
            if (headNode == null) {
                headNode = node;
                continue;
            }
            if (current != null)
                current.setNext(node);
            current = current.getNext();
            if (count == 4)
                crossing = current;
            count++;
        }
        current.setNext(crossing);//构建循环链表，注掉则是线性链表
    }


    public static void main(String[] args) {
        ListUtils utils = new ListUtils();
        if (utils.isCircularList(headNode))
            System.out.println("循环链表!!!");
        else
            System.out.println("不循环!!!");
    }
}
