package com.spring.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOImpl implements AdminDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int getListCount() {
		
		return this.sqlSession.selectOne("count");
	}

	@Override
	public List<AdminDTO> getMemberList(PageDTO dto) {
		
		return this.sqlSession.selectList("adminList", dto);
	}

	@Override
	public int insertProduct(ProductDTO dto) {
		
		return this.sqlSession.insert("insertProduct", dto);
	}

	@Override
	public int updateProduct(ProductDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduct(int no) {
		// TODO Auto-generated method stub
		return this.sqlSession.delete("deleteProduct", no);
	}


	@Override
	public AdminDTO getMemberdetail(int no) {
		
		return this.sqlSession.selectOne("admincont", no);
	}

	public List<CateDTO> getCateList() {
		
		return this.sqlSession.selectList("cateList");
	}


	@Override public List<AdminDTO> adminSearchList(PageDTO dto) {
	 
	 return this.sqlSession.selectList("searchList", dto); 
	 }


	@Override
	public int adminSearchMemberListCount(String keyword) {
		
		return this.sqlSession.selectOne("searchListCount", keyword);
	}

	@Override
	public int getProductAllListCount() {
		
		return this.sqlSession.selectOne("proAllListCount");
	}

	@Override
	public List<ProductDTO> getProductAllList(PageDTO dto) {
		 
		return this.sqlSession.selectList("proAllList", dto);
	}

	@Override
	public CateDTO getProductCate(int no) {
		
		return this.sqlSession.selectOne("productCate", no);
	}

	@Override
	public int updateProductNoImg(ProductDTO dto) {
		
		return this.sqlSession.update("updateProductNoImg", dto);
	}

	@Override
	public int insertCategory(CateDTO dto) {
		 
		return this.sqlSession.insert("insertCate", dto);
	}
	
	@Override
	public int updateCate(CateDTO dto) {
		
		return this.sqlSession.update("updateCate", dto);
	}

	@Override
	public int deleteCate(int no) {
		 
		return this.sqlSession.delete("deleteCate", no);
	}


}
