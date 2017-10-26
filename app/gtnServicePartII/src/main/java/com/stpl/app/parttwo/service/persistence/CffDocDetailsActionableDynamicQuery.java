package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffDocDetails;
import com.stpl.app.parttwo.service.CffDocDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CffDocDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CffDocDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CffDocDetailsLocalServiceUtil.getService());
        setClass(CffDocDetails.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cffDocDetailsSid");
    }
}
