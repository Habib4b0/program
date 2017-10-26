package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldCpi;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld cpi service. This utility wraps {@link IvldCpiPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCpiPersistence
 * @see IvldCpiPersistenceImpl
 * @generated
 */
public class IvldCpiUtil {
    private static IvldCpiPersistence _persistence;

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
    public static void clearCache(IvldCpi ivldCpi) {
        getPersistence().clearCache(ivldCpi);
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
    public static List<IvldCpi> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldCpi> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldCpi> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldCpi update(IvldCpi ivldCpi) throws SystemException {
        return getPersistence().update(ivldCpi);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldCpi update(IvldCpi ivldCpi, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ivldCpi, serviceContext);
    }

    /**
    * Caches the ivld cpi in the entity cache if it is enabled.
    *
    * @param ivldCpi the ivld cpi
    */
    public static void cacheResult(com.stpl.app.model.IvldCpi ivldCpi) {
        getPersistence().cacheResult(ivldCpi);
    }

    /**
    * Caches the ivld cpis in the entity cache if it is enabled.
    *
    * @param ivldCpis the ivld cpis
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldCpi> ivldCpis) {
        getPersistence().cacheResult(ivldCpis);
    }

    /**
    * Creates a new ivld cpi with the primary key. Does not add the ivld cpi to the database.
    *
    * @param ivldCpiSid the primary key for the new ivld cpi
    * @return the new ivld cpi
    */
    public static com.stpl.app.model.IvldCpi create(int ivldCpiSid) {
        return getPersistence().create(ivldCpiSid);
    }

    /**
    * Removes the ivld cpi with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCpiSid the primary key of the ivld cpi
    * @return the ivld cpi that was removed
    * @throws com.stpl.app.NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldCpi remove(int ivldCpiSid)
        throws com.stpl.app.NoSuchIvldCpiException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldCpiSid);
    }

    public static com.stpl.app.model.IvldCpi updateImpl(
        com.stpl.app.model.IvldCpi ivldCpi)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldCpi);
    }

    /**
    * Returns the ivld cpi with the primary key or throws a {@link com.stpl.app.NoSuchIvldCpiException} if it could not be found.
    *
    * @param ivldCpiSid the primary key of the ivld cpi
    * @return the ivld cpi
    * @throws com.stpl.app.NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldCpi findByPrimaryKey(int ivldCpiSid)
        throws com.stpl.app.NoSuchIvldCpiException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldCpiSid);
    }

    /**
    * Returns the ivld cpi with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldCpiSid the primary key of the ivld cpi
    * @return the ivld cpi, or <code>null</code> if a ivld cpi with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldCpi fetchByPrimaryKey(int ivldCpiSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldCpiSid);
    }

    /**
    * Returns all the ivld cpis.
    *
    * @return the ivld cpis
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldCpi> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld cpis.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld cpis
    * @param end the upper bound of the range of ivld cpis (not inclusive)
    * @return the range of ivld cpis
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldCpi> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld cpis.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld cpis
    * @param end the upper bound of the range of ivld cpis (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld cpis
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldCpi> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld cpis from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld cpis.
    *
    * @return the number of ivld cpis
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldCpiPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldCpiPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldCpiPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldCpiUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldCpiPersistence persistence) {
    }
}
