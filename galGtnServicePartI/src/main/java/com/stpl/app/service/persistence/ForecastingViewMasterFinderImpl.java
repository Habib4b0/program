/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class ForecastingViewMasterFinderImpl extends BasePersistenceImpl<ForecastingViewMaster> implements ForecastingViewMasterFinder {

    private static final Logger LOGGER = Logger.getLogger(ForecastingViewMasterFinderImpl.class);

    public List findViewByName(String viewName, String forecastType, String userId, String viewType) {
        Session session = null;
        String customSql = StringUtils.EMPTY;
        try {
            LOGGER.info("Entering findViewByName method with viewName " + viewName + " forecastType " + forecastType + " userId " + userId + " viewType " + viewType);

            session = openSession();
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
            Query sqlQuery = session.createSQLQuery(customSql);
//            LOGGER.info("\n\nSearch query is -----> " + customSql + "\n\n");
            LOGGER.info("End of findViewByName method");
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return null;
        } finally {
            closeSession(session);
        }
    }

    public List findViewByNameForChannels(String viewName, String forecastType, String userId, String viewType) {
        Session session = null;
        String customSql = StringUtils.EMPTY;
        try {
            LOGGER.info("Entering findViewByName method with viewName " + viewName + " forecastType " + forecastType + " userId " + userId + " viewType " + viewType);

            session = openSession();
            customSql = CustomSQLUtil.get("findViewByNameJoinforchannel");
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
            Query sqlQuery = session.createSQLQuery(customSql);
//            LOGGER.info("\n\nSearch query is -----> " + customSql + "\n\n");
            LOGGER.info("End of findViewByName method");
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return null;
        } finally {
            closeSession(session);
        }
    }
}
