package com.stpl.app.model.impl;

import com.stpl.app.model.ItemGroup;
import com.stpl.app.service.ItemGroupLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the ItemGroup service. Represents a row in the &quot;ITEM_GROUP&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ItemGroupImpl}.
 * </p>
 *
 * @author
 * @see ItemGroupImpl
 * @see com.stpl.app.model.ItemGroup
 * @generated
 */
public abstract class ItemGroupBaseImpl extends ItemGroupModelImpl
    implements ItemGroup {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a item group model instance should use the {@link ItemGroup} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ItemGroupLocalServiceUtil.addItemGroup(this);
        } else {
            ItemGroupLocalServiceUtil.updateItemGroup(this);
        }
    }
}
