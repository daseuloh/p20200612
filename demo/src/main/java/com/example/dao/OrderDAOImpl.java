package com.example.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vo.OrderVO;

@Service
@Transactional
public class OrderDAOImpl implements OrderDAO{
	
	@Autowired
	private SqlSessionFactory sqlFactory = null;

	@Override
	public List<OrderVO> selectItemOrder() {
		return sqlFactory.openSession().selectList("Order.selectOrderCnt");
	}

}
