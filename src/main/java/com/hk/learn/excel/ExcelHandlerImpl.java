package com.hk.learn.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.poi.hssf.record.formula.functions.Replace;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelHandlerImpl implements ExcelHandler {

	private static int StartNum = 1;
	String excelPath;
	static ExcelReader excelReader;
	private static  int StartSheetNum = 0;
	private int GAPCOUNT = 1;

	private static int APINameNum;
	private static int DescriptionNum;
	private static int MethodNum;
	private static int InterfaceName;
	private static int RequestParameter;
	private static int BodyNum;
	private static int JsonParameterNum;
	private static int CheckPointNum;
	private static int ResultNum;

	//public List transferExcel(String excelPath) throws Exception {
	public List<HashMap<String, String>> transferExcel(String excelPath) throws Exception {
		this.excelPath = excelPath;

		List<HashMap<String, String>> caseList = new ArrayList<HashMap<String, String>>();
		
		excelReader = new ExcelReader(excelPath);
		File file = new File(excelPath);
		String excelName = file.getName();
		System.out.println(excelName);
		StartSheetNum = 0;
//		System.out.println(StartSheetNum);
	//	System.out.println(excelReader.getSheetCount());
		getRowCount();
		getColumnCount();
		for (int sheetNumber = StartSheetNum; sheetNumber < excelReader.getSheetCount(); sheetNumber++) 
		{
			excelReader.setSheet(sheetNumber);
			if (excelReader.getRowCount() <= 1) {

				continue;
			}
		//	setKeyNum();
			String sheetName = excelReader.getSheetName();
			Sheet sheet = excelReader.wb.getSheetAt(sheetNumber);
			System.out.println(sheetName);
			caseList = getAllCaseDetail(sheet);
			//testExcel.setTeamName(excelReader.getCellValue(2, 1));
			//testExcel.setCaseOwner(excelReader.getCellValue(2, 2));
			//testExcel.setAPIType("ExcelName: " + excelName + " " + "SheetName: " + sheetName);
			//testExcel.setAPICasesList(getAllCaseDetails(sheet));
			//caseExeclsList.add(testExcel);
		}

		return caseList;
	}

	public List runCaseByFolder(String rootFolderPath) throws Exception {

		File filesDir = new File(rootFolderPath);

		List caseExeclsList = new ArrayList<>();
		if (filesDir.isDirectory()) {
			File[] files = filesDir.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (!files[i].getName().startsWith("~$")) {
					caseExeclsList.addAll(transferExcel(files[i].getPath()));
				}
			}

		} else {
			if (filesDir.getName().startsWith("~$")) {
			} else {
				if (filesDir.getName().endsWith("xls") || filesDir.getName().endsWith("xlsx")
						|| filesDir.getName().endsWith("xlsm")) {

					caseExeclsList = transferExcel(rootFolderPath);

				}
			}
		}
		return caseExeclsList;

	}

	public void setKeyNum() {
		for (int rowNum = 1; rowNum < excelReader.getRowCount(); rowNum++) {
			 String startSlobe=excelReader.getCellValue(rowNum, 1);
			 if (startSlobe.equalsIgnoreCase("APIServerUrl")) {
					StartNum = rowNum;
					break;
				}
		}
      
		
		for (int columnNum = 1; columnNum <= excelReader.getColumnCount(StartNum); columnNum++) {

			String valueString = excelReader.getCellValue(StartNum, columnNum);
			if (valueString.equalsIgnoreCase("APIServerUrl")) {
				APINameNum = columnNum;
				continue;
			}
		}

	}

	// return specified caseNo caseDetail
	public HashMap<String, String> getCase(int caseNo) throws Exception, IOException {

		HashMap<String, String> caseDetail = new HashMap<String, String>();

		if (caseNo > excelReader.getRowCount() - 2) {
			throw new Exception("Out of boundry of caseRow Count, CaseNo: " + caseNo + ", RowCount "
					+ excelReader.getRowCount());
		} else {
			for (int column = 1; column <= excelReader.getColumnCount(1); column++) {

				caseDetail.put(excelReader.getCellValue(1, column), excelReader.getCellValue(caseNo + GAPCOUNT, column)
						.trim());
			}
		}

		return caseDetail;
	}

	// return specified rang caseDetail
	public List<HashMap<String, String>> getCase(int startNo, int endNo) throws Exception, IOException {
		List<HashMap<String, String>> caseList = new ArrayList<HashMap<String, String>>();

		for (int rowNo = startNo + GAPCOUNT; rowNo <= endNo + GAPCOUNT; rowNo++) {
			HashMap<String, String> caseDetail = new HashMap<String, String>();
			for (int column = 1; column <= excelReader.getColumnCount(1); column++) {

				caseDetail.put(excelReader.getCellValue(1, column), excelReader.getCellValue(rowNo, column).trim());
			}

			caseList.add(caseDetail);
		}
		return caseList;
	}

	// return all caseDetails
	public List getAllCaseDetails(Sheet sheet) throws Exception, IOException {

		List caseList = new ArrayList<>();

		for (int rowNo = StartNum + 1; rowNo <= excelReader.getRowCount(); rowNo++) {
			//APICases caseDetail = new APICases();
			// API url
			boolean isMerge = ReadMergeRegionExcel.isMergedRegion(sheet, rowNo - 1, APINameNum - 1);
			String apiUrl = "";
			if (isMerge) {
				apiUrl = ReadMergeRegionExcel.getMergedRegionValue(sheet, rowNo - 1, APINameNum - 1);

			} else {
				apiUrl = excelReader.getCellValue(rowNo, APINameNum).trim();
			}
			// 跟换环境URL
			// apiUrl=changeEvnUrl(apiUrl);
			//caseDetail.setApiName(apiUrl);
			// 描述
			isMerge = ReadMergeRegionExcel.isMergedRegion(sheet, rowNo - 1, DescriptionNum - 1);
			if (isMerge) {
				String rs = ReadMergeRegionExcel.getMergedRegionValue(sheet, rowNo - 1, DescriptionNum - 1);
				//caseDetail.setDescription(rs);
			} else {
				//caseDetail.setDescription(excelReader.getCellValue(rowNo, DescriptionNum));
			}
			// 接口
			isMerge = ReadMergeRegionExcel.isMergedRegion(sheet, rowNo - 1, InterfaceName - 1);
			if (isMerge) {
				String rs = ReadMergeRegionExcel.getMergedRegionValue(sheet, rowNo - 1, InterfaceName - 1);
				//caseDetail.setInterfaceName(rs);
			} else {
				//caseDetail.setInterfaceName(excelReader.getCellValue(rowNo, InterfaceName));
			}
			// 方法名
			isMerge = ReadMergeRegionExcel.isMergedRegion(sheet, rowNo - 1, MethodNum - 1);
			if (isMerge) {
				String rs = ReadMergeRegionExcel.getMergedRegionValue(sheet, rowNo - 1, MethodNum - 1);
				//caseDetail.setMethod(rs);
			} else {
				//caseDetail.setMethod(excelReader.getCellValue(rowNo, MethodNum));
			}

			//caseDetail.setReqParameters(excelReader.getCellValue(rowNo, RequestParameter));
			// body
			isMerge = ReadMergeRegionExcel.isMergedRegion(sheet, rowNo - 1, BodyNum - 1);
			if (isMerge) {
				String rs = ReadMergeRegionExcel.getMergedRegionValue(sheet, rowNo - 1, BodyNum - 1);
				//caseDetail.setBody(rs);
			} else {
				//caseDetail.setBody(excelReader.getCellValue(rowNo, BodyNum));
			}
			//caseDetail.setJsonParameter(ReplaceChineseChar(excelReader.getCellValue(rowNo, JsonParameterNum)));
			//caseDetail.setCheckPoint(ReplaceChineseChar(excelReader.getCellValue(rowNo, CheckPointNum)));
			//caseDetail.setResult(excelReader.getCellValue(rowNo, ResultNum));
			//caseList.add(caseDetail);
		}

		return caseList;
	}
	/*
public String ReplaceChineseChar(String str){
	String result = str.replace("：", ":").replace("（", "(").replace("）", ")").replace("，", ",").replace("“", "\"").replace("！", "!");
	return result;
}
	public String changeEvnUrl(String currentUrl) throws IOException, Exception {
		String envUrlkey = Config.getConfig("Env");
		String envUrlString = Config.getConfig(envUrlkey);

		String patternString = "(http|https)://.*?/+";
		Pattern pattern = Pattern.compile(patternString);
		if (!envUrlString.endsWith("/")) {
			envUrlString += "/";
		}
		Matcher matcher = pattern.matcher(envUrlString);
		Matcher matcher1 = pattern.matcher(currentUrl);
		String replaceDomain = "";
		if (matcher.find()) {
			System.out.println("domain匹配结果： " + matcher.group());
			replaceDomain = matcher.group();
		}
		if (matcher1.find()) {
			System.out.println("匹配结果： " + matcher1.group());

		}
		currentUrl = currentUrl.replace(matcher1.group(), replaceDomain);
		return currentUrl;
	}
*/
	public static void main(String[] args) throws Exception {

		String path = "D:/hkjmeter/FlightNotifyRequest.xlsx";

		List testExcels = new ExcelHandlerImpl().transferExcel(path);
		getRowCount();
		getColumnCount();
		List<String> columnNameList = getColumnName();
		List<HashMap<String, String>> caseList = getAllCaseDetail(excelReader.wb.getSheetAt(0));
		for (int i = 0; i < caseList.size(); i++)
		{
			System.out.println(caseList.get(i));
			  for (Map.Entry<String, String> entry : caseList.get(i).entrySet()) {
				   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
				  }
		}
		//System.out.println(testExcels.get(0));

	}

	// get row number
	public static void getRowCount()
	{
		System.out.println("行：" + excelReader.getRowCount());
	}
	
	//第一行的列数
	public static void getColumnCount()
	{
		System.out.println("列：" + excelReader.getColumnCount(1));
	}
	//第一行的列名
	public static List<String> getColumnName()
	{
		List<String> list = new ArrayList<String>();
		
		for (int column = 1; column <= excelReader.getColumnCount(1); column++)
		{
			//System.out.println(excelReader.getCellValue(1, column).trim());
			list.add(excelReader.getCellValue(1, column).trim());
		}
		return list;
	}
	
	/*
	public List<HashMap<String, String>> getCaseAll(int startNo, int endNo) throws Exception, IOException {
		List<HashMap<String, String>> caseList = new ArrayList<HashMap<String, String>>();

		for (int rowNo = startNo + GAPCOUNT; rowNo <= endNo + GAPCOUNT; rowNo++) {
			HashMap<String, String> caseDetail = new HashMap<String, String>();
			for (int column = 1; column <= excelReader.getColumnCount(1); column++) {

				caseDetail.put(excelReader.getCellValue(1, column), excelReader.getCellValue(rowNo, column).trim());
			}

			caseList.add(caseDetail);
		}
		return caseList;
	}
	*/
	// return all caseDetails
	public static List<HashMap<String, String>> getAllCaseDetail(Sheet sheet) throws Exception, IOException 
	{

		List<HashMap<String, String>> caseList = new ArrayList<HashMap<String, String>>();

		for (int rowNo = 1; rowNo <= excelReader.getRowCount(); rowNo++) 
		{
			HashMap<String, String> caseDetail = new HashMap<String, String>();
			for (int column = 1; column <= excelReader.getColumnCount(1); column++) {

				caseDetail.put(excelReader.getCellValue(1, column), excelReader.getCellValue(rowNo, column).trim());
			}

			caseList.add(caseDetail);

			//String apiUrl = "";
			//apiUrl = excelReader.getCellValue(rowNo, 1).trim();
			//System.out.println(apiUrl);

		}

		return caseList;
	}
	
	public boolean isMergedRegion(Sheet sheet, int row, int column) {

		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {

			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	public List transferExcel(String excelPath, int SheetNo) throws Exception {
		this.excelPath = excelPath;

		List caseExeclsList = new ArrayList<>();

		excelReader = new ExcelReader(excelPath);
		String excelName = excelPath.substring(excelPath.lastIndexOf("/") + 1, excelPath.lastIndexOf("."));
		// setKeyNum();

		//CaseExcel testExcel = new CaseExcel();
		//setKeyNum();
		//excelReader.setSheet(SheetNo);
		//String sheetName = excelReader.getSheetName();
		//Sheet sheet = excelReader.wb.getSheetAt(SheetNo);
		//testExcel.setTeamName(excelReader.getCellValue(2, 1));
		//testExcel.setCaseOwner(excelReader.getCellValue(2, 2));
		//testExcel.setAPIType("ExcelName: " + excelName + " " + "SheetName: " + sheetName);
		//testExcel.setAPICasesList(getAllCaseDetails(sheet));
		//caseExeclsList.add(testExcel);

		return caseExeclsList;
	}

	public List transferExcel(String excelPath, String SheetName) throws Exception {
		this.excelPath = excelPath;

		List caseExeclsList = new ArrayList<>();

		excelReader = new ExcelReader(excelPath);
		String excelName = excelPath.substring(excelPath.lastIndexOf("/") + 1, excelPath.lastIndexOf("."));
		// setKeyNum();
/*
		CaseExcel testExcel = new CaseExcel();
		setKeyNum();
		excelReader.setSheet(SheetName);
		String sheetName = excelReader.getSheetName();
		Sheet sheet = excelReader.wb.getSheet(SheetName);
		testExcel.setTeamName(excelReader.getCellValue(2, 1));
		testExcel.setCaseOwner(excelReader.getCellValue(2, 2));
		testExcel.setAPIType("ExcelName: " + excelName + " " + "SheetName: " + sheetName);
		testExcel.setAPICasesList(getAllCaseDetails(sheet));
		caseExeclsList.add(testExcel);
*/
		return caseExeclsList;
	}

	public List transferExcel(String excelPath, int startSheetNo, int endSheetNo) throws Exception {
		this.excelPath = excelPath;

		List caseExeclsList = new ArrayList<>();
		/*
		excelReader = new ExcelReader(excelPath);
		String excelName = excelPath.substring(excelPath.lastIndexOf("/") + 1, excelPath.lastIndexOf("."));
		// setKeyNum();
		for (int sheetNumber = startSheetNo; sheetNumber < endSheetNo; sheetNumber++) {
			CaseExcel testExcel = new CaseExcel();
			setKeyNum();
			excelReader.setSheet(sheetNumber);
			String sheetName = excelReader.getSheetName();
			Sheet sheet = excelReader.wb.getSheetAt(sheetNumber);
			testExcel.setTeamName(excelReader.getCellValue(2, 1));
			testExcel.setCaseOwner(excelReader.getCellValue(2, 2));
			testExcel.setAPIType("ExcelName: " + excelName + " " + "SheetName: " + sheetName);
			testExcel.setAPICasesList(getAllCaseDetails(sheet));
			caseExeclsList.add(testExcel);
		}
*/
		return caseExeclsList;
	}

}
