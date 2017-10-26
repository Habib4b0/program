package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCompanyMaster;
import com.stpl.app.parttwo.service.IvldCompanyMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldCompanyMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldCompanyMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldCompanyMasterLocalServiceUtil.getService());
        setClass(IvldCompanyMaster.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldCompanyMasterSid");
    }
}
