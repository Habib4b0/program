package com.stpl.app.service.persistence;

import com.stpl.app.model.HistItemGroup;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the hist item group service. This utility wraps {@link HistItemGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistItemGroupPersistence
 * @see HistItemGroupPersistenceImpl
 * @generated
 */
public class HistItemGroupUtil {
    private static HistItemGroupPersistence _persistence;

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
    public static void clearCache(HistItemGroup histItemGroup) {
        getPersistence().clearCache(histItemGroup);
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
    public static List<HistItemGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<HistItemGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<HistItemGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static HistItemGroup update(HistItemGroup histItemGroup)
        throws SystemException {
        return getPersistence().update(histItemGroup);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static HistItemGroup update(HistItemGroup histItemGroup,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(histItemGroup, serviceContext);
    }

    /**
    * Caches the hist item group in the entity cache if it is enabled.
    *
    * @param histItemGroup the hist item group
    */
    public static void cacheResult(
        com.stpl.app.model.HistItemGroup histItemGroup) {
        getPersistence().cacheResult(histItemGroup);
    }

    /**
    * Caches the hist item groups in the entity cache if it is enabled.
    *
    * @param histItemGroups the hist item groups
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.HistItemGroup> histItemGroups) {
        getPersistence().cacheResult(histItemGroups);
    }

    /**
    * Creates a new hist item group with the primary key. Does not add the hist item group to the database.
    *
    * @param histItemGroupPK the primary key for the new hist item group
    * @return the new hist item group
    */
    public static com.stpl.app.model.HistItemGroup create(
        HistItemGroupPK histItemGroupPK) {
        return getPersistence().create(histItemGroupPK);
    }

    /**
    * Removes the hist item group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histItemGroupPK the primary key of the hist item group
    * @return the hist item group that was removed
    * @throws com.stpl.app.NoSuchHistItemGroupException if a hist item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistItemGroup remove(
        HistItemGroupPK histItemGroupPK)
        throws com.stpl.app.NoSuchHistItemGroupException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(histItemGroupPK);
    }

    public static com.stpl.app.model.HistItemGroup updateImpl(
        com.stpl.app.model.HistItemGroup histItemGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(histItemGroup);
    }

    /**
    * Returns the hist item group with the primary key or throws a {@link com.stpl.app.NoSuchHistItemGroupException} if it could not be found.
    *
    * @param histItemGroupPK the primary key of the hist item group
    * @return the hist item group
    * @throws com.stpl.app.NoSuchHistItemGroupException if a hist item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistItemGroup findByPrimaryKey(
        HistItemGroupPK histItemGroupPK)
        throws com.stpl.app.NoSuchHistItemGroupException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(histItemGroupPK);
    }

    /**
    * Returns the hist item group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histItemGroupPK the primary key of the hist item group
    * @return the hist item group, or <code>null</code> if a hist item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistItemGroup fetchByPrimaryKey(
        HistItemGroupPK histItemGroupPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(histItemGroupPK);
    }

    /**
    * Returns all the hist item groups.
    *
    * @return the hist item groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistItemGroup> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the hist item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist item groups
    * @param end the upper bound of the range of hist item groups (not inclusive)
    * @return the range of hist item groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistItemGroup> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the hist item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist item groups
    * @param end the upper bound of the range of hist item groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist item groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistItemGroup> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the hist item groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of hist item groups.
    *
    * @return the number of hist item groups
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static HistItemGroupPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (HistItemGroupPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HistItemGroupPersistence.class.getName());

            ReferenceRegistry.registerReference(HistItemGroupUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(HistItemGroupPersistence persistence) {
    }
}
