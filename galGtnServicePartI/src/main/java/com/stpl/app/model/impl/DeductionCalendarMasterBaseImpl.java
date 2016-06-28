package com.stpl.app.model.impl;

import com.stpl.app.model.DeductionCalendarMaster;
import com.stpl.app.service.DeductionCalendarMasterLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the DeductionCalendarMaster service. Represents a row in the &quot;DEDUCTION_CALENDAR_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DeductionCalendarMasterImpl}.
 * </p>
 *
 * @author
 * @see DeductionCalendarMasterImpl
 * @see com.stpl.app.model.DeductionCalendarMaster
 * @generated
 */
public abstract class DeductionCalendarMasterBaseImpl
    extends DeductionCalendarMasterModelImpl implements DeductionCalendarMaster {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a deduction calendar master model instance should use the {@link DeductionCalendarMaster} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DeductionCalendarMasterLocalServiceUtil.addDeductionCalendarMaster(this);
        } else {
            DeductionCalendarMasterLocalServiceUtil.updateDeductionCalendarMaster(this);
        }
    }
}
