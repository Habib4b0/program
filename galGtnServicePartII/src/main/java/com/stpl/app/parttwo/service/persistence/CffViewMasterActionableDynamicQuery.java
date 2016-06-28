package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffViewMaster;
import com.stpl.app.parttwo.service.CffViewMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CffViewMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CffViewMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CffViewMasterLocalServiceUtil.getService());
        setClass(CffViewMaster.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cffViewMasterSid");
    }
}
