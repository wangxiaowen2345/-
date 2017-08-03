package com.haojie.dao;

import java.util.Date;

import com.haojie.pojo.User;


public interface UserDao {
	/**
	 * ����¼�¼
	 * 
	 * @param user
	 * 
	 */
	public void AddUser(User user);

	/**
	 * ���ջ�Ա������Ҽ�¼
	 * 
	 * @param nsernum
	 * @return
	 */
	public User SelectUserByUsernum(String usernum);

	/**
	 * �����������Ҽ�¼
	 * 
	 * @param name
	 * @return
	 */
	public User SelectUserByName(String name);

	/**
	 * ģ����ѯ
	 * 
	 * @param para
	 * @return
	 */
	public User SelectUserByPara(String para);
	
	/**
	 * �������͵���ʱ��
	 */
	public void UpdateUserBalance(String num,double balance,Date expdate);

}