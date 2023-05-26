package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactusPage extends BasePage {

	public ContactusPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().equals("Automation Exercise - Contact Us"))
			throw new IllegalStateException(
					"This is not Contact Us Page," + " current page is: " + driver.getCurrentUrl());
	}

	@FindBy(css = "[name='name']")
	WebElement txtName;

	@FindBy(css = "[name='email']")
	WebElement txtEmail;

	@FindBy(css = "[name='subject']")
	WebElement txtSubject;

	@FindBy(css = "[name='message']")
	WebElement txtMessage;

	@FindBy(css = "[name='submit']")
	WebElement btnSubmit;

	@FindBy(css = "[name='upload_file']")
	WebElement fudUploadFile;

	@FindBy(css = "div[class='status alert alert-success']")
	WebElement lblSubmittedSuccessfullyMsg;

	@FindBy(css = "a[class*='btn-success']")
	WebElement btnHome;

	public void enterName(String name) {
		txtName.sendKeys(name);
	}

	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void enterSubject(String subject) {
		txtSubject.sendKeys(subject);
	}

	public void enterMessage(String message) {
		txtMessage.sendKeys(message);
	}

	public void cickSubmit() {
		btnSubmit.click();
		driver.switchTo().alert().accept();
	}

	public void uploadFile() {
		String path = System.getProperty("user.dir") + "\\files\\test_file.txt";
		fudUploadFile.sendKeys(path);
	}

	public String getSubmittedSuccessfullyMsg() {
		waitUntilVisibilityOf(lblSubmittedSuccessfullyMsg);
		return lblSubmittedSuccessfullyMsg.getText();
	}

	public void clickHome() {
		btnHome.click();
	}
}
