package com.stpl.app.service.persistence;

import com.stpl.app.model.NmPpaProjectionMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the nm ppa projection master service. This utility wraps {@link NmPpaProjectionMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmPpaProjectionMasterPersistence
 * @see NmPpaProjectionMasterPersistenceImpl
 * @generated
 */
public class NmPpaProjectionMasterUtil {
    private static NmPpaProjectionMasterPersistence _persistence;

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
    public static void clearCache(NmPpaProjectionMaster nmPpaProjectionMaster) {
        getPersistence().clearCache(nmPpaProjectionMaster);
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
    public static List<NmPpaProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<NmPpaProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<NmPpaProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static NmPpaProjectionMaster update(
        NmPpaProjectionMaster nmPpaProjectionMaster) throws SystemException {
        return getPersistence().update(nmPpaProjectionMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static NmPpaProjectionMaster update(
        NmPpaProjectionMaster nmPpaProjectionMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(nmPpaProjectionMaster, serviceContext);
    }

    /**
    * Caches the nm ppa projection master in the entity cache if it is enabled.
    *
    * @param nmPpaProjectionMaster the nm ppa projection master
    */
    public static void cacheResult(
        com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster) {
        getPersistence().cacheResult(nmPpaProjectionMaster);
    }

    /**
    * Caches the nm ppa projection masters in the entity cache if it is enabled.
    *
    * @param nmPpaProjectionMasters the nm ppa projection masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.NmPpaProjectionMaster> nmPpaProjectionMasters) {
        getPersistence().cacheResult(nmPpaProjectionMasters);
    }

    /**
    * Creates a new nm ppa projection master with the primary key. Does not add the nm ppa projection master to the database.
    *
    * @param projectionDetailsSid the primary key for the new nm ppa projection master
    * @return the new nm ppa projection master
    */
    public static com.stpl.app.model.NmPpaProjectionMaster create(
        int projectionDetailsSid) {
        return getPersistence().create(projectionDetailsSid);
    }

    /**
    * Removes the nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the nm ppa projection master
    * @return the nm ppa projection master that was removed
    * @throws com.stpl.app.NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmPpaProjectionMaster remove(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchNmPpaProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(projectionDetailsSid);
    }

    public static com.stpl.app.model.NmPpaProjectionMaster updateImpl(
        com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(nmPpaProjectionMaster);
    }

    /**
    * Returns the nm ppa projection master with the primary key or throws a {@link com.stpl.app.NoSuchNmPpaProjectionMasterException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the nm ppa projection master
    * @return the nm ppa projection master
    * @throws com.stpl.app.NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmPpaProjectionMaster findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchNmPpaProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(projectionDetailsSid);
    }

    /**
    * Returns the nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the nm ppa projection master
    * @return the nm ppa projection master, or <code>null</code> if a nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmPpaProjectionMaster fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(projectionDetailsSid);
    }

    /**
    * Returns all the nm ppa projection masters.
    *
    * @return the nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmPpaProjectionMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the nm ppa projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm ppa projection masters
    * @param end the upper bound of the range of nm ppa projection masters (not inclusive)
    * @return the range of nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmPpaProjectionMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the nm ppa projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm ppa projection masters
    * @param end the upper bound of the range of nm ppa projection masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmPpaProjectionMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the nm ppa projection masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of nm ppa projection masters.
    *
    * @return the number of nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static NmPpaProjectionMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (NmPpaProjectionMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NmPpaProjectionMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(NmPpaProjectionMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(NmPpaProjectionMasterPersistence persistence) {
    }
}
