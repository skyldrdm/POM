package com.mockaroo;

import static org.testng.Assert.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class M1 {
	WebDriver driver;
	BufferedReader reader;
	String path;
	List<String> citiesList = new ArrayList<>();
	List<String> countriesList = new ArrayList<>();;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
	@Test (priority = 1)
	public void checkPage() {
		driver.get("https://mockaroo.com");
		String title = driver.getTitle();
		String actualTitle="Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel";
		assertEquals(title, actualTitle);
		String header1 = driver.findElement(By.xpath("//div[@class='brand']")).getText();
		String header2 = driver.findElement(By.xpath("//div[@class='tagline']")).getText();	
		assertEquals(header1, "mockaroo");
		assertEquals(header2, "realistic data generator" );
	}
	@Test (priority = 2)
	public void removeFields()	{
	List<WebElement> removeFields = driver.findElements(By.xpath("//a[@class='close remove-field remove_nested_fields']"));
		for (WebElement webElement : removeFields) {
			webElement.click();
		}
	}

	@Test (priority = 3)
	public void controlPage() {
		assertEquals(driver.findElement(By.xpath("//div[@class='column column-header column-name']")).getText(),"Field Name");
		assertEquals(driver.findElement(By.xpath("//div[@class='column column-header column-type']")).getText(),"Type");
		assertEquals(driver.findElement(By.xpath("//div[@class='column column-header column-options']")).getText(),"Options");
		
		assertTrue(driver.findElement(By.xpath("//a[.='Add another field']")).isEnabled());
		assertEquals(driver.findElement(By.id("num_rows")).getAttribute("value"),"1000");
		assertEquals(new Select(driver.findElement(By.id("schema_file_format"))).getFirstSelectedOption().getText(),"CSV");
		assertEquals(new Select(driver.findElement(By.id("schema_line_ending"))).getFirstSelectedOption().getText(),"Unix (LF)");
		
		assertTrue(driver.findElement(By.id("schema_include_header")).isSelected());
		assertFalse(driver.findElement(By.id("schema_bom")).isSelected());
	}
	@Test (priority = 4)
	public void createTable() throws InterruptedException, IOException {
		addFields("City");
		Thread.sleep(1000);
		addFields("Country");
		downloadAndCheckFirstLine("City,Country");
	}
	//no @Test in here since there is a parameter and parameterization is something we did not learn yet
	//@Parameters({ "field" })
	//@Test
	public void downloadAndCheckFirstLine(String field) throws InterruptedException, IOException {
		Thread.sleep(1000);
		driver.findElement(By.id("download")).click();
		
		path = "C:\\Users\\harezmi\\Downloads\\MOCK_DATA.csv";
		reader = new BufferedReader(new FileReader(path));
		assertTrue(reader.readLine().contains(field), "kim bilir ne oldu");
	}
	
	@Test (priority = 6)
	public void downloadAndCheckAllLines() throws IOException {
		int lines = 0;
		while (reader.readLine() != null) 
			lines++;
		System.out.println("There are "+lines+" lines in the file");
		assertEquals(lines, 1000, "There are "+lines+" lines");
		reader.close();
	}
	
	@Test (priority = 7)
	public void creatingLists() throws IOException {
		
		reader = new BufferedReader(new FileReader(path));
		System.out.println("Reading the titles: "+reader.readLine());
		String line;
		while ((line = reader.readLine()) != null) {
			citiesList.add(splitter(line)[0]);
			countriesList.add(splitter(line)[1]);
		}
		Collections.sort(citiesList);
		Collections.sort(countriesList);
	}
	@Test (priority = 8)
	public void citiesReport() throws IOException {
		int min=999, max=0;
		String longest="", shortest="";
		
		for (int i = 0; i < citiesList.size(); i++) {
			int length = citiesList.get(i).length();
			if(length>max) {
				max=length;
				longest=citiesList.get(i);
			}
			if(length<min) {
				min=length;
				shortest=citiesList.get(i);
			}
		}
		System.out.println("City with the longest name is: "+longest+" with "+max+" characters");
		System.out.println("City with the shortest name is: "+shortest+" with "+min+" characters");
	}
	@Test (priority = 9)
	public void countriesReport() throws IOException {
		Map<String, Integer> countries = new HashMap<>();
			
		for (String str : countriesList) {
			if(countries.containsKey(str))
				countries.put(str, countries.get(str)+1);
			else
				countries.put(str, 1);
		}
		Set<String> countrySet = new HashSet<>();
		countrySet = countries.keySet();
		Iterator<String> it = countrySet.iterator();
	    while(it.hasNext()) {
	        String key = it.next();
	    System.out.println(key+"-"+countries.get(key));
	    }
	    int uniqueCountriesArrayList = countries.size();
	    int uniqueCountriesSet = countrySet.size();
	    System.out.println("Unique number of countries: "+uniqueCountriesArrayList);
	    //Collections.frequency can be used rather than Map
	    assertEquals(uniqueCountriesSet, uniqueCountriesArrayList);
	}
	@Test (priority = 10)
	public void uniqueTests() throws IOException {
	
		Set<String> citiesSet = new HashSet<>(citiesList);
		int uniqueCitiesSet = citiesSet.size();
		
		Map<String, Integer> cities = new HashMap<>();
		for (String str : citiesList) {
			if(cities.containsKey(str))
				cities.put(str, cities.get(str)+1);
			else
				cities.put(str, 1);
		}
		int uniqueCitiesArrayList = cities.size();
		System.out.println("Unique number of cities: "+uniqueCitiesArrayList);
		assertEquals(uniqueCitiesSet, uniqueCitiesArrayList);
	}
	//splits the city and country by the "," delimiter
	public String[] splitter (String str) {
		String[] arr = new String[2];
		arr = str.split(",");
		return arr;
	}
	public void addFields(String fieldType) throws InterruptedException {
	driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
	
	//dynamic xpath, finds all elements (field name) and takes the last one
	driver.findElement(By.xpath("(//input[@class='column-name form-control'])[last()]")).sendKeys(fieldType);
	chooseType(fieldType);
	}
	public void chooseType(String str) throws InterruptedException {
	//dynamic xpath, finds all elements (type) and takes the last one
	driver.findElement(By.xpath("(//input[@class='btn btn-default'])[last()]")).click();
	Thread.sleep(1000);	
	// attribute aria-hidden is "false" when type dialog box is open.
	assertFalse(Boolean.valueOf(driver.findElement(By.xpath("//div[@id='type_dialog']")).getAttribute("aria-hidden")));
    driver.findElement(By.xpath("//input[@id='type_search_field']")).sendKeys(str);
    driver.findElement(By.xpath("//div[@class='examples']")).click();
 	}
}