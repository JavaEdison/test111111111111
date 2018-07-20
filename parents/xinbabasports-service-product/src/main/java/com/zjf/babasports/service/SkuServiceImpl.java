package com.zjf.babasports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjf.babasports.dao.SkuDao;
import com.zjf.babasports.pojo.Sku;
import com.zjf.babasports.pojo.SkuQuery;
import com.zjf.babasports.pojo.SkuQuery.Criteria;
@Service("skuService")
@Transactional
public class SkuServiceImpl implements SkuService {

	@Autowired
	private SkuDao skuDao;
	@Override
	public List<Sku> selectSkuByProductId(Long productId) {
		// TODO Auto-generated method stub
		SkuQuery skuQuery=new SkuQuery();
		Criteria criteria = skuQuery.createCriteria();
		criteria.andProductIdEqualTo(productId);
		List<Sku> list = skuDao.selectByExample(skuQuery);
		return list;
	}
	@Override
	public void updateSkuBySku(Sku sku) {
		// TODO Auto-generated method stub
		skuDao.updateByPrimaryKeySelective(sku);
	}

}
