package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmActualPpa;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st nm actual ppa service. This utility wraps {@link StNmActualPpaPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmActualPpaPersistence
 * @see StNmActualPpaPersistenceImpl
 * @generated
 */
public class StNmActualPpaUtil {
    private static StNmActualPpaPersistence _persistence;

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
    public static void clearCache(StNmActualPpa stNmActualPpa) {
        getPersistence().clearCache(stNmActualPpa);
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
    public static List<StNmActualPpa> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StNmActualPpa> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StNmActualPpa> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StNmActualPpa update(StNmActualPpa stNmActualPpa)
        throws SystemException {
        return getPersistence().update(stNmActualPpa);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StNmActualPpa update(StNmActualPpa stNmActualPpa,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stNmActualPpa, serviceContext);
    }

    /**
    * Caches the st nm actual ppa in the entity cache if it is enabled.
    *
    * @param stNmActualPpa the st nm actual ppa
    */
    public static void cacheResult(
        com.stpl.app.model.StNmActualPpa stNmActualPpa) {
        getPersistence().cacheResult(stNmActualPpa);
    }

    /**
    * Caches the st nm actual ppas in the entity cache if it is enabled.
    *
    * @param stNmActualPpas the st nm actual ppas
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StNmActualPpa> stNmActualPpas) {
        getPersistence().cacheResult(stNmActualPpas);
    }

    /**
    * Creates a new st nm actual ppa with the primary key. Does not add the st nm actual ppa to the database.
    *
    * @param stNmActualPpaPK the primary key for the new st nm actual ppa
    * @return the new st nm actual ppa
    */
    public static com.stpl.app.model.StNmActualPpa create(
        StNmActualPpaPK stNmActualPpaPK) {
        return getPersistence().create(stNmActualPpaPK);
    }

    /**
    * Removes the st nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmActualPpaPK the primary key of the st nm actual ppa
    * @return the st nm actual ppa that was removed
    * @throws com.stpl.app.NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmActualPpa remove(
        StNmActualPpaPK stNmActualPpaPK)
        throws com.stpl.app.NoSuchStNmActualPpaException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stNmActualPpaPK);
    }

    public static com.stpl.app.model.StNmActualPpa updateImpl(
        com.stpl.app.model.StNmActualPpa stNmActualPpa)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stNmActualPpa);
    }

    /**
    * Returns the st nm actual ppa with the primary key or throws a {@link com.stpl.app.NoSuchStNmActualPpaException} if it could not be found.
    *
    * @param stNmActualPpaPK the primary key of the st nm actual ppa
    * @return the st nm actual ppa
    * @throws com.stpl.app.NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmActualPpa findByPrimaryKey(
        StNmActualPpaPK stNmActualPpaPK)
        throws com.stpl.app.NoSuchStNmActualPpaException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stNmActualPpaPK);
    }

    /**
    * Returns the st nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmActualPpaPK the primary key of the st nm actual ppa
    * @return the st nm actual ppa, or <code>null</code> if a st nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmActualPpa fetchByPrimaryKey(
        StNmActualPpaPK stNmActualPpaPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stNmActualPpaPK);
    }

    /**
    * Returns all the st nm actual ppas.
    *
    * @return the st nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmActualPpa> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st nm actual ppas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm actual ppas
    * @param end the upper bound of the range of st nm actual ppas (not inclusive)
    * @return the range of st nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmActualPpa> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st nm actual ppas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm actual ppas
    * @param end the upper bound of the range of st nm actual ppas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmActualPpa> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st nm actual ppas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st nm actual ppas.
    *
    * @return the number of st nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StNmActualPpaPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StNmActualPpaPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StNmActualPpaPersistence.class.getName());

            ReferenceRegistry.registerReference(StNmActualPpaUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StNmActualPpaPersistence persistence) {
    }
}
