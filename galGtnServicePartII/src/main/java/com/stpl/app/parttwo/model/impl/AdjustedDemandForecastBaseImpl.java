package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.AdjustedDemandForecast;
import com.stpl.app.parttwo.service.AdjustedDemandForecastLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the AdjustedDemandForecast service. Represents a row in the &quot;ADJUSTED_DEMAND_FORECAST&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AdjustedDemandForecastImpl}.
 * </p>
 *
 * @author
 * @see AdjustedDemandForecastImpl
 * @see com.stpl.app.parttwo.model.AdjustedDemandForecast
 * @generated
 */
public abstract class AdjustedDemandForecastBaseImpl
    extends AdjustedDemandForecastModelImpl implements AdjustedDemandForecast {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a adjusted demand forecast model instance should use the {@link AdjustedDemandForecast} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AdjustedDemandForecastLocalServiceUtil.addAdjustedDemandForecast(this);
        } else {
            AdjustedDemandForecastLocalServiceUtil.updateAdjustedDemandForecast(this);
        }
    }
}
