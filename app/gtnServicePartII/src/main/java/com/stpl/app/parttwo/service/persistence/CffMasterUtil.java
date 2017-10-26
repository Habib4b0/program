package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cff master service. This utility wraps {@link CffMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffMasterPersistence
 * @see CffMasterPersistenceImpl
 * @generated
 */
public class CffMasterUtil {
    private static CffMasterPersistence _persistence;

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
    public static void clearCache(CffMaster cffMaster) {
        getPersistence().clearCache(cffMaster);
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
    public static List<CffMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CffMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CffMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CffMaster update(CffMaster cffMaster)
        throws SystemException {
        return getPersistence().update(cffMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CffMaster update(CffMaster cffMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(cffMaster, serviceContext);
    }

    /**
    * Caches the cff master in the entity cache if it is enabled.
    *
    * @param cffMaster the cff master
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.CffMaster cffMaster) {
        getPersistence().cacheResult(cffMaster);
    }

    /**
    * Caches the cff masters in the entity cache if it is enabled.
    *
    * @param cffMasters the cff masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffMaster> cffMasters) {
        getPersistence().cacheResult(cffMasters);
    }

    /**
    * Creates a new cff master with the primary key. Does not add the cff master to the database.
    *
    * @param cffMasterSid the primary key for the new cff master
    * @return the new cff master
    */
    public static com.stpl.app.parttwo.model.CffMaster create(int cffMasterSid) {
        return getPersistence().create(cffMasterSid);
    }

    /**
    * Removes the cff master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffMasterSid the primary key of the cff master
    * @return the cff master that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffMasterException if a cff master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffMaster remove(int cffMasterSid)
        throws com.stpl.app.parttwo.NoSuchCffMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(cffMasterSid);
    }

    public static com.stpl.app.parttwo.model.CffMaster updateImpl(
        com.stpl.app.parttwo.model.CffMaster cffMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(cffMaster);
    }

    /**
    * Returns the cff master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffMasterException} if it could not be found.
    *
    * @param cffMasterSid the primary key of the cff master
    * @return the cff master
    * @throws com.stpl.app.parttwo.NoSuchCffMasterException if a cff master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffMaster findByPrimaryKey(
        int cffMasterSid)
        throws com.stpl.app.parttwo.NoSuchCffMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(cffMasterSid);
    }

    /**
    * Returns the cff master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffMasterSid the primary key of the cff master
    * @return the cff master, or <code>null</code> if a cff master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffMaster fetchByPrimaryKey(
        int cffMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(cffMasterSid);
    }

    /**
    * Returns all the cff masters.
    *
    * @return the cff masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the cff masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff masters
    * @param end the upper bound of the range of cff masters (not inclusive)
    * @return the range of cff masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the cff masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff masters
    * @param end the upper bound of the range of cff masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the cff masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of cff masters.
    *
    * @return the number of cff masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CffMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CffMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    CffMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(CffMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CffMasterPersistence persistence) {
    }
}
