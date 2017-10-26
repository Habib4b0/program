package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.service.CffDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CffDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CffDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CffDetailsLocalServiceUtil.getService());
        setClass(CffDetails.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cffDetailsSid");
    }
}
