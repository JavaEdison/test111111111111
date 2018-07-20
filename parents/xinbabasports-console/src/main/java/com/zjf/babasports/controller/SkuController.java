package com.zjf.babasports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjf.babasports.pojo.ReturnUploadImage;
import com.zjf.babasports.pojo.Sku;
import com.zjf.babasports.service.SkuService;

@Controller
@RequestMapping("/sku")
public class SkuController {
	@Autowired
	private SkuService skuService;
	@RequestMapping("/skuList.do")
	public String skuList(Long productId,Model model){
		List<Sku> list = skuService.selectSkuByProductId(productId);
		model.addAttribute("skus", list);
		return "sku/list";
	}
	@RequestMapping("/updateSku.do")
	public @ResponseBody ReturnUploadImage updateSku(Sku sku){
		skuService.updateSkuBySku(sku);
		ReturnUploadImage returnUploadImage = new ReturnUploadImage();
		returnUploadImage.setState("SUCCESS");
		return returnUploadImage;
	}
}
