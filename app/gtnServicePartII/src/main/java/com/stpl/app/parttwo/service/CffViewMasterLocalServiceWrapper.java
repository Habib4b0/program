package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CffViewMasterLocalService}.
 *
 * @author
 * @see CffViewMasterLocalService
 * @generated
 */
public class CffViewMasterLocalServiceWrapper
    implements CffViewMasterLocalService,
        ServiceWrapper<CffViewMasterLocalService> {
    private CffViewMasterLocalService _cffViewMasterLocalService;

    public CffViewMasterLocalServiceWrapper(
        CffViewMasterLocalService cffViewMasterLocalService) {
        _cffViewMasterLocalService = cffViewMasterLocalService;
    }

    /**
    * Adds the cff view master to the database. Also notifies the appropriate model listeners.
    *
    * @param cffViewMaster the cff view master
    * @return the cff view master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffViewMaster addCffViewMaster(
        com.stpl.app.parttwo.model.CffViewMaster cffViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffViewMasterLocalService.addCffViewMaster(cffViewMaster);
    }

    /**
    * Creates a new cff view master with the primary key. Does not add the cff view master to the database.
    *
    * @param cffViewMasterSid the primary key for the new cff view master
    * @return the new cff view master
    */
    @Override
    public com.stpl.app.parttwo.model.CffViewMaster createCffViewMaster(
        int cffViewMasterSid) {
        return _cffViewMasterLocalService.createCffViewMaster(cffViewMasterSid);
    }

    /**
    * Deletes the cff view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffViewMasterSid the primary key of the cff view master
    * @return the cff view master that was removed
    * @throws PortalException if a cff view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffViewMaster deleteCffViewMaster(
        int cffViewMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffViewMasterLocalService.deleteCffViewMaster(cffViewMasterSid);
    }

    /**
    * Deletes the cff view master from the database. Also notifies the appropriate model listeners.
    *
    * @param cffViewMaster the cff view master
    * @return the cff view master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffViewMaster deleteCffViewMaster(
        com.stpl.app.parttwo.model.CffViewMaster cffViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffViewMasterLocalService.deleteCffViewMaster(cffViewMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cffViewMasterLocalService.dynamicQuery();
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
        return _cffViewMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffViewMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffViewMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _cffViewMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cffViewMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.CffViewMaster fetchCffViewMaster(
        int cffViewMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffViewMasterLocalService.fetchCffViewMaster(cffViewMasterSid);
    }

    /**
    * Returns the cff view master with the primary key.
    *
    * @param cffViewMasterSid the primary key of the cff view master
    * @return the cff view master
    * @throws PortalException if a cff view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffViewMaster getCffViewMaster(
        int cffViewMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffViewMasterLocalService.getCffViewMaster(cffViewMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffViewMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cff view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff view masters
    * @param end the upper bound of the range of cff view masters (not inclusive)
    * @return the range of cff view masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.CffViewMaster> getCffViewMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffViewMasterLocalService.getCffViewMasters(start, end);
    }

    /**
    * Returns the number of cff view masters.
    *
    * @return the number of cff view masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCffViewMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffViewMasterLocalService.getCffViewMastersCount();
    }

    /**
    * Updates the cff view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cffViewMaster the cff view master
    * @return the cff view master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffViewMaster updateCffViewMaster(
        com.stpl.app.parttwo.model.CffViewMaster cffViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffViewMasterLocalService.updateCffViewMaster(cffViewMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cffViewMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cffViewMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cffViewMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CffViewMasterLocalService getWrappedCffViewMasterLocalService() {
        return _cffViewMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCffViewMasterLocalService(
        CffViewMasterLocalService cffViewMasterLocalService) {
        _cffViewMasterLocalService = cffViewMasterLocalService;
    }

    @Override
    public CffViewMasterLocalService getWrappedService() {
        return _cffViewMasterLocalService;
    }

    @Override
    public void setWrappedService(
        CffViewMasterLocalService cffViewMasterLocalService) {
        _cffViewMasterLocalService = cffViewMasterLocalService;
    }
}
