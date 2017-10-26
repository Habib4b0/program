package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CdrDetailsLocalService}.
 *
 * @author
 * @see CdrDetailsLocalService
 * @generated
 */
public class CdrDetailsLocalServiceWrapper implements CdrDetailsLocalService,
    ServiceWrapper<CdrDetailsLocalService> {
    private CdrDetailsLocalService _cdrDetailsLocalService;

    public CdrDetailsLocalServiceWrapper(
        CdrDetailsLocalService cdrDetailsLocalService) {
        _cdrDetailsLocalService = cdrDetailsLocalService;
    }

    /**
    * Adds the cdr details to the database. Also notifies the appropriate model listeners.
    *
    * @param cdrDetails the cdr details
    * @return the cdr details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CdrDetails addCdrDetails(
        com.stpl.app.model.CdrDetails cdrDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrDetailsLocalService.addCdrDetails(cdrDetails);
    }

    /**
    * Creates a new cdr details with the primary key. Does not add the cdr details to the database.
    *
    * @param cdrDetailsSid the primary key for the new cdr details
    * @return the new cdr details
    */
    @Override
    public com.stpl.app.model.CdrDetails createCdrDetails(int cdrDetailsSid) {
        return _cdrDetailsLocalService.createCdrDetails(cdrDetailsSid);
    }

    /**
    * Deletes the cdr details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cdrDetailsSid the primary key of the cdr details
    * @return the cdr details that was removed
    * @throws PortalException if a cdr details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CdrDetails deleteCdrDetails(int cdrDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cdrDetailsLocalService.deleteCdrDetails(cdrDetailsSid);
    }

    /**
    * Deletes the cdr details from the database. Also notifies the appropriate model listeners.
    *
    * @param cdrDetails the cdr details
    * @return the cdr details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CdrDetails deleteCdrDetails(
        com.stpl.app.model.CdrDetails cdrDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrDetailsLocalService.deleteCdrDetails(cdrDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cdrDetailsLocalService.dynamicQuery();
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
        return _cdrDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cdrDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cdrDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _cdrDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cdrDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.CdrDetails fetchCdrDetails(int cdrDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrDetailsLocalService.fetchCdrDetails(cdrDetailsSid);
    }

    /**
    * Returns the cdr details with the primary key.
    *
    * @param cdrDetailsSid the primary key of the cdr details
    * @return the cdr details
    * @throws PortalException if a cdr details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CdrDetails getCdrDetails(int cdrDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cdrDetailsLocalService.getCdrDetails(cdrDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cdrDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cdr detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cdr detailses
    * @param end the upper bound of the range of cdr detailses (not inclusive)
    * @return the range of cdr detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CdrDetails> getCdrDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrDetailsLocalService.getCdrDetailses(start, end);
    }

    /**
    * Returns the number of cdr detailses.
    *
    * @return the number of cdr detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCdrDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrDetailsLocalService.getCdrDetailsesCount();
    }

    /**
    * Updates the cdr details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cdrDetails the cdr details
    * @return the cdr details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CdrDetails updateCdrDetails(
        com.stpl.app.model.CdrDetails cdrDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrDetailsLocalService.updateCdrDetails(cdrDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cdrDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cdrDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cdrDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CdrDetailsLocalService getWrappedCdrDetailsLocalService() {
        return _cdrDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCdrDetailsLocalService(
        CdrDetailsLocalService cdrDetailsLocalService) {
        _cdrDetailsLocalService = cdrDetailsLocalService;
    }

    @Override
    public CdrDetailsLocalService getWrappedService() {
        return _cdrDetailsLocalService;
    }

    @Override
    public void setWrappedService(CdrDetailsLocalService cdrDetailsLocalService) {
        _cdrDetailsLocalService = cdrDetailsLocalService;
    }
}
