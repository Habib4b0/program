package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCompanyTradeClass;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw company trade class service. This utility wraps {@link VwCompanyTradeClassPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyTradeClassPersistence
 * @see VwCompanyTradeClassPersistenceImpl
 * @generated
 */
public class VwCompanyTradeClassUtil {
    private static VwCompanyTradeClassPersistence _persistence;

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
    public static void clearCache(VwCompanyTradeClass vwCompanyTradeClass) {
        getPersistence().clearCache(vwCompanyTradeClass);
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
    public static List<VwCompanyTradeClass> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwCompanyTradeClass> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwCompanyTradeClass> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwCompanyTradeClass update(
        VwCompanyTradeClass vwCompanyTradeClass) throws SystemException {
        return getPersistence().update(vwCompanyTradeClass);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwCompanyTradeClass update(
        VwCompanyTradeClass vwCompanyTradeClass, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(vwCompanyTradeClass, serviceContext);
    }

    /**
    * Caches the vw company trade class in the entity cache if it is enabled.
    *
    * @param vwCompanyTradeClass the vw company trade class
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.VwCompanyTradeClass vwCompanyTradeClass) {
        getPersistence().cacheResult(vwCompanyTradeClass);
    }

    /**
    * Caches the vw company trade classes in the entity cache if it is enabled.
    *
    * @param vwCompanyTradeClasses the vw company trade classes
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwCompanyTradeClass> vwCompanyTradeClasses) {
        getPersistence().cacheResult(vwCompanyTradeClasses);
    }

    /**
    * Creates a new vw company trade class with the primary key. Does not add the vw company trade class to the database.
    *
    * @param companyTradeClassSid the primary key for the new vw company trade class
    * @return the new vw company trade class
    */
    public static com.stpl.app.parttwo.model.VwCompanyTradeClass create(
        int companyTradeClassSid) {
        return getPersistence().create(companyTradeClassSid);
    }

    /**
    * Removes the vw company trade class with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyTradeClassSid the primary key of the vw company trade class
    * @return the vw company trade class that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCompanyTradeClass remove(
        int companyTradeClassSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(companyTradeClassSid);
    }

    public static com.stpl.app.parttwo.model.VwCompanyTradeClass updateImpl(
        com.stpl.app.parttwo.model.VwCompanyTradeClass vwCompanyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwCompanyTradeClass);
    }

    /**
    * Returns the vw company trade class with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException} if it could not be found.
    *
    * @param companyTradeClassSid the primary key of the vw company trade class
    * @return the vw company trade class
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCompanyTradeClass findByPrimaryKey(
        int companyTradeClassSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(companyTradeClassSid);
    }

    /**
    * Returns the vw company trade class with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyTradeClassSid the primary key of the vw company trade class
    * @return the vw company trade class, or <code>null</code> if a vw company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCompanyTradeClass fetchByPrimaryKey(
        int companyTradeClassSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(companyTradeClassSid);
    }

    /**
    * Returns all the vw company trade classes.
    *
    * @return the vw company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCompanyTradeClass> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the vw company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company trade classes
    * @param end the upper bound of the range of vw company trade classes (not inclusive)
    * @return the range of vw company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCompanyTradeClass> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the vw company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company trade classes
    * @param end the upper bound of the range of vw company trade classes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCompanyTradeClass> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw company trade classes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw company trade classes.
    *
    * @return the number of vw company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwCompanyTradeClassPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwCompanyTradeClassPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    VwCompanyTradeClassPersistence.class.getName());

            ReferenceRegistry.registerReference(VwCompanyTradeClassUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwCompanyTradeClassPersistence persistence) {
    }
}
