package com.zjf.babasports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjf.babasports.service.BrandService;
import com.zjf.babasports.service.ProductService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/console")
public class CenterController {
	@Autowired
	private ProductService productService;
	@Autowired
	private BrandService brandService;
	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}
	@RequestMapping("/top.do")
	public String top(){
		return "top";
	}
	@RequestMapping("/main.do")
	public String main(){
		return "main";
	}
	@RequestMapping("/left.do")
	public String left(){
		return "left";
	}
	@RequestMapping("/right.do")
	public String right(){
		return "right";
	}
	@RequestMapping("/frame/product_main.do")
	public String product_main(){
		return "frame/product_main";
	}
	@RequestMapping("/frame/product_left.do")
	public String product_left(){
		return "frame/product_left";
	}
	@RequestMapping("/product/list.do")
	public String product_list1(Integer pageNo,String name,Long brandId,Boolean isShow,Model model){
		Pagination pagination = productService.selectPaginationByQuery(pageNo, name, brandId, isShow);
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		model.addAttribute("brandId", brandId);
		model.addAttribute("brandList", brandService.selectBeandAll());
		model.addAttribute("isShow", isShow==null?false:isShow);
		return "product/list";
	}
	@RequestMapping("/product/add.do")
	public String add(){
		return "product/add";
	}
}
