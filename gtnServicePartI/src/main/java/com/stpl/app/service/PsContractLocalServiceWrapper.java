package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PsContractLocalService}.
 *
 * @author
 * @see PsContractLocalService
 * @generated
 */
public class PsContractLocalServiceWrapper implements PsContractLocalService,
    ServiceWrapper<PsContractLocalService> {
    private PsContractLocalService _psContractLocalService;

    public PsContractLocalServiceWrapper(
        PsContractLocalService psContractLocalService) {
        _psContractLocalService = psContractLocalService;
    }

    /**
    * Adds the ps contract to the database. Also notifies the appropriate model listeners.
    *
    * @param psContract the ps contract
    * @return the ps contract that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsContract addPsContract(
        com.stpl.app.model.PsContract psContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractLocalService.addPsContract(psContract);
    }

    /**
    * Creates a new ps contract with the primary key. Does not add the ps contract to the database.
    *
    * @param psContractSid the primary key for the new ps contract
    * @return the new ps contract
    */
    @Override
    public com.stpl.app.model.PsContract createPsContract(int psContractSid) {
        return _psContractLocalService.createPsContract(psContractSid);
    }

    /**
    * Deletes the ps contract with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psContractSid the primary key of the ps contract
    * @return the ps contract that was removed
    * @throws PortalException if a ps contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsContract deletePsContract(int psContractSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psContractLocalService.deletePsContract(psContractSid);
    }

    /**
    * Deletes the ps contract from the database. Also notifies the appropriate model listeners.
    *
    * @param psContract the ps contract
    * @return the ps contract that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsContract deletePsContract(
        com.stpl.app.model.PsContract psContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractLocalService.deletePsContract(psContract);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _psContractLocalService.dynamicQuery();
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
        return _psContractLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _psContractLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _psContractLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _psContractLocalService.dynamicQueryCount(dynamicQuery);
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
        return _psContractLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.PsContract fetchPsContract(int psContractSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractLocalService.fetchPsContract(psContractSid);
    }

    /**
    * Returns the ps contract with the primary key.
    *
    * @param psContractSid the primary key of the ps contract
    * @return the ps contract
    * @throws PortalException if a ps contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsContract getPsContract(int psContractSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psContractLocalService.getPsContract(psContractSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psContractLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ps contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps contracts
    * @param end the upper bound of the range of ps contracts (not inclusive)
    * @return the range of ps contracts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.PsContract> getPsContracts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractLocalService.getPsContracts(start, end);
    }

    /**
    * Returns the number of ps contracts.
    *
    * @return the number of ps contracts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPsContractsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractLocalService.getPsContractsCount();
    }

    /**
    * Updates the ps contract in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param psContract the ps contract
    * @return the ps contract that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsContract updatePsContract(
        com.stpl.app.model.PsContract psContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractLocalService.updatePsContract(psContract);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _psContractLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _psContractLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _psContractLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PsContractLocalService getWrappedPsContractLocalService() {
        return _psContractLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPsContractLocalService(
        PsContractLocalService psContractLocalService) {
        _psContractLocalService = psContractLocalService;
    }

    @Override
    public PsContractLocalService getWrappedService() {
        return _psContractLocalService;
    }

    @Override
    public void setWrappedService(PsContractLocalService psContractLocalService) {
        _psContractLocalService = psContractLocalService;
    }
}
