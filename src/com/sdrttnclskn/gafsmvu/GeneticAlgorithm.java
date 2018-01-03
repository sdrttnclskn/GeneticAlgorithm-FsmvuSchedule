package com.sdrttnclskn.gafsmvu;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class GeneticAlgorithm {

	private Data data;

	public GeneticAlgorithm(Data data) {
		super();
		this.data = data;
	}

	public Population evolve(Population population) {
		return mutatePopulation(crossoverPopulation(population));

	}

	Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(population.getSchedules().size(), data);
		IntStream.range(0, MainSchedule.NUMB_OF_ELITE_SCHEDULES)
				.forEach(x -> crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x)));

		IntStream.range(0, MainSchedule.NUMB_OF_ELITE_SCHEDULES).forEach(x -> {
			if (MainSchedule.MUTATION_RATE > Math.random()) {
				Schedule schedule1 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
				Schedule schedule2 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
				crossoverPopulation.getSchedules().set(x, crossoverSchedule(schedule1, schedule2));
			} else {
				crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x));
			}
		});

		return crossoverPopulation;
	}

	/******************* Crossover Methods *******************************/

	/***** OnePointCrossover *******/

	Schedule onePointCrossoverSchedule(Schedule schedule1, Schedule schedule2) {
		Schedule onePointCrossoverSchedule = new Schedule(data).initialize();

		int r1 = schedule1.getClasses().size();
		int index = (int) RandomNumbers.getInstance().getRandomNumber() * r1;
		IntStream.range(0, onePointCrossoverSchedule.getClasses().size()).forEach(x -> {
			if (x > index)
				onePointCrossoverSchedule.getClasses().set(x, schedule1.getClasses().get(x));
			else
				onePointCrossoverSchedule.getClasses().set(x, schedule2.getClasses().get(x));
		});
		return onePointCrossoverSchedule;
	}

	/***** TwoPointCrossover ******/

	Schedule twoPointCrossoverSchedule(Schedule schedule1, Schedule schedule2) {
		Schedule twoPointCrossoverSchedule = new Schedule(data).initialize();

		int firstIndex = (int) (schedule1.getClasses().size() * RandomNumbers.getInstance().getRandomNumber());
		int secondIndex = (int) (schedule2.getClasses().size() * RandomNumbers.getInstance().getRandomNumber());
		IntStream.range(0, twoPointCrossoverSchedule.getClasses().size()).forEach(x -> {
			if (secondIndex > firstIndex)
				twoPointCrossoverSchedule.getClasses().set(x, schedule1.getClasses().get(x));
			else
				twoPointCrossoverSchedule.getClasses().set(x, schedule2.getClasses().get(x));
		});
		return twoPointCrossoverSchedule;
	}

	/*********** UniformCrossover *********/

	Schedule crossoverSchedule(Schedule schedule1, Schedule schedule2) {
		Schedule crossoverSchedule = new Schedule(data).initialize();
		IntStream.range(0, crossoverSchedule.getClasses().size()).forEach(x -> {
			if (Math.random() > 0.5)
				crossoverSchedule.getClasses().set(x, schedule1.getClasses().get(x));
			else
				crossoverSchedule.getClasses().set(x, schedule2.getClasses().get(x));
		});
		return crossoverSchedule;
	}

	/************************************************************/

	Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(population.getSchedules().size(), data);
		ArrayList<Schedule> schedules = mutatePopulation.getSchedules();
		IntStream.range(0, MainSchedule.NUMB_OF_ELITE_SCHEDULES)
				.forEach(x -> schedules.set(x, population.getSchedules().get(x)));
		IntStream.range(MainSchedule.NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(x -> {
			schedules.set(x, mutateSchedule(population.getSchedules().get(x)));

		});
		return mutatePopulation;
	}

	Schedule mutateSchedule(Schedule mutateSchedule) {
		Schedule schedule = new Schedule(data).initialize();
		IntStream.range(0, mutateSchedule.getClasses().size()).forEach(x -> {
			if (MainSchedule.MUTATION_RATE > Math.random())
				mutateSchedule.getClasses().set(x, schedule.getClasses().get(x));
		});
		return mutateSchedule;
	}

	Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(MainSchedule.TOURNAMENT_SELECTION_SIZE, data);
		IntStream.range(0, MainSchedule.TOURNAMENT_SELECTION_SIZE).forEach(x -> {
			tournamentPopulation.getSchedules().set(x,
					population.getSchedules().get((int) (Math.random() * population.getSchedules().size())));

		});
		return tournamentPopulation;
	}
}
