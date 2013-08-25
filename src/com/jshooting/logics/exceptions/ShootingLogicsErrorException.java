package com.jshooting.logics.exceptions;

/**
 * Error while performing logics operation
 *
 * @author pgalex
 */
public class ShootingLogicsErrorException extends Exception
{
	public ShootingLogicsErrorException(Throwable ex)
	{
		super(ex);
	}

	public ShootingLogicsErrorException(String string)
	{
		super(string);
	}
}
