package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCompanyIdentifier;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw company identifier service. This utility wraps {@link VwCompanyIdentifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyIdentifierPersistence
 * @see VwCompanyIdentifierPersistenceImpl
 * @generated
 */
public class VwCompanyIdentifierUtil {
    private static VwCompanyIdentifierPersistence _persistence;

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
    public static void clearCache(VwCompanyIdentifier vwCompanyIdentifier) {
        getPersistence().clearCache(vwCompanyIdentifier);
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
    public static List<VwCompanyIdentifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwCompanyIdentifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwCompanyIdentifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwCompanyIdentifier update(
        VwCompanyIdentifier vwCompanyIdentifier) throws SystemException {
        return getPersistence().update(vwCompanyIdentifier);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwCompanyIdentifier update(
        VwCompanyIdentifier vwCompanyIdentifier, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(vwCompanyIdentifier, serviceContext);
    }

    /**
    * Caches the vw company identifier in the entity cache if it is enabled.
    *
    * @param vwCompanyIdentifier the vw company identifier
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.VwCompanyIdentifier vwCompanyIdentifier) {
        getPersistence().cacheResult(vwCompanyIdentifier);
    }

    /**
    * Caches the vw company identifiers in the entity cache if it is enabled.
    *
    * @param vwCompanyIdentifiers the vw company identifiers
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwCompanyIdentifier> vwCompanyIdentifiers) {
        getPersistence().cacheResult(vwCompanyIdentifiers);
    }

    /**
    * Creates a new vw company identifier with the primary key. Does not add the vw company identifier to the database.
    *
    * @param companyIdentifierSid the primary key for the new vw company identifier
    * @return the new vw company identifier
    */
    public static com.stpl.app.parttwo.model.VwCompanyIdentifier create(
        int companyIdentifierSid) {
        return getPersistence().create(companyIdentifierSid);
    }

    /**
    * Removes the vw company identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyIdentifierSid the primary key of the vw company identifier
    * @return the vw company identifier that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException if a vw company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCompanyIdentifier remove(
        int companyIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(companyIdentifierSid);
    }

    public static com.stpl.app.parttwo.model.VwCompanyIdentifier updateImpl(
        com.stpl.app.parttwo.model.VwCompanyIdentifier vwCompanyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwCompanyIdentifier);
    }

    /**
    * Returns the vw company identifier with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException} if it could not be found.
    *
    * @param companyIdentifierSid the primary key of the vw company identifier
    * @return the vw company identifier
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException if a vw company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCompanyIdentifier findByPrimaryKey(
        int companyIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(companyIdentifierSid);
    }

    /**
    * Returns the vw company identifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyIdentifierSid the primary key of the vw company identifier
    * @return the vw company identifier, or <code>null</code> if a vw company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCompanyIdentifier fetchByPrimaryKey(
        int companyIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(companyIdentifierSid);
    }

    /**
    * Returns all the vw company identifiers.
    *
    * @return the vw company identifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCompanyIdentifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the vw company identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company identifiers
    * @param end the upper bound of the range of vw company identifiers (not inclusive)
    * @return the range of vw company identifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCompanyIdentifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the vw company identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company identifiers
    * @param end the upper bound of the range of vw company identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw company identifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCompanyIdentifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw company identifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw company identifiers.
    *
    * @return the number of vw company identifiers
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwCompanyIdentifierPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwCompanyIdentifierPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    VwCompanyIdentifierPersistence.class.getName());

            ReferenceRegistry.registerReference(VwCompanyIdentifierUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwCompanyIdentifierPersistence persistence) {
    }
}
