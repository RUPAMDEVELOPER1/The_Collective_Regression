package utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.asynchttpclient.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.PageObjectManager;
import pageObjects.SeachPage;

public class GenericUtils {
	public String copiedCount;
	public String verifyCopiedCount;
	public WebDriver driver;
	public PageObjectManager pageObjectManager;
	
	public GenericUtils(WebDriver driver,PageObjectManager pageObjectManager)
	{

		this.driver = driver;
		this.pageObjectManager = pageObjectManager;
	}



	public void SwitchWindowToChild()
	{
		Set<String> s1=driver.getWindowHandles();
		Iterator<String> i1 =s1.iterator();
		String parentWindow = i1.next();
		String childWindow = i1.next();
		driver.switchTo().window(childWindow);
	}

//	public void removeAllitem(){
//		List<WebElement> item = driver.findElements(By.xpath("remove"));
//
//		for(int i = 0; i<item.size();i++){
//			WebElement element = item.get(i);
//			element.click();
//		}
//
//	}
	public void clickRandom(By commonxpath) {

		try {
			List<WebElement> products  = driver.findElements(commonxpath);
			//to genrate number incluing the min and max value
//		      Random rand = new Random();
//		      int randomNum = rand.nextInt(max – min + 1) + min;
			if(products.size()!=0){
			System.out.println("size of product"+products.size());
			int min = 0;
			int max = products.size()-1;
			Random rand = new Random();
			int randomNum = rand.nextInt((max - min) + 1) + min;
			products.get(randomNum).click();}
			else {
				System.out.println("your list is empty no element found");
			}
		} catch (Exception e) {
			System.out.println("xpath is not correct");
			throw new RuntimeException(e);
		}


	}
	public void addToBag() throws InterruptedException {

		Thread.sleep(2000);
		WebElement element = null;
		try {
			Thread.sleep(4000);
			element = driver.findElement(pageObjectManager.seachPage.addtoBag);
		} catch (Exception e) {
			System.out.println("element not found for add to bag");
			throw new RuntimeException(e);
		}
		try {
			element.click();
			Thread.sleep(5000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	public void copyCartcount(){
		//copiedCount = driver.findElement(pageObjectManager.seachPage.cartCount).getText()
		try {

			 String copiednumbercount = driver.findElement(pageObjectManager.getShoppingCartpage().shopingCartCount).getText();
			driver.findElement(pageObjectManager.getShoppingCartpage().shoopingcarxpath).click();
			System.out.println("copiednumbercount"+copiednumbercount);
		} catch (Exception e) {
			System.out.println("bag is not clicked");
			throw new RuntimeException(e);
		}

		System.out.println("copied value"+ copiedCount);
	}
	public void verifyCopiedCountIsNotSame() {
		verifyCopiedCount = driver.findElement(pageObjectManager.seachPage.cartCount).getText();
		System.out.println("earlier copied count is " + copiedCount);
		System.out.println("my now count is" + verifyCopiedCount);
		if (copiedCount != verifyCopiedCount) {
			System.out.println("the count is different test is pass");
		} else {
			throw new RuntimeException("count is not chanign its same");
		}
	}

	public void SendMailWithMessege(String subject, String messege) throws IOException {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//SendMail.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String to = prop.getProperty("toemail");
		String from = prop.getProperty("frommail");
		String pass = prop.getProperty("apppassword");
		//System.out.println(url);
		System.out.println("preparing to send email");
		//String messege = " hello i am rupam";
		//String body = "regrading autoamtion mail";
		//String to = "rupam@algoshack.com";
		//String from = "testtestabfrlrupam@gmail.com";
		String host="smtp.gmail.com";
		Properties properties = System.getProperties();
		//System.out.println("emtry of properties");
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		//System.out.println("exit of properties");
		//properties.put("mail.smtp.connectiontimeout", "t1");
		//properties.put("mail.smtp.timeout", "t2");
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,pass);
			}
		});
		//System.out.println("sesssion completed");

		MimeMessage mimeMessage = new MimeMessage(session);

		try {
			mimeMessage.setFrom(from);
			mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(messege);
			//System.out.println("before send");
			Transport.send(mimeMessage);
			System.out.println("messsege sent sucessfully......!");

		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		}

