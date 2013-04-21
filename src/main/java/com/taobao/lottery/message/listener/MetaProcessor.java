package com.taobao.lottery.message.listener;

public interface MetaProcessor {
	
	void processMsg(String type,String jsonStr);
}
