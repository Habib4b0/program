package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyQualifier;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the company qualifier service. This utility wraps {@link CompanyQualifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyQualifierPersistence
 * @see CompanyQualifierPersistenceImpl
 * @generated
 */
public class CompanyQualifierUtil {
    private static CompanyQualifierPersistence _persistence;

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
    public static void clearCache(CompanyQualifier companyQualifier) {
        getPersistence().clearCache(companyQualifier);
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
    public static List<CompanyQualifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CompanyQualifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CompanyQualifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CompanyQualifier update(CompanyQualifier companyQualifier)
        throws SystemException {
        return getPersistence().update(companyQualifier);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CompanyQualifier update(CompanyQualifier companyQualifier,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(companyQualifier, serviceContext);
    }

    /**
    * Returns all the company qualifiers where companyQualifierName = &#63;.
    *
    * @param companyQualifierName the company qualifier name
    * @return the matching company qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyQualifier> findByCompanyCrtQualifierName(
        java.lang.String companyQualifierName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyCrtQualifierName(companyQualifierName);
    }

    /**
    * Returns a range of all the company qualifiers where companyQualifierName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyQualifierName the company qualifier name
    * @param start the lower bound of the range of company qualifiers
    * @param end the upper bound of the range of company qualifiers (not inclusive)
    * @return the range of matching company qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyQualifier> findByCompanyCrtQualifierName(
        java.lang.String companyQualifierName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyCrtQualifierName(companyQualifierName, start,
            end);
    }

    /**
    * Returns an ordered range of all the company qualifiers where companyQualifierName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyQualifierName the company qualifier name
    * @param start the lower bound of the range of company qualifiers
    * @param end the upper bound of the range of company qualifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyQualifier> findByCompanyCrtQualifierName(
        java.lang.String companyQualifierName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyCrtQualifierName(companyQualifierName, start,
            end, orderByComparator);
    }

    /**
    * Returns the first company qualifier in the ordered set where companyQualifierName = &#63;.
    *
    * @param companyQualifierName the company qualifier name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company qualifier
    * @throws com.stpl.app.NoSuchCompanyQualifierException if a matching company qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier findByCompanyCrtQualifierName_First(
        java.lang.String companyQualifierName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyCrtQualifierName_First(companyQualifierName,
            orderByComparator);
    }

    /**
    * Returns the first company qualifier in the ordered set where companyQualifierName = &#63;.
    *
    * @param companyQualifierName the company qualifier name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier fetchByCompanyCrtQualifierName_First(
        java.lang.String companyQualifierName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyCrtQualifierName_First(companyQualifierName,
            orderByComparator);
    }

    /**
    * Returns the last company qualifier in the ordered set where companyQualifierName = &#63;.
    *
    * @param companyQualifierName the company qualifier name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company qualifier
    * @throws com.stpl.app.NoSuchCompanyQualifierException if a matching company qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier findByCompanyCrtQualifierName_Last(
        java.lang.String companyQualifierName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyCrtQualifierName_Last(companyQualifierName,
            orderByComparator);
    }

    /**
    * Returns the last company qualifier in the ordered set where companyQualifierName = &#63;.
    *
    * @param companyQualifierName the company qualifier name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier fetchByCompanyCrtQualifierName_Last(
        java.lang.String companyQualifierName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyCrtQualifierName_Last(companyQualifierName,
            orderByComparator);
    }

    /**
    * Returns the company qualifiers before and after the current company qualifier in the ordered set where companyQualifierName = &#63;.
    *
    * @param companyQualifierSid the primary key of the current company qualifier
    * @param companyQualifierName the company qualifier name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company qualifier
    * @throws com.stpl.app.NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier[] findByCompanyCrtQualifierName_PrevAndNext(
        int companyQualifierSid, java.lang.String companyQualifierName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyCrtQualifierName_PrevAndNext(companyQualifierSid,
            companyQualifierName, orderByComparator);
    }

    /**
    * Removes all the company qualifiers where companyQualifierName = &#63; from the database.
    *
    * @param companyQualifierName the company qualifier name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyCrtQualifierName(
        java.lang.String companyQualifierName)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyCrtQualifierName(companyQualifierName);
    }

    /**
    * Returns the number of company qualifiers where companyQualifierName = &#63;.
    *
    * @param companyQualifierName the company qualifier name
    * @return the number of matching company qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyCrtQualifierName(
        java.lang.String companyQualifierName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByCompanyCrtQualifierName(companyQualifierName);
    }

    /**
    * Returns the company qualifier where companyQualifierName = &#63; or throws a {@link com.stpl.app.NoSuchCompanyQualifierException} if it could not be found.
    *
    * @param companyQualifierName the company qualifier name
    * @return the matching company qualifier
    * @throws com.stpl.app.NoSuchCompanyQualifierException if a matching company qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier findByCompanyCrtQualifierByName(
        java.lang.String companyQualifierName)
        throws com.stpl.app.NoSuchCompanyQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyCrtQualifierByName(companyQualifierName);
    }

    /**
    * Returns the company qualifier where companyQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param companyQualifierName the company qualifier name
    * @return the matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier fetchByCompanyCrtQualifierByName(
        java.lang.String companyQualifierName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyCrtQualifierByName(companyQualifierName);
    }

    /**
    * Returns the company qualifier where companyQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param companyQualifierName the company qualifier name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier fetchByCompanyCrtQualifierByName(
        java.lang.String companyQualifierName, boolean retrieveFromCache)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyCrtQualifierByName(companyQualifierName,
            retrieveFromCache);
    }

    /**
    * Removes the company qualifier where companyQualifierName = &#63; from the database.
    *
    * @param companyQualifierName the company qualifier name
    * @return the company qualifier that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier removeByCompanyCrtQualifierByName(
        java.lang.String companyQualifierName)
        throws com.stpl.app.NoSuchCompanyQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .removeByCompanyCrtQualifierByName(companyQualifierName);
    }

    /**
    * Returns the number of company qualifiers where companyQualifierName = &#63;.
    *
    * @param companyQualifierName the company qualifier name
    * @return the number of matching company qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyCrtQualifierByName(
        java.lang.String companyQualifierName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByCompanyCrtQualifierByName(companyQualifierName);
    }

    /**
    * Caches the company qualifier in the entity cache if it is enabled.
    *
    * @param companyQualifier the company qualifier
    */
    public static void cacheResult(
        com.stpl.app.model.CompanyQualifier companyQualifier) {
        getPersistence().cacheResult(companyQualifier);
    }

    /**
    * Caches the company qualifiers in the entity cache if it is enabled.
    *
    * @param companyQualifiers the company qualifiers
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CompanyQualifier> companyQualifiers) {
        getPersistence().cacheResult(companyQualifiers);
    }

    /**
    * Creates a new company qualifier with the primary key. Does not add the company qualifier to the database.
    *
    * @param companyQualifierSid the primary key for the new company qualifier
    * @return the new company qualifier
    */
    public static com.stpl.app.model.CompanyQualifier create(
        int companyQualifierSid) {
        return getPersistence().create(companyQualifierSid);
    }

    /**
    * Removes the company qualifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyQualifierSid the primary key of the company qualifier
    * @return the company qualifier that was removed
    * @throws com.stpl.app.NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier remove(
        int companyQualifierSid)
        throws com.stpl.app.NoSuchCompanyQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(companyQualifierSid);
    }

    public static com.stpl.app.model.CompanyQualifier updateImpl(
        com.stpl.app.model.CompanyQualifier companyQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(companyQualifier);
    }

    /**
    * Returns the company qualifier with the primary key or throws a {@link com.stpl.app.NoSuchCompanyQualifierException} if it could not be found.
    *
    * @param companyQualifierSid the primary key of the company qualifier
    * @return the company qualifier
    * @throws com.stpl.app.NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier findByPrimaryKey(
        int companyQualifierSid)
        throws com.stpl.app.NoSuchCompanyQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(companyQualifierSid);
    }

    /**
    * Returns the company qualifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyQualifierSid the primary key of the company qualifier
    * @return the company qualifier, or <code>null</code> if a company qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyQualifier fetchByPrimaryKey(
        int companyQualifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(companyQualifierSid);
    }

    /**
    * Returns all the company qualifiers.
    *
    * @return the company qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyQualifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the company qualifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company qualifiers
    * @param end the upper bound of the range of company qualifiers (not inclusive)
    * @return the range of company qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyQualifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the company qualifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company qualifiers
    * @param end the upper bound of the range of company qualifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of company qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyQualifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the company qualifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of company qualifiers.
    *
    * @return the number of company qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CompanyQualifierPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CompanyQualifierPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CompanyQualifierPersistence.class.getName());

            ReferenceRegistry.registerReference(CompanyQualifierUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CompanyQualifierPersistence persistence) {
    }
}
