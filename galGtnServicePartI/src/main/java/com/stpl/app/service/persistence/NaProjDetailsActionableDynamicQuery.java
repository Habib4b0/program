package com.stpl.app.service.persistence;

import com.stpl.app.model.NaProjDetails;
import com.stpl.app.service.NaProjDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NaProjDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NaProjDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(NaProjDetailsLocalServiceUtil.getService());
        setClass(NaProjDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("naProjDetailsSid");
    }
}
