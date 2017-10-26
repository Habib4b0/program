package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldItemHierarchy;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld item hierarchy service. This utility wraps {@link IvldItemHierarchyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemHierarchyPersistence
 * @see IvldItemHierarchyPersistenceImpl
 * @generated
 */
public class IvldItemHierarchyUtil {
    private static IvldItemHierarchyPersistence _persistence;

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
    public static void clearCache(IvldItemHierarchy ivldItemHierarchy) {
        getPersistence().clearCache(ivldItemHierarchy);
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
    public static List<IvldItemHierarchy> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldItemHierarchy> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldItemHierarchy> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldItemHierarchy update(IvldItemHierarchy ivldItemHierarchy)
        throws SystemException {
        return getPersistence().update(ivldItemHierarchy);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldItemHierarchy update(
        IvldItemHierarchy ivldItemHierarchy, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ivldItemHierarchy, serviceContext);
    }

    /**
    * Caches the ivld item hierarchy in the entity cache if it is enabled.
    *
    * @param ivldItemHierarchy the ivld item hierarchy
    */
    public static void cacheResult(
        com.stpl.app.model.IvldItemHierarchy ivldItemHierarchy) {
        getPersistence().cacheResult(ivldItemHierarchy);
    }

    /**
    * Caches the ivld item hierarchies in the entity cache if it is enabled.
    *
    * @param ivldItemHierarchies the ivld item hierarchies
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldItemHierarchy> ivldItemHierarchies) {
        getPersistence().cacheResult(ivldItemHierarchies);
    }

    /**
    * Creates a new ivld item hierarchy with the primary key. Does not add the ivld item hierarchy to the database.
    *
    * @param ivldItemHierarchySid the primary key for the new ivld item hierarchy
    * @return the new ivld item hierarchy
    */
    public static com.stpl.app.model.IvldItemHierarchy create(
        int ivldItemHierarchySid) {
        return getPersistence().create(ivldItemHierarchySid);
    }

    /**
    * Removes the ivld item hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
    * @return the ivld item hierarchy that was removed
    * @throws com.stpl.app.NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldItemHierarchy remove(
        int ivldItemHierarchySid)
        throws com.stpl.app.NoSuchIvldItemHierarchyException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldItemHierarchySid);
    }

    public static com.stpl.app.model.IvldItemHierarchy updateImpl(
        com.stpl.app.model.IvldItemHierarchy ivldItemHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldItemHierarchy);
    }

    /**
    * Returns the ivld item hierarchy with the primary key or throws a {@link com.stpl.app.NoSuchIvldItemHierarchyException} if it could not be found.
    *
    * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
    * @return the ivld item hierarchy
    * @throws com.stpl.app.NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldItemHierarchy findByPrimaryKey(
        int ivldItemHierarchySid)
        throws com.stpl.app.NoSuchIvldItemHierarchyException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldItemHierarchySid);
    }

    /**
    * Returns the ivld item hierarchy with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
    * @return the ivld item hierarchy, or <code>null</code> if a ivld item hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldItemHierarchy fetchByPrimaryKey(
        int ivldItemHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldItemHierarchySid);
    }

    /**
    * Returns all the ivld item hierarchies.
    *
    * @return the ivld item hierarchies
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldItemHierarchy> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld item hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item hierarchies
    * @param end the upper bound of the range of ivld item hierarchies (not inclusive)
    * @return the range of ivld item hierarchies
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldItemHierarchy> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld item hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item hierarchies
    * @param end the upper bound of the range of ivld item hierarchies (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld item hierarchies
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldItemHierarchy> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld item hierarchies from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld item hierarchies.
    *
    * @return the number of ivld item hierarchies
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldItemHierarchyPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldItemHierarchyPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldItemHierarchyPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldItemHierarchyUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldItemHierarchyPersistence persistence) {
    }
}
