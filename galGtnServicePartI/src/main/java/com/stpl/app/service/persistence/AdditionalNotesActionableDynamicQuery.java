package com.stpl.app.service.persistence;

import com.stpl.app.model.AdditionalNotes;
import com.stpl.app.service.AdditionalNotesLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AdditionalNotesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AdditionalNotesActionableDynamicQuery() throws SystemException {
        setBaseLocalService(AdditionalNotesLocalServiceUtil.getService());
        setClass(AdditionalNotes.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("additionalNotesId");
    }
}
