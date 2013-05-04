package com.jshooting.testUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Common utils for read/write testing
 *
 * @author pgalex
 */
public class IOTesting
{
	/**
	 * Name of file, using to test IO
	 */
	public static String TEST_FILE_NAME = "testFile.txt";

	public static void deleteTestFile()
	{
		File testFile = new File(TEST_FILE_NAME);
		if (testFile.exists())
		{
			testFile.delete();
		}
	}

	public static void writeSomeDataToTestFile() throws FileNotFoundException, IOException
	{
		DataOutputStream ouput = new DataOutputStream(new FileOutputStream(IOTesting.TEST_FILE_NAME));
		ouput.writeUTF("some string");
		ouput.writeInt(123);
		ouput.close();
	}
}
