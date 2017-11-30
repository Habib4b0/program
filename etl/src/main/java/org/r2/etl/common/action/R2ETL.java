package org.r2.etl.common.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
//import org.bpi.di.core.EtlEnvironment;
import org.pentaho.di.core.encryption.Encr;
//import org.bpi.di.core.encryption.Encr;
//import org.bpi.di.core.exception.EtlException;
//import org.bpi.di.job.Job;
import org.pentaho.di.core.exception.KettleException;
//import org.bpi.di.job.JobMeta;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.r2.etl.common.util.CommonUtils;
import org.r2.etl.common.controller.BPIETLException;
import org.r2.etl.common.util.FilePathUtil;

/**
 * This class is used to execute the BPI ETL job.
 *
 * @author stpl
 *
 */
public class R2ETL {

    /**
     * The variable used for logger
     */
    private static final Logger LOGGER = Logger.getLogger(R2ETL.class);

    /**
     * The variable used to get the error count if any error happen in execution
     * of ETL code.
     */
    private static final int ERRORCOUNT = 0;

    /**
     * The method is used to set environment variable , get values from
     * properties file etc.
     *
     * @param filename
     * @throws Exception
     */
    public void runJob(final String filename) throws BPIETLException {

        try {
            final File file = new File("");
            String logpath = "";
            logpath = file.getCanonicalPath();
            final Properties dbResouce = new Properties();
            final String path = logpath + FilePathUtil.DATABASE_CONFIGURATION_FILE_NAME;
            final String procedurePath = logpath + FilePathUtil.PROCEDURE_CONFIGURATION_FILE_NAME;

            dbResouce.load(new FileInputStream(path));
            LOGGER.info("The Database Configuration files exist in the path");
            KettleEnvironment.init();
            //EtlEnvironment.init();
            final JobMeta jobMeta = new JobMeta(filename, null);
            final Job job = new Job(null, jobMeta);

            final String bpiPassword = Encr
                    .decryptPassword(dbResouce.getProperty("BPI_PASSWORD"));
            final String bpipassphrase = Encr
                    .decryptPassword(dbResouce.getProperty("BPI_PASSPHRASE"));
            final String stagingPassword = Encr
                    .decryptPassword(dbResouce.getProperty("STAGING_PASSWORD"));
            final String sourcePassword = Encr
                    .decryptPassword(dbResouce.getProperty("BPI_SOURCE_PASSWORD"));

            final String sysPassword = Encr
                    .decryptPassword(dbResouce.getProperty(BPI_SYS_PSW));
            final String[] fileNameExtension = getFileName(filename, dbResouce.getProperty(INBOUND_FILE_PATH));

            int index = fileNameExtension[0].indexOf('.');
            String[] fileArr = new String[2];
            if (index != -1) {
                fileArr[0] = fileNameExtension[0].substring(0, index);

                fileArr[1] = fileNameExtension[0].substring(index + 1,
                        fileNameExtension[0].length());
            } else {
                fileArr[0] = fileNameExtension[0];
            }

            job.setVariable("ETL_PROPERTY_FILE", path);
            job.setVariable("ETL_PROCEDURE_PROPERTY_FILE", procedurePath);
            job.setVariable("BPI_SYS_HOST", dbResouce.getProperty("BPI_SYS_HOST"));
            job.setVariable("BPI_SYS_DB", dbResouce.getProperty("BPI_SYS_DB"));
            job.setVariable("BPI_SYS_USER", dbResouce.getProperty("BPI_SYS_USER"));
            job.setVariable(BPI_SYS_PSW, dbResouce.getProperty(BPI_SYS_PSW));
            String serverName = dbResouce.getProperty("FILE_LOCAL_PATH");
            job.setVariable("FILE_LOCAL_PATH", dbResouce.getProperty("FILE_LOCAL_PATH"));
            
            FilePathUtil.latestServerName = serverName.split("/")[2];
            System.out.println("latestServerName = " + FilePathUtil.latestServerName);
            

            job.setVariable("MAIL_FILE_PATH", dbResouce.getProperty("MAIL_FILE_PATH"));

            job.setVariable("COMMON_LOGIC_PATH", dbResouce.getProperty("COMMON_LOGIC_PATH"));

            job.setVariable("DECRYPTION_MODE", dbResouce.getProperty("DECRYPTION_MODE"));
            job.setVariable("SOURCE_MODE", dbResouce.getProperty("SOURCE_MODE"));
            job.setVariable("GPG_LOCATION", dbResouce.getProperty("GPG_LOCATION"));

            job.setVariable("OUTBOUND_PURGE_DAYS", dbResouce.getProperty("OUTBOUND_PURGE_DAYS"));
            job.setVariable("LOG_ZIP_DAYS", dbResouce.getProperty("LOG_ZIP_DAYS"));
            job.setVariable("ETL_LOG_PATH", dbResouce.getProperty("ETL_LOG_PATH"));

            job.setVariable("FILE_NAME_EXT", fileNameExtension[0]);
            job.setVariable("INTERFACE_PROCESS_NAME", fileNameExtension[1]);

            job.setVariable("DELIMITER", dbResouce.getProperty("DELIMITER"));

            job.setVariable("FULL_LOAD_PURGE_DAYS", dbResouce.getProperty("FULL_LOAD_PURGE_DAYS"));

            job.setVariable(INBOUND_FILE_PATH, dbResouce.getProperty(INBOUND_FILE_PATH));
            job.setVariable("OUTBOUND_FILE_PATH", dbResouce.getProperty("OUTBOUND_FILE_PATH"));
            job.setVariable("SERVER_NAME", dbResouce.getProperty("SERVER_NAME"));
            job.setVariable("SERVER_PASSWORD", dbResouce.getProperty("SERVER_PASSWORD"));
            job.setVariable("SERVER_PORT", dbResouce.getProperty("SERVER_PORT"));
            job.setVariable("SERVER_USERNAME", dbResouce.getProperty("SERVER_USERNAME"));
            job.setVariable("BPI_HOST", dbResouce.getProperty("BPI_HOST"));
            job.setVariable("BPI_DB", dbResouce.getProperty("BPI_DB"));
            job.setVariable("BPI_USER", dbResouce.getProperty("BPI_USER"));
            job.setVariable("BPI_PASSWORD", bpiPassword);
            job.setVariable("STAGING_HOST", dbResouce.getProperty("STAGING_HOST"));
            job.setVariable("STAGING_DB", dbResouce.getProperty("STAGING_DB"));
            job.setVariable("STAGING_USER", dbResouce.getProperty("STAGING_USER"));
            job.setVariable("STAGING_PASSWORD", stagingPassword);
            job.setVariable(BPI_SYS_PSW, sysPassword);
            job.setVariable("BPI_PASSPHRASE", bpipassphrase);

            job.setVariable("BPI_SOURCE_HOST", dbResouce.getProperty("BPI_SOURCE_HOST"));
            job.setVariable("BPI_SOURCE_DB", dbResouce.getProperty("BPI_SOURCE_DB"));
            job.setVariable("BPI_SOURCE_USER", dbResouce.getProperty("BPI_SOURCE_USER"));
            job.setVariable("BPI_SOURCE_PASSWORD", sourcePassword);

            job.setVariable("DATE", new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date()));

            job.initializeVariablesFrom(null);
            job.getJobMeta().setInternalKettleVariables(job);

            job.getJobMeta().getLogTables();
            job.execute(0, null);
            job.waitUntilFinished();

            if (fileNameExtension[1] == null) {
                fileNameExtension[1] = " Interface ";
            }

            if (job.getErrors() > ERRORCOUNT) {

                LOGGER.error("There were errors during job execution.");
                LOGGER.info("The Email with error notification has been sent successfully");

                throw new BPIETLException("");

            } else {

                LOGGER.info(fileNameExtension[1].toLowerCase() + " executed successfully ");
                LOGGER.info("The Email notification has been sent successfully ");

            }

        } catch (KettleException ex) {
            LOGGER.error(ex.getMessage());
            throw new BPIETLException(ex);
        } catch (FileNotFoundException ex) {
            LOGGER.error(ex.getMessage());
            throw new BPIETLException(ex);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            throw new BPIETLException(ex);
        }

    }
    public static final String INBOUND_FILE_PATH = "INBOUND_FILE_PATH";
    public static final String BPI_SYS_PSW = "BPI_SYS_PASSWORD";

    private String[] getFileName(String interfaceName, String folderPath) {
        String[] fileName = new String[2];
        try {

            String[] subKey = interfaceName.split("/");
            String interface1 = subKey[subKey.length - 2];

            String folderName = folderPath + "/" + FileNameUtil.getMap().get(subKey[subKey.length - 2]) + "/Input";

            LOGGER.info("Enters Interface:" + folderName);

            fileName[0] = getFolderWithFile(folderName);
            fileName[1] = FileNameUtil.getInterfaceName().get(interface1);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        }
        return fileName;
    }

    private String getFolderWithFile(String folderName) {
        String fileName = "";
        File dir = new File(folderName);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                fileName = file.getName();
            }

        }

        return fileName;
    }

    /**
     * USED TO APPEND THE FILEnAME WITH DATE
     *
     * @param fileName
     * @param appendValue
     * @return
     */
}
