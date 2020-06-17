package com.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.example.vo.BoardVO;

public interface BoardMapper {
	
	@Delete("DELETE FROM BOARD WHERE BRD_NO = #{brd_no}") //xml역할
	public int deleteBoard(@Param("obj") BoardVO obj); //dao역할, 파라메터 여러개를 넘길 수 있음
	

	
	

}
