package com.jshooting.modelTests;

import com.jshooting.model.Place;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of Place class
 *
 * @author pgalex
 */
public class PlaceTest
{
	@Test
	public void isCorrecteginDateBNullTest()
	{
		Place place = new Place();
		place.setBeginDate(null);
		place.setEndDate(new Date());
		
		assertFalse(place.isCorrect());
	}
	
	@Test
	public void isCorrectEndDateNullTest()
	{
		Place place = new Place();
		place.setBeginDate(new Date());
		place.setEndDate(null);
		
		assertFalse(place.isCorrect());
	}
	
	@Test
	public void isCorrectBeginDateEqualsEndTest()
	{
		Place place = new Place();
		place.setBeginDate(new Date(1000));
		place.setEndDate(new Date(1000));
		
		assertTrue(place.isCorrect());
	}
	
	@Test
	public void isCorrectBeginDateAfterEndTest()
	{
		Place place = new Place();
		place.setBeginDate(new Date(1001));
		place.setEndDate(new Date(1000));
		
		assertFalse(place.isCorrect());
	}
}