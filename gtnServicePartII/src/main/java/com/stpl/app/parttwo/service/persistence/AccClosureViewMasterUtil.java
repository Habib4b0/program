package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AccClosureViewMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the acc closure view master service. This utility wraps {@link AccClosureViewMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureViewMasterPersistence
 * @see AccClosureViewMasterPersistenceImpl
 * @generated
 */
public class AccClosureViewMasterUtil {
    private static AccClosureViewMasterPersistence _persistence;

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
    public static void clearCache(AccClosureViewMaster accClosureViewMaster) {
        getPersistence().clearCache(accClosureViewMaster);
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
    public static List<AccClosureViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AccClosureViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AccClosureViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static AccClosureViewMaster update(
        AccClosureViewMaster accClosureViewMaster) throws SystemException {
        return getPersistence().update(accClosureViewMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static AccClosureViewMaster update(
        AccClosureViewMaster accClosureViewMaster, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(accClosureViewMaster, serviceContext);
    }

    /**
    * Caches the acc closure view master in the entity cache if it is enabled.
    *
    * @param accClosureViewMaster the acc closure view master
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster) {
        getPersistence().cacheResult(accClosureViewMaster);
    }

    /**
    * Caches the acc closure view masters in the entity cache if it is enabled.
    *
    * @param accClosureViewMasters the acc closure view masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AccClosureViewMaster> accClosureViewMasters) {
        getPersistence().cacheResult(accClosureViewMasters);
    }

    /**
    * Creates a new acc closure view master with the primary key. Does not add the acc closure view master to the database.
    *
    * @param accClosureViewMasterSid the primary key for the new acc closure view master
    * @return the new acc closure view master
    */
    public static com.stpl.app.parttwo.model.AccClosureViewMaster create(
        int accClosureViewMasterSid) {
        return getPersistence().create(accClosureViewMasterSid);
    }

    /**
    * Removes the acc closure view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureViewMasterSid the primary key of the acc closure view master
    * @return the acc closure view master that was removed
    * @throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AccClosureViewMaster remove(
        int accClosureViewMasterSid)
        throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(accClosureViewMasterSid);
    }

    public static com.stpl.app.parttwo.model.AccClosureViewMaster updateImpl(
        com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(accClosureViewMaster);
    }

    /**
    * Returns the acc closure view master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAccClosureViewMasterException} if it could not be found.
    *
    * @param accClosureViewMasterSid the primary key of the acc closure view master
    * @return the acc closure view master
    * @throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AccClosureViewMaster findByPrimaryKey(
        int accClosureViewMasterSid)
        throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(accClosureViewMasterSid);
    }

    /**
    * Returns the acc closure view master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accClosureViewMasterSid the primary key of the acc closure view master
    * @return the acc closure view master, or <code>null</code> if a acc closure view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AccClosureViewMaster fetchByPrimaryKey(
        int accClosureViewMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(accClosureViewMasterSid);
    }

    /**
    * Returns all the acc closure view masters.
    *
    * @return the acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AccClosureViewMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the acc closure view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure view masters
    * @param end the upper bound of the range of acc closure view masters (not inclusive)
    * @return the range of acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AccClosureViewMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the acc closure view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure view masters
    * @param end the upper bound of the range of acc closure view masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AccClosureViewMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the acc closure view masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of acc closure view masters.
    *
    * @return the number of acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AccClosureViewMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AccClosureViewMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    AccClosureViewMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(AccClosureViewMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(AccClosureViewMasterPersistence persistence) {
    }
}
