package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AccClosureMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the acc closure master service. This utility wraps {@link AccClosureMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureMasterPersistence
 * @see AccClosureMasterPersistenceImpl
 * @generated
 */
public class AccClosureMasterUtil {
    private static AccClosureMasterPersistence _persistence;

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
    public static void clearCache(AccClosureMaster accClosureMaster) {
        getPersistence().clearCache(accClosureMaster);
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
    public static List<AccClosureMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AccClosureMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AccClosureMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static AccClosureMaster update(AccClosureMaster accClosureMaster)
        throws SystemException {
        return getPersistence().update(accClosureMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static AccClosureMaster update(AccClosureMaster accClosureMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(accClosureMaster, serviceContext);
    }

    /**
    * Caches the acc closure master in the entity cache if it is enabled.
    *
    * @param accClosureMaster the acc closure master
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.AccClosureMaster accClosureMaster) {
        getPersistence().cacheResult(accClosureMaster);
    }

    /**
    * Caches the acc closure masters in the entity cache if it is enabled.
    *
    * @param accClosureMasters the acc closure masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AccClosureMaster> accClosureMasters) {
        getPersistence().cacheResult(accClosureMasters);
    }

    /**
    * Creates a new acc closure master with the primary key. Does not add the acc closure master to the database.
    *
    * @param accClosureMasterSid the primary key for the new acc closure master
    * @return the new acc closure master
    */
    public static com.stpl.app.parttwo.model.AccClosureMaster create(
        int accClosureMasterSid) {
        return getPersistence().create(accClosureMasterSid);
    }

    /**
    * Removes the acc closure master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureMasterSid the primary key of the acc closure master
    * @return the acc closure master that was removed
    * @throws com.stpl.app.parttwo.NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AccClosureMaster remove(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchAccClosureMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(accClosureMasterSid);
    }

    public static com.stpl.app.parttwo.model.AccClosureMaster updateImpl(
        com.stpl.app.parttwo.model.AccClosureMaster accClosureMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(accClosureMaster);
    }

    /**
    * Returns the acc closure master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAccClosureMasterException} if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the acc closure master
    * @return the acc closure master
    * @throws com.stpl.app.parttwo.NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AccClosureMaster findByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchAccClosureMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(accClosureMasterSid);
    }

    /**
    * Returns the acc closure master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the acc closure master
    * @return the acc closure master, or <code>null</code> if a acc closure master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AccClosureMaster fetchByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(accClosureMasterSid);
    }

    /**
    * Returns all the acc closure masters.
    *
    * @return the acc closure masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AccClosureMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the acc closure masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure masters
    * @param end the upper bound of the range of acc closure masters (not inclusive)
    * @return the range of acc closure masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AccClosureMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the acc closure masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure masters
    * @param end the upper bound of the range of acc closure masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of acc closure masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AccClosureMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the acc closure masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of acc closure masters.
    *
    * @return the number of acc closure masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AccClosureMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AccClosureMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    AccClosureMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(AccClosureMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(AccClosureMasterPersistence persistence) {
    }
}
