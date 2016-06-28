package com.stpl.app.model.impl;

import com.stpl.app.model.MasterDataAttribute;
import com.stpl.app.service.MasterDataAttributeLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the MasterDataAttribute service. Represents a row in the &quot;MASTER_DATA_ATTRIBUTE&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MasterDataAttributeImpl}.
 * </p>
 *
 * @author
 * @see MasterDataAttributeImpl
 * @see com.stpl.app.model.MasterDataAttribute
 * @generated
 */
public abstract class MasterDataAttributeBaseImpl
    extends MasterDataAttributeModelImpl implements MasterDataAttribute {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a master data attribute model instance should use the {@link MasterDataAttribute} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MasterDataAttributeLocalServiceUtil.addMasterDataAttribute(this);
        } else {
            MasterDataAttributeLocalServiceUtil.updateMasterDataAttribute(this);
        }
    }
}
