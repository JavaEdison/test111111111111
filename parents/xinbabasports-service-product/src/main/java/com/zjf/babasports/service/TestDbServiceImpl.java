package com.zjf.babasports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjf.babasports.dao.TestDbDao;
import com.zjf.babasports.pojo.TestDb;
import com.zjf.babasports.service.TestDbService;
@Service("testDbService")
@Transactional
public class TestDbServiceImpl implements TestDbService{
	@Autowired
	private TestDbDao testDbDao;
	@Override
	public void testInsert(TestDb testDb) throws Exception {
		// TODO Auto-generated method stub
		testDbDao.insertTest(testDb);
		//throw new RuntimeException();
	}

}
