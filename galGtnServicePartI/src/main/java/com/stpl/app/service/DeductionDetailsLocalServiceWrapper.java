package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DeductionDetailsLocalService}.
 *
 * @author
 * @see DeductionDetailsLocalService
 * @generated
 */
public class DeductionDetailsLocalServiceWrapper
    implements DeductionDetailsLocalService,
        ServiceWrapper<DeductionDetailsLocalService> {
    private DeductionDetailsLocalService _deductionDetailsLocalService;

    public DeductionDetailsLocalServiceWrapper(
        DeductionDetailsLocalService deductionDetailsLocalService) {
        _deductionDetailsLocalService = deductionDetailsLocalService;
    }

    /**
    * Adds the deduction details to the database. Also notifies the appropriate model listeners.
    *
    * @param deductionDetails the deduction details
    * @return the deduction details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionDetails addDeductionDetails(
        com.stpl.app.model.DeductionDetails deductionDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionDetailsLocalService.addDeductionDetails(deductionDetails);
    }

    /**
    * Creates a new deduction details with the primary key. Does not add the deduction details to the database.
    *
    * @param deductionDetailsSid the primary key for the new deduction details
    * @return the new deduction details
    */
    @Override
    public com.stpl.app.model.DeductionDetails createDeductionDetails(
        int deductionDetailsSid) {
        return _deductionDetailsLocalService.createDeductionDetails(deductionDetailsSid);
    }

    /**
    * Deletes the deduction details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionDetailsSid the primary key of the deduction details
    * @return the deduction details that was removed
    * @throws PortalException if a deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionDetails deleteDeductionDetails(
        int deductionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _deductionDetailsLocalService.deleteDeductionDetails(deductionDetailsSid);
    }

    /**
    * Deletes the deduction details from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionDetails the deduction details
    * @return the deduction details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionDetails deleteDeductionDetails(
        com.stpl.app.model.DeductionDetails deductionDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionDetailsLocalService.deleteDeductionDetails(deductionDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _deductionDetailsLocalService.dynamicQuery();
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
        return _deductionDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _deductionDetailsLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _deductionDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _deductionDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _deductionDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.DeductionDetails fetchDeductionDetails(
        int deductionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionDetailsLocalService.fetchDeductionDetails(deductionDetailsSid);
    }

    /**
    * Returns the deduction details with the primary key.
    *
    * @param deductionDetailsSid the primary key of the deduction details
    * @return the deduction details
    * @throws PortalException if a deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionDetails getDeductionDetails(
        int deductionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _deductionDetailsLocalService.getDeductionDetails(deductionDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _deductionDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the deduction detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction detailses
    * @param end the upper bound of the range of deduction detailses (not inclusive)
    * @return the range of deduction detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.DeductionDetails> getDeductionDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionDetailsLocalService.getDeductionDetailses(start, end);
    }

    /**
    * Returns the number of deduction detailses.
    *
    * @return the number of deduction detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getDeductionDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionDetailsLocalService.getDeductionDetailsesCount();
    }

    /**
    * Updates the deduction details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param deductionDetails the deduction details
    * @return the deduction details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionDetails updateDeductionDetails(
        com.stpl.app.model.DeductionDetails deductionDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionDetailsLocalService.updateDeductionDetails(deductionDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _deductionDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _deductionDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _deductionDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DeductionDetailsLocalService getWrappedDeductionDetailsLocalService() {
        return _deductionDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDeductionDetailsLocalService(
        DeductionDetailsLocalService deductionDetailsLocalService) {
        _deductionDetailsLocalService = deductionDetailsLocalService;
    }

    @Override
    public DeductionDetailsLocalService getWrappedService() {
        return _deductionDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        DeductionDetailsLocalService deductionDetailsLocalService) {
        _deductionDetailsLocalService = deductionDetailsLocalService;
    }
}
