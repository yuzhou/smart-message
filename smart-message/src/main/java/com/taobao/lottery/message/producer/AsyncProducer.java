package com.taobao.lottery.message.producer;


import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendMessageCallback;
import com.taobao.metamorphosis.client.producer.SendResult;

public class AsyncProducer {
	private static Logger logger = LoggerFactory.getLogger(AsyncProducer.class);
	protected MessageProducer producer;
	
	final String DEFAULT_TOPIC = "lottery-message";
	
	
	public void sendMessage(String topic,String msg){
		producer.sendMessage(new Message(topic, msg.getBytes()), new SendMessageCallback() {

            public void onMessageSent(final SendResult result) {
                if (result.isSuccess()) {
                	logger.error("Send message successfully,sent to " + result.getPartition());

                }
                else {
                	logger.error("Send message failed,error message:" + result.getErrorMessage());
                }

            }

            public void onException(final Throwable e) {
            	logger.error(e);
            }
        });
	}


    public void setProducer(MessageProducer producer) {
        this.producer = producer;
    }
	
}
