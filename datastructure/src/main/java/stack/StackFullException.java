package stack;

/**
 * @author luzj
 * @description: Stack已满时push报异常
 * @date 2018/6/26
 */
public class StackFullException extends Exception {
    public StackFullException(String message) {
        super(message);
    }
}
