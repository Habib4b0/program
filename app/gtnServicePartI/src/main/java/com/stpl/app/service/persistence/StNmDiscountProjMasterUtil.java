package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmDiscountProjMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st nm discount proj master service. This utility wraps {@link StNmDiscountProjMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmDiscountProjMasterPersistence
 * @see StNmDiscountProjMasterPersistenceImpl
 * @generated
 */
public class StNmDiscountProjMasterUtil {
    private static StNmDiscountProjMasterPersistence _persistence;

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
    public static void clearCache(StNmDiscountProjMaster stNmDiscountProjMaster) {
        getPersistence().clearCache(stNmDiscountProjMaster);
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
    public static List<StNmDiscountProjMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StNmDiscountProjMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StNmDiscountProjMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StNmDiscountProjMaster update(
        StNmDiscountProjMaster stNmDiscountProjMaster)
        throws SystemException {
        return getPersistence().update(stNmDiscountProjMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StNmDiscountProjMaster update(
        StNmDiscountProjMaster stNmDiscountProjMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stNmDiscountProjMaster, serviceContext);
    }

    /**
    * Caches the st nm discount proj master in the entity cache if it is enabled.
    *
    * @param stNmDiscountProjMaster the st nm discount proj master
    */
    public static void cacheResult(
        com.stpl.app.model.StNmDiscountProjMaster stNmDiscountProjMaster) {
        getPersistence().cacheResult(stNmDiscountProjMaster);
    }

    /**
    * Caches the st nm discount proj masters in the entity cache if it is enabled.
    *
    * @param stNmDiscountProjMasters the st nm discount proj masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StNmDiscountProjMaster> stNmDiscountProjMasters) {
        getPersistence().cacheResult(stNmDiscountProjMasters);
    }

    /**
    * Creates a new st nm discount proj master with the primary key. Does not add the st nm discount proj master to the database.
    *
    * @param stNmDiscountProjMasterPK the primary key for the new st nm discount proj master
    * @return the new st nm discount proj master
    */
    public static com.stpl.app.model.StNmDiscountProjMaster create(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK) {
        return getPersistence().create(stNmDiscountProjMasterPK);
    }

    /**
    * Removes the st nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
    * @return the st nm discount proj master that was removed
    * @throws com.stpl.app.NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmDiscountProjMaster remove(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
        throws com.stpl.app.NoSuchStNmDiscountProjMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stNmDiscountProjMasterPK);
    }

    public static com.stpl.app.model.StNmDiscountProjMaster updateImpl(
        com.stpl.app.model.StNmDiscountProjMaster stNmDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stNmDiscountProjMaster);
    }

    /**
    * Returns the st nm discount proj master with the primary key or throws a {@link com.stpl.app.NoSuchStNmDiscountProjMasterException} if it could not be found.
    *
    * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
    * @return the st nm discount proj master
    * @throws com.stpl.app.NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmDiscountProjMaster findByPrimaryKey(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
        throws com.stpl.app.NoSuchStNmDiscountProjMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stNmDiscountProjMasterPK);
    }

    /**
    * Returns the st nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
    * @return the st nm discount proj master, or <code>null</code> if a st nm discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmDiscountProjMaster fetchByPrimaryKey(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stNmDiscountProjMasterPK);
    }

    /**
    * Returns all the st nm discount proj masters.
    *
    * @return the st nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmDiscountProjMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st nm discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm discount proj masters
    * @param end the upper bound of the range of st nm discount proj masters (not inclusive)
    * @return the range of st nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmDiscountProjMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st nm discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm discount proj masters
    * @param end the upper bound of the range of st nm discount proj masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmDiscountProjMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st nm discount proj masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st nm discount proj masters.
    *
    * @return the number of st nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StNmDiscountProjMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StNmDiscountProjMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StNmDiscountProjMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(StNmDiscountProjMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StNmDiscountProjMasterPersistence persistence) {
    }
}
