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
 * Provides the local service interface for ImtdItemPriceRebateDetails. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author
 * @see ImtdItemPriceRebateDetailsLocalServiceUtil
 * @see com.stpl.app.service.base.ImtdItemPriceRebateDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ImtdItemPriceRebateDetailsLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ImtdItemPriceRebateDetailsLocalService extends BaseLocalService,
    InvokableLocalService, PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImtdItemPriceRebateDetailsLocalServiceUtil} to access the imtd item price rebate details local service. Add custom service methods to {@link com.stpl.app.service.impl.ImtdItemPriceRebateDetailsLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the imtd item price rebate details to the database. Also notifies the appropriate model listeners.
    *
    * @param imtdItemPriceRebateDetails the imtd item price rebate details
    * @return the imtd item price rebate details that was added
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdItemPriceRebateDetails addImtdItemPriceRebateDetails(
        com.stpl.app.model.ImtdItemPriceRebateDetails imtdItemPriceRebateDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Creates a new imtd item price rebate details with the primary key. Does not add the imtd item price rebate details to the database.
    *
    * @param imtdItemPriceRebateSid the primary key for the new imtd item price rebate details
    * @return the new imtd item price rebate details
    */
    public com.stpl.app.model.ImtdItemPriceRebateDetails createImtdItemPriceRebateDetails(
        int imtdItemPriceRebateSid);

    /**
    * Deletes the imtd item price rebate details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
    * @return the imtd item price rebate details that was removed
    * @throws PortalException if a imtd item price rebate details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdItemPriceRebateDetails deleteImtdItemPriceRebateDetails(
        int imtdItemPriceRebateSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Deletes the imtd item price rebate details from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdItemPriceRebateDetails the imtd item price rebate details
    * @return the imtd item price rebate details that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdItemPriceRebateDetails deleteImtdItemPriceRebateDetails(
        com.stpl.app.model.ImtdItemPriceRebateDetails imtdItemPriceRebateDetails)
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public com.stpl.app.model.ImtdItemPriceRebateDetails fetchImtdItemPriceRebateDetails(
        int imtdItemPriceRebateSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd item price rebate details with the primary key.
    *
    * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
    * @return the imtd item price rebate details
    * @throws PortalException if a imtd item price rebate details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.app.model.ImtdItemPriceRebateDetails getImtdItemPriceRebateDetails(
        int imtdItemPriceRebateSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the imtd item price rebate detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd item price rebate detailses
    * @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
    * @return the range of imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.stpl.app.model.ImtdItemPriceRebateDetails> getImtdItemPriceRebateDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of imtd item price rebate detailses.
    *
    * @return the number of imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getImtdItemPriceRebateDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Updates the imtd item price rebate details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param imtdItemPriceRebateDetails the imtd item price rebate details
    * @return the imtd item price rebate details that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdItemPriceRebateDetails updateImtdItemPriceRebateDetails(
        com.stpl.app.model.ImtdItemPriceRebateDetails imtdItemPriceRebateDetails)
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
    public java.lang.Boolean loadTempItemdetails(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.lang.Boolean massPopulate(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.lang.Boolean massPopulateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.lang.Boolean saveItem(java.util.List<java.lang.Object> input,
        java.lang.Object future);

    public java.lang.Boolean deleteAll(java.util.List<java.lang.Object> input,
        java.lang.Object future);

    public java.lang.Boolean updateAll(java.util.List<java.lang.Object> input,
        java.lang.Object future);

    public java.lang.Object validateTempRSDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getSelectedItemList(java.lang.String userID,
        java.lang.String sessionID, int start, int offset,
        java.lang.String column, java.lang.Boolean orderBy,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        boolean isCount, java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3);

    public void mergeImtdRsContractDetailsFormula(int contRsdSid, int itemSid,
        java.lang.String userId, java.lang.String sessionId);

    public void deleteTempRsContractTableRecords(int contRsSid, int contRsdSid,
        java.lang.String userId, java.lang.String sessionId);

    public void insertFormulaToContractRsdFrImtd(int contRsdSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate);

    public void addAllFormulaToContractRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate);

    public void remaoveAllFormulaToContractRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate);
}
