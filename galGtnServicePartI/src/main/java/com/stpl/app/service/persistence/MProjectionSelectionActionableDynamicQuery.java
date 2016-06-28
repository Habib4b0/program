package com.stpl.app.service.persistence;

import com.stpl.app.model.MProjectionSelection;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class MProjectionSelectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MProjectionSelectionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MProjectionSelectionLocalServiceUtil.getService());
        setClass(MProjectionSelection.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("mProjectionSelectionSid");
    }
}
