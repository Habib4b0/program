package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AhTempDetails;
import com.stpl.app.parttwo.service.AhTempDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AhTempDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AhTempDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(AhTempDetailsLocalServiceUtil.getService());
        setClass(AhTempDetails.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("componentMasterSid");
    }
}
