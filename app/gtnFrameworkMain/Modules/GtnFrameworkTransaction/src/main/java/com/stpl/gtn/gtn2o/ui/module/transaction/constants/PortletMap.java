/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.transaction.constants;

import java.io.IOException;
import java.util.Properties;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Karthik.Raja
 */
public class PortletMap {
	
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(PortletMap.class);
	
    public static void main(String[] args) throws IOException {
       Properties prop = new Properties();
		prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("Portlet.properties"));
		LOGGER.info(""+prop.getProperty("demand"));
    }
}
