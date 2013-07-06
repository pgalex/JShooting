package com.jshooting.logics.exceptions;

/**
 * Error while performing logics operation
 *
 * @author pgalex
 */
public class ShootingLogicsException extends Exception
{
	public ShootingLogicsException(Throwable ex)
	{
		super(ex);
	}

	public ShootingLogicsException(String string)
	{
		super(string);
	}
}
