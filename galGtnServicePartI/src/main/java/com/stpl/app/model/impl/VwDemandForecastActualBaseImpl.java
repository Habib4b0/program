package com.stpl.app.model.impl;

import com.stpl.app.model.VwDemandForecastActual;
import com.stpl.app.service.VwDemandForecastActualLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the VwDemandForecastActual service. Represents a row in the &quot;vw_DEMAND_FORECAST_ACTUAL&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link VwDemandForecastActualImpl}.
 * </p>
 *
 * @author
 * @see VwDemandForecastActualImpl
 * @see com.stpl.app.model.VwDemandForecastActual
 * @generated
 */
public abstract class VwDemandForecastActualBaseImpl
    extends VwDemandForecastActualModelImpl implements VwDemandForecastActual {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a vw demand forecast actual model instance should use the {@link VwDemandForecastActual} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwDemandForecastActualLocalServiceUtil.addVwDemandForecastActual(this);
        } else {
            VwDemandForecastActualLocalServiceUtil.updateVwDemandForecastActual(this);
        }
    }
}
