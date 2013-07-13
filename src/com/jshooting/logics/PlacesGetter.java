package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.Place;
import com.jshooting.shootingDatabase.PlacesTable;
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
	 * @throws ShootingLogicsException error while getting places
	 */
	public List<Place> getAllPlaces() throws ShootingLogicsException
	{
		try
		{
			return placesTable.getAllPlaces();
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsException(ex);
		}
	}
}
