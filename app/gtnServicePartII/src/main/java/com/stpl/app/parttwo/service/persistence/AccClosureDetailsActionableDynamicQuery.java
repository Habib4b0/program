package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AccClosureDetails;
import com.stpl.app.parttwo.service.AccClosureDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AccClosureDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AccClosureDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(AccClosureDetailsLocalServiceUtil.getService());
        setClass(AccClosureDetails.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("accClosureDetailsSid");
    }
}
