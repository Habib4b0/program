package com.stpl.app.service.persistence;

import com.stpl.app.model.HistHierarchyLevelDefn;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the hist hierarchy level defn service. This utility wraps {@link HistHierarchyLevelDefnPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyLevelDefnPersistence
 * @see HistHierarchyLevelDefnPersistenceImpl
 * @generated
 */
public class HistHierarchyLevelDefnUtil {
    private static HistHierarchyLevelDefnPersistence _persistence;

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
    public static void clearCache(HistHierarchyLevelDefn histHierarchyLevelDefn) {
        getPersistence().clearCache(histHierarchyLevelDefn);
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
    public static List<HistHierarchyLevelDefn> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<HistHierarchyLevelDefn> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<HistHierarchyLevelDefn> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static HistHierarchyLevelDefn update(
        HistHierarchyLevelDefn histHierarchyLevelDefn)
        throws SystemException {
        return getPersistence().update(histHierarchyLevelDefn);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static HistHierarchyLevelDefn update(
        HistHierarchyLevelDefn histHierarchyLevelDefn,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(histHierarchyLevelDefn, serviceContext);
    }

    /**
    * Caches the hist hierarchy level defn in the entity cache if it is enabled.
    *
    * @param histHierarchyLevelDefn the hist hierarchy level defn
    */
    public static void cacheResult(
        com.stpl.app.model.HistHierarchyLevelDefn histHierarchyLevelDefn) {
        getPersistence().cacheResult(histHierarchyLevelDefn);
    }

    /**
    * Caches the hist hierarchy level defns in the entity cache if it is enabled.
    *
    * @param histHierarchyLevelDefns the hist hierarchy level defns
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.HistHierarchyLevelDefn> histHierarchyLevelDefns) {
        getPersistence().cacheResult(histHierarchyLevelDefns);
    }

    /**
    * Creates a new hist hierarchy level defn with the primary key. Does not add the hist hierarchy level defn to the database.
    *
    * @param histHierarchyLevelDefnPK the primary key for the new hist hierarchy level defn
    * @return the new hist hierarchy level defn
    */
    public static com.stpl.app.model.HistHierarchyLevelDefn create(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK) {
        return getPersistence().create(histHierarchyLevelDefnPK);
    }

    /**
    * Removes the hist hierarchy level defn with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
    * @return the hist hierarchy level defn that was removed
    * @throws com.stpl.app.NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistHierarchyLevelDefn remove(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
        throws com.stpl.app.NoSuchHistHierarchyLevelDefnException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(histHierarchyLevelDefnPK);
    }

    public static com.stpl.app.model.HistHierarchyLevelDefn updateImpl(
        com.stpl.app.model.HistHierarchyLevelDefn histHierarchyLevelDefn)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(histHierarchyLevelDefn);
    }

    /**
    * Returns the hist hierarchy level defn with the primary key or throws a {@link com.stpl.app.NoSuchHistHierarchyLevelDefnException} if it could not be found.
    *
    * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
    * @return the hist hierarchy level defn
    * @throws com.stpl.app.NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistHierarchyLevelDefn findByPrimaryKey(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
        throws com.stpl.app.NoSuchHistHierarchyLevelDefnException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(histHierarchyLevelDefnPK);
    }

    /**
    * Returns the hist hierarchy level defn with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
    * @return the hist hierarchy level defn, or <code>null</code> if a hist hierarchy level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistHierarchyLevelDefn fetchByPrimaryKey(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(histHierarchyLevelDefnPK);
    }

    /**
    * Returns all the hist hierarchy level defns.
    *
    * @return the hist hierarchy level defns
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistHierarchyLevelDefn> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the hist hierarchy level defns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy level defns
    * @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
    * @return the range of hist hierarchy level defns
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistHierarchyLevelDefn> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the hist hierarchy level defns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy level defns
    * @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist hierarchy level defns
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistHierarchyLevelDefn> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the hist hierarchy level defns from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of hist hierarchy level defns.
    *
    * @return the number of hist hierarchy level defns
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static HistHierarchyLevelDefnPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (HistHierarchyLevelDefnPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HistHierarchyLevelDefnPersistence.class.getName());

            ReferenceRegistry.registerReference(HistHierarchyLevelDefnUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(HistHierarchyLevelDefnPersistence persistence) {
    }
}
