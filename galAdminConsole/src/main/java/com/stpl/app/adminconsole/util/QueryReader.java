/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.vaadin.data.Property;
import com.vaadin.ui.ComboBox;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.jboss.logging.Logger;

/**
 *
 * @author kasiammal.m
 */
public class QueryReader {

    private static ResourceBundle queryNameBundle = ResourceBundle.getBundle("properties.GlPosting-Queries");
    private static final Logger LOGGER = Logger.getLogger(QueryReader.class);
    final static CommonDAO dao = new CommonDAOImpl();

    public static void main(String[] arg) {
        ComboBox box1 = new ComboBox();
        box1.setData("box1");
        Property pro = (Property) box1;
        pro.setValue("pro");
        ComboBox box = (ComboBox) pro;
    }

    public static String getQuery(String key) {
        return queryNameBundle.getString(key);
    }
    
    public static Object executeSelectQuery( Map<String, Object> input,  String query) {
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            StringBuilder queryString = new StringBuilder();
            if (query != null && !query.isEmpty()) {
                queryString = new StringBuilder(query);
                if (input != null) {
            for (Map.Entry<String, Object> entry : input.entrySet()) {
                final String string = entry.getKey();
                final Object string1 = entry.getValue();
                while (queryString.toString().contains(string)) {
                    queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                }
            }
            
        }
                returnList = (List<Object[]>) dao.executeSelectQuery(queryString.toString(), null, null);
            }
        } catch (Exception e) {
         LOGGER.error(e);
        }
        return returnList;
    }
}
