package com.jshooting.logicsTests;

import com.jshooting.logics.ShootingTrainingsStatisticsCalculator;
import com.jshooting.model.ShootingTraining;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of ShootingTrainingsStatisticsCalculator
 *
 * @author pgalex
 */
public class ShootingTrainingsTotalStatisticsCalculatorTest
{
	@Test
	public void gettingNotExistsLyingEffectivenessTest()
	{
		ShootingTraining training1 = new ShootingTraining();
		training1.setNumLyingInRest(0);
		training1.setNumLyingLoading(0);
		training1.setNumLyingCompetition(0);
		training1.setMissLyingInRest(0);
		training1.setMissLyingLoading(0);
		training1.setMissLyingCompetition(0);

		List<ShootingTraining> trainingsList = new ArrayList<ShootingTraining>();
		trainingsList.add(training1);

		ShootingTrainingsStatisticsCalculator calculator = new ShootingTrainingsStatisticsCalculator();
		calculator.calculateFor(trainingsList);
		try
		{
			calculator.getEffectivenessLying();
			fail();
		}
		catch (NullPointerException ex)
		{
			// ok
		}
	}

	@Test
	public void gettingNotExistsStandingEffectivenessTest()
	{
		ShootingTraining training1 = new ShootingTraining();
		training1.setNumStandingInRest(0);
		training1.setNumStandingLoading(0);
		training1.setNumStandingCompetition(0);
		training1.setMissStandingInRest(0);
		training1.setMissStandingLoading(0);
		training1.setMissStandingCompetition(0);

		List<ShootingTraining> trainingsList = new ArrayList<ShootingTraining>();
		trainingsList.add(training1);

		ShootingTrainingsStatisticsCalculator calculator = new ShootingTrainingsStatisticsCalculator();
		calculator.calculateFor(trainingsList);

		try
		{
			calculator.getEffectivenessStanding();
			fail();
		}
		catch (NullPointerException ex)
		{
			// ok
		}
	}

	@Test
	public void standingEffectivenessNotExistsTest()
	{
		ShootingTraining training1 = new ShootingTraining();
		training1.setNumStandingInRest(0);
		training1.setNumStandingLoading(0);
		training1.setNumStandingCompetition(0);
		training1.setMissStandingInRest(0);
		training1.setMissStandingLoading(0);
		training1.setMissStandingCompetition(0);

		List<ShootingTraining> trainingsList = new ArrayList<ShootingTraining>();
		trainingsList.add(training1);

		ShootingTrainingsStatisticsCalculator calculator = new ShootingTrainingsStatisticsCalculator();
		calculator.calculateFor(trainingsList);

		assertFalse(calculator.isEffectivenessStandingExists());
	}

	@Test
	public void lyingEffectivenessNotExistsTest()
	{
		ShootingTraining training1 = new ShootingTraining();
		training1.setNumLyingInRest(0);
		training1.setNumLyingLoading(0);
		training1.setNumLyingCompetition(0);
		training1.setMissLyingInRest(0);
		training1.setMissLyingLoading(0);
		training1.setMissLyingCompetition(0);

		ShootingTraining training2 = new ShootingTraining();
		training2.setNumLyingInRest(0);
		training2.setNumLyingLoading(0);
		training2.setNumLyingCompetition(0);
		training2.setMissLyingInRest(0);
		training2.setMissLyingLoading(0);
		training2.setMissLyingCompetition(0);

		List<ShootingTraining> trainingsList = new ArrayList<ShootingTraining>();
		trainingsList.add(training1);
		trainingsList.add(training2);

		ShootingTrainingsStatisticsCalculator calculator = new ShootingTrainingsStatisticsCalculator();
		calculator.calculateFor(trainingsList);

		assertFalse(calculator.isEffectivenessLyingExists());
	}

