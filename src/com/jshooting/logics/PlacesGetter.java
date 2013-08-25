package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
import com.jshooting.model.Place;
import com.jshooting.shootingDatabase.PlacesTable;
import java.util.Date;
import java.util.List;

/**
 * Using to get places
 *
 * @author pgalex
 */
public class PlacesGetter
{
	/**
	 * Table of places
	 */
	private PlacesTable placesTable;

	/**
	 * Create with places table
	 *
	 * @param placesTable table of places
	 * @throws IllegalArgumentException placesTable is null
	 */
	public PlacesGetter(PlacesTable placesTable) throws IllegalArgumentException
	{
		if (placesTable == null)
		{
			throw new IllegalArgumentException("placesTable is null");
		}

		this.placesTable = placesTable;
	}

	/**
	 * Get all places
	 *
	 * @return list of all places
	 * @throws ShootingLogicsErrorException error while getting places
	 */
	public List<Place> getAllPlaces() throws ShootingLogicsErrorException
	{
		try
		{
			return placesTable.getAllPlaces();
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}

	/**
	 * Get places that exists in period by its begin and end date
	 *
	 * @param periodDateFrom period date from (including)
	 * @param periodDateTo period date to (including)
	 * @return list of places exists in given period
	 * @throws IllegalArgumentException periodDateFrom is null, periodDateTo is
	 * null; periodDateFrom more than periodDateTo
	 * @throws ShootingLogicsErrorException error while getting
	 */
	public List<Place> getPlacesByPeriod(Date periodDateFrom, Date periodDateTo) throws IllegalArgumentException, ShootingLogicsErrorException
	{
		if (periodDateFrom == null)
		{
			throw new IllegalArgumentException("periodDateFrom is null");
		}
		if (periodDateTo == null)
		{
			throw new IllegalArgumentException("periodDateTo is null");
		}
		if (periodDateFrom.after(periodDateTo))
		{
			throw new IllegalArgumentException("periodDateFrom is after periodDateTo");
		}

		try
		{
			return placesTable.getPlacesByPeriod(periodDateFrom, periodDateTo);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}
}
