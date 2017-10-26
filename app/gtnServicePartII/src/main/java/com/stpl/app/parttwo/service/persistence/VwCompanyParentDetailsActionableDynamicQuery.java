package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCompanyParentDetails;
import com.stpl.app.parttwo.service.VwCompanyParentDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwCompanyParentDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwCompanyParentDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(VwCompanyParentDetailsLocalServiceUtil.getService());
        setClass(VwCompanyParentDetails.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("companyParentDetailsSid");
    }
}
