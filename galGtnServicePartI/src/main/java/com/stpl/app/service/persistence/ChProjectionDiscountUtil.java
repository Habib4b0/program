package com.stpl.app.service.persistence;

import com.stpl.app.model.ChProjectionDiscount;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ch projection discount service. This utility wraps {@link ChProjectionDiscountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChProjectionDiscountPersistence
 * @see ChProjectionDiscountPersistenceImpl
 * @generated
 */
public class ChProjectionDiscountUtil {
    private static ChProjectionDiscountPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#clearCache(com.stpl.portal.model.BaseModel)
     */
    public static void clearCache(ChProjectionDiscount chProjectionDiscount) {
        getPersistence().clearCache(chProjectionDiscount);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ChProjectionDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ChProjectionDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ChProjectionDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ChProjectionDiscount update(
        ChProjectionDiscount chProjectionDiscount) throws SystemException {
        return getPersistence().update(chProjectionDiscount);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ChProjectionDiscount update(
        ChProjectionDiscount chProjectionDiscount, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(chProjectionDiscount, serviceContext);
    }

    /**
    * Caches the ch projection discount in the entity cache if it is enabled.
    *
    * @param chProjectionDiscount the ch projection discount
    */
    public static void cacheResult(
        com.stpl.app.model.ChProjectionDiscount chProjectionDiscount) {
        getPersistence().cacheResult(chProjectionDiscount);
    }

    /**
    * Caches the ch projection discounts in the entity cache if it is enabled.
    *
    * @param chProjectionDiscounts the ch projection discounts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ChProjectionDiscount> chProjectionDiscounts) {
        getPersistence().cacheResult(chProjectionDiscounts);
    }

    /**
    * Creates a new ch projection discount with the primary key. Does not add the ch projection discount to the database.
    *
    * @param chProjectionDiscountPK the primary key for the new ch projection discount
    * @return the new ch projection discount
    */
    public static com.stpl.app.model.ChProjectionDiscount create(
        ChProjectionDiscountPK chProjectionDiscountPK) {
        return getPersistence().create(chProjectionDiscountPK);
    }

    /**
    * Removes the ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chProjectionDiscountPK the primary key of the ch projection discount
    * @return the ch projection discount that was removed
    * @throws com.stpl.app.NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChProjectionDiscount remove(
        ChProjectionDiscountPK chProjectionDiscountPK)
        throws com.stpl.app.NoSuchChProjectionDiscountException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(chProjectionDiscountPK);
    }

    public static com.stpl.app.model.ChProjectionDiscount updateImpl(
        com.stpl.app.model.ChProjectionDiscount chProjectionDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(chProjectionDiscount);
    }

    /**
    * Returns the ch projection discount with the primary key or throws a {@link com.stpl.app.NoSuchChProjectionDiscountException} if it could not be found.
    *
    * @param chProjectionDiscountPK the primary key of the ch projection discount
    * @return the ch projection discount
    * @throws com.stpl.app.NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChProjectionDiscount findByPrimaryKey(
        ChProjectionDiscountPK chProjectionDiscountPK)
        throws com.stpl.app.NoSuchChProjectionDiscountException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(chProjectionDiscountPK);
    }

    /**
    * Returns the ch projection discount with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param chProjectionDiscountPK the primary key of the ch projection discount
    * @return the ch projection discount, or <code>null</code> if a ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChProjectionDiscount fetchByPrimaryKey(
        ChProjectionDiscountPK chProjectionDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(chProjectionDiscountPK);
    }

    /**
    * Returns all the ch projection discounts.
    *
    * @return the ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChProjectionDiscount> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ch projection discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch projection discounts
    * @param end the upper bound of the range of ch projection discounts (not inclusive)
    * @return the range of ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChProjectionDiscount> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ch projection discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch projection discounts
    * @param end the upper bound of the range of ch projection discounts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChProjectionDiscount> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ch projection discounts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ch projection discounts.
    *
    * @return the number of ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ChProjectionDiscountPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ChProjectionDiscountPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ChProjectionDiscountPersistence.class.getName());

            ReferenceRegistry.registerReference(ChProjectionDiscountUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ChProjectionDiscountPersistence persistence) {
    }
}
