package com.stpl.app.service.persistence;

import com.stpl.app.model.NaProjMaster;
import com.stpl.app.service.NaProjMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NaProjMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NaProjMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(NaProjMasterLocalServiceUtil.getService());
        setClass(NaProjMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("naProjMasterSid");
    }
}
