package com.disney.alm.test;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.disney.utils.Xls_Reader;

public class TestClass {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GenerateExcelReport generate = new GenerateExcelReport();
		
		generate.generateExcel();
		
		Xls_Reader writeResults = new Xls_Reader("C:\\Users\\k.andapally\\ReportingBamboo\\ALM-SeleniumIntegration\\src\\test\\java\\com\\disney\\alm\\test\\ExecutionResults.xlsx");
		
		writeResults.setCellData("Test Results","TestCase Name", 2, "testname");
		writeResults.setCellData("Test Results","TestID", 2, "12345");
		writeResults.setCellData("Test Results","Result", 2, "Pass");

	}

}
