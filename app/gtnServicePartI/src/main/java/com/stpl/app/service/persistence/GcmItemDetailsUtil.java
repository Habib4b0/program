package com.stpl.app.service.persistence;

import com.stpl.app.model.GcmItemDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the gcm item details service. This utility wraps {@link GcmItemDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmItemDetailsPersistence
 * @see GcmItemDetailsPersistenceImpl
 * @generated
 */
public class GcmItemDetailsUtil {
    private static GcmItemDetailsPersistence _persistence;

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
    public static void clearCache(GcmItemDetails gcmItemDetails) {
        getPersistence().clearCache(gcmItemDetails);
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
    public static List<GcmItemDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<GcmItemDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<GcmItemDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static GcmItemDetails update(GcmItemDetails gcmItemDetails)
        throws SystemException {
        return getPersistence().update(gcmItemDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static GcmItemDetails update(GcmItemDetails gcmItemDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(gcmItemDetails, serviceContext);
    }

    /**
    * Caches the gcm item details in the entity cache if it is enabled.
    *
    * @param gcmItemDetails the gcm item details
    */
    public static void cacheResult(
        com.stpl.app.model.GcmItemDetails gcmItemDetails) {
        getPersistence().cacheResult(gcmItemDetails);
    }

    /**
    * Caches the gcm item detailses in the entity cache if it is enabled.
    *
    * @param gcmItemDetailses the gcm item detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.GcmItemDetails> gcmItemDetailses) {
        getPersistence().cacheResult(gcmItemDetailses);
    }

    /**
    * Creates a new gcm item details with the primary key. Does not add the gcm item details to the database.
    *
    * @param gcmItemDetailsSid the primary key for the new gcm item details
    * @return the new gcm item details
    */
    public static com.stpl.app.model.GcmItemDetails create(
        int gcmItemDetailsSid) {
        return getPersistence().create(gcmItemDetailsSid);
    }

    /**
    * Removes the gcm item details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param gcmItemDetailsSid the primary key of the gcm item details
    * @return the gcm item details that was removed
    * @throws com.stpl.app.NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GcmItemDetails remove(
        int gcmItemDetailsSid)
        throws com.stpl.app.NoSuchGcmItemDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(gcmItemDetailsSid);
    }

    public static com.stpl.app.model.GcmItemDetails updateImpl(
        com.stpl.app.model.GcmItemDetails gcmItemDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(gcmItemDetails);
    }

    /**
    * Returns the gcm item details with the primary key or throws a {@link com.stpl.app.NoSuchGcmItemDetailsException} if it could not be found.
    *
    * @param gcmItemDetailsSid the primary key of the gcm item details
    * @return the gcm item details
    * @throws com.stpl.app.NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GcmItemDetails findByPrimaryKey(
        int gcmItemDetailsSid)
        throws com.stpl.app.NoSuchGcmItemDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(gcmItemDetailsSid);
    }

    /**
    * Returns the gcm item details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param gcmItemDetailsSid the primary key of the gcm item details
    * @return the gcm item details, or <code>null</code> if a gcm item details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GcmItemDetails fetchByPrimaryKey(
        int gcmItemDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(gcmItemDetailsSid);
    }

    /**
    * Returns all the gcm item detailses.
    *
    * @return the gcm item detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GcmItemDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the gcm item detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm item detailses
    * @param end the upper bound of the range of gcm item detailses (not inclusive)
    * @return the range of gcm item detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GcmItemDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the gcm item detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm item detailses
    * @param end the upper bound of the range of gcm item detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of gcm item detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GcmItemDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the gcm item detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of gcm item detailses.
    *
    * @return the number of gcm item detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static GcmItemDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (GcmItemDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    GcmItemDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(GcmItemDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(GcmItemDetailsPersistence persistence) {
    }
}
