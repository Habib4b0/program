package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldReturns;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld returns service. This utility wraps {@link IvldReturnsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldReturnsPersistence
 * @see IvldReturnsPersistenceImpl
 * @generated
 */
public class IvldReturnsUtil {
    private static IvldReturnsPersistence _persistence;

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
    public static void clearCache(IvldReturns ivldReturns) {
        getPersistence().clearCache(ivldReturns);
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
    public static List<IvldReturns> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldReturns> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldReturns> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldReturns update(IvldReturns ivldReturns)
        throws SystemException {
        return getPersistence().update(ivldReturns);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldReturns update(IvldReturns ivldReturns,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldReturns, serviceContext);
    }

    /**
    * Caches the ivld returns in the entity cache if it is enabled.
    *
    * @param ivldReturns the ivld returns
    */
    public static void cacheResult(com.stpl.app.model.IvldReturns ivldReturns) {
        getPersistence().cacheResult(ivldReturns);
    }

    /**
    * Caches the ivld returnses in the entity cache if it is enabled.
    *
    * @param ivldReturnses the ivld returnses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldReturns> ivldReturnses) {
        getPersistence().cacheResult(ivldReturnses);
    }

    /**
    * Creates a new ivld returns with the primary key. Does not add the ivld returns to the database.
    *
    * @param ivldReturnsSid the primary key for the new ivld returns
    * @return the new ivld returns
    */
    public static com.stpl.app.model.IvldReturns create(int ivldReturnsSid) {
        return getPersistence().create(ivldReturnsSid);
    }

    /**
    * Removes the ivld returns with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldReturnsSid the primary key of the ivld returns
    * @return the ivld returns that was removed
    * @throws com.stpl.app.NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldReturns remove(int ivldReturnsSid)
        throws com.stpl.app.NoSuchIvldReturnsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldReturnsSid);
    }

    public static com.stpl.app.model.IvldReturns updateImpl(
        com.stpl.app.model.IvldReturns ivldReturns)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldReturns);
    }

    /**
    * Returns the ivld returns with the primary key or throws a {@link com.stpl.app.NoSuchIvldReturnsException} if it could not be found.
    *
    * @param ivldReturnsSid the primary key of the ivld returns
    * @return the ivld returns
    * @throws com.stpl.app.NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldReturns findByPrimaryKey(
        int ivldReturnsSid)
        throws com.stpl.app.NoSuchIvldReturnsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldReturnsSid);
    }

    /**
    * Returns the ivld returns with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldReturnsSid the primary key of the ivld returns
    * @return the ivld returns, or <code>null</code> if a ivld returns with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldReturns fetchByPrimaryKey(
        int ivldReturnsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldReturnsSid);
    }

    /**
    * Returns all the ivld returnses.
    *
    * @return the ivld returnses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldReturns> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld returnses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld returnses
    * @param end the upper bound of the range of ivld returnses (not inclusive)
    * @return the range of ivld returnses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldReturns> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld returnses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld returnses
    * @param end the upper bound of the range of ivld returnses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld returnses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldReturns> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld returnses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld returnses.
    *
    * @return the number of ivld returnses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldReturnsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldReturnsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldReturnsPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldReturnsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldReturnsPersistence persistence) {
    }
}
