package com.stpl.app.service.persistence;

import com.stpl.app.model.StSelectionTable;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st selection table service. This utility wraps {@link StSelectionTablePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StSelectionTablePersistence
 * @see StSelectionTablePersistenceImpl
 * @generated
 */
public class StSelectionTableUtil {
    private static StSelectionTablePersistence _persistence;

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
    public static void clearCache(StSelectionTable stSelectionTable) {
        getPersistence().clearCache(stSelectionTable);
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
    public static List<StSelectionTable> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StSelectionTable> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StSelectionTable> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StSelectionTable update(StSelectionTable stSelectionTable)
        throws SystemException {
        return getPersistence().update(stSelectionTable);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StSelectionTable update(StSelectionTable stSelectionTable,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stSelectionTable, serviceContext);
    }

    /**
    * Caches the st selection table in the entity cache if it is enabled.
    *
    * @param stSelectionTable the st selection table
    */
    public static void cacheResult(
        com.stpl.app.model.StSelectionTable stSelectionTable) {
        getPersistence().cacheResult(stSelectionTable);
    }

    /**
    * Caches the st selection tables in the entity cache if it is enabled.
    *
    * @param stSelectionTables the st selection tables
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StSelectionTable> stSelectionTables) {
        getPersistence().cacheResult(stSelectionTables);
    }

    /**
    * Creates a new st selection table with the primary key. Does not add the st selection table to the database.
    *
    * @param stSelectionTablePK the primary key for the new st selection table
    * @return the new st selection table
    */
    public static com.stpl.app.model.StSelectionTable create(
        StSelectionTablePK stSelectionTablePK) {
        return getPersistence().create(stSelectionTablePK);
    }

    /**
    * Removes the st selection table with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stSelectionTablePK the primary key of the st selection table
    * @return the st selection table that was removed
    * @throws com.stpl.app.NoSuchStSelectionTableException if a st selection table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StSelectionTable remove(
        StSelectionTablePK stSelectionTablePK)
        throws com.stpl.app.NoSuchStSelectionTableException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stSelectionTablePK);
    }

    public static com.stpl.app.model.StSelectionTable updateImpl(
        com.stpl.app.model.StSelectionTable stSelectionTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stSelectionTable);
    }

    /**
    * Returns the st selection table with the primary key or throws a {@link com.stpl.app.NoSuchStSelectionTableException} if it could not be found.
    *
    * @param stSelectionTablePK the primary key of the st selection table
    * @return the st selection table
    * @throws com.stpl.app.NoSuchStSelectionTableException if a st selection table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StSelectionTable findByPrimaryKey(
        StSelectionTablePK stSelectionTablePK)
        throws com.stpl.app.NoSuchStSelectionTableException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stSelectionTablePK);
    }

    /**
    * Returns the st selection table with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stSelectionTablePK the primary key of the st selection table
    * @return the st selection table, or <code>null</code> if a st selection table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StSelectionTable fetchByPrimaryKey(
        StSelectionTablePK stSelectionTablePK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stSelectionTablePK);
    }

    /**
    * Returns all the st selection tables.
    *
    * @return the st selection tables
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StSelectionTable> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st selection tables.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st selection tables
    * @param end the upper bound of the range of st selection tables (not inclusive)
    * @return the range of st selection tables
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StSelectionTable> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st selection tables.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st selection tables
    * @param end the upper bound of the range of st selection tables (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st selection tables
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StSelectionTable> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st selection tables from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st selection tables.
    *
    * @return the number of st selection tables
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StSelectionTablePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StSelectionTablePersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StSelectionTablePersistence.class.getName());

            ReferenceRegistry.registerReference(StSelectionTableUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StSelectionTablePersistence persistence) {
    }
}
