package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdLevelValues;
import com.stpl.app.service.ImtdLevelValuesLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ImtdLevelValuesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImtdLevelValuesActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ImtdLevelValuesLocalServiceUtil.getService());
        setClass(ImtdLevelValues.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("imtdLevelValuesSid");
    }
}
