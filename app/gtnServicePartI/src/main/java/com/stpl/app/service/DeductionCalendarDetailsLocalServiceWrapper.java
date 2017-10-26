package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DeductionCalendarDetailsLocalService}.
 *
 * @author
 * @see DeductionCalendarDetailsLocalService
 * @generated
 */
public class DeductionCalendarDetailsLocalServiceWrapper
    implements DeductionCalendarDetailsLocalService,
        ServiceWrapper<DeductionCalendarDetailsLocalService> {
    private DeductionCalendarDetailsLocalService _deductionCalendarDetailsLocalService;

    public DeductionCalendarDetailsLocalServiceWrapper(
        DeductionCalendarDetailsLocalService deductionCalendarDetailsLocalService) {
        _deductionCalendarDetailsLocalService = deductionCalendarDetailsLocalService;
    }

    /**
    * Adds the deduction calendar details to the database. Also notifies the appropriate model listeners.
    *
    * @param deductionCalendarDetails the deduction calendar details
    * @return the deduction calendar details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionCalendarDetails addDeductionCalendarDetails(
        com.stpl.app.model.DeductionCalendarDetails deductionCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionCalendarDetailsLocalService.addDeductionCalendarDetails(deductionCalendarDetails);
    }

    /**
    * Creates a new deduction calendar details with the primary key. Does not add the deduction calendar details to the database.
    *
    * @param deductionCalendarDetailsPK the primary key for the new deduction calendar details
    * @return the new deduction calendar details
    */
    @Override
    public com.stpl.app.model.DeductionCalendarDetails createDeductionCalendarDetails(
        com.stpl.app.service.persistence.DeductionCalendarDetailsPK deductionCalendarDetailsPK) {
        return _deductionCalendarDetailsLocalService.createDeductionCalendarDetails(deductionCalendarDetailsPK);
    }

    /**
    * Deletes the deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionCalendarDetailsPK the primary key of the deduction calendar details
    * @return the deduction calendar details that was removed
    * @throws PortalException if a deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionCalendarDetails deleteDeductionCalendarDetails(
        com.stpl.app.service.persistence.DeductionCalendarDetailsPK deductionCalendarDetailsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _deductionCalendarDetailsLocalService.deleteDeductionCalendarDetails(deductionCalendarDetailsPK);
    }

    /**
    * Deletes the deduction calendar details from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionCalendarDetails the deduction calendar details
    * @return the deduction calendar details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionCalendarDetails deleteDeductionCalendarDetails(
        com.stpl.app.model.DeductionCalendarDetails deductionCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionCalendarDetailsLocalService.deleteDeductionCalendarDetails(deductionCalendarDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _deductionCalendarDetailsLocalService.dynamicQuery();
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
        return _deductionCalendarDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _deductionCalendarDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _deductionCalendarDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _deductionCalendarDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _deductionCalendarDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.DeductionCalendarDetails fetchDeductionCalendarDetails(
        com.stpl.app.service.persistence.DeductionCalendarDetailsPK deductionCalendarDetailsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionCalendarDetailsLocalService.fetchDeductionCalendarDetails(deductionCalendarDetailsPK);
    }

    /**
    * Returns the deduction calendar details with the primary key.
    *
    * @param deductionCalendarDetailsPK the primary key of the deduction calendar details
    * @return the deduction calendar details
    * @throws PortalException if a deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionCalendarDetails getDeductionCalendarDetails(
        com.stpl.app.service.persistence.DeductionCalendarDetailsPK deductionCalendarDetailsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _deductionCalendarDetailsLocalService.getDeductionCalendarDetails(deductionCalendarDetailsPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _deductionCalendarDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the deduction calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction calendar detailses
    * @param end the upper bound of the range of deduction calendar detailses (not inclusive)
    * @return the range of deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.DeductionCalendarDetails> getDeductionCalendarDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionCalendarDetailsLocalService.getDeductionCalendarDetailses(start,
            end);
    }

    /**
    * Returns the number of deduction calendar detailses.
    *
    * @return the number of deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getDeductionCalendarDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionCalendarDetailsLocalService.getDeductionCalendarDetailsesCount();
    }

    /**
    * Updates the deduction calendar details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param deductionCalendarDetails the deduction calendar details
    * @return the deduction calendar details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionCalendarDetails updateDeductionCalendarDetails(
        com.stpl.app.model.DeductionCalendarDetails deductionCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionCalendarDetailsLocalService.updateDeductionCalendarDetails(deductionCalendarDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _deductionCalendarDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _deductionCalendarDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _deductionCalendarDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DeductionCalendarDetailsLocalService getWrappedDeductionCalendarDetailsLocalService() {
        return _deductionCalendarDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDeductionCalendarDetailsLocalService(
        DeductionCalendarDetailsLocalService deductionCalendarDetailsLocalService) {
        _deductionCalendarDetailsLocalService = deductionCalendarDetailsLocalService;
    }

    @Override
    public DeductionCalendarDetailsLocalService getWrappedService() {
        return _deductionCalendarDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        DeductionCalendarDetailsLocalService deductionCalendarDetailsLocalService) {
        _deductionCalendarDetailsLocalService = deductionCalendarDetailsLocalService;
    }
}
