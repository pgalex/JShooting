package com.jshooting.shootingDatabase;

import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;

/**
 * Table of places
 *
 * @author pgalex
 */
public interface PlacesTable
{
	/**
	 * Get all places
	 *
	 * @return list of all plces in table. Empty if there is no places
	 * @throws DatabaseErrorException error while getting places
	 */
	public List<Place> getAllPlaces() throws DatabaseErrorException;

	/**
	 * Add new place
	 *
	 * @param placeToAdd adding place. Must be not null
	 * @throws IllegalArgumentException placeToAdd is null
	 * @throws DatabaseErrorException error while adding
	 */
	public void addPlace(Place placeToAdd) throws IllegalArgumentException, DatabaseErrorException;

	/**
	 * Update exists place
	 *
	 * @param placeToUpdate updating place. Must be not null
	 * @throws IllegalArgumentException placeToUpdate is null
	 * @throws DatabaseErrorException error while updating
	 */
	public void updatePlace(Place placeToUpdate) throws IllegalArgumentException, DatabaseErrorException;
}
