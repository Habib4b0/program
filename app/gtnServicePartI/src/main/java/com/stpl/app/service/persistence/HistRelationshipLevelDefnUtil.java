package com.stpl.app.service.persistence;

import com.stpl.app.model.HistRelationshipLevelDefn;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the hist relationship level defn service. This utility wraps {@link HistRelationshipLevelDefnPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistRelationshipLevelDefnPersistence
 * @see HistRelationshipLevelDefnPersistenceImpl
 * @generated
 */
public class HistRelationshipLevelDefnUtil {
    private static HistRelationshipLevelDefnPersistence _persistence;

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
    public static void clearCache(
        HistRelationshipLevelDefn histRelationshipLevelDefn) {
        getPersistence().clearCache(histRelationshipLevelDefn);
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
    public static List<HistRelationshipLevelDefn> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<HistRelationshipLevelDefn> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<HistRelationshipLevelDefn> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static HistRelationshipLevelDefn update(
        HistRelationshipLevelDefn histRelationshipLevelDefn)
        throws SystemException {
        return getPersistence().update(histRelationshipLevelDefn);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static HistRelationshipLevelDefn update(
        HistRelationshipLevelDefn histRelationshipLevelDefn,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(histRelationshipLevelDefn, serviceContext);
    }

    /**
    * Caches the hist relationship level defn in the entity cache if it is enabled.
    *
    * @param histRelationshipLevelDefn the hist relationship level defn
    */
    public static void cacheResult(
        com.stpl.app.model.HistRelationshipLevelDefn histRelationshipLevelDefn) {
        getPersistence().cacheResult(histRelationshipLevelDefn);
    }

    /**
    * Caches the hist relationship level defns in the entity cache if it is enabled.
    *
    * @param histRelationshipLevelDefns the hist relationship level defns
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.HistRelationshipLevelDefn> histRelationshipLevelDefns) {
        getPersistence().cacheResult(histRelationshipLevelDefns);
    }

    /**
    * Creates a new hist relationship level defn with the primary key. Does not add the hist relationship level defn to the database.
    *
    * @param histRelationshipLevelDefnPK the primary key for the new hist relationship level defn
    * @return the new hist relationship level defn
    */
    public static com.stpl.app.model.HistRelationshipLevelDefn create(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK) {
        return getPersistence().create(histRelationshipLevelDefnPK);
    }

    /**
    * Removes the hist relationship level defn with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
    * @return the hist relationship level defn that was removed
    * @throws com.stpl.app.NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistRelationshipLevelDefn remove(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
        throws com.stpl.app.NoSuchHistRelationshipLevelDefnException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(histRelationshipLevelDefnPK);
    }

    public static com.stpl.app.model.HistRelationshipLevelDefn updateImpl(
        com.stpl.app.model.HistRelationshipLevelDefn histRelationshipLevelDefn)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(histRelationshipLevelDefn);
    }

    /**
    * Returns the hist relationship level defn with the primary key or throws a {@link com.stpl.app.NoSuchHistRelationshipLevelDefnException} if it could not be found.
    *
    * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
    * @return the hist relationship level defn
    * @throws com.stpl.app.NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistRelationshipLevelDefn findByPrimaryKey(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
        throws com.stpl.app.NoSuchHistRelationshipLevelDefnException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(histRelationshipLevelDefnPK);
    }

    /**
    * Returns the hist relationship level defn with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
    * @return the hist relationship level defn, or <code>null</code> if a hist relationship level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistRelationshipLevelDefn fetchByPrimaryKey(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(histRelationshipLevelDefnPK);
    }

    /**
    * Returns all the hist relationship level defns.
    *
    * @return the hist relationship level defns
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistRelationshipLevelDefn> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the hist relationship level defns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist relationship level defns
    * @param end the upper bound of the range of hist relationship level defns (not inclusive)
    * @return the range of hist relationship level defns
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistRelationshipLevelDefn> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the hist relationship level defns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist relationship level defns
    * @param end the upper bound of the range of hist relationship level defns (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist relationship level defns
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistRelationshipLevelDefn> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the hist relationship level defns from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of hist relationship level defns.
    *
    * @return the number of hist relationship level defns
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static HistRelationshipLevelDefnPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (HistRelationshipLevelDefnPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HistRelationshipLevelDefnPersistence.class.getName());

            ReferenceRegistry.registerReference(HistRelationshipLevelDefnUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(HistRelationshipLevelDefnPersistence persistence) {
    }
}
