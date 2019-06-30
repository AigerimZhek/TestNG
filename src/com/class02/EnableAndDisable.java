package com.class02;

import org.testng.annotations.Test;

public class EnableAndDisable {
	
	@Test(enabled=false)
	public static void first() {
		System.out.println("First test annotation");
	}

	@Test(enabled=true,priority=0)
	public static void second() {
		System.out.println("Second test annotation");
	}
	
	@Test(enabled=false)
	public static void third() {
		System.out.println("Third test annotation");
	}

	@Test(enabled=true,priority=1)
	public static void fourth() {
		System.out.println("Fourth test annotation");
	}

}
