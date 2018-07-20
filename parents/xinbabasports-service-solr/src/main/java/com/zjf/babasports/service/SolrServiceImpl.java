package com.zjf.babasports.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjf.babasports.dao.ProductDao;
import com.zjf.babasports.dao.SkuDao;
import com.zjf.babasports.pojo.Product;
import com.zjf.babasports.pojo.Sku;
import com.zjf.babasports.pojo.SkuQuery;
import com.zjf.babasports.pojo.SkuQuery.Criteria;
@Service("solrService")
@Transactional
public class SolrServiceImpl implements SolrService {
	@Autowired
	private SolrServer solrServer;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private SkuDao skuDao;
	
	@Override
	public void saveProductToSolr(Long id) {
		// TODO Auto-generated method stub
		SolrInputDocument doc=new SolrInputDocument();
		//商品ID
		doc.setField("id", id);
		//查找
		Product p = productDao.selectByPrimaryKey(id);
		//商品名称
		doc.setField("name_ik", p.getName());
		//商品图片
		doc.setField("url", (p.getImgUrl().split(","))[0]);
		//价格售价
		SkuQuery sku=new SkuQuery();
		Criteria skuCri = sku.createCriteria();
		sku.setFields("price");
		sku.setOrderByClause("price asc");
		sku.setPageNo(1);
		sku.setPageSize(1);
		skuCri.andProductIdEqualTo(id);
		List<Sku> result = skuDao.selectByExample(sku);
		//价格
		doc.setField("price", result.get(0).getPrice());
		//品牌ID
		doc.setField("brandId", p.getBrandId());
		//时间可选
		try {
			solrServer.add(doc);
			solrServer.commit();
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
