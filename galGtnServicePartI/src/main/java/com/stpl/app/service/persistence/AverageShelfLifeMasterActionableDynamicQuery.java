package com.stpl.app.service.persistence;

import com.stpl.app.model.AverageShelfLifeMaster;
import com.stpl.app.service.AverageShelfLifeMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AverageShelfLifeMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AverageShelfLifeMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(AverageShelfLifeMasterLocalServiceUtil.getService());
        setClass(AverageShelfLifeMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("averageShelfLifeMasterSid");
    }
}
