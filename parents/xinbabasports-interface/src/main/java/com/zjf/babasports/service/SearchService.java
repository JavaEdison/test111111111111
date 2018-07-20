package com.zjf.babasports.service;

import cn.itcast.common.page.Pagination;

public interface SearchService {
	/**
	 * 前台搜索页面从solr数据库中查询产生结果集
	 * @param pageNo 当前页
	 * @param keyWord 叶大小
	 * @return
	 */
	public Pagination selectProductBySolr(Integer pageNo,String keyWord,Long BrandId,String price);
}
