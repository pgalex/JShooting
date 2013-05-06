package com.jshooting.hibernateShootingDatabaseTests;

import com.jshooting.hiberanteShootingDatabase.HibernateShootingDatabase;
import com.jshooting.shootingDatabase.Sportsman;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import com.jshooting.testUtils.IOTesting;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of HibernateSportsmansTable
 *
 * @author pgalex
 */
public class HibernateSportsmansTableTest
{
	// creating with null factory
	// adding null sportsman
	// adding and getting normal work
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