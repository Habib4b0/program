package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PsDetailsLocalService}.
 *
 * @author
 * @see PsDetailsLocalService
 * @generated
 */
public class PsDetailsLocalServiceWrapper implements PsDetailsLocalService,
    ServiceWrapper<PsDetailsLocalService> {
    private PsDetailsLocalService _psDetailsLocalService;

    public PsDetailsLocalServiceWrapper(
        PsDetailsLocalService psDetailsLocalService) {
        _psDetailsLocalService = psDetailsLocalService;
    }

    /**
    * Adds the ps details to the database. Also notifies the appropriate model listeners.
    *
    * @param psDetails the ps details
    * @return the ps details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsDetails addPsDetails(
        com.stpl.app.model.PsDetails psDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psDetailsLocalService.addPsDetails(psDetails);
    }

    /**
    * Creates a new ps details with the primary key. Does not add the ps details to the database.
    *
    * @param psDetailsSid the primary key for the new ps details
    * @return the new ps details
    */
    @Override
    public com.stpl.app.model.PsDetails createPsDetails(int psDetailsSid) {
        return _psDetailsLocalService.createPsDetails(psDetailsSid);
    }

    /**
    * Deletes the ps details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psDetailsSid the primary key of the ps details
    * @return the ps details that was removed
    * @throws PortalException if a ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsDetails deletePsDetails(int psDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psDetailsLocalService.deletePsDetails(psDetailsSid);
    }

    /**
    * Deletes the ps details from the database. Also notifies the appropriate model listeners.
    *
    * @param psDetails the ps details
    * @return the ps details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsDetails deletePsDetails(
        com.stpl.app.model.PsDetails psDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psDetailsLocalService.deletePsDetails(psDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _psDetailsLocalService.dynamicQuery();
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
        return _psDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _psDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _psDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _psDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _psDetailsLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.PsDetails fetchPsDetails(int psDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psDetailsLocalService.fetchPsDetails(psDetailsSid);
    }

    /**
    * Returns the ps details with the primary key.
    *
    * @param psDetailsSid the primary key of the ps details
    * @return the ps details
    * @throws PortalException if a ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsDetails getPsDetails(int psDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psDetailsLocalService.getPsDetails(psDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ps detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps detailses
    * @param end the upper bound of the range of ps detailses (not inclusive)
    * @return the range of ps detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.PsDetails> getPsDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psDetailsLocalService.getPsDetailses(start, end);
    }

    /**
    * Returns the number of ps detailses.
    *
    * @return the number of ps detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPsDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psDetailsLocalService.getPsDetailsesCount();
    }

    /**
    * Updates the ps details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param psDetails the ps details
    * @return the ps details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsDetails updatePsDetails(
        com.stpl.app.model.PsDetails psDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psDetailsLocalService.updatePsDetails(psDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _psDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _psDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _psDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List getItemAndPricingForPs(int psSystemId) {
        return _psDetailsLocalService.getItemAndPricingForPs(psSystemId);
    }

    @Override
    public java.util.List<com.stpl.app.model.PsDetails> findByPriceScheduleMasterDetails(
        int psModelSid) throws com.stpl.portal.kernel.exception.SystemException {
        return _psDetailsLocalService.findByPriceScheduleMasterDetails(psModelSid);
    }

    @Override
    public java.util.List getPsSearchList(java.lang.String psId,
        java.lang.String psNo, java.lang.String psName, int psStatus,
        int psType, java.lang.String itemId, java.lang.String itemNo,
        java.lang.String itemName,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int end, java.lang.String column, java.lang.String orderBy,
        boolean isCount) {
        return _psDetailsLocalService.getPsSearchList(psId, psNo, psName,
            psStatus, psType, itemId, itemNo, itemName, filterMap, start, end,
            column, orderBy, isCount);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PsDetailsLocalService getWrappedPsDetailsLocalService() {
        return _psDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPsDetailsLocalService(
        PsDetailsLocalService psDetailsLocalService) {
        _psDetailsLocalService = psDetailsLocalService;
    }

    @Override
    public PsDetailsLocalService getWrappedService() {
        return _psDetailsLocalService;
    }

    @Override
    public void setWrappedService(PsDetailsLocalService psDetailsLocalService) {
        _psDetailsLocalService = psDetailsLocalService;
    }
}
