package com.stpl.app.service.persistence;

import com.stpl.app.model.MailNotificationMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the mail notification master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MailNotificationMasterPersistenceImpl
 * @see MailNotificationMasterUtil
 * @generated
 */
public interface MailNotificationMasterPersistence extends BasePersistence<MailNotificationMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MailNotificationMasterUtil} to access the mail notification master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the mail notification master in the entity cache if it is enabled.
    *
    * @param mailNotificationMaster the mail notification master
    */
    public void cacheResult(
        com.stpl.app.model.MailNotificationMaster mailNotificationMaster);

    /**
    * Caches the mail notification masters in the entity cache if it is enabled.
    *
    * @param mailNotificationMasters the mail notification masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.MailNotificationMaster> mailNotificationMasters);

    /**
    * Creates a new mail notification master with the primary key. Does not add the mail notification master to the database.
    *
    * @param mailNotificationSid the primary key for the new mail notification master
    * @return the new mail notification master
    */
    public com.stpl.app.model.MailNotificationMaster create(
        int mailNotificationSid);

    /**
    * Removes the mail notification master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mailNotificationSid the primary key of the mail notification master
    * @return the mail notification master that was removed
    * @throws com.stpl.app.NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MailNotificationMaster remove(
        int mailNotificationSid)
        throws com.stpl.app.NoSuchMailNotificationMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.MailNotificationMaster updateImpl(
        com.stpl.app.model.MailNotificationMaster mailNotificationMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the mail notification master with the primary key or throws a {@link com.stpl.app.NoSuchMailNotificationMasterException} if it could not be found.
    *
    * @param mailNotificationSid the primary key of the mail notification master
    * @return the mail notification master
    * @throws com.stpl.app.NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MailNotificationMaster findByPrimaryKey(
        int mailNotificationSid)
        throws com.stpl.app.NoSuchMailNotificationMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the mail notification master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param mailNotificationSid the primary key of the mail notification master
    * @return the mail notification master, or <code>null</code> if a mail notification master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MailNotificationMaster fetchByPrimaryKey(
        int mailNotificationSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the mail notification masters.
    *
    * @return the mail notification masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MailNotificationMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.MailNotificationMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.MailNotificationMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the mail notification masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of mail notification masters.
    *
    * @return the number of mail notification masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
