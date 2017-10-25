package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CffDocDetailsLocalService}.
 *
 * @author
 * @see CffDocDetailsLocalService
 * @generated
 */
public class CffDocDetailsLocalServiceWrapper
    implements CffDocDetailsLocalService,
        ServiceWrapper<CffDocDetailsLocalService> {
    private CffDocDetailsLocalService _cffDocDetailsLocalService;

    public CffDocDetailsLocalServiceWrapper(
        CffDocDetailsLocalService cffDocDetailsLocalService) {
        _cffDocDetailsLocalService = cffDocDetailsLocalService;
    }

    /**
    * Adds the cff doc details to the database. Also notifies the appropriate model listeners.
    *
    * @param cffDocDetails the cff doc details
    * @return the cff doc details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffDocDetails addCffDocDetails(
        com.stpl.app.parttwo.model.CffDocDetails cffDocDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffDocDetailsLocalService.addCffDocDetails(cffDocDetails);
    }

    /**
    * Creates a new cff doc details with the primary key. Does not add the cff doc details to the database.
    *
    * @param cffDocDetailsSid the primary key for the new cff doc details
    * @return the new cff doc details
    */
    @Override
    public com.stpl.app.parttwo.model.CffDocDetails createCffDocDetails(
        int cffDocDetailsSid) {
        return _cffDocDetailsLocalService.createCffDocDetails(cffDocDetailsSid);
    }

    /**
    * Deletes the cff doc details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffDocDetailsSid the primary key of the cff doc details
    * @return the cff doc details that was removed
    * @throws PortalException if a cff doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffDocDetails deleteCffDocDetails(
        int cffDocDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffDocDetailsLocalService.deleteCffDocDetails(cffDocDetailsSid);
    }

    /**
    * Deletes the cff doc details from the database. Also notifies the appropriate model listeners.
    *
    * @param cffDocDetails the cff doc details
    * @return the cff doc details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffDocDetails deleteCffDocDetails(
        com.stpl.app.parttwo.model.CffDocDetails cffDocDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffDocDetailsLocalService.deleteCffDocDetails(cffDocDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cffDocDetailsLocalService.dynamicQuery();
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
        return _cffDocDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffDocDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffDocDetailsLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _cffDocDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cffDocDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.CffDocDetails fetchCffDocDetails(
        int cffDocDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffDocDetailsLocalService.fetchCffDocDetails(cffDocDetailsSid);
    }

    /**
    * Returns the cff doc details with the primary key.
    *
    * @param cffDocDetailsSid the primary key of the cff doc details
    * @return the cff doc details
    * @throws PortalException if a cff doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffDocDetails getCffDocDetails(
        int cffDocDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffDocDetailsLocalService.getCffDocDetails(cffDocDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffDocDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cff doc detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff doc detailses
    * @param end the upper bound of the range of cff doc detailses (not inclusive)
    * @return the range of cff doc detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.CffDocDetails> getCffDocDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffDocDetailsLocalService.getCffDocDetailses(start, end);
    }

    /**
    * Returns the number of cff doc detailses.
    *
    * @return the number of cff doc detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCffDocDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffDocDetailsLocalService.getCffDocDetailsesCount();
    }

    /**
    * Updates the cff doc details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cffDocDetails the cff doc details
    * @return the cff doc details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffDocDetails updateCffDocDetails(
        com.stpl.app.parttwo.model.CffDocDetails cffDocDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffDocDetailsLocalService.updateCffDocDetails(cffDocDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cffDocDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cffDocDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cffDocDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CffDocDetailsLocalService getWrappedCffDocDetailsLocalService() {
        return _cffDocDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCffDocDetailsLocalService(
        CffDocDetailsLocalService cffDocDetailsLocalService) {
        _cffDocDetailsLocalService = cffDocDetailsLocalService;
    }

    @Override
    public CffDocDetailsLocalService getWrappedService() {
        return _cffDocDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        CffDocDetailsLocalService cffDocDetailsLocalService) {
        _cffDocDetailsLocalService = cffDocDetailsLocalService;
    }
}
