package common_toolkits.test.testFunction;

import common_toolkits.test.Message;

import java.util.function.Function;

/**
 * @author luzj
 * @package common_toolkits.test.testFunction
 * @description:
 * @date 2018/2/6 16:01
 */
public class MessageHandler {
    void handlerMessage(Message msg,Function getClientCode){
         msg.setClientCode("");
    }
}
