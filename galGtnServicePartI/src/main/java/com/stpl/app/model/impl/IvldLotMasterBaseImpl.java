package com.stpl.app.model.impl;

import com.stpl.app.model.IvldLotMaster;
import com.stpl.app.service.IvldLotMasterLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the IvldLotMaster service. Represents a row in the &quot;IVLD_LOT_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IvldLotMasterImpl}.
 * </p>
 *
 * @author
 * @see IvldLotMasterImpl
 * @see com.stpl.app.model.IvldLotMaster
 * @generated
 */
public abstract class IvldLotMasterBaseImpl extends IvldLotMasterModelImpl
    implements IvldLotMaster {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a ivld lot master model instance should use the {@link IvldLotMaster} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldLotMasterLocalServiceUtil.addIvldLotMaster(this);
        } else {
            IvldLotMasterLocalServiceUtil.updateIvldLotMaster(this);
        }
    }
}
