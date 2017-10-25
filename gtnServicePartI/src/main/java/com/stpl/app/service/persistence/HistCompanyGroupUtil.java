package com.stpl.app.service.persistence;

import com.stpl.app.model.HistCompanyGroup;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the hist company group service. This utility wraps {@link HistCompanyGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistCompanyGroupPersistence
 * @see HistCompanyGroupPersistenceImpl
 * @generated
 */
public class HistCompanyGroupUtil {
    private static HistCompanyGroupPersistence _persistence;

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
    public static void clearCache(HistCompanyGroup histCompanyGroup) {
        getPersistence().clearCache(histCompanyGroup);
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
    public static List<HistCompanyGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<HistCompanyGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<HistCompanyGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static HistCompanyGroup update(HistCompanyGroup histCompanyGroup)
        throws SystemException {
        return getPersistence().update(histCompanyGroup);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static HistCompanyGroup update(HistCompanyGroup histCompanyGroup,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(histCompanyGroup, serviceContext);
    }

    /**
    * Caches the hist company group in the entity cache if it is enabled.
    *
    * @param histCompanyGroup the hist company group
    */
    public static void cacheResult(
        com.stpl.app.model.HistCompanyGroup histCompanyGroup) {
        getPersistence().cacheResult(histCompanyGroup);
    }

    /**
    * Caches the hist company groups in the entity cache if it is enabled.
    *
    * @param histCompanyGroups the hist company groups
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.HistCompanyGroup> histCompanyGroups) {
        getPersistence().cacheResult(histCompanyGroups);
    }

    /**
    * Creates a new hist company group with the primary key. Does not add the hist company group to the database.
    *
    * @param histCompanyGroupPK the primary key for the new hist company group
    * @return the new hist company group
    */
    public static com.stpl.app.model.HistCompanyGroup create(
        HistCompanyGroupPK histCompanyGroupPK) {
        return getPersistence().create(histCompanyGroupPK);
    }

    /**
    * Removes the hist company group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroupPK the primary key of the hist company group
    * @return the hist company group that was removed
    * @throws com.stpl.app.NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistCompanyGroup remove(
        HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.app.NoSuchHistCompanyGroupException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(histCompanyGroupPK);
    }

    public static com.stpl.app.model.HistCompanyGroup updateImpl(
        com.stpl.app.model.HistCompanyGroup histCompanyGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(histCompanyGroup);
    }

    /**
    * Returns the hist company group with the primary key or throws a {@link com.stpl.app.NoSuchHistCompanyGroupException} if it could not be found.
    *
    * @param histCompanyGroupPK the primary key of the hist company group
    * @return the hist company group
    * @throws com.stpl.app.NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistCompanyGroup findByPrimaryKey(
        HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.app.NoSuchHistCompanyGroupException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(histCompanyGroupPK);
    }

    /**
    * Returns the hist company group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histCompanyGroupPK the primary key of the hist company group
    * @return the hist company group, or <code>null</code> if a hist company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistCompanyGroup fetchByPrimaryKey(
        HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(histCompanyGroupPK);
    }

    /**
    * Returns all the hist company groups.
    *
    * @return the hist company groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistCompanyGroup> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the hist company groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist company groups
    * @param end the upper bound of the range of hist company groups (not inclusive)
    * @return the range of hist company groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistCompanyGroup> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the hist company groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist company groups
    * @param end the upper bound of the range of hist company groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist company groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistCompanyGroup> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the hist company groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of hist company groups.
    *
    * @return the number of hist company groups
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static HistCompanyGroupPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (HistCompanyGroupPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HistCompanyGroupPersistence.class.getName());

            ReferenceRegistry.registerReference(HistCompanyGroupUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(HistCompanyGroupPersistence persistence) {
    }
}
