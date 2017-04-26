package com.disney.alm.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.disney.utils.Base64Coder;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;


public class ALMTestResultUpdate {

	/*
	 * This test update the ALM test result status based on the excel sheet test result
	 *  from bamboo test script execution
	 */
	@Test
	public void soapUI_ALM_TestIntegration_Service() throws Exception {

		Status returnStatus = null;
		Status failedStatus = null;
		String serviceInTest = "ALM_Rest_Project.xml";
		List<String> list = new ArrayList<String>();

		// set up the path to the test
		String filePath = "src/test/soapui/" + serviceInTest;

		// Create a new WSDL project
		WsdlProject project = new WsdlProject(filePath);

		// Send variable to Soap Project
		project.setPropertyValue("Username", System.getProperty("username"));
		project.setPropertyValue("Password",System.getProperty("password"));
		project.setPropertyValue("ExcelPath",System.getProperty("excelpath"));
		project.setPropertyValue("Project", System.getProperty("brand"));
		project.setPropertyValue("TestSetID", System.getProperty("testsetid"));

		// create empty properties and run synchronously
		TestRunner runner = project.run(new PropertiesMap(), false);

		System.out.println("Test Status = " + runner.getStatus());

		// If we get a failure, set the flag and add the failed test to a list
		if (TestRunner.Status.FAILED == runner.getStatus()) {
			failedStatus = TestRunner.Status.FAILED;
			list.add(serviceInTest);
		} else {
			// otherwise set then return status to Pass
			returnStatus = TestRunner.Status.FINISHED;
		}

		// if there was any failed test(s), print the list of tests
		if (failedStatus == TestRunner.Status.FAILED) {
			System.out.println("Failed Services include the following:");
			for (String temp : list) {
				System.out.println("     " + temp);
			}
			// Fail the suite for any failed test
			Assert.assertEquals(TestRunner.Status.FINISHED, failedStatus);
		} else {
			// Else, Pass the entire suite
			Assert.assertEquals(TestRunner.Status.FINISHED, returnStatus);

		}
	}
}
