package com.zjf.babasports.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import com.zjf.babasports.service.SolrService;

public class CustomMessageListener implements MessageListener {

	@Autowired
	private SolrService solrService;
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		//转换成对应的activeMq消息
		ActiveMQTextMessage am=(ActiveMQTextMessage)message;
		try {
			//得到消息的内容
			String id = am.getText();
			System.out.println(id);
			//调用存储的方法
			solrService.saveProductToSolr(Long.parseLong(id));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
