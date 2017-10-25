package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpContractDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cfp contract details service. This utility wraps {@link CfpContractDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpContractDetailsPersistence
 * @see CfpContractDetailsPersistenceImpl
 * @generated
 */
public class CfpContractDetailsUtil {
    private static CfpContractDetailsPersistence _persistence;

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
    public static void clearCache(CfpContractDetails cfpContractDetails) {
        getPersistence().clearCache(cfpContractDetails);
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
    public static List<CfpContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CfpContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CfpContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CfpContractDetails update(
        CfpContractDetails cfpContractDetails) throws SystemException {
        return getPersistence().update(cfpContractDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CfpContractDetails update(
        CfpContractDetails cfpContractDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(cfpContractDetails, serviceContext);
    }

    /**
    * Returns all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @return the matching cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpContractDetails> findByCFPDetails(
        int companyMasterSid, int cfpContractSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCFPDetails(companyMasterSid, cfpContractSid);
    }

    /**
    * Returns a range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param start the lower bound of the range of cfp contract detailses
    * @param end the upper bound of the range of cfp contract detailses (not inclusive)
    * @return the range of matching cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpContractDetails> findByCFPDetails(
        int companyMasterSid, int cfpContractSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCFPDetails(companyMasterSid, cfpContractSid, start,
            end);
    }

    /**
    * Returns an ordered range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param start the lower bound of the range of cfp contract detailses
    * @param end the upper bound of the range of cfp contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpContractDetails> findByCFPDetails(
        int companyMasterSid, int cfpContractSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCFPDetails(companyMasterSid, cfpContractSid, start,
            end, orderByComparator);
    }

    /**
    * Returns the first cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp contract details
    * @throws com.stpl.app.NoSuchCfpContractDetailsException if a matching cfp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpContractDetails findByCFPDetails_First(
        int companyMasterSid, int cfpContractSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCFPDetails_First(companyMasterSid, cfpContractSid,
            orderByComparator);
    }

    /**
    * Returns the first cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp contract details, or <code>null</code> if a matching cfp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpContractDetails fetchByCFPDetails_First(
        int companyMasterSid, int cfpContractSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCFPDetails_First(companyMasterSid, cfpContractSid,
            orderByComparator);
    }

    /**
    * Returns the last cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp contract details
    * @throws com.stpl.app.NoSuchCfpContractDetailsException if a matching cfp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpContractDetails findByCFPDetails_Last(
        int companyMasterSid, int cfpContractSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCFPDetails_Last(companyMasterSid, cfpContractSid,
            orderByComparator);
    }

    /**
    * Returns the last cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp contract details, or <code>null</code> if a matching cfp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpContractDetails fetchByCFPDetails_Last(
        int companyMasterSid, int cfpContractSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCFPDetails_Last(companyMasterSid, cfpContractSid,
            orderByComparator);
    }

    /**
    * Returns the cfp contract detailses before and after the current cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param cfpContractDetailsSid the primary key of the current cfp contract details
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp contract details
    * @throws com.stpl.app.NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpContractDetails[] findByCFPDetails_PrevAndNext(
        int cfpContractDetailsSid, int companyMasterSid, int cfpContractSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCFPDetails_PrevAndNext(cfpContractDetailsSid,
            companyMasterSid, cfpContractSid, orderByComparator);
    }

    /**
    * Removes all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63; from the database.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCFPDetails(int companyMasterSid,
        int cfpContractSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCFPDetails(companyMasterSid, cfpContractSid);
    }

    /**
    * Returns the number of cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @return the number of matching cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countByCFPDetails(int companyMasterSid, int cfpContractSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByCFPDetails(companyMasterSid, cfpContractSid);
    }

    /**
    * Caches the cfp contract details in the entity cache if it is enabled.
    *
    * @param cfpContractDetails the cfp contract details
    */
    public static void cacheResult(
        com.stpl.app.model.CfpContractDetails cfpContractDetails) {
        getPersistence().cacheResult(cfpContractDetails);
    }

    /**
    * Caches the cfp contract detailses in the entity cache if it is enabled.
    *
    * @param cfpContractDetailses the cfp contract detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CfpContractDetails> cfpContractDetailses) {
        getPersistence().cacheResult(cfpContractDetailses);
    }

    /**
    * Creates a new cfp contract details with the primary key. Does not add the cfp contract details to the database.
    *
    * @param cfpContractDetailsSid the primary key for the new cfp contract details
    * @return the new cfp contract details
    */
    public static com.stpl.app.model.CfpContractDetails create(
        int cfpContractDetailsSid) {
        return getPersistence().create(cfpContractDetailsSid);
    }

    /**
    * Removes the cfp contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpContractDetailsSid the primary key of the cfp contract details
    * @return the cfp contract details that was removed
    * @throws com.stpl.app.NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpContractDetails remove(
        int cfpContractDetailsSid)
        throws com.stpl.app.NoSuchCfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(cfpContractDetailsSid);
    }

    public static com.stpl.app.model.CfpContractDetails updateImpl(
        com.stpl.app.model.CfpContractDetails cfpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(cfpContractDetails);
    }

    /**
    * Returns the cfp contract details with the primary key or throws a {@link com.stpl.app.NoSuchCfpContractDetailsException} if it could not be found.
    *
    * @param cfpContractDetailsSid the primary key of the cfp contract details
    * @return the cfp contract details
    * @throws com.stpl.app.NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpContractDetails findByPrimaryKey(
        int cfpContractDetailsSid)
        throws com.stpl.app.NoSuchCfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(cfpContractDetailsSid);
    }

    /**
    * Returns the cfp contract details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cfpContractDetailsSid the primary key of the cfp contract details
    * @return the cfp contract details, or <code>null</code> if a cfp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpContractDetails fetchByPrimaryKey(
        int cfpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(cfpContractDetailsSid);
    }

    /**
    * Returns all the cfp contract detailses.
    *
    * @return the cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpContractDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the cfp contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp contract detailses
    * @param end the upper bound of the range of cfp contract detailses (not inclusive)
    * @return the range of cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpContractDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the cfp contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp contract detailses
    * @param end the upper bound of the range of cfp contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpContractDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the cfp contract detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of cfp contract detailses.
    *
    * @return the number of cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CfpContractDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CfpContractDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CfpContractDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(CfpContractDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CfpContractDetailsPersistence persistence) {
    }
}
