package com.zjf.babasports.service;

import java.util.Map;

public interface StaticPageService {
	//静态化 商品  ActiveMQ
	public void productStaticPage(Map<String,Object> root,String id);
	/**
	 * 获取项目绝对路径
	 * @param path
	 * @return
	 */
	public String getPath(String path);
}
