package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TransactionModuleDetailsLocalService}.
 *
 * @author
 * @see TransactionModuleDetailsLocalService
 * @generated
 */
public class TransactionModuleDetailsLocalServiceWrapper
    implements TransactionModuleDetailsLocalService,
        ServiceWrapper<TransactionModuleDetailsLocalService> {
    private TransactionModuleDetailsLocalService _transactionModuleDetailsLocalService;

    public TransactionModuleDetailsLocalServiceWrapper(
        TransactionModuleDetailsLocalService transactionModuleDetailsLocalService) {
        _transactionModuleDetailsLocalService = transactionModuleDetailsLocalService;
    }

    /**
    * Adds the transaction module details to the database. Also notifies the appropriate model listeners.
    *
    * @param transactionModuleDetails the transaction module details
    * @return the transaction module details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.TransactionModuleDetails addTransactionModuleDetails(
        com.stpl.app.model.TransactionModuleDetails transactionModuleDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _transactionModuleDetailsLocalService.addTransactionModuleDetails(transactionModuleDetails);
    }

    /**
    * Creates a new transaction module details with the primary key. Does not add the transaction module details to the database.
    *
    * @param transactionModuleDetailsSid the primary key for the new transaction module details
    * @return the new transaction module details
    */
    @Override
    public com.stpl.app.model.TransactionModuleDetails createTransactionModuleDetails(
        int transactionModuleDetailsSid) {
        return _transactionModuleDetailsLocalService.createTransactionModuleDetails(transactionModuleDetailsSid);
    }

    /**
    * Deletes the transaction module details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param transactionModuleDetailsSid the primary key of the transaction module details
    * @return the transaction module details that was removed
    * @throws PortalException if a transaction module details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.TransactionModuleDetails deleteTransactionModuleDetails(
        int transactionModuleDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _transactionModuleDetailsLocalService.deleteTransactionModuleDetails(transactionModuleDetailsSid);
    }

    /**
    * Deletes the transaction module details from the database. Also notifies the appropriate model listeners.
    *
    * @param transactionModuleDetails the transaction module details
    * @return the transaction module details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.TransactionModuleDetails deleteTransactionModuleDetails(
        com.stpl.app.model.TransactionModuleDetails transactionModuleDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _transactionModuleDetailsLocalService.deleteTransactionModuleDetails(transactionModuleDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _transactionModuleDetailsLocalService.dynamicQuery();
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
        return _transactionModuleDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _transactionModuleDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _transactionModuleDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _transactionModuleDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _transactionModuleDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.TransactionModuleDetails fetchTransactionModuleDetails(
        int transactionModuleDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _transactionModuleDetailsLocalService.fetchTransactionModuleDetails(transactionModuleDetailsSid);
    }

    /**
    * Returns the transaction module details with the primary key.
    *
    * @param transactionModuleDetailsSid the primary key of the transaction module details
    * @return the transaction module details
    * @throws PortalException if a transaction module details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.TransactionModuleDetails getTransactionModuleDetails(
        int transactionModuleDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _transactionModuleDetailsLocalService.getTransactionModuleDetails(transactionModuleDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _transactionModuleDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the transaction module detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of transaction module detailses
    * @param end the upper bound of the range of transaction module detailses (not inclusive)
    * @return the range of transaction module detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.TransactionModuleDetails> getTransactionModuleDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _transactionModuleDetailsLocalService.getTransactionModuleDetailses(start,
            end);
    }

    /**
    * Returns the number of transaction module detailses.
    *
    * @return the number of transaction module detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getTransactionModuleDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _transactionModuleDetailsLocalService.getTransactionModuleDetailsesCount();
    }

    /**
    * Updates the transaction module details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param transactionModuleDetails the transaction module details
    * @return the transaction module details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.TransactionModuleDetails updateTransactionModuleDetails(
        com.stpl.app.model.TransactionModuleDetails transactionModuleDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _transactionModuleDetailsLocalService.updateTransactionModuleDetails(transactionModuleDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _transactionModuleDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _transactionModuleDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _transactionModuleDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public TransactionModuleDetailsLocalService getWrappedTransactionModuleDetailsLocalService() {
        return _transactionModuleDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedTransactionModuleDetailsLocalService(
        TransactionModuleDetailsLocalService transactionModuleDetailsLocalService) {
        _transactionModuleDetailsLocalService = transactionModuleDetailsLocalService;
    }

    @Override
    public TransactionModuleDetailsLocalService getWrappedService() {
        return _transactionModuleDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        TransactionModuleDetailsLocalService transactionModuleDetailsLocalService) {
        _transactionModuleDetailsLocalService = transactionModuleDetailsLocalService;
    }
}
