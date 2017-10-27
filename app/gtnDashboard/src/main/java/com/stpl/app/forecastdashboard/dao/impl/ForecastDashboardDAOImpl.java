/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.dao.impl;

import com.stpl.app.forecastdashboard.dao.ForecastDashboardDAO;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author nandhakumar
 */
public class ForecastDashboardDAOImpl implements ForecastDashboardDAO{
    /* The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ForecastDashboardDAOImpl.class);
    @Override
    public List getResultList(String query) {
        return (List)RsModelLocalServiceUtil.executeSelectQuery(query,null,null);
    }

    @Override
    public int getBusinessProcessTypeHelperTableSid(String businessProcessType) {
        int businessSid=0;
        
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("listName",CommonUtils.BUSINESS_PROCESS_TYPE));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("description",businessProcessType));
        try {
          List<HelperTable>  resultList = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
          HelperTable helperTable = resultList.get(0);
          businessSid = helperTable.getHelperTableSid();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        
        return businessSid;
    }

    @Override
    public ForecastConfig getForecastConfigurationTimePeriod(int businessProcessTypeSid) {
        LOGGER.info("Entering FC getForecastConfigurationTimePeriod");
        List<ForecastConfig> resultList = null;
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType",businessProcessTypeSid ));
        dynamicQuery.addOrder(OrderFactoryUtil.desc("versionNo"));
        try {
            resultList = ForecastConfigLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        ForecastConfig forecastConfig = null;
        if (resultList != null && !resultList.isEmpty()) {
            forecastConfig = (ForecastConfig) resultList.get(0);
        }
        LOGGER.info("Ending FC getForecastConfigurationTimePeriod");
        return forecastConfig;
    }
    
}
