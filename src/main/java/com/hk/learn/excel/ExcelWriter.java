package com.hk.learn.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelWriter {
	Workbook wb ;
	//=new XSSFWorkbook();
	private Sheet sheet = null;
	
	public void CreateExcelFile(String fullFilePath,String sheetName,List<HashMap<String, String>> value) throws IOException
    {	 
    	File file=new File(fullFilePath);

    	//sheet = wb.createSheet();	
    	//判断文件是否存在
    	if(file.exists())
    	{
    		FileInputStream fs=new FileInputStream(fullFilePath);
    		POIFSFileSystem ps=new POIFSFileSystem(fs);

    		//sheet =wb.getSheet(sheetName);
    		wb = new XSSFWorkbook();
    		sheet = wb.createSheet(sheetName);
    	}
    	else
    	{
    		//sheet =wb.getSheet(sheetName);
    		wb = new XSSFWorkbook();
    		sheet = wb.createSheet(sheetName);
    	//initialRowNum=0;
    	}
    	                          // 设置excel每列宽度
        sheet.setColumnWidth(0, 2500);
        sheet.setColumnWidth(1, 2500);
        sheet.setColumnWidth(2, 2500);
        sheet.setColumnWidth(3, 2500);
        sheet.setColumnWidth(4, 2500);
        sheet.setColumnWidth(5, 2500);
        sheet.setColumnWidth(6, 2500);
        for(int i = 0; i < value.size();i++)
        {
        	Row row=sheet.createRow(i);

        	Cell cellcasetitle = row.createCell(0);  
        	cellcasetitle.setCellValue(value.get(i).get("casetitle"));
        	Cell cellcasenumber = row.createCell(1);  
        	cellcasenumber.setCellValue(value.get(i).get("casenumber"));	
        	Cell cellproducerType = row.createCell(2);  
        	cellproducerType.setCellValue(value.get(i).get("producerType"));
        	Cell cellorderId = row.createCell(3);        
        	cellorderId.setCellValue(value.get(i).get("orderId"));
        	Cell cellfirstCount = row.createCell(4);  
        	cellfirstCount.setCellValue(value.get(i).get("firstCount"));
        	Cell cellnextcount = row.createCell(5);  
        	cellnextcount.setCellValue(value.get(i).get("nextCount"));
        	//System.out.println(value.get(i).size());
        }
        
        // 创建Excel的sheet的一行
       // Row row=sheet.createRow(0);
       // Cell cell=row.createCell(0); 
       // cell.setCellValue("casetitle");
       // Cell cell1=row.createCell(1); 
       // cell1.setCellValue("casetitle");
        /*
        row=sheet.createRow((short)(sheet.getLastRowNum())); 
        row.setHeight((short) 400);// 设定行的高度


        for(int i=0; i<value.size();i++ )
        {
            // 给Excel的单元格设置样式和赋值
            //String cellValue=value.get(i).toString();
            //HSSFCellStyle style=getStyle(styleType,cellValue,wb);
            Cell cell=row.createCell(i); 
            cell.setCellValue(value.get(i).get("casetitle"));
            //cell.setCellStyle(style);
           
         }
        */
         FileOutputStream os = new FileOutputStream(fullFilePath);
         os.flush();
         wb.write(os);
         os.close();
    }
	
	public void CreateExcelFileByCustom(String fullFilePath,String sheetName,List<HashMap<String, String>> value) throws IOException
    {	 
    	File file=new File(fullFilePath);

    	//sheet = wb.createSheet();	
    	//判断文件是否存在
    	if(file.exists())
    	{
    		FileInputStream fs=new FileInputStream(fullFilePath);
    		POIFSFileSystem ps=new POIFSFileSystem(fs);

    		//sheet =wb.getSheet(sheetName);
    		wb = new XSSFWorkbook();
    		sheet = wb.createSheet(sheetName);
    	}
    	else
    	{
    		//sheet =wb.getSheet(sheetName);
    		wb = new XSSFWorkbook();
    		sheet = wb.createSheet(sheetName);
    	//initialRowNum=0;
    	}
    	                          // 设置excel每列宽度
        sheet.setColumnWidth(0, 2500);
        if (value.size() > 0)
        {
        	//int i = 0;
        	
        	for (int i = 0; i < value.size(); i++)
        	{
        		Row row=sheet.createRow(i);
        		HashMap map = value.get(i);
        		Set set = map.keySet();
        		int j = 0;
        		for(Iterator iter = set.iterator(); iter.hasNext();)
        		{
        			sheet.setColumnWidth(i, 4000);
        			Cell cell = row.createCell(j);  
        			//Map.Entry entry = (Map.Entry)iter.next();
			   
        			String key = (String)iter.next();
        			String value1 = (String)map.get(key);
        			System.out.println(key +" :" + value1);
        			if (i == 0)
        			{
        				cell.setCellValue(key);
        			}
        			if (i > 0)
        			{
        				cell.setCellValue(value1);
        			}
        			j++;
        		}
        	}
			  /*
        	Collection objs = value.get(0).entrySet();
        	int i = 0;
        	for (Iterator iterator=objs.iterator(); iterator.hasNext();)
        	{
        		
        		
        		Row row=sheet.createRow(0);
        		Object obj = iterator.next();
            	Cell cell = row.createCell(i);  
            	cell.setCellValue(obj.toString());
            	i = i + 1;
        		System.out.println(obj);
        	}
        	*/
        }
        /*
        for(int i = 0; i < value.size();i++)
        {
        	Row row=sheet.createRow(i);

        	Cell cellcasetitle = row.createCell(0);  
        	cellcasetitle.setCellValue(value.get(i).get("casetitle"));
        	Cell cellcasenumber = row.createCell(1);  
        	cellcasenumber.setCellValue(value.get(i).get("casenumber"));	
        	Cell cellproducerType = row.createCell(2);  
        	cellproducerType.setCellValue(value.get(i).get("producerType"));
        	Cell cellorderId = row.createCell(3);        
        	cellorderId.setCellValue(value.get(i).get("orderId"));
        	Cell cellfirstCount = row.createCell(4);  
        	cellfirstCount.setCellValue(value.get(i).get("firstCount"));
        	Cell cellnextcount = row.createCell(5);  
        	cellnextcount.setCellValue(value.get(i).get("nextCount"));
        	//System.out.println(value.get(i).size());
        }
        */

         FileOutputStream os = new FileOutputStream(fullFilePath);
         os.flush();
         wb.write(os);
         os.close();
    }

	public void CreateExcelFileFromOrderAuthHandleDetail(String fullFilePath, String sheetName,List<OrderAuthHandleDetail> orderAuthHandleDetailList) throws IOException {
		// TODO Auto-generated method stub
    	File file=new File(fullFilePath);

    	//sheet = wb.createSheet();	
    	//判断文件是否存在
    	if(file.exists())
    	{
    		FileInputStream fs=new FileInputStream(fullFilePath);
    		POIFSFileSystem ps=new POIFSFileSystem(fs);

    		//sheet =wb.getSheet(sheetName);
    		wb = new XSSFWorkbook();
    		sheet = wb.createSheet(sheetName);
    	}
    	else
    	{
    		//sheet =wb.getSheet(sheetName);
    		wb = new XSSFWorkbook();
    		sheet = wb.createSheet(sheetName);
    	//initialRowNum=0;
    	}
    	                          // 设置excel每列宽度
        sheet.setColumnWidth(0, 2500);
        if (orderAuthHandleDetailList.size() > 0)
        {
        	//int i = 0;
        	
        	for (int i = 0; i < orderAuthHandleDetailList.size(); i++)
        	{
            	Row row=sheet.createRow(i);

            	Cell cellcasetitle = row.createCell(0);  
            	cellcasetitle.setCellValue(orderAuthHandleDetailList.get(i).getCaseTitle());
            	Cell cellBusinessId = row.createCell(1);  
            	cellBusinessId.setCellValue(orderAuthHandleDetailList.get(i).getBusinessId());	
            	Cell cellMsg = row.createCell(2);  
            	cellMsg.setCellValue(orderAuthHandleDetailList.get(i).getMsg());
        	}

         FileOutputStream os = new FileOutputStream(fullFilePath);
         os.flush();
         wb.write(os);
         os.close();
        }
	}
	
}
