package utility

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
	 * This Keyword will get Full address or path
	 * </br>
	 * You can give a parameter the return using backslash or no. Also you can set the target path
	 *
	 * @param isUsingBackslash
	 * @param directoryPath
	 * @return
	 */
	@Keyword
	static String getDirectory(boolean isProjectDir = true, String directoryPath = "") {
		if (isProjectDir) directoryPath = RunConfiguration.getProjectDir() + "/" + directoryPath
		boolean isUsingBackslash = true

		if (directoryPath[0]=='/') {
			isUsingBackslash = false
		}

		if (isUsingBackslash) {
			directoryPath = directoryPath.replaceAll("/", "\\\\").trim()
		}
		
		KeywordUtil.markPassed("✅path : '${directoryPath}'✅")
		
		return directoryPath
	}

	static String[] getListFileName(String directoryPath = "") {
		ArrayList<String> fileNameList = new ArrayList()
		File dir = new File(directoryPath)

		if (dir.exists() && dir.isDirectory()) {
			File[] filesList = dir.listFiles()

			if (filesList.length == 0) {
				KeywordUtil.markWarning("⚠The directory is empty⚠️")
			} else {
				println "Listing files from: " + directoryPath
				filesList.each { file ->
					fileNameList.add(file.getName())
				}
			}

			return fileNameList
		} else {
			KeywordUtil.markFailed("The specified path is not a directory or does not exist.")
		}
	}
}