	@Test
	public void calculationEffectivenessLyingTest()
	{
		ShootingTraining training1 = new ShootingTraining();
		training1.setNumLyingInRest(0);
		training1.setNumLyingLoading(10);
		training1.setNumLyingCompetition(10);
		training1.setMissLyingInRest(0);
		training1.setMissLyingLoading(2);
		training1.setMissLyingCompetition(2);

		ShootingTraining training2 = new ShootingTraining();
		training2.setNumLyingInRest(10);
		training2.setNumLyingLoading(0);
		training2.setNumLyingCompetition(10);
		training2.setMissLyingInRest(2);
		training2.setMissLyingLoading(0);
		training2.setMissLyingCompetition(2);

		ShootingTraining training3 = new ShootingTraining();
		training3.setNumLyingInRest(10);
		training3.setNumLyingLoading(10);
		training3.setNumLyingCompetition(0);
		training3.setMissLyingInRest(2);
		training3.setMissLyingLoading(2);
		training3.setMissLyingCompetition(0);

		List<ShootingTraining> trainingsList = new ArrayList<ShootingTraining>();
		trainingsList.add(training1);
		trainingsList.add(training2);
		trainingsList.add(training3);

		ShootingTrainingsStatisticsCalculator calculator = new ShootingTrainingsStatisticsCalculator();
		calculator.calculateFor(trainingsList);

		assertTrue(calculator.isEffectivenessLyingExists());
		assertEquals(80, calculator.getEffectivenessLying(), 0.0001);
	}

	@Test
	public void calculationEffectivenessStandingTest()
	{
		ShootingTraining training1 = new ShootingTraining();
		training1.setNumStandingInRest(0);
		training1.setNumStandingLoading(30);
		training1.setNumStandingCompetition(30);
		training1.setMissStandingInRest(0);
		training1.setMissStandingLoading(3);
		training1.setMissStandingCompetition(3);

		ShootingTraining training2 = new ShootingTraining();
		training2.setNumStandingInRest(30);
		training2.setNumStandingLoading(0);
		training2.setNumStandingCompetition(30);
		training2.setMissStandingInRest(3);
		training2.setMissStandingLoading(0);
		training2.setMissStandingCompetition(3);

		ShootingTraining training3 = new ShootingTraining();
		training3.setNumStandingInRest(30);
		training3.setNumStandingLoading(30);
		training3.setNumStandingCompetition(0);
		training3.setMissStandingInRest(3);
		training3.setMissStandingLoading(3);
		training3.setMissStandingCompetition(0);

		List<ShootingTraining> trainingsList = new ArrayList<ShootingTraining>();
		trainingsList.add(training1);
		trainingsList.add(training2);
		trainingsList.add(training3);

		ShootingTrainingsStatisticsCalculator calculator = new ShootingTrainingsStatisticsCalculator();
		calculator.calculateFor(trainingsList);

		assertTrue(calculator.isEffectivenessStandingExists());
		assertEquals(90, calculator.getEffectivenessStanding(), 0.0001);
	}

	@Test
	public void calculationDelayTest()
	{
		ShootingTraining training1 = new ShootingTraining();
		training1.setDelayLyingLoading(0);
		training1.setDelayLyingCompetition(30);

		training1.setDelayStandingLoading(45);
		training1.setDelayStandingCompetition(0);

		ShootingTraining training2 = new ShootingTraining();
		training2.setDelayLyingLoading(20);
		training2.setDelayLyingCompetition(0);

		training2.setDelayStandingLoading(0);
		training2.setDelayStandingCompetition(35);

		List<ShootingTraining> trainingsList = new ArrayList<ShootingTraining>();
		trainingsList.add(training1);
		trainingsList.add(training2);

		ShootingTrainingsStatisticsCalculator calculator = new ShootingTrainingsStatisticsCalculator();
		calculator.calculateFor(trainingsList);

		assertEquals(25, calculator.getAverageDelayLying(), 0.0001);
		assertEquals(40, calculator.getAverageDelayStanding(), 0.0001);

		assertEquals(0, calculator.getAverageFirstLying(), 0.0001);
		assertEquals(0, calculator.getAverageFirstStanding(), 0.0001);
		assertEquals(0, calculator.getTotalShoots());
		assertEquals(0, calculator.getTotalInRest());
		assertEquals(0, calculator.getTotalLoading());
		assertEquals(0, calculator.getTotalCompetition());
		assertEquals(0, calculator.getTotalTrail());
		assertEquals(0, calculator.getTotalScatt());
	}

