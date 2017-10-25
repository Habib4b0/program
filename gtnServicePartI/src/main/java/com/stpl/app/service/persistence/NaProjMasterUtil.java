package com.stpl.app.service.persistence;

import com.stpl.app.model.NaProjMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the na proj master service. This utility wraps {@link NaProjMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NaProjMasterPersistence
 * @see NaProjMasterPersistenceImpl
 * @generated
 */
public class NaProjMasterUtil {
    private static NaProjMasterPersistence _persistence;

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
    public static void clearCache(NaProjMaster naProjMaster) {
        getPersistence().clearCache(naProjMaster);
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
    public static List<NaProjMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<NaProjMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<NaProjMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static NaProjMaster update(NaProjMaster naProjMaster)
        throws SystemException {
        return getPersistence().update(naProjMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static NaProjMaster update(NaProjMaster naProjMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(naProjMaster, serviceContext);
    }

    /**
    * Caches the na proj master in the entity cache if it is enabled.
    *
    * @param naProjMaster the na proj master
    */
    public static void cacheResult(com.stpl.app.model.NaProjMaster naProjMaster) {
        getPersistence().cacheResult(naProjMaster);
    }

    /**
    * Caches the na proj masters in the entity cache if it is enabled.
    *
    * @param naProjMasters the na proj masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.NaProjMaster> naProjMasters) {
        getPersistence().cacheResult(naProjMasters);
    }

    /**
    * Creates a new na proj master with the primary key. Does not add the na proj master to the database.
    *
    * @param naProjMasterSid the primary key for the new na proj master
    * @return the new na proj master
    */
    public static com.stpl.app.model.NaProjMaster create(int naProjMasterSid) {
        return getPersistence().create(naProjMasterSid);
    }

    /**
    * Removes the na proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param naProjMasterSid the primary key of the na proj master
    * @return the na proj master that was removed
    * @throws com.stpl.app.NoSuchNaProjMasterException if a na proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NaProjMaster remove(int naProjMasterSid)
        throws com.stpl.app.NoSuchNaProjMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(naProjMasterSid);
    }

    public static com.stpl.app.model.NaProjMaster updateImpl(
        com.stpl.app.model.NaProjMaster naProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(naProjMaster);
    }

    /**
    * Returns the na proj master with the primary key or throws a {@link com.stpl.app.NoSuchNaProjMasterException} if it could not be found.
    *
    * @param naProjMasterSid the primary key of the na proj master
    * @return the na proj master
    * @throws com.stpl.app.NoSuchNaProjMasterException if a na proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NaProjMaster findByPrimaryKey(
        int naProjMasterSid)
        throws com.stpl.app.NoSuchNaProjMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(naProjMasterSid);
    }

    /**
    * Returns the na proj master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param naProjMasterSid the primary key of the na proj master
    * @return the na proj master, or <code>null</code> if a na proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NaProjMaster fetchByPrimaryKey(
        int naProjMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(naProjMasterSid);
    }

    /**
    * Returns all the na proj masters.
    *
    * @return the na proj masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NaProjMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the na proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of na proj masters
    * @param end the upper bound of the range of na proj masters (not inclusive)
    * @return the range of na proj masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NaProjMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the na proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of na proj masters
    * @param end the upper bound of the range of na proj masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of na proj masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NaProjMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the na proj masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of na proj masters.
    *
    * @return the number of na proj masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static NaProjMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (NaProjMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NaProjMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(NaProjMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(NaProjMasterPersistence persistence) {
    }
}
