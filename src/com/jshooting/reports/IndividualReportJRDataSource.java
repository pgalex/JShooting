package com.jshooting.reports;

import com.jshooting.calculation.ShootingTrainingsStatisticsCalculator;
import com.jshooting.shootingDatabase.ShootingTraining;
import com.jshooting.shootingDatabase.ShootingTrainingType;
import com.jshooting.shootingDatabase.ShootingTrainingsFilter;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Data source for individual report
 *
 * @author pgalex
 */
public class IndividualReportJRDataSource implements JRDataSource
{
	/**
	 * Statistics calculator using to build report
	 */
	private ShootingTrainingsStatisticsCalculator statisticsCalculator;
	/**
	 * Index of currently processing training
	 */
	private int currentlyTrainingIndex;
	/**
	 * Trainings for report
	 */
	private List<ShootingTraining> reportingTrainings;

	/**
	 * Create for trainings defined by filter
	 *
	 * @param trainingsFilter filter of trainings which will be used for report.
	 * Must be not null. Must include only one sportsmans
	 * @param shootingTrainingsTable table of shootings trainings to get data
	 * from. Must be not null
	 * @throws IllegalArgumentException trainingsFilter or shootingTrainingsTable
	 * is null; or trainingsFilter not contains only one sportsmans
	 */
	public IndividualReportJRDataSource(ShootingTrainingsFilter trainingsFilter,
					ShootingTrainingsTable shootingTrainingsTable) throws IllegalArgumentException
	{
		if (trainingsFilter == null)
		{
			throw new IllegalArgumentException("trainingsFilter is null");
		}
		if (trainingsFilter.getSportsmans().size() >= 1)
		{
			throw new IllegalArgumentException("filter must contains one sportsman");
		}
		if (shootingTrainingsTable == null)
		{
			throw new IllegalArgumentException("shootingTrainingsTable is null");
		}

		currentlyTrainingIndex = -1;
		statisticsCalculator = new ShootingTrainingsStatisticsCalculator();

		try
		{
			reportingTrainings = shootingTrainingsTable.getTrainingsWithFilter(trainingsFilter);
		}
		catch (DatabaseErrorException ex)
		{
			reportingTrainings = new ArrayList<ShootingTraining>();
		}
	}

	@Override
	public boolean next() throws JRException
	{
		currentlyTrainingIndex++;
		statisticsCalculator.calculateFor(reportingTrainings.get(currentlyTrainingIndex));
		return currentlyTrainingIndex < reportingTrainings.size();
	}

	@Override
	public Object getFieldValue(JRField jrf) throws JRException
	{
		if ("trainingDate".equals(jrf.getName()))
		{
			return new SimpleDateFormat("dd.MM.yyyy").format(reportingTrainings.get(currentlyTrainingIndex).getDate());
		}
		else if ("trainingType".equals(jrf.getName()))
		{
			return ShootingTrainingType.toString(reportingTrainings.get(currentlyTrainingIndex).getType());
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
