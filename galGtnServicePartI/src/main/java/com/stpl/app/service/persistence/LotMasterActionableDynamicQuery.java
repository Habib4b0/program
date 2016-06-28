package com.stpl.app.service.persistence;

import com.stpl.app.model.LotMaster;
import com.stpl.app.service.LotMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class LotMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public LotMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(LotMasterLocalServiceUtil.getService());
        setClass(LotMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("lotMasterSid");
    }
}
