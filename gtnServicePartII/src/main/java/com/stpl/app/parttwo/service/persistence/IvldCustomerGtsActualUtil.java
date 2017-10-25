package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCustomerGtsActual;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld customer gts actual service. This utility wraps {@link IvldCustomerGtsActualPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCustomerGtsActualPersistence
 * @see IvldCustomerGtsActualPersistenceImpl
 * @generated
 */
public class IvldCustomerGtsActualUtil {
    private static IvldCustomerGtsActualPersistence _persistence;

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
    public static void clearCache(IvldCustomerGtsActual ivldCustomerGtsActual) {
        getPersistence().clearCache(ivldCustomerGtsActual);
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
    public static List<IvldCustomerGtsActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldCustomerGtsActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldCustomerGtsActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldCustomerGtsActual update(
        IvldCustomerGtsActual ivldCustomerGtsActual) throws SystemException {
        return getPersistence().update(ivldCustomerGtsActual);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldCustomerGtsActual update(
        IvldCustomerGtsActual ivldCustomerGtsActual,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldCustomerGtsActual, serviceContext);
    }

    /**
    * Caches the ivld customer gts actual in the entity cache if it is enabled.
    *
    * @param ivldCustomerGtsActual the ivld customer gts actual
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.IvldCustomerGtsActual ivldCustomerGtsActual) {
        getPersistence().cacheResult(ivldCustomerGtsActual);
    }

    /**
    * Caches the ivld customer gts actuals in the entity cache if it is enabled.
    *
    * @param ivldCustomerGtsActuals the ivld customer gts actuals
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldCustomerGtsActual> ivldCustomerGtsActuals) {
        getPersistence().cacheResult(ivldCustomerGtsActuals);
    }

    /**
    * Creates a new ivld customer gts actual with the primary key. Does not add the ivld customer gts actual to the database.
    *
    * @param ivldCustomerGtsActualSid the primary key for the new ivld customer gts actual
    * @return the new ivld customer gts actual
    */
    public static com.stpl.app.parttwo.model.IvldCustomerGtsActual create(
        int ivldCustomerGtsActualSid) {
        return getPersistence().create(ivldCustomerGtsActualSid);
    }

    /**
    * Removes the ivld customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
    * @return the ivld customer gts actual that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCustomerGtsActual remove(
        int ivldCustomerGtsActualSid)
        throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldCustomerGtsActualSid);
    }

    public static com.stpl.app.parttwo.model.IvldCustomerGtsActual updateImpl(
        com.stpl.app.parttwo.model.IvldCustomerGtsActual ivldCustomerGtsActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldCustomerGtsActual);
    }

    /**
    * Returns the ivld customer gts actual with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException} if it could not be found.
    *
    * @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
    * @return the ivld customer gts actual
    * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCustomerGtsActual findByPrimaryKey(
        int ivldCustomerGtsActualSid)
        throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsActualException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldCustomerGtsActualSid);
    }

    /**
    * Returns the ivld customer gts actual with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
    * @return the ivld customer gts actual, or <code>null</code> if a ivld customer gts actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCustomerGtsActual fetchByPrimaryKey(
        int ivldCustomerGtsActualSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldCustomerGtsActualSid);
    }

    /**
    * Returns all the ivld customer gts actuals.
    *
    * @return the ivld customer gts actuals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCustomerGtsActual> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld customer gts actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld customer gts actuals
    * @param end the upper bound of the range of ivld customer gts actuals (not inclusive)
    * @return the range of ivld customer gts actuals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCustomerGtsActual> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld customer gts actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld customer gts actuals
    * @param end the upper bound of the range of ivld customer gts actuals (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld customer gts actuals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCustomerGtsActual> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld customer gts actuals from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld customer gts actuals.
    *
    * @return the number of ivld customer gts actuals
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldCustomerGtsActualPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldCustomerGtsActualPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    IvldCustomerGtsActualPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldCustomerGtsActualUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldCustomerGtsActualPersistence persistence) {
    }
}
