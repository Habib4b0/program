package com.stpl.app.model.impl;

import com.stpl.app.model.MParityLookup;
import com.stpl.app.service.MParityLookupLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the MParityLookup service. Represents a row in the &quot;M_PARITY_LOOKUP&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MParityLookupImpl}.
 * </p>
 *
 * @author
 * @see MParityLookupImpl
 * @see com.stpl.app.model.MParityLookup
 * @generated
 */
public abstract class MParityLookupBaseImpl extends MParityLookupModelImpl
    implements MParityLookup {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a m parity lookup model instance should use the {@link MParityLookup} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MParityLookupLocalServiceUtil.addMParityLookup(this);
        } else {
            MParityLookupLocalServiceUtil.updateMParityLookup(this);
        }
    }
}
