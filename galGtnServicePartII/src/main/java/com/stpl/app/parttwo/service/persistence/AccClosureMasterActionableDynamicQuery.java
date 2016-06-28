package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AccClosureMaster;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AccClosureMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AccClosureMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(AccClosureMasterLocalServiceUtil.getService());
        setClass(AccClosureMaster.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("accClosureMasterSid");
    }
}
