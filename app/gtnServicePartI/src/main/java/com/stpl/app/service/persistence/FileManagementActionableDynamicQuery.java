package com.stpl.app.service.persistence;

import com.stpl.app.model.FileManagement;
import com.stpl.app.service.FileManagementLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class FileManagementActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public FileManagementActionableDynamicQuery() throws SystemException {
        setBaseLocalService(FileManagementLocalServiceUtil.getService());
        setClass(FileManagement.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("fileManagementSid");
    }
}
