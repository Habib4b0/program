package com.stpl.app.service.persistence;

import com.stpl.app.model.MailNotificationMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the mail notification master service. This utility wraps {@link MailNotificationMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MailNotificationMasterPersistence
 * @see MailNotificationMasterPersistenceImpl
 * @generated
 */
public class MailNotificationMasterUtil {
    private static MailNotificationMasterPersistence _persistence;

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
    public static void clearCache(MailNotificationMaster mailNotificationMaster) {
        getPersistence().clearCache(mailNotificationMaster);
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
    public static List<MailNotificationMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MailNotificationMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MailNotificationMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static MailNotificationMaster update(
        MailNotificationMaster mailNotificationMaster)
        throws SystemException {
        return getPersistence().update(mailNotificationMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static MailNotificationMaster update(
        MailNotificationMaster mailNotificationMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(mailNotificationMaster, serviceContext);
    }

    /**
    * Caches the mail notification master in the entity cache if it is enabled.
    *
    * @param mailNotificationMaster the mail notification master
    */
    public static void cacheResult(
        com.stpl.app.model.MailNotificationMaster mailNotificationMaster) {
        getPersistence().cacheResult(mailNotificationMaster);
    }

    /**
    * Caches the mail notification masters in the entity cache if it is enabled.
    *
    * @param mailNotificationMasters the mail notification masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.MailNotificationMaster> mailNotificationMasters) {
        getPersistence().cacheResult(mailNotificationMasters);
    }

    /**
    * Creates a new mail notification master with the primary key. Does not add the mail notification master to the database.
    *
    * @param mailNotificationSid the primary key for the new mail notification master
    * @return the new mail notification master
    */
    public static com.stpl.app.model.MailNotificationMaster create(
        int mailNotificationSid) {
        return getPersistence().create(mailNotificationSid);
    }

    /**
    * Removes the mail notification master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mailNotificationSid the primary key of the mail notification master
    * @return the mail notification master that was removed
    * @throws com.stpl.app.NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MailNotificationMaster remove(
        int mailNotificationSid)
        throws com.stpl.app.NoSuchMailNotificationMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(mailNotificationSid);
    }

    public static com.stpl.app.model.MailNotificationMaster updateImpl(
        com.stpl.app.model.MailNotificationMaster mailNotificationMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(mailNotificationMaster);
    }

    /**
    * Returns the mail notification master with the primary key or throws a {@link com.stpl.app.NoSuchMailNotificationMasterException} if it could not be found.
    *
    * @param mailNotificationSid the primary key of the mail notification master
    * @return the mail notification master
    * @throws com.stpl.app.NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MailNotificationMaster findByPrimaryKey(
        int mailNotificationSid)
        throws com.stpl.app.NoSuchMailNotificationMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(mailNotificationSid);
    }

    /**
    * Returns the mail notification master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param mailNotificationSid the primary key of the mail notification master
    * @return the mail notification master, or <code>null</code> if a mail notification master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MailNotificationMaster fetchByPrimaryKey(
        int mailNotificationSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(mailNotificationSid);
    }

    /**
    * Returns all the mail notification masters.
    *
    * @return the mail notification masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MailNotificationMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the mail notification masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of mail notification masters
    * @param end the upper bound of the range of mail notification masters (not inclusive)
    * @return the range of mail notification masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MailNotificationMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the mail notification masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of mail notification masters
    * @param end the upper bound of the range of mail notification masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of mail notification masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MailNotificationMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the mail notification masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of mail notification masters.
    *
    * @return the number of mail notification masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MailNotificationMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MailNotificationMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MailNotificationMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(MailNotificationMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MailNotificationMasterPersistence persistence) {
    }
}
