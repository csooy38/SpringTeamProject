package com.spring.model;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int snsJoinCheck(HashMap<String, String> hm) {
		
		return this.sqlSession.selectOne("snsJoinCheck", hm);
	}

	@Override
	public int snsJoin(MemberDTO dto) {
		
		return this.sqlSession.insert("snsJoin", dto);
	}

	@Override
	public MemberDTO getSnsMemInfo(HashMap<String, String> hm) {
		
		return this.sqlSession.selectOne("snsInfo", hm);
	}

	@Override
	public int deleteSnsID(String id) {
		
		return this.sqlSession.update("deleteSnsID", id);
	}

	@Override
	public int addSnsID(HashMap hm) {
		
		return this.sqlSession.insert("addSnsID", hm);
	}

	@Override
	public int snsConnectCheck(HashMap hm) {
		
		return this.sqlSession.selectOne("snsConnectCheck", hm);
	}
}
