package com.stpl.app.service.persistence;

import com.stpl.app.model.ContractMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contract master service. This utility wraps {@link ContractMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ContractMasterPersistence
 * @see ContractMasterPersistenceImpl
 * @generated
 */
public class ContractMasterUtil {
    private static ContractMasterPersistence _persistence;

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
    public static void clearCache(ContractMaster contractMaster) {
        getPersistence().clearCache(contractMaster);
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
    public static List<ContractMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ContractMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ContractMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ContractMaster update(ContractMaster contractMaster)
        throws SystemException {
        return getPersistence().update(contractMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ContractMaster update(ContractMaster contractMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(contractMaster, serviceContext);
    }

    /**
    * Caches the contract master in the entity cache if it is enabled.
    *
    * @param contractMaster the contract master
    */
    public static void cacheResult(
        com.stpl.app.model.ContractMaster contractMaster) {
        getPersistence().cacheResult(contractMaster);
    }

    /**
    * Caches the contract masters in the entity cache if it is enabled.
    *
    * @param contractMasters the contract masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ContractMaster> contractMasters) {
        getPersistence().cacheResult(contractMasters);
    }

    /**
    * Creates a new contract master with the primary key. Does not add the contract master to the database.
    *
    * @param contractMasterSid the primary key for the new contract master
    * @return the new contract master
    */
    public static com.stpl.app.model.ContractMaster create(
        int contractMasterSid) {
        return getPersistence().create(contractMasterSid);
    }

    /**
    * Removes the contract master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param contractMasterSid the primary key of the contract master
    * @return the contract master that was removed
    * @throws com.stpl.app.NoSuchContractMasterException if a contract master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractMaster remove(
        int contractMasterSid)
        throws com.stpl.app.NoSuchContractMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(contractMasterSid);
    }

    public static com.stpl.app.model.ContractMaster updateImpl(
        com.stpl.app.model.ContractMaster contractMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contractMaster);
    }

    /**
    * Returns the contract master with the primary key or throws a {@link com.stpl.app.NoSuchContractMasterException} if it could not be found.
    *
    * @param contractMasterSid the primary key of the contract master
    * @return the contract master
    * @throws com.stpl.app.NoSuchContractMasterException if a contract master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractMaster findByPrimaryKey(
        int contractMasterSid)
        throws com.stpl.app.NoSuchContractMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(contractMasterSid);
    }

    /**
    * Returns the contract master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param contractMasterSid the primary key of the contract master
    * @return the contract master, or <code>null</code> if a contract master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractMaster fetchByPrimaryKey(
        int contractMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(contractMasterSid);
    }

    /**
    * Returns all the contract masters.
    *
    * @return the contract masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ContractMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the contract masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contract masters
    * @param end the upper bound of the range of contract masters (not inclusive)
    * @return the range of contract masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ContractMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the contract masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contract masters
    * @param end the upper bound of the range of contract masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contract masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ContractMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the contract masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contract masters.
    *
    * @return the number of contract masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContractMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContractMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ContractMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(ContractMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ContractMasterPersistence persistence) {
    }
}
