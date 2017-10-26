package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdIfpDetails;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ImtdIfpDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImtdIfpDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ImtdIfpDetailsLocalServiceUtil.getService());
        setClass(ImtdIfpDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("imtdIfpDetailsSid");
    }
}
