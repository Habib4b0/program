package com.stpl.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsProperties {

    private static final Properties properties = new Properties();
    private static boolean isPrinted = false;

    private static final Logger logger = LoggerFactory.getLogger(DroolsProperties.class);

    /**
     * method will return properties class
     *
     * @return
     */
    public static Properties getPropertiesData() {
        String path = StringUtils.EMPTY;
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
            try (FileInputStream fileInput = new FileInputStream(file)) {
                properties.load(fileInput);
            }
            if (!isPrinted) {
                Enumeration<Object> enuKeys = properties.keys();
                while (enuKeys.hasMoreElements()) {
                    String key = (String) enuKeys.nextElement();
                    String value = properties.getProperty(key);
                    logger.debug("Data in Properties File :Key :" + key + ": Value :" + value);
                }
                isPrinted = true;
            }
            return (Properties)properties.clone();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            logger.error("Please check the hierarchy_properties.properties file in following path :[" + path + "]");
            throw new IllegalArgumentException("Error while reading the property file :" + e);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException("Error while reading the property file :" + e);
        }
    }

}
