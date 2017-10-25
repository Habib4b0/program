package com.stpl.app.parttwo.service.base;

import com.stpl.app.parttwo.model.CffViewMaster;
import com.stpl.app.parttwo.service.CffViewMasterLocalService;
import com.stpl.app.parttwo.service.persistence.AcBaseRateBaselineDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.AcBrMethodologyDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.AcFdAdjustmentSelectionPersistence;
import com.stpl.app.parttwo.service.persistence.AccClosureDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.AccClosureMasterFinder;
import com.stpl.app.parttwo.service.persistence.AccClosureMasterPersistence;
import com.stpl.app.parttwo.service.persistence.AccClosureViewMasterPersistence;
import com.stpl.app.parttwo.service.persistence.AdjustedDemandForecastPersistence;
import com.stpl.app.parttwo.service.persistence.AhTempDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.ArpOutboundPersistence;
import com.stpl.app.parttwo.service.persistence.CffAdditionalInfoPersistence;
import com.stpl.app.parttwo.service.persistence.CffApprovalDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.CffCustHierarchyPersistence;
import com.stpl.app.parttwo.service.persistence.CffCustomViewDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.CffCustomViewMasterPersistence;
import com.stpl.app.parttwo.service.persistence.CffDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.CffDocDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.CffMasterPersistence;
import com.stpl.app.parttwo.service.persistence.CffProdHierarchyPersistence;
import com.stpl.app.parttwo.service.persistence.CffViewMasterPersistence;
import com.stpl.app.parttwo.service.persistence.CustomerGtsActualPersistence;
import com.stpl.app.parttwo.service.persistence.CustomerGtsForecastPersistence;
import com.stpl.app.parttwo.service.persistence.IvldAccrualInboundPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCompanyIdentifierPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCompanyMasterPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCompanyParentPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCompanyTradeClassPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCustomerGtsActualPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCustomerGtsForecastPersistence;
import com.stpl.app.parttwo.service.persistence.IvldItemIdentifierPersistence;
import com.stpl.app.parttwo.service.persistence.IvldItemMasterPersistence;
import com.stpl.app.parttwo.service.persistence.IvldItemPricingPersistence;
import com.stpl.app.parttwo.service.persistence.SlaCalendarDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.SlaCalendarMasterPersistence;
import com.stpl.app.parttwo.service.persistence.StAccClosureDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPersistence;
import com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailPersistence;
import com.stpl.app.parttwo.service.persistence.StArpOutboundPersistence;
import com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPersistence;
import com.stpl.app.parttwo.service.persistence.VwAdjustDemandForecastActPersistence;
import com.stpl.app.parttwo.service.persistence.VwCompanyIdentifierPersistence;
import com.stpl.app.parttwo.service.persistence.VwCompanyMasterPersistence;
import com.stpl.app.parttwo.service.persistence.VwCompanyParentDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.VwCompanyTradeClassPersistence;
import com.stpl.app.parttwo.service.persistence.VwCustomerGtsForecastPersistence;
import com.stpl.app.parttwo.service.persistence.VwItemIdentifierPersistence;
import com.stpl.app.parttwo.service.persistence.VwItemMasterPersistence;
import com.stpl.app.parttwo.service.persistence.VwItemPricingPersistence;
import com.stpl.app.parttwo.service.persistence.VwIvldAdjDemandForeActualPersistence;
import com.stpl.app.parttwo.service.persistence.VwIvldReturnReservePersistence;
import com.stpl.app.parttwo.service.persistence.VwReturnReservePersistence;

import com.stpl.portal.kernel.bean.BeanReference;
import com.stpl.portal.kernel.bean.IdentifiableBean;
import com.stpl.portal.kernel.dao.jdbc.SqlUpdate;
import com.stpl.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.Projection;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.search.Indexable;
import com.stpl.portal.kernel.search.IndexableType;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.model.PersistedModel;
import com.stpl.portal.service.BaseLocalServiceImpl;
import com.stpl.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.stpl.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the cff view master local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.stpl.app.parttwo.service.impl.CffViewMasterLocalServiceImpl}.
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.impl.CffViewMasterLocalServiceImpl
 * @see com.stpl.app.parttwo.service.CffViewMasterLocalServiceUtil
 * @generated
 */
