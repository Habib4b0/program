package com.stpl.app.adminconsole.bpm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.jboss.logging.Logger;

public class Properties_Reader {

    private static final Logger LOGGER = Logger.getLogger(Properties_Reader.class);

    public static void main(String[] args) {
        try {
            File file = new File("src/main/resources/com.stpl.app.bpm/bpm.properties");
            Properties properties;
            try (FileInputStream fileInput = new FileInputStream(file)) {
                Properties_Reader.class.getResource("Test").getFile();
                properties = new Properties();
                properties.load(fileInput);
            }
            String hd_group = properties.getProperty("hierarchy_definition-group_id");
            String hd_artifact_id = properties.getProperty("hierarchy_definition-artifact_id");
            String hd_version = properties.getProperty("hierarchy_definition-version");
            String hdr_group_id = properties.getProperty("hierarchy_definition_rules-group_id");
            String hdr_artifact_id = properties.getProperty("hierarchy_definition_rules-artifact_id");
            String hdr_version = properties.getProperty("hierarchy_definition_rules-version");
            LOGGER.debug(hd_group);
            LOGGER.debug(hd_artifact_id);
            LOGGER.debug(hd_version);
            LOGGER.debug(hdr_group_id);
            LOGGER.debug(hdr_artifact_id);
            LOGGER.debug(hdr_version);
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

}
