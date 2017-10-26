package com.stpl.app.service.persistence;

import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.service.CustomViewMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CustomViewMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CustomViewMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CustomViewMasterLocalServiceUtil.getService());
        setClass(CustomViewMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("customViewMasterSid");
    }
}
