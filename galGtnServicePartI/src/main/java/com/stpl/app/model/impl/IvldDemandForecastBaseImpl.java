package com.stpl.app.model.impl;

import com.stpl.app.model.IvldDemandForecast;
import com.stpl.app.service.IvldDemandForecastLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the IvldDemandForecast service. Represents a row in the &quot;IVLD_DEMAND_FORECAST&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IvldDemandForecastImpl}.
 * </p>
 *
 * @author
 * @see IvldDemandForecastImpl
 * @see com.stpl.app.model.IvldDemandForecast
 * @generated
 */
public abstract class IvldDemandForecastBaseImpl
    extends IvldDemandForecastModelImpl implements IvldDemandForecast {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a ivld demand forecast model instance should use the {@link IvldDemandForecast} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldDemandForecastLocalServiceUtil.addIvldDemandForecast(this);
        } else {
            IvldDemandForecastLocalServiceUtil.updateIvldDemandForecast(this);
        }
    }
}
