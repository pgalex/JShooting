package com.jshooting.modelTests;

import com.jshooting.model.Place;
import com.jshooting.model.ShootingTrainingType;
import com.jshooting.model.ShootingTrainingsFilter;
import com.jshooting.model.Sportsman;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of ShootingTrainingsFilter class
 *
 * @author pgalex
 */
public class ShootingTrainingsFilterTest
{
	@Test
	public void filterWithDatesTest()
	{
		ShootingTrainingsFilter filterWithDates = new ShootingTrainingsFilter(new ArrayList<Sportsman>(), new Date(1000), new Date(1005), new ArrayList<ShootingTrainingType>());
		
		assertFalse(filterWithDates.isPlaceUsedAsPeriod());
		assertNull(filterWithDates.getPlace());
		assertEquals(new Date(1000), filterWithDates.getDateFrom());
		assertEquals(new Date(1005), filterWithDates.getDateTo());
	}
	
	@Test
	public void filterWithPlacesTest()
	{	
		Place place = new Place();
		place.setBeginDate(new Date(1005));
		place.setEndDate(new Date(1010));
		ShootingTrainingsFilter filterWithPlace = new ShootingTrainingsFilter(new ArrayList<Sportsman>(), place, new ArrayList<ShootingTrainingType>());
		
		assertTrue(filterWithPlace.isPlaceUsedAsPeriod());
		assertNotNull(filterWithPlace.getPlace());
		assertEquals(new Date(1005), filterWithPlace.getDateFrom());
		assertEquals(new Date(1010), filterWithPlace.getDateTo());
	}
}