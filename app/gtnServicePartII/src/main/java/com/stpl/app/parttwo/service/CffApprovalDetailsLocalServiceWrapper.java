package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CffApprovalDetailsLocalService}.
 *
 * @author
 * @see CffApprovalDetailsLocalService
 * @generated
 */
public class CffApprovalDetailsLocalServiceWrapper
    implements CffApprovalDetailsLocalService,
        ServiceWrapper<CffApprovalDetailsLocalService> {
    private CffApprovalDetailsLocalService _cffApprovalDetailsLocalService;

    public CffApprovalDetailsLocalServiceWrapper(
        CffApprovalDetailsLocalService cffApprovalDetailsLocalService) {
        _cffApprovalDetailsLocalService = cffApprovalDetailsLocalService;
    }

    /**
    * Adds the cff approval details to the database. Also notifies the appropriate model listeners.
    *
    * @param cffApprovalDetails the cff approval details
    * @return the cff approval details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffApprovalDetails addCffApprovalDetails(
        com.stpl.app.parttwo.model.CffApprovalDetails cffApprovalDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffApprovalDetailsLocalService.addCffApprovalDetails(cffApprovalDetails);
    }

    /**
    * Creates a new cff approval details with the primary key. Does not add the cff approval details to the database.
    *
    * @param cffApprovalDetailsSid the primary key for the new cff approval details
    * @return the new cff approval details
    */
    @Override
    public com.stpl.app.parttwo.model.CffApprovalDetails createCffApprovalDetails(
        int cffApprovalDetailsSid) {
        return _cffApprovalDetailsLocalService.createCffApprovalDetails(cffApprovalDetailsSid);
    }

    /**
    * Deletes the cff approval details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffApprovalDetailsSid the primary key of the cff approval details
    * @return the cff approval details that was removed
    * @throws PortalException if a cff approval details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffApprovalDetails deleteCffApprovalDetails(
        int cffApprovalDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffApprovalDetailsLocalService.deleteCffApprovalDetails(cffApprovalDetailsSid);
    }

    /**
    * Deletes the cff approval details from the database. Also notifies the appropriate model listeners.
    *
    * @param cffApprovalDetails the cff approval details
    * @return the cff approval details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffApprovalDetails deleteCffApprovalDetails(
        com.stpl.app.parttwo.model.CffApprovalDetails cffApprovalDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffApprovalDetailsLocalService.deleteCffApprovalDetails(cffApprovalDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cffApprovalDetailsLocalService.dynamicQuery();
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
        return _cffApprovalDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffApprovalDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffApprovalDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _cffApprovalDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cffApprovalDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.CffApprovalDetails fetchCffApprovalDetails(
        int cffApprovalDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffApprovalDetailsLocalService.fetchCffApprovalDetails(cffApprovalDetailsSid);
    }

    /**
    * Returns the cff approval details with the primary key.
    *
    * @param cffApprovalDetailsSid the primary key of the cff approval details
    * @return the cff approval details
    * @throws PortalException if a cff approval details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffApprovalDetails getCffApprovalDetails(
        int cffApprovalDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffApprovalDetailsLocalService.getCffApprovalDetails(cffApprovalDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffApprovalDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cff approval detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff approval detailses
    * @param end the upper bound of the range of cff approval detailses (not inclusive)
    * @return the range of cff approval detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.CffApprovalDetails> getCffApprovalDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffApprovalDetailsLocalService.getCffApprovalDetailses(start,
            end);
    }

    /**
    * Returns the number of cff approval detailses.
    *
    * @return the number of cff approval detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCffApprovalDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffApprovalDetailsLocalService.getCffApprovalDetailsesCount();
    }

    /**
    * Updates the cff approval details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cffApprovalDetails the cff approval details
    * @return the cff approval details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffApprovalDetails updateCffApprovalDetails(
        com.stpl.app.parttwo.model.CffApprovalDetails cffApprovalDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffApprovalDetailsLocalService.updateCffApprovalDetails(cffApprovalDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cffApprovalDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cffApprovalDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cffApprovalDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CffApprovalDetailsLocalService getWrappedCffApprovalDetailsLocalService() {
        return _cffApprovalDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCffApprovalDetailsLocalService(
        CffApprovalDetailsLocalService cffApprovalDetailsLocalService) {
        _cffApprovalDetailsLocalService = cffApprovalDetailsLocalService;
    }

    @Override
    public CffApprovalDetailsLocalService getWrappedService() {
        return _cffApprovalDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        CffApprovalDetailsLocalService cffApprovalDetailsLocalService) {
        _cffApprovalDetailsLocalService = cffApprovalDetailsLocalService;
    }
}
