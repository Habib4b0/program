package com.stpl.app.service.persistence;

import com.stpl.app.model.MSalesProjectionMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the m sales projection master service. This utility wraps {@link MSalesProjectionMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSalesProjectionMasterPersistence
 * @see MSalesProjectionMasterPersistenceImpl
 * @generated
 */
public class MSalesProjectionMasterUtil {
    private static MSalesProjectionMasterPersistence _persistence;

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
    public static void clearCache(MSalesProjectionMaster mSalesProjectionMaster) {
        getPersistence().clearCache(mSalesProjectionMaster);
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
    public static List<MSalesProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MSalesProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MSalesProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static MSalesProjectionMaster update(
        MSalesProjectionMaster mSalesProjectionMaster)
        throws SystemException {
        return getPersistence().update(mSalesProjectionMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static MSalesProjectionMaster update(
        MSalesProjectionMaster mSalesProjectionMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(mSalesProjectionMaster, serviceContext);
    }

    /**
    * Caches the m sales projection master in the entity cache if it is enabled.
    *
    * @param mSalesProjectionMaster the m sales projection master
    */
    public static void cacheResult(
        com.stpl.app.model.MSalesProjectionMaster mSalesProjectionMaster) {
        getPersistence().cacheResult(mSalesProjectionMaster);
    }

    /**
    * Caches the m sales projection masters in the entity cache if it is enabled.
    *
    * @param mSalesProjectionMasters the m sales projection masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.MSalesProjectionMaster> mSalesProjectionMasters) {
        getPersistence().cacheResult(mSalesProjectionMasters);
    }

    /**
    * Creates a new m sales projection master with the primary key. Does not add the m sales projection master to the database.
    *
    * @param projectionDetailsSid the primary key for the new m sales projection master
    * @return the new m sales projection master
    */
    public static com.stpl.app.model.MSalesProjectionMaster create(
        int projectionDetailsSid) {
        return getPersistence().create(projectionDetailsSid);
    }

    /**
    * Removes the m sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the m sales projection master
    * @return the m sales projection master that was removed
    * @throws com.stpl.app.NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MSalesProjectionMaster remove(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSalesProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(projectionDetailsSid);
    }

    public static com.stpl.app.model.MSalesProjectionMaster updateImpl(
        com.stpl.app.model.MSalesProjectionMaster mSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(mSalesProjectionMaster);
    }

    /**
    * Returns the m sales projection master with the primary key or throws a {@link com.stpl.app.NoSuchMSalesProjectionMasterException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m sales projection master
    * @return the m sales projection master
    * @throws com.stpl.app.NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MSalesProjectionMaster findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSalesProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(projectionDetailsSid);
    }

    /**
    * Returns the m sales projection master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m sales projection master
    * @return the m sales projection master, or <code>null</code> if a m sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MSalesProjectionMaster fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(projectionDetailsSid);
    }

    /**
    * Returns all the m sales projection masters.
    *
    * @return the m sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MSalesProjectionMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the m sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m sales projection masters
    * @param end the upper bound of the range of m sales projection masters (not inclusive)
    * @return the range of m sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MSalesProjectionMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the m sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m sales projection masters
    * @param end the upper bound of the range of m sales projection masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MSalesProjectionMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the m sales projection masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of m sales projection masters.
    *
    * @return the number of m sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MSalesProjectionMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MSalesProjectionMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MSalesProjectionMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(MSalesProjectionMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MSalesProjectionMasterPersistence persistence) {
    }
}
