package com.jshooting.hibernateShootingDatabaseTests;

import com.jshooting.hiberanteShootingDatabase.HibernateShootingTrainingsTable;
import com.jshooting.hiberanteShootingDatabase.HibernateSportsmansTable;
import com.jshooting.hiberanteShootingDatabase.HibernateTeamsTable;
import com.jshooting.hiberanteShootingDatabase.HibernateTrainingMethodsTable;
import com.jshooting.shootingDatabase.ShootingTraining;
import com.jshooting.shootingDatabase.ShootingTrainingType;
import com.jshooting.shootingDatabase.ShootingTrainingsFilter;
import com.jshooting.shootingDatabase.Sportsman;
import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.TrainingMethod;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import com.jshooting.testUtils.HibernateTesting;
import com.jshooting.testUtils.IOTesting;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * HibernateShootingTrainingsTable class tests
 *
 * @author pgalex
 */
public class HibernateShootingTrainingsTableTest
{
	@Test
	public void deletingTest() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = sessionFactory.openSession();

		TrainingMethod trainingMethod = new TrainingMethod();
		trainingMethod.setName("method");
		HibernateTrainingMethodsTable trainingMethodsTable = new HibernateTrainingMethodsTable(session);
		trainingMethodsTable.addTrainingMethod(trainingMethod);

		Team team = new Team();
		team.setName("team");
		HibernateTeamsTable teamsTable = new HibernateTeamsTable(session);
		teamsTable.addTeam(team);

		Sportsman sportsman = new Sportsman();
		sportsman.setName("sportsman");
		sportsman.setTeam(team);
		HibernateSportsmansTable sportsmansTable = new HibernateSportsmansTable(session);
		sportsmansTable.addSportsman(sportsman);

		ShootingTraining shootingTraining = new ShootingTraining();
		HibernateShootingTrainingsTable shootingTrainingsTable = new HibernateShootingTrainingsTable(session);
		shootingTraining.setComments("comment");
		shootingTraining.setDate(new Date(1000));
		shootingTraining.setSportsman(sportsman);
		shootingTraining.setTrainingMethod(trainingMethod);
		shootingTraining.setType(ShootingTrainingType.COMPLEX);
		shootingTraining.setWeather("waether");
		
		shootingTrainingsTable.addTraining(shootingTraining);
		assertEquals(1, shootingTrainingsTable.getAllTrainings().size());
		ShootingTraining deletingTraining = shootingTrainingsTable.getAllTrainings().get(0);
		shootingTrainingsTable.deleteTraining(deletingTraining);
		assertEquals(0, shootingTrainingsTable.getAllTrainings().size());

