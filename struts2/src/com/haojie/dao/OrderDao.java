package com.haojie.dao;

import java.io.Serializable;
import java.util.List;

import com.haojie.pojo.Order;
import com.haojie.pojo.OrderList;
import com.haojie.utils.Page;

public interface OrderDao {
	
	/**
	 * ����Id�鶩��
	 * @param order
	 * @return
	 */
	public Order OrderById(Order order);
	
	/**
	 * ���ж���
	 * @return
	 */
	public List<Order> AllOrder(Page page);
	
	/**
	 * ��������
	 * @return
	 */
	public int OrderCount();
	
	/**
	 * ���ݱ�Ų鶩��
	 * @param order
	 * @return
	 */
	public Order OrderByNumber(Order order);
	
	/**
	 * ����¶���
	 */
	public Serializable AddOrder(Order order);
	
	/**
	 * ��Ӷ����б�
	 */
	public Serializable AddOrderList(OrderList order);
	
	/**
	 * ���ݱ�Ų�ѯ�����б�
	 */
	public List<OrderList> OrderListByOrderNumber(String ordernumber);
	
	/**
	 * ��������¼
	 */
	public void EnterWareHouse(String number);
	
	/**
	 * ���ݲ�Ʒnumber��ѯ�����������һ����¼
	 */
	public OrderList SelectOrderListByIdDesc(String number);
}
