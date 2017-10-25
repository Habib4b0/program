package com.stpl.app.service.persistence;

import com.stpl.app.model.ChProjectionSelection;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ch projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChProjectionSelectionPersistenceImpl
 * @see ChProjectionSelectionUtil
 * @generated
 */
public interface ChProjectionSelectionPersistence extends BasePersistence<ChProjectionSelection> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ChProjectionSelectionUtil} to access the ch projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ch projection selection in the entity cache if it is enabled.
    *
    * @param chProjectionSelection the ch projection selection
    */
    public void cacheResult(
        com.stpl.app.model.ChProjectionSelection chProjectionSelection);

    /**
    * Caches the ch projection selections in the entity cache if it is enabled.
    *
    * @param chProjectionSelections the ch projection selections
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ChProjectionSelection> chProjectionSelections);

    /**
    * Creates a new ch projection selection with the primary key. Does not add the ch projection selection to the database.
    *
    * @param chProjectionSelectionSid the primary key for the new ch projection selection
    * @return the new ch projection selection
    */
    public com.stpl.app.model.ChProjectionSelection create(
        int chProjectionSelectionSid);

    /**
    * Removes the ch projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chProjectionSelectionSid the primary key of the ch projection selection
    * @return the ch projection selection that was removed
    * @throws com.stpl.app.NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChProjectionSelection remove(
        int chProjectionSelectionSid)
        throws com.stpl.app.NoSuchChProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ChProjectionSelection updateImpl(
        com.stpl.app.model.ChProjectionSelection chProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch projection selection with the primary key or throws a {@link com.stpl.app.NoSuchChProjectionSelectionException} if it could not be found.
    *
    * @param chProjectionSelectionSid the primary key of the ch projection selection
    * @return the ch projection selection
    * @throws com.stpl.app.NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChProjectionSelection findByPrimaryKey(
        int chProjectionSelectionSid)
        throws com.stpl.app.NoSuchChProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch projection selection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param chProjectionSelectionSid the primary key of the ch projection selection
    * @return the ch projection selection, or <code>null</code> if a ch projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChProjectionSelection fetchByPrimaryKey(
        int chProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ch projection selections.
    *
    * @return the ch projection selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChProjectionSelection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ch projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch projection selections
    * @param end the upper bound of the range of ch projection selections (not inclusive)
    * @return the range of ch projection selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChProjectionSelection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ch projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch projection selections
    * @param end the upper bound of the range of ch projection selections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch projection selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChProjectionSelection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ch projection selections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ch projection selections.
    *
    * @return the number of ch projection selections
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
