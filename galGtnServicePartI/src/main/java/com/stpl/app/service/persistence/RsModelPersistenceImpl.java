package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchRsModelException;
import com.stpl.app.model.RsModel;
import com.stpl.app.model.impl.RsModelImpl;
import com.stpl.app.model.impl.RsModelModelImpl;
import com.stpl.app.service.persistence.RsModelPersistence;

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
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the rs model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsModelPersistence
 * @see RsModelUtil
 * @generated
 */
public class RsModelPersistenceImpl extends BasePersistenceImpl<RsModel>
    implements RsModelPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link RsModelUtil} to access the rs model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = RsModelImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULEID =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRebateScheduleId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULEID =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByRebateScheduleId", new String[] { String.class.getName() },
            RsModelModelImpl.RSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REBATESCHEDULEID = new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByRebateScheduleId", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_REBATESCHEDULEID_RSID_1 = "rsModel.rsId IS NULL";
    private static final String _FINDER_COLUMN_REBATESCHEDULEID_RSID_2 = "rsModel.rsId = ?";
    private static final String _FINDER_COLUMN_REBATESCHEDULEID_RSID_3 = "(rsModel.rsId IS NULL OR rsModel.rsId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULENO =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRebateScheduleNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULENO =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByRebateScheduleNo", new String[] { String.class.getName() },
            RsModelModelImpl.RSNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REBATESCHEDULENO = new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByRebateScheduleNo", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_REBATESCHEDULENO_RSNO_1 = "rsModel.rsNo IS NULL";
    private static final String _FINDER_COLUMN_REBATESCHEDULENO_RSNO_2 = "rsModel.rsNo = ?";
    private static final String _FINDER_COLUMN_REBATESCHEDULENO_RSNO_3 = "(rsModel.rsNo IS NULL OR rsModel.rsNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULENAME =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRebateScheduleName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULENAME =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByRebateScheduleName",
            new String[] { String.class.getName() },
            RsModelModelImpl.RSNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REBATESCHEDULENAME = new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByRebateScheduleName", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_1 = "rsModel.rsName IS NULL";
    private static final String _FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_2 = "rsModel.rsName = ?";
    private static final String _FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_3 = "(rsModel.rsName IS NULL OR rsModel.rsName = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULETYPE =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRebateScheduleType",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULETYPE =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByRebateScheduleType",
            new String[] { Integer.class.getName() },
            RsModelModelImpl.RSTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REBATESCHEDULETYPE = new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByRebateScheduleType",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_REBATESCHEDULETYPE_RSTYPE_2 = "rsModel.rsType = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULESTATUS =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByRebateScheduleStatus",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULESTATUS =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByRebateScheduleStatus",
            new String[] { Integer.class.getName() },
            RsModelModelImpl.RSSTATUS_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REBATESCHEDULESTATUS = new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByRebateScheduleStatus",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_REBATESCHEDULESTATUS_RSSTATUS_2 = "rsModel.rsStatus = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPROGRAMTYPE =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRebateProgramType",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPROGRAMTYPE =
        new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, RsModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByRebateProgramType",
            new String[] { Integer.class.getName() },
            RsModelModelImpl.REBATEPROGRAMTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REBATEPROGRAMTYPE = new FinderPath(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByRebateProgramType", new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_REBATEPROGRAMTYPE_REBATEPROGRAMTYPE_2 =
        "rsModel.rebateProgramType = ?";
    private static final String _SQL_SELECT_RSMODEL = "SELECT rsModel FROM RsModel rsModel";
    private static final String _SQL_SELECT_RSMODEL_WHERE = "SELECT rsModel FROM RsModel rsModel WHERE ";
    private static final String _SQL_COUNT_RSMODEL = "SELECT COUNT(rsModel) FROM RsModel rsModel";
    private static final String _SQL_COUNT_RSMODEL_WHERE = "SELECT COUNT(rsModel) FROM RsModel rsModel WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "rsModel.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RsModel exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No RsModel exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(RsModelPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "formulaMethodId", "calculationType", "rsStatus", "rsEndDate",
                "rsTransRefNo", "paymentFrequency", "modifiedDate",
                "calculationLevel", "rsName", "source", "rebatePlanLevel",
                "rebateRuleType", "inboundStatus", "modifiedBy", "rsAlias",
                "rsId", "paymentMethod", "zipCode", "rebateRuleAssociation",
                "parentRsName", "internalNotes", "paymentLevel",
                "recordLockStatus", "rsCalendar", "rebateProgramType",
                "interestBearingBasis", "rsModelSid", "city",
                "rebateProcessingType", "rsNo", "state", "rebateFrequency",
                "parentRsId", "rsType", "rsStartDate", "makePayableTo",
                "rsDesignation", "rsTransRefName", "createdBy", "createdDate",
                "rsTransRefId", "rsCategory", "rsTradeClass",
                "interestBearingIndicator", "manfCompanyMasterSid",
                "paymentTerms", "address1", "address2", "rsValidationProfile",
                "paymentGracePeriod", "batchId", "evaluationRuleType",
                "evaluationRuleLevel", "evaluationRuleOrAssociation",
                "calculationRuleLevel", "calculationRule", "deductionInclusion"
            });
    private static RsModel _nullRsModel = new RsModelImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<RsModel> toCacheModel() {
                return _nullRsModelCacheModel;
            }
        };

    private static CacheModel<RsModel> _nullRsModelCacheModel = new CacheModel<RsModel>() {
            @Override
            public RsModel toEntityModel() {
                return _nullRsModel;
            }
        };

    public RsModelPersistenceImpl() {
        setModelClass(RsModel.class);
    }

    /**
     * Returns all the rs models where rsId = &#63;.
     *
     * @param rsId the rs ID
     * @return the matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsModel> findByRebateScheduleId(String rsId)
        throws SystemException {
        return findByRebateScheduleId(rsId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<RsModel> findByRebateScheduleId(String rsId, int start, int end)
        throws SystemException {
        return findByRebateScheduleId(rsId, start, end, null);
    }

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
    @Override
    public List<RsModel> findByRebateScheduleId(String rsId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULEID;
            finderArgs = new Object[] { rsId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULEID;
            finderArgs = new Object[] { rsId, start, end, orderByComparator };
        }

        List<RsModel> list = (List<RsModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (RsModel rsModel : list) {
                if (!Validator.equals(rsId, rsModel.getRsId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_RSMODEL_WHERE);

            boolean bindRsId = false;

            if (rsId == null) {
                query.append(_FINDER_COLUMN_REBATESCHEDULEID_RSID_1);
            } else if (rsId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATESCHEDULEID_RSID_3);
            } else {
                bindRsId = true;

                query.append(_FINDER_COLUMN_REBATESCHEDULEID_RSID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(RsModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRsId) {
                    qPos.add(rsId);
                }

                if (!pagination) {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RsModel>(list);
                } else {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first rs model in the ordered set where rsId = &#63;.
     *
     * @param rsId the rs ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateScheduleId_First(String rsId,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateScheduleId_First(rsId, orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rsId=");
        msg.append(rsId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the first rs model in the ordered set where rsId = &#63;.
     *
     * @param rsId the rs ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateScheduleId_First(String rsId,
        OrderByComparator orderByComparator) throws SystemException {
        List<RsModel> list = findByRebateScheduleId(rsId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last rs model in the ordered set where rsId = &#63;.
     *
     * @param rsId the rs ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateScheduleId_Last(String rsId,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateScheduleId_Last(rsId, orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rsId=");
        msg.append(rsId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the last rs model in the ordered set where rsId = &#63;.
     *
     * @param rsId the rs ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateScheduleId_Last(String rsId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByRebateScheduleId(rsId);

        if (count == 0) {
            return null;
        }

        List<RsModel> list = findByRebateScheduleId(rsId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public RsModel[] findByRebateScheduleId_PrevAndNext(int rsModelSid,
        String rsId, OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = findByPrimaryKey(rsModelSid);

        Session session = null;

        try {
            session = openSession();

            RsModel[] array = new RsModelImpl[3];

            array[0] = getByRebateScheduleId_PrevAndNext(session, rsModel,
                    rsId, orderByComparator, true);

            array[1] = rsModel;

            array[2] = getByRebateScheduleId_PrevAndNext(session, rsModel,
                    rsId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected RsModel getByRebateScheduleId_PrevAndNext(Session session,
        RsModel rsModel, String rsId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_RSMODEL_WHERE);

        boolean bindRsId = false;

        if (rsId == null) {
            query.append(_FINDER_COLUMN_REBATESCHEDULEID_RSID_1);
        } else if (rsId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_REBATESCHEDULEID_RSID_3);
        } else {
            bindRsId = true;

            query.append(_FINDER_COLUMN_REBATESCHEDULEID_RSID_2);
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
            query.append(RsModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindRsId) {
            qPos.add(rsId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(rsModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<RsModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the rs models where rsId = &#63; from the database.
     *
     * @param rsId the rs ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByRebateScheduleId(String rsId) throws SystemException {
        for (RsModel rsModel : findByRebateScheduleId(rsId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(rsModel);
        }
    }

    /**
     * Returns the number of rs models where rsId = &#63;.
     *
     * @param rsId the rs ID
     * @return the number of matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByRebateScheduleId(String rsId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATESCHEDULEID;

        Object[] finderArgs = new Object[] { rsId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_RSMODEL_WHERE);

            boolean bindRsId = false;

            if (rsId == null) {
                query.append(_FINDER_COLUMN_REBATESCHEDULEID_RSID_1);
            } else if (rsId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATESCHEDULEID_RSID_3);
            } else {
                bindRsId = true;

                query.append(_FINDER_COLUMN_REBATESCHEDULEID_RSID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRsId) {
                    qPos.add(rsId);
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
     * Returns all the rs models where rsNo = &#63;.
     *
     * @param rsNo the rs no
     * @return the matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsModel> findByRebateScheduleNo(String rsNo)
        throws SystemException {
        return findByRebateScheduleNo(rsNo, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<RsModel> findByRebateScheduleNo(String rsNo, int start, int end)
        throws SystemException {
        return findByRebateScheduleNo(rsNo, start, end, null);
    }

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
    @Override
    public List<RsModel> findByRebateScheduleNo(String rsNo, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULENO;
            finderArgs = new Object[] { rsNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULENO;
            finderArgs = new Object[] { rsNo, start, end, orderByComparator };
        }

        List<RsModel> list = (List<RsModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (RsModel rsModel : list) {
                if (!Validator.equals(rsNo, rsModel.getRsNo())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_RSMODEL_WHERE);

            boolean bindRsNo = false;

            if (rsNo == null) {
                query.append(_FINDER_COLUMN_REBATESCHEDULENO_RSNO_1);
            } else if (rsNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATESCHEDULENO_RSNO_3);
            } else {
                bindRsNo = true;

                query.append(_FINDER_COLUMN_REBATESCHEDULENO_RSNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(RsModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRsNo) {
                    qPos.add(rsNo);
                }

                if (!pagination) {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RsModel>(list);
                } else {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first rs model in the ordered set where rsNo = &#63;.
     *
     * @param rsNo the rs no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateScheduleNo_First(String rsNo,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateScheduleNo_First(rsNo, orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rsNo=");
        msg.append(rsNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the first rs model in the ordered set where rsNo = &#63;.
     *
     * @param rsNo the rs no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateScheduleNo_First(String rsNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<RsModel> list = findByRebateScheduleNo(rsNo, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last rs model in the ordered set where rsNo = &#63;.
     *
     * @param rsNo the rs no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateScheduleNo_Last(String rsNo,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateScheduleNo_Last(rsNo, orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rsNo=");
        msg.append(rsNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the last rs model in the ordered set where rsNo = &#63;.
     *
     * @param rsNo the rs no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateScheduleNo_Last(String rsNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByRebateScheduleNo(rsNo);

        if (count == 0) {
            return null;
        }

        List<RsModel> list = findByRebateScheduleNo(rsNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public RsModel[] findByRebateScheduleNo_PrevAndNext(int rsModelSid,
        String rsNo, OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = findByPrimaryKey(rsModelSid);

        Session session = null;

        try {
            session = openSession();

            RsModel[] array = new RsModelImpl[3];

            array[0] = getByRebateScheduleNo_PrevAndNext(session, rsModel,
                    rsNo, orderByComparator, true);

            array[1] = rsModel;

            array[2] = getByRebateScheduleNo_PrevAndNext(session, rsModel,
                    rsNo, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected RsModel getByRebateScheduleNo_PrevAndNext(Session session,
        RsModel rsModel, String rsNo, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_RSMODEL_WHERE);

        boolean bindRsNo = false;

        if (rsNo == null) {
            query.append(_FINDER_COLUMN_REBATESCHEDULENO_RSNO_1);
        } else if (rsNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_REBATESCHEDULENO_RSNO_3);
        } else {
            bindRsNo = true;

            query.append(_FINDER_COLUMN_REBATESCHEDULENO_RSNO_2);
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
            query.append(RsModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindRsNo) {
            qPos.add(rsNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(rsModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<RsModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the rs models where rsNo = &#63; from the database.
     *
     * @param rsNo the rs no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByRebateScheduleNo(String rsNo) throws SystemException {
        for (RsModel rsModel : findByRebateScheduleNo(rsNo, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(rsModel);
        }
    }

    /**
     * Returns the number of rs models where rsNo = &#63;.
     *
     * @param rsNo the rs no
     * @return the number of matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByRebateScheduleNo(String rsNo) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATESCHEDULENO;

        Object[] finderArgs = new Object[] { rsNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_RSMODEL_WHERE);

            boolean bindRsNo = false;

            if (rsNo == null) {
                query.append(_FINDER_COLUMN_REBATESCHEDULENO_RSNO_1);
            } else if (rsNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATESCHEDULENO_RSNO_3);
            } else {
                bindRsNo = true;

                query.append(_FINDER_COLUMN_REBATESCHEDULENO_RSNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRsNo) {
                    qPos.add(rsNo);
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
     * Returns all the rs models where rsName = &#63;.
     *
     * @param rsName the rs name
     * @return the matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsModel> findByRebateScheduleName(String rsName)
        throws SystemException {
        return findByRebateScheduleName(rsName, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<RsModel> findByRebateScheduleName(String rsName, int start,
        int end) throws SystemException {
        return findByRebateScheduleName(rsName, start, end, null);
    }

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
    @Override
    public List<RsModel> findByRebateScheduleName(String rsName, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULENAME;
            finderArgs = new Object[] { rsName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULENAME;
            finderArgs = new Object[] { rsName, start, end, orderByComparator };
        }

        List<RsModel> list = (List<RsModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (RsModel rsModel : list) {
                if (!Validator.equals(rsName, rsModel.getRsName())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_RSMODEL_WHERE);

            boolean bindRsName = false;

            if (rsName == null) {
                query.append(_FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_1);
            } else if (rsName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_3);
            } else {
                bindRsName = true;

                query.append(_FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(RsModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRsName) {
                    qPos.add(rsName);
                }

                if (!pagination) {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RsModel>(list);
                } else {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first rs model in the ordered set where rsName = &#63;.
     *
     * @param rsName the rs name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateScheduleName_First(String rsName,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateScheduleName_First(rsName,
                orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rsName=");
        msg.append(rsName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the first rs model in the ordered set where rsName = &#63;.
     *
     * @param rsName the rs name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateScheduleName_First(String rsName,
        OrderByComparator orderByComparator) throws SystemException {
        List<RsModel> list = findByRebateScheduleName(rsName, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last rs model in the ordered set where rsName = &#63;.
     *
     * @param rsName the rs name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateScheduleName_Last(String rsName,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateScheduleName_Last(rsName,
                orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rsName=");
        msg.append(rsName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the last rs model in the ordered set where rsName = &#63;.
     *
     * @param rsName the rs name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateScheduleName_Last(String rsName,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByRebateScheduleName(rsName);

        if (count == 0) {
            return null;
        }

        List<RsModel> list = findByRebateScheduleName(rsName, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public RsModel[] findByRebateScheduleName_PrevAndNext(int rsModelSid,
        String rsName, OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = findByPrimaryKey(rsModelSid);

        Session session = null;

        try {
            session = openSession();

            RsModel[] array = new RsModelImpl[3];

            array[0] = getByRebateScheduleName_PrevAndNext(session, rsModel,
                    rsName, orderByComparator, true);

            array[1] = rsModel;

            array[2] = getByRebateScheduleName_PrevAndNext(session, rsModel,
                    rsName, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected RsModel getByRebateScheduleName_PrevAndNext(Session session,
        RsModel rsModel, String rsName, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_RSMODEL_WHERE);

        boolean bindRsName = false;

        if (rsName == null) {
            query.append(_FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_1);
        } else if (rsName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_3);
        } else {
            bindRsName = true;

            query.append(_FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_2);
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
            query.append(RsModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindRsName) {
            qPos.add(rsName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(rsModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<RsModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the rs models where rsName = &#63; from the database.
     *
     * @param rsName the rs name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByRebateScheduleName(String rsName)
        throws SystemException {
        for (RsModel rsModel : findByRebateScheduleName(rsName,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(rsModel);
        }
    }

    /**
     * Returns the number of rs models where rsName = &#63;.
     *
     * @param rsName the rs name
     * @return the number of matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByRebateScheduleName(String rsName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATESCHEDULENAME;

        Object[] finderArgs = new Object[] { rsName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_RSMODEL_WHERE);

            boolean bindRsName = false;

            if (rsName == null) {
                query.append(_FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_1);
            } else if (rsName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_3);
            } else {
                bindRsName = true;

                query.append(_FINDER_COLUMN_REBATESCHEDULENAME_RSNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindRsName) {
                    qPos.add(rsName);
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
     * Returns all the rs models where rsType = &#63;.
     *
     * @param rsType the rs type
     * @return the matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsModel> findByRebateScheduleType(int rsType)
        throws SystemException {
        return findByRebateScheduleType(rsType, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<RsModel> findByRebateScheduleType(int rsType, int start, int end)
        throws SystemException {
        return findByRebateScheduleType(rsType, start, end, null);
    }

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
    @Override
    public List<RsModel> findByRebateScheduleType(int rsType, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULETYPE;
            finderArgs = new Object[] { rsType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULETYPE;
            finderArgs = new Object[] { rsType, start, end, orderByComparator };
        }

        List<RsModel> list = (List<RsModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (RsModel rsModel : list) {
                if ((rsType != rsModel.getRsType())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_RSMODEL_WHERE);

            query.append(_FINDER_COLUMN_REBATESCHEDULETYPE_RSTYPE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(RsModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(rsType);

                if (!pagination) {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RsModel>(list);
                } else {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first rs model in the ordered set where rsType = &#63;.
     *
     * @param rsType the rs type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateScheduleType_First(int rsType,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateScheduleType_First(rsType,
                orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rsType=");
        msg.append(rsType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the first rs model in the ordered set where rsType = &#63;.
     *
     * @param rsType the rs type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateScheduleType_First(int rsType,
        OrderByComparator orderByComparator) throws SystemException {
        List<RsModel> list = findByRebateScheduleType(rsType, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last rs model in the ordered set where rsType = &#63;.
     *
     * @param rsType the rs type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateScheduleType_Last(int rsType,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateScheduleType_Last(rsType,
                orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rsType=");
        msg.append(rsType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the last rs model in the ordered set where rsType = &#63;.
     *
     * @param rsType the rs type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateScheduleType_Last(int rsType,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByRebateScheduleType(rsType);

        if (count == 0) {
            return null;
        }

        List<RsModel> list = findByRebateScheduleType(rsType, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public RsModel[] findByRebateScheduleType_PrevAndNext(int rsModelSid,
        int rsType, OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = findByPrimaryKey(rsModelSid);

        Session session = null;

        try {
            session = openSession();

            RsModel[] array = new RsModelImpl[3];

            array[0] = getByRebateScheduleType_PrevAndNext(session, rsModel,
                    rsType, orderByComparator, true);

            array[1] = rsModel;

            array[2] = getByRebateScheduleType_PrevAndNext(session, rsModel,
                    rsType, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected RsModel getByRebateScheduleType_PrevAndNext(Session session,
        RsModel rsModel, int rsType, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_RSMODEL_WHERE);

        query.append(_FINDER_COLUMN_REBATESCHEDULETYPE_RSTYPE_2);

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
            query.append(RsModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(rsType);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(rsModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<RsModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the rs models where rsType = &#63; from the database.
     *
     * @param rsType the rs type
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByRebateScheduleType(int rsType)
        throws SystemException {
        for (RsModel rsModel : findByRebateScheduleType(rsType,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(rsModel);
        }
    }

    /**
     * Returns the number of rs models where rsType = &#63;.
     *
     * @param rsType the rs type
     * @return the number of matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByRebateScheduleType(int rsType) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATESCHEDULETYPE;

        Object[] finderArgs = new Object[] { rsType };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_RSMODEL_WHERE);

            query.append(_FINDER_COLUMN_REBATESCHEDULETYPE_RSTYPE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(rsType);

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
     * Returns all the rs models where rsStatus = &#63;.
     *
     * @param rsStatus the rs status
     * @return the matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsModel> findByRebateScheduleStatus(int rsStatus)
        throws SystemException {
        return findByRebateScheduleStatus(rsStatus, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<RsModel> findByRebateScheduleStatus(int rsStatus, int start,
        int end) throws SystemException {
        return findByRebateScheduleStatus(rsStatus, start, end, null);
    }

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
    @Override
    public List<RsModel> findByRebateScheduleStatus(int rsStatus, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULESTATUS;
            finderArgs = new Object[] { rsStatus };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULESTATUS;
            finderArgs = new Object[] { rsStatus, start, end, orderByComparator };
        }

        List<RsModel> list = (List<RsModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (RsModel rsModel : list) {
                if ((rsStatus != rsModel.getRsStatus())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_RSMODEL_WHERE);

            query.append(_FINDER_COLUMN_REBATESCHEDULESTATUS_RSSTATUS_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(RsModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(rsStatus);

                if (!pagination) {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RsModel>(list);
                } else {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first rs model in the ordered set where rsStatus = &#63;.
     *
     * @param rsStatus the rs status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateScheduleStatus_First(int rsStatus,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateScheduleStatus_First(rsStatus,
                orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rsStatus=");
        msg.append(rsStatus);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the first rs model in the ordered set where rsStatus = &#63;.
     *
     * @param rsStatus the rs status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateScheduleStatus_First(int rsStatus,
        OrderByComparator orderByComparator) throws SystemException {
        List<RsModel> list = findByRebateScheduleStatus(rsStatus, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last rs model in the ordered set where rsStatus = &#63;.
     *
     * @param rsStatus the rs status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateScheduleStatus_Last(int rsStatus,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateScheduleStatus_Last(rsStatus,
                orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rsStatus=");
        msg.append(rsStatus);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the last rs model in the ordered set where rsStatus = &#63;.
     *
     * @param rsStatus the rs status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateScheduleStatus_Last(int rsStatus,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByRebateScheduleStatus(rsStatus);

        if (count == 0) {
            return null;
        }

        List<RsModel> list = findByRebateScheduleStatus(rsStatus, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public RsModel[] findByRebateScheduleStatus_PrevAndNext(int rsModelSid,
        int rsStatus, OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = findByPrimaryKey(rsModelSid);

        Session session = null;

        try {
            session = openSession();

            RsModel[] array = new RsModelImpl[3];

            array[0] = getByRebateScheduleStatus_PrevAndNext(session, rsModel,
                    rsStatus, orderByComparator, true);

            array[1] = rsModel;

            array[2] = getByRebateScheduleStatus_PrevAndNext(session, rsModel,
                    rsStatus, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected RsModel getByRebateScheduleStatus_PrevAndNext(Session session,
        RsModel rsModel, int rsStatus, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_RSMODEL_WHERE);

        query.append(_FINDER_COLUMN_REBATESCHEDULESTATUS_RSSTATUS_2);

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
            query.append(RsModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(rsStatus);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(rsModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<RsModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the rs models where rsStatus = &#63; from the database.
     *
     * @param rsStatus the rs status
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByRebateScheduleStatus(int rsStatus)
        throws SystemException {
        for (RsModel rsModel : findByRebateScheduleStatus(rsStatus,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(rsModel);
        }
    }

    /**
     * Returns the number of rs models where rsStatus = &#63;.
     *
     * @param rsStatus the rs status
     * @return the number of matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByRebateScheduleStatus(int rsStatus)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATESCHEDULESTATUS;

        Object[] finderArgs = new Object[] { rsStatus };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_RSMODEL_WHERE);

            query.append(_FINDER_COLUMN_REBATESCHEDULESTATUS_RSSTATUS_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(rsStatus);

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
     * Returns all the rs models where rebateProgramType = &#63;.
     *
     * @param rebateProgramType the rebate program type
     * @return the matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsModel> findByRebateProgramType(int rebateProgramType)
        throws SystemException {
        return findByRebateProgramType(rebateProgramType, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<RsModel> findByRebateProgramType(int rebateProgramType,
        int start, int end) throws SystemException {
        return findByRebateProgramType(rebateProgramType, start, end, null);
    }

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
    @Override
    public List<RsModel> findByRebateProgramType(int rebateProgramType,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPROGRAMTYPE;
            finderArgs = new Object[] { rebateProgramType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATEPROGRAMTYPE;
            finderArgs = new Object[] {
                    rebateProgramType,
                    
                    start, end, orderByComparator
                };
        }

        List<RsModel> list = (List<RsModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (RsModel rsModel : list) {
                if ((rebateProgramType != rsModel.getRebateProgramType())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_RSMODEL_WHERE);

            query.append(_FINDER_COLUMN_REBATEPROGRAMTYPE_REBATEPROGRAMTYPE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(RsModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(rebateProgramType);

                if (!pagination) {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RsModel>(list);
                } else {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first rs model in the ordered set where rebateProgramType = &#63;.
     *
     * @param rebateProgramType the rebate program type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateProgramType_First(int rebateProgramType,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateProgramType_First(rebateProgramType,
                orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebateProgramType=");
        msg.append(rebateProgramType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the first rs model in the ordered set where rebateProgramType = &#63;.
     *
     * @param rebateProgramType the rebate program type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateProgramType_First(int rebateProgramType,
        OrderByComparator orderByComparator) throws SystemException {
        List<RsModel> list = findByRebateProgramType(rebateProgramType, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last rs model in the ordered set where rebateProgramType = &#63;.
     *
     * @param rebateProgramType the rebate program type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model
     * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByRebateProgramType_Last(int rebateProgramType,
        OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByRebateProgramType_Last(rebateProgramType,
                orderByComparator);

        if (rsModel != null) {
            return rsModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("rebateProgramType=");
        msg.append(rebateProgramType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchRsModelException(msg.toString());
    }

    /**
     * Returns the last rs model in the ordered set where rebateProgramType = &#63;.
     *
     * @param rebateProgramType the rebate program type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByRebateProgramType_Last(int rebateProgramType,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByRebateProgramType(rebateProgramType);

        if (count == 0) {
            return null;
        }

        List<RsModel> list = findByRebateProgramType(rebateProgramType,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public RsModel[] findByRebateProgramType_PrevAndNext(int rsModelSid,
        int rebateProgramType, OrderByComparator orderByComparator)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = findByPrimaryKey(rsModelSid);

        Session session = null;

        try {
            session = openSession();

            RsModel[] array = new RsModelImpl[3];

            array[0] = getByRebateProgramType_PrevAndNext(session, rsModel,
                    rebateProgramType, orderByComparator, true);

            array[1] = rsModel;

            array[2] = getByRebateProgramType_PrevAndNext(session, rsModel,
                    rebateProgramType, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected RsModel getByRebateProgramType_PrevAndNext(Session session,
        RsModel rsModel, int rebateProgramType,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_RSMODEL_WHERE);

        query.append(_FINDER_COLUMN_REBATEPROGRAMTYPE_REBATEPROGRAMTYPE_2);

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
            query.append(RsModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(rebateProgramType);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(rsModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<RsModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the rs models where rebateProgramType = &#63; from the database.
     *
     * @param rebateProgramType the rebate program type
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByRebateProgramType(int rebateProgramType)
        throws SystemException {
        for (RsModel rsModel : findByRebateProgramType(rebateProgramType,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(rsModel);
        }
    }

    /**
     * Returns the number of rs models where rebateProgramType = &#63;.
     *
     * @param rebateProgramType the rebate program type
     * @return the number of matching rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByRebateProgramType(int rebateProgramType)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATEPROGRAMTYPE;

        Object[] finderArgs = new Object[] { rebateProgramType };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_RSMODEL_WHERE);

            query.append(_FINDER_COLUMN_REBATEPROGRAMTYPE_REBATEPROGRAMTYPE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(rebateProgramType);

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
     * Caches the rs model in the entity cache if it is enabled.
     *
     * @param rsModel the rs model
     */
    @Override
    public void cacheResult(RsModel rsModel) {
        EntityCacheUtil.putResult(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelImpl.class, rsModel.getPrimaryKey(), rsModel);

        rsModel.resetOriginalValues();
    }

    /**
     * Caches the rs models in the entity cache if it is enabled.
     *
     * @param rsModels the rs models
     */
    @Override
    public void cacheResult(List<RsModel> rsModels) {
        for (RsModel rsModel : rsModels) {
            if (EntityCacheUtil.getResult(
                        RsModelModelImpl.ENTITY_CACHE_ENABLED,
                        RsModelImpl.class, rsModel.getPrimaryKey()) == null) {
                cacheResult(rsModel);
            } else {
                rsModel.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all rs models.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(RsModelImpl.class.getName());
        }

        EntityCacheUtil.clearCache(RsModelImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the rs model.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(RsModel rsModel) {
        EntityCacheUtil.removeResult(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelImpl.class, rsModel.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<RsModel> rsModels) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (RsModel rsModel : rsModels) {
            EntityCacheUtil.removeResult(RsModelModelImpl.ENTITY_CACHE_ENABLED,
                RsModelImpl.class, rsModel.getPrimaryKey());
        }
    }

    /**
     * Creates a new rs model with the primary key. Does not add the rs model to the database.
     *
     * @param rsModelSid the primary key for the new rs model
     * @return the new rs model
     */
    @Override
    public RsModel create(int rsModelSid) {
        RsModel rsModel = new RsModelImpl();

        rsModel.setNew(true);
        rsModel.setPrimaryKey(rsModelSid);

        return rsModel;
    }

    /**
     * Removes the rs model with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param rsModelSid the primary key of the rs model
     * @return the rs model that was removed
     * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel remove(int rsModelSid)
        throws NoSuchRsModelException, SystemException {
        return remove((Serializable) rsModelSid);
    }

    /**
     * Removes the rs model with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the rs model
     * @return the rs model that was removed
     * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel remove(Serializable primaryKey)
        throws NoSuchRsModelException, SystemException {
        Session session = null;

        try {
            session = openSession();

            RsModel rsModel = (RsModel) session.get(RsModelImpl.class,
                    primaryKey);

            if (rsModel == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRsModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(rsModel);
        } catch (NoSuchRsModelException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected RsModel removeImpl(RsModel rsModel) throws SystemException {
        rsModel = toUnwrappedModel(rsModel);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(rsModel)) {
                rsModel = (RsModel) session.get(RsModelImpl.class,
                        rsModel.getPrimaryKeyObj());
            }

            if (rsModel != null) {
                session.delete(rsModel);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (rsModel != null) {
            clearCache(rsModel);
        }

        return rsModel;
    }

    @Override
    public RsModel updateImpl(com.stpl.app.model.RsModel rsModel)
        throws SystemException {
        rsModel = toUnwrappedModel(rsModel);

        boolean isNew = rsModel.isNew();

        RsModelModelImpl rsModelModelImpl = (RsModelModelImpl) rsModel;

        Session session = null;

        try {
            session = openSession();

            if (rsModel.isNew()) {
                session.save(rsModel);

                rsModel.setNew(false);
            } else {
                session.merge(rsModel);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !RsModelModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((rsModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { rsModelModelImpl.getOriginalRsId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULEID,
                    args);

                args = new Object[] { rsModelModelImpl.getRsId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULEID,
                    args);
            }

            if ((rsModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULENO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { rsModelModelImpl.getOriginalRsNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULENO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULENO,
                    args);

                args = new Object[] { rsModelModelImpl.getRsNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULENO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULENO,
                    args);
            }

            if ((rsModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULENAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        rsModelModelImpl.getOriginalRsName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULENAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULENAME,
                    args);

                args = new Object[] { rsModelModelImpl.getRsName() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULENAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULENAME,
                    args);
            }

            if ((rsModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULETYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        rsModelModelImpl.getOriginalRsType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULETYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULETYPE,
                    args);

                args = new Object[] { rsModelModelImpl.getRsType() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULETYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULETYPE,
                    args);
            }

            if ((rsModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULESTATUS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        rsModelModelImpl.getOriginalRsStatus()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULESTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULESTATUS,
                    args);

                args = new Object[] { rsModelModelImpl.getRsStatus() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULESTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULESTATUS,
                    args);
            }

            if ((rsModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPROGRAMTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        rsModelModelImpl.getOriginalRebateProgramType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPROGRAMTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPROGRAMTYPE,
                    args);

                args = new Object[] { rsModelModelImpl.getRebateProgramType() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REBATEPROGRAMTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATEPROGRAMTYPE,
                    args);
            }
        }

        EntityCacheUtil.putResult(RsModelModelImpl.ENTITY_CACHE_ENABLED,
            RsModelImpl.class, rsModel.getPrimaryKey(), rsModel);

        return rsModel;
    }

    protected RsModel toUnwrappedModel(RsModel rsModel) {
        if (rsModel instanceof RsModelImpl) {
            return rsModel;
        }

        RsModelImpl rsModelImpl = new RsModelImpl();

        rsModelImpl.setNew(rsModel.isNew());
        rsModelImpl.setPrimaryKey(rsModel.getPrimaryKey());

        rsModelImpl.setFormulaMethodId(rsModel.getFormulaMethodId());
        rsModelImpl.setCalculationType(rsModel.getCalculationType());
        rsModelImpl.setRsStatus(rsModel.getRsStatus());
        rsModelImpl.setRsEndDate(rsModel.getRsEndDate());
        rsModelImpl.setRsTransRefNo(rsModel.getRsTransRefNo());
        rsModelImpl.setPaymentFrequency(rsModel.getPaymentFrequency());
        rsModelImpl.setModifiedDate(rsModel.getModifiedDate());
        rsModelImpl.setCalculationLevel(rsModel.getCalculationLevel());
        rsModelImpl.setRsName(rsModel.getRsName());
        rsModelImpl.setSource(rsModel.getSource());
        rsModelImpl.setRebatePlanLevel(rsModel.getRebatePlanLevel());
        rsModelImpl.setRebateRuleType(rsModel.getRebateRuleType());
        rsModelImpl.setInboundStatus(rsModel.getInboundStatus());
        rsModelImpl.setModifiedBy(rsModel.getModifiedBy());
        rsModelImpl.setRsAlias(rsModel.getRsAlias());
        rsModelImpl.setRsId(rsModel.getRsId());
        rsModelImpl.setPaymentMethod(rsModel.getPaymentMethod());
        rsModelImpl.setZipCode(rsModel.getZipCode());
        rsModelImpl.setRebateRuleAssociation(rsModel.getRebateRuleAssociation());
        rsModelImpl.setParentRsName(rsModel.getParentRsName());
        rsModelImpl.setInternalNotes(rsModel.getInternalNotes());
        rsModelImpl.setPaymentLevel(rsModel.getPaymentLevel());
        rsModelImpl.setRecordLockStatus(rsModel.isRecordLockStatus());
        rsModelImpl.setRsCalendar(rsModel.getRsCalendar());
        rsModelImpl.setRebateProgramType(rsModel.getRebateProgramType());
        rsModelImpl.setInterestBearingBasis(rsModel.getInterestBearingBasis());
        rsModelImpl.setRsModelSid(rsModel.getRsModelSid());
        rsModelImpl.setCity(rsModel.getCity());
        rsModelImpl.setRebateProcessingType(rsModel.getRebateProcessingType());
        rsModelImpl.setRsNo(rsModel.getRsNo());
        rsModelImpl.setState(rsModel.getState());
        rsModelImpl.setRebateFrequency(rsModel.getRebateFrequency());
        rsModelImpl.setParentRsId(rsModel.getParentRsId());
        rsModelImpl.setRsType(rsModel.getRsType());
        rsModelImpl.setRsStartDate(rsModel.getRsStartDate());
        rsModelImpl.setMakePayableTo(rsModel.getMakePayableTo());
        rsModelImpl.setRsDesignation(rsModel.getRsDesignation());
        rsModelImpl.setRsTransRefName(rsModel.getRsTransRefName());
        rsModelImpl.setCreatedBy(rsModel.getCreatedBy());
        rsModelImpl.setCreatedDate(rsModel.getCreatedDate());
        rsModelImpl.setRsTransRefId(rsModel.getRsTransRefId());
        rsModelImpl.setRsCategory(rsModel.getRsCategory());
        rsModelImpl.setRsTradeClass(rsModel.getRsTradeClass());
        rsModelImpl.setInterestBearingIndicator(rsModel.getInterestBearingIndicator());
        rsModelImpl.setManfCompanyMasterSid(rsModel.getManfCompanyMasterSid());
        rsModelImpl.setPaymentTerms(rsModel.getPaymentTerms());
        rsModelImpl.setAddress1(rsModel.getAddress1());
        rsModelImpl.setAddress2(rsModel.getAddress2());
        rsModelImpl.setRsValidationProfile(rsModel.getRsValidationProfile());
        rsModelImpl.setPaymentGracePeriod(rsModel.getPaymentGracePeriod());
        rsModelImpl.setBatchId(rsModel.getBatchId());
        rsModelImpl.setEvaluationRuleType(rsModel.getEvaluationRuleType());
        rsModelImpl.setEvaluationRuleLevel(rsModel.getEvaluationRuleLevel());
        rsModelImpl.setEvaluationRuleOrAssociation(rsModel.getEvaluationRuleOrAssociation());
        rsModelImpl.setCalculationRuleLevel(rsModel.getCalculationRuleLevel());
        rsModelImpl.setCalculationRule(rsModel.getCalculationRule());
        rsModelImpl.setDeductionInclusion(rsModel.getDeductionInclusion());

        return rsModelImpl;
    }

    /**
     * Returns the rs model with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the rs model
     * @return the rs model
     * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRsModelException, SystemException {
        RsModel rsModel = fetchByPrimaryKey(primaryKey);

        if (rsModel == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRsModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return rsModel;
    }

    /**
     * Returns the rs model with the primary key or throws a {@link com.stpl.app.NoSuchRsModelException} if it could not be found.
     *
     * @param rsModelSid the primary key of the rs model
     * @return the rs model
     * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel findByPrimaryKey(int rsModelSid)
        throws NoSuchRsModelException, SystemException {
        return findByPrimaryKey((Serializable) rsModelSid);
    }

    /**
     * Returns the rs model with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the rs model
     * @return the rs model, or <code>null</code> if a rs model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        RsModel rsModel = (RsModel) EntityCacheUtil.getResult(RsModelModelImpl.ENTITY_CACHE_ENABLED,
                RsModelImpl.class, primaryKey);

        if (rsModel == _nullRsModel) {
            return null;
        }

        if (rsModel == null) {
            Session session = null;

            try {
                session = openSession();

                rsModel = (RsModel) session.get(RsModelImpl.class, primaryKey);

                if (rsModel != null) {
                    cacheResult(rsModel);
                } else {
                    EntityCacheUtil.putResult(RsModelModelImpl.ENTITY_CACHE_ENABLED,
                        RsModelImpl.class, primaryKey, _nullRsModel);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(RsModelModelImpl.ENTITY_CACHE_ENABLED,
                    RsModelImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return rsModel;
    }

    /**
     * Returns the rs model with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param rsModelSid the primary key of the rs model
     * @return the rs model, or <code>null</code> if a rs model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsModel fetchByPrimaryKey(int rsModelSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) rsModelSid);
    }

    /**
     * Returns all the rs models.
     *
     * @return the rs models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsModel> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<RsModel> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

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
    @Override
    public List<RsModel> findAll(int start, int end,
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

        List<RsModel> list = (List<RsModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_RSMODEL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_RSMODEL;

                if (pagination) {
                    sql = sql.concat(RsModelModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RsModel>(list);
                } else {
                    list = (List<RsModel>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the rs models from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (RsModel rsModel : findAll()) {
            remove(rsModel);
        }
    }

    /**
     * Returns the number of rs models.
     *
     * @return the number of rs models
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

                Query q = session.createQuery(_SQL_COUNT_RSMODEL);

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
     * Initializes the rs model persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.RsModel")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<RsModel>> listenersList = new ArrayList<ModelListener<RsModel>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<RsModel>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(RsModelImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
