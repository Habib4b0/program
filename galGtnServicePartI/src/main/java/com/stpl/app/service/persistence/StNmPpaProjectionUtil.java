package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmPpaProjection;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st nm ppa projection service. This utility wraps {@link StNmPpaProjectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmPpaProjectionPersistence
 * @see StNmPpaProjectionPersistenceImpl
 * @generated
 */
public class StNmPpaProjectionUtil {
    private static StNmPpaProjectionPersistence _persistence;

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
    public static void clearCache(StNmPpaProjection stNmPpaProjection) {
        getPersistence().clearCache(stNmPpaProjection);
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
    public static List<StNmPpaProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StNmPpaProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StNmPpaProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StNmPpaProjection update(StNmPpaProjection stNmPpaProjection)
        throws SystemException {
        return getPersistence().update(stNmPpaProjection);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StNmPpaProjection update(
        StNmPpaProjection stNmPpaProjection, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(stNmPpaProjection, serviceContext);
    }

    /**
    * Caches the st nm ppa projection in the entity cache if it is enabled.
    *
    * @param stNmPpaProjection the st nm ppa projection
    */
    public static void cacheResult(
        com.stpl.app.model.StNmPpaProjection stNmPpaProjection) {
        getPersistence().cacheResult(stNmPpaProjection);
    }

    /**
    * Caches the st nm ppa projections in the entity cache if it is enabled.
    *
    * @param stNmPpaProjections the st nm ppa projections
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StNmPpaProjection> stNmPpaProjections) {
        getPersistence().cacheResult(stNmPpaProjections);
    }

    /**
    * Creates a new st nm ppa projection with the primary key. Does not add the st nm ppa projection to the database.
    *
    * @param stNmPpaProjectionPK the primary key for the new st nm ppa projection
    * @return the new st nm ppa projection
    */
    public static com.stpl.app.model.StNmPpaProjection create(
        StNmPpaProjectionPK stNmPpaProjectionPK) {
        return getPersistence().create(stNmPpaProjectionPK);
    }

    /**
    * Removes the st nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmPpaProjectionPK the primary key of the st nm ppa projection
    * @return the st nm ppa projection that was removed
    * @throws com.stpl.app.NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmPpaProjection remove(
        StNmPpaProjectionPK stNmPpaProjectionPK)
        throws com.stpl.app.NoSuchStNmPpaProjectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stNmPpaProjectionPK);
    }

    public static com.stpl.app.model.StNmPpaProjection updateImpl(
        com.stpl.app.model.StNmPpaProjection stNmPpaProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stNmPpaProjection);
    }

    /**
    * Returns the st nm ppa projection with the primary key or throws a {@link com.stpl.app.NoSuchStNmPpaProjectionException} if it could not be found.
    *
    * @param stNmPpaProjectionPK the primary key of the st nm ppa projection
    * @return the st nm ppa projection
    * @throws com.stpl.app.NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmPpaProjection findByPrimaryKey(
        StNmPpaProjectionPK stNmPpaProjectionPK)
        throws com.stpl.app.NoSuchStNmPpaProjectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stNmPpaProjectionPK);
    }

    /**
    * Returns the st nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmPpaProjectionPK the primary key of the st nm ppa projection
    * @return the st nm ppa projection, or <code>null</code> if a st nm ppa projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmPpaProjection fetchByPrimaryKey(
        StNmPpaProjectionPK stNmPpaProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stNmPpaProjectionPK);
    }

    /**
    * Returns all the st nm ppa projections.
    *
    * @return the st nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmPpaProjection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st nm ppa projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm ppa projections
    * @param end the upper bound of the range of st nm ppa projections (not inclusive)
    * @return the range of st nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmPpaProjection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st nm ppa projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm ppa projections
    * @param end the upper bound of the range of st nm ppa projections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmPpaProjection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st nm ppa projections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st nm ppa projections.
    *
    * @return the number of st nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StNmPpaProjectionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StNmPpaProjectionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StNmPpaProjectionPersistence.class.getName());

            ReferenceRegistry.registerReference(StNmPpaProjectionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StNmPpaProjectionPersistence persistence) {
    }
}
