package com.stpl.app.service.persistence;

import com.stpl.app.model.ChActualSales;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ch actual sales service. This utility wraps {@link ChActualSalesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChActualSalesPersistence
 * @see ChActualSalesPersistenceImpl
 * @generated
 */
public class ChActualSalesUtil {
    private static ChActualSalesPersistence _persistence;

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
    public static void clearCache(ChActualSales chActualSales) {
        getPersistence().clearCache(chActualSales);
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
    public static List<ChActualSales> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ChActualSales> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ChActualSales> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ChActualSales update(ChActualSales chActualSales)
        throws SystemException {
        return getPersistence().update(chActualSales);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ChActualSales update(ChActualSales chActualSales,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(chActualSales, serviceContext);
    }

    /**
    * Caches the ch actual sales in the entity cache if it is enabled.
    *
    * @param chActualSales the ch actual sales
    */
    public static void cacheResult(
        com.stpl.app.model.ChActualSales chActualSales) {
        getPersistence().cacheResult(chActualSales);
    }

    /**
    * Caches the ch actual saleses in the entity cache if it is enabled.
    *
    * @param chActualSaleses the ch actual saleses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ChActualSales> chActualSaleses) {
        getPersistence().cacheResult(chActualSaleses);
    }

    /**
    * Creates a new ch actual sales with the primary key. Does not add the ch actual sales to the database.
    *
    * @param chActualSalesPK the primary key for the new ch actual sales
    * @return the new ch actual sales
    */
    public static com.stpl.app.model.ChActualSales create(
        ChActualSalesPK chActualSalesPK) {
        return getPersistence().create(chActualSalesPK);
    }

    /**
    * Removes the ch actual sales with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chActualSalesPK the primary key of the ch actual sales
    * @return the ch actual sales that was removed
    * @throws com.stpl.app.NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChActualSales remove(
        ChActualSalesPK chActualSalesPK)
        throws com.stpl.app.NoSuchChActualSalesException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(chActualSalesPK);
    }

    public static com.stpl.app.model.ChActualSales updateImpl(
        com.stpl.app.model.ChActualSales chActualSales)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(chActualSales);
    }

    /**
    * Returns the ch actual sales with the primary key or throws a {@link com.stpl.app.NoSuchChActualSalesException} if it could not be found.
    *
    * @param chActualSalesPK the primary key of the ch actual sales
    * @return the ch actual sales
    * @throws com.stpl.app.NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChActualSales findByPrimaryKey(
        ChActualSalesPK chActualSalesPK)
        throws com.stpl.app.NoSuchChActualSalesException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(chActualSalesPK);
    }

    /**
    * Returns the ch actual sales with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param chActualSalesPK the primary key of the ch actual sales
    * @return the ch actual sales, or <code>null</code> if a ch actual sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChActualSales fetchByPrimaryKey(
        ChActualSalesPK chActualSalesPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(chActualSalesPK);
    }

    /**
    * Returns all the ch actual saleses.
    *
    * @return the ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChActualSales> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ch actual saleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch actual saleses
    * @param end the upper bound of the range of ch actual saleses (not inclusive)
    * @return the range of ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChActualSales> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ch actual saleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch actual saleses
    * @param end the upper bound of the range of ch actual saleses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChActualSales> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ch actual saleses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ch actual saleses.
    *
    * @return the number of ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ChActualSalesPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ChActualSalesPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ChActualSalesPersistence.class.getName());

            ReferenceRegistry.registerReference(ChActualSalesUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ChActualSalesPersistence persistence) {
    }
}
