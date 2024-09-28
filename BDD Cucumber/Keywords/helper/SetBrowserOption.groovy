package helper

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import utilitize.GeneralFunction

public class SetBrowserOption {
	/**
	 * setChromeOption
	 *
	 * @param isHeadless
	 * @param downloadPath
	 * @param url
	 */
	static void setChromeOption(boolean isHeadless, String downloadPath, String url) {
		ChromeOptions options = new ChromeOptions()
		options.addArguments("--start-maximized")
		options.addArguments("--force-device-scale-factor=0.85")
		options.addArguments("--auto-open-devtools-for-tabs")
		!isHeadless?: options.addArguments("--headless");
		options.addArguments("--disable-dev-shm-usage");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", downloadPath);
		prefs.put("download.prompt_for_download", false)
		prefs.put("download.directory_upgrade", true)
		prefs.put("plugins.always_open_pdf_externally", true)
		options.setExperimentalOption("prefs", prefs);
		System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
		KeywordUtil.markPassed("⚠ Set Preferences and Opening the browser ⚠️")
		WebDriver driver = new ChromeDriver(options)
		RunConfiguration.storeDriver(driver)
		KeywordUtil.markPassed("✅ Success Set Preferences ✅")
		DriverFactory.changeWebDriver(driver)
		driver.get(url)
		KeywordUtil.markPassed("✅  Browser Opened ✅")
	}


	/**
	 *  setFirefoxOption
	 *
	 * @param isHeadless
	 * @param downloadPath
	 * @param url
	 */
	static void setFirefoxOption(boolean isHeadless, String downloadPath, String url) {
		FirefoxOptions options = new FirefoxOptions()
		options.addPreference("browser.download.dir", downloadPath)
		options.addPreference("browser.download.useDownloadDir", true)
		options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/*")
		options.addPreference("browser.download.folderList", 2)
		options.addPreference("pdfjs.disabled", true)
		options.addPreference("layout.css.devPixelsPerPx", "0.80")
		options.addPreference("network.proxy.type", 0)
		options.setHeadless(isHeadless)
		System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverPath());
		KeywordUtil.markPassed("⚠ Set Preferences and Opening the browser ⚠️")
		def WebDriver driver = new FirefoxDriver(options);
		KeywordUtil.markPassed("✅  Success Set Preferences ✅")
		DriverFactory.changeWebDriver(driver);
		driver.get(url);
		com.kms.katalon.core.configuration.RunConfiguration.storeDriver(driver)
		WebUI.maximizeWindow()
		KeywordUtil.markPassed("✅  Browser Opened ✅")
	}

	/** ========== Icon for Keyword Utils. "mark"
	 * INFO = ⚠️
	 * FAILED = ❌
	 * SUCCESS = ✅
	 */

	/**
	 * This keywords will Open your browser set Desired Capability for Chrome and Firefox include maximized window, scale,and set download default directory.
	 * </br>
	 * You can use this keyword in TestCase(s) level or TestSuite(s) level.
	 * </br>
	 * Call this keyword and give a required parameter
	 * </br>
	 * setDesiredCapability('Download_Dir_Path/', 'https://.....')
	 *
	 * @param downloadPath is relative path to set download default directory
	 * @param url is parameter given to navigate to url
	 */
	@Keyword
	static void setDesiredCapability(String downloadPath, String url) {
		WebUIDriverType executedBrowser = DriverFactory.getExecutedBrowser()
		downloadPath = GeneralFunction.getDirectory(downloadPath)

		switch(executedBrowser) {
			case WebUIDriverType.CHROME_DRIVER :
				setChromeOption(false, downloadPath, url)
				break

			case WebUIDriverType.FIREFOX_DRIVER :
				setFirefoxOption(false, downloadPath, url)
				break
			case WebUIDriverType.HEADLESS_DRIVER :
				setChromeOption(true, downloadPath, url)
				break

			case WebUIDriverType.FIREFOX_HEADLESS_DRIVER :
				setFirefoxOption(true, downloadPath, url)
				break

			default :
				KeywordUtil.markFailed("Driver not set yet")
				break
		}
	}
}
