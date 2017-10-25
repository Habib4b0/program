package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GlCostCenterMasterLocalService}.
 *
 * @author
 * @see GlCostCenterMasterLocalService
 * @generated
 */
public class GlCostCenterMasterLocalServiceWrapper
    implements GlCostCenterMasterLocalService,
        ServiceWrapper<GlCostCenterMasterLocalService> {
    private GlCostCenterMasterLocalService _glCostCenterMasterLocalService;

    public GlCostCenterMasterLocalServiceWrapper(
        GlCostCenterMasterLocalService glCostCenterMasterLocalService) {
        _glCostCenterMasterLocalService = glCostCenterMasterLocalService;
    }

    /**
    * Adds the gl cost center master to the database. Also notifies the appropriate model listeners.
    *
    * @param glCostCenterMaster the gl cost center master
    * @return the gl cost center master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GlCostCenterMaster addGlCostCenterMaster(
        com.stpl.app.model.GlCostCenterMaster glCostCenterMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glCostCenterMasterLocalService.addGlCostCenterMaster(glCostCenterMaster);
    }

    /**
    * Creates a new gl cost center master with the primary key. Does not add the gl cost center master to the database.
    *
    * @param glCostCenterMasterSid the primary key for the new gl cost center master
    * @return the new gl cost center master
    */
    @Override
    public com.stpl.app.model.GlCostCenterMaster createGlCostCenterMaster(
        int glCostCenterMasterSid) {
        return _glCostCenterMasterLocalService.createGlCostCenterMaster(glCostCenterMasterSid);
    }

    /**
    * Deletes the gl cost center master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param glCostCenterMasterSid the primary key of the gl cost center master
    * @return the gl cost center master that was removed
    * @throws PortalException if a gl cost center master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GlCostCenterMaster deleteGlCostCenterMaster(
        int glCostCenterMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _glCostCenterMasterLocalService.deleteGlCostCenterMaster(glCostCenterMasterSid);
    }

    /**
    * Deletes the gl cost center master from the database. Also notifies the appropriate model listeners.
    *
    * @param glCostCenterMaster the gl cost center master
    * @return the gl cost center master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GlCostCenterMaster deleteGlCostCenterMaster(
        com.stpl.app.model.GlCostCenterMaster glCostCenterMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glCostCenterMasterLocalService.deleteGlCostCenterMaster(glCostCenterMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _glCostCenterMasterLocalService.dynamicQuery();
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
        return _glCostCenterMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _glCostCenterMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _glCostCenterMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _glCostCenterMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _glCostCenterMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.GlCostCenterMaster fetchGlCostCenterMaster(
        int glCostCenterMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glCostCenterMasterLocalService.fetchGlCostCenterMaster(glCostCenterMasterSid);
    }

    /**
    * Returns the gl cost center master with the primary key.
    *
    * @param glCostCenterMasterSid the primary key of the gl cost center master
    * @return the gl cost center master
    * @throws PortalException if a gl cost center master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GlCostCenterMaster getGlCostCenterMaster(
        int glCostCenterMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _glCostCenterMasterLocalService.getGlCostCenterMaster(glCostCenterMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _glCostCenterMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the gl cost center masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gl cost center masters
    * @param end the upper bound of the range of gl cost center masters (not inclusive)
    * @return the range of gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> getGlCostCenterMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glCostCenterMasterLocalService.getGlCostCenterMasters(start, end);
    }

    /**
    * Returns the number of gl cost center masters.
    *
    * @return the number of gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getGlCostCenterMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glCostCenterMasterLocalService.getGlCostCenterMastersCount();
    }

    /**
    * Updates the gl cost center master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param glCostCenterMaster the gl cost center master
    * @return the gl cost center master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GlCostCenterMaster updateGlCostCenterMaster(
        com.stpl.app.model.GlCostCenterMaster glCostCenterMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glCostCenterMasterLocalService.updateGlCostCenterMaster(glCostCenterMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _glCostCenterMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _glCostCenterMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _glCostCenterMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public GlCostCenterMasterLocalService getWrappedGlCostCenterMasterLocalService() {
        return _glCostCenterMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedGlCostCenterMasterLocalService(
        GlCostCenterMasterLocalService glCostCenterMasterLocalService) {
        _glCostCenterMasterLocalService = glCostCenterMasterLocalService;
    }

    @Override
    public GlCostCenterMasterLocalService getWrappedService() {
        return _glCostCenterMasterLocalService;
    }

    @Override
    public void setWrappedService(
        GlCostCenterMasterLocalService glCostCenterMasterLocalService) {
        _glCostCenterMasterLocalService = glCostCenterMasterLocalService;
    }
}
