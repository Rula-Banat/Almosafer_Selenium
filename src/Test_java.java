import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Test_java extends Parameters {
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void MyBeforeTest() {
		driver.get("https://www.almosafer.com/");
	}

	@Test
	public void MyTest() {
		// Test the default language is English
		WebElement htmlElement = driver.findElement(By.tagName("html"));
		String langAttribute = htmlElement.getAttribute("lang");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(langAttribute, "en", "The 'lang' attribute is not 'en'");
		softAssert.assertAll();
		
		//Visit both languages randomly
		driver.get(langUrls[randomIndex]);
		System.out.println(randomIndex);
		
		
		// Test default departure and arrival dates
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
    		String expectedArrivalMonth = monthsEnglish[afterNextDayMonth - 1];
    		softAssert.assertEquals(actualArrivalMonth, expectedArrivalMonth);
        }
	}

	@AfterTest
	public void MyAfterTest() {

	}
}
