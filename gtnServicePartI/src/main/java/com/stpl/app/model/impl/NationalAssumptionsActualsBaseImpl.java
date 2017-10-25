package com.stpl.app.model.impl;

import com.stpl.app.model.NationalAssumptionsActuals;
import com.stpl.app.service.NationalAssumptionsActualsLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the NationalAssumptionsActuals service. Represents a row in the &quot;NATIONAL_ASSUMPTIONS_ACTUALS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NationalAssumptionsActualsImpl}.
 * </p>
 *
 * @author
 * @see NationalAssumptionsActualsImpl
 * @see com.stpl.app.model.NationalAssumptionsActuals
 * @generated
 */
public abstract class NationalAssumptionsActualsBaseImpl
    extends NationalAssumptionsActualsModelImpl
    implements NationalAssumptionsActuals {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a national assumptions actuals model instance should use the {@link NationalAssumptionsActuals} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NationalAssumptionsActualsLocalServiceUtil.addNationalAssumptionsActuals(this);
        } else {
            NationalAssumptionsActualsLocalServiceUtil.updateNationalAssumptionsActuals(this);
        }
    }
}
