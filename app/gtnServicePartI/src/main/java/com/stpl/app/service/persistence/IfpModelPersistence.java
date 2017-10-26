package com.stpl.app.service.persistence;

import com.stpl.app.model.IfpModel;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ifp model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpModelPersistenceImpl
 * @see IfpModelUtil
 * @generated
 */
public interface IfpModelPersistence extends BasePersistence<IfpModel> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IfpModelUtil} to access the ifp model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the ifp models where ifpId = &#63;.
    *
    * @param ifpId the ifp ID
    * @return the matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanId(
        java.lang.String ifpId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ifp models where ifpId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpId the ifp ID
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @return the range of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanId(
        java.lang.String ifpId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ifp models where ifpId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpId the ifp ID
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanId(
        java.lang.String ifpId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp model in the ordered set where ifpId = &#63;.
    *
    * @param ifpId the ifp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel findByItemFamilyPlanId_First(
        java.lang.String ifpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp model in the ordered set where ifpId = &#63;.
    *
    * @param ifpId the ifp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel fetchByItemFamilyPlanId_First(
        java.lang.String ifpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp model in the ordered set where ifpId = &#63;.
    *
    * @param ifpId the ifp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel findByItemFamilyPlanId_Last(
        java.lang.String ifpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp model in the ordered set where ifpId = &#63;.
    *
    * @param ifpId the ifp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel fetchByItemFamilyPlanId_Last(
        java.lang.String ifpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp models before and after the current ifp model in the ordered set where ifpId = &#63;.
    *
    * @param ifpModelSid the primary key of the current ifp model
    * @param ifpId the ifp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a ifp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel[] findByItemFamilyPlanId_PrevAndNext(
        int ifpModelSid, java.lang.String ifpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ifp models where ifpId = &#63; from the database.
    *
    * @param ifpId the ifp ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemFamilyPlanId(java.lang.String ifpId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ifp models where ifpId = &#63;.
    *
    * @param ifpId the ifp ID
    * @return the number of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public int countByItemFamilyPlanId(java.lang.String ifpId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ifp models where ifpNo = &#63;.
    *
    * @param ifpNo the ifp no
    * @return the matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanNo(
        java.lang.String ifpNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ifp models where ifpNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpNo the ifp no
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @return the range of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanNo(
        java.lang.String ifpNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ifp models where ifpNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpNo the ifp no
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanNo(
        java.lang.String ifpNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp model in the ordered set where ifpNo = &#63;.
    *
    * @param ifpNo the ifp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel findByItemFamilyPlanNo_First(
        java.lang.String ifpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp model in the ordered set where ifpNo = &#63;.
    *
    * @param ifpNo the ifp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel fetchByItemFamilyPlanNo_First(
        java.lang.String ifpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp model in the ordered set where ifpNo = &#63;.
    *
    * @param ifpNo the ifp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel findByItemFamilyPlanNo_Last(
        java.lang.String ifpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp model in the ordered set where ifpNo = &#63;.
    *
    * @param ifpNo the ifp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel fetchByItemFamilyPlanNo_Last(
        java.lang.String ifpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp models before and after the current ifp model in the ordered set where ifpNo = &#63;.
    *
    * @param ifpModelSid the primary key of the current ifp model
    * @param ifpNo the ifp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a ifp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel[] findByItemFamilyPlanNo_PrevAndNext(
        int ifpModelSid, java.lang.String ifpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ifp models where ifpNo = &#63; from the database.
    *
    * @param ifpNo the ifp no
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemFamilyPlanNo(java.lang.String ifpNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ifp models where ifpNo = &#63;.
    *
    * @param ifpNo the ifp no
    * @return the number of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public int countByItemFamilyPlanNo(java.lang.String ifpNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ifp models where ifpName = &#63;.
    *
    * @param ifpName the ifp name
    * @return the matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanName(
        java.lang.String ifpName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ifp models where ifpName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpName the ifp name
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @return the range of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanName(
        java.lang.String ifpName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ifp models where ifpName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpName the ifp name
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanName(
        java.lang.String ifpName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp model in the ordered set where ifpName = &#63;.
    *
    * @param ifpName the ifp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel findByItemFamilyPlanName_First(
        java.lang.String ifpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp model in the ordered set where ifpName = &#63;.
    *
    * @param ifpName the ifp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel fetchByItemFamilyPlanName_First(
        java.lang.String ifpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp model in the ordered set where ifpName = &#63;.
    *
    * @param ifpName the ifp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel findByItemFamilyPlanName_Last(
        java.lang.String ifpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp model in the ordered set where ifpName = &#63;.
    *
    * @param ifpName the ifp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel fetchByItemFamilyPlanName_Last(
        java.lang.String ifpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp models before and after the current ifp model in the ordered set where ifpName = &#63;.
    *
    * @param ifpModelSid the primary key of the current ifp model
    * @param ifpName the ifp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a ifp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel[] findByItemFamilyPlanName_PrevAndNext(
        int ifpModelSid, java.lang.String ifpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ifp models where ifpName = &#63; from the database.
    *
    * @param ifpName the ifp name
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemFamilyPlanName(java.lang.String ifpName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ifp models where ifpName = &#63;.
    *
    * @param ifpName the ifp name
    * @return the number of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public int countByItemFamilyPlanName(java.lang.String ifpName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ifp models where ifpType = &#63;.
    *
    * @param ifpType the ifp type
    * @return the matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanType(
        int ifpType) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ifp models where ifpType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpType the ifp type
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @return the range of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanType(
        int ifpType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ifp models where ifpType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpType the ifp type
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanType(
        int ifpType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp model in the ordered set where ifpType = &#63;.
    *
    * @param ifpType the ifp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel findByItemFamilyPlanType_First(
        int ifpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp model in the ordered set where ifpType = &#63;.
    *
    * @param ifpType the ifp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel fetchByItemFamilyPlanType_First(
        int ifpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp model in the ordered set where ifpType = &#63;.
    *
    * @param ifpType the ifp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel findByItemFamilyPlanType_Last(
        int ifpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp model in the ordered set where ifpType = &#63;.
    *
    * @param ifpType the ifp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel fetchByItemFamilyPlanType_Last(
        int ifpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp models before and after the current ifp model in the ordered set where ifpType = &#63;.
    *
    * @param ifpModelSid the primary key of the current ifp model
    * @param ifpType the ifp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a ifp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel[] findByItemFamilyPlanType_PrevAndNext(
        int ifpModelSid, int ifpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ifp models where ifpType = &#63; from the database.
    *
    * @param ifpType the ifp type
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemFamilyPlanType(int ifpType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ifp models where ifpType = &#63;.
    *
    * @param ifpType the ifp type
    * @return the number of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public int countByItemFamilyPlanType(int ifpType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ifp models where ifpStatus = &#63;.
    *
    * @param ifpStatus the ifp status
    * @return the matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanStatus(
        int ifpStatus) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ifp models where ifpStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpStatus the ifp status
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @return the range of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanStatus(
        int ifpStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ifp models where ifpStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpStatus the ifp status
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findByItemFamilyPlanStatus(
        int ifpStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp model in the ordered set where ifpStatus = &#63;.
    *
    * @param ifpStatus the ifp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel findByItemFamilyPlanStatus_First(
        int ifpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp model in the ordered set where ifpStatus = &#63;.
    *
    * @param ifpStatus the ifp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel fetchByItemFamilyPlanStatus_First(
        int ifpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp model in the ordered set where ifpStatus = &#63;.
    *
    * @param ifpStatus the ifp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel findByItemFamilyPlanStatus_Last(
        int ifpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp model in the ordered set where ifpStatus = &#63;.
    *
    * @param ifpStatus the ifp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel fetchByItemFamilyPlanStatus_Last(
        int ifpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp models before and after the current ifp model in the ordered set where ifpStatus = &#63;.
    *
    * @param ifpModelSid the primary key of the current ifp model
    * @param ifpStatus the ifp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a ifp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel[] findByItemFamilyPlanStatus_PrevAndNext(
        int ifpModelSid, int ifpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ifp models where ifpStatus = &#63; from the database.
    *
    * @param ifpStatus the ifp status
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemFamilyPlanStatus(int ifpStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ifp models where ifpStatus = &#63;.
    *
    * @param ifpStatus the ifp status
    * @return the number of matching ifp models
    * @throws SystemException if a system exception occurred
    */
    public int countByItemFamilyPlanStatus(int ifpStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the ifp model in the entity cache if it is enabled.
    *
    * @param ifpModel the ifp model
    */
    public void cacheResult(com.stpl.app.model.IfpModel ifpModel);

    /**
    * Caches the ifp models in the entity cache if it is enabled.
    *
    * @param ifpModels the ifp models
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IfpModel> ifpModels);

    /**
    * Creates a new ifp model with the primary key. Does not add the ifp model to the database.
    *
    * @param ifpModelSid the primary key for the new ifp model
    * @return the new ifp model
    */
    public com.stpl.app.model.IfpModel create(int ifpModelSid);

    /**
    * Removes the ifp model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpModelSid the primary key of the ifp model
    * @return the ifp model that was removed
    * @throws com.stpl.app.NoSuchIfpModelException if a ifp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel remove(int ifpModelSid)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IfpModel updateImpl(
        com.stpl.app.model.IfpModel ifpModel)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp model with the primary key or throws a {@link com.stpl.app.NoSuchIfpModelException} if it could not be found.
    *
    * @param ifpModelSid the primary key of the ifp model
    * @return the ifp model
    * @throws com.stpl.app.NoSuchIfpModelException if a ifp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel findByPrimaryKey(int ifpModelSid)
        throws com.stpl.app.NoSuchIfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp model with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ifpModelSid the primary key of the ifp model
    * @return the ifp model, or <code>null</code> if a ifp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpModel fetchByPrimaryKey(int ifpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ifp models.
    *
    * @return the ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ifp models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @return the range of ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ifp models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ifp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpModel> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ifp models from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ifp models.
    *
    * @return the number of ifp models
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
