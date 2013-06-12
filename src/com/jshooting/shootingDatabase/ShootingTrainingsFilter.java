package com.jshooting.shootingDatabase;

import java.util.Date;
import java.util.List;

/**
 * Filter of shooting trainings
 *
 * @author pgalex
 */
public class ShootingTrainingsFilter
{
	/**
	 * Sportsmans which trainings need to use
	 */
	private List<Sportsman> sportsmans;
	/**
	 * Trainings begin date (including). Must be before or equals dateTo
	 */
	private Date dateFrom;
	/**
	 * Trainings end date (including). Must be after or equals dateTo
	 */
	private Date dateTo;
	/**
	 * Trainings types
	 */
	private List<ShootingTrainingType> trainingTypes;

	/**
	 * Create with filtering options
	 *
	 * @param sportsmans sportsmans which trainings need to use. Must be not null,
	 * not contains null
	 * @param dateFrom trainings begin date (including). Must be before or equals
	 * dateTo
	 * @param dateTo trainings end date (including). Must be after or equals
	 * dateTo
	 * @param trainingTypes trainings types. Must be not null, not contains null
	 * @throws IllegalArgumentException sportsmans is null or contains empty;
	 * dateFrom or dateTo is null or dateFrom > dateTo; trainingTypes is null or
	 * contains null
	 */
	public ShootingTrainingsFilter(List<Sportsman> sportsmans, Date dateFrom, Date dateTo,
					List<ShootingTrainingType> trainingTypes) throws IllegalArgumentException
	{
		if (!isSportsmansCorrect(sportsmans))
		{
			throw new IllegalArgumentException("sportsmans incorrect");
		}
		if (!isDatesCorrect(dateFrom, dateTo))
		{
			throw new IllegalArgumentException("dates incorrect");
		}
		if (!isTrainingTypesCorrect(trainingTypes))
		{
			throw new IllegalArgumentException("trainingTypes incorrect");
		}

		this.sportsmans = sportsmans;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.trainingTypes = trainingTypes;
	}

	/**
	 * Test is list of filtering trainings types correct
	 *
	 * @param trainingTypesToTest testing list of training types
	 * @return is list of filtering trainings types correct
	 */
	private boolean isTrainingTypesCorrect(List<ShootingTrainingType> trainingTypesToTest)
	{
		if (trainingTypesToTest == null)
		{
			return false;
		}
		for (ShootingTrainingType shootingTrainingType : trainingTypesToTest)
		{
			if (shootingTrainingType == null)
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Test is dates correct
	 *
	 * @param dateFrom "date from" to test
	 * @param dateTo "date to" to test
	 * @return is dates correct
	 */
	private boolean isDatesCorrect(Date dateFrom, Date dateTo)
	{
		if (dateFrom == null || dateTo == null)
		{
			return false;
		}
		if (dateFrom.after(dateTo))
		{
			return false;
		}

		return true;
	}

	/**
	 * Is list of sportsmans correct for filter
	 *
	 * @param sportsmansListToTest list of sportsmans to test for correction
	 * @return is list of sportsmans correct
	 */
	private boolean isSportsmansCorrect(List<Sportsman> sportsmansListToTest)
	{
		if (sportsmansListToTest == null)
		{
			return false;
		}
		for (Sportsman sportsman : sportsmansListToTest)
		{
			if (sportsman == null)
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Sportsmans which trainings need to use
	 *
	 * @return the sportsmans
	 */
	public List<Sportsman> getSportsmans()
	{
		return sportsmans;
	}

	/**
	 * Trainings begin date (including)
	 *
	 * @return the dateFrom. Always before or equals dateTo
	 */
	public Date getDateFrom()
	{
		return dateFrom;
	}

	/**
	 * Trainings end date (including)
	 *
	 * @return the dateTo. Always after or equals dateFrom
	 */
	public Date getDateTo()
	{
		return dateTo;
	}

	/**
	 * Trainings types
	 *
	 * @return the trainingTypes
	 */
	public List<ShootingTrainingType> getTrainingTypes()
	{
		return trainingTypes;
	}
}
