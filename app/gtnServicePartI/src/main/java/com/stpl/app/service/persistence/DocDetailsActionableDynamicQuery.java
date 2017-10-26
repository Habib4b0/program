package com.stpl.app.service.persistence;

import com.stpl.app.model.DocDetails;
import com.stpl.app.service.DocDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class DocDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public DocDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(DocDetailsLocalServiceUtil.getService());
        setClass(DocDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("docDetailsId");
    }
}
