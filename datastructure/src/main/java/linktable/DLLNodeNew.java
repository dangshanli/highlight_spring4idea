package linktable;

/**
 * @author luzj
 * @description: 新的双向链表节点定义，更加的简介
 * data+2个指针 --> data+一个ptrdiff
 * ptrdiff = 前置节点 与 后置节点的地址的异或运算
 * 前驱⊕后置
 *
 * 在java中目前无法知道如果通过hashcode去拿到对应的对象，或许通过调用native方法可以得到
 *
 * c++/c 语言对于指针运算具有天然的支持，更加容易实现
 *
 * @date 2018/5/3
 */
public class DLLNodeNew<T> {
    private T data;
    DLLNodeNew<T> ptrdiff;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DLLNodeNew<T> getPtrdiff() {
        return ptrdiff;
    }

    public void setPtrdiff(DLLNodeNew<T> ptrdiff) {
        this.ptrdiff = ptrdiff;
    }
}


