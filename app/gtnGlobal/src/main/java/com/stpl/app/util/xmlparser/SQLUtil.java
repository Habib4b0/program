/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util.xmlparser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author karthikeyans
 */
public class SQLUtil {

    private final Map<String, String> QUERY_MAP = new HashMap<>();
    private static SQLUtil sqlUtil = null;
    private File[] files = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(SQLUtil.class);

    private SQLUtil() {
        try {
            URL url = getClass().getResource("/sqlresources/");
            File file = new File(url.getFile().replace("%20", " "));
            this.files = file.listFiles();
            getResources();
        } catch (IOException | JAXBException e) {
            LOGGER.error(e.getMessage());
        }

    }

    private static SQLUtil getContext() {
        if (sqlUtil == null) {
            sqlUtil = new SQLUtil();
        }
        return sqlUtil;
    }

    private void getResources() throws JAXBException, IOException {
        if (files == null) {
            return;
        }

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file != null && file.isFile() && file.getCanonicalPath().endsWith(".xml")) {
                JAXBContext jaxbContext = JAXBContext.newInstance(Sql.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Sql que = (Sql) jaxbUnmarshaller.unmarshal(file);
                List<SqlEntity> list = que.getSqlEntity();
                for (SqlEntity ans : list) {
                    QUERY_MAP.put(ans.getSqlID(), ans.getSqlQuery());
                }
            }
        }

    }

    private Map<String, String> getQueryMap() {
        return QUERY_MAP;
    }

    public static String getQuery(String sqlId) {
        return SQLUtil.getContext().getQueryMap().get(sqlId);
    }

}
