/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.impl;

import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class PsContractDetailsImpl {
      
     private static final Logger LOGGER = LoggerFactory.getLogger(PsContractDetailsImpl.class);
    /**
     * Save the CFP Details to CFP Details Attached
     * 
     * @param input
     * @param future
     * @return 
     */
    public static Boolean savePsDetailsAttached(final List<Object> input, final Object future){
        Boolean retFlag;
        String sql = SQlUtil.getQuery("com.contractDashboard.savePS");
        try {
            sql=sql.replaceFirst("[?]", input.get(0).toString());
            sql=sql.replaceFirst("[?]", input.get(1).toString());
            sql=sql.replaceFirst("[?]", input.get(2).toString());
            sql=sql.replaceFirst("[?]", input.get(3).toString());
            sql=sql.replaceFirst("[?]", input.get(4).toString());
            sql=sql.replaceFirst("[?]", input.get(5).toString());
            sql=sql.replace("@PS_START_DATE","'"+input.get(6).toString()+"'");
            sql=sql.replace("@PS_END_DATE",String.valueOf(input.get(7)).equals("null") ? "NULL" : "'"+input.get(7).toString()+"'");
            sql=sql.replace("@ATTACHDATE",input.get(8).toString());
            LOGGER.debug("====savePsDetailsAttached========" +sql);
            HelperTableLocalServiceUtil.executeUpdateQuery(sql);
            retFlag = true;

        } catch (Exception e) {
            retFlag = false;
           LOGGER.error(e.getMessage());
           LOGGER.error(sql);
        } 
        return retFlag;

    }
    
}
