package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldAverageShelfLife;
import com.stpl.app.service.IvldAverageShelfLifeLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldAverageShelfLifeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldAverageShelfLifeActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(IvldAverageShelfLifeLocalServiceUtil.getService());
        setClass(IvldAverageShelfLife.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldAverageShelfLifeSid");
    }
}
