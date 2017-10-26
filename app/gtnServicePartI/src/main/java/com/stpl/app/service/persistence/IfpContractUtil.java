package com.stpl.app.service.persistence;

import com.stpl.app.model.IfpContract;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ifp contract service. This utility wraps {@link IfpContractPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpContractPersistence
 * @see IfpContractPersistenceImpl
 * @generated
 */
public class IfpContractUtil {
    private static IfpContractPersistence _persistence;

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
    public static void clearCache(IfpContract ifpContract) {
        getPersistence().clearCache(ifpContract);
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
    public static List<IfpContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IfpContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IfpContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IfpContract update(IfpContract ifpContract)
        throws SystemException {
        return getPersistence().update(ifpContract);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IfpContract update(IfpContract ifpContract,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ifpContract, serviceContext);
    }

    /**
    * Caches the ifp contract in the entity cache if it is enabled.
    *
    * @param ifpContract the ifp contract
    */
    public static void cacheResult(com.stpl.app.model.IfpContract ifpContract) {
        getPersistence().cacheResult(ifpContract);
    }

    /**
    * Caches the ifp contracts in the entity cache if it is enabled.
    *
    * @param ifpContracts the ifp contracts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IfpContract> ifpContracts) {
        getPersistence().cacheResult(ifpContracts);
    }

    /**
    * Creates a new ifp contract with the primary key. Does not add the ifp contract to the database.
    *
    * @param ifpContractSid the primary key for the new ifp contract
    * @return the new ifp contract
    */
    public static com.stpl.app.model.IfpContract create(int ifpContractSid) {
        return getPersistence().create(ifpContractSid);
    }

    /**
    * Removes the ifp contract with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContractSid the primary key of the ifp contract
    * @return the ifp contract that was removed
    * @throws com.stpl.app.NoSuchIfpContractException if a ifp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContract remove(int ifpContractSid)
        throws com.stpl.app.NoSuchIfpContractException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ifpContractSid);
    }

    public static com.stpl.app.model.IfpContract updateImpl(
        com.stpl.app.model.IfpContract ifpContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ifpContract);
    }

    /**
    * Returns the ifp contract with the primary key or throws a {@link com.stpl.app.NoSuchIfpContractException} if it could not be found.
    *
    * @param ifpContractSid the primary key of the ifp contract
    * @return the ifp contract
    * @throws com.stpl.app.NoSuchIfpContractException if a ifp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContract findByPrimaryKey(
        int ifpContractSid)
        throws com.stpl.app.NoSuchIfpContractException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ifpContractSid);
    }

    /**
    * Returns the ifp contract with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ifpContractSid the primary key of the ifp contract
    * @return the ifp contract, or <code>null</code> if a ifp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContract fetchByPrimaryKey(
        int ifpContractSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ifpContractSid);
    }

    /**
    * Returns all the ifp contracts.
    *
    * @return the ifp contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpContract> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ifp contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp contracts
    * @param end the upper bound of the range of ifp contracts (not inclusive)
    * @return the range of ifp contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpContract> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ifp contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp contracts
    * @param end the upper bound of the range of ifp contracts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ifp contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpContract> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ifp contracts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ifp contracts.
    *
    * @return the number of ifp contracts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IfpContractPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IfpContractPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IfpContractPersistence.class.getName());

            ReferenceRegistry.registerReference(IfpContractUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IfpContractPersistence persistence) {
    }
}
