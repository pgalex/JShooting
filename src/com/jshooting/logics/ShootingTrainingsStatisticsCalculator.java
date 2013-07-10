package com.jshooting.logics;

import com.jshooting.model.ShootingTraining;
import java.util.ArrayList;
import java.util.List;

/**
 * Calculates statistics values for shooting trainings
 *
 * @author pgalex
 */
public class ShootingTrainingsStatisticsCalculator
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
	 * Total number of shoots - lying
	 */
	private int totalShootsLying;
	/**
	 * Total number of miss shoots - lying
	 */
	private int totalMissLying;
	/**
	 * Total number of shoots - standing
	 */
	private int totalShootsStanding;
	/**
	 * Total number of miss shoots - standing
	 */
	private int totalMissStanding;

	/**
	 * Create with zero values
	 */
	public ShootingTrainingsStatisticsCalculator()
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
		totalShootsLying = 0;
		totalMissLying = 0;
		totalShootsStanding = 0;
		totalMissStanding = 0;
	}

	/**
	 * Calculate statistics for given training
	 *
	 * @param training shooting training which statistics need to calculate. Must
	 * be not null
	 * @throws IllegalArgumentException training is null
	 */
	public void calculateFor(ShootingTraining training) throws IllegalArgumentException
	{
		if (training == null)
		{
			throw new IllegalArgumentException("training is null");
		}

		ArrayList<ShootingTraining> listOfTrainings = new ArrayList<ShootingTraining>();
		listOfTrainings.add(training);
		calculateFor(listOfTrainings);
	}

	/**
	 * Calculate statistics for given list of trainings
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
		totalShootsLying = 0;
		totalMissLying = 0;
		totalShootsStanding = 0;
		totalMissStanding = 0;

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

			totalShootsLying += shootingTraining.getNumLyingInRest() + shootingTraining.getNumLyingLoading()
							+ shootingTraining.getNumLyingCompetition();
			totalMissLying += shootingTraining.getMissLyingInRest() + shootingTraining.getMissLyingLoading()
							+ shootingTraining.getMissLyingCompetition();

			totalShootsStanding += shootingTraining.getNumStandingInRest() + shootingTraining.getNumStandingLoading()
							+ shootingTraining.getNumStandingCompetition();
			totalMissStanding += shootingTraining.getMissStandingInRest() + shootingTraining.getMissStandingLoading()
							+ shootingTraining.getMissStandingCompetition();
		}

		if (averageFirstLyingNum > 0)
			averageFirstLying /= (double) averageFirstLyingNum;

		if (averageFirstStandingNum > 0)
			averageFirstStanding /= (double) averageFirstStandingNum;

		if (averageDelayLyingNum > 0)
			averageDelayLying /= (double) averageDelayLyingNum;

		if (averageDelayStandingNum > 0)
			averageDelayStanding /= (double) averageDelayStandingNum;
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

	/**
	 * Effectiveness of all shoots - lying
	 *
	 * @return the effectivenessLying
	 * @throws NullPointerException effectiveness lying not exists (can not be
	 * computed)
	 */
	public double getEffectivenessLying() throws NullPointerException
	{
		if (!isEffectivenessLyingExists())
		{
			throw new NullPointerException("effectiveness lying not exists");
		}

		return (100.0 - ((double) totalMissLying / (double) totalShootsLying * 100.0));
	}

	/**
	 * Effectiveness of all shoots - standing in percent
	 *
	 * @return the effectivenessStanding
	 * @throws NullPointerException effectiveness standing not exists (can not be
	 * computed)
	 */
	public double getEffectivenessStanding() throws NullPointerException
	{
		if (!isEffectivenessStandingExists())
		{
			throw new NullPointerException("effectiveness lying not exists");
		}

		return (100.0 - ((double) totalMissStanding / (double) totalShootsStanding * 100.0));
	}

	/**
	 * Is effectiveness lying exists. Effectiveness not exists if there is no
	 * shoots lying
	 *
	 * @return is effectiveness lying exists
	 */
	public boolean isEffectivenessLyingExists()
	{
		return totalShootsLying > 0;
	}

	/**
	 * Is effectiveness standing exists. Effectiveness not exists if there is no
	 * shoots standing
	 *
	 * @return is effectiveness standing exists
	 */
	public boolean isEffectivenessStandingExists()
	{
		return totalShootsStanding > 0;
	}

	/**
	 * Is average effectiveness exists. Effectiveness exists if effectiveness
	 * lying or effectiveness standing exists
	 *
	 * @return is average effectiveness exists
	 */
	public boolean isAverageEffectivenessExists()
	{
		return isEffectivenessLyingExists() || isEffectivenessStandingExists();
	}

	/**
	 * Get average standing and lying effectiveness
	 *
	 * @return average of standing and lying effectiveness
	 * @throws NullPointerException average effectiveness not exists
	 */
	public double getAverageEffectiveness() throws NullPointerException
	{
		if (isAverageEffectivenessExists())
		{
			throw new NullPointerException("average effectiveness not exists");
		}

		if (isEffectivenessLyingExists())
		{
			if (isEffectivenessStandingExists())
			{
				return (getEffectivenessLying() + getEffectivenessStanding()) / 2.0;
			}
			else
			{
				return getEffectivenessLying();
			}
		}
		else
		{
			if (isEffectivenessStandingExists())
			{
				return getEffectivenessStanding();
			}
			else
			{
				// equals to isAverageEffectivenessExists
				throw new NullPointerException("average effectiveness not exists");
			}
		}
	}
}
