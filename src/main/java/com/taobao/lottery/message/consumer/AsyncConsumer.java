package com.taobao.lottery.message.consumer;


import com.alibaba.common.lang.StringUtil;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.exception.MetaClientException;

public class AsyncConsumer {
    private static Logger logger = LoggerFactory.getLogger(AsyncConsumer.class);
	final String DEFAULT_TOPIC = "lottery-message";
    
    protected MessageListener listener;
    protected MessageConsumer consumer;
    
    public void subscribe(String topic) throws MetaClientException{
        logger.warn("consumerConfig.getGroup()=" + consumer.getConsumerConfig().getGroup());
    	if(StringUtil.isEmpty(topic)){
    		topic = DEFAULT_TOPIC;
    	}
    	consumer.subscribe(topic, 1024 * 1024, listener);
        consumer.completeSubscribe();
        logger.warn("subscribe " + topic + " topic ready!");
    }

    public void setListener(MessageListener listener) {
        this.listener = listener;
    }

    public void setConsumer(MessageConsumer consumer) {
        this.consumer = consumer;
    }
    
}
