package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AcFdAdjustmentSelection;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ac fd adjustment selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcFdAdjustmentSelectionPersistenceImpl
 * @see AcFdAdjustmentSelectionUtil
 * @generated
 */
public interface AcFdAdjustmentSelectionPersistence extends BasePersistence<AcFdAdjustmentSelection> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AcFdAdjustmentSelectionUtil} to access the ac fd adjustment selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ac fd adjustment selection in the entity cache if it is enabled.
    *
    * @param acFdAdjustmentSelection the ac fd adjustment selection
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.AcFdAdjustmentSelection acFdAdjustmentSelection);

    /**
    * Caches the ac fd adjustment selections in the entity cache if it is enabled.
    *
    * @param acFdAdjustmentSelections the ac fd adjustment selections
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AcFdAdjustmentSelection> acFdAdjustmentSelections);

    /**
    * Creates a new ac fd adjustment selection with the primary key. Does not add the ac fd adjustment selection to the database.
    *
    * @param accClosureMasterSid the primary key for the new ac fd adjustment selection
    * @return the new ac fd adjustment selection
    */
    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection create(
        int accClosureMasterSid);

    /**
    * Removes the ac fd adjustment selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureMasterSid the primary key of the ac fd adjustment selection
    * @return the ac fd adjustment selection that was removed
    * @throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection remove(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection updateImpl(
        com.stpl.app.parttwo.model.AcFdAdjustmentSelection acFdAdjustmentSelection)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ac fd adjustment selection with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException} if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the ac fd adjustment selection
    * @return the ac fd adjustment selection
    * @throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection findByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ac fd adjustment selection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the ac fd adjustment selection
    * @return the ac fd adjustment selection, or <code>null</code> if a ac fd adjustment selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection fetchByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ac fd adjustment selections.
    *
    * @return the ac fd adjustment selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AcFdAdjustmentSelection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ac fd adjustment selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac fd adjustment selections
    * @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
    * @return the range of ac fd adjustment selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AcFdAdjustmentSelection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ac fd adjustment selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac fd adjustment selections
    * @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ac fd adjustment selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AcFdAdjustmentSelection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ac fd adjustment selections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ac fd adjustment selections.
    *
    * @return the number of ac fd adjustment selections
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
