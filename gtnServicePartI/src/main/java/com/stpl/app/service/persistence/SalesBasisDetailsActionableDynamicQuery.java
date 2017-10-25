package com.stpl.app.service.persistence;

import com.stpl.app.model.SalesBasisDetails;
import com.stpl.app.service.SalesBasisDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class SalesBasisDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public SalesBasisDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(SalesBasisDetailsLocalServiceUtil.getService());
        setClass(SalesBasisDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("salesBasisDetailsSid");
    }
}
