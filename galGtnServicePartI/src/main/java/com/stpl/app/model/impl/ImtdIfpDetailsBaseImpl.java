package com.stpl.app.model.impl;

import com.stpl.app.model.ImtdIfpDetails;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the ImtdIfpDetails service. Represents a row in the &quot;IMTD_IFP_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ImtdIfpDetailsImpl}.
 * </p>
 *
 * @author
 * @see ImtdIfpDetailsImpl
 * @see com.stpl.app.model.ImtdIfpDetails
 * @generated
 */
public abstract class ImtdIfpDetailsBaseImpl extends ImtdIfpDetailsModelImpl
    implements ImtdIfpDetails {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a imtd ifp details model instance should use the {@link ImtdIfpDetails} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdIfpDetailsLocalServiceUtil.addImtdIfpDetails(this);
        } else {
            ImtdIfpDetailsLocalServiceUtil.updateImtdIfpDetails(this);
        }
    }
}
