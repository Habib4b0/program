package com.stpl.app.service;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.transaction.Isolation;
import com.stpl.portal.kernel.transaction.Propagation;
import com.stpl.portal.kernel.transaction.Transactional;
import com.stpl.portal.service.BaseLocalService;
import com.stpl.portal.service.InvokableLocalService;
import com.stpl.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for RsModel. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author
 * @see RsModelLocalServiceUtil
 * @see com.stpl.app.service.base.RsModelLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.RsModelLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface RsModelLocalService extends BaseLocalService,
    InvokableLocalService, PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link RsModelLocalServiceUtil} to access the rs model local service. Add custom service methods to {@link com.stpl.app.service.impl.RsModelLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the rs model to the database. Also notifies the appropriate model listeners.
    *
    * @param rsModel the rs model
    * @return the rs model that was added
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel addRsModel(
        com.stpl.app.model.RsModel rsModel)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Creates a new rs model with the primary key. Does not add the rs model to the database.
    *
    * @param rsModelSid the primary key for the new rs model
    * @return the new rs model
    */
    public com.stpl.app.model.RsModel createRsModel(int rsModelSid);

    /**
    * Deletes the rs model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model that was removed
    * @throws PortalException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel deleteRsModel(int rsModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Deletes the rs model from the database. Also notifies the appropriate model listeners.
    *
    * @param rsModel the rs model
    * @return the rs model that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel deleteRsModel(
        com.stpl.app.model.RsModel rsModel)
        throws com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.app.model.RsModel fetchRsModel(int rsModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs model with the primary key.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model
    * @throws PortalException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.app.model.RsModel getRsModel(int rsModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rs models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of rs models
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.stpl.app.model.RsModel> getRsModels(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rs models.
    *
    * @return the number of rs models
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getRsModelsCount()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Updates the rs model in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param rsModel the rs model
    * @return the rs model that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel updateRsModel(
        com.stpl.app.model.RsModel rsModel)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getItemDetailsOfIfp(java.lang.String ifpId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getRebateScheduleDetails(int rebateScheduleSystemId,
        java.lang.Object future1, java.lang.Object future2);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getRebateScheduleDetailsUniqueCheck(
        java.lang.String rebateScheduleId, java.lang.String itemId,
        java.util.Date itemStartDate);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getRebateScheduleDetailsUniqueCheckWithSysId(
        int rebateScheduleSystemId, java.lang.String rebateScheduleId,
        java.lang.String itemId, java.util.Date itemStartDate);

    public java.util.List findRSModel(java.lang.String rsId,
        java.lang.String rsNo, java.lang.String rsName,
        java.lang.String rsStatus, java.lang.String rpType,
        java.lang.String itemId, java.lang.String itemNo,
        java.lang.String itemName, java.lang.String future,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List findIFP(java.lang.String ifpNumber,
        java.lang.String ifpName, java.lang.String ifpType,
        java.lang.String ifpTypeString, java.lang.String ifpStartDate,
        java.lang.String ifpEndDate, java.lang.String itemNo,
        java.lang.String itemName,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getParentRsList(java.lang.String rebateSchId,
        java.lang.String rebateSchNo, java.lang.String rebateSchName,
        java.lang.String rebateSchStatus, java.lang.String rebateSchType,
        java.lang.String rebateProgType, java.lang.String itemId,
        java.lang.String itemNo, java.lang.String itemName, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters,
        boolean isCount);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getIfpList(java.lang.String ifpNumber,
        java.lang.String ifpName, java.lang.Object ifpType,
        java.lang.String ifpStartDate, java.lang.String ifpEndDate,
        java.lang.String itemNo, java.lang.String itemName, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2);

    public java.lang.Object executeUpdateQuery(
        java.util.List<java.lang.StringBuilder> queryList,
        java.lang.Object obj1, java.lang.Object obj2);

    public java.lang.Object executeUpdateQuery(java.lang.String queryList,
        java.lang.Object obj1, java.lang.Object obj2);
}
