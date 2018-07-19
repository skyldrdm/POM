package com.cybertek;

import com.github.javafaker.Faker;

public class Faker1 {
	
	public static void main(String[] args) {
		
		Faker faker = new Faker();
		
		String creditCard = faker.finance().creditCard();
		
		String fullName= faker.name().fullName();
		
		System.out.println(creditCard);
		
		System.out.println(fullName);


	}

}