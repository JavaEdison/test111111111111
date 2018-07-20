package com.zjf.babasports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjf.babasports.dao.BuyerDao;
import com.zjf.babasports.pojo.Buyer;
import com.zjf.babasports.pojo.BuyerQuery;

@Service("buyerServie")
public class BuyerServiceImpl implements BuyerService{
	
	@Autowired
	private BuyerDao buyerDao;
	/**
	 * 登陆
	 * @param buyer
	 * @return 系统判定登陆之后的信息
	 */
	public Buyer selectBuyerByUserName(Buyer buyer){
		BuyerQuery bq=new BuyerQuery();
		bq.createCriteria().andUsernameEqualTo(buyer.getUsername());
		List<Buyer> users = buyerDao.selectByExample(bq);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}

}
