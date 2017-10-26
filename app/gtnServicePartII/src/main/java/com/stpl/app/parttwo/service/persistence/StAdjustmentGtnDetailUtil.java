package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StAdjustmentGtnDetail;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st adjustment gtn detail service. This utility wraps {@link StAdjustmentGtnDetailPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAdjustmentGtnDetailPersistence
 * @see StAdjustmentGtnDetailPersistenceImpl
 * @generated
 */
public class StAdjustmentGtnDetailUtil {
    private static StAdjustmentGtnDetailPersistence _persistence;

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
    public static void clearCache(StAdjustmentGtnDetail stAdjustmentGtnDetail) {
        getPersistence().clearCache(stAdjustmentGtnDetail);
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
    public static List<StAdjustmentGtnDetail> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StAdjustmentGtnDetail> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StAdjustmentGtnDetail> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StAdjustmentGtnDetail update(
        StAdjustmentGtnDetail stAdjustmentGtnDetail) throws SystemException {
        return getPersistence().update(stAdjustmentGtnDetail);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StAdjustmentGtnDetail update(
        StAdjustmentGtnDetail stAdjustmentGtnDetail,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stAdjustmentGtnDetail, serviceContext);
    }

    /**
    * Caches the st adjustment gtn detail in the entity cache if it is enabled.
    *
    * @param stAdjustmentGtnDetail the st adjustment gtn detail
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.StAdjustmentGtnDetail stAdjustmentGtnDetail) {
        getPersistence().cacheResult(stAdjustmentGtnDetail);
    }

    /**
    * Caches the st adjustment gtn details in the entity cache if it is enabled.
    *
    * @param stAdjustmentGtnDetails the st adjustment gtn details
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.StAdjustmentGtnDetail> stAdjustmentGtnDetails) {
        getPersistence().cacheResult(stAdjustmentGtnDetails);
    }

    /**
    * Creates a new st adjustment gtn detail with the primary key. Does not add the st adjustment gtn detail to the database.
    *
    * @param stAdjustmentGtnDetailPK the primary key for the new st adjustment gtn detail
    * @return the new st adjustment gtn detail
    */
    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail create(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK) {
        return getPersistence().create(stAdjustmentGtnDetailPK);
    }

    /**
    * Removes the st adjustment gtn detail with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
    * @return the st adjustment gtn detail that was removed
    * @throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail remove(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stAdjustmentGtnDetailPK);
    }

    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail updateImpl(
        com.stpl.app.parttwo.model.StAdjustmentGtnDetail stAdjustmentGtnDetail)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stAdjustmentGtnDetail);
    }

    /**
    * Returns the st adjustment gtn detail with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException} if it could not be found.
    *
    * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
    * @return the st adjustment gtn detail
    * @throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail findByPrimaryKey(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stAdjustmentGtnDetailPK);
    }

    /**
    * Returns the st adjustment gtn detail with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
    * @return the st adjustment gtn detail, or <code>null</code> if a st adjustment gtn detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail fetchByPrimaryKey(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stAdjustmentGtnDetailPK);
    }

    /**
    * Returns all the st adjustment gtn details.
    *
    * @return the st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StAdjustmentGtnDetail> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st adjustment gtn details.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st adjustment gtn details
    * @param end the upper bound of the range of st adjustment gtn details (not inclusive)
    * @return the range of st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StAdjustmentGtnDetail> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st adjustment gtn details.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st adjustment gtn details
    * @param end the upper bound of the range of st adjustment gtn details (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StAdjustmentGtnDetail> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st adjustment gtn details from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st adjustment gtn details.
    *
    * @return the number of st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StAdjustmentGtnDetailPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StAdjustmentGtnDetailPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    StAdjustmentGtnDetailPersistence.class.getName());

            ReferenceRegistry.registerReference(StAdjustmentGtnDetailUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StAdjustmentGtnDetailPersistence persistence) {
    }
}
