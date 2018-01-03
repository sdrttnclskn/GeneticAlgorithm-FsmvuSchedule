package com.sdrttnclskn.datafsmvu;

import java.util.ArrayList;

public class Course {
	private String number = null;
	private String name = null;
	private int maxNumbOfStudents;
	private ArrayList<Instructor> instructors;

	public Course(String number, String name, int maxNumbOfStudents, ArrayList<Instructor> instructors) {
		super();
		this.number = number;
		this.name = name;
		this.maxNumbOfStudents = maxNumbOfStudents;
		this.instructors = instructors;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public int getMaxNumbOfStudents() {
		return maxNumbOfStudents;
	}

	public ArrayList<Instructor> getInstructors() {
		return instructors;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}

}
