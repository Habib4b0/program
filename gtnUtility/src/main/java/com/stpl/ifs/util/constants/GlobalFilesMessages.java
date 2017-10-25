/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util.constants;

import com.stpl.app.util.service.PropertiesReader;

/**
 *
 * @author sathyaseelan.v
 */
public class GlobalFilesMessages {
    private static final PropertiesReader.ExtProperties properties = PropertiesReader.getInstance().getConfirmationMessages();
    public static String getOpenMessageName() {
        return properties.getProperty("OPEN_MESSAGE_NAME_001");
    }
   
}
