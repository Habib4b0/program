/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao.impl;

import com.stpl.app.adminconsole.dao.ForecastLogicDAO;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ForecastLogicDAOImpl.
 *
 * @author mohamed
 */
public class ForecastLogicDAOImpl implements ForecastLogicDAO {

    /** (non-Javadoc)
     * saveForecastYears(com.stpl.app.adminconsole.model.HelperTable)
     */
    public ForecastConfig saveForecastYears(final ForecastConfig forecastConfig) throws SystemException{
    
        return ForecastConfigLocalServiceUtil.addForecastConfig(forecastConfig);
    }


    public List getForecastVersion(DynamicQuery query) throws SystemException {
       return ForecastConfigLocalServiceUtil.dynamicQuery(query);
    }
}
