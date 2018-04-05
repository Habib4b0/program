package org.r2.etl.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.r2.etl.common.controller.BPIETLException;

/**
 * This is the CommonUtils class for email notification ,log File Creation etc.
 *
 * @author stpl
 *
 */
public final class CommonUtils {

	/**
	 * The variable used for logger.
	 */
	public static final Logger LOGGER = Logger.getLogger(CommonUtils.class);

	public static final String X00 = "\\x00";
	public static final String DEBUG_MODE = "DEBUG_MODE";
	public static final String ETL_LOG_PATH = "ETL_LOG_PATH";

	/**
	 * The method is used to get the File Path for the properties file.
	 *
	 * @return logpath
	 *
	 */
	public static String getFilePath() {

		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		final URL resource = classLoader.getResource("");
		String logpath = resource.getPath();
		logpath = logpath.replace('/', '/');
		return logpath;

	}

	public static void commonCode(String logPath) throws IOException {
		try (FileReader fReader = new FileReader(getPropertyFromDbresource(ETL_LOG_PATH) + FilePathUtil.TEMP_PATH);
				BufferedReader reader = new BufferedReader(fReader);) {
			File dir = new File(getPropertyFromDbresource(ETL_LOG_PATH));
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if (getPropertyFromDbresource(DEBUG_MODE) != null
					&& getPropertyFromDbresource(DEBUG_MODE).equalsIgnoreCase("yes")) {

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = simpleDateFormat.format(new Date());

				String finalPath = getPropertyFromDbresource(ETL_LOG_PATH)
						+ logPath.replace(".txt", "-" + date + ".txt");

				File logfile = new File(finalPath);

				if (!logfile.exists() && !logfile.createNewFile()) {

					LOGGER.warn("Log file not created..");

				}

				commonLogicReadWrite(logfile, reader);
			}
		}
	}

	/**
	 * This method is used to create the log file.
	 *
	 */
	public static void createlog() throws IOException {
		commonCode(FilePathUtil.getLogPath());
	}

	/**
	 * This method is used to clear the temp files for old logs.
	 */
	public static void cleartemp() throws BPIETLException {
		try (PrintWriter writer = new PrintWriter(getPropertyFromDbresource(ETL_LOG_PATH) + FilePathUtil.TEMP_PATH);) {

			File dir = new File(getPropertyFromDbresource(ETL_LOG_PATH));
			if (!dir.exists()) {
				dir.mkdirs();
			}

			writer.print("");

		} catch (Exception ex) {

			LOGGER.error(ex.getMessage());
			throw new BPIETLException(ex);
		}

	}

	/**
	 * This method is used to create the log file.
	 */
	public static void createErrorlog() throws IOException {
		commonCode(FilePathUtil.getErrorLogPath());
	}

	public static void commonLogicReadWrite(File logfile, BufferedReader reader) throws IOException {

		try (FileWriter fWriter = new FileWriter(logfile, true); PrintWriter writer = new PrintWriter(fWriter)) {
			String line = reader.readLine();
			while (line != null) {
				line = line.trim();

				if (line.contains(X00)) {
					line = line.replace(X00, "");
				}
				writer.println(line);
				line = reader.readLine();
			}
		} catch (Exception ex) {

			LOGGER.error(ex.getMessage());

		}

	}

	static String getPropertyFromDbresource(String property) {
		final File file = new File("");
		String logpath = "";
		try {
			logpath = file.getCanonicalPath();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		final Properties dbResouce = new Properties();
		final String path = logpath + FilePathUtil.DATABASE_CONFIGURATION_FILE_NAME;
		try {
			dbResouce.load(new FileInputStream(path));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return dbResouce.getProperty(property);
	}

	private CommonUtils() {
	}
}
