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
            FileInputStream fileInput = new FileInputStream(file);
            Properties_Reader.class.getResource("Test").getFile();
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();
            String hd_group = properties.getProperty("hierarchy_definition-group_id");
            String hd_artifact_id = properties.getProperty("hierarchy_definition-artifact_id");
            String hd_version = properties.getProperty("hierarchy_definition-version");
            String hdr_group_id = properties.getProperty("hierarchy_definition_rules-group_id");
            String hdr_artifact_id = properties.getProperty("hierarchy_definition_rules-artifact_id");
            String hdr_version = properties.getProperty("hierarchy_definition_rules-version");
            LOGGER.info(hd_group);
            LOGGER.info(hd_artifact_id);
            LOGGER.info(hd_version);
            LOGGER.info(hdr_group_id);
            LOGGER.info(hdr_artifact_id);
            LOGGER.info(hdr_version);
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

}
