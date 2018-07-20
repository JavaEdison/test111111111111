package com.zjf.babasports.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtils {
	public static String UploadFile(String Path,MultipartFile picture){
		if(picture!=null){
			try {
			String fileName = picture.getOriginalFilename();
			String newName=UUID.randomUUID().toString().replace("-", "")+fileName.substring(fileName.lastIndexOf("."));
			File file=new File(Path+newName);
			picture.transferTo(file);
			return newName;
			} catch (IllegalStateException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			//news.setImage("http://localhost:8080/picture/"+newName);
			}
		return null;
	}
}
