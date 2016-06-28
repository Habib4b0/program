package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AcBaseRateBaselineDetails;
import com.stpl.app.parttwo.service.AcBaseRateBaselineDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AcBaseRateBaselineDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AcBaseRateBaselineDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(AcBaseRateBaselineDetailsLocalServiceUtil.getService());
        setClass(AcBaseRateBaselineDetails.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("acBrMethodologyDetailsSid");
    }
}
