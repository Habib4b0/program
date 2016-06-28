/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.model.RsContractDetails;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author pvinoth
 */
public class RsContractDetailsFinderImpl extends BasePersistenceImpl<RsContractDetails> implements RsContractDetailsFinder{
    private static final Logger LOGGER = Logger.getLogger(RsContractDetailsFinderImpl.class);
    /**
     * Save the CFP Details to CFP Details Attached
     * 
     * @param input
     * @param future
     * @return 
     */
    public Boolean saveRsDetailsAttached(final List<Object> input, final Object future){
               Session session = null;
        Boolean retFlag;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();
            sql = CustomSQLUtil.get("com.contractDashboard.saveRS");
            sql=sql.replaceFirst("[?]", input.get(0).toString());
            sql=sql.replaceFirst("[?]", input.get(1).toString());
            sql=sql.replaceFirst("[?]", input.get(2).toString());
            sql=sql.replaceFirst("[?]", input.get(3).toString());
            sql=sql.replaceFirst("[?]", input.get(4).toString());
            sql=sql.replaceFirst("[?]", input.get(5).toString());
            sql=sql.replace("@RS_START_DATE","'"+input.get(6).toString()+"'");
            sql=sql.replace("@RS_END_DATE",String.valueOf(input.get(7)).equals("null") ? "NULL" : "'"+input.get(7).toString()+"'");
            SQLQuery q = session.createSQLQuery(sql);
            q.executeUpdate();
            retFlag = true;
        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }
        return retFlag;

    }
    
    public void saveFormulaToContractRs(int contRsdSid,int rsdSid,int itemSid){
        
         Session session = null;
         String sql = StringUtils.EMPTY;
        
        try {
            session = openSession();
            
            sql = "MERGE RS_CONTRACT_DETAILS_FR AS T USING RS_DETAILS_FR AS S ON(T.RS_CONTRACT_DETAILS_SID="+contRsdSid+" AND T.ITEM_MASTER_SID="+itemSid
                    +" ) WHEN NOT MATCHED BY TARGET AND S.RS_DETAILS_SID="+rsdSid+" AND S.ITEM_MASTER_SID="+itemSid+" AND S.INBOUND_STATUS <> 'D' THEN "
                    + "INSERT (RS_CONTRACT_DETAILS_SID,ITEM_MASTER_SID,FORMULA_ID,FORMULA_METHOD_ID,INBOUND_STATUS,RECORD_LOCK_STATUS,CREATED_BY,MODIFIED_BY) VALUES("
                    + contRsdSid+","+itemSid+",S.FORMULA_ID,S.FORMULA_METHOD_ID,'A','false',S.CREATED_BY,S.MODIFIED_BY) WHEN MATCHED AND S.RS_DETAILS_SID="+rsdSid
                    +" AND S.ITEM_MASTER_SID="+itemSid+" AND S.INBOUND_STATUS <> 'D' THEN UPDATE SET"
                    + " T.RS_CONTRACT_DETAILS_SID="+contRsdSid+",T.ITEM_MASTER_SID="+itemSid+", T.FORMULA_ID=S.FORMULA_ID,T.FORMULA_METHOD_ID=S.FORMULA_METHOD_ID,"
                    + "T.INBOUND_STATUS='U',T.RECORD_LOCK_STATUS='false',"
                    + "T.CREATED_BY=S.CREATED_BY,T.MODIFIED_BY=S.MODIFIED_BY;";
           
         SQLQuery q = session.createSQLQuery(sql);
            q.executeUpdate();
            
        } catch (Exception e) {
            
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }
        
    }

        
}