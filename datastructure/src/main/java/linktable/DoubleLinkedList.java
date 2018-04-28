package linktable;

import util.GeneratorRand;

/**
 * @author luzj
 * @description: 双向链表
 * 操作：
 * 遍历
 * 插入
 * 删除
 * @date 2018/4/27
 */
public class DoubleLinkedList<T> {

    /**
     * 遍历链表
     * 打印节点data
     *
     * @param headNode
     */
    void traverseDll(DLLNode<T> headNode) {
        if (headNode == null)
            System.out.println("空链表");
        DLLNode<T> currentNode = headNode;
        while (currentNode != null) {
            T data = currentNode.getData();
            System.out.println(data);
            currentNode = currentNode.getNext();
        }
        System.out.println();
    }

    /**
     * 双向链表插入：头部 中部 尾部
     *
     * @param headNode
     * @param nodeToInsert
     * @param position
     * @return 插入后链表表头
     */
    DLLNode<T> dllInsert(DLLNode<T> headNode, DLLNode<T> nodeToInsert, int position) {
        if (headNode == null)
            return nodeToInsert;
        int length = getDLLLength(headNode);

        if (position < 1 || position > length + 1) {//验证输入位置
            System.out.println("插入位置无效！有效输入为：1-" + (length + 1));
            return headNode;
        }

        if (position == 1) {//头部插入
            nodeToInsert.setNext(headNode);
            headNode.setPrevious(nodeToInsert);
            return nodeToInsert;
        } else {//中间和尾部插入
            DLLNode previousNode = headNode;
            int count = 1;
            while (count < position - 1) {
                previousNode = previousNode.getNext();
                count++;
            }
            //currentNode:待插入后置节点   previousNode:待插入前置节点
            DLLNode currentNode = previousNode.getNext();

            //配置前驱、后置关系
            previousNode.setNext(nodeToInsert);
            nodeToInsert.setPrevious(previousNode);

            nodeToInsert.setNext(currentNode);//若是尾部，则插入空
            //插入尾部的情况
            if (currentNode != null)
                currentNode.setPrevious(nodeToInsert);
        }
        return headNode;
    }

    /**
     * 删除节点： 头部 中间 尾部
     * @param headNode
     * @param position 删除节点位置
     * @return 删除后的链表表头
     */
    DLLNode<T> dllDelete(DLLNode<T> headNode, int position) {
        int size = getDLLLength(headNode);

        if (position < 1 || position > size) {//验证位置有效性
            System.out.println("删除位置无效,有效输入为1->" + size);
            return headNode;
        }

        if (position == 1) {//删除第一个节点
            DLLNode currentNode = headNode.getNext();
            headNode = null;
            currentNode.setPrevious(null);
            return currentNode;
        } else {//
            DLLNode previousNode = headNode;
            int count = 1;
            while (count < position - 1) {
                previousNode = previousNode.getNext();//previousNode为前置节点
                count++;
            }

            DLLNode currentNode = previousNode.getNext();//要删除的节点
            DLLNode laterNode = currentNode.getNext();//后置节点
            previousNode.setNext(laterNode);

            if (laterNode != null)//后置节点非空，则设置前驱节点为previousNode
                laterNode.setPrevious(previousNode);
            currentNode = null;//要删除的节点置为null

        }
        return headNode;
    }


    /**
     * 获取双向链表的长度
     *
     * @param headNode
     * @return
     */
    int getDLLLength(DLLNode<T> headNode) {
        int length = 0;
        DLLNode<T> currentNode = null;
        currentNode = headNode;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.getNext();
        }
        return length;
    }


    /**
     * 使用数组初始化一个双向链表
     *
     * @param arr
     * @return
     */
    DLLNode<T> initDList(T[] arr) {
        DLLNode<T> headNode = null;
        for (T data : arr) {
            DLLNode<T> node = new DLLNode();
            node.setData(data);
            headNode = dllInsert(headNode, node, getDLLLength(headNode) + 1);
        }
        return headNode;
    }

    public static void main(String[] args) {
        Student[] students = new Student[10];
        for (int i = 0; i < 10; i++) {
            Student student = new Student(
                    GeneratorRand.generatorName(),
                    GeneratorRand.generatorName(),
                    GeneratorRand.generatorAge());
            students[i] = student;
        }

        DoubleLinkedList<Student> dlls = new DoubleLinkedList<>();
        DLLNode<Student> head = dlls.initDList(students);
        dlls.traverseDll(head);

        Student s = new Student("狗子","二狗子",23);
        Student v = new Student("麻子","王麻子",44);

        DLLNode<Student> node1 = new DLLNode<>();
        node1.setData(s);

        DLLNode<Student> node2 = new DLLNode<>();
        node2.setData(v);

        DLLNode n1 = dlls.dllInsert(head,node1,1);
        DLLNode n2 = dlls.dllInsert(n1,node2,7);

        dlls.traverseDll(n2);

        DLLNode n3 = dlls.dllDelete(n2,1);
        DLLNode n4 = dlls.dllDelete(n3,3);
        dlls.traverseDll(n4);
    }
}
