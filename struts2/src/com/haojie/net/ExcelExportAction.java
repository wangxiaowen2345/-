package com.haojie.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import com.haojie.pojo.PrintOut;
import com.opensymphony.xwork2.ActionSupport;

public class ExcelExportAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5408623744535587961L;
	private PrintOut p;

	// private String[] headers;
	// private String title;
	// private String other;;
	// private List<Object[]> dataList = new ArrayList<Object[]>();

	/** ����Excel���� */
	public String exportExcel() {
		try {
			String title = p.getTitle();
			String other = p.getOther();
			String[] header = p.getHeaders();
			List<Object[]> dataList = p.getDataList();
			String s = header[0];
			String a[] = s.split(",");
			System.out.println(dataList);
			System.out.println(dataList.size());

			System.out.println("=======��ʼ���ñ��style======");
			// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
			HSSFWorkbook wb = new HSSFWorkbook();
			// �ڶ�������webbook�����һ��sheet����ӦExcel�ļ��е� sheet
			HSSFSheet sheet = wb.createSheet("���");
			// ����������sheet����ӱ�ͷ��0�У�ע���ϰ汾poi��Excel����������������
			HSSFRow row = sheet.createRow(0);

			// ���Ĳ���������Ԫ����ʽ������
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			System.out.println("=======������ͷ��Ԫ�񣬲�������ʽ======");// ���岽��������ͷ��Ԫ�񣬲�������ʽ
			HSSFCell cell, cell1, cell2;

			cell = row.createCell(0);
			cell.setCellValue(title);
			cell.setCellStyle(style);
			// cell = row.createCell(1);
			// cell.setCellValue("1");
			// cell = row.createCell(2);
			// cell.setCellValue("");
			// cell = row.createCell(3);
			// cell.setCellValue("");
			// cell = row.createCell(4);
			// cell.setCellValue("");
			// cell = row.createCell(5);
			// cell.setCellValue("");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, a.length - 1));

			row = sheet.createRow(1);
			cell1 = row.createCell(0);
			cell1.setCellValue(other);
			cell1.setCellStyle(style);
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, a.length - 1));

			row = sheet.createRow(2);
			for (int i = 0; i < a.length; i++) {
				cell2 = row.createCell(i);
				cell2.setCellValue(a[i]);
				cell2.setCellStyle(style);
			}

			System.out.println("=======д��ʵ������======");// ��������д��ʵ�����ݣ�ʵ��Ӧ������Щ���ݴ����ݿ�õ�
			// Iterator it = dataList.iterator();
			// while (it.hasNext()) {
			// System.out.println(it.next());
			// Object[] ob=(Object[])it.get(i)
			// }
			// for (int i = 0; i < dataList.size(); i++) {
			// System.out.println(dataList.get(i));
			//
			// }
			// ���ַ�������ָ�
			String b[] = null;
			int c = 0;
			for (Object obj : dataList) {
				System.out.println(obj);
				b = obj.toString().split(",");
				System.out.println(b.length);
			}
			// �����ά�������
			int len = b.length / a.length;
			String[][] temp = new String[len][a.length];
			// ����ά���鸳ֵ
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < a.length; j++, c++) {
					temp[i][j] = b[c];
				}
			}
			// JSONArray arr = JSONArray.fromObject(dataList.get(0));
			// for (Object o : arr) {
			// JSONArray aa = (JSONArray) o;
			// for (int i = 0; i < aa.size(); i++) {
			// String j = (String) aa.get(i);
			// System.out.println(j);
			// }
			// }
			for (int i = 3; i < (len + 3); i++) {
				row = sheet.createRow(i);
				for (int j = 0; j < a.length; j++) {

					row.createCell(j).setCellValue(temp[i - 3][j]);
				}
			}

			System.out.println("=======���ļ��浽����======");// ���߲������ļ��浽����
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] fileContent = os.toByteArray();
			ByteArrayInputStream is = new ByteArrayInputStream(fileContent);

			excelStream = is; // �ļ���
			excelFileName = "report.xls"; // �������ص��ļ���
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=======���======");
		// HttpServletResponse response = ServletActionContext.getResponse();
		// response.setContentType("text/html;charset=utf-8");
		// PrintWriter out = response.getWriter();
		// out.print("success");
		return "success";
	}

	// -------------------------------------------------------------
	private InputStream excelStream; // ���������
	private String excelFileName; // �����ļ���

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public void setP(PrintOut p) {
		this.p = p;
	}

	public PrintOut getP() {
		return p;
	}
}