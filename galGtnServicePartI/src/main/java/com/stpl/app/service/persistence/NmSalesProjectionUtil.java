package com.stpl.app.service.persistence;

import com.stpl.app.model.NmSalesProjection;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the nm sales projection service. This utility wraps {@link NmSalesProjectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmSalesProjectionPersistence
 * @see NmSalesProjectionPersistenceImpl
 * @generated
 */
public class NmSalesProjectionUtil {
    private static NmSalesProjectionPersistence _persistence;

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
    public static void clearCache(NmSalesProjection nmSalesProjection) {
        getPersistence().clearCache(nmSalesProjection);
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
    public static List<NmSalesProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<NmSalesProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<NmSalesProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static NmSalesProjection update(NmSalesProjection nmSalesProjection)
        throws SystemException {
        return getPersistence().update(nmSalesProjection);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static NmSalesProjection update(
        NmSalesProjection nmSalesProjection, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(nmSalesProjection, serviceContext);
    }

    /**
    * Caches the nm sales projection in the entity cache if it is enabled.
    *
    * @param nmSalesProjection the nm sales projection
    */
    public static void cacheResult(
        com.stpl.app.model.NmSalesProjection nmSalesProjection) {
        getPersistence().cacheResult(nmSalesProjection);
    }

    /**
    * Caches the nm sales projections in the entity cache if it is enabled.
    *
    * @param nmSalesProjections the nm sales projections
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.NmSalesProjection> nmSalesProjections) {
        getPersistence().cacheResult(nmSalesProjections);
    }

    /**
    * Creates a new nm sales projection with the primary key. Does not add the nm sales projection to the database.
    *
    * @param nmSalesProjectionPK the primary key for the new nm sales projection
    * @return the new nm sales projection
    */
    public static com.stpl.app.model.NmSalesProjection create(
        NmSalesProjectionPK nmSalesProjectionPK) {
        return getPersistence().create(nmSalesProjectionPK);
    }

    /**
    * Removes the nm sales projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjectionPK the primary key of the nm sales projection
    * @return the nm sales projection that was removed
    * @throws com.stpl.app.NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmSalesProjection remove(
        NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.app.NoSuchNmSalesProjectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(nmSalesProjectionPK);
    }

    public static com.stpl.app.model.NmSalesProjection updateImpl(
        com.stpl.app.model.NmSalesProjection nmSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(nmSalesProjection);
    }

    /**
    * Returns the nm sales projection with the primary key or throws a {@link com.stpl.app.NoSuchNmSalesProjectionException} if it could not be found.
    *
    * @param nmSalesProjectionPK the primary key of the nm sales projection
    * @return the nm sales projection
    * @throws com.stpl.app.NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmSalesProjection findByPrimaryKey(
        NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.app.NoSuchNmSalesProjectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(nmSalesProjectionPK);
    }

    /**
    * Returns the nm sales projection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nmSalesProjectionPK the primary key of the nm sales projection
    * @return the nm sales projection, or <code>null</code> if a nm sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmSalesProjection fetchByPrimaryKey(
        NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(nmSalesProjectionPK);
    }

    /**
    * Returns all the nm sales projections.
    *
    * @return the nm sales projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmSalesProjection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the nm sales projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm sales projections
    * @param end the upper bound of the range of nm sales projections (not inclusive)
    * @return the range of nm sales projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmSalesProjection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the nm sales projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm sales projections
    * @param end the upper bound of the range of nm sales projections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm sales projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmSalesProjection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the nm sales projections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of nm sales projections.
    *
    * @return the number of nm sales projections
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static NmSalesProjectionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (NmSalesProjectionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NmSalesProjectionPersistence.class.getName());

            ReferenceRegistry.registerReference(NmSalesProjectionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(NmSalesProjectionPersistence persistence) {
    }
}
