package com.stpl.app.service.persistence;

import com.stpl.app.model.ModuleProperties;
import com.stpl.app.service.ModulePropertiesLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ModulePropertiesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ModulePropertiesActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ModulePropertiesLocalServiceUtil.getService());
        setClass(ModuleProperties.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("modulePropertySid");
    }
}
