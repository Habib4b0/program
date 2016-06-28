package com.stpl.app.service.persistence;

import com.stpl.app.model.StChProjectionDiscount;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st ch projection discount service. This utility wraps {@link StChProjectionDiscountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChProjectionDiscountPersistence
 * @see StChProjectionDiscountPersistenceImpl
 * @generated
 */
public class StChProjectionDiscountUtil {
    private static StChProjectionDiscountPersistence _persistence;

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
    public static void clearCache(StChProjectionDiscount stChProjectionDiscount) {
        getPersistence().clearCache(stChProjectionDiscount);
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
    public static List<StChProjectionDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StChProjectionDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StChProjectionDiscount> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StChProjectionDiscount update(
        StChProjectionDiscount stChProjectionDiscount)
        throws SystemException {
        return getPersistence().update(stChProjectionDiscount);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StChProjectionDiscount update(
        StChProjectionDiscount stChProjectionDiscount,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stChProjectionDiscount, serviceContext);
    }

    /**
    * Caches the st ch projection discount in the entity cache if it is enabled.
    *
    * @param stChProjectionDiscount the st ch projection discount
    */
    public static void cacheResult(
        com.stpl.app.model.StChProjectionDiscount stChProjectionDiscount) {
        getPersistence().cacheResult(stChProjectionDiscount);
    }

    /**
    * Caches the st ch projection discounts in the entity cache if it is enabled.
    *
    * @param stChProjectionDiscounts the st ch projection discounts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StChProjectionDiscount> stChProjectionDiscounts) {
        getPersistence().cacheResult(stChProjectionDiscounts);
    }

    /**
    * Creates a new st ch projection discount with the primary key. Does not add the st ch projection discount to the database.
    *
    * @param stChProjectionDiscountPK the primary key for the new st ch projection discount
    * @return the new st ch projection discount
    */
    public static com.stpl.app.model.StChProjectionDiscount create(
        StChProjectionDiscountPK stChProjectionDiscountPK) {
        return getPersistence().create(stChProjectionDiscountPK);
    }

    /**
    * Removes the st ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stChProjectionDiscountPK the primary key of the st ch projection discount
    * @return the st ch projection discount that was removed
    * @throws com.stpl.app.NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChProjectionDiscount remove(
        StChProjectionDiscountPK stChProjectionDiscountPK)
        throws com.stpl.app.NoSuchStChProjectionDiscountException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stChProjectionDiscountPK);
    }

    public static com.stpl.app.model.StChProjectionDiscount updateImpl(
        com.stpl.app.model.StChProjectionDiscount stChProjectionDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stChProjectionDiscount);
    }

    /**
    * Returns the st ch projection discount with the primary key or throws a {@link com.stpl.app.NoSuchStChProjectionDiscountException} if it could not be found.
    *
    * @param stChProjectionDiscountPK the primary key of the st ch projection discount
    * @return the st ch projection discount
    * @throws com.stpl.app.NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChProjectionDiscount findByPrimaryKey(
        StChProjectionDiscountPK stChProjectionDiscountPK)
        throws com.stpl.app.NoSuchStChProjectionDiscountException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stChProjectionDiscountPK);
    }

    /**
    * Returns the st ch projection discount with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stChProjectionDiscountPK the primary key of the st ch projection discount
    * @return the st ch projection discount, or <code>null</code> if a st ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChProjectionDiscount fetchByPrimaryKey(
        StChProjectionDiscountPK stChProjectionDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stChProjectionDiscountPK);
    }

    /**
    * Returns all the st ch projection discounts.
    *
    * @return the st ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StChProjectionDiscount> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st ch projection discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch projection discounts
    * @param end the upper bound of the range of st ch projection discounts (not inclusive)
    * @return the range of st ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StChProjectionDiscount> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st ch projection discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch projection discounts
    * @param end the upper bound of the range of st ch projection discounts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StChProjectionDiscount> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st ch projection discounts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st ch projection discounts.
    *
    * @return the number of st ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StChProjectionDiscountPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StChProjectionDiscountPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StChProjectionDiscountPersistence.class.getName());

            ReferenceRegistry.registerReference(StChProjectionDiscountUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StChProjectionDiscountPersistence persistence) {
    }
}
