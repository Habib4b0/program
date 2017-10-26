package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyTradeClass;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the company trade class service. This utility wraps {@link CompanyTradeClassPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyTradeClassPersistence
 * @see CompanyTradeClassPersistenceImpl
 * @generated
 */
public class CompanyTradeClassUtil {
    private static CompanyTradeClassPersistence _persistence;

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
    public static void clearCache(CompanyTradeClass companyTradeClass) {
        getPersistence().clearCache(companyTradeClass);
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
    public static List<CompanyTradeClass> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CompanyTradeClass> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CompanyTradeClass> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CompanyTradeClass update(CompanyTradeClass companyTradeClass)
        throws SystemException {
        return getPersistence().update(companyTradeClass);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CompanyTradeClass update(
        CompanyTradeClass companyTradeClass, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(companyTradeClass, serviceContext);
    }

    /**
    * Caches the company trade class in the entity cache if it is enabled.
    *
    * @param companyTradeClass the company trade class
    */
    public static void cacheResult(
        com.stpl.app.model.CompanyTradeClass companyTradeClass) {
        getPersistence().cacheResult(companyTradeClass);
    }

    /**
    * Caches the company trade classes in the entity cache if it is enabled.
    *
    * @param companyTradeClasses the company trade classes
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CompanyTradeClass> companyTradeClasses) {
        getPersistence().cacheResult(companyTradeClasses);
    }

    /**
    * Creates a new company trade class with the primary key. Does not add the company trade class to the database.
    *
    * @param companyTradeClassSid the primary key for the new company trade class
    * @return the new company trade class
    */
    public static com.stpl.app.model.CompanyTradeClass create(
        int companyTradeClassSid) {
        return getPersistence().create(companyTradeClassSid);
    }

    /**
    * Removes the company trade class with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyTradeClassSid the primary key of the company trade class
    * @return the company trade class that was removed
    * @throws com.stpl.app.NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyTradeClass remove(
        int companyTradeClassSid)
        throws com.stpl.app.NoSuchCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(companyTradeClassSid);
    }

    public static com.stpl.app.model.CompanyTradeClass updateImpl(
        com.stpl.app.model.CompanyTradeClass companyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(companyTradeClass);
    }

    /**
    * Returns the company trade class with the primary key or throws a {@link com.stpl.app.NoSuchCompanyTradeClassException} if it could not be found.
    *
    * @param companyTradeClassSid the primary key of the company trade class
    * @return the company trade class
    * @throws com.stpl.app.NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyTradeClass findByPrimaryKey(
        int companyTradeClassSid)
        throws com.stpl.app.NoSuchCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(companyTradeClassSid);
    }

    /**
    * Returns the company trade class with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyTradeClassSid the primary key of the company trade class
    * @return the company trade class, or <code>null</code> if a company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyTradeClass fetchByPrimaryKey(
        int companyTradeClassSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(companyTradeClassSid);
    }

    /**
    * Returns all the company trade classes.
    *
    * @return the company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyTradeClass> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company trade classes
    * @param end the upper bound of the range of company trade classes (not inclusive)
    * @return the range of company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyTradeClass> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company trade classes
    * @param end the upper bound of the range of company trade classes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyTradeClass> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the company trade classes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of company trade classes.
    *
    * @return the number of company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CompanyTradeClassPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CompanyTradeClassPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CompanyTradeClassPersistence.class.getName());

            ReferenceRegistry.registerReference(CompanyTradeClassUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CompanyTradeClassPersistence persistence) {
    }
}