	@Test
	public void calculationAverageFirstTest()
	{
		ShootingTraining training1 = new ShootingTraining();
		training1.setFirstLyingLoading(15);
		training1.setFirstLyingCompetition(0);

		training1.setFirstStandingLoading(0);
		training1.setFirstStandingCompetition(25);

		ShootingTraining training2 = new ShootingTraining();
		training2.setFirstLyingLoading(0);
		training2.setFirstLyingCompetition(5);

		training2.setFirstStandingLoading(35);
		training2.setFirstStandingCompetition(0);

		List<ShootingTraining> trainingsList = new ArrayList<ShootingTraining>();
		trainingsList.add(training1);
		trainingsList.add(training2);

		ShootingTrainingsStatisticsCalculator calculator = new ShootingTrainingsStatisticsCalculator();
		calculator.calculateFor(trainingsList);

		assertEquals(10, calculator.getAverageFirstLying(), 0.0001);
		assertEquals(30, calculator.getAverageFirstStanding(), 0.0001);

		assertEquals(0, calculator.getAverageDelayLying(), 0.0001);
		assertEquals(0, calculator.getAverageDelayStanding(), 0.0001);
		assertEquals(0, calculator.getTotalShoots());
		assertEquals(0, calculator.getTotalInRest());
		assertEquals(0, calculator.getTotalLoading());
		assertEquals(0, calculator.getTotalCompetition());
		assertEquals(0, calculator.getTotalTrail());
		assertEquals(0, calculator.getTotalScatt());
	}

	@Test
	public void calculationCommonStatisticsTest()
	{
		ShootingTraining training1 = new ShootingTraining();
		training1.setNumLyingInRest(5);
		training1.setNumLyingLoading(10);
		training1.setNumLyingCompetition(15);

		training1.setNumStandingInRest(20);
		training1.setNumStandingLoading(25);
		training1.setNumStandingCompetition(30);

		training1.setTrail(10);
		training1.setZeroingIn(15);
		training1.setScatt(2);

		ShootingTraining training2 = new ShootingTraining();
		training2.setNumLyingInRest(35);
		training2.setNumLyingLoading(40);
		training2.setNumLyingCompetition(45);

		training2.setTrail(20);
		training2.setZeroingIn(5);
		training2.setScatt(8);

		training2.setNumStandingInRest(50);
		training2.setNumStandingLoading(55);
		training2.setNumStandingCompetition(60);

		List<ShootingTraining> trainingsList = new ArrayList<ShootingTraining>();
		trainingsList.add(training1);
		trainingsList.add(training2);

		ShootingTrainingsStatisticsCalculator calculator = new ShootingTrainingsStatisticsCalculator();
		calculator.calculateFor(trainingsList);

		assertEquals(390 + 20/*zeroingin*/, calculator.getTotalShoots());
		assertEquals(110, calculator.getTotalInRest());
		assertEquals(130, calculator.getTotalLoading());
		assertEquals(150, calculator.getTotalCompetition());
		assertEquals(30, calculator.getTotalTrail());
		assertEquals(10, calculator.getTotalScatt());

		assertEquals(0, calculator.getAverageFirstLying(), 0.0001);
		assertEquals(0, calculator.getAverageFirstStanding(), 0.0001);
		assertEquals(0, calculator.getAverageDelayLying(), 0.0001);
		assertEquals(0, calculator.getAverageDelayStanding(), 0.0001);
	}
}