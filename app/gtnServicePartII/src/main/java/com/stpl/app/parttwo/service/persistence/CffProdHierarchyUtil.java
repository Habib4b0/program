package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffProdHierarchy;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cff prod hierarchy service. This utility wraps {@link CffProdHierarchyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffProdHierarchyPersistence
 * @see CffProdHierarchyPersistenceImpl
 * @generated
 */
public class CffProdHierarchyUtil {
    private static CffProdHierarchyPersistence _persistence;

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
    public static void clearCache(CffProdHierarchy cffProdHierarchy) {
        getPersistence().clearCache(cffProdHierarchy);
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
    public static List<CffProdHierarchy> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CffProdHierarchy> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CffProdHierarchy> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CffProdHierarchy update(CffProdHierarchy cffProdHierarchy)
        throws SystemException {
        return getPersistence().update(cffProdHierarchy);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CffProdHierarchy update(CffProdHierarchy cffProdHierarchy,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(cffProdHierarchy, serviceContext);
    }

    /**
    * Caches the cff prod hierarchy in the entity cache if it is enabled.
    *
    * @param cffProdHierarchy the cff prod hierarchy
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.CffProdHierarchy cffProdHierarchy) {
        getPersistence().cacheResult(cffProdHierarchy);
    }

    /**
    * Caches the cff prod hierarchies in the entity cache if it is enabled.
    *
    * @param cffProdHierarchies the cff prod hierarchies
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffProdHierarchy> cffProdHierarchies) {
        getPersistence().cacheResult(cffProdHierarchies);
    }

    /**
    * Creates a new cff prod hierarchy with the primary key. Does not add the cff prod hierarchy to the database.
    *
    * @param cffProdHierarchySid the primary key for the new cff prod hierarchy
    * @return the new cff prod hierarchy
    */
    public static com.stpl.app.parttwo.model.CffProdHierarchy create(
        int cffProdHierarchySid) {
        return getPersistence().create(cffProdHierarchySid);
    }

    /**
    * Removes the cff prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffProdHierarchySid the primary key of the cff prod hierarchy
    * @return the cff prod hierarchy that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffProdHierarchy remove(
        int cffProdHierarchySid)
        throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(cffProdHierarchySid);
    }

    public static com.stpl.app.parttwo.model.CffProdHierarchy updateImpl(
        com.stpl.app.parttwo.model.CffProdHierarchy cffProdHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(cffProdHierarchy);
    }

    /**
    * Returns the cff prod hierarchy with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffProdHierarchyException} if it could not be found.
    *
    * @param cffProdHierarchySid the primary key of the cff prod hierarchy
    * @return the cff prod hierarchy
    * @throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffProdHierarchy findByPrimaryKey(
        int cffProdHierarchySid)
        throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(cffProdHierarchySid);
    }

    /**
    * Returns the cff prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffProdHierarchySid the primary key of the cff prod hierarchy
    * @return the cff prod hierarchy, or <code>null</code> if a cff prod hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffProdHierarchy fetchByPrimaryKey(
        int cffProdHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(cffProdHierarchySid);
    }

    /**
    * Returns all the cff prod hierarchies.
    *
    * @return the cff prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffProdHierarchy> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the cff prod hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff prod hierarchies
    * @param end the upper bound of the range of cff prod hierarchies (not inclusive)
    * @return the range of cff prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffProdHierarchy> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the cff prod hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff prod hierarchies
    * @param end the upper bound of the range of cff prod hierarchies (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffProdHierarchy> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the cff prod hierarchies from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of cff prod hierarchies.
    *
    * @return the number of cff prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CffProdHierarchyPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CffProdHierarchyPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    CffProdHierarchyPersistence.class.getName());

            ReferenceRegistry.registerReference(CffProdHierarchyUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CffProdHierarchyPersistence persistence) {
    }
}
