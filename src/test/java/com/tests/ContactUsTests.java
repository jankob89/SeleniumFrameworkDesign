package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.ContactusPage;
import com.pages.HomePage;
import com.testcomponents.BaseTests;

public class ContactUsTests extends BaseTests {
	
	@Test
	public void sendQuestionWithFile() {
		final String submittedSuccessfullyMsg  = "Success! Your details have been submitted successfully.";
		HomePage homePage = openSite();
		ContactusPage contactusPage = homePage.goToContactusPage();
		contactusPage.enterEmail("test.mail@gmail.com");
		contactusPage.enterName("Name");
		contactusPage.enterSubject("Subject");
		contactusPage.enterMessage("Hello there!");
		contactusPage.uploadFile();
		contactusPage.cickSubmit();	
		Assert.assertEquals(contactusPage.getSubmittedSuccessfullyMsg(), submittedSuccessfullyMsg);
		contactusPage.clickHome();		
	}
}
