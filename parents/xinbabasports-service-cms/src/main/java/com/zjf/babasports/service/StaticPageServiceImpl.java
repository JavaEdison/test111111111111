package com.zjf.babasports.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service("staticPageService")
public class StaticPageServiceImpl implements StaticPageService,ServletContextAware{
	private ServletContext servletContext;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer; 
	private Configuration configuration=null;
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext=servletContext;
	}
	//静态化 商品  ActiveMQ
	public void productStaticPage(Map<String,Object> root,String id){
		//输出的路径全路径
		String path=getPath("/html/product/"+id+".html");
		File f=new File(path);
		File parent = f.getParentFile();
		if(!parent.exists()){
			//创建多重目录
			parent.mkdirs();
		}
		Writer out=null;
		try {
			configuration=freeMarkerConfigurer.getConfiguration();
			Template template =configuration.getTemplate("productDetail.html");
			out=new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			//处理
			template.process(root, out);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
				try {
					if(out!=null)
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public String getPath(String path){
		return servletContext.getRealPath(path);
	}

}
