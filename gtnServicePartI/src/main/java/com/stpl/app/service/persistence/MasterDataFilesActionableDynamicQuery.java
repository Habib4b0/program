package com.stpl.app.service.persistence;

import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class MasterDataFilesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MasterDataFilesActionableDynamicQuery() throws SystemException {
        setBaseLocalService(MasterDataFilesLocalServiceUtil.getService());
        setClass(MasterDataFiles.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("masterDataFilesSid");
    }
}
