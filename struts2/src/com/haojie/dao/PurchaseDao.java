package com.haojie.dao;

import java.io.Serializable;
import java.util.List;

import com.haojie.pojo.Purchase;
import com.haojie.utils.Page;

public interface PurchaseDao {

	/**
	 * �����ˮ
	 * 
	 * @param cf
	 * @return
	 */
	public Serializable AddPurchase(Purchase p);

	/**
	 * ����id������
	 * 
	 * @param id
	 * @return
	 */
	public Purchase PurchaseById(int id);

	/**
	 * �����б�
	 */
	public List<Purchase> PurchaseList(Page page);

	/**
	 * ����id��auditor
	 * 
	 * @param id
	 */
	public void AuditorById(int id);
}
