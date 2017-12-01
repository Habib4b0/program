/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Abishek.Ram
 */
public class ForecastingViewMasterImpl {
     public static final Logger LOGGER = Logger
            .getLogger(ForecastingViewMasterImpl.class);
    public List findViewByName(String viewName, String forecastType, String userId, String viewType) {
        Session session = null;
        String customSql = StringUtils.EMPTY;
        try {
            LOGGER.debug("Entering findViewByName method with viewName " + viewName + " forecastType " + forecastType + " userId " + userId + " viewType " + viewType);


            customSql = CustomSQLUtil.get("findViewByNameJoin");
//            customSql += " PM.projection_Master_Sid = FVM.projection_Id "; 
            if (StringUtils.isNotEmpty(viewType)
                    && StringUtils.isNotBlank(viewType)) {
                customSql += " FVM.view_Type ='" + viewType + "' ";
            }
            if (StringUtils.isNotEmpty(viewName)
                    && StringUtils.isNotBlank(viewName)) {
                customSql += " AND FVM.view_Name LIKE '" + viewName + "' ";
            }

            if (StringUtils.isNotEmpty(forecastType)
                    && StringUtils.isNotBlank(forecastType)) {
                customSql += " AND PM.forecasting_Type = '" + forecastType + "' ";
            }
            if (viewType.equalsIgnoreCase("private")) {
                if (StringUtils.isNotEmpty(userId)
                        && StringUtils.isNotBlank(userId)) {
                    customSql += "AND FVM.created_By = " + userId;
                }
            }
//            LOGGER.debug("\n\nSearch query is -----> " + customSql + "\n\n");
            LOGGER.debug("End of findViewByName method");
            return HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return null;
        } 
    }
}
