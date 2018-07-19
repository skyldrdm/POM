package com.cybertek;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AssertionsDemo {
	
	@AfterMethod //guarantee run
	public void tearDownMethod() {
		System.out.println("cleaning up");
	}
	@Test
	public void testOne() {
		System.out.println("asserting first");
		Assert.assertTrue(true);
		System.out.println("done asserting first");
	}
	@Test
	public void testTwo() {
		System.out.println("asserting second");
		Assert.assertTrue(true);
		System.out.println("done asserting second");
	}
}