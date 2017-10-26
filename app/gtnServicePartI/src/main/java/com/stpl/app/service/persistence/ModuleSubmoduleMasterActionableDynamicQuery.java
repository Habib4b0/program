package com.stpl.app.service.persistence;

import com.stpl.app.model.ModuleSubmoduleMaster;
import com.stpl.app.service.ModuleSubmoduleMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ModuleSubmoduleMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ModuleSubmoduleMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ModuleSubmoduleMasterLocalServiceUtil.getService());
        setClass(ModuleSubmoduleMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("moduleSubmoduleSid");
    }
}
