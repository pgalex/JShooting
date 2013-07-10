package com.jshooting.reports;

import com.jshooting.logics.ShootingTrainingsStatisticsCalculator;

/**
 * Common part of shooting statistics jasper reports data source
 *
 * @author pgalex
 */
public class ReportsDataSourceFieldValuesFormer
{
	public static String determineAverageEffectivenessFieldValue(ShootingTrainingsStatisticsCalculator statisticsCalculator) throws IllegalArgumentException
	{
		if (statisticsCalculator == null)
		{
			throw new IllegalArgumentException("statisticsCalculator is null");
		}

		if (statisticsCalculator.isAverageEffectivenessExists())
		{
			return determineDoubleFieldValue(statisticsCalculator.getAverageEffectiveness());
		}
		else
		{
			return "";
		}
	}
	
	public static String determineStandingEffectivenessFieldValue(ShootingTrainingsStatisticsCalculator statisticsCalculator) throws IllegalArgumentException
	{
		if (statisticsCalculator == null)
		{
			throw new IllegalArgumentException("statisticsCalculator is null");
		}

		if (statisticsCalculator.isEffectivenessStandingExists())
		{
			return determineDoubleFieldValue(statisticsCalculator.getEffectivenessStanding());
		}
		else
		{
			return "";
		}
	}

	public static String determineLyingEffectivenessFieldValue(ShootingTrainingsStatisticsCalculator statisticsCalculator) throws IllegalArgumentException
	{
		if (statisticsCalculator == null)
		{
			throw new IllegalArgumentException("statisticsCalculator is null");
		}

		if (statisticsCalculator.isEffectivenessLyingExists())
		{
			return determineDoubleFieldValue(statisticsCalculator.getEffectivenessLying());
		}
		else
		{
			return "";
		}
	}

	public static String determineIntFieldValue(int value)
	{
		if (value > 0)
		{
			return new Integer(value).toString();
		}
		else
		{
			return "";
		}
	}

	public static String determineDoubleFieldValue(double value)
	{
		if (value > 0)
		{
			return new Double(Math.round(value * 10.0) / 10.0).toString();
		}
		else
		{
			return "";
		}
	}
}
