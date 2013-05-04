package com.jshooting.shootingDatabase.exceptions;

/**
 * Error while performing database operation
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
