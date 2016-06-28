package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImtdLevelValuesLocalService}.
 *
 * @author
 * @see ImtdLevelValuesLocalService
 * @generated
 */
public class ImtdLevelValuesLocalServiceWrapper
    implements ImtdLevelValuesLocalService,
        ServiceWrapper<ImtdLevelValuesLocalService> {
    private ImtdLevelValuesLocalService _imtdLevelValuesLocalService;

    public ImtdLevelValuesLocalServiceWrapper(
        ImtdLevelValuesLocalService imtdLevelValuesLocalService) {
        _imtdLevelValuesLocalService = imtdLevelValuesLocalService;
    }

    /**
    * Adds the imtd level values to the database. Also notifies the appropriate model listeners.
    *
    * @param imtdLevelValues the imtd level values
    * @return the imtd level values that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdLevelValues addImtdLevelValues(
        com.stpl.app.model.ImtdLevelValues imtdLevelValues)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdLevelValuesLocalService.addImtdLevelValues(imtdLevelValues);
    }

    /**
    * Creates a new imtd level values with the primary key. Does not add the imtd level values to the database.
    *
    * @param imtdLevelValuesSid the primary key for the new imtd level values
    * @return the new imtd level values
    */
    @Override
    public com.stpl.app.model.ImtdLevelValues createImtdLevelValues(
        int imtdLevelValuesSid) {
        return _imtdLevelValuesLocalService.createImtdLevelValues(imtdLevelValuesSid);
    }

    /**
    * Deletes the imtd level values with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdLevelValuesSid the primary key of the imtd level values
    * @return the imtd level values that was removed
    * @throws PortalException if a imtd level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdLevelValues deleteImtdLevelValues(
        int imtdLevelValuesSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdLevelValuesLocalService.deleteImtdLevelValues(imtdLevelValuesSid);
    }

    /**
    * Deletes the imtd level values from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdLevelValues the imtd level values
    * @return the imtd level values that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdLevelValues deleteImtdLevelValues(
        com.stpl.app.model.ImtdLevelValues imtdLevelValues)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdLevelValuesLocalService.deleteImtdLevelValues(imtdLevelValues);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _imtdLevelValuesLocalService.dynamicQuery();
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
        return _imtdLevelValuesLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdLevelValuesLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdLevelValuesLocalService.dynamicQuery(dynamicQuery, start,
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
        return _imtdLevelValuesLocalService.dynamicQueryCount(dynamicQuery);
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
        return _imtdLevelValuesLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ImtdLevelValues fetchImtdLevelValues(
        int imtdLevelValuesSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdLevelValuesLocalService.fetchImtdLevelValues(imtdLevelValuesSid);
    }

    /**
    * Returns the imtd level values with the primary key.
    *
    * @param imtdLevelValuesSid the primary key of the imtd level values
    * @return the imtd level values
    * @throws PortalException if a imtd level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdLevelValues getImtdLevelValues(
        int imtdLevelValuesSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdLevelValuesLocalService.getImtdLevelValues(imtdLevelValuesSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdLevelValuesLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the imtd level valueses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd level valueses
    * @param end the upper bound of the range of imtd level valueses (not inclusive)
    * @return the range of imtd level valueses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ImtdLevelValues> getImtdLevelValueses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdLevelValuesLocalService.getImtdLevelValueses(start, end);
    }

    /**
    * Returns the number of imtd level valueses.
    *
    * @return the number of imtd level valueses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getImtdLevelValuesesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdLevelValuesLocalService.getImtdLevelValuesesCount();
    }

    /**
    * Updates the imtd level values in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param imtdLevelValues the imtd level values
    * @return the imtd level values that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdLevelValues updateImtdLevelValues(
        com.stpl.app.model.ImtdLevelValues imtdLevelValues)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdLevelValuesLocalService.updateImtdLevelValues(imtdLevelValues);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _imtdLevelValuesLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _imtdLevelValuesLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _imtdLevelValuesLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImtdLevelValuesLocalService getWrappedImtdLevelValuesLocalService() {
        return _imtdLevelValuesLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImtdLevelValuesLocalService(
        ImtdLevelValuesLocalService imtdLevelValuesLocalService) {
        _imtdLevelValuesLocalService = imtdLevelValuesLocalService;
    }

    @Override
    public ImtdLevelValuesLocalService getWrappedService() {
        return _imtdLevelValuesLocalService;
    }

    @Override
    public void setWrappedService(
        ImtdLevelValuesLocalService imtdLevelValuesLocalService) {
        _imtdLevelValuesLocalService = imtdLevelValuesLocalService;
    }
}
