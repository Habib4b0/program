package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MailNotificationMasterLocalService}.
 *
 * @author
 * @see MailNotificationMasterLocalService
 * @generated
 */
public class MailNotificationMasterLocalServiceWrapper
    implements MailNotificationMasterLocalService,
        ServiceWrapper<MailNotificationMasterLocalService> {
    private MailNotificationMasterLocalService _mailNotificationMasterLocalService;

    public MailNotificationMasterLocalServiceWrapper(
        MailNotificationMasterLocalService mailNotificationMasterLocalService) {
        _mailNotificationMasterLocalService = mailNotificationMasterLocalService;
    }

    /**
    * Adds the mail notification master to the database. Also notifies the appropriate model listeners.
    *
    * @param mailNotificationMaster the mail notification master
    * @return the mail notification master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MailNotificationMaster addMailNotificationMaster(
        com.stpl.app.model.MailNotificationMaster mailNotificationMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.addMailNotificationMaster(mailNotificationMaster);
    }

    /**
    * Creates a new mail notification master with the primary key. Does not add the mail notification master to the database.
    *
    * @param mailNotificationSid the primary key for the new mail notification master
    * @return the new mail notification master
    */
    @Override
    public com.stpl.app.model.MailNotificationMaster createMailNotificationMaster(
        int mailNotificationSid) {
        return _mailNotificationMasterLocalService.createMailNotificationMaster(mailNotificationSid);
    }

    /**
    * Deletes the mail notification master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mailNotificationSid the primary key of the mail notification master
    * @return the mail notification master that was removed
    * @throws PortalException if a mail notification master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MailNotificationMaster deleteMailNotificationMaster(
        int mailNotificationSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.deleteMailNotificationMaster(mailNotificationSid);
    }

    /**
    * Deletes the mail notification master from the database. Also notifies the appropriate model listeners.
    *
    * @param mailNotificationMaster the mail notification master
    * @return the mail notification master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MailNotificationMaster deleteMailNotificationMaster(
        com.stpl.app.model.MailNotificationMaster mailNotificationMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.deleteMailNotificationMaster(mailNotificationMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _mailNotificationMasterLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.MailNotificationMaster fetchMailNotificationMaster(
        int mailNotificationSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.fetchMailNotificationMaster(mailNotificationSid);
    }

    /**
    * Returns the mail notification master with the primary key.
    *
    * @param mailNotificationSid the primary key of the mail notification master
    * @return the mail notification master
    * @throws PortalException if a mail notification master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MailNotificationMaster getMailNotificationMaster(
        int mailNotificationSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.getMailNotificationMaster(mailNotificationSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.getPersistedModel(primaryKeyObj);
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
    @Override
    public java.util.List<com.stpl.app.model.MailNotificationMaster> getMailNotificationMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.getMailNotificationMasters(start,
            end);
    }

    /**
    * Returns the number of mail notification masters.
    *
    * @return the number of mail notification masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMailNotificationMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.getMailNotificationMastersCount();
    }

    /**
    * Updates the mail notification master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param mailNotificationMaster the mail notification master
    * @return the mail notification master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MailNotificationMaster updateMailNotificationMaster(
        com.stpl.app.model.MailNotificationMaster mailNotificationMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mailNotificationMasterLocalService.updateMailNotificationMaster(mailNotificationMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _mailNotificationMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _mailNotificationMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _mailNotificationMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MailNotificationMasterLocalService getWrappedMailNotificationMasterLocalService() {
        return _mailNotificationMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMailNotificationMasterLocalService(
        MailNotificationMasterLocalService mailNotificationMasterLocalService) {
        _mailNotificationMasterLocalService = mailNotificationMasterLocalService;
    }

    @Override
    public MailNotificationMasterLocalService getWrappedService() {
        return _mailNotificationMasterLocalService;
    }

    @Override
    public void setWrappedService(
        MailNotificationMasterLocalService mailNotificationMasterLocalService) {
        _mailNotificationMasterLocalService = mailNotificationMasterLocalService;
    }
}
