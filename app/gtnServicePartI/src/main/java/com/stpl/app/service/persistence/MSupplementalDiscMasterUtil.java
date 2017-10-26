package com.stpl.app.service.persistence;

import com.stpl.app.model.MSupplementalDiscMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the m supplemental disc master service. This utility wraps {@link MSupplementalDiscMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSupplementalDiscMasterPersistence
 * @see MSupplementalDiscMasterPersistenceImpl
 * @generated
 */
public class MSupplementalDiscMasterUtil {
    private static MSupplementalDiscMasterPersistence _persistence;

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
    public static void clearCache(
        MSupplementalDiscMaster mSupplementalDiscMaster) {
        getPersistence().clearCache(mSupplementalDiscMaster);
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
    public static List<MSupplementalDiscMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MSupplementalDiscMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MSupplementalDiscMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static MSupplementalDiscMaster update(
        MSupplementalDiscMaster mSupplementalDiscMaster)
        throws SystemException {
        return getPersistence().update(mSupplementalDiscMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static MSupplementalDiscMaster update(
        MSupplementalDiscMaster mSupplementalDiscMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(mSupplementalDiscMaster, serviceContext);
    }

    /**
    * Caches the m supplemental disc master in the entity cache if it is enabled.
    *
    * @param mSupplementalDiscMaster the m supplemental disc master
    */
    public static void cacheResult(
        com.stpl.app.model.MSupplementalDiscMaster mSupplementalDiscMaster) {
        getPersistence().cacheResult(mSupplementalDiscMaster);
    }

    /**
    * Caches the m supplemental disc masters in the entity cache if it is enabled.
    *
    * @param mSupplementalDiscMasters the m supplemental disc masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.MSupplementalDiscMaster> mSupplementalDiscMasters) {
        getPersistence().cacheResult(mSupplementalDiscMasters);
    }

    /**
    * Creates a new m supplemental disc master with the primary key. Does not add the m supplemental disc master to the database.
    *
    * @param projectionDetailsSid the primary key for the new m supplemental disc master
    * @return the new m supplemental disc master
    */
    public static com.stpl.app.model.MSupplementalDiscMaster create(
        int projectionDetailsSid) {
        return getPersistence().create(projectionDetailsSid);
    }

    /**
    * Removes the m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc master
    * @return the m supplemental disc master that was removed
    * @throws com.stpl.app.NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MSupplementalDiscMaster remove(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSupplementalDiscMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(projectionDetailsSid);
    }

    public static com.stpl.app.model.MSupplementalDiscMaster updateImpl(
        com.stpl.app.model.MSupplementalDiscMaster mSupplementalDiscMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(mSupplementalDiscMaster);
    }

    /**
    * Returns the m supplemental disc master with the primary key or throws a {@link com.stpl.app.NoSuchMSupplementalDiscMasterException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc master
    * @return the m supplemental disc master
    * @throws com.stpl.app.NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MSupplementalDiscMaster findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSupplementalDiscMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(projectionDetailsSid);
    }

    /**
    * Returns the m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc master
    * @return the m supplemental disc master, or <code>null</code> if a m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MSupplementalDiscMaster fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(projectionDetailsSid);
    }

    /**
    * Returns all the m supplemental disc masters.
    *
    * @return the m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MSupplementalDiscMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the m supplemental disc masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m supplemental disc masters
    * @param end the upper bound of the range of m supplemental disc masters (not inclusive)
    * @return the range of m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MSupplementalDiscMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the m supplemental disc masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m supplemental disc masters
    * @param end the upper bound of the range of m supplemental disc masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MSupplementalDiscMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the m supplemental disc masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of m supplemental disc masters.
    *
    * @return the number of m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MSupplementalDiscMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MSupplementalDiscMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MSupplementalDiscMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(MSupplementalDiscMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MSupplementalDiscMasterPersistence persistence) {
    }
}
