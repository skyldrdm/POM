package asservsverify;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {
	
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void test1() {
		
		int i = 100;
		int j = 200;
		
		System.out.println("First Assertion:");
		softAssert.assertEquals(i, j, "i and j are not equals");
		
		System.out.println("Second Assertion:");
		softAssert.assertNotEquals(i, j);
		
		System.out.println("Third Assertion");
		softAssert.assertTrue( i > j , "i is not greater than j");
		
		softAssert.assertAll();
		
	}
}