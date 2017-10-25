package com.stpl.app.model.impl;

import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the ImtdCfpDetails service. Represents a row in the &quot;IMTD_CFP_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ImtdCfpDetailsImpl}.
 * </p>
 *
 * @author
 * @see ImtdCfpDetailsImpl
 * @see com.stpl.app.model.ImtdCfpDetails
 * @generated
 */
public abstract class ImtdCfpDetailsBaseImpl extends ImtdCfpDetailsModelImpl
    implements ImtdCfpDetails {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a imtd cfp details model instance should use the {@link ImtdCfpDetails} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdCfpDetailsLocalServiceUtil.addImtdCfpDetails(this);
        } else {
            ImtdCfpDetailsLocalServiceUtil.updateImtdCfpDetails(this);
        }
    }
}
