package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldForecastSales;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld forecast sales service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldForecastSalesPersistenceImpl
 * @see IvldForecastSalesUtil
 * @generated
 */
public interface IvldForecastSalesPersistence extends BasePersistence<IvldForecastSales> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldForecastSalesUtil} to access the ivld forecast sales persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld forecast sales in the entity cache if it is enabled.
    *
    * @param ivldForecastSales the ivld forecast sales
    */
    public void cacheResult(
        com.stpl.app.model.IvldForecastSales ivldForecastSales);

    /**
    * Caches the ivld forecast saleses in the entity cache if it is enabled.
    *
    * @param ivldForecastSaleses the ivld forecast saleses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldForecastSales> ivldForecastSaleses);

    /**
    * Creates a new ivld forecast sales with the primary key. Does not add the ivld forecast sales to the database.
    *
    * @param ivldForecastSalesSid the primary key for the new ivld forecast sales
    * @return the new ivld forecast sales
    */
    public com.stpl.app.model.IvldForecastSales create(int ivldForecastSalesSid);

    /**
    * Removes the ivld forecast sales with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldForecastSalesSid the primary key of the ivld forecast sales
    * @return the ivld forecast sales that was removed
    * @throws com.stpl.app.NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldForecastSales remove(int ivldForecastSalesSid)
        throws com.stpl.app.NoSuchIvldForecastSalesException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldForecastSales updateImpl(
        com.stpl.app.model.IvldForecastSales ivldForecastSales)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld forecast sales with the primary key or throws a {@link com.stpl.app.NoSuchIvldForecastSalesException} if it could not be found.
    *
    * @param ivldForecastSalesSid the primary key of the ivld forecast sales
    * @return the ivld forecast sales
    * @throws com.stpl.app.NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldForecastSales findByPrimaryKey(
        int ivldForecastSalesSid)
        throws com.stpl.app.NoSuchIvldForecastSalesException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld forecast sales with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldForecastSalesSid the primary key of the ivld forecast sales
    * @return the ivld forecast sales, or <code>null</code> if a ivld forecast sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldForecastSales fetchByPrimaryKey(
        int ivldForecastSalesSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld forecast saleses.
    *
    * @return the ivld forecast saleses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldForecastSales> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld forecast saleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld forecast saleses
    * @param end the upper bound of the range of ivld forecast saleses (not inclusive)
    * @return the range of ivld forecast saleses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldForecastSales> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld forecast saleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld forecast saleses
    * @param end the upper bound of the range of ivld forecast saleses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld forecast saleses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldForecastSales> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld forecast saleses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld forecast saleses.
    *
    * @return the number of ivld forecast saleses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
