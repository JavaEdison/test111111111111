package com.zjf.babasports.service;

import java.util.List;
import java.util.Map;

import com.zjf.babasports.pojo.Brand;

import cn.itcast.common.page.Pagination;

public interface BrandService {
	/**
	 * 根据条件查询结果集并放入分页对象Pagination中
	 * @param pageNo 当前页
	 * @param name	查询条件1
	 * @param isDisplay 查询条件2是否可用
	 * @return
	 */
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Integer isDisplay);
	/**
	 * 根据id查询相应的品牌名称
	 * @param id
	 * @return
	 */
	public Brand selectBrandById(Integer id);
	/**
	 * 更新相应的品牌数据
	 * @param brand
	 */
	public void updateBrandByBrand(Brand brand);
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteBrandByIds(Integer[] ids);
	/**
	 * 查找所有
	 * @return
	 */
	public List<Brand> selectBeandAll();
	/**
	 * 由于没有用Spring的缓存，只能手动建立一个方法从redis中查找所有
	 * @return
	 */
	public Map<String, String> selectBeandAllByRedis();
}
