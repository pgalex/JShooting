package com.jshooting.calculation;

import com.jshooting.shootingDatabase.ShootingTraining;
import java.util.List;

/**
 * Calculates total statistics values for list of trainings
 *
 * @author pgalex
 */
public class ShootingTrainingsTotalStatisticsCalculator
{
	/**
	 * Total number(sum) of shoots
	 */
	private int totalShoots;
	/**
	 * Total number of shoots - in rest
	 */
	private int totalInRest;
	/**
	 * Total number of shoots - loading
	 */
	private int totalLoading;
	/**
	 * Total number of shoots - competition
	 */
	private int totalCompetition;
	/**
	 * Total sum of trail minutes
	 */
	private int totalTrail;

	/**
	 * Create with zero values
	 */
	public ShootingTrainingsTotalStatisticsCalculator()
	{
		totalShoots = 0;
		totalInRest = 0;
		totalLoading = 0;
		totalCompetition = 0;
		totalTrail = 0;
	}

	/**
	 * Calculate statistics for given trainings
	 *
	 * @param trainings shooting trainings which statistics need to calculate.
	 * Must be not null, not contains null
	 * @throws IllegalArgumentException trainings is null or contains null
	 */
	public void calculateFor(List<ShootingTraining> trainings) throws IllegalArgumentException
	{
		if (!isTrainingsListCorrect(trainings))
		{
			throw new IllegalArgumentException("trainings incorrect");
		}

		totalShoots = 0;
		totalInRest = 0;
		totalLoading = 0;
		totalCompetition = 0;
		totalTrail = 0;

		for (ShootingTraining shootingTraining : trainings)
		{
			totalShoots += shootingTraining.getNumLyingInRest() + shootingTraining.getNumLyingLoading()
							+ shootingTraining.getNumLyingCompetition() + shootingTraining.getNumStandingInRest()
							+ shootingTraining.getNumStandingLoading() + shootingTraining.getNumStandingCompetition()
							+ shootingTraining.getZeroingIn();

			totalInRest += shootingTraining.getNumLyingInRest() + shootingTraining.getNumStandingInRest();

			totalLoading += shootingTraining.getNumLyingLoading() + shootingTraining.getNumStandingLoading();

			totalCompetition += shootingTraining.getNumLyingCompetition() + shootingTraining.getNumStandingCompetition();

			totalTrail += shootingTraining.getTrail();
		}
	}

	/**
	 * Is trainings list correct for calculation
	 *
	 * @param trainingListToTest testing list of shooting trainings
	 * @return is trainings list correct
	 */
	private boolean isTrainingsListCorrect(List<ShootingTraining> trainingListToTest)
	{
		if (trainingListToTest == null)
		{
			return false;
		}
		for (ShootingTraining shootingTraining : trainingListToTest)
		{
			if (shootingTraining == null)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Get total sum of shoots
	 *
	 * @return total sum of shoots
	 */
	public int getTotalShoots()
	{
		return totalShoots;
	}

	/**
	 * Get total sum of shoots in rest
	 *
	 * @return total sum of shoots in rest
	 */
	public int getTotalInRest()
	{
		return totalInRest;
	}

	/**
	 * Get total sum of shoots loading
	 *
	 * @return total sum of shoots loading
	 */
	public int getTotalLoading()
	{
		return totalLoading;
	}

	/**
	 * Get total sum of shoots competition
	 *
	 * @return total sum of shoots competition
	 */
	public int getTotalCompetition()
	{
		return totalCompetition;
	}

	/**
	 * Get total sum of trial minutes
	 *
	 * @return total sum of trial minutes
	 */
	public int getTotalTrail()
	{
		return totalTrail;
	}
}
