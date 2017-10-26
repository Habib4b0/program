package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwItemPricing;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw item pricing service. This utility wraps {@link VwItemPricingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemPricingPersistence
 * @see VwItemPricingPersistenceImpl
 * @generated
 */
public class VwItemPricingUtil {
    private static VwItemPricingPersistence _persistence;

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
    public static void clearCache(VwItemPricing vwItemPricing) {
        getPersistence().clearCache(vwItemPricing);
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
    public static List<VwItemPricing> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwItemPricing> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwItemPricing> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwItemPricing update(VwItemPricing vwItemPricing)
        throws SystemException {
        return getPersistence().update(vwItemPricing);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwItemPricing update(VwItemPricing vwItemPricing,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(vwItemPricing, serviceContext);
    }

    /**
    * Caches the vw item pricing in the entity cache if it is enabled.
    *
    * @param vwItemPricing the vw item pricing
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.VwItemPricing vwItemPricing) {
        getPersistence().cacheResult(vwItemPricing);
    }

    /**
    * Caches the vw item pricings in the entity cache if it is enabled.
    *
    * @param vwItemPricings the vw item pricings
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwItemPricing> vwItemPricings) {
        getPersistence().cacheResult(vwItemPricings);
    }

    /**
    * Creates a new vw item pricing with the primary key. Does not add the vw item pricing to the database.
    *
    * @param itemPricingSid the primary key for the new vw item pricing
    * @return the new vw item pricing
    */
    public static com.stpl.app.parttwo.model.VwItemPricing create(
        int itemPricingSid) {
        return getPersistence().create(itemPricingSid);
    }

    /**
    * Removes the vw item pricing with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricingSid the primary key of the vw item pricing
    * @return the vw item pricing that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwItemPricing remove(
        int itemPricingSid)
        throws com.stpl.app.parttwo.NoSuchVwItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(itemPricingSid);
    }

    public static com.stpl.app.parttwo.model.VwItemPricing updateImpl(
        com.stpl.app.parttwo.model.VwItemPricing vwItemPricing)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwItemPricing);
    }

    /**
    * Returns the vw item pricing with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwItemPricingException} if it could not be found.
    *
    * @param itemPricingSid the primary key of the vw item pricing
    * @return the vw item pricing
    * @throws com.stpl.app.parttwo.NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwItemPricing findByPrimaryKey(
        int itemPricingSid)
        throws com.stpl.app.parttwo.NoSuchVwItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(itemPricingSid);
    }

    /**
    * Returns the vw item pricing with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemPricingSid the primary key of the vw item pricing
    * @return the vw item pricing, or <code>null</code> if a vw item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwItemPricing fetchByPrimaryKey(
        int itemPricingSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(itemPricingSid);
    }

    /**
    * Returns all the vw item pricings.
    *
    * @return the vw item pricings
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwItemPricing> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.parttwo.model.VwItemPricing> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.parttwo.model.VwItemPricing> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw item pricings from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw item pricings.
    *
    * @return the number of vw item pricings
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwItemPricingPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwItemPricingPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    VwItemPricingPersistence.class.getName());

            ReferenceRegistry.registerReference(VwItemPricingUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwItemPricingPersistence persistence) {
    }
}
