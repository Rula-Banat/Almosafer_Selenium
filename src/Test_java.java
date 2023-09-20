import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Test_java extends Parameters {

	@BeforeTest
	public void MyBeforeTest() {
		driver.get("https://www.almosafer.com/");
	}

	@Test
	public void MyTest() throws InterruptedException {
		//Test 1: Test the default language is English
		WebElement htmlElement = driver.findElement(By.tagName("html"));
		String langAttribute = htmlElement.getAttribute("lang");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(langAttribute, "en", "The 'lang' attribute is not 'en'");
		
		//Test 2: Visit both languages randomly
		driver.get(langUrls[randomIndex]);
		Thread.sleep(2000);
		
		//Test 3: Test default departure and arrival dates
		// First check the day of month is as expected
		List<WebElement> listOfDates = driver.findElements(By.className("sc-eSePXt"));
		String ActualDepartureText = listOfDates.get(0).getText();
		int ActualDepartureDay = Integer.parseInt(ActualDepartureText);
		String ActualArrivalText = listOfDates.get(1).getText();
		int ActualArrivalDay = Integer.parseInt(ActualArrivalText);
		LocalDate currentDate = LocalDate.now();
		LocalDate nextDay = currentDate.plusDays(1);
		int expectedDepartureDay = nextDay.getDayOfMonth();
		softAssert.assertEquals(ActualDepartureDay, expectedDepartureDay,
				"The default departure day is not as expected");

		LocalDate afterNextDay = currentDate.plusDays(2);
		int expectedArrivalDay = afterNextDay.getDayOfMonth();
		softAssert.assertEquals(ActualArrivalDay, expectedArrivalDay, "The default arrival day is not as expected");

		// Check the month is as expected

		List<WebElement> listOfMonths = driver.findElements(By.className("sc-fvLVrH"));

		// Actual Departure month
		String actualDepartureMonth = listOfMonths.get(0).getText();
		int nextDayMonth = nextDay.getMonthValue();
		// Actual Arrival month
		String actualArrivalMonth = listOfMonths.get(1).getText();
		int afterNextDayMonth = afterNextDay.getMonthValue();
		String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("/en")) {
        // Departure month in English
		String expectedDepartureMonth = monthsEnglish[nextDayMonth - 1];
		softAssert.assertEquals(actualDepartureMonth, expectedDepartureMonth);

		// Arrival in English
		String expectedArrivalMonth = monthsEnglish[afterNextDayMonth - 1];
		softAssert.assertEquals(actualArrivalMonth, expectedArrivalMonth);
        } 
        else {
        	// Departure month in Arabic
    		String expectedDepartureMonth = monthsArabic[nextDayMonth - 1];
    		softAssert.assertEquals(actualDepartureMonth, expectedDepartureMonth);
    		// Arrival month in Arabic
    		String expectedArrivalMonth = monthsArabic[afterNextDayMonth - 1];
    		softAssert.assertEquals(actualArrivalMonth, expectedArrivalMonth);
        }
        
        //Test 4: Select SAR currency and test currency is as selected
        driver.findElement(By.className("cta__saudi")).click();
        Thread.sleep(2000);
        String currency = driver.findElement(By.cssSelector("[data-testid='Header__CurrencySelector']")).getText();
        softAssert.assertEquals(currency, "SAR");
        
        //Test 5: Test hotels search
        driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).click();
        if (currentUrl.contains("/en")) {
        driver.findElement(By.className("cerrLM")).sendKeys(citiesEnglish[randomIndex]);
        } else {
            driver.findElement(By.className("cerrLM")).sendKeys(citiesArabic[randomIndex]);
        }
        driver.findElement(By.cssSelector("[data-testid='HotelSearchBox__SearchButton']")).click();
        
        //Test 6: Test sorting by price (low to high)
        //Wait for results to load (loading bar to be invisible)
        By resultsFoundMsg = By.cssSelector("[data-testid=\"HotelSearchResult__resultsFoundCount\"]");
        //WebElement loadingBarElement = driver.findElement(loadingBar);
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsFoundMsg));
        driver.findElement(By.cssSelector("[data-testid='HotelSearchResult__sort__LOWEST_PRICE']")).click();
        Thread.sleep(2000);
        List<WebElement> listOfPrices = driver.findElements(By.cssSelector("[data-testid=\"HotelSearchResult__ResultsList\"] .Price__Value"));
        Boolean isSorted = true;
        for (int i = 0; i < listOfPrices.size(); i++) {
			if (Integer.parseInt(listOfPrices.get(i).getText()) > Integer.parseInt(listOfPrices.get(i + 1).getText())) {
                isSorted = false;
                break;
            }
			softAssert.assertEquals(isSorted,true);
		}
        
		softAssert.assertAll();
	}


	@AfterTest
	public void MyAfterTest() {

	}
}
