package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StCffOutboundMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st cff outbound master service. This utility wraps {@link StCffOutboundMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StCffOutboundMasterPersistence
 * @see StCffOutboundMasterPersistenceImpl
 * @generated
 */
public class StCffOutboundMasterUtil {
    private static StCffOutboundMasterPersistence _persistence;

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
    public static void clearCache(StCffOutboundMaster stCffOutboundMaster) {
        getPersistence().clearCache(stCffOutboundMaster);
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
    public static List<StCffOutboundMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StCffOutboundMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StCffOutboundMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StCffOutboundMaster update(
        StCffOutboundMaster stCffOutboundMaster) throws SystemException {
        return getPersistence().update(stCffOutboundMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StCffOutboundMaster update(
        StCffOutboundMaster stCffOutboundMaster, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(stCffOutboundMaster, serviceContext);
    }

    /**
    * Caches the st cff outbound master in the entity cache if it is enabled.
    *
    * @param stCffOutboundMaster the st cff outbound master
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.StCffOutboundMaster stCffOutboundMaster) {
        getPersistence().cacheResult(stCffOutboundMaster);
    }

    /**
    * Caches the st cff outbound masters in the entity cache if it is enabled.
    *
    * @param stCffOutboundMasters the st cff outbound masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.StCffOutboundMaster> stCffOutboundMasters) {
        getPersistence().cacheResult(stCffOutboundMasters);
    }

    /**
    * Creates a new st cff outbound master with the primary key. Does not add the st cff outbound master to the database.
    *
    * @param stCffOutboundMasterPK the primary key for the new st cff outbound master
    * @return the new st cff outbound master
    */
    public static com.stpl.app.parttwo.model.StCffOutboundMaster create(
        StCffOutboundMasterPK stCffOutboundMasterPK) {
        return getPersistence().create(stCffOutboundMasterPK);
    }

    /**
    * Removes the st cff outbound master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stCffOutboundMasterPK the primary key of the st cff outbound master
    * @return the st cff outbound master that was removed
    * @throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StCffOutboundMaster remove(
        StCffOutboundMasterPK stCffOutboundMasterPK)
        throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stCffOutboundMasterPK);
    }

    public static com.stpl.app.parttwo.model.StCffOutboundMaster updateImpl(
        com.stpl.app.parttwo.model.StCffOutboundMaster stCffOutboundMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stCffOutboundMaster);
    }

    /**
    * Returns the st cff outbound master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStCffOutboundMasterException} if it could not be found.
    *
    * @param stCffOutboundMasterPK the primary key of the st cff outbound master
    * @return the st cff outbound master
    * @throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StCffOutboundMaster findByPrimaryKey(
        StCffOutboundMasterPK stCffOutboundMasterPK)
        throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stCffOutboundMasterPK);
    }

    /**
    * Returns the st cff outbound master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stCffOutboundMasterPK the primary key of the st cff outbound master
    * @return the st cff outbound master, or <code>null</code> if a st cff outbound master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StCffOutboundMaster fetchByPrimaryKey(
        StCffOutboundMasterPK stCffOutboundMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stCffOutboundMasterPK);
    }

    /**
    * Returns all the st cff outbound masters.
    *
    * @return the st cff outbound masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StCffOutboundMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st cff outbound masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st cff outbound masters
    * @param end the upper bound of the range of st cff outbound masters (not inclusive)
    * @return the range of st cff outbound masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StCffOutboundMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st cff outbound masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st cff outbound masters
    * @param end the upper bound of the range of st cff outbound masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st cff outbound masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StCffOutboundMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st cff outbound masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st cff outbound masters.
    *
    * @return the number of st cff outbound masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StCffOutboundMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StCffOutboundMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    StCffOutboundMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(StCffOutboundMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StCffOutboundMasterPersistence persistence) {
    }
}
