package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
import com.jshooting.model.Place;
import com.jshooting.shootingDatabase.PlacesTable;

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
	 * Add place
	 *
	 * @param placeToAdd adding place. Must be not null and correct
	 * @throws IllegalArgumentException placeToAdd is null or incorrect
	 * @throws ShootingLogicsErrorException error while adding
	 */
	public void addPlace(Place placeToAdd) throws IllegalArgumentException, ShootingLogicsErrorException
	{
		if (placeToAdd == null)
		{
			throw new IllegalArgumentException("placeToAdd is null");
		}
		if (!placeToAdd.isCorrect())
		{
			throw new IllegalArgumentException("placeToAdd incorrect");
		}

		try
		{
			placesTable.addPlace(placeToAdd);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}

	/**
	 * Update exists place
	 *
	 * @param placeToUpdate updating place. Must be not null, must be correct
	 * @throws IllegalArgumentException placeToUpdate is null or incorrect
	 * @throws ShootingLogicsErrorException error while updating
	 */
	public void updatePlace(Place placeToUpdate) throws IllegalArgumentException, ShootingLogicsErrorException
	{
		if (placeToUpdate == null)
		{
			throw new IllegalArgumentException("placeToUpdate is null");
		}
		if (!placeToUpdate.isCorrect())
		{
			throw new IllegalArgumentException("placeToUpdate incorrect");
		}

		try
		{
			placesTable.updatePlace(placeToUpdate);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}
}
