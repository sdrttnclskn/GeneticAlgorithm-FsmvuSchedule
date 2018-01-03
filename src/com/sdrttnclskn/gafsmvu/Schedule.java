package com.sdrttnclskn.gafsmvu;

import java.util.ArrayList;
import java.util.Arrays;

import com.sdrttnclskn.datafsmvu.Class;
import com.sdrttnclskn.datafsmvu.Department;

public class Schedule {

	private ArrayList<Class> classes;
	private boolean isFitnessChanged = true;
	private double fitness = -1;
	private int numOfConflicts = 0;
	private int classNumb = 0;
	private Data data;

	public Data getData() {
		return data;
	}

	public Schedule(Data data) {
		super();
		this.data = data;
		classes = new ArrayList<Class>(data.getNumberOfClasses());

	}

	public Schedule initialize() {
		new ArrayList<Department>(data.getDepts()).forEach(dept -> {
			dept.getCourses().forEach(course -> {
				Class newClass = new Class(classNumb++, dept, course);
				newClass.setMeetingTime(
						data.getMeetingTimes().get((int) (data.getMeetingTimes().size() * Math.random())));
				newClass.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
				newClass.setInstructor(
						course.getInstructors().get((int) (course.getInstructors().size() * Math.random())));
				classes.add(newClass);
			});

		});
		return this;
	}

	public int getNumOfConflicts() {
		return numOfConflicts;
	}

	public ArrayList<Class> getClasses() {
		isFitnessChanged = true;
		return classes;
	}

	public double getFitness() {
		if (isFitnessChanged == true) {
			fitness = calculateFitness();
			isFitnessChanged = false;
		}
		return fitness;
	}

	public double calculateFitness() {
		numOfConflicts = 0;
		classes.forEach(x -> {
			if (x.getRoom().getSeatingCapacity() < x.getCourse().getMaxNumbOfStudents())
				numOfConflicts++;
			classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> {
				if (x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId()) {
					if (x.getRoom() == y.getRoom())
						numOfConflicts++;
					if (x.getInstructor() == y.getInstructor())
						numOfConflicts++;
				}
			});

		});

		return 1 / (double) (numOfConflicts + 1);

	}

	@Override
	public String toString() {
		String returnValue = new String();
		for (int x = 0; x < classes.size() - 1; x++)
			returnValue += classes.get(x) + ",";
		returnValue += classes.get(classes.size() - 1);

		return returnValue;

	}
}
