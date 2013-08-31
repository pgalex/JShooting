package com.jshooting.modelTests;

import com.jshooting.model.ShootingTraining;
import com.jshooting.model.ShootingTrainingType;
import com.jshooting.model.Sportsman;
import com.jshooting.model.TrainingMethod;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of ShootingTraining class
 *
 * @author pgalex
 */
public class ShootingTrainingTest
{
	private ShootingTraining createValidTraining()
	{
		ShootingTraining training = new ShootingTraining();
		training.setDate(new Date());
		training.setType(ShootingTrainingType.SHOOTING);
		training.setSportsman(new Sportsman());
		training.setTrainingMethod(new TrainingMethod());
		return training;
	}

	@Test
	public void validTrainingTest()
	{
		ShootingTraining validTraining = createValidTraining();
		assertTrue(validTraining.isValid());
	}

	@Test
	public void isSportsmanValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setSportsman(null);
		assertFalse(training.isValid());
	}

	@Test
	public void isTrainingMethodValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setTrainingMethod(null);
		assertFalse(training.isValid());
	}

	@Test
	public void isDateValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setDate(null);
		assertFalse(training.isValid());
	}

	@Test
	public void isZeroingInValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setZeroingIn(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isTrailValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setTrail(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isScattValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setScatt(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isTypeValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setType(null);
		assertFalse(training.isValid());
	}

	@Test
	public void isNumLyingInRestValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumLyingInRest(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isMissLyingInRestValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumLyingInRest(1);
		training.setMissLyingInRest(2);
		assertFalse(training.isValid());
	}

	@Test
	public void isNumLyingLoadingValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumLyingLoading(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isMissLyingLoadingValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumLyingLoading(1);
		training.setMissLyingLoading(2);
		assertFalse(training.isValid());
	}

	@Test
	public void isNumLyingCompetitionValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumLyingCompetition(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isMissLyingCompetitionValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumLyingCompetition(1);
		training.setMissLyingCompetition(2);
		assertFalse(training.isValid());
	}

	@Test
	public void isNumStandingInRestValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumStandingInRest(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isMissStandingInRestValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumStandingInRest(1);
		training.setMissStandingInRest(2);
		assertFalse(training.isValid());
	}

	@Test
	public void isNumStandingLoadingValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumStandingLoading(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isMissStandingLoadingValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumStandingLoading(1);
		training.setMissStandingLoading(2);
		assertFalse(training.isValid());
	}

	@Test
	public void isNumStandingCompetitionValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumStandingCompetition(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isMissStandingCompetitionValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setNumStandingCompetition(1);
		training.setMissStandingCompetition(2);
		assertFalse(training.isValid());
	}

	@Test
	public void isFirstLyingLoadingValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setFirstLyingLoading(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isFirstLyingCompetitionValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setFirstLyingCompetition(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isDelayLyingLoadingValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setDelayLyingLoading(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isDelayLyingCompetitionValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setDelayLyingCompetition(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isFirstStandingLoadingValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setFirstStandingLoading(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isFirstStandingCompetitionValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setFirstStandingCompetition(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isDelayStandingLoadingValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setDelayStandingLoading(-1);
		assertFalse(training.isValid());
	}

	@Test
	public void isDelayStandingCompetitionValidTest()
	{
		ShootingTraining training = new ShootingTraining();
		training.setDelayStandingCompetition(-1);
		assertFalse(training.isValid());
	}
}