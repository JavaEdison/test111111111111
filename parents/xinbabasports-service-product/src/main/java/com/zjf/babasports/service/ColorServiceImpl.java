package com.zjf.babasports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjf.babasports.dao.ColorDao;
import com.zjf.babasports.pojo.Color;
import com.zjf.babasports.pojo.ColorQuery;
import com.zjf.babasports.pojo.ColorQuery.Criteria;
@Service("colorService")
@Transactional
public class ColorServiceImpl implements ColorService {

	@Autowired
	private ColorDao colorDao;
	@Override
	public List<Color> selectAllColor() {
		// TODO Auto-generated method stub
		ColorQuery colorQuery=new ColorQuery();
		Criteria createCriteria = colorQuery.createCriteria();
		createCriteria.andParentIdNotEqualTo(0L);
		List<Color> list = colorDao.selectByExample(colorQuery);
		return list;
	}

}
