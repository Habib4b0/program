package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StAdjustmentReserveDetailLocalService}.
 *
 * @author
 * @see StAdjustmentReserveDetailLocalService
 * @generated
 */
public class StAdjustmentReserveDetailLocalServiceWrapper
    implements StAdjustmentReserveDetailLocalService,
        ServiceWrapper<StAdjustmentReserveDetailLocalService> {
    private StAdjustmentReserveDetailLocalService _stAdjustmentReserveDetailLocalService;

    public StAdjustmentReserveDetailLocalServiceWrapper(
        StAdjustmentReserveDetailLocalService stAdjustmentReserveDetailLocalService) {
        _stAdjustmentReserveDetailLocalService = stAdjustmentReserveDetailLocalService;
    }

    /**
    * Adds the st adjustment reserve detail to the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentReserveDetail the st adjustment reserve detail
    * @return the st adjustment reserve detail that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail addStAdjustmentReserveDetail(
        com.stpl.app.parttwo.model.StAdjustmentReserveDetail stAdjustmentReserveDetail)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentReserveDetailLocalService.addStAdjustmentReserveDetail(stAdjustmentReserveDetail);
    }

    /**
    * Creates a new st adjustment reserve detail with the primary key. Does not add the st adjustment reserve detail to the database.
    *
    * @param stAdjustmentReserveDetailPK the primary key for the new st adjustment reserve detail
    * @return the new st adjustment reserve detail
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail createStAdjustmentReserveDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK) {
        return _stAdjustmentReserveDetailLocalService.createStAdjustmentReserveDetail(stAdjustmentReserveDetailPK);
    }

    /**
    * Deletes the st adjustment reserve detail with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentReserveDetailPK the primary key of the st adjustment reserve detail
    * @return the st adjustment reserve detail that was removed
    * @throws PortalException if a st adjustment reserve detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail deleteStAdjustmentReserveDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentReserveDetailLocalService.deleteStAdjustmentReserveDetail(stAdjustmentReserveDetailPK);
    }

    /**
    * Deletes the st adjustment reserve detail from the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentReserveDetail the st adjustment reserve detail
    * @return the st adjustment reserve detail that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail deleteStAdjustmentReserveDetail(
        com.stpl.app.parttwo.model.StAdjustmentReserveDetail stAdjustmentReserveDetail)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentReserveDetailLocalService.deleteStAdjustmentReserveDetail(stAdjustmentReserveDetail);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stAdjustmentReserveDetailLocalService.dynamicQuery();
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
        return _stAdjustmentReserveDetailLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stAdjustmentReserveDetailLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stAdjustmentReserveDetailLocalService.dynamicQuery(dynamicQuery,
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
        return _stAdjustmentReserveDetailLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stAdjustmentReserveDetailLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail fetchStAdjustmentReserveDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentReserveDetailLocalService.fetchStAdjustmentReserveDetail(stAdjustmentReserveDetailPK);
    }

    /**
    * Returns the st adjustment reserve detail with the primary key.
    *
    * @param stAdjustmentReserveDetailPK the primary key of the st adjustment reserve detail
    * @return the st adjustment reserve detail
    * @throws PortalException if a st adjustment reserve detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail getStAdjustmentReserveDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentReserveDetailLocalService.getStAdjustmentReserveDetail(stAdjustmentReserveDetailPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentReserveDetailLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st adjustment reserve details.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st adjustment reserve details
    * @param end the upper bound of the range of st adjustment reserve details (not inclusive)
    * @return the range of st adjustment reserve details
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.StAdjustmentReserveDetail> getStAdjustmentReserveDetails(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentReserveDetailLocalService.getStAdjustmentReserveDetails(start,
            end);
    }

    /**
    * Returns the number of st adjustment reserve details.
    *
    * @return the number of st adjustment reserve details
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStAdjustmentReserveDetailsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentReserveDetailLocalService.getStAdjustmentReserveDetailsCount();
    }

    /**
    * Updates the st adjustment reserve detail in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentReserveDetail the st adjustment reserve detail
    * @return the st adjustment reserve detail that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail updateStAdjustmentReserveDetail(
        com.stpl.app.parttwo.model.StAdjustmentReserveDetail stAdjustmentReserveDetail)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentReserveDetailLocalService.updateStAdjustmentReserveDetail(stAdjustmentReserveDetail);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stAdjustmentReserveDetailLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stAdjustmentReserveDetailLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stAdjustmentReserveDetailLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StAdjustmentReserveDetailLocalService getWrappedStAdjustmentReserveDetailLocalService() {
        return _stAdjustmentReserveDetailLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStAdjustmentReserveDetailLocalService(
        StAdjustmentReserveDetailLocalService stAdjustmentReserveDetailLocalService) {
        _stAdjustmentReserveDetailLocalService = stAdjustmentReserveDetailLocalService;
    }

    @Override
    public StAdjustmentReserveDetailLocalService getWrappedService() {
        return _stAdjustmentReserveDetailLocalService;
    }

    @Override
    public void setWrappedService(
        StAdjustmentReserveDetailLocalService stAdjustmentReserveDetailLocalService) {
        _stAdjustmentReserveDetailLocalService = stAdjustmentReserveDetailLocalService;
    }
}
