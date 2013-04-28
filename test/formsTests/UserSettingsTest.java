package formsTests;

import com.jshooting.forms.UserSettings;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of UserSettings class
 *
 * @author pgalex
 */
public class UserSettingsTest
{
	public static String TEST_FILE_NAME = "testFile.txt";

	/**
	 * Test saving given data
	 */
	@Test
	public void savingDataTest()
	{
		try
		{
			UserSettings.getInstance().setDatabaseFileName("testDatabaseFile");
			UserSettings.getInstance().writeToFile(new File(TEST_FILE_NAME));
			UserSettings.getInstance().setDatabaseFileName("");
			UserSettings.getInstance().readFromFile(new File(TEST_FILE_NAME));

			assertEquals("testDatabaseFile", UserSettings.getInstance().getDatabaseFileName());
		}
		catch (IOException ex)
		{
			fail();
		}
	}

	/**
	 * Test settings null saving database file name
	 */
	@Test
	public void settingNullDatabaseFileTest()
	{
		try
		{
			UserSettings.getInstance().setDatabaseFileName(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}

	/**
	 * Keeping single instance of settings test
	 */
	@Test
	public void singleInstanceTest()
	{
		UserSettings.getInstance().setDatabaseFileName("1");
		UserSettings.getInstance().setDatabaseFileName("2");
		assertEquals("2", UserSettings.getInstance().getDatabaseFileName());
	}

	/**
	 * Test set all settings to default
	 */
	@Test
	public void setAsDefaultTest()
	{
		UserSettings.getInstance().setDatabaseFileName("123");
		UserSettings.setAsDefault();
		assertNotSame("123", UserSettings.getInstance().getDatabaseFileName());
	}

	/**
	 * Test read from null file
	 */
	@Test
	public void readFromNullFileTest()
	{
		try
		{
			UserSettings.getInstance().readFromFile(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
		catch (IOException ex)
		{
			fail();
		}
	}

	/**
	 * Test write to null file
	 */
	@Test
	public void writeToNullFileTest()
	{
		try
		{
			UserSettings.getInstance().writeToFile(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
		catch (IOException ex)
		{
			fail();
		}
	}
}