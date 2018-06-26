package stack;

import linktable.SingleNode;

/**
 * @author luzj
 * @description: 链表实现栈
 * @date 2018/6/26
 */
public class LinkedStack {
    private SingleNode<Integer> head;//栈顶节点

    public LinkedStack() {//首次，初始化栈顶
        this.head = new SingleNode<>();
    }

    public int push(int element){//入栈
        if (head == null)
            head = new SingleNode<>(element);
        else if (head.getDate() == null)
            head.setDate(element);
        else {
            SingleNode node = new SingleNode(element);
            node.setNext(head);
            head = node;
        }
        return element;
    }

    public Integer top(){//栈顶元素审查
        if (head == null)
            return null;
        return head.getDate();
    }

    public Integer pop() throws StackEmptyException {//出栈
        if (isEmpty())
            throw new StackEmptyException("栈已空！！！");
        else {
            int data = head.getDate();
            head = head.getNext();
            return data;
        }
    }

    public boolean isEmpty(){//栈是否空
        if (head == null)
            return true;
        return false;
    }

    public void deleteStack(){//清空栈
        head = null;
    }

    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();

        for (int i = 0; i < 10; i++) {
            linkedStack.push(i);
        }

        while (true){
            try {
                int data = linkedStack.pop();
                System.err.print(data+"\t");
            } catch (StackEmptyException e) {
                e.printStackTrace();
                return;
            }
        }
    }

}
