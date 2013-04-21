package com.taobao.lottery.message.config;

import java.io.StringReader;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;
import com.taobao.lottery.message.util.DiamondUtils;

public class MessageDiamondManagerImpl implements MessageDiamondManager{
	
	 /**
     * 从diamond获取消息中心配置的dataId，默认为"com.taobao.lottery.service.message"
     */
	public final String MESSAGE_DATAID = "com.taobao.lottery.service.message";
    /**
     * 从diamond获取消息中心配置的groupId，默认为"com.taobao.lottery.service.groupId"
     */
	public final   String MESSAGE_GROUPID = "lottery";
	
    private static Log log = LogFactory.getLog(MessageDiamondManagerImpl.class);
    
    private final Map<String, String> messageMap =
        new ConcurrentHashMap<String, String>();

    private DiamondManager diamondManager;

    public void init() throws Exception {
    	log.warn("MessageDiamondManager==");
        this.diamondManager =
                new DefaultDiamondManager(MESSAGE_GROUPID,
                		MESSAGE_DATAID, new ManagerListener() {
                        @Override
                        public Executor getExecutor() {
                            return null;
                        }
                        @Override
                        public void receiveConfigInfo(String configInfo) {
                            final Properties properties = new Properties();
                            try {
                            	if(configInfo!=null){
                            		properties.load(new StringReader(configInfo));
                            		DiamondUtils.getProperties(properties, MessageDiamondManagerImpl.this.messageMap);
                            	}
                            }
                            catch (Exception e) {
                                log.error("从MessageDiamondManager加载 diamond配置失败", e);
                            }
                        }

                    });
        DiamondUtils.getProperties(this.diamondManager, 10000, this.messageMap);
    }

    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(this.messageMap);
    }
    
	public String getPropInfo(String key) {

		if(this.getProperties() != null && this.getProperties().size() != 0) {
			String val = this.getProperties().get(key);
			return val;
		}
		else {
			return null;
		}
	}

	public void setDiamondManager(DiamondManager diamondManager) {
		this.diamondManager = diamondManager;
	}
	

}
