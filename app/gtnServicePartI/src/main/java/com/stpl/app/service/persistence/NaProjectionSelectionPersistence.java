package com.stpl.app.service.persistence;

import com.stpl.app.model.NaProjectionSelection;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the na projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NaProjectionSelectionPersistenceImpl
 * @see NaProjectionSelectionUtil
 * @generated
 */
public interface NaProjectionSelectionPersistence extends BasePersistence<NaProjectionSelection> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NaProjectionSelectionUtil} to access the na projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the na projection selection in the entity cache if it is enabled.
    *
    * @param naProjectionSelection the na projection selection
    */
    public void cacheResult(
        com.stpl.app.model.NaProjectionSelection naProjectionSelection);

    /**
    * Caches the na projection selections in the entity cache if it is enabled.
    *
    * @param naProjectionSelections the na projection selections
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NaProjectionSelection> naProjectionSelections);

    /**
    * Creates a new na projection selection with the primary key. Does not add the na projection selection to the database.
    *
    * @param naProjectionSelectionSid the primary key for the new na projection selection
    * @return the new na projection selection
    */
    public com.stpl.app.model.NaProjectionSelection create(
        int naProjectionSelectionSid);

    /**
    * Removes the na projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param naProjectionSelectionSid the primary key of the na projection selection
    * @return the na projection selection that was removed
    * @throws com.stpl.app.NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NaProjectionSelection remove(
        int naProjectionSelectionSid)
        throws com.stpl.app.NoSuchNaProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NaProjectionSelection updateImpl(
        com.stpl.app.model.NaProjectionSelection naProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the na projection selection with the primary key or throws a {@link com.stpl.app.NoSuchNaProjectionSelectionException} if it could not be found.
    *
    * @param naProjectionSelectionSid the primary key of the na projection selection
    * @return the na projection selection
    * @throws com.stpl.app.NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NaProjectionSelection findByPrimaryKey(
        int naProjectionSelectionSid)
        throws com.stpl.app.NoSuchNaProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the na projection selection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param naProjectionSelectionSid the primary key of the na projection selection
    * @return the na projection selection, or <code>null</code> if a na projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NaProjectionSelection fetchByPrimaryKey(
        int naProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the na projection selections.
    *
    * @return the na projection selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NaProjectionSelection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the na projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of na projection selections
    * @param end the upper bound of the range of na projection selections (not inclusive)
    * @return the range of na projection selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NaProjectionSelection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the na projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of na projection selections
    * @param end the upper bound of the range of na projection selections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of na projection selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NaProjectionSelection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the na projection selections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of na projection selections.
    *
    * @return the number of na projection selections
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
