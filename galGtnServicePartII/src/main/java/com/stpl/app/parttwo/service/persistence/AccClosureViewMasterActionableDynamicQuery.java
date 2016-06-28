package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AccClosureViewMaster;
import com.stpl.app.parttwo.service.AccClosureViewMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AccClosureViewMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AccClosureViewMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(AccClosureViewMasterLocalServiceUtil.getService());
        setClass(AccClosureViewMaster.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("accClosureViewMasterSid");
    }
}
