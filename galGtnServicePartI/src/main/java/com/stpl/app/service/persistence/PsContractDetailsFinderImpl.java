/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.model.PsContractDetails;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;

/**
 *
 * @author pvinoth
 */
public class PsContractDetailsFinderImpl extends BasePersistenceImpl<PsContractDetails> implements PsContractDetailsFinder{
     
     private static final Logger LOGGER = Logger.getLogger(PsContractDetailsFinderImpl.class);
    /**
     * Save the CFP Details to CFP Details Attached
     * 
     * @param input
     * @param future
     * @return 
     */
    public Boolean savePsDetailsAttached(final List<Object> input, final Object future){
               Session session = null;
        Boolean retFlag;
        String sql = CustomSQLUtil.get("com.contractDashboard.savePS");
        try {
            session = openSession();
            sql=sql.replaceFirst("[?]", input.get(0).toString());
            sql=sql.replaceFirst("[?]", input.get(1).toString());
            sql=sql.replaceFirst("[?]", input.get(2).toString());
            sql=sql.replaceFirst("[?]", input.get(3).toString());
            sql=sql.replaceFirst("[?]", input.get(4).toString());
            sql=sql.replaceFirst("[?]", input.get(5).toString());
            sql=sql.replace("@PS_START_DATE","'"+input.get(6).toString()+"'");
            sql=sql.replace("@PS_END_DATE",String.valueOf(input.get(7)).equals("null") ? "NULL" : "'"+input.get(7).toString()+"'");
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
}
