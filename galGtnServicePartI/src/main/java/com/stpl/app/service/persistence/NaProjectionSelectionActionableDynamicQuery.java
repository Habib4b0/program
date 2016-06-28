package com.stpl.app.service.persistence;

import com.stpl.app.model.NaProjectionSelection;
import com.stpl.app.service.NaProjectionSelectionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NaProjectionSelectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NaProjectionSelectionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(NaProjectionSelectionLocalServiceUtil.getService());
        setClass(NaProjectionSelection.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("naProjectionSelectionSid");
    }
}
