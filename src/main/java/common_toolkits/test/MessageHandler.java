package common_toolkits.test;

/**
 * @author luzj
 * @package common_toolkits.test
 * @description:
 * @date 2018/2/6 15:54
 */
public abstract class MessageHandler {
    void handlerMessage(Message msg){
        //...
        msg.setClientCode(getClientCode());
        //...
        sendMessage(msg);
    }

    private void sendMessage(Message msg){
        System.out.println(msg.getClientCode());
    }

    abstract String getClientCode();
}
