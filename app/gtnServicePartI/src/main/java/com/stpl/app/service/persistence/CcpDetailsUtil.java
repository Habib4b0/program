package com.stpl.app.service.persistence;

import com.stpl.app.model.CcpDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ccp details service. This utility wraps {@link CcpDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CcpDetailsPersistence
 * @see CcpDetailsPersistenceImpl
 * @generated
 */
public class CcpDetailsUtil {
    private static CcpDetailsPersistence _persistence;

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
    public static void clearCache(CcpDetails ccpDetails) {
        getPersistence().clearCache(ccpDetails);
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
    public static List<CcpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CcpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CcpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CcpDetails update(CcpDetails ccpDetails)
        throws SystemException {
        return getPersistence().update(ccpDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CcpDetails update(CcpDetails ccpDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ccpDetails, serviceContext);
    }

    /**
    * Caches the ccp details in the entity cache if it is enabled.
    *
    * @param ccpDetails the ccp details
    */
    public static void cacheResult(com.stpl.app.model.CcpDetails ccpDetails) {
        getPersistence().cacheResult(ccpDetails);
    }

    /**
    * Caches the ccp detailses in the entity cache if it is enabled.
    *
    * @param ccpDetailses the ccp detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CcpDetails> ccpDetailses) {
        getPersistence().cacheResult(ccpDetailses);
    }

    /**
    * Creates a new ccp details with the primary key. Does not add the ccp details to the database.
    *
    * @param ccpDetailsSid the primary key for the new ccp details
    * @return the new ccp details
    */
    public static com.stpl.app.model.CcpDetails create(int ccpDetailsSid) {
        return getPersistence().create(ccpDetailsSid);
    }

    /**
    * Removes the ccp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ccpDetailsSid the primary key of the ccp details
    * @return the ccp details that was removed
    * @throws com.stpl.app.NoSuchCcpDetailsException if a ccp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CcpDetails remove(int ccpDetailsSid)
        throws com.stpl.app.NoSuchCcpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ccpDetailsSid);
    }

    public static com.stpl.app.model.CcpDetails updateImpl(
        com.stpl.app.model.CcpDetails ccpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ccpDetails);
    }

    /**
    * Returns the ccp details with the primary key or throws a {@link com.stpl.app.NoSuchCcpDetailsException} if it could not be found.
    *
    * @param ccpDetailsSid the primary key of the ccp details
    * @return the ccp details
    * @throws com.stpl.app.NoSuchCcpDetailsException if a ccp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CcpDetails findByPrimaryKey(
        int ccpDetailsSid)
        throws com.stpl.app.NoSuchCcpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ccpDetailsSid);
    }

    /**
    * Returns the ccp details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ccpDetailsSid the primary key of the ccp details
    * @return the ccp details, or <code>null</code> if a ccp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CcpDetails fetchByPrimaryKey(
        int ccpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ccpDetailsSid);
    }

    /**
    * Returns all the ccp detailses.
    *
    * @return the ccp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CcpDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ccp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ccp detailses
    * @param end the upper bound of the range of ccp detailses (not inclusive)
    * @return the range of ccp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CcpDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ccp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ccp detailses
    * @param end the upper bound of the range of ccp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ccp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CcpDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ccp detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ccp detailses.
    *
    * @return the number of ccp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CcpDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CcpDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CcpDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(CcpDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CcpDetailsPersistence persistence) {
    }
}
