package com.stpl.app.service.persistence;

import com.stpl.app.model.ChActualSales;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ch actual sales service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChActualSalesPersistenceImpl
 * @see ChActualSalesUtil
 * @generated
 */
public interface ChActualSalesPersistence extends BasePersistence<ChActualSales> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ChActualSalesUtil} to access the ch actual sales persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ch actual sales in the entity cache if it is enabled.
    *
    * @param chActualSales the ch actual sales
    */
    public void cacheResult(com.stpl.app.model.ChActualSales chActualSales);

    /**
    * Caches the ch actual saleses in the entity cache if it is enabled.
    *
    * @param chActualSaleses the ch actual saleses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ChActualSales> chActualSaleses);

    /**
    * Creates a new ch actual sales with the primary key. Does not add the ch actual sales to the database.
    *
    * @param chActualSalesPK the primary key for the new ch actual sales
    * @return the new ch actual sales
    */
    public com.stpl.app.model.ChActualSales create(
        ChActualSalesPK chActualSalesPK);

    /**
    * Removes the ch actual sales with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chActualSalesPK the primary key of the ch actual sales
    * @return the ch actual sales that was removed
    * @throws com.stpl.app.NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChActualSales remove(
        ChActualSalesPK chActualSalesPK)
        throws com.stpl.app.NoSuchChActualSalesException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ChActualSales updateImpl(
        com.stpl.app.model.ChActualSales chActualSales)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch actual sales with the primary key or throws a {@link com.stpl.app.NoSuchChActualSalesException} if it could not be found.
    *
    * @param chActualSalesPK the primary key of the ch actual sales
    * @return the ch actual sales
    * @throws com.stpl.app.NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChActualSales findByPrimaryKey(
        ChActualSalesPK chActualSalesPK)
        throws com.stpl.app.NoSuchChActualSalesException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch actual sales with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param chActualSalesPK the primary key of the ch actual sales
    * @return the ch actual sales, or <code>null</code> if a ch actual sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChActualSales fetchByPrimaryKey(
        ChActualSalesPK chActualSalesPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ch actual saleses.
    *
    * @return the ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChActualSales> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ch actual saleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch actual saleses
    * @param end the upper bound of the range of ch actual saleses (not inclusive)
    * @return the range of ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChActualSales> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ch actual saleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch actual saleses
    * @param end the upper bound of the range of ch actual saleses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChActualSales> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ch actual saleses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ch actual saleses.
    *
    * @return the number of ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
