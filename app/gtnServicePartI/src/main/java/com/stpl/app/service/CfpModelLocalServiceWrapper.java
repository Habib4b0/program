package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CfpModelLocalService}.
 *
 * @author
 * @see CfpModelLocalService
 * @generated
 */
public class CfpModelLocalServiceWrapper implements CfpModelLocalService,
    ServiceWrapper<CfpModelLocalService> {
    private CfpModelLocalService _cfpModelLocalService;

    public CfpModelLocalServiceWrapper(
        CfpModelLocalService cfpModelLocalService) {
        _cfpModelLocalService = cfpModelLocalService;
    }

    /**
    * Adds the cfp model to the database. Also notifies the appropriate model listeners.
    *
    * @param cfpModel the cfp model
    * @return the cfp model that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpModel addCfpModel(
        com.stpl.app.model.CfpModel cfpModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpModelLocalService.addCfpModel(cfpModel);
    }

    /**
    * Creates a new cfp model with the primary key. Does not add the cfp model to the database.
    *
    * @param cfpModelSid the primary key for the new cfp model
    * @return the new cfp model
    */
    @Override
    public com.stpl.app.model.CfpModel createCfpModel(int cfpModelSid) {
        return _cfpModelLocalService.createCfpModel(cfpModelSid);
    }

    /**
    * Deletes the cfp model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpModelSid the primary key of the cfp model
    * @return the cfp model that was removed
    * @throws PortalException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpModel deleteCfpModel(int cfpModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cfpModelLocalService.deleteCfpModel(cfpModelSid);
    }

    /**
    * Deletes the cfp model from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpModel the cfp model
    * @return the cfp model that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpModel deleteCfpModel(
        com.stpl.app.model.CfpModel cfpModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpModelLocalService.deleteCfpModel(cfpModel);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cfpModelLocalService.dynamicQuery();
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
        return _cfpModelLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cfpModelLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cfpModelLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _cfpModelLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cfpModelLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.CfpModel fetchCfpModel(int cfpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpModelLocalService.fetchCfpModel(cfpModelSid);
    }

    /**
    * Returns the cfp model with the primary key.
    *
    * @param cfpModelSid the primary key of the cfp model
    * @return the cfp model
    * @throws PortalException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpModel getCfpModel(int cfpModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cfpModelLocalService.getCfpModel(cfpModelSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cfpModelLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cfp models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of cfp models
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CfpModel> getCfpModels(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpModelLocalService.getCfpModels(start, end);
    }

    /**
    * Returns the number of cfp models.
    *
    * @return the number of cfp models
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCfpModelsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpModelLocalService.getCfpModelsCount();
    }

    /**
    * Updates the cfp model in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cfpModel the cfp model
    * @return the cfp model that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CfpModel updateCfpModel(
        com.stpl.app.model.CfpModel cfpModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cfpModelLocalService.updateCfpModel(cfpModel);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cfpModelLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cfpModelLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cfpModelLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List findCfpModelV1(
        java.util.Map<java.lang.Object, java.lang.Object> cfp,
        java.lang.String orderByColumn, java.lang.Boolean sortOrder,
        java.lang.Object index, java.lang.Object next,
        java.util.Map<java.lang.Object, java.lang.Object> parameters,
        java.lang.String operation, java.lang.Object future,
        java.lang.Object future1) {
        return _cfpModelLocalService.findCfpModelV1(cfp, orderByColumn,
            sortOrder, index, next, parameters, operation, future, future1);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CfpModelLocalService getWrappedCfpModelLocalService() {
        return _cfpModelLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCfpModelLocalService(
        CfpModelLocalService cfpModelLocalService) {
        _cfpModelLocalService = cfpModelLocalService;
    }

    @Override
    public CfpModelLocalService getWrappedService() {
        return _cfpModelLocalService;
    }

    @Override
    public void setWrappedService(CfpModelLocalService cfpModelLocalService) {
        _cfpModelLocalService = cfpModelLocalService;
    }
}
