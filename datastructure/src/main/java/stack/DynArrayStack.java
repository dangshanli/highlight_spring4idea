package stack;

import java.util.Arrays;

/**
 * @author luzj
 * @description: 动态数组实现的栈
 * 1 当栈到达极限时，将当前的栈扩大两倍
 * 2 时间开销为 T(n) = O(n)
 * 3 未使用泛型，数组元素为int
 * 4 倍数太多可能导致栈溢出
 * @date 2018/6/26
 */
public class DynArrayStack {
    private int top;//栈顶游标
    private int capacity; //栈容量
    private int[] array;//持有元素

    public DynArrayStack() {//初始capacity为2
        top = -1;
        capacity = 2;
        array = new int[2];
    }

    public boolean isEmpty(){//是否为空
        return (top == -1);
    }

    public boolean isFull(){//是否已满
        return (top == capacity-1);
    }

    public int size(){//栈大小
        return capacity;
    }

    public int push(int element){//元素入栈
        if (isFull())
            doubleCap();
        array[++top] = element;
        return element;
    }

    private void doubleCap(){//双倍扩容
        int[] arr = Arrays.copyOf(array,capacity * 2);
        capacity = capacity * 2;
        array = arr;
    }

    public int pop() throws StackEmptyException {//元素出栈
        if (isEmpty())
            throw new StackEmptyException("栈已空！！！");
        return array[top--];
    }

    public static void main(String[] args) {//测试
        DynArrayStack dynArrayStack = new DynArrayStack();
        for (int i = 0; i < 10; i++) {
            dynArrayStack.push(i);
        }

        System.out.println("栈大小:"+dynArrayStack.size());

        while (true){
            try {
                int a = dynArrayStack.pop();
                System.err.println(a);
            } catch (StackEmptyException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
