package com.stpl.app.service.persistence;

import com.stpl.app.model.HierarchyLevelValues;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the hierarchy level values service. This utility wraps {@link HierarchyLevelValuesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyLevelValuesPersistence
 * @see HierarchyLevelValuesPersistenceImpl
 * @generated
 */
public class HierarchyLevelValuesUtil {
    private static HierarchyLevelValuesPersistence _persistence;

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
    public static void clearCache(HierarchyLevelValues hierarchyLevelValues) {
        getPersistence().clearCache(hierarchyLevelValues);
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
    public static List<HierarchyLevelValues> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<HierarchyLevelValues> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<HierarchyLevelValues> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static HierarchyLevelValues update(
        HierarchyLevelValues hierarchyLevelValues) throws SystemException {
        return getPersistence().update(hierarchyLevelValues);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static HierarchyLevelValues update(
        HierarchyLevelValues hierarchyLevelValues, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(hierarchyLevelValues, serviceContext);
    }

    /**
    * Caches the hierarchy level values in the entity cache if it is enabled.
    *
    * @param hierarchyLevelValues the hierarchy level values
    */
    public static void cacheResult(
        com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues) {
        getPersistence().cacheResult(hierarchyLevelValues);
    }

    /**
    * Caches the hierarchy level valueses in the entity cache if it is enabled.
    *
    * @param hierarchyLevelValueses the hierarchy level valueses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.HierarchyLevelValues> hierarchyLevelValueses) {
        getPersistence().cacheResult(hierarchyLevelValueses);
    }

    /**
    * Creates a new hierarchy level values with the primary key. Does not add the hierarchy level values to the database.
    *
    * @param hierarchyLevelValuesSid the primary key for the new hierarchy level values
    * @return the new hierarchy level values
    */
    public static com.stpl.app.model.HierarchyLevelValues create(
        int hierarchyLevelValuesSid) {
        return getPersistence().create(hierarchyLevelValuesSid);
    }

    /**
    * Removes the hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
    * @return the hierarchy level values that was removed
    * @throws com.stpl.app.NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyLevelValues remove(
        int hierarchyLevelValuesSid)
        throws com.stpl.app.NoSuchHierarchyLevelValuesException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(hierarchyLevelValuesSid);
    }

    public static com.stpl.app.model.HierarchyLevelValues updateImpl(
        com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(hierarchyLevelValues);
    }

    /**
    * Returns the hierarchy level values with the primary key or throws a {@link com.stpl.app.NoSuchHierarchyLevelValuesException} if it could not be found.
    *
    * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
    * @return the hierarchy level values
    * @throws com.stpl.app.NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyLevelValues findByPrimaryKey(
        int hierarchyLevelValuesSid)
        throws com.stpl.app.NoSuchHierarchyLevelValuesException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(hierarchyLevelValuesSid);
    }

    /**
    * Returns the hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
    * @return the hierarchy level values, or <code>null</code> if a hierarchy level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyLevelValues fetchByPrimaryKey(
        int hierarchyLevelValuesSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(hierarchyLevelValuesSid);
    }

    /**
    * Returns all the hierarchy level valueses.
    *
    * @return the hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HierarchyLevelValues> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the hierarchy level valueses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy level valueses
    * @param end the upper bound of the range of hierarchy level valueses (not inclusive)
    * @return the range of hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HierarchyLevelValues> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the hierarchy level valueses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy level valueses
    * @param end the upper bound of the range of hierarchy level valueses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HierarchyLevelValues> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the hierarchy level valueses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of hierarchy level valueses.
    *
    * @return the number of hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static HierarchyLevelValuesPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (HierarchyLevelValuesPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HierarchyLevelValuesPersistence.class.getName());

            ReferenceRegistry.registerReference(HierarchyLevelValuesUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(HierarchyLevelValuesPersistence persistence) {
    }
}
