package com.stpl.app.model.impl;

import com.stpl.app.model.IvldItemHierarchyDefinition;
import com.stpl.app.service.IvldItemHierarchyDefinitionLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the IvldItemHierarchyDefinition service. Represents a row in the &quot;IVLD_ITEM_HIERARCHY_DEFINITION&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IvldItemHierarchyDefinitionImpl}.
 * </p>
 *
 * @author
 * @see IvldItemHierarchyDefinitionImpl
 * @see com.stpl.app.model.IvldItemHierarchyDefinition
 * @generated
 */
public abstract class IvldItemHierarchyDefinitionBaseImpl
    extends IvldItemHierarchyDefinitionModelImpl
    implements IvldItemHierarchyDefinition {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a ivld item hierarchy definition model instance should use the {@link IvldItemHierarchyDefinition} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldItemHierarchyDefinitionLocalServiceUtil.addIvldItemHierarchyDefinition(this);
        } else {
            IvldItemHierarchyDefinitionLocalServiceUtil.updateIvldItemHierarchyDefinition(this);
        }
    }
}
