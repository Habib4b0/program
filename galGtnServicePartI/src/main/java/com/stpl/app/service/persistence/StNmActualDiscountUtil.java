package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmActualDiscount;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st nm actual discount service. This utility wraps {@link StNmActualDiscountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmActualDiscountPersistence
 * @see StNmActualDiscountPersistenceImpl
 * @generated
 */
public class StNmActualDiscountUtil {
    private static StNmActualDiscountPersistence _persistence;

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
    public static void clearCache(StNmActualDiscount stNmActualDiscount) {
        getPersistence().clearCache(stNmActualDiscount);
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
    public static List<StNmActualDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StNmActualDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StNmActualDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StNmActualDiscount update(
        StNmActualDiscount stNmActualDiscount) throws SystemException {
        return getPersistence().update(stNmActualDiscount);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StNmActualDiscount update(
        StNmActualDiscount stNmActualDiscount, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(stNmActualDiscount, serviceContext);
    }

    /**
    * Caches the st nm actual discount in the entity cache if it is enabled.
    *
    * @param stNmActualDiscount the st nm actual discount
    */
    public static void cacheResult(
        com.stpl.app.model.StNmActualDiscount stNmActualDiscount) {
        getPersistence().cacheResult(stNmActualDiscount);
    }

    /**
    * Caches the st nm actual discounts in the entity cache if it is enabled.
    *
    * @param stNmActualDiscounts the st nm actual discounts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StNmActualDiscount> stNmActualDiscounts) {
        getPersistence().cacheResult(stNmActualDiscounts);
    }

    /**
    * Creates a new st nm actual discount with the primary key. Does not add the st nm actual discount to the database.
    *
    * @param stNmActualDiscountPK the primary key for the new st nm actual discount
    * @return the new st nm actual discount
    */
    public static com.stpl.app.model.StNmActualDiscount create(
        StNmActualDiscountPK stNmActualDiscountPK) {
        return getPersistence().create(stNmActualDiscountPK);
    }

    /**
    * Removes the st nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmActualDiscountPK the primary key of the st nm actual discount
    * @return the st nm actual discount that was removed
    * @throws com.stpl.app.NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmActualDiscount remove(
        StNmActualDiscountPK stNmActualDiscountPK)
        throws com.stpl.app.NoSuchStNmActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stNmActualDiscountPK);
    }

    public static com.stpl.app.model.StNmActualDiscount updateImpl(
        com.stpl.app.model.StNmActualDiscount stNmActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stNmActualDiscount);
    }

    /**
    * Returns the st nm actual discount with the primary key or throws a {@link com.stpl.app.NoSuchStNmActualDiscountException} if it could not be found.
    *
    * @param stNmActualDiscountPK the primary key of the st nm actual discount
    * @return the st nm actual discount
    * @throws com.stpl.app.NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmActualDiscount findByPrimaryKey(
        StNmActualDiscountPK stNmActualDiscountPK)
        throws com.stpl.app.NoSuchStNmActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stNmActualDiscountPK);
    }

    /**
    * Returns the st nm actual discount with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmActualDiscountPK the primary key of the st nm actual discount
    * @return the st nm actual discount, or <code>null</code> if a st nm actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmActualDiscount fetchByPrimaryKey(
        StNmActualDiscountPK stNmActualDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stNmActualDiscountPK);
    }

    /**
    * Returns all the st nm actual discounts.
    *
    * @return the st nm actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmActualDiscount> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st nm actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm actual discounts
    * @param end the upper bound of the range of st nm actual discounts (not inclusive)
    * @return the range of st nm actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmActualDiscount> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st nm actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm actual discounts
    * @param end the upper bound of the range of st nm actual discounts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmActualDiscount> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st nm actual discounts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st nm actual discounts.
    *
    * @return the number of st nm actual discounts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StNmActualDiscountPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StNmActualDiscountPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StNmActualDiscountPersistence.class.getName());

            ReferenceRegistry.registerReference(StNmActualDiscountUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StNmActualDiscountPersistence persistence) {
    }
}
