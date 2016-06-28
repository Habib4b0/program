package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CustomerGtsActual;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the customer gts actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomerGtsActualPersistenceImpl
 * @see CustomerGtsActualUtil
 * @generated
 */
public interface CustomerGtsActualPersistence extends BasePersistence<CustomerGtsActual> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CustomerGtsActualUtil} to access the customer gts actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the customer gts actual in the entity cache if it is enabled.
    *
    * @param customerGtsActual the customer gts actual
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.CustomerGtsActual customerGtsActual);

    /**
    * Caches the customer gts actuals in the entity cache if it is enabled.
    *
    * @param customerGtsActuals the customer gts actuals
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CustomerGtsActual> customerGtsActuals);

    /**
    * Creates a new customer gts actual with the primary key. Does not add the customer gts actual to the database.
    *
    * @param customerGtsActualSid the primary key for the new customer gts actual
    * @return the new customer gts actual
    */
    public com.stpl.app.parttwo.model.CustomerGtsActual create(
        int customerGtsActualSid);

    /**
    * Removes the customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customerGtsActualSid the primary key of the customer gts actual
    * @return the customer gts actual that was removed
    * @throws com.stpl.app.parttwo.NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CustomerGtsActual remove(
        int customerGtsActualSid)
        throws com.stpl.app.parttwo.NoSuchCustomerGtsActualException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.CustomerGtsActual updateImpl(
        com.stpl.app.parttwo.model.CustomerGtsActual customerGtsActual)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the customer gts actual with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCustomerGtsActualException} if it could not be found.
    *
    * @param customerGtsActualSid the primary key of the customer gts actual
    * @return the customer gts actual
    * @throws com.stpl.app.parttwo.NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CustomerGtsActual findByPrimaryKey(
        int customerGtsActualSid)
        throws com.stpl.app.parttwo.NoSuchCustomerGtsActualException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the customer gts actual with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param customerGtsActualSid the primary key of the customer gts actual
    * @return the customer gts actual, or <code>null</code> if a customer gts actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CustomerGtsActual fetchByPrimaryKey(
        int customerGtsActualSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the customer gts actuals.
    *
    * @return the customer gts actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CustomerGtsActual> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the customer gts actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of customer gts actuals
    * @param end the upper bound of the range of customer gts actuals (not inclusive)
    * @return the range of customer gts actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CustomerGtsActual> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the customer gts actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of customer gts actuals
    * @param end the upper bound of the range of customer gts actuals (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of customer gts actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CustomerGtsActual> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the customer gts actuals from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of customer gts actuals.
    *
    * @return the number of customer gts actuals
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
