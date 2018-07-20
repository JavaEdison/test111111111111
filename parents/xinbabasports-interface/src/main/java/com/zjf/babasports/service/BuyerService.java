package com.zjf.babasports.service;

import com.zjf.babasports.pojo.Buyer;

public interface BuyerService {
	/**
	 * 登陆
	 * @param buyer
	 * @return 系统判定登陆之后的信息
	 */
	public Buyer selectBuyerByUserName(Buyer buyer);
}
