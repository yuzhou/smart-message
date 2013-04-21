package com.taobao.lottery.message.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.taobao.diamond.manager.DiamondManager;


/**
 * ��diamond����������
 * 
 * @author xuanchen.lbj@taobao.com
 * @Date 2012-8-29
 * 
 */
public class DiamondUtils {
   
    static final Log log = LogFactory.getLog(DiamondUtils.class);
    
    public static void getProperties(final DiamondManager diamondManager, final long timeout,
            final Map<String, String> partitionsMap) {
        Properties props = null;
        try {
            props = diamondManager.getAvailablePropertiesConfigureInfomation(timeout);
        }
        catch (final Exception e) {
            log.warn(e.getMessage());
        }

        log.info("��diamond����Properties���ã�" + props);
        getProperties(props, partitionsMap);
    }

    public static void getProperties(final Properties properties, final Map<String, String> ret){
        log.info("��Ϣ������Ϣ�����ļ�");
        final Map<String, String> map = new HashMap<String, String>();
        if (properties != null) {
            for (final Map.Entry<Object, Object> entry : properties.entrySet()) {
                final String key = (String) entry.getKey();
                final String value = (String) entry.getValue();
                if (value != null && !value.isEmpty()) {
                        map.put(key, value);
                    }
                
            }
            ret.clear();
            ret.putAll(map);
            if (!ret.isEmpty()) {
                log.info("������Ϣ: " + map);
            }
            else {
                log.info("empty Properties info");
            }
        }
        else {
            log.warn("Null Properties config");
        }
    }
}
