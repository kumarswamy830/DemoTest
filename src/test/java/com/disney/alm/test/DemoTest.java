package com.disney.alm.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;





import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.eviware.soapui.support.SoapUIException;

public class DemoTest {

	private WebDriver driver = null;
	private ALMTestResult alm = new ALMTestResult();
	private int testID = 0;
	private GenerateExcelReport generateExcelReport = new GenerateExcelReport();
	private XSSFWorkbook workbook = new XSSFWorkbook();
	private String result = null;
	private String testName = null;

	@BeforeTest(alwaysRun = true)
	public void openBrowser() {

		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();

	}

	@Test(alwaysRun = true)
	public void iD_12918_loginTest() {

		try {
			
			testName = new Object(){}.getClass().getEnclosingMethod().getName();

			driver.get(System.getProperty("baseUrl"));
			
			System.out.println("Naviagted to "+System.getProperty("baseUrl"));

			driver.findElement(By.className("signIn")).click();
			
			System.out.println("Clicked on sign in button");

			driver.findElement(By.id("loginPageUsernam")).sendKeys(
					"demoTestone@ngetestmail.com");

			driver.findElement(By.id("loginPagePassword")).sendKeys(
					"Autom@tion321");

			driver.findElement(By.id("loginPageSubmitButton")).click();

			WebDriverWait wait = new WebDriverWait(driver, 30);

			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.className("signOut")));

			result = "Passed";

		} catch (Exception ex) {

			result = "Failed";
			
			System.out.println("Failed reason: "+ex.getMessage());

			Assert.fail();

		}

	}

	
	@AfterMethod(alwaysRun = true)
	public void updateResult() throws XmlException, IOException,
			SoapUIException {
		
		driver.close();

		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(testName);
		while (m.find()) {
			testID = Integer.parseInt(m.group());
			break;
		}
		generateExcelReport.generateExcel();
		generateExcelReport.updateResult(testName,
				String.valueOf(testID), result);
		alm.updateALM();
	}

}
