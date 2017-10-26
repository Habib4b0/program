package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCompanyParentDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw company parent details service. This utility wraps {@link VwCompanyParentDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyParentDetailsPersistence
 * @see VwCompanyParentDetailsPersistenceImpl
 * @generated
 */
public class VwCompanyParentDetailsUtil {
    private static VwCompanyParentDetailsPersistence _persistence;

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
    public static void clearCache(VwCompanyParentDetails vwCompanyParentDetails) {
        getPersistence().clearCache(vwCompanyParentDetails);
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
    public static List<VwCompanyParentDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwCompanyParentDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwCompanyParentDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwCompanyParentDetails update(
        VwCompanyParentDetails vwCompanyParentDetails)
        throws SystemException {
        return getPersistence().update(vwCompanyParentDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwCompanyParentDetails update(
        VwCompanyParentDetails vwCompanyParentDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(vwCompanyParentDetails, serviceContext);
    }

    /**
    * Caches the vw company parent details in the entity cache if it is enabled.
    *
    * @param vwCompanyParentDetails the vw company parent details
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.VwCompanyParentDetails vwCompanyParentDetails) {
        getPersistence().cacheResult(vwCompanyParentDetails);
    }

    /**
    * Caches the vw company parent detailses in the entity cache if it is enabled.
    *
    * @param vwCompanyParentDetailses the vw company parent detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwCompanyParentDetails> vwCompanyParentDetailses) {
        getPersistence().cacheResult(vwCompanyParentDetailses);
    }

    /**
    * Creates a new vw company parent details with the primary key. Does not add the vw company parent details to the database.
    *
    * @param companyParentDetailsSid the primary key for the new vw company parent details
    * @return the new vw company parent details
    */
    public static com.stpl.app.parttwo.model.VwCompanyParentDetails create(
        int companyParentDetailsSid) {
        return getPersistence().create(companyParentDetailsSid);
    }

    /**
    * Removes the vw company parent details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyParentDetailsSid the primary key of the vw company parent details
    * @return the vw company parent details that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCompanyParentDetails remove(
        int companyParentDetailsSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(companyParentDetailsSid);
    }

    public static com.stpl.app.parttwo.model.VwCompanyParentDetails updateImpl(
        com.stpl.app.parttwo.model.VwCompanyParentDetails vwCompanyParentDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwCompanyParentDetails);
    }

    /**
    * Returns the vw company parent details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException} if it could not be found.
    *
    * @param companyParentDetailsSid the primary key of the vw company parent details
    * @return the vw company parent details
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCompanyParentDetails findByPrimaryKey(
        int companyParentDetailsSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(companyParentDetailsSid);
    }

    /**
    * Returns the vw company parent details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyParentDetailsSid the primary key of the vw company parent details
    * @return the vw company parent details, or <code>null</code> if a vw company parent details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCompanyParentDetails fetchByPrimaryKey(
        int companyParentDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(companyParentDetailsSid);
    }

    /**
    * Returns all the vw company parent detailses.
    *
    * @return the vw company parent detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCompanyParentDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the vw company parent detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company parent detailses
    * @param end the upper bound of the range of vw company parent detailses (not inclusive)
    * @return the range of vw company parent detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCompanyParentDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the vw company parent detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company parent detailses
    * @param end the upper bound of the range of vw company parent detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw company parent detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCompanyParentDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw company parent detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw company parent detailses.
    *
    * @return the number of vw company parent detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwCompanyParentDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwCompanyParentDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    VwCompanyParentDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(VwCompanyParentDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwCompanyParentDetailsPersistence persistence) {
    }
}
