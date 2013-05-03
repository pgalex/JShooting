package com.jshooting.shootingDatabaseTests;

import com.jshooting.shootingDatabase.ShootingDatabase;
import com.jshooting.shootingDatabase.ShootingDatabaseFactory;
import com.jshooting.testUtils.IOTesting;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of ShootingDatabaseFactory class
 *
 * @author pgalex
 */
public class ShootingDatabaseFactoryTest
{
	/**
	 * Test opening database with null file name
	 */
	@Test
	public void openingWithNullFileName()
	{
		try
		{
			ShootingDatabaseFactory.openDatabaseFromFile(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}

	/**
	 * Test opening database with empty file name
	 */
	@Test
	public void openingWithEmptyFileName()
	{
		try
		{
			ShootingDatabaseFactory.openDatabaseFromFile(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}

	/**
	 * Test opening database normal work
	 */
	@Test
	public void openingNormalWork()
	{
		ShootingDatabase openDatabase = ShootingDatabaseFactory.openDatabaseFromFile(IOTesting.TEST_FILE_NAME);
		assertNotNull(openDatabase);
	}
}