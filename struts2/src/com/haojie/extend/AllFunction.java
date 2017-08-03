package com.haojie.extend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.SessionAware;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.haojie.impl.AdminImpl;
import com.haojie.impl.BuytypeImpl;
import com.haojie.impl.CarImpl;
import com.haojie.impl.CarWashImpl;
import com.haojie.impl.CashflowImpl;
import com.haojie.impl.FastCarListImpl;
import com.haojie.impl.FunctionImpl;
import com.haojie.impl.ModelsImpl;
import com.haojie.impl.OrderImpl;
import com.haojie.impl.PicImpl;
import com.haojie.impl.PowerImpl;
import com.haojie.impl.PurchaseImpl;
import com.haojie.impl.RepairImpl;
import com.haojie.impl.SuppliersImpl;
import com.haojie.impl.SystemValueImpl;
import com.haojie.impl.UserImpl;
import com.haojie.impl.WareImpl;
import com.haojie.pojo.Admin;
import com.haojie.pojo.AdminNav;
import com.haojie.pojo.Allot;
import com.haojie.pojo.Buytype;
import com.haojie.pojo.Car;
import com.haojie.pojo.CarWash;
import com.haojie.pojo.CarWashRecord;
import com.haojie.pojo.Cashflow;
import com.haojie.pojo.DatePojo;
import com.haojie.pojo.Department;
import com.haojie.pojo.FastCarList;
import com.haojie.pojo.FastCarRecord;
import com.haojie.pojo.Function;
import com.haojie.pojo.Models;
import com.haojie.pojo.Order;
import com.haojie.pojo.OrderList;
import com.haojie.pojo.OutWare;
import com.haojie.pojo.Power;
import com.haojie.pojo.PowerAss;
import com.haojie.pojo.PowerFuntion;
import com.haojie.pojo.Purchase;
import com.haojie.pojo.Repair;
import com.haojie.pojo.RepairRecord;
import com.haojie.pojo.ReturnedWare;
import com.haojie.pojo.Suppliers;
import com.haojie.pojo.SystemValue;
import com.haojie.pojo.User;
import com.haojie.pojo.WareHouse;
import com.haojie.pojo.WareRecord;
import com.haojie.pojo.Wares;
import com.haojie.utils.HttpRequest;
import com.haojie.utils.MD5Utils;
import com.haojie.utils.NumberToCN;
import com.haojie.utils.PageUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AllFunction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8201482453588372623L;

	private static SystemValue sv = null;

	private static String Success = "Success";
	private static String Fail = "Fail";

	public void setSystemValue(SystemValue sv) {
		this.sv = sv;
	}

	/**
	 * ͨ��KeyName��ѯϵͳ����Model
	 * 
	 * @param ValueName
	 */
	private void getSystemValueByValueName(String KeyName) {
		SystemValueImpl svi = new SystemValueImpl();
		SystemValue sv = new SystemValue();
		sv.setKeyname(KeyName);
		setSystemValue(svi.SelectValue(sv));
	}

	/**
	 * ͨ��KeyName��ѯϵͳ����ֵ
	 * 
	 * @param KeyName
	 * @return
	 */
	public String getSystemValue(String KeyName) {
		if (sv == null || !sv.getKeyname().equals(KeyName))
			getSystemValueByValueName(KeyName);
		return sv.getVle();
	}

	/**
	 * ͨ��KeyName��ѯϵͳ�������
	 * 
	 * @param KeyName
	 * @return
	 */
	public String getSystemRemark(String KeyName) {
		if (sv == null || !sv.getKeyname().equals(KeyName))
			getSystemValueByValueName(KeyName);
		return sv.getRemark();
	}

	/**
	 * ��ѯAdminϵͳ�˵�
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getMenu() {
		long startTime = System.currentTimeMillis();
		List<AdminNav> list = new ArrayList<AdminNav>();
		AdminImpl ai = new AdminImpl();

		list = ai.getAdminNav("");

		float seconds = (System.currentTimeMillis() - startTime) / 1000F;
		System.out.println("��ѯ����˵���ʱ��" + Float.toString(seconds) + " ��");

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		for (AdminNav an : list) {
			long sonstartTime = System.currentTimeMillis();
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", an.getId());
			map.put("navname", an.getNavname());
			map.put("colid", an.getColid());
			map.put("vle", an.getVle());
			map.put("function", an.getFunction());
			map.put("power", an.getPower());
			map.put("son", ai.getAdminNav(an.getId() + ""));

			result.add(map);
			seconds = (System.currentTimeMillis() - sonstartTime) / 1000F;
			System.out.println(an.getNavname() + "		�˵���ʱ��"
					+ Float.toString(seconds) + " ��");
		}

		seconds = (System.currentTimeMillis() - startTime) / 1000F;
		System.out.println("��ѯ��̨�˵��ܺ�ʱ��" + Float.toString(seconds) + " ��");

		return result;

	}

	/**
	 * ��ѯ����Admin�û�
	 * 
	 * @return
	 */
	public List<Admin> SelectAllAdminUser() {
		long startTime = System.currentTimeMillis();
		List<Admin> list = new AdminImpl().SelectAllAadminUser();
		float seconds = (System.currentTimeMillis() - startTime) / 1000F;
		System.out.println("��ѯAdmin�û��б��ܺ�ʱ��" + Float.toString(seconds) + " ��");
		return list;
	}

	/**
	 * ����Ȩ����Id��Ȩ��������
	 * 
	 * @param id
	 * @return
	 */
	public String getPowerNameById(String id) {
		long startTime = System.currentTimeMillis();
		String result = new PowerImpl().getPowerNameById(id).getPower();
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯID = " + id + "��Ȩ���û���ʱ��"
				+ Float.toString(seconds) + " ��");
		return result;
	}

	/**
	 * ��ѯ����Ȩ����
	 * 
	 * @return
	 */
	public List<Power> getAllPower() {

		long startTime = System.currentTimeMillis();
		List<Power> result = new PowerImpl().getAllPower();
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ����Ȩ�����ʱ��" + Float.toString(seconds) + " ��");
		return result;

	}

	/**
	 * ����admin��Id��ѯ�û�
	 * 
	 * @param id
	 * @return
	 */
	public Admin SelectAdminUserById(int id) {
		long startTime = System.currentTimeMillis();
		Admin aus = new AdminImpl().SelectAdminUserById(id);
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯIDΪ" + id + "��Admin�û���ʱ��"
				+ Float.toString(seconds) + " ��");
		return aus;
	}

	/**
	 * ����������� ���ŷ���
	 * 
	 * @param id
	 */
	public int smsramdomresetpassword(int id) {
		long startTime = System.currentTimeMillis();
		String pw = getRom();
		AdminImpl ai = new AdminImpl();
		Admin aus = ai.SelectAdminUserById(id);
		aus.setPassword(MD5Utils.MD5(pw));
		int rs = ai.ResetPassword(aus);
		if (rs == 1)
			SendSMS(aus, pw);
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("������������ܺ�ʱ��" + Float.toString(seconds) + " ��");
		return rs;

	}

	/**
	 * ���ŷ���
	 */
	public void SendSMS(Admin admin, String pw) {
		long startTime = System.currentTimeMillis();
		String info = "Hello " + admin.getUsername()
				+ ",your password reset success,your password is " + pw;

		try {

			// ���� POST ����
			String sr = HttpRequest.sendPost(getSystemValue("smsaddress"),
					"user=" + getSystemValue("smsusername") + "&pwd="
							+ getSystemValue("smspassword") + "&contents="
							+ info + "&mobiles=" + admin.getTel()
							+ "&chid=0&sendtime=");
			SAXReader sax = new SAXReader();

			Document document = DocumentHelper.parseText(sr);

			Element root = document.getRootElement();// ���ڵ�
			Element son = root.element("Result");
			System.out.println(son);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��������������ź�ʱ��" + Float.toString(seconds) + " ��");
	}

	/**
	 * Admin ��½
	 * 
	 * @param aus
	 * @return
	 */
	public Admin Login(Admin aus) {
		long startTime = System.currentTimeMillis();
		AdminImpl ai = new AdminImpl();
		aus.setPassword(MD5Utils.MD5(aus.getPassword()));
		Admin rs = ai.Login(aus);
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("Admin�û���¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;

	}

	/**
	 * ajax�������뷽ʽ ����json���ݷ���
	 * 
	 * @param session
	 * @param password
	 * @param newpassword
	 * @return
	 */
	public String AdminRePassWord(Map session, String password,
			String newpassword) {
		long startTime = System.currentTimeMillis();
		Admin aus = (Admin) session.get("adminuser");
		aus.setPassword(password);
		if (Login(aus) == null) {
			return "Password error";
		}
		AdminImpl ai = new AdminImpl();
		int rsi = ai.AdminRePassWord(aus.getUsername(), MD5Utils
				.MD5(newpassword));
		String rs = "";
		if (rsi == 1)
			rs = Success;
		else
			rs = Fail;
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("Admin�޸������ܺ�ʱ��" + Float.toString(seconds) + " ��");
		return rs;

	}

	/**
	 * ��ȡ���й�Ӧ���б�
	 * 
	 * @return
	 */
	public List<Suppliers> AllSupplies() {
		long startTime = System.currentTimeMillis();

		SuppliersImpl si = new SuppliersImpl();
		List<Suppliers> rs = si.SelectAllSupplies();

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ���й�Ӧ���б��ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * HTML Alert��ʾ
	 * 
	 * @param msg
	 * @return
	 */
	public String Alert(String msg) {
		return "alert(\"" + msg + "\");";
	}

	/**
	 * �������ֲ�ѯ��Ӧ��
	 * 
	 * @param name
	 * @return
	 */
	public Suppliers SelectSuppliersByName(String name) {

		long startTime = System.currentTimeMillis();

		SuppliersImpl si = new SuppliersImpl();
		Suppliers rs = si.SelectSuppliersByName(name);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯName = " + name + " �Ĺ�Ӧ���ܺ�ʱ��"
				+ Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ����µĹ�Ӧ��
	 * 
	 * @param s
	 */
	public void AddSuppliers(Suppliers s) {
		long startTime = System.currentTimeMillis();

		SuppliersImpl si = new SuppliersImpl();
		si.InsertSuppliers(s);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���빩Ӧ��  " + s.getName() + "  �ܺ�ʱ��"
				+ Float.toString(seconds) + " ��");

	}

	/**
	 * ��ѯ����Function
	 * 
	 * @return
	 */
	public List<Function> MenuFunction() {

		long startTime = System.currentTimeMillis();

		FunctionImpl fi = new FunctionImpl();
		List<Function> rs = fi.MenuFunction();

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯAllFunction��ʱ��" + Float.toString(seconds) + " ��");
		return rs;

	}

	/**
	 * Ȩ�޲�ѯ
	 * 
	 * @param FunctionName
	 * @return
	 */
	public boolean Power(int AdminUserId, String FunctionName) {
		long startTime = System.currentTimeMillis();

		boolean rs = false;
		PowerImpl pi = new PowerImpl();
		FunctionImpl fi = new FunctionImpl();
		int Fid = fi.SelectFunctionFid(FunctionName);
		if (Fid == -1)
			rs = true;
		if (pi.Power(SelectAdminUserById(AdminUserId).getPower(), Fid))
			rs = true;

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯȨ�޺�ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ȡPower��Function�б�
	 * 
	 * @param id
	 * @return
	 */
	public List<PowerFuntion> PowerFunctionByPowerId(int id) {

		long startTime = System.currentTimeMillis();
		PowerImpl pi = new PowerImpl();
		List<PowerFuntion> rs = pi.PowerFunctionByPowerId(id);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯPowerFunction��ʱ��" + Float.toString(seconds)
				+ " ��");
		return rs;
	}

	/**
	 * ���Ȩ��
	 * 
	 * @param powername
	 * @param powerass
	 * @return
	 */
	public String AddPower(String powername, String powerass) {
		long startTime = System.currentTimeMillis();
		PowerImpl pi = new PowerImpl();
		String rs = "";
		if (pi.getPowerByName(powername) != null || powername.equals(""))
			rs = "Power existence";
		else {
			Power p = new Power();
			p.setPower(powername);
			pi.SavePower(p);
			p = pi.getPowerByName(powername);
			int s = 0;
			for (int i = 0; i < powerass.length(); i++) {
				if (powerass.charAt(i) == ',') {
					PowerAss pa = new PowerAss();
					pa
							.setFunctionid(Integer.parseInt(powerass.substring(
									s, i)));
					pa.setPowerid(p.getId());
					pi.SavaPowerAss(pa);
					s = i + 1;
				}
			}

			rs = Success;

		}
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("AddFunction��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����Ȩ����Ϣ
	 * 
	 * @param id
	 * @param power
	 * @param powerass
	 * @return
	 */
	public String UpdatePower(String id, String power, String powerass) {
		long startTime = System.currentTimeMillis();
		PowerImpl pi = new PowerImpl();
		String rs = "";
		if (id.equals(""))
			rs = "Powerid error";
		else {
			Power p = new Power();
			p.setId(Integer.parseInt(id));
			p.setPower(power);
			pi.UpdatePowerById(p);
			int s = 0;
			pi.DeletePowerFunctionById(p.getId());
			for (int i = 0; i < powerass.length(); i++) {
				if (powerass.charAt(i) == ',') {
					PowerAss pa = new PowerAss();
					pa
							.setFunctionid(Integer.parseInt(powerass.substring(
									s, i)));
					pa.setPowerid(p.getId());
					pi.SavaPowerAss(pa);
					s = i + 1;
				}
			}

			rs = Success;

		}
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out
				.println("UpdateFunction��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����Admin��Ϣ
	 * 
	 * @param admin
	 * @return
	 */
	public String UpdateAdmin(Admin admin) {
		long startTime = System.currentTimeMillis();

		if (admin == null || admin.getTel().equals("")
				|| admin.getName().equals("") || admin.getUsername().equals(""))
			return "request error";

		AdminImpl ai = new AdminImpl();
		ai.UpdateAdmin(admin);
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����admin�û���Ϣ��ʱ��" + Float.toString(seconds) + " ��");
		return Success;

	}

	/**
	 * �������Admin�û�
	 * 
	 * @param admin
	 * @return
	 */
	public String AddAadmin(Admin admin) {
		long startTime = System.currentTimeMillis();

		if (admin == null || admin.getTel().equals("")
				|| admin.getName().equals("") || admin.getUsername().equals(""))
			return "request error";

		AdminImpl ai = new AdminImpl();
		if (ai.SelectAdminUserByName(admin) != null)
			return "Admin existence";
		ai.AddAdmin(admin);
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���admin�û���Ϣ��ʱ��" + Float.toString(seconds) + " ��");
		return Success;
	}

	/**
	 * ��ȡ���вֿ�
	 * 
	 * @return
	 */
	public List<WareHouse> AllWareHouse() {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		List<WareHouse> rs = wi.AllWareHouse();

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ȡ���вֿ���Ϣ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ȡ���в���
	 * 
	 * @return
	 */
	public List<Department> AllDepartment() {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		List<Department> rs = wi.AllDepartment();

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ȡ���в�����Ϣ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����²ֿ�
	 * 
	 * @param name
	 * @return
	 */
	public String AddWareHouse(String name) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		String rs = "";
		if (name == null || name.equals("")) {
			rs = "request error";
		} else if (wi.WareHouseByName(name) != null) {
			rs = "Name existence";
		} else {
			WareHouse wh = new WareHouse();
			wh.setName(name);
			wi.AddWareHouse(wh);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����²ֿ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public String AddDepartment(String name) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		String rs = "";
		if (name == null || name.equals("")) {
			rs = "request error";
		} else if (wi.DepartmentByName(name) != null) {
			rs = "Name existence";
		} else {
			Department wh = new Department();
			wh.setDepartmentname(name);
			wi.AddDepartment(wh);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����²��ź�ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ���²ֿ�
	 * 
	 * @param name
	 * @return
	 */
	public String UpdateWareHouse(String name, int Id) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		String rs = "";
		if (name == null || name.equals("")) {
			rs = "request error";
		} else if (wi.WareHouseByName(name) != null) {
			rs = "Name existence";
		} else {
			WareHouse wh = new WareHouse();
			wh.setName(name);
			wh.setId(Id);
			wi.UpdateWareHouse(wh);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���²ֿ���Ϣ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ���²���
	 * 
	 * @param name
	 * @return
	 */
	public String UpdateDepartment(String name, int Id) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		String rs = "";
		if (name == null || name.equals("")) {
			rs = "request error";
		} else if (wi.DepartmentByName(name) != null) {
			rs = "Name existence";
		} else {
			Department wh = new Department();
			wh.setDepartmentname(name);
			wh.setId(Id);
			wi.UpdateDepartment(wh);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���²�����Ϣ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ������ĳ���
	 * 
	 * @param
	 * @return
	 */
	public String Revoke(int Id) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		String rs = "";
		WareRecord wr = wi.SelectWareListById(Id);
		if (wr.getIstrue() == 0) {
			// ��ЧֵΪ0
			// �ҵ��˶�����ЧֵΪ1�ģ����ϳ���������
			WareRecord wr1 = wi.SelectWareRecordIstureByNumberAndProcuct(wr
					.getOrdernumber(), wr.getProductnumber());
			wr1.setCount(wr1.getCount() + wr.getDocount());
			wr1.setIstrue(1);
			wi.UpdateWareRecordAll(wr1);
		} else if (wr.getIstrue() == 1) {
			// ��ЧֵΪ1
			// ����fid�ҵ���Ч��������������
			WareRecord wr2 = wi.SelectWareListById(wr.getFid());
			wr2.setCount(wr2.getCount() + wr.getDocount());
			wr2.setIstrue(1);
			System.out.println(wr2.getIstrue());// 1
			System.out.println(wr2.getCount());// 8
			wi.UpdateWareRecordAll(wr2);
		} else
			rs = "Error";

		wi.DeleteWareRecordById(Id);
		// ɾ����id
		rs = Success;

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("������ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �����б�
	 */
	public String BeRevokeOutWare(String ordernum, String productnum) {

		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		WareRecord rs = wi.SelectWareRecordByNumberAndProcuct(ordernum,
				productnum, 3);
		wi.UpdateWareRecordDotypeById(rs.getId(), 1);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��˳����ʱ��" + Float.toString(seconds) + " ��");
		return Success;
	}

	@Test
	public void a1() {
		System.out.println(BeRevokeOutWare("518414", "373781"));
	}

	/**
	 * �����б�
	 */
	public List<Order> OrderList(String page) {

		long startTime = System.currentTimeMillis();

		OrderImpl oi = new OrderImpl();
		List<Order> rs = oi.AllOrder(PageUtil.createPage(10000, oi
				.OrderCount(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�����б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����ID��ѯ��Ӧ��
	 * 
	 * @param id
	 * @return
	 */
	public String SuppliersById(String id) {
		long startTime = System.currentTimeMillis();

		SuppliersImpl si = new SuppliersImpl();
		Suppliers rs = si.SelectSuppliersById(id);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯid = " + id + " �Ĺ�Ӧ���ܺ�ʱ��"
				+ Float.toString(seconds) + " ��");

		return rs.getName();
	}

	/**
	 * ����ID��ѯ��Ӧ��
	 * 
	 * @param id
	 * @return
	 */
	public Suppliers SuppliersAllById(String id) {
		long startTime = System.currentTimeMillis();

		SuppliersImpl si = new SuppliersImpl();
		Suppliers rs = si.SelectSuppliersById(id);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯid = " + id + " �Ĺ�Ӧ���ܺ�ʱ��"
				+ Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ��ȡ�������
	 * 
	 * @return
	 */
	public String GetOrderNumber() {
		long startTime = System.currentTimeMillis();
		String rs = getRom();

		OrderImpl oi = new OrderImpl();
		Order order = new Order();
		order.setNumber(rs);

		while (oi.OrderById(order) != null) {
			rs = getRom();
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����������ź�ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public List<Buytype> AllBuytype() {
		long startTime = System.currentTimeMillis();

		BuytypeImpl wi = new BuytypeImpl();
		List<Buytype> rs = wi.AllBuytype();

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ȡ���й�����ʽ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ȡ���г���
	 * 
	 * @return
	 */
	public List<Models> AllModels() {
		long startTime = System.currentTimeMillis();

		ModelsImpl wi = new ModelsImpl();
		List<Models> rs = wi.AllModels();

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ȡ���г�����Ϣ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����Id��ȡ
	 * 
	 * @return
	 */
	public Buytype BuytypeById(int id) {
		long startTime = System.currentTimeMillis();

		BuytypeImpl wi = new BuytypeImpl();
		Buytype rs = wi.BuytypeById(id);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ȡid = " + id + " ������ʽ��ʱ��"
				+ Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����Id��ȡ����
	 * 
	 * @return
	 */
	public Models ModelsById(int id) {
		long startTime = System.currentTimeMillis();

		ModelsImpl wi = new ModelsImpl();
		Models rs = wi.ModelsById(id);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ȡid = " + id + " ������Ϣ��ʱ��"
				+ Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����
	 * 
	 * @param name
	 * @param Id
	 * @return
	 */
	public String UpdateBuytype(String type, String Id) {
		long startTime = System.currentTimeMillis();
		BuytypeImpl wi = new BuytypeImpl();
		String rs = "";
		if (type == null || type.equals("")) {
			rs = "request error";
		} else if (wi.BuytypeBytype(type) != null) {
			rs = "Name existence";
		} else {
			Buytype wh = new Buytype();
			wh.setType(type);
			wh.setId(Integer.parseInt(Id));
			wi.UpdateBuytype(wh);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���¹�����ʽ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����
	 * 
	 * @param name
	 * @param Id
	 * @return
	 */
	public String UpdateUserIdIsture(int Id) {
		long startTime = System.currentTimeMillis();
		UserImpl wi = new UserImpl();
		String rs = "";
		wi.UpdateIsture(Id);
		rs = Success;

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��Ա����ʧ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����
	 * 
	 * @param Id
	 * @return
	 */
	public String UpdateCarWashIstrue(int Id) {
		long startTime = System.currentTimeMillis();
		CarWashImpl wi = new CarWashImpl();
		String rs = "";
		wi.UpdateIsture(Id);
		rs = Success;

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("ϴ������ʧ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ���³���
	 * 
	 * @param name
	 * @param Id
	 * @return
	 */
	public String UpdateModels(String name, int Id) {
		long startTime = System.currentTimeMillis();

		ModelsImpl wi = new ModelsImpl();
		String rs = "";
		if (name == null || name.equals("")) {
			rs = "request error";
		} else if (wi.ModelsByName(name) != null) {
			rs = "Name existence";
		} else {
			Models wh = new Models();
			wh.setName(name);
			wh.setId(Id);
			wi.UpdateModels(wh);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���³�����Ϣ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����³���
	 * 
	 * @param name
	 * @return
	 */
	public String AddModels(String name) {
		long startTime = System.currentTimeMillis();

		ModelsImpl wi = new ModelsImpl();
		String rs = "";
		if (name == null || name.equals("")) {
			rs = "request error";
		} else if (wi.ModelsByName(name) != null) {
			rs = "Name existence";
		} else {
			Models wh = new Models();
			wh.setName(name);
			wi.AddModels(wh);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����³��ͺ�ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ӹ�����ʽ
	 * 
	 * @param name
	 * @return
	 */
	public String AddBuytype(String type) {
		long startTime = System.currentTimeMillis();

		BuytypeImpl wi = new BuytypeImpl();
		String rs = "";
		if (type == null || type.equals("")) {
			rs = "request error";
		} else if (wi.BuytypeBytype(type) != null) {
			rs = "Name existence";
		} else {
			Buytype wh = new Buytype();
			wh.setType(type);
			wi.AddBuytype(wh);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ӹ���ʽ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ���ݹ�Ӧ�̲�ѯ��Ʒ
	 * 
	 * @param v
	 * @return
	 */
	public List<Wares> SelectWaresBySuppliers(String v) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		Wares w = new Wares();
		w.setSuppliers(Integer.parseInt(v));
		List<Wares> rs = wi.SelectWaresBySuppliers(w);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ��Ӧ����Ʒ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �����Ʒ
	 * 
	 * @param w
	 * @return
	 */
	public Object AddWares(Wares w) {
		long startTime = System.currentTimeMillis();
		WareImpl wi = new WareImpl();
		Object rs = wi.SelectWares(w);

		if (rs == null) {
			w.setNumber(getRom());
			while (wi.SelectWaresByNumber(w) != null) {
				w.setNumber(getRom());
			}
			int id = Integer.parseInt(wi.AddWares(w).toString());
			w.setId(id);
			rs = wi.SelectWaresById(w);
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�����Ʒ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ȡ6λ���
	 * 
	 * @return
	 */
	private String getRom() {
		return (int) ((Math.random() * 9 + 1) * 100000) + "";
	}

	/**
	 * ���ٲ�ѯ��Ʒ
	 * 
	 * @param s
	 * @param sup
	 * @return
	 */
	public List<Wares> FastWares(String s, String sup) {
		long startTime = System.currentTimeMillis();
		WareImpl wi = new WareImpl();
		List<Wares> rs = null;

		rs = wi.FastSelectWares(s, Integer.parseInt(sup));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ٲ�ѯ��Ӧ����Ʒ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��Ӷ���
	 * 
	 * @param json
	 * @param spid
	 * @param ordernumber
	 * @return
	 */
	public String AddOrder(String json, String spid, String ordernumber) {
		long startTime = System.currentTimeMillis();

		JSONArray js = JSONArray.fromObject(json);
		System.out.println(js);
		System.out.println(json);
		OrderImpl oi = new OrderImpl();

		double all = 0.00;
		// �����ܼ�
		for (int i = 0; i < js.size(); i++) {
			JSONArray son = JSONArray.fromObject(js.get(i));
			double count = Double.parseDouble(son.get(3).toString());
			double p = Double.parseDouble(son.get(4).toString());
			all += p * count;
		}

		Order order = new Order();
		order.setNumber(ordernumber);
		order.setSuppliers(Integer.parseInt(spid));
		order.setAlltotalprice(all);
		order.setTime(new Date());
		if (oi.OrderByNumber(order) != null)
			return "existence";
		else
			oi.AddOrder(order);

		for (int i = 0; i < js.size(); i++) {
			JSONArray son = JSONArray.fromObject(js.get(i).toString());
			double count = Double.parseDouble(son.get(3).toString());
			double p = Double.parseDouble(son.get(4).toString());

			com.haojie.pojo.OrderList ol = new com.haojie.pojo.OrderList();
			ol.setCount(count);
			ol.setPrice(p);
			ol.setNumber(ordernumber);
			ol.setProductnumber(son.get(0).toString());

			oi.AddOrderList(ol);
		}
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��Ӷ�����ʱ��" + Float.toString(seconds) + " ��");
		return Success;
	}

	/**
	 * ����ordernumber�鶩������
	 * 
	 * @param s
	 * @return
	 */
	public List<OrderList> OrderListByNumber(String s) {
		long startTime = System.currentTimeMillis();
		List<OrderList> rs;
		OrderImpl oi = new OrderImpl();
		rs = oi.OrderListByOrderNumber(s);
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ���������б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����ordernumber�鶩������
	 * 
	 * @param s
	 * @return
	 */
	public Order OrderByNumber(String s) {
		long startTime = System.currentTimeMillis();
		Order rs;
		OrderImpl oi = new OrderImpl();
		Order o = new Order();
		o.setNumber(s);
		rs = oi.OrderByNumber(o);
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ���������ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����NUMBER��ѯ��Ʒ����
	 * 
	 * @param number
	 * @return
	 */
	public Wares WaresByNumber(String number) {
		long startTime = System.currentTimeMillis();
		Wares rs;
		WareImpl oi = new WareImpl();
		Wares o = new Wares();
		o.setNumber(number);
		rs = oi.SelectWaresByNumber(o);
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ��Ʒ�����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ���
	 */
	public String EnterWareHouse(String ordernumber, String user,
			List<WareRecord> list) {
		long startTime = System.currentTimeMillis();
		OrderImpl oi = new OrderImpl();
		Order o = new Order();
		o.setNumber(ordernumber);
		String rs = "";
		if (oi.OrderByNumber(o).getTag() == 1) {
			rs = "�ö��������,���β���ʧ��";
		} else {
			oi.EnterWareHouse(ordernumber);

			for (int i = 0; i < list.size(); i++) {
				String number = list.get(i).getProductnumber();
				List<OrderList> l = oi.OrderListByOrderNumber(ordernumber);

				for (int j = 0; j < l.size(); j++) {

					if (l.get(j).getProductnumber().equals(number)) {
						// ��Ҫ���� û�м���֮ǰ�Ŀ������ ��ʱ����
						WareRecord r = new WareRecord();
						r.setCount(l.get(j).getCount());
						r.setDocount(l.get(j).getCount());
						r.setDotype(0);
						r.setIstrue(1);
						r.setOrdernumber(ordernumber);
						r.setDouser(user);
						// r.setDepartment(list.get(i).getDepartment());
						r.setOutprice(list.get(i).getOutprice());
						r.setProductnumber(number);
						r.setWarehouse(list.get(i).getWarehouse());
						r.setTime(new Date());
						new WareImpl().AddWareRecord(r);
					}
				}
			}

			rs = "���ɹ�";

		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��Ʒ����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ���ݲ�ƷNumber��ѯ���һ���������¼
	 * 
	 * @param productnumber
	 * @return
	 */
	public WareRecord SelectOneWareByNumberOrderByTimeDesc(String productnumber) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		WareRecord rs = wi.SelectOneWareByTimeDesc(productnumber);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����ʱ��ͻ��Ų�ѯ���һ����Ʒ��¼��ʱ��" + Float.toString(seconds)
				+ " ��");
		return rs;
	}

	public WareRecord SelectWareByOrderPro(String ordernumber,
			String productnumber) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		WareRecord rs = wi.SelectWareRecordIstureByNumberAndProcuct(
				ordernumber, productnumber);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����ʱ��ͻ��Ų�ѯ���һ����Ʒ��¼��ʱ��" + Float.toString(seconds)
				+ " ��");
		return rs;
	}

	/**
	 * �����б�
	 * 
	 * @param productnumber
	 * @return
	 */
	public List<WareRecord> OutWareListByDotype(String page) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		List<WareRecord> rs = wi.OutWareListByDotype(PageUtil.createPage(10000,
				wi.OutWareListByDotypeCount(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�����б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �����б�byid
	 * 
	 * @param value1
	 * @return
	 */
	public WareRecord OutWareListByDotype1(String value1) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		WareRecord rs = wi.OutWareListByDotype(Integer.parseInt(value1));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�����б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �����б�byarray
	 * 
	 * @param value1
	 * @return
	 */
	public List<WareRecord> OutWareListByDotype2(String value2) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		String[] a = value2.split(",");
		List<WareRecord> rs = new ArrayList<WareRecord>();
		for (int i = 0; i < a.length; i++) {
			rs.add(wi.OutWareListByDotype(Integer.parseInt(a[i])));
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�����б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * Ա������
	 * 
	 * @param productnumber
	 * @return
	 */
	public List<WareRecord> EmployeeOutByDotype(String page) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		List<WareRecord> rs = wi.EmployeeOutByDotype(PageUtil.createPage(10000,
				wi.EmployeeOutByDotypeCount(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯԱ�������б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ���ݲ�ƷNumber��ѯ���һ��������¼
	 * 
	 * @param productnumber
	 * @return
	 */
	public OrderList SelectOrderListByNumberOrderByTimeDesc(String productnumber) {
		long startTime = System.currentTimeMillis();

		OrderImpl wi = new OrderImpl();
		OrderList rs = wi.SelectOrderListByIdDesc(productnumber);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ݲ�ƷNumber��ѯ���һ��������¼��ʱ��" + Float.toString(seconds)
				+ " ��");
		return rs;
	}

	/**
	 * ͳ�ƻ�����
	 * 
	 * @param warehouse
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> SelectWareListByWhere(String warehouse, String key) {
		long startTime = System.currentTimeMillis();

		if (warehouse == null || warehouse.equals(""))
			warehouse = "0";
		if (key == null)
			key = "";

		WareImpl wi = new WareImpl();
		List<Object[]> rs = wi.SelectWareListByWhere(Integer
				.parseInt(warehouse), key);
		List<Object[]> list = new ArrayList();

		// ͳ������
		for (int i = 0; i < rs.size(); i++) {
			double count = Double.parseDouble(rs.get(i)[1].toString());
			for (int j = i + 1; j < rs.size(); j++) {

				if (rs.get(j)[3].toString().equals(rs.get(i)[3].toString())) {
					count += Double.parseDouble(rs.get(j)[1].toString());
					rs.remove(j);
				}
			}
			Object[] temp = { rs.get(i)[0], count, rs.get(i)[2], rs.get(i)[3],
					rs.get(i)[4], rs.get(i)[5], rs.get(i)[11] };
			list.add(temp);
		}

		rs = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			double count = Double.parseDouble(list.get(i)[1].toString());
			for (int j = i + 1; j < list.size(); j++) {

				if (list.get(j)[3].toString().equals(list.get(i)[3].toString())) {
					count += Double.parseDouble(list.get(j)[1].toString());
					list.remove(j);
				}
			}
			Object[] temp = { list.get(i)[0], count, list.get(i)[2],
					list.get(i)[3], list.get(i)[4], list.get(i)[5],
					list.get(i)[6] };
			rs.add(temp);
		}

		// ѭ��ȥ��
		list = new ArrayList<Object[]>();
		for (int i = 0; i < rs.size(); i++) {
			double count = Double.parseDouble(rs.get(i)[1].toString());
			for (int j = i + 1; j < rs.size(); j++) {

				if (rs.get(j)[3].toString().equals(rs.get(i)[3].toString())) {
					count += Double.parseDouble(rs.get(j)[1].toString());
					rs.remove(j);
				}
			}
			Object[] temp = { rs.get(i)[0], count, rs.get(i)[2], rs.get(i)[3],
					rs.get(i)[4], rs.get(i)[5], rs.get(i)[6] };
			list.add(temp);
		}
		rs = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			double count = Double.parseDouble(list.get(i)[1].toString());
			for (int j = i + 1; j < list.size(); j++) {

				if (list.get(j)[3].toString().equals(list.get(i)[3].toString())) {
					count += Double.parseDouble(list.get(j)[1].toString());
					list.remove(j);
				}
			}
			Object[] temp = { list.get(i)[0], count, list.get(i)[2],
					list.get(i)[3], list.get(i)[4], list.get(i)[5],
					list.get(i)[6] };
			rs.add(temp);
		}
		// ѭ��ȥ�ؽ���

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�ڿ��¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ѯ�ڿ��¼ ���Ǹ��������
	 * 
	 * @param number
	 * @return
	 */
	public List<WareRecord> GetOneWareRecordList(String number) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		List<WareRecord> list = wi.SelectWareListByNumber(number);

		List<WareRecord> temp = new ArrayList<WareRecord>();

		// �ϲ��۸���ͬ�Ĳ�Ʒ�������ۼ�
		for (int i = 0; i < list.size(); i++) {
			boolean tag = true;// ��Ǳ���
			int j = 0;
			for (j = 0; j < temp.size(); j++) {
				if (list.get(i).getOutprice() == temp.get(j).getOutprice()
						&& list.get(i).getWarehouse() == temp.get(j)
								.getWarehouse()) {
					tag = false;
					double c = temp.get(j).getCount() + list.get(i).getCount();
					WareRecord s = temp.get(j);
					s.setCount(c);

					temp.remove(j);
					temp.add(s);

					break;
				}
			}

			if (tag) {
				temp.add(list.get(i));
			}

		}
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�ڿ��¼��ʱ��" + Float.toString(seconds) + " ��");
		return temp;
	}

	/**
	 * ���ݶ����Ż��Ų�ѯһ��list
	 * 
	 * @param number
	 * @return
	 */
	public WareRecord GetOneEnterWareRecordByOrderNumAndProductNum(
			String number, String product) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		WareRecord rs = wi.SelectWareRecordByNumberAndProcuct(number, product,
				0);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ��Ӧ���Ż��ż�¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����ID��ѯ�ֿ��ʱ
	 * 
	 * @param id
	 * @return
	 */
	public WareHouse WareHouseById(int id) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		WareHouse rs = wi.WareHouseById(id);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����ID��ѯ�ֿ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����ID��ѯ
	 * 
	 * @param id
	 * @return
	 */
	public Department DepartmentById(int id) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		Department rs = wi.DepartmentById(id);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����ID��ѯ�ֿ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �������б�
	 */
	public List<Car> AllCarByTimdeDecs(String page) {

		long startTime = System.currentTimeMillis();

		CarImpl oi = new CarImpl();
		List<Car> rs = oi.AllCarByTimeDesc(PageUtil.createPage(10000, oi
				.CarCount(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�����б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �������б�
	 */
	public List<Car> CarByTimdeDecs(String page) {

		long startTime = System.currentTimeMillis();

		CarImpl oi = new CarImpl();
		List<Car> rs = oi.CarByTimeDesc(PageUtil.createPage(10000, oi
				.CarCountLack(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�����б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��˾�������б�
	 */
	public List<Car> SelfCarByTimdeDecs(String page) {

		long startTime = System.currentTimeMillis();

		CarImpl oi = new CarImpl();
		List<Car> rs = oi.SelfCarByTimeDesc(PageUtil.createPage(10000, oi
				.CarCountLack(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ��˾�����б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����ID��ѯ����
	 * 
	 * @param id
	 * @return
	 */
	public Car CarById(String id) {
		long startTime = System.currentTimeMillis();

		CarImpl oi = new CarImpl();
		Car rs = oi.CarById(Integer.parseInt(id));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����ID=" + id + "��ѯ������ʱ��" + Float.toString(seconds)
				+ " ��");
		return rs;
	}

	/**
	 * ����³���
	 * 
	 * @param car
	 * @param douser
	 * @return
	 */
	public String AddCar(Car car, Map douser) {
		long startTime = System.currentTimeMillis();

		String rs = "";
		CarImpl oi = new CarImpl();
		Car c = oi.CarByNum(car.getCarnum());

		car.setTime(new Date());
		car.setDouser(((Admin) douser.get("adminuser")).getUsername());

		if (c != null)
			rs = "existence";
		else {
			oi.AddCar(car);
			rs = Success;
		}
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ӳ�����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public String UpdataCar(Car car, Map douser) {
		long startTime = System.currentTimeMillis();

		String rs = "";
		CarImpl oi = new CarImpl();

		car.setTime(new Date());
		car.setDouser(((Admin) douser.get("adminuser")).getUsername());

		oi.UpdateCar(car);
		rs = Success;

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ӳ�����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public String UpdataCarRecord(FastCarRecord fcr) {
		long startTime = System.currentTimeMillis();

		String rs = "";
		CarImpl oi = new CarImpl();
		// List<FastCarRecord> f = oi.FastCarRecordByCarnum(fcr.getCarnum());
		// for (int i = 0; i < f.size(); i++) {
		//
		// if (f.get(i).getRegistartime() == fcr.getRegistartime()
		// && f.get(i).getExptime() == fcr.getExptime()) {
		// rs = "Exist";
		// }
		//
		// }

		FastCarRecord fc = new FastCarRecord();
		fc.setCarnum(fcr.getCarnum());
		fc.setRegistartime(fcr.getRegistartime());
		fc.setExptime(fcr.getExptime());
		fc.setBuytype(fcr.getBuytype());
		fc.setContractname(fcr.getContractname());
		fc.setContracttel(fcr.getContracttel());
		fc.setUsebeizhu(fcr.getUsebeizhu());
		fc.setTime(new Date());
		oi.AddFastCarRecord(fc);

		rs = Success;

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ʹ���˼�¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �����VIP
	 * 
	 * @param car
	 * @param douser
	 * @return
	 */
	public String AddVip(User user, Map douser) {
		long startTime = System.currentTimeMillis();

		String rs = "";
		UserImpl oi = new UserImpl();
		User c = oi.SelectUserByUsernum(user.getUsernum());

		user.setTime(new Date());
		user.setIsture(1);
		user.setDouser(((Admin) douser.get("adminuser")).getUsername());

		if (c != null)
			rs = "existence";
		else {
			oi.AddUser(user);
			rs = Success;
		}
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���VIP��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ���ݿ��Ų�ѯVIP
	 * 
	 * @param number
	 * @return
	 */
	public User VipByUserNum(String number) {
		long startTime = System.currentTimeMillis();

		User rs = null;
		UserImpl oi = new UserImpl();
		rs = oi.SelectUserByUsernum(number);
		System.out.println(rs.getKind());
		System.out.println(rs.getCarnum());
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ݿ��Ų�ѯVIP��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public DatePojo DateUtil(Date date) {
		DatePojo dp = new DatePojo();
		dp.setYyyy(date.getYear() + 1900);
		dp.setMm(date.getMonth() + 1);
		dp.setDd(date.getDate());
		dp.setHh(date.getHours());
		dp.setMM(date.getMinutes());
		dp.setSs(date.getSeconds());

		return dp;
	}

	/**
	 * ������������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastCar(String fast) {
		long startTime = System.currentTimeMillis();

		CarImpl ci = new CarImpl();
		List<Object> rs = ci.FastCar(fast);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��������������ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��������vip
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastVipList(String fast) {
		long startTime = System.currentTimeMillis();

		UserImpl ci = new UserImpl();
		List<Object> rs = ci.FastVipList(fast);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����������Ա��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��������vip
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastUserLoss(String fast) {
		long startTime = System.currentTimeMillis();

		UserImpl ci = new UserImpl();
		List<Object> rs = ci.FastUserLoss(fast);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ٻ�Ա��ʧ�б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��������vip
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastCarWashLoss(String fast) {
		long startTime = System.currentTimeMillis();
		System.out.println(fast);
		CarWashImpl ci = new CarWashImpl();
		List<Object> rs = ci.FastCarWashLoss(fast);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��������ϴ������ʧ�б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * vip�б�
	 */
	public List<User> VipList(String page) {

		long startTime = System.currentTimeMillis();

		UserImpl oi = new UserImpl();
		List<User> rs = oi.AllUserByTimeDesc(PageUtil.createPage(100000, oi
				.UserCount(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯvip�б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ʧ�б�
	 */
	public List<User> LossList(String page) {

		long startTime = System.currentTimeMillis();

		UserImpl oi = new UserImpl();
		List<User> rs = oi.AllLossByTimeDesc(PageUtil.createPage(10000, oi
				.LossCount(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ��ʧ�б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ʧ�б�
	 */
	public List<CarWash> CarWashLossList(String page) {

		long startTime = System.currentTimeMillis();

		CarWashImpl oi = new CarWashImpl();
		List<CarWash> rs = oi.AllLossByTimeDesc(PageUtil.createPage(10000, oi
				.LossCount(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯϴ������ʧ�б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��������������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastSup(String fast) {
		long startTime = System.currentTimeMillis();

		SuppliersImpl ci = new SuppliersImpl();
		List<Object> rs = ci.FastSup(fast);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("�������������̺�ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ������������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastOrder(String fast) {
		long startTime = System.currentTimeMillis();

		OrderImpl ci = new OrderImpl();
		List<Object> rs = ci.FastOrder(fast);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��������������ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ȡ���ڵ�ʱ�䣨��ʽ����
	 * 
	 * @param format
	 * @return
	 */
	public String GetNowDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		long times = System.currentTimeMillis();

		Date date = new Date(times);
		String tim = sdf.format(date);
		return tim;
	}

	/**
	 * ʱ���ʽ��
	 * 
	 * @param format
	 * @return
	 */
	public String DateFormat(String format, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String tim = sdf.format(date);
		return tim;
	}

	/**
	 * ʱ���ʽ������ǰһ����
	 * 
	 * @param format
	 * @return
	 */
	public String DateFormatBefor(String format, Date date, int va) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		long beforeTime = (date.getTime() / 1000) - 60 * 60 * 24 * va;
		date.setTime(beforeTime * 1000);
		String beforeDate = sdf.format(date);
		return beforeDate;
	}

	/**
	 * ��ǰʱ��
	 * 
	 * @param format
	 * @return
	 */
	public String DateFormatNow(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String tim = sdf.format(new Date());
		return tim;
	}

	/**
	 * ��ȡά�޶�����
	 * 
	 * @param fast
	 * @return
	 */
	public String GetRepairNum() {
		long startTime = System.currentTimeMillis();
		String rs = getRom();

		RepairImpl ri = new RepairImpl();

		while (ri.RepairByNum(rs) != null) {
			rs = getRom();
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����ά�޶�����ź�ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �����ά�޼�¼
	 * 
	 * @param car
	 * @param douser
	 * @return
	 */
	public String AddRepair(Repair repair, Map douser) {
		long startTime = System.currentTimeMillis();
		String rs = "";
		repair.setDouser(((Admin) douser.get("adminuser")).getUsername());
		repair.setTime(new Date());

		RepairImpl ri = new RepairImpl();

		if (ri.RepairByNum(repair.getRepairnum()) != null) {
			rs = "existence";
		} else {
			ri.AddRepair(repair);
			rs = Success;
		}
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ά�޼�¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����
	 * 
	 * @return
	 */
	public List<Repair> RepairIngList() {
		long startTime = System.currentTimeMillis();

		RepairImpl ci = new RepairImpl();
		List<Repair> rs = ci.RepairIngList();

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ����ά�޳�����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ɾ��ά���ж��� (���޸�)
	 * 
	 * @param num
	 * @return
	 */
	public String DeleteRepairIng(String num) {
		long startTime = System.currentTimeMillis();

		RepairImpl ci = new RepairImpl();
		String rs = "";
		WareImpl wi = new WareImpl();
		RepairImpl ri = new RepairImpl();

		Repair r = ci.RepairByNum(num);

		if (r == null) {
			rs = "not find";
		} else if (r.getState() != 0) {
			rs = "can not be deleted (Error 1)";
		} else if (ri.RepairRecordByRepairnum(num, 0).size() != 0) {
			rs = "can not be deleted (Error 2)";
		} else if (ri.RepairRecordByRepairnum(num, 1).size() != 0) {
			rs = "can not be deleted (Error 3)";
		} else {
			ci.DeleteRepair(num);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("ɾ������ά�޳�����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ɾ��ά����Ŀ
	 * 
	 * @param num
	 */
	public String DeleteRepairText(String repairnumber) {
		System.out.println(repairnumber + "00000000000000");
		long startTime = System.currentTimeMillis();
		String rs = "";
		RepairImpl ci = new RepairImpl();

		ci.DeleteRepairRecordById(Integer.parseInt(repairnumber));

		rs = Success;
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("ɾ������ά����Ŀ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ɾ������
	 * 
	 * @param num
	 */
	public String DeleteCar(int id) {
		long startTime = System.currentTimeMillis();
		String rs = "";
		CarImpl ci = new CarImpl();

		ci.DeleteCar(id);

		rs = Success;
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("ɾ��������ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}
	
	/**
	 * ɾ�����ڳ���
	 * 
	 * @param num
	 */
	public String DeleteOut(String value1) {
		long startTime = System.currentTimeMillis();
		String rs = "";
		WareImpl wi = new WareImpl();
		String a = "";
	
		JSONArray son = JSONArray.fromObject(value1);
		for (int i = 0; i < son.size(); i++) {
			Object j = son.get(i);
			a= wi.DeleteWareRecord(Integer.parseInt(j.toString()));
		}
		if(a.equals("1")){
			rs = "ɾ���ɹ���";
		}
		else
			rs = "ɾ��ʧ�ܣ�";
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("ɾ����Ч�����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ɾ��ͼƬ
	 * 
	 * @param num
	 */
	public String DeletePic(int id) {
		long startTime = System.currentTimeMillis();
		String rs = "";
		PicImpl ci = new PicImpl();

		ci.DeletePic(id);

		rs = Success;
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("ɾ��ͼƬ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ɾ��δȷ����Ʒ
	 * 
	 * @param num
	 */
	public String DeleteOneOrder(int id) {
		long startTime = System.currentTimeMillis();
		String rs = "";
		OrderImpl ci = new OrderImpl();
		OrderList ol = new OrderList();
		Order o = new Order();
		// ɾ������Ʒ��orderinfo��

		// �޸�orderlist����Ϣ
		// �ҵ�ol�������������Ϣ
		ol = ci.OrderListById(id);
		// ������
		System.out.println("2222");
		double all = (double) ol.getPrice() * ol.getCount();
		System.out.println(all);
		// �ҵ���ǰ����
		o = ci.OrderByNumber(ol.getNumber());
		System.out.println("33");
		double allprice = (double) o.getAlltotalprice() - all;
		System.out.println(allprice);
		System.out.println((double) allprice);
		System.out.println(o.getId());
		// ����
		ci.DeleteOne(id);
		ci.UpdatePri((double) allprice, o.getId());

		rs = Success;
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("ɾ��δȷ����Ʒ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ɾ������ʹ���˼�¼
	 * 
	 * @param num
	 */
	public String DeleteCarUser(int id) {
		long startTime = System.currentTimeMillis();
		String rs = "";
		CarImpl ci = new CarImpl();

		ci.DeleteCarUser(id);

		rs = Success;
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("ɾ������ʹ���˼�¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ѯά�޳���
	 * 
	 * @param num
	 * @return
	 */
	public Repair RepairByNum(String num) {
		long startTime = System.currentTimeMillis();

		RepairImpl ci = new RepairImpl();

		Repair rs = ci.RepairByNum(num);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����num��ѯά�޳�����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ������ʱ�������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastTime(String fast1, String fast2) {
		long startTime = System.currentTimeMillis();

		CarImpl ci = new CarImpl();
		List<Object> rs = ci.FastTime(fast1, fast2);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("������ʱ���������ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ��ʱ�������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastTime1(String fast1) {
		long startTime = System.currentTimeMillis();

		CarImpl ci = new CarImpl();
		List<Object> rs = ci.FastTime1(fast1);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ʱ���������ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ��ʱ�����������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastOrderTime(String fast1, String fast2) {
		long startTime = System.currentTimeMillis();

		OrderImpl ci = new OrderImpl();
		List<Object> rs = ci.FastOrderTime(fast1, fast2);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ʱ�������������ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ��ʱ�����������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastOrderTime1(String fast1) {
		long startTime = System.currentTimeMillis();

		OrderImpl ci = new OrderImpl();
		List<Object> rs = ci.FastOrderTime1(fast1);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ʱ�������������ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ��ʱ�������ά�޼�¼
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastRepairTime1(String fast1) {
		long startTime = System.currentTimeMillis();

		RepairImpl ci = new RepairImpl();
		List<Object> rs = ci.FastRepairTime1(fast1);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ʱ�������ά�޼�¼��ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ��������ά�޼�¼
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastRepair(String fast) {
		long startTime = System.currentTimeMillis();

		RepairImpl ci = new RepairImpl();
		List<Object> rs = ci.FastRepair(fast);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��������ά�޼�¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ʱ�������ά�޼�¼
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastRepairTime(String fast1, String fast2) {
		long startTime = System.currentTimeMillis();

		RepairImpl ci = new RepairImpl();
		List<Object> rs = ci.FastRepairTime(fast1, fast2);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ʱ�������ά�޼�¼��ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ����ά�޵��Ų�ѯ�����¼
	 * 
	 * @param num
	 * @return
	 */
	public List<WareRecord> OutWareHouseRecord(String num) {
		long startTime = System.currentTimeMillis();

		WareImpl ci = new WareImpl();
		List<WareRecord> rs = ci.OutWareHouseRecord(num);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�����¼��ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ��ѯά����ϸ��¼
	 * 
	 * @param id
	 * @return
	 */
	public RepairRecord RepairRecordByWRId(int id) {
		long startTime = System.currentTimeMillis();

		RepairImpl ci = new RepairImpl();
		RepairRecord rs = ci.RepairRecordByWRId(id);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯά�޼�¼��ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ����
	 */
	public String OutWareRecord(OutWare ow) {

		double ccc = ow.getDocount();

		if (!(ow.getType() == 1 || ow.getType() == 2))
			return "DoType Error!";
		long startTime = System.currentTimeMillis();
		WareImpl wi = new WareImpl();

		List<WareRecord> ll = wi.OutWareHouseRecord(ow.getGetuser());

		for (int i = 0; i < ll.size(); i++) {
			WareRecord wr = ll.get(i);

			if (wr.getProductnumber().equals(ow.getProductnum()))
				return "existence";
		}

		String rs = "";
		List<WareRecord> list = wi.SelectWareListByNumber(ow.getProductnum());
		List<WareRecord> t = new ArrayList<WareRecord>();

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getOutprice() == ow.getPrice()
					&& list.get(i).getWarehouse() == ow.getWarehouseid()) {
				t.add(list.get(i));
			}

		}

		list = t;
		// ����Ϊֹ��listΪ���пɲ����Ķ���

		// �ϲ��۸���ͬ�Ĳ�Ʒ�������ۼ�
		for (int i = 0; i < list.size(); i++) {
			WareRecord wr = list.get(i);

			// �������������ڿ��--���ɾ�
			if (ow.getDocount() >= wr.getCount()) {
				double temp = wr.getCount() - ow.getDocount();
				if (temp < 0)
					temp = 0;

				ow.setDocount(ow.getDocount() - wr.getCount());
				if (ow.getDocount() == wr.getCount())
					ow.setDocount(ow.getDocount());
				if (temp == 0)
					wr.setIstrue(0);
				else
					wr.setIstrue(1);

				wr.setFid(wr.getId());
				wr.setDocount(wr.getCount());
				wr.setCount(temp);
				wr.setDotype(3);
				wr.setDouser(ow.getDouser());
				wr.setGetuser(ow.getGetuser());
				wr.setTime(new Date());
				wi.setWrIsfalse(wr.getOrdernumber(), wr.getProductnumber());
				Serializable si = wi.AddWareRecord(wr);

				RepairImpl ri = new RepairImpl();
				RepairRecord rr = new RepairRecord();
				rr.setAmount(wr.getDocount());
				rr.setRepairnum(ow.getGetuser());
				rr.setTime(new Date());
				rr.setTotal(ow.getPrice());
				rr.setType(0);
				rr.setWrid(Integer.parseInt(si.toString()));
				ri.AddRepairRecord(rr);

			} else {
				wr.setFid(wr.getId());
				wr.setCount(wr.getCount() - ow.getDocount());
				wr.setDocount(ow.getDocount());
				wr.setDotype(3);
				wr.setIstrue(1);
				wr.setDouser(ow.getDouser());
				wr.setGetuser(ow.getGetuser());
				wr.setTime(new Date());
				wi.setWrIsfalse(wr.getOrdernumber(), wr.getProductnumber());
				Serializable si = wi.AddWareRecord(wr);

				RepairImpl ri = new RepairImpl();
				RepairRecord rr = new RepairRecord();
				rr.setAmount(wr.getDocount());
				rr.setRepairnum(ow.getGetuser());
				rr.setTime(new Date());
				rr.setTotal(ow.getPrice());
				rr.setType(0);
				rr.setWrid(Integer.parseInt(si.toString()));
				ri.AddRepairRecord(rr);

				ow.setDocount(0);
			}

			if (ow.getDocount() == 0)
				break;

		}

		rs = Success;

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��Ʒ�����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public String RevokeOutWare(String id, String wrid) {
		long startTime = System.currentTimeMillis();

		String rs = "";

		WareImpl wi = new WareImpl();
		RepairImpl ri = new RepairImpl();

		WareRecord rr = wi.WareRecordById(Integer.parseInt(wrid));
		if (rr == null) {
			rs = "not find";
		} else {
			WareRecord rrr = wi.WareRecordById(rr.getFid());
			rrr.setIstrue(1);
			wi.UpdateWareRecordById(rrr);
			wi.DeleteWareRecordById(rr.getId());
			ri.DeleteRepairRecordById(Integer.parseInt(id));
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�����¼��ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ά�޼�¼
	 * 
	 * @return
	 */
	public List<Repair> RepairedList() {
		long startTime = System.currentTimeMillis();

		RepairImpl ci = new RepairImpl();
		List<Repair> rs = ci.RepairedList();

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ����ά�޳�����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ���ά����Ŀ
	 * 
	 * @param rr
	 * @return
	 */
	public String AddArtificialRepair(RepairRecord rr) {
		long startTime = System.currentTimeMillis();
		String rs = "";
		if (rr == null)
			rs = Fail;
		else {
			RepairImpl ci = new RepairImpl();
			rr.setTime(new Date());
			rr.setWrid(0);
			rr.setType(1);
			ci.AddRepairRecord(rr);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ά����Ŀ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ���ά����Ŀ
	 * 
	 * @param rr
	 * @return
	 */
	public List<RepairRecord> ArtificialRepair(String repairnum) {
		long startTime = System.currentTimeMillis();
		RepairImpl ci = new RepairImpl();
		List<RepairRecord> rs = ci.RepairRecordByRepairnum(repairnum, 1);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯά����Ŀ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public String NumToCN(String s) {
		return NumberToCN.number2CNMontrayUnit(new BigDecimal(s));
	}

	/**
	 * ����ֽ���ˮ��¼
	 * 
	 * @param cf
	 * @return
	 */
	public String PayReapirOrder(Cashflow cf) {
		long startTime = System.currentTimeMillis();
		// System.out.println(cf.getPayuser());
		String rs = "";
		if (cf == null)
			rs = Fail;
		else {

			if (cf.getPaytype() == 2) {
				User u = VipByUserNum(cf.getPayuser());
				if (u == null) {
					return "vip not find";
				}
				if (u.getExpdate().before(new Date())) {
					return "vip be overdue";
				}
				if (u.getBalance() < cf.getReallytotal()) {
					return "not sufficient funds";
				}

				UserImpl ui = new UserImpl();
				ui.UpdateUserBalance(cf.getPayuser(), u.getBalance()
						- cf.getReallytotal(), u.getExpdate());
			}

			if (cf.getPaytype() == 3)
				cf.setIstrue(0);
			else
				cf.setIstrue(1);
			CashflowImpl ci = new CashflowImpl();
			int cid = cf.getFid();
			ci.IstrueById(cid);
			cf.setTime(new Date());
			cf.setOrdertype("repair");
			Serializable id = ci.AddCashflow(cf);

			RepairImpl ri = new RepairImpl();

			ri.UpdateRepairState(cf.getNumber(), 1, Integer.parseInt(id
					.toString()));

			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯά����Ŀ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �����
	 * 
	 * @param cf
	 * @return
	 */
	public String PayInfoReturned(String num) {
		long startTime = System.currentTimeMillis();
		// System.out.println(cf.getPayuser());
		String rs = "";
		if (num == null)
			rs = Fail;
		else {

			RepairImpl ri = new RepairImpl();
			ri.UpdateRepairState(num, 0, 0);

			CashflowImpl ci = new CashflowImpl();
			ci.DeleteInfo(num);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����˺�ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����ֽ���ˮ��¼
	 * 
	 * @param cf
	 * @return
	 */
	public String PayReturned(Cashflow cf) {
		long startTime = System.currentTimeMillis();
		// System.out.println(cf.getPayuser());
		String rs = "";
		if (cf == null)
			rs = Fail;
		else {

			if (cf.getPaytype() == 2) {
				User u = VipByUserNum(cf.getPayuser());
				if (u == null) {
					return "vip not find";
				}
				if (u.getExpdate().before(new Date())) {
					return "vip be overdue";
				}
				if (u.getBalance() < cf.getReallytotal()) {
					return "not sufficient funds";
				}

				UserImpl ui = new UserImpl();
				ui.UpdateUserBalance(cf.getPayuser(), u.getBalance()
						- cf.getReallytotal(), u.getExpdate());
			}

			if (cf.getPaytype() == 3)
				cf.setIstrue(0);
			else
				cf.setIstrue(1);
			CashflowImpl ci = new CashflowImpl();
			int cid = cf.getFid();
			ci.IstrueById(cid);
			cf.setTime(new Date());
			cf.setOrdertype("returned");
			Serializable id = ci.AddCashflow(cf);

			RepairImpl ri = new RepairImpl();

			ri.UpdateRepairState(cf.getNumber(), 1, Integer.parseInt(id
					.toString()));

			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯά����Ŀ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public Cashflow CashFlowByNum(String num) {
		long startTime = System.currentTimeMillis();

		CashflowImpl ci = new CashflowImpl();
		Cashflow rs = ci.CashFlowByNum(num);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("Num��ѯ��ˮ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public Cashflow CashFlowById(String id) {
		long startTime = System.currentTimeMillis();

		CashflowImpl ci = new CashflowImpl();
		Cashflow rs = ci.CashFlowById(Integer.parseInt(id));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("Id��ѯ��ˮ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ĳ������ά�޼�¼
	 * 
	 * @return
	 */
	public List<Repair> CarRepairedRecord(int carid) {
		long startTime = System.currentTimeMillis();

		RepairImpl ci = new RepairImpl();
		List<Repair> rs = ci.RepairByCarid(carid);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯĳ����ά�޼�¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ĳ������ʹ���˼�¼
	 * 
	 * @return
	 */
	public List<FastCarRecord> AllFastCarRecord(String carnum) {
		long startTime = System.currentTimeMillis();

		CarImpl ci = new CarImpl();
		List<FastCarRecord> rs = ci.FastCarRecordByCarnum(carnum);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯĳ������ʹ���˼�¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public List<Cashflow> ArrearsList() {
		long startTime = System.currentTimeMillis();

		CashflowImpl ci = new CashflowImpl();
		List<Cashflow> rs = ci.ArrearsList();

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯά��δ����¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public List<Cashflow> ReturnedWareList() {
		long startTime = System.currentTimeMillis();

		CashflowImpl ci = new CashflowImpl();
		List<Cashflow> rs = ci.ReturnedList();

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�˻�δ����¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * Ա����ĳ���
	 */
	public String OutWareRecordByDouser(OutWare ow) {

		long startTime = System.currentTimeMillis();

		double ccc = ow.getDocount();
		String rs = "";
		if (ow.getType() != 2)
			return "DoType Error!";
		WareImpl wi = new WareImpl();
		WareRecord list = new WareRecord();
		WareRecord wr = wi.SelectWareRecordIstureByNumberAndProcuct(ow
				.getOrdernum(), ow.getProductnum());
		// ����Ϊֹ��wrΪ���пɲ����Ķ���
		wi.setWrIsfalse(wr.getOrdernumber(), wr.getProductnumber());
		// wr.setIstrue(0);������ͬ
		// ��idΪ��Ч
		list.setOrdernumber(wr.getOrdernumber());
		list.setProductnumber(wr.getProductnumber());
		list.setOutprice(wr.getOutprice());
		list.setDotype(2);
		list.setDocount(ccc);
		list.setCount(wr.getCount() - ccc);
		list.setDouser(ow.getDouser());
		list.setGetuser(ow.getGetuser());
		list.setTime(new Date());
		list.setWarehouse(wr.getWarehouse());
		list.setFid(wr.getId());
		list.setDepartment(ow.getDepartment());
		// �ж�istureΪ0
		if (ccc == wr.getCount())
			list.setIstrue(0);
		else
			list.setIstrue(1);
		// �������Ч��¼
		wi.AddWareRecord(list);

		// �����˼�¼ // ����ʽ���
		CashflowImpl ci = new CashflowImpl();
		Cashflow cf = new Cashflow();
		cf.setType(1);
		cf.setNumber(ow.getOrdernum());
		cf.setOrdertype("UserOutWare");
		cf.setDouser(ow.getDouser());
		cf.setPaytype(1);
		cf.setIstrue(1);
		cf.setTime(new Date());
		cf.setTotal(ow.getPrice() * ow.getDocount());
		cf.setReallytotal(ow.getPrice() * ow.getDocount());
		cf.setFid(0);
		cf.setPayuser(Integer.toString(ow.getDepartment()));
		ci.AddCashflow(cf);

		rs = Success;

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("Ա����ĳ����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ѯ���е���Ч�ڿ��¼
	 * 
	 * @param
	 * @return
	 */
	public List<WareRecord> GetWareRecordList(int wh) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		List<WareRecord> list = wi.SelectWareListByIsture(wh);

		// List<WareRecord> temp = new ArrayList<WareRecord>();

		// �ϲ��۸���ͬ�Ĳ�Ʒ�������ۼ�
		// for (int i = 0; i < list.size(); i++) {
		// boolean tag = true;// ��Ǳ���
		// int j = 0;
		// for (j = 0; j < temp.size(); j++) {
		// if (list.get(i).getOutprice() == temp.get(j).getOutprice()
		// && list.get(i).getWarehouse() == temp.get(j)
		// .getWarehouse()) {
		// tag = false;
		// int c = temp.get(j).getCount() + list.get(i).getCount();
		// WareRecord s = temp.get(j);
		// s.setCount(c);
		//
		// temp.remove(j);
		// temp.add(s);
		//
		// break;
		// }
		// }
		//
		// if (tag) {
		// temp.add(list.get(i));
		// }
		//
		// }
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ��Ч�ڿ��¼��ʱ��" + Float.toString(seconds) + " ��");
		return list;
	}

	/**
	 * ��������
	 * 
	 * @param fast
	 * @return
	 */
	public Object FastConsumption(String fast, String id, String wh) {
		long startTime = System.currentTimeMillis();
		WareImpl ci = new WareImpl();
		Object rs = ci.FastConsumptionList(fast, Integer.parseInt(id), Integer
				.parseInt(wh));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����������Ч�ڿ��б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	// @Test
	// public void ax() {
	// System.out.println(FastConsumption("3", 1).size());
	// }

	public String Recharge(String usernum, String recharge, String time1,
			String unit, String douser, String payment) {
		long startTime = System.currentTimeMillis();

		String rs = "";

		UserImpl oi = new UserImpl();

		User c = oi.SelectUserByUsernum(usernum);
		Calendar cl = Calendar.getInstance();
		cl.setTime(c.getExpdate());
		if (unit.trim().equals("y")) {
			cl.add(Calendar.YEAR, Integer.parseInt(time1));
		} else if (unit.trim().equals("m")) {
			cl.add(Calendar.MONTH, Integer.parseInt(time1));
		}
		c.setExpdate(cl.getTime());

		c.setBalance(c.getBalance() + Double.parseDouble(recharge));

		// ʱ��Ӽ�

		// ���� д���µ����ݿ�������ˮ��¼
		if (Double.parseDouble(recharge) != 0) {
			oi.UpdateUserBalance(usernum, c.getBalance(), c.getExpdate());
			CashflowImpl ci = new CashflowImpl();
			Cashflow cf = new Cashflow();
			cf.setType(1);
			cf.setOrdertype("recharge");
			cf.setNumber(usernum);
			cf.setDouser(douser);
			cf.setIstrue(1);
			cf.setTime(new Date());
			cf.setPaytype(Integer.parseInt(payment));
			cf.setTotal(Double.parseDouble(recharge));
			cf.setReallytotal(Double.parseDouble(recharge));
			ci.AddCashflow(cf);
		}

		rs = "success";
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("VIP��ֵ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public Admin AdminByUsername(String username) {
		long startTime = System.currentTimeMillis();

		AdminImpl ci = new AdminImpl();
		Admin rs = ci.AdminUserByUsername(username);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����username��ѯAdmin��¼��ʱ��" + Float.toString(seconds)
				+ " ��");
		return rs;
	}

	/**
	 * ��ѯ�����ڿ��¼ ����Ч�ļ�¼
	 * 
	 * @param number
	 * @return
	 */
	public List<WareRecord> GetIstureWareRecordList(String number) {
		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		List<WareRecord> list = wi.SelectWareListByNumber(number);
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�ڿ��¼��ʱ��" + Float.toString(seconds) + " ��");
		return list;
	}

	/**
	 * ����productnumberandproductnumber�鶩������
	 * 
	 * @param s
	 * @return
	 */
	public OrderList OrderListByProductNumberAndNumber(String productnumber,
			String number) {
		long startTime = System.currentTimeMillis();
		OrderList rs;
		OrderImpl oi = new OrderImpl();
		rs = oi.OrderListByProductNumberAndNumber(productnumber, number);
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ݻ��Ŷ����Ų�ѯ���������б��ʱ��" + Float.toString(seconds)
				+ " ��");
		return rs;
	}

	/**
	 * ������ϴ����
	 * 
	 * @param carwash
	 * @param douser
	 * @return
	 */
	public String AddCarWash(CarWash carwash, Map douser) {

		long startTime = System.currentTimeMillis();
		String rs = "";
		CarWashImpl oi = new CarWashImpl();
		CarWash c = oi.CarWashByNum(carwash.getNumber());
		carwash.setTime(new Date());
		carwash.setDouser(((Admin) douser.get("adminuser")).getUsername());
		carwash.setIstrue("1");
		// ��Ӽ�¼
		CarWashImpl ci = new CarWashImpl();
		CarWashRecord cwr = new CarWashRecord();
		cwr.setDotype(1);
		cwr.setName(carwash.getName());
		cwr.setNumber(carwash.getNumber());
		cwr.setDouser(((Admin) douser.get("adminuser")).getUsername());
		cwr.setOutprice(carwash.getBalance());
		cwr.setTime(new Date());
		cwr.setResiduedegree(carwash.getResiduedegree());
		cwr.setPayment(carwash.getPayment());
		ci.AddCarWashRecord(cwr);
		if (c != null)
			rs = "existence";
		else {
			oi.AddCarWash(carwash);
			rs = Success;
		}
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ϴ������ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	// @Test
	// public void a() {
	// CarWash c = new CarWash();
	// c.setNumber("4321");
	// c.setName("mahaojie");
	// c.setTel("12343123");
	// c.setResiduedegree(11);
	// // c.setBalance(4000);
	// c.setExpdate(new Date());
	// c.setPayment(0);
	// c.setDouser("admin");
	// // AddCarWash(c);
	// }

	/**
	 * ���ݿ��Ų�ѯϴ����
	 * 
	 * @param number
	 * @return
	 */
	public CarWash CarWashByNum(String number) {
		long startTime = System.currentTimeMillis();

		CarWash rs = null;
		CarWashImpl oi = new CarWashImpl();
		rs = oi.SelectCarWashByNumber(number);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ݿ��Ų�ѯϴ������ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ���ݿ��Ų�ѯϴ����¼
	 * 
	 * @param number
	 * @return
	 */
	public List<CarWashRecord> CarWashRecordByNum(String number) {
		long startTime = System.currentTimeMillis();

		CarWashImpl oi = new CarWashImpl();
		List<CarWashRecord> rs = oi.SelectCarWashRecordByNumber(number);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ݿ��Ų�ѯϴ�������Ѽ�¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ϴ�����б�
	 */
	public List<CarWash> CarWashList(String page) {

		long startTime = System.currentTimeMillis();

		CarWashImpl oi = new CarWashImpl();
		List<CarWash> rs = oi.AllCarWareByTimeDesc(PageUtil.createPage(100000,
				oi.CarWashCount(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯϴ�����б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * 
	 * @param number
	 * @param douser
	 * @param time1
	 * @param unit
	 * @param recharge
	 * @param rechargetime
	 * @param payment
	 * @return
	 */
	public String CarWashRecharge(String number, String douser, String time1,
			String unit, String recharge, String rechargetime, String payment) {
		long startTime = System.currentTimeMillis();

		String rs = "";

		CarWashImpl oi = new CarWashImpl();

		CarWash c = oi.CarWashByNum(number);
		Calendar cl = Calendar.getInstance();
		cl.setTime(c.getExpdate());
		if (unit.trim().equals("y")) {
			cl.add(Calendar.YEAR, Integer.parseInt(time1));
		} else if (unit.trim().equals("m")) {
			cl.add(Calendar.MONTH, Integer.parseInt(time1));
		}
		c.setExpdate(cl.getTime());
		c.setResiduedegree(c.getResiduedegree()
				+ Integer.parseInt(rechargetime));
		// c.setBalance(c.getBalance() + Double.parseDouble(recharge));

		// ʱ��Ӽ�

		// ���������
		if (Double.parseDouble(recharge) != 0) {
			oi.UpdateCarWash(number, c.getExpdate(), c.getResiduedegree());
			CarWashImpl ci = new CarWashImpl();
			CarWashRecord cwr = new CarWashRecord();
			cwr.setDotype(1);
			cwr.setName(c.getName());
			cwr.setNumber(number);
			cwr.setDouser(douser);
			cwr.setOutprice(Double.parseDouble(recharge));
			cwr.setTime(new Date());
			cwr.setResiduedegree(Integer.parseInt(rechargetime));
			cwr.setPayment(Integer.parseInt(payment));
			ci.AddCarWashRecord(cwr);
		}

		rs = "success";
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("ϴ������ֵ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �������������б�
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object[]> FastOutWareList(String fast) {
		long startTime = System.currentTimeMillis();

		WareImpl ci = new WareImpl();
		List<Object[]> rs = ci.FastOutWareList(fast);
		for (int i = 0; i < rs.size(); i++) {
			String adname = rs.get(i)[4].toString();
			if (isNumeric(adname) == false) {
				AdminImpl ai = new AdminImpl();
				Admin ad = ai.AdminUserByUsername(adname);
				// ��һ����ad.getname����4��
				rs.get(i)[4] = ad.getName();
			}
			int department = Integer.parseInt(rs.get(i)[9].toString());
			String departmentName = DepartmentById(department)
					.getDepartmentname();
			rs.get(i)[9] = departmentName;

		}
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("�������������б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			// System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ��������ϴ�����б�
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastCarWashList(String fast) {
		long startTime = System.currentTimeMillis();
		CarWashImpl ci = new CarWashImpl();
		List<Object> rs = ci.FastCarWashList(fast);
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��������ϴ�����б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ˢ��ϴ��
	 * 
	 * @param fast
	 * @return
	 */
	public String SwipingCard(String number) {
		long startTime = System.currentTimeMillis();
		String r = "";
		// ϴ����������һ
		CarWashImpl ci = new CarWashImpl();
		CarWash c = ci.CarWashByNum(number);
		int residuedegree = c.getResiduedegree() - 1;
		System.out.println(residuedegree);
		ci.UpdateCarWashResidueDegree(residuedegree, number);
		// ���µ���Ӽ�¼
		CarWashRecord cwr = new CarWashRecord();
		cwr.setDotype(0);
		cwr.setName(c.getName());
		cwr.setNumber(number);
		cwr.setDouser(c.getDouser());
		cwr.setTime(new Date());
		cwr.setResiduedegree(1);
		cwr.setPayment(5);
		ci.AddCarWashRecord(cwr);
		r = "ˢ���ɹ�";
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("ϴ��ˢ����ʱ��" + Float.toString(seconds) + " ��");
		return r;
	}

	/**
	 * ���п쳵�б�
	 */
	public List<FastCarList> AllPublicFastCarByTimdeDecs(String page) {

		long startTime = System.currentTimeMillis();

		FastCarListImpl oi = new FastCarListImpl();
		List<FastCarList> rs = oi.AllPublicFastCarListByTimeDesc(PageUtil
				.createPage(10000, oi.PublicFastCarCount(), Integer
						.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ���п쳵�б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ˽�п쳵�б�
	 */
	public List<FastCarList> AllPrivateFastCarByTimeDesc(String page) {

		long startTime = System.currentTimeMillis();

		FastCarListImpl oi = new FastCarListImpl();
		List<FastCarList> rs = oi.AllPrivateFastCarListByTimeDesc(PageUtil
				.createPage(10000, oi.PrivateFastCarCount(), Integer
						.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ˽�п쳵�б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����³���
	 * 
	 * @param car
	 * @param douser
	 * @return
	 */
	public String AddFastCar(FastCarList fastcarlist) {
		long startTime = System.currentTimeMillis();
		String rs = "";
		System.out.println("111111");
		FastCarListImpl oi = new FastCarListImpl();
		FastCarList c = oi.FastCarListByNum(fastcarlist.getCarnum());

		System.out.println("FastCarListByNum");
		fastcarlist.setTime(new Date());
		// fastcarlist.setInsureoffer(fastcarlist.getInsureoffer());
		System.out.println(fastcarlist.getInsureoffer());
		if (c != null)
			rs = "existence";
		else {
			oi.AddFastCarList(fastcarlist);
			rs = Success;
		}
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ӹ��п쳵��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����ID��ѯ����
	 * 
	 * @param id
	 * @return
	 */
	public FastCarList SelectFastCarbyId(String id) {
		long startTime = System.currentTimeMillis();

		FastCarListImpl oi = new FastCarListImpl();
		FastCarList rs = oi.FastCarListById(Integer.parseInt(id));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����ID=" + id + "��ѯ�쳵������ʱ��" + Float.toString(seconds)
				+ " ��");
		System.out.println(rs.getUserdiscount());
		return rs;
	}

	public String UpdataFastCar(FastCarList fastcarlist, Map douser) {
		long startTime = System.currentTimeMillis();
		String rs = "";

		FastCarListImpl oi = new FastCarListImpl();

		fastcarlist.setTime(new Date());
		oi.UpdateFastCarList(fastcarlist);

		// FastCarRecord fr = new FastCarRecord();
		// fr.setCarnum(fastcarlist.getCarnum());
		// fr.setFramenum(fastcarlist.getFramenum());
		// fr.setContractaddress(fastcarlist.getContractaddress());
		// fr.setContractidentity(fastcarlist.getContractidentity());
		// fr.setContractname(fastcarlist.getContractname());
		// fr.setContracttel(fastcarlist.getContracttel());
		// fr.setMileage(fastcarlist.getMileage());
		// fr.setModel(fastcarlist.getModel());
		// fr.setTime(new Date());
		// oi.AddFastCarRecord(fr);
		rs = Success;
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���¿쳵������ʱ��" + Float.toString(seconds) + " ��");
		return rs;

	}

	/**
	 * ���������쳵
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastFastCar(String fast) {
		long startTime = System.currentTimeMillis();

		FastCarListImpl ci = new FastCarListImpl();
		List<Object> rs = ci.FastCarList(fast);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ٿ쳵��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ����˽�������쳵
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastFastCarOne(String fast) {
		long startTime = System.currentTimeMillis();

		FastCarListImpl ci = new FastCarListImpl();
		List<Object> rs = ci.FastCarListone(fast);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���ٿ쳵��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 *�˻��ֿ�
	 * 
	 * @return
	 */
	public List<WareRecord> ReturnedList(int wh) {
		long startTime = System.currentTimeMillis();

		WareImpl ci = new WareImpl();
		List<WareRecord> rs = ci.SelectWareListByWareHouse(wh);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯĳ�ֿ�������Ч����ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public int AddReturned(ReturnedWare rw, Map douser) {
		long startTime = System.currentTimeMillis();
		int rs ;
		// ����ʽ���
		CashflowImpl ci = new CashflowImpl();
		Cashflow cf = new Cashflow();
		cf.setType(1);
		cf.setNumber(rw.getOrdernumber());
		cf.setOrdertype("returned");
		cf.setDouser(((Admin) douser.get("adminuser")).getUsername());
		cf.setPaytype(rw.getPaytype());
		if (rw.getPaytype() == 3)
			cf.setIstrue(0);
		else
			cf.setIstrue(1);
		cf.setTime(new Date());
		cf.setTotal(rw.getAllprice());
		cf.setReallytotal(rw.getAllprice());
		cf.setFid(0);
		cf.setPayuser(rw.getProductnumber());
		ci.AddCashflow(cf);
		// ����˻���¼
		WareImpl oi = new WareImpl();
		rw.setDouser(((Admin) douser.get("adminuser")).getUsername());
		rw.setTime(new Date());
		oi.AddReturnedWareRecord(rw);
		WareImpl wi = new WareImpl();
		// ���ҵ����嶩��
		WareRecord wr = wi.SelectWareRecordIstureByNumberAndProcuct(rw
				.getOrdernumber(), rw.getProductnumber());
		WareRecord wr2 = new WareRecord();
		if (wr.getCount() == rw.getCount())
			wr2.setIstrue(0);// �˻����ڿ��ʱ

		else
			wr2.setIstrue(1);// �˻�С�ڿ��ʱ

		wr2.setOrdernumber(rw.getOrdernumber());
		wr2.setProductnumber(rw.getProductnumber());
		wr2.setOutprice(wr.getOutprice());
		wr2.setCount(wr.getCount() - rw.getCount());
		wr2.setDouser(((Admin) douser.get("adminuser")).getUsername());
		wr2.setTime(new Date());
		wr2.setWarehouse(rw.getWarehouse());
		wr2.setDocount(rw.getCount());
		wr2.setGetuser(rw.getOrdernumber());
		wr2.setFid(wr.getId());
		wr2.setDotype(3);
		
		wi.setWrIsfalse(rw.getOrdernumber(), rw.getProductnumber());
		wi.AddWareRecord(wr2);
		rs = wi.FindNewId().getId();
		
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("�˻���ʱ��" + Float.toString(seconds) + " ��");
		return rs;

	}

	/**
	 * ��ʱ�������
	 * 
	 * @param fast
	 * @return
	 */
	public WareRecord ReturnInfo() {
		long startTime = System.currentTimeMillis();

		WareRecord rs = null ;
		WareImpl wi = new WareImpl();
		rs = wi.FindNewId();
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("�˻�����ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ��ʱ�������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastReturnedTime1(String fast1, String wh) {
		long startTime = System.currentTimeMillis();

		WareImpl ci = new WareImpl();
		List<Object> rs = ci.FastReturnedTime1(fast1, wh);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ʱ��������˻�����ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ��������ά�޼�¼
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastReturned(String fast, String wh) {
		long startTime = System.currentTimeMillis();

		WareImpl ci = new WareImpl();
		List<Object> rs = ci.FastReturned(fast, wh);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("���������˻���¼��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ʱ�������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastReturnedTime(String fast1, String fast2, String wh) {
		long startTime = System.currentTimeMillis();

		WareImpl ci = new WareImpl();
		List<Object> rs = ci.FastReturnedTime1(fast1, fast2, wh);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ʱ��������˻�����ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * �������
	 * 
	 * @param purchase
	 * @param douser
	 * @return
	 */
	public String AddPurchase(Purchase purchase, Map douser) {
		long startTime = System.currentTimeMillis();

		String rs = "";
		PurchaseImpl pi = new PurchaseImpl();
		Purchase p = new Purchase();
		p.setMaterial(purchase.getMaterial());
		p.setTime(new Date());
		p.setProposer(((Admin) douser.get("adminuser")).getUsername());
		p.setCount(purchase.getCount());
		p.setUnit(purchase.getUnit());
		p.setSpecifications(purchase.getSpecifications());
		p.setMadein(purchase.getMadein());
		p.setModels(purchase.getModels());
		p.setPrice(purchase.getPrice());
		p.setUseto(purchase.getUseto());
		p.setAuditor(0);

		pi.AddPurchase(p);
		rs = Success;

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��Ӳɹ������ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �ɹ������б�
	 */
	public List<Purchase> AllPurchaseByTimdeDecs(String page) {

		long startTime = System.currentTimeMillis();

		PurchaseImpl oi = new PurchaseImpl();
		List<Purchase> rs = oi.PurchaseList(PageUtil.createPage(100000, oi
				.PurchaseCount(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�ɹ������б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ͨ������
	 */
	public String UpdateAuditor(int id) {
		long startTime = System.currentTimeMillis();
		String rs = "";
		if ((Object) id == null)
			rs = "false";
		else {
			PurchaseImpl oi = new PurchaseImpl();
			oi.AuditorById(id);
			rs = Success;
		}

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("ͨ�������ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ������������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastPurchase(String fast) {
		long startTime = System.currentTimeMillis();

		PurchaseImpl ci = new PurchaseImpl();
		List<Object> rs = ci.FastPurchase(fast);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("�������������ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ��ʱ�������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastPurchaseTime1(String fast1) {
		long startTime = System.currentTimeMillis();

		PurchaseImpl ci = new PurchaseImpl();
		List<Object> rs = ci.FastTime1(fast1);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ʱ������������ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ��ʱ�������
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastPurchaseTime(String fast1, String fast2) {
		long startTime = System.currentTimeMillis();

		PurchaseImpl ci = new PurchaseImpl();
		List<Object> rs = ci.FastTime(fast1, fast2);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ʱ������������ʱ��" + Float.toString(seconds) + " ��");

		return rs;
	}

	/**
	 * ����б�
	 */
	public List<Object> InventoryList(String page, String value1) {

		long startTime = System.currentTimeMillis();

		WareImpl oi = new WareImpl();
		List<Object> rs = oi.InventoryList(PageUtil.createPage(100000, oi
				.InventoryCount(), Integer.parseInt(page)), Integer
				.parseInt(value1));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�����б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ������������б�
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastInventory(String fast, String value2) {
		long startTime = System.currentTimeMillis();

		WareImpl oi = new WareImpl();
		List<Object> rs = oi.FastInventory(fast, Integer.parseInt(value2));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("������������б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	public String UpdateSupp(Suppliers supp) {
		long startTime = System.currentTimeMillis();

		String rs = "";
		SuppliersImpl si = new SuppliersImpl();
		Suppliers s = new Suppliers();
		s.setFastname(supp.getFastname());
		s.setId(supp.getId());
		s.setName(supp.getName());
		s.setTel(supp.getTel());
		si.UpdateSupplier(s);
		rs = Success;
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("�޸Ĺ�Ӧ�̺�ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * ת���ֿ�
	 * 
	 * @return
	 */
	public String RollOut(String id, String wc, String wh) {
		long startTime = System.currentTimeMillis();
		double wcc = Double.parseDouble(wc);
		WareImpl wi = new WareImpl();
		String rs = "";
		// �����ȸ���id�ҵ�ת��ǰ������
		WareRecord wr = wi.SelectWareListById(Integer.parseInt(id));
		// ���Ű��������ֿ��Ӧ׼�ˣ�����һ������
		WareRecord wr2 = new WareRecord();
		wr2.setCount(wcc);
		wr2.setDocount(wcc);
		wr2.setDotype(0);
		wr2.setFid(wr.getId());
		wr2.setGetuser(wr.getGetuser());
		wr2.setIstrue(1);
		wr2.setOrdernumber(wr.getOrdernumber());
		wr2.setOutprice(wr.getOutprice());
		wr2.setTime(new Date());
		wr2.setProductnumber(wr.getProductnumber());
		wr2.setWarehouse(Integer.parseInt(wh));
		wr2.setDouser(wr.getDouser());
		wi.AddWareRecord(wr2);
		// ���Ѵ���ǰ���������ĺ�(Ҫ����count��wc��������ϵ)
		if (wr.getCount() > wcc) {
			wi.UpdateWareRecordCountById(Integer.parseInt(id), wr.getCount()
					- wcc);
		} else if (wr.getCount() == wcc) {
			wi.UpdateWareRecordAllById(Integer.parseInt(id), Double.parseDouble(wc));
		}
		Allot a = new Allot();
		a.setCallout(wr.getWarehouse());
		a.setCallin(Integer.parseInt(wh));
		a.setOrdernumber(wr.getOrdernumber());
		a.setProductnumber(wr.getProductnumber());
		a.setTime(new Date());
		a.setCount(wc);
		wi.AddAllot(a);
		rs = Success;

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("����²ֿ��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �����б�
	 */
	public List<Allot> AllotList(String page) {

		long startTime = System.currentTimeMillis();

		WareImpl wi = new WareImpl();
		List<Allot> rs = wi.AllAllot(PageUtil.createPage(100000, wi
				.AllotCount(), Integer.parseInt(page)));

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("��ѯ�����б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}

	/**
	 * �������������б�
	 * 
	 * @param fast
	 * @return
	 */
	public List<Object> FastAllot(String fast) {
		long startTime = System.currentTimeMillis();

		WareImpl oi = new WareImpl();
		List<Object> rs = oi.FastAllot(fast);

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
		System.out.println("�������������б��ʱ��" + Float.toString(seconds) + " ��");
		return rs;
	}
}
