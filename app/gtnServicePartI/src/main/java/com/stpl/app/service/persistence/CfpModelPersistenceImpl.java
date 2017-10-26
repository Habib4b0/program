package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCfpModelException;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.impl.CfpModelImpl;
import com.stpl.app.model.impl.CfpModelModelImpl;
import com.stpl.app.service.persistence.CfpModelPersistence;

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
 * The persistence implementation for the cfp model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpModelPersistence
 * @see CfpModelUtil
 * @generated
 */
public class CfpModelPersistenceImpl extends BasePersistenceImpl<CfpModel>
    implements CfpModelPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CfpModelUtil} to access the cfp model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CfpModelImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPID = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPID = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpId",
            new String[] { String.class.getName() },
            CfpModelModelImpl.CFPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CFPID = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_CFPID_CFPID_1 = "cfpModel.cfpId IS NULL";
    private static final String _FINDER_COLUMN_CFPID_CFPID_2 = "cfpModel.cfpId = ?";
    private static final String _FINDER_COLUMN_CFPID_CFPID_3 = "(cfpModel.cfpId IS NULL OR cfpModel.cfpId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPNO = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNO = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpNo",
            new String[] { String.class.getName() },
            CfpModelModelImpl.CFPNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CFPNO = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_CFPNO_CFPNO_1 = "cfpModel.cfpNo IS NULL";
    private static final String _FINDER_COLUMN_CFPNO_CFPNO_2 = "cfpModel.cfpNo = ?";
    private static final String _FINDER_COLUMN_CFPNO_CFPNO_3 = "(cfpModel.cfpNo IS NULL OR cfpModel.cfpNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPNAME = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNAME =
        new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpName",
            new String[] { String.class.getName() },
            CfpModelModelImpl.CFPNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CFPNAME = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_CFPNAME_CFPNAME_1 = "cfpModel.cfpName IS NULL";
    private static final String _FINDER_COLUMN_CFPNAME_CFPNAME_2 = "cfpModel.cfpName = ?";
    private static final String _FINDER_COLUMN_CFPNAME_CFPNAME_3 = "(cfpModel.cfpName IS NULL OR cfpModel.cfpName = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPTYPE = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpType",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTYPE =
        new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpType",
            new String[] { Integer.class.getName() },
            CfpModelModelImpl.CFPTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CFPTYPE = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpType",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_CFPTYPE_CFPTYPE_2 = "cfpModel.cfpType = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPSTATUS =
        new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpStatus",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPSTATUS =
        new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpStatus",
            new String[] { Integer.class.getName() },
            CfpModelModelImpl.CFPSTATUS_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CFPSTATUS = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpStatus",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_CFPSTATUS_CFPSTATUS_2 = "cfpModel.cfpStatus = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPTRADECLASS =
        new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpTradeClass",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTRADECLASS =
        new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpTradeClass",
            new String[] { Integer.class.getName() },
            CfpModelModelImpl.CFPTRADECLASS_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CFPTRADECLASS = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpTradeClass",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_CFPTRADECLASS_CFPTRADECLASS_2 = "cfpModel.cfpTradeClass = ?";
    private static final String _SQL_SELECT_CFPMODEL = "SELECT cfpModel FROM CfpModel cfpModel";
    private static final String _SQL_SELECT_CFPMODEL_WHERE = "SELECT cfpModel FROM CfpModel cfpModel WHERE ";
    private static final String _SQL_COUNT_CFPMODEL = "SELECT COUNT(cfpModel) FROM CfpModel cfpModel";
    private static final String _SQL_COUNT_CFPMODEL_WHERE = "SELECT COUNT(cfpModel) FROM CfpModel cfpModel WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cfpModel.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CfpModel exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CfpModel exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CfpModelPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "cfpType", "cfpTradeClass", "modifiedBy",
                "createdDate", "cfpNo", "cfpModelSid", "batchId", "modifiedDate",
                "recordLockStatus", "internalNotes", "cfpDesignation",
                "salesInclusion", "cfpName", "cfpCategory", "source", "cfpId",
                "cfpStatus", "parentCfpId", "cfpStartDate", "cfpEndDate",
                "parentCfpName", "inboundStatus"
            });
    private static CfpModel _nullCfpModel = new CfpModelImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CfpModel> toCacheModel() {
                return _nullCfpModelCacheModel;
            }
        };

    private static CacheModel<CfpModel> _nullCfpModelCacheModel = new CacheModel<CfpModel>() {
            @Override
            public CfpModel toEntityModel() {
                return _nullCfpModel;
            }
        };

    public CfpModelPersistenceImpl() {
        setModelClass(CfpModel.class);
    }

    /**
     * Returns all the cfp models where cfpId = &#63;.
     *
     * @param cfpId the cfp ID
     * @return the matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpModel> findByCfpId(String cfpId) throws SystemException {
        return findByCfpId(cfpId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<CfpModel> findByCfpId(String cfpId, int start, int end)
        throws SystemException {
        return findByCfpId(cfpId, start, end, null);
    }

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
    @Override
    public List<CfpModel> findByCfpId(String cfpId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPID;
            finderArgs = new Object[] { cfpId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPID;
            finderArgs = new Object[] { cfpId, start, end, orderByComparator };
        }

        List<CfpModel> list = (List<CfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CfpModel cfpModel : list) {
                if (!Validator.equals(cfpId, cfpModel.getCfpId())) {
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

            query.append(_SQL_SELECT_CFPMODEL_WHERE);

            boolean bindCfpId = false;

            if (cfpId == null) {
                query.append(_FINDER_COLUMN_CFPID_CFPID_1);
            } else if (cfpId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CFPID_CFPID_3);
            } else {
                bindCfpId = true;

                query.append(_FINDER_COLUMN_CFPID_CFPID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CfpModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCfpId) {
                    qPos.add(cfpId);
                }

                if (!pagination) {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpModel>(list);
                } else {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
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
     * Returns the first cfp model in the ordered set where cfpId = &#63;.
     *
     * @param cfpId the cfp ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpId_First(String cfpId,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpId_First(cfpId, orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpId=");
        msg.append(cfpId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the first cfp model in the ordered set where cfpId = &#63;.
     *
     * @param cfpId the cfp ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpId_First(String cfpId,
        OrderByComparator orderByComparator) throws SystemException {
        List<CfpModel> list = findByCfpId(cfpId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last cfp model in the ordered set where cfpId = &#63;.
     *
     * @param cfpId the cfp ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpId_Last(String cfpId,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpId_Last(cfpId, orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpId=");
        msg.append(cfpId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the last cfp model in the ordered set where cfpId = &#63;.
     *
     * @param cfpId the cfp ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpId_Last(String cfpId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCfpId(cfpId);

        if (count == 0) {
            return null;
        }

        List<CfpModel> list = findByCfpId(cfpId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public CfpModel[] findByCfpId_PrevAndNext(int cfpModelSid, String cfpId,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

        Session session = null;

        try {
            session = openSession();

            CfpModel[] array = new CfpModelImpl[3];

            array[0] = getByCfpId_PrevAndNext(session, cfpModel, cfpId,
                    orderByComparator, true);

            array[1] = cfpModel;

            array[2] = getByCfpId_PrevAndNext(session, cfpModel, cfpId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CfpModel getByCfpId_PrevAndNext(Session session,
        CfpModel cfpModel, String cfpId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CFPMODEL_WHERE);

        boolean bindCfpId = false;

        if (cfpId == null) {
            query.append(_FINDER_COLUMN_CFPID_CFPID_1);
        } else if (cfpId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_CFPID_CFPID_3);
        } else {
            bindCfpId = true;

            query.append(_FINDER_COLUMN_CFPID_CFPID_2);
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
            query.append(CfpModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCfpId) {
            qPos.add(cfpId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CfpModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the cfp models where cfpId = &#63; from the database.
     *
     * @param cfpId the cfp ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCfpId(String cfpId) throws SystemException {
        for (CfpModel cfpModel : findByCfpId(cfpId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(cfpModel);
        }
    }

    /**
     * Returns the number of cfp models where cfpId = &#63;.
     *
     * @param cfpId the cfp ID
     * @return the number of matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCfpId(String cfpId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPID;

        Object[] finderArgs = new Object[] { cfpId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CFPMODEL_WHERE);

            boolean bindCfpId = false;

            if (cfpId == null) {
                query.append(_FINDER_COLUMN_CFPID_CFPID_1);
            } else if (cfpId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CFPID_CFPID_3);
            } else {
                bindCfpId = true;

                query.append(_FINDER_COLUMN_CFPID_CFPID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCfpId) {
                    qPos.add(cfpId);
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
     * Returns all the cfp models where cfpNo = &#63;.
     *
     * @param cfpNo the cfp no
     * @return the matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpModel> findByCfpNo(String cfpNo) throws SystemException {
        return findByCfpNo(cfpNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<CfpModel> findByCfpNo(String cfpNo, int start, int end)
        throws SystemException {
        return findByCfpNo(cfpNo, start, end, null);
    }

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
    @Override
    public List<CfpModel> findByCfpNo(String cfpNo, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNO;
            finderArgs = new Object[] { cfpNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPNO;
            finderArgs = new Object[] { cfpNo, start, end, orderByComparator };
        }

        List<CfpModel> list = (List<CfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CfpModel cfpModel : list) {
                if (!Validator.equals(cfpNo, cfpModel.getCfpNo())) {
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

            query.append(_SQL_SELECT_CFPMODEL_WHERE);

            boolean bindCfpNo = false;

            if (cfpNo == null) {
                query.append(_FINDER_COLUMN_CFPNO_CFPNO_1);
            } else if (cfpNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CFPNO_CFPNO_3);
            } else {
                bindCfpNo = true;

                query.append(_FINDER_COLUMN_CFPNO_CFPNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CfpModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCfpNo) {
                    qPos.add(cfpNo);
                }

                if (!pagination) {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpModel>(list);
                } else {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
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
     * Returns the first cfp model in the ordered set where cfpNo = &#63;.
     *
     * @param cfpNo the cfp no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpNo_First(String cfpNo,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpNo_First(cfpNo, orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpNo=");
        msg.append(cfpNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the first cfp model in the ordered set where cfpNo = &#63;.
     *
     * @param cfpNo the cfp no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpNo_First(String cfpNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<CfpModel> list = findByCfpNo(cfpNo, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last cfp model in the ordered set where cfpNo = &#63;.
     *
     * @param cfpNo the cfp no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpNo_Last(String cfpNo,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpNo_Last(cfpNo, orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpNo=");
        msg.append(cfpNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the last cfp model in the ordered set where cfpNo = &#63;.
     *
     * @param cfpNo the cfp no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpNo_Last(String cfpNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCfpNo(cfpNo);

        if (count == 0) {
            return null;
        }

        List<CfpModel> list = findByCfpNo(cfpNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public CfpModel[] findByCfpNo_PrevAndNext(int cfpModelSid, String cfpNo,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

        Session session = null;

        try {
            session = openSession();

            CfpModel[] array = new CfpModelImpl[3];

            array[0] = getByCfpNo_PrevAndNext(session, cfpModel, cfpNo,
                    orderByComparator, true);

            array[1] = cfpModel;

            array[2] = getByCfpNo_PrevAndNext(session, cfpModel, cfpNo,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CfpModel getByCfpNo_PrevAndNext(Session session,
        CfpModel cfpModel, String cfpNo, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CFPMODEL_WHERE);

        boolean bindCfpNo = false;

        if (cfpNo == null) {
            query.append(_FINDER_COLUMN_CFPNO_CFPNO_1);
        } else if (cfpNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_CFPNO_CFPNO_3);
        } else {
            bindCfpNo = true;

            query.append(_FINDER_COLUMN_CFPNO_CFPNO_2);
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
            query.append(CfpModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCfpNo) {
            qPos.add(cfpNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CfpModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the cfp models where cfpNo = &#63; from the database.
     *
     * @param cfpNo the cfp no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCfpNo(String cfpNo) throws SystemException {
        for (CfpModel cfpModel : findByCfpNo(cfpNo, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(cfpModel);
        }
    }

    /**
     * Returns the number of cfp models where cfpNo = &#63;.
     *
     * @param cfpNo the cfp no
     * @return the number of matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCfpNo(String cfpNo) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPNO;

        Object[] finderArgs = new Object[] { cfpNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CFPMODEL_WHERE);

            boolean bindCfpNo = false;

            if (cfpNo == null) {
                query.append(_FINDER_COLUMN_CFPNO_CFPNO_1);
            } else if (cfpNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CFPNO_CFPNO_3);
            } else {
                bindCfpNo = true;

                query.append(_FINDER_COLUMN_CFPNO_CFPNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCfpNo) {
                    qPos.add(cfpNo);
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
     * Returns all the cfp models where cfpName = &#63;.
     *
     * @param cfpName the cfp name
     * @return the matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpModel> findByCfpName(String cfpName)
        throws SystemException {
        return findByCfpName(cfpName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<CfpModel> findByCfpName(String cfpName, int start, int end)
        throws SystemException {
        return findByCfpName(cfpName, start, end, null);
    }

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
    @Override
    public List<CfpModel> findByCfpName(String cfpName, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNAME;
            finderArgs = new Object[] { cfpName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPNAME;
            finderArgs = new Object[] { cfpName, start, end, orderByComparator };
        }

        List<CfpModel> list = (List<CfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CfpModel cfpModel : list) {
                if (!Validator.equals(cfpName, cfpModel.getCfpName())) {
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

            query.append(_SQL_SELECT_CFPMODEL_WHERE);

            boolean bindCfpName = false;

            if (cfpName == null) {
                query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_1);
            } else if (cfpName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_3);
            } else {
                bindCfpName = true;

                query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CfpModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCfpName) {
                    qPos.add(cfpName);
                }

                if (!pagination) {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpModel>(list);
                } else {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
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
     * Returns the first cfp model in the ordered set where cfpName = &#63;.
     *
     * @param cfpName the cfp name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpName_First(String cfpName,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpName_First(cfpName, orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpName=");
        msg.append(cfpName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the first cfp model in the ordered set where cfpName = &#63;.
     *
     * @param cfpName the cfp name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpName_First(String cfpName,
        OrderByComparator orderByComparator) throws SystemException {
        List<CfpModel> list = findByCfpName(cfpName, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last cfp model in the ordered set where cfpName = &#63;.
     *
     * @param cfpName the cfp name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpName_Last(String cfpName,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpName_Last(cfpName, orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpName=");
        msg.append(cfpName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the last cfp model in the ordered set where cfpName = &#63;.
     *
     * @param cfpName the cfp name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpName_Last(String cfpName,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCfpName(cfpName);

        if (count == 0) {
            return null;
        }

        List<CfpModel> list = findByCfpName(cfpName, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public CfpModel[] findByCfpName_PrevAndNext(int cfpModelSid,
        String cfpName, OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

        Session session = null;

        try {
            session = openSession();

            CfpModel[] array = new CfpModelImpl[3];

            array[0] = getByCfpName_PrevAndNext(session, cfpModel, cfpName,
                    orderByComparator, true);

            array[1] = cfpModel;

            array[2] = getByCfpName_PrevAndNext(session, cfpModel, cfpName,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CfpModel getByCfpName_PrevAndNext(Session session,
        CfpModel cfpModel, String cfpName, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CFPMODEL_WHERE);

        boolean bindCfpName = false;

        if (cfpName == null) {
            query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_1);
        } else if (cfpName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_3);
        } else {
            bindCfpName = true;

            query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_2);
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
            query.append(CfpModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCfpName) {
            qPos.add(cfpName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CfpModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the cfp models where cfpName = &#63; from the database.
     *
     * @param cfpName the cfp name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCfpName(String cfpName) throws SystemException {
        for (CfpModel cfpModel : findByCfpName(cfpName, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(cfpModel);
        }
    }

    /**
     * Returns the number of cfp models where cfpName = &#63;.
     *
     * @param cfpName the cfp name
     * @return the number of matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCfpName(String cfpName) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPNAME;

        Object[] finderArgs = new Object[] { cfpName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CFPMODEL_WHERE);

            boolean bindCfpName = false;

            if (cfpName == null) {
                query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_1);
            } else if (cfpName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_3);
            } else {
                bindCfpName = true;

                query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCfpName) {
                    qPos.add(cfpName);
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
     * Returns all the cfp models where cfpType = &#63;.
     *
     * @param cfpType the cfp type
     * @return the matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpModel> findByCfpType(int cfpType) throws SystemException {
        return findByCfpType(cfpType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<CfpModel> findByCfpType(int cfpType, int start, int end)
        throws SystemException {
        return findByCfpType(cfpType, start, end, null);
    }

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
    @Override
    public List<CfpModel> findByCfpType(int cfpType, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTYPE;
            finderArgs = new Object[] { cfpType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPTYPE;
            finderArgs = new Object[] { cfpType, start, end, orderByComparator };
        }

        List<CfpModel> list = (List<CfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CfpModel cfpModel : list) {
                if ((cfpType != cfpModel.getCfpType())) {
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

            query.append(_SQL_SELECT_CFPMODEL_WHERE);

            query.append(_FINDER_COLUMN_CFPTYPE_CFPTYPE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CfpModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(cfpType);

                if (!pagination) {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpModel>(list);
                } else {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
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
     * Returns the first cfp model in the ordered set where cfpType = &#63;.
     *
     * @param cfpType the cfp type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpType_First(int cfpType,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpType_First(cfpType, orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpType=");
        msg.append(cfpType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the first cfp model in the ordered set where cfpType = &#63;.
     *
     * @param cfpType the cfp type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpType_First(int cfpType,
        OrderByComparator orderByComparator) throws SystemException {
        List<CfpModel> list = findByCfpType(cfpType, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last cfp model in the ordered set where cfpType = &#63;.
     *
     * @param cfpType the cfp type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpType_Last(int cfpType,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpType_Last(cfpType, orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpType=");
        msg.append(cfpType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the last cfp model in the ordered set where cfpType = &#63;.
     *
     * @param cfpType the cfp type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpType_Last(int cfpType,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCfpType(cfpType);

        if (count == 0) {
            return null;
        }

        List<CfpModel> list = findByCfpType(cfpType, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public CfpModel[] findByCfpType_PrevAndNext(int cfpModelSid, int cfpType,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

        Session session = null;

        try {
            session = openSession();

            CfpModel[] array = new CfpModelImpl[3];

            array[0] = getByCfpType_PrevAndNext(session, cfpModel, cfpType,
                    orderByComparator, true);

            array[1] = cfpModel;

            array[2] = getByCfpType_PrevAndNext(session, cfpModel, cfpType,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CfpModel getByCfpType_PrevAndNext(Session session,
        CfpModel cfpModel, int cfpType, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CFPMODEL_WHERE);

        query.append(_FINDER_COLUMN_CFPTYPE_CFPTYPE_2);

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
            query.append(CfpModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(cfpType);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CfpModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the cfp models where cfpType = &#63; from the database.
     *
     * @param cfpType the cfp type
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCfpType(int cfpType) throws SystemException {
        for (CfpModel cfpModel : findByCfpType(cfpType, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(cfpModel);
        }
    }

    /**
     * Returns the number of cfp models where cfpType = &#63;.
     *
     * @param cfpType the cfp type
     * @return the number of matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCfpType(int cfpType) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPTYPE;

        Object[] finderArgs = new Object[] { cfpType };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CFPMODEL_WHERE);

            query.append(_FINDER_COLUMN_CFPTYPE_CFPTYPE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(cfpType);

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
     * Returns all the cfp models where cfpStatus = &#63;.
     *
     * @param cfpStatus the cfp status
     * @return the matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpModel> findByCfpStatus(int cfpStatus)
        throws SystemException {
        return findByCfpStatus(cfpStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

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
    @Override
    public List<CfpModel> findByCfpStatus(int cfpStatus, int start, int end)
        throws SystemException {
        return findByCfpStatus(cfpStatus, start, end, null);
    }

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
    @Override
    public List<CfpModel> findByCfpStatus(int cfpStatus, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPSTATUS;
            finderArgs = new Object[] { cfpStatus };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPSTATUS;
            finderArgs = new Object[] { cfpStatus, start, end, orderByComparator };
        }

        List<CfpModel> list = (List<CfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CfpModel cfpModel : list) {
                if ((cfpStatus != cfpModel.getCfpStatus())) {
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

            query.append(_SQL_SELECT_CFPMODEL_WHERE);

            query.append(_FINDER_COLUMN_CFPSTATUS_CFPSTATUS_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CfpModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(cfpStatus);

                if (!pagination) {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpModel>(list);
                } else {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
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
     * Returns the first cfp model in the ordered set where cfpStatus = &#63;.
     *
     * @param cfpStatus the cfp status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpStatus_First(int cfpStatus,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpStatus_First(cfpStatus, orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpStatus=");
        msg.append(cfpStatus);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the first cfp model in the ordered set where cfpStatus = &#63;.
     *
     * @param cfpStatus the cfp status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpStatus_First(int cfpStatus,
        OrderByComparator orderByComparator) throws SystemException {
        List<CfpModel> list = findByCfpStatus(cfpStatus, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last cfp model in the ordered set where cfpStatus = &#63;.
     *
     * @param cfpStatus the cfp status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpStatus_Last(int cfpStatus,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpStatus_Last(cfpStatus, orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpStatus=");
        msg.append(cfpStatus);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the last cfp model in the ordered set where cfpStatus = &#63;.
     *
     * @param cfpStatus the cfp status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpStatus_Last(int cfpStatus,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCfpStatus(cfpStatus);

        if (count == 0) {
            return null;
        }

        List<CfpModel> list = findByCfpStatus(cfpStatus, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public CfpModel[] findByCfpStatus_PrevAndNext(int cfpModelSid,
        int cfpStatus, OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

        Session session = null;

        try {
            session = openSession();

            CfpModel[] array = new CfpModelImpl[3];

            array[0] = getByCfpStatus_PrevAndNext(session, cfpModel, cfpStatus,
                    orderByComparator, true);

            array[1] = cfpModel;

            array[2] = getByCfpStatus_PrevAndNext(session, cfpModel, cfpStatus,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CfpModel getByCfpStatus_PrevAndNext(Session session,
        CfpModel cfpModel, int cfpStatus, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CFPMODEL_WHERE);

        query.append(_FINDER_COLUMN_CFPSTATUS_CFPSTATUS_2);

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
            query.append(CfpModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(cfpStatus);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CfpModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the cfp models where cfpStatus = &#63; from the database.
     *
     * @param cfpStatus the cfp status
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCfpStatus(int cfpStatus) throws SystemException {
        for (CfpModel cfpModel : findByCfpStatus(cfpStatus, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(cfpModel);
        }
    }

    /**
     * Returns the number of cfp models where cfpStatus = &#63;.
     *
     * @param cfpStatus the cfp status
     * @return the number of matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCfpStatus(int cfpStatus) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPSTATUS;

        Object[] finderArgs = new Object[] { cfpStatus };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CFPMODEL_WHERE);

            query.append(_FINDER_COLUMN_CFPSTATUS_CFPSTATUS_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(cfpStatus);

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
     * Returns all the cfp models where cfpTradeClass = &#63;.
     *
     * @param cfpTradeClass the cfp trade class
     * @return the matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpModel> findByCfpTradeClass(int cfpTradeClass)
        throws SystemException {
        return findByCfpTradeClass(cfpTradeClass, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<CfpModel> findByCfpTradeClass(int cfpTradeClass, int start,
        int end) throws SystemException {
        return findByCfpTradeClass(cfpTradeClass, start, end, null);
    }

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
    @Override
    public List<CfpModel> findByCfpTradeClass(int cfpTradeClass, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTRADECLASS;
            finderArgs = new Object[] { cfpTradeClass };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPTRADECLASS;
            finderArgs = new Object[] {
                    cfpTradeClass,
                    
                    start, end, orderByComparator
                };
        }

        List<CfpModel> list = (List<CfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CfpModel cfpModel : list) {
                if ((cfpTradeClass != cfpModel.getCfpTradeClass())) {
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

            query.append(_SQL_SELECT_CFPMODEL_WHERE);

            query.append(_FINDER_COLUMN_CFPTRADECLASS_CFPTRADECLASS_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CfpModelModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(cfpTradeClass);

                if (!pagination) {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpModel>(list);
                } else {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
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
     * Returns the first cfp model in the ordered set where cfpTradeClass = &#63;.
     *
     * @param cfpTradeClass the cfp trade class
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpTradeClass_First(int cfpTradeClass,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpTradeClass_First(cfpTradeClass,
                orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpTradeClass=");
        msg.append(cfpTradeClass);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the first cfp model in the ordered set where cfpTradeClass = &#63;.
     *
     * @param cfpTradeClass the cfp trade class
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpTradeClass_First(int cfpTradeClass,
        OrderByComparator orderByComparator) throws SystemException {
        List<CfpModel> list = findByCfpTradeClass(cfpTradeClass, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last cfp model in the ordered set where cfpTradeClass = &#63;.
     *
     * @param cfpTradeClass the cfp trade class
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByCfpTradeClass_Last(int cfpTradeClass,
        OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByCfpTradeClass_Last(cfpTradeClass,
                orderByComparator);

        if (cfpModel != null) {
            return cfpModel;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("cfpTradeClass=");
        msg.append(cfpTradeClass);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCfpModelException(msg.toString());
    }

    /**
     * Returns the last cfp model in the ordered set where cfpTradeClass = &#63;.
     *
     * @param cfpTradeClass the cfp trade class
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByCfpTradeClass_Last(int cfpTradeClass,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCfpTradeClass(cfpTradeClass);

        if (count == 0) {
            return null;
        }

        List<CfpModel> list = findByCfpTradeClass(cfpTradeClass, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public CfpModel[] findByCfpTradeClass_PrevAndNext(int cfpModelSid,
        int cfpTradeClass, OrderByComparator orderByComparator)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

        Session session = null;

        try {
            session = openSession();

            CfpModel[] array = new CfpModelImpl[3];

            array[0] = getByCfpTradeClass_PrevAndNext(session, cfpModel,
                    cfpTradeClass, orderByComparator, true);

            array[1] = cfpModel;

            array[2] = getByCfpTradeClass_PrevAndNext(session, cfpModel,
                    cfpTradeClass, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CfpModel getByCfpTradeClass_PrevAndNext(Session session,
        CfpModel cfpModel, int cfpTradeClass,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CFPMODEL_WHERE);

        query.append(_FINDER_COLUMN_CFPTRADECLASS_CFPTRADECLASS_2);

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
            query.append(CfpModelModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(cfpTradeClass);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CfpModel> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the cfp models where cfpTradeClass = &#63; from the database.
     *
     * @param cfpTradeClass the cfp trade class
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCfpTradeClass(int cfpTradeClass)
        throws SystemException {
        for (CfpModel cfpModel : findByCfpTradeClass(cfpTradeClass,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(cfpModel);
        }
    }

    /**
     * Returns the number of cfp models where cfpTradeClass = &#63;.
     *
     * @param cfpTradeClass the cfp trade class
     * @return the number of matching cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCfpTradeClass(int cfpTradeClass)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPTRADECLASS;

        Object[] finderArgs = new Object[] { cfpTradeClass };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CFPMODEL_WHERE);

            query.append(_FINDER_COLUMN_CFPTRADECLASS_CFPTRADECLASS_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(cfpTradeClass);

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
     * Caches the cfp model in the entity cache if it is enabled.
     *
     * @param cfpModel the cfp model
     */
    @Override
    public void cacheResult(CfpModel cfpModel) {
        EntityCacheUtil.putResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelImpl.class, cfpModel.getPrimaryKey(), cfpModel);

        cfpModel.resetOriginalValues();
    }

    /**
     * Caches the cfp models in the entity cache if it is enabled.
     *
     * @param cfpModels the cfp models
     */
    @Override
    public void cacheResult(List<CfpModel> cfpModels) {
        for (CfpModel cfpModel : cfpModels) {
            if (EntityCacheUtil.getResult(
                        CfpModelModelImpl.ENTITY_CACHE_ENABLED,
                        CfpModelImpl.class, cfpModel.getPrimaryKey()) == null) {
                cacheResult(cfpModel);
            } else {
                cfpModel.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cfp models.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CfpModelImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CfpModelImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cfp model.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CfpModel cfpModel) {
        EntityCacheUtil.removeResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelImpl.class, cfpModel.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CfpModel> cfpModels) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CfpModel cfpModel : cfpModels) {
            EntityCacheUtil.removeResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
                CfpModelImpl.class, cfpModel.getPrimaryKey());
        }
    }

    /**
     * Creates a new cfp model with the primary key. Does not add the cfp model to the database.
     *
     * @param cfpModelSid the primary key for the new cfp model
     * @return the new cfp model
     */
    @Override
    public CfpModel create(int cfpModelSid) {
        CfpModel cfpModel = new CfpModelImpl();

        cfpModel.setNew(true);
        cfpModel.setPrimaryKey(cfpModelSid);

        return cfpModel;
    }

    /**
     * Removes the cfp model with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cfpModelSid the primary key of the cfp model
     * @return the cfp model that was removed
     * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel remove(int cfpModelSid)
        throws NoSuchCfpModelException, SystemException {
        return remove((Serializable) cfpModelSid);
    }

    /**
     * Removes the cfp model with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cfp model
     * @return the cfp model that was removed
     * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel remove(Serializable primaryKey)
        throws NoSuchCfpModelException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CfpModel cfpModel = (CfpModel) session.get(CfpModelImpl.class,
                    primaryKey);

            if (cfpModel == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCfpModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cfpModel);
        } catch (NoSuchCfpModelException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CfpModel removeImpl(CfpModel cfpModel) throws SystemException {
        cfpModel = toUnwrappedModel(cfpModel);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cfpModel)) {
                cfpModel = (CfpModel) session.get(CfpModelImpl.class,
                        cfpModel.getPrimaryKeyObj());
            }

            if (cfpModel != null) {
                session.delete(cfpModel);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cfpModel != null) {
            clearCache(cfpModel);
        }

        return cfpModel;
    }

    @Override
    public CfpModel updateImpl(com.stpl.app.model.CfpModel cfpModel)
        throws SystemException {
        cfpModel = toUnwrappedModel(cfpModel);

        boolean isNew = cfpModel.isNew();

        CfpModelModelImpl cfpModelModelImpl = (CfpModelModelImpl) cfpModel;

        Session session = null;

        try {
            session = openSession();

            if (cfpModel.isNew()) {
                session.save(cfpModel);

                cfpModel.setNew(false);
            } else {
                session.merge(cfpModel);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CfpModelModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((cfpModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        cfpModelModelImpl.getOriginalCfpId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPID,
                    args);

                args = new Object[] { cfpModelModelImpl.getCfpId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPID,
                    args);
            }

            if ((cfpModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        cfpModelModelImpl.getOriginalCfpNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPNO, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNO,
                    args);

                args = new Object[] { cfpModelModelImpl.getCfpNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPNO, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNO,
                    args);
            }

            if ((cfpModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        cfpModelModelImpl.getOriginalCfpName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPNAME, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNAME,
                    args);

                args = new Object[] { cfpModelModelImpl.getCfpName() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPNAME, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNAME,
                    args);
            }

            if ((cfpModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        cfpModelModelImpl.getOriginalCfpType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPTYPE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTYPE,
                    args);

                args = new Object[] { cfpModelModelImpl.getCfpType() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPTYPE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTYPE,
                    args);
            }

            if ((cfpModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPSTATUS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        cfpModelModelImpl.getOriginalCfpStatus()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPSTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPSTATUS,
                    args);

                args = new Object[] { cfpModelModelImpl.getCfpStatus() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPSTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPSTATUS,
                    args);
            }

            if ((cfpModelModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTRADECLASS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        cfpModelModelImpl.getOriginalCfpTradeClass()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPTRADECLASS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTRADECLASS,
                    args);

                args = new Object[] { cfpModelModelImpl.getCfpTradeClass() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CFPTRADECLASS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTRADECLASS,
                    args);
            }
        }

        EntityCacheUtil.putResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
            CfpModelImpl.class, cfpModel.getPrimaryKey(), cfpModel);

        return cfpModel;
    }

    protected CfpModel toUnwrappedModel(CfpModel cfpModel) {
        if (cfpModel instanceof CfpModelImpl) {
            return cfpModel;
        }

        CfpModelImpl cfpModelImpl = new CfpModelImpl();

        cfpModelImpl.setNew(cfpModel.isNew());
        cfpModelImpl.setPrimaryKey(cfpModel.getPrimaryKey());

        cfpModelImpl.setCreatedBy(cfpModel.getCreatedBy());
        cfpModelImpl.setCfpType(cfpModel.getCfpType());
        cfpModelImpl.setCfpTradeClass(cfpModel.getCfpTradeClass());
        cfpModelImpl.setModifiedBy(cfpModel.getModifiedBy());
        cfpModelImpl.setCreatedDate(cfpModel.getCreatedDate());
        cfpModelImpl.setCfpNo(cfpModel.getCfpNo());
        cfpModelImpl.setCfpModelSid(cfpModel.getCfpModelSid());
        cfpModelImpl.setBatchId(cfpModel.getBatchId());
        cfpModelImpl.setModifiedDate(cfpModel.getModifiedDate());
        cfpModelImpl.setRecordLockStatus(cfpModel.isRecordLockStatus());
        cfpModelImpl.setInternalNotes(cfpModel.getInternalNotes());
        cfpModelImpl.setCfpDesignation(cfpModel.getCfpDesignation());
        cfpModelImpl.setSalesInclusion(cfpModel.getSalesInclusion());
        cfpModelImpl.setCfpName(cfpModel.getCfpName());
        cfpModelImpl.setCfpCategory(cfpModel.getCfpCategory());
        cfpModelImpl.setSource(cfpModel.getSource());
        cfpModelImpl.setCfpId(cfpModel.getCfpId());
        cfpModelImpl.setCfpStatus(cfpModel.getCfpStatus());
        cfpModelImpl.setParentCfpId(cfpModel.getParentCfpId());
        cfpModelImpl.setCfpStartDate(cfpModel.getCfpStartDate());
        cfpModelImpl.setCfpEndDate(cfpModel.getCfpEndDate());
        cfpModelImpl.setParentCfpName(cfpModel.getParentCfpName());
        cfpModelImpl.setInboundStatus(cfpModel.getInboundStatus());

        return cfpModelImpl;
    }

    /**
     * Returns the cfp model with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cfp model
     * @return the cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCfpModelException, SystemException {
        CfpModel cfpModel = fetchByPrimaryKey(primaryKey);

        if (cfpModel == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCfpModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cfpModel;
    }

    /**
     * Returns the cfp model with the primary key or throws a {@link com.stpl.app.NoSuchCfpModelException} if it could not be found.
     *
     * @param cfpModelSid the primary key of the cfp model
     * @return the cfp model
     * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel findByPrimaryKey(int cfpModelSid)
        throws NoSuchCfpModelException, SystemException {
        return findByPrimaryKey((Serializable) cfpModelSid);
    }

    /**
     * Returns the cfp model with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cfp model
     * @return the cfp model, or <code>null</code> if a cfp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CfpModel cfpModel = (CfpModel) EntityCacheUtil.getResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
                CfpModelImpl.class, primaryKey);

        if (cfpModel == _nullCfpModel) {
            return null;
        }

        if (cfpModel == null) {
            Session session = null;

            try {
                session = openSession();

                cfpModel = (CfpModel) session.get(CfpModelImpl.class, primaryKey);

                if (cfpModel != null) {
                    cacheResult(cfpModel);
                } else {
                    EntityCacheUtil.putResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
                        CfpModelImpl.class, primaryKey, _nullCfpModel);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
                    CfpModelImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cfpModel;
    }

    /**
     * Returns the cfp model with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cfpModelSid the primary key of the cfp model
     * @return the cfp model, or <code>null</code> if a cfp model with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpModel fetchByPrimaryKey(int cfpModelSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cfpModelSid);
    }

    /**
     * Returns all the cfp models.
     *
     * @return the cfp models
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpModel> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<CfpModel> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

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
    @Override
    public List<CfpModel> findAll(int start, int end,
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

        List<CfpModel> list = (List<CfpModel>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFPMODEL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFPMODEL;

                if (pagination) {
                    sql = sql.concat(CfpModelModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpModel>(list);
                } else {
                    list = (List<CfpModel>) QueryUtil.list(q, getDialect(),
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
     * Removes all the cfp models from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CfpModel cfpModel : findAll()) {
            remove(cfpModel);
        }
    }

    /**
     * Returns the number of cfp models.
     *
     * @return the number of cfp models
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

                Query q = session.createQuery(_SQL_COUNT_CFPMODEL);

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
     * Initializes the cfp model persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CfpModel")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CfpModel>> listenersList = new ArrayList<ModelListener<CfpModel>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CfpModel>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CfpModelImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
