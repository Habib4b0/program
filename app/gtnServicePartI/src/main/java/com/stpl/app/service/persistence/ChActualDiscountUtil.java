package com.stpl.app.service.persistence;

import com.stpl.app.model.ChActualDiscount;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ch actual discount service. This utility wraps {@link ChActualDiscountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChActualDiscountPersistence
 * @see ChActualDiscountPersistenceImpl
 * @generated
 */
public class ChActualDiscountUtil {
    private static ChActualDiscountPersistence _persistence;

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
    public static void clearCache(ChActualDiscount chActualDiscount) {
        getPersistence().clearCache(chActualDiscount);
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
    public static List<ChActualDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ChActualDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ChActualDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ChActualDiscount update(ChActualDiscount chActualDiscount)
        throws SystemException {
        return getPersistence().update(chActualDiscount);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ChActualDiscount update(ChActualDiscount chActualDiscount,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(chActualDiscount, serviceContext);
    }

    /**
    * Caches the ch actual discount in the entity cache if it is enabled.
    *
    * @param chActualDiscount the ch actual discount
    */
    public static void cacheResult(
        com.stpl.app.model.ChActualDiscount chActualDiscount) {
        getPersistence().cacheResult(chActualDiscount);
    }

    /**
    * Caches the ch actual discounts in the entity cache if it is enabled.
    *
    * @param chActualDiscounts the ch actual discounts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ChActualDiscount> chActualDiscounts) {
        getPersistence().cacheResult(chActualDiscounts);
    }

    /**
    * Creates a new ch actual discount with the primary key. Does not add the ch actual discount to the database.
    *
    * @param chActualDiscountPK the primary key for the new ch actual discount
    * @return the new ch actual discount
    */
    public static com.stpl.app.model.ChActualDiscount create(
        ChActualDiscountPK chActualDiscountPK) {
        return getPersistence().create(chActualDiscountPK);
    }

    /**
    * Removes the ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chActualDiscountPK the primary key of the ch actual discount
    * @return the ch actual discount that was removed
    * @throws com.stpl.app.NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChActualDiscount remove(
        ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.app.NoSuchChActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(chActualDiscountPK);
    }

    public static com.stpl.app.model.ChActualDiscount updateImpl(
        com.stpl.app.model.ChActualDiscount chActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(chActualDiscount);
    }

    /**
    * Returns the ch actual discount with the primary key or throws a {@link com.stpl.app.NoSuchChActualDiscountException} if it could not be found.
    *
    * @param chActualDiscountPK the primary key of the ch actual discount
    * @return the ch actual discount
    * @throws com.stpl.app.NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChActualDiscount findByPrimaryKey(
        ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.app.NoSuchChActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(chActualDiscountPK);
    }

    /**
    * Returns the ch actual discount with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param chActualDiscountPK the primary key of the ch actual discount
    * @return the ch actual discount, or <code>null</code> if a ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChActualDiscount fetchByPrimaryKey(
        ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(chActualDiscountPK);
    }

    /**
    * Returns all the ch actual discounts.
    *
    * @return the ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChActualDiscount> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ch actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch actual discounts
    * @param end the upper bound of the range of ch actual discounts (not inclusive)
    * @return the range of ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChActualDiscount> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ch actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch actual discounts
    * @param end the upper bound of the range of ch actual discounts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChActualDiscount> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ch actual discounts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ch actual discounts.
    *
    * @return the number of ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ChActualDiscountPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ChActualDiscountPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ChActualDiscountPersistence.class.getName());

            ReferenceRegistry.registerReference(ChActualDiscountUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ChActualDiscountPersistence persistence) {
    }
}
