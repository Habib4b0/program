package com.stpl.app.service.persistence;

import com.stpl.app.model.BrandMaster;
import com.stpl.app.service.BrandMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class BrandMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public BrandMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(BrandMasterLocalServiceUtil.getService());
        setClass(BrandMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("brandMasterSid");
    }
}
