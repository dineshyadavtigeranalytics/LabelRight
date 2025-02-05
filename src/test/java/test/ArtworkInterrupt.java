package test;

import java.io.File;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArtworkInterrupt {

	private static WebDriver driver;
	private static JavascriptExecutor js;
	private static WebDriverWait wait;
	private static Logger log = LogManager.getLogger();

	public ArtworkInterrupt(WebDriver driver) {
		ArtworkInterrupt.driver = driver;
		ArtworkInterrupt.js = (JavascriptExecutor) driver;
		ArtworkInterrupt.wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
	}

	public void artworkInterruptFlow(int regionOption, int packageType, String artworkUrl, String lidUrl)
			throws InterruptedException {
		ArtworkDto artworkDto = new ArtworkDto();

		WebElement region_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/header/app-header/div/div/div[2]/mat-form-field/div/div[1]/div[2]/mat-icon")));
		js.executeScript("arguments[0].click();", region_selection_element);
		

		WebElement region_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[" + regionOption + "]/span")));
		js.executeScript("arguments[0].click();", region_option_element);

		WebElement adhoc_project_btn = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/main/app-get-started/div/div/div/div/div[4]/button[2]")));
		js.executeScript("arguments[0].click();", adhoc_project_btn);

		Thread.sleep(2000);

		WebElement package_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[1]/div[2]/mat-form-field/div/div[1]/div/mat-select/div/div[1]/span/span")));
		js.executeScript("arguments[0].click();", package_selection_element);

		WebElement package_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[" + packageType + "]/span")));
		js.executeScript("arguments[0].click();", package_option_element);

		Thread.sleep(3000);

		WebElement aw_upload_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[1]/app-file-upload/div/div[2]/div/input")));
		File artwork = new File(artworkUrl);
		log.info(artwork);
		aw_upload_element.sendKeys(artwork.getAbsolutePath());
		log.info("Artwork Uploaded");

		Thread.sleep(4000);

		String ArtworkID = (String) js.executeScript("return window.sessionStorage.getItem('projectId');");
		log.info("Artwork Request Successful: \nArtwork Request ID: " + ArtworkID);

		artworkDto.setArtworkID(Integer.parseInt(ArtworkID));

		WebElement aw_upload_element_img = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[1]/app-file-upload/div/div[2]/div[1]/div/img")));
		log.info("Artwork Uploaded. Going to Label Right Home Page.");

		Thread.sleep(5000);

		WebElement labelRightHome = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/app-root/header/app-header/div/h3/span")));
		js.executeScript("arguments[0].click();", labelRightHome);
		Thread.sleep(4000);
		
		region_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/header/app-header/div/div/div[2]/mat-form-field/div/div[1]/div[2]/mat-icon")));
		js.executeScript("arguments[0].click();", region_selection_element);
		

		region_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[" + regionOption + "]/span")));
		js.executeScript("arguments[0].click();", region_option_element);

		log.info("going to dashboard");

		WebElement dashboardBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/main/app-get-started/div/div/div/div/div[4]/button[1]")));

		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", dashboardBtn);

		WebElement reviewInProgress = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/app-root/main/app-homepage/div/div/div[1]/button[1]")));
		js.executeScript("arguments[0].click();", reviewInProgress);
		Thread.sleep(3000);

		WebElement search_bar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//app-root//app-homepage[@class='ng-star-inserted']/div[@class='dashboardpageContainer']/div[@class='maindiv']//app-dashboardtable[@class='ng-star-inserted']/div[@class='dashboardtablemain']//input[@type='text']")));
		search_bar.sendKeys("" + artworkDto.getArtworkID());
		js.executeScript("arguments[0].click();", search_bar);

		Thread.sleep(3000);

		WebElement resumeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[2]/table/tbody/tr[1]/td[11]/button")));
		js.executeScript("arguments[0].click();", resumeButton);
		log.info("resume button is clicked");
		Thread.sleep(7000);

		WebElement lid_upload_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[2]/app-file-upload/div/div[2]/div/input")));
		File lid = new File(lidUrl);
		log.info(lid);
		lid_upload_element.sendKeys(lid.getAbsolutePath());
		log.info("Lid Uploaded");

		WebElement lid_upload_element_img = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[2]/app-file-upload/div/div[2]/div[1]/div/img")));
		log.info("LID Uploaded. ");

		
		
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
            
            artworkDto.setErrorCount(Integer.parseInt(errorCount.getText().substring(0, 1)));
        	
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
        
		Thread.sleep(3000);
		

	}
}
