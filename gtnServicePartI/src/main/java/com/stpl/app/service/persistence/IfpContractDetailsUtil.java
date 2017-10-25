package com.stpl.app.service.persistence;

import com.stpl.app.model.IfpContractDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ifp contract details service. This utility wraps {@link IfpContractDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpContractDetailsPersistence
 * @see IfpContractDetailsPersistenceImpl
 * @generated
 */
public class IfpContractDetailsUtil {
    private static IfpContractDetailsPersistence _persistence;

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
    public static void clearCache(IfpContractDetails ifpContractDetails) {
        getPersistence().clearCache(ifpContractDetails);
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
    public static List<IfpContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IfpContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IfpContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IfpContractDetails update(
        IfpContractDetails ifpContractDetails) throws SystemException {
        return getPersistence().update(ifpContractDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IfpContractDetails update(
        IfpContractDetails ifpContractDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ifpContractDetails, serviceContext);
    }

    /**
    * Returns all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @return the matching ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpContractDetails> findByIFPDetails(
        int ifpContractSid, int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIFPDetails(ifpContractSid, itemMasterSid);
    }

    /**
    * Returns a range of all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param start the lower bound of the range of ifp contract detailses
    * @param end the upper bound of the range of ifp contract detailses (not inclusive)
    * @return the range of matching ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpContractDetails> findByIFPDetails(
        int ifpContractSid, int itemMasterSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIFPDetails(ifpContractSid, itemMasterSid, start, end);
    }

    /**
    * Returns an ordered range of all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param start the lower bound of the range of ifp contract detailses
    * @param end the upper bound of the range of ifp contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpContractDetails> findByIFPDetails(
        int ifpContractSid, int itemMasterSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIFPDetails(ifpContractSid, itemMasterSid, start, end,
            orderByComparator);
    }

    /**
    * Returns the first ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp contract details
    * @throws com.stpl.app.NoSuchIfpContractDetailsException if a matching ifp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails findByIFPDetails_First(
        int ifpContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIFPDetails_First(ifpContractSid, itemMasterSid,
            orderByComparator);
    }

    /**
    * Returns the first ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp contract details, or <code>null</code> if a matching ifp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails fetchByIFPDetails_First(
        int ifpContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByIFPDetails_First(ifpContractSid, itemMasterSid,
            orderByComparator);
    }

    /**
    * Returns the last ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp contract details
    * @throws com.stpl.app.NoSuchIfpContractDetailsException if a matching ifp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails findByIFPDetails_Last(
        int ifpContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIFPDetails_Last(ifpContractSid, itemMasterSid,
            orderByComparator);
    }

    /**
    * Returns the last ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp contract details, or <code>null</code> if a matching ifp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails fetchByIFPDetails_Last(
        int ifpContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByIFPDetails_Last(ifpContractSid, itemMasterSid,
            orderByComparator);
    }

    /**
    * Returns the ifp contract detailses before and after the current ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractDetailsSid the primary key of the current ifp contract details
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ifp contract details
    * @throws com.stpl.app.NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails[] findByIFPDetails_PrevAndNext(
        int ifpContractDetailsSid, int ifpContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIFPDetails_PrevAndNext(ifpContractDetailsSid,
            ifpContractSid, itemMasterSid, orderByComparator);
    }

    /**
    * Removes all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63; from the database.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByIFPDetails(int ifpContractSid, int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByIFPDetails(ifpContractSid, itemMasterSid);
    }

    /**
    * Returns the number of ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @return the number of matching ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countByIFPDetails(int ifpContractSid, int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByIFPDetails(ifpContractSid, itemMasterSid);
    }

    /**
    * Caches the ifp contract details in the entity cache if it is enabled.
    *
    * @param ifpContractDetails the ifp contract details
    */
    public static void cacheResult(
        com.stpl.app.model.IfpContractDetails ifpContractDetails) {
        getPersistence().cacheResult(ifpContractDetails);
    }

    /**
    * Caches the ifp contract detailses in the entity cache if it is enabled.
    *
    * @param ifpContractDetailses the ifp contract detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IfpContractDetails> ifpContractDetailses) {
        getPersistence().cacheResult(ifpContractDetailses);
    }

    /**
    * Creates a new ifp contract details with the primary key. Does not add the ifp contract details to the database.
    *
    * @param ifpContractDetailsSid the primary key for the new ifp contract details
    * @return the new ifp contract details
    */
    public static com.stpl.app.model.IfpContractDetails create(
        int ifpContractDetailsSid) {
        return getPersistence().create(ifpContractDetailsSid);
    }

    /**
    * Removes the ifp contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContractDetailsSid the primary key of the ifp contract details
    * @return the ifp contract details that was removed
    * @throws com.stpl.app.NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails remove(
        int ifpContractDetailsSid)
        throws com.stpl.app.NoSuchIfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ifpContractDetailsSid);
    }

    public static com.stpl.app.model.IfpContractDetails updateImpl(
        com.stpl.app.model.IfpContractDetails ifpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ifpContractDetails);
    }

    /**
    * Returns the ifp contract details with the primary key or throws a {@link com.stpl.app.NoSuchIfpContractDetailsException} if it could not be found.
    *
    * @param ifpContractDetailsSid the primary key of the ifp contract details
    * @return the ifp contract details
    * @throws com.stpl.app.NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails findByPrimaryKey(
        int ifpContractDetailsSid)
        throws com.stpl.app.NoSuchIfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ifpContractDetailsSid);
    }

    /**
    * Returns the ifp contract details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ifpContractDetailsSid the primary key of the ifp contract details
    * @return the ifp contract details, or <code>null</code> if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails fetchByPrimaryKey(
        int ifpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ifpContractDetailsSid);
    }

    /**
    * Returns all the ifp contract detailses.
    *
    * @return the ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpContractDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ifp contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp contract detailses
    * @param end the upper bound of the range of ifp contract detailses (not inclusive)
    * @return the range of ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpContractDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ifp contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp contract detailses
    * @param end the upper bound of the range of ifp contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpContractDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ifp contract detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ifp contract detailses.
    *
    * @return the number of ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IfpContractDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IfpContractDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IfpContractDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(IfpContractDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IfpContractDetailsPersistence persistence) {
    }
}
