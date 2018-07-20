package com.zjf.babasports.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjf.babasports.dao.BrandDao;
import com.zjf.babasports.pojo.Brand;
import com.zjf.babasports.pojo.BrandQuery;

import cn.itcast.common.page.Pagination;
import redis.clients.jedis.Jedis;
@Service("brandService")
@Transactional
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandDao brandDao;
	@Autowired
	private Jedis jedis;
	@Override
	public Pagination selectPaginationByQuery(Integer pageNo, String name, Integer isDisplay) {
		// TODO Auto-generated method stub
		BrandQuery brandQuery=new BrandQuery();
		//当前页 pageNo如果为null或为0  设置pageNo等于1
		brandQuery.setPageNo(Pagination.cpn(pageNo));
		//每页数
		brandQuery.setPageSize(3);
		//拼接
		StringBuffer params=new StringBuffer();
		//判断
		if(null!=name){
			brandQuery.setName(name);
			params.append("name=").append(name);
		}
		//是否可见
		if(null!=isDisplay){
			brandQuery.setIsDisplay(isDisplay);
			params.append("&isDisplay=").append(isDisplay);
		}else{
			brandQuery.setId(1);
			params.append("&isDisplay=1");
		}
		//构建分页对象
		Pagination pagination=new Pagination(brandQuery.getPageNo(),
				brandQuery.getPageSize(),
				brandDao.selectBrandCountByQuery(brandQuery),
				brandDao.selectBrandListByQuery(brandQuery));
		String url="/consoleBrand/brand/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}
	@Override
	public Brand selectBrandById(Integer id) {
		// TODO Auto-generated method stub
		return brandDao.selectBrandById(id);
	}
	@Override
	public void updateBrandByBrand(Brand brand) {
		// TODO Auto-generated method stub
		if(brand.getId()!=null){
			brandDao.updateBrandByBrand(brand);
			//每次更新则将更新后的数据加入redis中
			jedis.hset("brands", String.valueOf(brand.getId()), brand.getName());
		}
	}
	@Override
	public void deleteBrandByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		if(ids!=null&&ids.length>0){
			brandDao.deleteBrandByIds(ids);
			//删除数据
			String idss[]=new String[ids.length];
			for (int i=0;i<ids.length;i++) {
				idss[i]=String.valueOf(ids[i]);
			}
			jedis.hdel("brands", idss);
		}
	}
	@Override
	public List<Brand> selectBeandAll() {
		// TODO Auto-generated method stub
		BrandQuery brandQuery=new BrandQuery();
		brandQuery.setPageNo(1);
		brandQuery.setPageSize(brandDao.selectBrandCountByQuery(brandQuery));
		return brandDao.selectBrandListByQuery(brandQuery);
	}
	@Override
	public Map<String, String> selectBeandAllByRedis() {
		// TODO Auto-generated method stub
		if(jedis.hlen("brands")==0){
			List<Brand> l = selectBeandAll();
			for (Brand brand : l) {
				jedis.hset("brands", String.valueOf(brand.getId()), brand.getName());
			}
		}
		Map<String, String> map = jedis.hgetAll("brands");
//		if(jedis!=null)jedis.close();
		return map;
	}
	

}
