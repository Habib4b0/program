package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StAccClosureDetails;
import com.stpl.app.parttwo.service.StAccClosureDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StAccClosureDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StAccClosureDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StAccClosureDetailsLocalServiceUtil.getService());
        setClass(StAccClosureDetails.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("accClosureMasterSid");
    }
}
