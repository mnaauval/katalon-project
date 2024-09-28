package com.login

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.util.HandleElement

import internal.GlobalVariable

public class Login_out {
	HandleElement he = new HandleElement()

	def loginTest(HashMap containerHashMap) {
		String username = containerHashMap.get("username")
		String password = containerHashMap.get("password")

		WebUI.waitForElementVisible(findTestObject('Object Repository/Login Page/button_Login'), GlobalVariable.timeoutLoading, FailureHandling.STOP_ON_FAILURE)
		he.clickElementAndType(findTestObject('Object Repository/Login Page/username'), username)
		he.clickElementAndType(findTestObject('Object Repository/Login Page/password'), password)
		WebUI.click(findTestObject('Object Repository/Login Page/button_Login'), FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForPageLoad(GlobalVariable.timeoutLoading, FailureHandling.STOP_ON_FAILURE)
	}

	def loginValidation(HashMap containerHashMap) {
		String TD_ID = containerHashMap.get("TD_ID")
		String username = containerHashMap.get("username")
		String password = containerHashMap.get("password")
		String TYPE_TESTCASE = containerHashMap.get("TYPE_TESTCASE")

		switch(TYPE_TESTCASE) {
			case "Positive":
			// code block
				WebUI.waitForElementVisible(findTestObject('Object Repository/Top Bar/profile_name'), GlobalVariable.timeoutLoading, FailureHandling.STOP_ON_FAILURE)
				WebUI.verifyElementVisible(findTestObject('Object Repository/Top Bar/profile_name'), FailureHandling.STOP_ON_FAILURE)
				KeywordUtil.markPassed("Berhasil login")
				logout()
				break;
			case "Negative":
			// code block
				if (username == "" || password == "") {
					WebUI.verifyElementVisible(findTestObject('Object Repository/Login Page/username_required'), FailureHandling.STOP_ON_FAILURE)
				} else {
					WebUI.verifyElementVisible(findTestObject('Object Repository/Login Page/invalid_credentials'), FailureHandling.STOP_ON_FAILURE)
				}
				break;
			default:
			// code block
				KeywordUtil.markFailed("TYPE_TESTCASE ${TYPE_TESTCASE} tidak valid")
		}
	}

	def logout() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Top Bar/button_profile'), GlobalVariable.timeoutLoading, FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject('Object Repository/Top Bar/button_profile'), FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForElementVisible(findTestObject('Object Repository/Top Bar/a_Logout'), GlobalVariable.timeoutLoading, FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject('Object Repository/Top Bar/a_Logout'), FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForPageLoad(GlobalVariable.timeoutLoading, FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForElementVisible(findTestObject('Object Repository/Login Page/button_Login'), GlobalVariable.timeoutLoading, FailureHandling.STOP_ON_FAILURE)
	}

	def checkVersion() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Login Page/version'), GlobalVariable.timeoutLoading, FailureHandling.STOP_ON_FAILURE)
		GlobalVariable.version = WebUI.getText(findTestObject('Object Repository/Login Page/version'), FailureHandling.STOP_ON_FAILURE)
		KeywordUtil.logInfo("<========== Current version is ${GlobalVariable.version} ==========>")
	}
}
