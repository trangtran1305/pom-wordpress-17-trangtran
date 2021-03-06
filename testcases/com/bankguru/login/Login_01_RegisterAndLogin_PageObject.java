package com.bankguru.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.RegisterPageObject;

public class Login_01_RegisterAndLogin_PageObject {
	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	String userID;
	String password;
	String loginPageUrl;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4");
		loginPage = new LoginPageObject(driver);
		loginPageUrl = loginPage.getLoginPageUrl();

	}

	@Test
	public void TC_01_Register() {
		loginPage.clickToHereLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToEmailTextbox("johndeep" + random() + "@gmail.com");
		registerPage.clickToSubmitButton();
		userID = registerPage.getUserIDText();
		password = registerPage.getPasswordText();
		registerPage.openLoginPage(loginPageUrl);
		loginPage = new LoginPageObject(driver);
	}

	@Test
	public void TC_02_Login() {
		loginPage.inputToUserIdTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int random() {
		Random ran = new Random();
		return ran.nextInt(999999);
	}

}
