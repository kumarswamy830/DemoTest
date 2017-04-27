package com.disney.alm.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.disney.utils.Xls_Reader;

public class GenerateExcelReport {
	
	private XSSFWorkbook workbook = new XSSFWorkbook();

	public  void   generateExcel() {
		
		try {
			
			FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+"\\DemoTest\\src\\test\\java\\com\\disney\\alm\\test\\ExecutionResults.xlsx");
			workbook = new XSSFWorkbook();
			XSSFSheet worksheet = workbook.createSheet("TestResults");

			// index from 0,0... cell A1 is cell(0,0)
			XSSFRow row1 = worksheet.createRow((short) 0);

			XSSFCell cellA1 = row1.createCell((short) 0);
			cellA1.setCellValue("TestCase Name");
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			XSSFFont xSSFFont = workbook.createFont();
			xSSFFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setFont(xSSFFont);
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setBorderBottom(BorderStyle.THIN);
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderRight(BorderStyle.THIN);
			cellA1.setCellStyle(cellStyle);
			

			XSSFCell cellB1 = row1.createCell((short) 1);
			cellB1.setCellValue("TestID");
			cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
			cellB1.setCellStyle(cellStyle);
			
			XSSFCell cellC1 = row1.createCell((short) 2);
			cellC1.setCellValue("Result");
			cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
			cellC1.setCellStyle(cellStyle);
			
			GenerateExcelReport testClass = new GenerateExcelReport();
			testClass.autoSizeColumns(workbook);
			


			workbook.write(fileOut);
			
			fileOut.flush();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	
	public void autoSizeColumns(XSSFWorkbook workbook) {
	    int numberOfSheets = workbook.getNumberOfSheets();
	    for (int i = 0; i < numberOfSheets; i++) {
	        Sheet sheet = workbook.getSheetAt(i);
	        if (sheet.getPhysicalNumberOfRows() > 0) {
	            Row row = sheet.getRow(0);
	            Iterator<Cell> cellIterator = row.cellIterator();
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                int columnIndex = cell.getColumnIndex();
	                sheet.autoSizeColumn(columnIndex);
	            }
	        }
	    }
	}
	
	public void updateResult(String testName,String testID,String result){
		
        Xls_Reader writeResults = new Xls_Reader(System.getProperty("user.dir")+"\\DemoTest\\src\\test\\java\\com\\disney\\alm\\test\\ExecutionResults.xlsx");
		
		writeResults.setCellData("TestResults","TestCase Name", 2, testName);
		writeResults.setCellData("TestResults","TestID", 2, testID);
		writeResults.setCellData("TestResults","Result", 2, result);
	}

}
