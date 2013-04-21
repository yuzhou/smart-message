package com.taobao.lottery.message.listener;

import java.util.Map;
import java.util.concurrent.Executor;


import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.consumer.MessageListener;

public class DefaultMsgListener implements MessageListener {

	private static Logger logger = LoggerFactory.getLogger(DefaultMsgListener.class);
	protected Executor metaCosumerExecute;
	
	protected Map msgProcessors;
	
	@Override
	public void recieveMessages(Message message) {
		String jsonStr = new String(message.getData());
		String type = message.getAttribute();
		logger.warn("get message:" + jsonStr + ";messgeType:"+ type);
		MetaProcessor processor = (MetaProcessor)msgProcessors.get(type);
		if(processor == null){
			logger.error("can't find processor !" + "type=" + type + ";msg=" + jsonStr);
			return;
		}
		processor.processMsg(type, jsonStr);
	}

	@Override
	public Executor getExecutor() {
		
		return metaCosumerExecute;
	}

    public void setMetaCosumerExecute(Executor metaCosumerExecute) {
        this.metaCosumerExecute = metaCosumerExecute;
    }

    public void setMsgProcessors(Map msgProcessors) {
        this.msgProcessors = msgProcessors;
    }
	
	

}
