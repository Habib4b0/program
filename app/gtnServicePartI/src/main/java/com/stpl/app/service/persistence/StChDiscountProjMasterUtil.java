package com.stpl.app.service.persistence;

import com.stpl.app.model.StChDiscountProjMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st ch discount proj master service. This utility wraps {@link StChDiscountProjMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChDiscountProjMasterPersistence
 * @see StChDiscountProjMasterPersistenceImpl
 * @generated
 */
public class StChDiscountProjMasterUtil {
    private static StChDiscountProjMasterPersistence _persistence;

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
    public static void clearCache(StChDiscountProjMaster stChDiscountProjMaster) {
        getPersistence().clearCache(stChDiscountProjMaster);
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
    public static List<StChDiscountProjMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StChDiscountProjMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StChDiscountProjMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StChDiscountProjMaster update(
        StChDiscountProjMaster stChDiscountProjMaster)
        throws SystemException {
        return getPersistence().update(stChDiscountProjMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StChDiscountProjMaster update(
        StChDiscountProjMaster stChDiscountProjMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stChDiscountProjMaster, serviceContext);
    }

    /**
    * Caches the st ch discount proj master in the entity cache if it is enabled.
    *
    * @param stChDiscountProjMaster the st ch discount proj master
    */
    public static void cacheResult(
        com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster) {
        getPersistence().cacheResult(stChDiscountProjMaster);
    }

    /**
    * Caches the st ch discount proj masters in the entity cache if it is enabled.
    *
    * @param stChDiscountProjMasters the st ch discount proj masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StChDiscountProjMaster> stChDiscountProjMasters) {
        getPersistence().cacheResult(stChDiscountProjMasters);
    }

    /**
    * Creates a new st ch discount proj master with the primary key. Does not add the st ch discount proj master to the database.
    *
    * @param stChDiscountProjMasterPK the primary key for the new st ch discount proj master
    * @return the new st ch discount proj master
    */
    public static com.stpl.app.model.StChDiscountProjMaster create(
        StChDiscountProjMasterPK stChDiscountProjMasterPK) {
        return getPersistence().create(stChDiscountProjMasterPK);
    }

    /**
    * Removes the st ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
    * @return the st ch discount proj master that was removed
    * @throws com.stpl.app.NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChDiscountProjMaster remove(
        StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws com.stpl.app.NoSuchStChDiscountProjMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stChDiscountProjMasterPK);
    }

    public static com.stpl.app.model.StChDiscountProjMaster updateImpl(
        com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stChDiscountProjMaster);
    }

    /**
    * Returns the st ch discount proj master with the primary key or throws a {@link com.stpl.app.NoSuchStChDiscountProjMasterException} if it could not be found.
    *
    * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
    * @return the st ch discount proj master
    * @throws com.stpl.app.NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChDiscountProjMaster findByPrimaryKey(
        StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws com.stpl.app.NoSuchStChDiscountProjMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stChDiscountProjMasterPK);
    }

    /**
    * Returns the st ch discount proj master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
    * @return the st ch discount proj master, or <code>null</code> if a st ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChDiscountProjMaster fetchByPrimaryKey(
        StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stChDiscountProjMasterPK);
    }

    /**
    * Returns all the st ch discount proj masters.
    *
    * @return the st ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StChDiscountProjMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st ch discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch discount proj masters
    * @param end the upper bound of the range of st ch discount proj masters (not inclusive)
    * @return the range of st ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StChDiscountProjMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st ch discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch discount proj masters
    * @param end the upper bound of the range of st ch discount proj masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StChDiscountProjMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st ch discount proj masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st ch discount proj masters.
    *
    * @return the number of st ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StChDiscountProjMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StChDiscountProjMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StChDiscountProjMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(StChDiscountProjMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StChDiscountProjMasterPersistence persistence) {
    }
}
