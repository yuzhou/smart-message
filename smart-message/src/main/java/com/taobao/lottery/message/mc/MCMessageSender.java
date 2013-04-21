package com.taobao.lottery.message.mc;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.taobao.messenger.exception.MessageTaskInitException;
import com.taobao.messenger.service.MessageSenderService;
import com.taobao.messenger.service.common.Channel;
import com.taobao.messenger.task.MessageTask;
import com.taobao.messenger.task.TaskResult;
import com.taobao.messenger.task.impl.DefaultResult;
import com.taobao.messenger.task.impl.MailMessageTask;
import com.taobao.messenger.task.impl.MobileMessageTask;

public class MCMessageSender {

    private static Logger log = LoggerFactory.getLogger(MCMessageSender.class);

    private MessageSenderService defaultMessageSenderService;
    private int emailMessageTypeId;// = 142180133
    private int emailMessageTemplateId;// = 142333410
    private int smsMessageTypeId;
    private int smsMessageTemplateId;
    private String sourceId;// lottery*lottery-web
    
    private String lotteryEnv;

    public TaskResult sendMail(String target, String subject, String content) {
        TaskResult taskResult = null;
        try {
            MessageTask task = new MailMessageTask();
            // ָ�����͵���������������ʹ�õ�ģ��
            task.addChannel(Channel.MAIL, emailMessageTemplateId);

            task.addTarget(target);
            // �趨��Ϣ����
            task.setMessageTypeId(emailMessageTypeId);
            // ���õ���source ������֤��ͳ�ƣ�������Ӧ�õ�·��
            task.setSourceId(sourceId);
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("subject", subject);
            m.put("content", content);
            task.setContext(m);
            taskResult = defaultMessageSenderService.send(task);
        } catch (MessageTaskInitException e) {
            log.error("�����ʼ���MCʧ��", e);
        } catch (Exception e) {
            log.error("�����ʼ���MCʧ��", e);
        }
        if (taskResult == null) {
            taskResult = new DefaultResult();
            taskResult.setFailure("������MCʧ�ܣ�taskResult == null");
        }
        return taskResult;
    }

    public TaskResult sendSMS(String target, String subject, String content) {
        if (!lotteryEnv.equals("prepare") && !lotteryEnv.equals("release")) {
            log.warn("[mock] send SMS to " + target + " : " + content);
            TaskResult r = new DefaultResult();
            r.setTaskID(0);
            r.setSuccess();
            return r;
        }
        
        TaskResult taskResult = null;
        try {
            // send to mc
            MessageTask task = new MobileMessageTask();
            // ָ�����͵���������������ʹ�õ�ģ��
            task.addChannel(Channel.SMS, smsMessageTemplateId);
            // ָ�����Ͷ��� MobileMessageTask ֱ��ʹ���ֻ����뷢��
            task.addTarget(target);
            // �趨��Ϣ����
            task.setMessageTypeId(smsMessageTypeId);
            // ���õ���source ������֤��ͳ�ƣ�������Ӧ�õ�·��
            task.setSourceId(sourceId);
            Map<String, Object> m = new HashMap<String, Object>();
            if (subject == null || "".equals(subject)) {
                subject = "�Ա���Ʊ";
            }
            m.put("subject", subject);
            m.put("content", content);
            task.setContext(m);
            taskResult = defaultMessageSenderService.send(task);
        } catch (MessageTaskInitException e) {
            log.error("���Ͷ�����MCʧ��: phone = " + target, e);
        } catch (Exception e) {
            log.error("���Ͷ�����MCʧ��", e);
        }
        if (taskResult == null) {
            taskResult = new DefaultResult();
            taskResult.setFailure("������MCʧ�ܣ�taskResult == null");
        }
        return taskResult;
    }

    public void setEmailMessageTypeId(int emailMessageTypeId) {
        this.emailMessageTypeId = emailMessageTypeId;
    }

    public void setEmailMessageTemplateId(int emailMessageTemplateId) {
        this.emailMessageTemplateId = emailMessageTemplateId;
    }

    public void setSmsMessageTypeId(int smsMessageTypeId) {
        this.smsMessageTypeId = smsMessageTypeId;
    }

    public void setSmsMessageTemplateId(int smsMessageTemplateId) {
        this.smsMessageTemplateId = smsMessageTemplateId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setDefaultMessageSenderService(MessageSenderService defaultMessageSenderService) {
        this.defaultMessageSenderService = defaultMessageSenderService;
    }
    
    public void setLotteryEnv(String lotteryEnv) {
        this.lotteryEnv = lotteryEnv;
    }

}