		session.close();
		sessionFactory.close();
	}

	/**
	 * Test getting trainings by filter
	 *
	 * @throws DatabaseErrorException
	 */
	@Test
	public void gettingWithFilter() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = sessionFactory.openSession();

		TrainingMethod trainingMethod = new TrainingMethod();
		trainingMethod.setName("method");
		HibernateTrainingMethodsTable trainingMethodsTable = new HibernateTrainingMethodsTable(session);
		trainingMethodsTable.addTrainingMethod(trainingMethod);

		Team team = new Team();
		team.setName("team");
		HibernateTeamsTable teamsTable = new HibernateTeamsTable(session);
		teamsTable.addTeam(team);

		Sportsman sportsman1 = new Sportsman();
		sportsman1.setName("sportsman1");
		sportsman1.setTeam(team);

		Sportsman sportsman2 = new Sportsman();
		sportsman2.setName("sportsman2");
		sportsman2.setTeam(team);

		Sportsman sportsman3 = new Sportsman();
		sportsman3.setName("sportsman3");
		sportsman3.setTeam(team);

		HibernateSportsmansTable sportsmansTable = new HibernateSportsmansTable(session);
		sportsmansTable.addSportsman(sportsman1);
		sportsmansTable.addSportsman(sportsman2);
		sportsmansTable.addSportsman(sportsman3);

		ShootingTraining shootingTraining1 = new ShootingTraining();
		shootingTraining1.setComments("comment");
		shootingTraining1.setDate(new Date(1000));
		shootingTraining1.setSportsman(sportsman1);
		shootingTraining1.setTrainingMethod(trainingMethod);
		shootingTraining1.setType(ShootingTrainingType.COMPLEX);

		ShootingTraining shootingTraining2 = new ShootingTraining();
		shootingTraining2.setComments("comment");
		shootingTraining2.setDate(new Date(2000));
		shootingTraining2.setSportsman(sportsman2);
		shootingTraining2.setTrainingMethod(trainingMethod);
		shootingTraining2.setType(ShootingTrainingType.SHOOTING);

		HibernateShootingTrainingsTable shootingTrainingsTable = new HibernateShootingTrainingsTable(session);
		shootingTrainingsTable.addTraining(shootingTraining1);
		shootingTrainingsTable.addTraining(shootingTraining2);

		List<Sportsman> filterSportsmans = new ArrayList<Sportsman>();
		filterSportsmans.add(sportsman1);
		filterSportsmans.add(sportsman3);
		List<ShootingTrainingType> filterTrainingTypes = new ArrayList<ShootingTrainingType>();
		filterTrainingTypes.add(ShootingTrainingType.COMPETITION);
		filterTrainingTypes.add(ShootingTrainingType.COMPLEX);
		ShootingTrainingsFilter filter = new ShootingTrainingsFilter(filterSportsmans,
						new Date(1000), new Date(2000), filterTrainingTypes);

		List<ShootingTraining> trainingsByFilter = shootingTrainingsTable.getTrainingsWithFilter(filter);
		assertEquals(1, trainingsByFilter.size());

		session.close();
		sessionFactory.close();
	}

	/**
	 * Test adding null training
	 *
	 * @throws DatabaseErrorException
	 */
	@Test
	public void addingNullTraining() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory factory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = factory.openSession();
		try
		{
			HibernateShootingTrainingsTable shootingTrainingsTable = new HibernateShootingTrainingsTable(session);
			shootingTrainingsTable.addTraining(null);

			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}

		session.close();
		factory.close();
	}

	/**
	 * Test addding training with null sportsman
	 *
	 * @throws DatabaseErrorException
	 */
	@Test
	public void addingTrainingWithNullSportsman() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory factory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = factory.openSession();
		try
		{
			HibernateShootingTrainingsTable shootingTrainingsTable = new HibernateShootingTrainingsTable(session);
			ShootingTraining training = new ShootingTraining();
			training.setDate(new Date());
			training.setTrainingMethod(new TrainingMethod());
			training.setSportsman(null);
			shootingTrainingsTable.addTraining(training);

			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}

		session.close();
		factory.close();
	}

	/**
	 * Test addding training with null training method
	 *
	 * @throws DatabaseErrorException
	 */
	@Test
	public void addingTrainingWithNullMethod() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory factory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = factory.openSession();
		try
		{
			HibernateShootingTrainingsTable shootingTrainingsTable = new HibernateShootingTrainingsTable(session);
			ShootingTraining training = new ShootingTraining();
			training.setDate(new Date());
			training.setTrainingMethod(null);
			training.setSportsman(new Sportsman());
			shootingTrainingsTable.addTraining(training);

			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}

		session.close();
		factory.close();
	}

	/**
	 * Test addding training with null training method
	 *
	 * @throws DatabaseErrorException
	 */
	@Test
	public void addingTrainingWithNullDate() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory factory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = factory.openSession();
		try
		{
			HibernateShootingTrainingsTable shootingTrainingsTable = new HibernateShootingTrainingsTable(session);
			ShootingTraining training = new ShootingTraining();
			training.setDate(null);
			training.setTrainingMethod(new TrainingMethod());
			training.setSportsman(new Sportsman());
			shootingTrainingsTable.addTraining(training);

			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}

		session.close();
		factory.close();
	}

	/**
	 * Test creating with null factory
	 */
	@Test
	public void creatingWithNullFactory()
	{
		try
		{
			HibernateShootingTrainingsTable shootingTrainingsTable = new HibernateShootingTrainingsTable(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}

	/**
	 * Test adding and getting normal work
	 *
	 * @throws DatabaseErrorException
	 */
	@Test
	public void addingAndGettingsTest() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = sessionFactory.openSession();

		TrainingMethod trainingMethod = new TrainingMethod();
		trainingMethod.setName("method");
		HibernateTrainingMethodsTable trainingMethodsTable = new HibernateTrainingMethodsTable(session);
		trainingMethodsTable.addTrainingMethod(trainingMethod);

		Team team = new Team();
		team.setName("team");
		HibernateTeamsTable teamsTable = new HibernateTeamsTable(session);
		teamsTable.addTeam(team);

		Sportsman sportsman = new Sportsman();
		sportsman.setName("sportsman");
		sportsman.setTeam(team);
		HibernateSportsmansTable sportsmansTable = new HibernateSportsmansTable(session);
		sportsmansTable.addSportsman(sportsman);

		ShootingTraining shootingTraining = new ShootingTraining();
		HibernateShootingTrainingsTable shootingTrainingsTable = new HibernateShootingTrainingsTable(session);
		shootingTraining.setComments("comment");
		shootingTraining.setDate(new Date(1000));
		shootingTraining.setSportsman(sportsman);
		shootingTraining.setTrainingMethod(trainingMethod);
		shootingTraining.setType(ShootingTrainingType.COMPLEX);
		shootingTraining.setWeather("waether");
		shootingTraining.setDelayLyingCompetition(1);
		shootingTraining.setDelayLyingLoading(2);
		shootingTraining.setDelayStandingCompetition(3);
		shootingTraining.setDelayStandingLoading(4);
		shootingTraining.setFirstLyingCompetition(5);
		shootingTraining.setFirstLyingLoading(6);
		shootingTraining.setFirstStandingCompetition(7);
		shootingTraining.setFirstStandingLoading(8);
		shootingTraining.setMissLyingCompetition(9);
		shootingTraining.setMissLyingInRest(10);
		shootingTraining.setMissLyingLoading(11);
		shootingTraining.setMissStandingCompetition(12);
		shootingTraining.setMissStandingInRest(13);
		shootingTraining.setMissStandingLoading(14);
		shootingTraining.setNumLyingInRest(15);
		shootingTraining.setNumLyingLoading(16);
		shootingTraining.setNumStandingCompetition(17);
		shootingTraining.setNumStandingInRest(18);
		shootingTraining.setNumStandingLoading(19);
		shootingTraining.setScatt(20);
		shootingTraining.setTrail(21);
		shootingTraining.setZeroingIn(22);

		shootingTraining.setMissMarksLying(new byte[]
		{
			1, 2, 3
		});
		shootingTraining.setMissMarksStanding(new byte[]
		{
			3, 4, 5
		});

		shootingTrainingsTable.addTraining(shootingTraining);

		List<ShootingTraining> allTrainings = shootingTrainingsTable.getAllTrainings();
		assertEquals(1, allTrainings.size());
		assertEquals("waether", allTrainings.get(0).getWeather());
		assertEquals("comment", allTrainings.get(0).getComments());
		assertEquals(new Date(1000), allTrainings.get(0).getDate());
		assertEquals(ShootingTrainingType.COMPLEX, allTrainings.get(0).getType());

		assertEquals(1, allTrainings.get(0).getDelayLyingCompetition());
		assertEquals(2, allTrainings.get(0).getDelayLyingLoading());
		assertEquals(3, allTrainings.get(0).getDelayStandingCompetition());
		assertEquals(4, allTrainings.get(0).getDelayStandingLoading());
		assertEquals(5, allTrainings.get(0).getFirstLyingCompetition());
		assertEquals(6, allTrainings.get(0).getFirstLyingLoading());
		assertEquals(7, allTrainings.get(0).getFirstStandingCompetition());
		assertEquals(8, allTrainings.get(0).getFirstStandingLoading());
		assertEquals(9, allTrainings.get(0).getMissLyingCompetition());
		assertEquals(10, allTrainings.get(0).getMissLyingInRest());
		assertEquals(11, allTrainings.get(0).getMissLyingLoading());
		assertEquals(12, allTrainings.get(0).getMissStandingCompetition());
		assertEquals(13, allTrainings.get(0).getMissStandingInRest());
		assertEquals(14, allTrainings.get(0).getMissStandingLoading());
		assertEquals(15, allTrainings.get(0).getNumLyingInRest());
		assertEquals(16, allTrainings.get(0).getNumLyingLoading());
		assertEquals(17, allTrainings.get(0).getNumStandingCompetition());
		assertEquals(18, allTrainings.get(0).getNumStandingInRest());
		assertEquals(19, allTrainings.get(0).getNumStandingLoading());
		assertEquals(20, allTrainings.get(0).getScatt());
		assertEquals(21, allTrainings.get(0).getTrail());
		assertEquals(22, allTrainings.get(0).getZeroingIn());
		assertArrayEquals(new byte[]
		{
			1, 2, 3
		}, allTrainings.get(0).getMissMarksLying());
		assertArrayEquals(new byte[]
		{
			3, 4, 5
		}, allTrainings.get(0).getMissMarksStanding());

		session.close();
		sessionFactory.close();
	}
}