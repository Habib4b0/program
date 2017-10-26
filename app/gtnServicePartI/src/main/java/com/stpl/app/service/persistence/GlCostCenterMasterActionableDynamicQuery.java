package com.stpl.app.service.persistence;

import com.stpl.app.model.GlCostCenterMaster;
import com.stpl.app.service.GlCostCenterMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class GlCostCenterMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public GlCostCenterMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(GlCostCenterMasterLocalServiceUtil.getService());
        setClass(GlCostCenterMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("glCostCenterMasterSid");
    }
}
