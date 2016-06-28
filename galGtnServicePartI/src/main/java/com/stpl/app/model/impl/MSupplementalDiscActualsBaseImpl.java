package com.stpl.app.model.impl;

import com.stpl.app.model.MSupplementalDiscActuals;
import com.stpl.app.service.MSupplementalDiscActualsLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the MSupplementalDiscActuals service. Represents a row in the &quot;M_SUPPLEMENTAL_DISC_ACTUALS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MSupplementalDiscActualsImpl}.
 * </p>
 *
 * @author
 * @see MSupplementalDiscActualsImpl
 * @see com.stpl.app.model.MSupplementalDiscActuals
 * @generated
 */
public abstract class MSupplementalDiscActualsBaseImpl
    extends MSupplementalDiscActualsModelImpl
    implements MSupplementalDiscActuals {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a m supplemental disc actuals model instance should use the {@link MSupplementalDiscActuals} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MSupplementalDiscActualsLocalServiceUtil.addMSupplementalDiscActuals(this);
        } else {
            MSupplementalDiscActualsLocalServiceUtil.updateMSupplementalDiscActuals(this);
        }
    }
}
