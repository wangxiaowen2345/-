package com.haojie.dao;

import java.util.Date;
import java.util.List;

import com.haojie.pojo.CarWash;
import com.haojie.pojo.CarWashRecord;
import com.haojie.utils.Page;

public interface CarWashDao {
	/**
	 * �����ϴ����
	 * 
	 * 
	 */
	public void AddCarWash(CarWash carwash);

	/**
	 * ��ѯ����ϴ����
	 */
	public List<CarWash> AllCarWareByTimeDesc(Page page);

	/**
	 * ���տ��Ų���ϴ����
	 * 
	 * @param nsernum
	 * @return
	 */
	public CarWash SelectCarWashByNumber(String number);

	/**
	 * ��������ϴ�����б�
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastCarWashList(String fast);

	/**
	 * ���տ��Ų���ϴ�������Ѽ�¼
	 * 
	 * @param nsernum
	 * @return
	 */
	public List<CarWashRecord> SelectCarWashRecordConsumerByNumber(String number);

	/**
	 * ���տ��Ų���ϴ������ֵ��¼
	 * 
	 * @param nsernum
	 * @return
	 */
	public List<CarWashRecord> SelectCarWashRecordByNumber(String number);

	// /**
	// * ģ����ѯ
	// *
	// * @param para
	// * @return
	// */
	// public CarWash SelectCarWashByPara(String para);

	/**
	 * ���µ���ʱ���ʣ�����
	 */
	public void UpdateCarWash(String num, Date expdate, int residuedegree);

	/**
	 * ���ݿ��Ų���
	 * 
	 * @param num
	 * @return
	 */
	public CarWash CarWashByNum(String num);

	/**
	 * ���´���
	 * 
	 * @param residuedegree
	 */
	public void UpdateCarWashResidueDegree(int residuedegree, String num);

}