package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import po.DashboardPage;
import po.LoginPage;
import utils.ExcelUtils;
import utils.WebDriverUtils;

public class OpenEMR {
	WebDriverUtils wdu;
	@BeforeSuite
	public void suiteSetup() {
		wdu = new WebDriverUtils();
		wdu.initializeBrowser("gc");
	}
	
	@BeforeMethod
	public void testSetup(Method method) {
		wdu.test = wdu.report.createTest(method.getName());
	}
	
	@Test(dataProvider="loginData")
	public void login(String id, String pwd, String user) {
		wdu.loadUrl("https://demo.openemr.io/openemr");
		wdu.type(LoginPage.username_ip, id);
		wdu.type(LoginPage.pwd_ip, pwd);
		wdu.click(LoginPage.login_btn);
		wdu.mouseOver(DashboardPage.usericon);
		wdu.validateText(DashboardPage.username_label, user);
		wdu.click(DashboardPage.logout_menu);
	}
	
	@DataProvider
	public Object[][] loginData() throws FileNotFoundException, IOException {
		return ExcelUtils.readExcel("Sheet1");
	}
	
	@AfterSuite
	public void suiteTearDown() {
		wdu.quit();
	}
}
