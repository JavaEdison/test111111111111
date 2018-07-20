package com.zjf.babasports.staticMessage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import com.zjf.babasports.pojo.Color;
import com.zjf.babasports.pojo.Product;
import com.zjf.babasports.pojo.Sku;
import com.zjf.babasports.service.CmsService;
import com.zjf.babasports.service.StaticPageService;

public class StaticPageMessage implements MessageListener{

	@Autowired
	private CmsService cmsService;
	@Autowired
	private StaticPageService staticPageService;
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		ActiveMQTextMessage amm=(ActiveMQTextMessage)message;
		try {
			String id = amm.getText();
			Map<String,Object> root=new HashMap<>();
			//库存
			List<Sku> skus = cmsService.selectSkuBuProductId(Long.parseLong(id));
			//商品
			Product product = cmsService.selectProductById(Long.parseLong(id));
			//颜色
			Set<Color> colors = new HashSet<>();
			for (Sku sku : skus) {
				colors.add(sku.getColor());
			}
			root.put("product", product);
			root.put("skus", skus);
			root.put("colors", colors);
			//静态化
			staticPageService.productStaticPage(root, id);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
