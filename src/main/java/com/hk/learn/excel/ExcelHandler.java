package com.hk.learn.excel;

import java.util.List;


public interface ExcelHandler {
	
	public List runCaseByFolder(String Path) throws Exception;
	
	public List transferExcel(String excelPath) throws Exception;
	
	public List transferExcel(String excelPath, int sheetNo) throws Exception;
	
	public List transferExcel(String excelPath, String sheetName) throws Exception;
	
	public List transferExcel(String excelPath, int startNo,int endNo) throws Exception;
	
}
