package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AcFdAdjustmentSelection;
import com.stpl.app.parttwo.service.AcFdAdjustmentSelectionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AcFdAdjustmentSelectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AcFdAdjustmentSelectionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(AcFdAdjustmentSelectionLocalServiceUtil.getService());
        setClass(AcFdAdjustmentSelection.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("accClosureMasterSid");
    }
}
