package com.zjf.babasports.dao;

import java.util.List;

import com.zjf.babasports.pojo.Brand;
import com.zjf.babasports.pojo.BrandQuery;

public interface BrandDao {
	/**
	 * 根据条件查询结果集
	 * @param brandQuery Brand类的扩展分页类
	 * @return
	 */
	public List<Brand> selectBrandListByQuery(BrandQuery brandQuery);
	/**
	 * 根据条件查询Brand的总数目
	 * @param brandQuery
	 * @return
	 */
	public Integer selectBrandCountByQuery(BrandQuery brandQuery);
	/**
	 * 根据Id查询品牌
	 * @param id
	 * @return
	 */
	public Brand selectBrandById(Integer id);
	/**
	 * 更新Brand
	 * @param brand
	 */
	public void updateBrandByBrand(Brand brand);
	/**
	 * 根据id数组批量删除品牌
	 * @param ids
	 */
	public void deleteBrandByIds(Integer[] ids);
}
