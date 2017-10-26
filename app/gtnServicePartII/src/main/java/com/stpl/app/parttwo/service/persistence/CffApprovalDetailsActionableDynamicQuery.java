package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffApprovalDetails;
import com.stpl.app.parttwo.service.CffApprovalDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CffApprovalDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CffApprovalDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CffApprovalDetailsLocalServiceUtil.getService());
        setClass(CffApprovalDetails.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("cffApprovalDetailsSid");
    }
}
