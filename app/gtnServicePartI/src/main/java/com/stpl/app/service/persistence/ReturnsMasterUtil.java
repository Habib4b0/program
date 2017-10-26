package com.stpl.app.service.persistence;

import com.stpl.app.model.ReturnsMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the returns master service. This utility wraps {@link ReturnsMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ReturnsMasterPersistence
 * @see ReturnsMasterPersistenceImpl
 * @generated
 */
public class ReturnsMasterUtil {
    private static ReturnsMasterPersistence _persistence;

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
    public static void clearCache(ReturnsMaster returnsMaster) {
        getPersistence().clearCache(returnsMaster);
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
    public static List<ReturnsMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ReturnsMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ReturnsMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ReturnsMaster update(ReturnsMaster returnsMaster)
        throws SystemException {
        return getPersistence().update(returnsMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ReturnsMaster update(ReturnsMaster returnsMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(returnsMaster, serviceContext);
    }

    /**
    * Caches the returns master in the entity cache if it is enabled.
    *
    * @param returnsMaster the returns master
    */
    public static void cacheResult(
        com.stpl.app.model.ReturnsMaster returnsMaster) {
        getPersistence().cacheResult(returnsMaster);
    }

    /**
    * Caches the returns masters in the entity cache if it is enabled.
    *
    * @param returnsMasters the returns masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ReturnsMaster> returnsMasters) {
        getPersistence().cacheResult(returnsMasters);
    }

    /**
    * Creates a new returns master with the primary key. Does not add the returns master to the database.
    *
    * @param returnsMasterSid the primary key for the new returns master
    * @return the new returns master
    */
    public static com.stpl.app.model.ReturnsMaster create(int returnsMasterSid) {
        return getPersistence().create(returnsMasterSid);
    }

    /**
    * Removes the returns master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param returnsMasterSid the primary key of the returns master
    * @return the returns master that was removed
    * @throws com.stpl.app.NoSuchReturnsMasterException if a returns master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ReturnsMaster remove(int returnsMasterSid)
        throws com.stpl.app.NoSuchReturnsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(returnsMasterSid);
    }

    public static com.stpl.app.model.ReturnsMaster updateImpl(
        com.stpl.app.model.ReturnsMaster returnsMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(returnsMaster);
    }

    /**
    * Returns the returns master with the primary key or throws a {@link com.stpl.app.NoSuchReturnsMasterException} if it could not be found.
    *
    * @param returnsMasterSid the primary key of the returns master
    * @return the returns master
    * @throws com.stpl.app.NoSuchReturnsMasterException if a returns master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ReturnsMaster findByPrimaryKey(
        int returnsMasterSid)
        throws com.stpl.app.NoSuchReturnsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(returnsMasterSid);
    }

    /**
    * Returns the returns master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param returnsMasterSid the primary key of the returns master
    * @return the returns master, or <code>null</code> if a returns master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ReturnsMaster fetchByPrimaryKey(
        int returnsMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(returnsMasterSid);
    }

    /**
    * Returns all the returns masters.
    *
    * @return the returns masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ReturnsMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the returns masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of returns masters
    * @param end the upper bound of the range of returns masters (not inclusive)
    * @return the range of returns masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ReturnsMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the returns masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of returns masters
    * @param end the upper bound of the range of returns masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of returns masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ReturnsMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the returns masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of returns masters.
    *
    * @return the number of returns masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ReturnsMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ReturnsMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ReturnsMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(ReturnsMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ReturnsMasterPersistence persistence) {
    }
}
