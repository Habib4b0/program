package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffAdditionalInfo;
import com.stpl.app.parttwo.service.CffAdditionalInfoLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CffAdditionalInfoActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CffAdditionalInfoActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CffAdditionalInfoLocalServiceUtil.getService());
        setClass(CffAdditionalInfo.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cffAdditionalInfoSid");
    }
}
