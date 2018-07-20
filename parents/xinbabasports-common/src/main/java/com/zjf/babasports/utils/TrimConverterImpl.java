package com.zjf.babasports.utils;

import org.springframework.core.convert.converter.Converter;

public class TrimConverterImpl implements Converter<String, String>{

	@Override
	public String convert(String str) {
		// TODO Auto-generated method stub
		if(str!=null){
			str=str.trim();
			if(str.equals("")){
				return null;
			}else 
				return str;
		}else 
			return null;
	}

}
