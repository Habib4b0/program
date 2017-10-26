package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StChDiscountProjMasterLocalService}.
 *
 * @author
 * @see StChDiscountProjMasterLocalService
 * @generated
 */
public class StChDiscountProjMasterLocalServiceWrapper
    implements StChDiscountProjMasterLocalService,
        ServiceWrapper<StChDiscountProjMasterLocalService> {
    private StChDiscountProjMasterLocalService _stChDiscountProjMasterLocalService;

    public StChDiscountProjMasterLocalServiceWrapper(
        StChDiscountProjMasterLocalService stChDiscountProjMasterLocalService) {
        _stChDiscountProjMasterLocalService = stChDiscountProjMasterLocalService;
    }

    /**
    * Adds the st ch discount proj master to the database. Also notifies the appropriate model listeners.
    *
    * @param stChDiscountProjMaster the st ch discount proj master
    * @return the st ch discount proj master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChDiscountProjMaster addStChDiscountProjMaster(
        com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChDiscountProjMasterLocalService.addStChDiscountProjMaster(stChDiscountProjMaster);
    }

    /**
    * Creates a new st ch discount proj master with the primary key. Does not add the st ch discount proj master to the database.
    *
    * @param stChDiscountProjMasterPK the primary key for the new st ch discount proj master
    * @return the new st ch discount proj master
    */
    @Override
    public com.stpl.app.model.StChDiscountProjMaster createStChDiscountProjMaster(
        com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK) {
        return _stChDiscountProjMasterLocalService.createStChDiscountProjMaster(stChDiscountProjMasterPK);
    }

    /**
    * Deletes the st ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
    * @return the st ch discount proj master that was removed
    * @throws PortalException if a st ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChDiscountProjMaster deleteStChDiscountProjMaster(
        com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stChDiscountProjMasterLocalService.deleteStChDiscountProjMaster(stChDiscountProjMasterPK);
    }

    /**
    * Deletes the st ch discount proj master from the database. Also notifies the appropriate model listeners.
    *
    * @param stChDiscountProjMaster the st ch discount proj master
    * @return the st ch discount proj master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChDiscountProjMaster deleteStChDiscountProjMaster(
        com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChDiscountProjMasterLocalService.deleteStChDiscountProjMaster(stChDiscountProjMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stChDiscountProjMasterLocalService.dynamicQuery();
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
        return _stChDiscountProjMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stChDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stChDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _stChDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stChDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StChDiscountProjMaster fetchStChDiscountProjMaster(
        com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChDiscountProjMasterLocalService.fetchStChDiscountProjMaster(stChDiscountProjMasterPK);
    }

    /**
    * Returns the st ch discount proj master with the primary key.
    *
    * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
    * @return the st ch discount proj master
    * @throws PortalException if a st ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChDiscountProjMaster getStChDiscountProjMaster(
        com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stChDiscountProjMasterLocalService.getStChDiscountProjMaster(stChDiscountProjMasterPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stChDiscountProjMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st ch discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch discount proj masters
    * @param end the upper bound of the range of st ch discount proj masters (not inclusive)
    * @return the range of st ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StChDiscountProjMaster> getStChDiscountProjMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChDiscountProjMasterLocalService.getStChDiscountProjMasters(start,
            end);
    }

    /**
    * Returns the number of st ch discount proj masters.
    *
    * @return the number of st ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStChDiscountProjMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChDiscountProjMasterLocalService.getStChDiscountProjMastersCount();
    }

    /**
    * Updates the st ch discount proj master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stChDiscountProjMaster the st ch discount proj master
    * @return the st ch discount proj master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChDiscountProjMaster updateStChDiscountProjMaster(
        com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChDiscountProjMasterLocalService.updateStChDiscountProjMaster(stChDiscountProjMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stChDiscountProjMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stChDiscountProjMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stChDiscountProjMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List executeQuery(java.lang.String query) {
        return _stChDiscountProjMasterLocalService.executeQuery(query);
    }

    @Override
    public int updateQuery(java.lang.String query) {
        return _stChDiscountProjMasterLocalService.updateQuery(query);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StChDiscountProjMasterLocalService getWrappedStChDiscountProjMasterLocalService() {
        return _stChDiscountProjMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStChDiscountProjMasterLocalService(
        StChDiscountProjMasterLocalService stChDiscountProjMasterLocalService) {
        _stChDiscountProjMasterLocalService = stChDiscountProjMasterLocalService;
    }

    @Override
    public StChDiscountProjMasterLocalService getWrappedService() {
        return _stChDiscountProjMasterLocalService;
    }

    @Override
    public void setWrappedService(
        StChDiscountProjMasterLocalService stChDiscountProjMasterLocalService) {
        _stChDiscountProjMasterLocalService = stChDiscountProjMasterLocalService;
    }
}
