package java_essential.my_exception;

import java.util.LinkedList;

/**
 * @author: luzj
 * @date: 2018/2/14
 * @description:
 */
public class MyLinkedList {
    LinkedList<Object> linkedList = new LinkedList<>();
    public Object objectAt(int n) throws InvalidIndexException, EmptyListException {
        if (linkedList == null || linkedList.size() == 0)
            throw new EmptyListException();
        if (n > linkedList.size()-1 || n < 0)
            throw new InvalidIndexException();

        Object obj = linkedList.get(n);
        return obj;
    }







}
