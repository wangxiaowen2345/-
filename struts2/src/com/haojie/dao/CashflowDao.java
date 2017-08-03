package com.haojie.dao;

import java.io.Serializable;
import java.util.List;

import com.haojie.pojo.Cashflow;

public interface CashflowDao {

	/**
	 * �����ˮ
	 * 
	 * @param cf
	 * @return
	 */
	public Serializable AddCashflow(Cashflow cf);

	/**
	 * ����NUM����ˮ
	 * 
	 * @param num
	 * @return
	 */
	public Cashflow CashFlowByNum(String num);

	/**
	 * �˻��ҵ�
	 * 
	 * @return
	 */
	public List<Cashflow> ReturnedList();

	/**
	 * ����Id����ˮ
	 * 
	 * @param num
	 * @return
	 */
	public Cashflow CashFlowById(int id);

	/**
	 * �ҵ��б�
	 */
	public List<Cashflow> ArrearsList();

	/**
	 * ����id��isture
	 * 
	 * @param id
	 */
	public void IstrueById(int id);
}
