package com.zjf.babasports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjf.babasports.pojo.Product;
import com.zjf.babasports.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/toAdd.do")
	public String toAdd(Product product){
		productService.insertProductByProduct(product);
		return "redirect:/console/product/list.do";
	}
	@RequestMapping("/isShow.do")
	public String isShow(Long[] ids,String name,Boolean isShow,Integer pageNo,Model model){
		//更改商品状态
		productService.updateProductShowByIds(ids);
		/*//将数据上传至solr
		for (Long id : ids) {
			solrService.saveProductToSolr(id);
		}*/
		//静态化商品详情
		return "forward:/console/product/list.do";
	}
}
