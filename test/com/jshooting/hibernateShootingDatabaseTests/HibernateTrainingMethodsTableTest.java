package com.jshooting.hibernateShootingDatabaseTests;

import com.jshooting.hiberanteShootingDatabase.HibernateTrainingMethodsTable;
import com.jshooting.model.TrainingMethod;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import com.jshooting.testUtils.HibernateTesting;
import com.jshooting.testUtils.IOTesting;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of HibernateTrainingMethodsTable class
 *
 * @author pgalex
 */
public class HibernateTrainingMethodsTableTest
{
	/**
	 * Test creating with null session factory
	 */
	@Test
	public void creatingWithNullFactory()
	{
		try
		{
			HibernateTrainingMethodsTable trainingMethodsTable = new HibernateTrainingMethodsTable(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}

	/**
	 * Test adding null method
	 *
	 * @throws DatabaseErrorException
	 */
	@Test
	public void addingNullMethod() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);

		try
		{
			HibernateTrainingMethodsTable trainingMethodsTable = new HibernateTrainingMethodsTable(null);
			trainingMethodsTable.addTrainingMethod(null);

			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}

		sessionFactory.close();
	}

	/**
	 * Test updating null method
	 *
	 * @throws DatabaseErrorException
	 */
	@Test
	public void updatingNullMethod() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);

		try
		{
			HibernateTrainingMethodsTable trainingMethodsTable = new HibernateTrainingMethodsTable(null);
			trainingMethodsTable.updateTrainingMethod(null);

			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}

		sessionFactory.close();
	}

	/**
	 * Test adding and getting normal work
	 *
	 * @throws DatabaseErrorException
	 */
	@Test
	public void addingAndGettingTest() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = sessionFactory.openSession();

		HibernateTrainingMethodsTable trainingMethodsTable = new HibernateTrainingMethodsTable(session);

		TrainingMethod trainingMethod1 = new TrainingMethod();
		trainingMethod1.setName("method1");
		trainingMethodsTable.addTrainingMethod(trainingMethod1);

		TrainingMethod trainingMethod2 = new TrainingMethod();
		trainingMethod2.setName("method2");
		trainingMethodsTable.addTrainingMethod(trainingMethod2);

		List<TrainingMethod> allMethods = trainingMethodsTable.getAllTrainingMethods();

		assertEquals(2, allMethods.size());
		assertEquals("method1", allMethods.get(0).getName());
		assertEquals("method2", allMethods.get(1).getName());

		session.close();
		sessionFactory.close();
	}

	/**
	 * Test updating normal work
	 *
	 * @throws DatabaseErrorException
	 */
	@Test
	public void updatingTest() throws DatabaseErrorException
	{
		IOTesting.deleteTestFile();
		SessionFactory sessionFactory = HibernateTesting.createSessionFactoryByFile(IOTesting.TEST_FILE_NAME);
		Session session = sessionFactory.openSession();

		HibernateTrainingMethodsTable trainingMethodsTable = new HibernateTrainingMethodsTable(session);

		TrainingMethod trainingMethod1 = new TrainingMethod();
		trainingMethod1.setName("method1");
		trainingMethodsTable.addTrainingMethod(trainingMethod1);

		trainingMethod1.setName("someMethod");
		trainingMethodsTable.updateTrainingMethod(trainingMethod1);

		List<TrainingMethod> allMethods = trainingMethodsTable.getAllTrainingMethods();
		assertEquals(1, allMethods.size());
		assertEquals("someMethod", allMethods.get(0).getName());

		session.close();
		sessionFactory.close();
	}
}