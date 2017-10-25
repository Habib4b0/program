package com.stpl.app.service.persistence;

import com.stpl.app.model.InventoryWdActualMas;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the inventory wd actual mas service. This utility wraps {@link InventoryWdActualMasPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see InventoryWdActualMasPersistence
 * @see InventoryWdActualMasPersistenceImpl
 * @generated
 */
public class InventoryWdActualMasUtil {
    private static InventoryWdActualMasPersistence _persistence;

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
    public static void clearCache(InventoryWdActualMas inventoryWdActualMas) {
        getPersistence().clearCache(inventoryWdActualMas);
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
    public static List<InventoryWdActualMas> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<InventoryWdActualMas> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<InventoryWdActualMas> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static InventoryWdActualMas update(
        InventoryWdActualMas inventoryWdActualMas) throws SystemException {
        return getPersistence().update(inventoryWdActualMas);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static InventoryWdActualMas update(
        InventoryWdActualMas inventoryWdActualMas, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(inventoryWdActualMas, serviceContext);
    }

    /**
    * Caches the inventory wd actual mas in the entity cache if it is enabled.
    *
    * @param inventoryWdActualMas the inventory wd actual mas
    */
    public static void cacheResult(
        com.stpl.app.model.InventoryWdActualMas inventoryWdActualMas) {
        getPersistence().cacheResult(inventoryWdActualMas);
    }

    /**
    * Caches the inventory wd actual mases in the entity cache if it is enabled.
    *
    * @param inventoryWdActualMases the inventory wd actual mases
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.InventoryWdActualMas> inventoryWdActualMases) {
        getPersistence().cacheResult(inventoryWdActualMases);
    }

    /**
    * Creates a new inventory wd actual mas with the primary key. Does not add the inventory wd actual mas to the database.
    *
    * @param inventoryWdActualMasSid the primary key for the new inventory wd actual mas
    * @return the new inventory wd actual mas
    */
    public static com.stpl.app.model.InventoryWdActualMas create(
        int inventoryWdActualMasSid) {
        return getPersistence().create(inventoryWdActualMasSid);
    }

    /**
    * Removes the inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
    * @return the inventory wd actual mas that was removed
    * @throws com.stpl.app.NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.InventoryWdActualMas remove(
        int inventoryWdActualMasSid)
        throws com.stpl.app.NoSuchInventoryWdActualMasException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(inventoryWdActualMasSid);
    }

    public static com.stpl.app.model.InventoryWdActualMas updateImpl(
        com.stpl.app.model.InventoryWdActualMas inventoryWdActualMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(inventoryWdActualMas);
    }

    /**
    * Returns the inventory wd actual mas with the primary key or throws a {@link com.stpl.app.NoSuchInventoryWdActualMasException} if it could not be found.
    *
    * @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
    * @return the inventory wd actual mas
    * @throws com.stpl.app.NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.InventoryWdActualMas findByPrimaryKey(
        int inventoryWdActualMasSid)
        throws com.stpl.app.NoSuchInventoryWdActualMasException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(inventoryWdActualMasSid);
    }

    /**
    * Returns the inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
    * @return the inventory wd actual mas, or <code>null</code> if a inventory wd actual mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.InventoryWdActualMas fetchByPrimaryKey(
        int inventoryWdActualMasSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(inventoryWdActualMasSid);
    }

    /**
    * Returns all the inventory wd actual mases.
    *
    * @return the inventory wd actual mases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.InventoryWdActualMas> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the inventory wd actual mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of inventory wd actual mases
    * @param end the upper bound of the range of inventory wd actual mases (not inclusive)
    * @return the range of inventory wd actual mases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.InventoryWdActualMas> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the inventory wd actual mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of inventory wd actual mases
    * @param end the upper bound of the range of inventory wd actual mases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of inventory wd actual mases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.InventoryWdActualMas> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the inventory wd actual mases from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of inventory wd actual mases.
    *
    * @return the number of inventory wd actual mases
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static InventoryWdActualMasPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (InventoryWdActualMasPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    InventoryWdActualMasPersistence.class.getName());

            ReferenceRegistry.registerReference(InventoryWdActualMasUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(InventoryWdActualMasPersistence persistence) {
    }
}
