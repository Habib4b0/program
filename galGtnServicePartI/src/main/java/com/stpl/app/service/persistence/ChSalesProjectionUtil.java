package com.stpl.app.service.persistence;

import com.stpl.app.model.ChSalesProjection;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ch sales projection service. This utility wraps {@link ChSalesProjectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChSalesProjectionPersistence
 * @see ChSalesProjectionPersistenceImpl
 * @generated
 */
public class ChSalesProjectionUtil {
    private static ChSalesProjectionPersistence _persistence;

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
    public static void clearCache(ChSalesProjection chSalesProjection) {
        getPersistence().clearCache(chSalesProjection);
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
    public static List<ChSalesProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ChSalesProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ChSalesProjection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ChSalesProjection update(ChSalesProjection chSalesProjection)
        throws SystemException {
        return getPersistence().update(chSalesProjection);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ChSalesProjection update(
        ChSalesProjection chSalesProjection, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(chSalesProjection, serviceContext);
    }

    /**
    * Caches the ch sales projection in the entity cache if it is enabled.
    *
    * @param chSalesProjection the ch sales projection
    */
    public static void cacheResult(
        com.stpl.app.model.ChSalesProjection chSalesProjection) {
        getPersistence().cacheResult(chSalesProjection);
    }

    /**
    * Caches the ch sales projections in the entity cache if it is enabled.
    *
    * @param chSalesProjections the ch sales projections
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ChSalesProjection> chSalesProjections) {
        getPersistence().cacheResult(chSalesProjections);
    }

    /**
    * Creates a new ch sales projection with the primary key. Does not add the ch sales projection to the database.
    *
    * @param chSalesProjectionPK the primary key for the new ch sales projection
    * @return the new ch sales projection
    */
    public static com.stpl.app.model.ChSalesProjection create(
        ChSalesProjectionPK chSalesProjectionPK) {
        return getPersistence().create(chSalesProjectionPK);
    }

    /**
    * Removes the ch sales projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chSalesProjectionPK the primary key of the ch sales projection
    * @return the ch sales projection that was removed
    * @throws com.stpl.app.NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChSalesProjection remove(
        ChSalesProjectionPK chSalesProjectionPK)
        throws com.stpl.app.NoSuchChSalesProjectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(chSalesProjectionPK);
    }

    public static com.stpl.app.model.ChSalesProjection updateImpl(
        com.stpl.app.model.ChSalesProjection chSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(chSalesProjection);
    }

    /**
    * Returns the ch sales projection with the primary key or throws a {@link com.stpl.app.NoSuchChSalesProjectionException} if it could not be found.
    *
    * @param chSalesProjectionPK the primary key of the ch sales projection
    * @return the ch sales projection
    * @throws com.stpl.app.NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChSalesProjection findByPrimaryKey(
        ChSalesProjectionPK chSalesProjectionPK)
        throws com.stpl.app.NoSuchChSalesProjectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(chSalesProjectionPK);
    }

    /**
    * Returns the ch sales projection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param chSalesProjectionPK the primary key of the ch sales projection
    * @return the ch sales projection, or <code>null</code> if a ch sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChSalesProjection fetchByPrimaryKey(
        ChSalesProjectionPK chSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(chSalesProjectionPK);
    }

    /**
    * Returns all the ch sales projections.
    *
    * @return the ch sales projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChSalesProjection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ch sales projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch sales projections
    * @param end the upper bound of the range of ch sales projections (not inclusive)
    * @return the range of ch sales projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChSalesProjection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ch sales projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch sales projections
    * @param end the upper bound of the range of ch sales projections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch sales projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChSalesProjection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ch sales projections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ch sales projections.
    *
    * @return the number of ch sales projections
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ChSalesProjectionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ChSalesProjectionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ChSalesProjectionPersistence.class.getName());

            ReferenceRegistry.registerReference(ChSalesProjectionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ChSalesProjectionPersistence persistence) {
    }
}
