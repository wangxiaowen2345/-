package com.haojie.dao;

import java.util.List;

import com.haojie.pojo.Suppliers;

public interface SuppliersDao {

	/**
	 * ��ѯ���й�Ӧ��
	 * @return
	 */
	public List<Suppliers> SelectAllSupplies();
	
	/**
	 * ����µĹ�Ӧ��
	 * @param s
	 * @return
	 */
	public boolean InsertSuppliers(Suppliers s);
	
	
	/**
	 * ��ѯ������Ӧ��
	 */
	public Suppliers SelectSuppliersByName(String name);
	
	
	/**
	 * ����ID�鹩Ӧ��
	 */
	public Suppliers SelectSuppliersById(String id);
}
