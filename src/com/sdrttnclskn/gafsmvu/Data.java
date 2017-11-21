package com.sdrttnclskn.gafsmvu;

import java.util.ArrayList;
import java.util.Arrays;

import com.sdrttnclskn.datafsmvu.Course;
import com.sdrttnclskn.datafsmvu.Department;
import com.sdrttnclskn.datafsmvu.Instructor;
import com.sdrttnclskn.datafsmvu.MeetingTime;
import com.sdrttnclskn.datafsmvu.Room;

public class Data {
	private ArrayList<Room> rooms;
	private ArrayList<Instructor> instructors;
	private ArrayList<Course> courses;
	private ArrayList<Department> depts;
	private ArrayList<MeetingTime>meetingTimes;
	private int numberOfClasses = 0;
	
	public Data () {
		initialize();
	}
		
	private Data initialize () {
		//new rooms
		Room room1 = new Room("R1", 20);
		Room room2 = new Room("R2", 25);
		Room room3 = new Room("R3", 35);
		rooms = new ArrayList<Room>(Arrays.asList(room1,room2,room3));
		//new meeting Times
		MeetingTime meetingTime1 = new MeetingTime("MT1", "PÇC 09:00 - 10:00");
		MeetingTime meetingTime2 = new MeetingTime("MT2", "PÇC 10:00 - 11:00");
		MeetingTime meetingTime3 = new MeetingTime("MT3", "SPH 09:00 - 10:30");
		MeetingTime meetingTime4 = new MeetingTime("MT4", "SPH 10:30 - 12:00");
		meetingTimes= new ArrayList<MeetingTime>(Arrays.asList(meetingTime1,meetingTime2,meetingTime3,meetingTime4));
		// new instuctors
		Instructor instructor1 = new Instructor("I1", "Dr.Ebubekir Koç       ");
		Instructor instructor2 = new Instructor("I2", "Prf.Dr.Burhannetin Can");
		Instructor instructor3 = new Instructor("I3", "Prf.Dr.Fevzi Yılmaz   ");
		Instructor instructor4 = new Instructor("I4", "Dr.Berna Kiraz        ");
		instructors = new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2,instructor3,instructor4));
		// new course
		Course course1 = new Course("C1", "325K", 25, new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2,instructor4)));
		Course course2 = new Course("C2", "319K", 35, new ArrayList<Instructor>(Arrays.asList(instructor4,instructor2,instructor3)));
		Course course3 = new Course("C3", "416K", 20, new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2)));
		Course course4 = new Course("C4", "464K", 25, new ArrayList<Instructor>(Arrays.asList(instructor3,instructor4)));
		Course course5 = new Course("C5", "360C", 35, new ArrayList<Instructor>(Arrays.asList(instructor4)));
		Course course6 = new Course("C6", "303K", 35, new ArrayList<Instructor>(Arrays.asList(instructor1,instructor3)));
		Course course7 = new Course("C7", "303LK", 20, new ArrayList<Instructor>(Arrays.asList(instructor2,instructor4)));
		courses = new ArrayList<Course>(Arrays.asList(course1,course2,course3,course4,course5,course6,course7));
		
		// new dept
		Department dept1 = new Department("BLM", new ArrayList<Course>(Arrays.asList(course1,course4)));
		Department dept2 = new Department("EEM", new ArrayList<Course>(Arrays.asList(course2,course4,course5)));
		Department dept3 = new Department("BMD", new ArrayList<Course>(Arrays.asList(course6,course7)));
		depts = new ArrayList<Department>(Arrays.asList(dept1,dept2,dept3));
		depts.forEach(x -> numberOfClasses += x.getCourses().size());
		
		
		return this;
		
	}	
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	public ArrayList<Instructor> getInstructors() {
		return instructors;
	}
	public ArrayList<Course> getCourses() {
		return courses;
	}
	public ArrayList<Department> getDepts() {
		return depts;
	}
	public ArrayList<MeetingTime> getMeetingTimes() {
		return meetingTimes;
	}
	public int getNumberOfClasses() {
		return numberOfClasses;
	}
	
}
