package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldMasterDataAttributeLocalService}.
 *
 * @author
 * @see IvldMasterDataAttributeLocalService
 * @generated
 */
public class IvldMasterDataAttributeLocalServiceWrapper
    implements IvldMasterDataAttributeLocalService,
        ServiceWrapper<IvldMasterDataAttributeLocalService> {
    private IvldMasterDataAttributeLocalService _ivldMasterDataAttributeLocalService;

    public IvldMasterDataAttributeLocalServiceWrapper(
        IvldMasterDataAttributeLocalService ivldMasterDataAttributeLocalService) {
        _ivldMasterDataAttributeLocalService = ivldMasterDataAttributeLocalService;
    }

    /**
    * Adds the ivld master data attribute to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldMasterDataAttribute the ivld master data attribute
    * @return the ivld master data attribute that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldMasterDataAttribute addIvldMasterDataAttribute(
        com.stpl.app.model.IvldMasterDataAttribute ivldMasterDataAttribute)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldMasterDataAttributeLocalService.addIvldMasterDataAttribute(ivldMasterDataAttribute);
    }

    /**
    * Creates a new ivld master data attribute with the primary key. Does not add the ivld master data attribute to the database.
    *
    * @param ivldMasterDataAtbteSid the primary key for the new ivld master data attribute
    * @return the new ivld master data attribute
    */
    @Override
    public com.stpl.app.model.IvldMasterDataAttribute createIvldMasterDataAttribute(
        int ivldMasterDataAtbteSid) {
        return _ivldMasterDataAttributeLocalService.createIvldMasterDataAttribute(ivldMasterDataAtbteSid);
    }

    /**
    * Deletes the ivld master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
    * @return the ivld master data attribute that was removed
    * @throws PortalException if a ivld master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldMasterDataAttribute deleteIvldMasterDataAttribute(
        int ivldMasterDataAtbteSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldMasterDataAttributeLocalService.deleteIvldMasterDataAttribute(ivldMasterDataAtbteSid);
    }

    /**
    * Deletes the ivld master data attribute from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldMasterDataAttribute the ivld master data attribute
    * @return the ivld master data attribute that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldMasterDataAttribute deleteIvldMasterDataAttribute(
        com.stpl.app.model.IvldMasterDataAttribute ivldMasterDataAttribute)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldMasterDataAttributeLocalService.deleteIvldMasterDataAttribute(ivldMasterDataAttribute);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldMasterDataAttributeLocalService.dynamicQuery();
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
        return _ivldMasterDataAttributeLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldMasterDataAttributeLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldMasterDataAttributeLocalService.dynamicQuery(dynamicQuery,
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
        return _ivldMasterDataAttributeLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldMasterDataAttributeLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldMasterDataAttribute fetchIvldMasterDataAttribute(
        int ivldMasterDataAtbteSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldMasterDataAttributeLocalService.fetchIvldMasterDataAttribute(ivldMasterDataAtbteSid);
    }

    /**
    * Returns the ivld master data attribute with the primary key.
    *
    * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
    * @return the ivld master data attribute
    * @throws PortalException if a ivld master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldMasterDataAttribute getIvldMasterDataAttribute(
        int ivldMasterDataAtbteSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldMasterDataAttributeLocalService.getIvldMasterDataAttribute(ivldMasterDataAtbteSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldMasterDataAttributeLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld master data attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld master data attributes
    * @param end the upper bound of the range of ivld master data attributes (not inclusive)
    * @return the range of ivld master data attributes
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldMasterDataAttribute> getIvldMasterDataAttributes(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldMasterDataAttributeLocalService.getIvldMasterDataAttributes(start,
            end);
    }

    /**
    * Returns the number of ivld master data attributes.
    *
    * @return the number of ivld master data attributes
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldMasterDataAttributesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldMasterDataAttributeLocalService.getIvldMasterDataAttributesCount();
    }

    /**
    * Updates the ivld master data attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldMasterDataAttribute the ivld master data attribute
    * @return the ivld master data attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldMasterDataAttribute updateIvldMasterDataAttribute(
        com.stpl.app.model.IvldMasterDataAttribute ivldMasterDataAttribute)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldMasterDataAttributeLocalService.updateIvldMasterDataAttribute(ivldMasterDataAttribute);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldMasterDataAttributeLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldMasterDataAttributeLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldMasterDataAttributeLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldMasterDataAttributeLocalService getWrappedIvldMasterDataAttributeLocalService() {
        return _ivldMasterDataAttributeLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldMasterDataAttributeLocalService(
        IvldMasterDataAttributeLocalService ivldMasterDataAttributeLocalService) {
        _ivldMasterDataAttributeLocalService = ivldMasterDataAttributeLocalService;
    }

    @Override
    public IvldMasterDataAttributeLocalService getWrappedService() {
        return _ivldMasterDataAttributeLocalService;
    }

    @Override
    public void setWrappedService(
        IvldMasterDataAttributeLocalService ivldMasterDataAttributeLocalService) {
        _ivldMasterDataAttributeLocalService = ivldMasterDataAttributeLocalService;
    }
}
