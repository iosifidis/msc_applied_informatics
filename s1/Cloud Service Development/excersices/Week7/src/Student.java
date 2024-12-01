package com.example.demo.hello;

public class Student {
	private String name;
	private int age;
	private String location;
	
	public Student(String n, int a, String l) {
		name = n;
		age = a;
		location =l;
	}
	
	public String getName() {return name;}
	public int getAge() {return age;}
	public String getLocation() {return location;}
	
}
