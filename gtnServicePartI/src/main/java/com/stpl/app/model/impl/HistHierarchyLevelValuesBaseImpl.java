package com.stpl.app.model.impl;

import com.stpl.app.model.HistHierarchyLevelValues;
import com.stpl.app.service.HistHierarchyLevelValuesLocalServiceUtil;

import com.stpl.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the HistHierarchyLevelValues service. Represents a row in the &quot;HIST_HIERARCHY_LEVEL_VALUES&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link HistHierarchyLevelValuesImpl}.
 * </p>
 *
 * @author
 * @see HistHierarchyLevelValuesImpl
 * @see com.stpl.app.model.HistHierarchyLevelValues
 * @generated
 */
public abstract class HistHierarchyLevelValuesBaseImpl
    extends HistHierarchyLevelValuesModelImpl
    implements HistHierarchyLevelValues {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a hist hierarchy level values model instance should use the {@link HistHierarchyLevelValues} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HistHierarchyLevelValuesLocalServiceUtil.addHistHierarchyLevelValues(this);
        } else {
            HistHierarchyLevelValuesLocalServiceUtil.updateHistHierarchyLevelValues(this);
        }
    }
}
