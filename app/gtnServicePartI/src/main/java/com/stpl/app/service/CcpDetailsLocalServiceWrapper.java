package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CcpDetailsLocalService}.
 *
 * @author
 * @see CcpDetailsLocalService
 * @generated
 */
public class CcpDetailsLocalServiceWrapper implements CcpDetailsLocalService,
    ServiceWrapper<CcpDetailsLocalService> {
    private CcpDetailsLocalService _ccpDetailsLocalService;

    public CcpDetailsLocalServiceWrapper(
        CcpDetailsLocalService ccpDetailsLocalService) {
        _ccpDetailsLocalService = ccpDetailsLocalService;
    }

    /**
    * Adds the ccp details to the database. Also notifies the appropriate model listeners.
    *
    * @param ccpDetails the ccp details
    * @return the ccp details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CcpDetails addCcpDetails(
        com.stpl.app.model.CcpDetails ccpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpDetailsLocalService.addCcpDetails(ccpDetails);
    }

    /**
    * Creates a new ccp details with the primary key. Does not add the ccp details to the database.
    *
    * @param ccpDetailsSid the primary key for the new ccp details
    * @return the new ccp details
    */
    @Override
    public com.stpl.app.model.CcpDetails createCcpDetails(int ccpDetailsSid) {
        return _ccpDetailsLocalService.createCcpDetails(ccpDetailsSid);
    }

    /**
    * Deletes the ccp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ccpDetailsSid the primary key of the ccp details
    * @return the ccp details that was removed
    * @throws PortalException if a ccp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CcpDetails deleteCcpDetails(int ccpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ccpDetailsLocalService.deleteCcpDetails(ccpDetailsSid);
    }

    /**
    * Deletes the ccp details from the database. Also notifies the appropriate model listeners.
    *
    * @param ccpDetails the ccp details
    * @return the ccp details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CcpDetails deleteCcpDetails(
        com.stpl.app.model.CcpDetails ccpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpDetailsLocalService.deleteCcpDetails(ccpDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ccpDetailsLocalService.dynamicQuery();
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
        return _ccpDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ccpDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ccpDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _ccpDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ccpDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.CcpDetails fetchCcpDetails(int ccpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpDetailsLocalService.fetchCcpDetails(ccpDetailsSid);
    }

    /**
    * Returns the ccp details with the primary key.
    *
    * @param ccpDetailsSid the primary key of the ccp details
    * @return the ccp details
    * @throws PortalException if a ccp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CcpDetails getCcpDetails(int ccpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ccpDetailsLocalService.getCcpDetails(ccpDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ccpDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ccp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ccp detailses
    * @param end the upper bound of the range of ccp detailses (not inclusive)
    * @return the range of ccp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CcpDetails> getCcpDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpDetailsLocalService.getCcpDetailses(start, end);
    }

    /**
    * Returns the number of ccp detailses.
    *
    * @return the number of ccp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCcpDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpDetailsLocalService.getCcpDetailsesCount();
    }

    /**
    * Updates the ccp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ccpDetails the ccp details
    * @return the ccp details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CcpDetails updateCcpDetails(
        com.stpl.app.model.CcpDetails ccpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ccpDetailsLocalService.updateCcpDetails(ccpDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ccpDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ccpDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ccpDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CcpDetailsLocalService getWrappedCcpDetailsLocalService() {
        return _ccpDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCcpDetailsLocalService(
        CcpDetailsLocalService ccpDetailsLocalService) {
        _ccpDetailsLocalService = ccpDetailsLocalService;
    }

    @Override
    public CcpDetailsLocalService getWrappedService() {
        return _ccpDetailsLocalService;
    }

    @Override
    public void setWrappedService(CcpDetailsLocalService ccpDetailsLocalService) {
        _ccpDetailsLocalService = ccpDetailsLocalService;
    }
}
