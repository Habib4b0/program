package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchImtdRsDetailsException;
import com.stpl.app.model.ImtdRsDetails;
import com.stpl.app.model.impl.ImtdRsDetailsImpl;
import com.stpl.app.model.impl.ImtdRsDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdRsDetailsPersistence;

import com.stpl.portal.kernel.cache.CacheRegistryUtil;
import com.stpl.portal.kernel.dao.orm.EntityCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderPath;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.QueryPos;
import com.stpl.portal.kernel.dao.orm.QueryUtil;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.log.Log;
import com.stpl.portal.kernel.log.LogFactoryUtil;
import com.stpl.portal.kernel.util.CalendarUtil;
import com.stpl.portal.kernel.util.GetterUtil;
import com.stpl.portal.kernel.util.InstanceFactory;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.PropsKeys;
import com.stpl.portal.kernel.util.PropsUtil;
import com.stpl.portal.kernel.util.SetUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.kernel.util.StringUtil;
import com.stpl.portal.kernel.util.UnmodifiableList;
import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.model.ModelListener;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the imtd rs details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsDetailsPersistence
 * @see ImtdRsDetailsUtil
 * @generated
 */
public class ImtdRsDetailsPersistenceImpl extends BasePersistenceImpl<ImtdRsDetails>
    implements ImtdRsDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImtdRsDetailsUtil} to access the imtd rs details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImtdRsDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdRsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdRsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEMPRSSEARCH =
        new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdRsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByTempRsSearch",
            new String[] {
                String.class.getName(), String.class.getName(),
                Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPRSSEARCH =
        new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdRsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByTempRsSearch",
            new String[] {
                String.class.getName(), String.class.getName(),
                Date.class.getName()
            },
            ImtdRsDetailsModelImpl.USERSSID_COLUMN_BITMASK |
            ImtdRsDetailsModelImpl.SESSIONID_COLUMN_BITMASK |
            ImtdRsDetailsModelImpl.IMTDCREATEDDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TEMPRSSEARCH = new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTempRsSearch",
            new String[] {
                String.class.getName(), String.class.getName(),
                Date.class.getName()
            });
    private static final String _FINDER_COLUMN_TEMPRSSEARCH_USERSSID_1 = "imtdRsDetails.usersSid IS NULL AND ";
    private static final String _FINDER_COLUMN_TEMPRSSEARCH_USERSSID_2 = "imtdRsDetails.usersSid = ? AND ";
    private static final String _FINDER_COLUMN_TEMPRSSEARCH_USERSSID_3 = "(imtdRsDetails.usersSid IS NULL OR imtdRsDetails.usersSid = '') AND ";
    private static final String _FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_1 = "imtdRsDetails.sessionId IS NULL AND ";
    private static final String _FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_2 = "imtdRsDetails.sessionId = ? AND ";
    private static final String _FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_3 = "(imtdRsDetails.sessionId IS NULL OR imtdRsDetails.sessionId = '') AND ";
    private static final String _FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_1 = "imtdRsDetails.imtdCreatedDate IS NULL";
    private static final String _FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_2 = "imtdRsDetails.imtdCreatedDate = ?";
    private static final String _SQL_SELECT_IMTDRSDETAILS = "SELECT imtdRsDetails FROM ImtdRsDetails imtdRsDetails";
    private static final String _SQL_SELECT_IMTDRSDETAILS_WHERE = "SELECT imtdRsDetails FROM ImtdRsDetails imtdRsDetails WHERE ";
    private static final String _SQL_COUNT_IMTDRSDETAILS = "SELECT COUNT(imtdRsDetails) FROM ImtdRsDetails imtdRsDetails";
    private static final String _SQL_COUNT_IMTDRSDETAILS_WHERE = "SELECT COUNT(imtdRsDetails) FROM ImtdRsDetails imtdRsDetails WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "imtdRsDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdRsDetails exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ImtdRsDetails exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImtdRsDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "rsDetailsModifiedDate", "rsDetailsBundleNo", "itemMasterSid",
                "imtdRsDetailsSid", "itemId", "rsDetailsFormulaMethodId",
                "modifiedDate", "createdDate", "createdBy", "usersSid",
                "contractMasterSid", "rsDetailsFormulaId", "imtdCreatedDate",
                "psModelSid", "modifiedBy", "rsDetailsCreatedDate", "itemNo",
                "rsDetailsFormulaName", "udc6", "rsDetailsCreatedBy", "udc5",
                "ifpModelSid", "udc4", "rsDetailsFormulaNo", "checkRecord",
                "rsId", "udc1", "rsDetailsRebateAmount", "udc2",
                "rsDetailsModifiedBy", "udc3", "rebatePlanMasterSid",
                "rsDetailsAttachedDate", "itemRebateEndDate",
                "rsDetailsRebatePlanName", "itemRebateStartDate",
                "rsDetailsFormulaType", "sessionId", "itemName", "operation",
                "cfpModelSid", "rsModelSid", "rsDetailsSid",
                "rsDetailsAttachedStatus", "rsDetailsNetSalesFormulaNo",
                "rsDetailsNetSalesFormulaName", "rsDetailsDeductionCalendarNo",
                "rsDetailsDeductionCalendarName", "deductionCalendarMasterSid",
                "netSalesFormulaMasterSid", "evaluationRule", "netSalesRule",
                "formulaType", "calculationRule", "calculationRuleBundle",
                "evaluationRuleBundle"
            });
    private static ImtdRsDetails _nullImtdRsDetails = new ImtdRsDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImtdRsDetails> toCacheModel() {
                return _nullImtdRsDetailsCacheModel;
            }
        };

    private static CacheModel<ImtdRsDetails> _nullImtdRsDetailsCacheModel = new CacheModel<ImtdRsDetails>() {
            @Override
            public ImtdRsDetails toEntityModel() {
                return _nullImtdRsDetails;
            }
        };

    public ImtdRsDetailsPersistenceImpl() {
        setModelClass(ImtdRsDetails.class);
    }

    /**
     * Returns all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
     *
     * @param usersSid the users sid
     * @param sessionId the session ID
     * @param imtdCreatedDate the imtd created date
     * @return the matching imtd rs detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsDetails> findByTempRsSearch(String usersSid,
        String sessionId, Date imtdCreatedDate) throws SystemException {
        return findByTempRsSearch(usersSid, sessionId, imtdCreatedDate,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param usersSid the users sid
     * @param sessionId the session ID
     * @param imtdCreatedDate the imtd created date
     * @param start the lower bound of the range of imtd rs detailses
     * @param end the upper bound of the range of imtd rs detailses (not inclusive)
     * @return the range of matching imtd rs detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsDetails> findByTempRsSearch(String usersSid,
        String sessionId, Date imtdCreatedDate, int start, int end)
        throws SystemException {
        return findByTempRsSearch(usersSid, sessionId, imtdCreatedDate, start,
            end, null);
    }

    /**
     * Returns an ordered range of all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param usersSid the users sid
     * @param sessionId the session ID
     * @param imtdCreatedDate the imtd created date
     * @param start the lower bound of the range of imtd rs detailses
     * @param end the upper bound of the range of imtd rs detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching imtd rs detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsDetails> findByTempRsSearch(String usersSid,
        String sessionId, Date imtdCreatedDate, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPRSSEARCH;
            finderArgs = new Object[] { usersSid, sessionId, imtdCreatedDate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEMPRSSEARCH;
            finderArgs = new Object[] {
                    usersSid, sessionId, imtdCreatedDate,
                    
                    start, end, orderByComparator
                };
        }

        List<ImtdRsDetails> list = (List<ImtdRsDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ImtdRsDetails imtdRsDetails : list) {
                if (!Validator.equals(usersSid, imtdRsDetails.getUsersSid()) ||
                        !Validator.equals(sessionId,
                            imtdRsDetails.getSessionId()) ||
                        !Validator.equals(imtdCreatedDate,
                            imtdRsDetails.getImtdCreatedDate())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(5 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(5);
            }

            query.append(_SQL_SELECT_IMTDRSDETAILS_WHERE);

            boolean bindUsersSid = false;

            if (usersSid == null) {
                query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_1);
            } else if (usersSid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_3);
            } else {
                bindUsersSid = true;

                query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_2);
            }

            boolean bindSessionId = false;

            if (sessionId == null) {
                query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_1);
            } else if (sessionId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_3);
            } else {
                bindSessionId = true;

                query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_2);
            }

            boolean bindImtdCreatedDate = false;

            if (imtdCreatedDate == null) {
                query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_1);
            } else {
                bindImtdCreatedDate = true;

                query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ImtdRsDetailsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUsersSid) {
                    qPos.add(usersSid);
                }

                if (bindSessionId) {
                    qPos.add(sessionId);
                }

                if (bindImtdCreatedDate) {
                    qPos.add(CalendarUtil.getTimestamp(imtdCreatedDate));
                }

                if (!pagination) {
                    list = (List<ImtdRsDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImtdRsDetails>(list);
                } else {
                    list = (List<ImtdRsDetails>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
     *
     * @param usersSid the users sid
     * @param sessionId the session ID
     * @param imtdCreatedDate the imtd created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching imtd rs details
     * @throws com.stpl.app.NoSuchImtdRsDetailsException if a matching imtd rs details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetails findByTempRsSearch_First(String usersSid,
        String sessionId, Date imtdCreatedDate,
        OrderByComparator orderByComparator)
        throws NoSuchImtdRsDetailsException, SystemException {
        ImtdRsDetails imtdRsDetails = fetchByTempRsSearch_First(usersSid,
                sessionId, imtdCreatedDate, orderByComparator);

        if (imtdRsDetails != null) {
            return imtdRsDetails;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("usersSid=");
        msg.append(usersSid);

        msg.append(", sessionId=");
        msg.append(sessionId);

        msg.append(", imtdCreatedDate=");
        msg.append(imtdCreatedDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImtdRsDetailsException(msg.toString());
    }

    /**
     * Returns the first imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
     *
     * @param usersSid the users sid
     * @param sessionId the session ID
     * @param imtdCreatedDate the imtd created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching imtd rs details, or <code>null</code> if a matching imtd rs details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetails fetchByTempRsSearch_First(String usersSid,
        String sessionId, Date imtdCreatedDate,
        OrderByComparator orderByComparator) throws SystemException {
        List<ImtdRsDetails> list = findByTempRsSearch(usersSid, sessionId,
                imtdCreatedDate, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
     *
     * @param usersSid the users sid
     * @param sessionId the session ID
     * @param imtdCreatedDate the imtd created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching imtd rs details
     * @throws com.stpl.app.NoSuchImtdRsDetailsException if a matching imtd rs details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetails findByTempRsSearch_Last(String usersSid,
        String sessionId, Date imtdCreatedDate,
        OrderByComparator orderByComparator)
        throws NoSuchImtdRsDetailsException, SystemException {
        ImtdRsDetails imtdRsDetails = fetchByTempRsSearch_Last(usersSid,
                sessionId, imtdCreatedDate, orderByComparator);

        if (imtdRsDetails != null) {
            return imtdRsDetails;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("usersSid=");
        msg.append(usersSid);

        msg.append(", sessionId=");
        msg.append(sessionId);

        msg.append(", imtdCreatedDate=");
        msg.append(imtdCreatedDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImtdRsDetailsException(msg.toString());
    }

    /**
     * Returns the last imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
     *
     * @param usersSid the users sid
     * @param sessionId the session ID
     * @param imtdCreatedDate the imtd created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching imtd rs details, or <code>null</code> if a matching imtd rs details could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetails fetchByTempRsSearch_Last(String usersSid,
        String sessionId, Date imtdCreatedDate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByTempRsSearch(usersSid, sessionId, imtdCreatedDate);

        if (count == 0) {
            return null;
        }

        List<ImtdRsDetails> list = findByTempRsSearch(usersSid, sessionId,
                imtdCreatedDate, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the imtd rs detailses before and after the current imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
     *
     * @param imtdRsDetailsSid the primary key of the current imtd rs details
     * @param usersSid the users sid
     * @param sessionId the session ID
     * @param imtdCreatedDate the imtd created date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next imtd rs details
     * @throws com.stpl.app.NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetails[] findByTempRsSearch_PrevAndNext(
        int imtdRsDetailsSid, String usersSid, String sessionId,
        Date imtdCreatedDate, OrderByComparator orderByComparator)
        throws NoSuchImtdRsDetailsException, SystemException {
        ImtdRsDetails imtdRsDetails = findByPrimaryKey(imtdRsDetailsSid);

        Session session = null;

        try {
            session = openSession();

            ImtdRsDetails[] array = new ImtdRsDetailsImpl[3];

            array[0] = getByTempRsSearch_PrevAndNext(session, imtdRsDetails,
                    usersSid, sessionId, imtdCreatedDate, orderByComparator,
                    true);

            array[1] = imtdRsDetails;

            array[2] = getByTempRsSearch_PrevAndNext(session, imtdRsDetails,
                    usersSid, sessionId, imtdCreatedDate, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ImtdRsDetails getByTempRsSearch_PrevAndNext(Session session,
        ImtdRsDetails imtdRsDetails, String usersSid, String sessionId,
        Date imtdCreatedDate, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IMTDRSDETAILS_WHERE);

        boolean bindUsersSid = false;

        if (usersSid == null) {
            query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_1);
        } else if (usersSid.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_3);
        } else {
            bindUsersSid = true;

            query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_2);
        }

        boolean bindSessionId = false;

        if (sessionId == null) {
            query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_1);
        } else if (sessionId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_3);
        } else {
            bindSessionId = true;

            query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_2);
        }

        boolean bindImtdCreatedDate = false;

        if (imtdCreatedDate == null) {
            query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_1);
        } else {
            bindImtdCreatedDate = true;

            query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ImtdRsDetailsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindUsersSid) {
            qPos.add(usersSid);
        }

        if (bindSessionId) {
            qPos.add(sessionId);
        }

        if (bindImtdCreatedDate) {
            qPos.add(CalendarUtil.getTimestamp(imtdCreatedDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(imtdRsDetails);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ImtdRsDetails> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63; from the database.
     *
     * @param usersSid the users sid
     * @param sessionId the session ID
     * @param imtdCreatedDate the imtd created date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByTempRsSearch(String usersSid, String sessionId,
        Date imtdCreatedDate) throws SystemException {
        for (ImtdRsDetails imtdRsDetails : findByTempRsSearch(usersSid,
                sessionId, imtdCreatedDate, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(imtdRsDetails);
        }
    }

    /**
     * Returns the number of imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
     *
     * @param usersSid the users sid
     * @param sessionId the session ID
     * @param imtdCreatedDate the imtd created date
     * @return the number of matching imtd rs detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByTempRsSearch(String usersSid, String sessionId,
        Date imtdCreatedDate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TEMPRSSEARCH;

        Object[] finderArgs = new Object[] { usersSid, sessionId, imtdCreatedDate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_IMTDRSDETAILS_WHERE);

            boolean bindUsersSid = false;

            if (usersSid == null) {
                query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_1);
            } else if (usersSid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_3);
            } else {
                bindUsersSid = true;

                query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_2);
            }

            boolean bindSessionId = false;

            if (sessionId == null) {
                query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_1);
            } else if (sessionId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_3);
            } else {
                bindSessionId = true;

                query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_2);
            }

            boolean bindImtdCreatedDate = false;

            if (imtdCreatedDate == null) {
                query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_1);
            } else {
                bindImtdCreatedDate = true;

                query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUsersSid) {
                    qPos.add(usersSid);
                }

                if (bindSessionId) {
                    qPos.add(sessionId);
                }

                if (bindImtdCreatedDate) {
                    qPos.add(CalendarUtil.getTimestamp(imtdCreatedDate));
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the imtd rs details in the entity cache if it is enabled.
     *
     * @param imtdRsDetails the imtd rs details
     */
    @Override
    public void cacheResult(ImtdRsDetails imtdRsDetails) {
        EntityCacheUtil.putResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsImpl.class, imtdRsDetails.getPrimaryKey(),
            imtdRsDetails);

        imtdRsDetails.resetOriginalValues();
    }

    /**
     * Caches the imtd rs detailses in the entity cache if it is enabled.
     *
     * @param imtdRsDetailses the imtd rs detailses
     */
    @Override
    public void cacheResult(List<ImtdRsDetails> imtdRsDetailses) {
        for (ImtdRsDetails imtdRsDetails : imtdRsDetailses) {
            if (EntityCacheUtil.getResult(
                        ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdRsDetailsImpl.class, imtdRsDetails.getPrimaryKey()) == null) {
                cacheResult(imtdRsDetails);
            } else {
                imtdRsDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all imtd rs detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImtdRsDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImtdRsDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the imtd rs details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ImtdRsDetails imtdRsDetails) {
        EntityCacheUtil.removeResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsImpl.class, imtdRsDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ImtdRsDetails> imtdRsDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImtdRsDetails imtdRsDetails : imtdRsDetailses) {
            EntityCacheUtil.removeResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ImtdRsDetailsImpl.class, imtdRsDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new imtd rs details with the primary key. Does not add the imtd rs details to the database.
     *
     * @param imtdRsDetailsSid the primary key for the new imtd rs details
     * @return the new imtd rs details
     */
    @Override
    public ImtdRsDetails create(int imtdRsDetailsSid) {
        ImtdRsDetails imtdRsDetails = new ImtdRsDetailsImpl();

        imtdRsDetails.setNew(true);
        imtdRsDetails.setPrimaryKey(imtdRsDetailsSid);

        return imtdRsDetails;
    }

    /**
     * Removes the imtd rs details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param imtdRsDetailsSid the primary key of the imtd rs details
     * @return the imtd rs details that was removed
     * @throws com.stpl.app.NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetails remove(int imtdRsDetailsSid)
        throws NoSuchImtdRsDetailsException, SystemException {
        return remove((Serializable) imtdRsDetailsSid);
    }

    /**
     * Removes the imtd rs details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the imtd rs details
     * @return the imtd rs details that was removed
     * @throws com.stpl.app.NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetails remove(Serializable primaryKey)
        throws NoSuchImtdRsDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImtdRsDetails imtdRsDetails = (ImtdRsDetails) session.get(ImtdRsDetailsImpl.class,
                    primaryKey);

            if (imtdRsDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImtdRsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(imtdRsDetails);
        } catch (NoSuchImtdRsDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImtdRsDetails removeImpl(ImtdRsDetails imtdRsDetails)
        throws SystemException {
        imtdRsDetails = toUnwrappedModel(imtdRsDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(imtdRsDetails)) {
                imtdRsDetails = (ImtdRsDetails) session.get(ImtdRsDetailsImpl.class,
                        imtdRsDetails.getPrimaryKeyObj());
            }

            if (imtdRsDetails != null) {
                session.delete(imtdRsDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (imtdRsDetails != null) {
            clearCache(imtdRsDetails);
        }

        return imtdRsDetails;
    }

    @Override
    public ImtdRsDetails updateImpl(
        com.stpl.app.model.ImtdRsDetails imtdRsDetails)
        throws SystemException {
        imtdRsDetails = toUnwrappedModel(imtdRsDetails);

        boolean isNew = imtdRsDetails.isNew();

        ImtdRsDetailsModelImpl imtdRsDetailsModelImpl = (ImtdRsDetailsModelImpl) imtdRsDetails;

        Session session = null;

        try {
            session = openSession();

            if (imtdRsDetails.isNew()) {
                session.save(imtdRsDetails);

                imtdRsDetails.setNew(false);
            } else {
                session.merge(imtdRsDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ImtdRsDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((imtdRsDetailsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPRSSEARCH.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        imtdRsDetailsModelImpl.getOriginalUsersSid(),
                        imtdRsDetailsModelImpl.getOriginalSessionId(),
                        imtdRsDetailsModelImpl.getOriginalImtdCreatedDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEMPRSSEARCH,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPRSSEARCH,
                    args);

                args = new Object[] {
                        imtdRsDetailsModelImpl.getUsersSid(),
                        imtdRsDetailsModelImpl.getSessionId(),
                        imtdRsDetailsModelImpl.getImtdCreatedDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEMPRSSEARCH,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPRSSEARCH,
                    args);
            }
        }

        EntityCacheUtil.putResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdRsDetailsImpl.class, imtdRsDetails.getPrimaryKey(),
            imtdRsDetails);

        return imtdRsDetails;
    }

    protected ImtdRsDetails toUnwrappedModel(ImtdRsDetails imtdRsDetails) {
        if (imtdRsDetails instanceof ImtdRsDetailsImpl) {
            return imtdRsDetails;
        }

        ImtdRsDetailsImpl imtdRsDetailsImpl = new ImtdRsDetailsImpl();

        imtdRsDetailsImpl.setNew(imtdRsDetails.isNew());
        imtdRsDetailsImpl.setPrimaryKey(imtdRsDetails.getPrimaryKey());

        imtdRsDetailsImpl.setRsDetailsModifiedDate(imtdRsDetails.getRsDetailsModifiedDate());
        imtdRsDetailsImpl.setRsDetailsBundleNo(imtdRsDetails.getRsDetailsBundleNo());
        imtdRsDetailsImpl.setItemMasterSid(imtdRsDetails.getItemMasterSid());
        imtdRsDetailsImpl.setImtdRsDetailsSid(imtdRsDetails.getImtdRsDetailsSid());
        imtdRsDetailsImpl.setItemId(imtdRsDetails.getItemId());
        imtdRsDetailsImpl.setRsDetailsFormulaMethodId(imtdRsDetails.getRsDetailsFormulaMethodId());
        imtdRsDetailsImpl.setModifiedDate(imtdRsDetails.getModifiedDate());
        imtdRsDetailsImpl.setCreatedDate(imtdRsDetails.getCreatedDate());
        imtdRsDetailsImpl.setCreatedBy(imtdRsDetails.getCreatedBy());
        imtdRsDetailsImpl.setUsersSid(imtdRsDetails.getUsersSid());
        imtdRsDetailsImpl.setContractMasterSid(imtdRsDetails.getContractMasterSid());
        imtdRsDetailsImpl.setRsDetailsFormulaId(imtdRsDetails.getRsDetailsFormulaId());
        imtdRsDetailsImpl.setImtdCreatedDate(imtdRsDetails.getImtdCreatedDate());
        imtdRsDetailsImpl.setPsModelSid(imtdRsDetails.getPsModelSid());
        imtdRsDetailsImpl.setModifiedBy(imtdRsDetails.getModifiedBy());
        imtdRsDetailsImpl.setRsDetailsCreatedDate(imtdRsDetails.getRsDetailsCreatedDate());
        imtdRsDetailsImpl.setItemNo(imtdRsDetails.getItemNo());
        imtdRsDetailsImpl.setRsDetailsFormulaName(imtdRsDetails.getRsDetailsFormulaName());
        imtdRsDetailsImpl.setUdc6(imtdRsDetails.getUdc6());
        imtdRsDetailsImpl.setRsDetailsCreatedBy(imtdRsDetails.getRsDetailsCreatedBy());
        imtdRsDetailsImpl.setUdc5(imtdRsDetails.getUdc5());
        imtdRsDetailsImpl.setIfpModelSid(imtdRsDetails.getIfpModelSid());
        imtdRsDetailsImpl.setUdc4(imtdRsDetails.getUdc4());
        imtdRsDetailsImpl.setRsDetailsFormulaNo(imtdRsDetails.getRsDetailsFormulaNo());
        imtdRsDetailsImpl.setCheckRecord(imtdRsDetails.isCheckRecord());
        imtdRsDetailsImpl.setRsId(imtdRsDetails.getRsId());
        imtdRsDetailsImpl.setUdc1(imtdRsDetails.getUdc1());
        imtdRsDetailsImpl.setRsDetailsRebateAmount(imtdRsDetails.getRsDetailsRebateAmount());
        imtdRsDetailsImpl.setUdc2(imtdRsDetails.getUdc2());
        imtdRsDetailsImpl.setRsDetailsModifiedBy(imtdRsDetails.getRsDetailsModifiedBy());
        imtdRsDetailsImpl.setUdc3(imtdRsDetails.getUdc3());
        imtdRsDetailsImpl.setRebatePlanMasterSid(imtdRsDetails.getRebatePlanMasterSid());
        imtdRsDetailsImpl.setRsDetailsAttachedDate(imtdRsDetails.getRsDetailsAttachedDate());
        imtdRsDetailsImpl.setItemRebateEndDate(imtdRsDetails.getItemRebateEndDate());
        imtdRsDetailsImpl.setRsDetailsRebatePlanName(imtdRsDetails.getRsDetailsRebatePlanName());
        imtdRsDetailsImpl.setItemRebateStartDate(imtdRsDetails.getItemRebateStartDate());
        imtdRsDetailsImpl.setRsDetailsFormulaType(imtdRsDetails.getRsDetailsFormulaType());
        imtdRsDetailsImpl.setSessionId(imtdRsDetails.getSessionId());
        imtdRsDetailsImpl.setItemName(imtdRsDetails.getItemName());
        imtdRsDetailsImpl.setOperation(imtdRsDetails.getOperation());
        imtdRsDetailsImpl.setCfpModelSid(imtdRsDetails.getCfpModelSid());
        imtdRsDetailsImpl.setRsModelSid(imtdRsDetails.getRsModelSid());
        imtdRsDetailsImpl.setRsDetailsSid(imtdRsDetails.getRsDetailsSid());
        imtdRsDetailsImpl.setRsDetailsAttachedStatus(imtdRsDetails.getRsDetailsAttachedStatus());
        imtdRsDetailsImpl.setRsDetailsNetSalesFormulaNo(imtdRsDetails.getRsDetailsNetSalesFormulaNo());
        imtdRsDetailsImpl.setRsDetailsNetSalesFormulaName(imtdRsDetails.getRsDetailsNetSalesFormulaName());
        imtdRsDetailsImpl.setRsDetailsDeductionCalendarNo(imtdRsDetails.getRsDetailsDeductionCalendarNo());
        imtdRsDetailsImpl.setRsDetailsDeductionCalendarName(imtdRsDetails.getRsDetailsDeductionCalendarName());
        imtdRsDetailsImpl.setDeductionCalendarMasterSid(imtdRsDetails.getDeductionCalendarMasterSid());
        imtdRsDetailsImpl.setNetSalesFormulaMasterSid(imtdRsDetails.getNetSalesFormulaMasterSid());
        imtdRsDetailsImpl.setEvaluationRule(imtdRsDetails.getEvaluationRule());
        imtdRsDetailsImpl.setNetSalesRule(imtdRsDetails.getNetSalesRule());
        imtdRsDetailsImpl.setFormulaType(imtdRsDetails.getFormulaType());
        imtdRsDetailsImpl.setCalculationRule(imtdRsDetails.getCalculationRule());
        imtdRsDetailsImpl.setCalculationRuleBundle(imtdRsDetails.getCalculationRuleBundle());
        imtdRsDetailsImpl.setEvaluationRuleBundle(imtdRsDetails.getEvaluationRuleBundle());

        return imtdRsDetailsImpl;
    }

    /**
     * Returns the imtd rs details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the imtd rs details
     * @return the imtd rs details
     * @throws com.stpl.app.NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImtdRsDetailsException, SystemException {
        ImtdRsDetails imtdRsDetails = fetchByPrimaryKey(primaryKey);

        if (imtdRsDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImtdRsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return imtdRsDetails;
    }

    /**
     * Returns the imtd rs details with the primary key or throws a {@link com.stpl.app.NoSuchImtdRsDetailsException} if it could not be found.
     *
     * @param imtdRsDetailsSid the primary key of the imtd rs details
     * @return the imtd rs details
     * @throws com.stpl.app.NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetails findByPrimaryKey(int imtdRsDetailsSid)
        throws NoSuchImtdRsDetailsException, SystemException {
        return findByPrimaryKey((Serializable) imtdRsDetailsSid);
    }

    /**
     * Returns the imtd rs details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the imtd rs details
     * @return the imtd rs details, or <code>null</code> if a imtd rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImtdRsDetails imtdRsDetails = (ImtdRsDetails) EntityCacheUtil.getResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ImtdRsDetailsImpl.class, primaryKey);

        if (imtdRsDetails == _nullImtdRsDetails) {
            return null;
        }

        if (imtdRsDetails == null) {
            Session session = null;

            try {
                session = openSession();

                imtdRsDetails = (ImtdRsDetails) session.get(ImtdRsDetailsImpl.class,
                        primaryKey);

                if (imtdRsDetails != null) {
                    cacheResult(imtdRsDetails);
                } else {
                    EntityCacheUtil.putResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdRsDetailsImpl.class, primaryKey, _nullImtdRsDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    ImtdRsDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return imtdRsDetails;
    }

    /**
     * Returns the imtd rs details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param imtdRsDetailsSid the primary key of the imtd rs details
     * @return the imtd rs details, or <code>null</code> if a imtd rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdRsDetails fetchByPrimaryKey(int imtdRsDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) imtdRsDetailsSid);
    }

    /**
     * Returns all the imtd rs detailses.
     *
     * @return the imtd rs detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the imtd rs detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd rs detailses
     * @param end the upper bound of the range of imtd rs detailses (not inclusive)
     * @return the range of imtd rs detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the imtd rs detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd rs detailses
     * @param end the upper bound of the range of imtd rs detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of imtd rs detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdRsDetails> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<ImtdRsDetails> list = (List<ImtdRsDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMTDRSDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMTDRSDETAILS;

                if (pagination) {
                    sql = sql.concat(ImtdRsDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImtdRsDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImtdRsDetails>(list);
                } else {
                    list = (List<ImtdRsDetails>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the imtd rs detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImtdRsDetails imtdRsDetails : findAll()) {
            remove(imtdRsDetails);
        }
    }

    /**
     * Returns the number of imtd rs detailses.
     *
     * @return the number of imtd rs detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_IMTDRSDETAILS);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the imtd rs details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ImtdRsDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImtdRsDetails>> listenersList = new ArrayList<ModelListener<ImtdRsDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImtdRsDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImtdRsDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
