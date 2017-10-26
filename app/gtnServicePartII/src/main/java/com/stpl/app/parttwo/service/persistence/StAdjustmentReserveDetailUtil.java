package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StAdjustmentReserveDetail;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st adjustment reserve detail service. This utility wraps {@link StAdjustmentReserveDetailPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAdjustmentReserveDetailPersistence
 * @see StAdjustmentReserveDetailPersistenceImpl
 * @generated
 */
public class StAdjustmentReserveDetailUtil {
    private static StAdjustmentReserveDetailPersistence _persistence;

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
    public static void clearCache(
        StAdjustmentReserveDetail stAdjustmentReserveDetail) {
        getPersistence().clearCache(stAdjustmentReserveDetail);
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
    public static List<StAdjustmentReserveDetail> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StAdjustmentReserveDetail> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StAdjustmentReserveDetail> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StAdjustmentReserveDetail update(
        StAdjustmentReserveDetail stAdjustmentReserveDetail)
        throws SystemException {
        return getPersistence().update(stAdjustmentReserveDetail);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StAdjustmentReserveDetail update(
        StAdjustmentReserveDetail stAdjustmentReserveDetail,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stAdjustmentReserveDetail, serviceContext);
    }

    /**
    * Caches the st adjustment reserve detail in the entity cache if it is enabled.
    *
    * @param stAdjustmentReserveDetail the st adjustment reserve detail
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.StAdjustmentReserveDetail stAdjustmentReserveDetail) {
        getPersistence().cacheResult(stAdjustmentReserveDetail);
    }

    /**
    * Caches the st adjustment reserve details in the entity cache if it is enabled.
    *
    * @param stAdjustmentReserveDetails the st adjustment reserve details
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.StAdjustmentReserveDetail> stAdjustmentReserveDetails) {
        getPersistence().cacheResult(stAdjustmentReserveDetails);
    }

    /**
    * Creates a new st adjustment reserve detail with the primary key. Does not add the st adjustment reserve detail to the database.
    *
    * @param stAdjustmentReserveDetailPK the primary key for the new st adjustment reserve detail
    * @return the new st adjustment reserve detail
    */
    public static com.stpl.app.parttwo.model.StAdjustmentReserveDetail create(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK) {
        return getPersistence().create(stAdjustmentReserveDetailPK);
    }

    /**
    * Removes the st adjustment reserve detail with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentReserveDetailPK the primary key of the st adjustment reserve detail
    * @return the st adjustment reserve detail that was removed
    * @throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAdjustmentReserveDetail remove(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stAdjustmentReserveDetailPK);
    }

    public static com.stpl.app.parttwo.model.StAdjustmentReserveDetail updateImpl(
        com.stpl.app.parttwo.model.StAdjustmentReserveDetail stAdjustmentReserveDetail)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stAdjustmentReserveDetail);
    }

    /**
    * Returns the st adjustment reserve detail with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException} if it could not be found.
    *
    * @param stAdjustmentReserveDetailPK the primary key of the st adjustment reserve detail
    * @return the st adjustment reserve detail
    * @throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAdjustmentReserveDetail findByPrimaryKey(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stAdjustmentReserveDetailPK);
    }

    /**
    * Returns the st adjustment reserve detail with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stAdjustmentReserveDetailPK the primary key of the st adjustment reserve detail
    * @return the st adjustment reserve detail, or <code>null</code> if a st adjustment reserve detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAdjustmentReserveDetail fetchByPrimaryKey(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stAdjustmentReserveDetailPK);
    }

    /**
    * Returns all the st adjustment reserve details.
    *
    * @return the st adjustment reserve details
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StAdjustmentReserveDetail> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st adjustment reserve details.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st adjustment reserve details
    * @param end the upper bound of the range of st adjustment reserve details (not inclusive)
    * @return the range of st adjustment reserve details
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StAdjustmentReserveDetail> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st adjustment reserve details.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st adjustment reserve details
    * @param end the upper bound of the range of st adjustment reserve details (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st adjustment reserve details
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StAdjustmentReserveDetail> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st adjustment reserve details from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st adjustment reserve details.
    *
    * @return the number of st adjustment reserve details
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StAdjustmentReserveDetailPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StAdjustmentReserveDetailPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    StAdjustmentReserveDetailPersistence.class.getName());

            ReferenceRegistry.registerReference(StAdjustmentReserveDetailUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StAdjustmentReserveDetailPersistence persistence) {
    }
}
