package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchSalesMasterException;
import com.stpl.app.model.SalesMaster;
import com.stpl.app.model.impl.SalesMasterImpl;
import com.stpl.app.model.impl.SalesMasterModelImpl;
import com.stpl.app.service.persistence.SalesMasterPersistence;

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
 * The persistence implementation for the sales master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SalesMasterPersistence
 * @see SalesMasterUtil
 * @generated
 */
public class SalesMasterPersistenceImpl extends BasePersistenceImpl<SalesMaster>
    implements SalesMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link SalesMasterUtil} to access the sales master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = SalesMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountId",
            new String[] { String.class.getName() },
            SalesMasterModelImpl.ACCOUNTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTID = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1 = "salesMaster.accountId IS NULL";
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2 = "salesMaster.accountId = ?";
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3 = "(salesMaster.accountId IS NULL OR salesMaster.accountId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMNO = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemNo",
            new String[] { String.class.getName() },
            SalesMasterModelImpl.ITEMNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMNO = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_1 = "salesMaster.itemNo IS NULL";
    private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_2 = "salesMaster.itemNo = ?";
    private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_3 = "(salesMaster.itemNo IS NULL OR salesMaster.itemNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMID = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemId",
            new String[] { String.class.getName() },
            SalesMasterModelImpl.ITEMID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMID = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_1 = "salesMaster.itemId IS NULL";
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_2 = "salesMaster.itemId = ?";
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_3 = "(salesMaster.itemId IS NULL OR salesMaster.itemId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SALESID = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySalesId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SALESID =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySalesId",
            new String[] { String.class.getName() },
            SalesMasterModelImpl.SALESID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SALESID = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySalesId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_SALESID_SALESID_1 = "salesMaster.salesId IS NULL";
    private static final String _FINDER_COLUMN_SALESID_SALESID_2 = "salesMaster.salesId = ?";
    private static final String _FINDER_COLUMN_SALESID_SALESID_3 = "(salesMaster.salesId IS NULL OR salesMaster.salesId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTNO =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountNo",
            new String[] { String.class.getName() },
            SalesMasterModelImpl.ACCOUNTNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTNO = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1 = "salesMaster.accountNo IS NULL";
    private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2 = "salesMaster.accountNo = ?";
    private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3 = "(salesMaster.accountNo IS NULL OR salesMaster.accountNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTID =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContractId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContractId",
            new String[] { String.class.getName() },
            SalesMasterModelImpl.CONTRACTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTRACTID = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContractId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_1 = "salesMaster.contractId IS NULL";
    private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_2 = "salesMaster.contractId = ?";
    private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_3 = "(salesMaster.contractId IS NULL OR salesMaster.contractId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
            new String[] { String.class.getName() },
            SalesMasterModelImpl.COMPANYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_1 = "salesMaster.companyId IS NULL";
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "salesMaster.companyId = ?";
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_3 = "(salesMaster.companyId IS NULL OR salesMaster.companyId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTNO =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContractNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTNO =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContractNo",
            new String[] { String.class.getName() },
            SalesMasterModelImpl.CONTRACTNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTRACTNO = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContractNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_CONTRACTNO_CONTRACTNO_1 = "salesMaster.contractNo IS NULL";
    private static final String _FINDER_COLUMN_CONTRACTNO_CONTRACTNO_2 = "salesMaster.contractNo = ?";
    private static final String _FINDER_COLUMN_CONTRACTNO_CONTRACTNO_3 = "(salesMaster.contractNo IS NULL OR salesMaster.contractNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SALESUNIQUE =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySalesUnique",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SALESUNIQUE =
        new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, SalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySalesUnique",
            new String[] { String.class.getName() },
            SalesMasterModelImpl.SALESID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SALESUNIQUE = new FinderPath(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySalesUnique",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_SALESUNIQUE_SALESID_1 = "salesMaster.salesId IS NULL";
    private static final String _FINDER_COLUMN_SALESUNIQUE_SALESID_2 = "salesMaster.salesId = ?";
    private static final String _FINDER_COLUMN_SALESUNIQUE_SALESID_3 = "(salesMaster.salesId IS NULL OR salesMaster.salesId = '')";
    private static final String _SQL_SELECT_SALESMASTER = "SELECT salesMaster FROM SalesMaster salesMaster";
    private static final String _SQL_SELECT_SALESMASTER_WHERE = "SELECT salesMaster FROM SalesMaster salesMaster WHERE ";
    private static final String _SQL_COUNT_SALESMASTER = "SELECT COUNT(salesMaster) FROM SalesMaster salesMaster";
    private static final String _SQL_COUNT_SALESMASTER_WHERE = "SELECT COUNT(salesMaster) FROM SalesMaster salesMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "salesMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SalesMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SalesMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(SalesMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemNo", "recordSequence", "quantity", "accountId",
                "createdDate", "identifierCodeQualifier", "isActive", "marketId",
                "invoiceDate", "accountName", "docType", "orderReceivedDate",
                "amount", "salesMasterSid", "orderSubtype", "createdBy", "price",
                "uploadDate", "itemId", "priceAdjustmentName",
                "itemCodeQualifier", "contractId", "itemUom", "modifiedDate",
                "customerSubtype", "provisionId", "wholesaleOwnerId", "source",
                "accountNo", "lotNo", "parentItemId", "customerCompanyCode",
                "analysisCode", "accountType", "modifiedBy", "contractNo",
                "batchId", "brandId", "salesId", "companyId", "organizationKey",
                "itemParentNo", "invoiceNumber", "orderType", "recordLockStatus",
                "divisionId", "invoiceLineNumber", "orderNumber",
                "inboundStatus"
            });
    private static SalesMaster _nullSalesMaster = new SalesMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<SalesMaster> toCacheModel() {
                return _nullSalesMasterCacheModel;
            }
        };

    private static CacheModel<SalesMaster> _nullSalesMasterCacheModel = new CacheModel<SalesMaster>() {
            @Override
            public SalesMaster toEntityModel() {
                return _nullSalesMaster;
            }
        };

    public SalesMasterPersistenceImpl() {
        setModelClass(SalesMaster.class);
    }

    /**
     * Returns all the sales masters where accountId = &#63;.
     *
     * @param accountId the account ID
     * @return the matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByAccountId(String accountId)
        throws SystemException {
        return findByAccountId(accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the sales masters where accountId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountId the account ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @return the range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByAccountId(String accountId, int start,
        int end) throws SystemException {
        return findByAccountId(accountId, start, end, null);
    }

    /**
     * Returns an ordered range of all the sales masters where accountId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountId the account ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByAccountId(String accountId, int start,
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

        List<SalesMaster> list = (List<SalesMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SalesMaster salesMaster : list) {
                if (!Validator.equals(accountId, salesMaster.getAccountId())) {
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

            query.append(_SQL_SELECT_SALESMASTER_WHERE);

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
                query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
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
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SalesMaster>(list);
                } else {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first sales master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByAccountId_First(String accountId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByAccountId_First(accountId,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountId=");
        msg.append(accountId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the first sales master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByAccountId_First(String accountId,
        OrderByComparator orderByComparator) throws SystemException {
        List<SalesMaster> list = findByAccountId(accountId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last sales master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByAccountId_Last(String accountId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByAccountId_Last(accountId,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountId=");
        msg.append(accountId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the last sales master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByAccountId_Last(String accountId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByAccountId(accountId);

        if (count == 0) {
            return null;
        }

        List<SalesMaster> list = findByAccountId(accountId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the sales masters before and after the current sales master in the ordered set where accountId = &#63;.
     *
     * @param salesMasterSid the primary key of the current sales master
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster[] findByAccountId_PrevAndNext(int salesMasterSid,
        String accountId, OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = findByPrimaryKey(salesMasterSid);

        Session session = null;

        try {
            session = openSession();

            SalesMaster[] array = new SalesMasterImpl[3];

            array[0] = getByAccountId_PrevAndNext(session, salesMaster,
                    accountId, orderByComparator, true);

            array[1] = salesMaster;

            array[2] = getByAccountId_PrevAndNext(session, salesMaster,
                    accountId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SalesMaster getByAccountId_PrevAndNext(Session session,
        SalesMaster salesMaster, String accountId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SALESMASTER_WHERE);

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
            query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(salesMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SalesMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the sales masters where accountId = &#63; from the database.
     *
     * @param accountId the account ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAccountId(String accountId) throws SystemException {
        for (SalesMaster salesMaster : findByAccountId(accountId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(salesMaster);
        }
    }

    /**
     * Returns the number of sales masters where accountId = &#63;.
     *
     * @param accountId the account ID
     * @return the number of matching sales masters
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

            query.append(_SQL_COUNT_SALESMASTER_WHERE);

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
     * Returns all the sales masters where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @return the matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByItemNo(String itemNo)
        throws SystemException {
        return findByItemNo(itemNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the sales masters where itemNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemNo the item no
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @return the range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByItemNo(String itemNo, int start, int end)
        throws SystemException {
        return findByItemNo(itemNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the sales masters where itemNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemNo the item no
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByItemNo(String itemNo, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
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

        List<SalesMaster> list = (List<SalesMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SalesMaster salesMaster : list) {
                if (!Validator.equals(itemNo, salesMaster.getItemNo())) {
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

            query.append(_SQL_SELECT_SALESMASTER_WHERE);

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
                query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
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
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SalesMaster>(list);
                } else {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first sales master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByItemNo_First(String itemNo,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByItemNo_First(itemNo, orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemNo=");
        msg.append(itemNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the first sales master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByItemNo_First(String itemNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<SalesMaster> list = findByItemNo(itemNo, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last sales master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByItemNo_Last(String itemNo,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByItemNo_Last(itemNo, orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemNo=");
        msg.append(itemNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the last sales master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByItemNo_Last(String itemNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemNo(itemNo);

        if (count == 0) {
            return null;
        }

        List<SalesMaster> list = findByItemNo(itemNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the sales masters before and after the current sales master in the ordered set where itemNo = &#63;.
     *
     * @param salesMasterSid the primary key of the current sales master
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster[] findByItemNo_PrevAndNext(int salesMasterSid,
        String itemNo, OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = findByPrimaryKey(salesMasterSid);

        Session session = null;

        try {
            session = openSession();

            SalesMaster[] array = new SalesMasterImpl[3];

            array[0] = getByItemNo_PrevAndNext(session, salesMaster, itemNo,
                    orderByComparator, true);

            array[1] = salesMaster;

            array[2] = getByItemNo_PrevAndNext(session, salesMaster, itemNo,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SalesMaster getByItemNo_PrevAndNext(Session session,
        SalesMaster salesMaster, String itemNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SALESMASTER_WHERE);

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
            query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(salesMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SalesMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the sales masters where itemNo = &#63; from the database.
     *
     * @param itemNo the item no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemNo(String itemNo) throws SystemException {
        for (SalesMaster salesMaster : findByItemNo(itemNo, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(salesMaster);
        }
    }

    /**
     * Returns the number of sales masters where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @return the number of matching sales masters
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

            query.append(_SQL_COUNT_SALESMASTER_WHERE);

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
     * Returns all the sales masters where itemId = &#63;.
     *
     * @param itemId the item ID
     * @return the matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByItemId(String itemId)
        throws SystemException {
        return findByItemId(itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the sales masters where itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @return the range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByItemId(String itemId, int start, int end)
        throws SystemException {
        return findByItemId(itemId, start, end, null);
    }

    /**
     * Returns an ordered range of all the sales masters where itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByItemId(String itemId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
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

        List<SalesMaster> list = (List<SalesMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SalesMaster salesMaster : list) {
                if (!Validator.equals(itemId, salesMaster.getItemId())) {
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

            query.append(_SQL_SELECT_SALESMASTER_WHERE);

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
                query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
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
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SalesMaster>(list);
                } else {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first sales master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByItemId_First(String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByItemId_First(itemId, orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the first sales master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByItemId_First(String itemId,
        OrderByComparator orderByComparator) throws SystemException {
        List<SalesMaster> list = findByItemId(itemId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last sales master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByItemId_Last(String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByItemId_Last(itemId, orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the last sales master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByItemId_Last(String itemId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemId(itemId);

        if (count == 0) {
            return null;
        }

        List<SalesMaster> list = findByItemId(itemId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the sales masters before and after the current sales master in the ordered set where itemId = &#63;.
     *
     * @param salesMasterSid the primary key of the current sales master
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster[] findByItemId_PrevAndNext(int salesMasterSid,
        String itemId, OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = findByPrimaryKey(salesMasterSid);

        Session session = null;

        try {
            session = openSession();

            SalesMaster[] array = new SalesMasterImpl[3];

            array[0] = getByItemId_PrevAndNext(session, salesMaster, itemId,
                    orderByComparator, true);

            array[1] = salesMaster;

            array[2] = getByItemId_PrevAndNext(session, salesMaster, itemId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SalesMaster getByItemId_PrevAndNext(Session session,
        SalesMaster salesMaster, String itemId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SALESMASTER_WHERE);

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
            query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(salesMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SalesMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the sales masters where itemId = &#63; from the database.
     *
     * @param itemId the item ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemId(String itemId) throws SystemException {
        for (SalesMaster salesMaster : findByItemId(itemId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(salesMaster);
        }
    }

    /**
     * Returns the number of sales masters where itemId = &#63;.
     *
     * @param itemId the item ID
     * @return the number of matching sales masters
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

            query.append(_SQL_COUNT_SALESMASTER_WHERE);

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
     * Returns all the sales masters where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @return the matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findBySalesId(String salesId)
        throws SystemException {
        return findBySalesId(salesId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the sales masters where salesId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param salesId the sales ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @return the range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findBySalesId(String salesId, int start, int end)
        throws SystemException {
        return findBySalesId(salesId, start, end, null);
    }

    /**
     * Returns an ordered range of all the sales masters where salesId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param salesId the sales ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findBySalesId(String salesId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SALESID;
            finderArgs = new Object[] { salesId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SALESID;
            finderArgs = new Object[] { salesId, start, end, orderByComparator };
        }

        List<SalesMaster> list = (List<SalesMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SalesMaster salesMaster : list) {
                if (!Validator.equals(salesId, salesMaster.getSalesId())) {
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

            query.append(_SQL_SELECT_SALESMASTER_WHERE);

            boolean bindSalesId = false;

            if (salesId == null) {
                query.append(_FINDER_COLUMN_SALESID_SALESID_1);
            } else if (salesId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_SALESID_SALESID_3);
            } else {
                bindSalesId = true;

                query.append(_FINDER_COLUMN_SALESID_SALESID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindSalesId) {
                    qPos.add(salesId);
                }

                if (!pagination) {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SalesMaster>(list);
                } else {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first sales master in the ordered set where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findBySalesId_First(String salesId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchBySalesId_First(salesId,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("salesId=");
        msg.append(salesId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the first sales master in the ordered set where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchBySalesId_First(String salesId,
        OrderByComparator orderByComparator) throws SystemException {
        List<SalesMaster> list = findBySalesId(salesId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last sales master in the ordered set where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findBySalesId_Last(String salesId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchBySalesId_Last(salesId, orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("salesId=");
        msg.append(salesId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the last sales master in the ordered set where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchBySalesId_Last(String salesId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBySalesId(salesId);

        if (count == 0) {
            return null;
        }

        List<SalesMaster> list = findBySalesId(salesId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the sales masters before and after the current sales master in the ordered set where salesId = &#63;.
     *
     * @param salesMasterSid the primary key of the current sales master
     * @param salesId the sales ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster[] findBySalesId_PrevAndNext(int salesMasterSid,
        String salesId, OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = findByPrimaryKey(salesMasterSid);

        Session session = null;

        try {
            session = openSession();

            SalesMaster[] array = new SalesMasterImpl[3];

            array[0] = getBySalesId_PrevAndNext(session, salesMaster, salesId,
                    orderByComparator, true);

            array[1] = salesMaster;

            array[2] = getBySalesId_PrevAndNext(session, salesMaster, salesId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SalesMaster getBySalesId_PrevAndNext(Session session,
        SalesMaster salesMaster, String salesId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SALESMASTER_WHERE);

        boolean bindSalesId = false;

        if (salesId == null) {
            query.append(_FINDER_COLUMN_SALESID_SALESID_1);
        } else if (salesId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_SALESID_SALESID_3);
        } else {
            bindSalesId = true;

            query.append(_FINDER_COLUMN_SALESID_SALESID_2);
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
            query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindSalesId) {
            qPos.add(salesId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(salesMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SalesMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the sales masters where salesId = &#63; from the database.
     *
     * @param salesId the sales ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySalesId(String salesId) throws SystemException {
        for (SalesMaster salesMaster : findBySalesId(salesId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(salesMaster);
        }
    }

    /**
     * Returns the number of sales masters where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @return the number of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySalesId(String salesId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SALESID;

        Object[] finderArgs = new Object[] { salesId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_SALESMASTER_WHERE);

            boolean bindSalesId = false;

            if (salesId == null) {
                query.append(_FINDER_COLUMN_SALESID_SALESID_1);
            } else if (salesId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_SALESID_SALESID_3);
            } else {
                bindSalesId = true;

                query.append(_FINDER_COLUMN_SALESID_SALESID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindSalesId) {
                    qPos.add(salesId);
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
     * Returns all the sales masters where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @return the matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByAccountNo(String accountNo)
        throws SystemException {
        return findByAccountNo(accountNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the sales masters where accountNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountNo the account no
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @return the range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByAccountNo(String accountNo, int start,
        int end) throws SystemException {
        return findByAccountNo(accountNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the sales masters where accountNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountNo the account no
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByAccountNo(String accountNo, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO;
            finderArgs = new Object[] { accountNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTNO;
            finderArgs = new Object[] { accountNo, start, end, orderByComparator };
        }

        List<SalesMaster> list = (List<SalesMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SalesMaster salesMaster : list) {
                if (!Validator.equals(accountNo, salesMaster.getAccountNo())) {
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

            query.append(_SQL_SELECT_SALESMASTER_WHERE);

            boolean bindAccountNo = false;

            if (accountNo == null) {
                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1);
            } else if (accountNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3);
            } else {
                bindAccountNo = true;

                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAccountNo) {
                    qPos.add(accountNo);
                }

                if (!pagination) {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SalesMaster>(list);
                } else {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first sales master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByAccountNo_First(String accountNo,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByAccountNo_First(accountNo,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountNo=");
        msg.append(accountNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the first sales master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByAccountNo_First(String accountNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<SalesMaster> list = findByAccountNo(accountNo, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last sales master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByAccountNo_Last(String accountNo,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByAccountNo_Last(accountNo,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountNo=");
        msg.append(accountNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the last sales master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByAccountNo_Last(String accountNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByAccountNo(accountNo);

        if (count == 0) {
            return null;
        }

        List<SalesMaster> list = findByAccountNo(accountNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the sales masters before and after the current sales master in the ordered set where accountNo = &#63;.
     *
     * @param salesMasterSid the primary key of the current sales master
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster[] findByAccountNo_PrevAndNext(int salesMasterSid,
        String accountNo, OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = findByPrimaryKey(salesMasterSid);

        Session session = null;

        try {
            session = openSession();

            SalesMaster[] array = new SalesMasterImpl[3];

            array[0] = getByAccountNo_PrevAndNext(session, salesMaster,
                    accountNo, orderByComparator, true);

            array[1] = salesMaster;

            array[2] = getByAccountNo_PrevAndNext(session, salesMaster,
                    accountNo, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SalesMaster getByAccountNo_PrevAndNext(Session session,
        SalesMaster salesMaster, String accountNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SALESMASTER_WHERE);

        boolean bindAccountNo = false;

        if (accountNo == null) {
            query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1);
        } else if (accountNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3);
        } else {
            bindAccountNo = true;

            query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2);
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
            query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindAccountNo) {
            qPos.add(accountNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(salesMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SalesMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the sales masters where accountNo = &#63; from the database.
     *
     * @param accountNo the account no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAccountNo(String accountNo) throws SystemException {
        for (SalesMaster salesMaster : findByAccountNo(accountNo,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(salesMaster);
        }
    }

    /**
     * Returns the number of sales masters where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @return the number of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByAccountNo(String accountNo) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTNO;

        Object[] finderArgs = new Object[] { accountNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_SALESMASTER_WHERE);

            boolean bindAccountNo = false;

            if (accountNo == null) {
                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1);
            } else if (accountNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3);
            } else {
                bindAccountNo = true;

                query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAccountNo) {
                    qPos.add(accountNo);
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
     * Returns all the sales masters where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @return the matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByContractId(String contractId)
        throws SystemException {
        return findByContractId(contractId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the sales masters where contractId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractId the contract ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @return the range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByContractId(String contractId, int start,
        int end) throws SystemException {
        return findByContractId(contractId, start, end, null);
    }

    /**
     * Returns an ordered range of all the sales masters where contractId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractId the contract ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByContractId(String contractId, int start,
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

        List<SalesMaster> list = (List<SalesMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SalesMaster salesMaster : list) {
                if (!Validator.equals(contractId, salesMaster.getContractId())) {
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

            query.append(_SQL_SELECT_SALESMASTER_WHERE);

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
                query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
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
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SalesMaster>(list);
                } else {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first sales master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByContractId_First(String contractId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByContractId_First(contractId,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractId=");
        msg.append(contractId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the first sales master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByContractId_First(String contractId,
        OrderByComparator orderByComparator) throws SystemException {
        List<SalesMaster> list = findByContractId(contractId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last sales master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByContractId_Last(String contractId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByContractId_Last(contractId,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractId=");
        msg.append(contractId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the last sales master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByContractId_Last(String contractId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContractId(contractId);

        if (count == 0) {
            return null;
        }

        List<SalesMaster> list = findByContractId(contractId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the sales masters before and after the current sales master in the ordered set where contractId = &#63;.
     *
     * @param salesMasterSid the primary key of the current sales master
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster[] findByContractId_PrevAndNext(int salesMasterSid,
        String contractId, OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = findByPrimaryKey(salesMasterSid);

        Session session = null;

        try {
            session = openSession();

            SalesMaster[] array = new SalesMasterImpl[3];

            array[0] = getByContractId_PrevAndNext(session, salesMaster,
                    contractId, orderByComparator, true);

            array[1] = salesMaster;

            array[2] = getByContractId_PrevAndNext(session, salesMaster,
                    contractId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SalesMaster getByContractId_PrevAndNext(Session session,
        SalesMaster salesMaster, String contractId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SALESMASTER_WHERE);

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
            query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(salesMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SalesMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the sales masters where contractId = &#63; from the database.
     *
     * @param contractId the contract ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContractId(String contractId) throws SystemException {
        for (SalesMaster salesMaster : findByContractId(contractId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(salesMaster);
        }
    }

    /**
     * Returns the number of sales masters where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @return the number of matching sales masters
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

            query.append(_SQL_COUNT_SALESMASTER_WHERE);

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
     * Returns all the sales masters where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByCompanyId(String companyId)
        throws SystemException {
        return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the sales masters where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @return the range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByCompanyId(String companyId, int start,
        int end) throws SystemException {
        return findByCompanyId(companyId, start, end, null);
    }

    /**
     * Returns an ordered range of all the sales masters where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByCompanyId(String companyId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
            finderArgs = new Object[] { companyId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
            finderArgs = new Object[] { companyId, start, end, orderByComparator };
        }

        List<SalesMaster> list = (List<SalesMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SalesMaster salesMaster : list) {
                if (!Validator.equals(companyId, salesMaster.getCompanyId())) {
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

            query.append(_SQL_SELECT_SALESMASTER_WHERE);

            boolean bindCompanyId = false;

            if (companyId == null) {
                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_1);
            } else if (companyId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_3);
            } else {
                bindCompanyId = true;

                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyId) {
                    qPos.add(companyId);
                }

                if (!pagination) {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SalesMaster>(list);
                } else {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first sales master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByCompanyId_First(String companyId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByCompanyId_First(companyId,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyId=");
        msg.append(companyId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the first sales master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByCompanyId_First(String companyId,
        OrderByComparator orderByComparator) throws SystemException {
        List<SalesMaster> list = findByCompanyId(companyId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last sales master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByCompanyId_Last(String companyId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByCompanyId_Last(companyId,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyId=");
        msg.append(companyId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the last sales master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByCompanyId_Last(String companyId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCompanyId(companyId);

        if (count == 0) {
            return null;
        }

        List<SalesMaster> list = findByCompanyId(companyId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the sales masters before and after the current sales master in the ordered set where companyId = &#63;.
     *
     * @param salesMasterSid the primary key of the current sales master
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster[] findByCompanyId_PrevAndNext(int salesMasterSid,
        String companyId, OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = findByPrimaryKey(salesMasterSid);

        Session session = null;

        try {
            session = openSession();

            SalesMaster[] array = new SalesMasterImpl[3];

            array[0] = getByCompanyId_PrevAndNext(session, salesMaster,
                    companyId, orderByComparator, true);

            array[1] = salesMaster;

            array[2] = getByCompanyId_PrevAndNext(session, salesMaster,
                    companyId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SalesMaster getByCompanyId_PrevAndNext(Session session,
        SalesMaster salesMaster, String companyId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SALESMASTER_WHERE);

        boolean bindCompanyId = false;

        if (companyId == null) {
            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_1);
        } else if (companyId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_3);
        } else {
            bindCompanyId = true;

            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);
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
            query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCompanyId) {
            qPos.add(companyId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(salesMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SalesMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the sales masters where companyId = &#63; from the database.
     *
     * @param companyId the company ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyId(String companyId) throws SystemException {
        for (SalesMaster salesMaster : findByCompanyId(companyId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(salesMaster);
        }
    }

    /**
     * Returns the number of sales masters where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the number of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyId(String companyId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

        Object[] finderArgs = new Object[] { companyId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_SALESMASTER_WHERE);

            boolean bindCompanyId = false;

            if (companyId == null) {
                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_1);
            } else if (companyId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_3);
            } else {
                bindCompanyId = true;

                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyId) {
                    qPos.add(companyId);
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
     * Returns all the sales masters where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @return the matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByContractNo(String contractNo)
        throws SystemException {
        return findByContractNo(contractNo, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the sales masters where contractNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractNo the contract no
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @return the range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByContractNo(String contractNo, int start,
        int end) throws SystemException {
        return findByContractNo(contractNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the sales masters where contractNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractNo the contract no
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findByContractNo(String contractNo, int start,
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

        List<SalesMaster> list = (List<SalesMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SalesMaster salesMaster : list) {
                if (!Validator.equals(contractNo, salesMaster.getContractNo())) {
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

            query.append(_SQL_SELECT_SALESMASTER_WHERE);

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
                query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
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
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SalesMaster>(list);
                } else {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first sales master in the ordered set where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByContractNo_First(String contractNo,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByContractNo_First(contractNo,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractNo=");
        msg.append(contractNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the first sales master in the ordered set where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByContractNo_First(String contractNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<SalesMaster> list = findByContractNo(contractNo, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last sales master in the ordered set where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByContractNo_Last(String contractNo,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByContractNo_Last(contractNo,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractNo=");
        msg.append(contractNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the last sales master in the ordered set where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByContractNo_Last(String contractNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContractNo(contractNo);

        if (count == 0) {
            return null;
        }

        List<SalesMaster> list = findByContractNo(contractNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the sales masters before and after the current sales master in the ordered set where contractNo = &#63;.
     *
     * @param salesMasterSid the primary key of the current sales master
     * @param contractNo the contract no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster[] findByContractNo_PrevAndNext(int salesMasterSid,
        String contractNo, OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = findByPrimaryKey(salesMasterSid);

        Session session = null;

        try {
            session = openSession();

            SalesMaster[] array = new SalesMasterImpl[3];

            array[0] = getByContractNo_PrevAndNext(session, salesMaster,
                    contractNo, orderByComparator, true);

            array[1] = salesMaster;

            array[2] = getByContractNo_PrevAndNext(session, salesMaster,
                    contractNo, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SalesMaster getByContractNo_PrevAndNext(Session session,
        SalesMaster salesMaster, String contractNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SALESMASTER_WHERE);

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
            query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(salesMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SalesMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the sales masters where contractNo = &#63; from the database.
     *
     * @param contractNo the contract no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContractNo(String contractNo) throws SystemException {
        for (SalesMaster salesMaster : findByContractNo(contractNo,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(salesMaster);
        }
    }

    /**
     * Returns the number of sales masters where contractNo = &#63;.
     *
     * @param contractNo the contract no
     * @return the number of matching sales masters
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

            query.append(_SQL_COUNT_SALESMASTER_WHERE);

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
     * Returns all the sales masters where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @return the matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findBySalesUnique(String salesId)
        throws SystemException {
        return findBySalesUnique(salesId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the sales masters where salesId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param salesId the sales ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @return the range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findBySalesUnique(String salesId, int start,
        int end) throws SystemException {
        return findBySalesUnique(salesId, start, end, null);
    }

    /**
     * Returns an ordered range of all the sales masters where salesId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param salesId the sales ID
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findBySalesUnique(String salesId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SALESUNIQUE;
            finderArgs = new Object[] { salesId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SALESUNIQUE;
            finderArgs = new Object[] { salesId, start, end, orderByComparator };
        }

        List<SalesMaster> list = (List<SalesMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SalesMaster salesMaster : list) {
                if (!Validator.equals(salesId, salesMaster.getSalesId())) {
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

            query.append(_SQL_SELECT_SALESMASTER_WHERE);

            boolean bindSalesId = false;

            if (salesId == null) {
                query.append(_FINDER_COLUMN_SALESUNIQUE_SALESID_1);
            } else if (salesId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_SALESUNIQUE_SALESID_3);
            } else {
                bindSalesId = true;

                query.append(_FINDER_COLUMN_SALESUNIQUE_SALESID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindSalesId) {
                    qPos.add(salesId);
                }

                if (!pagination) {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SalesMaster>(list);
                } else {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first sales master in the ordered set where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findBySalesUnique_First(String salesId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchBySalesUnique_First(salesId,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("salesId=");
        msg.append(salesId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the first sales master in the ordered set where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchBySalesUnique_First(String salesId,
        OrderByComparator orderByComparator) throws SystemException {
        List<SalesMaster> list = findBySalesUnique(salesId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last sales master in the ordered set where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findBySalesUnique_Last(String salesId,
        OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchBySalesUnique_Last(salesId,
                orderByComparator);

        if (salesMaster != null) {
            return salesMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("salesId=");
        msg.append(salesId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSalesMasterException(msg.toString());
    }

    /**
     * Returns the last sales master in the ordered set where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchBySalesUnique_Last(String salesId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBySalesUnique(salesId);

        if (count == 0) {
            return null;
        }

        List<SalesMaster> list = findBySalesUnique(salesId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the sales masters before and after the current sales master in the ordered set where salesId = &#63;.
     *
     * @param salesMasterSid the primary key of the current sales master
     * @param salesId the sales ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster[] findBySalesUnique_PrevAndNext(int salesMasterSid,
        String salesId, OrderByComparator orderByComparator)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = findByPrimaryKey(salesMasterSid);

        Session session = null;

        try {
            session = openSession();

            SalesMaster[] array = new SalesMasterImpl[3];

            array[0] = getBySalesUnique_PrevAndNext(session, salesMaster,
                    salesId, orderByComparator, true);

            array[1] = salesMaster;

            array[2] = getBySalesUnique_PrevAndNext(session, salesMaster,
                    salesId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SalesMaster getBySalesUnique_PrevAndNext(Session session,
        SalesMaster salesMaster, String salesId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SALESMASTER_WHERE);

        boolean bindSalesId = false;

        if (salesId == null) {
            query.append(_FINDER_COLUMN_SALESUNIQUE_SALESID_1);
        } else if (salesId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_SALESUNIQUE_SALESID_3);
        } else {
            bindSalesId = true;

            query.append(_FINDER_COLUMN_SALESUNIQUE_SALESID_2);
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
            query.append(SalesMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindSalesId) {
            qPos.add(salesId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(salesMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SalesMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the sales masters where salesId = &#63; from the database.
     *
     * @param salesId the sales ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySalesUnique(String salesId) throws SystemException {
        for (SalesMaster salesMaster : findBySalesUnique(salesId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(salesMaster);
        }
    }

    /**
     * Returns the number of sales masters where salesId = &#63;.
     *
     * @param salesId the sales ID
     * @return the number of matching sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySalesUnique(String salesId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SALESUNIQUE;

        Object[] finderArgs = new Object[] { salesId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_SALESMASTER_WHERE);

            boolean bindSalesId = false;

            if (salesId == null) {
                query.append(_FINDER_COLUMN_SALESUNIQUE_SALESID_1);
            } else if (salesId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_SALESUNIQUE_SALESID_3);
            } else {
                bindSalesId = true;

                query.append(_FINDER_COLUMN_SALESUNIQUE_SALESID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindSalesId) {
                    qPos.add(salesId);
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
     * Caches the sales master in the entity cache if it is enabled.
     *
     * @param salesMaster the sales master
     */
    @Override
    public void cacheResult(SalesMaster salesMaster) {
        EntityCacheUtil.putResult(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterImpl.class, salesMaster.getPrimaryKey(), salesMaster);

        salesMaster.resetOriginalValues();
    }

    /**
     * Caches the sales masters in the entity cache if it is enabled.
     *
     * @param salesMasters the sales masters
     */
    @Override
    public void cacheResult(List<SalesMaster> salesMasters) {
        for (SalesMaster salesMaster : salesMasters) {
            if (EntityCacheUtil.getResult(
                        SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
                        SalesMasterImpl.class, salesMaster.getPrimaryKey()) == null) {
                cacheResult(salesMaster);
            } else {
                salesMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all sales masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(SalesMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(SalesMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the sales master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(SalesMaster salesMaster) {
        EntityCacheUtil.removeResult(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterImpl.class, salesMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<SalesMaster> salesMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (SalesMaster salesMaster : salesMasters) {
            EntityCacheUtil.removeResult(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
                SalesMasterImpl.class, salesMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new sales master with the primary key. Does not add the sales master to the database.
     *
     * @param salesMasterSid the primary key for the new sales master
     * @return the new sales master
     */
    @Override
    public SalesMaster create(int salesMasterSid) {
        SalesMaster salesMaster = new SalesMasterImpl();

        salesMaster.setNew(true);
        salesMaster.setPrimaryKey(salesMasterSid);

        return salesMaster;
    }

    /**
     * Removes the sales master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param salesMasterSid the primary key of the sales master
     * @return the sales master that was removed
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster remove(int salesMasterSid)
        throws NoSuchSalesMasterException, SystemException {
        return remove((Serializable) salesMasterSid);
    }

    /**
     * Removes the sales master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the sales master
     * @return the sales master that was removed
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster remove(Serializable primaryKey)
        throws NoSuchSalesMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            SalesMaster salesMaster = (SalesMaster) session.get(SalesMasterImpl.class,
                    primaryKey);

            if (salesMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchSalesMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(salesMaster);
        } catch (NoSuchSalesMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected SalesMaster removeImpl(SalesMaster salesMaster)
        throws SystemException {
        salesMaster = toUnwrappedModel(salesMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(salesMaster)) {
                salesMaster = (SalesMaster) session.get(SalesMasterImpl.class,
                        salesMaster.getPrimaryKeyObj());
            }

            if (salesMaster != null) {
                session.delete(salesMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (salesMaster != null) {
            clearCache(salesMaster);
        }

        return salesMaster;
    }

    @Override
    public SalesMaster updateImpl(com.stpl.app.model.SalesMaster salesMaster)
        throws SystemException {
        salesMaster = toUnwrappedModel(salesMaster);

        boolean isNew = salesMaster.isNew();

        SalesMasterModelImpl salesMasterModelImpl = (SalesMasterModelImpl) salesMaster;

        Session session = null;

        try {
            session = openSession();

            if (salesMaster.isNew()) {
                session.save(salesMaster);

                salesMaster.setNew(false);
            } else {
                session.merge(salesMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !SalesMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((salesMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        salesMasterModelImpl.getOriginalAccountId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
                    args);

                args = new Object[] { salesMasterModelImpl.getAccountId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
                    args);
            }

            if ((salesMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        salesMasterModelImpl.getOriginalItemNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
                    args);

                args = new Object[] { salesMasterModelImpl.getItemNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
                    args);
            }

            if ((salesMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        salesMasterModelImpl.getOriginalItemId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
                    args);

                args = new Object[] { salesMasterModelImpl.getItemId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
                    args);
            }

            if ((salesMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SALESID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        salesMasterModelImpl.getOriginalSalesId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SALESID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SALESID,
                    args);

                args = new Object[] { salesMasterModelImpl.getSalesId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SALESID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SALESID,
                    args);
            }

            if ((salesMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        salesMasterModelImpl.getOriginalAccountNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO,
                    args);

                args = new Object[] { salesMasterModelImpl.getAccountNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO,
                    args);
            }

            if ((salesMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        salesMasterModelImpl.getOriginalContractId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID,
                    args);

                args = new Object[] { salesMasterModelImpl.getContractId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID,
                    args);
            }

            if ((salesMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        salesMasterModelImpl.getOriginalCompanyId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);

                args = new Object[] { salesMasterModelImpl.getCompanyId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);
            }

            if ((salesMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        salesMasterModelImpl.getOriginalContractNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTNO,
                    args);

                args = new Object[] { salesMasterModelImpl.getContractNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTNO,
                    args);
            }

            if ((salesMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SALESUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        salesMasterModelImpl.getOriginalSalesId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SALESUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SALESUNIQUE,
                    args);

                args = new Object[] { salesMasterModelImpl.getSalesId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SALESUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SALESUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            SalesMasterImpl.class, salesMaster.getPrimaryKey(), salesMaster);

        return salesMaster;
    }

    protected SalesMaster toUnwrappedModel(SalesMaster salesMaster) {
        if (salesMaster instanceof SalesMasterImpl) {
            return salesMaster;
        }

        SalesMasterImpl salesMasterImpl = new SalesMasterImpl();

        salesMasterImpl.setNew(salesMaster.isNew());
        salesMasterImpl.setPrimaryKey(salesMaster.getPrimaryKey());

        salesMasterImpl.setItemNo(salesMaster.getItemNo());
        salesMasterImpl.setRecordSequence(salesMaster.getRecordSequence());
        salesMasterImpl.setQuantity(salesMaster.getQuantity());
        salesMasterImpl.setAccountId(salesMaster.getAccountId());
        salesMasterImpl.setCreatedDate(salesMaster.getCreatedDate());
        salesMasterImpl.setIdentifierCodeQualifier(salesMaster.getIdentifierCodeQualifier());
        salesMasterImpl.setIsActive(salesMaster.getIsActive());
        salesMasterImpl.setMarketId(salesMaster.getMarketId());
        salesMasterImpl.setInvoiceDate(salesMaster.getInvoiceDate());
        salesMasterImpl.setAccountName(salesMaster.getAccountName());
        salesMasterImpl.setDocType(salesMaster.getDocType());
        salesMasterImpl.setOrderReceivedDate(salesMaster.getOrderReceivedDate());
        salesMasterImpl.setAmount(salesMaster.getAmount());
        salesMasterImpl.setSalesMasterSid(salesMaster.getSalesMasterSid());
        salesMasterImpl.setOrderSubtype(salesMaster.getOrderSubtype());
        salesMasterImpl.setCreatedBy(salesMaster.getCreatedBy());
        salesMasterImpl.setPrice(salesMaster.getPrice());
        salesMasterImpl.setUploadDate(salesMaster.getUploadDate());
        salesMasterImpl.setItemId(salesMaster.getItemId());
        salesMasterImpl.setPriceAdjustmentName(salesMaster.getPriceAdjustmentName());
        salesMasterImpl.setItemCodeQualifier(salesMaster.getItemCodeQualifier());
        salesMasterImpl.setContractId(salesMaster.getContractId());
        salesMasterImpl.setItemUom(salesMaster.getItemUom());
        salesMasterImpl.setModifiedDate(salesMaster.getModifiedDate());
        salesMasterImpl.setCustomerSubtype(salesMaster.getCustomerSubtype());
        salesMasterImpl.setProvisionId(salesMaster.getProvisionId());
        salesMasterImpl.setWholesaleOwnerId(salesMaster.getWholesaleOwnerId());
        salesMasterImpl.setSource(salesMaster.getSource());
        salesMasterImpl.setAccountNo(salesMaster.getAccountNo());
        salesMasterImpl.setLotNo(salesMaster.getLotNo());
        salesMasterImpl.setParentItemId(salesMaster.getParentItemId());
        salesMasterImpl.setCustomerCompanyCode(salesMaster.getCustomerCompanyCode());
        salesMasterImpl.setAnalysisCode(salesMaster.getAnalysisCode());
        salesMasterImpl.setAccountType(salesMaster.getAccountType());
        salesMasterImpl.setModifiedBy(salesMaster.getModifiedBy());
        salesMasterImpl.setContractNo(salesMaster.getContractNo());
        salesMasterImpl.setBatchId(salesMaster.getBatchId());
        salesMasterImpl.setBrandId(salesMaster.getBrandId());
        salesMasterImpl.setSalesId(salesMaster.getSalesId());
        salesMasterImpl.setCompanyId(salesMaster.getCompanyId());
        salesMasterImpl.setOrganizationKey(salesMaster.getOrganizationKey());
        salesMasterImpl.setItemParentNo(salesMaster.getItemParentNo());
        salesMasterImpl.setInvoiceNumber(salesMaster.getInvoiceNumber());
        salesMasterImpl.setOrderType(salesMaster.getOrderType());
        salesMasterImpl.setRecordLockStatus(salesMaster.isRecordLockStatus());
        salesMasterImpl.setDivisionId(salesMaster.getDivisionId());
        salesMasterImpl.setInvoiceLineNumber(salesMaster.getInvoiceLineNumber());
        salesMasterImpl.setOrderNumber(salesMaster.getOrderNumber());
        salesMasterImpl.setInboundStatus(salesMaster.getInboundStatus());

        return salesMasterImpl;
    }

    /**
     * Returns the sales master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the sales master
     * @return the sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchSalesMasterException, SystemException {
        SalesMaster salesMaster = fetchByPrimaryKey(primaryKey);

        if (salesMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchSalesMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return salesMaster;
    }

    /**
     * Returns the sales master with the primary key or throws a {@link com.stpl.app.NoSuchSalesMasterException} if it could not be found.
     *
     * @param salesMasterSid the primary key of the sales master
     * @return the sales master
     * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster findByPrimaryKey(int salesMasterSid)
        throws NoSuchSalesMasterException, SystemException {
        return findByPrimaryKey((Serializable) salesMasterSid);
    }

    /**
     * Returns the sales master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the sales master
     * @return the sales master, or <code>null</code> if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        SalesMaster salesMaster = (SalesMaster) EntityCacheUtil.getResult(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
                SalesMasterImpl.class, primaryKey);

        if (salesMaster == _nullSalesMaster) {
            return null;
        }

        if (salesMaster == null) {
            Session session = null;

            try {
                session = openSession();

                salesMaster = (SalesMaster) session.get(SalesMasterImpl.class,
                        primaryKey);

                if (salesMaster != null) {
                    cacheResult(salesMaster);
                } else {
                    EntityCacheUtil.putResult(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
                        SalesMasterImpl.class, primaryKey, _nullSalesMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(SalesMasterModelImpl.ENTITY_CACHE_ENABLED,
                    SalesMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return salesMaster;
    }

    /**
     * Returns the sales master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param salesMasterSid the primary key of the sales master
     * @return the sales master, or <code>null</code> if a sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesMaster fetchByPrimaryKey(int salesMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) salesMasterSid);
    }

    /**
     * Returns all the sales masters.
     *
     * @return the sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the sales masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @return the range of sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the sales masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of sales masters
     * @param end the upper bound of the range of sales masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesMaster> findAll(int start, int end,
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

        List<SalesMaster> list = (List<SalesMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_SALESMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_SALESMASTER;

                if (pagination) {
                    sql = sql.concat(SalesMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SalesMaster>(list);
                } else {
                    list = (List<SalesMaster>) QueryUtil.list(q, getDialect(),
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
     * Removes all the sales masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (SalesMaster salesMaster : findAll()) {
            remove(salesMaster);
        }
    }

    /**
     * Returns the number of sales masters.
     *
     * @return the number of sales masters
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

                Query q = session.createQuery(_SQL_COUNT_SALESMASTER);

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
     * Initializes the sales master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.SalesMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<SalesMaster>> listenersList = new ArrayList<ModelListener<SalesMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<SalesMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(SalesMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
