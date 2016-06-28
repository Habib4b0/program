package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastingMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the forecasting master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingMasterPersistenceImpl
 * @see ForecastingMasterUtil
 * @generated
 */
public interface ForecastingMasterPersistence extends BasePersistence<ForecastingMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ForecastingMasterUtil} to access the forecasting master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the forecasting masters where forecastYear = &#63;.
    *
    * @param forecastYear the forecast year
    * @return the matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastYear(
        java.lang.String forecastYear)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the forecasting masters where forecastYear = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param forecastYear the forecast year
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @return the range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastYear(
        java.lang.String forecastYear, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the forecasting masters where forecastYear = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param forecastYear the forecast year
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastYear(
        java.lang.String forecastYear, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where forecastYear = &#63;.
    *
    * @param forecastYear the forecast year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByForecastYear_First(
        java.lang.String forecastYear,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where forecastYear = &#63;.
    *
    * @param forecastYear the forecast year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByForecastYear_First(
        java.lang.String forecastYear,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where forecastYear = &#63;.
    *
    * @param forecastYear the forecast year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByForecastYear_Last(
        java.lang.String forecastYear,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where forecastYear = &#63;.
    *
    * @param forecastYear the forecast year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByForecastYear_Last(
        java.lang.String forecastYear,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastYear = &#63;.
    *
    * @param forecastMasterSid the primary key of the current forecasting master
    * @param forecastYear the forecast year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster[] findByForecastYear_PrevAndNext(
        int forecastMasterSid, java.lang.String forecastYear,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the forecasting masters where forecastYear = &#63; from the database.
    *
    * @param forecastYear the forecast year
    * @throws SystemException if a system exception occurred
    */
    public void removeByForecastYear(java.lang.String forecastYear)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of forecasting masters where forecastYear = &#63;.
    *
    * @param forecastYear the forecast year
    * @return the number of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public int countByForecastYear(java.lang.String forecastYear)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the forecasting masters where forecastMonth = &#63;.
    *
    * @param forecastMonth the forecast month
    * @return the matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastMonth(
        java.lang.String forecastMonth)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the forecasting masters where forecastMonth = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param forecastMonth the forecast month
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @return the range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastMonth(
        java.lang.String forecastMonth, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the forecasting masters where forecastMonth = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param forecastMonth the forecast month
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastMonth(
        java.lang.String forecastMonth, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where forecastMonth = &#63;.
    *
    * @param forecastMonth the forecast month
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByForecastMonth_First(
        java.lang.String forecastMonth,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where forecastMonth = &#63;.
    *
    * @param forecastMonth the forecast month
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByForecastMonth_First(
        java.lang.String forecastMonth,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where forecastMonth = &#63;.
    *
    * @param forecastMonth the forecast month
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByForecastMonth_Last(
        java.lang.String forecastMonth,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where forecastMonth = &#63;.
    *
    * @param forecastMonth the forecast month
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByForecastMonth_Last(
        java.lang.String forecastMonth,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastMonth = &#63;.
    *
    * @param forecastMasterSid the primary key of the current forecasting master
    * @param forecastMonth the forecast month
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster[] findByForecastMonth_PrevAndNext(
        int forecastMasterSid, java.lang.String forecastMonth,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the forecasting masters where forecastMonth = &#63; from the database.
    *
    * @param forecastMonth the forecast month
    * @throws SystemException if a system exception occurred
    */
    public void removeByForecastMonth(java.lang.String forecastMonth)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of forecasting masters where forecastMonth = &#63;.
    *
    * @param forecastMonth the forecast month
    * @return the number of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public int countByForecastMonth(java.lang.String forecastMonth)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the forecasting masters where country = &#63;.
    *
    * @param country the country
    * @return the matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByCountry(
        java.lang.String country)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the forecasting masters where country = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param country the country
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @return the range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByCountry(
        java.lang.String country, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the forecasting masters where country = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param country the country
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByCountry(
        java.lang.String country, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where country = &#63;.
    *
    * @param country the country
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByCountry_First(
        java.lang.String country,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where country = &#63;.
    *
    * @param country the country
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByCountry_First(
        java.lang.String country,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where country = &#63;.
    *
    * @param country the country
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByCountry_Last(
        java.lang.String country,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where country = &#63;.
    *
    * @param country the country
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByCountry_Last(
        java.lang.String country,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting masters before and after the current forecasting master in the ordered set where country = &#63;.
    *
    * @param forecastMasterSid the primary key of the current forecasting master
    * @param country the country
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster[] findByCountry_PrevAndNext(
        int forecastMasterSid, java.lang.String country,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the forecasting masters where country = &#63; from the database.
    *
    * @param country the country
    * @throws SystemException if a system exception occurred
    */
    public void removeByCountry(java.lang.String country)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of forecasting masters where country = &#63;.
    *
    * @param country the country
    * @return the number of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCountry(java.lang.String country)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the forecasting masters where ndc = &#63;.
    *
    * @param ndc the ndc
    * @return the matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByNdc(
        java.lang.String ndc)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the forecasting masters where ndc = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ndc the ndc
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @return the range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByNdc(
        java.lang.String ndc, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the forecasting masters where ndc = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ndc the ndc
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByNdc(
        java.lang.String ndc, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where ndc = &#63;.
    *
    * @param ndc the ndc
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByNdc_First(
        java.lang.String ndc,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where ndc = &#63;.
    *
    * @param ndc the ndc
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByNdc_First(
        java.lang.String ndc,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where ndc = &#63;.
    *
    * @param ndc the ndc
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByNdc_Last(
        java.lang.String ndc,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where ndc = &#63;.
    *
    * @param ndc the ndc
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByNdc_Last(
        java.lang.String ndc,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting masters before and after the current forecasting master in the ordered set where ndc = &#63;.
    *
    * @param forecastMasterSid the primary key of the current forecasting master
    * @param ndc the ndc
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster[] findByNdc_PrevAndNext(
        int forecastMasterSid, java.lang.String ndc,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the forecasting masters where ndc = &#63; from the database.
    *
    * @param ndc the ndc
    * @throws SystemException if a system exception occurred
    */
    public void removeByNdc(java.lang.String ndc)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of forecasting masters where ndc = &#63;.
    *
    * @param ndc the ndc
    * @return the number of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public int countByNdc(java.lang.String ndc)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the forecasting masters where forecastStartDate = &#63;.
    *
    * @param forecastStartDate the forecast start date
    * @return the matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastStartDate(
        java.util.Date forecastStartDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the forecasting masters where forecastStartDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param forecastStartDate the forecast start date
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @return the range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastStartDate(
        java.util.Date forecastStartDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the forecasting masters where forecastStartDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param forecastStartDate the forecast start date
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastStartDate(
        java.util.Date forecastStartDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where forecastStartDate = &#63;.
    *
    * @param forecastStartDate the forecast start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByForecastStartDate_First(
        java.util.Date forecastStartDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where forecastStartDate = &#63;.
    *
    * @param forecastStartDate the forecast start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByForecastStartDate_First(
        java.util.Date forecastStartDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where forecastStartDate = &#63;.
    *
    * @param forecastStartDate the forecast start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByForecastStartDate_Last(
        java.util.Date forecastStartDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where forecastStartDate = &#63;.
    *
    * @param forecastStartDate the forecast start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByForecastStartDate_Last(
        java.util.Date forecastStartDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastStartDate = &#63;.
    *
    * @param forecastMasterSid the primary key of the current forecasting master
    * @param forecastStartDate the forecast start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster[] findByForecastStartDate_PrevAndNext(
        int forecastMasterSid, java.util.Date forecastStartDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the forecasting masters where forecastStartDate = &#63; from the database.
    *
    * @param forecastStartDate the forecast start date
    * @throws SystemException if a system exception occurred
    */
    public void removeByForecastStartDate(java.util.Date forecastStartDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of forecasting masters where forecastStartDate = &#63;.
    *
    * @param forecastStartDate the forecast start date
    * @return the number of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public int countByForecastStartDate(java.util.Date forecastStartDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the forecasting masters where createdDate = &#63;.
    *
    * @param createdDate the created date
    * @return the matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByCreatedDate(
        java.util.Date createdDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the forecasting masters where createdDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param createdDate the created date
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @return the range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByCreatedDate(
        java.util.Date createdDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the forecasting masters where createdDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param createdDate the created date
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByCreatedDate(
        java.util.Date createdDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where createdDate = &#63;.
    *
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByCreatedDate_First(
        java.util.Date createdDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where createdDate = &#63;.
    *
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByCreatedDate_First(
        java.util.Date createdDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where createdDate = &#63;.
    *
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByCreatedDate_Last(
        java.util.Date createdDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where createdDate = &#63;.
    *
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByCreatedDate_Last(
        java.util.Date createdDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting masters before and after the current forecasting master in the ordered set where createdDate = &#63;.
    *
    * @param forecastMasterSid the primary key of the current forecasting master
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster[] findByCreatedDate_PrevAndNext(
        int forecastMasterSid, java.util.Date createdDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the forecasting masters where createdDate = &#63; from the database.
    *
    * @param createdDate the created date
    * @throws SystemException if a system exception occurred
    */
    public void removeByCreatedDate(java.util.Date createdDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of forecasting masters where createdDate = &#63;.
    *
    * @param createdDate the created date
    * @return the number of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCreatedDate(java.util.Date createdDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
    *
    * @param forecastYear the forecast year
    * @param forecastMonth the forecast month
    * @param country the country
    * @param forecastStartDate the forecast start date
    * @param createdDate the created date
    * @return the matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastingSalesUnique(
        java.lang.String forecastYear, java.lang.String forecastMonth,
        java.lang.String country, java.util.Date forecastStartDate,
        java.util.Date createdDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param forecastYear the forecast year
    * @param forecastMonth the forecast month
    * @param country the country
    * @param forecastStartDate the forecast start date
    * @param createdDate the created date
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @return the range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastingSalesUnique(
        java.lang.String forecastYear, java.lang.String forecastMonth,
        java.lang.String country, java.util.Date forecastStartDate,
        java.util.Date createdDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param forecastYear the forecast year
    * @param forecastMonth the forecast month
    * @param country the country
    * @param forecastStartDate the forecast start date
    * @param createdDate the created date
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findByForecastingSalesUnique(
        java.lang.String forecastYear, java.lang.String forecastMonth,
        java.lang.String country, java.util.Date forecastStartDate,
        java.util.Date createdDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
    *
    * @param forecastYear the forecast year
    * @param forecastMonth the forecast month
    * @param country the country
    * @param forecastStartDate the forecast start date
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByForecastingSalesUnique_First(
        java.lang.String forecastYear, java.lang.String forecastMonth,
        java.lang.String country, java.util.Date forecastStartDate,
        java.util.Date createdDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
    *
    * @param forecastYear the forecast year
    * @param forecastMonth the forecast month
    * @param country the country
    * @param forecastStartDate the forecast start date
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByForecastingSalesUnique_First(
        java.lang.String forecastYear, java.lang.String forecastMonth,
        java.lang.String country, java.util.Date forecastStartDate,
        java.util.Date createdDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
    *
    * @param forecastYear the forecast year
    * @param forecastMonth the forecast month
    * @param country the country
    * @param forecastStartDate the forecast start date
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByForecastingSalesUnique_Last(
        java.lang.String forecastYear, java.lang.String forecastMonth,
        java.lang.String country, java.util.Date forecastStartDate,
        java.util.Date createdDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
    *
    * @param forecastYear the forecast year
    * @param forecastMonth the forecast month
    * @param country the country
    * @param forecastStartDate the forecast start date
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByForecastingSalesUnique_Last(
        java.lang.String forecastYear, java.lang.String forecastMonth,
        java.lang.String country, java.util.Date forecastStartDate,
        java.util.Date createdDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
    *
    * @param forecastMasterSid the primary key of the current forecasting master
    * @param forecastYear the forecast year
    * @param forecastMonth the forecast month
    * @param country the country
    * @param forecastStartDate the forecast start date
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster[] findByForecastingSalesUnique_PrevAndNext(
        int forecastMasterSid, java.lang.String forecastYear,
        java.lang.String forecastMonth, java.lang.String country,
        java.util.Date forecastStartDate, java.util.Date createdDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63; from the database.
    *
    * @param forecastYear the forecast year
    * @param forecastMonth the forecast month
    * @param country the country
    * @param forecastStartDate the forecast start date
    * @param createdDate the created date
    * @throws SystemException if a system exception occurred
    */
    public void removeByForecastingSalesUnique(java.lang.String forecastYear,
        java.lang.String forecastMonth, java.lang.String country,
        java.util.Date forecastStartDate, java.util.Date createdDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
    *
    * @param forecastYear the forecast year
    * @param forecastMonth the forecast month
    * @param country the country
    * @param forecastStartDate the forecast start date
    * @param createdDate the created date
    * @return the number of matching forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public int countByForecastingSalesUnique(java.lang.String forecastYear,
        java.lang.String forecastMonth, java.lang.String country,
        java.util.Date forecastStartDate, java.util.Date createdDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the forecasting master in the entity cache if it is enabled.
    *
    * @param forecastingMaster the forecasting master
    */
    public void cacheResult(
        com.stpl.app.model.ForecastingMaster forecastingMaster);

    /**
    * Caches the forecasting masters in the entity cache if it is enabled.
    *
    * @param forecastingMasters the forecasting masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ForecastingMaster> forecastingMasters);

    /**
    * Creates a new forecasting master with the primary key. Does not add the forecasting master to the database.
    *
    * @param forecastMasterSid the primary key for the new forecasting master
    * @return the new forecasting master
    */
    public com.stpl.app.model.ForecastingMaster create(int forecastMasterSid);

    /**
    * Removes the forecasting master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param forecastMasterSid the primary key of the forecasting master
    * @return the forecasting master that was removed
    * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster remove(int forecastMasterSid)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ForecastingMaster updateImpl(
        com.stpl.app.model.ForecastingMaster forecastingMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting master with the primary key or throws a {@link com.stpl.app.NoSuchForecastingMasterException} if it could not be found.
    *
    * @param forecastMasterSid the primary key of the forecasting master
    * @return the forecasting master
    * @throws com.stpl.app.NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster findByPrimaryKey(
        int forecastMasterSid)
        throws com.stpl.app.NoSuchForecastingMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param forecastMasterSid the primary key of the forecasting master
    * @return the forecasting master, or <code>null</code> if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingMaster fetchByPrimaryKey(
        int forecastMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the forecasting masters.
    *
    * @return the forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the forecasting masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @return the range of forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the forecasting masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the forecasting masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of forecasting masters.
    *
    * @return the number of forecasting masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
