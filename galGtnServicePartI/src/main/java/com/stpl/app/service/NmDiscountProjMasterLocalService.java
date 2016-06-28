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
 * Provides the local service interface for NmDiscountProjMaster. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author
 * @see NmDiscountProjMasterLocalServiceUtil
 * @see com.stpl.app.service.base.NmDiscountProjMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.NmDiscountProjMasterLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface NmDiscountProjMasterLocalService extends BaseLocalService,
    InvokableLocalService, PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NmDiscountProjMasterLocalServiceUtil} to access the nm discount proj master local service. Add custom service methods to {@link com.stpl.app.service.impl.NmDiscountProjMasterLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the nm discount proj master to the database. Also notifies the appropriate model listeners.
    *
    * @param nmDiscountProjMaster the nm discount proj master
    * @return the nm discount proj master that was added
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmDiscountProjMaster addNmDiscountProjMaster(
        com.stpl.app.model.NmDiscountProjMaster nmDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Creates a new nm discount proj master with the primary key. Does not add the nm discount proj master to the database.
    *
    * @param projectionDetailsSid the primary key for the new nm discount proj master
    * @return the new nm discount proj master
    */
    public com.stpl.app.model.NmDiscountProjMaster createNmDiscountProjMaster(
        int projectionDetailsSid);

    /**
    * Deletes the nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the nm discount proj master
    * @return the nm discount proj master that was removed
    * @throws PortalException if a nm discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmDiscountProjMaster deleteNmDiscountProjMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Deletes the nm discount proj master from the database. Also notifies the appropriate model listeners.
    *
    * @param nmDiscountProjMaster the nm discount proj master
    * @return the nm discount proj master that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmDiscountProjMaster deleteNmDiscountProjMaster(
        com.stpl.app.model.NmDiscountProjMaster nmDiscountProjMaster)
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public com.stpl.app.model.NmDiscountProjMaster fetchNmDiscountProjMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm discount proj master with the primary key.
    *
    * @param projectionDetailsSid the primary key of the nm discount proj master
    * @return the nm discount proj master
    * @throws PortalException if a nm discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.app.model.NmDiscountProjMaster getNmDiscountProjMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the nm discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm discount proj masters
    * @param end the upper bound of the range of nm discount proj masters (not inclusive)
    * @return the range of nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.stpl.app.model.NmDiscountProjMaster> getNmDiscountProjMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of nm discount proj masters.
    *
    * @return the number of nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getNmDiscountProjMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Updates the nm discount proj master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmDiscountProjMaster the nm discount proj master
    * @return the nm discount proj master that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmDiscountProjMaster updateNmDiscountProjMaster(
        com.stpl.app.model.NmDiscountProjMaster nmDiscountProjMaster)
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
    public java.util.List getDiscountProjection(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String hierarchyNo, boolean isProgram,
        java.util.List<java.lang.String> discountList, java.lang.String year,
        int historyNumber, int levelNo, java.lang.String hierarchyIndicator,
        java.lang.String userGroup, int startIndex, int endIndex,
        boolean isCount, boolean isCustom,
        java.util.List<java.lang.String> customViewDetails, boolean isRefresh,
        java.lang.String refreshHierarchyNumbers,
        java.lang.String relationshipBuilderSid, boolean isAltHistory);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getDiscountPrograms(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String programType);

    public void checkClearAll(int projectionId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String userGroup,
        boolean checkValue, boolean isProgram,
        java.util.List<java.lang.String> discountNamesList);

    public int updateCheckRecord(int projectionId, java.lang.String userId,
        java.lang.String sessionId, boolean checkValue,
        java.lang.String hierarchyNo, java.lang.String userGroup,
        java.lang.String hierarchyIndicator, boolean isCustomView,
        java.util.List<java.lang.String> customViewDetails,
        java.lang.String relationshipBuilderSid, boolean isProgram,
        java.util.List<java.lang.String> discountNamesList);

    public void massUpdate(int projectionId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String selectedField, java.lang.String fieldValue,
        java.util.List<java.lang.String> checkedDiscounts, boolean isProgram);

    public boolean updateInputsForAdjustment(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String frequency, java.lang.String levelType,
        java.lang.String adjustmentType, java.lang.String adjustmentBasis,
        java.lang.String adjustmentValue,
        java.lang.String allocationMethodology,
        java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.List<java.lang.String>>> selectedPeriodsMap);

    public boolean saveDiscountProjectionListView(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String frequency, int period, int year,
        java.lang.String hierarchy, int levelNo, java.lang.String hierarchyNo,
        java.lang.String discountName, java.lang.String fieldValue,
        boolean isProgram, boolean isCustomHierarchy,
        java.util.List<java.lang.String> customViewDetails,
        java.lang.String relationshipBuilderSid);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<java.lang.String> getGroupValues(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String masterTableName,
        java.util.List<java.lang.String> discountList,
        java.lang.String relationshipBuilderSid);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getIndex(int projectionId, java.lang.String hierarchy,
        java.lang.String hierarchyNo, java.lang.String selectedHiearchyNo);

    public boolean saveGroupValues(int projectionId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String hierarchyNo,
        java.lang.String groupValue, boolean isProgram, boolean isCustom,
        java.util.List<java.lang.String> customViewDetails,
        java.util.List<java.lang.String> discountList,
        java.lang.String relationshipBuilderSid);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getDiscountProjectionResults(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String history,
        java.lang.String actualsOrProjections, java.lang.String view,
        java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getChildDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String projection,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String frequency, int userId, int sessionId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getVarianceDiscount(int projectionId,
        java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String actualsOrProjections, java.lang.String parentName,
        java.util.List<java.lang.String> discountList, java.lang.String year,
        int levelNo, java.lang.String sales);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getDiscountNo(int projectionId,
        java.util.List<java.lang.String> priceGroupType);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getTotalDiscountNumber(
        java.util.List<java.lang.Integer> projectionDetailsId,
        java.lang.String frequency, java.lang.String actualsOrProjections,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId, java.util.List<java.lang.Object> view);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getSubDiscount(
        java.util.List<java.lang.Integer> projectionDetailsId,
        java.lang.String frequency, java.lang.String actualsOrProjections,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getTotalDiscountCount(int projectionMasterId,
        java.lang.String frequency, java.lang.String actualsOrProjections,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getAllPesriodDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String history,
        java.lang.String actualsOrProjections, java.lang.String view,
        java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getCheckedRecordCount(int projectionId, java.lang.String userId,
        java.lang.String sessionId, boolean isProgram,
        java.util.List<java.lang.String> discountList);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getCCPDetailsID(int ProjectionMasterSid,
        java.lang.String hierarchyNo, java.lang.String levelNo);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getCCPDetailsIDForProductHierarchy(
        int ProjectionMasterSid, java.lang.String hierarchyNo,
        java.lang.String levelNo);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getCCPDetailsIDForCustomHierarchy(
        int ProjectionMasterSid, java.lang.String hierarchyNo,
        java.lang.String customViewId);
}
