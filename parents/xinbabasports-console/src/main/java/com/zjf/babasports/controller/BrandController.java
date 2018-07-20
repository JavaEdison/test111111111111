package com.zjf.babasports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjf.babasports.pojo.Brand;
import com.zjf.babasports.service.BrandService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/consoleBrand")
public class BrandController {
	@Autowired
	private BrandService brandService;
	@RequestMapping("/brand/list.do")
	public String brandList(Integer pageNo,Integer isDisplay,String name,Model model){
		Pagination pagination = brandService.selectPaginationByQuery(pageNo, name, isDisplay);
		//回显
		model.addAttribute("name", name);
		model.addAttribute("isDisplay", isDisplay==null?1:isDisplay);
		model.addAttribute("pagination", pagination);
		return "brand/list";
	}
	///brand/toEdit.do去编辑页面
	@RequestMapping("/brand/toEdit.do")
	public String toEdit(Integer id,Model model){
		Brand brand = brandService.selectBrandById(id);
		model.addAttribute("brand", brand);
		return "brand/edit";
	}
	//提交编辑页面redirect:
	@RequestMapping("/brand/commitEdit.do")
	public String commitEdit(Brand brand){
		brandService.updateBrandByBrand(brand);
		return "redirect:/consoleBrand/brand/list.do";
	}
	
	//批量删除
	@RequestMapping("/brand/delete.do")
	public String deletes(Integer[] ids,String name,Integer isDisplay,Integer pageNo){
		brandService.deleteBrandByIds(ids);
		/**
		 * 回显，采用forward保证数据不丢失
		 */
		return "forward:/consoleBrand/brand/list.do";
	}
	@RequestMapping("/brand/sync.do")
	public @ResponseBody
	List<Brand> getAllBrandBysync(){
		return brandService.selectBeandAll();
	}
}
