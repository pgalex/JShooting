package com.jshooting.hibernateShootingDatabaseTests;

import com.jshooting.hiberanteShootingDatabase.HibernateShootingDatabase;
import com.jshooting.hiberanteShootingDatabase.HibernateSportsmansTable;
import com.jshooting.shootingDatabase.Sportsman;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import com.jshooting.testUtils.IOTesting;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of HibernateSportsmansTable
 *
 * @author pgalex
 */
public class HibernateSportsmansTableTest
{
	/**
	 * Test creating with null session factory
	 */
	@Test
	public void creatingWithNullFactory()
	{
		try
		{
			IOTesting.deleteTestFile();
			HibernateSportsmansTable sportsmansTable = new HibernateSportsmansTable(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}

	/**
	 * Test adding null sportsman
	 */
	@Test
	public void addingNullSportsman()
	{
		try
		{
			IOTesting.deleteTestFile();
			HibernateShootingDatabase database = new HibernateShootingDatabase(IOTesting.TEST_FILE_NAME);
			SportsmansTable sportsmansTable = database.getSportsmansTable();
			sportsmansTable.addSportsman(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
		catch (DatabaseErrorException ex)
		{
			fail();
		}
	}

	/**
	 * Test adding and getting normal work
	 */
	@Test
	public void addingAndGetting()
	{
		try
		{
			IOTesting.deleteTestFile();
			HibernateShootingDatabase database = new HibernateShootingDatabase(IOTesting.TEST_FILE_NAME);

			TeamsTable teamsTable = database.getTeamsTable();
			Team team1 = new Team();
			team1.setName("team1");
			teamsTable.addTeam(team1);

			SportsmansTable sportsmansTable = database.getSportsmansTable();
			Sportsman sportsman1 = new Sportsman();
			sportsman1.setName("1");
			sportsman1.setTeam(team1);
			sportsmansTable.addSportsman(sportsman1);

			Sportsman sportsman2 = new Sportsman();
			sportsman2.setName("2");
			sportsman2.setTeam(team1);
			sportsmansTable.addSportsman(sportsman2);


			List<Sportsman> allSportsmans = sportsmansTable.getAllSportsmans();
			assertEquals(2, allSportsmans.size());
			assertNotNull(allSportsmans.get(0).getTeam());
			assertEquals("1", allSportsmans.get(0).getName());
			assertNotNull(allSportsmans.get(1).getTeam());
			assertEquals("2", allSportsmans.get(1).getName());
		}
		catch (Exception ex)
		{
			fail();
		}

	}
}