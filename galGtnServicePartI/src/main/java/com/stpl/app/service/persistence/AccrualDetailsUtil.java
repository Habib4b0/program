package com.stpl.app.service.persistence;

import com.stpl.app.model.AccrualDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the accrual details service. This utility wraps {@link AccrualDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccrualDetailsPersistence
 * @see AccrualDetailsPersistenceImpl
 * @generated
 */
public class AccrualDetailsUtil {
    private static AccrualDetailsPersistence _persistence;

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
    public static void clearCache(AccrualDetails accrualDetails) {
        getPersistence().clearCache(accrualDetails);
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
    public static List<AccrualDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AccrualDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AccrualDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static AccrualDetails update(AccrualDetails accrualDetails)
        throws SystemException {
        return getPersistence().update(accrualDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static AccrualDetails update(AccrualDetails accrualDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(accrualDetails, serviceContext);
    }

    /**
    * Caches the accrual details in the entity cache if it is enabled.
    *
    * @param accrualDetails the accrual details
    */
    public static void cacheResult(
        com.stpl.app.model.AccrualDetails accrualDetails) {
        getPersistence().cacheResult(accrualDetails);
    }

    /**
    * Caches the accrual detailses in the entity cache if it is enabled.
    *
    * @param accrualDetailses the accrual detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.AccrualDetails> accrualDetailses) {
        getPersistence().cacheResult(accrualDetailses);
    }

    /**
    * Creates a new accrual details with the primary key. Does not add the accrual details to the database.
    *
    * @param accrualDetailsSid the primary key for the new accrual details
    * @return the new accrual details
    */
    public static com.stpl.app.model.AccrualDetails create(
        int accrualDetailsSid) {
        return getPersistence().create(accrualDetailsSid);
    }

    /**
    * Removes the accrual details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accrualDetailsSid the primary key of the accrual details
    * @return the accrual details that was removed
    * @throws com.stpl.app.NoSuchAccrualDetailsException if a accrual details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AccrualDetails remove(
        int accrualDetailsSid)
        throws com.stpl.app.NoSuchAccrualDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(accrualDetailsSid);
    }

    public static com.stpl.app.model.AccrualDetails updateImpl(
        com.stpl.app.model.AccrualDetails accrualDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(accrualDetails);
    }

    /**
    * Returns the accrual details with the primary key or throws a {@link com.stpl.app.NoSuchAccrualDetailsException} if it could not be found.
    *
    * @param accrualDetailsSid the primary key of the accrual details
    * @return the accrual details
    * @throws com.stpl.app.NoSuchAccrualDetailsException if a accrual details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AccrualDetails findByPrimaryKey(
        int accrualDetailsSid)
        throws com.stpl.app.NoSuchAccrualDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(accrualDetailsSid);
    }

    /**
    * Returns the accrual details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accrualDetailsSid the primary key of the accrual details
    * @return the accrual details, or <code>null</code> if a accrual details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AccrualDetails fetchByPrimaryKey(
        int accrualDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(accrualDetailsSid);
    }

    /**
    * Returns all the accrual detailses.
    *
    * @return the accrual detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AccrualDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the accrual detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of accrual detailses
    * @param end the upper bound of the range of accrual detailses (not inclusive)
    * @return the range of accrual detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AccrualDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the accrual detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of accrual detailses
    * @param end the upper bound of the range of accrual detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of accrual detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AccrualDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the accrual detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of accrual detailses.
    *
    * @return the number of accrual detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AccrualDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AccrualDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    AccrualDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(AccrualDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(AccrualDetailsPersistence persistence) {
    }
}
