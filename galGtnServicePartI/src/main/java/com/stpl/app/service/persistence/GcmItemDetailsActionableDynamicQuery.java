package com.stpl.app.service.persistence;

import com.stpl.app.model.GcmItemDetails;
import com.stpl.app.service.GcmItemDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class GcmItemDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public GcmItemDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(GcmItemDetailsLocalServiceUtil.getService());
        setClass(GcmItemDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("gcmItemDetailsSid");
    }
}
