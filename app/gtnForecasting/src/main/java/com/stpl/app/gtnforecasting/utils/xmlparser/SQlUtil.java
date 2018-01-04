/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils.xmlparser;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.jboss.logging.Logger;
import org.osgi.framework.FrameworkUtil;

/**
 *
 * @author karthikeyans
 */
public class SQlUtil {

    private final Map<String, String> QUERY_MAP = new HashMap<>();
    private static SQlUtil sqlUtil = null;
    private static final Logger LOGGER = Logger.getLogger(SQlUtil.class);

    private SQlUtil() {
        try {
            Enumeration<URL> urls = FrameworkUtil.getBundle(SQlUtil.class).getBundleContext().getBundle().findEntries("/sqlresources", "*", false);
            getResources(urls);
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

    private void getResources(Enumeration<URL> urls) throws JAXBException, IOException {
        if (urls == null) {
            return;
        }

        while (urls.hasMoreElements()) {
            URL tempUrl = urls.nextElement();
            if (tempUrl.getFile() != null && tempUrl.getFile().contains(".xml")) {
                Map<String, Object> properties = new HashMap<String, Object>(1);
//                properties.put("javax.xml.bind.context.factory",
//                        "org.eclipse.persistence.jaxb.JAXBContextFactory");
                JAXBContext jaxbContext = JAXBContext.newInstance(Sql.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Sql que = (Sql) jaxbUnmarshaller.unmarshal(tempUrl);
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
    
     public static String getQuery(Class clasName,String sqlId) {
        return SQlUtil.getContext().getQUERY_MAP().get(sqlId);
    }

}
