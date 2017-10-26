package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIfpModelException;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.impl.IfpModelImpl;
import com.stpl.app.model.impl.IfpModelModelImpl;
import com.stpl.app.service.persistence.IfpModelPersistence;

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
 * The persistence implementation for the ifp model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpModelPersistence
 * @see IfpModelUtil
 * @generated
 */
public class IfpModelPersistenceImpl extends BasePersistenceImpl<IfpModel>
    implements IfpModelPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IfpModelUtil} to access the ifp model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IfpModelImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANID =
        new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemFamilyPlanId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANID =
        new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemFamilyPlanId", new String[] { String.class.getName() },
            IfpModelModelImpl.IFPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANID = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemFamilyPlanId", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_1 = "ifpModel.ifpId IS NULL";
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_2 = "ifpModel.ifpId = ?";
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_3 = "(ifpModel.ifpId IS NULL OR ifpModel.ifpId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANNO =
        new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemFamilyPlanNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNO =
        new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemFamilyPlanNo", new String[] { String.class.getName() },
            IfpModelModelImpl.IFPNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNO = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemFamilyPlanNo", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_1 = "ifpModel.ifpNo IS NULL";
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_2 = "ifpModel.ifpNo = ?";
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_3 = "(ifpModel.ifpNo IS NULL OR ifpModel.ifpNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME =
        new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemFamilyPlanName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME =
        new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemFamilyPlanName",
            new String[] { String.class.getName() },
            IfpModelModelImpl.IFPNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNAME = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemFamilyPlanName", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_1 = "ifpModel.ifpName IS NULL";
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_2 = "ifpModel.ifpName = ?";
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_3 = "(ifpModel.ifpName IS NULL OR ifpModel.ifpName = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE =
        new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemFamilyPlanType",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE =
        new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemFamilyPlanType",
            new String[] { Integer.class.getName() },
            IfpModelModelImpl.IFPTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANTYPE = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemFamilyPlanType",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANTYPE_IFPTYPE_2 = "ifpModel.ifpType = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS =
        new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByItemFamilyPlanStatus",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS =
        new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemFamilyPlanStatus",
            new String[] { Integer.class.getName() },
            IfpModelModelImpl.IFPSTATUS_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANSTATUS = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemFamilyPlanStatus",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_ITEMFAMILYPLANSTATUS_IFPSTATUS_2 = "ifpModel.ifpStatus = ?";
    private static final String _SQL_SELECT_IFPMODEL = "SELECT ifpModel FROM IfpModel ifpModel";
    private static final String _SQL_SELECT_IFPMODEL_WHERE = "SELECT ifpModel FROM IfpModel ifpModel WHERE ";
    private static final String _SQL_COUNT_IFPMODEL = "SELECT COUNT(ifpModel) FROM IfpModel ifpModel";
    private static final String _SQL_COUNT_IFPMODEL_WHERE = "SELECT COUNT(ifpModel) FROM IfpModel ifpModel WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ifpModel.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IfpModel exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IfpModel exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IfpModelPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "modifiedBy", "totalDollarCommitment", "createdDate",
                "ifpStatus", "totalVolumeCommitment", "batchId", "internalNotes",
                "ifpId", "totalMarketshareCommitment", "ifpCategory",
                "parentIfpName", "ifpEndDate", "ifpDesignation", "createdBy",
                "ifpStartDate", "parentIfpId", "commitmentPeriod", "ifpType",
                "modifiedDate", "ifpModelSid", "recordLockStatus", "source",
                "ifpName", "ifpNo", "inboundStatus"
            });
    private static IfpModel _nullIfpModel = new IfpModelImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IfpModel> toCacheModel() {
                return _nullIfpModelCacheModel;
            }
        };

    private static CacheModel<IfpModel> _nullIfpModelCacheModel = new CacheModel<IfpModel>() {
            @Override
            public IfpModel toEntityModel() {
                return _nullIfpModel;
            }
        };

    public IfpModelPersistenceImpl() {
        setModelClass(IfpModel.class);
    }

    /**
     * Returns all the ifp models where ifpId = &#63;.
     *
     * @param ifpId the ifp ID
     * @return the matching ifp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpModel> findByItemFamilyPlanId(String ifpId)
        throws SystemException {
        return findByItemFamilyPlanId(ifpId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<IfpModel> findByItemFamilyPlanId(String ifpId, int start,
        int end) throws SystemException {
        return findByItemFamilyPlanId(ifpId, start, end, null);
    }

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
    @Override
    public List<IfpModel> findByItemFamilyPlanId(String ifpId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANID;
            finderArgs = new Object[] { ifpId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANID;
            finderArgs = new Object[] { ifpId, start, end, orderByComparator };
        }

        List<IfpModel> list = (List<IfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IfpModel ifpModel : list) {
                if (!Validator.equals(ifpId, ifpModel.getIfpId())) {
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

            query.append(_SQL_SELECT_IFPMODEL_WHERE);

            boolean bindIfpId = false;

            if (ifpId == null) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_1);
            } else if (ifpId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_3);
            } else {
                bindIfpId = true;

                query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IfpModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindIfpId) {
                    qPos.add(ifpId);
                }

                if (!pagination) {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IfpModel>(list);
                } else {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
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
     * Returns the first ifp model in the ordered set where ifpId = &#63;.
     *
     * @param ifpId the ifp ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByItemFamilyPlanId_First(String ifpId,
        OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = fetchByItemFamilyPlanId_First(ifpId,
                orderByComparator);

        if (ifpModel != null) {
            return ifpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpId=");
        msg.append(ifpId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpModelException(msg.toString());
    }

    /**
     * Returns the first ifp model in the ordered set where ifpId = &#63;.
     *
     * @param ifpId the ifp ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByItemFamilyPlanId_First(String ifpId,
        OrderByComparator orderByComparator) throws SystemException {
        List<IfpModel> list = findByItemFamilyPlanId(ifpId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ifp model in the ordered set where ifpId = &#63;.
     *
     * @param ifpId the ifp ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByItemFamilyPlanId_Last(String ifpId,
        OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = fetchByItemFamilyPlanId_Last(ifpId,
                orderByComparator);

        if (ifpModel != null) {
            return ifpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpId=");
        msg.append(ifpId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpModelException(msg.toString());
    }

    /**
     * Returns the last ifp model in the ordered set where ifpId = &#63;.
     *
     * @param ifpId the ifp ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByItemFamilyPlanId_Last(String ifpId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemFamilyPlanId(ifpId);

        if (count == 0) {
            return null;
        }

        List<IfpModel> list = findByItemFamilyPlanId(ifpId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public IfpModel[] findByItemFamilyPlanId_PrevAndNext(int ifpModelSid,
        String ifpId, OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = findByPrimaryKey(ifpModelSid);

        Session session = null;

        try {
            session = openSession();

            IfpModel[] array = new IfpModelImpl[3];

            array[0] = getByItemFamilyPlanId_PrevAndNext(session, ifpModel,
                    ifpId, orderByComparator, true);

            array[1] = ifpModel;

            array[2] = getByItemFamilyPlanId_PrevAndNext(session, ifpModel,
                    ifpId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected IfpModel getByItemFamilyPlanId_PrevAndNext(Session session,
        IfpModel ifpModel, String ifpId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IFPMODEL_WHERE);

        boolean bindIfpId = false;

        if (ifpId == null) {
            query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_1);
        } else if (ifpId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_3);
        } else {
            bindIfpId = true;

            query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_2);
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
            query.append(IfpModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindIfpId) {
            qPos.add(ifpId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ifpModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<IfpModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ifp models where ifpId = &#63; from the database.
     *
     * @param ifpId the ifp ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemFamilyPlanId(String ifpId)
        throws SystemException {
        for (IfpModel ifpModel : findByItemFamilyPlanId(ifpId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ifpModel);
        }
    }

    /**
     * Returns the number of ifp models where ifpId = &#63;.
     *
     * @param ifpId the ifp ID
     * @return the number of matching ifp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemFamilyPlanId(String ifpId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANID;

        Object[] finderArgs = new Object[] { ifpId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IFPMODEL_WHERE);

            boolean bindIfpId = false;

            if (ifpId == null) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_1);
            } else if (ifpId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_3);
            } else {
                bindIfpId = true;

                query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindIfpId) {
                    qPos.add(ifpId);
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
     * Returns all the ifp models where ifpNo = &#63;.
     *
     * @param ifpNo the ifp no
     * @return the matching ifp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpModel> findByItemFamilyPlanNo(String ifpNo)
        throws SystemException {
        return findByItemFamilyPlanNo(ifpNo, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<IfpModel> findByItemFamilyPlanNo(String ifpNo, int start,
        int end) throws SystemException {
        return findByItemFamilyPlanNo(ifpNo, start, end, null);
    }

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
    @Override
    public List<IfpModel> findByItemFamilyPlanNo(String ifpNo, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNO;
            finderArgs = new Object[] { ifpNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANNO;
            finderArgs = new Object[] { ifpNo, start, end, orderByComparator };
        }

        List<IfpModel> list = (List<IfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IfpModel ifpModel : list) {
                if (!Validator.equals(ifpNo, ifpModel.getIfpNo())) {
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

            query.append(_SQL_SELECT_IFPMODEL_WHERE);

            boolean bindIfpNo = false;

            if (ifpNo == null) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_1);
            } else if (ifpNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_3);
            } else {
                bindIfpNo = true;

                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IfpModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindIfpNo) {
                    qPos.add(ifpNo);
                }

                if (!pagination) {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IfpModel>(list);
                } else {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
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
     * Returns the first ifp model in the ordered set where ifpNo = &#63;.
     *
     * @param ifpNo the ifp no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByItemFamilyPlanNo_First(String ifpNo,
        OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = fetchByItemFamilyPlanNo_First(ifpNo,
                orderByComparator);

        if (ifpModel != null) {
            return ifpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpNo=");
        msg.append(ifpNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpModelException(msg.toString());
    }

    /**
     * Returns the first ifp model in the ordered set where ifpNo = &#63;.
     *
     * @param ifpNo the ifp no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByItemFamilyPlanNo_First(String ifpNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<IfpModel> list = findByItemFamilyPlanNo(ifpNo, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ifp model in the ordered set where ifpNo = &#63;.
     *
     * @param ifpNo the ifp no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByItemFamilyPlanNo_Last(String ifpNo,
        OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = fetchByItemFamilyPlanNo_Last(ifpNo,
                orderByComparator);

        if (ifpModel != null) {
            return ifpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpNo=");
        msg.append(ifpNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpModelException(msg.toString());
    }

    /**
     * Returns the last ifp model in the ordered set where ifpNo = &#63;.
     *
     * @param ifpNo the ifp no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByItemFamilyPlanNo_Last(String ifpNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemFamilyPlanNo(ifpNo);

        if (count == 0) {
            return null;
        }

        List<IfpModel> list = findByItemFamilyPlanNo(ifpNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public IfpModel[] findByItemFamilyPlanNo_PrevAndNext(int ifpModelSid,
        String ifpNo, OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = findByPrimaryKey(ifpModelSid);

        Session session = null;

        try {
            session = openSession();

            IfpModel[] array = new IfpModelImpl[3];

            array[0] = getByItemFamilyPlanNo_PrevAndNext(session, ifpModel,
                    ifpNo, orderByComparator, true);

            array[1] = ifpModel;

            array[2] = getByItemFamilyPlanNo_PrevAndNext(session, ifpModel,
                    ifpNo, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected IfpModel getByItemFamilyPlanNo_PrevAndNext(Session session,
        IfpModel ifpModel, String ifpNo, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IFPMODEL_WHERE);

        boolean bindIfpNo = false;

        if (ifpNo == null) {
            query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_1);
        } else if (ifpNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_3);
        } else {
            bindIfpNo = true;

            query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_2);
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
            query.append(IfpModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindIfpNo) {
            qPos.add(ifpNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ifpModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<IfpModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ifp models where ifpNo = &#63; from the database.
     *
     * @param ifpNo the ifp no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemFamilyPlanNo(String ifpNo)
        throws SystemException {
        for (IfpModel ifpModel : findByItemFamilyPlanNo(ifpNo,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ifpModel);
        }
    }

    /**
     * Returns the number of ifp models where ifpNo = &#63;.
     *
     * @param ifpNo the ifp no
     * @return the number of matching ifp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemFamilyPlanNo(String ifpNo) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNO;

        Object[] finderArgs = new Object[] { ifpNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IFPMODEL_WHERE);

            boolean bindIfpNo = false;

            if (ifpNo == null) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_1);
            } else if (ifpNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_3);
            } else {
                bindIfpNo = true;

                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindIfpNo) {
                    qPos.add(ifpNo);
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
     * Returns all the ifp models where ifpName = &#63;.
     *
     * @param ifpName the ifp name
     * @return the matching ifp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpModel> findByItemFamilyPlanName(String ifpName)
        throws SystemException {
        return findByItemFamilyPlanName(ifpName, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<IfpModel> findByItemFamilyPlanName(String ifpName, int start,
        int end) throws SystemException {
        return findByItemFamilyPlanName(ifpName, start, end, null);
    }

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
    @Override
    public List<IfpModel> findByItemFamilyPlanName(String ifpName, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME;
            finderArgs = new Object[] { ifpName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME;
            finderArgs = new Object[] { ifpName, start, end, orderByComparator };
        }

        List<IfpModel> list = (List<IfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IfpModel ifpModel : list) {
                if (!Validator.equals(ifpName, ifpModel.getIfpName())) {
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

            query.append(_SQL_SELECT_IFPMODEL_WHERE);

            boolean bindIfpName = false;

            if (ifpName == null) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_1);
            } else if (ifpName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_3);
            } else {
                bindIfpName = true;

                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IfpModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindIfpName) {
                    qPos.add(ifpName);
                }

                if (!pagination) {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IfpModel>(list);
                } else {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
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
     * Returns the first ifp model in the ordered set where ifpName = &#63;.
     *
     * @param ifpName the ifp name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByItemFamilyPlanName_First(String ifpName,
        OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = fetchByItemFamilyPlanName_First(ifpName,
                orderByComparator);

        if (ifpModel != null) {
            return ifpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpName=");
        msg.append(ifpName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpModelException(msg.toString());
    }

    /**
     * Returns the first ifp model in the ordered set where ifpName = &#63;.
     *
     * @param ifpName the ifp name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByItemFamilyPlanName_First(String ifpName,
        OrderByComparator orderByComparator) throws SystemException {
        List<IfpModel> list = findByItemFamilyPlanName(ifpName, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ifp model in the ordered set where ifpName = &#63;.
     *
     * @param ifpName the ifp name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByItemFamilyPlanName_Last(String ifpName,
        OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = fetchByItemFamilyPlanName_Last(ifpName,
                orderByComparator);

        if (ifpModel != null) {
            return ifpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpName=");
        msg.append(ifpName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpModelException(msg.toString());
    }

    /**
     * Returns the last ifp model in the ordered set where ifpName = &#63;.
     *
     * @param ifpName the ifp name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByItemFamilyPlanName_Last(String ifpName,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemFamilyPlanName(ifpName);

        if (count == 0) {
            return null;
        }

        List<IfpModel> list = findByItemFamilyPlanName(ifpName, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public IfpModel[] findByItemFamilyPlanName_PrevAndNext(int ifpModelSid,
        String ifpName, OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = findByPrimaryKey(ifpModelSid);

        Session session = null;

        try {
            session = openSession();

            IfpModel[] array = new IfpModelImpl[3];

            array[0] = getByItemFamilyPlanName_PrevAndNext(session, ifpModel,
                    ifpName, orderByComparator, true);

            array[1] = ifpModel;

            array[2] = getByItemFamilyPlanName_PrevAndNext(session, ifpModel,
                    ifpName, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected IfpModel getByItemFamilyPlanName_PrevAndNext(Session session,
        IfpModel ifpModel, String ifpName, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IFPMODEL_WHERE);

        boolean bindIfpName = false;

        if (ifpName == null) {
            query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_1);
        } else if (ifpName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_3);
        } else {
            bindIfpName = true;

            query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_2);
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
            query.append(IfpModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindIfpName) {
            qPos.add(ifpName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ifpModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<IfpModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ifp models where ifpName = &#63; from the database.
     *
     * @param ifpName the ifp name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemFamilyPlanName(String ifpName)
        throws SystemException {
        for (IfpModel ifpModel : findByItemFamilyPlanName(ifpName,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ifpModel);
        }
    }

    /**
     * Returns the number of ifp models where ifpName = &#63;.
     *
     * @param ifpName the ifp name
     * @return the number of matching ifp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemFamilyPlanName(String ifpName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNAME;

        Object[] finderArgs = new Object[] { ifpName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IFPMODEL_WHERE);

            boolean bindIfpName = false;

            if (ifpName == null) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_1);
            } else if (ifpName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_3);
            } else {
                bindIfpName = true;

                query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindIfpName) {
                    qPos.add(ifpName);
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
     * Returns all the ifp models where ifpType = &#63;.
     *
     * @param ifpType the ifp type
     * @return the matching ifp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpModel> findByItemFamilyPlanType(int ifpType)
        throws SystemException {
        return findByItemFamilyPlanType(ifpType, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<IfpModel> findByItemFamilyPlanType(int ifpType, int start,
        int end) throws SystemException {
        return findByItemFamilyPlanType(ifpType, start, end, null);
    }

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
    @Override
    public List<IfpModel> findByItemFamilyPlanType(int ifpType, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE;
            finderArgs = new Object[] { ifpType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE;
            finderArgs = new Object[] { ifpType, start, end, orderByComparator };
        }

        List<IfpModel> list = (List<IfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IfpModel ifpModel : list) {
                if ((ifpType != ifpModel.getIfpType())) {
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

            query.append(_SQL_SELECT_IFPMODEL_WHERE);

            query.append(_FINDER_COLUMN_ITEMFAMILYPLANTYPE_IFPTYPE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IfpModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ifpType);

                if (!pagination) {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IfpModel>(list);
                } else {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
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
     * Returns the first ifp model in the ordered set where ifpType = &#63;.
     *
     * @param ifpType the ifp type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByItemFamilyPlanType_First(int ifpType,
        OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = fetchByItemFamilyPlanType_First(ifpType,
                orderByComparator);

        if (ifpModel != null) {
            return ifpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpType=");
        msg.append(ifpType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpModelException(msg.toString());
    }

    /**
     * Returns the first ifp model in the ordered set where ifpType = &#63;.
     *
     * @param ifpType the ifp type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByItemFamilyPlanType_First(int ifpType,
        OrderByComparator orderByComparator) throws SystemException {
        List<IfpModel> list = findByItemFamilyPlanType(ifpType, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ifp model in the ordered set where ifpType = &#63;.
     *
     * @param ifpType the ifp type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByItemFamilyPlanType_Last(int ifpType,
        OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = fetchByItemFamilyPlanType_Last(ifpType,
                orderByComparator);

        if (ifpModel != null) {
            return ifpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpType=");
        msg.append(ifpType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpModelException(msg.toString());
    }

    /**
     * Returns the last ifp model in the ordered set where ifpType = &#63;.
     *
     * @param ifpType the ifp type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByItemFamilyPlanType_Last(int ifpType,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemFamilyPlanType(ifpType);

        if (count == 0) {
            return null;
        }

        List<IfpModel> list = findByItemFamilyPlanType(ifpType, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public IfpModel[] findByItemFamilyPlanType_PrevAndNext(int ifpModelSid,
        int ifpType, OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = findByPrimaryKey(ifpModelSid);

        Session session = null;

        try {
            session = openSession();

            IfpModel[] array = new IfpModelImpl[3];

            array[0] = getByItemFamilyPlanType_PrevAndNext(session, ifpModel,
                    ifpType, orderByComparator, true);

            array[1] = ifpModel;

            array[2] = getByItemFamilyPlanType_PrevAndNext(session, ifpModel,
                    ifpType, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected IfpModel getByItemFamilyPlanType_PrevAndNext(Session session,
        IfpModel ifpModel, int ifpType, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IFPMODEL_WHERE);

        query.append(_FINDER_COLUMN_ITEMFAMILYPLANTYPE_IFPTYPE_2);

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
            query.append(IfpModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(ifpType);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ifpModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<IfpModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ifp models where ifpType = &#63; from the database.
     *
     * @param ifpType the ifp type
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemFamilyPlanType(int ifpType)
        throws SystemException {
        for (IfpModel ifpModel : findByItemFamilyPlanType(ifpType,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ifpModel);
        }
    }

    /**
     * Returns the number of ifp models where ifpType = &#63;.
     *
     * @param ifpType the ifp type
     * @return the number of matching ifp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemFamilyPlanType(int ifpType) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANTYPE;

        Object[] finderArgs = new Object[] { ifpType };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IFPMODEL_WHERE);

            query.append(_FINDER_COLUMN_ITEMFAMILYPLANTYPE_IFPTYPE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ifpType);

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
     * Returns all the ifp models where ifpStatus = &#63;.
     *
     * @param ifpStatus the ifp status
     * @return the matching ifp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus)
        throws SystemException {
        return findByItemFamilyPlanStatus(ifpStatus, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus, int start,
        int end) throws SystemException {
        return findByItemFamilyPlanStatus(ifpStatus, start, end, null);
    }

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
    @Override
    public List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS;
            finderArgs = new Object[] { ifpStatus };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS;
            finderArgs = new Object[] { ifpStatus, start, end, orderByComparator };
        }

        List<IfpModel> list = (List<IfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IfpModel ifpModel : list) {
                if ((ifpStatus != ifpModel.getIfpStatus())) {
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

            query.append(_SQL_SELECT_IFPMODEL_WHERE);

            query.append(_FINDER_COLUMN_ITEMFAMILYPLANSTATUS_IFPSTATUS_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IfpModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ifpStatus);

                if (!pagination) {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IfpModel>(list);
                } else {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
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
     * Returns the first ifp model in the ordered set where ifpStatus = &#63;.
     *
     * @param ifpStatus the ifp status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByItemFamilyPlanStatus_First(int ifpStatus,
        OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = fetchByItemFamilyPlanStatus_First(ifpStatus,
                orderByComparator);

        if (ifpModel != null) {
            return ifpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpStatus=");
        msg.append(ifpStatus);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpModelException(msg.toString());
    }

    /**
     * Returns the first ifp model in the ordered set where ifpStatus = &#63;.
     *
     * @param ifpStatus the ifp status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByItemFamilyPlanStatus_First(int ifpStatus,
        OrderByComparator orderByComparator) throws SystemException {
        List<IfpModel> list = findByItemFamilyPlanStatus(ifpStatus, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ifp model in the ordered set where ifpStatus = &#63;.
     *
     * @param ifpStatus the ifp status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByItemFamilyPlanStatus_Last(int ifpStatus,
        OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = fetchByItemFamilyPlanStatus_Last(ifpStatus,
                orderByComparator);

        if (ifpModel != null) {
            return ifpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ifpStatus=");
        msg.append(ifpStatus);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIfpModelException(msg.toString());
    }

    /**
     * Returns the last ifp model in the ordered set where ifpStatus = &#63;.
     *
     * @param ifpStatus the ifp status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByItemFamilyPlanStatus_Last(int ifpStatus,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemFamilyPlanStatus(ifpStatus);

        if (count == 0) {
            return null;
        }

        List<IfpModel> list = findByItemFamilyPlanStatus(ifpStatus, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public IfpModel[] findByItemFamilyPlanStatus_PrevAndNext(int ifpModelSid,
        int ifpStatus, OrderByComparator orderByComparator)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = findByPrimaryKey(ifpModelSid);

        Session session = null;

        try {
            session = openSession();

            IfpModel[] array = new IfpModelImpl[3];

            array[0] = getByItemFamilyPlanStatus_PrevAndNext(session, ifpModel,
                    ifpStatus, orderByComparator, true);

            array[1] = ifpModel;

            array[2] = getByItemFamilyPlanStatus_PrevAndNext(session, ifpModel,
                    ifpStatus, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected IfpModel getByItemFamilyPlanStatus_PrevAndNext(Session session,
        IfpModel ifpModel, int ifpStatus, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IFPMODEL_WHERE);

        query.append(_FINDER_COLUMN_ITEMFAMILYPLANSTATUS_IFPSTATUS_2);

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
            query.append(IfpModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(ifpStatus);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ifpModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<IfpModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ifp models where ifpStatus = &#63; from the database.
     *
     * @param ifpStatus the ifp status
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemFamilyPlanStatus(int ifpStatus)
        throws SystemException {
        for (IfpModel ifpModel : findByItemFamilyPlanStatus(ifpStatus,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ifpModel);
        }
    }

    /**
     * Returns the number of ifp models where ifpStatus = &#63;.
     *
     * @param ifpStatus the ifp status
     * @return the number of matching ifp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemFamilyPlanStatus(int ifpStatus)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANSTATUS;

        Object[] finderArgs = new Object[] { ifpStatus };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IFPMODEL_WHERE);

            query.append(_FINDER_COLUMN_ITEMFAMILYPLANSTATUS_IFPSTATUS_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ifpStatus);

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
     * Caches the ifp model in the entity cache if it is enabled.
     *
     * @param ifpModel the ifp model
     */
    @Override
    public void cacheResult(IfpModel ifpModel) {
        EntityCacheUtil.putResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelImpl.class, ifpModel.getPrimaryKey(), ifpModel);

        ifpModel.resetOriginalValues();
    }

    /**
     * Caches the ifp models in the entity cache if it is enabled.
     *
     * @param ifpModels the ifp models
     */
    @Override
    public void cacheResult(List<IfpModel> ifpModels) {
        for (IfpModel ifpModel : ifpModels) {
            if (EntityCacheUtil.getResult(
                        IfpModelModelImpl.ENTITY_CACHE_ENABLED,
                        IfpModelImpl.class, ifpModel.getPrimaryKey()) == null) {
                cacheResult(ifpModel);
            } else {
                ifpModel.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ifp models.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IfpModelImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IfpModelImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ifp model.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IfpModel ifpModel) {
        EntityCacheUtil.removeResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelImpl.class, ifpModel.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IfpModel> ifpModels) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IfpModel ifpModel : ifpModels) {
            EntityCacheUtil.removeResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
                IfpModelImpl.class, ifpModel.getPrimaryKey());
        }
    }

    /**
     * Creates a new ifp model with the primary key. Does not add the ifp model to the database.
     *
     * @param ifpModelSid the primary key for the new ifp model
     * @return the new ifp model
     */
    @Override
    public IfpModel create(int ifpModelSid) {
        IfpModel ifpModel = new IfpModelImpl();

        ifpModel.setNew(true);
        ifpModel.setPrimaryKey(ifpModelSid);

        return ifpModel;
    }

    /**
     * Removes the ifp model with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ifpModelSid the primary key of the ifp model
     * @return the ifp model that was removed
     * @throws com.stpl.app.NoSuchIfpModelException if a ifp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel remove(int ifpModelSid)
        throws NoSuchIfpModelException, SystemException {
        return remove((Serializable) ifpModelSid);
    }

    /**
     * Removes the ifp model with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ifp model
     * @return the ifp model that was removed
     * @throws com.stpl.app.NoSuchIfpModelException if a ifp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel remove(Serializable primaryKey)
        throws NoSuchIfpModelException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IfpModel ifpModel = (IfpModel) session.get(IfpModelImpl.class,
                    primaryKey);

            if (ifpModel == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIfpModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ifpModel);
        } catch (NoSuchIfpModelException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IfpModel removeImpl(IfpModel ifpModel) throws SystemException {
        ifpModel = toUnwrappedModel(ifpModel);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ifpModel)) {
                ifpModel = (IfpModel) session.get(IfpModelImpl.class,
                        ifpModel.getPrimaryKeyObj());
            }

            if (ifpModel != null) {
                session.delete(ifpModel);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ifpModel != null) {
            clearCache(ifpModel);
        }

        return ifpModel;
    }

    @Override
    public IfpModel updateImpl(com.stpl.app.model.IfpModel ifpModel)
        throws SystemException {
        ifpModel = toUnwrappedModel(ifpModel);

        boolean isNew = ifpModel.isNew();

        IfpModelModelImpl ifpModelModelImpl = (IfpModelModelImpl) ifpModel;

        Session session = null;

        try {
            session = openSession();

            if (ifpModel.isNew()) {
                session.save(ifpModel);

                ifpModel.setNew(false);
            } else {
                session.merge(ifpModel);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !IfpModelModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((ifpModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ifpModelModelImpl.getOriginalIfpId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANID,
                    args);

                args = new Object[] { ifpModelModelImpl.getIfpId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANID,
                    args);
            }

            if ((ifpModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ifpModelModelImpl.getOriginalIfpNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNO,
                    args);

                args = new Object[] { ifpModelModelImpl.getIfpNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNO,
                    args);
            }

            if ((ifpModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ifpModelModelImpl.getOriginalIfpName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME,
                    args);

                args = new Object[] { ifpModelModelImpl.getIfpName() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME,
                    args);
            }

            if ((ifpModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ifpModelModelImpl.getOriginalIfpType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE,
                    args);

                args = new Object[] { ifpModelModelImpl.getIfpType() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE,
                    args);
            }

            if ((ifpModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ifpModelModelImpl.getOriginalIfpStatus()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANSTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS,
                    args);

                args = new Object[] { ifpModelModelImpl.getIfpStatus() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANSTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS,
                    args);
            }
        }

        EntityCacheUtil.putResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
            IfpModelImpl.class, ifpModel.getPrimaryKey(), ifpModel);

        return ifpModel;
    }

    protected IfpModel toUnwrappedModel(IfpModel ifpModel) {
        if (ifpModel instanceof IfpModelImpl) {
            return ifpModel;
        }

        IfpModelImpl ifpModelImpl = new IfpModelImpl();

        ifpModelImpl.setNew(ifpModel.isNew());
        ifpModelImpl.setPrimaryKey(ifpModel.getPrimaryKey());

        ifpModelImpl.setModifiedBy(ifpModel.getModifiedBy());
        ifpModelImpl.setTotalDollarCommitment(ifpModel.getTotalDollarCommitment());
        ifpModelImpl.setCreatedDate(ifpModel.getCreatedDate());
        ifpModelImpl.setIfpStatus(ifpModel.getIfpStatus());
        ifpModelImpl.setTotalVolumeCommitment(ifpModel.getTotalVolumeCommitment());
        ifpModelImpl.setBatchId(ifpModel.getBatchId());
        ifpModelImpl.setInternalNotes(ifpModel.getInternalNotes());
        ifpModelImpl.setIfpId(ifpModel.getIfpId());
        ifpModelImpl.setTotalMarketshareCommitment(ifpModel.getTotalMarketshareCommitment());
        ifpModelImpl.setIfpCategory(ifpModel.getIfpCategory());
        ifpModelImpl.setParentIfpName(ifpModel.getParentIfpName());
        ifpModelImpl.setIfpEndDate(ifpModel.getIfpEndDate());
        ifpModelImpl.setIfpDesignation(ifpModel.getIfpDesignation());
        ifpModelImpl.setCreatedBy(ifpModel.getCreatedBy());
        ifpModelImpl.setIfpStartDate(ifpModel.getIfpStartDate());
        ifpModelImpl.setParentIfpId(ifpModel.getParentIfpId());
        ifpModelImpl.setCommitmentPeriod(ifpModel.getCommitmentPeriod());
        ifpModelImpl.setIfpType(ifpModel.getIfpType());
        ifpModelImpl.setModifiedDate(ifpModel.getModifiedDate());
        ifpModelImpl.setIfpModelSid(ifpModel.getIfpModelSid());
        ifpModelImpl.setRecordLockStatus(ifpModel.isRecordLockStatus());
        ifpModelImpl.setSource(ifpModel.getSource());
        ifpModelImpl.setIfpName(ifpModel.getIfpName());
        ifpModelImpl.setIfpNo(ifpModel.getIfpNo());
        ifpModelImpl.setInboundStatus(ifpModel.getInboundStatus());

        return ifpModelImpl;
    }

    /**
     * Returns the ifp model with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ifp model
     * @return the ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a ifp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIfpModelException, SystemException {
        IfpModel ifpModel = fetchByPrimaryKey(primaryKey);

        if (ifpModel == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIfpModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ifpModel;
    }

    /**
     * Returns the ifp model with the primary key or throws a {@link com.stpl.app.NoSuchIfpModelException} if it could not be found.
     *
     * @param ifpModelSid the primary key of the ifp model
     * @return the ifp model
     * @throws com.stpl.app.NoSuchIfpModelException if a ifp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel findByPrimaryKey(int ifpModelSid)
        throws NoSuchIfpModelException, SystemException {
        return findByPrimaryKey((Serializable) ifpModelSid);
    }

    /**
     * Returns the ifp model with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ifp model
     * @return the ifp model, or <code>null</code> if a ifp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IfpModel ifpModel = (IfpModel) EntityCacheUtil.getResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
                IfpModelImpl.class, primaryKey);

        if (ifpModel == _nullIfpModel) {
            return null;
        }

        if (ifpModel == null) {
            Session session = null;

            try {
                session = openSession();

                ifpModel = (IfpModel) session.get(IfpModelImpl.class, primaryKey);

                if (ifpModel != null) {
                    cacheResult(ifpModel);
                } else {
                    EntityCacheUtil.putResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
                        IfpModelImpl.class, primaryKey, _nullIfpModel);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
                    IfpModelImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ifpModel;
    }

    /**
     * Returns the ifp model with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ifpModelSid the primary key of the ifp model
     * @return the ifp model, or <code>null</code> if a ifp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpModel fetchByPrimaryKey(int ifpModelSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ifpModelSid);
    }

    /**
     * Returns all the ifp models.
     *
     * @return the ifp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpModel> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<IfpModel> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

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
    @Override
    public List<IfpModel> findAll(int start, int end,
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

        List<IfpModel> list = (List<IfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IFPMODEL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IFPMODEL;

                if (pagination) {
                    sql = sql.concat(IfpModelModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IfpModel>(list);
                } else {
                    list = (List<IfpModel>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ifp models from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IfpModel ifpModel : findAll()) {
            remove(ifpModel);
        }
    }

    /**
     * Returns the number of ifp models.
     *
     * @return the number of ifp models
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

                Query q = session.createQuery(_SQL_COUNT_IFPMODEL);

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
     * Initializes the ifp model persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IfpModel")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IfpModel>> listenersList = new ArrayList<ModelListener<IfpModel>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IfpModel>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IfpModelImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
