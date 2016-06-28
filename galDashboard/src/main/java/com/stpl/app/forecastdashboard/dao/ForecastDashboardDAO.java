/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.dao;

import com.stpl.app.model.ForecastConfig;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author nandhakumar
 */
public interface ForecastDashboardDAO {
    
     /**
     * Gets approved projection.
     *
     * @param query the query
     * @return the item groups list
     * @throws SystemException the system exception
     */
    List getResultList(String query);
    
    /**
     * Method to get the Business Process Type Helper Table Sid
     * @param businessProcessType
     * @return helperTable sid
     */
    int getBusinessProcessTypeHelperTableSid(String businessProcessType);
    
    /**
     * Gets the Forecast Configuration details according to the Business type passes
     * @param businessProcessTypeSid
     * @return ForecastConfiguration
     */
    public ForecastConfig getForecastConfigurationTimePeriod(int businessProcessTypeSid);
    
}
