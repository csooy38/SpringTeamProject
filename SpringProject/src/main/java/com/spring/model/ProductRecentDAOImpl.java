package com.spring.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRecentDAOImpl implements ProductRecentDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	

}