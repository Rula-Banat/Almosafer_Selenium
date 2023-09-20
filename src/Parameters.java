import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Parameters {

	static WebDriver driver = new ChromeDriver();
	static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	
	//Random Index
    static Random random = new Random();
	static int randomIndex = random.nextInt(2);
	
	//Month Names
	static String[] monthsEnglish = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	static String[] monthsArabic = { "يناير", "فبراير", "مارس", "إبريل", "مايو", "يونيو", "يوليو", "أغسطس", "سبتمبر",
			"أكتوبر", "نوفمبر", "ديسمبر" };
	
	//Test Data: language URLs
	static String [] langUrls = {"https://www.almosafer.com/en","https://www.almosafer.com/ar"};

	
    //Test Data: Cities
    static String[] citiesArabic = {"دبي","جدة"};
    static String[] citiesEnglish = {"Dubai","Jeddah"};
    
    //Loading bar
    
}
