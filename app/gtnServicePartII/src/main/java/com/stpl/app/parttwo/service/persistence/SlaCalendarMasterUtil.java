package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.SlaCalendarMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the sla calendar master service. This utility wraps {@link SlaCalendarMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SlaCalendarMasterPersistence
 * @see SlaCalendarMasterPersistenceImpl
 * @generated
 */
public class SlaCalendarMasterUtil {
    private static SlaCalendarMasterPersistence _persistence;

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
    public static void clearCache(SlaCalendarMaster slaCalendarMaster) {
        getPersistence().clearCache(slaCalendarMaster);
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
    public static List<SlaCalendarMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<SlaCalendarMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<SlaCalendarMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static SlaCalendarMaster update(SlaCalendarMaster slaCalendarMaster)
        throws SystemException {
        return getPersistence().update(slaCalendarMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static SlaCalendarMaster update(
        SlaCalendarMaster slaCalendarMaster, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(slaCalendarMaster, serviceContext);
    }

    /**
    * Caches the sla calendar master in the entity cache if it is enabled.
    *
    * @param slaCalendarMaster the sla calendar master
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster) {
        getPersistence().cacheResult(slaCalendarMaster);
    }

    /**
    * Caches the sla calendar masters in the entity cache if it is enabled.
    *
    * @param slaCalendarMasters the sla calendar masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.SlaCalendarMaster> slaCalendarMasters) {
        getPersistence().cacheResult(slaCalendarMasters);
    }

    /**
    * Creates a new sla calendar master with the primary key. Does not add the sla calendar master to the database.
    *
    * @param slaCalendarMasterSid the primary key for the new sla calendar master
    * @return the new sla calendar master
    */
    public static com.stpl.app.parttwo.model.SlaCalendarMaster create(
        int slaCalendarMasterSid) {
        return getPersistence().create(slaCalendarMasterSid);
    }

    /**
    * Removes the sla calendar master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarMasterSid the primary key of the sla calendar master
    * @return the sla calendar master that was removed
    * @throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.SlaCalendarMaster remove(
        int slaCalendarMasterSid)
        throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(slaCalendarMasterSid);
    }

    public static com.stpl.app.parttwo.model.SlaCalendarMaster updateImpl(
        com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(slaCalendarMaster);
    }

    /**
    * Returns the sla calendar master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchSlaCalendarMasterException} if it could not be found.
    *
    * @param slaCalendarMasterSid the primary key of the sla calendar master
    * @return the sla calendar master
    * @throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.SlaCalendarMaster findByPrimaryKey(
        int slaCalendarMasterSid)
        throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(slaCalendarMasterSid);
    }

    /**
    * Returns the sla calendar master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param slaCalendarMasterSid the primary key of the sla calendar master
    * @return the sla calendar master, or <code>null</code> if a sla calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.SlaCalendarMaster fetchByPrimaryKey(
        int slaCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(slaCalendarMasterSid);
    }

    /**
    * Returns all the sla calendar masters.
    *
    * @return the sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.SlaCalendarMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the sla calendar masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sla calendar masters
    * @param end the upper bound of the range of sla calendar masters (not inclusive)
    * @return the range of sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.SlaCalendarMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the sla calendar masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sla calendar masters
    * @param end the upper bound of the range of sla calendar masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.SlaCalendarMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the sla calendar masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of sla calendar masters.
    *
    * @return the number of sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static SlaCalendarMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (SlaCalendarMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    SlaCalendarMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(SlaCalendarMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(SlaCalendarMasterPersistence persistence) {
    }
}
