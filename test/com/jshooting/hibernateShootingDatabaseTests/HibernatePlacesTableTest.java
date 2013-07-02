package com.jshooting.hibernateShootingDatabaseTests;

import com.jshooting.hiberanteShootingDatabase.HibernatePlacesTable;
import com.jshooting.shootingDatabase.Place;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import com.jshooting.testUtils.HibernateTesting;
import com.jshooting.testUtils.IOTesting;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of HibernatePlacesTable class
 *
 * @author pgalex
 */
public class HibernatePlacesTableTest
{
	@Test
	public void getPlacesInPeriod() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = sessionFactory.openSession();

		Calendar calendar = Calendar.getInstance();


		Place place1 = new Place();
		place1.setName("place1");
		calendar.set(2013, 1, 12, 0, 0, 0);
		place1.setBeginDate(calendar.getTime());
		calendar.set(2013, 1, 22, 23, 59, 59);
		place1.setEndDate(calendar.getTime());  //12-22 +


		Place place2 = new Place();
		place2.setName("place2");
		calendar.set(2013, 1, 20, 0, 0, 0);
		place2.setBeginDate(calendar.getTime());
		calendar.set(2013, 1, 25, 23, 59, 59);
		place2.setEndDate(calendar.getTime()); //20-25 +


		Place place3 = new Place();
		place3.setName("place3");
		calendar.set(2013, 1, 10, 0, 0, 0);
		place3.setBeginDate(calendar.getTime());
		calendar.set(2013, 1, 21, 0, 0, 0); //10-21 -
		place3.setEndDate(calendar.getTime());

		Place place4 = new Place();
		place4.setName("place4");
		calendar.set(2013, 1, 26, 0, 0, 0);
		place4.setBeginDate(calendar.getTime());
		calendar.set(2013, 1, 28, 0, 0, 0);
		place4.setEndDate(calendar.getTime());// 26-28 -


		HibernatePlacesTable placesTable = new HibernatePlacesTable(session);
		placesTable.addPlace(place1);
		placesTable.addPlace(place2);
		placesTable.addPlace(place3);
		placesTable.addPlace(place4);

		calendar.set(2013, 1, 22, 0, 0, 0);
		Date beginDate = calendar.getTime();
		calendar.set(2013, 1, 25, 0, 0, 0);
		Date endDate = calendar.getTime();

		List<Place> placesInPeriod = placesTable.getPlacesByPeriod(beginDate, endDate);
		assertEquals(2, placesInPeriod.size());
		assertTrue(placesInPeriod.get(0).getName().equals(place1.getName()) || placesInPeriod.get(0).getName().equals(place2.getName()));
		assertTrue(placesInPeriod.get(1).getName().equals(place1.getName()) || placesInPeriod.get(1).getName().equals(place2.getName()));

		session.close();
		sessionFactory.close();
	}

	@Test
	public void updatingNormalWork() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = sessionFactory.openSession();

		Place place1 = new Place();
		place1.setBeginDate(new Date());
		place1.setEndDate(new Date());
		place1.setName("place1");
		HibernatePlacesTable placesTable = new HibernatePlacesTable(session);
		placesTable.addPlace(place1);

		place1.setName("somePlace");
		placesTable.updatePlace(place1);

		assertEquals("somePlace", placesTable.getAllPlaces().get(0).getName());

		session.close();
		sessionFactory.close();
	}

	@Test
	public void addingAndGettingNormalWork() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = sessionFactory.openSession();

		Place place1 = new Place();
		place1.setBeginDate(new Date());
		place1.setEndDate(new Date());
		place1.setName("place1");

		Place place2 = new Place();
		place2.setBeginDate(new Date());
		place2.setEndDate(new Date());
		place2.setName("place2");

		HibernatePlacesTable placesTable = new HibernatePlacesTable(session);
		placesTable.addPlace(place1);
		placesTable.addPlace(place2);

		List<Place> allPlaces = placesTable.getAllPlaces();
		assertEquals(2, allPlaces.size());
		assertEquals("place1", allPlaces.get(0).getName());
		assertEquals("place2", allPlaces.get(1).getName());

		session.close();
		sessionFactory.close();
	}
}