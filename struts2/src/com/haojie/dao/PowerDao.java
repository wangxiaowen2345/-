package com.haojie.dao;

import java.util.List;

import com.haojie.pojo.Power;
import com.haojie.pojo.PowerAss;
import com.haojie.pojo.PowerFuntion;

public interface PowerDao {

	/**
	 * ����ID��ѯȨ�����б�
	 * @param id
	 * @return
	 */
	public Power getPowerNameById(String id);
	
	
	/**
	 * ��ȡ���е�Ȩ�����б�
	 * @return
	 */
	public List<Power> getAllPower();
	
	/**
	 * ��ѯ�Ƿ���Ȩ��
	 * @return
	 */
	public boolean Power(int PowerId,int FunctionId);
	
	/**
	 * ����id�����Ȩ�޷���
	 * @param id
	 * @return
	 */
	public List<PowerFuntion> PowerFunctionByPowerId(int id);
	
	/**
	 * �����Ȩ��
	 */
	public void SavePower(Power power);
	
	
	/**
	 * �û���Ȩ�޷��䱣�����
	 */
	public void SavaPowerAss(PowerAss powerass);
	
	/**
	 * �������ֲ�Ȩ����
	 * @param powername
	 * @return
	 */
	public Power getPowerByName(String powername);
	
	/**
	 * ɾ��Ȩ��
	 * @param id
	 */
	public void DeletePowerFunctionById(int id);
	
	/**
	 * ����Ȩ������
	 * @param power
	 */
	public void UpdatePowerById(Power power);
	
	
}
