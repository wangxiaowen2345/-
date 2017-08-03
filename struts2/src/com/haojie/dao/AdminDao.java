package com.haojie.dao;

import java.util.List;

import com.haojie.pojo.Admin;

public interface AdminDao {

	/**
	 * ��½��ѯ
	 * 
	 * @param aus
	 * @return
	 */
	public Admin Login(Admin aus);

	/**
	 * �û�����ѯ
	 * 
	 * @param aus
	 * @return
	 */
	public Admin SelectAdminUserByName(Admin aus);

	/**
	 * ��ѯ�����û�
	 * 
	 * @return
	 */
	public List<Admin> SelectAllAadminUser();

	/**
	 * ����Id��ѯ�û�
	 * 
	 * @return
	 */
	public Admin SelectAdminUserById(int id);

	/**
	 * ������������
	 * 
	 * @param id
	 * @return
	 */
	public int ResetPassword(Admin admin);

	/**
	 * Admin�û��޸�����
	 * 
	 * @param username
	 * @param newpassword
	 * @return
	 */
	public int AdminRePassWord(String username, String newpassword);

	/**
	 * �����û���Ϣ
	 * 
	 * @param admin
	 */
	public void UpdateAdmin(Admin admin);

	/**
	 * ������û�
	 * 
	 * @param admin
	 */
	public void AddAdmin(Admin admin);

	public Admin AdminUserByUsername(String username);

}
