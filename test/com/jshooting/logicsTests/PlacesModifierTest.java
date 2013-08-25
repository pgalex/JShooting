package com.jshooting.logicsTests;

import com.jshooting.logics.PlacesModifier;
import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
import com.jshooting.model.Place;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of PlacesModifier class
 *
 * @author pgalex
 */
public class PlacesModifierTest
{
	@Test
	public void updatingPlaceWithNullBeginDate() throws ShootingLogicsErrorException
	{
		try
		{
			Place placeWithNullBeginDate = new Place();
			placeWithNullBeginDate.setBeginDate(null);
			placeWithNullBeginDate.setEndDate(new Date());

			PlacesModifier placesModifier = new PlacesModifier(new PlacesTableMock());
			placesModifier.updatePlace(placeWithNullBeginDate);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}

	@Test
	public void updatingPlaceWithNullEndDate() throws ShootingLogicsErrorException
	{
		try
		{
			Place placeWithNullEndDate = new Place();
			placeWithNullEndDate.setBeginDate(new Date());
			placeWithNullEndDate.setEndDate(null);

			PlacesModifier placesModifier = new PlacesModifier(new PlacesTableMock());
			placesModifier.updatePlace(placeWithNullEndDate);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}

	@Test
	public void updatingPlaceBeginDateAfterEnd() throws ShootingLogicsErrorException
	{
		try
		{
			Place placeWithBeginDateAfterEnd = new Place();
			placeWithBeginDateAfterEnd.setBeginDate(new Date(1001));
			placeWithBeginDateAfterEnd.setEndDate(new Date(1000));

			PlacesModifier placesModifier = new PlacesModifier(new PlacesTableMock());
			placesModifier.updatePlace(placeWithBeginDateAfterEnd);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}

	@Test
	public void updatingCorrectPlace() throws ShootingLogicsErrorException
	{
		Place correctPlace = new Place();
		correctPlace.setBeginDate(new Date(1000));
		correctPlace.setEndDate(new Date(1005));

		PlacesTableMock placesTableMock = new PlacesTableMock();
		PlacesModifier placesModifier = new PlacesModifier(placesTableMock);
		placesModifier.updatePlace(correctPlace);

		assertEquals(1, placesTableMock.updatePlaceCallCount);

	}
}