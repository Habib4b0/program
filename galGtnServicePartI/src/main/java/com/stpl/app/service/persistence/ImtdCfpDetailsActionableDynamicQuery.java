package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ImtdCfpDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImtdCfpDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ImtdCfpDetailsLocalServiceUtil.getService());
        setClass(ImtdCfpDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("imtdCfpDetailsSid");
    }
}
