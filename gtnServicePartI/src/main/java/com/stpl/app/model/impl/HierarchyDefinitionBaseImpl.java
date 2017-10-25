package com.stpl.app.model.impl;

import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the HierarchyDefinition service. Represents a row in the &quot;HIERARCHY_DEFINITION&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link HierarchyDefinitionImpl}.
 * </p>
 *
 * @author
 * @see HierarchyDefinitionImpl
 * @see com.stpl.app.model.HierarchyDefinition
 * @generated
 */
public abstract class HierarchyDefinitionBaseImpl
    extends HierarchyDefinitionModelImpl implements HierarchyDefinition {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a hierarchy definition model instance should use the {@link HierarchyDefinition} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HierarchyDefinitionLocalServiceUtil.addHierarchyDefinition(this);
        } else {
            HierarchyDefinitionLocalServiceUtil.updateHierarchyDefinition(this);
        }
    }
}
