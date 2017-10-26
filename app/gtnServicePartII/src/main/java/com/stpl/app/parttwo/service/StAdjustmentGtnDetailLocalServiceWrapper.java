package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StAdjustmentGtnDetailLocalService}.
 *
 * @author
 * @see StAdjustmentGtnDetailLocalService
 * @generated
 */
public class StAdjustmentGtnDetailLocalServiceWrapper
    implements StAdjustmentGtnDetailLocalService,
        ServiceWrapper<StAdjustmentGtnDetailLocalService> {
    private StAdjustmentGtnDetailLocalService _stAdjustmentGtnDetailLocalService;

    public StAdjustmentGtnDetailLocalServiceWrapper(
        StAdjustmentGtnDetailLocalService stAdjustmentGtnDetailLocalService) {
        _stAdjustmentGtnDetailLocalService = stAdjustmentGtnDetailLocalService;
    }

    /**
    * Adds the st adjustment gtn detail to the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentGtnDetail the st adjustment gtn detail
    * @return the st adjustment gtn detail that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail addStAdjustmentGtnDetail(
        com.stpl.app.parttwo.model.StAdjustmentGtnDetail stAdjustmentGtnDetail)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentGtnDetailLocalService.addStAdjustmentGtnDetail(stAdjustmentGtnDetail);
    }

    /**
    * Creates a new st adjustment gtn detail with the primary key. Does not add the st adjustment gtn detail to the database.
    *
    * @param stAdjustmentGtnDetailPK the primary key for the new st adjustment gtn detail
    * @return the new st adjustment gtn detail
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail createStAdjustmentGtnDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK) {
        return _stAdjustmentGtnDetailLocalService.createStAdjustmentGtnDetail(stAdjustmentGtnDetailPK);
    }

    /**
    * Deletes the st adjustment gtn detail with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
    * @return the st adjustment gtn detail that was removed
    * @throws PortalException if a st adjustment gtn detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail deleteStAdjustmentGtnDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentGtnDetailLocalService.deleteStAdjustmentGtnDetail(stAdjustmentGtnDetailPK);
    }

    /**
    * Deletes the st adjustment gtn detail from the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentGtnDetail the st adjustment gtn detail
    * @return the st adjustment gtn detail that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail deleteStAdjustmentGtnDetail(
        com.stpl.app.parttwo.model.StAdjustmentGtnDetail stAdjustmentGtnDetail)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentGtnDetailLocalService.deleteStAdjustmentGtnDetail(stAdjustmentGtnDetail);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stAdjustmentGtnDetailLocalService.dynamicQuery();
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
        return _stAdjustmentGtnDetailLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stAdjustmentGtnDetailLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stAdjustmentGtnDetailLocalService.dynamicQuery(dynamicQuery,
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
        return _stAdjustmentGtnDetailLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stAdjustmentGtnDetailLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail fetchStAdjustmentGtnDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentGtnDetailLocalService.fetchStAdjustmentGtnDetail(stAdjustmentGtnDetailPK);
    }

    /**
    * Returns the st adjustment gtn detail with the primary key.
    *
    * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
    * @return the st adjustment gtn detail
    * @throws PortalException if a st adjustment gtn detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail getStAdjustmentGtnDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentGtnDetailLocalService.getStAdjustmentGtnDetail(stAdjustmentGtnDetailPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentGtnDetailLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st adjustment gtn details.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st adjustment gtn details
    * @param end the upper bound of the range of st adjustment gtn details (not inclusive)
    * @return the range of st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.StAdjustmentGtnDetail> getStAdjustmentGtnDetails(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentGtnDetailLocalService.getStAdjustmentGtnDetails(start,
            end);
    }

    /**
    * Returns the number of st adjustment gtn details.
    *
    * @return the number of st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStAdjustmentGtnDetailsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentGtnDetailLocalService.getStAdjustmentGtnDetailsCount();
    }

    /**
    * Updates the st adjustment gtn detail in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentGtnDetail the st adjustment gtn detail
    * @return the st adjustment gtn detail that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail updateStAdjustmentGtnDetail(
        com.stpl.app.parttwo.model.StAdjustmentGtnDetail stAdjustmentGtnDetail)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAdjustmentGtnDetailLocalService.updateStAdjustmentGtnDetail(stAdjustmentGtnDetail);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stAdjustmentGtnDetailLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stAdjustmentGtnDetailLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stAdjustmentGtnDetailLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StAdjustmentGtnDetailLocalService getWrappedStAdjustmentGtnDetailLocalService() {
        return _stAdjustmentGtnDetailLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStAdjustmentGtnDetailLocalService(
        StAdjustmentGtnDetailLocalService stAdjustmentGtnDetailLocalService) {
        _stAdjustmentGtnDetailLocalService = stAdjustmentGtnDetailLocalService;
    }

    @Override
    public StAdjustmentGtnDetailLocalService getWrappedService() {
        return _stAdjustmentGtnDetailLocalService;
    }

    @Override
    public void setWrappedService(
        StAdjustmentGtnDetailLocalService stAdjustmentGtnDetailLocalService) {
        _stAdjustmentGtnDetailLocalService = stAdjustmentGtnDetailLocalService;
    }
}
