package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GcmGlobalDetailsLocalService}.
 *
 * @author
 * @see GcmGlobalDetailsLocalService
 * @generated
 */
public class GcmGlobalDetailsLocalServiceWrapper
    implements GcmGlobalDetailsLocalService,
        ServiceWrapper<GcmGlobalDetailsLocalService> {
    private GcmGlobalDetailsLocalService _gcmGlobalDetailsLocalService;

    public GcmGlobalDetailsLocalServiceWrapper(
        GcmGlobalDetailsLocalService gcmGlobalDetailsLocalService) {
        _gcmGlobalDetailsLocalService = gcmGlobalDetailsLocalService;
    }

    /**
    * Adds the gcm global details to the database. Also notifies the appropriate model listeners.
    *
    * @param gcmGlobalDetails the gcm global details
    * @return the gcm global details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GcmGlobalDetails addGcmGlobalDetails(
        com.stpl.app.model.GcmGlobalDetails gcmGlobalDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmGlobalDetailsLocalService.addGcmGlobalDetails(gcmGlobalDetails);
    }

    /**
    * Creates a new gcm global details with the primary key. Does not add the gcm global details to the database.
    *
    * @param gcmGlobalDetailsSid the primary key for the new gcm global details
    * @return the new gcm global details
    */
    @Override
    public com.stpl.app.model.GcmGlobalDetails createGcmGlobalDetails(
        int gcmGlobalDetailsSid) {
        return _gcmGlobalDetailsLocalService.createGcmGlobalDetails(gcmGlobalDetailsSid);
    }

    /**
    * Deletes the gcm global details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param gcmGlobalDetailsSid the primary key of the gcm global details
    * @return the gcm global details that was removed
    * @throws PortalException if a gcm global details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GcmGlobalDetails deleteGcmGlobalDetails(
        int gcmGlobalDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _gcmGlobalDetailsLocalService.deleteGcmGlobalDetails(gcmGlobalDetailsSid);
    }

    /**
    * Deletes the gcm global details from the database. Also notifies the appropriate model listeners.
    *
    * @param gcmGlobalDetails the gcm global details
    * @return the gcm global details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GcmGlobalDetails deleteGcmGlobalDetails(
        com.stpl.app.model.GcmGlobalDetails gcmGlobalDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmGlobalDetailsLocalService.deleteGcmGlobalDetails(gcmGlobalDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _gcmGlobalDetailsLocalService.dynamicQuery();
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
        return _gcmGlobalDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmGlobalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _gcmGlobalDetailsLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmGlobalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _gcmGlobalDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _gcmGlobalDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _gcmGlobalDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.GcmGlobalDetails fetchGcmGlobalDetails(
        int gcmGlobalDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmGlobalDetailsLocalService.fetchGcmGlobalDetails(gcmGlobalDetailsSid);
    }

    /**
    * Returns the gcm global details with the primary key.
    *
    * @param gcmGlobalDetailsSid the primary key of the gcm global details
    * @return the gcm global details
    * @throws PortalException if a gcm global details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GcmGlobalDetails getGcmGlobalDetails(
        int gcmGlobalDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _gcmGlobalDetailsLocalService.getGcmGlobalDetails(gcmGlobalDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _gcmGlobalDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the gcm global detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmGlobalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm global detailses
    * @param end the upper bound of the range of gcm global detailses (not inclusive)
    * @return the range of gcm global detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.GcmGlobalDetails> getGcmGlobalDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmGlobalDetailsLocalService.getGcmGlobalDetailses(start, end);
    }

    /**
    * Returns the number of gcm global detailses.
    *
    * @return the number of gcm global detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getGcmGlobalDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmGlobalDetailsLocalService.getGcmGlobalDetailsesCount();
    }

    /**
    * Updates the gcm global details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param gcmGlobalDetails the gcm global details
    * @return the gcm global details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GcmGlobalDetails updateGcmGlobalDetails(
        com.stpl.app.model.GcmGlobalDetails gcmGlobalDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmGlobalDetailsLocalService.updateGcmGlobalDetails(gcmGlobalDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _gcmGlobalDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _gcmGlobalDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _gcmGlobalDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public GcmGlobalDetailsLocalService getWrappedGcmGlobalDetailsLocalService() {
        return _gcmGlobalDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedGcmGlobalDetailsLocalService(
        GcmGlobalDetailsLocalService gcmGlobalDetailsLocalService) {
        _gcmGlobalDetailsLocalService = gcmGlobalDetailsLocalService;
    }

    @Override
    public GcmGlobalDetailsLocalService getWrappedService() {
        return _gcmGlobalDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        GcmGlobalDetailsLocalService gcmGlobalDetailsLocalService) {
        _gcmGlobalDetailsLocalService = gcmGlobalDetailsLocalService;
    }
}
