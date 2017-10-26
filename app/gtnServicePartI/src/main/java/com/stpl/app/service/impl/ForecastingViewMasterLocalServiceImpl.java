package com.stpl.app.service.impl;

import com.stpl.app.service.base.ForecastingViewMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ForecastingViewMasterFinderUtil;

/**
 * The implementation of the forecasting view master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.ForecastingViewMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ForecastingViewMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.ForecastingViewMasterLocalServiceUtil
 */
public class ForecastingViewMasterLocalServiceImpl
    extends ForecastingViewMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.ForecastingViewMasterLocalServiceUtil} to access the forecasting view master local service.
     */
    
     public java.util.List findViewByName(java.lang.String viewName,
            java.lang.String forecastType, java.lang.String userId, String viewType) {
        return ForecastingViewMasterFinderUtil.findViewByName(viewName, forecastType, userId, viewType);
    }
     
     public java.util.List findViewByNameForChannels(java.lang.String viewName,
        java.lang.String forecastType, java.lang.String userId,
        java.lang.String viewType){
         return ForecastingViewMasterFinderUtil.findViewByNameForChannels(viewName, forecastType, userId, viewType);
     }
}
