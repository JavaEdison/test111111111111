package com.zjf.babasports.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjf.babasports.pojo.TestDb;
import com.zjf.babasports.service.TestDbService;

@Controller
@RequestMapping("/testController")
public class TestController {
	@Autowired
	private TestDbService testDbService;
	
	@RequestMapping("/index.do")
	public String index() throws Exception{
		TestDb testDb=new TestDb();
		testDb.setName("饭饭");
		testDb.setBirthday(new Date());
		testDbService.testInsert(testDb);
		return "index";
	}
}
