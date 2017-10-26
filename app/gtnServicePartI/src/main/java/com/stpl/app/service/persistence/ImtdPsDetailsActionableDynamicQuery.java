package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdPsDetails;
import com.stpl.app.service.ImtdPsDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ImtdPsDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImtdPsDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ImtdPsDetailsLocalServiceUtil.getService());
        setClass(ImtdPsDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("imtdPsDetailsSid");
    }
}
