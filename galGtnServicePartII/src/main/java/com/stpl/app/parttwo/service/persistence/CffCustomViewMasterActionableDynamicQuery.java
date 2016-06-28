package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffCustomViewMaster;
import com.stpl.app.parttwo.service.CffCustomViewMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CffCustomViewMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CffCustomViewMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(CffCustomViewMasterLocalServiceUtil.getService());
        setClass(CffCustomViewMaster.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cffCustomViewMasterSid");
    }
}
