package linktable.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luzj
 * @description:
 * 1.单节点：指向下一个指针 和 随机指针
 * @date 2018/5/18
 */
public class RandomNode<T> {
    private T data;
    private RandomNode<T> next;
    private RandomNode<T> random;

    /**
     * 问题42：复制一个RandomNode节点构成的链表
     * 1.针对每个节点复制一个同数据的节点，next random指针为空
     * 2.原节点为key 新节点为value 放入散列表中
     * 3.构建一个全数据的散列表
     * 4.再次遍历散列表,由于现在的指针指向的节点都已经存在，因此可以对每个节点赋值指针
     * @param head
     * @return
     */
    RandomNode<Integer> cloneList(RandomNode<Integer> head) {
        RandomNode<Integer> p1 = head, p2 = null;

        //装散列
        Map<RandomNode<Integer>, RandomNode<Integer>> map = new HashMap<>();
        while (p1 != null) {
            p2 = new RandomNode<>();
            p2.setData(p1.getData());
            p2.setNext(null);
            p2.setRandom(null);
            map.put(p1, p2);
            p1 = p1.getNext();

        }

        //复制节点
        p1 = head;
        while (p1 != null) {
            p2 = map.get(p1);
            p2.setNext(map.get(p1.getNext()));
            p2.setRandom(map.get(p1.getRandom()));
            p1 = p1.getNext();
        }

        return map.get(head);
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RandomNode<T> getNext() {
        return next;
    }

    public void setNext(RandomNode<T> next) {
        this.next = next;
    }

    public RandomNode<T> getRandom() {
        return random;
    }

    public void setRandom(RandomNode<T> random) {
        this.random = random;
    }
}
