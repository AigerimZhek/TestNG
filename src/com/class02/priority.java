package com.class02;

import org.testng.annotations.Test;

public class priority {
	
	@Test()
	public static void first() {
		System.out.println("First test annotation");
	}

	@Test(priority=1)
	public static void second() {
		System.out.println("Second test annotation");
	}
	
	@Test(priority=2)
	public static void third() {
		System.out.println("Third test annotation");
	}

	@Test(priority=3)
	public static void fourth() {
		System.out.println("Fourth test annotation");
	}
}