public abstract class CffViewMasterLocalServiceBaseImpl
    extends BaseLocalServiceImpl implements CffViewMasterLocalService,
        IdentifiableBean {
    @BeanReference(type = com.stpl.app.parttwo.service.AcBaseRateBaselineDetailsLocalService.class)
    protected com.stpl.app.parttwo.service.AcBaseRateBaselineDetailsLocalService acBaseRateBaselineDetailsLocalService;
    @BeanReference(type = AcBaseRateBaselineDetailsPersistence.class)
    protected AcBaseRateBaselineDetailsPersistence acBaseRateBaselineDetailsPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.AcBrMethodologyDetailsLocalService.class)
    protected com.stpl.app.parttwo.service.AcBrMethodologyDetailsLocalService acBrMethodologyDetailsLocalService;
    @BeanReference(type = AcBrMethodologyDetailsPersistence.class)
    protected AcBrMethodologyDetailsPersistence acBrMethodologyDetailsPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.AccClosureDetailsLocalService.class)
    protected com.stpl.app.parttwo.service.AccClosureDetailsLocalService accClosureDetailsLocalService;
    @BeanReference(type = AccClosureDetailsPersistence.class)
    protected AccClosureDetailsPersistence accClosureDetailsPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.AccClosureMasterLocalService.class)
    protected com.stpl.app.parttwo.service.AccClosureMasterLocalService accClosureMasterLocalService;
    @BeanReference(type = AccClosureMasterPersistence.class)
    protected AccClosureMasterPersistence accClosureMasterPersistence;
    @BeanReference(type = AccClosureMasterFinder.class)
    protected AccClosureMasterFinder accClosureMasterFinder;
    @BeanReference(type = com.stpl.app.parttwo.service.AccClosureViewMasterLocalService.class)
    protected com.stpl.app.parttwo.service.AccClosureViewMasterLocalService accClosureViewMasterLocalService;
    @BeanReference(type = AccClosureViewMasterPersistence.class)
    protected AccClosureViewMasterPersistence accClosureViewMasterPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.AcFdAdjustmentSelectionLocalService.class)
    protected com.stpl.app.parttwo.service.AcFdAdjustmentSelectionLocalService acFdAdjustmentSelectionLocalService;
    @BeanReference(type = AcFdAdjustmentSelectionPersistence.class)
    protected AcFdAdjustmentSelectionPersistence acFdAdjustmentSelectionPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.AdjustedDemandForecastLocalService.class)
    protected com.stpl.app.parttwo.service.AdjustedDemandForecastLocalService adjustedDemandForecastLocalService;
    @BeanReference(type = AdjustedDemandForecastPersistence.class)
    protected AdjustedDemandForecastPersistence adjustedDemandForecastPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.AhTempDetailsLocalService.class)
    protected com.stpl.app.parttwo.service.AhTempDetailsLocalService ahTempDetailsLocalService;
    @BeanReference(type = AhTempDetailsPersistence.class)
    protected AhTempDetailsPersistence ahTempDetailsPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.ArpOutboundLocalService.class)
    protected com.stpl.app.parttwo.service.ArpOutboundLocalService arpOutboundLocalService;
    @BeanReference(type = ArpOutboundPersistence.class)
    protected ArpOutboundPersistence arpOutboundPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CffAdditionalInfoLocalService.class)
    protected com.stpl.app.parttwo.service.CffAdditionalInfoLocalService cffAdditionalInfoLocalService;
    @BeanReference(type = CffAdditionalInfoPersistence.class)
    protected CffAdditionalInfoPersistence cffAdditionalInfoPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CffApprovalDetailsLocalService.class)
    protected com.stpl.app.parttwo.service.CffApprovalDetailsLocalService cffApprovalDetailsLocalService;
    @BeanReference(type = CffApprovalDetailsPersistence.class)
    protected CffApprovalDetailsPersistence cffApprovalDetailsPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CffCustHierarchyLocalService.class)
    protected com.stpl.app.parttwo.service.CffCustHierarchyLocalService cffCustHierarchyLocalService;
    @BeanReference(type = CffCustHierarchyPersistence.class)
    protected CffCustHierarchyPersistence cffCustHierarchyPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CffCustomViewDetailsLocalService.class)
    protected com.stpl.app.parttwo.service.CffCustomViewDetailsLocalService cffCustomViewDetailsLocalService;
    @BeanReference(type = CffCustomViewDetailsPersistence.class)
    protected CffCustomViewDetailsPersistence cffCustomViewDetailsPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CffCustomViewMasterLocalService.class)
    protected com.stpl.app.parttwo.service.CffCustomViewMasterLocalService cffCustomViewMasterLocalService;
    @BeanReference(type = CffCustomViewMasterPersistence.class)
    protected CffCustomViewMasterPersistence cffCustomViewMasterPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CffDetailsLocalService.class)
    protected com.stpl.app.parttwo.service.CffDetailsLocalService cffDetailsLocalService;
    @BeanReference(type = CffDetailsPersistence.class)
    protected CffDetailsPersistence cffDetailsPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CffDocDetailsLocalService.class)
    protected com.stpl.app.parttwo.service.CffDocDetailsLocalService cffDocDetailsLocalService;
    @BeanReference(type = CffDocDetailsPersistence.class)
    protected CffDocDetailsPersistence cffDocDetailsPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CffMasterLocalService.class)
    protected com.stpl.app.parttwo.service.CffMasterLocalService cffMasterLocalService;
    @BeanReference(type = CffMasterPersistence.class)
    protected CffMasterPersistence cffMasterPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CffProdHierarchyLocalService.class)
    protected com.stpl.app.parttwo.service.CffProdHierarchyLocalService cffProdHierarchyLocalService;
    @BeanReference(type = CffProdHierarchyPersistence.class)
    protected CffProdHierarchyPersistence cffProdHierarchyPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CffViewMasterLocalService.class)
    protected com.stpl.app.parttwo.service.CffViewMasterLocalService cffViewMasterLocalService;
    @BeanReference(type = CffViewMasterPersistence.class)
    protected CffViewMasterPersistence cffViewMasterPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CustomerGtsActualLocalService.class)
    protected com.stpl.app.parttwo.service.CustomerGtsActualLocalService customerGtsActualLocalService;
    @BeanReference(type = CustomerGtsActualPersistence.class)
    protected CustomerGtsActualPersistence customerGtsActualPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.CustomerGtsForecastLocalService.class)
    protected com.stpl.app.parttwo.service.CustomerGtsForecastLocalService customerGtsForecastLocalService;
    @BeanReference(type = CustomerGtsForecastPersistence.class)
    protected CustomerGtsForecastPersistence customerGtsForecastPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.IvldAccrualInboundLocalService.class)
    protected com.stpl.app.parttwo.service.IvldAccrualInboundLocalService ivldAccrualInboundLocalService;
    @BeanReference(type = IvldAccrualInboundPersistence.class)
    protected IvldAccrualInboundPersistence ivldAccrualInboundPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.IvldCompanyIdentifierLocalService.class)
    protected com.stpl.app.parttwo.service.IvldCompanyIdentifierLocalService ivldCompanyIdentifierLocalService;
    @BeanReference(type = IvldCompanyIdentifierPersistence.class)
    protected IvldCompanyIdentifierPersistence ivldCompanyIdentifierPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.IvldCompanyMasterLocalService.class)
    protected com.stpl.app.parttwo.service.IvldCompanyMasterLocalService ivldCompanyMasterLocalService;
    @BeanReference(type = IvldCompanyMasterPersistence.class)
    protected IvldCompanyMasterPersistence ivldCompanyMasterPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.IvldCompanyParentLocalService.class)
    protected com.stpl.app.parttwo.service.IvldCompanyParentLocalService ivldCompanyParentLocalService;
    @BeanReference(type = IvldCompanyParentPersistence.class)
    protected IvldCompanyParentPersistence ivldCompanyParentPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.IvldCompanyTradeClassLocalService.class)
    protected com.stpl.app.parttwo.service.IvldCompanyTradeClassLocalService ivldCompanyTradeClassLocalService;
    @BeanReference(type = IvldCompanyTradeClassPersistence.class)
    protected IvldCompanyTradeClassPersistence ivldCompanyTradeClassPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.IvldCustomerGtsActualLocalService.class)
    protected com.stpl.app.parttwo.service.IvldCustomerGtsActualLocalService ivldCustomerGtsActualLocalService;
    @BeanReference(type = IvldCustomerGtsActualPersistence.class)
    protected IvldCustomerGtsActualPersistence ivldCustomerGtsActualPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.IvldCustomerGtsForecastLocalService.class)
    protected com.stpl.app.parttwo.service.IvldCustomerGtsForecastLocalService ivldCustomerGtsForecastLocalService;
    @BeanReference(type = IvldCustomerGtsForecastPersistence.class)
    protected IvldCustomerGtsForecastPersistence ivldCustomerGtsForecastPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.IvldItemIdentifierLocalService.class)
    protected com.stpl.app.parttwo.service.IvldItemIdentifierLocalService ivldItemIdentifierLocalService;
    @BeanReference(type = IvldItemIdentifierPersistence.class)
    protected IvldItemIdentifierPersistence ivldItemIdentifierPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.IvldItemMasterLocalService.class)
    protected com.stpl.app.parttwo.service.IvldItemMasterLocalService ivldItemMasterLocalService;
    @BeanReference(type = IvldItemMasterPersistence.class)
    protected IvldItemMasterPersistence ivldItemMasterPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.IvldItemPricingLocalService.class)
    protected com.stpl.app.parttwo.service.IvldItemPricingLocalService ivldItemPricingLocalService;
    @BeanReference(type = IvldItemPricingPersistence.class)
    protected IvldItemPricingPersistence ivldItemPricingPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.SlaCalendarDetailsLocalService.class)
    protected com.stpl.app.parttwo.service.SlaCalendarDetailsLocalService slaCalendarDetailsLocalService;
    @BeanReference(type = SlaCalendarDetailsPersistence.class)
    protected SlaCalendarDetailsPersistence slaCalendarDetailsPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.SlaCalendarMasterLocalService.class)
    protected com.stpl.app.parttwo.service.SlaCalendarMasterLocalService slaCalendarMasterLocalService;
    @BeanReference(type = SlaCalendarMasterPersistence.class)
    protected SlaCalendarMasterPersistence slaCalendarMasterPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.StAccClosureDetailsLocalService.class)
    protected com.stpl.app.parttwo.service.StAccClosureDetailsLocalService stAccClosureDetailsLocalService;
    @BeanReference(type = StAccClosureDetailsPersistence.class)
    protected StAccClosureDetailsPersistence stAccClosureDetailsPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.StAdjustmentGtnDetailLocalService.class)
    protected com.stpl.app.parttwo.service.StAdjustmentGtnDetailLocalService stAdjustmentGtnDetailLocalService;
    @BeanReference(type = StAdjustmentGtnDetailPersistence.class)
    protected StAdjustmentGtnDetailPersistence stAdjustmentGtnDetailPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.StAdjustmentReserveDetailLocalService.class)
    protected com.stpl.app.parttwo.service.StAdjustmentReserveDetailLocalService stAdjustmentReserveDetailLocalService;
    @BeanReference(type = StAdjustmentReserveDetailPersistence.class)
    protected StAdjustmentReserveDetailPersistence stAdjustmentReserveDetailPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.StArpOutboundLocalService.class)
    protected com.stpl.app.parttwo.service.StArpOutboundLocalService stArpOutboundLocalService;
    @BeanReference(type = StArpOutboundPersistence.class)
    protected StArpOutboundPersistence stArpOutboundPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.StCffOutboundMasterLocalService.class)
    protected com.stpl.app.parttwo.service.StCffOutboundMasterLocalService stCffOutboundMasterLocalService;
    @BeanReference(type = StCffOutboundMasterPersistence.class)
    protected StCffOutboundMasterPersistence stCffOutboundMasterPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwAdjustDemandForecastActLocalService.class)
    protected com.stpl.app.parttwo.service.VwAdjustDemandForecastActLocalService vwAdjustDemandForecastActLocalService;
    @BeanReference(type = VwAdjustDemandForecastActPersistence.class)
    protected VwAdjustDemandForecastActPersistence vwAdjustDemandForecastActPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwCompanyIdentifierLocalService.class)
    protected com.stpl.app.parttwo.service.VwCompanyIdentifierLocalService vwCompanyIdentifierLocalService;
    @BeanReference(type = VwCompanyIdentifierPersistence.class)
    protected VwCompanyIdentifierPersistence vwCompanyIdentifierPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwCompanyMasterLocalService.class)
    protected com.stpl.app.parttwo.service.VwCompanyMasterLocalService vwCompanyMasterLocalService;
    @BeanReference(type = VwCompanyMasterPersistence.class)
    protected VwCompanyMasterPersistence vwCompanyMasterPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwCompanyParentDetailsLocalService.class)
    protected com.stpl.app.parttwo.service.VwCompanyParentDetailsLocalService vwCompanyParentDetailsLocalService;
    @BeanReference(type = VwCompanyParentDetailsPersistence.class)
    protected VwCompanyParentDetailsPersistence vwCompanyParentDetailsPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwCompanyTradeClassLocalService.class)
    protected com.stpl.app.parttwo.service.VwCompanyTradeClassLocalService vwCompanyTradeClassLocalService;
    @BeanReference(type = VwCompanyTradeClassPersistence.class)
    protected VwCompanyTradeClassPersistence vwCompanyTradeClassPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwCustomerGtsForecastLocalService.class)
    protected com.stpl.app.parttwo.service.VwCustomerGtsForecastLocalService vwCustomerGtsForecastLocalService;
    @BeanReference(type = VwCustomerGtsForecastPersistence.class)
    protected VwCustomerGtsForecastPersistence vwCustomerGtsForecastPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwItemIdentifierLocalService.class)
    protected com.stpl.app.parttwo.service.VwItemIdentifierLocalService vwItemIdentifierLocalService;
    @BeanReference(type = VwItemIdentifierPersistence.class)
    protected VwItemIdentifierPersistence vwItemIdentifierPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwItemMasterLocalService.class)
    protected com.stpl.app.parttwo.service.VwItemMasterLocalService vwItemMasterLocalService;
    @BeanReference(type = VwItemMasterPersistence.class)
    protected VwItemMasterPersistence vwItemMasterPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwItemPricingLocalService.class)
    protected com.stpl.app.parttwo.service.VwItemPricingLocalService vwItemPricingLocalService;
    @BeanReference(type = VwItemPricingPersistence.class)
    protected VwItemPricingPersistence vwItemPricingPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwIvldAdjDemandForeActualLocalService.class)
    protected com.stpl.app.parttwo.service.VwIvldAdjDemandForeActualLocalService vwIvldAdjDemandForeActualLocalService;
    @BeanReference(type = VwIvldAdjDemandForeActualPersistence.class)
    protected VwIvldAdjDemandForeActualPersistence vwIvldAdjDemandForeActualPersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwIvldReturnReserveLocalService.class)
    protected com.stpl.app.parttwo.service.VwIvldReturnReserveLocalService vwIvldReturnReserveLocalService;
    @BeanReference(type = VwIvldReturnReservePersistence.class)
    protected VwIvldReturnReservePersistence vwIvldReturnReservePersistence;
    @BeanReference(type = com.stpl.app.parttwo.service.VwReturnReserveLocalService.class)
    protected com.stpl.app.parttwo.service.VwReturnReserveLocalService vwReturnReserveLocalService;
    @BeanReference(type = VwReturnReservePersistence.class)
    protected VwReturnReservePersistence vwReturnReservePersistence;
    @BeanReference(type = com.stpl.counter.service.CounterLocalService.class)
    protected com.stpl.counter.service.CounterLocalService counterLocalService;
    @BeanReference(type = com.stpl.portal.service.ResourceLocalService.class)
    protected com.stpl.portal.service.ResourceLocalService resourceLocalService;
    @BeanReference(type = com.stpl.portal.service.UserLocalService.class)
    protected com.stpl.portal.service.UserLocalService userLocalService;
    @BeanReference(type = com.stpl.portal.service.UserService.class)
    protected com.stpl.portal.service.UserService userService;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;
    private String _beanIdentifier;
    private ClassLoader _classLoader;
    private CffViewMasterLocalServiceClpInvoker _clpInvoker = new CffViewMasterLocalServiceClpInvoker();

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link com.stpl.app.parttwo.service.CffViewMasterLocalServiceUtil} to access the cff view master local service.
     */

    /**
     * Adds the cff view master to the database. Also notifies the appropriate model listeners.
     *
     * @param cffViewMaster the cff view master
     * @return the cff view master that was added
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public CffViewMaster addCffViewMaster(CffViewMaster cffViewMaster)
        throws SystemException {
        cffViewMaster.setNew(true);

        return cffViewMasterPersistence.update(cffViewMaster);
    }

    /**
     * Creates a new cff view master with the primary key. Does not add the cff view master to the database.
     *
     * @param cffViewMasterSid the primary key for the new cff view master
     * @return the new cff view master
     */
    @Override
    public CffViewMaster createCffViewMaster(int cffViewMasterSid) {
        return cffViewMasterPersistence.create(cffViewMasterSid);
    }

    /**
     * Deletes the cff view master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cffViewMasterSid the primary key of the cff view master
     * @return the cff view master that was removed
     * @throws PortalException if a cff view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.DELETE)
    @Override
    public CffViewMaster deleteCffViewMaster(int cffViewMasterSid)
        throws PortalException, SystemException {
        return cffViewMasterPersistence.remove(cffViewMasterSid);
    }

    /**
     * Deletes the cff view master from the database. Also notifies the appropriate model listeners.
     *
     * @param cffViewMaster the cff view master
     * @return the cff view master that was removed
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.DELETE)
    @Override
    public CffViewMaster deleteCffViewMaster(CffViewMaster cffViewMaster)
        throws SystemException {
        return cffViewMasterPersistence.remove(cffViewMaster);
    }

    @Override
    public DynamicQuery dynamicQuery() {
        Class<?> clazz = getClass();

        return DynamicQueryFactoryUtil.forClass(CffViewMaster.class,
            clazz.getClassLoader());
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery the dynamic query
     * @return the matching rows
     * @throws SystemException if a system exception occurred
     */
    @Override
    @SuppressWarnings("rawtypes")
    public List dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return cffViewMasterPersistence.findWithDynamicQuery(dynamicQuery);
    }

    /**
     * Performs a dynamic query on the database and returns a range of the matching rows.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param dynamicQuery the dynamic query
     * @param start the lower bound of the range of model instances
     * @param end the upper bound of the range of model instances (not inclusive)
     * @return the range of matching rows
     * @throws SystemException if a system exception occurred
     */
    @Override
    @SuppressWarnings("rawtypes")
    public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return cffViewMasterPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    /**
     * Performs a dynamic query on the database and returns an ordered range of the matching rows.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param dynamicQuery the dynamic query
     * @param start the lower bound of the range of model instances
     * @param end the upper bound of the range of model instances (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching rows
     * @throws SystemException if a system exception occurred
     */
    @Override
    @SuppressWarnings("rawtypes")
    public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return cffViewMasterPersistence.findWithDynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
     * Returns the number of rows that match the dynamic query.
     *
     * @param dynamicQuery the dynamic query
     * @return the number of rows that match the dynamic query
     * @throws SystemException if a system exception occurred
     */
    @Override
    public long dynamicQueryCount(DynamicQuery dynamicQuery)
        throws SystemException {
        return cffViewMasterPersistence.countWithDynamicQuery(dynamicQuery);
    }

    /**
     * Returns the number of rows that match the dynamic query.
     *
     * @param dynamicQuery the dynamic query
     * @param projection the projection to apply to the query
     * @return the number of rows that match the dynamic query
     * @throws SystemException if a system exception occurred
     */
    @Override
    public long dynamicQueryCount(DynamicQuery dynamicQuery,
        Projection projection) throws SystemException {
        return cffViewMasterPersistence.countWithDynamicQuery(dynamicQuery,
            projection);
    }

    @Override
    public CffViewMaster fetchCffViewMaster(int cffViewMasterSid)
        throws SystemException {
        return cffViewMasterPersistence.fetchByPrimaryKey(cffViewMasterSid);
    }

    /**
     * Returns the cff view master with the primary key.
     *
     * @param cffViewMasterSid the primary key of the cff view master
     * @return the cff view master
     * @throws PortalException if a cff view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffViewMaster getCffViewMaster(int cffViewMasterSid)
        throws PortalException, SystemException {
        return cffViewMasterPersistence.findByPrimaryKey(cffViewMasterSid);
    }

    @Override
    public PersistedModel getPersistedModel(Serializable primaryKeyObj)
        throws PortalException, SystemException {
        return cffViewMasterPersistence.findByPrimaryKey(primaryKeyObj);
    }

    /**
     * Returns a range of all the cff view masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff view masters
     * @param end the upper bound of the range of cff view masters (not inclusive)
     * @return the range of cff view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffViewMaster> getCffViewMasters(int start, int end)
        throws SystemException {
        return cffViewMasterPersistence.findAll(start, end);
    }

    /**
     * Returns the number of cff view masters.
     *
     * @return the number of cff view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int getCffViewMastersCount() throws SystemException {
        return cffViewMasterPersistence.countAll();
    }

    /**
     * Updates the cff view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param cffViewMaster the cff view master
     * @return the cff view master that was updated
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public CffViewMaster updateCffViewMaster(CffViewMaster cffViewMaster)
        throws SystemException {
        return cffViewMasterPersistence.update(cffViewMaster);
    }

    /**
     * Returns the ac base rate baseline details local service.
     *
     * @return the ac base rate baseline details local service
     */
    public com.stpl.app.parttwo.service.AcBaseRateBaselineDetailsLocalService getAcBaseRateBaselineDetailsLocalService() {
        return acBaseRateBaselineDetailsLocalService;
    }

    /**
     * Sets the ac base rate baseline details local service.
     *
     * @param acBaseRateBaselineDetailsLocalService the ac base rate baseline details local service
     */
    public void setAcBaseRateBaselineDetailsLocalService(
        com.stpl.app.parttwo.service.AcBaseRateBaselineDetailsLocalService acBaseRateBaselineDetailsLocalService) {
        this.acBaseRateBaselineDetailsLocalService = acBaseRateBaselineDetailsLocalService;
    }

    /**
     * Returns the ac base rate baseline details persistence.
     *
     * @return the ac base rate baseline details persistence
     */
    public AcBaseRateBaselineDetailsPersistence getAcBaseRateBaselineDetailsPersistence() {
        return acBaseRateBaselineDetailsPersistence;
    }

    /**
     * Sets the ac base rate baseline details persistence.
     *
     * @param acBaseRateBaselineDetailsPersistence the ac base rate baseline details persistence
     */
    public void setAcBaseRateBaselineDetailsPersistence(
        AcBaseRateBaselineDetailsPersistence acBaseRateBaselineDetailsPersistence) {
        this.acBaseRateBaselineDetailsPersistence = acBaseRateBaselineDetailsPersistence;
    }

    /**
     * Returns the ac br methodology details local service.
     *
     * @return the ac br methodology details local service
     */
    public com.stpl.app.parttwo.service.AcBrMethodologyDetailsLocalService getAcBrMethodologyDetailsLocalService() {
        return acBrMethodologyDetailsLocalService;
    }

    /**
     * Sets the ac br methodology details local service.
     *
     * @param acBrMethodologyDetailsLocalService the ac br methodology details local service
     */
    public void setAcBrMethodologyDetailsLocalService(
        com.stpl.app.parttwo.service.AcBrMethodologyDetailsLocalService acBrMethodologyDetailsLocalService) {
        this.acBrMethodologyDetailsLocalService = acBrMethodologyDetailsLocalService;
    }

    /**
     * Returns the ac br methodology details persistence.
     *
     * @return the ac br methodology details persistence
     */
    public AcBrMethodologyDetailsPersistence getAcBrMethodologyDetailsPersistence() {
        return acBrMethodologyDetailsPersistence;
    }

    /**
     * Sets the ac br methodology details persistence.
     *
     * @param acBrMethodologyDetailsPersistence the ac br methodology details persistence
     */
    public void setAcBrMethodologyDetailsPersistence(
        AcBrMethodologyDetailsPersistence acBrMethodologyDetailsPersistence) {
        this.acBrMethodologyDetailsPersistence = acBrMethodologyDetailsPersistence;
    }

    /**
     * Returns the acc closure details local service.
     *
     * @return the acc closure details local service
     */
    public com.stpl.app.parttwo.service.AccClosureDetailsLocalService getAccClosureDetailsLocalService() {
        return accClosureDetailsLocalService;
    }

    /**
     * Sets the acc closure details local service.
     *
     * @param accClosureDetailsLocalService the acc closure details local service
     */
    public void setAccClosureDetailsLocalService(
        com.stpl.app.parttwo.service.AccClosureDetailsLocalService accClosureDetailsLocalService) {
        this.accClosureDetailsLocalService = accClosureDetailsLocalService;
    }

    /**
     * Returns the acc closure details persistence.
     *
     * @return the acc closure details persistence
     */
    public AccClosureDetailsPersistence getAccClosureDetailsPersistence() {
        return accClosureDetailsPersistence;
    }

    /**
     * Sets the acc closure details persistence.
     *
     * @param accClosureDetailsPersistence the acc closure details persistence
     */
    public void setAccClosureDetailsPersistence(
        AccClosureDetailsPersistence accClosureDetailsPersistence) {
        this.accClosureDetailsPersistence = accClosureDetailsPersistence;
    }

    /**
     * Returns the acc closure master local service.
     *
     * @return the acc closure master local service
     */
    public com.stpl.app.parttwo.service.AccClosureMasterLocalService getAccClosureMasterLocalService() {
        return accClosureMasterLocalService;
    }

    /**
     * Sets the acc closure master local service.
     *
     * @param accClosureMasterLocalService the acc closure master local service
     */
    public void setAccClosureMasterLocalService(
        com.stpl.app.parttwo.service.AccClosureMasterLocalService accClosureMasterLocalService) {
        this.accClosureMasterLocalService = accClosureMasterLocalService;
    }

    /**
     * Returns the acc closure master persistence.
     *
     * @return the acc closure master persistence
     */
    public AccClosureMasterPersistence getAccClosureMasterPersistence() {
        return accClosureMasterPersistence;
    }

    /**
     * Sets the acc closure master persistence.
     *
     * @param accClosureMasterPersistence the acc closure master persistence
     */
    public void setAccClosureMasterPersistence(
        AccClosureMasterPersistence accClosureMasterPersistence) {
        this.accClosureMasterPersistence = accClosureMasterPersistence;
    }

    /**
     * Returns the acc closure master finder.
     *
     * @return the acc closure master finder
     */
    public AccClosureMasterFinder getAccClosureMasterFinder() {
        return accClosureMasterFinder;
    }

    /**
     * Sets the acc closure master finder.
     *
     * @param accClosureMasterFinder the acc closure master finder
     */
    public void setAccClosureMasterFinder(
        AccClosureMasterFinder accClosureMasterFinder) {
        this.accClosureMasterFinder = accClosureMasterFinder;
    }

    /**
     * Returns the acc closure view master local service.
     *
     * @return the acc closure view master local service
     */
    public com.stpl.app.parttwo.service.AccClosureViewMasterLocalService getAccClosureViewMasterLocalService() {
        return accClosureViewMasterLocalService;
    }

    /**
     * Sets the acc closure view master local service.
     *
     * @param accClosureViewMasterLocalService the acc closure view master local service
     */
    public void setAccClosureViewMasterLocalService(
        com.stpl.app.parttwo.service.AccClosureViewMasterLocalService accClosureViewMasterLocalService) {
        this.accClosureViewMasterLocalService = accClosureViewMasterLocalService;
    }

    /**
     * Returns the acc closure view master persistence.
     *
     * @return the acc closure view master persistence
     */
    public AccClosureViewMasterPersistence getAccClosureViewMasterPersistence() {
        return accClosureViewMasterPersistence;
    }

    /**
     * Sets the acc closure view master persistence.
     *
     * @param accClosureViewMasterPersistence the acc closure view master persistence
     */
    public void setAccClosureViewMasterPersistence(
        AccClosureViewMasterPersistence accClosureViewMasterPersistence) {
        this.accClosureViewMasterPersistence = accClosureViewMasterPersistence;
    }

    /**
     * Returns the ac fd adjustment selection local service.
     *
     * @return the ac fd adjustment selection local service
     */
    public com.stpl.app.parttwo.service.AcFdAdjustmentSelectionLocalService getAcFdAdjustmentSelectionLocalService() {
        return acFdAdjustmentSelectionLocalService;
    }

    /**
     * Sets the ac fd adjustment selection local service.
     *
     * @param acFdAdjustmentSelectionLocalService the ac fd adjustment selection local service
     */
    public void setAcFdAdjustmentSelectionLocalService(
        com.stpl.app.parttwo.service.AcFdAdjustmentSelectionLocalService acFdAdjustmentSelectionLocalService) {
        this.acFdAdjustmentSelectionLocalService = acFdAdjustmentSelectionLocalService;
    }

    /**
     * Returns the ac fd adjustment selection persistence.
     *
     * @return the ac fd adjustment selection persistence
     */
    public AcFdAdjustmentSelectionPersistence getAcFdAdjustmentSelectionPersistence() {
        return acFdAdjustmentSelectionPersistence;
    }

    /**
     * Sets the ac fd adjustment selection persistence.
     *
     * @param acFdAdjustmentSelectionPersistence the ac fd adjustment selection persistence
     */
    public void setAcFdAdjustmentSelectionPersistence(
        AcFdAdjustmentSelectionPersistence acFdAdjustmentSelectionPersistence) {
        this.acFdAdjustmentSelectionPersistence = acFdAdjustmentSelectionPersistence;
    }

    /**
     * Returns the adjusted demand forecast local service.
     *
     * @return the adjusted demand forecast local service
     */
    public com.stpl.app.parttwo.service.AdjustedDemandForecastLocalService getAdjustedDemandForecastLocalService() {
        return adjustedDemandForecastLocalService;
    }

    /**
     * Sets the adjusted demand forecast local service.
     *
     * @param adjustedDemandForecastLocalService the adjusted demand forecast local service
     */
    public void setAdjustedDemandForecastLocalService(
        com.stpl.app.parttwo.service.AdjustedDemandForecastLocalService adjustedDemandForecastLocalService) {
        this.adjustedDemandForecastLocalService = adjustedDemandForecastLocalService;
    }

    /**
     * Returns the adjusted demand forecast persistence.
     *
     * @return the adjusted demand forecast persistence
     */
    public AdjustedDemandForecastPersistence getAdjustedDemandForecastPersistence() {
        return adjustedDemandForecastPersistence;
    }

    /**
     * Sets the adjusted demand forecast persistence.
     *
     * @param adjustedDemandForecastPersistence the adjusted demand forecast persistence
     */
    public void setAdjustedDemandForecastPersistence(
        AdjustedDemandForecastPersistence adjustedDemandForecastPersistence) {
        this.adjustedDemandForecastPersistence = adjustedDemandForecastPersistence;
    }

    /**
     * Returns the ah temp details local service.
     *
     * @return the ah temp details local service
     */
    public com.stpl.app.parttwo.service.AhTempDetailsLocalService getAhTempDetailsLocalService() {
        return ahTempDetailsLocalService;
    }

    /**
     * Sets the ah temp details local service.
     *
     * @param ahTempDetailsLocalService the ah temp details local service
     */
    public void setAhTempDetailsLocalService(
        com.stpl.app.parttwo.service.AhTempDetailsLocalService ahTempDetailsLocalService) {
        this.ahTempDetailsLocalService = ahTempDetailsLocalService;
    }

    /**
     * Returns the ah temp details persistence.
     *
     * @return the ah temp details persistence
     */
    public AhTempDetailsPersistence getAhTempDetailsPersistence() {
        return ahTempDetailsPersistence;
    }

    /**
     * Sets the ah temp details persistence.
     *
     * @param ahTempDetailsPersistence the ah temp details persistence
     */
    public void setAhTempDetailsPersistence(
        AhTempDetailsPersistence ahTempDetailsPersistence) {
        this.ahTempDetailsPersistence = ahTempDetailsPersistence;
    }

    /**
     * Returns the arp outbound local service.
     *
     * @return the arp outbound local service
     */
    public com.stpl.app.parttwo.service.ArpOutboundLocalService getArpOutboundLocalService() {
        return arpOutboundLocalService;
    }

    /**
     * Sets the arp outbound local service.
     *
     * @param arpOutboundLocalService the arp outbound local service
     */
    public void setArpOutboundLocalService(
        com.stpl.app.parttwo.service.ArpOutboundLocalService arpOutboundLocalService) {
        this.arpOutboundLocalService = arpOutboundLocalService;
    }

    /**
     * Returns the arp outbound persistence.
     *
     * @return the arp outbound persistence
     */
    public ArpOutboundPersistence getArpOutboundPersistence() {
        return arpOutboundPersistence;
    }

    /**
     * Sets the arp outbound persistence.
     *
     * @param arpOutboundPersistence the arp outbound persistence
     */
    public void setArpOutboundPersistence(
        ArpOutboundPersistence arpOutboundPersistence) {
        this.arpOutboundPersistence = arpOutboundPersistence;
    }

    /**
     * Returns the cff additional info local service.
     *
     * @return the cff additional info local service
     */
    public com.stpl.app.parttwo.service.CffAdditionalInfoLocalService getCffAdditionalInfoLocalService() {
        return cffAdditionalInfoLocalService;
    }

    /**
     * Sets the cff additional info local service.
     *
     * @param cffAdditionalInfoLocalService the cff additional info local service
     */
    public void setCffAdditionalInfoLocalService(
        com.stpl.app.parttwo.service.CffAdditionalInfoLocalService cffAdditionalInfoLocalService) {
        this.cffAdditionalInfoLocalService = cffAdditionalInfoLocalService;
    }

    /**
     * Returns the cff additional info persistence.
     *
     * @return the cff additional info persistence
     */
    public CffAdditionalInfoPersistence getCffAdditionalInfoPersistence() {
        return cffAdditionalInfoPersistence;
    }

    /**
     * Sets the cff additional info persistence.
     *
     * @param cffAdditionalInfoPersistence the cff additional info persistence
     */
    public void setCffAdditionalInfoPersistence(
        CffAdditionalInfoPersistence cffAdditionalInfoPersistence) {
        this.cffAdditionalInfoPersistence = cffAdditionalInfoPersistence;
    }

    /**
     * Returns the cff approval details local service.
     *
     * @return the cff approval details local service
     */
    public com.stpl.app.parttwo.service.CffApprovalDetailsLocalService getCffApprovalDetailsLocalService() {
        return cffApprovalDetailsLocalService;
    }

    /**
     * Sets the cff approval details local service.
     *
     * @param cffApprovalDetailsLocalService the cff approval details local service
     */
    public void setCffApprovalDetailsLocalService(
        com.stpl.app.parttwo.service.CffApprovalDetailsLocalService cffApprovalDetailsLocalService) {
        this.cffApprovalDetailsLocalService = cffApprovalDetailsLocalService;
    }

    /**
     * Returns the cff approval details persistence.
     *
     * @return the cff approval details persistence
     */
    public CffApprovalDetailsPersistence getCffApprovalDetailsPersistence() {
        return cffApprovalDetailsPersistence;
    }

    /**
     * Sets the cff approval details persistence.
     *
     * @param cffApprovalDetailsPersistence the cff approval details persistence
     */
    public void setCffApprovalDetailsPersistence(
        CffApprovalDetailsPersistence cffApprovalDetailsPersistence) {
        this.cffApprovalDetailsPersistence = cffApprovalDetailsPersistence;
    }

    /**
     * Returns the cff cust hierarchy local service.
     *
     * @return the cff cust hierarchy local service
     */
    public com.stpl.app.parttwo.service.CffCustHierarchyLocalService getCffCustHierarchyLocalService() {
        return cffCustHierarchyLocalService;
    }

    /**
     * Sets the cff cust hierarchy local service.
     *
     * @param cffCustHierarchyLocalService the cff cust hierarchy local service
     */
    public void setCffCustHierarchyLocalService(
        com.stpl.app.parttwo.service.CffCustHierarchyLocalService cffCustHierarchyLocalService) {
        this.cffCustHierarchyLocalService = cffCustHierarchyLocalService;
    }

    /**
     * Returns the cff cust hierarchy persistence.
     *
     * @return the cff cust hierarchy persistence
     */
    public CffCustHierarchyPersistence getCffCustHierarchyPersistence() {
        return cffCustHierarchyPersistence;
    }

    /**
     * Sets the cff cust hierarchy persistence.
     *
     * @param cffCustHierarchyPersistence the cff cust hierarchy persistence
     */
    public void setCffCustHierarchyPersistence(
        CffCustHierarchyPersistence cffCustHierarchyPersistence) {
        this.cffCustHierarchyPersistence = cffCustHierarchyPersistence;
    }

    /**
     * Returns the cff custom view details local service.
     *
     * @return the cff custom view details local service
     */
    public com.stpl.app.parttwo.service.CffCustomViewDetailsLocalService getCffCustomViewDetailsLocalService() {
        return cffCustomViewDetailsLocalService;
    }

    /**
     * Sets the cff custom view details local service.
     *
     * @param cffCustomViewDetailsLocalService the cff custom view details local service
     */
    public void setCffCustomViewDetailsLocalService(
        com.stpl.app.parttwo.service.CffCustomViewDetailsLocalService cffCustomViewDetailsLocalService) {
        this.cffCustomViewDetailsLocalService = cffCustomViewDetailsLocalService;
    }

    /**
     * Returns the cff custom view details persistence.
     *
     * @return the cff custom view details persistence
     */
    public CffCustomViewDetailsPersistence getCffCustomViewDetailsPersistence() {
        return cffCustomViewDetailsPersistence;
    }

    /**
     * Sets the cff custom view details persistence.
     *
     * @param cffCustomViewDetailsPersistence the cff custom view details persistence
     */
    public void setCffCustomViewDetailsPersistence(
        CffCustomViewDetailsPersistence cffCustomViewDetailsPersistence) {
        this.cffCustomViewDetailsPersistence = cffCustomViewDetailsPersistence;
    }

    /**
     * Returns the cff custom view master local service.
     *
     * @return the cff custom view master local service
     */
    public com.stpl.app.parttwo.service.CffCustomViewMasterLocalService getCffCustomViewMasterLocalService() {
        return cffCustomViewMasterLocalService;
    }

    /**
     * Sets the cff custom view master local service.
     *
     * @param cffCustomViewMasterLocalService the cff custom view master local service
     */
    public void setCffCustomViewMasterLocalService(
        com.stpl.app.parttwo.service.CffCustomViewMasterLocalService cffCustomViewMasterLocalService) {
        this.cffCustomViewMasterLocalService = cffCustomViewMasterLocalService;
    }

    /**
     * Returns the cff custom view master persistence.
     *
     * @return the cff custom view master persistence
     */
    public CffCustomViewMasterPersistence getCffCustomViewMasterPersistence() {
        return cffCustomViewMasterPersistence;
    }

    /**
     * Sets the cff custom view master persistence.
     *
     * @param cffCustomViewMasterPersistence the cff custom view master persistence
     */
    public void setCffCustomViewMasterPersistence(
        CffCustomViewMasterPersistence cffCustomViewMasterPersistence) {
        this.cffCustomViewMasterPersistence = cffCustomViewMasterPersistence;
    }

    /**
     * Returns the cff details local service.
     *
     * @return the cff details local service
     */
    public com.stpl.app.parttwo.service.CffDetailsLocalService getCffDetailsLocalService() {
        return cffDetailsLocalService;
    }

    /**
     * Sets the cff details local service.
     *
     * @param cffDetailsLocalService the cff details local service
     */
    public void setCffDetailsLocalService(
        com.stpl.app.parttwo.service.CffDetailsLocalService cffDetailsLocalService) {
        this.cffDetailsLocalService = cffDetailsLocalService;
    }

    /**
     * Returns the cff details persistence.
     *
     * @return the cff details persistence
     */
    public CffDetailsPersistence getCffDetailsPersistence() {
        return cffDetailsPersistence;
    }

    /**
     * Sets the cff details persistence.
     *
     * @param cffDetailsPersistence the cff details persistence
     */
    public void setCffDetailsPersistence(
        CffDetailsPersistence cffDetailsPersistence) {
        this.cffDetailsPersistence = cffDetailsPersistence;
    }

    /**
     * Returns the cff doc details local service.
     *
     * @return the cff doc details local service
     */
    public com.stpl.app.parttwo.service.CffDocDetailsLocalService getCffDocDetailsLocalService() {
        return cffDocDetailsLocalService;
    }

    /**
     * Sets the cff doc details local service.
     *
     * @param cffDocDetailsLocalService the cff doc details local service
     */
    public void setCffDocDetailsLocalService(
        com.stpl.app.parttwo.service.CffDocDetailsLocalService cffDocDetailsLocalService) {
        this.cffDocDetailsLocalService = cffDocDetailsLocalService;
    }

    /**
     * Returns the cff doc details persistence.
     *
     * @return the cff doc details persistence
     */
    public CffDocDetailsPersistence getCffDocDetailsPersistence() {
        return cffDocDetailsPersistence;
    }

    /**
     * Sets the cff doc details persistence.
     *
     * @param cffDocDetailsPersistence the cff doc details persistence
     */
    public void setCffDocDetailsPersistence(
        CffDocDetailsPersistence cffDocDetailsPersistence) {
        this.cffDocDetailsPersistence = cffDocDetailsPersistence;
    }

    /**
     * Returns the cff master local service.
     *
     * @return the cff master local service
     */
    public com.stpl.app.parttwo.service.CffMasterLocalService getCffMasterLocalService() {
        return cffMasterLocalService;
    }

    /**
     * Sets the cff master local service.
     *
     * @param cffMasterLocalService the cff master local service
     */
    public void setCffMasterLocalService(
        com.stpl.app.parttwo.service.CffMasterLocalService cffMasterLocalService) {
        this.cffMasterLocalService = cffMasterLocalService;
    }

    /**
     * Returns the cff master persistence.
     *
     * @return the cff master persistence
     */
    public CffMasterPersistence getCffMasterPersistence() {
        return cffMasterPersistence;
    }

    /**
     * Sets the cff master persistence.
     *
     * @param cffMasterPersistence the cff master persistence
     */
    public void setCffMasterPersistence(
        CffMasterPersistence cffMasterPersistence) {
        this.cffMasterPersistence = cffMasterPersistence;
    }

    /**
     * Returns the cff prod hierarchy local service.
     *
     * @return the cff prod hierarchy local service
     */
    public com.stpl.app.parttwo.service.CffProdHierarchyLocalService getCffProdHierarchyLocalService() {
        return cffProdHierarchyLocalService;
    }

    /**
     * Sets the cff prod hierarchy local service.
     *
     * @param cffProdHierarchyLocalService the cff prod hierarchy local service
     */
    public void setCffProdHierarchyLocalService(
        com.stpl.app.parttwo.service.CffProdHierarchyLocalService cffProdHierarchyLocalService) {
        this.cffProdHierarchyLocalService = cffProdHierarchyLocalService;
    }

    /**
     * Returns the cff prod hierarchy persistence.
     *
     * @return the cff prod hierarchy persistence
     */
    public CffProdHierarchyPersistence getCffProdHierarchyPersistence() {
        return cffProdHierarchyPersistence;
    }

    /**
     * Sets the cff prod hierarchy persistence.
     *
     * @param cffProdHierarchyPersistence the cff prod hierarchy persistence
     */
    public void setCffProdHierarchyPersistence(
        CffProdHierarchyPersistence cffProdHierarchyPersistence) {
        this.cffProdHierarchyPersistence = cffProdHierarchyPersistence;
    }

    /**
     * Returns the cff view master local service.
     *
     * @return the cff view master local service
     */
    public com.stpl.app.parttwo.service.CffViewMasterLocalService getCffViewMasterLocalService() {
        return cffViewMasterLocalService;
    }

    /**
     * Sets the cff view master local service.
     *
     * @param cffViewMasterLocalService the cff view master local service
     */
    public void setCffViewMasterLocalService(
        com.stpl.app.parttwo.service.CffViewMasterLocalService cffViewMasterLocalService) {
        this.cffViewMasterLocalService = cffViewMasterLocalService;
    }

    /**
     * Returns the cff view master persistence.
     *
     * @return the cff view master persistence
     */
    public CffViewMasterPersistence getCffViewMasterPersistence() {
        return cffViewMasterPersistence;
    }

    /**
     * Sets the cff view master persistence.
     *
     * @param cffViewMasterPersistence the cff view master persistence
     */
    public void setCffViewMasterPersistence(
        CffViewMasterPersistence cffViewMasterPersistence) {
        this.cffViewMasterPersistence = cffViewMasterPersistence;
    }

    /**
     * Returns the customer gts actual local service.
     *
     * @return the customer gts actual local service
     */
    public com.stpl.app.parttwo.service.CustomerGtsActualLocalService getCustomerGtsActualLocalService() {
        return customerGtsActualLocalService;
    }

    /**
     * Sets the customer gts actual local service.
     *
     * @param customerGtsActualLocalService the customer gts actual local service
     */
    public void setCustomerGtsActualLocalService(
        com.stpl.app.parttwo.service.CustomerGtsActualLocalService customerGtsActualLocalService) {
        this.customerGtsActualLocalService = customerGtsActualLocalService;
    }

    /**
     * Returns the customer gts actual persistence.
     *
     * @return the customer gts actual persistence
     */
    public CustomerGtsActualPersistence getCustomerGtsActualPersistence() {
        return customerGtsActualPersistence;
    }

    /**
     * Sets the customer gts actual persistence.
     *
     * @param customerGtsActualPersistence the customer gts actual persistence
     */
    public void setCustomerGtsActualPersistence(
        CustomerGtsActualPersistence customerGtsActualPersistence) {
        this.customerGtsActualPersistence = customerGtsActualPersistence;
    }

    /**
     * Returns the customer gts forecast local service.
     *
     * @return the customer gts forecast local service
     */
    public com.stpl.app.parttwo.service.CustomerGtsForecastLocalService getCustomerGtsForecastLocalService() {
        return customerGtsForecastLocalService;
    }

    /**
     * Sets the customer gts forecast local service.
     *
     * @param customerGtsForecastLocalService the customer gts forecast local service
     */
    public void setCustomerGtsForecastLocalService(
        com.stpl.app.parttwo.service.CustomerGtsForecastLocalService customerGtsForecastLocalService) {
        this.customerGtsForecastLocalService = customerGtsForecastLocalService;
    }

    /**
     * Returns the customer gts forecast persistence.
     *
     * @return the customer gts forecast persistence
     */
    public CustomerGtsForecastPersistence getCustomerGtsForecastPersistence() {
        return customerGtsForecastPersistence;
    }

    /**
     * Sets the customer gts forecast persistence.
     *
     * @param customerGtsForecastPersistence the customer gts forecast persistence
     */
    public void setCustomerGtsForecastPersistence(
        CustomerGtsForecastPersistence customerGtsForecastPersistence) {
        this.customerGtsForecastPersistence = customerGtsForecastPersistence;
    }

    /**
     * Returns the ivld accrual inbound local service.
     *
     * @return the ivld accrual inbound local service
     */
    public com.stpl.app.parttwo.service.IvldAccrualInboundLocalService getIvldAccrualInboundLocalService() {
        return ivldAccrualInboundLocalService;
    }

    /**
     * Sets the ivld accrual inbound local service.
     *
     * @param ivldAccrualInboundLocalService the ivld accrual inbound local service
     */
    public void setIvldAccrualInboundLocalService(
        com.stpl.app.parttwo.service.IvldAccrualInboundLocalService ivldAccrualInboundLocalService) {
        this.ivldAccrualInboundLocalService = ivldAccrualInboundLocalService;
    }

    /**
     * Returns the ivld accrual inbound persistence.
     *
     * @return the ivld accrual inbound persistence
     */
    public IvldAccrualInboundPersistence getIvldAccrualInboundPersistence() {
        return ivldAccrualInboundPersistence;
    }

    /**
     * Sets the ivld accrual inbound persistence.
     *
     * @param ivldAccrualInboundPersistence the ivld accrual inbound persistence
     */
    public void setIvldAccrualInboundPersistence(
        IvldAccrualInboundPersistence ivldAccrualInboundPersistence) {
        this.ivldAccrualInboundPersistence = ivldAccrualInboundPersistence;
    }

    /**
     * Returns the ivld company identifier local service.
     *
     * @return the ivld company identifier local service
     */
    public com.stpl.app.parttwo.service.IvldCompanyIdentifierLocalService getIvldCompanyIdentifierLocalService() {
        return ivldCompanyIdentifierLocalService;
    }

    /**
     * Sets the ivld company identifier local service.
     *
     * @param ivldCompanyIdentifierLocalService the ivld company identifier local service
     */
    public void setIvldCompanyIdentifierLocalService(
        com.stpl.app.parttwo.service.IvldCompanyIdentifierLocalService ivldCompanyIdentifierLocalService) {
        this.ivldCompanyIdentifierLocalService = ivldCompanyIdentifierLocalService;
    }

    /**
     * Returns the ivld company identifier persistence.
     *
     * @return the ivld company identifier persistence
     */
    public IvldCompanyIdentifierPersistence getIvldCompanyIdentifierPersistence() {
        return ivldCompanyIdentifierPersistence;
    }

    /**
     * Sets the ivld company identifier persistence.
     *
     * @param ivldCompanyIdentifierPersistence the ivld company identifier persistence
     */
    public void setIvldCompanyIdentifierPersistence(
        IvldCompanyIdentifierPersistence ivldCompanyIdentifierPersistence) {
        this.ivldCompanyIdentifierPersistence = ivldCompanyIdentifierPersistence;
    }

    /**
     * Returns the ivld company master local service.
     *
     * @return the ivld company master local service
     */
    public com.stpl.app.parttwo.service.IvldCompanyMasterLocalService getIvldCompanyMasterLocalService() {
        return ivldCompanyMasterLocalService;
    }

    /**
     * Sets the ivld company master local service.
     *
     * @param ivldCompanyMasterLocalService the ivld company master local service
     */
    public void setIvldCompanyMasterLocalService(
        com.stpl.app.parttwo.service.IvldCompanyMasterLocalService ivldCompanyMasterLocalService) {
        this.ivldCompanyMasterLocalService = ivldCompanyMasterLocalService;
    }

    /**
     * Returns the ivld company master persistence.
     *
     * @return the ivld company master persistence
     */
    public IvldCompanyMasterPersistence getIvldCompanyMasterPersistence() {
        return ivldCompanyMasterPersistence;
    }

    /**
     * Sets the ivld company master persistence.
     *
     * @param ivldCompanyMasterPersistence the ivld company master persistence
     */
    public void setIvldCompanyMasterPersistence(
        IvldCompanyMasterPersistence ivldCompanyMasterPersistence) {
        this.ivldCompanyMasterPersistence = ivldCompanyMasterPersistence;
    }

    /**
     * Returns the ivld company parent local service.
     *
     * @return the ivld company parent local service
     */
    public com.stpl.app.parttwo.service.IvldCompanyParentLocalService getIvldCompanyParentLocalService() {
        return ivldCompanyParentLocalService;
    }

    /**
     * Sets the ivld company parent local service.
     *
     * @param ivldCompanyParentLocalService the ivld company parent local service
     */
    public void setIvldCompanyParentLocalService(
        com.stpl.app.parttwo.service.IvldCompanyParentLocalService ivldCompanyParentLocalService) {
        this.ivldCompanyParentLocalService = ivldCompanyParentLocalService;
    }

    /**
     * Returns the ivld company parent persistence.
     *
     * @return the ivld company parent persistence
     */
    public IvldCompanyParentPersistence getIvldCompanyParentPersistence() {
        return ivldCompanyParentPersistence;
    }

    /**
     * Sets the ivld company parent persistence.
     *
     * @param ivldCompanyParentPersistence the ivld company parent persistence
     */
    public void setIvldCompanyParentPersistence(
        IvldCompanyParentPersistence ivldCompanyParentPersistence) {
        this.ivldCompanyParentPersistence = ivldCompanyParentPersistence;
    }

    /**
     * Returns the ivld company trade class local service.
     *
     * @return the ivld company trade class local service
     */
    public com.stpl.app.parttwo.service.IvldCompanyTradeClassLocalService getIvldCompanyTradeClassLocalService() {
        return ivldCompanyTradeClassLocalService;
    }

    /**
     * Sets the ivld company trade class local service.
     *
     * @param ivldCompanyTradeClassLocalService the ivld company trade class local service
     */
    public void setIvldCompanyTradeClassLocalService(
        com.stpl.app.parttwo.service.IvldCompanyTradeClassLocalService ivldCompanyTradeClassLocalService) {
        this.ivldCompanyTradeClassLocalService = ivldCompanyTradeClassLocalService;
    }

    /**
     * Returns the ivld company trade class persistence.
     *
     * @return the ivld company trade class persistence
     */
    public IvldCompanyTradeClassPersistence getIvldCompanyTradeClassPersistence() {
        return ivldCompanyTradeClassPersistence;
    }

    /**
     * Sets the ivld company trade class persistence.
     *
     * @param ivldCompanyTradeClassPersistence the ivld company trade class persistence
     */
    public void setIvldCompanyTradeClassPersistence(
        IvldCompanyTradeClassPersistence ivldCompanyTradeClassPersistence) {
        this.ivldCompanyTradeClassPersistence = ivldCompanyTradeClassPersistence;
    }

    /**
     * Returns the ivld customer gts actual local service.
     *
     * @return the ivld customer gts actual local service
     */
    public com.stpl.app.parttwo.service.IvldCustomerGtsActualLocalService getIvldCustomerGtsActualLocalService() {
        return ivldCustomerGtsActualLocalService;
    }

    /**
     * Sets the ivld customer gts actual local service.
     *
     * @param ivldCustomerGtsActualLocalService the ivld customer gts actual local service
     */
    public void setIvldCustomerGtsActualLocalService(
        com.stpl.app.parttwo.service.IvldCustomerGtsActualLocalService ivldCustomerGtsActualLocalService) {
        this.ivldCustomerGtsActualLocalService = ivldCustomerGtsActualLocalService;
    }

    /**
     * Returns the ivld customer gts actual persistence.
     *
     * @return the ivld customer gts actual persistence
     */
    public IvldCustomerGtsActualPersistence getIvldCustomerGtsActualPersistence() {
        return ivldCustomerGtsActualPersistence;
    }

    /**
     * Sets the ivld customer gts actual persistence.
     *
     * @param ivldCustomerGtsActualPersistence the ivld customer gts actual persistence
     */
    public void setIvldCustomerGtsActualPersistence(
        IvldCustomerGtsActualPersistence ivldCustomerGtsActualPersistence) {
        this.ivldCustomerGtsActualPersistence = ivldCustomerGtsActualPersistence;
    }

    /**
     * Returns the ivld customer gts forecast local service.
     *
     * @return the ivld customer gts forecast local service
     */
    public com.stpl.app.parttwo.service.IvldCustomerGtsForecastLocalService getIvldCustomerGtsForecastLocalService() {
        return ivldCustomerGtsForecastLocalService;
    }

    /**
     * Sets the ivld customer gts forecast local service.
     *
     * @param ivldCustomerGtsForecastLocalService the ivld customer gts forecast local service
     */
    public void setIvldCustomerGtsForecastLocalService(
        com.stpl.app.parttwo.service.IvldCustomerGtsForecastLocalService ivldCustomerGtsForecastLocalService) {
        this.ivldCustomerGtsForecastLocalService = ivldCustomerGtsForecastLocalService;
    }

    /**
     * Returns the ivld customer gts forecast persistence.
     *
     * @return the ivld customer gts forecast persistence
     */
    public IvldCustomerGtsForecastPersistence getIvldCustomerGtsForecastPersistence() {
        return ivldCustomerGtsForecastPersistence;
    }

    /**
     * Sets the ivld customer gts forecast persistence.
     *
     * @param ivldCustomerGtsForecastPersistence the ivld customer gts forecast persistence
     */
    public void setIvldCustomerGtsForecastPersistence(
        IvldCustomerGtsForecastPersistence ivldCustomerGtsForecastPersistence) {
        this.ivldCustomerGtsForecastPersistence = ivldCustomerGtsForecastPersistence;
    }

    /**
     * Returns the ivld item identifier local service.
     *
     * @return the ivld item identifier local service
     */
    public com.stpl.app.parttwo.service.IvldItemIdentifierLocalService getIvldItemIdentifierLocalService() {
        return ivldItemIdentifierLocalService;
    }

    /**
     * Sets the ivld item identifier local service.
     *
     * @param ivldItemIdentifierLocalService the ivld item identifier local service
     */
    public void setIvldItemIdentifierLocalService(
        com.stpl.app.parttwo.service.IvldItemIdentifierLocalService ivldItemIdentifierLocalService) {
        this.ivldItemIdentifierLocalService = ivldItemIdentifierLocalService;
    }

    /**
     * Returns the ivld item identifier persistence.
     *
     * @return the ivld item identifier persistence
     */
    public IvldItemIdentifierPersistence getIvldItemIdentifierPersistence() {
        return ivldItemIdentifierPersistence;
    }

    /**
     * Sets the ivld item identifier persistence.
     *
     * @param ivldItemIdentifierPersistence the ivld item identifier persistence
     */
    public void setIvldItemIdentifierPersistence(
        IvldItemIdentifierPersistence ivldItemIdentifierPersistence) {
        this.ivldItemIdentifierPersistence = ivldItemIdentifierPersistence;
    }

    /**
     * Returns the ivld item master local service.
     *
     * @return the ivld item master local service
     */
    public com.stpl.app.parttwo.service.IvldItemMasterLocalService getIvldItemMasterLocalService() {
        return ivldItemMasterLocalService;
    }

    /**
     * Sets the ivld item master local service.
     *
     * @param ivldItemMasterLocalService the ivld item master local service
     */
    public void setIvldItemMasterLocalService(
        com.stpl.app.parttwo.service.IvldItemMasterLocalService ivldItemMasterLocalService) {
        this.ivldItemMasterLocalService = ivldItemMasterLocalService;
    }

    /**
     * Returns the ivld item master persistence.
     *
     * @return the ivld item master persistence
     */
    public IvldItemMasterPersistence getIvldItemMasterPersistence() {
        return ivldItemMasterPersistence;
    }

    /**
     * Sets the ivld item master persistence.
     *
     * @param ivldItemMasterPersistence the ivld item master persistence
     */
    public void setIvldItemMasterPersistence(
        IvldItemMasterPersistence ivldItemMasterPersistence) {
        this.ivldItemMasterPersistence = ivldItemMasterPersistence;
    }

    /**
     * Returns the ivld item pricing local service.
     *
     * @return the ivld item pricing local service
     */
    public com.stpl.app.parttwo.service.IvldItemPricingLocalService getIvldItemPricingLocalService() {
        return ivldItemPricingLocalService;
    }

    /**
     * Sets the ivld item pricing local service.
     *
     * @param ivldItemPricingLocalService the ivld item pricing local service
     */
    public void setIvldItemPricingLocalService(
        com.stpl.app.parttwo.service.IvldItemPricingLocalService ivldItemPricingLocalService) {
        this.ivldItemPricingLocalService = ivldItemPricingLocalService;
    }

    /**
     * Returns the ivld item pricing persistence.
     *
     * @return the ivld item pricing persistence
     */
    public IvldItemPricingPersistence getIvldItemPricingPersistence() {
        return ivldItemPricingPersistence;
    }

    /**
     * Sets the ivld item pricing persistence.
     *
     * @param ivldItemPricingPersistence the ivld item pricing persistence
     */
    public void setIvldItemPricingPersistence(
        IvldItemPricingPersistence ivldItemPricingPersistence) {
        this.ivldItemPricingPersistence = ivldItemPricingPersistence;
    }

    /**
     * Returns the sla calendar details local service.
     *
     * @return the sla calendar details local service
     */
    public com.stpl.app.parttwo.service.SlaCalendarDetailsLocalService getSlaCalendarDetailsLocalService() {
        return slaCalendarDetailsLocalService;
    }

    /**
     * Sets the sla calendar details local service.
     *
     * @param slaCalendarDetailsLocalService the sla calendar details local service
     */
    public void setSlaCalendarDetailsLocalService(
        com.stpl.app.parttwo.service.SlaCalendarDetailsLocalService slaCalendarDetailsLocalService) {
        this.slaCalendarDetailsLocalService = slaCalendarDetailsLocalService;
    }

    /**
     * Returns the sla calendar details persistence.
     *
     * @return the sla calendar details persistence
     */
    public SlaCalendarDetailsPersistence getSlaCalendarDetailsPersistence() {
        return slaCalendarDetailsPersistence;
    }

    /**
     * Sets the sla calendar details persistence.
     *
     * @param slaCalendarDetailsPersistence the sla calendar details persistence
     */
    public void setSlaCalendarDetailsPersistence(
        SlaCalendarDetailsPersistence slaCalendarDetailsPersistence) {
        this.slaCalendarDetailsPersistence = slaCalendarDetailsPersistence;
    }

    /**
     * Returns the sla calendar master local service.
     *
     * @return the sla calendar master local service
     */
    public com.stpl.app.parttwo.service.SlaCalendarMasterLocalService getSlaCalendarMasterLocalService() {
        return slaCalendarMasterLocalService;
    }

    /**
     * Sets the sla calendar master local service.
     *
     * @param slaCalendarMasterLocalService the sla calendar master local service
     */
    public void setSlaCalendarMasterLocalService(
        com.stpl.app.parttwo.service.SlaCalendarMasterLocalService slaCalendarMasterLocalService) {
        this.slaCalendarMasterLocalService = slaCalendarMasterLocalService;
    }

    /**
     * Returns the sla calendar master persistence.
     *
     * @return the sla calendar master persistence
     */
    public SlaCalendarMasterPersistence getSlaCalendarMasterPersistence() {
        return slaCalendarMasterPersistence;
    }

    /**
     * Sets the sla calendar master persistence.
     *
     * @param slaCalendarMasterPersistence the sla calendar master persistence
     */
    public void setSlaCalendarMasterPersistence(
        SlaCalendarMasterPersistence slaCalendarMasterPersistence) {
        this.slaCalendarMasterPersistence = slaCalendarMasterPersistence;
    }

    /**
     * Returns the st acc closure details local service.
     *
     * @return the st acc closure details local service
     */
    public com.stpl.app.parttwo.service.StAccClosureDetailsLocalService getStAccClosureDetailsLocalService() {
        return stAccClosureDetailsLocalService;
    }

    /**
     * Sets the st acc closure details local service.
     *
     * @param stAccClosureDetailsLocalService the st acc closure details local service
     */
    public void setStAccClosureDetailsLocalService(
        com.stpl.app.parttwo.service.StAccClosureDetailsLocalService stAccClosureDetailsLocalService) {
        this.stAccClosureDetailsLocalService = stAccClosureDetailsLocalService;
    }

    /**
     * Returns the st acc closure details persistence.
     *
     * @return the st acc closure details persistence
     */
    public StAccClosureDetailsPersistence getStAccClosureDetailsPersistence() {
        return stAccClosureDetailsPersistence;
    }

    /**
     * Sets the st acc closure details persistence.
     *
     * @param stAccClosureDetailsPersistence the st acc closure details persistence
     */
    public void setStAccClosureDetailsPersistence(
        StAccClosureDetailsPersistence stAccClosureDetailsPersistence) {
        this.stAccClosureDetailsPersistence = stAccClosureDetailsPersistence;
    }

    /**
     * Returns the st adjustment gtn detail local service.
     *
     * @return the st adjustment gtn detail local service
     */
    public com.stpl.app.parttwo.service.StAdjustmentGtnDetailLocalService getStAdjustmentGtnDetailLocalService() {
        return stAdjustmentGtnDetailLocalService;
    }

    /**
     * Sets the st adjustment gtn detail local service.
     *
     * @param stAdjustmentGtnDetailLocalService the st adjustment gtn detail local service
     */
    public void setStAdjustmentGtnDetailLocalService(
        com.stpl.app.parttwo.service.StAdjustmentGtnDetailLocalService stAdjustmentGtnDetailLocalService) {
        this.stAdjustmentGtnDetailLocalService = stAdjustmentGtnDetailLocalService;
    }

    /**
     * Returns the st adjustment gtn detail persistence.
     *
     * @return the st adjustment gtn detail persistence
     */
    public StAdjustmentGtnDetailPersistence getStAdjustmentGtnDetailPersistence() {
        return stAdjustmentGtnDetailPersistence;
    }

    /**
     * Sets the st adjustment gtn detail persistence.
     *
     * @param stAdjustmentGtnDetailPersistence the st adjustment gtn detail persistence
     */
    public void setStAdjustmentGtnDetailPersistence(
        StAdjustmentGtnDetailPersistence stAdjustmentGtnDetailPersistence) {
        this.stAdjustmentGtnDetailPersistence = stAdjustmentGtnDetailPersistence;
    }

    /**
     * Returns the st adjustment reserve detail local service.
     *
     * @return the st adjustment reserve detail local service
     */
    public com.stpl.app.parttwo.service.StAdjustmentReserveDetailLocalService getStAdjustmentReserveDetailLocalService() {
        return stAdjustmentReserveDetailLocalService;
    }

    /**
     * Sets the st adjustment reserve detail local service.
     *
     * @param stAdjustmentReserveDetailLocalService the st adjustment reserve detail local service
     */
    public void setStAdjustmentReserveDetailLocalService(
        com.stpl.app.parttwo.service.StAdjustmentReserveDetailLocalService stAdjustmentReserveDetailLocalService) {
        this.stAdjustmentReserveDetailLocalService = stAdjustmentReserveDetailLocalService;
    }

    /**
     * Returns the st adjustment reserve detail persistence.
     *
     * @return the st adjustment reserve detail persistence
     */
    public StAdjustmentReserveDetailPersistence getStAdjustmentReserveDetailPersistence() {
        return stAdjustmentReserveDetailPersistence;
    }

    /**
     * Sets the st adjustment reserve detail persistence.
     *
     * @param stAdjustmentReserveDetailPersistence the st adjustment reserve detail persistence
     */
    public void setStAdjustmentReserveDetailPersistence(
        StAdjustmentReserveDetailPersistence stAdjustmentReserveDetailPersistence) {
        this.stAdjustmentReserveDetailPersistence = stAdjustmentReserveDetailPersistence;
    }

    /**
     * Returns the st arp outbound local service.
     *
     * @return the st arp outbound local service
     */
    public com.stpl.app.parttwo.service.StArpOutboundLocalService getStArpOutboundLocalService() {
        return stArpOutboundLocalService;
    }

    /**
     * Sets the st arp outbound local service.
     *
     * @param stArpOutboundLocalService the st arp outbound local service
     */
    public void setStArpOutboundLocalService(
        com.stpl.app.parttwo.service.StArpOutboundLocalService stArpOutboundLocalService) {
        this.stArpOutboundLocalService = stArpOutboundLocalService;
    }

    /**
     * Returns the st arp outbound persistence.
     *
     * @return the st arp outbound persistence
     */
    public StArpOutboundPersistence getStArpOutboundPersistence() {
        return stArpOutboundPersistence;
    }

    /**
     * Sets the st arp outbound persistence.
     *
     * @param stArpOutboundPersistence the st arp outbound persistence
     */
    public void setStArpOutboundPersistence(
        StArpOutboundPersistence stArpOutboundPersistence) {
        this.stArpOutboundPersistence = stArpOutboundPersistence;
    }

    /**
     * Returns the st cff outbound master local service.
     *
     * @return the st cff outbound master local service
     */
    public com.stpl.app.parttwo.service.StCffOutboundMasterLocalService getStCffOutboundMasterLocalService() {
        return stCffOutboundMasterLocalService;
    }

    /**
     * Sets the st cff outbound master local service.
     *
     * @param stCffOutboundMasterLocalService the st cff outbound master local service
     */
    public void setStCffOutboundMasterLocalService(
        com.stpl.app.parttwo.service.StCffOutboundMasterLocalService stCffOutboundMasterLocalService) {
        this.stCffOutboundMasterLocalService = stCffOutboundMasterLocalService;
    }

    /**
     * Returns the st cff outbound master persistence.
     *
     * @return the st cff outbound master persistence
     */
    public StCffOutboundMasterPersistence getStCffOutboundMasterPersistence() {
        return stCffOutboundMasterPersistence;
    }

    /**
     * Sets the st cff outbound master persistence.
     *
     * @param stCffOutboundMasterPersistence the st cff outbound master persistence
     */
    public void setStCffOutboundMasterPersistence(
        StCffOutboundMasterPersistence stCffOutboundMasterPersistence) {
        this.stCffOutboundMasterPersistence = stCffOutboundMasterPersistence;
    }

    /**
     * Returns the vw adjust demand forecast act local service.
     *
     * @return the vw adjust demand forecast act local service
     */
    public com.stpl.app.parttwo.service.VwAdjustDemandForecastActLocalService getVwAdjustDemandForecastActLocalService() {
        return vwAdjustDemandForecastActLocalService;
    }

    /**
     * Sets the vw adjust demand forecast act local service.
     *
     * @param vwAdjustDemandForecastActLocalService the vw adjust demand forecast act local service
     */
    public void setVwAdjustDemandForecastActLocalService(
        com.stpl.app.parttwo.service.VwAdjustDemandForecastActLocalService vwAdjustDemandForecastActLocalService) {
        this.vwAdjustDemandForecastActLocalService = vwAdjustDemandForecastActLocalService;
    }

    /**
     * Returns the vw adjust demand forecast act persistence.
     *
     * @return the vw adjust demand forecast act persistence
     */
    public VwAdjustDemandForecastActPersistence getVwAdjustDemandForecastActPersistence() {
        return vwAdjustDemandForecastActPersistence;
    }

    /**
     * Sets the vw adjust demand forecast act persistence.
     *
     * @param vwAdjustDemandForecastActPersistence the vw adjust demand forecast act persistence
     */
    public void setVwAdjustDemandForecastActPersistence(
        VwAdjustDemandForecastActPersistence vwAdjustDemandForecastActPersistence) {
        this.vwAdjustDemandForecastActPersistence = vwAdjustDemandForecastActPersistence;
    }

    /**
     * Returns the vw company identifier local service.
     *
     * @return the vw company identifier local service
     */
    public com.stpl.app.parttwo.service.VwCompanyIdentifierLocalService getVwCompanyIdentifierLocalService() {
        return vwCompanyIdentifierLocalService;
    }

    /**
     * Sets the vw company identifier local service.
     *
     * @param vwCompanyIdentifierLocalService the vw company identifier local service
     */
    public void setVwCompanyIdentifierLocalService(
        com.stpl.app.parttwo.service.VwCompanyIdentifierLocalService vwCompanyIdentifierLocalService) {
        this.vwCompanyIdentifierLocalService = vwCompanyIdentifierLocalService;
    }

    /**
     * Returns the vw company identifier persistence.
     *
     * @return the vw company identifier persistence
     */
    public VwCompanyIdentifierPersistence getVwCompanyIdentifierPersistence() {
        return vwCompanyIdentifierPersistence;
    }

    /**
     * Sets the vw company identifier persistence.
     *
     * @param vwCompanyIdentifierPersistence the vw company identifier persistence
     */
    public void setVwCompanyIdentifierPersistence(
        VwCompanyIdentifierPersistence vwCompanyIdentifierPersistence) {
        this.vwCompanyIdentifierPersistence = vwCompanyIdentifierPersistence;
    }

    /**
     * Returns the vw company master local service.
     *
     * @return the vw company master local service
     */
    public com.stpl.app.parttwo.service.VwCompanyMasterLocalService getVwCompanyMasterLocalService() {
        return vwCompanyMasterLocalService;
    }

    /**
     * Sets the vw company master local service.
     *
     * @param vwCompanyMasterLocalService the vw company master local service
     */
    public void setVwCompanyMasterLocalService(
        com.stpl.app.parttwo.service.VwCompanyMasterLocalService vwCompanyMasterLocalService) {
        this.vwCompanyMasterLocalService = vwCompanyMasterLocalService;
    }

    /**
     * Returns the vw company master persistence.
     *
     * @return the vw company master persistence
     */
    public VwCompanyMasterPersistence getVwCompanyMasterPersistence() {
        return vwCompanyMasterPersistence;
    }

    /**
     * Sets the vw company master persistence.
     *
     * @param vwCompanyMasterPersistence the vw company master persistence
     */
    public void setVwCompanyMasterPersistence(
        VwCompanyMasterPersistence vwCompanyMasterPersistence) {
        this.vwCompanyMasterPersistence = vwCompanyMasterPersistence;
    }

    /**
     * Returns the vw company parent details local service.
     *
     * @return the vw company parent details local service
     */
    public com.stpl.app.parttwo.service.VwCompanyParentDetailsLocalService getVwCompanyParentDetailsLocalService() {
        return vwCompanyParentDetailsLocalService;
    }

    /**
     * Sets the vw company parent details local service.
     *
     * @param vwCompanyParentDetailsLocalService the vw company parent details local service
     */
    public void setVwCompanyParentDetailsLocalService(
        com.stpl.app.parttwo.service.VwCompanyParentDetailsLocalService vwCompanyParentDetailsLocalService) {
        this.vwCompanyParentDetailsLocalService = vwCompanyParentDetailsLocalService;
    }

    /**
     * Returns the vw company parent details persistence.
     *
     * @return the vw company parent details persistence
     */
    public VwCompanyParentDetailsPersistence getVwCompanyParentDetailsPersistence() {
        return vwCompanyParentDetailsPersistence;
    }

    /**
     * Sets the vw company parent details persistence.
     *
     * @param vwCompanyParentDetailsPersistence the vw company parent details persistence
     */
    public void setVwCompanyParentDetailsPersistence(
        VwCompanyParentDetailsPersistence vwCompanyParentDetailsPersistence) {
        this.vwCompanyParentDetailsPersistence = vwCompanyParentDetailsPersistence;
    }

    /**
     * Returns the vw company trade class local service.
     *
     * @return the vw company trade class local service
     */
    public com.stpl.app.parttwo.service.VwCompanyTradeClassLocalService getVwCompanyTradeClassLocalService() {
        return vwCompanyTradeClassLocalService;
    }

    /**
     * Sets the vw company trade class local service.
     *
     * @param vwCompanyTradeClassLocalService the vw company trade class local service
     */
    public void setVwCompanyTradeClassLocalService(
        com.stpl.app.parttwo.service.VwCompanyTradeClassLocalService vwCompanyTradeClassLocalService) {
        this.vwCompanyTradeClassLocalService = vwCompanyTradeClassLocalService;
    }

    /**
     * Returns the vw company trade class persistence.
     *
     * @return the vw company trade class persistence
     */
    public VwCompanyTradeClassPersistence getVwCompanyTradeClassPersistence() {
        return vwCompanyTradeClassPersistence;
    }

    /**
     * Sets the vw company trade class persistence.
     *
     * @param vwCompanyTradeClassPersistence the vw company trade class persistence
     */
    public void setVwCompanyTradeClassPersistence(
        VwCompanyTradeClassPersistence vwCompanyTradeClassPersistence) {
        this.vwCompanyTradeClassPersistence = vwCompanyTradeClassPersistence;
    }

    /**
     * Returns the vw customer gts forecast local service.
     *
     * @return the vw customer gts forecast local service
     */
    public com.stpl.app.parttwo.service.VwCustomerGtsForecastLocalService getVwCustomerGtsForecastLocalService() {
        return vwCustomerGtsForecastLocalService;
    }

    /**
     * Sets the vw customer gts forecast local service.
     *
     * @param vwCustomerGtsForecastLocalService the vw customer gts forecast local service
     */
    public void setVwCustomerGtsForecastLocalService(
        com.stpl.app.parttwo.service.VwCustomerGtsForecastLocalService vwCustomerGtsForecastLocalService) {
        this.vwCustomerGtsForecastLocalService = vwCustomerGtsForecastLocalService;
    }

    /**
     * Returns the vw customer gts forecast persistence.
     *
     * @return the vw customer gts forecast persistence
     */
    public VwCustomerGtsForecastPersistence getVwCustomerGtsForecastPersistence() {
        return vwCustomerGtsForecastPersistence;
    }

    /**
     * Sets the vw customer gts forecast persistence.
     *
     * @param vwCustomerGtsForecastPersistence the vw customer gts forecast persistence
     */
    public void setVwCustomerGtsForecastPersistence(
        VwCustomerGtsForecastPersistence vwCustomerGtsForecastPersistence) {
        this.vwCustomerGtsForecastPersistence = vwCustomerGtsForecastPersistence;
    }

    /**
     * Returns the vw item identifier local service.
     *
     * @return the vw item identifier local service
     */
    public com.stpl.app.parttwo.service.VwItemIdentifierLocalService getVwItemIdentifierLocalService() {
        return vwItemIdentifierLocalService;
    }

    /**
     * Sets the vw item identifier local service.
     *
     * @param vwItemIdentifierLocalService the vw item identifier local service
     */
    public void setVwItemIdentifierLocalService(
        com.stpl.app.parttwo.service.VwItemIdentifierLocalService vwItemIdentifierLocalService) {
        this.vwItemIdentifierLocalService = vwItemIdentifierLocalService;
    }

    /**
     * Returns the vw item identifier persistence.
     *
     * @return the vw item identifier persistence
     */
    public VwItemIdentifierPersistence getVwItemIdentifierPersistence() {
        return vwItemIdentifierPersistence;
    }

    /**
     * Sets the vw item identifier persistence.
     *
     * @param vwItemIdentifierPersistence the vw item identifier persistence
     */
    public void setVwItemIdentifierPersistence(
        VwItemIdentifierPersistence vwItemIdentifierPersistence) {
        this.vwItemIdentifierPersistence = vwItemIdentifierPersistence;
    }

    /**
     * Returns the vw item master local service.
     *
     * @return the vw item master local service
     */
    public com.stpl.app.parttwo.service.VwItemMasterLocalService getVwItemMasterLocalService() {
        return vwItemMasterLocalService;
    }

    /**
     * Sets the vw item master local service.
     *
     * @param vwItemMasterLocalService the vw item master local service
     */
    public void setVwItemMasterLocalService(
        com.stpl.app.parttwo.service.VwItemMasterLocalService vwItemMasterLocalService) {
        this.vwItemMasterLocalService = vwItemMasterLocalService;
    }

    /**
     * Returns the vw item master persistence.
     *
     * @return the vw item master persistence
     */
    public VwItemMasterPersistence getVwItemMasterPersistence() {
        return vwItemMasterPersistence;
    }

    /**
     * Sets the vw item master persistence.
     *
     * @param vwItemMasterPersistence the vw item master persistence
     */
    public void setVwItemMasterPersistence(
        VwItemMasterPersistence vwItemMasterPersistence) {
        this.vwItemMasterPersistence = vwItemMasterPersistence;
    }

    /**
     * Returns the vw item pricing local service.
     *
     * @return the vw item pricing local service
     */
    public com.stpl.app.parttwo.service.VwItemPricingLocalService getVwItemPricingLocalService() {
        return vwItemPricingLocalService;
    }

    /**
     * Sets the vw item pricing local service.
     *
     * @param vwItemPricingLocalService the vw item pricing local service
     */
    public void setVwItemPricingLocalService(
        com.stpl.app.parttwo.service.VwItemPricingLocalService vwItemPricingLocalService) {
        this.vwItemPricingLocalService = vwItemPricingLocalService;
    }

    /**
     * Returns the vw item pricing persistence.
     *
     * @return the vw item pricing persistence
     */
    public VwItemPricingPersistence getVwItemPricingPersistence() {
        return vwItemPricingPersistence;
    }

    /**
     * Sets the vw item pricing persistence.
     *
     * @param vwItemPricingPersistence the vw item pricing persistence
     */
    public void setVwItemPricingPersistence(
        VwItemPricingPersistence vwItemPricingPersistence) {
        this.vwItemPricingPersistence = vwItemPricingPersistence;
    }

    /**
     * Returns the vw ivld adj demand fore actual local service.
     *
     * @return the vw ivld adj demand fore actual local service
     */
    public com.stpl.app.parttwo.service.VwIvldAdjDemandForeActualLocalService getVwIvldAdjDemandForeActualLocalService() {
        return vwIvldAdjDemandForeActualLocalService;
    }

    /**
     * Sets the vw ivld adj demand fore actual local service.
     *
     * @param vwIvldAdjDemandForeActualLocalService the vw ivld adj demand fore actual local service
     */
    public void setVwIvldAdjDemandForeActualLocalService(
        com.stpl.app.parttwo.service.VwIvldAdjDemandForeActualLocalService vwIvldAdjDemandForeActualLocalService) {
        this.vwIvldAdjDemandForeActualLocalService = vwIvldAdjDemandForeActualLocalService;
    }

    /**
     * Returns the vw ivld adj demand fore actual persistence.
     *
     * @return the vw ivld adj demand fore actual persistence
     */
    public VwIvldAdjDemandForeActualPersistence getVwIvldAdjDemandForeActualPersistence() {
        return vwIvldAdjDemandForeActualPersistence;
    }

    /**
     * Sets the vw ivld adj demand fore actual persistence.
     *
     * @param vwIvldAdjDemandForeActualPersistence the vw ivld adj demand fore actual persistence
     */
    public void setVwIvldAdjDemandForeActualPersistence(
        VwIvldAdjDemandForeActualPersistence vwIvldAdjDemandForeActualPersistence) {
        this.vwIvldAdjDemandForeActualPersistence = vwIvldAdjDemandForeActualPersistence;
    }

    /**
     * Returns the vw ivld return reserve local service.
     *
     * @return the vw ivld return reserve local service
     */
    public com.stpl.app.parttwo.service.VwIvldReturnReserveLocalService getVwIvldReturnReserveLocalService() {
        return vwIvldReturnReserveLocalService;
    }

    /**
     * Sets the vw ivld return reserve local service.
     *
     * @param vwIvldReturnReserveLocalService the vw ivld return reserve local service
     */
    public void setVwIvldReturnReserveLocalService(
        com.stpl.app.parttwo.service.VwIvldReturnReserveLocalService vwIvldReturnReserveLocalService) {
        this.vwIvldReturnReserveLocalService = vwIvldReturnReserveLocalService;
    }

    /**
     * Returns the vw ivld return reserve persistence.
     *
     * @return the vw ivld return reserve persistence
     */
    public VwIvldReturnReservePersistence getVwIvldReturnReservePersistence() {
        return vwIvldReturnReservePersistence;
    }

    /**
     * Sets the vw ivld return reserve persistence.
     *
     * @param vwIvldReturnReservePersistence the vw ivld return reserve persistence
     */
    public void setVwIvldReturnReservePersistence(
        VwIvldReturnReservePersistence vwIvldReturnReservePersistence) {
        this.vwIvldReturnReservePersistence = vwIvldReturnReservePersistence;
    }

    /**
     * Returns the vw return reserve local service.
     *
     * @return the vw return reserve local service
     */
    public com.stpl.app.parttwo.service.VwReturnReserveLocalService getVwReturnReserveLocalService() {
        return vwReturnReserveLocalService;
    }

    /**
     * Sets the vw return reserve local service.
     *
     * @param vwReturnReserveLocalService the vw return reserve local service
     */
    public void setVwReturnReserveLocalService(
        com.stpl.app.parttwo.service.VwReturnReserveLocalService vwReturnReserveLocalService) {
        this.vwReturnReserveLocalService = vwReturnReserveLocalService;
    }

    /**
     * Returns the vw return reserve persistence.
     *
     * @return the vw return reserve persistence
     */
    public VwReturnReservePersistence getVwReturnReservePersistence() {
        return vwReturnReservePersistence;
    }

    /**
     * Sets the vw return reserve persistence.
     *
     * @param vwReturnReservePersistence the vw return reserve persistence
     */
    public void setVwReturnReservePersistence(
        VwReturnReservePersistence vwReturnReservePersistence) {
        this.vwReturnReservePersistence = vwReturnReservePersistence;
    }

    /**
     * Returns the counter local service.
     *
     * @return the counter local service
     */
    public com.stpl.counter.service.CounterLocalService getCounterLocalService() {
        return counterLocalService;
    }

    /**
     * Sets the counter local service.
     *
     * @param counterLocalService the counter local service
     */
    public void setCounterLocalService(
        com.stpl.counter.service.CounterLocalService counterLocalService) {
        this.counterLocalService = counterLocalService;
    }

    /**
     * Returns the resource local service.
     *
     * @return the resource local service
     */
    public com.stpl.portal.service.ResourceLocalService getResourceLocalService() {
        return resourceLocalService;
    }

    /**
     * Sets the resource local service.
     *
     * @param resourceLocalService the resource local service
     */
    public void setResourceLocalService(
        com.stpl.portal.service.ResourceLocalService resourceLocalService) {
        this.resourceLocalService = resourceLocalService;
    }

    /**
     * Returns the user local service.
     *
     * @return the user local service
     */
    public com.stpl.portal.service.UserLocalService getUserLocalService() {
        return userLocalService;
    }

    /**
     * Sets the user local service.
     *
     * @param userLocalService the user local service
     */
    public void setUserLocalService(
        com.stpl.portal.service.UserLocalService userLocalService) {
        this.userLocalService = userLocalService;
    }

    /**
     * Returns the user remote service.
     *
     * @return the user remote service
     */
    public com.stpl.portal.service.UserService getUserService() {
        return userService;
    }

    /**
     * Sets the user remote service.
     *
     * @param userService the user remote service
     */
    public void setUserService(com.stpl.portal.service.UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns the user persistence.
     *
     * @return the user persistence
     */
    public UserPersistence getUserPersistence() {
        return userPersistence;
    }

    /**
     * Sets the user persistence.
     *
     * @param userPersistence the user persistence
     */
    public void setUserPersistence(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    public void afterPropertiesSet() {
        Class<?> clazz = getClass();

        _classLoader = clazz.getClassLoader();

        PersistedModelLocalServiceRegistryUtil.register("com.stpl.app.parttwo.model.CffViewMaster",
            cffViewMasterLocalService);
    }

    public void destroy() {
        PersistedModelLocalServiceRegistryUtil.unregister(
            "com.stpl.app.parttwo.model.CffViewMaster");
    }

    /**
     * Returns the Spring bean ID for this bean.
     *
     * @return the Spring bean ID for this bean
     */
    @Override
    public String getBeanIdentifier() {
        return _beanIdentifier;
    }

    /**
     * Sets the Spring bean ID for this bean.
     *
     * @param beanIdentifier the Spring bean ID for this bean
     */
    @Override
    public void setBeanIdentifier(String beanIdentifier) {
        _beanIdentifier = beanIdentifier;
    }

    @Override
    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        if (contextClassLoader != _classLoader) {
            currentThread.setContextClassLoader(_classLoader);
        }

        try {
            return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
        } finally {
            if (contextClassLoader != _classLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    protected Class<?> getModelClass() {
        return CffViewMaster.class;
    }

    protected String getModelClassName() {
        return CffViewMaster.class.getName();
    }

    /**
     * Performs an SQL query.
     *
     * @param sql the sql query
     */
    protected void runSQL(String sql) throws SystemException {
        try {
            DataSource dataSource = cffViewMasterPersistence.getDataSource();

            SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
                    sql, new int[0]);

            sqlUpdate.update();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
