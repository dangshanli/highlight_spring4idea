package linktable.practice;

import linktable.SingleNode;
import linktable.SList;
import linktable.Student;
import util.GeneratorRand;

/**
 * @author luzj
 * @description: 问题1：以单向链表为基础实现stack
 * @date 2018/5/4
 */
public class Stack<T> {

    private SingleNode<T> head;

    private SList<T> sList = new SList<>();

    /**
     * 尾部添加节点
     *
     * @param data
     * @return 插入后stack的长度
     */
    public int add(T data) {
        SingleNode<T> node = new SingleNode<>();
        node.setDate(data);
        if (head == null)
            head = node;
        else {
            sList.insertInLinkedList(head, node, sList.listLength(head) + 1);
        }
        return sList.listLength(head);
    }

    /**
     * stack长度
     *
     * @return
     */
    public int length() {
        return sList.listLength(head);
    }

    /**
     * 弹出stack尾部的节点
     *
     * @return
     */
    public T pop() {
        if (length() == 0){
            System.err.println("空链表!!!");
            return null;
        }

        SingleNode<T> current = head;
        SingleNode<T> previous = null;

        if (length() == 1){
            head = null;
            return current.getDate();
        }

        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }

        T data = null;
        if (current != null)
            data = current.getDate();
        previous.setNext(null);
        current = null;
        return data;
    }

    /**
     * 清空栈
     */
    public void empty() {
        sList.deleteLikedList(head);
    }

    public static void main(String[] args) {
        Stack<Student> studentStack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            Student s = new Student(
                    GeneratorRand.generatorName(),
                    GeneratorRand.generatorName(),
                    GeneratorRand.generatorAge());
            System.err.println(s);
            studentStack.add(s);
        }

        System.out.println("length:"+studentStack.length());
        System.out.println(studentStack.pop());
        System.out.println("2: "+studentStack.pop());
        System.out.println("3: "+studentStack.pop());
        System.out.println("4: "+studentStack.pop());
        System.out.println("5: "+studentStack.pop());
        System.out.println("6: "+studentStack.pop());
    }


}
