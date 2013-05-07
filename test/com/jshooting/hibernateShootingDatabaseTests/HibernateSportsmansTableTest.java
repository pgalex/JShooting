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
	 * Test adding sportsman with null team
	 */
	@Test
	public void addingWithNullTeam()
	{
		try
		{
			IOTesting.deleteTestFile();
			HibernateShootingDatabase database = new HibernateShootingDatabase(IOTesting.TEST_FILE_NAME);
			SportsmansTable sportsmansTable = database.getSportsmansTable();
			Sportsman sportsman = new Sportsman();
			sportsman.setTeam(null);
			sportsmansTable.addSportsman(sportsman);
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

	/**
	 * Test getting sportsmans in team
	 */
	@Test
	public void gettingInTeam()
	{
		try
		{
			IOTesting.deleteTestFile();
			HibernateShootingDatabase database = new HibernateShootingDatabase(IOTesting.TEST_FILE_NAME);

			TeamsTable teamsTable = database.getTeamsTable();
			Team team1 = new Team();
			team1.setName("team1");
			teamsTable.addTeam(team1);

			Team team2 = new Team();
			team2.setName("team2");
			teamsTable.addTeam(team2);


			SportsmansTable sportsmansTable = database.getSportsmansTable();
			Sportsman sportsman1 = new Sportsman();
			sportsman1.setName("1");
			sportsman1.setTeam(team1);
			sportsmansTable.addSportsman(sportsman1);

			Sportsman sportsman2 = new Sportsman();
			sportsman2.setName("2");
			sportsman2.setTeam(team2);
			sportsmansTable.addSportsman(sportsman2);

			List<Sportsman> team1Sportsmans = sportsmansTable.getSportsmansInTeam(team1);
			assertEquals(1, team1Sportsmans.size());
			assertNotNull(team1Sportsmans.get(0).getTeam());
			assertEquals("1", team1Sportsmans.get(0).getName());

			List<Sportsman> team2Sportsmans = sportsmansTable.getSportsmansInTeam(team2);
			assertEquals(1, team2Sportsmans.size());
			assertNotNull(team2Sportsmans.get(0).getTeam());
			assertEquals("2", team2Sportsmans.get(0).getName());
		}
		catch (Exception ex)
		{
			fail();
		}
	}
}