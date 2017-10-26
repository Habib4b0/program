package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffMaster;
import com.stpl.app.parttwo.service.CffMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CffMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CffMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CffMasterLocalServiceUtil.getService());
        setClass(CffMaster.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cffMasterSid");
    }
}
