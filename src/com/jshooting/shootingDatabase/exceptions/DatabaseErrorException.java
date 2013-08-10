package com.jshooting.shootingDatabase.exceptions;

/**
 * Error while performing operation with database
 *
 * @author pgalex
 */
public class DatabaseErrorException extends Exception
{
	public DatabaseErrorException(Throwable ex)
	{
		super(ex);
	}

	public DatabaseErrorException(String string)
	{
		super(string);
	}
}
