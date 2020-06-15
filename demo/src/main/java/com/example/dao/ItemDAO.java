package com.example.dao;

import java.util.List;

import com.example.vo.ItemVO;


public interface ItemDAO {

	public List<ItemVO> selectItemList();
	public ItemVO selectItemOne(int no);
	
	public List<ItemVO> selectItemSearch(String txt); //Item.selectItemSearch
	
	public int insertItemBatch(List<ItemVO> list);
	
	public int deleteItemBatch(int[] no);
	
	public List<ItemVO> selectItemWhere(int[] itemno);
	
	public int updateItemBatch(List<ItemVO> list);
	
	public int deleteItemOne(int no);

}
