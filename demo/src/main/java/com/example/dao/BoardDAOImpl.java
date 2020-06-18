package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vo.BoardVO;

//DAO 어노테이션: @Service
//Controller 어노테이션 : @Controller

@Service
@Transactional
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired //@Bean으로 만들어진 객체를 받아옴
	private SqlSessionFactory sqlFactory = null;

	@Override
	public int insertBoard(BoardVO obj) {
		return sqlFactory.openSession().insert("Board.insertBoard", obj);
	}

	@Override
	public List<BoardVO> selectBoard(HashMap<String, Object> map) {
		return sqlFactory.openSession().selectList("Board.selectList", map);
	}

	@Override
	public BoardVO selectBoardOne(int no) {
		return sqlFactory.openSession().selectOne("Board.selectBoardOne", no);
	}

	@Override
	public int updateBoard(BoardVO obj) {
		return sqlFactory.openSession().update("Board.updateBoardOne", obj);
	}

	@Override
	public int deleteBoard(BoardVO obj) {
		return sqlFactory.openSession().delete("Board.deleteBoard", obj);
	}

	@Override
	public int countBoard(String text) {
		return sqlFactory.openSession().selectOne("Board.count", text);
	}

	@Override
	public int updateHit(int no) {
		return sqlFactory.openSession().update("Board.updateHit", no);
	}

	@Override
	public int insertBatch(List<BoardVO> list) {
		return sqlFactory.openSession().insert("Board.insertBatch", list);
	}

	@Override
	public BoardVO selectBoardImg(int no) {
		return sqlFactory.openSession().selectOne("Board.selectBoardImg", no);
	}

	@Override
	public int selectBoardPrev(int no) {
		return sqlFactory.openSession().selectOne("Board.selectBoardPrev", no);
	}

	@Override
	public int selectBoardNext(int no) {
		return sqlFactory.openSession().selectOne("Board.selectBoardNext", no);
	}

}
