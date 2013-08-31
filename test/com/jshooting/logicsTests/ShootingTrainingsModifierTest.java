package com.jshooting.logicsTests;

import com.jshooting.logics.ShootingTrainingsModifier;
import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
import com.jshooting.model.ShootingTraining;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of ShootingTrainingsModifier class
 *
 * @author pgalex
 */
public class ShootingTrainingsModifierTest
{
	@Test
	public void addingInvalidTrainingTest() throws ShootingLogicsErrorException
	{
		try
		{
			ShootingTrainingsModifier modifier = new ShootingTrainingsModifier(new FakeShootingTrainingsTable());
			ShootingTraining incorrectTraining = new ShootingTraining();
			incorrectTraining.setSportsman(null);
			modifier.addTraining(incorrectTraining);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}
}