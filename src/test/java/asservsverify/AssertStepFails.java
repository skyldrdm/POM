package asservsverify;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertStepFails {
	
	@Test
	public void test1() {
	  int n = 5;
	  int k = 10;

	  Assert.assertEquals(n,k);
	  System.out.println("next step in Test1");
	  Assert.assertTrue(n>k);  
	}
	@Test
	public void test2() {
	  int n = 10;
	  int k = 5;

	  Assert.assertNotEquals(n,k);
	  System.out.println("next step in Test2");
	  Assert.assertTrue(n>k);
	}
}
