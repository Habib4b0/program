package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldForecastSales;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld forecast sales service. This utility wraps {@link IvldForecastSalesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldForecastSalesPersistence
 * @see IvldForecastSalesPersistenceImpl
 * @generated
 */
public class IvldForecastSalesUtil {
    private static IvldForecastSalesPersistence _persistence;

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
    public static void clearCache(IvldForecastSales ivldForecastSales) {
        getPersistence().clearCache(ivldForecastSales);
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
    public static List<IvldForecastSales> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldForecastSales> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldForecastSales> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldForecastSales update(IvldForecastSales ivldForecastSales)
        throws SystemException {
        return getPersistence().update(ivldForecastSales);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldForecastSales update(
        IvldForecastSales ivldForecastSales, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ivldForecastSales, serviceContext);
    }

    /**
    * Caches the ivld forecast sales in the entity cache if it is enabled.
    *
    * @param ivldForecastSales the ivld forecast sales
    */
    public static void cacheResult(
        com.stpl.app.model.IvldForecastSales ivldForecastSales) {
        getPersistence().cacheResult(ivldForecastSales);
    }

    /**
    * Caches the ivld forecast saleses in the entity cache if it is enabled.
    *
    * @param ivldForecastSaleses the ivld forecast saleses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldForecastSales> ivldForecastSaleses) {
        getPersistence().cacheResult(ivldForecastSaleses);
    }

    /**
    * Creates a new ivld forecast sales with the primary key. Does not add the ivld forecast sales to the database.
    *
    * @param ivldForecastSalesSid the primary key for the new ivld forecast sales
    * @return the new ivld forecast sales
    */
    public static com.stpl.app.model.IvldForecastSales create(
        int ivldForecastSalesSid) {
        return getPersistence().create(ivldForecastSalesSid);
    }

    /**
    * Removes the ivld forecast sales with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldForecastSalesSid the primary key of the ivld forecast sales
    * @return the ivld forecast sales that was removed
    * @throws com.stpl.app.NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldForecastSales remove(
        int ivldForecastSalesSid)
        throws com.stpl.app.NoSuchIvldForecastSalesException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldForecastSalesSid);
    }

    public static com.stpl.app.model.IvldForecastSales updateImpl(
        com.stpl.app.model.IvldForecastSales ivldForecastSales)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldForecastSales);
    }

    /**
    * Returns the ivld forecast sales with the primary key or throws a {@link com.stpl.app.NoSuchIvldForecastSalesException} if it could not be found.
    *
    * @param ivldForecastSalesSid the primary key of the ivld forecast sales
    * @return the ivld forecast sales
    * @throws com.stpl.app.NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldForecastSales findByPrimaryKey(
        int ivldForecastSalesSid)
        throws com.stpl.app.NoSuchIvldForecastSalesException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldForecastSalesSid);
    }

    /**
    * Returns the ivld forecast sales with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldForecastSalesSid the primary key of the ivld forecast sales
    * @return the ivld forecast sales, or <code>null</code> if a ivld forecast sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldForecastSales fetchByPrimaryKey(
        int ivldForecastSalesSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldForecastSalesSid);
    }

    /**
    * Returns all the ivld forecast saleses.
    *
    * @return the ivld forecast saleses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldForecastSales> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld forecast saleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld forecast saleses
    * @param end the upper bound of the range of ivld forecast saleses (not inclusive)
    * @return the range of ivld forecast saleses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldForecastSales> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld forecast saleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld forecast saleses
    * @param end the upper bound of the range of ivld forecast saleses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld forecast saleses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldForecastSales> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld forecast saleses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld forecast saleses.
    *
    * @return the number of ivld forecast saleses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldForecastSalesPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldForecastSalesPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldForecastSalesPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldForecastSalesUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldForecastSalesPersistence persistence) {
    }
}
