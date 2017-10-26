package com.stpl.app.service.persistence;

import com.stpl.app.model.CpiIndexMaster;
import com.stpl.app.service.CpiIndexMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CpiIndexMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CpiIndexMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CpiIndexMasterLocalServiceUtil.getService());
        setClass(CpiIndexMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cpiIndexMasterSid");
    }
}
