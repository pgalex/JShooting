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
	 * Total scatt in minutes
	 */
	private int totalScatt;
	/**
	 * Average of time to first - lying
	 */
	private double averageFirstLying;
	/**
	 * Average of time to first - standing
	 */
	private double averageFirstStanding;
	/**
	 * Average time of shooting (delay)- lying
	 */
	private double averageDelayLying;
	/**
	 * Average time of shooting (delay)- standing
	 */
	private double averageDelayStanding;

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
		totalScatt = 0;
		averageFirstLying = 0;
		averageFirstStanding = 0;
		averageDelayLying = 0;
		averageDelayStanding = 0;
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
		totalScatt = 0;

		averageFirstLying = 0;
		int averageFirstLyingNum = 0;

		averageFirstStanding = 0;
		int averageFirstStandingNum = 0;

		averageDelayLying = 0;
		int averageDelayLyingNum = 0;

		averageDelayStanding = 0;
		int averageDelayStandingNum = 0;

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
			totalScatt += shootingTraining.getScatt();

			if (shootingTraining.getFirstLyingLoading() > 0)
			{
				averageFirstLying += shootingTraining.getFirstLyingLoading();
				averageFirstLyingNum++;
			}
			if (shootingTraining.getFirstLyingCompetition() > 0)
			{
				averageFirstLying += shootingTraining.getFirstLyingCompetition();
				averageFirstLyingNum++;
			}

			if (shootingTraining.getFirstStandingLoading() > 0)
			{
				averageFirstStanding += shootingTraining.getFirstStandingLoading();
				averageFirstStandingNum++;
			}
			if (shootingTraining.getFirstStandingCompetition() > 0)
			{
				averageFirstStanding += shootingTraining.getFirstStandingCompetition();
				averageFirstStandingNum++;
			}

			if (shootingTraining.getDelayLyingLoading() > 0)
			{
				averageDelayLying += shootingTraining.getDelayLyingLoading();
				averageDelayLyingNum++;
			}
			if (shootingTraining.getDelayLyingCompetition() > 0)
			{
				averageDelayLying += shootingTraining.getDelayLyingCompetition();
				averageDelayLyingNum++;
			}

			if (shootingTraining.getDelayStandingLoading() > 0)
			{
				averageDelayStanding += shootingTraining.getDelayStandingLoading();
				averageDelayStandingNum++;
			}

			if (shootingTraining.getDelayStandingCompetition() > 0)
			{
				averageDelayStanding += shootingTraining.getDelayStandingCompetition();
				averageDelayStandingNum++;
			}
		}

		if (averageFirstLyingNum > 0)
			averageFirstLying /= averageFirstLyingNum;

		if (averageFirstStandingNum > 0)
			averageFirstStanding /= averageFirstStandingNum;

		if (averageDelayLyingNum > 0)
			averageDelayLying /= averageDelayLyingNum;

		if (averageDelayStandingNum > 0)
			averageDelayStanding /= averageDelayStandingNum;
	}

	/**
	 * Is trainings list correct for calculation
	 *
	 * @param trainingsListToTest testing list of shooting trainings
	 * @return is trainings list correct
	 */
	private boolean isTrainingsListCorrect(List<ShootingTraining> trainingsListToTest)
	{
		if (trainingsListToTest == null)
		{
			return false;
		}
		for (ShootingTraining shootingTraining : trainingsListToTest)
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

	/**
	 * Total scatt in minutes
	 *
	 * @return the totalScatt
	 */
	public int getTotalScatt()
	{
		return totalScatt;
	}

	/**
	 * Average of time to first lying
	 *
	 * @return the averageFirstLying
	 */
	public double getAverageFirstLying()
	{
		return averageFirstLying;
	}

	/**
	 * Average of time to first - standing
	 *
	 * @return the averageFirstStanding
	 */
	public double getAverageFirstStanding()
	{
		return averageFirstStanding;
	}

	/**
	 * Average time of shooting - lying
	 *
	 * @return the averageDelayLying
	 */
	public double getAverageDelayLying()
	{
		return averageDelayLying;
	}

	/**
	 * Average time of shooting - standing
	 *
	 * @return the averageDelayStanding
	 */
	public double getAverageDelayStanding()
	{
		return averageDelayStanding;
	}
}
