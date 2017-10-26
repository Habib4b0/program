package com.stpl.app.service.persistence;

import com.stpl.app.model.MSupplementalDiscMaster;
import com.stpl.app.service.MSupplementalDiscMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class MSupplementalDiscMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MSupplementalDiscMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MSupplementalDiscMasterLocalServiceUtil.getService());
        setClass(MSupplementalDiscMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionDetailsSid");
    }
}
