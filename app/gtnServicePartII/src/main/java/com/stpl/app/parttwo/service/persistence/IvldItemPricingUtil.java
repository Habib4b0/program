package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldItemPricing;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld item pricing service. This utility wraps {@link IvldItemPricingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemPricingPersistence
 * @see IvldItemPricingPersistenceImpl
 * @generated
 */
public class IvldItemPricingUtil {
    private static IvldItemPricingPersistence _persistence;

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
    public static void clearCache(IvldItemPricing ivldItemPricing) {
        getPersistence().clearCache(ivldItemPricing);
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
    public static List<IvldItemPricing> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldItemPricing> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldItemPricing> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldItemPricing update(IvldItemPricing ivldItemPricing)
        throws SystemException {
        return getPersistence().update(ivldItemPricing);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldItemPricing update(IvldItemPricing ivldItemPricing,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldItemPricing, serviceContext);
    }

    /**
    * Caches the ivld item pricing in the entity cache if it is enabled.
    *
    * @param ivldItemPricing the ivld item pricing
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.IvldItemPricing ivldItemPricing) {
        getPersistence().cacheResult(ivldItemPricing);
    }

    /**
    * Caches the ivld item pricings in the entity cache if it is enabled.
    *
    * @param ivldItemPricings the ivld item pricings
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldItemPricing> ivldItemPricings) {
        getPersistence().cacheResult(ivldItemPricings);
    }

    /**
    * Creates a new ivld item pricing with the primary key. Does not add the ivld item pricing to the database.
    *
    * @param ivldItemPricingSid the primary key for the new ivld item pricing
    * @return the new ivld item pricing
    */
    public static com.stpl.app.parttwo.model.IvldItemPricing create(
        int ivldItemPricingSid) {
        return getPersistence().create(ivldItemPricingSid);
    }

    /**
    * Removes the ivld item pricing with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemPricingSid the primary key of the ivld item pricing
    * @return the ivld item pricing that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldItemPricing remove(
        int ivldItemPricingSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldItemPricingSid);
    }

    public static com.stpl.app.parttwo.model.IvldItemPricing updateImpl(
        com.stpl.app.parttwo.model.IvldItemPricing ivldItemPricing)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldItemPricing);
    }

    /**
    * Returns the ivld item pricing with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldItemPricingException} if it could not be found.
    *
    * @param ivldItemPricingSid the primary key of the ivld item pricing
    * @return the ivld item pricing
    * @throws com.stpl.app.parttwo.NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldItemPricing findByPrimaryKey(
        int ivldItemPricingSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldItemPricingSid);
    }

    /**
    * Returns the ivld item pricing with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldItemPricingSid the primary key of the ivld item pricing
    * @return the ivld item pricing, or <code>null</code> if a ivld item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldItemPricing fetchByPrimaryKey(
        int ivldItemPricingSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldItemPricingSid);
    }

    /**
    * Returns all the ivld item pricings.
    *
    * @return the ivld item pricings
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldItemPricing> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.parttwo.model.IvldItemPricing> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.parttwo.model.IvldItemPricing> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld item pricings from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld item pricings.
    *
    * @return the number of ivld item pricings
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldItemPricingPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldItemPricingPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    IvldItemPricingPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldItemPricingUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldItemPricingPersistence persistence) {
    }
}
