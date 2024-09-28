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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

def login(String uname, String password) {
	WebUI.setText(findTestObject('Object Repository/Login Page/user-name'), uname, FailureHandling.STOP_ON_FAILURE)
	WebUI.setText(findTestObject('Object Repository/Login Page/password'), password, FailureHandling.STOP_ON_FAILURE)
	WebUI.click(findTestObject('Object Repository/Login Page/login-button'), FailureHandling.STOP_ON_FAILURE)
}

login("locked_out_user", "secret_sauce")
if (WebUI.verifyElementVisible(findTestObject('Object Repository/Login Page/error-box'), FailureHandling.OPTIONAL)) {
	String error_box = WebUI.getText(findTestObject('Object Repository/Login Page/error-box'), FailureHandling.STOP_ON_FAILURE)
	KeywordUtil.markWarning(error_box)
	login("standard_user", "secret_sauce")
	WebUI.waitForPageLoad(GlobalVariable.timeout, FailureHandling.STOP_ON_FAILURE)
	WebUI.waitForElementVisible(findTestObject('Object Repository/Navbar/span_Products'), GlobalVariable.timeout, FailureHandling.STOP_ON_FAILURE)
} else {
	KeywordUtil.markPassed("error-box not visible")
}

List<WebElement> inventory_item = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Products Page/inventory_item'), GlobalVariable.timeout)
int inventory_item_size = inventory_item.size()
KeywordUtil.logInfo("inventory_item size = ${inventory_item_size}")

String item_name_added = ""
String item_price_added = ""

if (inventory_item_size == 6) {
	TestObject button_add_to_cart = findTestObject('Object Repository/Products Page/add-to-cart-item', [("item_name") : "Fleece Jacket"])
	WebUI.click(button_add_to_cart, FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyMatch(WebUI.getText(button_add_to_cart, FailureHandling.STOP_ON_FAILURE), "Remove", false, FailureHandling.STOP_ON_FAILURE)
	item_name_added = WebUI.getText(findTestObject('Object Repository/Products Page/added-item-name'), FailureHandling.STOP_ON_FAILURE)
	item_price_added = WebUI.getText(findTestObject('Object Repository/Products Page/added-item-price'), FailureHandling.STOP_ON_FAILURE)
	
	WebUI.click(findTestObject('Object Repository/Navbar/a_1'), FailureHandling.STOP_ON_FAILURE)
	WebUI.waitForElementVisible(findTestObject('Object Repository/Navbar/span_Your Cart'), GlobalVariable.timeout, FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/inventory_item_name'), FailureHandling.STOP_ON_FAILURE), item_name_added, false, FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/inventory_item_price'), FailureHandling.STOP_ON_FAILURE), item_price_added, false, FailureHandling.STOP_ON_FAILURE)
} else {
	KeywordUtil.markFailed("cart size not equals 6")
}