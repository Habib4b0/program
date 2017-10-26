package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchActualsMasterException;
import com.stpl.app.model.ActualsMaster;
import com.stpl.app.model.impl.ActualsMasterImpl;
import com.stpl.app.model.impl.ActualsMasterModelImpl;
import com.stpl.app.service.persistence.ActualsMasterPersistence;

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
 * The persistence implementation for the actuals master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ActualsMasterPersistence
 * @see ActualsMasterUtil
 * @generated
 */
public class ActualsMasterPersistenceImpl extends BasePersistenceImpl<ActualsMaster>
    implements ActualsMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ActualsMasterUtil} to access the actuals master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ActualsMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByAccountId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByAccountId", new String[] { String.class.getName() },
            ActualsMasterModelImpl.ACCOUNTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1 = "actualsMaster.accountId IS NULL";
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2 = "actualsMaster.accountId = ?";
    private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3 = "(actualsMaster.accountId IS NULL OR actualsMaster.accountId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTUALID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByActualId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActualId", new String[] { String.class.getName() },
            ActualsMasterModelImpl.ACTUALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTUALID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActualId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ACTUALID_ACTUALID_1 = "actualsMaster.actualId IS NULL";
    private static final String _FINDER_COLUMN_ACTUALID_ACTUALID_2 = "actualsMaster.actualId = ?";
    private static final String _FINDER_COLUMN_ACTUALID_ACTUALID_3 = "(actualsMaster.actualId IS NULL OR actualsMaster.actualId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DIVISIONID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByDivisionId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DIVISIONID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByDivisionId", new String[] { String.class.getName() },
            ActualsMasterModelImpl.DIVISIONID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_DIVISIONID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDivisionId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_DIVISIONID_DIVISIONID_1 = "actualsMaster.divisionId IS NULL";
    private static final String _FINDER_COLUMN_DIVISIONID_DIVISIONID_2 = "actualsMaster.divisionId = ?";
    private static final String _FINDER_COLUMN_DIVISIONID_DIVISIONID_3 = "(actualsMaster.divisionId IS NULL OR actualsMaster.divisionId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByContractId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByContractId", new String[] { String.class.getName() },
            ActualsMasterModelImpl.CONTRACTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTRACTID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContractId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_1 = "actualsMaster.contractId IS NULL";
    private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_2 = "actualsMaster.contractId = ?";
    private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_3 = "(actualsMaster.contractId IS NULL OR actualsMaster.contractId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROVISIONID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByProvisionId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROVISIONID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByProvisionId", new String[] { String.class.getName() },
            ActualsMasterModelImpl.PROVISIONID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROVISIONID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProvisionId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_PROVISIONID_PROVISIONID_1 = "actualsMaster.provisionId IS NULL";
    private static final String _FINDER_COLUMN_PROVISIONID_PROVISIONID_2 = "actualsMaster.provisionId = ?";
    private static final String _FINDER_COLUMN_PROVISIONID_PROVISIONID_3 = "(actualsMaster.provisionId IS NULL OR actualsMaster.provisionId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByItemId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemId", new String[] { String.class.getName() },
            ActualsMasterModelImpl.ITEMID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_1 = "actualsMaster.itemId IS NULL";
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_2 = "actualsMaster.itemId = ?";
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_3 = "(actualsMaster.itemId IS NULL OR actualsMaster.itemId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMNO = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByItemNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemNo", new String[] { String.class.getName() },
            ActualsMasterModelImpl.ITEMNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMNO = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_1 = "actualsMaster.itemNo IS NULL";
    private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_2 = "actualsMaster.itemNo = ?";
    private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_3 = "(actualsMaster.itemNo IS NULL OR actualsMaster.itemNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTNO =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByAccountNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByAccountNo", new String[] { String.class.getName() },
            ActualsMasterModelImpl.ACCOUNTNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTNO = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1 = "actualsMaster.accountNo IS NULL";
    private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2 = "actualsMaster.accountNo = ?";
    private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3 = "(actualsMaster.accountNo IS NULL OR actualsMaster.accountNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByMarketId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByMarketId", new String[] { String.class.getName() },
            ActualsMasterModelImpl.MARKETID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MARKETID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMarketId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_MARKETID_MARKETID_1 = "actualsMaster.marketId IS NULL";
    private static final String _FINDER_COLUMN_MARKETID_MARKETID_2 = "actualsMaster.marketId = ?";
    private static final String _FINDER_COLUMN_MARKETID_MARKETID_3 = "(actualsMaster.marketId IS NULL OR actualsMaster.marketId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BRANDID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByBrandId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BRANDID =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByBrandId", new String[] { String.class.getName() },
            ActualsMasterModelImpl.BRANDID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_BRANDID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBrandId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_BRANDID_BRANDID_1 = "actualsMaster.brandId IS NULL";
    private static final String _FINDER_COLUMN_BRANDID_BRANDID_2 = "actualsMaster.brandId = ?";
    private static final String _FINDER_COLUMN_BRANDID_BRANDID_3 = "(actualsMaster.brandId IS NULL OR actualsMaster.brandId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTUALSUNIQUE =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByActualsUnique",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALSUNIQUE =
        new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
            ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActualsUnique", new String[] { String.class.getName() },
            ActualsMasterModelImpl.ACTUALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTUALSUNIQUE = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActualsUnique",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_1 = "actualsMaster.actualId IS NULL";
    private static final String _FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_2 = "actualsMaster.actualId = ?";
    private static final String _FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_3 = "(actualsMaster.actualId IS NULL OR actualsMaster.actualId = '')";
    private static final String _SQL_SELECT_ACTUALSMASTER = "SELECT actualsMaster FROM ActualsMaster actualsMaster";
    private static final String _SQL_SELECT_ACTUALSMASTER_WHERE = "SELECT actualsMaster FROM ActualsMaster actualsMaster WHERE ";
    private static final String _SQL_COUNT_ACTUALSMASTER = "SELECT COUNT(actualsMaster) FROM ActualsMaster actualsMaster";
    private static final String _SQL_COUNT_ACTUALSMASTER_WHERE = "SELECT COUNT(actualsMaster) FROM ActualsMaster actualsMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "actualsMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ActualsMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ActualsMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ActualsMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "quantityInclusion", "mandatedDiscountAmount", "itemNo",
                "analysisCode", "recordSequence", "modifiedBy",
                "settlementMethodNo", "quantity", "accountId", "createdDate",
                "provisionClaimIndicator", "dispensedDate", "isActive",
                "batchId", "accrualActualEndDate", "marketId", "brandId",
                "accountName", "amount", "actualsMasterSid",
                "acctIdentifierCodeQualifier", "organizationKey", "createdBy",
                "accrualProcessed", "parentcomDivmktBrandProdkey",
                "cashPaidDate", "salesAmount", "accrualActualStartDate",
                "settlementNo", "price", "uploadDate", "claimIndicator",
                "itemId", "priceAdjustmentName", "contractId", "modifiedDate",
                "actualId", "provisionId", "sentOut", "recordLockStatus",
                "divisionId", "itemIdentifierCodeQualifier", "programStateCode",
                "source", "invoiceLineNo", "accountNo", "comDivMktBrandProdKey",
                "inboundStatus"
            });
    private static ActualsMaster _nullActualsMaster = new ActualsMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ActualsMaster> toCacheModel() {
                return _nullActualsMasterCacheModel;
            }
        };

    private static CacheModel<ActualsMaster> _nullActualsMasterCacheModel = new CacheModel<ActualsMaster>() {
            @Override
            public ActualsMaster toEntityModel() {
                return _nullActualsMaster;
            }
        };

    public ActualsMasterPersistenceImpl() {
        setModelClass(ActualsMaster.class);
    }

    /**
     * Returns all the actuals masters where accountId = &#63;.
     *
     * @param accountId the account ID
     * @return the matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByAccountId(String accountId)
        throws SystemException {
        return findByAccountId(accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the actuals masters where accountId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountId the account ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByAccountId(String accountId, int start,
        int end) throws SystemException {
        return findByAccountId(accountId, start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters where accountId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountId the account ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByAccountId(String accountId, int start,
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

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ActualsMaster actualsMaster : list) {
                if (!Validator.equals(accountId, actualsMaster.getAccountId())) {
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

            query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
                query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Returns the first actuals master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByAccountId_First(String accountId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByAccountId_First(accountId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountId=");
        msg.append(accountId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the first actuals master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByAccountId_First(String accountId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ActualsMaster> list = findByAccountId(accountId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last actuals master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByAccountId_Last(String accountId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByAccountId_Last(accountId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountId=");
        msg.append(accountId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the last actuals master in the ordered set where accountId = &#63;.
     *
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByAccountId_Last(String accountId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByAccountId(accountId);

        if (count == 0) {
            return null;
        }

        List<ActualsMaster> list = findByAccountId(accountId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the actuals masters before and after the current actuals master in the ordered set where accountId = &#63;.
     *
     * @param actualsMasterSid the primary key of the current actuals master
     * @param accountId the account ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster[] findByAccountId_PrevAndNext(int actualsMasterSid,
        String accountId, OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

        Session session = null;

        try {
            session = openSession();

            ActualsMaster[] array = new ActualsMasterImpl[3];

            array[0] = getByAccountId_PrevAndNext(session, actualsMaster,
                    accountId, orderByComparator, true);

            array[1] = actualsMaster;

            array[2] = getByAccountId_PrevAndNext(session, actualsMaster,
                    accountId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ActualsMaster getByAccountId_PrevAndNext(Session session,
        ActualsMaster actualsMaster, String accountId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
            query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ActualsMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the actuals masters where accountId = &#63; from the database.
     *
     * @param accountId the account ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAccountId(String accountId) throws SystemException {
        for (ActualsMaster actualsMaster : findByAccountId(accountId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters where accountId = &#63;.
     *
     * @param accountId the account ID
     * @return the number of matching actuals masters
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

            query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

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
     * Returns all the actuals masters where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @return the matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByActualId(String actualId)
        throws SystemException {
        return findByActualId(actualId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the actuals masters where actualId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param actualId the actual ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByActualId(String actualId, int start,
        int end) throws SystemException {
        return findByActualId(actualId, start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters where actualId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param actualId the actual ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByActualId(String actualId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALID;
            finderArgs = new Object[] { actualId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTUALID;
            finderArgs = new Object[] { actualId, start, end, orderByComparator };
        }

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ActualsMaster actualsMaster : list) {
                if (!Validator.equals(actualId, actualsMaster.getActualId())) {
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

            query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

            boolean bindActualId = false;

            if (actualId == null) {
                query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_1);
            } else if (actualId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_3);
            } else {
                bindActualId = true;

                query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindActualId) {
                    qPos.add(actualId);
                }

                if (!pagination) {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Returns the first actuals master in the ordered set where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByActualId_First(String actualId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByActualId_First(actualId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("actualId=");
        msg.append(actualId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the first actuals master in the ordered set where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByActualId_First(String actualId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ActualsMaster> list = findByActualId(actualId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last actuals master in the ordered set where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByActualId_Last(String actualId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByActualId_Last(actualId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("actualId=");
        msg.append(actualId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the last actuals master in the ordered set where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByActualId_Last(String actualId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByActualId(actualId);

        if (count == 0) {
            return null;
        }

        List<ActualsMaster> list = findByActualId(actualId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the actuals masters before and after the current actuals master in the ordered set where actualId = &#63;.
     *
     * @param actualsMasterSid the primary key of the current actuals master
     * @param actualId the actual ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster[] findByActualId_PrevAndNext(int actualsMasterSid,
        String actualId, OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

        Session session = null;

        try {
            session = openSession();

            ActualsMaster[] array = new ActualsMasterImpl[3];

            array[0] = getByActualId_PrevAndNext(session, actualsMaster,
                    actualId, orderByComparator, true);

            array[1] = actualsMaster;

            array[2] = getByActualId_PrevAndNext(session, actualsMaster,
                    actualId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ActualsMaster getByActualId_PrevAndNext(Session session,
        ActualsMaster actualsMaster, String actualId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

        boolean bindActualId = false;

        if (actualId == null) {
            query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_1);
        } else if (actualId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_3);
        } else {
            bindActualId = true;

            query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_2);
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
            query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindActualId) {
            qPos.add(actualId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ActualsMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the actuals masters where actualId = &#63; from the database.
     *
     * @param actualId the actual ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActualId(String actualId) throws SystemException {
        for (ActualsMaster actualsMaster : findByActualId(actualId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @return the number of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActualId(String actualId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTUALID;

        Object[] finderArgs = new Object[] { actualId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

            boolean bindActualId = false;

            if (actualId == null) {
                query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_1);
            } else if (actualId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_3);
            } else {
                bindActualId = true;

                query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindActualId) {
                    qPos.add(actualId);
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
     * Returns all the actuals masters where divisionId = &#63;.
     *
     * @param divisionId the division ID
     * @return the matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByDivisionId(String divisionId)
        throws SystemException {
        return findByDivisionId(divisionId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the actuals masters where divisionId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param divisionId the division ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByDivisionId(String divisionId, int start,
        int end) throws SystemException {
        return findByDivisionId(divisionId, start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters where divisionId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param divisionId the division ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByDivisionId(String divisionId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DIVISIONID;
            finderArgs = new Object[] { divisionId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DIVISIONID;
            finderArgs = new Object[] { divisionId, start, end, orderByComparator };
        }

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ActualsMaster actualsMaster : list) {
                if (!Validator.equals(divisionId, actualsMaster.getDivisionId())) {
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

            query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

            boolean bindDivisionId = false;

            if (divisionId == null) {
                query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_1);
            } else if (divisionId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_3);
            } else {
                bindDivisionId = true;

                query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindDivisionId) {
                    qPos.add(divisionId);
                }

                if (!pagination) {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Returns the first actuals master in the ordered set where divisionId = &#63;.
     *
     * @param divisionId the division ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByDivisionId_First(String divisionId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByDivisionId_First(divisionId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("divisionId=");
        msg.append(divisionId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the first actuals master in the ordered set where divisionId = &#63;.
     *
     * @param divisionId the division ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByDivisionId_First(String divisionId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ActualsMaster> list = findByDivisionId(divisionId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last actuals master in the ordered set where divisionId = &#63;.
     *
     * @param divisionId the division ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByDivisionId_Last(String divisionId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByDivisionId_Last(divisionId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("divisionId=");
        msg.append(divisionId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the last actuals master in the ordered set where divisionId = &#63;.
     *
     * @param divisionId the division ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByDivisionId_Last(String divisionId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByDivisionId(divisionId);

        if (count == 0) {
            return null;
        }

        List<ActualsMaster> list = findByDivisionId(divisionId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the actuals masters before and after the current actuals master in the ordered set where divisionId = &#63;.
     *
     * @param actualsMasterSid the primary key of the current actuals master
     * @param divisionId the division ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster[] findByDivisionId_PrevAndNext(int actualsMasterSid,
        String divisionId, OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

        Session session = null;

        try {
            session = openSession();

            ActualsMaster[] array = new ActualsMasterImpl[3];

            array[0] = getByDivisionId_PrevAndNext(session, actualsMaster,
                    divisionId, orderByComparator, true);

            array[1] = actualsMaster;

            array[2] = getByDivisionId_PrevAndNext(session, actualsMaster,
                    divisionId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ActualsMaster getByDivisionId_PrevAndNext(Session session,
        ActualsMaster actualsMaster, String divisionId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

        boolean bindDivisionId = false;

        if (divisionId == null) {
            query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_1);
        } else if (divisionId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_3);
        } else {
            bindDivisionId = true;

            query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_2);
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
            query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindDivisionId) {
            qPos.add(divisionId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ActualsMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the actuals masters where divisionId = &#63; from the database.
     *
     * @param divisionId the division ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByDivisionId(String divisionId) throws SystemException {
        for (ActualsMaster actualsMaster : findByDivisionId(divisionId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters where divisionId = &#63;.
     *
     * @param divisionId the division ID
     * @return the number of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByDivisionId(String divisionId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_DIVISIONID;

        Object[] finderArgs = new Object[] { divisionId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

            boolean bindDivisionId = false;

            if (divisionId == null) {
                query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_1);
            } else if (divisionId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_3);
            } else {
                bindDivisionId = true;

                query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindDivisionId) {
                    qPos.add(divisionId);
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
     * Returns all the actuals masters where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @return the matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByContractId(String contractId)
        throws SystemException {
        return findByContractId(contractId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the actuals masters where contractId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractId the contract ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByContractId(String contractId, int start,
        int end) throws SystemException {
        return findByContractId(contractId, start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters where contractId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contractId the contract ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByContractId(String contractId, int start,
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

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ActualsMaster actualsMaster : list) {
                if (!Validator.equals(contractId, actualsMaster.getContractId())) {
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

            query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
                query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Returns the first actuals master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByContractId_First(String contractId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByContractId_First(contractId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractId=");
        msg.append(contractId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the first actuals master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByContractId_First(String contractId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ActualsMaster> list = findByContractId(contractId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last actuals master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByContractId_Last(String contractId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByContractId_Last(contractId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contractId=");
        msg.append(contractId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the last actuals master in the ordered set where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByContractId_Last(String contractId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContractId(contractId);

        if (count == 0) {
            return null;
        }

        List<ActualsMaster> list = findByContractId(contractId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the actuals masters before and after the current actuals master in the ordered set where contractId = &#63;.
     *
     * @param actualsMasterSid the primary key of the current actuals master
     * @param contractId the contract ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster[] findByContractId_PrevAndNext(int actualsMasterSid,
        String contractId, OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

        Session session = null;

        try {
            session = openSession();

            ActualsMaster[] array = new ActualsMasterImpl[3];

            array[0] = getByContractId_PrevAndNext(session, actualsMaster,
                    contractId, orderByComparator, true);

            array[1] = actualsMaster;

            array[2] = getByContractId_PrevAndNext(session, actualsMaster,
                    contractId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ActualsMaster getByContractId_PrevAndNext(Session session,
        ActualsMaster actualsMaster, String contractId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
            query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ActualsMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the actuals masters where contractId = &#63; from the database.
     *
     * @param contractId the contract ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContractId(String contractId) throws SystemException {
        for (ActualsMaster actualsMaster : findByContractId(contractId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters where contractId = &#63;.
     *
     * @param contractId the contract ID
     * @return the number of matching actuals masters
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

            query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

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
     * Returns all the actuals masters where provisionId = &#63;.
     *
     * @param provisionId the provision ID
     * @return the matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByProvisionId(String provisionId)
        throws SystemException {
        return findByProvisionId(provisionId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the actuals masters where provisionId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param provisionId the provision ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByProvisionId(String provisionId, int start,
        int end) throws SystemException {
        return findByProvisionId(provisionId, start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters where provisionId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param provisionId the provision ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByProvisionId(String provisionId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROVISIONID;
            finderArgs = new Object[] { provisionId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROVISIONID;
            finderArgs = new Object[] { provisionId, start, end, orderByComparator };
        }

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ActualsMaster actualsMaster : list) {
                if (!Validator.equals(provisionId,
                            actualsMaster.getProvisionId())) {
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

            query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

            boolean bindProvisionId = false;

            if (provisionId == null) {
                query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_1);
            } else if (provisionId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_3);
            } else {
                bindProvisionId = true;

                query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindProvisionId) {
                    qPos.add(provisionId);
                }

                if (!pagination) {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Returns the first actuals master in the ordered set where provisionId = &#63;.
     *
     * @param provisionId the provision ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByProvisionId_First(String provisionId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByProvisionId_First(provisionId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("provisionId=");
        msg.append(provisionId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the first actuals master in the ordered set where provisionId = &#63;.
     *
     * @param provisionId the provision ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByProvisionId_First(String provisionId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ActualsMaster> list = findByProvisionId(provisionId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last actuals master in the ordered set where provisionId = &#63;.
     *
     * @param provisionId the provision ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByProvisionId_Last(String provisionId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByProvisionId_Last(provisionId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("provisionId=");
        msg.append(provisionId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the last actuals master in the ordered set where provisionId = &#63;.
     *
     * @param provisionId the provision ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByProvisionId_Last(String provisionId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByProvisionId(provisionId);

        if (count == 0) {
            return null;
        }

        List<ActualsMaster> list = findByProvisionId(provisionId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the actuals masters before and after the current actuals master in the ordered set where provisionId = &#63;.
     *
     * @param actualsMasterSid the primary key of the current actuals master
     * @param provisionId the provision ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster[] findByProvisionId_PrevAndNext(int actualsMasterSid,
        String provisionId, OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

        Session session = null;

        try {
            session = openSession();

            ActualsMaster[] array = new ActualsMasterImpl[3];

            array[0] = getByProvisionId_PrevAndNext(session, actualsMaster,
                    provisionId, orderByComparator, true);

            array[1] = actualsMaster;

            array[2] = getByProvisionId_PrevAndNext(session, actualsMaster,
                    provisionId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ActualsMaster getByProvisionId_PrevAndNext(Session session,
        ActualsMaster actualsMaster, String provisionId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

        boolean bindProvisionId = false;

        if (provisionId == null) {
            query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_1);
        } else if (provisionId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_3);
        } else {
            bindProvisionId = true;

            query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_2);
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
            query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindProvisionId) {
            qPos.add(provisionId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ActualsMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the actuals masters where provisionId = &#63; from the database.
     *
     * @param provisionId the provision ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByProvisionId(String provisionId)
        throws SystemException {
        for (ActualsMaster actualsMaster : findByProvisionId(provisionId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters where provisionId = &#63;.
     *
     * @param provisionId the provision ID
     * @return the number of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProvisionId(String provisionId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROVISIONID;

        Object[] finderArgs = new Object[] { provisionId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

            boolean bindProvisionId = false;

            if (provisionId == null) {
                query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_1);
            } else if (provisionId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_3);
            } else {
                bindProvisionId = true;

                query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindProvisionId) {
                    qPos.add(provisionId);
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
     * Returns all the actuals masters where itemId = &#63;.
     *
     * @param itemId the item ID
     * @return the matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByItemId(String itemId)
        throws SystemException {
        return findByItemId(itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the actuals masters where itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByItemId(String itemId, int start, int end)
        throws SystemException {
        return findByItemId(itemId, start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters where itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByItemId(String itemId, int start, int end,
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

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ActualsMaster actualsMaster : list) {
                if (!Validator.equals(itemId, actualsMaster.getItemId())) {
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

            query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
                query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Returns the first actuals master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByItemId_First(String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByItemId_First(itemId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the first actuals master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByItemId_First(String itemId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ActualsMaster> list = findByItemId(itemId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last actuals master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByItemId_Last(String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByItemId_Last(itemId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the last actuals master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByItemId_Last(String itemId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemId(itemId);

        if (count == 0) {
            return null;
        }

        List<ActualsMaster> list = findByItemId(itemId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the actuals masters before and after the current actuals master in the ordered set where itemId = &#63;.
     *
     * @param actualsMasterSid the primary key of the current actuals master
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster[] findByItemId_PrevAndNext(int actualsMasterSid,
        String itemId, OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

        Session session = null;

        try {
            session = openSession();

            ActualsMaster[] array = new ActualsMasterImpl[3];

            array[0] = getByItemId_PrevAndNext(session, actualsMaster, itemId,
                    orderByComparator, true);

            array[1] = actualsMaster;

            array[2] = getByItemId_PrevAndNext(session, actualsMaster, itemId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ActualsMaster getByItemId_PrevAndNext(Session session,
        ActualsMaster actualsMaster, String itemId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
            query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ActualsMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the actuals masters where itemId = &#63; from the database.
     *
     * @param itemId the item ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemId(String itemId) throws SystemException {
        for (ActualsMaster actualsMaster : findByItemId(itemId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters where itemId = &#63;.
     *
     * @param itemId the item ID
     * @return the number of matching actuals masters
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

            query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

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
     * Returns all the actuals masters where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @return the matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByItemNo(String itemNo)
        throws SystemException {
        return findByItemNo(itemNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the actuals masters where itemNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemNo the item no
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByItemNo(String itemNo, int start, int end)
        throws SystemException {
        return findByItemNo(itemNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters where itemNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemNo the item no
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByItemNo(String itemNo, int start, int end,
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

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ActualsMaster actualsMaster : list) {
                if (!Validator.equals(itemNo, actualsMaster.getItemNo())) {
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

            query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
                query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Returns the first actuals master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByItemNo_First(String itemNo,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByItemNo_First(itemNo,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemNo=");
        msg.append(itemNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the first actuals master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByItemNo_First(String itemNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<ActualsMaster> list = findByItemNo(itemNo, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last actuals master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByItemNo_Last(String itemNo,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByItemNo_Last(itemNo,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemNo=");
        msg.append(itemNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the last actuals master in the ordered set where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByItemNo_Last(String itemNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemNo(itemNo);

        if (count == 0) {
            return null;
        }

        List<ActualsMaster> list = findByItemNo(itemNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the actuals masters before and after the current actuals master in the ordered set where itemNo = &#63;.
     *
     * @param actualsMasterSid the primary key of the current actuals master
     * @param itemNo the item no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster[] findByItemNo_PrevAndNext(int actualsMasterSid,
        String itemNo, OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

        Session session = null;

        try {
            session = openSession();

            ActualsMaster[] array = new ActualsMasterImpl[3];

            array[0] = getByItemNo_PrevAndNext(session, actualsMaster, itemNo,
                    orderByComparator, true);

            array[1] = actualsMaster;

            array[2] = getByItemNo_PrevAndNext(session, actualsMaster, itemNo,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ActualsMaster getByItemNo_PrevAndNext(Session session,
        ActualsMaster actualsMaster, String itemNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
            query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ActualsMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the actuals masters where itemNo = &#63; from the database.
     *
     * @param itemNo the item no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemNo(String itemNo) throws SystemException {
        for (ActualsMaster actualsMaster : findByItemNo(itemNo,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters where itemNo = &#63;.
     *
     * @param itemNo the item no
     * @return the number of matching actuals masters
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

            query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

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
     * Returns all the actuals masters where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @return the matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByAccountNo(String accountNo)
        throws SystemException {
        return findByAccountNo(accountNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the actuals masters where accountNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountNo the account no
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByAccountNo(String accountNo, int start,
        int end) throws SystemException {
        return findByAccountNo(accountNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters where accountNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param accountNo the account no
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByAccountNo(String accountNo, int start,
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

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ActualsMaster actualsMaster : list) {
                if (!Validator.equals(accountNo, actualsMaster.getAccountNo())) {
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

            query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
                query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Returns the first actuals master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByAccountNo_First(String accountNo,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByAccountNo_First(accountNo,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountNo=");
        msg.append(accountNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the first actuals master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByAccountNo_First(String accountNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<ActualsMaster> list = findByAccountNo(accountNo, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last actuals master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByAccountNo_Last(String accountNo,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByAccountNo_Last(accountNo,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("accountNo=");
        msg.append(accountNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the last actuals master in the ordered set where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByAccountNo_Last(String accountNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByAccountNo(accountNo);

        if (count == 0) {
            return null;
        }

        List<ActualsMaster> list = findByAccountNo(accountNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the actuals masters before and after the current actuals master in the ordered set where accountNo = &#63;.
     *
     * @param actualsMasterSid the primary key of the current actuals master
     * @param accountNo the account no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster[] findByAccountNo_PrevAndNext(int actualsMasterSid,
        String accountNo, OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

        Session session = null;

        try {
            session = openSession();

            ActualsMaster[] array = new ActualsMasterImpl[3];

            array[0] = getByAccountNo_PrevAndNext(session, actualsMaster,
                    accountNo, orderByComparator, true);

            array[1] = actualsMaster;

            array[2] = getByAccountNo_PrevAndNext(session, actualsMaster,
                    accountNo, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ActualsMaster getByAccountNo_PrevAndNext(Session session,
        ActualsMaster actualsMaster, String accountNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
            query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ActualsMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the actuals masters where accountNo = &#63; from the database.
     *
     * @param accountNo the account no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAccountNo(String accountNo) throws SystemException {
        for (ActualsMaster actualsMaster : findByAccountNo(accountNo,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters where accountNo = &#63;.
     *
     * @param accountNo the account no
     * @return the number of matching actuals masters
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

            query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

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
     * Returns all the actuals masters where marketId = &#63;.
     *
     * @param marketId the market ID
     * @return the matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByMarketId(String marketId)
        throws SystemException {
        return findByMarketId(marketId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the actuals masters where marketId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param marketId the market ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByMarketId(String marketId, int start,
        int end) throws SystemException {
        return findByMarketId(marketId, start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters where marketId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param marketId the market ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByMarketId(String marketId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETID;
            finderArgs = new Object[] { marketId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETID;
            finderArgs = new Object[] { marketId, start, end, orderByComparator };
        }

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ActualsMaster actualsMaster : list) {
                if (!Validator.equals(marketId, actualsMaster.getMarketId())) {
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

            query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

            boolean bindMarketId = false;

            if (marketId == null) {
                query.append(_FINDER_COLUMN_MARKETID_MARKETID_1);
            } else if (marketId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MARKETID_MARKETID_3);
            } else {
                bindMarketId = true;

                query.append(_FINDER_COLUMN_MARKETID_MARKETID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMarketId) {
                    qPos.add(marketId);
                }

                if (!pagination) {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Returns the first actuals master in the ordered set where marketId = &#63;.
     *
     * @param marketId the market ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByMarketId_First(String marketId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByMarketId_First(marketId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("marketId=");
        msg.append(marketId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the first actuals master in the ordered set where marketId = &#63;.
     *
     * @param marketId the market ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByMarketId_First(String marketId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ActualsMaster> list = findByMarketId(marketId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last actuals master in the ordered set where marketId = &#63;.
     *
     * @param marketId the market ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByMarketId_Last(String marketId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByMarketId_Last(marketId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("marketId=");
        msg.append(marketId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the last actuals master in the ordered set where marketId = &#63;.
     *
     * @param marketId the market ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByMarketId_Last(String marketId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByMarketId(marketId);

        if (count == 0) {
            return null;
        }

        List<ActualsMaster> list = findByMarketId(marketId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the actuals masters before and after the current actuals master in the ordered set where marketId = &#63;.
     *
     * @param actualsMasterSid the primary key of the current actuals master
     * @param marketId the market ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster[] findByMarketId_PrevAndNext(int actualsMasterSid,
        String marketId, OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

        Session session = null;

        try {
            session = openSession();

            ActualsMaster[] array = new ActualsMasterImpl[3];

            array[0] = getByMarketId_PrevAndNext(session, actualsMaster,
                    marketId, orderByComparator, true);

            array[1] = actualsMaster;

            array[2] = getByMarketId_PrevAndNext(session, actualsMaster,
                    marketId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ActualsMaster getByMarketId_PrevAndNext(Session session,
        ActualsMaster actualsMaster, String marketId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

        boolean bindMarketId = false;

        if (marketId == null) {
            query.append(_FINDER_COLUMN_MARKETID_MARKETID_1);
        } else if (marketId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_MARKETID_MARKETID_3);
        } else {
            bindMarketId = true;

            query.append(_FINDER_COLUMN_MARKETID_MARKETID_2);
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
            query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindMarketId) {
            qPos.add(marketId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ActualsMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the actuals masters where marketId = &#63; from the database.
     *
     * @param marketId the market ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByMarketId(String marketId) throws SystemException {
        for (ActualsMaster actualsMaster : findByMarketId(marketId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters where marketId = &#63;.
     *
     * @param marketId the market ID
     * @return the number of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByMarketId(String marketId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MARKETID;

        Object[] finderArgs = new Object[] { marketId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

            boolean bindMarketId = false;

            if (marketId == null) {
                query.append(_FINDER_COLUMN_MARKETID_MARKETID_1);
            } else if (marketId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MARKETID_MARKETID_3);
            } else {
                bindMarketId = true;

                query.append(_FINDER_COLUMN_MARKETID_MARKETID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMarketId) {
                    qPos.add(marketId);
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
     * Returns all the actuals masters where brandId = &#63;.
     *
     * @param brandId the brand ID
     * @return the matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByBrandId(String brandId)
        throws SystemException {
        return findByBrandId(brandId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the actuals masters where brandId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param brandId the brand ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByBrandId(String brandId, int start, int end)
        throws SystemException {
        return findByBrandId(brandId, start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters where brandId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param brandId the brand ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByBrandId(String brandId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BRANDID;
            finderArgs = new Object[] { brandId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BRANDID;
            finderArgs = new Object[] { brandId, start, end, orderByComparator };
        }

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ActualsMaster actualsMaster : list) {
                if (!Validator.equals(brandId, actualsMaster.getBrandId())) {
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

            query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

            boolean bindBrandId = false;

            if (brandId == null) {
                query.append(_FINDER_COLUMN_BRANDID_BRANDID_1);
            } else if (brandId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BRANDID_BRANDID_3);
            } else {
                bindBrandId = true;

                query.append(_FINDER_COLUMN_BRANDID_BRANDID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindBrandId) {
                    qPos.add(brandId);
                }

                if (!pagination) {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Returns the first actuals master in the ordered set where brandId = &#63;.
     *
     * @param brandId the brand ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByBrandId_First(String brandId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByBrandId_First(brandId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("brandId=");
        msg.append(brandId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the first actuals master in the ordered set where brandId = &#63;.
     *
     * @param brandId the brand ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByBrandId_First(String brandId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ActualsMaster> list = findByBrandId(brandId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last actuals master in the ordered set where brandId = &#63;.
     *
     * @param brandId the brand ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByBrandId_Last(String brandId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByBrandId_Last(brandId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("brandId=");
        msg.append(brandId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the last actuals master in the ordered set where brandId = &#63;.
     *
     * @param brandId the brand ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByBrandId_Last(String brandId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByBrandId(brandId);

        if (count == 0) {
            return null;
        }

        List<ActualsMaster> list = findByBrandId(brandId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the actuals masters before and after the current actuals master in the ordered set where brandId = &#63;.
     *
     * @param actualsMasterSid the primary key of the current actuals master
     * @param brandId the brand ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster[] findByBrandId_PrevAndNext(int actualsMasterSid,
        String brandId, OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

        Session session = null;

        try {
            session = openSession();

            ActualsMaster[] array = new ActualsMasterImpl[3];

            array[0] = getByBrandId_PrevAndNext(session, actualsMaster,
                    brandId, orderByComparator, true);

            array[1] = actualsMaster;

            array[2] = getByBrandId_PrevAndNext(session, actualsMaster,
                    brandId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ActualsMaster getByBrandId_PrevAndNext(Session session,
        ActualsMaster actualsMaster, String brandId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

        boolean bindBrandId = false;

        if (brandId == null) {
            query.append(_FINDER_COLUMN_BRANDID_BRANDID_1);
        } else if (brandId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_BRANDID_BRANDID_3);
        } else {
            bindBrandId = true;

            query.append(_FINDER_COLUMN_BRANDID_BRANDID_2);
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
            query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindBrandId) {
            qPos.add(brandId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ActualsMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the actuals masters where brandId = &#63; from the database.
     *
     * @param brandId the brand ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByBrandId(String brandId) throws SystemException {
        for (ActualsMaster actualsMaster : findByBrandId(brandId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters where brandId = &#63;.
     *
     * @param brandId the brand ID
     * @return the number of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByBrandId(String brandId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_BRANDID;

        Object[] finderArgs = new Object[] { brandId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

            boolean bindBrandId = false;

            if (brandId == null) {
                query.append(_FINDER_COLUMN_BRANDID_BRANDID_1);
            } else if (brandId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BRANDID_BRANDID_3);
            } else {
                bindBrandId = true;

                query.append(_FINDER_COLUMN_BRANDID_BRANDID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindBrandId) {
                    qPos.add(brandId);
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
     * Returns all the actuals masters where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @return the matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByActualsUnique(String actualId)
        throws SystemException {
        return findByActualsUnique(actualId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the actuals masters where actualId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param actualId the actual ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByActualsUnique(String actualId, int start,
        int end) throws SystemException {
        return findByActualsUnique(actualId, start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters where actualId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param actualId the actual ID
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findByActualsUnique(String actualId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALSUNIQUE;
            finderArgs = new Object[] { actualId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTUALSUNIQUE;
            finderArgs = new Object[] { actualId, start, end, orderByComparator };
        }

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ActualsMaster actualsMaster : list) {
                if (!Validator.equals(actualId, actualsMaster.getActualId())) {
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

            query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

            boolean bindActualId = false;

            if (actualId == null) {
                query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_1);
            } else if (actualId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_3);
            } else {
                bindActualId = true;

                query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindActualId) {
                    qPos.add(actualId);
                }

                if (!pagination) {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Returns the first actuals master in the ordered set where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByActualsUnique_First(String actualId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByActualsUnique_First(actualId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("actualId=");
        msg.append(actualId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the first actuals master in the ordered set where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByActualsUnique_First(String actualId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ActualsMaster> list = findByActualsUnique(actualId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last actuals master in the ordered set where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByActualsUnique_Last(String actualId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByActualsUnique_Last(actualId,
                orderByComparator);

        if (actualsMaster != null) {
            return actualsMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("actualId=");
        msg.append(actualId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchActualsMasterException(msg.toString());
    }

    /**
     * Returns the last actuals master in the ordered set where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByActualsUnique_Last(String actualId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByActualsUnique(actualId);

        if (count == 0) {
            return null;
        }

        List<ActualsMaster> list = findByActualsUnique(actualId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the actuals masters before and after the current actuals master in the ordered set where actualId = &#63;.
     *
     * @param actualsMasterSid the primary key of the current actuals master
     * @param actualId the actual ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster[] findByActualsUnique_PrevAndNext(
        int actualsMasterSid, String actualId,
        OrderByComparator orderByComparator)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

        Session session = null;

        try {
            session = openSession();

            ActualsMaster[] array = new ActualsMasterImpl[3];

            array[0] = getByActualsUnique_PrevAndNext(session, actualsMaster,
                    actualId, orderByComparator, true);

            array[1] = actualsMaster;

            array[2] = getByActualsUnique_PrevAndNext(session, actualsMaster,
                    actualId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ActualsMaster getByActualsUnique_PrevAndNext(Session session,
        ActualsMaster actualsMaster, String actualId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

        boolean bindActualId = false;

        if (actualId == null) {
            query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_1);
        } else if (actualId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_3);
        } else {
            bindActualId = true;

            query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_2);
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
            query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindActualId) {
            qPos.add(actualId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ActualsMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the actuals masters where actualId = &#63; from the database.
     *
     * @param actualId the actual ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActualsUnique(String actualId)
        throws SystemException {
        for (ActualsMaster actualsMaster : findByActualsUnique(actualId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters where actualId = &#63;.
     *
     * @param actualId the actual ID
     * @return the number of matching actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActualsUnique(String actualId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTUALSUNIQUE;

        Object[] finderArgs = new Object[] { actualId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

            boolean bindActualId = false;

            if (actualId == null) {
                query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_1);
            } else if (actualId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_3);
            } else {
                bindActualId = true;

                query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindActualId) {
                    qPos.add(actualId);
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
     * Caches the actuals master in the entity cache if it is enabled.
     *
     * @param actualsMaster the actuals master
     */
    @Override
    public void cacheResult(ActualsMaster actualsMaster) {
        EntityCacheUtil.putResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterImpl.class, actualsMaster.getPrimaryKey(),
            actualsMaster);

        actualsMaster.resetOriginalValues();
    }

    /**
     * Caches the actuals masters in the entity cache if it is enabled.
     *
     * @param actualsMasters the actuals masters
     */
    @Override
    public void cacheResult(List<ActualsMaster> actualsMasters) {
        for (ActualsMaster actualsMaster : actualsMasters) {
            if (EntityCacheUtil.getResult(
                        ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ActualsMasterImpl.class, actualsMaster.getPrimaryKey()) == null) {
                cacheResult(actualsMaster);
            } else {
                actualsMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all actuals masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ActualsMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ActualsMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the actuals master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ActualsMaster actualsMaster) {
        EntityCacheUtil.removeResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterImpl.class, actualsMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ActualsMaster> actualsMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ActualsMaster actualsMaster : actualsMasters) {
            EntityCacheUtil.removeResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
                ActualsMasterImpl.class, actualsMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new actuals master with the primary key. Does not add the actuals master to the database.
     *
     * @param actualsMasterSid the primary key for the new actuals master
     * @return the new actuals master
     */
    @Override
    public ActualsMaster create(int actualsMasterSid) {
        ActualsMaster actualsMaster = new ActualsMasterImpl();

        actualsMaster.setNew(true);
        actualsMaster.setPrimaryKey(actualsMasterSid);

        return actualsMaster;
    }

    /**
     * Removes the actuals master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param actualsMasterSid the primary key of the actuals master
     * @return the actuals master that was removed
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster remove(int actualsMasterSid)
        throws NoSuchActualsMasterException, SystemException {
        return remove((Serializable) actualsMasterSid);
    }

    /**
     * Removes the actuals master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the actuals master
     * @return the actuals master that was removed
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster remove(Serializable primaryKey)
        throws NoSuchActualsMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ActualsMaster actualsMaster = (ActualsMaster) session.get(ActualsMasterImpl.class,
                    primaryKey);

            if (actualsMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchActualsMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(actualsMaster);
        } catch (NoSuchActualsMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ActualsMaster removeImpl(ActualsMaster actualsMaster)
        throws SystemException {
        actualsMaster = toUnwrappedModel(actualsMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(actualsMaster)) {
                actualsMaster = (ActualsMaster) session.get(ActualsMasterImpl.class,
                        actualsMaster.getPrimaryKeyObj());
            }

            if (actualsMaster != null) {
                session.delete(actualsMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (actualsMaster != null) {
            clearCache(actualsMaster);
        }

        return actualsMaster;
    }

    @Override
    public ActualsMaster updateImpl(
        com.stpl.app.model.ActualsMaster actualsMaster)
        throws SystemException {
        actualsMaster = toUnwrappedModel(actualsMaster);

        boolean isNew = actualsMaster.isNew();

        ActualsMasterModelImpl actualsMasterModelImpl = (ActualsMasterModelImpl) actualsMaster;

        Session session = null;

        try {
            session = openSession();

            if (actualsMaster.isNew()) {
                session.save(actualsMaster);

                actualsMaster.setNew(false);
            } else {
                session.merge(actualsMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ActualsMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((actualsMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        actualsMasterModelImpl.getOriginalAccountId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
                    args);

                args = new Object[] { actualsMasterModelImpl.getAccountId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
                    args);
            }

            if ((actualsMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        actualsMasterModelImpl.getOriginalActualId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTUALID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALID,
                    args);

                args = new Object[] { actualsMasterModelImpl.getActualId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTUALID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALID,
                    args);
            }

            if ((actualsMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DIVISIONID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        actualsMasterModelImpl.getOriginalDivisionId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DIVISIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DIVISIONID,
                    args);

                args = new Object[] { actualsMasterModelImpl.getDivisionId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DIVISIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DIVISIONID,
                    args);
            }

            if ((actualsMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        actualsMasterModelImpl.getOriginalContractId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID,
                    args);

                args = new Object[] { actualsMasterModelImpl.getContractId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTRACTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID,
                    args);
            }

            if ((actualsMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROVISIONID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        actualsMasterModelImpl.getOriginalProvisionId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROVISIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROVISIONID,
                    args);

                args = new Object[] { actualsMasterModelImpl.getProvisionId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROVISIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROVISIONID,
                    args);
            }

            if ((actualsMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        actualsMasterModelImpl.getOriginalItemId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
                    args);

                args = new Object[] { actualsMasterModelImpl.getItemId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
                    args);
            }

            if ((actualsMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        actualsMasterModelImpl.getOriginalItemNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
                    args);

                args = new Object[] { actualsMasterModelImpl.getItemNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
                    args);
            }

            if ((actualsMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        actualsMasterModelImpl.getOriginalAccountNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO,
                    args);

                args = new Object[] { actualsMasterModelImpl.getAccountNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO,
                    args);
            }

            if ((actualsMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        actualsMasterModelImpl.getOriginalMarketId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MARKETID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETID,
                    args);

                args = new Object[] { actualsMasterModelImpl.getMarketId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MARKETID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETID,
                    args);
            }

            if ((actualsMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BRANDID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        actualsMasterModelImpl.getOriginalBrandId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BRANDID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BRANDID,
                    args);

                args = new Object[] { actualsMasterModelImpl.getBrandId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BRANDID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BRANDID,
                    args);
            }

            if ((actualsMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALSUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        actualsMasterModelImpl.getOriginalActualId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTUALSUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALSUNIQUE,
                    args);

                args = new Object[] { actualsMasterModelImpl.getActualId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTUALSUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALSUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ActualsMasterImpl.class, actualsMaster.getPrimaryKey(),
            actualsMaster);

        return actualsMaster;
    }

    protected ActualsMaster toUnwrappedModel(ActualsMaster actualsMaster) {
        if (actualsMaster instanceof ActualsMasterImpl) {
            return actualsMaster;
        }

        ActualsMasterImpl actualsMasterImpl = new ActualsMasterImpl();

        actualsMasterImpl.setNew(actualsMaster.isNew());
        actualsMasterImpl.setPrimaryKey(actualsMaster.getPrimaryKey());

        actualsMasterImpl.setQuantityInclusion(actualsMaster.getQuantityInclusion());
        actualsMasterImpl.setMandatedDiscountAmount(actualsMaster.getMandatedDiscountAmount());
        actualsMasterImpl.setItemNo(actualsMaster.getItemNo());
        actualsMasterImpl.setAnalysisCode(actualsMaster.getAnalysisCode());
        actualsMasterImpl.setRecordSequence(actualsMaster.getRecordSequence());
        actualsMasterImpl.setModifiedBy(actualsMaster.getModifiedBy());
        actualsMasterImpl.setSettlementMethodNo(actualsMaster.getSettlementMethodNo());
        actualsMasterImpl.setQuantity(actualsMaster.getQuantity());
        actualsMasterImpl.setAccountId(actualsMaster.getAccountId());
        actualsMasterImpl.setCreatedDate(actualsMaster.getCreatedDate());
        actualsMasterImpl.setProvisionClaimIndicator(actualsMaster.getProvisionClaimIndicator());
        actualsMasterImpl.setDispensedDate(actualsMaster.getDispensedDate());
        actualsMasterImpl.setIsActive(actualsMaster.getIsActive());
        actualsMasterImpl.setBatchId(actualsMaster.getBatchId());
        actualsMasterImpl.setAccrualActualEndDate(actualsMaster.getAccrualActualEndDate());
        actualsMasterImpl.setMarketId(actualsMaster.getMarketId());
        actualsMasterImpl.setBrandId(actualsMaster.getBrandId());
        actualsMasterImpl.setAccountName(actualsMaster.getAccountName());
        actualsMasterImpl.setAmount(actualsMaster.getAmount());
        actualsMasterImpl.setActualsMasterSid(actualsMaster.getActualsMasterSid());
        actualsMasterImpl.setAcctIdentifierCodeQualifier(actualsMaster.getAcctIdentifierCodeQualifier());
        actualsMasterImpl.setOrganizationKey(actualsMaster.getOrganizationKey());
        actualsMasterImpl.setCreatedBy(actualsMaster.getCreatedBy());
        actualsMasterImpl.setAccrualProcessed(actualsMaster.getAccrualProcessed());
        actualsMasterImpl.setParentcomDivmktBrandProdkey(actualsMaster.getParentcomDivmktBrandProdkey());
        actualsMasterImpl.setCashPaidDate(actualsMaster.getCashPaidDate());
        actualsMasterImpl.setSalesAmount(actualsMaster.getSalesAmount());
        actualsMasterImpl.setAccrualActualStartDate(actualsMaster.getAccrualActualStartDate());
        actualsMasterImpl.setSettlementNo(actualsMaster.getSettlementNo());
        actualsMasterImpl.setPrice(actualsMaster.getPrice());
        actualsMasterImpl.setUploadDate(actualsMaster.getUploadDate());
        actualsMasterImpl.setClaimIndicator(actualsMaster.getClaimIndicator());
        actualsMasterImpl.setItemId(actualsMaster.getItemId());
        actualsMasterImpl.setPriceAdjustmentName(actualsMaster.getPriceAdjustmentName());
        actualsMasterImpl.setContractId(actualsMaster.getContractId());
        actualsMasterImpl.setModifiedDate(actualsMaster.getModifiedDate());
        actualsMasterImpl.setActualId(actualsMaster.getActualId());
        actualsMasterImpl.setProvisionId(actualsMaster.getProvisionId());
        actualsMasterImpl.setSentOut(actualsMaster.getSentOut());
        actualsMasterImpl.setRecordLockStatus(actualsMaster.isRecordLockStatus());
        actualsMasterImpl.setDivisionId(actualsMaster.getDivisionId());
        actualsMasterImpl.setItemIdentifierCodeQualifier(actualsMaster.getItemIdentifierCodeQualifier());
        actualsMasterImpl.setProgramStateCode(actualsMaster.getProgramStateCode());
        actualsMasterImpl.setSource(actualsMaster.getSource());
        actualsMasterImpl.setInvoiceLineNo(actualsMaster.getInvoiceLineNo());
        actualsMasterImpl.setAccountNo(actualsMaster.getAccountNo());
        actualsMasterImpl.setComDivMktBrandProdKey(actualsMaster.getComDivMktBrandProdKey());
        actualsMasterImpl.setInboundStatus(actualsMaster.getInboundStatus());

        return actualsMasterImpl;
    }

    /**
     * Returns the actuals master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the actuals master
     * @return the actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchActualsMasterException, SystemException {
        ActualsMaster actualsMaster = fetchByPrimaryKey(primaryKey);

        if (actualsMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchActualsMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return actualsMaster;
    }

    /**
     * Returns the actuals master with the primary key or throws a {@link com.stpl.app.NoSuchActualsMasterException} if it could not be found.
     *
     * @param actualsMasterSid the primary key of the actuals master
     * @return the actuals master
     * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster findByPrimaryKey(int actualsMasterSid)
        throws NoSuchActualsMasterException, SystemException {
        return findByPrimaryKey((Serializable) actualsMasterSid);
    }

    /**
     * Returns the actuals master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the actuals master
     * @return the actuals master, or <code>null</code> if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ActualsMaster actualsMaster = (ActualsMaster) EntityCacheUtil.getResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
                ActualsMasterImpl.class, primaryKey);

        if (actualsMaster == _nullActualsMaster) {
            return null;
        }

        if (actualsMaster == null) {
            Session session = null;

            try {
                session = openSession();

                actualsMaster = (ActualsMaster) session.get(ActualsMasterImpl.class,
                        primaryKey);

                if (actualsMaster != null) {
                    cacheResult(actualsMaster);
                } else {
                    EntityCacheUtil.putResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ActualsMasterImpl.class, primaryKey, _nullActualsMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
                    ActualsMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return actualsMaster;
    }

    /**
     * Returns the actuals master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param actualsMasterSid the primary key of the actuals master
     * @return the actuals master, or <code>null</code> if a actuals master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ActualsMaster fetchByPrimaryKey(int actualsMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) actualsMasterSid);
    }

    /**
     * Returns all the actuals masters.
     *
     * @return the actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the actuals masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @return the range of actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the actuals masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of actuals masters
     * @param end the upper bound of the range of actuals masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of actuals masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ActualsMaster> findAll(int start, int end,
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

        List<ActualsMaster> list = (List<ActualsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ACTUALSMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ACTUALSMASTER;

                if (pagination) {
                    sql = sql.concat(ActualsMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ActualsMaster>(list);
                } else {
                    list = (List<ActualsMaster>) QueryUtil.list(q,
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
     * Removes all the actuals masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ActualsMaster actualsMaster : findAll()) {
            remove(actualsMaster);
        }
    }

    /**
     * Returns the number of actuals masters.
     *
     * @return the number of actuals masters
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

                Query q = session.createQuery(_SQL_COUNT_ACTUALSMASTER);

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
     * Initializes the actuals master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ActualsMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ActualsMaster>> listenersList = new ArrayList<ModelListener<ActualsMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ActualsMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ActualsMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
