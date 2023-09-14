import java.util.Random;

public class Parameters {

    Random random = new Random();
	int randomIndex = random.nextInt(2);
	
	//Month Names

	String[] monthsEnglish = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	String[] monthsArabic = { "يناير", "فبراير", "مارس", "إبريل", "مايو", "يونيو", "يوليو", "أغسطس", "سبتمبر",
			"أكتوبر", "نوفمبر", "ديسمبر" };
	
	//Test Data: language URLs
	String [] langUrls = {"https://www.almosafer.com/en","https://www.almosafer.com/ar"};
}
