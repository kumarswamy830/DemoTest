package com.disney.alm.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.disney.utils.Base64Coder;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;
import com.eviware.soapui.support.SoapUIException;
import com.eviware.soapui.tools.SoapUITestCaseRunner;

public class ALMTestResult {

	/*
	 * This test update the ALM test result status based on the excel sheet test result
	 *  from bamboo test script execution
	 *  
	 */
	
//	public static String userName = null;
//	public static String passWord = null;
//	public static String excelPath = null;
//	public static String domain = null;
//	public static String brand = null;
//	public static String testSetID = null;

	
//	public static void main(String[] args) throws Exception {
	
	public void updateALM() throws XmlException, IOException, SoapUIException{

//		Status returnStatus = null;
//		Status failedStatus = null;
		String serviceInTest = "ALM_Rest_Project.xml";
		List<String> list = new ArrayList<String>();
		
		
		// set up the path to the test
		String filePath = "src/test/soapui/" + serviceInTest;
		
		GetUserInput getUserInput = new GetUserInput();
	//	getUserInput.readUserInput();
		
		// Create a new WSDL project
		WsdlProject project = new WsdlProject(filePath);

		// Send variable to Soap Project
		project.setPropertyValue("Username", System.getProperty("username"));
		project.setPropertyValue("Password",System.getProperty("password"));
		project.setPropertyValue("ExcelPath",System.getProperty("excelPath"));
		project.setPropertyValue("Domain", System.getProperty("domain"));
		project.setPropertyValue("Project", System.getProperty("project"));
		project.setPropertyValue("TestSetID", System.getProperty("testsetid"));

		// create empty properties and run synchronously
		TestRunner runner = project.run(new PropertiesMap(), false);

		System.out.println("Test Status = " + runner.getStatus());

		
	}
	
}
