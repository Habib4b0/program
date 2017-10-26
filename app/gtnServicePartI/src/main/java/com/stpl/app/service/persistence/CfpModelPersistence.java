package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpModel;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cfp model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpModelPersistenceImpl
 * @see CfpModelUtil
 * @generated
 */
public interface CfpModelPersistence extends BasePersistence<CfpModel> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CfpModelUtil} to access the cfp model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the cfp models where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpId(
        java.lang.String cfpId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp models where cfpId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpId the cfp ID
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpId(
        java.lang.String cfpId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp models where cfpId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpId the cfp ID
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpId(
        java.lang.String cfpId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpId_First(
        java.lang.String cfpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpId_First(
        java.lang.String cfpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpId_Last(
        java.lang.String cfpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpId_Last(
        java.lang.String cfpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpId = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpId the cfp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel[] findByCfpId_PrevAndNext(
        int cfpModelSid, java.lang.String cfpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp models where cfpId = &#63; from the database.
    *
    * @param cfpId the cfp ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCfpId(java.lang.String cfpId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp models where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public int countByCfpId(java.lang.String cfpId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cfp models where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpNo(
        java.lang.String cfpNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp models where cfpNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpNo the cfp no
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpNo(
        java.lang.String cfpNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp models where cfpNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpNo the cfp no
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpNo(
        java.lang.String cfpNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpNo_First(
        java.lang.String cfpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpNo_First(
        java.lang.String cfpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpNo_Last(
        java.lang.String cfpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpNo_Last(
        java.lang.String cfpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpNo = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpNo the cfp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel[] findByCfpNo_PrevAndNext(
        int cfpModelSid, java.lang.String cfpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp models where cfpNo = &#63; from the database.
    *
    * @param cfpNo the cfp no
    * @throws SystemException if a system exception occurred
    */
    public void removeByCfpNo(java.lang.String cfpNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp models where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public int countByCfpNo(java.lang.String cfpNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cfp models where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpName(
        java.lang.String cfpName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp models where cfpName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpName the cfp name
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpName(
        java.lang.String cfpName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp models where cfpName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpName the cfp name
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpName(
        java.lang.String cfpName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpName_First(
        java.lang.String cfpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpName_First(
        java.lang.String cfpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpName_Last(
        java.lang.String cfpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpName_Last(
        java.lang.String cfpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpName = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpName the cfp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel[] findByCfpName_PrevAndNext(
        int cfpModelSid, java.lang.String cfpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp models where cfpName = &#63; from the database.
    *
    * @param cfpName the cfp name
    * @throws SystemException if a system exception occurred
    */
    public void removeByCfpName(java.lang.String cfpName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp models where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public int countByCfpName(java.lang.String cfpName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cfp models where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpType(
        int cfpType) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp models where cfpType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpType the cfp type
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpType(
        int cfpType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp models where cfpType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpType the cfp type
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpType(
        int cfpType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpType_First(int cfpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpType_First(int cfpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpType_Last(int cfpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpType_Last(int cfpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpType = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpType the cfp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel[] findByCfpType_PrevAndNext(
        int cfpModelSid, int cfpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp models where cfpType = &#63; from the database.
    *
    * @param cfpType the cfp type
    * @throws SystemException if a system exception occurred
    */
    public void removeByCfpType(int cfpType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp models where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public int countByCfpType(int cfpType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cfp models where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpStatus(
        int cfpStatus) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp models where cfpStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpStatus the cfp status
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpStatus(
        int cfpStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp models where cfpStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpStatus the cfp status
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpStatus(
        int cfpStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpStatus_First(int cfpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpStatus_First(int cfpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpStatus_Last(int cfpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpStatus_Last(int cfpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpStatus = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpStatus the cfp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel[] findByCfpStatus_PrevAndNext(
        int cfpModelSid, int cfpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp models where cfpStatus = &#63; from the database.
    *
    * @param cfpStatus the cfp status
    * @throws SystemException if a system exception occurred
    */
    public void removeByCfpStatus(int cfpStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp models where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public int countByCfpStatus(int cfpStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cfp models where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpTradeClass(
        int cfpTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp models where cfpTradeClass = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpTradeClass the cfp trade class
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpTradeClass(
        int cfpTradeClass, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp models where cfpTradeClass = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpTradeClass the cfp trade class
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findByCfpTradeClass(
        int cfpTradeClass, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpTradeClass_First(
        int cfpTradeClass,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp model in the ordered set where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpTradeClass_First(
        int cfpTradeClass,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByCfpTradeClass_Last(
        int cfpTradeClass,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp model in the ordered set where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByCfpTradeClass_Last(
        int cfpTradeClass,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpTradeClass = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpTradeClass the cfp trade class
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel[] findByCfpTradeClass_PrevAndNext(
        int cfpModelSid, int cfpTradeClass,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp models where cfpTradeClass = &#63; from the database.
    *
    * @param cfpTradeClass the cfp trade class
    * @throws SystemException if a system exception occurred
    */
    public void removeByCfpTradeClass(int cfpTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp models where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public int countByCfpTradeClass(int cfpTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the cfp model in the entity cache if it is enabled.
    *
    * @param cfpModel the cfp model
    */
    public void cacheResult(com.stpl.app.model.CfpModel cfpModel);

    /**
    * Caches the cfp models in the entity cache if it is enabled.
    *
    * @param cfpModels the cfp models
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CfpModel> cfpModels);

    /**
    * Creates a new cfp model with the primary key. Does not add the cfp model to the database.
    *
    * @param cfpModelSid the primary key for the new cfp model
    * @return the new cfp model
    */
    public com.stpl.app.model.CfpModel create(int cfpModelSid);

    /**
    * Removes the cfp model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpModelSid the primary key of the cfp model
    * @return the cfp model that was removed
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel remove(int cfpModelSid)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CfpModel updateImpl(
        com.stpl.app.model.CfpModel cfpModel)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp model with the primary key or throws a {@link com.stpl.app.NoSuchCfpModelException} if it could not be found.
    *
    * @param cfpModelSid the primary key of the cfp model
    * @return the cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel findByPrimaryKey(int cfpModelSid)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp model with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cfpModelSid the primary key of the cfp model
    * @return the cfp model, or <code>null</code> if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpModel fetchByPrimaryKey(int cfpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cfp models.
    *
    * @return the cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cfp models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpModel> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp models from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp models.
    *
    * @return the number of cfp models
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
