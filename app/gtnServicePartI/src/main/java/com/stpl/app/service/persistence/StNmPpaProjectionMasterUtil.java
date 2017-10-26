package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmPpaProjectionMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st nm ppa projection master service. This utility wraps {@link StNmPpaProjectionMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmPpaProjectionMasterPersistence
 * @see StNmPpaProjectionMasterPersistenceImpl
 * @generated
 */
public class StNmPpaProjectionMasterUtil {
    private static StNmPpaProjectionMasterPersistence _persistence;

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
        StNmPpaProjectionMaster stNmPpaProjectionMaster) {
        getPersistence().clearCache(stNmPpaProjectionMaster);
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
    public static List<StNmPpaProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StNmPpaProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StNmPpaProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StNmPpaProjectionMaster update(
        StNmPpaProjectionMaster stNmPpaProjectionMaster)
        throws SystemException {
        return getPersistence().update(stNmPpaProjectionMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StNmPpaProjectionMaster update(
        StNmPpaProjectionMaster stNmPpaProjectionMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stNmPpaProjectionMaster, serviceContext);
    }

    /**
    * Caches the st nm ppa projection master in the entity cache if it is enabled.
    *
    * @param stNmPpaProjectionMaster the st nm ppa projection master
    */
    public static void cacheResult(
        com.stpl.app.model.StNmPpaProjectionMaster stNmPpaProjectionMaster) {
        getPersistence().cacheResult(stNmPpaProjectionMaster);
    }

    /**
    * Caches the st nm ppa projection masters in the entity cache if it is enabled.
    *
    * @param stNmPpaProjectionMasters the st nm ppa projection masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StNmPpaProjectionMaster> stNmPpaProjectionMasters) {
        getPersistence().cacheResult(stNmPpaProjectionMasters);
    }

    /**
    * Creates a new st nm ppa projection master with the primary key. Does not add the st nm ppa projection master to the database.
    *
    * @param stNmPpaProjectionMasterPK the primary key for the new st nm ppa projection master
    * @return the new st nm ppa projection master
    */
    public static com.stpl.app.model.StNmPpaProjectionMaster create(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK) {
        return getPersistence().create(stNmPpaProjectionMasterPK);
    }

    /**
    * Removes the st nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
    * @return the st nm ppa projection master that was removed
    * @throws com.stpl.app.NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmPpaProjectionMaster remove(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws com.stpl.app.NoSuchStNmPpaProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stNmPpaProjectionMasterPK);
    }

    public static com.stpl.app.model.StNmPpaProjectionMaster updateImpl(
        com.stpl.app.model.StNmPpaProjectionMaster stNmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stNmPpaProjectionMaster);
    }

    /**
    * Returns the st nm ppa projection master with the primary key or throws a {@link com.stpl.app.NoSuchStNmPpaProjectionMasterException} if it could not be found.
    *
    * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
    * @return the st nm ppa projection master
    * @throws com.stpl.app.NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmPpaProjectionMaster findByPrimaryKey(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws com.stpl.app.NoSuchStNmPpaProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stNmPpaProjectionMasterPK);
    }

    /**
    * Returns the st nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
    * @return the st nm ppa projection master, or <code>null</code> if a st nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmPpaProjectionMaster fetchByPrimaryKey(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stNmPpaProjectionMasterPK);
    }

    /**
    * Returns all the st nm ppa projection masters.
    *
    * @return the st nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmPpaProjectionMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st nm ppa projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm ppa projection masters
    * @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
    * @return the range of st nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmPpaProjectionMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st nm ppa projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm ppa projection masters
    * @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmPpaProjectionMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st nm ppa projection masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st nm ppa projection masters.
    *
    * @return the number of st nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StNmPpaProjectionMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StNmPpaProjectionMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StNmPpaProjectionMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(StNmPpaProjectionMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StNmPpaProjectionMasterPersistence persistence) {
    }
}
