package com.zjf.babasports.service;

public interface SessionService {
		//生成csession
		public void setAttributeForUsername(String CSESSIONID,String value);
		//得到对应的session
		public String getAttributeForCSessionId(String CSESSIONID);
		//删除对应的session
		public void delSessionForCSESSIONID(String CSESSIONID);
}
