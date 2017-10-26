package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdRsDetailsFr;
import com.stpl.app.service.ImtdRsDetailsFrLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ImtdRsDetailsFrActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImtdRsDetailsFrActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ImtdRsDetailsFrLocalServiceUtil.getService());
        setClass(ImtdRsDetailsFr.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("imtdRsDetailsFrSid");
    }
}
