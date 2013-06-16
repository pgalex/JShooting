package com.jshooting.calculationTests;

import com.jshooting.calculation.ShootingTrainingsTotalStatisticsCalculator;
import com.jshooting.shootingDatabase.ShootingTraining;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of ShootingTrainingsTotalStatisticsCalculator
 *
 * @author pgalex
 */
public class ShootingTrainingsTotalStatisticsCalculatorTest
{
	/**
	 * Test calculating statistics
	 */
	@Test
	public void calculationTest()
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

		training1.setFirstLyingLoading(20);
		training1.setFirstLyingCompetition(10);

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

		training2.setFirstLyingLoading(5);
		training2.setFirstLyingCompetition(5);

		List<ShootingTraining> trainingsList = new ArrayList<ShootingTraining>();
		trainingsList.add(training1);
		trainingsList.add(training2);

		ShootingTrainingsTotalStatisticsCalculator calculator = new ShootingTrainingsTotalStatisticsCalculator();
		calculator.calculateFor(trainingsList);

		assertEquals(390 + 20/*zeroingin*/, calculator.getTotalShoots());
		assertEquals(110, calculator.getTotalInRest());
		assertEquals(130, calculator.getTotalLoading());
		assertEquals(150, calculator.getTotalCompetition());
		assertEquals(30, calculator.getTotalTrail());
		assertEquals(10, calculator.getTotalScatt());
		assertEquals(40 / 4, calculator.getAverageFirstLying(), 0.0001);
	}
}