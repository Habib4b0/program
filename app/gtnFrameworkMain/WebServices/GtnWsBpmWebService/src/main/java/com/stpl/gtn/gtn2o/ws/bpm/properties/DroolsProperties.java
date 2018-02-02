package com.stpl.gtn.gtn2o.ws.bpm.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class DroolsProperties {

    private DroolsProperties() {
        /**
         * empty constructor
         */
    }

    private static Properties properties = new Properties();
    private static Properties cffProperties = new Properties();
    private static boolean isPrinted = false;

    private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(DroolsProperties.class);

    private static final String ERROR_READING_PROPERTY_FILE = "Error while reading the property file :";
    /**
     * method will return properties class
     *
     * @return
     */
    public static Properties getPropertiesData() {
        String path = "";
        try {
            path = System.getProperty("jboss.server.config.dir");
            if (!isPrinted) {
                logger.debug("jboss.server.config.dir :" + path);
            }
            path = path.replace("standalone", "bpmconfig");
            path = path.replace("configuration", "forecasting_properties.properties");
            if (!isPrinted) {
                logger.debug("Resources Path :[" + path + "]");
            }
            File file = new File(path);
            if (!isPrinted) {
                logger.debug("File resources Path :" + file.getAbsolutePath());
            }
            FileInputStream fileInput = new FileInputStream(file);
            properties.load(fileInput);
            fileInput.close();
            if (!isPrinted) {
                Enumeration<Object> enuKeys = properties.keys();
                while (enuKeys.hasMoreElements()) {
                    String key = (String) enuKeys.nextElement();
                    String value = properties.getProperty(key);
                    logger.debug("Data in Properties File :Key :" + key + ": Value :" + value);
                }
                isPrinted = true;
            }
            return properties;
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            logger.error("Please check the forecasting_properties.properties file in following path :[" + path + "]");
            throw new IllegalArgumentException(ERROR_READING_PROPERTY_FILE + e);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(ERROR_READING_PROPERTY_FILE + e);
        }
    }

    /**
     * method will return properties class
     *
     * @return
     */
    public static Properties getCffPropertiesData() {
        String path = "";
        try {
            path = System.getProperty("jboss.server.config.dir");
            if (!isPrinted) {
                logger.info("jboss.server.config.dir :" + path);
            }
            path = path.replace("standalone", "bpmconfig");
            path = path.replace("configuration", "cff_properties.properties");
            if (!isPrinted) {
                logger.info("Resources Path :[" + path + "]");
            }
            File file = new File(path);
            if (!isPrinted) {
                logger.info("File resources Path :" + file.getAbsolutePath());
            }
            FileInputStream fileInput = new FileInputStream(file);
            cffProperties.load(fileInput);
            fileInput.close();
            if (!isPrinted) {
                Enumeration<Object> enuKeys = cffProperties.keys();
                while (enuKeys.hasMoreElements()) {
                    String key = (String) enuKeys.nextElement();
                    String value = cffProperties.getProperty(key);
                    logger.info("Data in cffProperties File :Key :" + key + ": Value :" + value);
                }
                isPrinted = true;
            }
            return cffProperties;
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            logger.error("Please check the hierarchy_properties.properties file in following path :[" + path + "]");
            throw new IllegalArgumentException(ERROR_READING_PROPERTY_FILE + e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(ERROR_READING_PROPERTY_FILE + e.getMessage());
        }
    }

}
