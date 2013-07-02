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


		HibernatePlacesTable placesTable = new HibernatePlacesTable(session);
		placesTable.addPlace(place1);

		calendar.set(2013, 1, 22, 0, 0, 0);
		Date beginDate = calendar.getTime();
		calendar.set(2013, 1, 22, 23, 59, 59);
		Date endDate = calendar.getTime();

		List<Place> placesInPeriod = placesTable.getPlacesByPeriod(beginDate, endDate);
		assertEquals(1, placesInPeriod.size());

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