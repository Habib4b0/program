package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MasterDataFilesLocalService}.
 *
 * @author
 * @see MasterDataFilesLocalService
 * @generated
 */
public class MasterDataFilesLocalServiceWrapper
    implements MasterDataFilesLocalService,
        ServiceWrapper<MasterDataFilesLocalService> {
    private MasterDataFilesLocalService _masterDataFilesLocalService;

    public MasterDataFilesLocalServiceWrapper(
        MasterDataFilesLocalService masterDataFilesLocalService) {
        _masterDataFilesLocalService = masterDataFilesLocalService;
    }

    /**
    * Adds the master data files to the database. Also notifies the appropriate model listeners.
    *
    * @param masterDataFiles the master data files
    * @return the master data files that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MasterDataFiles addMasterDataFiles(
        com.stpl.app.model.MasterDataFiles masterDataFiles)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.addMasterDataFiles(masterDataFiles);
    }

    /**
    * Creates a new master data files with the primary key. Does not add the master data files to the database.
    *
    * @param masterDataFilesSid the primary key for the new master data files
    * @return the new master data files
    */
    @Override
    public com.stpl.app.model.MasterDataFiles createMasterDataFiles(
        int masterDataFilesSid) {
        return _masterDataFilesLocalService.createMasterDataFiles(masterDataFilesSid);
    }

    /**
    * Deletes the master data files with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param masterDataFilesSid the primary key of the master data files
    * @return the master data files that was removed
    * @throws PortalException if a master data files with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MasterDataFiles deleteMasterDataFiles(
        int masterDataFilesSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.deleteMasterDataFiles(masterDataFilesSid);
    }

    /**
    * Deletes the master data files from the database. Also notifies the appropriate model listeners.
    *
    * @param masterDataFiles the master data files
    * @return the master data files that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MasterDataFiles deleteMasterDataFiles(
        com.stpl.app.model.MasterDataFiles masterDataFiles)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.deleteMasterDataFiles(masterDataFiles);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _masterDataFilesLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.MasterDataFiles fetchMasterDataFiles(
        int masterDataFilesSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.fetchMasterDataFiles(masterDataFilesSid);
    }

    /**
    * Returns the master data files with the primary key.
    *
    * @param masterDataFilesSid the primary key of the master data files
    * @return the master data files
    * @throws PortalException if a master data files with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MasterDataFiles getMasterDataFiles(
        int masterDataFilesSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.getMasterDataFiles(masterDataFilesSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the master data fileses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of master data fileses
    * @param end the upper bound of the range of master data fileses (not inclusive)
    * @return the range of master data fileses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.MasterDataFiles> getMasterDataFileses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.getMasterDataFileses(start, end);
    }

    /**
    * Returns the number of master data fileses.
    *
    * @return the number of master data fileses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMasterDataFilesesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.getMasterDataFilesesCount();
    }

    /**
    * Updates the master data files in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param masterDataFiles the master data files
    * @return the master data files that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MasterDataFiles updateMasterDataFiles(
        com.stpl.app.model.MasterDataFiles masterDataFiles)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _masterDataFilesLocalService.updateMasterDataFiles(masterDataFiles);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _masterDataFilesLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _masterDataFilesLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _masterDataFilesLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MasterDataFilesLocalService getWrappedMasterDataFilesLocalService() {
        return _masterDataFilesLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMasterDataFilesLocalService(
        MasterDataFilesLocalService masterDataFilesLocalService) {
        _masterDataFilesLocalService = masterDataFilesLocalService;
    }

    @Override
    public MasterDataFilesLocalService getWrappedService() {
        return _masterDataFilesLocalService;
    }

    @Override
    public void setWrappedService(
        MasterDataFilesLocalService masterDataFilesLocalService) {
        _masterDataFilesLocalService = masterDataFilesLocalService;
    }
}
