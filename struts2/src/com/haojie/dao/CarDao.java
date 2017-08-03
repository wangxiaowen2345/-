package com.haojie.dao;

import java.io.Serializable;
import java.util.List;

import com.haojie.pojo.Car;
import com.haojie.utils.Page;

public interface CarDao {

	/**
	 * ��ѯ���г���
	 */
	public List<Car> AllCarByTimeDesc(Page page);

	/**
	 * �����⳵������
	 * 
	 * @return
	 */
	public int CarCount();

	/**
	 * ����id��������
	 * 
	 * @param id
	 * @return
	 */
	public Car CarById(int id);

	/**
	 * ���ݳ�����������
	 * 
	 * @param num
	 * @return
	 */
	public Car CarByNum(String num);

	/**
	 * ��ʱ�����
	 */
	public List<Object> FastTime1(String fast1);

	/**
	 * ��ʱ�����
	 */
	public List<Object> FastTime(String fast1, String fast2);

	/**
	 * ����³���
	 * 
	 * @param car
	 * @return
	 */
	public Serializable AddCar(Car car);

	/**
	 * ���³�����Ϣ
	 * 
	 * @param car
	 */
	public void UpdateCar(Car car);

	/**
	 * ������������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastCar(String fast);
}
