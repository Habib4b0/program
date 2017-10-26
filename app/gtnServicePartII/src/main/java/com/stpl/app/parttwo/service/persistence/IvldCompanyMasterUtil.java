package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCompanyMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld company master service. This utility wraps {@link IvldCompanyMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyMasterPersistence
 * @see IvldCompanyMasterPersistenceImpl
 * @generated
 */
public class IvldCompanyMasterUtil {
    private static IvldCompanyMasterPersistence _persistence;

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
    public static void clearCache(IvldCompanyMaster ivldCompanyMaster) {
        getPersistence().clearCache(ivldCompanyMaster);
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
    public static List<IvldCompanyMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldCompanyMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldCompanyMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldCompanyMaster update(IvldCompanyMaster ivldCompanyMaster)
        throws SystemException {
        return getPersistence().update(ivldCompanyMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldCompanyMaster update(
        IvldCompanyMaster ivldCompanyMaster, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ivldCompanyMaster, serviceContext);
    }

    /**
    * Caches the ivld company master in the entity cache if it is enabled.
    *
    * @param ivldCompanyMaster the ivld company master
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.IvldCompanyMaster ivldCompanyMaster) {
        getPersistence().cacheResult(ivldCompanyMaster);
    }

    /**
    * Caches the ivld company masters in the entity cache if it is enabled.
    *
    * @param ivldCompanyMasters the ivld company masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldCompanyMaster> ivldCompanyMasters) {
        getPersistence().cacheResult(ivldCompanyMasters);
    }

    /**
    * Creates a new ivld company master with the primary key. Does not add the ivld company master to the database.
    *
    * @param ivldCompanyMasterSid the primary key for the new ivld company master
    * @return the new ivld company master
    */
    public static com.stpl.app.parttwo.model.IvldCompanyMaster create(
        int ivldCompanyMasterSid) {
        return getPersistence().create(ivldCompanyMasterSid);
    }

    /**
    * Removes the ivld company master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyMasterSid the primary key of the ivld company master
    * @return the ivld company master that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCompanyMaster remove(
        int ivldCompanyMasterSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldCompanyMasterSid);
    }

    public static com.stpl.app.parttwo.model.IvldCompanyMaster updateImpl(
        com.stpl.app.parttwo.model.IvldCompanyMaster ivldCompanyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldCompanyMaster);
    }

    /**
    * Returns the ivld company master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCompanyMasterException} if it could not be found.
    *
    * @param ivldCompanyMasterSid the primary key of the ivld company master
    * @return the ivld company master
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCompanyMaster findByPrimaryKey(
        int ivldCompanyMasterSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldCompanyMasterSid);
    }

    /**
    * Returns the ivld company master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldCompanyMasterSid the primary key of the ivld company master
    * @return the ivld company master, or <code>null</code> if a ivld company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCompanyMaster fetchByPrimaryKey(
        int ivldCompanyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldCompanyMasterSid);
    }

    /**
    * Returns all the ivld company masters.
    *
    * @return the ivld company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCompanyMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company masters
    * @param end the upper bound of the range of ivld company masters (not inclusive)
    * @return the range of ivld company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCompanyMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company masters
    * @param end the upper bound of the range of ivld company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCompanyMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld company masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld company masters.
    *
    * @return the number of ivld company masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldCompanyMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldCompanyMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    IvldCompanyMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldCompanyMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldCompanyMasterPersistence persistence) {
    }
}
