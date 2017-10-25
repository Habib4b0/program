package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StDeductionCalendarDetailsLocalService}.
 *
 * @author
 * @see StDeductionCalendarDetailsLocalService
 * @generated
 */
public class StDeductionCalendarDetailsLocalServiceWrapper
    implements StDeductionCalendarDetailsLocalService,
        ServiceWrapper<StDeductionCalendarDetailsLocalService> {
    private StDeductionCalendarDetailsLocalService _stDeductionCalendarDetailsLocalService;

    public StDeductionCalendarDetailsLocalServiceWrapper(
        StDeductionCalendarDetailsLocalService stDeductionCalendarDetailsLocalService) {
        _stDeductionCalendarDetailsLocalService = stDeductionCalendarDetailsLocalService;
    }

    /**
    * Adds the st deduction calendar details to the database. Also notifies the appropriate model listeners.
    *
    * @param stDeductionCalendarDetails the st deduction calendar details
    * @return the st deduction calendar details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StDeductionCalendarDetails addStDeductionCalendarDetails(
        com.stpl.app.model.StDeductionCalendarDetails stDeductionCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stDeductionCalendarDetailsLocalService.addStDeductionCalendarDetails(stDeductionCalendarDetails);
    }

    /**
    * Creates a new st deduction calendar details with the primary key. Does not add the st deduction calendar details to the database.
    *
    * @param stDeductionCalendarDetailsPK the primary key for the new st deduction calendar details
    * @return the new st deduction calendar details
    */
    @Override
    public com.stpl.app.model.StDeductionCalendarDetails createStDeductionCalendarDetails(
        com.stpl.app.service.persistence.StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK) {
        return _stDeductionCalendarDetailsLocalService.createStDeductionCalendarDetails(stDeductionCalendarDetailsPK);
    }

    /**
    * Deletes the st deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
    * @return the st deduction calendar details that was removed
    * @throws PortalException if a st deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StDeductionCalendarDetails deleteStDeductionCalendarDetails(
        com.stpl.app.service.persistence.StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stDeductionCalendarDetailsLocalService.deleteStDeductionCalendarDetails(stDeductionCalendarDetailsPK);
    }

    /**
    * Deletes the st deduction calendar details from the database. Also notifies the appropriate model listeners.
    *
    * @param stDeductionCalendarDetails the st deduction calendar details
    * @return the st deduction calendar details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StDeductionCalendarDetails deleteStDeductionCalendarDetails(
        com.stpl.app.model.StDeductionCalendarDetails stDeductionCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stDeductionCalendarDetailsLocalService.deleteStDeductionCalendarDetails(stDeductionCalendarDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stDeductionCalendarDetailsLocalService.dynamicQuery();
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
        return _stDeductionCalendarDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stDeductionCalendarDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stDeductionCalendarDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
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
        return _stDeductionCalendarDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stDeductionCalendarDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StDeductionCalendarDetails fetchStDeductionCalendarDetails(
        com.stpl.app.service.persistence.StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stDeductionCalendarDetailsLocalService.fetchStDeductionCalendarDetails(stDeductionCalendarDetailsPK);
    }

    /**
    * Returns the st deduction calendar details with the primary key.
    *
    * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
    * @return the st deduction calendar details
    * @throws PortalException if a st deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StDeductionCalendarDetails getStDeductionCalendarDetails(
        com.stpl.app.service.persistence.StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stDeductionCalendarDetailsLocalService.getStDeductionCalendarDetails(stDeductionCalendarDetailsPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stDeductionCalendarDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st deduction calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st deduction calendar detailses
    * @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
    * @return the range of st deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StDeductionCalendarDetails> getStDeductionCalendarDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stDeductionCalendarDetailsLocalService.getStDeductionCalendarDetailses(start,
            end);
    }

    /**
    * Returns the number of st deduction calendar detailses.
    *
    * @return the number of st deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStDeductionCalendarDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stDeductionCalendarDetailsLocalService.getStDeductionCalendarDetailsesCount();
    }

    /**
    * Updates the st deduction calendar details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stDeductionCalendarDetails the st deduction calendar details
    * @return the st deduction calendar details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StDeductionCalendarDetails updateStDeductionCalendarDetails(
        com.stpl.app.model.StDeductionCalendarDetails stDeductionCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stDeductionCalendarDetailsLocalService.updateStDeductionCalendarDetails(stDeductionCalendarDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stDeductionCalendarDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stDeductionCalendarDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stDeductionCalendarDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StDeductionCalendarDetailsLocalService getWrappedStDeductionCalendarDetailsLocalService() {
        return _stDeductionCalendarDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStDeductionCalendarDetailsLocalService(
        StDeductionCalendarDetailsLocalService stDeductionCalendarDetailsLocalService) {
        _stDeductionCalendarDetailsLocalService = stDeductionCalendarDetailsLocalService;
    }

    @Override
    public StDeductionCalendarDetailsLocalService getWrappedService() {
        return _stDeductionCalendarDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        StDeductionCalendarDetailsLocalService stDeductionCalendarDetailsLocalService) {
        _stDeductionCalendarDetailsLocalService = stDeductionCalendarDetailsLocalService;
    }
}
