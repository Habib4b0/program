package com.stpl.app.service.persistence;

import com.stpl.app.model.FederalNewNdc;
import com.stpl.app.service.FederalNewNdcLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class FederalNewNdcActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public FederalNewNdcActionableDynamicQuery() throws SystemException {
        setBaseLocalService(FederalNewNdcLocalServiceUtil.getService());
        setClass(FederalNewNdc.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemMasterSid");
    }
}
