package com.stpl.app.model.impl;

import com.stpl.app.model.ItemMaster;
import com.stpl.app.service.ItemMasterLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the ItemMaster service. Represents a row in the &quot;ITEM_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ItemMasterImpl}.
 * </p>
 *
 * @author
 * @see ItemMasterImpl
 * @see com.stpl.app.model.ItemMaster
 * @generated
 */
public abstract class ItemMasterBaseImpl extends ItemMasterModelImpl
    implements ItemMaster {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a item master model instance should use the {@link ItemMaster} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ItemMasterLocalServiceUtil.addItemMaster(this);
        } else {
            ItemMasterLocalServiceUtil.updateItemMaster(this);
        }
    }
}
