package com.stpl.app.service.persistence;

import com.stpl.app.model.StChActualDiscount;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st ch actual discount service. This utility wraps {@link StChActualDiscountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChActualDiscountPersistence
 * @see StChActualDiscountPersistenceImpl
 * @generated
 */
public class StChActualDiscountUtil {
    private static StChActualDiscountPersistence _persistence;

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
    public static void clearCache(StChActualDiscount stChActualDiscount) {
        getPersistence().clearCache(stChActualDiscount);
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
    public static List<StChActualDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StChActualDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StChActualDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StChActualDiscount update(
        StChActualDiscount stChActualDiscount) throws SystemException {
        return getPersistence().update(stChActualDiscount);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StChActualDiscount update(
        StChActualDiscount stChActualDiscount, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(stChActualDiscount, serviceContext);
    }

    /**
    * Caches the st ch actual discount in the entity cache if it is enabled.
    *
    * @param stChActualDiscount the st ch actual discount
    */
    public static void cacheResult(
        com.stpl.app.model.StChActualDiscount stChActualDiscount) {
        getPersistence().cacheResult(stChActualDiscount);
    }

    /**
    * Caches the st ch actual discounts in the entity cache if it is enabled.
    *
    * @param stChActualDiscounts the st ch actual discounts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StChActualDiscount> stChActualDiscounts) {
        getPersistence().cacheResult(stChActualDiscounts);
    }

    /**
    * Creates a new st ch actual discount with the primary key. Does not add the st ch actual discount to the database.
    *
    * @param stChActualDiscountPK the primary key for the new st ch actual discount
    * @return the new st ch actual discount
    */
    public static com.stpl.app.model.StChActualDiscount create(
        StChActualDiscountPK stChActualDiscountPK) {
        return getPersistence().create(stChActualDiscountPK);
    }

    /**
    * Removes the st ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stChActualDiscountPK the primary key of the st ch actual discount
    * @return the st ch actual discount that was removed
    * @throws com.stpl.app.NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChActualDiscount remove(
        StChActualDiscountPK stChActualDiscountPK)
        throws com.stpl.app.NoSuchStChActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stChActualDiscountPK);
    }

    public static com.stpl.app.model.StChActualDiscount updateImpl(
        com.stpl.app.model.StChActualDiscount stChActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stChActualDiscount);
    }

    /**
    * Returns the st ch actual discount with the primary key or throws a {@link com.stpl.app.NoSuchStChActualDiscountException} if it could not be found.
    *
    * @param stChActualDiscountPK the primary key of the st ch actual discount
    * @return the st ch actual discount
    * @throws com.stpl.app.NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChActualDiscount findByPrimaryKey(
        StChActualDiscountPK stChActualDiscountPK)
        throws com.stpl.app.NoSuchStChActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stChActualDiscountPK);
    }

    /**
    * Returns the st ch actual discount with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stChActualDiscountPK the primary key of the st ch actual discount
    * @return the st ch actual discount, or <code>null</code> if a st ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChActualDiscount fetchByPrimaryKey(
        StChActualDiscountPK stChActualDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stChActualDiscountPK);
    }

    /**
    * Returns all the st ch actual discounts.
    *
    * @return the st ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StChActualDiscount> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st ch actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch actual discounts
    * @param end the upper bound of the range of st ch actual discounts (not inclusive)
    * @return the range of st ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StChActualDiscount> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st ch actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch actual discounts
    * @param end the upper bound of the range of st ch actual discounts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StChActualDiscount> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st ch actual discounts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st ch actual discounts.
    *
    * @return the number of st ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StChActualDiscountPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StChActualDiscountPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StChActualDiscountPersistence.class.getName());

            ReferenceRegistry.registerReference(StChActualDiscountUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StChActualDiscountPersistence persistence) {
    }
}
