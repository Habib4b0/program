/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.MasterDataAttribute;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class MasterDataAttributeFinderImpl extends BasePersistenceImpl<MasterDataAttribute> implements MasterDataAttributeFinder {

    private static final Logger LOGGER = Logger.getLogger(MasterDataAttributeFinderImpl.class);
    public List getTotalLives(Object[] inputs) {
        List list = new ArrayList();
        Session session = null;
        String coundition = (String) inputs[1];
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
        try {
            if (coundition.equals("TotalLives")) {
                queryBuilder.append("  SELECT COLUMN_3 as col3  ");
            } else {
                queryBuilder.append("  SELECT MASTER_ID,COLUMN_1 as col1,COLUMN_2 as col2,COLUMN_3 as col3   ");
            }
            queryBuilder.append(" FROM MASTER_DATA_ATTRIBUTE M JOIN COMPANY_MASTER C ON C.COMPANY_ID=M.MASTER_ID  ");
            queryBuilder.append(" JOIN CCP_DETAILS CCP  ON C.COMPANY_MASTER_SID=CCP.COMPANY_MASTER_SID JOIN PROJECTION_DETAILS P ON CCP.CCP_DETAILS_SID=P.CCP_DETAILS_SID  ");
            queryBuilder.append(" where M.MASTER_TYPE='COMPANY_MASTER' AND M.MASTER_ATTRIBUTE LIKE'%COV_LIVES%' and P.PROJECTION_MASTER_SID=" + inputs[0] + "   ");




            // where projectionMasterSid=81
//            LOGGER.info("Query:\n" + queryBuilder.toString());
            session = openSession();
            Query sqlQuery = session.createSQLQuery(queryBuilder.toString());

            list = sqlQuery.list();

//            LOGGER.info("Query:\n" + queryBuilder.toString() + "\n\nquery hit list size: " + list.size() + "\n\n");

        } catch (Exception ex) {
            LOGGER.error(ex);
            LOGGER.error(queryBuilder.toString());
        } finally {
            closeSession(session);
        }

        return list;
    }

    public List getLives(Object[] inputs) {

        return null;
    }

}
