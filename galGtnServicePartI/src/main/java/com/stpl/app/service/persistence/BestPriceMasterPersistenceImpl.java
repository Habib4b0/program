package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchBestPriceMasterException;
import com.stpl.app.model.BestPriceMaster;
import com.stpl.app.model.impl.BestPriceMasterImpl;
import com.stpl.app.model.impl.BestPriceMasterModelImpl;
import com.stpl.app.service.persistence.BestPriceMasterPersistence;

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
 * The persistence implementation for the best price master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BestPriceMasterPersistence
 * @see BestPriceMasterUtil
 * @generated
 */
public class BestPriceMasterPersistenceImpl extends BasePersistenceImpl<BestPriceMaster>
    implements BestPriceMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link BestPriceMasterUtil} to access the best price master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = BestPriceMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMID = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByItemId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID =
        new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemId",
            new String[] { String.class.getName() },
            BestPriceMasterModelImpl.ITEMID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMID = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_1 = "bestPriceMaster.itemId IS NULL";
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_2 = "bestPriceMaster.itemId = ?";
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_3 = "(bestPriceMaster.itemId IS NULL OR bestPriceMaster.itemId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMNO = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByItemNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO =
        new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemNo",
            new String[] { String.class.getName() },
            BestPriceMasterModelImpl.ITEMNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMNO = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_1 = "bestPriceMaster.itemNo IS NULL";
    private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_2 = "bestPriceMaster.itemNo = ?";
    private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_3 = "(bestPriceMaster.itemNo IS NULL OR bestPriceMaster.itemNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTNO =
        new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByContractNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTNO =
        new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContractNo",
            new String[] { String.class.getName() },
            BestPriceMasterModelImpl.CONTRACTNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTRACTNO = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContractNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_CONTRACTNO_CONTRACTNO_1 = "bestPriceMaster.contractNo IS NULL";
    private static final String _FINDER_COLUMN_CONTRACTNO_CONTRACTNO_2 = "bestPriceMaster.contractNo = ?";
    private static final String _FINDER_COLUMN_CONTRACTNO_CONTRACTNO_3 = "(bestPriceMaster.contractNo IS NULL OR bestPriceMaster.contractNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTID =
        new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByContractId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID =
        new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContractId",
            new String[] { String.class.getName() },
            BestPriceMasterModelImpl.CONTRACTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTRACTID = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContractId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_1 = "bestPriceMaster.contractId IS NULL";
    private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_2 = "bestPriceMaster.contractId = ?";
    private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_3 = "(bestPriceMaster.contractId IS NULL OR bestPriceMaster.contractId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID =
        new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByAccountId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID =
        new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountId",
            new String[] { String.class.getName() },
            BestPriceMasterModelImpl.ACCOUNTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTID = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1 = "bestPriceMaster.accountId IS NULL";
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2 = "bestPriceMaster.accountId = ?";
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3 = "(bestPriceMaster.accountId IS NULL OR bestPriceMaster.accountId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PERIOD = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByPeriod",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIOD =
        new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPeriod",
            new String[] { String.class.getName() },
            BestPriceMasterModelImpl.PERIOD_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PERIOD = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPeriod",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_PERIOD_PERIOD_1 = "bestPriceMaster.period IS NULL";
    private static final String _FINDER_COLUMN_PERIOD_PERIOD_2 = "bestPriceMaster.period = ?";
    private static final String _FINDER_COLUMN_PERIOD_PERIOD_3 = "(bestPriceMaster.period IS NULL OR bestPriceMaster.period = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BESTPRICEUNIQUE =
        new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByBestPriceUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName(), String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BESTPRICEUNIQUE =
        new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED,
            BestPriceMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBestPriceUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName(), String.class.getName()
            },
            BestPriceMasterModelImpl.ITEMID_COLUMN_BITMASK |
            BestPriceMasterModelImpl.ACCOUNTID_COLUMN_BITMASK |
            BestPriceMasterModelImpl.PERIOD_COLUMN_BITMASK |
            BestPriceMasterModelImpl.CONTRACTNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_BESTPRICEUNIQUE = new FinderPath(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByBestPriceUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName(), String.class.getName()
            });
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_1 = "bestPriceMaster.itemId IS NULL AND ";
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_2 = "bestPriceMaster.itemId = ? AND ";
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_3 = "(bestPriceMaster.itemId IS NULL OR bestPriceMaster.itemId = '') AND ";
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_1 = "bestPriceMaster.accountId IS NULL AND ";
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_2 = "bestPriceMaster.accountId = ? AND ";
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_3 = "(bestPriceMaster.accountId IS NULL OR bestPriceMaster.accountId = '') AND ";
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_1 = "bestPriceMaster.period IS NULL AND ";
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_2 = "bestPriceMaster.period = ? AND ";
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_3 = "(bestPriceMaster.period IS NULL OR bestPriceMaster.period = '') AND ";
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_1 = "bestPriceMaster.contractNo IS NULL";
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_2 = "bestPriceMaster.contractNo = ?";
    private static final String _FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_3 = "(bestPriceMaster.contractNo IS NULL OR bestPriceMaster.contractNo = '')";
    private static final String _SQL_SELECT_BESTPRICEMASTER = "SELECT bestPriceMaster FROM BestPriceMaster bestPriceMaster";
    private static final String _SQL_SELECT_BESTPRICEMASTER_WHERE = "SELECT bestPriceMaster FROM BestPriceMaster bestPriceMaster WHERE ";
    private static final String _SQL_COUNT_BESTPRICEMASTER = "SELECT COUNT(bestPriceMaster) FROM BestPriceMaster bestPriceMaster";
    private static final String _SQL_COUNT_BESTPRICEMASTER_WHERE = "SELECT COUNT(bestPriceMaster) FROM BestPriceMaster bestPriceMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "bestPriceMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BestPriceMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BestPriceMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(BestPriceMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "initialBestPrice", "createdBy", "itemNo", "modifiedBy",
                "accountId", "createdDate", "itemId", "itemDesc", "sellingPrice",
                "contractId", "contractNo", "batchId", "bestPriceMasterSid",
                "modifiedDate", "recordLockStatus", "beginningWacPackage",
                "initialDiscount", "period", "source", "contractStartDate",
                "contractEndDate", "inboundStatus"
            });
    private static BestPriceMaster _nullBestPriceMaster = new BestPriceMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<BestPriceMaster> toCacheModel() {
                return _nullBestPriceMasterCacheModel;
            }
        };

    private static CacheModel<BestPriceMaster> _nullBestPriceMasterCacheModel = new CacheModel<BestPriceMaster>() {
            @Override
            public BestPriceMaster toEntityModel() {
                return _nullBestPriceMaster;
            }
        };

    public BestPriceMasterPersistenceImpl() {
        setModelClass(BestPriceMaster.class);
    }

    /**
     * Returns all the best price masters where itemId = &#63;.
     *
     * @param itemId the item ID
     * @return the matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByItemId(String itemId)
        throws SystemException {
        return findByItemId(itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the best price masters where itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @return the range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByItemId(String itemId, int start, int end)
        throws SystemException {
        return findByItemId(itemId, start, end, null);
    }

    /**
     * Returns an ordered range of all the best price masters where itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByItemId(String itemId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID;
            finderArgs = new Object[] { itemId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMID;
            finderArgs = new Object[] { itemId, start, end, orderByComparator };
        }

        List<BestPriceMaster> list = (List<BestPriceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (BestPriceMaster bestPriceMaster : list) {
                if (!Validator.equals(itemId, bestPriceMaster.getItemId())) {
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

            query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

            boolean bindItemId = false;

            if (itemId == null) {
                query.append(_FINDER_COLUMN_ITEMID_ITEMID_1);
            } else if (itemId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMID_ITEMID_3);
            } else {
                bindItemId = true;

                query.append(_FINDER_COLUMN_ITEMID_ITEMID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemId) {
                    qPos.add(itemId);
                }

                if (!pagination) {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BestPriceMaster>(list);
                } else {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
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
     * Returns the first best price master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByItemId_First(String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByItemId_First(itemId,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the first best price master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByItemId_First(String itemId,
        OrderByComparator orderByComparator) throws SystemException {
        List<BestPriceMaster> list = findByItemId(itemId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last best price master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByItemId_Last(String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByItemId_Last(itemId,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the last best price master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByItemId_Last(String itemId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemId(itemId);

        if (count == 0) {
            return null;
        }

        List<BestPriceMaster> list = findByItemId(itemId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the best price masters before and after the current best price master in the ordered set where itemId = &#63;.
     *
     * @param bestPriceMasterSid the primary key of the current best price master
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster[] findByItemId_PrevAndNext(int bestPriceMasterSid,
        String itemId, OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = findByPrimaryKey(bestPriceMasterSid);

        Session session = null;

        try {
            session = openSession();

            BestPriceMaster[] array = new BestPriceMasterImpl[3];

            array[0] = getByItemId_PrevAndNext(session, bestPriceMaster,
                    itemId, orderByComparator, true);

            array[1] = bestPriceMaster;

            array[2] = getByItemId_PrevAndNext(session, bestPriceMaster,
                    itemId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected BestPriceMaster getByItemId_PrevAndNext(Session session,
        BestPriceMaster bestPriceMaster, String itemId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

        boolean bindItemId = false;

        if (itemId == null) {
            query.append(_FINDER_COLUMN_ITEMID_ITEMID_1);
        } else if (itemId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMID_ITEMID_3);
        } else {
            bindItemId = true;

            query.append(_FINDER_COLUMN_ITEMID_ITEMID_2);
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
            query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindItemId) {
            qPos.add(itemId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(bestPriceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<BestPriceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the best price masters where itemId = &#63; from the database.
     *
     * @param itemId the item ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemId(String itemId) throws SystemException {
        for (BestPriceMaster bestPriceMaster : findByItemId(itemId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(bestPriceMaster);
        }
    }

    /**
     * Returns the number of best price masters where itemId = &#63;.
     *
     * @param itemId the item ID
     * @return the number of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemId(String itemId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMID;

        Object[] finderArgs = new Object[] { itemId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_BESTPRICEMASTER_WHERE);

            boolean bindItemId = false;

            if (itemId == null) {
                query.append(_FINDER_COLUMN_ITEMID_ITEMID_1);
            } else if (itemId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMID_ITEMID_3);
            } else {
                bindItemId = true;

                query.append(_FINDER_COLUMN_ITEMID_ITEMID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemId) {
                    qPos.add(itemId);
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
     * Returns all the best price masters where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @return the matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByItemNo(String itemNo)
        throws SystemException {
        return findByItemNo(itemNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the best price masters where itemNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemNo the item no
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @return the range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByItemNo(String itemNo, int start, int end)
        throws SystemException {
        return findByItemNo(itemNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the best price masters where itemNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemNo the item no
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByItemNo(String itemNo, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO;
            finderArgs = new Object[] { itemNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMNO;
            finderArgs = new Object[] { itemNo, start, end, orderByComparator };
        }

        List<BestPriceMaster> list = (List<BestPriceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (BestPriceMaster bestPriceMaster : list) {
                if (!Validator.equals(itemNo, bestPriceMaster.getItemNo())) {
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

            query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

            boolean bindItemNo = false;

            if (itemNo == null) {
                query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_1);
            } else if (itemNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_3);
            } else {
                bindItemNo = true;

                query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemNo) {
                    qPos.add(itemNo);
                }

                if (!pagination) {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BestPriceMaster>(list);
                } else {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
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
     * Returns the first best price master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByItemNo_First(String itemNo,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByItemNo_First(itemNo,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemNo=");
        msg.append(itemNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the first best price master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByItemNo_First(String itemNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<BestPriceMaster> list = findByItemNo(itemNo, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last best price master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByItemNo_Last(String itemNo,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByItemNo_Last(itemNo,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemNo=");
        msg.append(itemNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the last best price master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByItemNo_Last(String itemNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemNo(itemNo);

        if (count == 0) {
            return null;
        }

        List<BestPriceMaster> list = findByItemNo(itemNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the best price masters before and after the current best price master in the ordered set where itemNo = &#63;.
     *
     * @param bestPriceMasterSid the primary key of the current best price master
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster[] findByItemNo_PrevAndNext(int bestPriceMasterSid,
        String itemNo, OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = findByPrimaryKey(bestPriceMasterSid);

        Session session = null;

        try {
            session = openSession();

            BestPriceMaster[] array = new BestPriceMasterImpl[3];

            array[0] = getByItemNo_PrevAndNext(session, bestPriceMaster,
                    itemNo, orderByComparator, true);

            array[1] = bestPriceMaster;

            array[2] = getByItemNo_PrevAndNext(session, bestPriceMaster,
                    itemNo, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected BestPriceMaster getByItemNo_PrevAndNext(Session session,
        BestPriceMaster bestPriceMaster, String itemNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

        boolean bindItemNo = false;

        if (itemNo == null) {
            query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_1);
        } else if (itemNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_3);
        } else {
            bindItemNo = true;

            query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_2);
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
            query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindItemNo) {
            qPos.add(itemNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(bestPriceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<BestPriceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the best price masters where itemNo = &#63; from the database.
     *
     * @param itemNo the item no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemNo(String itemNo) throws SystemException {
        for (BestPriceMaster bestPriceMaster : findByItemNo(itemNo,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(bestPriceMaster);
        }
    }

    /**
     * Returns the number of best price masters where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @return the number of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemNo(String itemNo) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMNO;

        Object[] finderArgs = new Object[] { itemNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_BESTPRICEMASTER_WHERE);

            boolean bindItemNo = false;

            if (itemNo == null) {
                query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_1);
            } else if (itemNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_3);
            } else {
                bindItemNo = true;

                query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemNo) {
                    qPos.add(itemNo);
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
     * Returns all the best price masters where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @return the matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByContractNo(String contractNo)
        throws SystemException {
        return findByContractNo(contractNo, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the best price masters where contractNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractNo the contract no
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @return the range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByContractNo(String contractNo, int start,
        int end) throws SystemException {
        return findByContractNo(contractNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the best price masters where contractNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractNo the contract no
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByContractNo(String contractNo, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTNO;
            finderArgs = new Object[] { contractNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTNO;
            finderArgs = new Object[] { contractNo, start, end, orderByComparator };
        }

        List<BestPriceMaster> list = (List<BestPriceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (BestPriceMaster bestPriceMaster : list) {
                if (!Validator.equals(contractNo,
                            bestPriceMaster.getContractNo())) {
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

            query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

            boolean bindContractNo = false;

            if (contractNo == null) {
                query.append(_FINDER_COLUMN_CONTRACTNO_CONTRACTNO_1);
            } else if (contractNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CONTRACTNO_CONTRACTNO_3);
            } else {
                bindContractNo = true;

                query.append(_FINDER_COLUMN_CONTRACTNO_CONTRACTNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindContractNo) {
                    qPos.add(contractNo);
                }

                if (!pagination) {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BestPriceMaster>(list);
                } else {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
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
     * Returns the first best price master in the ordered set where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByContractNo_First(String contractNo,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByContractNo_First(contractNo,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractNo=");
        msg.append(contractNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the first best price master in the ordered set where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByContractNo_First(String contractNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<BestPriceMaster> list = findByContractNo(contractNo, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last best price master in the ordered set where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByContractNo_Last(String contractNo,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByContractNo_Last(contractNo,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractNo=");
        msg.append(contractNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the last best price master in the ordered set where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByContractNo_Last(String contractNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContractNo(contractNo);

        if (count == 0) {
            return null;
        }

        List<BestPriceMaster> list = findByContractNo(contractNo, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the best price masters before and after the current best price master in the ordered set where contractNo = &#63;.
     *
     * @param bestPriceMasterSid the primary key of the current best price master
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster[] findByContractNo_PrevAndNext(
        int bestPriceMasterSid, String contractNo,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = findByPrimaryKey(bestPriceMasterSid);

        Session session = null;

        try {
            session = openSession();

            BestPriceMaster[] array = new BestPriceMasterImpl[3];

            array[0] = getByContractNo_PrevAndNext(session, bestPriceMaster,
                    contractNo, orderByComparator, true);

            array[1] = bestPriceMaster;

            array[2] = getByContractNo_PrevAndNext(session, bestPriceMaster,
                    contractNo, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected BestPriceMaster getByContractNo_PrevAndNext(Session session,
        BestPriceMaster bestPriceMaster, String contractNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

        boolean bindContractNo = false;

        if (contractNo == null) {
            query.append(_FINDER_COLUMN_CONTRACTNO_CONTRACTNO_1);
        } else if (contractNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_CONTRACTNO_CONTRACTNO_3);
        } else {
            bindContractNo = true;

            query.append(_FINDER_COLUMN_CONTRACTNO_CONTRACTNO_2);
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
            query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindContractNo) {
            qPos.add(contractNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(bestPriceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<BestPriceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the best price masters where contractNo = &#63; from the database.
     *
     * @param contractNo the contract no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContractNo(String contractNo) throws SystemException {
        for (BestPriceMaster bestPriceMaster : findByContractNo(contractNo,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(bestPriceMaster);
        }
    }

    /**
     * Returns the number of best price masters where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @return the number of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContractNo(String contractNo) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTRACTNO;

        Object[] finderArgs = new Object[] { contractNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_BESTPRICEMASTER_WHERE);

            boolean bindContractNo = false;

            if (contractNo == null) {
                query.append(_FINDER_COLUMN_CONTRACTNO_CONTRACTNO_1);
            } else if (contractNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CONTRACTNO_CONTRACTNO_3);
            } else {
                bindContractNo = true;

                query.append(_FINDER_COLUMN_CONTRACTNO_CONTRACTNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindContractNo) {
                    qPos.add(contractNo);
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
     * Returns all the best price masters where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @return the matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByContractId(String contractId)
        throws SystemException {
        return findByContractId(contractId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the best price masters where contractId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractId the contract ID
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @return the range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByContractId(String contractId, int start,
        int end) throws SystemException {
        return findByContractId(contractId, start, end, null);
    }

    /**
     * Returns an ordered range of all the best price masters where contractId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractId the contract ID
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByContractId(String contractId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID;
            finderArgs = new Object[] { contractId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTID;
            finderArgs = new Object[] { contractId, start, end, orderByComparator };
        }

        List<BestPriceMaster> list = (List<BestPriceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (BestPriceMaster bestPriceMaster : list) {
                if (!Validator.equals(contractId,
                            bestPriceMaster.getContractId())) {
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

            query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

            boolean bindContractId = false;

            if (contractId == null) {
                query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_1);
            } else if (contractId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_3);
            } else {
                bindContractId = true;

                query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindContractId) {
                    qPos.add(contractId);
                }

                if (!pagination) {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BestPriceMaster>(list);
                } else {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
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
     * Returns the first best price master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByContractId_First(String contractId,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByContractId_First(contractId,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractId=");
        msg.append(contractId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the first best price master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByContractId_First(String contractId,
        OrderByComparator orderByComparator) throws SystemException {
        List<BestPriceMaster> list = findByContractId(contractId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last best price master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByContractId_Last(String contractId,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByContractId_Last(contractId,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractId=");
        msg.append(contractId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the last best price master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByContractId_Last(String contractId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContractId(contractId);

        if (count == 0) {
            return null;
        }

        List<BestPriceMaster> list = findByContractId(contractId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the best price masters before and after the current best price master in the ordered set where contractId = &#63;.
     *
     * @param bestPriceMasterSid the primary key of the current best price master
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster[] findByContractId_PrevAndNext(
        int bestPriceMasterSid, String contractId,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = findByPrimaryKey(bestPriceMasterSid);

        Session session = null;

        try {
            session = openSession();

            BestPriceMaster[] array = new BestPriceMasterImpl[3];

            array[0] = getByContractId_PrevAndNext(session, bestPriceMaster,
                    contractId, orderByComparator, true);

            array[1] = bestPriceMaster;

            array[2] = getByContractId_PrevAndNext(session, bestPriceMaster,
                    contractId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected BestPriceMaster getByContractId_PrevAndNext(Session session,
        BestPriceMaster bestPriceMaster, String contractId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

        boolean bindContractId = false;

        if (contractId == null) {
            query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_1);
        } else if (contractId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_3);
        } else {
            bindContractId = true;

            query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_2);
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
            query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindContractId) {
            qPos.add(contractId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(bestPriceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<BestPriceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the best price masters where contractId = &#63; from the database.
     *
     * @param contractId the contract ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContractId(String contractId) throws SystemException {
        for (BestPriceMaster bestPriceMaster : findByContractId(contractId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(bestPriceMaster);
        }
    }

    /**
     * Returns the number of best price masters where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @return the number of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContractId(String contractId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTRACTID;

        Object[] finderArgs = new Object[] { contractId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_BESTPRICEMASTER_WHERE);

            boolean bindContractId = false;

            if (contractId == null) {
                query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_1);
            } else if (contractId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_3);
            } else {
                bindContractId = true;

                query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindContractId) {
                    qPos.add(contractId);
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
     * Returns all the best price masters where accountId = &#63;.
     *
     * @param accountId the account ID
     * @return the matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByAccountId(String accountId)
        throws SystemException {
        return findByAccountId(accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the best price masters where accountId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountId the account ID
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @return the range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByAccountId(String accountId, int start,
        int end) throws SystemException {
        return findByAccountId(accountId, start, end, null);
    }

    /**
     * Returns an ordered range of all the best price masters where accountId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountId the account ID
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByAccountId(String accountId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID;
            finderArgs = new Object[] { accountId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID;
            finderArgs = new Object[] { accountId, start, end, orderByComparator };
        }

        List<BestPriceMaster> list = (List<BestPriceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (BestPriceMaster bestPriceMaster : list) {
                if (!Validator.equals(accountId, bestPriceMaster.getAccountId())) {
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

            query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

            boolean bindAccountId = false;

            if (accountId == null) {
                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1);
            } else if (accountId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3);
            } else {
                bindAccountId = true;

                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAccountId) {
                    qPos.add(accountId);
                }

                if (!pagination) {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BestPriceMaster>(list);
                } else {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
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
     * Returns the first best price master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByAccountId_First(String accountId,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByAccountId_First(accountId,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountId=");
        msg.append(accountId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the first best price master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByAccountId_First(String accountId,
        OrderByComparator orderByComparator) throws SystemException {
        List<BestPriceMaster> list = findByAccountId(accountId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last best price master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByAccountId_Last(String accountId,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByAccountId_Last(accountId,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountId=");
        msg.append(accountId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the last best price master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByAccountId_Last(String accountId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByAccountId(accountId);

        if (count == 0) {
            return null;
        }

        List<BestPriceMaster> list = findByAccountId(accountId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the best price masters before and after the current best price master in the ordered set where accountId = &#63;.
     *
     * @param bestPriceMasterSid the primary key of the current best price master
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster[] findByAccountId_PrevAndNext(
        int bestPriceMasterSid, String accountId,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = findByPrimaryKey(bestPriceMasterSid);

        Session session = null;

        try {
            session = openSession();

            BestPriceMaster[] array = new BestPriceMasterImpl[3];

            array[0] = getByAccountId_PrevAndNext(session, bestPriceMaster,
                    accountId, orderByComparator, true);

            array[1] = bestPriceMaster;

            array[2] = getByAccountId_PrevAndNext(session, bestPriceMaster,
                    accountId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected BestPriceMaster getByAccountId_PrevAndNext(Session session,
        BestPriceMaster bestPriceMaster, String accountId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

        boolean bindAccountId = false;

        if (accountId == null) {
            query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1);
        } else if (accountId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3);
        } else {
            bindAccountId = true;

            query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);
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
            query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindAccountId) {
            qPos.add(accountId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(bestPriceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<BestPriceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the best price masters where accountId = &#63; from the database.
     *
     * @param accountId the account ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAccountId(String accountId) throws SystemException {
        for (BestPriceMaster bestPriceMaster : findByAccountId(accountId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(bestPriceMaster);
        }
    }

    /**
     * Returns the number of best price masters where accountId = &#63;.
     *
     * @param accountId the account ID
     * @return the number of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByAccountId(String accountId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTID;

        Object[] finderArgs = new Object[] { accountId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_BESTPRICEMASTER_WHERE);

            boolean bindAccountId = false;

            if (accountId == null) {
                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1);
            } else if (accountId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3);
            } else {
                bindAccountId = true;

                query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAccountId) {
                    qPos.add(accountId);
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
     * Returns all the best price masters where period = &#63;.
     *
     * @param period the period
     * @return the matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByPeriod(String period)
        throws SystemException {
        return findByPeriod(period, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the best price masters where period = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param period the period
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @return the range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByPeriod(String period, int start, int end)
        throws SystemException {
        return findByPeriod(period, start, end, null);
    }

    /**
     * Returns an ordered range of all the best price masters where period = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param period the period
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByPeriod(String period, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIOD;
            finderArgs = new Object[] { period };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PERIOD;
            finderArgs = new Object[] { period, start, end, orderByComparator };
        }

        List<BestPriceMaster> list = (List<BestPriceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (BestPriceMaster bestPriceMaster : list) {
                if (!Validator.equals(period, bestPriceMaster.getPeriod())) {
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

            query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

            boolean bindPeriod = false;

            if (period == null) {
                query.append(_FINDER_COLUMN_PERIOD_PERIOD_1);
            } else if (period.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PERIOD_PERIOD_3);
            } else {
                bindPeriod = true;

                query.append(_FINDER_COLUMN_PERIOD_PERIOD_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindPeriod) {
                    qPos.add(period);
                }

                if (!pagination) {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BestPriceMaster>(list);
                } else {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
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
     * Returns the first best price master in the ordered set where period = &#63;.
     *
     * @param period the period
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByPeriod_First(String period,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByPeriod_First(period,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("period=");
        msg.append(period);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the first best price master in the ordered set where period = &#63;.
     *
     * @param period the period
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByPeriod_First(String period,
        OrderByComparator orderByComparator) throws SystemException {
        List<BestPriceMaster> list = findByPeriod(period, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last best price master in the ordered set where period = &#63;.
     *
     * @param period the period
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByPeriod_Last(String period,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByPeriod_Last(period,
                orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("period=");
        msg.append(period);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the last best price master in the ordered set where period = &#63;.
     *
     * @param period the period
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByPeriod_Last(String period,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByPeriod(period);

        if (count == 0) {
            return null;
        }

        List<BestPriceMaster> list = findByPeriod(period, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the best price masters before and after the current best price master in the ordered set where period = &#63;.
     *
     * @param bestPriceMasterSid the primary key of the current best price master
     * @param period the period
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster[] findByPeriod_PrevAndNext(int bestPriceMasterSid,
        String period, OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = findByPrimaryKey(bestPriceMasterSid);

        Session session = null;

        try {
            session = openSession();

            BestPriceMaster[] array = new BestPriceMasterImpl[3];

            array[0] = getByPeriod_PrevAndNext(session, bestPriceMaster,
                    period, orderByComparator, true);

            array[1] = bestPriceMaster;

            array[2] = getByPeriod_PrevAndNext(session, bestPriceMaster,
                    period, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected BestPriceMaster getByPeriod_PrevAndNext(Session session,
        BestPriceMaster bestPriceMaster, String period,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

        boolean bindPeriod = false;

        if (period == null) {
            query.append(_FINDER_COLUMN_PERIOD_PERIOD_1);
        } else if (period.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_PERIOD_PERIOD_3);
        } else {
            bindPeriod = true;

            query.append(_FINDER_COLUMN_PERIOD_PERIOD_2);
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
            query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindPeriod) {
            qPos.add(period);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(bestPriceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<BestPriceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the best price masters where period = &#63; from the database.
     *
     * @param period the period
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByPeriod(String period) throws SystemException {
        for (BestPriceMaster bestPriceMaster : findByPeriod(period,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(bestPriceMaster);
        }
    }

    /**
     * Returns the number of best price masters where period = &#63;.
     *
     * @param period the period
     * @return the number of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPeriod(String period) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PERIOD;

        Object[] finderArgs = new Object[] { period };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_BESTPRICEMASTER_WHERE);

            boolean bindPeriod = false;

            if (period == null) {
                query.append(_FINDER_COLUMN_PERIOD_PERIOD_1);
            } else if (period.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PERIOD_PERIOD_3);
            } else {
                bindPeriod = true;

                query.append(_FINDER_COLUMN_PERIOD_PERIOD_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindPeriod) {
                    qPos.add(period);
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
     * Returns all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
     *
     * @param itemId the item ID
     * @param accountId the account ID
     * @param period the period
     * @param contractNo the contract no
     * @return the matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByBestPriceUnique(String itemId,
        String accountId, String period, String contractNo)
        throws SystemException {
        return findByBestPriceUnique(itemId, accountId, period, contractNo,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param accountId the account ID
     * @param period the period
     * @param contractNo the contract no
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @return the range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByBestPriceUnique(String itemId,
        String accountId, String period, String contractNo, int start, int end)
        throws SystemException {
        return findByBestPriceUnique(itemId, accountId, period, contractNo,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param accountId the account ID
     * @param period the period
     * @param contractNo the contract no
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findByBestPriceUnique(String itemId,
        String accountId, String period, String contractNo, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BESTPRICEUNIQUE;
            finderArgs = new Object[] { itemId, accountId, period, contractNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BESTPRICEUNIQUE;
            finderArgs = new Object[] {
                    itemId, accountId, period, contractNo,
                    
                    start, end, orderByComparator
                };
        }

        List<BestPriceMaster> list = (List<BestPriceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (BestPriceMaster bestPriceMaster : list) {
                if (!Validator.equals(itemId, bestPriceMaster.getItemId()) ||
                        !Validator.equals(accountId,
                            bestPriceMaster.getAccountId()) ||
                        !Validator.equals(period, bestPriceMaster.getPeriod()) ||
                        !Validator.equals(contractNo,
                            bestPriceMaster.getContractNo())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(6 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(6);
            }

            query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

            boolean bindItemId = false;

            if (itemId == null) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_1);
            } else if (itemId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_3);
            } else {
                bindItemId = true;

                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_2);
            }

            boolean bindAccountId = false;

            if (accountId == null) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_1);
            } else if (accountId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_3);
            } else {
                bindAccountId = true;

                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_2);
            }

            boolean bindPeriod = false;

            if (period == null) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_1);
            } else if (period.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_3);
            } else {
                bindPeriod = true;

                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_2);
            }

            boolean bindContractNo = false;

            if (contractNo == null) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_1);
            } else if (contractNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_3);
            } else {
                bindContractNo = true;

                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemId) {
                    qPos.add(itemId);
                }

                if (bindAccountId) {
                    qPos.add(accountId);
                }

                if (bindPeriod) {
                    qPos.add(period);
                }

                if (bindContractNo) {
                    qPos.add(contractNo);
                }

                if (!pagination) {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BestPriceMaster>(list);
                } else {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
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
     * Returns the first best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
     *
     * @param itemId the item ID
     * @param accountId the account ID
     * @param period the period
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByBestPriceUnique_First(String itemId,
        String accountId, String period, String contractNo,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByBestPriceUnique_First(itemId,
                accountId, period, contractNo, orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(10);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(", accountId=");
        msg.append(accountId);

        msg.append(", period=");
        msg.append(period);

        msg.append(", contractNo=");
        msg.append(contractNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the first best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
     *
     * @param itemId the item ID
     * @param accountId the account ID
     * @param period the period
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByBestPriceUnique_First(String itemId,
        String accountId, String period, String contractNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<BestPriceMaster> list = findByBestPriceUnique(itemId, accountId,
                period, contractNo, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
     *
     * @param itemId the item ID
     * @param accountId the account ID
     * @param period the period
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByBestPriceUnique_Last(String itemId,
        String accountId, String period, String contractNo,
        OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByBestPriceUnique_Last(itemId,
                accountId, period, contractNo, orderByComparator);

        if (bestPriceMaster != null) {
            return bestPriceMaster;
        }

        StringBundler msg = new StringBundler(10);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(", accountId=");
        msg.append(accountId);

        msg.append(", period=");
        msg.append(period);

        msg.append(", contractNo=");
        msg.append(contractNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBestPriceMasterException(msg.toString());
    }

    /**
     * Returns the last best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
     *
     * @param itemId the item ID
     * @param accountId the account ID
     * @param period the period
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByBestPriceUnique_Last(String itemId,
        String accountId, String period, String contractNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByBestPriceUnique(itemId, accountId, period, contractNo);

        if (count == 0) {
            return null;
        }

        List<BestPriceMaster> list = findByBestPriceUnique(itemId, accountId,
                period, contractNo, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the best price masters before and after the current best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
     *
     * @param bestPriceMasterSid the primary key of the current best price master
     * @param itemId the item ID
     * @param accountId the account ID
     * @param period the period
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster[] findByBestPriceUnique_PrevAndNext(
        int bestPriceMasterSid, String itemId, String accountId, String period,
        String contractNo, OrderByComparator orderByComparator)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = findByPrimaryKey(bestPriceMasterSid);

        Session session = null;

        try {
            session = openSession();

            BestPriceMaster[] array = new BestPriceMasterImpl[3];

            array[0] = getByBestPriceUnique_PrevAndNext(session,
                    bestPriceMaster, itemId, accountId, period, contractNo,
                    orderByComparator, true);

            array[1] = bestPriceMaster;

            array[2] = getByBestPriceUnique_PrevAndNext(session,
                    bestPriceMaster, itemId, accountId, period, contractNo,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected BestPriceMaster getByBestPriceUnique_PrevAndNext(
        Session session, BestPriceMaster bestPriceMaster, String itemId,
        String accountId, String period, String contractNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_BESTPRICEMASTER_WHERE);

        boolean bindItemId = false;

        if (itemId == null) {
            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_1);
        } else if (itemId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_3);
        } else {
            bindItemId = true;

            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_2);
        }

        boolean bindAccountId = false;

        if (accountId == null) {
            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_1);
        } else if (accountId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_3);
        } else {
            bindAccountId = true;

            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_2);
        }

        boolean bindPeriod = false;

        if (period == null) {
            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_1);
        } else if (period.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_3);
        } else {
            bindPeriod = true;

            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_2);
        }

        boolean bindContractNo = false;

        if (contractNo == null) {
            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_1);
        } else if (contractNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_3);
        } else {
            bindContractNo = true;

            query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_2);
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
            query.append(BestPriceMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindItemId) {
            qPos.add(itemId);
        }

        if (bindAccountId) {
            qPos.add(accountId);
        }

        if (bindPeriod) {
            qPos.add(period);
        }

        if (bindContractNo) {
            qPos.add(contractNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(bestPriceMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<BestPriceMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63; from the database.
     *
     * @param itemId the item ID
     * @param accountId the account ID
     * @param period the period
     * @param contractNo the contract no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByBestPriceUnique(String itemId, String accountId,
        String period, String contractNo) throws SystemException {
        for (BestPriceMaster bestPriceMaster : findByBestPriceUnique(itemId,
                accountId, period, contractNo, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(bestPriceMaster);
        }
    }

    /**
     * Returns the number of best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
     *
     * @param itemId the item ID
     * @param accountId the account ID
     * @param period the period
     * @param contractNo the contract no
     * @return the number of matching best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByBestPriceUnique(String itemId, String accountId,
        String period, String contractNo) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_BESTPRICEUNIQUE;

        Object[] finderArgs = new Object[] { itemId, accountId, period, contractNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(5);

            query.append(_SQL_COUNT_BESTPRICEMASTER_WHERE);

            boolean bindItemId = false;

            if (itemId == null) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_1);
            } else if (itemId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_3);
            } else {
                bindItemId = true;

                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ITEMID_2);
            }

            boolean bindAccountId = false;

            if (accountId == null) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_1);
            } else if (accountId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_3);
            } else {
                bindAccountId = true;

                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_ACCOUNTID_2);
            }

            boolean bindPeriod = false;

            if (period == null) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_1);
            } else if (period.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_3);
            } else {
                bindPeriod = true;

                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_PERIOD_2);
            }

            boolean bindContractNo = false;

            if (contractNo == null) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_1);
            } else if (contractNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_3);
            } else {
                bindContractNo = true;

                query.append(_FINDER_COLUMN_BESTPRICEUNIQUE_CONTRACTNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemId) {
                    qPos.add(itemId);
                }

                if (bindAccountId) {
                    qPos.add(accountId);
                }

                if (bindPeriod) {
                    qPos.add(period);
                }

                if (bindContractNo) {
                    qPos.add(contractNo);
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
     * Caches the best price master in the entity cache if it is enabled.
     *
     * @param bestPriceMaster the best price master
     */
    @Override
    public void cacheResult(BestPriceMaster bestPriceMaster) {
        EntityCacheUtil.putResult(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterImpl.class, bestPriceMaster.getPrimaryKey(),
            bestPriceMaster);

        bestPriceMaster.resetOriginalValues();
    }

    /**
     * Caches the best price masters in the entity cache if it is enabled.
     *
     * @param bestPriceMasters the best price masters
     */
    @Override
    public void cacheResult(List<BestPriceMaster> bestPriceMasters) {
        for (BestPriceMaster bestPriceMaster : bestPriceMasters) {
            if (EntityCacheUtil.getResult(
                        BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
                        BestPriceMasterImpl.class,
                        bestPriceMaster.getPrimaryKey()) == null) {
                cacheResult(bestPriceMaster);
            } else {
                bestPriceMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all best price masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(BestPriceMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(BestPriceMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the best price master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(BestPriceMaster bestPriceMaster) {
        EntityCacheUtil.removeResult(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterImpl.class, bestPriceMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<BestPriceMaster> bestPriceMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (BestPriceMaster bestPriceMaster : bestPriceMasters) {
            EntityCacheUtil.removeResult(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
                BestPriceMasterImpl.class, bestPriceMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new best price master with the primary key. Does not add the best price master to the database.
     *
     * @param bestPriceMasterSid the primary key for the new best price master
     * @return the new best price master
     */
    @Override
    public BestPriceMaster create(int bestPriceMasterSid) {
        BestPriceMaster bestPriceMaster = new BestPriceMasterImpl();

        bestPriceMaster.setNew(true);
        bestPriceMaster.setPrimaryKey(bestPriceMasterSid);

        return bestPriceMaster;
    }

    /**
     * Removes the best price master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param bestPriceMasterSid the primary key of the best price master
     * @return the best price master that was removed
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster remove(int bestPriceMasterSid)
        throws NoSuchBestPriceMasterException, SystemException {
        return remove((Serializable) bestPriceMasterSid);
    }

    /**
     * Removes the best price master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the best price master
     * @return the best price master that was removed
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster remove(Serializable primaryKey)
        throws NoSuchBestPriceMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            BestPriceMaster bestPriceMaster = (BestPriceMaster) session.get(BestPriceMasterImpl.class,
                    primaryKey);

            if (bestPriceMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchBestPriceMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(bestPriceMaster);
        } catch (NoSuchBestPriceMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected BestPriceMaster removeImpl(BestPriceMaster bestPriceMaster)
        throws SystemException {
        bestPriceMaster = toUnwrappedModel(bestPriceMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(bestPriceMaster)) {
                bestPriceMaster = (BestPriceMaster) session.get(BestPriceMasterImpl.class,
                        bestPriceMaster.getPrimaryKeyObj());
            }

            if (bestPriceMaster != null) {
                session.delete(bestPriceMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (bestPriceMaster != null) {
            clearCache(bestPriceMaster);
        }

        return bestPriceMaster;
    }

    @Override
    public BestPriceMaster updateImpl(
        com.stpl.app.model.BestPriceMaster bestPriceMaster)
        throws SystemException {
        bestPriceMaster = toUnwrappedModel(bestPriceMaster);

        boolean isNew = bestPriceMaster.isNew();

        BestPriceMasterModelImpl bestPriceMasterModelImpl = (BestPriceMasterModelImpl) bestPriceMaster;

        Session session = null;

        try {
            session = openSession();

            if (bestPriceMaster.isNew()) {
                session.save(bestPriceMaster);

                bestPriceMaster.setNew(false);
            } else {
                session.merge(bestPriceMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !BestPriceMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((bestPriceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        bestPriceMasterModelImpl.getOriginalItemId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
                    args);

                args = new Object[] { bestPriceMasterModelImpl.getItemId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
                    args);
            }

            if ((bestPriceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        bestPriceMasterModelImpl.getOriginalItemNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
                    args);

                args = new Object[] { bestPriceMasterModelImpl.getItemNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
                    args);
            }

            if ((bestPriceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        bestPriceMasterModelImpl.getOriginalContractNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTNO,
                    args);

                args = new Object[] { bestPriceMasterModelImpl.getContractNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTNO,
                    args);
            }

            if ((bestPriceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        bestPriceMasterModelImpl.getOriginalContractId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID,
                    args);

                args = new Object[] { bestPriceMasterModelImpl.getContractId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID,
                    args);
            }

            if ((bestPriceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        bestPriceMasterModelImpl.getOriginalAccountId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
                    args);

                args = new Object[] { bestPriceMasterModelImpl.getAccountId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
                    args);
            }

            if ((bestPriceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIOD.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        bestPriceMasterModelImpl.getOriginalPeriod()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PERIOD, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIOD,
                    args);

                args = new Object[] { bestPriceMasterModelImpl.getPeriod() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PERIOD, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PERIOD,
                    args);
            }

            if ((bestPriceMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BESTPRICEUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        bestPriceMasterModelImpl.getOriginalItemId(),
                        bestPriceMasterModelImpl.getOriginalAccountId(),
                        bestPriceMasterModelImpl.getOriginalPeriod(),
                        bestPriceMasterModelImpl.getOriginalContractNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BESTPRICEUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BESTPRICEUNIQUE,
                    args);

                args = new Object[] {
                        bestPriceMasterModelImpl.getItemId(),
                        bestPriceMasterModelImpl.getAccountId(),
                        bestPriceMasterModelImpl.getPeriod(),
                        bestPriceMasterModelImpl.getContractNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BESTPRICEUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BESTPRICEUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
            BestPriceMasterImpl.class, bestPriceMaster.getPrimaryKey(),
            bestPriceMaster);

        return bestPriceMaster;
    }

    protected BestPriceMaster toUnwrappedModel(BestPriceMaster bestPriceMaster) {
        if (bestPriceMaster instanceof BestPriceMasterImpl) {
            return bestPriceMaster;
        }

        BestPriceMasterImpl bestPriceMasterImpl = new BestPriceMasterImpl();

        bestPriceMasterImpl.setNew(bestPriceMaster.isNew());
        bestPriceMasterImpl.setPrimaryKey(bestPriceMaster.getPrimaryKey());

        bestPriceMasterImpl.setInitialBestPrice(bestPriceMaster.getInitialBestPrice());
        bestPriceMasterImpl.setCreatedBy(bestPriceMaster.getCreatedBy());
        bestPriceMasterImpl.setItemNo(bestPriceMaster.getItemNo());
        bestPriceMasterImpl.setModifiedBy(bestPriceMaster.getModifiedBy());
        bestPriceMasterImpl.setAccountId(bestPriceMaster.getAccountId());
        bestPriceMasterImpl.setCreatedDate(bestPriceMaster.getCreatedDate());
        bestPriceMasterImpl.setItemId(bestPriceMaster.getItemId());
        bestPriceMasterImpl.setItemDesc(bestPriceMaster.getItemDesc());
        bestPriceMasterImpl.setSellingPrice(bestPriceMaster.getSellingPrice());
        bestPriceMasterImpl.setContractId(bestPriceMaster.getContractId());
        bestPriceMasterImpl.setContractNo(bestPriceMaster.getContractNo());
        bestPriceMasterImpl.setBatchId(bestPriceMaster.getBatchId());
        bestPriceMasterImpl.setBestPriceMasterSid(bestPriceMaster.getBestPriceMasterSid());
        bestPriceMasterImpl.setModifiedDate(bestPriceMaster.getModifiedDate());
        bestPriceMasterImpl.setRecordLockStatus(bestPriceMaster.isRecordLockStatus());
        bestPriceMasterImpl.setBeginningWacPackage(bestPriceMaster.getBeginningWacPackage());
        bestPriceMasterImpl.setInitialDiscount(bestPriceMaster.getInitialDiscount());
        bestPriceMasterImpl.setPeriod(bestPriceMaster.getPeriod());
        bestPriceMasterImpl.setSource(bestPriceMaster.getSource());
        bestPriceMasterImpl.setContractStartDate(bestPriceMaster.getContractStartDate());
        bestPriceMasterImpl.setContractEndDate(bestPriceMaster.getContractEndDate());
        bestPriceMasterImpl.setInboundStatus(bestPriceMaster.getInboundStatus());

        return bestPriceMasterImpl;
    }

    /**
     * Returns the best price master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the best price master
     * @return the best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchBestPriceMasterException, SystemException {
        BestPriceMaster bestPriceMaster = fetchByPrimaryKey(primaryKey);

        if (bestPriceMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchBestPriceMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return bestPriceMaster;
    }

    /**
     * Returns the best price master with the primary key or throws a {@link com.stpl.app.NoSuchBestPriceMasterException} if it could not be found.
     *
     * @param bestPriceMasterSid the primary key of the best price master
     * @return the best price master
     * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster findByPrimaryKey(int bestPriceMasterSid)
        throws NoSuchBestPriceMasterException, SystemException {
        return findByPrimaryKey((Serializable) bestPriceMasterSid);
    }

    /**
     * Returns the best price master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the best price master
     * @return the best price master, or <code>null</code> if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        BestPriceMaster bestPriceMaster = (BestPriceMaster) EntityCacheUtil.getResult(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
                BestPriceMasterImpl.class, primaryKey);

        if (bestPriceMaster == _nullBestPriceMaster) {
            return null;
        }

        if (bestPriceMaster == null) {
            Session session = null;

            try {
                session = openSession();

                bestPriceMaster = (BestPriceMaster) session.get(BestPriceMasterImpl.class,
                        primaryKey);

                if (bestPriceMaster != null) {
                    cacheResult(bestPriceMaster);
                } else {
                    EntityCacheUtil.putResult(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
                        BestPriceMasterImpl.class, primaryKey,
                        _nullBestPriceMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(BestPriceMasterModelImpl.ENTITY_CACHE_ENABLED,
                    BestPriceMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return bestPriceMaster;
    }

    /**
     * Returns the best price master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param bestPriceMasterSid the primary key of the best price master
     * @return the best price master, or <code>null</code> if a best price master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BestPriceMaster fetchByPrimaryKey(int bestPriceMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) bestPriceMasterSid);
    }

    /**
     * Returns all the best price masters.
     *
     * @return the best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the best price masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @return the range of best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the best price masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of best price masters
     * @param end the upper bound of the range of best price masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of best price masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BestPriceMaster> findAll(int start, int end,
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

        List<BestPriceMaster> list = (List<BestPriceMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_BESTPRICEMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_BESTPRICEMASTER;

                if (pagination) {
                    sql = sql.concat(BestPriceMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BestPriceMaster>(list);
                } else {
                    list = (List<BestPriceMaster>) QueryUtil.list(q,
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
     * Removes all the best price masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (BestPriceMaster bestPriceMaster : findAll()) {
            remove(bestPriceMaster);
        }
    }

    /**
     * Returns the number of best price masters.
     *
     * @return the number of best price masters
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

                Query q = session.createQuery(_SQL_COUNT_BESTPRICEMASTER);

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
     * Initializes the best price master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.BestPriceMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<BestPriceMaster>> listenersList = new ArrayList<ModelListener<BestPriceMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<BestPriceMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(BestPriceMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
