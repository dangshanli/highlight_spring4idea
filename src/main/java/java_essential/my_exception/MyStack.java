package java_essential.my_exception;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * @author: luzj
 * @date: 2018/2/14
 * @description: 栈实现演示抛出异常
 */
public class MyStack {
    int position ;
    int SIZE = 20;
    ArrayList<Object> list = new ArrayList(SIZE);

    public Object pop() {
        Object object;
        //todo 当栈为空时抛出空栈异常
        //EmptyStackException为非检查异常，不需要在pop方法上声明
        if (position == 0)
            throw new EmptyStackException();

        object = list.get(position-1);
        list.set(position-1,null);
        position--;
        return object;
    }
}
