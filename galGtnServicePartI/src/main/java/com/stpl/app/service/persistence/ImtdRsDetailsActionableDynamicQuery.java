package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdRsDetails;
import com.stpl.app.service.ImtdRsDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ImtdRsDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImtdRsDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ImtdRsDetailsLocalServiceUtil.getService());
        setClass(ImtdRsDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("imtdRsDetailsSid");
    }
}
