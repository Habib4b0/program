package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StAccClosureDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st acc closure details service. This utility wraps {@link StAccClosureDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAccClosureDetailsPersistence
 * @see StAccClosureDetailsPersistenceImpl
 * @generated
 */
public class StAccClosureDetailsUtil {
    private static StAccClosureDetailsPersistence _persistence;

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
    public static void clearCache(StAccClosureDetails stAccClosureDetails) {
        getPersistence().clearCache(stAccClosureDetails);
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
    public static List<StAccClosureDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StAccClosureDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StAccClosureDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StAccClosureDetails update(
        StAccClosureDetails stAccClosureDetails) throws SystemException {
        return getPersistence().update(stAccClosureDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StAccClosureDetails update(
        StAccClosureDetails stAccClosureDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(stAccClosureDetails, serviceContext);
    }

    /**
    * Caches the st acc closure details in the entity cache if it is enabled.
    *
    * @param stAccClosureDetails the st acc closure details
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.StAccClosureDetails stAccClosureDetails) {
        getPersistence().cacheResult(stAccClosureDetails);
    }

    /**
    * Caches the st acc closure detailses in the entity cache if it is enabled.
    *
    * @param stAccClosureDetailses the st acc closure detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.StAccClosureDetails> stAccClosureDetailses) {
        getPersistence().cacheResult(stAccClosureDetailses);
    }

    /**
    * Creates a new st acc closure details with the primary key. Does not add the st acc closure details to the database.
    *
    * @param accClosureMasterSid the primary key for the new st acc closure details
    * @return the new st acc closure details
    */
    public static com.stpl.app.parttwo.model.StAccClosureDetails create(
        int accClosureMasterSid) {
        return getPersistence().create(accClosureMasterSid);
    }

    /**
    * Removes the st acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureMasterSid the primary key of the st acc closure details
    * @return the st acc closure details that was removed
    * @throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAccClosureDetails remove(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(accClosureMasterSid);
    }

    public static com.stpl.app.parttwo.model.StAccClosureDetails updateImpl(
        com.stpl.app.parttwo.model.StAccClosureDetails stAccClosureDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stAccClosureDetails);
    }

    /**
    * Returns the st acc closure details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStAccClosureDetailsException} if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the st acc closure details
    * @return the st acc closure details
    * @throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAccClosureDetails findByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(accClosureMasterSid);
    }

    /**
    * Returns the st acc closure details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the st acc closure details
    * @return the st acc closure details, or <code>null</code> if a st acc closure details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAccClosureDetails fetchByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(accClosureMasterSid);
    }

    /**
    * Returns all the st acc closure detailses.
    *
    * @return the st acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StAccClosureDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st acc closure detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st acc closure detailses
    * @param end the upper bound of the range of st acc closure detailses (not inclusive)
    * @return the range of st acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StAccClosureDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st acc closure detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st acc closure detailses
    * @param end the upper bound of the range of st acc closure detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StAccClosureDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st acc closure detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st acc closure detailses.
    *
    * @return the number of st acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StAccClosureDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StAccClosureDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    StAccClosureDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(StAccClosureDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StAccClosureDetailsPersistence persistence) {
    }
}
