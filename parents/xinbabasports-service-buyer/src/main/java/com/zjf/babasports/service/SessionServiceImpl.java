package com.zjf.babasports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service("sessionService")
public class SessionServiceImpl implements SessionService{
	@Autowired
	private Jedis jedis;
	//生成csession
	public void setAttributeForUsername(String CSESSIONID,String value){
		//将session存入redis集群中
		jedis.set(CSESSIONID+":"+"USER_NAME", value);
		//设置存活时间
		jedis.expire(CSESSIONID+":"+"USER_NAME", 6000);
	}
	//得到对应的session
	public String getAttributeForCSessionId(String CSESSIONID){
		String value = jedis.get(CSESSIONID+":"+"USER_NAME");
		if(value!=null){
			//每访问一次服务器都重新设置存活时间
			jedis.expire(CSESSIONID+":"+"USER_NAME", 6000);
		}
		return value;
	}
	//删除对应的session
	public void delSessionForCSESSIONID(String CSESSIONID){
		jedis.del(CSESSIONID+":"+"USER_NAME");
	}
}
