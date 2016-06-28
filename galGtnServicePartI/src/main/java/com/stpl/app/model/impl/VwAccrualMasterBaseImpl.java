package com.stpl.app.model.impl;

import com.stpl.app.model.VwAccrualMaster;
import com.stpl.app.service.VwAccrualMasterLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the VwAccrualMaster service. Represents a row in the &quot;vw_ACCRUAL_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link VwAccrualMasterImpl}.
 * </p>
 *
 * @author
 * @see VwAccrualMasterImpl
 * @see com.stpl.app.model.VwAccrualMaster
 * @generated
 */
public abstract class VwAccrualMasterBaseImpl extends VwAccrualMasterModelImpl
    implements VwAccrualMaster {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a vw accrual master model instance should use the {@link VwAccrualMaster} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwAccrualMasterLocalServiceUtil.addVwAccrualMaster(this);
        } else {
            VwAccrualMasterLocalServiceUtil.updateVwAccrualMaster(this);
        }
    }
}
