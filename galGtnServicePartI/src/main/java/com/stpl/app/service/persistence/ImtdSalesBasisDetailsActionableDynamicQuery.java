package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdSalesBasisDetails;
import com.stpl.app.service.ImtdSalesBasisDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ImtdSalesBasisDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImtdSalesBasisDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ImtdSalesBasisDetailsLocalServiceUtil.getService());
        setClass(ImtdSalesBasisDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("imtdSalesBasisDetailsSid");
    }
}
