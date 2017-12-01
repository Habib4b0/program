/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author Abishek.Ram
 */
public class NMSalesProjectionMasterImpl {

    private static final Logger LOGGER = Logger
            .getLogger(NMSalesProjectionMasterImpl.class);

    public Object executeSelectQuery(String query, Object udc1, Object udc2) {

        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            returnList = HelperTableLocalServiceUtil.executeSelectQuery(query);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        }
        return returnList;
    }

    public Object executeBulkUpdateQuery(String query, Object udc1, Object udc2) {

        try {
            HelperTableLocalServiceUtil.executeUpdateQuery(query);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        }
        return true;
    }

    public Object executeUpdateQuery(List<?> nmSalesList, Object udc1, Object udc2, Object udc3) {

        try {
            HelperTableLocalServiceUtil.executeUpdateEntitiy(nmSalesList);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        } finally {

        }
        return true;
    }
    
    public List getAssumptionResult(List input, String queryName) {
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder(CustomSQLUtil.get(queryName));

        try {
            if ("update".equals(input.get(0))) {
                for (int i = 1; i < input.size(); i++) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(input.get(i)));
                }
//            	LOGGER.debug("Query After Change : "+sql);
                HelperTableLocalServiceUtil.executeUpdateQuery(sql.toString());
            } else {
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }

//                LOGGER.debug("Query After Change : "+sql);
                list = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getCause());
            LOGGER.error(sql.toString());
        } 

        return list;
    }
    /**
     * ************** END OF BPM METHODS***********
     */
}
