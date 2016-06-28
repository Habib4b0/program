package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AhTempDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ah temp details service. This utility wraps {@link AhTempDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AhTempDetailsPersistence
 * @see AhTempDetailsPersistenceImpl
 * @generated
 */
public class AhTempDetailsUtil {
    private static AhTempDetailsPersistence _persistence;

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
    public static void clearCache(AhTempDetails ahTempDetails) {
        getPersistence().clearCache(ahTempDetails);
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
    public static List<AhTempDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AhTempDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AhTempDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static AhTempDetails update(AhTempDetails ahTempDetails)
        throws SystemException {
        return getPersistence().update(ahTempDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static AhTempDetails update(AhTempDetails ahTempDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ahTempDetails, serviceContext);
    }

    /**
    * Caches the ah temp details in the entity cache if it is enabled.
    *
    * @param ahTempDetails the ah temp details
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.AhTempDetails ahTempDetails) {
        getPersistence().cacheResult(ahTempDetails);
    }

    /**
    * Caches the ah temp detailses in the entity cache if it is enabled.
    *
    * @param ahTempDetailses the ah temp detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AhTempDetails> ahTempDetailses) {
        getPersistence().cacheResult(ahTempDetailses);
    }

    /**
    * Creates a new ah temp details with the primary key. Does not add the ah temp details to the database.
    *
    * @param componentMasterSid the primary key for the new ah temp details
    * @return the new ah temp details
    */
    public static com.stpl.app.parttwo.model.AhTempDetails create(
        int componentMasterSid) {
        return getPersistence().create(componentMasterSid);
    }

    /**
    * Removes the ah temp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param componentMasterSid the primary key of the ah temp details
    * @return the ah temp details that was removed
    * @throws com.stpl.app.parttwo.NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AhTempDetails remove(
        int componentMasterSid)
        throws com.stpl.app.parttwo.NoSuchAhTempDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(componentMasterSid);
    }

    public static com.stpl.app.parttwo.model.AhTempDetails updateImpl(
        com.stpl.app.parttwo.model.AhTempDetails ahTempDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ahTempDetails);
    }

    /**
    * Returns the ah temp details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAhTempDetailsException} if it could not be found.
    *
    * @param componentMasterSid the primary key of the ah temp details
    * @return the ah temp details
    * @throws com.stpl.app.parttwo.NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AhTempDetails findByPrimaryKey(
        int componentMasterSid)
        throws com.stpl.app.parttwo.NoSuchAhTempDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(componentMasterSid);
    }

    /**
    * Returns the ah temp details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param componentMasterSid the primary key of the ah temp details
    * @return the ah temp details, or <code>null</code> if a ah temp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AhTempDetails fetchByPrimaryKey(
        int componentMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(componentMasterSid);
    }

    /**
    * Returns all the ah temp detailses.
    *
    * @return the ah temp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AhTempDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ah temp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ah temp detailses
    * @param end the upper bound of the range of ah temp detailses (not inclusive)
    * @return the range of ah temp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AhTempDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ah temp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ah temp detailses
    * @param end the upper bound of the range of ah temp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ah temp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AhTempDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ah temp detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ah temp detailses.
    *
    * @return the number of ah temp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AhTempDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AhTempDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    AhTempDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(AhTempDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(AhTempDetailsPersistence persistence) {
    }
}
