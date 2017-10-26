package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CffCustomViewMasterLocalService}.
 *
 * @author
 * @see CffCustomViewMasterLocalService
 * @generated
 */
public class CffCustomViewMasterLocalServiceWrapper
    implements CffCustomViewMasterLocalService,
        ServiceWrapper<CffCustomViewMasterLocalService> {
    private CffCustomViewMasterLocalService _cffCustomViewMasterLocalService;

    public CffCustomViewMasterLocalServiceWrapper(
        CffCustomViewMasterLocalService cffCustomViewMasterLocalService) {
        _cffCustomViewMasterLocalService = cffCustomViewMasterLocalService;
    }

    /**
    * Adds the cff custom view master to the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustomViewMaster the cff custom view master
    * @return the cff custom view master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewMaster addCffCustomViewMaster(
        com.stpl.app.parttwo.model.CffCustomViewMaster cffCustomViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewMasterLocalService.addCffCustomViewMaster(cffCustomViewMaster);
    }

    /**
    * Creates a new cff custom view master with the primary key. Does not add the cff custom view master to the database.
    *
    * @param cffCustomViewMasterSid the primary key for the new cff custom view master
    * @return the new cff custom view master
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewMaster createCffCustomViewMaster(
        int cffCustomViewMasterSid) {
        return _cffCustomViewMasterLocalService.createCffCustomViewMaster(cffCustomViewMasterSid);
    }

    /**
    * Deletes the cff custom view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustomViewMasterSid the primary key of the cff custom view master
    * @return the cff custom view master that was removed
    * @throws PortalException if a cff custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewMaster deleteCffCustomViewMaster(
        int cffCustomViewMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewMasterLocalService.deleteCffCustomViewMaster(cffCustomViewMasterSid);
    }

    /**
    * Deletes the cff custom view master from the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustomViewMaster the cff custom view master
    * @return the cff custom view master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewMaster deleteCffCustomViewMaster(
        com.stpl.app.parttwo.model.CffCustomViewMaster cffCustomViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewMasterLocalService.deleteCffCustomViewMaster(cffCustomViewMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cffCustomViewMasterLocalService.dynamicQuery();
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
        return _cffCustomViewMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffCustomViewMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffCustomViewMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _cffCustomViewMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cffCustomViewMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.CffCustomViewMaster fetchCffCustomViewMaster(
        int cffCustomViewMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewMasterLocalService.fetchCffCustomViewMaster(cffCustomViewMasterSid);
    }

    /**
    * Returns the cff custom view master with the primary key.
    *
    * @param cffCustomViewMasterSid the primary key of the cff custom view master
    * @return the cff custom view master
    * @throws PortalException if a cff custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewMaster getCffCustomViewMaster(
        int cffCustomViewMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewMasterLocalService.getCffCustomViewMaster(cffCustomViewMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cff custom view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff custom view masters
    * @param end the upper bound of the range of cff custom view masters (not inclusive)
    * @return the range of cff custom view masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.CffCustomViewMaster> getCffCustomViewMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewMasterLocalService.getCffCustomViewMasters(start,
            end);
    }

    /**
    * Returns the number of cff custom view masters.
    *
    * @return the number of cff custom view masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCffCustomViewMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewMasterLocalService.getCffCustomViewMastersCount();
    }

    /**
    * Updates the cff custom view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cffCustomViewMaster the cff custom view master
    * @return the cff custom view master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustomViewMaster updateCffCustomViewMaster(
        com.stpl.app.parttwo.model.CffCustomViewMaster cffCustomViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustomViewMasterLocalService.updateCffCustomViewMaster(cffCustomViewMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cffCustomViewMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cffCustomViewMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cffCustomViewMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CffCustomViewMasterLocalService getWrappedCffCustomViewMasterLocalService() {
        return _cffCustomViewMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCffCustomViewMasterLocalService(
        CffCustomViewMasterLocalService cffCustomViewMasterLocalService) {
        _cffCustomViewMasterLocalService = cffCustomViewMasterLocalService;
    }

    @Override
    public CffCustomViewMasterLocalService getWrappedService() {
        return _cffCustomViewMasterLocalService;
    }

    @Override
    public void setWrappedService(
        CffCustomViewMasterLocalService cffCustomViewMasterLocalService) {
        _cffCustomViewMasterLocalService = cffCustomViewMasterLocalService;
    }
}
