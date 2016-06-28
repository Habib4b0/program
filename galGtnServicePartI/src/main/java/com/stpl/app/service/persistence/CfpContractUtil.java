package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpContract;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cfp contract service. This utility wraps {@link CfpContractPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpContractPersistence
 * @see CfpContractPersistenceImpl
 * @generated
 */
public class CfpContractUtil {
    private static CfpContractPersistence _persistence;

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
    public static void clearCache(CfpContract cfpContract) {
        getPersistence().clearCache(cfpContract);
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
    public static List<CfpContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CfpContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CfpContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CfpContract update(CfpContract cfpContract)
        throws SystemException {
        return getPersistence().update(cfpContract);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CfpContract update(CfpContract cfpContract,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(cfpContract, serviceContext);
    }

    /**
    * Caches the cfp contract in the entity cache if it is enabled.
    *
    * @param cfpContract the cfp contract
    */
    public static void cacheResult(com.stpl.app.model.CfpContract cfpContract) {
        getPersistence().cacheResult(cfpContract);
    }

    /**
    * Caches the cfp contracts in the entity cache if it is enabled.
    *
    * @param cfpContracts the cfp contracts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CfpContract> cfpContracts) {
        getPersistence().cacheResult(cfpContracts);
    }

    /**
    * Creates a new cfp contract with the primary key. Does not add the cfp contract to the database.
    *
    * @param cfpContractSid the primary key for the new cfp contract
    * @return the new cfp contract
    */
    public static com.stpl.app.model.CfpContract create(int cfpContractSid) {
        return getPersistence().create(cfpContractSid);
    }

    /**
    * Removes the cfp contract with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpContractSid the primary key of the cfp contract
    * @return the cfp contract that was removed
    * @throws com.stpl.app.NoSuchCfpContractException if a cfp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpContract remove(int cfpContractSid)
        throws com.stpl.app.NoSuchCfpContractException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(cfpContractSid);
    }

    public static com.stpl.app.model.CfpContract updateImpl(
        com.stpl.app.model.CfpContract cfpContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(cfpContract);
    }

    /**
    * Returns the cfp contract with the primary key or throws a {@link com.stpl.app.NoSuchCfpContractException} if it could not be found.
    *
    * @param cfpContractSid the primary key of the cfp contract
    * @return the cfp contract
    * @throws com.stpl.app.NoSuchCfpContractException if a cfp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpContract findByPrimaryKey(
        int cfpContractSid)
        throws com.stpl.app.NoSuchCfpContractException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(cfpContractSid);
    }

    /**
    * Returns the cfp contract with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cfpContractSid the primary key of the cfp contract
    * @return the cfp contract, or <code>null</code> if a cfp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpContract fetchByPrimaryKey(
        int cfpContractSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(cfpContractSid);
    }

    /**
    * Returns all the cfp contracts.
    *
    * @return the cfp contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpContract> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the cfp contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp contracts
    * @param end the upper bound of the range of cfp contracts (not inclusive)
    * @return the range of cfp contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpContract> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the cfp contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp contracts
    * @param end the upper bound of the range of cfp contracts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cfp contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpContract> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the cfp contracts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of cfp contracts.
    *
    * @return the number of cfp contracts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CfpContractPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CfpContractPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CfpContractPersistence.class.getName());

            ReferenceRegistry.registerReference(CfpContractUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CfpContractPersistence persistence) {
    }
}
