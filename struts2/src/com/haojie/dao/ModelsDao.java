package com.haojie.dao;

import java.util.List;

import com.haojie.pojo.Models;
import com.haojie.pojo.WareHouse;

public interface ModelsDao {
	
	/**
	 * ��ѯ���вֿ�
	 * @return
	 */
	public List<Models> AllModels();
	
	/**
	 * ����Id��ѯ�ֿ�
	 * @param id
	 * @return
	 */
	public Models ModelsById(int id);
	
	/**
	 * ����µĲֿ�
	 */
	public void AddModels(Models wh);
	
	/**
	 * ���²ֿ�����
	 */
	public void UpdateModels(Models wh);
	
	/**
	 * ����Id��ѯ�ֿ�
	 */
	public Models ModelsByName(String name);
	
	

}
