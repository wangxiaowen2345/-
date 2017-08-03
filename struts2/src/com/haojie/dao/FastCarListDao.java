package com.haojie.dao;

import java.io.Serializable;
import java.util.List;

import com.haojie.pojo.FastCarList;
import com.haojie.pojo.FastCarRecord;
import com.haojie.utils.Page;

public interface FastCarListDao {

	/**
	 * ��ѯ���й��п쳵
	 */
	public List<FastCarList> AllPublicFastCarListByTimeDesc(Page page);

	/**
	 * ��ѯ����˽�п쳵
	 */
	public List<FastCarList> AllPrivateFastCarListByTimeDesc(Page page);

	/**
	 * ���п쳵����
	 * 
	 * @return
	 */
	public int PublicFastCarCount();

	/**
	 * ˽�п쳵����
	 * 
	 * @return
	 */
	public int PrivateFastCarCount();

	/**
	 * ����¿쳵
	 * 
	 * @return
	 */
	public Serializable AddFastCarList(FastCarList fastcarlist);

	/**
	 * ���ݳ��Ų�쳵
	 * 
	 * @param num
	 * @return
	 */
	public FastCarList FastCarListByNum(String num);

	/**
	 * ����id�鳵��
	 * 
	 * @param id
	 * @return
	 */
	public FastCarList FastCarListById(int id);

	/**
	 * ���ӿ쳵������¼
	 * 
	 * @param fastcarrecord
	 * @return
	 */
	public Serializable AddFastCarRecord(FastCarRecord fastcarrecord);

	/**
	 * ���³�����Ϣ
	 * 
	 */
	public void UpdateFastCarList(FastCarList fastcar);

	/**
	 * ��������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastCarList(String fast);

	/**
	 * ���ݳ���id��ѯ�а�����Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public List<Object> ContractInfoByCarId(String id);

}
