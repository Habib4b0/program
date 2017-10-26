/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.priceschedule.util;

/**
 *
 * @author shrihariharan
 */
public class QueryUtil {
    
    
    public String getPsDetailsSidForDelete(int psModelSid){
      return "select PS_DETAILS_SID from PS_DETAILS where PS_MODEL_SID="+psModelSid;          
    }
    
}
