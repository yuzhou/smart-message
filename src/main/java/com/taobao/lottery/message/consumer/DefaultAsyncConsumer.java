package com.taobao.lottery.message.consumer;

import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;

public class DefaultAsyncConsumer extends AsyncConsumer {
    
    private static Logger logger = LoggerFactory.getLogger(DefaultAsyncConsumer.class);
    
    public void init(){
        try {
            subscribe(null);
        } catch (Exception e) {
            logger.error("start meta consumer failed.", e);
        }
    }
}
