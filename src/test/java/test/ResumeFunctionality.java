package test;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResumeFunctionality {

	private static WebDriver driver;
	private static JavascriptExecutor js;
	private static WebDriverWait wait;
	private static Logger log = LogManager.getLogger();

	public ResumeFunctionality(WebDriver driver) {
		ResumeFunctionality.driver = driver;
		ResumeFunctionality.js = (JavascriptExecutor) driver;
		ResumeFunctionality.wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
	}

	public void resumeFlow() throws InterruptedException {

		WebElement dashboardBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/main/app-get-started/div/div/div/div/div[4]/button[1]")));

		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", dashboardBtn);
		log.info("Dashboard button is clicked");

		WebElement reviewInProgress = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/app-root/main/app-homepage/div/div/div[1]/button[1]")));
		js.executeScript("arguments[0].click();", reviewInProgress);
		log.info("review in progress button is clicked");

		WebElement resumeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[2]/table/tbody/tr[1]/td[11]/button")));
		js.executeScript("arguments[0].click();", resumeButton);
		

		try {
            WebElement upload_page_next = new WebDriverWait(driver,Duration.ofSeconds(100) ).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
            js.executeScript("arguments[0].click();", upload_page_next);
            log.info("Upload Button CLicked.");
        } catch (Exception e) {
            log.error("Error in UploadPage");
            log.error(e);
        }

        
        try {

        	String loading_container_xpath = "/html/body/app-root/app-page-loading/div/app-loading-spinner/div/div";
        	Boolean loading_container_element = new WebDriverWait(driver, Duration.ofSeconds(400)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loading_container_xpath)));

        	String verify_page_next_xpath = "/html/body/app-root/main/app-verify/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button";
        	WebElement verify_page_next = new WebDriverWait(driver,Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.xpath(verify_page_next_xpath)));

        	js.executeScript("arguments[0].click();", verify_page_next);
             
        	
            log.info("Verify Button Clicked.");
        } catch (Exception e) {
            log.error("Error in VerifyPage");
            log.error(e);
        }

        
        
        try {
        	
        	String loading_container_xpath = "/html/body/app-root/app-page-loading/div/app-loading-spinner/div/div";
        	Boolean loading_container_element = new WebDriverWait(driver, Duration.ofSeconds(400)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loading_container_xpath)));

        	String results_page_next_xpath = "/html/body/app-root/main/app-results/div/div/div[1]/app-side-button-panel/div/div/div[2]/div[1]/button";
        	WebElement results_page_next = new WebDriverWait(driver,Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.xpath(results_page_next_xpath)));

        	WebElement errorCount = driver.findElement(By.xpath("/html/body/app-root/main/app-results/div/div/div[1]/span"));
            log.info("Error Count: "+errorCount.getText());
            
//            artworkDto.setErrorCount(Integer.parseInt(errorCount.getText().substring(0, 1)));
        	
        	js.executeScript("arguments[0].click();", results_page_next);

        	log.info("Save button clicked");
        } catch (Exception e) {
            log.error("Error in ResultsPage");
            log.error(e);
        }
        
        
        
        try {
        	
        	String loading_container_xpath = "/html/body/app-root/app-page-loading/div/app-loading-spinner/div/div";
        	Boolean loading_container_element = new WebDriverWait(driver, Duration.ofSeconds(400)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loading_container_xpath)));

        	String results_page_next_xpath = "/html/body/app-root/main/app-all-done/div/div/div[2]/div/div[3]/div[1]/a";
        	WebElement downloadButton = new WebDriverWait(driver,Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(By.xpath(results_page_next_xpath)));

        	js.executeScript("arguments[0].click();", downloadButton);

        	log.info("download button is clicked");
		} catch (Exception e) {
	        log.error("Error in DownloadPage");
	        log.error(e);
	    }

	}
	
	
//		WebElement rowData = driver.findElement(By.xpath(
//				"/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[2]/table/tbody/tr[1]"));
//		String rowdata = rowData.getText();
//		String statusData = rowdata.split(".jpg")[1].split("Resume")[0].trim();
//		log.info(statusData);
//
//		if (statusData.equals("Results Generation" ) ) {
//			log.info("save results method invoked");
//			saveResultsFlow();
//		}
//
//		if ( statusData.equals("Grey PDF Generation" ) ) {
//			log.info("next_btn method invoked");
//			nextBtnFlow();
//		}
//
//		Thread.sleep(3000);

	
//	private void saveResultsFlow() throws InterruptedException {
//
//		log.info("save results method started");
//
//		WebElement resumeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//				"/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[2]/table/tbody/tr[1]/td[11]/button")));
//		js.executeScript("arguments[0].click();", resumeButton);
//		log.info("resume button is clicked");
//
//		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//				"/html/body/app-root/main/app-results/div/div/div[1]/app-side-button-panel/div/div/div[2]/div[1]/button")));
//		js.executeScript("arguments[0].click();", saveButton);
//		log.info("save button is clicked");
//
//		WebElement dwnldButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-all-done/div/div/div[2]/div/div[3]/div[1]/a")));
//		js.executeScript("arguments[0].click();", dwnldButton);
//		log.info("download button is clicked");
//
//		Thread.sleep(3000);
//	}
//	
//	private void nextBtnFlow() throws InterruptedException {
//		
//		log.info("next button method started");
//
//		WebElement resumeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//				"/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[2]/table/tbody/tr[5]/td[11]/button")));
//		js.executeScript("arguments[0].click();", resumeButton);
//		log.info("resume button is clicked");
//		
//		Thread.sleep(2000);
//
//		WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//				"/html/body/app-root/main/app-verify/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
//		js.executeScript("arguments[0].click();", nextButton);
//		log.info("next button is clicked");
//		
//		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//				"/html/body/app-root/main/app-results/div/div/div[1]/app-side-button-panel/div/div/div[2]/div[1]/button")));
//		js.executeScript("arguments[0].click();", saveButton);
//		log.info("save button is clicked");
//		
//		WebElement dwnldButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-all-done/div/div/div[2]/div/div[3]/div[1]/a")));
//		js.executeScript("arguments[0].click();", dwnldButton);
//		log.info("download button is clicked");
//		
//		Thread.sleep(3000);
//
//	}

}
