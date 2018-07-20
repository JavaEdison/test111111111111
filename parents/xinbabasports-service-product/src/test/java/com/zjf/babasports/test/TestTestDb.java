package com.zjf.babasports.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zjf.babasports.pojo.TestDb;
import com.zjf.babasports.service.TestDbService;

/**
 * 测试 测试类
 * @author admin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class TestTestDb {
	@Autowired
	private TestDbService testDbService;
	@Test
	public void testInsert() {
		// TODO Auto-generated method stub
		TestDb testDb=new TestDb();
		testDb.setBirthday(new Date());
		testDb.setName("FBB233");
		try {
			testDbService.testInsert(testDb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
