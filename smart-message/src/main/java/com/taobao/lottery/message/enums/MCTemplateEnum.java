package com.taobao.lottery.message.enums;


public enum MCTemplateEnum {

	MESSAGE_STRING_IPHONE(8,"ͨ����Ϣģ��ID");//�ƶ��ֻ���ͨ��ģ��ID,ƴװ���ַ���ֱ�ӷ���
	
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
