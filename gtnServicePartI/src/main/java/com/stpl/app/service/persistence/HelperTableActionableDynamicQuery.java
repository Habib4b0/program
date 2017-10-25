package com.stpl.app.service.persistence;

import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class HelperTableActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public HelperTableActionableDynamicQuery() throws SystemException {
        setBaseLocalService(HelperTableLocalServiceUtil.getService());
        setClass(HelperTable.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("helperTableSid");
    }
}
