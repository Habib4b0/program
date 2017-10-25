package com.stpl.app.service.persistence;

import com.stpl.app.model.MParityLookup;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the m parity lookup service. This utility wraps {@link MParityLookupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MParityLookupPersistence
 * @see MParityLookupPersistenceImpl
 * @generated
 */
public class MParityLookupUtil {
    private static MParityLookupPersistence _persistence;

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
    public static void clearCache(MParityLookup mParityLookup) {
        getPersistence().clearCache(mParityLookup);
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
    public static List<MParityLookup> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MParityLookup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MParityLookup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static MParityLookup update(MParityLookup mParityLookup)
        throws SystemException {
        return getPersistence().update(mParityLookup);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static MParityLookup update(MParityLookup mParityLookup,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(mParityLookup, serviceContext);
    }

    /**
    * Caches the m parity lookup in the entity cache if it is enabled.
    *
    * @param mParityLookup the m parity lookup
    */
    public static void cacheResult(
        com.stpl.app.model.MParityLookup mParityLookup) {
        getPersistence().cacheResult(mParityLookup);
    }

    /**
    * Caches the m parity lookups in the entity cache if it is enabled.
    *
    * @param mParityLookups the m parity lookups
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.MParityLookup> mParityLookups) {
        getPersistence().cacheResult(mParityLookups);
    }

    /**
    * Creates a new m parity lookup with the primary key. Does not add the m parity lookup to the database.
    *
    * @param mParityLookupSid the primary key for the new m parity lookup
    * @return the new m parity lookup
    */
    public static com.stpl.app.model.MParityLookup create(int mParityLookupSid) {
        return getPersistence().create(mParityLookupSid);
    }

    /**
    * Removes the m parity lookup with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mParityLookupSid the primary key of the m parity lookup
    * @return the m parity lookup that was removed
    * @throws com.stpl.app.NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MParityLookup remove(int mParityLookupSid)
        throws com.stpl.app.NoSuchMParityLookupException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(mParityLookupSid);
    }

    public static com.stpl.app.model.MParityLookup updateImpl(
        com.stpl.app.model.MParityLookup mParityLookup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(mParityLookup);
    }

    /**
    * Returns the m parity lookup with the primary key or throws a {@link com.stpl.app.NoSuchMParityLookupException} if it could not be found.
    *
    * @param mParityLookupSid the primary key of the m parity lookup
    * @return the m parity lookup
    * @throws com.stpl.app.NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MParityLookup findByPrimaryKey(
        int mParityLookupSid)
        throws com.stpl.app.NoSuchMParityLookupException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(mParityLookupSid);
    }

    /**
    * Returns the m parity lookup with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param mParityLookupSid the primary key of the m parity lookup
    * @return the m parity lookup, or <code>null</code> if a m parity lookup with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MParityLookup fetchByPrimaryKey(
        int mParityLookupSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(mParityLookupSid);
    }

    /**
    * Returns all the m parity lookups.
    *
    * @return the m parity lookups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MParityLookup> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the m parity lookups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m parity lookups
    * @param end the upper bound of the range of m parity lookups (not inclusive)
    * @return the range of m parity lookups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MParityLookup> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the m parity lookups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m parity lookups
    * @param end the upper bound of the range of m parity lookups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m parity lookups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MParityLookup> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the m parity lookups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of m parity lookups.
    *
    * @return the number of m parity lookups
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MParityLookupPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MParityLookupPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MParityLookupPersistence.class.getName());

            ReferenceRegistry.registerReference(MParityLookupUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MParityLookupPersistence persistence) {
    }
}
