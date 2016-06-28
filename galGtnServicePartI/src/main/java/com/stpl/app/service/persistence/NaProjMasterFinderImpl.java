/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.NaProjMaster;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class NaProjMasterFinderImpl extends BasePersistenceImpl<NaProjMaster> implements NaProjMasterFinder {
    private static final Logger LOGGER = Logger.getLogger(NaProjMasterFinderImpl.class);

    /**
     *
     * @param companyValue
     * @param therapeuticClassValue
     * @param productGroupValue
     * @param future3
     * @return
     */
    @Override
    public List getAvailableProducts(Object companyValue, Object therapeuticClassValue, Object productGroupValue, Object future3) {
        Session session = null;
        StringBuilder sql = null;
        try {
            boolean flag = false;
            session = openSession();
          
                if (!companyValue.toString().equals("") || !therapeuticClassValue.toString().equals("") && !therapeuticClassValue.toString().equals("0")) {
                sql = new StringBuilder(CustomSQLUtil.get("loadAvailableProducts"));
                if (!companyValue.toString().equals("")) {
                    flag = true;
                    sql = sql.append(" where CM.COMPANY_NAME='").append(companyValue.toString()).append("' ");
                }
                if (!therapeuticClassValue.toString().equals("") && !therapeuticClassValue.toString().equals("0")) {
                    if (flag) {
                        sql = sql.append(" and ");
                    } else {
                        sql = sql.append(" where ");
                    }
                    sql = sql.append(" IM.THERAPEUTIC_CLASS = '").append(therapeuticClassValue.toString()).append("'");
                }
            }

            //LOGGER.info("getAvailableProducts Query--------->" + sql);
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }
}
