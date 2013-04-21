package com.taobao.lottery.message.producer;

import java.util.Map;

import net.sf.json.JSONObject;


import com.alibaba.common.lang.StringUtil;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.taobao.metamorphosis.exception.MetaClientException;

public class MetaProducer {

	protected MessageProducer producer;
	
	final String DEFAULT_TOPIC = "lottery-message";
	
	public SendResult sendMessage(String topic, String msgType,String msg) throws MetaClientException, InterruptedException{
		if(StringUtil.isEmpty(topic)){
			topic = DEFAULT_TOPIC;
		}
		producer.publish(topic);
		SendResult sendResult = producer.sendMessage(new Message(topic,msg.getBytes(),msgType));
		return sendResult;
	}
	
	public SendResult sendMessage(String topic,String msgType,Map map) throws MetaClientException, InterruptedException{
		if(StringUtil.isEmpty(topic)){
			topic = DEFAULT_TOPIC;
		}
		producer.publish(topic);
		JSONObject json = JSONObject.fromObject(map);
		String msg = json.toString();
		SendResult sendResult = producer.sendMessage(new Message(topic,msg.getBytes(),msgType));
		return sendResult;
	}
	
	public SendResult sendMessage(String topic,String msgType,Object beanObj) throws MetaClientException, InterruptedException{
		if(StringUtil.isEmpty(topic)){
			topic = DEFAULT_TOPIC;
		}
		producer.publish(topic);
		JSONObject json = JSONObject.fromObject(beanObj);
		String msg = json.toString();
		SendResult sendResult = producer.sendMessage(new Message(topic,msg.getBytes(),msgType));
		return sendResult;
	}

    public void setProducer(MessageProducer producer) {
        this.producer = producer;
    }
	

}
