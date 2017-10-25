package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmDiscountProjection;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st nm discount projection service. This utility wraps {@link StNmDiscountProjectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmDiscountProjectionPersistence
 * @see StNmDiscountProjectionPersistenceImpl
 * @generated
 */
public class StNmDiscountProjectionUtil {
    private static StNmDiscountProjectionPersistence _persistence;

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
    public static void clearCache(StNmDiscountProjection stNmDiscountProjection) {
        getPersistence().clearCache(stNmDiscountProjection);
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
    public static List<StNmDiscountProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StNmDiscountProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StNmDiscountProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StNmDiscountProjection update(
        StNmDiscountProjection stNmDiscountProjection)
        throws SystemException {
        return getPersistence().update(stNmDiscountProjection);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StNmDiscountProjection update(
        StNmDiscountProjection stNmDiscountProjection,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stNmDiscountProjection, serviceContext);
    }

    /**
    * Caches the st nm discount projection in the entity cache if it is enabled.
    *
    * @param stNmDiscountProjection the st nm discount projection
    */
    public static void cacheResult(
        com.stpl.app.model.StNmDiscountProjection stNmDiscountProjection) {
        getPersistence().cacheResult(stNmDiscountProjection);
    }

    /**
    * Caches the st nm discount projections in the entity cache if it is enabled.
    *
    * @param stNmDiscountProjections the st nm discount projections
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StNmDiscountProjection> stNmDiscountProjections) {
        getPersistence().cacheResult(stNmDiscountProjections);
    }

    /**
    * Creates a new st nm discount projection with the primary key. Does not add the st nm discount projection to the database.
    *
    * @param stNmDiscountProjectionPK the primary key for the new st nm discount projection
    * @return the new st nm discount projection
    */
    public static com.stpl.app.model.StNmDiscountProjection create(
        StNmDiscountProjectionPK stNmDiscountProjectionPK) {
        return getPersistence().create(stNmDiscountProjectionPK);
    }

    /**
    * Removes the st nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
    * @return the st nm discount projection that was removed
    * @throws com.stpl.app.NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmDiscountProjection remove(
        StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws com.stpl.app.NoSuchStNmDiscountProjectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stNmDiscountProjectionPK);
    }

    public static com.stpl.app.model.StNmDiscountProjection updateImpl(
        com.stpl.app.model.StNmDiscountProjection stNmDiscountProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stNmDiscountProjection);
    }

    /**
    * Returns the st nm discount projection with the primary key or throws a {@link com.stpl.app.NoSuchStNmDiscountProjectionException} if it could not be found.
    *
    * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
    * @return the st nm discount projection
    * @throws com.stpl.app.NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmDiscountProjection findByPrimaryKey(
        StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws com.stpl.app.NoSuchStNmDiscountProjectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stNmDiscountProjectionPK);
    }

    /**
    * Returns the st nm discount projection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
    * @return the st nm discount projection, or <code>null</code> if a st nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmDiscountProjection fetchByPrimaryKey(
        StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stNmDiscountProjectionPK);
    }

    /**
    * Returns all the st nm discount projections.
    *
    * @return the st nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmDiscountProjection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st nm discount projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm discount projections
    * @param end the upper bound of the range of st nm discount projections (not inclusive)
    * @return the range of st nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmDiscountProjection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st nm discount projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm discount projections
    * @param end the upper bound of the range of st nm discount projections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmDiscountProjection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st nm discount projections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st nm discount projections.
    *
    * @return the number of st nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StNmDiscountProjectionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StNmDiscountProjectionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StNmDiscountProjectionPersistence.class.getName());

            ReferenceRegistry.registerReference(StNmDiscountProjectionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StNmDiscountProjectionPersistence persistence) {
    }
}
