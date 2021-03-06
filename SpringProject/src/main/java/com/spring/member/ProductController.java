package com.spring.member;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.model.AdminDAO;
import com.spring.model.CateDTO;
import com.spring.model.PageDTO;
import com.spring.model.ProductDAO;
import com.spring.model.ProductDTO;
import com.spring.model.ProductLikeDAO;
import com.spring.model.ProductLikeDTO;
import com.spring.model.ProductRecentDAO;
import com.spring.model.ProductRecentDTO;
import com.spring.model.QnaDTO;
import com.spring.model.ReviewDTO;

@Controller
public class ProductController {

	@Autowired
	private ProductDAO pdao;
	
	@Autowired
	private ProductRecentDAO rdao;
	
	@Autowired
	private ProductLikeDAO ldao;
	
	@Autowired
	private AdminDAO adao;

	private int totalRecord = 0;
	private int rowsize = 0;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@RequestMapping("product_list.do")
	public String product_list(HttpServletRequest request, HttpSession session, Model model) {

		String user_id = (String) session.getAttribute("session_id");
		if (user_id == null)
			user_id = "guest";
		
		ProductLikeDTO ldto = new ProductLikeDTO();
		ldto.setLike_mem(user_id);
		
		int likeCheck = 0;
		int page = 0;
		int no = 0;
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		}
		
		if(request.getParameter("no") != null) {
			no = Integer.parseInt(request.getParameter("no"));
		}

		rowsize = 9;
		totalRecord = this.pdao.getProductListCount(no);
		
		PageDTO pageDTO = new PageDTO(page, rowsize, totalRecord, no);
		List<ProductDTO> list = this.pdao.getProductList(pageDTO);

		for (int i=0; i<list.size(); i++) {
			list.get(i).tag_split();
			
			ldto.setLike_product(list.get(i).getPro_no());
			likeCheck = ldao.checkLike(ldto);
			list.get(i).setLike_check(likeCheck);
		}
		
		List<CateDTO> cList = this.adao.getCateList();
		
		model.addAttribute("page", pageDTO);
		model.addAttribute("List", list);
		model.addAttribute("cList", cList);

