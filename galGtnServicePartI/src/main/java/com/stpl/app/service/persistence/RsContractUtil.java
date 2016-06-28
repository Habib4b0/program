package com.stpl.app.service.persistence;

import com.stpl.app.model.RsContract;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the rs contract service. This utility wraps {@link RsContractPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsContractPersistence
 * @see RsContractPersistenceImpl
 * @generated
 */
public class RsContractUtil {
    private static RsContractPersistence _persistence;

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
    public static void clearCache(RsContract rsContract) {
        getPersistence().clearCache(rsContract);
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
    public static List<RsContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RsContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RsContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static RsContract update(RsContract rsContract)
        throws SystemException {
        return getPersistence().update(rsContract);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static RsContract update(RsContract rsContract,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(rsContract, serviceContext);
    }

    /**
    * Caches the rs contract in the entity cache if it is enabled.
    *
    * @param rsContract the rs contract
    */
    public static void cacheResult(com.stpl.app.model.RsContract rsContract) {
        getPersistence().cacheResult(rsContract);
    }

    /**
    * Caches the rs contracts in the entity cache if it is enabled.
    *
    * @param rsContracts the rs contracts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.RsContract> rsContracts) {
        getPersistence().cacheResult(rsContracts);
    }

    /**
    * Creates a new rs contract with the primary key. Does not add the rs contract to the database.
    *
    * @param rsContractSid the primary key for the new rs contract
    * @return the new rs contract
    */
    public static com.stpl.app.model.RsContract create(int rsContractSid) {
        return getPersistence().create(rsContractSid);
    }

    /**
    * Removes the rs contract with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsContractSid the primary key of the rs contract
    * @return the rs contract that was removed
    * @throws com.stpl.app.NoSuchRsContractException if a rs contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContract remove(int rsContractSid)
        throws com.stpl.app.NoSuchRsContractException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(rsContractSid);
    }

    public static com.stpl.app.model.RsContract updateImpl(
        com.stpl.app.model.RsContract rsContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(rsContract);
    }

    /**
    * Returns the rs contract with the primary key or throws a {@link com.stpl.app.NoSuchRsContractException} if it could not be found.
    *
    * @param rsContractSid the primary key of the rs contract
    * @return the rs contract
    * @throws com.stpl.app.NoSuchRsContractException if a rs contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContract findByPrimaryKey(
        int rsContractSid)
        throws com.stpl.app.NoSuchRsContractException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(rsContractSid);
    }

    /**
    * Returns the rs contract with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rsContractSid the primary key of the rs contract
    * @return the rs contract, or <code>null</code> if a rs contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContract fetchByPrimaryKey(
        int rsContractSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(rsContractSid);
    }

    /**
    * Returns all the rs contracts.
    *
    * @return the rs contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContract> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the rs contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs contracts
    * @param end the upper bound of the range of rs contracts (not inclusive)
    * @return the range of rs contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContract> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the rs contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs contracts
    * @param end the upper bound of the range of rs contracts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rs contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContract> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the rs contracts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of rs contracts.
    *
    * @return the number of rs contracts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RsContractPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RsContractPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RsContractPersistence.class.getName());

            ReferenceRegistry.registerReference(RsContractUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(RsContractPersistence persistence) {
    }
}
