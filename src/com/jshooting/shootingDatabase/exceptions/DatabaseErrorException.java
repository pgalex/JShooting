package com.jshooting.shootingDatabase.exceptions;

/**
 * Error while performing operation with database
 *
 * @author pgalex
 */
public class DatabaseErrorException extends Exception
{
	public DatabaseErrorException(Throwable thrwbl)
	{
		super(thrwbl);
	}

	public DatabaseErrorException(String string)
	{
		super(string);
	}
}
