package stack;

/**
 * @author luzj
 * @description: stack为空时pop报出该异常
 * @date 2018/6/26
 */
public class StackEmptyException extends Exception {
    public StackEmptyException() {
    }

    public StackEmptyException(String message) {
        super(message);
    }
}
