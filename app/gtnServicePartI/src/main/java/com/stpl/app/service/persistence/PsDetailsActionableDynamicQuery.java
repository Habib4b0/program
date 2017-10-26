package com.stpl.app.service.persistence;

import com.stpl.app.model.PsDetails;
import com.stpl.app.service.PsDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class PsDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PsDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PsDetailsLocalServiceUtil.getService());
        setClass(PsDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("psDetailsSid");
    }
}
