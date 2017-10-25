package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StCffOutboundMasterLocalService}.
 *
 * @author
 * @see StCffOutboundMasterLocalService
 * @generated
 */
public class StCffOutboundMasterLocalServiceWrapper
    implements StCffOutboundMasterLocalService,
        ServiceWrapper<StCffOutboundMasterLocalService> {
    private StCffOutboundMasterLocalService _stCffOutboundMasterLocalService;

    public StCffOutboundMasterLocalServiceWrapper(
        StCffOutboundMasterLocalService stCffOutboundMasterLocalService) {
        _stCffOutboundMasterLocalService = stCffOutboundMasterLocalService;
    }

    /**
    * Adds the st cff outbound master to the database. Also notifies the appropriate model listeners.
    *
    * @param stCffOutboundMaster the st cff outbound master
    * @return the st cff outbound master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StCffOutboundMaster addStCffOutboundMaster(
        com.stpl.app.parttwo.model.StCffOutboundMaster stCffOutboundMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stCffOutboundMasterLocalService.addStCffOutboundMaster(stCffOutboundMaster);
    }

    /**
    * Creates a new st cff outbound master with the primary key. Does not add the st cff outbound master to the database.
    *
    * @param stCffOutboundMasterPK the primary key for the new st cff outbound master
    * @return the new st cff outbound master
    */
    @Override
    public com.stpl.app.parttwo.model.StCffOutboundMaster createStCffOutboundMaster(
        com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK stCffOutboundMasterPK) {
        return _stCffOutboundMasterLocalService.createStCffOutboundMaster(stCffOutboundMasterPK);
    }

    /**
    * Deletes the st cff outbound master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stCffOutboundMasterPK the primary key of the st cff outbound master
    * @return the st cff outbound master that was removed
    * @throws PortalException if a st cff outbound master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StCffOutboundMaster deleteStCffOutboundMaster(
        com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK stCffOutboundMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stCffOutboundMasterLocalService.deleteStCffOutboundMaster(stCffOutboundMasterPK);
    }

    /**
    * Deletes the st cff outbound master from the database. Also notifies the appropriate model listeners.
    *
    * @param stCffOutboundMaster the st cff outbound master
    * @return the st cff outbound master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StCffOutboundMaster deleteStCffOutboundMaster(
        com.stpl.app.parttwo.model.StCffOutboundMaster stCffOutboundMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stCffOutboundMasterLocalService.deleteStCffOutboundMaster(stCffOutboundMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stCffOutboundMasterLocalService.dynamicQuery();
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
        return _stCffOutboundMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stCffOutboundMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stCffOutboundMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _stCffOutboundMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stCffOutboundMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.StCffOutboundMaster fetchStCffOutboundMaster(
        com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK stCffOutboundMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stCffOutboundMasterLocalService.fetchStCffOutboundMaster(stCffOutboundMasterPK);
    }

    /**
    * Returns the st cff outbound master with the primary key.
    *
    * @param stCffOutboundMasterPK the primary key of the st cff outbound master
    * @return the st cff outbound master
    * @throws PortalException if a st cff outbound master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StCffOutboundMaster getStCffOutboundMaster(
        com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK stCffOutboundMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stCffOutboundMasterLocalService.getStCffOutboundMaster(stCffOutboundMasterPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stCffOutboundMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st cff outbound masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st cff outbound masters
    * @param end the upper bound of the range of st cff outbound masters (not inclusive)
    * @return the range of st cff outbound masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.StCffOutboundMaster> getStCffOutboundMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stCffOutboundMasterLocalService.getStCffOutboundMasters(start,
            end);
    }

    /**
    * Returns the number of st cff outbound masters.
    *
    * @return the number of st cff outbound masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStCffOutboundMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stCffOutboundMasterLocalService.getStCffOutboundMastersCount();
    }

    /**
    * Updates the st cff outbound master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stCffOutboundMaster the st cff outbound master
    * @return the st cff outbound master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StCffOutboundMaster updateStCffOutboundMaster(
        com.stpl.app.parttwo.model.StCffOutboundMaster stCffOutboundMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stCffOutboundMasterLocalService.updateStCffOutboundMaster(stCffOutboundMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stCffOutboundMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stCffOutboundMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stCffOutboundMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StCffOutboundMasterLocalService getWrappedStCffOutboundMasterLocalService() {
        return _stCffOutboundMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStCffOutboundMasterLocalService(
        StCffOutboundMasterLocalService stCffOutboundMasterLocalService) {
        _stCffOutboundMasterLocalService = stCffOutboundMasterLocalService;
    }

    @Override
    public StCffOutboundMasterLocalService getWrappedService() {
        return _stCffOutboundMasterLocalService;
    }

    @Override
    public void setWrappedService(
        StCffOutboundMasterLocalService stCffOutboundMasterLocalService) {
        _stCffOutboundMasterLocalService = stCffOutboundMasterLocalService;
    }
}
