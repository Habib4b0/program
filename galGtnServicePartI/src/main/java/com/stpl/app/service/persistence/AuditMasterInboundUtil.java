package com.stpl.app.service.persistence;

import com.stpl.app.model.AuditMasterInbound;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the audit master inbound service. This utility wraps {@link AuditMasterInboundPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AuditMasterInboundPersistence
 * @see AuditMasterInboundPersistenceImpl
 * @generated
 */
public class AuditMasterInboundUtil {
    private static AuditMasterInboundPersistence _persistence;

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
    public static void clearCache(AuditMasterInbound auditMasterInbound) {
        getPersistence().clearCache(auditMasterInbound);
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
    public static List<AuditMasterInbound> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AuditMasterInbound> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AuditMasterInbound> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static AuditMasterInbound update(
        AuditMasterInbound auditMasterInbound) throws SystemException {
        return getPersistence().update(auditMasterInbound);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static AuditMasterInbound update(
        AuditMasterInbound auditMasterInbound, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(auditMasterInbound, serviceContext);
    }

    /**
    * Caches the audit master inbound in the entity cache if it is enabled.
    *
    * @param auditMasterInbound the audit master inbound
    */
    public static void cacheResult(
        com.stpl.app.model.AuditMasterInbound auditMasterInbound) {
        getPersistence().cacheResult(auditMasterInbound);
    }

    /**
    * Caches the audit master inbounds in the entity cache if it is enabled.
    *
    * @param auditMasterInbounds the audit master inbounds
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.AuditMasterInbound> auditMasterInbounds) {
        getPersistence().cacheResult(auditMasterInbounds);
    }

    /**
    * Creates a new audit master inbound with the primary key. Does not add the audit master inbound to the database.
    *
    * @param auditInboundSid the primary key for the new audit master inbound
    * @return the new audit master inbound
    */
    public static com.stpl.app.model.AuditMasterInbound create(
        int auditInboundSid) {
        return getPersistence().create(auditInboundSid);
    }

    /**
    * Removes the audit master inbound with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param auditInboundSid the primary key of the audit master inbound
    * @return the audit master inbound that was removed
    * @throws com.stpl.app.NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AuditMasterInbound remove(
        int auditInboundSid)
        throws com.stpl.app.NoSuchAuditMasterInboundException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(auditInboundSid);
    }

    public static com.stpl.app.model.AuditMasterInbound updateImpl(
        com.stpl.app.model.AuditMasterInbound auditMasterInbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(auditMasterInbound);
    }

    /**
    * Returns the audit master inbound with the primary key or throws a {@link com.stpl.app.NoSuchAuditMasterInboundException} if it could not be found.
    *
    * @param auditInboundSid the primary key of the audit master inbound
    * @return the audit master inbound
    * @throws com.stpl.app.NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AuditMasterInbound findByPrimaryKey(
        int auditInboundSid)
        throws com.stpl.app.NoSuchAuditMasterInboundException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(auditInboundSid);
    }

    /**
    * Returns the audit master inbound with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param auditInboundSid the primary key of the audit master inbound
    * @return the audit master inbound, or <code>null</code> if a audit master inbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AuditMasterInbound fetchByPrimaryKey(
        int auditInboundSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(auditInboundSid);
    }

    /**
    * Returns all the audit master inbounds.
    *
    * @return the audit master inbounds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AuditMasterInbound> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the audit master inbounds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of audit master inbounds
    * @param end the upper bound of the range of audit master inbounds (not inclusive)
    * @return the range of audit master inbounds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AuditMasterInbound> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the audit master inbounds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of audit master inbounds
    * @param end the upper bound of the range of audit master inbounds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of audit master inbounds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AuditMasterInbound> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the audit master inbounds from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of audit master inbounds.
    *
    * @return the number of audit master inbounds
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AuditMasterInboundPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AuditMasterInboundPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    AuditMasterInboundPersistence.class.getName());

            ReferenceRegistry.registerReference(AuditMasterInboundUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(AuditMasterInboundPersistence persistence) {
    }
}
