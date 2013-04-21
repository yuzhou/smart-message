package com.taobao.lottery.message.mbpush;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.taobao.nb.mobile.push.PushSender4HSF;

public class SendWirelessMessage {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	   
	private PushSender4HSF pushSenderHSF;
	
	public PushSender4HSF getPushSenderHSF() {
		return pushSenderHSF;
	}

	public void setPushSenderHSF(PushSender4HSF pushSenderHSF) {
		this.pushSenderHSF = pushSenderHSF;
	}

	@SuppressWarnings("unused")
	public boolean sendWireless(int messageType, Map<String, Object> context,
			Long  userIDNum) {
		String msgStr = null;
		Map<String, Object> urlContext = new HashMap<String, Object>();
		urlContext.put("linktype", "http");
		if(messageType>0&&userIDNum>0) {
			log.warn("successfully send wireless message, userIDNum:" + userIDNum + ", content:" + context.get("msg"));
			pushSenderHSF.send(userIDNum, "lottery", messageType, context, urlContext);
		} else {
			return false;
		}
		return true;
	}
}
