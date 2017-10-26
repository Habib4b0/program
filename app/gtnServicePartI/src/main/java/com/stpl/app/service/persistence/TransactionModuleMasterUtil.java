package com.stpl.app.service.persistence;

import com.stpl.app.model.TransactionModuleMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the transaction module master service. This utility wraps {@link TransactionModuleMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see TransactionModuleMasterPersistence
 * @see TransactionModuleMasterPersistenceImpl
 * @generated
 */
public class TransactionModuleMasterUtil {
    private static TransactionModuleMasterPersistence _persistence;

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
    public static void clearCache(
        TransactionModuleMaster transactionModuleMaster) {
        getPersistence().clearCache(transactionModuleMaster);
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
    public static List<TransactionModuleMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<TransactionModuleMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<TransactionModuleMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static TransactionModuleMaster update(
        TransactionModuleMaster transactionModuleMaster)
        throws SystemException {
        return getPersistence().update(transactionModuleMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static TransactionModuleMaster update(
        TransactionModuleMaster transactionModuleMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(transactionModuleMaster, serviceContext);
    }

    /**
    * Caches the transaction module master in the entity cache if it is enabled.
    *
    * @param transactionModuleMaster the transaction module master
    */
    public static void cacheResult(
        com.stpl.app.model.TransactionModuleMaster transactionModuleMaster) {
        getPersistence().cacheResult(transactionModuleMaster);
    }

    /**
    * Caches the transaction module masters in the entity cache if it is enabled.
    *
    * @param transactionModuleMasters the transaction module masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.TransactionModuleMaster> transactionModuleMasters) {
        getPersistence().cacheResult(transactionModuleMasters);
    }

    /**
    * Creates a new transaction module master with the primary key. Does not add the transaction module master to the database.
    *
    * @param transactionModuleMasterSid the primary key for the new transaction module master
    * @return the new transaction module master
    */
    public static com.stpl.app.model.TransactionModuleMaster create(
        int transactionModuleMasterSid) {
        return getPersistence().create(transactionModuleMasterSid);
    }

    /**
    * Removes the transaction module master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param transactionModuleMasterSid the primary key of the transaction module master
    * @return the transaction module master that was removed
    * @throws com.stpl.app.NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.TransactionModuleMaster remove(
        int transactionModuleMasterSid)
        throws com.stpl.app.NoSuchTransactionModuleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(transactionModuleMasterSid);
    }

    public static com.stpl.app.model.TransactionModuleMaster updateImpl(
        com.stpl.app.model.TransactionModuleMaster transactionModuleMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(transactionModuleMaster);
    }

    /**
    * Returns the transaction module master with the primary key or throws a {@link com.stpl.app.NoSuchTransactionModuleMasterException} if it could not be found.
    *
    * @param transactionModuleMasterSid the primary key of the transaction module master
    * @return the transaction module master
    * @throws com.stpl.app.NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.TransactionModuleMaster findByPrimaryKey(
        int transactionModuleMasterSid)
        throws com.stpl.app.NoSuchTransactionModuleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(transactionModuleMasterSid);
    }

    /**
    * Returns the transaction module master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param transactionModuleMasterSid the primary key of the transaction module master
    * @return the transaction module master, or <code>null</code> if a transaction module master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.TransactionModuleMaster fetchByPrimaryKey(
        int transactionModuleMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(transactionModuleMasterSid);
    }

    /**
    * Returns all the transaction module masters.
    *
    * @return the transaction module masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.TransactionModuleMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the transaction module masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of transaction module masters
    * @param end the upper bound of the range of transaction module masters (not inclusive)
    * @return the range of transaction module masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.TransactionModuleMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the transaction module masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of transaction module masters
    * @param end the upper bound of the range of transaction module masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of transaction module masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.TransactionModuleMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the transaction module masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of transaction module masters.
    *
    * @return the number of transaction module masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static TransactionModuleMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (TransactionModuleMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    TransactionModuleMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(TransactionModuleMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(TransactionModuleMasterPersistence persistence) {
    }
}
