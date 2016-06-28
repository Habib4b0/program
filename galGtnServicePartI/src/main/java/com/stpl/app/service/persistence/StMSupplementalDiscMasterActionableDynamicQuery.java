package com.stpl.app.service.persistence;

import com.stpl.app.model.StMSupplementalDiscMaster;
import com.stpl.app.service.StMSupplementalDiscMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StMSupplementalDiscMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StMSupplementalDiscMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StMSupplementalDiscMasterLocalServiceUtil.getService());
        setClass(StMSupplementalDiscMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.userId");
    }
}
