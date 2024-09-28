import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.waitForElementVisible(findTestObject('Object Repository/header-text/Book Store Application-header'), GlobalVariable.timeout, FailureHandling.STOP_ON_FAILURE)
WebUI.scrollToElement(findTestObject('Object Repository/header-text/Book Store Application-header'), GlobalVariable.timeout, FailureHandling.STOP_ON_FAILURE)
if (WebUI.verifyElementNotVisible(findTestObject('Object Repository/menu-list/Book Store Application/Book Store'), FailureHandling.OPTIONAL)) {	
	WebUI.click(findTestObject('Object Repository/header-text/Book Store Application-header'), FailureHandling.STOP_ON_FAILURE)
}

WebUI.waitForElementVisible(findTestObject('Object Repository/menu-list/Book Store Application/Book Store'), GlobalVariable.timeout, FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/menu-list/Book Store Application/Book Store'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Object Repository/menu-list/Book Store Application/Rows Per Page'), GlobalVariable.timeout, FailureHandling.STOP_ON_FAILURE)
WebUI.scrollToElement(findTestObject('Object Repository/menu-list/Book Store Application/Rows Per Page'), GlobalVariable.timeout, FailureHandling.STOP_ON_FAILURE)
WebUI.selectOptionByValue(findTestObject('Object Repository/menu-list/Book Store Application/Rows Per Page'), '5', false, FailureHandling.STOP_ON_FAILURE)

while(WebUI.verifyElementNotPresent(findTestObject('Object Repository/menu-list/Book Store Application/Book Title', [("book_title") : "Understanding ECMAScript 6"]), GlobalVariable.timeout, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/menu-list/Book Store Application/Button Next'), FailureHandling.STOP_ON_FAILURE)
}

WebUI.click(findTestObject('Object Repository/menu-list/Book Store Application/Book Title', [("book_title") : "Understanding ECMAScript 6"]), FailureHandling.STOP_ON_FAILURE)
WebUI.waitForPageLoad(GlobalVariable.timeout, FailureHandling.STOP_ON_FAILURE)
WebUI.getUrl(FailureHandling.STOP_ON_FAILURE)