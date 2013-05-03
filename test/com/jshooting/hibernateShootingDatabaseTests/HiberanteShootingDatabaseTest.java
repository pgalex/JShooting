package com.jshooting.hibernateShootingDatabaseTests;

import com.jshooting.hiberanteShootingDatabase.HibernateShootingDatabase;
import com.jshooting.testUtils.IOTesting;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of HiberanteShootingDatabase class
 *
 * @author pgalex
 */
public class HiberanteShootingDatabaseTest
{
	/**
	 * Test creating with null database file name
	 */
	@Test
	public void creatingWithNullFileName()
	{
		try
		{
			HibernateShootingDatabase database = new HibernateShootingDatabase(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{// ok
		}
	}

	/**
	 * Test creating with empty database file name
	 */
	@Test
	public void creatingWithEmptyFileName()
	{
		try
		{
			HibernateShootingDatabase database = new HibernateShootingDatabase("");
			fail();
		}
		catch (IllegalArgumentException ex)
		{// ok
		}
	}

	/**
	 * Test getting connection to database
	 */
	@Test
	public void openingDatabaseNormalWork()
	{
		HibernateShootingDatabase database = new HibernateShootingDatabase(IOTesting.TEST_FILE_NAME);
		assertEquals(IOTesting.TEST_FILE_NAME, database.getFileName());
		database.close();
	}
}