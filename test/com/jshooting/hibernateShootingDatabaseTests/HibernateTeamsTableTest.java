package com.jshooting.hibernateShootingDatabaseTests;

import com.jshooting.hiberanteShootingDatabase.HibernateShootingDatabase;
import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import com.jshooting.testUtils.IOTesting;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of HibernateTeamsTable class
 *
 * @author pgalex
 */
public class HibernateTeamsTableTest
{
	/**
	 * Test updating team
	 */
	@Test
	public void updatingTest()
	{
		try
		{
			IOTesting.deleteTestFile();
			HibernateShootingDatabase database = new HibernateShootingDatabase(IOTesting.TEST_FILE_NAME);

			TeamsTable teamsTable = database.getTeamsTable();

			Team team1 = new Team();
			team1.setName("team1");
			teamsTable.addTeam(team1);
			
			team1.setName("someTeam");
			teamsTable.updateTeam(team1);

			Team[] allTeams = new Team[0];
			allTeams = teamsTable.getAllTeams().toArray(allTeams);
			assertEquals(1, allTeams.length);
			assertEquals("someTeam", allTeams[0].getName());

			database.close();
		}
		catch (Exception ex)
		{
			fail();
		}
	}

	/**
	 * Test adding and getting teams normal work
	 */
	@Test
	public void addingAndGettingTest()
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

			Team[] allTeams = new Team[0];
			allTeams = teamsTable.getAllTeams().toArray(allTeams);
			assertEquals(2, allTeams.length);
			assertEquals("team1", allTeams[0].getName());
			assertEquals("team2", allTeams[1].getName());

			database.close();
		}
		catch (Exception ex)
		{
			fail();
		}
	}

	/**
	 * Test adding null team
	 */
	@Test
	public void addingNullTeamTest()
	{
		try
		{
			IOTesting.deleteTestFile();
			HibernateShootingDatabase database = new HibernateShootingDatabase(IOTesting.TEST_FILE_NAME);

			TeamsTable teamsTable = database.getTeamsTable();
			teamsTable.addTeam(null);

			database.close();
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
}