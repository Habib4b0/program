package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldItemPricing;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld item pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemPricingPersistenceImpl
 * @see IvldItemPricingUtil
 * @generated
 */
public interface IvldItemPricingPersistence extends BasePersistence<IvldItemPricing> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldItemPricingUtil} to access the ivld item pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld item pricing in the entity cache if it is enabled.
    *
    * @param ivldItemPricing the ivld item pricing
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.IvldItemPricing ivldItemPricing);

    /**
    * Caches the ivld item pricings in the entity cache if it is enabled.
    *
    * @param ivldItemPricings the ivld item pricings
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldItemPricing> ivldItemPricings);

    /**
    * Creates a new ivld item pricing with the primary key. Does not add the ivld item pricing to the database.
    *
    * @param ivldItemPricingSid the primary key for the new ivld item pricing
    * @return the new ivld item pricing
    */
    public com.stpl.app.parttwo.model.IvldItemPricing create(
        int ivldItemPricingSid);

    /**
    * Removes the ivld item pricing with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemPricingSid the primary key of the ivld item pricing
    * @return the ivld item pricing that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldItemPricing remove(
        int ivldItemPricingSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.IvldItemPricing updateImpl(
        com.stpl.app.parttwo.model.IvldItemPricing ivldItemPricing)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld item pricing with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldItemPricingException} if it could not be found.
    *
    * @param ivldItemPricingSid the primary key of the ivld item pricing
    * @return the ivld item pricing
    * @throws com.stpl.app.parttwo.NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldItemPricing findByPrimaryKey(
        int ivldItemPricingSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld item pricing with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldItemPricingSid the primary key of the ivld item pricing
    * @return the ivld item pricing, or <code>null</code> if a ivld item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldItemPricing fetchByPrimaryKey(
        int ivldItemPricingSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld item pricings.
    *
    * @return the ivld item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldItemPricing> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld item pricings.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item pricings
    * @param end the upper bound of the range of ivld item pricings (not inclusive)
    * @return the range of ivld item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldItemPricing> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld item pricings.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item pricings
    * @param end the upper bound of the range of ivld item pricings (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldItemPricing> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld item pricings from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld item pricings.
    *
    * @return the number of ivld item pricings
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
