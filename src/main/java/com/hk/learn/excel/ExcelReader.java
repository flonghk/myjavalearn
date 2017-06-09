package com.hk.learn.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	Workbook wb;
	private Sheet sheet = null;
	private String excelPath = null;

	private final int elementSize = 7;
	private int elementRow = 1;
	private int dataStartRow = 2;
	private String descriptionRowColumn = null;
	private String ownerRowColumn = null;
	private String datasetRowColumn = null;

	public void setSheet(int sheetNumber) {
		sheet = wb.getSheetAt(sheetNumber);
	}

	public void setSheet(String sheetName) {
		sheet = wb.getSheet(sheetName);
	}

	public int getColumns() {
		return getColumnCount(elementRow);
	}

	public int getElementRow() {
		return elementRow;
	}

	public int getElementSize() {
		return elementSize;
	}

	public int getDataStartRow() {
		return dataStartRow;
	}

	/**
	 * 
	 * @function: do valueSet specified sheetName
	 * @param path
	 *            is excel file path
	 * @param sheetName
	 *            is sheetName in excel
	 * @throws FileNotFoundException
	 *             ,InvalidFormatException,IOException
	 **/
	public ExcelReader(String path, String sheetName)
			throws InvalidFormatException, IOException {
		try {
			InputStream inp = new FileInputStream(path);
			excelPath = path;
			Workbook tempWorkbook = WorkbookFactory.create(inp);
			sheet = tempWorkbook.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (InvalidFormatException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	public ExcelReader(String path, int sheetNumber)
			throws InvalidFormatException, IOException {
		try {
			InputStream inp = new FileInputStream(path);
			excelPath = path;
			Workbook tempWorkbook = WorkbookFactory.create(inp);
			sheet = tempWorkbook.getSheetAt(sheetNumber);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (InvalidFormatException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	public ExcelReader(String path) throws InvalidFormatException, IOException {
		try {
			InputStream inp = new FileInputStream(path);
			excelPath = path;
			wb = WorkbookFactory.create(inp);
			sheet = wb.getSheetAt(0);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (InvalidFormatException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	public int getSheetCount() {
		return wb.getNumberOfSheets();
	}

	public void getSheet(int index) {
		sheet = wb.getSheetAt(index);
	}

	public String getSheetName() {
		return sheet.getSheetName();
	}

	public String getDescription() {
		if (descriptionRowColumn != null) {
			String[] temp = descriptionRowColumn.split("/");
			int rowNum = Integer.parseInt(temp[0]);
			int columnNum = Integer.parseInt(temp[1]);

			return getCellValue(rowNum, columnNum);
		} else
			return null;
	}

	public String getDataSet() {
		if (datasetRowColumn != null) {
			String[] temp = datasetRowColumn.split("/");
			int rowNum = Integer.parseInt(temp[0]);
			int columnNum = Integer.parseInt(temp[1]);
			return getCellValue(rowNum, columnNum);
		} else
			return null;
	}

	public String getOwner() {
		if (ownerRowColumn != null) {
			String[] temp = ownerRowColumn.split("/");
			int rowNum = Integer.parseInt(temp[0]);
			int columnNum = Integer.parseInt(temp[1]);
			return getCellValue(rowNum, columnNum);
		} else {
			return null;
		}
	}

	public int getDataSetColumn() throws Exception {
		int columnSize = getColumnCount(elementRow);
		String datasetValue = getDataSet();
		for (int i = elementSize; i < columnSize; i++) {
			String tempValue = getCellValue(elementRow, i);
			if (datasetValue != null
					&& datasetValue.equals(beforeHandle(tempValue, "="))) {
				return i;
			}
		}
		return 0;
	}

	public int getDataSetColumn(String datasetName) throws Exception {
		int columnSize = getColumnCount(elementRow);
		if (datasetName.equals("")) {
			return elementSize - 1;
		} else {
			for (int i = elementSize; i < columnSize; i++) {
				String tempValue = getCellValue(elementRow, i);
				if (tempValue.contains("=")) {
					if (datasetName.equals(beforeHandle(tempValue, "="))) {
						return i;
					}
				} else {
					if (datasetName.equals(tempValue)) {
						return i;
					}
				}
			}
		}

		return 0;
	}

	public String getDataSetDescription() throws Exception {
		int columnSize = getColumnCount(elementRow);
		String datasetValue = getDataSet();
		for (int i = elementSize; i < columnSize; i++) {
			String tempValue = getCellValue(elementRow, i);
			if (datasetValue != null
					&& datasetValue.equals(beforeHandle(tempValue, "="))) {
				return afterHandle(tempValue, "=");
			}
		}
		return null;
	}

	public List<String> getAllElements() {
		List<String> allElements = new ArrayList<String>();

		for (int i = 0; i < elementSize; i++) {
			allElements.add(getCellValue(elementRow, i));
		}
		return allElements;
	}

	public String getCellValue(int rowNum, int columnNum) {
		Row tempRow = sheet.getRow(rowNum - 1);
		String cellValue = null;

		if (tempRow != null) {

			Cell cell = tempRow
					.getCell(columnNum - 1, Row.CREATE_NULL_AS_BLANK);

			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = Boolean.toString(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					cellValue = String.valueOf(cell.getDateCellValue());
				} else {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String temp = cell.getStringCellValue();
					if (temp.contains(".")) {
						cellValue = String.valueOf(new Double(temp)).trim();
					} else {
						cellValue = temp.trim();
					}
				}
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cellValue = cell.getStringCellValue();
				if (cellValue != null) {
					cellValue = cellValue.replaceAll("#N/A", "").trim();
				}
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = "";
				break;
			default:
				cellValue = "";
				break;
			}
		} else {
			cellValue = "";
		}

		return cellValue;
	}

	/**
	 * return the row count
	 */
	public int getRowCount() {

		return sheet.getLastRowNum() + 1;
	}

	

	/**
	 * return the column count
	 */
	public int getColumnCount(int rowNum) {
		Row row = sheet.getRow(rowNum - 1);
		if (row != null && row.getLastCellNum() > 0) {
			return row.getLastCellNum();
		}
		return 0;
	}

	/**
	 * get the data of a row
	 */
	public String[] getRowData(int rowIndex) {
		int columnNum = getColumnCount(rowIndex);
		String[] rowData = new String[columnNum];
		for (int i = 0; i < columnNum; i++) {
			rowData[i] = getCellValue(rowIndex, i);
		}
		return rowData;
	}

	private static String beforeHandle(String str, String seperate)
			throws Exception {
		try {
			int i = str.indexOf(seperate);
			return str.substring(0, i);
		} catch (Exception e) {
			return str;
			// throw new Exception("Str: " + str +
			// " do not contain description");
		}

	}

	private static String afterHandle(String str, String seperate)
			throws Exception {
		try {
			int i = str.indexOf(seperate);
			return str.substring(i + 1, str.length());
		} catch (Exception e) {
			return str;
			// throw new Exception("Str: " + str +
			// " do not contain description");
		}
	}

	public String getExcelPath() {
		return excelPath;
	}

	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}
}