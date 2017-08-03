package com.haojie.net;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.haojie.extend.AllFunction;
import com.haojie.impl.FunctionImpl;
import com.haojie.pojo.Admin;
import com.haojie.pojo.Car;
import com.haojie.pojo.CarWash;
import com.haojie.pojo.Cashflow;
import com.haojie.pojo.FastCarList;
import com.haojie.pojo.FastCarRecord;
import com.haojie.pojo.OutWare;
import com.haojie.pojo.Purchase;
import com.haojie.pojo.Repair;
import com.haojie.pojo.RepairRecord;
import com.haojie.pojo.ReturnedWare;
import com.haojie.pojo.Suppliers;
import com.haojie.pojo.User;
import com.haojie.pojo.WareRecord;
import com.haojie.pojo.Wares;

public class AdminControlAction extends AllFunction implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8652577665737756355L;
	/**
	 * �û�ID
	 */
	private int AdminUserId;
	/**
	 * ��վSession
	 */
	private Map<String, Object> session;
	/**
	 * �û���Ϣ
	 */
	private Admin aus;
	/**
	 * ��ʾ��Ϣ
	 */
	private String msg;
	/**
	 * ��������
	 */
	private String function;
	/**
	 * ����
	 */
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String value6;
	private String value7;
	private String value8;
	private int value5;

	private String page;
	private Car car;
	private User vip;
	private Repair repair;
	private OutWare ow;
	private RepairRecord rr;
	private Cashflow cf;
	private CarWash carwash;
	private ReturnedWare rw;
	private Purchase purchase;

	private FastCarList fastcarlist;
	private FastCarRecord fastcarrecord;
	/**
	 * JSON����ֵ
	 */
	private Object ResultJson;

	/**
	 * ���ڶ���ȷ��
	 */
	private List<WareRecord> wrlist;

	/**
	 * ��Ʒ
	 */
	private Wares wares;

	/**
	 * ��̨�������봫ֵ
	 */
	private String npwd;
	private String pwd;
	/**
	 * Ȩ���� ��ֵ��
	 */
	private Suppliers suppliers;

	@Override
	public String execute() throws Exception {

		String result = route();
		return result;

	}

	private String route() {
		if (function != null) {

			if (function.equals("outLogin")) {
				if (session.get("adminuser") != null)
					System.out.println(((Admin) session.get("adminuser"))
							.getUsername()
							+ "�����˳���̨ϵͳ...");
				session.clear();
				setMsg("�˳�ϵͳ�ɹ��������µ�½��");
				System.out.println("�˳�ϵͳ�ɹ���");
				return "fail";
			}

			if (session.get("adminuser") == null) {
				System.out.println("δ��¼�û�����ִ��" + function + "����������");
				return "fail";
			}

			Admin ai = (Admin) session.get("adminuser");
			System.out.println("ID = " + ai.getId() + " , Name = "
					+ ai.getName() + " ���û�ִ�� Function = " + function + " �Ĳ���");

			System.out.println("����Ȩ����֤......");

			if (super.Power(ai.getId(), function)) {
				System.out.println("ͨ��Ȩ����֤,����");
			} else {
				System.out.println("û�з���Ȩ��,����");
				return "nopower";
			}

			if (new FunctionImpl().SelectFunctionByName(function) != null)
				return function;
			else {
				setMsg("û�и÷���");
				return "error";
			}
		}
		// �ѵ�½�û�
		if (session.get("adminuser") != null) {
			return "success";
		}
		// δ��ֵ��Ϊ���δ򿪵�½ҳ��
		if (aus == null) {
			return "fail";
		}

		// ��ֵ��֤
		Admin auss = Login(aus);

		// ��֤ʧ�ܣ����ش���
		if (auss == null) {
			setMsg("�û������������");
			return "fail";
		}

		// ��֤�ɹ�������session����ת��ҳ
		session.put("adminuser", auss);
		System.out.println(aus.getUsername() + "��½�ɹ���");

		return "success";
	}

	public Object getResultJson() {

		switch (new FunctionImpl().SelectFunctionByName(function).getId()) {
		case 5:
			if (super.smsramdomresetpassword(AdminUserId) == 1) {
				return "success";
			} else {
				return "fail";
			}

		case 8:
			if (suppliers == null || suppliers.getName() == null
					|| suppliers.equals(""))
				return "request error";

			if (super.SelectSuppliersByName(suppliers.getName()) != null)
				return "Name existence";

			super.AddSuppliers(suppliers);

			return "Success";

		case 4:
			return super.SelectAdminUserById(AdminUserId);
		case 6:
			return super.AdminRePassWord(session, pwd, npwd);
		case 9:
			return super.PowerFunctionByPowerId(Integer.parseInt(value1));
		case 10:
			return super.AddPower(value1, value2);
		case 11:
			return super.UpdatePower(value1, value2, value3);
		case 12:
			return super.UpdateAdmin(aus);
		case 13:
			return super.AddAadmin(aus);
		case 15:
			return super.AddWareHouse(value1);
		case 16:
			return super.UpdateWareHouse(value1, Integer.parseInt(value2));
		case 20:
			return super.AddModels(value1);
		case 21:
			return super.UpdateModels(value1, Integer.parseInt(value2));
		case 22:
			return super.AddWares(wares);
		case 23:
			return super.FastWares(value1, value2);
		case 24:
			return super.AddOrder(value1, value2, value3);
		case 28:
			return super.SelectOrderListByNumberOrderByTimeDesc(value1);
		case 32:
			return super.CarById(value1);
		case 33:
			return super.AddCar(car, session);
		case 34:
			return super.UpdataCar(car, session);
		case 37:
			return super.AddVip(vip, session);
		case 40:
			return super.FastCar(value1);
		case 42:
			return super.AddRepair(repair, session);
		case 48:
			return super.OutWareRecord(ow);
		case 49:
			return super.RevokeOutWare(value1, value2);
		case 50:
			return super.AddArtificialRepair(rr);
		case 51:
			return super.PayReapirOrder(cf);
		case 101:
			return super.FastVipList(value1);
		case 102:
			return super.FastSup(value1);
		case 103:
			return super.FastOrder(value1);
		case 104:
			return super.FastTime(value1, value2);
		case 105:
			return super.FastTime1(value1);
		case 106:
			return super.FastOrderTime(value1, value2);
		case 107:
			return super.FastOrderTime1(value1);
		case 112:
			return super.RepairedList();
		case 114:
			return super.FastRepairTime(value1, value2);
		case 113:
			return super.FastRepairTime1(value1);
		case 115:
			return super.FastRepair(value1);
		case 117:
			return super.CarRepairedRecord(value5);
		case 120:
			return super.GetWareRecordList(value5);
		case 122:
			return super.OutWareRecordByDouser(ow);
		case 127:
			return super.DeleteRepairText(value1);
		case 130:
			return super.Recharge(value1, value2, value3, value4, value6,
					value7);
		case 137:
			return super.AddCarWash(carwash, session);
		case 138:
			return super.CarWashRecharge(value1, value2, value3, value4,
					value6, value7, value8);
		case 140:
			return super.FastOutWareList(value1);
		case 141:
			return super.FastCarWashList(value1);
		case 142:
			return super.SwipingCard(value1);
		case 146:
			return super.AddFastCar(fastcarlist);
		case 147:
			return super.SelectFastCarbyId(value1);
		case 148:
			return super.UpdataFastCar(fastcarlist, session);
		case 149:
			return super.FastFastCar(value1);
		case 150:
			return super.FastFastCarOne(value1);
		case 153:
			return super.AddReturned(rw, session);
		case 154:
			return super.FastReturned(value1, value2);
		case 155:
			return super.FastReturnedTime1(value1, value2);
		case 156:
			return super.FastReturnedTime(value1, value2, value3);
		case 158:
			return super.PayReturned(cf);
		case 161:
			return super.AddPurchase(purchase, session);
		case 162:
			return super.UpdateAuditor(value5);
		case 163:
			return super.FastPurchase(value1);
		case 164:
			return super.FastPurchaseTime1(value1);
		case 165:
			return super.FastPurchaseTime(value1, value2);
		case 167:
			return super.FastInventory(value1, value2);
		case 169:
			return super.SuppliersAllById(value1);
		case 168:
			return super.UpdateSupp(suppliers);
		case 171:
			return super.UpdateSupp(suppliers);
		case 174:
			return super.AddBuytype(value1);
		case 175:
			return super.UpdateBuytype(value1, value2);
		case 177:
			return super.UpdataCarRecord(fastcarrecord);
		case 178:
			return super.DeleteCar(value5);
		case 179:
			return super.DeleteCarUser(value5);
		case 181:
			return super.FastConsumption(value1, value2, value3);
		case 184:
			return super.DeleteOneOrder(value5);
		case 185:
			return super.UpdateUserIdIsture(value5);
		case 186:
			return super.UpdateCarWashIstrue(value5);
		case 189:
			return super.FastCarWashLoss(value1);
		case 190:
			return super.FastUserLoss(value1);
		case 191:
			return super.DeletePic(value5);
		case 192:
			return super.RollOut(value4, value6, value7);
		case 194:
			return super.FastAllot(value1);
		case 197:
			return super.AddDepartment(value1);
		case 198:
			return super.UpdateDepartment(value1, Integer.parseInt(value2));
		case 199:
			return super.Revoke(value5);
		case 202:
			return super.BeRevokeOutWare(value1, value2);
		case 203:
			return super.PayInfoReturned(value1);
		case 205:
			return super.DeleteOut(value1);
		default:
			return "error";
		}
	}

	/*
	 * ����ΪGET SET
	 */

	public ReturnedWare getRw() {
		return rw;
	}

	public FastCarRecord getFastcarrecord() {
		return fastcarrecord;
	}

	public void setFastcarrecord(FastCarRecord fastcarrecord) {
		this.fastcarrecord = fastcarrecord;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public void setRw(ReturnedWare rw) {
		this.rw = rw;
	}

	public FastCarList getFastcarlist() {
		return fastcarlist;
	}

	public void setFastcarlist(FastCarList fastcarlist) {
		this.fastcarlist = fastcarlist;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public Admin getAus() {
		return aus;
	}

	public void setAus(Admin aus) {
		this.aus = aus;
	}

	public void setSession(Map<String, Object> arg0) {
		session = arg0;

	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getFunction() {
		return function;
	}

	public void setAdminUserId(int adminUserId) {
		AdminUserId = adminUserId;
	}

	public int getAdminUserId() {
		return AdminUserId;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwd() {
		return pwd;
	}

	public String getNpwd() {
		return npwd;
	}

	public void setNpwd(String npwd) {
		this.npwd = npwd;
	}

	public void setResultJson(Object resultJson) {
		ResultJson = resultJson;
	}

	public Suppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Suppliers suppliers) {
		this.suppliers = suppliers;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue3() {
		return value3;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

	public void setWares(Wares wares) {
		this.wares = wares;
	}

	public Wares getWares() {
		return wares;
	}

	public void setWrlist(List<WareRecord> wrlist) {
		this.wrlist = wrlist;
	}

	public List<WareRecord> getWrlist() {
		return wrlist;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Car getCar() {
		return car;
	}

	public void setVip(User vip) {
		this.vip = vip;
	}

	public User getVip() {
		return vip;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setOw(OutWare ow) {
		this.ow = ow;
	}

	public OutWare getOw() {
		return ow;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getValue4() {
		return value4;
	}

	public RepairRecord getRr() {
		return rr;
	}

	public void setRr(RepairRecord rr) {
		this.rr = rr;
	}

	public void setCf(Cashflow cf) {
		this.cf = cf;
	}

	public Cashflow getCf() {
		return cf;
	}

	public String getValue6() {
		return value6;
	}

	public void setValue6(String value6) {
		this.value6 = value6;
	}

	public String getValue7() {
		return value7;
	}

	public String getValue8() {
		return value8;
	}

	public void setValue8(String value8) {
		this.value8 = value8;
	}

	public CarWash getCarwash() {
		return carwash;
	}

	public void setCarwash(CarWash carwash) {
		this.carwash = carwash;
	}

	public void setValue7(String value7) {
		this.value7 = value7;
	}

	public int getValue5() {
		return value5;
	}

	public void setValue5(int value5) {
		this.value5 = value5;
	}

}
