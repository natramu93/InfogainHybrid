package po;

import org.openqa.selenium.By;

public class DashboardPage {
	public static By username_label = By.xpath("//ul[@id='userdropdown']/li");
	public static By usericon = By.id("user_icon");
	public static By logout_menu = By.className("fa-sign-out-alt");
}
