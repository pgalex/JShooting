package com.jshooting.logicsTests;

import com.jshooting.logics.ShootingLogicsFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ShootingLogicsFactory class tests
 *
 * @author pgalex
 */
public class ShootingLogicsFactoryTest
{
	@Test
	public void creatingWithNullDatabase()
	{
		try
		{
			ShootingLogicsFactory shootingLogicsFactory = new ShootingLogicsFactory(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}
}