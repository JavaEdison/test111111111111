package com.zjf.babasports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjf.babasports.pojo.Color;
import com.zjf.babasports.service.ColorService;

@Controller
@RequestMapping("/color")
public class ColorController {
	@Autowired
	private ColorService colorService;
	@RequestMapping("/sync.do")
	public @ResponseBody
	List<Color> selectAllColorBysync(){
		return colorService.selectAllColor();
	}
}
