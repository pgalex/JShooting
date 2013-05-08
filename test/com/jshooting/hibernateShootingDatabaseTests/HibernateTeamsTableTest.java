package com.jshooting.hibernateShootingDatabaseTests;

import com.jshooting.hiberanteShootingDatabase.HibernateTeamsTable;
import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import com.jshooting.testUtils.HibernateTesting;
import com.jshooting.testUtils.IOTesting;
import org.hibernate.SessionFactory;
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
			SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
			HibernateTeamsTable teamsTable = new HibernateTeamsTable(sessionFactory);

			Team team1 = new Team();
			team1.setName("team1");
			teamsTable.addTeam(team1);

			team1.setName("someTeam");
			teamsTable.updateTeam(team1);

			Team[] allTeams = new Team[0];
			allTeams = teamsTable.getAllTeams().toArray(allTeams);
			assertEquals(1, allTeams.length);
			assertEquals("someTeam", allTeams[0].getName());

			sessionFactory.close();
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
			SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
			HibernateTeamsTable teamsTable = new HibernateTeamsTable(sessionFactory);

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

			sessionFactory.close();
		}
		catch (Exception ex)
		{
			fail();
		}
	}

	/**
	 * Test adding null team
	 *
	 * @throws DatabaseErrorException
	 */
	@Test
	public void addingNullTeamTest() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		
		try
		{
			HibernateTeamsTable teamsTable = new HibernateTeamsTable(sessionFactory);
			teamsTable.addTeam(null);
			sessionFactory.close();
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}

		sessionFactory.close();
	}
}