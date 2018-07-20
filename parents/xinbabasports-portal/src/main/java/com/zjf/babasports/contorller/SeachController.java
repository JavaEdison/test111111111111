package com.zjf.babasports.contorller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;
import com.zjf.babasports.pojo.Brand;
import com.zjf.babasports.service.BrandService;
import com.zjf.babasports.service.SearchService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/portal")
public class SeachController {
	
	@Autowired
	private SearchService searchService;// do something
	
	@Autowired
	private BrandService brandService;
	
	@RequestMapping("/search")
	public String search(Integer pageNo,String keyWord,Long brandId,String price,Model model){
		Pagination pagination = searchService.selectProductBySolr(pageNo, keyWord,brandId,price);
		model.addAttribute("pagination", pagination);
		model.addAttribute("keyWord", keyWord);
		HashMap<String,String> map=new HashMap<String,String>();
		if(brandId!=null){
		Brand bb = brandService.selectBrandById(Integer.parseInt(brandId.toString()));
		map.put("商品", bb.getName());
		}
		if(price!=null){
			if(price.length()<=3)map.put("价格", price+"以上");
			else map.put("价格", price);
		}
		//过滤条件
		model.addAttribute("filterQuery", map);
		//查找所有品牌
		model.addAttribute("brands",brandService.selectBeandAllByRedis());
		return "product";
	}
}
