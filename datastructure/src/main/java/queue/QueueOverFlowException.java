package queue;

/**
 * @author luzj
 * @description: 队列溢出异常，当已满的还入队
 * @date 2018/7/2
 */
public class QueueOverFlowException extends Exception {
    public QueueOverFlowException() {
    }

    public QueueOverFlowException(String message) {
        super(message);
    }
}
