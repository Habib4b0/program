package com.stpl.app.model.impl;

import com.stpl.app.model.MedicaidNewNdc;
import com.stpl.app.service.MedicaidNewNdcLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the MedicaidNewNdc service. Represents a row in the &quot;MEDICAID_NEW_NDC&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MedicaidNewNdcImpl}.
 * </p>
 *
 * @author
 * @see MedicaidNewNdcImpl
 * @see com.stpl.app.model.MedicaidNewNdc
 * @generated
 */
public abstract class MedicaidNewNdcBaseImpl extends MedicaidNewNdcModelImpl
    implements MedicaidNewNdc {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a medicaid new ndc model instance should use the {@link MedicaidNewNdc} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MedicaidNewNdcLocalServiceUtil.addMedicaidNewNdc(this);
        } else {
            MedicaidNewNdcLocalServiceUtil.updateMedicaidNewNdc(this);
        }
    }
}
