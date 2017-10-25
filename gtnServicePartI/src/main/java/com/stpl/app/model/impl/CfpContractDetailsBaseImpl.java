package com.stpl.app.model.impl;

import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the CfpContractDetails service. Represents a row in the &quot;CFP_CONTRACT_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CfpContractDetailsImpl}.
 * </p>
 *
 * @author
 * @see CfpContractDetailsImpl
 * @see com.stpl.app.model.CfpContractDetails
 * @generated
 */
public abstract class CfpContractDetailsBaseImpl
    extends CfpContractDetailsModelImpl implements CfpContractDetails {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a cfp contract details model instance should use the {@link CfpContractDetails} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CfpContractDetailsLocalServiceUtil.addCfpContractDetails(this);
        } else {
            CfpContractDetailsLocalServiceUtil.updateCfpContractDetails(this);
        }
    }
}
