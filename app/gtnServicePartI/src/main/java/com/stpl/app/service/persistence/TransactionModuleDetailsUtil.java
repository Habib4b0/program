package com.stpl.app.service.persistence;

import com.stpl.app.model.TransactionModuleDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the transaction module details service. This utility wraps {@link TransactionModuleDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see TransactionModuleDetailsPersistence
 * @see TransactionModuleDetailsPersistenceImpl
 * @generated
 */
public class TransactionModuleDetailsUtil {
    private static TransactionModuleDetailsPersistence _persistence;

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
        TransactionModuleDetails transactionModuleDetails) {
        getPersistence().clearCache(transactionModuleDetails);
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
    public static List<TransactionModuleDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<TransactionModuleDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<TransactionModuleDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static TransactionModuleDetails update(
        TransactionModuleDetails transactionModuleDetails)
        throws SystemException {
        return getPersistence().update(transactionModuleDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static TransactionModuleDetails update(
        TransactionModuleDetails transactionModuleDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(transactionModuleDetails, serviceContext);
    }

    /**
    * Caches the transaction module details in the entity cache if it is enabled.
    *
    * @param transactionModuleDetails the transaction module details
    */
    public static void cacheResult(
        com.stpl.app.model.TransactionModuleDetails transactionModuleDetails) {
        getPersistence().cacheResult(transactionModuleDetails);
    }

    /**
    * Caches the transaction module detailses in the entity cache if it is enabled.
    *
    * @param transactionModuleDetailses the transaction module detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.TransactionModuleDetails> transactionModuleDetailses) {
        getPersistence().cacheResult(transactionModuleDetailses);
    }

    /**
    * Creates a new transaction module details with the primary key. Does not add the transaction module details to the database.
    *
    * @param transactionModuleDetailsSid the primary key for the new transaction module details
    * @return the new transaction module details
    */
    public static com.stpl.app.model.TransactionModuleDetails create(
        int transactionModuleDetailsSid) {
        return getPersistence().create(transactionModuleDetailsSid);
    }

    /**
    * Removes the transaction module details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param transactionModuleDetailsSid the primary key of the transaction module details
    * @return the transaction module details that was removed
    * @throws com.stpl.app.NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.TransactionModuleDetails remove(
        int transactionModuleDetailsSid)
        throws com.stpl.app.NoSuchTransactionModuleDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(transactionModuleDetailsSid);
    }

    public static com.stpl.app.model.TransactionModuleDetails updateImpl(
        com.stpl.app.model.TransactionModuleDetails transactionModuleDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(transactionModuleDetails);
    }

    /**
    * Returns the transaction module details with the primary key or throws a {@link com.stpl.app.NoSuchTransactionModuleDetailsException} if it could not be found.
    *
    * @param transactionModuleDetailsSid the primary key of the transaction module details
    * @return the transaction module details
    * @throws com.stpl.app.NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.TransactionModuleDetails findByPrimaryKey(
        int transactionModuleDetailsSid)
        throws com.stpl.app.NoSuchTransactionModuleDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(transactionModuleDetailsSid);
    }

    /**
    * Returns the transaction module details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param transactionModuleDetailsSid the primary key of the transaction module details
    * @return the transaction module details, or <code>null</code> if a transaction module details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.TransactionModuleDetails fetchByPrimaryKey(
        int transactionModuleDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(transactionModuleDetailsSid);
    }

    /**
    * Returns all the transaction module detailses.
    *
    * @return the transaction module detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.TransactionModuleDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the transaction module detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of transaction module detailses
    * @param end the upper bound of the range of transaction module detailses (not inclusive)
    * @return the range of transaction module detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.TransactionModuleDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the transaction module detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of transaction module detailses
    * @param end the upper bound of the range of transaction module detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of transaction module detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.TransactionModuleDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the transaction module detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of transaction module detailses.
    *
    * @return the number of transaction module detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static TransactionModuleDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (TransactionModuleDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    TransactionModuleDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(TransactionModuleDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(TransactionModuleDetailsPersistence persistence) {
    }
}
