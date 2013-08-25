package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
import com.jshooting.logics.exceptions.ShowingReportErrorException;
import com.jshooting.model.ShootingTrainingsFilter;
import com.jshooting.reports.CombinedReportJRDataSource;
import com.jshooting.reports.IndividualReportJRDataSource;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Using to make and show reports of shooting trainings
 *
 * @author pgalex
 */
public class ReportsMaker
{
	private static final String COMBINED_REPORT_FILE_NAME = "reports/combinedReport.jrxml";
	/**
	 * Date format for dates in report
	 */
	private DateFormat reportsDatesFormat;

	public ReportsMaker()
	{
		reportsDatesFormat = new SimpleDateFormat("dd.MM.yyyy");
	}

	/**
	 * Show individual report of shooting trainings with filter
	 *
	 * @param trainingsFilter filter that determines which trainings need to show
	 * in report. Must be not null
	 * @param trainingsTable shooting trainings table. Must be not null
	 * @throws IllegalArgumentException trainingsFilter or trainingsTable is null
	 * @throws ShowingReportErrorException error while showing report
	 */
	public void showIndividualReportWithFilter(ShootingTrainingsFilter trainingsFilter, ShootingTrainingsTable trainingsTable) throws IllegalArgumentException, ShowingReportErrorException
	{
		try
		{
			tryIndividualReportWithFilter(trainingsFilter, trainingsTable);
		}
		catch (Exception ex)
		{
			throw new ShowingReportErrorException(ex);
		}
	}

	private void tryIndividualReportWithFilter(ShootingTrainingsFilter trainingsFilter, ShootingTrainingsTable trainingsTable) throws IllegalArgumentException, JRException
	{
		if (trainingsFilter == null)
		{
			throw new IllegalArgumentException("trainingsFilter is null");
		}
		if (trainingsTable == null)
		{
			throw new IllegalArgumentException("trainingsTable is null");
		}

		IndividualReportJRDataSource reportDataSource = new IndividualReportJRDataSource(trainingsFilter, trainingsTable);

		Map<String, Object> parametersMap = new HashMap<String, Object>();
		DateFormat reportDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		parametersMap.put("DATE_FROM", reportDateFormat.format(trainingsFilter.getDateFrom()));
		parametersMap.put("DATE_TO", reportDateFormat.format(trainingsFilter.getDateTo()));
		if (trainingsFilter.isPlaceUsedAsPeriod())
		{
			parametersMap.put("PLACE_NAME", trainingsFilter.getPlace().getName());
		}
		else
		{
			parametersMap.put("PLACE_NAME", "");
		}
		if (trainingsFilter.getSportsmans().size() > 0)
		{
			parametersMap.put("SPORTSMAN_NAME", trainingsFilter.getSportsmans().get(0).getName());
		}

		JasperDesign desing = JRXmlLoader.load("reports/individualReport.jrxml");
		JasperReport report = JasperCompileManager.compileReport(desing);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametersMap, reportDataSource);
		JasperViewer.viewReport(jasperPrint, false);
	}

	/**
	 * Show combined report of shooting trainings with filter
	 *
	 * @param trainingsFilter filter that determines which trainings need to show
	 * in report. Must be not null
	 * @param trainingsTable shooting trainings table. Must be not null
	 * @throws IllegalArgumentException trainingsFilter or trainingsTable is null
	 * @throws ShowingReportErrorException error while showing report
	 */
	public void showCombinedReportWithFilter(ShootingTrainingsFilter trainingsFilter, ShootingTrainingsTable trainingsTable) throws IllegalArgumentException, ShowingReportErrorException
	{
		try
		{
			tryShowCombinedReportWithFilter(trainingsFilter, trainingsTable);
		}
		catch (Exception ex)
		{
			throw new ShowingReportErrorException(ex);
		}
	}

	private void tryShowCombinedReportWithFilter(ShootingTrainingsFilter trainingsFilter, ShootingTrainingsTable trainingsTable) throws IllegalArgumentException, JRException
	{
		if (trainingsFilter == null)
		{
			throw new IllegalArgumentException("trainingsFilter is null");
		}
		if (trainingsTable == null)
		{
			throw new IllegalArgumentException("trainingsTable is null");
		}

		CombinedReportJRDataSource reportDataSource = new CombinedReportJRDataSource(trainingsFilter, trainingsTable);

		Map<String, Object> parametersMap = new HashMap<String, Object>();

		parametersMap.put("DATE_FROM", reportsDatesFormat.format(trainingsFilter.getDateFrom()));
		parametersMap.put("DATE_TO", reportsDatesFormat.format(trainingsFilter.getDateTo()));
		if (trainingsFilter.isPlaceUsedAsPeriod())
		{
			parametersMap.put("PLACE_NAME", trainingsFilter.getPlace().getName());
		}
		else
		{
			parametersMap.put("PLACE_NAME", "");
		}

		JasperDesign desing = JRXmlLoader.load(COMBINED_REPORT_FILE_NAME);
		JasperReport report = JasperCompileManager.compileReport(desing);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametersMap, reportDataSource);
		JasperViewer.viewReport(jasperPrint, false);
	}
}
