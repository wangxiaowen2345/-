package com.haojie.dao;

import java.util.List;

import com.haojie.pojo.Function;

public interface FunctionDao {
	
	/**
	 * �������ֲ�ѯFunction
	 * @return
	 */
	public Function SelectFunctionByName(String FunctionName);
	
	
	public List<Function> MenuFunction();
	
	
	public int SelectFunctionFid(String name);

}
