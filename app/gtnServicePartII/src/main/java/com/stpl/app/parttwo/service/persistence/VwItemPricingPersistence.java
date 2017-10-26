package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwItemPricing;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw item pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemPricingPersistenceImpl
 * @see VwItemPricingUtil
 * @generated
 */
public interface VwItemPricingPersistence extends BasePersistence<VwItemPricing> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwItemPricingUtil} to access the vw item pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw item pricing in the entity cache if it is enabled.
    *
    * @param vwItemPricing the vw item pricing
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.VwItemPricing vwItemPricing);

    /**
    * Caches the vw item pricings in the entity cache if it is enabled.
    *
    * @param vwItemPricings the vw item pricings
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwItemPricing> vwItemPricings);

    /**
    * Creates a new vw item pricing with the primary key. Does not add the vw item pricing to the database.
    *
    * @param itemPricingSid the primary key for the new vw item pricing
    * @return the new vw item pricing
    */
    public com.stpl.app.parttwo.model.VwItemPricing create(int itemPricingSid);

    /**
    * Removes the vw item pricing with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricingSid the primary key of the vw item pricing
    * @return the vw item pricing that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwItemPricing remove(int itemPricingSid)
        throws com.stpl.app.parttwo.NoSuchVwItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.VwItemPricing updateImpl(
        com.stpl.app.parttwo.model.VwItemPricing vwItemPricing)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw item pricing with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwItemPricingException} if it could not be found.
    *
    * @param itemPricingSid the primary key of the vw item pricing
    * @return the vw item pricing
    * @throws com.stpl.app.parttwo.NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwItemPricing findByPrimaryKey(
        int itemPricingSid)
        throws com.stpl.app.parttwo.NoSuchVwItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw item pricing with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemPricingSid the primary key of the vw item pricing
    * @return the vw item pricing, or <code>null</code> if a vw item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwItemPricing fetchByPrimaryKey(
        int itemPricingSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw item pricings.
    *
    * @return the vw item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwItemPricing> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw item pricings.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw item pricings
    * @param end the upper bound of the range of vw item pricings (not inclusive)
    * @return the range of vw item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwItemPricing> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw item pricings.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw item pricings
    * @param end the upper bound of the range of vw item pricings (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwItemPricing> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw item pricings from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw item pricings.
    *
    * @return the number of vw item pricings
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
