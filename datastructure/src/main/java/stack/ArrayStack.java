package stack;

/**
 * @author luzj
 * @description: 功能描述：
 * 1 使用数组实现定长的stack
 * 2 未使用泛型 单测试int类型 关键在于实现stack的基本功能
 * 3 stack的基本功能：push pop
 * 4 辅助功能：isEmpty isFull size top
 * @date 2018/6/26
 */
public class ArrayStack {
    private int top;//栈顶光标
    private int capacity;//stack的容量，即数组长度
    private int[] array;//持有元素的数组

    public ArrayStack(int capacity) {//初始化栈大小
        this.top = -1;
        this.capacity = capacity;
        this.array = new int[capacity];
    }

    public boolean isEmpty() {//判断空栈
        if (top <= -1)
            return true;
        return false;
    }

    public boolean isFull() {//判断满栈
        if (top >= capacity - 1)
            return true;
        return false;
    }

    public int push(int element) throws StackFullException {//元素入栈
        if (isFull())
            throw new StackFullException("栈已满！！！");
        else
            array[++top] = element;
        return element;
    }

    public int pop() throws StackEmptyException {//元素出栈
        if (isEmpty())
            throw new StackEmptyException("栈已空！！！");
        else
            return array[top--];
    }

    public void deleteStack() {//删除栈
        top = -1;
    }

    public int size(){
        return top+1;
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        for (int i = 0; i < 5; i++) {
            try {
                stack.push(i);
            } catch (StackFullException e) {
                e.printStackTrace();
            }

        }

        while (true){
            int a = 0;
            try {
                 a = stack.pop();
            } catch (StackEmptyException e) {
                e.printStackTrace();
                return;
            }

            System.out.println(a);

        }

    }
}
