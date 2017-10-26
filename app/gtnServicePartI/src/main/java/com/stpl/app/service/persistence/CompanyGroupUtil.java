package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyGroup;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the company group service. This utility wraps {@link CompanyGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyGroupPersistence
 * @see CompanyGroupPersistenceImpl
 * @generated
 */
public class CompanyGroupUtil {
    private static CompanyGroupPersistence _persistence;

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
    public static void clearCache(CompanyGroup companyGroup) {
        getPersistence().clearCache(companyGroup);
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
    public static List<CompanyGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CompanyGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CompanyGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CompanyGroup update(CompanyGroup companyGroup)
        throws SystemException {
        return getPersistence().update(companyGroup);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CompanyGroup update(CompanyGroup companyGroup,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(companyGroup, serviceContext);
    }

    /**
    * Caches the company group in the entity cache if it is enabled.
    *
    * @param companyGroup the company group
    */
    public static void cacheResult(com.stpl.app.model.CompanyGroup companyGroup) {
        getPersistence().cacheResult(companyGroup);
    }

    /**
    * Caches the company groups in the entity cache if it is enabled.
    *
    * @param companyGroups the company groups
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CompanyGroup> companyGroups) {
        getPersistence().cacheResult(companyGroups);
    }

    /**
    * Creates a new company group with the primary key. Does not add the company group to the database.
    *
    * @param companyGroupSid the primary key for the new company group
    * @return the new company group
    */
    public static com.stpl.app.model.CompanyGroup create(int companyGroupSid) {
        return getPersistence().create(companyGroupSid);
    }

    /**
    * Removes the company group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyGroupSid the primary key of the company group
    * @return the company group that was removed
    * @throws com.stpl.app.NoSuchCompanyGroupException if a company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyGroup remove(int companyGroupSid)
        throws com.stpl.app.NoSuchCompanyGroupException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(companyGroupSid);
    }

    public static com.stpl.app.model.CompanyGroup updateImpl(
        com.stpl.app.model.CompanyGroup companyGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(companyGroup);
    }

    /**
    * Returns the company group with the primary key or throws a {@link com.stpl.app.NoSuchCompanyGroupException} if it could not be found.
    *
    * @param companyGroupSid the primary key of the company group
    * @return the company group
    * @throws com.stpl.app.NoSuchCompanyGroupException if a company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyGroup findByPrimaryKey(
        int companyGroupSid)
        throws com.stpl.app.NoSuchCompanyGroupException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(companyGroupSid);
    }

    /**
    * Returns the company group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyGroupSid the primary key of the company group
    * @return the company group, or <code>null</code> if a company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyGroup fetchByPrimaryKey(
        int companyGroupSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(companyGroupSid);
    }

    /**
    * Returns all the company groups.
    *
    * @return the company groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyGroup> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the company groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company groups
    * @param end the upper bound of the range of company groups (not inclusive)
    * @return the range of company groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyGroup> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the company groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company groups
    * @param end the upper bound of the range of company groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of company groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyGroup> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the company groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of company groups.
    *
    * @return the number of company groups
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CompanyGroupPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CompanyGroupPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CompanyGroupPersistence.class.getName());

            ReferenceRegistry.registerReference(CompanyGroupUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CompanyGroupPersistence persistence) {
    }
}
