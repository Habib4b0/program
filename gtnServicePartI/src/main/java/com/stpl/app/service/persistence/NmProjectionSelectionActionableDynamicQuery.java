package com.stpl.app.service.persistence;

import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NmProjectionSelectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NmProjectionSelectionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(NmProjectionSelectionLocalServiceUtil.getService());
        setClass(NmProjectionSelection.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("nmProjectionSelectionSid");
    }
}
