package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldGlCostCenter;
import com.stpl.app.service.IvldGlCostCenterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldGlCostCenterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldGlCostCenterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldGlCostCenterLocalServiceUtil.getService());
        setClass(IvldGlCostCenter.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldGlCostCenterSid");
    }
}
