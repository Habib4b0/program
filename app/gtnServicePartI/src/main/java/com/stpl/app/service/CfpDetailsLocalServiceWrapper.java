package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CfpDetailsLocalService}.
 *
 * @author
 * @see CfpDetailsLocalService
 * @generated
 */
public class CfpDetailsLocalServiceWrapper implements CfpDetailsLocalService,
    ServiceWrapper<CfpDetailsLocalService> {
    private CfpDetailsLocalService _cfpDetailsLocalService;

    public CfpDetailsLocalServiceWrapper(
        CfpDetailsLocalService cfpDetailsLocalService) {
        _cfpDetailsLocalService = cfpDetailsLocalService;
    }

    /**
    * Adds the cfp details to the database. Also notifies the appropriate model listeners.
    *
    * @param cfpDetails the cfp details
    * @return the cfp details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpDetails addCfpDetails(
        com.stpl.app.model.CfpDetails cfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpDetailsLocalService.addCfpDetails(cfpDetails);
    }

    /**
    * Creates a new cfp details with the primary key. Does not add the cfp details to the database.
    *
    * @param cfpDetailsSid the primary key for the new cfp details
    * @return the new cfp details
    */
    @Override
    public com.stpl.app.model.CfpDetails createCfpDetails(int cfpDetailsSid) {
        return _cfpDetailsLocalService.createCfpDetails(cfpDetailsSid);
    }

    /**
    * Deletes the cfp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpDetailsSid the primary key of the cfp details
    * @return the cfp details that was removed
    * @throws PortalException if a cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpDetails deleteCfpDetails(int cfpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cfpDetailsLocalService.deleteCfpDetails(cfpDetailsSid);
    }

    /**
    * Deletes the cfp details from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpDetails the cfp details
    * @return the cfp details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpDetails deleteCfpDetails(
        com.stpl.app.model.CfpDetails cfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpDetailsLocalService.deleteCfpDetails(cfpDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cfpDetailsLocalService.dynamicQuery();
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
        return _cfpDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cfpDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cfpDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _cfpDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cfpDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.CfpDetails fetchCfpDetails(int cfpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpDetailsLocalService.fetchCfpDetails(cfpDetailsSid);
    }

    /**
    * Returns the cfp details with the primary key.
    *
    * @param cfpDetailsSid the primary key of the cfp details
    * @return the cfp details
    * @throws PortalException if a cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpDetails getCfpDetails(int cfpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cfpDetailsLocalService.getCfpDetails(cfpDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cfpDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cfp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp detailses
    * @param end the upper bound of the range of cfp detailses (not inclusive)
    * @return the range of cfp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CfpDetails> getCfpDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpDetailsLocalService.getCfpDetailses(start, end);
    }

    /**
    * Returns the number of cfp detailses.
    *
    * @return the number of cfp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCfpDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpDetailsLocalService.getCfpDetailsesCount();
    }

    /**
    * Updates the cfp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cfpDetails the cfp details
    * @return the cfp details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpDetails updateCfpDetails(
        com.stpl.app.model.CfpDetails cfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpDetailsLocalService.updateCfpDetails(cfpDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cfpDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cfpDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cfpDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List<com.stpl.app.model.CfpDetails> findByCfpModelSid(
        int cfpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpDetailsLocalService.findByCfpModelSid(cfpModelSid);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CfpDetailsLocalService getWrappedCfpDetailsLocalService() {
        return _cfpDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCfpDetailsLocalService(
        CfpDetailsLocalService cfpDetailsLocalService) {
        _cfpDetailsLocalService = cfpDetailsLocalService;
    }

    @Override
    public CfpDetailsLocalService getWrappedService() {
        return _cfpDetailsLocalService;
    }

    @Override
    public void setWrappedService(CfpDetailsLocalService cfpDetailsLocalService) {
        _cfpDetailsLocalService = cfpDetailsLocalService;
    }
}
