package com.jshooting.reports;

import com.jshooting.calculation.ShootingTrainingsTotalStatisticsCalculator;
import com.jshooting.shootingDatabase.ShootingTrainingsFilter;
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
	private ShootingTrainingsTotalStatisticsCalculator statisticsCalculator;

	/**
	 * Create for trainings defined by filter
	 *
	 * @param trainingsFilter filter of trainings which will be used for report.
	 * Must be not null
	 */
	public CombinedReportJRDataSource(ShootingTrainingsFilter trainingsFilter)
	{
		if (trainingsFilter == null)
		{
			throw new IllegalArgumentException("trainingsFilter is null");
		}

		this.trainingsFilter = trainingsFilter;
		statisticsCalculator = new ShootingTrainingsTotalStatisticsCalculator();
		currentSportsmansIndex = -1;
	}

	@Override
	public boolean next() throws JRException
	{
		currentSportsmansIndex++;
		// получить тренировки спортсмена за пероид
		//statisticsCalculator.calculateFor(trainings);
		return currentSportsmansIndex < trainingsFilter.getSportsmans().size();
	}

	@Override
	public Object getFieldValue(JRField jrf) throws JRException
	{
		if ("number".equals(jrf.getName()))
		{
			return new Integer(currentSportsmansIndex + 1);
		}
		else if ("sportsmanName".equals(jrf.getName()))
		{
			return trainingsFilter.getSportsmans().get(currentSportsmansIndex).getName();
		}
		else
		{
			return 0;
		}
	}
}
