package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffCustomViewMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cff custom view master service. This utility wraps {@link CffCustomViewMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffCustomViewMasterPersistence
 * @see CffCustomViewMasterPersistenceImpl
 * @generated
 */
public class CffCustomViewMasterUtil {
    private static CffCustomViewMasterPersistence _persistence;

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
    public static void clearCache(CffCustomViewMaster cffCustomViewMaster) {
        getPersistence().clearCache(cffCustomViewMaster);
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
    public static List<CffCustomViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CffCustomViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CffCustomViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CffCustomViewMaster update(
        CffCustomViewMaster cffCustomViewMaster) throws SystemException {
        return getPersistence().update(cffCustomViewMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CffCustomViewMaster update(
        CffCustomViewMaster cffCustomViewMaster, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(cffCustomViewMaster, serviceContext);
    }

    /**
    * Caches the cff custom view master in the entity cache if it is enabled.
    *
    * @param cffCustomViewMaster the cff custom view master
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.CffCustomViewMaster cffCustomViewMaster) {
        getPersistence().cacheResult(cffCustomViewMaster);
    }

    /**
    * Caches the cff custom view masters in the entity cache if it is enabled.
    *
    * @param cffCustomViewMasters the cff custom view masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffCustomViewMaster> cffCustomViewMasters) {
        getPersistence().cacheResult(cffCustomViewMasters);
    }

    /**
    * Creates a new cff custom view master with the primary key. Does not add the cff custom view master to the database.
    *
    * @param cffCustomViewMasterSid the primary key for the new cff custom view master
    * @return the new cff custom view master
    */
    public static com.stpl.app.parttwo.model.CffCustomViewMaster create(
        int cffCustomViewMasterSid) {
        return getPersistence().create(cffCustomViewMasterSid);
    }

    /**
    * Removes the cff custom view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustomViewMasterSid the primary key of the cff custom view master
    * @return the cff custom view master that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffCustomViewMaster remove(
        int cffCustomViewMasterSid)
        throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(cffCustomViewMasterSid);
    }

    public static com.stpl.app.parttwo.model.CffCustomViewMaster updateImpl(
        com.stpl.app.parttwo.model.CffCustomViewMaster cffCustomViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(cffCustomViewMaster);
    }

    /**
    * Returns the cff custom view master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffCustomViewMasterException} if it could not be found.
    *
    * @param cffCustomViewMasterSid the primary key of the cff custom view master
    * @return the cff custom view master
    * @throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffCustomViewMaster findByPrimaryKey(
        int cffCustomViewMasterSid)
        throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(cffCustomViewMasterSid);
    }

    /**
    * Returns the cff custom view master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffCustomViewMasterSid the primary key of the cff custom view master
    * @return the cff custom view master, or <code>null</code> if a cff custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffCustomViewMaster fetchByPrimaryKey(
        int cffCustomViewMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(cffCustomViewMasterSid);
    }

    /**
    * Returns all the cff custom view masters.
    *
    * @return the cff custom view masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffCustomViewMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the cff custom view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff custom view masters
    * @param end the upper bound of the range of cff custom view masters (not inclusive)
    * @return the range of cff custom view masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffCustomViewMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the cff custom view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff custom view masters
    * @param end the upper bound of the range of cff custom view masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff custom view masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffCustomViewMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the cff custom view masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of cff custom view masters.
    *
    * @return the number of cff custom view masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CffCustomViewMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CffCustomViewMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    CffCustomViewMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(CffCustomViewMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CffCustomViewMasterPersistence persistence) {
    }
}
