package com.sdrttnclskn.datafsmvu;

public class Instructor {

	private String id;
	private String name;
	public Instructor(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Instructor [name=" + name + "]";
	}
	
	
}
