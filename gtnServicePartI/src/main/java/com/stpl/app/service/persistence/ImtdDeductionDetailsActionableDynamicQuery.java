package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdDeductionDetails;
import com.stpl.app.service.ImtdDeductionDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ImtdDeductionDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImtdDeductionDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ImtdDeductionDetailsLocalServiceUtil.getService());
        setClass(ImtdDeductionDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("imtdDeductionDetailsSid");
    }
}