	public void sendMailWithAttatchment(String subject, String messege, String path) throws IOException {
		System.out.println("preparing to send email");
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//SendMail.properties");
		Properties prop = new Properties();
		prop.load(fis);
		//String messege = " This mail is regarding with excel attathement through automation";
		//String body = "regrading autoamtion mail";
		String to = prop.getProperty("toemail");
		String from = prop.getProperty("frommail");
		String pass = prop.getProperty("apppassword");
//		String to = "rupam@algoshack.com";
//		String from = "testtestabfrlrupam@gmail.com";
		String host="smtp.gmail.com";
		Properties properties = System.getProperties();
		System.out.println("emtry of properties");
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		System.out.println("exit of properties");
		//properties.put("mail.smtp.connectiontimeout", "t1");
		//properties.put("mail.smtp.timeout", "t2");
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,pass);
			}
		});
		System.out.println("sesssion completed");

		MimeMessage mimeMessage = new MimeMessage(session);

		try {
			mimeMessage.setFrom(from);
			mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
			mimeMessage.setSubject(subject);
			//String path = "/Users/rupamsethi/Downloads/CucumberFramework excel and mail/target/Excelsheets/rupam.xlsx";
			MimeMultipart mimeMultipart = new MimeMultipart();//text and file both
			MimeBodyPart textmime = new MimeBodyPart();
			MimeBodyPart filemime = new MimeBodyPart();


			try {
				//mimeMessage.setText(messege);
				textmime.setText(messege);
				File file = new File(path);
				filemime.attachFile(file);
				mimeMultipart.addBodyPart(textmime);
				mimeMultipart.addBodyPart(filemime);


			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			System.out.println("before send");
			mimeMessage.setContent(mimeMultipart);
			Transport.send(mimeMessage);
			System.out.println("messsege sent sucessfully......!");

		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public void checkBrokenLinkAndSendEmail(String BrandURL) throws IOException {
		driver.get(BrandURL);
		int countofBrokenLinks = 0;
		String messeges = null;

		HashMap<String,Integer> map = new HashMap<>();
		HttpURLConnection huc = null;
		List<WebElement> links = driver.findElements(By.tagName("a"));
		Iterator<WebElement> iterator = links.iterator();
		String url ="";
//		https://www.thecollective.in/
		int position = BrandURL.indexOf(".",BrandURL.indexOf(".")+1);
		String brandName = BrandURL.substring(12,position);
		System.out.println("the brand name is :>"+ " "+brandName);
		System.out.println(System.getProperty("user.dir"));
        String path = System.getProperty("user.dir")+"/target/BrokenLinksExcelFiles/"+brandName+"Linkstatus"+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".xlsx";
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("brokenlinkstatus");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Links");
		header.createCell(1).setCellValue("StatusCode");
		File file = new File(path);
		FileOutputStream fos = new FileOutputStream(file);
		int rowcount = 0;
		for(int i = 0; i<links.size();i++){
			String code = "";
			boolean isURL = false;
			try{
				url = links.get(i).getAttribute("href");
				isURL = true;
			}
			catch (Exception e){
				System.out.println(e);
				isURL = false;
			}
			if(url != null && isURL && url.startsWith("https")) {
				rowcount++;
				Row row = sheet.createRow(rowcount);
				row.createCell(0).setCellValue(url);
				//sheet.createRow(rowcount);
//				if (url.contains("?")) {
//					String[] spliturl = url.split("\\?");
//					String param = URLEncoder.encode(spliturl[1], "UTF-8");
//					url = spliturl[0] + "?" + param;
//				}
				String statuc = null;
				int respCode = 0;
				try {
					huc = (HttpURLConnection) (new URL(url).openConnection());

					//huc.setRequestMethod("HEAD");

					huc.connect();

					respCode = huc.getResponseCode();
					if(respCode>=400){
						if(!map.containsKey(url)){
							map.put(url,respCode);
						}

					}

//
//					RestAssured.baseURI = url;
//					RestAssured.useRelaxedHTTPSValidation();
//					RequestSpecification httpRequest = RestAssured.given();
//					Response response = (Response) httpRequest.request(Method.GET);
//					statuc = String.valueOf(response.getStatusCode());
				} catch (Exception e) {
					System.out.println(e);
				}
				row.createCell(1).setCellValue(respCode);
				System.out.println(url + " :  >" + respCode);


			}



		}
		System.out.println("all links done");
		System.out.println("excel process started");
		wb.write(fos);
		wb.close();
		System.out.println("excel is created");
		//when all link done how many broken link are there
		if(map.size()>0){
			countofBrokenLinks = map.size();
			for( Map.Entry<String, Integer> entry : map.entrySet() ){
				String m =  entry.getKey() + " = " + entry.getValue();
				messeges += m;
			}
		}
		else{
			countofBrokenLinks =0;
			messeges = "There is no brokenLink for"+brandName+" ...........!!!!";
		}
		System.out.println(messeges);



		System.out.println("preparing to send email");
		//String messege = " This mail is regarding with excel attathement through automation";
		String body = "Broken Links report for all brand ";
		String to = "rupam@algoshack.com";
		String from = "testtestabfrlrupam@gmail.com";
		String host="smtp.gmail.com";
		Properties properties = System.getProperties();
		System.out.println("emtry of properties");
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		System.out.println("exit of properties");
		//properties.put("mail.smtp.connectiontimeout", "t1");
		//properties.put("mail.smtp.timeout", "t2");
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("testtestabfrlrupam@gmail.com","ovfdencjmibeblnn");
			}
		});
		System.out.println("sesssion completed");

		MimeMessage mimeMessage = new MimeMessage(session);

		try {
			mimeMessage.setFrom(from);
			mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
			mimeMessage.setSubject(body);
			//String path = "/Users/rupamsethi/Downloads/CucumberFramework excel and mail/target/Excelsheets/rupam.xlsx";
			MimeMultipart mimeMultipart = new MimeMultipart();//text and file both
			MimeBodyPart textmime = new MimeBodyPart();
			MimeBodyPart filemime = new MimeBodyPart();


			try {
				//mimeMessage.setText(messege);
				textmime.setText(messeges);
				 File file2 = new File(path);
				filemime.attachFile(file2);
				mimeMultipart.addBodyPart(textmime);
				mimeMultipart.addBodyPart(filemime);


			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			System.out.println("before send");
			mimeMessage.setContent(mimeMultipart);
			Transport.send(mimeMessage);
			System.out.println("messsege sent sucessfully......!");

		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
