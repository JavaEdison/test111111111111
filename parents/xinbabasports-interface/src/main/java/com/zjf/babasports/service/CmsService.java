package com.zjf.babasports.service;

import java.util.List;

import com.zjf.babasports.pojo.Product;
import com.zjf.babasports.pojo.Sku;

public interface CmsService {
	/**
	 * 查找商品根据ID
	 * @param id
	 * @return
	 */
	public Product selectProductById(Long id);
	/**
	 * 根据商品Id查询商品的库存
	 * @param id
	 * @return
	 */
	public List<Sku> selectSkuBuProductId(Long id);
}
