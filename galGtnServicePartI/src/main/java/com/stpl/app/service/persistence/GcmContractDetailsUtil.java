package com.stpl.app.service.persistence;

import com.stpl.app.model.GcmContractDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the gcm contract details service. This utility wraps {@link GcmContractDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmContractDetailsPersistence
 * @see GcmContractDetailsPersistenceImpl
 * @generated
 */
public class GcmContractDetailsUtil {
    private static GcmContractDetailsPersistence _persistence;

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
    public static void clearCache(GcmContractDetails gcmContractDetails) {
        getPersistence().clearCache(gcmContractDetails);
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
    public static List<GcmContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<GcmContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<GcmContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static GcmContractDetails update(
        GcmContractDetails gcmContractDetails) throws SystemException {
        return getPersistence().update(gcmContractDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static GcmContractDetails update(
        GcmContractDetails gcmContractDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(gcmContractDetails, serviceContext);
    }

    /**
    * Caches the gcm contract details in the entity cache if it is enabled.
    *
    * @param gcmContractDetails the gcm contract details
    */
    public static void cacheResult(
        com.stpl.app.model.GcmContractDetails gcmContractDetails) {
        getPersistence().cacheResult(gcmContractDetails);
    }

    /**
    * Caches the gcm contract detailses in the entity cache if it is enabled.
    *
    * @param gcmContractDetailses the gcm contract detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.GcmContractDetails> gcmContractDetailses) {
        getPersistence().cacheResult(gcmContractDetailses);
    }

    /**
    * Creates a new gcm contract details with the primary key. Does not add the gcm contract details to the database.
    *
    * @param gcmContractDetailsSid the primary key for the new gcm contract details
    * @return the new gcm contract details
    */
    public static com.stpl.app.model.GcmContractDetails create(
        int gcmContractDetailsSid) {
        return getPersistence().create(gcmContractDetailsSid);
    }

    /**
    * Removes the gcm contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param gcmContractDetailsSid the primary key of the gcm contract details
    * @return the gcm contract details that was removed
    * @throws com.stpl.app.NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GcmContractDetails remove(
        int gcmContractDetailsSid)
        throws com.stpl.app.NoSuchGcmContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(gcmContractDetailsSid);
    }

    public static com.stpl.app.model.GcmContractDetails updateImpl(
        com.stpl.app.model.GcmContractDetails gcmContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(gcmContractDetails);
    }

    /**
    * Returns the gcm contract details with the primary key or throws a {@link com.stpl.app.NoSuchGcmContractDetailsException} if it could not be found.
    *
    * @param gcmContractDetailsSid the primary key of the gcm contract details
    * @return the gcm contract details
    * @throws com.stpl.app.NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GcmContractDetails findByPrimaryKey(
        int gcmContractDetailsSid)
        throws com.stpl.app.NoSuchGcmContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(gcmContractDetailsSid);
    }

    /**
    * Returns the gcm contract details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param gcmContractDetailsSid the primary key of the gcm contract details
    * @return the gcm contract details, or <code>null</code> if a gcm contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GcmContractDetails fetchByPrimaryKey(
        int gcmContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(gcmContractDetailsSid);
    }

    /**
    * Returns all the gcm contract detailses.
    *
    * @return the gcm contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GcmContractDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the gcm contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm contract detailses
    * @param end the upper bound of the range of gcm contract detailses (not inclusive)
    * @return the range of gcm contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GcmContractDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the gcm contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm contract detailses
    * @param end the upper bound of the range of gcm contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of gcm contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GcmContractDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the gcm contract detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of gcm contract detailses.
    *
    * @return the number of gcm contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static GcmContractDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (GcmContractDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    GcmContractDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(GcmContractDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(GcmContractDetailsPersistence persistence) {
    }
}
