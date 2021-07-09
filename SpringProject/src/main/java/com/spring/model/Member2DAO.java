package com.spring.model;

import java.util.List;

public interface Member2DAO {

	public List<MemberDTO> getMemberList();
	public int insertMember(MemberDTO dto);
	public int idCheck(String id);
	public int pwdCheck(String pwd);
}