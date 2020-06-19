package com.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.vo.User1VO;

public interface User1Mapper {
	@Insert({ "INSERT INTO USER1 VALUES(MY_GET_SEQ_NUMBER, #{obj.username}, ",
			"#{obj.password}, #{obj.role}, SYSDATE)" })
	public int insertMember(@Param("obj") User1VO obj);
	
	@Select({ "SELECT * FROM USER1 WHERE USERNAME=#{username}"})
	public User1VO findByUsername(@Param("username") String username);

}
