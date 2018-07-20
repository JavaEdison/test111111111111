package com.zjf.babasports.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONObject;
import com.zjf.babasports.pojo.ReturnUploadImage;
import com.zjf.babasports.utils.UploadUtils;

@Controller
@RequestMapping("/upload")
public class UploadController {
	@RequestMapping("/uploadPic.do")
	public @ResponseBody String uploadPic(MultipartFile pic){
		String imgUrl=null;
		if(pic!=null){
			String path="E:/GraduationProject/picture/";
			imgUrl="http://localhost:8084/picture/"+UploadUtils.UploadFile(path, pic);
			}
		JSONObject json=new JSONObject();
		json.put("imgUrl", imgUrl);
		return json.toString();
	}
	@RequestMapping("/uploadPics.do")
	public @ResponseBody List<String> uploadPics(@RequestParam("pics")MultipartFile[] pics){
		List<String> list=new ArrayList<>();
		for (MultipartFile pic : pics) {
		if(pic!=null){
			String path="E:/GraduationProject/picture/";
			String imgUrl="http://localhost:8084/picture/"+UploadUtils.UploadFile(path, pic);
			list.add(imgUrl);
		}
		}
		return list;
	}
	@RequestMapping("/uploadUE.do")/*返回json格式字符串要完全正确*/
	public @ResponseBody ReturnUploadImage uploadUE(HttpServletRequest request){
		MultipartRequest mr=(MultipartRequest)request;
		Map<String, MultipartFile> pics = mr.getFileMap();
		ReturnUploadImage rui = null;//这个是UEditor需要的返回值内容，UEditor要的返回值需要封装成Json格式
		for(Entry<String, MultipartFile> p:pics.entrySet()){
			MultipartFile pic = p.getValue();
			if(pic!=null){
				rui = new ReturnUploadImage();
				String path="E:/GraduationProject/picture/";
				String fileName = UploadUtils.UploadFile(path, pic);
				rui.setOriginal(fileName);
				rui.setTitle(fileName);
				String imgUrl="http://localhost:8084/picture/"+fileName;
				rui.setState("SUCCESS");
				rui.setUrl("/"+fileName);
			}
		}
		return rui;
	}
}
