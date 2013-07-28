package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.Place;
import com.jshooting.shootingDatabase.PlacesTable;
import java.util.Calendar;

/**
 * Using to modify places
 *
 * @author pgalex
 */
public class PlacesModifier
{
	/**
	 * Places table
	 */
	private PlacesTable placesTable;

	/**
	 * Create with places table
	 *
	 * @param placesTable table of places. Must be not null
	 * @throws IllegalArgumentException placesTable is null
	 */
	public PlacesModifier(PlacesTable placesTable) throws IllegalArgumentException
	{
		if (placesTable == null)
		{
			throw new IllegalArgumentException("placesTable is null");
		}

		this.placesTable = placesTable;
	}

	/**
	 * Add new place with empty name and current day as dates
	 *
	 * @throws ShootingLogicsException error while adding
	 */
	public void addNewPlace() throws ShootingLogicsException
	{
		try
		{
			Place newPlace = new Place();
			newPlace.setName("");

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			newPlace.setBeginDate(calendar.getTime());

			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			newPlace.setEndDate(calendar.getTime());

			placesTable.addPlace(newPlace);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsException(ex);
		}
	}

	/**
	 * Update exists place
	 *
	 * @param placeToUpdate updating place. Must be not null, must be correct
	 * @throws IllegalArgumentException placeToUpdate is null or incorrect
	 * @throws ShootingLogicsException error while updating
	 */
	public void updatePlace(Place placeToUpdate) throws IllegalArgumentException, ShootingLogicsException
	{
		if (placeToUpdate == null)
		{
			throw new IllegalArgumentException("placeToUpdate is null");
		}
		if(!placeToUpdate.isCorrect())
		{
			throw new IllegalArgumentException("placeToUpdate incorrect");
		}

		try
		{
			placesTable.updatePlace(placeToUpdate);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsException(ex);
		}
	}
}
