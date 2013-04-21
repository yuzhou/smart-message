package com.taobao.lottery.message.enums;


public enum MCTemplateEnum {

	MESSAGE_STRING_IPHONE(8,"通用消息模版ID");//推动手机端通用模版ID,拼装好字符串直接发送
	
	private int type;
	private String describe;
	
	private MCTemplateEnum(int type , String describe){
		this.type = type;
		this.describe = describe;                                                                                                                                              
	}
	
	public int getType(){
		return type;
	}
	
	public String getDescribe(){
		return describe;
	}
}
