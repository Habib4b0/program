package com.stpl.app.service.persistence;

import com.stpl.app.model.PsContract;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ps contract service. This utility wraps {@link PsContractPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsContractPersistence
 * @see PsContractPersistenceImpl
 * @generated
 */
public class PsContractUtil {
    private static PsContractPersistence _persistence;

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
    public static void clearCache(PsContract psContract) {
        getPersistence().clearCache(psContract);
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
    public static List<PsContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PsContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PsContract> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static PsContract update(PsContract psContract)
        throws SystemException {
        return getPersistence().update(psContract);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static PsContract update(PsContract psContract,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(psContract, serviceContext);
    }

    /**
    * Caches the ps contract in the entity cache if it is enabled.
    *
    * @param psContract the ps contract
    */
    public static void cacheResult(com.stpl.app.model.PsContract psContract) {
        getPersistence().cacheResult(psContract);
    }

    /**
    * Caches the ps contracts in the entity cache if it is enabled.
    *
    * @param psContracts the ps contracts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.PsContract> psContracts) {
        getPersistence().cacheResult(psContracts);
    }

    /**
    * Creates a new ps contract with the primary key. Does not add the ps contract to the database.
    *
    * @param psContractSid the primary key for the new ps contract
    * @return the new ps contract
    */
    public static com.stpl.app.model.PsContract create(int psContractSid) {
        return getPersistence().create(psContractSid);
    }

    /**
    * Removes the ps contract with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psContractSid the primary key of the ps contract
    * @return the ps contract that was removed
    * @throws com.stpl.app.NoSuchPsContractException if a ps contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsContract remove(int psContractSid)
        throws com.stpl.app.NoSuchPsContractException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(psContractSid);
    }

    public static com.stpl.app.model.PsContract updateImpl(
        com.stpl.app.model.PsContract psContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(psContract);
    }

    /**
    * Returns the ps contract with the primary key or throws a {@link com.stpl.app.NoSuchPsContractException} if it could not be found.
    *
    * @param psContractSid the primary key of the ps contract
    * @return the ps contract
    * @throws com.stpl.app.NoSuchPsContractException if a ps contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsContract findByPrimaryKey(
        int psContractSid)
        throws com.stpl.app.NoSuchPsContractException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(psContractSid);
    }

    /**
    * Returns the ps contract with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param psContractSid the primary key of the ps contract
    * @return the ps contract, or <code>null</code> if a ps contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsContract fetchByPrimaryKey(
        int psContractSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(psContractSid);
    }

    /**
    * Returns all the ps contracts.
    *
    * @return the ps contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsContract> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ps contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps contracts
    * @param end the upper bound of the range of ps contracts (not inclusive)
    * @return the range of ps contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsContract> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ps contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps contracts
    * @param end the upper bound of the range of ps contracts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ps contracts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsContract> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ps contracts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ps contracts.
    *
    * @return the number of ps contracts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PsContractPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PsContractPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    PsContractPersistence.class.getName());

            ReferenceRegistry.registerReference(PsContractUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PsContractPersistence persistence) {
    }
}
