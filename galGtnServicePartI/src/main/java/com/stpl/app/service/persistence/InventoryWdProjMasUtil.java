package com.stpl.app.service.persistence;

import com.stpl.app.model.InventoryWdProjMas;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the inventory wd proj mas service. This utility wraps {@link InventoryWdProjMasPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see InventoryWdProjMasPersistence
 * @see InventoryWdProjMasPersistenceImpl
 * @generated
 */
public class InventoryWdProjMasUtil {
    private static InventoryWdProjMasPersistence _persistence;

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
    public static void clearCache(InventoryWdProjMas inventoryWdProjMas) {
        getPersistence().clearCache(inventoryWdProjMas);
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
    public static List<InventoryWdProjMas> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<InventoryWdProjMas> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<InventoryWdProjMas> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static InventoryWdProjMas update(
        InventoryWdProjMas inventoryWdProjMas) throws SystemException {
        return getPersistence().update(inventoryWdProjMas);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static InventoryWdProjMas update(
        InventoryWdProjMas inventoryWdProjMas, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(inventoryWdProjMas, serviceContext);
    }

    /**
    * Caches the inventory wd proj mas in the entity cache if it is enabled.
    *
    * @param inventoryWdProjMas the inventory wd proj mas
    */
    public static void cacheResult(
        com.stpl.app.model.InventoryWdProjMas inventoryWdProjMas) {
        getPersistence().cacheResult(inventoryWdProjMas);
    }

    /**
    * Caches the inventory wd proj mases in the entity cache if it is enabled.
    *
    * @param inventoryWdProjMases the inventory wd proj mases
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.InventoryWdProjMas> inventoryWdProjMases) {
        getPersistence().cacheResult(inventoryWdProjMases);
    }

    /**
    * Creates a new inventory wd proj mas with the primary key. Does not add the inventory wd proj mas to the database.
    *
    * @param inventoryWdProjMasSid the primary key for the new inventory wd proj mas
    * @return the new inventory wd proj mas
    */
    public static com.stpl.app.model.InventoryWdProjMas create(
        int inventoryWdProjMasSid) {
        return getPersistence().create(inventoryWdProjMasSid);
    }

    /**
    * Removes the inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
    * @return the inventory wd proj mas that was removed
    * @throws com.stpl.app.NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.InventoryWdProjMas remove(
        int inventoryWdProjMasSid)
        throws com.stpl.app.NoSuchInventoryWdProjMasException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(inventoryWdProjMasSid);
    }

    public static com.stpl.app.model.InventoryWdProjMas updateImpl(
        com.stpl.app.model.InventoryWdProjMas inventoryWdProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(inventoryWdProjMas);
    }

    /**
    * Returns the inventory wd proj mas with the primary key or throws a {@link com.stpl.app.NoSuchInventoryWdProjMasException} if it could not be found.
    *
    * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
    * @return the inventory wd proj mas
    * @throws com.stpl.app.NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.InventoryWdProjMas findByPrimaryKey(
        int inventoryWdProjMasSid)
        throws com.stpl.app.NoSuchInventoryWdProjMasException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(inventoryWdProjMasSid);
    }

    /**
    * Returns the inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
    * @return the inventory wd proj mas, or <code>null</code> if a inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.InventoryWdProjMas fetchByPrimaryKey(
        int inventoryWdProjMasSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(inventoryWdProjMasSid);
    }

    /**
    * Returns all the inventory wd proj mases.
    *
    * @return the inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.InventoryWdProjMas> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the inventory wd proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of inventory wd proj mases
    * @param end the upper bound of the range of inventory wd proj mases (not inclusive)
    * @return the range of inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.InventoryWdProjMas> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the inventory wd proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of inventory wd proj mases
    * @param end the upper bound of the range of inventory wd proj mases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.InventoryWdProjMas> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the inventory wd proj mases from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of inventory wd proj mases.
    *
    * @return the number of inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static InventoryWdProjMasPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (InventoryWdProjMasPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    InventoryWdProjMasPersistence.class.getName());

            ReferenceRegistry.registerReference(InventoryWdProjMasUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(InventoryWdProjMasPersistence persistence) {
    }
}
