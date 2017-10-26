package com.stpl.app.service.persistence;

import com.stpl.app.model.VwUserTables;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw user tables service. This utility wraps {@link VwUserTablesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwUserTablesPersistence
 * @see VwUserTablesPersistenceImpl
 * @generated
 */
public class VwUserTablesUtil {
    private static VwUserTablesPersistence _persistence;

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
    public static void clearCache(VwUserTables vwUserTables) {
        getPersistence().clearCache(vwUserTables);
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
    public static List<VwUserTables> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwUserTables> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwUserTables> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwUserTables update(VwUserTables vwUserTables)
        throws SystemException {
        return getPersistence().update(vwUserTables);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwUserTables update(VwUserTables vwUserTables,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(vwUserTables, serviceContext);
    }

    /**
    * Caches the vw user tables in the entity cache if it is enabled.
    *
    * @param vwUserTables the vw user tables
    */
    public static void cacheResult(com.stpl.app.model.VwUserTables vwUserTables) {
        getPersistence().cacheResult(vwUserTables);
    }

    /**
    * Caches the vw user tableses in the entity cache if it is enabled.
    *
    * @param vwUserTableses the vw user tableses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.VwUserTables> vwUserTableses) {
        getPersistence().cacheResult(vwUserTableses);
    }

    /**
    * Creates a new vw user tables with the primary key. Does not add the vw user tables to the database.
    *
    * @param uniqueId the primary key for the new vw user tables
    * @return the new vw user tables
    */
    public static com.stpl.app.model.VwUserTables create(int uniqueId) {
        return getPersistence().create(uniqueId);
    }

    /**
    * Removes the vw user tables with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param uniqueId the primary key of the vw user tables
    * @return the vw user tables that was removed
    * @throws com.stpl.app.NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwUserTables remove(int uniqueId)
        throws com.stpl.app.NoSuchVwUserTablesException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(uniqueId);
    }

    public static com.stpl.app.model.VwUserTables updateImpl(
        com.stpl.app.model.VwUserTables vwUserTables)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwUserTables);
    }

    /**
    * Returns the vw user tables with the primary key or throws a {@link com.stpl.app.NoSuchVwUserTablesException} if it could not be found.
    *
    * @param uniqueId the primary key of the vw user tables
    * @return the vw user tables
    * @throws com.stpl.app.NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwUserTables findByPrimaryKey(int uniqueId)
        throws com.stpl.app.NoSuchVwUserTablesException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(uniqueId);
    }

    /**
    * Returns the vw user tables with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param uniqueId the primary key of the vw user tables
    * @return the vw user tables, or <code>null</code> if a vw user tables with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwUserTables fetchByPrimaryKey(
        int uniqueId) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(uniqueId);
    }

    /**
    * Returns all the vw user tableses.
    *
    * @return the vw user tableses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.VwUserTables> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the vw user tableses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw user tableses
    * @param end the upper bound of the range of vw user tableses (not inclusive)
    * @return the range of vw user tableses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.VwUserTables> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the vw user tableses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw user tableses
    * @param end the upper bound of the range of vw user tableses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw user tableses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.VwUserTables> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw user tableses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw user tableses.
    *
    * @return the number of vw user tableses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwUserTablesPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwUserTablesPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    VwUserTablesPersistence.class.getName());

            ReferenceRegistry.registerReference(VwUserTablesUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwUserTablesPersistence persistence) {
    }
}
