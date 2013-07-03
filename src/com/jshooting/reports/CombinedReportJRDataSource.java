package com.jshooting.reports;

import com.jshooting.logics.ShootingTrainingsStatisticsCalculator;
import com.jshooting.model.ShootingTraining;
import com.jshooting.model.ShootingTrainingsFilter;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import com.jshooting.model.Sportsman;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Data source for combined report
 *
 * @author pgalex
 */
public class CombinedReportJRDataSource implements JRDataSource
{
	/**
	 * Filter of trainings which will be used for report
	 */
	private ShootingTrainingsFilter trainingsFilter;
	/**
	 * Index of currenlty processing sportsmans in list of sportsmans of filter
	 */
	private int currentSportsmansIndex;
	/**
	 * Statistics calculator using to build report
	 */
	private ShootingTrainingsStatisticsCalculator statisticsCalculator;
	/**
	 * Table of shootings trainings to get data from
	 */
	private ShootingTrainingsTable shootingTrainingsTable;

	/**
	 * Create for trainings defined by filter
	 *
	 * @param trainingsFilter filter of trainings which will be used for report.
	 * Must be not null
	 * @param shootingTrainingsTable table of shootings trainings to get data
	 * from. Must be not null
	 * @throws IllegalArgumentException trainingsFilter or shootingTrainingsTable
	 * is null
	 */
	public CombinedReportJRDataSource(ShootingTrainingsFilter trainingsFilter,
					ShootingTrainingsTable shootingTrainingsTable) throws IllegalArgumentException
	{
		if (trainingsFilter == null)
		{
			throw new IllegalArgumentException("trainingsFilter is null");
		}
		if (shootingTrainingsTable == null)
		{
			throw new IllegalArgumentException("shootingTrainingsTable is null");
		}

		this.trainingsFilter = trainingsFilter;
		this.statisticsCalculator = new ShootingTrainingsStatisticsCalculator();
		this.currentSportsmansIndex = -1;
		this.shootingTrainingsTable = shootingTrainingsTable;
	}

	@Override
	public boolean next() throws JRException
	{
		currentSportsmansIndex++;

		if (currentSportsmansIndex < trainingsFilter.getSportsmans().size())
		{
			calculateStatisticsForSportsmans(trainingsFilter.getSportsmans().get(currentSportsmansIndex));
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Recalculate statistics for sportsman
	 *
	 * @param sportsman sportsman calculate statistis for
	 * @throws IllegalArgumentException sportsman is null
	 */
	private void calculateStatisticsForSportsmans(Sportsman sportsman) throws IllegalArgumentException
	{
		if (sportsman == null)
		{
			throw new IllegalArgumentException("sportsman is null");
		}

		List<Sportsman> filterSportsmans = new ArrayList<Sportsman>();
		filterSportsmans.add(sportsman);
		ShootingTrainingsFilter filterForCurrentSportsman = new ShootingTrainingsFilter(filterSportsmans,
						trainingsFilter.getDateFrom(), trainingsFilter.getDateTo(), trainingsFilter.getTrainingTypes());

		List<ShootingTraining> currentSportsmanTrainings;
		try
		{
			currentSportsmanTrainings = shootingTrainingsTable.getTrainingsWithFilter(filterForCurrentSportsman);
		}
		catch (DatabaseErrorException ex)
		{
			currentSportsmanTrainings = new ArrayList<ShootingTraining>();
		}
		statisticsCalculator.calculateFor(currentSportsmanTrainings);
	}

	@Override
	public Object getFieldValue(JRField jrf) throws JRException
	{
		if ("number".equals(jrf.getName()))
		{
			return currentSportsmansIndex + 1;
		}
		else if ("sportsmanName".equals(jrf.getName()))
		{
			return trainingsFilter.getSportsmans().get(currentSportsmansIndex).getName();
		}
		else if ("totalShoots".equals(jrf.getName()))
		{
			return statisticsCalculator.getTotalShoots();
		}
		else if ("totalInRest".equals(jrf.getName()))
		{
			return statisticsCalculator.getTotalInRest();
		}
		else if ("totalLoading".equals(jrf.getName()))
		{
			return statisticsCalculator.getTotalLoading();
		}
		else if ("totalCompetition".equals(jrf.getName()))
		{
			return statisticsCalculator.getTotalCompetition();
		}
		else if ("totalTrial".equals(jrf.getName()))
		{
			return statisticsCalculator.getTotalTrail();
		}
		else if ("totalScatt".equals(jrf.getName()))
		{
			return statisticsCalculator.getTotalScatt();
		}
		else if ("averageFirstLying".equals(jrf.getName()))
		{
			return Math.round(statisticsCalculator.getAverageFirstLying() * 10.0) / 10.0;
		}
		else if ("averageFirstStanding".equals(jrf.getName()))
		{
			return Math.round(statisticsCalculator.getAverageFirstStanding() * 10.0) / 10.0;
		}
		else if ("averageDelayLying".equals(jrf.getName()))
		{
			return Math.round(statisticsCalculator.getAverageDelayLying() * 10.0) / 10.0;
		}
		else if ("averageDelayStanding".equals(jrf.getName()))
		{
			return Math.round(statisticsCalculator.getAverageDelayStanding() * 10.0) / 10.0;
		}
		else if ("effectivenessLying".equals(jrf.getName()))
		{
			return Math.round(statisticsCalculator.getEffectivenessLying() * 10.0) / 10.0;
		}
		else if ("effectivenessStanding".equals(jrf.getName()))
		{
			return Math.round(statisticsCalculator.getEffectivenessStanding() * 10.0) / 10.0;
		}
		else if ("effectivenessAverage".equals(jrf.getName()))
		{
			return Math.round(statisticsCalculator.getAverageEffectiveness() * 10.0) / 10.0;
		}
		else
		{
			return 0;
		}
	}
}
