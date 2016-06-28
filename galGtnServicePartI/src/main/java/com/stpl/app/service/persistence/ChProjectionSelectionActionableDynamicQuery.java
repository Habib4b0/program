package com.stpl.app.service.persistence;

import com.stpl.app.model.ChProjectionSelection;
import com.stpl.app.service.ChProjectionSelectionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ChProjectionSelectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ChProjectionSelectionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ChProjectionSelectionLocalServiceUtil.getService());
        setClass(ChProjectionSelection.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("chProjectionSelectionSid");
    }
}
