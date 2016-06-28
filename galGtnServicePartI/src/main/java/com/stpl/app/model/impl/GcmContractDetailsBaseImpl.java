package com.stpl.app.model.impl;

import com.stpl.app.model.GcmContractDetails;
import com.stpl.app.service.GcmContractDetailsLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the GcmContractDetails service. Represents a row in the &quot;GCM_CONTRACT_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link GcmContractDetailsImpl}.
 * </p>
 *
 * @author
 * @see GcmContractDetailsImpl
 * @see com.stpl.app.model.GcmContractDetails
 * @generated
 */
public abstract class GcmContractDetailsBaseImpl
    extends GcmContractDetailsModelImpl implements GcmContractDetails {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a gcm contract details model instance should use the {@link GcmContractDetails} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            GcmContractDetailsLocalServiceUtil.addGcmContractDetails(this);
        } else {
            GcmContractDetailsLocalServiceUtil.updateGcmContractDetails(this);
        }
    }
}
