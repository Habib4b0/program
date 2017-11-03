/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asi.ui.customcomponentdemo.demo.dto;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesProjectionDTO.
 *
 * @author maheshj
 */
public class SalesProjectionDTO {

    /** The check. */
//    private boolean checkRecord;

    /** The level. */
    private String levelName = "";
    
    /** The group. */
    private String group = "";
    
    /** The baseline. */
    private String baseline = "";
    
    /** The methodology. */
    private String methodology = "";
   
    private Map<String, String> properties=new HashMap<String, String>();

//    public boolean isCheckRecord() {
//        return checkRecord;
//    }
//
//    public void setCheckRecord(boolean checkRecord) {
//        this.checkRecord = checkRecord;
//    }

    
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getBaseline() {
        return baseline;
    }

    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
    
}
