/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util.xmlparser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.jboss.logging.Logger;

/**
 *
 * @author karthikeyans
 */
public class SQlUtil {

    private final Map<String, String> QUERY_MAP = new HashMap<>();
    private static SQlUtil sqlUtil = null;
    private File[] files = null;
    private static final Logger LOGGER = Logger.getLogger(SQlUtil.class);
    

    private SQlUtil() {
        try {
            URL url = getClass().getResource("/sqlresources/");
            File file = new File(url.getFile().replace("%20", " "));
            this.files = file.listFiles();
            getResources();
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    private static SQlUtil getContext() {
        if (sqlUtil == null) {
            sqlUtil = new SQlUtil();
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

    private Map<String, String> getQUERY_MAP() {
        return QUERY_MAP;
    }

    public static String getQuery(String sqlId) {
        return SQlUtil.getContext().getQUERY_MAP().get(sqlId);
    }

}
