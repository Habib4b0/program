package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CffCustomViewDetailsLocalService}.
 *
 * @author
 * @see CffCustomViewDetailsLocalService
 * @generated
 */
public class CffCustomViewDetailsLocalServiceWrapper
    implements CffCustomViewDetailsLocalService,
        ServiceWrapper<CffCustomViewDetailsLocalService> {
    private CffCustomViewDetailsLocalService _cffCustomViewDetailsLocalService;

    public CffCustomViewDetailsLocalServiceWrapper(
        CffCustomViewDetailsLocalService cffCustomViewDetailsLocalService) {
        _cffCustomViewDetailsLocalService = cffCustomViewDetailsLocalService;
    }

    /**
    * Adds the cff custom view details to the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustomViewDetails the cff custom view details
    * @return the cff custom view details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewDetails addCffCustomViewDetails(
        com.stpl.app.parttwo.model.CffCustomViewDetails cffCustomViewDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewDetailsLocalService.addCffCustomViewDetails(cffCustomViewDetails);
    }

    /**
    * Creates a new cff custom view details with the primary key. Does not add the cff custom view details to the database.
    *
    * @param cffCustomViewDetailsSid the primary key for the new cff custom view details
    * @return the new cff custom view details
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewDetails createCffCustomViewDetails(
        int cffCustomViewDetailsSid) {
        return _cffCustomViewDetailsLocalService.createCffCustomViewDetails(cffCustomViewDetailsSid);
    }

    /**
    * Deletes the cff custom view details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustomViewDetailsSid the primary key of the cff custom view details
    * @return the cff custom view details that was removed
    * @throws PortalException if a cff custom view details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewDetails deleteCffCustomViewDetails(
        int cffCustomViewDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewDetailsLocalService.deleteCffCustomViewDetails(cffCustomViewDetailsSid);
    }

    /**
    * Deletes the cff custom view details from the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustomViewDetails the cff custom view details
    * @return the cff custom view details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewDetails deleteCffCustomViewDetails(
        com.stpl.app.parttwo.model.CffCustomViewDetails cffCustomViewDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewDetailsLocalService.deleteCffCustomViewDetails(cffCustomViewDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cffCustomViewDetailsLocalService.dynamicQuery();
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
        return _cffCustomViewDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffCustomViewDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffCustomViewDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _cffCustomViewDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cffCustomViewDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.CffCustomViewDetails fetchCffCustomViewDetails(
        int cffCustomViewDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewDetailsLocalService.fetchCffCustomViewDetails(cffCustomViewDetailsSid);
    }

    /**
    * Returns the cff custom view details with the primary key.
    *
    * @param cffCustomViewDetailsSid the primary key of the cff custom view details
    * @return the cff custom view details
    * @throws PortalException if a cff custom view details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewDetails getCffCustomViewDetails(
        int cffCustomViewDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewDetailsLocalService.getCffCustomViewDetails(cffCustomViewDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cff custom view detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff custom view detailses
    * @param end the upper bound of the range of cff custom view detailses (not inclusive)
    * @return the range of cff custom view detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.CffCustomViewDetails> getCffCustomViewDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewDetailsLocalService.getCffCustomViewDetailses(start,
            end);
    }

    /**
    * Returns the number of cff custom view detailses.
    *
    * @return the number of cff custom view detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCffCustomViewDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewDetailsLocalService.getCffCustomViewDetailsesCount();
    }

    /**
    * Updates the cff custom view details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cffCustomViewDetails the cff custom view details
    * @return the cff custom view details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewDetails updateCffCustomViewDetails(
        com.stpl.app.parttwo.model.CffCustomViewDetails cffCustomViewDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewDetailsLocalService.updateCffCustomViewDetails(cffCustomViewDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cffCustomViewDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cffCustomViewDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cffCustomViewDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CffCustomViewDetailsLocalService getWrappedCffCustomViewDetailsLocalService() {
        return _cffCustomViewDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCffCustomViewDetailsLocalService(
        CffCustomViewDetailsLocalService cffCustomViewDetailsLocalService) {
        _cffCustomViewDetailsLocalService = cffCustomViewDetailsLocalService;
    }

    @Override
    public CffCustomViewDetailsLocalService getWrappedService() {
        return _cffCustomViewDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        CffCustomViewDetailsLocalService cffCustomViewDetailsLocalService) {
        _cffCustomViewDetailsLocalService = cffCustomViewDetailsLocalService;
    }
}
