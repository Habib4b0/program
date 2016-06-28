/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.queryUtils;

import com.vaadin.data.Property;
import com.vaadin.ui.ComboBox;
import java.util.ResourceBundle;

/**
 *
 * @author karthikeyans
 */
public class QueryReader {

    private static ResourceBundle listNameBundle = ResourceBundle.getBundle("custom-sql.PPAQueries");

    public static void main(String[] arg) {
        ComboBox box1 = new ComboBox();
        box1.setData("box1");
        Property pro = (Property) box1;
        pro.setValue("pro");
        ComboBox box = (ComboBox) pro;
    }

    public static String getQuery(String key) {
        return listNameBundle.getString(key);
    }

}
