package com.stpl.app.model.impl;

import com.stpl.app.model.MSupplementalDiscMaster;
import com.stpl.app.service.MSupplementalDiscMasterLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the MSupplementalDiscMaster service. Represents a row in the &quot;M_SUPPLEMENTAL_DISC_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MSupplementalDiscMasterImpl}.
 * </p>
 *
 * @author
 * @see MSupplementalDiscMasterImpl
 * @see com.stpl.app.model.MSupplementalDiscMaster
 * @generated
 */
public abstract class MSupplementalDiscMasterBaseImpl
    extends MSupplementalDiscMasterModelImpl implements MSupplementalDiscMaster {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a m supplemental disc master model instance should use the {@link MSupplementalDiscMaster} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MSupplementalDiscMasterLocalServiceUtil.addMSupplementalDiscMaster(this);
        } else {
            MSupplementalDiscMasterLocalServiceUtil.updateMSupplementalDiscMaster(this);
        }
    }
}
