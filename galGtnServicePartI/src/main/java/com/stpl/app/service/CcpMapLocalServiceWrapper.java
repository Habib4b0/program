package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CcpMapLocalService}.
 *
 * @author
 * @see CcpMapLocalService
 * @generated
 */
public class CcpMapLocalServiceWrapper implements CcpMapLocalService,
    ServiceWrapper<CcpMapLocalService> {
    private CcpMapLocalService _ccpMapLocalService;

    public CcpMapLocalServiceWrapper(CcpMapLocalService ccpMapLocalService) {
        _ccpMapLocalService = ccpMapLocalService;
    }

    /**
    * Adds the ccp map to the database. Also notifies the appropriate model listeners.
    *
    * @param ccpMap the ccp map
    * @return the ccp map that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CcpMap addCcpMap(com.stpl.app.model.CcpMap ccpMap)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpMapLocalService.addCcpMap(ccpMap);
    }

    /**
    * Creates a new ccp map with the primary key. Does not add the ccp map to the database.
    *
    * @param ccpMapSid the primary key for the new ccp map
    * @return the new ccp map
    */
    @Override
    public com.stpl.app.model.CcpMap createCcpMap(int ccpMapSid) {
        return _ccpMapLocalService.createCcpMap(ccpMapSid);
    }

    /**
    * Deletes the ccp map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ccpMapSid the primary key of the ccp map
    * @return the ccp map that was removed
    * @throws PortalException if a ccp map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CcpMap deleteCcpMap(int ccpMapSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ccpMapLocalService.deleteCcpMap(ccpMapSid);
    }

    /**
    * Deletes the ccp map from the database. Also notifies the appropriate model listeners.
    *
    * @param ccpMap the ccp map
    * @return the ccp map that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CcpMap deleteCcpMap(
        com.stpl.app.model.CcpMap ccpMap)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpMapLocalService.deleteCcpMap(ccpMap);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ccpMapLocalService.dynamicQuery();
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
        return _ccpMapLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ccpMapLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ccpMapLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _ccpMapLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ccpMapLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.CcpMap fetchCcpMap(int ccpMapSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpMapLocalService.fetchCcpMap(ccpMapSid);
    }

    /**
    * Returns the ccp map with the primary key.
    *
    * @param ccpMapSid the primary key of the ccp map
    * @return the ccp map
    * @throws PortalException if a ccp map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CcpMap getCcpMap(int ccpMapSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ccpMapLocalService.getCcpMap(ccpMapSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ccpMapLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ccp maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ccp maps
    * @param end the upper bound of the range of ccp maps (not inclusive)
    * @return the range of ccp maps
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CcpMap> getCcpMaps(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpMapLocalService.getCcpMaps(start, end);
    }

    /**
    * Returns the number of ccp maps.
    *
    * @return the number of ccp maps
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCcpMapsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpMapLocalService.getCcpMapsCount();
    }

    /**
    * Updates the ccp map in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ccpMap the ccp map
    * @return the ccp map that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CcpMap updateCcpMap(
        com.stpl.app.model.CcpMap ccpMap)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpMapLocalService.updateCcpMap(ccpMap);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ccpMapLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ccpMapLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ccpMapLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CcpMapLocalService getWrappedCcpMapLocalService() {
        return _ccpMapLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCcpMapLocalService(
        CcpMapLocalService ccpMapLocalService) {
        _ccpMapLocalService = ccpMapLocalService;
    }

    @Override
    public CcpMapLocalService getWrappedService() {
        return _ccpMapLocalService;
    }

    @Override
    public void setWrappedService(CcpMapLocalService ccpMapLocalService) {
        _ccpMapLocalService = ccpMapLocalService;
    }
}
