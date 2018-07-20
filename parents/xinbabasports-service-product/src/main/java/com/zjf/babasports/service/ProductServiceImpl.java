package com.zjf.babasports.service;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjf.babasports.dao.ProductDao;
import com.zjf.babasports.dao.SkuDao;
import com.zjf.babasports.pojo.Product;
import com.zjf.babasports.pojo.ProductQuery;
import com.zjf.babasports.pojo.ProductQuery.Criteria;
import com.zjf.babasports.pojo.Sku;

import cn.itcast.common.page.Pagination;
import redis.clients.jedis.Jedis;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDao productDao;
	@Autowired
	private Jedis jedis;
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private JmsTemplate JmsTemplate;
	
	@Override
	public Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow) {
		// TODO Auto-generated method stub
		ProductQuery pq=new ProductQuery();
		Criteria criteria = pq.createCriteria();
		//设置分页的页数
		pq.setPageNo(Pagination.cpn(pageNo));
		pq.setPageSize(11);
		//拼接
		StringBuilder params=new StringBuilder();
		//模糊姓名条件
		if(name!=null){
			criteria.andNameLike("%"+name+"%");
			params.append("name=").append(name);
		}
		//所属品牌条件
		if(brandId!=null){
			criteria.andBrandIdEqualTo(brandId);
			params.append("&brandId=").append(brandId);
		}
		//是否下架，默认为下架的
		params.append("&isShow=");
		if(isShow==null){
			criteria.andIsShowEqualTo(false);
			params.append(false);
		}else{
			criteria.andIsShowEqualTo(isShow);
			params.append(isShow);
		}
		//构建分页对象
		Pagination pagination=new Pagination(pq.getPageNo(), pq.getPageSize(),productDao.countByExample(pq));
		pagination.setList(productDao.selectByExample(pq));
		pagination.pageView("/console/product/list.do", params.toString());
		return pagination;
	}

	@Override
	public void insertProductByProduct(Product product) {
		// TODO Auto-generated method stub
		Long np =jedis.incr("pn");
		product.setId(np);
		product.setCreateTime(new Date());
		product.setIsDel(true);
		product.setIsShow(false);
		productDao.insertSelective(product);
		String[] sizes = product.getSizes().split(",");
		String[] colors = product.getColors().split(",");
		//存入库存
		for (String color : colors) {
			Sku sku=new Sku();
			for (String size : sizes) {
				sku.setColorId(Long.parseLong(color));
				sku.setProductId(product.getId());
				sku.setSize(size);
				sku.setCreateTime(new Date());
				sku.setDeliveFee(8f);
				sku.setMarketPrice(888f);
				sku.setPrice(1000f);
				sku.setStock(0);
				sku.setUpperLimit(200);
				skuDao.insertSelective(sku);
			}
		}
	}

	@Override
	public void updateProductShowByIds(Long[] ids) {
		// TODO Auto-generated method stub
		for (final Long l : ids) {
			Product p=new Product();
			p.setId(l);
			p.setIsShow(true);
			productDao.updateByPrimaryKeySelective(p);
			//使用activeMQ发送消息到productId通道
			JmsTemplate.send("productId", new MessageCreator(){

				@Override
				public Message createMessage(Session session) throws JMSException {
					// TODO Auto-generated method stub
					return session.createTextMessage(String.valueOf(l));
				}
				
			});
		}
	}

}
