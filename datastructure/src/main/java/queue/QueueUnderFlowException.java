package queue;

/**
 * @author luzj
 * @description: 队列下溢异常，当已空的时候还出队
 * @date 2018/7/2
 */
public class QueueUnderFlowException extends Exception {
    public QueueUnderFlowException() {
    }

    public QueueUnderFlowException(String message) {
        super(message);
    }
}
