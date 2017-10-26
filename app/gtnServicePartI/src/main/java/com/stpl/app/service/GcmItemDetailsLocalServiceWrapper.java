package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GcmItemDetailsLocalService}.
 *
 * @author
 * @see GcmItemDetailsLocalService
 * @generated
 */
public class GcmItemDetailsLocalServiceWrapper
    implements GcmItemDetailsLocalService,
        ServiceWrapper<GcmItemDetailsLocalService> {
    private GcmItemDetailsLocalService _gcmItemDetailsLocalService;

    public GcmItemDetailsLocalServiceWrapper(
        GcmItemDetailsLocalService gcmItemDetailsLocalService) {
        _gcmItemDetailsLocalService = gcmItemDetailsLocalService;
    }

    /**
    * Adds the gcm item details to the database. Also notifies the appropriate model listeners.
    *
    * @param gcmItemDetails the gcm item details
    * @return the gcm item details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GcmItemDetails addGcmItemDetails(
        com.stpl.app.model.GcmItemDetails gcmItemDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmItemDetailsLocalService.addGcmItemDetails(gcmItemDetails);
    }

    /**
    * Creates a new gcm item details with the primary key. Does not add the gcm item details to the database.
    *
    * @param gcmItemDetailsSid the primary key for the new gcm item details
    * @return the new gcm item details
    */
    @Override
    public com.stpl.app.model.GcmItemDetails createGcmItemDetails(
        int gcmItemDetailsSid) {
        return _gcmItemDetailsLocalService.createGcmItemDetails(gcmItemDetailsSid);
    }

    /**
    * Deletes the gcm item details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param gcmItemDetailsSid the primary key of the gcm item details
    * @return the gcm item details that was removed
    * @throws PortalException if a gcm item details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GcmItemDetails deleteGcmItemDetails(
        int gcmItemDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _gcmItemDetailsLocalService.deleteGcmItemDetails(gcmItemDetailsSid);
    }

    /**
    * Deletes the gcm item details from the database. Also notifies the appropriate model listeners.
    *
    * @param gcmItemDetails the gcm item details
    * @return the gcm item details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GcmItemDetails deleteGcmItemDetails(
        com.stpl.app.model.GcmItemDetails gcmItemDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmItemDetailsLocalService.deleteGcmItemDetails(gcmItemDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _gcmItemDetailsLocalService.dynamicQuery();
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
        return _gcmItemDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _gcmItemDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _gcmItemDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _gcmItemDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _gcmItemDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.GcmItemDetails fetchGcmItemDetails(
        int gcmItemDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmItemDetailsLocalService.fetchGcmItemDetails(gcmItemDetailsSid);
    }

    /**
    * Returns the gcm item details with the primary key.
    *
    * @param gcmItemDetailsSid the primary key of the gcm item details
    * @return the gcm item details
    * @throws PortalException if a gcm item details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GcmItemDetails getGcmItemDetails(
        int gcmItemDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _gcmItemDetailsLocalService.getGcmItemDetails(gcmItemDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _gcmItemDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the gcm item detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm item detailses
    * @param end the upper bound of the range of gcm item detailses (not inclusive)
    * @return the range of gcm item detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.GcmItemDetails> getGcmItemDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmItemDetailsLocalService.getGcmItemDetailses(start, end);
    }

    /**
    * Returns the number of gcm item detailses.
    *
    * @return the number of gcm item detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getGcmItemDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmItemDetailsLocalService.getGcmItemDetailsesCount();
    }

    /**
    * Updates the gcm item details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param gcmItemDetails the gcm item details
    * @return the gcm item details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GcmItemDetails updateGcmItemDetails(
        com.stpl.app.model.GcmItemDetails gcmItemDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _gcmItemDetailsLocalService.updateGcmItemDetails(gcmItemDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _gcmItemDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _gcmItemDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _gcmItemDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public GcmItemDetailsLocalService getWrappedGcmItemDetailsLocalService() {
        return _gcmItemDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedGcmItemDetailsLocalService(
        GcmItemDetailsLocalService gcmItemDetailsLocalService) {
        _gcmItemDetailsLocalService = gcmItemDetailsLocalService;
    }

    @Override
    public GcmItemDetailsLocalService getWrappedService() {
        return _gcmItemDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        GcmItemDetailsLocalService gcmItemDetailsLocalService) {
        _gcmItemDetailsLocalService = gcmItemDetailsLocalService;
    }
}
