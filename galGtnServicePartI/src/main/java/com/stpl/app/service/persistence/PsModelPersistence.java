package com.stpl.app.service.persistence;

import com.stpl.app.model.PsModel;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ps model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsModelPersistenceImpl
 * @see PsModelUtil
 * @generated
 */
public interface PsModelPersistence extends BasePersistence<PsModel> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PsModelUtil} to access the ps model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the ps models where psId = &#63;.
    *
    * @param psId the ps ID
    * @return the matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsId(
        java.lang.String psId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ps models where psId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psId the ps ID
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsId(
        java.lang.String psId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ps models where psId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psId the ps ID
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsId(
        java.lang.String psId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps model in the ordered set where psId = &#63;.
    *
    * @param psId the ps ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel findBypsId_First(java.lang.String psId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps model in the ordered set where psId = &#63;.
    *
    * @param psId the ps ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel fetchBypsId_First(java.lang.String psId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps model in the ordered set where psId = &#63;.
    *
    * @param psId the ps ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel findBypsId_Last(java.lang.String psId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps model in the ordered set where psId = &#63;.
    *
    * @param psId the ps ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel fetchBypsId_Last(java.lang.String psId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps models before and after the current ps model in the ordered set where psId = &#63;.
    *
    * @param psModelSid the primary key of the current ps model
    * @param psId the ps ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel[] findBypsId_PrevAndNext(int psModelSid,
        java.lang.String psId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ps models where psId = &#63; from the database.
    *
    * @param psId the ps ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBypsId(java.lang.String psId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ps models where psId = &#63;.
    *
    * @param psId the ps ID
    * @return the number of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public int countBypsId(java.lang.String psId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ps models where psNo = &#63;.
    *
    * @param psNo the ps no
    * @return the matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsNo(
        java.lang.String psNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ps models where psNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psNo the ps no
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsNo(
        java.lang.String psNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ps models where psNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psNo the ps no
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsNo(
        java.lang.String psNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps model in the ordered set where psNo = &#63;.
    *
    * @param psNo the ps no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel findBypsNo_First(java.lang.String psNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps model in the ordered set where psNo = &#63;.
    *
    * @param psNo the ps no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel fetchBypsNo_First(java.lang.String psNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps model in the ordered set where psNo = &#63;.
    *
    * @param psNo the ps no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel findBypsNo_Last(java.lang.String psNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps model in the ordered set where psNo = &#63;.
    *
    * @param psNo the ps no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel fetchBypsNo_Last(java.lang.String psNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps models before and after the current ps model in the ordered set where psNo = &#63;.
    *
    * @param psModelSid the primary key of the current ps model
    * @param psNo the ps no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel[] findBypsNo_PrevAndNext(int psModelSid,
        java.lang.String psNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ps models where psNo = &#63; from the database.
    *
    * @param psNo the ps no
    * @throws SystemException if a system exception occurred
    */
    public void removeBypsNo(java.lang.String psNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ps models where psNo = &#63;.
    *
    * @param psNo the ps no
    * @return the number of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public int countBypsNo(java.lang.String psNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ps models where psName = &#63;.
    *
    * @param psName the ps name
    * @return the matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsName(
        java.lang.String psName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ps models where psName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psName the ps name
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsName(
        java.lang.String psName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ps models where psName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psName the ps name
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsName(
        java.lang.String psName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps model in the ordered set where psName = &#63;.
    *
    * @param psName the ps name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel findBypsName_First(
        java.lang.String psName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps model in the ordered set where psName = &#63;.
    *
    * @param psName the ps name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel fetchBypsName_First(
        java.lang.String psName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps model in the ordered set where psName = &#63;.
    *
    * @param psName the ps name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel findBypsName_Last(
        java.lang.String psName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps model in the ordered set where psName = &#63;.
    *
    * @param psName the ps name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel fetchBypsName_Last(
        java.lang.String psName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps models before and after the current ps model in the ordered set where psName = &#63;.
    *
    * @param psModelSid the primary key of the current ps model
    * @param psName the ps name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel[] findBypsName_PrevAndNext(
        int psModelSid, java.lang.String psName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ps models where psName = &#63; from the database.
    *
    * @param psName the ps name
    * @throws SystemException if a system exception occurred
    */
    public void removeBypsName(java.lang.String psName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ps models where psName = &#63;.
    *
    * @param psName the ps name
    * @return the number of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public int countBypsName(java.lang.String psName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ps models where psType = &#63;.
    *
    * @param psType the ps type
    * @return the matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsType(int psType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ps models where psType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psType the ps type
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsType(int psType,
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ps models where psType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psType the ps type
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsType(int psType,
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps model in the ordered set where psType = &#63;.
    *
    * @param psType the ps type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel findBypsType_First(int psType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps model in the ordered set where psType = &#63;.
    *
    * @param psType the ps type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel fetchBypsType_First(int psType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps model in the ordered set where psType = &#63;.
    *
    * @param psType the ps type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel findBypsType_Last(int psType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps model in the ordered set where psType = &#63;.
    *
    * @param psType the ps type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel fetchBypsType_Last(int psType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps models before and after the current ps model in the ordered set where psType = &#63;.
    *
    * @param psModelSid the primary key of the current ps model
    * @param psType the ps type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel[] findBypsType_PrevAndNext(
        int psModelSid, int psType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ps models where psType = &#63; from the database.
    *
    * @param psType the ps type
    * @throws SystemException if a system exception occurred
    */
    public void removeBypsType(int psType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ps models where psType = &#63;.
    *
    * @param psType the ps type
    * @return the number of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public int countBypsType(int psType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ps models where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @return the matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsStatus(
        int psStatus) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ps models where psStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psStatus the ps status
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsStatus(
        int psStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ps models where psStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psStatus the ps status
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findBypsStatus(
        int psStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps model in the ordered set where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel findBypsStatus_First(int psStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps model in the ordered set where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel fetchBypsStatus_First(int psStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps model in the ordered set where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel findBypsStatus_Last(int psStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps model in the ordered set where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel fetchBypsStatus_Last(int psStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps models before and after the current ps model in the ordered set where psStatus = &#63;.
    *
    * @param psModelSid the primary key of the current ps model
    * @param psStatus the ps status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel[] findBypsStatus_PrevAndNext(
        int psModelSid, int psStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ps models where psStatus = &#63; from the database.
    *
    * @param psStatus the ps status
    * @throws SystemException if a system exception occurred
    */
    public void removeBypsStatus(int psStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ps models where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @return the number of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public int countBypsStatus(int psStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the ps model in the entity cache if it is enabled.
    *
    * @param psModel the ps model
    */
    public void cacheResult(com.stpl.app.model.PsModel psModel);

    /**
    * Caches the ps models in the entity cache if it is enabled.
    *
    * @param psModels the ps models
    */
    public void cacheResult(java.util.List<com.stpl.app.model.PsModel> psModels);

    /**
    * Creates a new ps model with the primary key. Does not add the ps model to the database.
    *
    * @param psModelSid the primary key for the new ps model
    * @return the new ps model
    */
    public com.stpl.app.model.PsModel create(int psModelSid);

    /**
    * Removes the ps model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psModelSid the primary key of the ps model
    * @return the ps model that was removed
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel remove(int psModelSid)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.PsModel updateImpl(
        com.stpl.app.model.PsModel psModel)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps model with the primary key or throws a {@link com.stpl.app.NoSuchPsModelException} if it could not be found.
    *
    * @param psModelSid the primary key of the ps model
    * @return the ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel findByPrimaryKey(int psModelSid)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps model with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param psModelSid the primary key of the ps model
    * @return the ps model, or <code>null</code> if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsModel fetchByPrimaryKey(int psModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ps models.
    *
    * @return the ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ps models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findAll(int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ps models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ps models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsModel> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ps models from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ps models.
    *
    * @return the number of ps models
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
