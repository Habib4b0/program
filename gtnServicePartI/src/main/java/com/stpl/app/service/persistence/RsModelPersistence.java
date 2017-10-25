package com.stpl.app.service.persistence;

import com.stpl.app.model.RsModel;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the rs model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsModelPersistenceImpl
 * @see RsModelUtil
 * @generated
 */
public interface RsModelPersistence extends BasePersistence<RsModel> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link RsModelUtil} to access the rs model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the rs models where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleId(
        java.lang.String rsId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rs models where rsId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsId the rs ID
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleId(
        java.lang.String rsId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rs models where rsId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsId the rs ID
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleId(
        java.lang.String rsId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateScheduleId_First(
        java.lang.String rsId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateScheduleId_First(
        java.lang.String rsId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateScheduleId_Last(
        java.lang.String rsId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateScheduleId_Last(
        java.lang.String rsId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rsId = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rsId the rs ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel[] findByRebateScheduleId_PrevAndNext(
        int rsModelSid, java.lang.String rsId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rs models where rsId = &#63; from the database.
    *
    * @param rsId the rs ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByRebateScheduleId(java.lang.String rsId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rs models where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public int countByRebateScheduleId(java.lang.String rsId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rs models where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleNo(
        java.lang.String rsNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rs models where rsNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsNo the rs no
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleNo(
        java.lang.String rsNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rs models where rsNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsNo the rs no
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleNo(
        java.lang.String rsNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateScheduleNo_First(
        java.lang.String rsNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateScheduleNo_First(
        java.lang.String rsNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateScheduleNo_Last(
        java.lang.String rsNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateScheduleNo_Last(
        java.lang.String rsNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rsNo = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rsNo the rs no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel[] findByRebateScheduleNo_PrevAndNext(
        int rsModelSid, java.lang.String rsNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rs models where rsNo = &#63; from the database.
    *
    * @param rsNo the rs no
    * @throws SystemException if a system exception occurred
    */
    public void removeByRebateScheduleNo(java.lang.String rsNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rs models where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public int countByRebateScheduleNo(java.lang.String rsNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rs models where rsName = &#63;.
    *
    * @param rsName the rs name
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleName(
        java.lang.String rsName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rs models where rsName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsName the rs name
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleName(
        java.lang.String rsName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rs models where rsName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsName the rs name
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleName(
        java.lang.String rsName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rsName = &#63;.
    *
    * @param rsName the rs name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateScheduleName_First(
        java.lang.String rsName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rsName = &#63;.
    *
    * @param rsName the rs name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateScheduleName_First(
        java.lang.String rsName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rsName = &#63;.
    *
    * @param rsName the rs name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateScheduleName_Last(
        java.lang.String rsName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rsName = &#63;.
    *
    * @param rsName the rs name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateScheduleName_Last(
        java.lang.String rsName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rsName = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rsName the rs name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel[] findByRebateScheduleName_PrevAndNext(
        int rsModelSid, java.lang.String rsName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rs models where rsName = &#63; from the database.
    *
    * @param rsName the rs name
    * @throws SystemException if a system exception occurred
    */
    public void removeByRebateScheduleName(java.lang.String rsName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rs models where rsName = &#63;.
    *
    * @param rsName the rs name
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public int countByRebateScheduleName(java.lang.String rsName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rs models where rsType = &#63;.
    *
    * @param rsType the rs type
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleType(
        int rsType) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rs models where rsType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsType the rs type
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleType(
        int rsType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rs models where rsType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsType the rs type
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleType(
        int rsType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rsType = &#63;.
    *
    * @param rsType the rs type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateScheduleType_First(
        int rsType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rsType = &#63;.
    *
    * @param rsType the rs type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateScheduleType_First(
        int rsType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rsType = &#63;.
    *
    * @param rsType the rs type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateScheduleType_Last(
        int rsType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rsType = &#63;.
    *
    * @param rsType the rs type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateScheduleType_Last(
        int rsType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rsType = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rsType the rs type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel[] findByRebateScheduleType_PrevAndNext(
        int rsModelSid, int rsType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rs models where rsType = &#63; from the database.
    *
    * @param rsType the rs type
    * @throws SystemException if a system exception occurred
    */
    public void removeByRebateScheduleType(int rsType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rs models where rsType = &#63;.
    *
    * @param rsType the rs type
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public int countByRebateScheduleType(int rsType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rs models where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleStatus(
        int rsStatus) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rs models where rsStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsStatus the rs status
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleStatus(
        int rsStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rs models where rsStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsStatus the rs status
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleStatus(
        int rsStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateScheduleStatus_First(
        int rsStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateScheduleStatus_First(
        int rsStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateScheduleStatus_Last(
        int rsStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateScheduleStatus_Last(
        int rsStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rsStatus = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rsStatus the rs status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel[] findByRebateScheduleStatus_PrevAndNext(
        int rsModelSid, int rsStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rs models where rsStatus = &#63; from the database.
    *
    * @param rsStatus the rs status
    * @throws SystemException if a system exception occurred
    */
    public void removeByRebateScheduleStatus(int rsStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rs models where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public int countByRebateScheduleStatus(int rsStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rs models where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateProgramType(
        int rebateProgramType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rs models where rebateProgramType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebateProgramType the rebate program type
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateProgramType(
        int rebateProgramType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rs models where rebateProgramType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebateProgramType the rebate program type
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findByRebateProgramType(
        int rebateProgramType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateProgramType_First(
        int rebateProgramType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rs model in the ordered set where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateProgramType_First(
        int rebateProgramType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByRebateProgramType_Last(
        int rebateProgramType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rs model in the ordered set where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByRebateProgramType_Last(
        int rebateProgramType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rebateProgramType = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rebateProgramType the rebate program type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel[] findByRebateProgramType_PrevAndNext(
        int rsModelSid, int rebateProgramType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rs models where rebateProgramType = &#63; from the database.
    *
    * @param rebateProgramType the rebate program type
    * @throws SystemException if a system exception occurred
    */
    public void removeByRebateProgramType(int rebateProgramType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rs models where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public int countByRebateProgramType(int rebateProgramType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the rs model in the entity cache if it is enabled.
    *
    * @param rsModel the rs model
    */
    public void cacheResult(com.stpl.app.model.RsModel rsModel);

    /**
    * Caches the rs models in the entity cache if it is enabled.
    *
    * @param rsModels the rs models
    */
    public void cacheResult(java.util.List<com.stpl.app.model.RsModel> rsModels);

    /**
    * Creates a new rs model with the primary key. Does not add the rs model to the database.
    *
    * @param rsModelSid the primary key for the new rs model
    * @return the new rs model
    */
    public com.stpl.app.model.RsModel create(int rsModelSid);

    /**
    * Removes the rs model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model that was removed
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel remove(int rsModelSid)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.RsModel updateImpl(
        com.stpl.app.model.RsModel rsModel)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs model with the primary key or throws a {@link com.stpl.app.NoSuchRsModelException} if it could not be found.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel findByPrimaryKey(int rsModelSid)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs model with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model, or <code>null</code> if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsModel fetchByPrimaryKey(int rsModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rs models.
    *
    * @return the rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.RsModel> findAll(int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rs models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rs models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsModel> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rs models from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rs models.
    *
    * @return the number of rs models
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
