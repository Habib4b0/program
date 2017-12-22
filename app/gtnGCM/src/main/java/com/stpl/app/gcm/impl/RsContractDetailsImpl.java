/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.impl;

import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class RsContractDetailsImpl {
    private static final Logger LOGGER = Logger.getLogger(RsContractDetailsImpl.class);
    /**
     * Save the CFP Details to CFP Details Attached
     * 
     * @param input
     * @param future
     * @return 
     */
    public static Boolean saveRsDetailsAttached(final List<Object> input, final Object future){
        Boolean retFlag;
        String sql = StringUtils.EMPTY;
        try {
            sql = SQlUtil.getQuery("com.contractDashboard.saveRS");
            sql=sql.replaceFirst("[?]", input.get(0).toString());
            sql=sql.replaceFirst("[?]", input.get(1).toString());
            sql=sql.replaceFirst("[?]", input.get(2).toString());
            sql=sql.replaceFirst("[?]", input.get(3).toString());
            sql=sql.replaceFirst("[?]", input.get(4).toString());
            sql=sql.replaceFirst("[?]", input.get(5).toString());
            sql=sql.replace("@RS_START_DATE","'"+input.get(6).toString()+"'");
            sql=sql.replace("@RS_END_DATE",String.valueOf(input.get(7)).equals("null") ? "NULL" : "'"+input.get(7).toString()+"'");
            sql=sql.replace("@ATTACHDATE", input.get(8).toString());
            HelperTableLocalServiceUtil.executeUpdateQuery(sql);
            retFlag = true;
        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return retFlag;

    }
    
    public void saveFormulaToContractRs(int contRsdSid,int rsdSid,int itemSid){
        
         String sql = StringUtils.EMPTY;
        
        try {
            
            sql = "MERGE RS_CONTRACT_DETAILS_FR AS T USING RS_DETAILS_FR AS S ON(T.RS_CONTRACT_DETAILS_SID="+contRsdSid+" AND T.ITEM_MASTER_SID="+itemSid
                    +" ) WHEN NOT MATCHED BY TARGET AND S.RS_DETAILS_SID="+rsdSid+" AND S.ITEM_MASTER_SID="+itemSid+" AND S.INBOUND_STATUS <> 'D' THEN "
                    + "INSERT (RS_CONTRACT_DETAILS_SID,ITEM_MASTER_SID,FORMULA_ID,FORMULA_METHOD_ID,INBOUND_STATUS,RECORD_LOCK_STATUS,CREATED_BY,MODIFIED_BY) VALUES("
                    + contRsdSid+","+itemSid+",S.FORMULA_ID,S.FORMULA_METHOD_ID,'A','false',S.CREATED_BY,S.MODIFIED_BY) WHEN MATCHED AND S.RS_DETAILS_SID="+rsdSid
                    +" AND S.ITEM_MASTER_SID="+itemSid+" AND S.INBOUND_STATUS <> 'D' THEN UPDATE SET"
                    + " T.RS_CONTRACT_DETAILS_SID="+contRsdSid+",T.ITEM_MASTER_SID="+itemSid+", T.FORMULA_ID=S.FORMULA_ID,T.FORMULA_METHOD_ID=S.FORMULA_METHOD_ID,"
                    + "T.INBOUND_STATUS='U',T.RECORD_LOCK_STATUS='false',"
                    + "T.CREATED_BY=S.CREATED_BY,T.MODIFIED_BY=S.MODIFIED_BY;";
           
         HelperTableLocalServiceUtil.executeUpdateQuery(sql);
            
        } catch (Exception e) {
            
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        
    }
    
}
}
