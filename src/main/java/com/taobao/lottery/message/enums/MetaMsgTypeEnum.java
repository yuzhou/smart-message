package com.taobao.lottery.message.enums;

public enum MetaMsgTypeEnum {
	
	ORDER_EMAIL("40","��������"),//�ֻ�����Ҳ����
	ORDER_PRE_FALSE("42","�ֻ�����δ��ȡ"),//�ֻ�����δ��ȡ
	WANGCAI_SMS("41","���ʶ�Ϣ");
	
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
