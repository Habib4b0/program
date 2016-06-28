package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.SlaCalendarMaster;
import com.stpl.app.parttwo.service.SlaCalendarMasterLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the SlaCalendarMaster service. Represents a row in the &quot;SLA_CALENDAR_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SlaCalendarMasterImpl}.
 * </p>
 *
 * @author
 * @see SlaCalendarMasterImpl
 * @see com.stpl.app.parttwo.model.SlaCalendarMaster
 * @generated
 */
public abstract class SlaCalendarMasterBaseImpl
    extends SlaCalendarMasterModelImpl implements SlaCalendarMaster {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a sla calendar master model instance should use the {@link SlaCalendarMaster} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            SlaCalendarMasterLocalServiceUtil.addSlaCalendarMaster(this);
        } else {
            SlaCalendarMasterLocalServiceUtil.updateSlaCalendarMaster(this);
        }
    }
}
