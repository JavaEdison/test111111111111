package com.zjf.babasports.service;

import com.zjf.babasports.pojo.Product;

import cn.itcast.common.page.Pagination;

public interface ProductService {
	/**
	 * 根据条件查询结果集并放入分页对象Pagination中
	 * @param pageNo 当前页
	 * @param name	查询条件1
	 * @param isDisplay 查询条件2是否可用
	 * @return
	 */
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Long brandId,Boolean isShow);
	/**
	 * 添加相应的产品数
	 * @param product
	 */
	public void insertProductByProduct(Product product);
	/**
	 * 批量上架商品
	 * @param ids
	 */
	public void updateProductShowByIds(Long[] ids);
}
