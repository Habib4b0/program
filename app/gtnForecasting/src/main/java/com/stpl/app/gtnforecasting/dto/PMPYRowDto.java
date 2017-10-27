/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.dto;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Maheshj
 */
public class PMPYRowDto {
    private Map<String, String> properties = new HashMap<>();
   
    String type=StringUtils.EMPTY;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public Map<String, String> getProperties() {
        return properties;
}

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
    
}
