package com.jshooting.model;

/**
 * Type of shooting training
 *
 * @author pgalex
 */
public enum ShootingTrainingType
{
	COMPLEX,
	SHOOTING,
	COMPETITION;

	/**
	 * Get string representation of training type
	 *
	 * @param value shooting training type to get string representation of
	 * @return string representation of given training type
	 * @throws IllegalArgumentException value is null
	 */
	public static String toString(ShootingTrainingType value) throws IllegalArgumentException
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value is null");
		}

		switch (value)
		{
			case COMPLEX:
				return "Комплексная";
			case SHOOTING:
				return "Стрелковая";
			case COMPETITION:
				return "Соревнование";
			default:
				throw new IllegalArgumentException("unknown value");
		}
	}
}
