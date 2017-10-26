package com.stpl.app.service.persistence;

import com.stpl.app.model.CustomViewDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the custom view details service. This utility wraps {@link CustomViewDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomViewDetailsPersistence
 * @see CustomViewDetailsPersistenceImpl
 * @generated
 */
public class CustomViewDetailsUtil {
    private static CustomViewDetailsPersistence _persistence;

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
    public static void clearCache(CustomViewDetails customViewDetails) {
        getPersistence().clearCache(customViewDetails);
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
    public static List<CustomViewDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CustomViewDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CustomViewDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CustomViewDetails update(CustomViewDetails customViewDetails)
        throws SystemException {
        return getPersistence().update(customViewDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CustomViewDetails update(
        CustomViewDetails customViewDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(customViewDetails, serviceContext);
    }

    /**
    * Caches the custom view details in the entity cache if it is enabled.
    *
    * @param customViewDetails the custom view details
    */
    public static void cacheResult(
        com.stpl.app.model.CustomViewDetails customViewDetails) {
        getPersistence().cacheResult(customViewDetails);
    }

    /**
    * Caches the custom view detailses in the entity cache if it is enabled.
    *
    * @param customViewDetailses the custom view detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CustomViewDetails> customViewDetailses) {
        getPersistence().cacheResult(customViewDetailses);
    }

    /**
    * Creates a new custom view details with the primary key. Does not add the custom view details to the database.
    *
    * @param customViewDetailsSid the primary key for the new custom view details
    * @return the new custom view details
    */
    public static com.stpl.app.model.CustomViewDetails create(
        int customViewDetailsSid) {
        return getPersistence().create(customViewDetailsSid);
    }

    /**
    * Removes the custom view details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customViewDetailsSid the primary key of the custom view details
    * @return the custom view details that was removed
    * @throws com.stpl.app.NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CustomViewDetails remove(
        int customViewDetailsSid)
        throws com.stpl.app.NoSuchCustomViewDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(customViewDetailsSid);
    }

    public static com.stpl.app.model.CustomViewDetails updateImpl(
        com.stpl.app.model.CustomViewDetails customViewDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(customViewDetails);
    }

    /**
    * Returns the custom view details with the primary key or throws a {@link com.stpl.app.NoSuchCustomViewDetailsException} if it could not be found.
    *
    * @param customViewDetailsSid the primary key of the custom view details
    * @return the custom view details
    * @throws com.stpl.app.NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CustomViewDetails findByPrimaryKey(
        int customViewDetailsSid)
        throws com.stpl.app.NoSuchCustomViewDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(customViewDetailsSid);
    }

    /**
    * Returns the custom view details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param customViewDetailsSid the primary key of the custom view details
    * @return the custom view details, or <code>null</code> if a custom view details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CustomViewDetails fetchByPrimaryKey(
        int customViewDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(customViewDetailsSid);
    }

    /**
    * Returns all the custom view detailses.
    *
    * @return the custom view detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CustomViewDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the custom view detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of custom view detailses
    * @param end the upper bound of the range of custom view detailses (not inclusive)
    * @return the range of custom view detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CustomViewDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the custom view detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of custom view detailses
    * @param end the upper bound of the range of custom view detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of custom view detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CustomViewDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the custom view detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of custom view detailses.
    *
    * @return the number of custom view detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CustomViewDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CustomViewDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CustomViewDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(CustomViewDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CustomViewDetailsPersistence persistence) {
    }
}
