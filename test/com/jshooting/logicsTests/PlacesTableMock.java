package com.jshooting.logicsTests;

import com.jshooting.model.Place;
import com.jshooting.shootingDatabase.PlacesTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pgalex
 */
public class PlacesTableMock implements PlacesTable
{
	public int updatePlaceCallCount;

	public PlacesTableMock()
	{
		updatePlaceCallCount = 0;
	}

	@Override
	public List<Place> getAllPlaces() throws DatabaseErrorException
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Place> getPlacesByPeriod(Date periodDateFrom, Date periodDateTo) throws IllegalArgumentException, DatabaseErrorException
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void addPlace(Place placeToAdd) throws IllegalArgumentException, DatabaseErrorException
	{
	}

	@Override
	public void updatePlace(Place placeToUpdate) throws IllegalArgumentException, DatabaseErrorException
	{
		updatePlaceCallCount++;
	}
}
