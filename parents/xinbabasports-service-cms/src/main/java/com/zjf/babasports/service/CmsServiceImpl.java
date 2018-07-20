package com.zjf.babasports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjf.babasports.dao.ColorDao;
import com.zjf.babasports.dao.ProductDao;
import com.zjf.babasports.dao.SkuDao;
import com.zjf.babasports.pojo.Product;
import com.zjf.babasports.pojo.Sku;
import com.zjf.babasports.pojo.SkuQuery;

@Service("cmsService")
public class CmsServiceImpl implements CmsService{
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ColorDao colorDao;
	@Autowired
	private ProductDao productDao;
	/**
	 * 查找商品根据ID
	 * @param id
	 * @return
	 */
	public Product selectProductById(Long id){
		return productDao.selectByPrimaryKey(id);
	}
	/**
	 * 根据商品Id查询商品的库存
	 * @param id
	 * @return
	 */
	public List<Sku> selectSkuBuProductId(Long id){
		SkuQuery sq=new SkuQuery();
		//条件是 商品Id,库存大于0
		sq.createCriteria().andProductIdEqualTo(id).andStockGreaterThan(0);
		List<Sku> skus = skuDao.selectByExample(sq);
		for (Sku sku : skus) {
			sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
		}
	return skus;
	}
	
}
