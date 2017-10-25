package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CfpContractDetailsLocalService}.
 *
 * @author
 * @see CfpContractDetailsLocalService
 * @generated
 */
public class CfpContractDetailsLocalServiceWrapper
    implements CfpContractDetailsLocalService,
        ServiceWrapper<CfpContractDetailsLocalService> {
    private CfpContractDetailsLocalService _cfpContractDetailsLocalService;

    public CfpContractDetailsLocalServiceWrapper(
        CfpContractDetailsLocalService cfpContractDetailsLocalService) {
        _cfpContractDetailsLocalService = cfpContractDetailsLocalService;
    }

    /**
    * Adds the cfp contract details to the database. Also notifies the appropriate model listeners.
    *
    * @param cfpContractDetails the cfp contract details
    * @return the cfp contract details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpContractDetails addCfpContractDetails(
        com.stpl.app.model.CfpContractDetails cfpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpContractDetailsLocalService.addCfpContractDetails(cfpContractDetails);
    }

    /**
    * Creates a new cfp contract details with the primary key. Does not add the cfp contract details to the database.
    *
    * @param cfpContractDetailsSid the primary key for the new cfp contract details
    * @return the new cfp contract details
    */
    @Override
    public com.stpl.app.model.CfpContractDetails createCfpContractDetails(
        int cfpContractDetailsSid) {
        return _cfpContractDetailsLocalService.createCfpContractDetails(cfpContractDetailsSid);
    }

    /**
    * Deletes the cfp contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpContractDetailsSid the primary key of the cfp contract details
    * @return the cfp contract details that was removed
    * @throws PortalException if a cfp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpContractDetails deleteCfpContractDetails(
        int cfpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cfpContractDetailsLocalService.deleteCfpContractDetails(cfpContractDetailsSid);
    }

    /**
    * Deletes the cfp contract details from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpContractDetails the cfp contract details
    * @return the cfp contract details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpContractDetails deleteCfpContractDetails(
        com.stpl.app.model.CfpContractDetails cfpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpContractDetailsLocalService.deleteCfpContractDetails(cfpContractDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cfpContractDetailsLocalService.dynamicQuery();
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
        return _cfpContractDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cfpContractDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cfpContractDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _cfpContractDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cfpContractDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.CfpContractDetails fetchCfpContractDetails(
        int cfpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpContractDetailsLocalService.fetchCfpContractDetails(cfpContractDetailsSid);
    }

    /**
    * Returns the cfp contract details with the primary key.
    *
    * @param cfpContractDetailsSid the primary key of the cfp contract details
    * @return the cfp contract details
    * @throws PortalException if a cfp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpContractDetails getCfpContractDetails(
        int cfpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cfpContractDetailsLocalService.getCfpContractDetails(cfpContractDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cfpContractDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cfp contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp contract detailses
    * @param end the upper bound of the range of cfp contract detailses (not inclusive)
    * @return the range of cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CfpContractDetails> getCfpContractDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpContractDetailsLocalService.getCfpContractDetailses(start,
            end);
    }

    /**
    * Returns the number of cfp contract detailses.
    *
    * @return the number of cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCfpContractDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpContractDetailsLocalService.getCfpContractDetailsesCount();
    }

    /**
    * Updates the cfp contract details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cfpContractDetails the cfp contract details
    * @return the cfp contract details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpContractDetails updateCfpContractDetails(
        com.stpl.app.model.CfpContractDetails cfpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpContractDetailsLocalService.updateCfpContractDetails(cfpContractDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cfpContractDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cfpContractDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cfpContractDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.lang.Boolean saveCfpDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return _cfpContractDetailsLocalService.saveCfpDetailsAttached(input,
            future);
    }

    @Override
    public java.util.List getCompaniesList(java.lang.String searchField,
        java.lang.String searchVal,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.lang.Object future1, java.lang.Object future2) {
        return _cfpContractDetailsLocalService.getCompaniesList(searchField,
            searchVal, filterMap, start, offset, column, orderBy, future1,
            future2);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CfpContractDetailsLocalService getWrappedCfpContractDetailsLocalService() {
        return _cfpContractDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCfpContractDetailsLocalService(
        CfpContractDetailsLocalService cfpContractDetailsLocalService) {
        _cfpContractDetailsLocalService = cfpContractDetailsLocalService;
    }

    @Override
    public CfpContractDetailsLocalService getWrappedService() {
        return _cfpContractDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        CfpContractDetailsLocalService cfpContractDetailsLocalService) {
        _cfpContractDetailsLocalService = cfpContractDetailsLocalService;
    }
}
