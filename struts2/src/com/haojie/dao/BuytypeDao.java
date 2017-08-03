package com.haojie.dao;

import java.util.List;

import com.haojie.pojo.Buytype;

public interface BuytypeDao {

	/**
	 * ��ѯ����
	 * 
	 * @return
	 */
	public List<Buytype> AllBuytype();

	/**
	 * ����Id��ѯ
	 * 
	 * @param id
	 * @return
	 */
	public Buytype BuytypeById(int id);

	/**
	 * ����µ�
	 */
	public void AddBuytype(Buytype b);

	/**
	 * ��������
	 */
	public void UpdateBuytype(Buytype b);

	/**
	 * �������Ͳ�ѯ
	 */
	public Buytype BuytypeBytype(String type);

}
