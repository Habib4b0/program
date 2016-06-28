package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FileManagementLocalService}.
 *
 * @author
 * @see FileManagementLocalService
 * @generated
 */
public class FileManagementLocalServiceWrapper
    implements FileManagementLocalService,
        ServiceWrapper<FileManagementLocalService> {
    private FileManagementLocalService _fileManagementLocalService;

    public FileManagementLocalServiceWrapper(
        FileManagementLocalService fileManagementLocalService) {
        _fileManagementLocalService = fileManagementLocalService;
    }

    /**
    * Adds the file management to the database. Also notifies the appropriate model listeners.
    *
    * @param fileManagement the file management
    * @return the file management that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FileManagement addFileManagement(
        com.stpl.app.model.FileManagement fileManagement)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fileManagementLocalService.addFileManagement(fileManagement);
    }

    /**
    * Creates a new file management with the primary key. Does not add the file management to the database.
    *
    * @param fileManagementSid the primary key for the new file management
    * @return the new file management
    */
    @Override
    public com.stpl.app.model.FileManagement createFileManagement(
        int fileManagementSid) {
        return _fileManagementLocalService.createFileManagement(fileManagementSid);
    }

    /**
    * Deletes the file management with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param fileManagementSid the primary key of the file management
    * @return the file management that was removed
    * @throws PortalException if a file management with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FileManagement deleteFileManagement(
        int fileManagementSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _fileManagementLocalService.deleteFileManagement(fileManagementSid);
    }

    /**
    * Deletes the file management from the database. Also notifies the appropriate model listeners.
    *
    * @param fileManagement the file management
    * @return the file management that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FileManagement deleteFileManagement(
        com.stpl.app.model.FileManagement fileManagement)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fileManagementLocalService.deleteFileManagement(fileManagement);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _fileManagementLocalService.dynamicQuery();
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
        return _fileManagementLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _fileManagementLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _fileManagementLocalService.dynamicQuery(dynamicQuery, start,
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
        return _fileManagementLocalService.dynamicQueryCount(dynamicQuery);
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
        return _fileManagementLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.FileManagement fetchFileManagement(
        int fileManagementSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fileManagementLocalService.fetchFileManagement(fileManagementSid);
    }

    /**
    * Returns the file management with the primary key.
    *
    * @param fileManagementSid the primary key of the file management
    * @return the file management
    * @throws PortalException if a file management with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FileManagement getFileManagement(
        int fileManagementSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _fileManagementLocalService.getFileManagement(fileManagementSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _fileManagementLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the file managements.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of file managements
    * @param end the upper bound of the range of file managements (not inclusive)
    * @return the range of file managements
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.FileManagement> getFileManagements(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fileManagementLocalService.getFileManagements(start, end);
    }

    /**
    * Returns the number of file managements.
    *
    * @return the number of file managements
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getFileManagementsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fileManagementLocalService.getFileManagementsCount();
    }

    /**
    * Updates the file management in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param fileManagement the file management
    * @return the file management that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FileManagement updateFileManagement(
        com.stpl.app.model.FileManagement fileManagement)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fileManagementLocalService.updateFileManagement(fileManagement);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _fileManagementLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _fileManagementLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _fileManagementLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public FileManagementLocalService getWrappedFileManagementLocalService() {
        return _fileManagementLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedFileManagementLocalService(
        FileManagementLocalService fileManagementLocalService) {
        _fileManagementLocalService = fileManagementLocalService;
    }

    @Override
    public FileManagementLocalService getWrappedService() {
        return _fileManagementLocalService;
    }

    @Override
    public void setWrappedService(
        FileManagementLocalService fileManagementLocalService) {
        _fileManagementLocalService = fileManagementLocalService;
    }
}
