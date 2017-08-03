package com.haojie.dao;

import java.util.List;

import com.haojie.pojo.Repair;
import com.haojie.pojo.RepairRecord;

public interface RepairDao {

	/**
	 * ���ά�ޣ�ά�޽�����
	 * 
	 * @param repair
	 */
	public void AddRepair(Repair repair);

	/**
	 * ��ѯά�޵�������
	 * 
	 * @param num
	 * @return
	 */
	public Repair RepairByNum(String num);

	/**
	 * ά�޳�����¼
	 */
	public List<Repair> RepairedList();

	/**
	 * ��ʱ�����ά�޼�¼
	 */
	public List<Object> FastRepairTime1(String fast1);

	/**
	 * ��ʱ�����ά�޼�¼
	 */
	public List<Object> FastRepairTime(String fast1, String fast2);

	/**
	 * ��������ά�޼�¼
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastRepair(String fast);

	/**
	 * ����ά�޳����б�
	 * 
	 * @return
	 */
	public List<Repair> RepairIngList();

	/**
	 * ɾ��ά�޶���
	 * 
	 * @param num
	 */
	public void DeleteRepair(String num);

	/**
	 * ����WRID��ѯά�޼�¼
	 */
	public RepairRecord RepairRecordByWRId(int id);

	/**
	 * ���ά�޼�¼
	 * 
	 * @param repairrecord
	 */
	public void AddRepairRecord(RepairRecord repairrecord);

	/**
	 * ɾ��ά�޶��������¼
	 */
	public void DeleteRepairRecordById(int id);

	/**
	 * ����NUM��ѯά�޼�¼
	 */
	public List<RepairRecord> RepairRecordByRepairnum(String num, int type);

	/**
	 * ���ݳ���ά�޵���
	 */
	public List<Repair> RepairByCarid(int carid);

	/**
	 * ����ά�޵�״̬��Ϣ
	 * 
	 * @param num
	 * @param state
	 */
	public void UpdateRepairState(String num, int state, int cashflow);
}
