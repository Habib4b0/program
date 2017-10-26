package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PsModelLocalService}.
 *
 * @author
 * @see PsModelLocalService
 * @generated
 */
public class PsModelLocalServiceWrapper implements PsModelLocalService,
    ServiceWrapper<PsModelLocalService> {
    private PsModelLocalService _psModelLocalService;

    public PsModelLocalServiceWrapper(PsModelLocalService psModelLocalService) {
        _psModelLocalService = psModelLocalService;
    }

    /**
    * Adds the ps model to the database. Also notifies the appropriate model listeners.
    *
    * @param psModel the ps model
    * @return the ps model that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsModel addPsModel(
        com.stpl.app.model.PsModel psModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psModelLocalService.addPsModel(psModel);
    }

    /**
    * Creates a new ps model with the primary key. Does not add the ps model to the database.
    *
    * @param psModelSid the primary key for the new ps model
    * @return the new ps model
    */
    @Override
    public com.stpl.app.model.PsModel createPsModel(int psModelSid) {
        return _psModelLocalService.createPsModel(psModelSid);
    }

    /**
    * Deletes the ps model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psModelSid the primary key of the ps model
    * @return the ps model that was removed
    * @throws PortalException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsModel deletePsModel(int psModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psModelLocalService.deletePsModel(psModelSid);
    }

    /**
    * Deletes the ps model from the database. Also notifies the appropriate model listeners.
    *
    * @param psModel the ps model
    * @return the ps model that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsModel deletePsModel(
        com.stpl.app.model.PsModel psModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psModelLocalService.deletePsModel(psModel);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _psModelLocalService.dynamicQuery();
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
        return _psModelLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _psModelLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _psModelLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _psModelLocalService.dynamicQueryCount(dynamicQuery);
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
        return _psModelLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.PsModel fetchPsModel(int psModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psModelLocalService.fetchPsModel(psModelSid);
    }

    /**
    * Returns the ps model with the primary key.
    *
    * @param psModelSid the primary key of the ps model
    * @return the ps model
    * @throws PortalException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsModel getPsModel(int psModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psModelLocalService.getPsModel(psModelSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psModelLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ps models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of ps models
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.PsModel> getPsModels(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _psModelLocalService.getPsModels(start, end);
    }

    /**
    * Returns the number of ps models.
    *
    * @return the number of ps models
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPsModelsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psModelLocalService.getPsModelsCount();
    }

    /**
    * Updates the ps model in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param psModel the ps model
    * @return the ps model that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsModel updatePsModel(
        com.stpl.app.model.PsModel psModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psModelLocalService.updatePsModel(psModel);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _psModelLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _psModelLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _psModelLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PsModelLocalService getWrappedPsModelLocalService() {
        return _psModelLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPsModelLocalService(
        PsModelLocalService psModelLocalService) {
        _psModelLocalService = psModelLocalService;
    }

    @Override
    public PsModelLocalService getWrappedService() {
        return _psModelLocalService;
    }

    @Override
    public void setWrappedService(PsModelLocalService psModelLocalService) {
        _psModelLocalService = psModelLocalService;
    }
}
