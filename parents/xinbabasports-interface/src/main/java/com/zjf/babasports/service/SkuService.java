package com.zjf.babasports.service;

import java.util.List;

import com.zjf.babasports.pojo.Sku;

public interface SkuService {
	/**
	 *根据产品ID查询相应产品库存
	 * @param productId
	 * @return
	 */
	public List<Sku> selectSkuByProductId(Long productId);
	/**
	 * 根据新的sku更新旧的sku
	 * @param sku
	 */
	public void updateSkuBySku(Sku sku);
}
