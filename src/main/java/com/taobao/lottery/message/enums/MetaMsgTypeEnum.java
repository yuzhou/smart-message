package com.taobao.lottery.message.enums;

public enum MetaMsgTypeEnum {
	
	ORDER_EMAIL("40","订单复制"),//手机赠送也复用
	ORDER_PRE_FALSE("42","手机赠送未领取"),//手机赠送未领取
	WANGCAI_SMS("41","旺彩短息");
	
	private String type;
	private String describe;
	
	private MetaMsgTypeEnum(String type , String describe){
		this.type = type;
		this.describe = describe;                                                                                                                                              
	}
	
	public String getType(){
		return type;
	}
	
	public String getDescribe(){
		return describe;
	}
}
