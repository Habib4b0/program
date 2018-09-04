/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Abishek.Ram
 */
public class ForecastingViewMasterImpl {
     public static final Logger LOGGER = LoggerFactory
            .getLogger(ForecastingViewMasterImpl.class);
    public List findViewByName(String viewName, String forecastType, String userId, String viewType) {
        String customSql = StringUtils.EMPTY;
        try {
            LOGGER.debug("Entering findViewByName method with viewName= {}, forecastType= {}, userId= {}, viewType= {}  " , viewName, forecastType, userId, viewType);


            customSql = SQlUtil.getQuery(getClass(),"findViewByNameJoin");
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
            if (("private").equalsIgnoreCase(viewType)) {
                if (StringUtils.isNotEmpty(userId)
                        && StringUtils.isNotBlank(userId)) {
                    customSql += "AND FVM.created_By = " + userId;
                }
            }
            LOGGER.debug("End of findViewByName method");
            return HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return null;
        } 
    }
}
