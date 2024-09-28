package utilitize

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
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

import internal.GlobalVariable

public class GeneralFunction {
	/**
	 * This Keyword will get Full address or path of your root level katalon project
	 * </br>
	 * You can give a parameter the return using backslash or no. Also you can set the target path
	 *
	 * @param isUsingBackslash
	 * @param targetPath
	 * @return
	 */
	@Keyword
	public static String getDirectory(String targetPath='') {
		String directoryPath = RunConfiguration.getProjectDir() + "/" + targetPath
		boolean isUsingBackslash = true

		if (directoryPath[0]=='/') {
			isUsingBackslash = false
		}

		if (isUsingBackslash) {
			directoryPath = directoryPath.replaceAll("/", "\\\\").trim()
		}
		KeywordUtil.markPassed("✅ path : '${directoryPath}' ✅")
		return directoryPath
	}
}
