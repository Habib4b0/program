package com.stpl.app.service.persistence;

import com.stpl.app.model.NmPpaProjection;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the nm ppa projection service. This utility wraps {@link NmPpaProjectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmPpaProjectionPersistence
 * @see NmPpaProjectionPersistenceImpl
 * @generated
 */
public class NmPpaProjectionUtil {
    private static NmPpaProjectionPersistence _persistence;

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
    public static void clearCache(NmPpaProjection nmPpaProjection) {
        getPersistence().clearCache(nmPpaProjection);
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
    public static List<NmPpaProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<NmPpaProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<NmPpaProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static NmPpaProjection update(NmPpaProjection nmPpaProjection)
        throws SystemException {
        return getPersistence().update(nmPpaProjection);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static NmPpaProjection update(NmPpaProjection nmPpaProjection,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(nmPpaProjection, serviceContext);
    }

    /**
    * Caches the nm ppa projection in the entity cache if it is enabled.
    *
    * @param nmPpaProjection the nm ppa projection
    */
    public static void cacheResult(
        com.stpl.app.model.NmPpaProjection nmPpaProjection) {
        getPersistence().cacheResult(nmPpaProjection);
    }

    /**
    * Caches the nm ppa projections in the entity cache if it is enabled.
    *
    * @param nmPpaProjections the nm ppa projections
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.NmPpaProjection> nmPpaProjections) {
        getPersistence().cacheResult(nmPpaProjections);
    }

    /**
    * Creates a new nm ppa projection with the primary key. Does not add the nm ppa projection to the database.
    *
    * @param nmPpaProjectionPK the primary key for the new nm ppa projection
    * @return the new nm ppa projection
    */
    public static com.stpl.app.model.NmPpaProjection create(
        NmPpaProjectionPK nmPpaProjectionPK) {
        return getPersistence().create(nmPpaProjectionPK);
    }

    /**
    * Removes the nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmPpaProjectionPK the primary key of the nm ppa projection
    * @return the nm ppa projection that was removed
    * @throws com.stpl.app.NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmPpaProjection remove(
        NmPpaProjectionPK nmPpaProjectionPK)
        throws com.stpl.app.NoSuchNmPpaProjectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(nmPpaProjectionPK);
    }

    public static com.stpl.app.model.NmPpaProjection updateImpl(
        com.stpl.app.model.NmPpaProjection nmPpaProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(nmPpaProjection);
    }

    /**
    * Returns the nm ppa projection with the primary key or throws a {@link com.stpl.app.NoSuchNmPpaProjectionException} if it could not be found.
    *
    * @param nmPpaProjectionPK the primary key of the nm ppa projection
    * @return the nm ppa projection
    * @throws com.stpl.app.NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmPpaProjection findByPrimaryKey(
        NmPpaProjectionPK nmPpaProjectionPK)
        throws com.stpl.app.NoSuchNmPpaProjectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(nmPpaProjectionPK);
    }

    /**
    * Returns the nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nmPpaProjectionPK the primary key of the nm ppa projection
    * @return the nm ppa projection, or <code>null</code> if a nm ppa projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmPpaProjection fetchByPrimaryKey(
        NmPpaProjectionPK nmPpaProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(nmPpaProjectionPK);
    }

    /**
    * Returns all the nm ppa projections.
    *
    * @return the nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmPpaProjection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the nm ppa projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm ppa projections
    * @param end the upper bound of the range of nm ppa projections (not inclusive)
    * @return the range of nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmPpaProjection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the nm ppa projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm ppa projections
    * @param end the upper bound of the range of nm ppa projections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmPpaProjection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the nm ppa projections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of nm ppa projections.
    *
    * @return the number of nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static NmPpaProjectionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (NmPpaProjectionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NmPpaProjectionPersistence.class.getName());

            ReferenceRegistry.registerReference(NmPpaProjectionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(NmPpaProjectionPersistence persistence) {
    }
}
