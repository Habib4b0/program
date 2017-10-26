package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GlBalanceMasterLocalService}.
 *
 * @author
 * @see GlBalanceMasterLocalService
 * @generated
 */
public class GlBalanceMasterLocalServiceWrapper
    implements GlBalanceMasterLocalService,
        ServiceWrapper<GlBalanceMasterLocalService> {
    private GlBalanceMasterLocalService _glBalanceMasterLocalService;

    public GlBalanceMasterLocalServiceWrapper(
        GlBalanceMasterLocalService glBalanceMasterLocalService) {
        _glBalanceMasterLocalService = glBalanceMasterLocalService;
    }

    /**
    * Adds the gl balance master to the database. Also notifies the appropriate model listeners.
    *
    * @param glBalanceMaster the gl balance master
    * @return the gl balance master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GlBalanceMaster addGlBalanceMaster(
        com.stpl.app.model.GlBalanceMaster glBalanceMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glBalanceMasterLocalService.addGlBalanceMaster(glBalanceMaster);
    }

    /**
    * Creates a new gl balance master with the primary key. Does not add the gl balance master to the database.
    *
    * @param glBalanceMasterSid the primary key for the new gl balance master
    * @return the new gl balance master
    */
    @Override
    public com.stpl.app.model.GlBalanceMaster createGlBalanceMaster(
        int glBalanceMasterSid) {
        return _glBalanceMasterLocalService.createGlBalanceMaster(glBalanceMasterSid);
    }

    /**
    * Deletes the gl balance master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param glBalanceMasterSid the primary key of the gl balance master
    * @return the gl balance master that was removed
    * @throws PortalException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GlBalanceMaster deleteGlBalanceMaster(
        int glBalanceMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _glBalanceMasterLocalService.deleteGlBalanceMaster(glBalanceMasterSid);
    }

    /**
    * Deletes the gl balance master from the database. Also notifies the appropriate model listeners.
    *
    * @param glBalanceMaster the gl balance master
    * @return the gl balance master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GlBalanceMaster deleteGlBalanceMaster(
        com.stpl.app.model.GlBalanceMaster glBalanceMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glBalanceMasterLocalService.deleteGlBalanceMaster(glBalanceMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _glBalanceMasterLocalService.dynamicQuery();
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
        return _glBalanceMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _glBalanceMasterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _glBalanceMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _glBalanceMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _glBalanceMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.GlBalanceMaster fetchGlBalanceMaster(
        int glBalanceMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glBalanceMasterLocalService.fetchGlBalanceMaster(glBalanceMasterSid);
    }

    /**
    * Returns the gl balance master with the primary key.
    *
    * @param glBalanceMasterSid the primary key of the gl balance master
    * @return the gl balance master
    * @throws PortalException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GlBalanceMaster getGlBalanceMaster(
        int glBalanceMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _glBalanceMasterLocalService.getGlBalanceMaster(glBalanceMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _glBalanceMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the gl balance masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of gl balance masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.GlBalanceMaster> getGlBalanceMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glBalanceMasterLocalService.getGlBalanceMasters(start, end);
    }

    /**
    * Returns the number of gl balance masters.
    *
    * @return the number of gl balance masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getGlBalanceMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glBalanceMasterLocalService.getGlBalanceMastersCount();
    }

    /**
    * Updates the gl balance master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param glBalanceMaster the gl balance master
    * @return the gl balance master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.GlBalanceMaster updateGlBalanceMaster(
        com.stpl.app.model.GlBalanceMaster glBalanceMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _glBalanceMasterLocalService.updateGlBalanceMaster(glBalanceMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _glBalanceMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _glBalanceMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _glBalanceMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public GlBalanceMasterLocalService getWrappedGlBalanceMasterLocalService() {
        return _glBalanceMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedGlBalanceMasterLocalService(
        GlBalanceMasterLocalService glBalanceMasterLocalService) {
        _glBalanceMasterLocalService = glBalanceMasterLocalService;
    }

    @Override
    public GlBalanceMasterLocalService getWrappedService() {
        return _glBalanceMasterLocalService;
    }

    @Override
    public void setWrappedService(
        GlBalanceMasterLocalService glBalanceMasterLocalService) {
        _glBalanceMasterLocalService = glBalanceMasterLocalService;
    }
}
