import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class LoginSteps {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Given("User navigate to Login Page")
	def navigateToLoginPage() {
		WebUI.navigateToUrl(GlobalVariable.baseUrl)
	}

	@When("User input username")
	def inputUsername() {
		WebUI.setText(findTestObject('Object Repository/General/input (id)', ['id': 'user-name']), GlobalVariable.username)
	}

	@And("User input password")
	def inputPassword() {
		WebUI.setEncryptedText(findTestObject('Object Repository/General/input (id)', ['id': 'password']), GlobalVariable.password)
	}

	@When("User input username (.*)")
	def inputUsername(String username) {
		WebUI.setText(findTestObject('Object Repository/General/input (id)', ['id': 'user-name']), username)
	}

	@And("User input password (.*)")
	def inputPassword(String password) {
		WebUI.setEncryptedText(findTestObject('Object Repository/General/input (id)', ['id': 'password']), password)
	}

	@And("User click login button")
	def clickLoginButton() {
		WebUI.click(findTestObject('Object Repository/General/input (id)', ['id': 'login-button']))
		WebUI.waitForPageLoad(0)
	}

	@And("User validate success login")
	def verifyLoginSuccess() {
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/General/button (class)', ['class': 'error-button']), GlobalVariable.timeoutLoading)
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/General/button (class)', ['class': 'login_logo']), GlobalVariable.timeoutLoading)
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/General/button (class)', ['class': 'login_wrapper']), GlobalVariable.timeoutLoading)
		logout()
	}

	@And("User validate error login (.*)")
	def verifyLoginError(String errorMessage) {
		WebUI.verifyElementPresent(findTestObject('Object Repository/General/button (class)', ['class': 'error-button']), GlobalVariable.timeoutLoading)
		String actualError = WebUI.getText(findTestObject('Object Repository/General/h3 (data-test)', ['data-test': 'error']))
		assert actualError.contains(errorMessage) : KeywordUtil.markFailed("${actualError} doesn't match ${errorMessage}")

		// Generate a timestamp for unique file names
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())

		// Define the custom file name
		String screenshotPath = "${RunConfiguration.getReportFolder()}/Scenario User login with invalid credentials/${timestamp}_${errorMessage}.png"

		WebUI.takeElementScreenshot(screenshotPath, findTestObject('Object Repository/General/div (class)', ['class': 'login_wrapper-inner']))
		WebUI.click(findTestObject('Object Repository/General/button (class)', ['class': 'error-button']))
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/General/button (class)', ['class': 'error-button']), GlobalVariable.timeoutLoading)
	}

	def logout() {
		if (WebUI.verifyElementNotVisible(findTestObject('Object Repository/General/a (id)', ['id': 'logout_sidebar_link']), FailureHandling.OPTIONAL)) {
			WebUI.click(findTestObject('Object Repository/General/button (text)', ['text': 'Open Menu']))
		}

		WebUI.click(findTestObject('Object Repository/General/a (id)', ['id': 'logout_sidebar_link']))
	}
}