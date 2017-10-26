package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldBestPrice;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld best price service. This utility wraps {@link IvldBestPricePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldBestPricePersistence
 * @see IvldBestPricePersistenceImpl
 * @generated
 */
public class IvldBestPriceUtil {
    private static IvldBestPricePersistence _persistence;

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
    public static void clearCache(IvldBestPrice ivldBestPrice) {
        getPersistence().clearCache(ivldBestPrice);
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
    public static List<IvldBestPrice> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldBestPrice> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldBestPrice> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldBestPrice update(IvldBestPrice ivldBestPrice)
        throws SystemException {
        return getPersistence().update(ivldBestPrice);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldBestPrice update(IvldBestPrice ivldBestPrice,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldBestPrice, serviceContext);
    }

    /**
    * Caches the ivld best price in the entity cache if it is enabled.
    *
    * @param ivldBestPrice the ivld best price
    */
    public static void cacheResult(
        com.stpl.app.model.IvldBestPrice ivldBestPrice) {
        getPersistence().cacheResult(ivldBestPrice);
    }

    /**
    * Caches the ivld best prices in the entity cache if it is enabled.
    *
    * @param ivldBestPrices the ivld best prices
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldBestPrice> ivldBestPrices) {
        getPersistence().cacheResult(ivldBestPrices);
    }

    /**
    * Creates a new ivld best price with the primary key. Does not add the ivld best price to the database.
    *
    * @param ivldBestPriceSid the primary key for the new ivld best price
    * @return the new ivld best price
    */
    public static com.stpl.app.model.IvldBestPrice create(int ivldBestPriceSid) {
        return getPersistence().create(ivldBestPriceSid);
    }

    /**
    * Removes the ivld best price with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldBestPriceSid the primary key of the ivld best price
    * @return the ivld best price that was removed
    * @throws com.stpl.app.NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldBestPrice remove(int ivldBestPriceSid)
        throws com.stpl.app.NoSuchIvldBestPriceException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldBestPriceSid);
    }

    public static com.stpl.app.model.IvldBestPrice updateImpl(
        com.stpl.app.model.IvldBestPrice ivldBestPrice)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldBestPrice);
    }

    /**
    * Returns the ivld best price with the primary key or throws a {@link com.stpl.app.NoSuchIvldBestPriceException} if it could not be found.
    *
    * @param ivldBestPriceSid the primary key of the ivld best price
    * @return the ivld best price
    * @throws com.stpl.app.NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldBestPrice findByPrimaryKey(
        int ivldBestPriceSid)
        throws com.stpl.app.NoSuchIvldBestPriceException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldBestPriceSid);
    }

    /**
    * Returns the ivld best price with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldBestPriceSid the primary key of the ivld best price
    * @return the ivld best price, or <code>null</code> if a ivld best price with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldBestPrice fetchByPrimaryKey(
        int ivldBestPriceSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldBestPriceSid);
    }

    /**
    * Returns all the ivld best prices.
    *
    * @return the ivld best prices
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldBestPrice> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.model.IvldBestPrice> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.model.IvldBestPrice> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld best prices from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld best prices.
    *
    * @return the number of ivld best prices
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldBestPricePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldBestPricePersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldBestPricePersistence.class.getName());

            ReferenceRegistry.registerReference(IvldBestPriceUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldBestPricePersistence persistence) {
    }
}
