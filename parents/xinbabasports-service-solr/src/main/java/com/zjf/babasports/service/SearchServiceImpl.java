package com.zjf.babasports.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjf.babasports.pojo.Product;
import com.zjf.babasports.pojo.ProductQuery;

import cn.itcast.common.page.Pagination;

@Service("searchService")
public class SearchServiceImpl implements SearchService{
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public Pagination selectProductBySolr(Integer pageNo, String keyWord,Long brandId,String price) {
		// TODO Auto-generated method stub
		ProductQuery pq=new ProductQuery();
		//设置当前页
		pq.setPageNo(Pagination.cpn(pageNo));
		//设置页数大小
		pq.setPageSize(12);
		//拼接条件
		StringBuilder params=new StringBuilder();
		List<Product> list=new ArrayList<Product>();
		SolrQuery sq=new SolrQuery();
		//关键词
		sq.set("q", "name_ik:"+keyWord);
		params.append("keyWord=").append(keyWord);
		//过滤条件
		if(brandId!=null){
			sq.addFilterQuery("brandId:"+brandId);
			params.append("&brandId="+brandId);
		}
		if(price!=null){
			String[] p = price.split("-");
			if(price.length()<=1){
				sq.addFilterQuery("price:["+Float.parseFloat(p[0])+" TO *]");
			}else{
				sq.addFilterQuery("price:["+Float.parseFloat(p[0])+" TO "+p[1]+"]");
			}
			params.append("&price="+price);
		}
		//高亮
		sq.setHighlight(true);
		sq.addHighlightField("name_ik");
		//样式
		sq.setHighlightSimplePre("<span style='color:red'>");
		sq.setHighlightSimplePost("</span>");
		//排序
		sq.addSort("price", ORDER.asc);
		//分页
		sq.setStart(pq.getStartRow());
		sq.setRows(pq.getPageSize());
		QueryResponse response=null;
		try {
			response = solrServer.query(sq);
			
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SolrDocumentList docs = response.getResults();
		//取高亮
		/*
		 * 第一个map代表每一个产品
		 * 第二个map代表solr中存储的字段(我们取name_ik)
		 * 第三个list代表每个字段中的name_ik 个数
		 */
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		long nums = docs.getNumFound();
		for (SolrDocument doc : docs) {
			//创建商品对象
			Product temp=new Product();
			//商品ID
			String id =(String) doc.get("id");
			temp.setId(Long.parseLong(id));
			//商品名称
			/*String name =(String) doc.get("name_ik");
			temp.setName(name);*/
			Map<String, List<String>> map = highlighting.get(id);
			List<String> list2 = map.get("name_ik");
			temp.setName(list2.get(0));
			//图片
			String url=(String)doc.get("url");
			temp.setImgUrl(url);
			//价格
			temp.setPrice((Float)doc.get("price"));
			//品牌ID
			temp.setBrandId((Long)doc.get("brandId"));
			list.add(temp);
		}
		//构建分页对象
		Pagination page=new Pagination(
				pq.getPageNo(), 
				pq.getPageSize(), 
				Integer.parseInt(nums+""),
				list
				);
		//页面展示
		String url="/portal/search";
		page.pageView(url, String.valueOf(params));
		return page;
	}
	
}
