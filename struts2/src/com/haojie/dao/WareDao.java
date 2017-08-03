package com.haojie.dao;

import java.io.Serializable;
import java.util.List;

import com.haojie.pojo.Department;
import com.haojie.pojo.ReturnedWare;
import com.haojie.pojo.WareHouse;
import com.haojie.pojo.WareRecord;
import com.haojie.pojo.Wares;
import com.haojie.utils.Page;

public interface WareDao {

	/**
	 * ��ѯ���вֿ�
	 * 
	 * @return
	 */
	public List<WareHouse> AllWareHouse();

	/**
	 * ��ѯ���в���
	 * 
	 * @return
	 */
	public List<Department> AllDepartment();

	/**
	 * ����˻���¼
	 * 
	 * @param rw
	 * @return
	 */
	public Serializable AddReturnedWareRecord(ReturnedWare rw);

	/**
	 * ����б�
	 * 
	 * @param page
	 * @return
	 */
	public List<Object> InventoryList(Page page, int value1);

	/**
	 * ����Id��ѯ�ֿ�
	 * 
	 * @param id
	 * @return
	 */
	public WareHouse WareHouseById(int id);

	/**
	 * ��������
	 * 
	 * @param wr
	 */
	public void UpdateWareRecordByOrderAndPro(WareRecord wr);

	/**
	 * ����µĲֿ�
	 */
	public void AddWareHouse(WareHouse wh);

	/**
	 * ����µ�bumen
	 */
	public void AddDepartment(Department d);

	/**
	 * ���²ֿ�����
	 */
	public void UpdateWareHouse(WareHouse wh);

	/**
	 * ����bumen����
	 */
	public void UpdateDepartment(Department b);

	/**
	 * ����Id��ѯ�ֿ�
	 */
	public WareHouse WareHouseByName(String name);

	/**
	 * ����Id��ѯbumen
	 */
	public Department DepartmentByName(String name);

	/**
	 * ����Ʒ���������Ʒ
	 * 
	 * @param w
	 */
	public Serializable AddWares(Wares w);

	/**
	 * ����Ʒ�� ��� ���� ���� ��ѯ
	 * 
	 * @param w
	 * @return
	 */
	public Wares SelectWares(Wares w);

	/**
	 * ���ݻ��Ų�ѯ
	 * 
	 * @param w
	 * @return
	 */
	public Wares SelectWaresByNumber(Wares w);

	/**
	 * ��������
	 * 
	 * @param w
	 * @return
	 */
	public List<Wares> FastSelectWares(String s, int suppliers);

	/**
	 * ����Id��ѯ��Ʒ
	 * 
	 * @param w
	 * @return
	 */
	public Wares SelectWaresById(Wares w);

	/**
	 * ���ݹ����̲�ѯ��Ʒ
	 * 
	 * @param w
	 * @return
	 */
	public List<Wares> SelectWaresBySuppliers(Wares w);

	/**
	 * �ֿ�����¼
	 */
	public Serializable AddWareRecord(WareRecord r);

	/**
	 * ��ѯ���һ�� BY ProductNumber
	 */
	public WareRecord SelectOneWareByTimeDesc(String number);

	/**
	 * ��ѯ�ڿ���Ʒ
	 */
	public List<Object[]> SelectWareListByWhere(int warehouse, String key);

	/**
	 * ��Ʒ����
	 */
	public void OutWareHouseByProductNumber(WareRecord w);

	/**
	 * ��ĳ��Ʒ�ڿ����м�¼
	 */
	public List<WareRecord> SelectWareListByNumber(String number);

	/**
	 * ��ĳ�ֿ��ڿ����м�¼
	 */
	public List<WareRecord> SelectWareListByWareHouse(int wh);

	/**
	 * ����ά�޵��Ų�ѯ�����¼
	 * 
	 * @param repairnum
	 * @return
	 */
	public List<WareRecord> OutWareHouseRecord(String repairnum);

	/**
	 *����ĳ��Ʒ���źͼ۸�������Ķ����� ��ʣ�µ�����
	 */
	// public List<WareRecord> OutWareListByPrice(String productnumber, int
	// price);

	/**
	 *��������¼
	 */
	public void OutWareRecord(WareRecord wr);

	/**
	 * ���¼�¼Ϊ��Ч
	 */
	public void setWrIsfalse(String ordernum, String productnum);

	/**
	 * ����ID��ֿ��¼
	 */
	public WareRecord WareRecordById(int id);

	/**
	 * ����ID���²ֿ��¼
	 */
	public void UpdateWareRecordById(WareRecord wr);

	/**
	 * ����IDɾ���ֿ��¼
	 */
	public void DeleteWareRecordById(int id);
	
	/**
	 * ����IDɾ����Ч�ֿ��¼
	 */
	public String DeleteWareRecord(int id);

	/**
	 * ���ݶ����Ż��Ų�ѯ��¼
	 * 
	 * @param number
	 * @param productnumber
	 * @return
	 */
	public WareRecord SelectWareRecordByNumberAndProcuct(String number,
			String productnumber, int dotype);

	/**
	 * �����¼
	 * 
	 * @return
	 */
	public List<WareRecord> OutWareListByDotype(Page page);

	/**
	 * ģ�����������б�
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object[]> FastOutWareList(String fast);

	/**
	 * 
	 * @param number
	 * @param productnumber
	 * @return
	 */
	public WareRecord SelectWareRecordIstureByNumberAndProcuct(String number,
			String productnumber);

	/**
	 * �˻�ģ����ѯ
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastReturned(String fast, String wh);

	/**
	 * ��ʱ����ѯ
	 * 
	 * @param fast1
	 * @param wh
	 * @return
	 */
	public List<Object> FastReturnedTime1(String fast1, String wh);

	/**
	 * ��ʱ��β�ѯ
	 * 
	 * @param fast1
	 * @param fast2
	 * @param wh
	 * @return
	 */
	public List<Object> FastReturnedTime1(String fast1, String fast2, String wh);

	/**
	 * �����������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastInventory(String fast, int value2);
}
