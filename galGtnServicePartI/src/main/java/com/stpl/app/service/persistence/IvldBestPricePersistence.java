package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldBestPrice;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld best price service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldBestPricePersistenceImpl
 * @see IvldBestPriceUtil
 * @generated
 */
public interface IvldBestPricePersistence extends BasePersistence<IvldBestPrice> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldBestPriceUtil} to access the ivld best price persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld best price in the entity cache if it is enabled.
    *
    * @param ivldBestPrice the ivld best price
    */
    public void cacheResult(com.stpl.app.model.IvldBestPrice ivldBestPrice);

    /**
    * Caches the ivld best prices in the entity cache if it is enabled.
    *
    * @param ivldBestPrices the ivld best prices
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldBestPrice> ivldBestPrices);

    /**
    * Creates a new ivld best price with the primary key. Does not add the ivld best price to the database.
    *
    * @param ivldBestPriceSid the primary key for the new ivld best price
    * @return the new ivld best price
    */
    public com.stpl.app.model.IvldBestPrice create(int ivldBestPriceSid);

    /**
    * Removes the ivld best price with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldBestPriceSid the primary key of the ivld best price
    * @return the ivld best price that was removed
    * @throws com.stpl.app.NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldBestPrice remove(int ivldBestPriceSid)
        throws com.stpl.app.NoSuchIvldBestPriceException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldBestPrice updateImpl(
        com.stpl.app.model.IvldBestPrice ivldBestPrice)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld best price with the primary key or throws a {@link com.stpl.app.NoSuchIvldBestPriceException} if it could not be found.
    *
    * @param ivldBestPriceSid the primary key of the ivld best price
    * @return the ivld best price
    * @throws com.stpl.app.NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldBestPrice findByPrimaryKey(
        int ivldBestPriceSid)
        throws com.stpl.app.NoSuchIvldBestPriceException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld best price with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldBestPriceSid the primary key of the ivld best price
    * @return the ivld best price, or <code>null</code> if a ivld best price with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldBestPrice fetchByPrimaryKey(
        int ivldBestPriceSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld best prices.
    *
    * @return the ivld best prices
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldBestPrice> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld best prices.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld best prices
    * @param end the upper bound of the range of ivld best prices (not inclusive)
    * @return the range of ivld best prices
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldBestPrice> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld best prices.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld best prices
    * @param end the upper bound of the range of ivld best prices (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld best prices
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldBestPrice> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld best prices from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld best prices.
    *
    * @return the number of ivld best prices
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
