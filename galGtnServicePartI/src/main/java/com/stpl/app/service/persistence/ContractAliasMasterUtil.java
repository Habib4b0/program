package com.stpl.app.service.persistence;

import com.stpl.app.model.ContractAliasMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contract alias master service. This utility wraps {@link ContractAliasMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ContractAliasMasterPersistence
 * @see ContractAliasMasterPersistenceImpl
 * @generated
 */
public class ContractAliasMasterUtil {
    private static ContractAliasMasterPersistence _persistence;

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
    public static void clearCache(ContractAliasMaster contractAliasMaster) {
        getPersistence().clearCache(contractAliasMaster);
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
    public static List<ContractAliasMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ContractAliasMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ContractAliasMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ContractAliasMaster update(
        ContractAliasMaster contractAliasMaster) throws SystemException {
        return getPersistence().update(contractAliasMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ContractAliasMaster update(
        ContractAliasMaster contractAliasMaster, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(contractAliasMaster, serviceContext);
    }

    /**
    * Returns all the contract alias masters where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @return the matching contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ContractAliasMaster> findByContractSystemId(
        int contractMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByContractSystemId(contractMasterSid);
    }

    /**
    * Returns a range of all the contract alias masters where contractMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractMasterSid the contract master sid
    * @param start the lower bound of the range of contract alias masters
    * @param end the upper bound of the range of contract alias masters (not inclusive)
    * @return the range of matching contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ContractAliasMaster> findByContractSystemId(
        int contractMasterSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractSystemId(contractMasterSid, start, end);
    }

    /**
    * Returns an ordered range of all the contract alias masters where contractMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractMasterSid the contract master sid
    * @param start the lower bound of the range of contract alias masters
    * @param end the upper bound of the range of contract alias masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ContractAliasMaster> findByContractSystemId(
        int contractMasterSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractSystemId(contractMasterSid, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contract alias master
    * @throws com.stpl.app.NoSuchContractAliasMasterException if a matching contract alias master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractAliasMaster findByContractSystemId_First(
        int contractMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchContractAliasMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractSystemId_First(contractMasterSid,
            orderByComparator);
    }

    /**
    * Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractAliasMaster fetchByContractSystemId_First(
        int contractMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContractSystemId_First(contractMasterSid,
            orderByComparator);
    }

    /**
    * Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contract alias master
    * @throws com.stpl.app.NoSuchContractAliasMasterException if a matching contract alias master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractAliasMaster findByContractSystemId_Last(
        int contractMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchContractAliasMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractSystemId_Last(contractMasterSid,
            orderByComparator);
    }

    /**
    * Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractAliasMaster fetchByContractSystemId_Last(
        int contractMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContractSystemId_Last(contractMasterSid,
            orderByComparator);
    }

    /**
    * Returns the contract alias masters before and after the current contract alias master in the ordered set where contractMasterSid = &#63;.
    *
    * @param contractAliasMasterSid the primary key of the current contract alias master
    * @param contractMasterSid the contract master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contract alias master
    * @throws com.stpl.app.NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractAliasMaster[] findByContractSystemId_PrevAndNext(
        int contractAliasMasterSid, int contractMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchContractAliasMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractSystemId_PrevAndNext(contractAliasMasterSid,
            contractMasterSid, orderByComparator);
    }

    /**
    * Removes all the contract alias masters where contractMasterSid = &#63; from the database.
    *
    * @param contractMasterSid the contract master sid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContractSystemId(int contractMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByContractSystemId(contractMasterSid);
    }

    /**
    * Returns the number of contract alias masters where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @return the number of matching contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByContractSystemId(int contractMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByContractSystemId(contractMasterSid);
    }

    /**
    * Caches the contract alias master in the entity cache if it is enabled.
    *
    * @param contractAliasMaster the contract alias master
    */
    public static void cacheResult(
        com.stpl.app.model.ContractAliasMaster contractAliasMaster) {
        getPersistence().cacheResult(contractAliasMaster);
    }

    /**
    * Caches the contract alias masters in the entity cache if it is enabled.
    *
    * @param contractAliasMasters the contract alias masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ContractAliasMaster> contractAliasMasters) {
        getPersistence().cacheResult(contractAliasMasters);
    }

    /**
    * Creates a new contract alias master with the primary key. Does not add the contract alias master to the database.
    *
    * @param contractAliasMasterSid the primary key for the new contract alias master
    * @return the new contract alias master
    */
    public static com.stpl.app.model.ContractAliasMaster create(
        int contractAliasMasterSid) {
        return getPersistence().create(contractAliasMasterSid);
    }

    /**
    * Removes the contract alias master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param contractAliasMasterSid the primary key of the contract alias master
    * @return the contract alias master that was removed
    * @throws com.stpl.app.NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractAliasMaster remove(
        int contractAliasMasterSid)
        throws com.stpl.app.NoSuchContractAliasMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(contractAliasMasterSid);
    }

    public static com.stpl.app.model.ContractAliasMaster updateImpl(
        com.stpl.app.model.ContractAliasMaster contractAliasMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contractAliasMaster);
    }

    /**
    * Returns the contract alias master with the primary key or throws a {@link com.stpl.app.NoSuchContractAliasMasterException} if it could not be found.
    *
    * @param contractAliasMasterSid the primary key of the contract alias master
    * @return the contract alias master
    * @throws com.stpl.app.NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractAliasMaster findByPrimaryKey(
        int contractAliasMasterSid)
        throws com.stpl.app.NoSuchContractAliasMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(contractAliasMasterSid);
    }

    /**
    * Returns the contract alias master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param contractAliasMasterSid the primary key of the contract alias master
    * @return the contract alias master, or <code>null</code> if a contract alias master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractAliasMaster fetchByPrimaryKey(
        int contractAliasMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(contractAliasMasterSid);
    }

    /**
    * Returns all the contract alias masters.
    *
    * @return the contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ContractAliasMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the contract alias masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contract alias masters
    * @param end the upper bound of the range of contract alias masters (not inclusive)
    * @return the range of contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ContractAliasMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the contract alias masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contract alias masters
    * @param end the upper bound of the range of contract alias masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ContractAliasMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the contract alias masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contract alias masters.
    *
    * @return the number of contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContractAliasMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContractAliasMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ContractAliasMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(ContractAliasMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ContractAliasMasterPersistence persistence) {
    }
}