		return "product/product_list";
	}

	@RequestMapping("product_cont.do")
	public String product_cont(@RequestParam("no") int pro_no, HttpSession session, HttpServletRequest request,
			Model model) {

		String user_id = (String) session.getAttribute("session_id");
		if (user_id == null)
			user_id = "guest";

		ProductDTO pdto = this.pdao.getProductCont(pro_no); // ?????? ?????? ??????
		pdto.tag_split();

		ProductRecentDTO rdto = new ProductRecentDTO();
		rdto.setRecent_product(pro_no);
		rdto.setRecent_mem(user_id);

		
		// ???????????? ????????? ?????? 
		ProductLikeDTO ldto = new ProductLikeDTO();
		ldto.setLike_mem(user_id);
		ldto.setLike_product(pro_no);
		
		int	likeCheck = ldao.checkLike(ldto);
		 
		// ?????? ??? ????????? ??????
		int check = this.rdao.recentProCheck(rdto); // ?????? ??? ?????? ??? ?????? ????????? ????????? ??????
		if (check > 0) {
			this.rdao.updateRecentPro(rdto); // ????????? ????????? ?????? ?????? ?????? ?????? ?????? ???????????? ??????
		} else {
			this.rdao.addRecent(rdto); // ?????? ??? ????????? ??????
		}

		// ?????? ????????? - ??????
		int rpage = 0;
		if (request.getParameter("rpage") != null) {
			rpage = Integer.parseInt(request.getParameter("rpage"));
		} else {
			rpage = 1;
		}

		rowsize = 5;
		totalRecord = this.pdao.getReviewListCount(pro_no);

		PageDTO rpageDTO = new PageDTO(rpage, rowsize, totalRecord, pro_no);
		List<ReviewDTO> rlist = this.pdao.getReviewList(rpageDTO);

		int star = this.pdao.getTotalStar(pro_no);

		// ?????? ????????? - ?????????
		int ppage = 0;
		if (request.getParameter("ppage") != null) {
			ppage = Integer.parseInt(request.getParameter("ppage"));
		} else {
			ppage = 1;
		}

		int ptotalRecord = this.pdao.getReviewPhotoCount(pro_no);

		PageDTO ppageDTO = new PageDTO(ppage, rowsize, ptotalRecord, pro_no);
		List<ReviewDTO> plist = this.pdao.getReviewPhotoList(ppageDTO);

		// qna ?????????
		int qpage = 0;
		if (request.getParameter("qpage") != null) {
			qpage = Integer.parseInt(request.getParameter("qpage"));
		} else {
			qpage = 1;
		}

		int qtotalRecord = this.pdao.getProQnaListCount(pro_no);

		PageDTO qpageDTO = new PageDTO(qpage, rowsize, qtotalRecord, pro_no);
		List<QnaDTO> qlist = this.pdao.getProQnaList(qpageDTO);
		
		if(qlist.size() != 0) {
			for(int i=0; i<qlist.size(); i++) {
				qlist.get(i).setQnaDTO(this.pdao.getProQnaAnswerList(qlist.get(i).getQna_no()));
			}
		}
		
		model.addAttribute("cont", pdto);

		model.addAttribute("likeCheck", likeCheck);

		model.addAttribute("review", rlist);
		model.addAttribute("rpage", rpageDTO);
		model.addAttribute("rtotal", totalRecord);
		model.addAttribute("star", star);

		model.addAttribute("photo", plist);
		model.addAttribute("ppage", ppageDTO);
		model.addAttribute("ptotal", ptotalRecord);

		model.addAttribute("qna", qlist);
		model.addAttribute("qpage", qpageDTO);
		model.addAttribute("qtotal", qtotalRecord);

		return "product/product_cont";
	}


	@RequestMapping("product_search.do")
	public String product_search_list(@RequestParam("k") String keyword, HttpServletRequest request, Model model) {

		int page = 0;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		}

		rowsize = 8;
		totalRecord = this.pdao.getSearchListCount(keyword);

		String field = "";
		PageDTO pageDTO = new PageDTO(page, rowsize, totalRecord, field, keyword);
		List<ProductDTO> list = this.pdao.getSearchList(pageDTO);

		for (int i = 0; i < list.size(); i++) {
			list.get(i).tag_split();
		}

		model.addAttribute("page", pageDTO);
		model.addAttribute("List", list);
		model.addAttribute("keyword", keyword);
		model.addAttribute("size", totalRecord);

		return "product/product_search_list";
	}

	@RequestMapping("search_tag.do")
	public String tag_search(@RequestParam("k") String keyword, HttpServletRequest request, Model model) {

		int page = 0;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		}

		rowsize = 8;
		totalRecord = this.pdao.getSearchTagCount(keyword);

		String field = "";
		PageDTO pageDTO = new PageDTO(page, rowsize, totalRecord, field, keyword);
		List<ProductDTO> list = this.pdao.getSearchTagList(pageDTO);

		for (int i = 0; i < list.size(); i++) {
			list.get(i).tag_split();
		}

		model.addAttribute("page", pageDTO);
		model.addAttribute("List", list);
		model.addAttribute("keyword", keyword);
		model.addAttribute("size", totalRecord);

		return "product/product_search_list";
	}

	@RequestMapping("product_qna_write.do")
	public String pro_qna_write(@RequestParam("no") int product_no, Model model) {

		ProductDTO dto = this.pdao.getProductCont(product_no);
		dto.tag_split();
		model.addAttribute("dto", dto);

		return "product/product_qna_write";
	}

	@RequestMapping("product_qna_write_ok.do")
	public void pro_qna_write_ok(QnaDTO dto, HttpSession session, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; UTF-8");
		PrintWriter out = response.getWriter();

		String user_id = (String) session.getAttribute("session_id");
		if (user_id == null) {
			user_id = "guest";
		}

		dto.setQna_writer(user_id);
		dto.setQna_category_no(7);

		int result = this.pdao.insertProQna(dto);

		if (result > 0) {
			out.println("<script>");
			out.println("alert('?????? Q&A??? ?????????????????????.')");
			out.println("opener.document.location.reload()");
			out.println("window.close()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('qna ?????? ??????')");
			out.println("history.back()");
			out.println("</script>");
		}

	}

	@RequestMapping("qna_delete.do")
	public void qna_delete(@RequestParam("no") int qna_no, @RequestParam("pno") int product_no,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html; UTF-8");
		PrintWriter out = response.getWriter();

		int result = this.pdao.deleteQna(qna_no);
		
		if (result > 0) {
			String check = this.pdao.qnaGroupCount(qna_no);
			
			if(check != null) {
				this.pdao.deleteQna(Integer.parseInt(check));
			}
			out.println("<script>");
			out.println("alert('?????????????????????.')");
			out.println("location.href='product_cont.do?no=" + product_no + "#qna'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('qna ?????? ??????')");
			out.println("</script>");
		}
	}

	@RequestMapping("product_qna_modify.do")
	public String pro_qna_modify(@RequestParam("no") int qna_no, @RequestParam("pno") int product_no, Model model) {

		QnaDTO qdto = this.pdao.getQnaCont(qna_no);
		ProductDTO pdto = this.pdao.getProductCont(product_no);
		pdto.tag_split();

		model.addAttribute("pdto", pdto);
		model.addAttribute("qdto", qdto);

		return "product/product_qna_modify";
	}

	@RequestMapping("product_qna_modify_ok.do")
	public void pro_qna_modify_ok(QnaDTO dto, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; UTF-8");
		PrintWriter out = response.getWriter();

		int result = this.pdao.updateProQna(dto);

		if (result > 0) {
			out.println("<script>");
			out.println("alert('?????????????????????.')");
			out.println("opener.document.location.reload()");
			out.println("window.close()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('qna ?????? ??????')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	@RequestMapping("product_qna_answer.do")
	public String pro_qna_ans(@RequestParam("no") int qna_no, @RequestParam("pno") int product_no, Model model) {

		QnaDTO qdto = this.pdao.getQnaCont(qna_no);
		ProductDTO pdto = this.pdao.getProductCont(product_no);
		pdto.tag_split();

		model.addAttribute("pdto", pdto);
		model.addAttribute("qdto", qdto);

		return "product/product_qna_answer";
	}

	@RequestMapping("product_qna_answer_ok.do")
	public void pro_qna_ans_ok(QnaDTO dto, HttpServletResponse response, HttpSession session) throws IOException {

		response.setContentType("text/html; UTF-8");
		PrintWriter out = response.getWriter();

		String user_id = (String) session.getAttribute("session_id");
		if (user_id == null) {
			user_id = "guest";
		}

		dto.setQna_writer(user_id);
		dto.setQna_category_no(7);

		int result = this.pdao.insertProQnaAns(dto);

		if (result > 0) {
			out.println("<script>");
			out.println("alert('????????? ?????????????????????.')");
			out.println("opener.document.location.reload()");
			out.println("window.close()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('qna ?????? ??????')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}
