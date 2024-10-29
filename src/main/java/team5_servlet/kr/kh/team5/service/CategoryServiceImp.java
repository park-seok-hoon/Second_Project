package team5_servlet.kr.kh.team5.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import team5_servlet.kr.kh.team5.dao.BoardDAO;
import team5_servlet.kr.kh.team5.dao.CategoryDAO;
import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.CategoryVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;

public class CategoryServiceImp implements CategoryService{
	
	private BoardService boardService = new BoardServiceImp();
	private CategoryDAO categoryDao;
	public CategoryServiceImp() {
		String resource = "team5_servlet/kr/kh/team5/config/config.xml";
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sessionFactory.openSession(true);
			categoryDao = session.getMapper(CategoryDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<CategoryVO> getCategoryList() {
		
		return categoryDao.selectCategoryList();
	}

	@Override
	public boolean insertCategory(String categoryName, ArrayList<CategoryVO> list) {
		if(categoryName.length() == 0 || categoryName == null) {
			return false;
		}
		//기존 카테고리명과 겹치면 false리턴
		for(CategoryVO tmp : list) {
			if(tmp.getC_name().equals(categoryName)) {
				System.out.println("duplication Category Name : " + categoryName);
				return false;
			}
		}
		return categoryDao.insertCategory(categoryName);
	}
	
	//관리자만 카테고리를 삭제를 해서 따로 예외처리를 하지는 않았습니다
	//카테고리 삭제 버튼은 관리자만 보일거 같아서요
	@Override
	public boolean categoryDelete(int cnum, MemberVO user ) {
		
		if(!user.getMb_nickname().equals("admin")) {
			return false;
		}
		//카테고리에 해당하는 게시판 번호 가져오기
		ArrayList<BoardVO> bnumList = boardService.selectBoardByCagoryNumList(cnum);
		//게시판 삭제
		boolean res;
		for(BoardVO tmp: bnumList) {	
			res = boardService.deleteBoard(tmp.getB_num(), user);
			if(!res) {
				System.out.println("게시판 삭제에서 문제 생김");
				return false;
			}
		}
		
		return categoryDao.deleteCategory(cnum);
	}

	@Override
	public CategoryVO getCategory(int num) {
		return categoryDao.selectCategory(num);
	}

	@Override
	public boolean CategoryUpdate(int num, String c_name) {
		if(c_name.length() == 0 || c_name == null) {
			return false;
		}
		return categoryDao.updateCategory(num, c_name);
	}
	
	
	
}
