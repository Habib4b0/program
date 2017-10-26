package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffCustomViewDetails;
import com.stpl.app.parttwo.service.CffCustomViewDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CffCustomViewDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CffCustomViewDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(CffCustomViewDetailsLocalServiceUtil.getService());
        setClass(CffCustomViewDetails.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cffCustomViewDetailsSid");
    }
}
