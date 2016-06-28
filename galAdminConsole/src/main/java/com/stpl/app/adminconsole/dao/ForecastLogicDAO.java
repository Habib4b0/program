/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao;

import com.stpl.app.model.ForecastConfig;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface ForecastLogicDAO.
 *
 * @author mohamed
 */
public interface ForecastLogicDAO {
    
    /**
     * Save forecast years.
     *
     * @param helperTable the helper table
     * @return the helper table
     * @throws SystemException the system exception
     */
    ForecastConfig saveForecastYears(ForecastConfig forecastConfig) throws SystemException;
    
    /**
     * 
     */
    List getForecastVersion(final DynamicQuery query) throws SystemException;
    
   
}
