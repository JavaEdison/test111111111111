package com.zjf.babasports.service;

public interface SolrService {
	/**
	 * 根据ID查找产品并保存到solr服务器
	 * @param id 上架产品ID
	 */
	public void saveProductToSolr(Long id);
}
