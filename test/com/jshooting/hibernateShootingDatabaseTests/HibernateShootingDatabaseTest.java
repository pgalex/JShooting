package com.jshooting.hibernateShootingDatabaseTests;

import com.jshooting.hiberanteShootingDatabase.HibernateShootingDatabase;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import com.jshooting.testUtils.IOTesting;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of HiberanteShootingDatabase class
 *
 * @author pgalex
 */
public class HibernateShootingDatabaseTest
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
		catch (DatabaseErrorException ex)
		{
			fail();
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
		catch (DatabaseErrorException ex)
		{
			fail();
		}
	}

	/**
	 * Test getting connection to database
	 */
	@Test
	public void openingDatabaseNormalWork()
	{
		try
		{
			IOTesting.deleteTestFile();
			HibernateShootingDatabase database = new HibernateShootingDatabase(IOTesting.TEST_FILE_NAME);
			assertEquals(IOTesting.TEST_FILE_NAME, database.getFileName());
			assertNotNull(database.getTeamsTable());
			assertNotNull(database.getSportsmansTable());
			assertNotNull(database.getTrainingMethodsTable());
			database.close();
		}
		catch (DatabaseErrorException ex)
		{
			fail();
		}
	}

	/**
	 * Test getting connection to incorrect database
	 */
	@Test
	public void openingDatabaseFromIncorrectDatabase()
	{
		try
		{
			IOTesting.writeSomeDataToTestFile();
			
			HibernateShootingDatabase database = new HibernateShootingDatabase(IOTesting.TEST_FILE_NAME);
			fail();
		}
		catch (DatabaseErrorException ex)
		{
			// ok
		}
		catch (FileNotFoundException ex)
		{
			fail();
		}
		catch (IOException ex)
		{
			fail();
		}
	}
}