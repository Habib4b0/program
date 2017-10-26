package com.stpl.app.parttwo.service.messaging;

import com.stpl.app.parttwo.service.AcBaseRateBaselineDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.AcBrMethodologyDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.AcFdAdjustmentSelectionLocalServiceUtil;
import com.stpl.app.parttwo.service.AccClosureDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.AccClosureViewMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.AdjustedDemandForecastLocalServiceUtil;
import com.stpl.app.parttwo.service.AhTempDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.ArpOutboundLocalServiceUtil;
import com.stpl.app.parttwo.service.CffAdditionalInfoLocalServiceUtil;
import com.stpl.app.parttwo.service.CffApprovalDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffCustHierarchyLocalServiceUtil;
import com.stpl.app.parttwo.service.CffCustomViewDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffCustomViewMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.CffDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffDocDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.CffProdHierarchyLocalServiceUtil;
import com.stpl.app.parttwo.service.CffViewMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.CustomerGtsActualLocalServiceUtil;
import com.stpl.app.parttwo.service.CustomerGtsForecastLocalServiceUtil;
import com.stpl.app.parttwo.service.IvldAccrualInboundLocalServiceUtil;
import com.stpl.app.parttwo.service.IvldCompanyIdentifierLocalServiceUtil;
import com.stpl.app.parttwo.service.IvldCompanyMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.IvldCompanyParentLocalServiceUtil;
import com.stpl.app.parttwo.service.IvldCompanyTradeClassLocalServiceUtil;
import com.stpl.app.parttwo.service.IvldCustomerGtsActualLocalServiceUtil;
import com.stpl.app.parttwo.service.IvldCustomerGtsForecastLocalServiceUtil;
import com.stpl.app.parttwo.service.IvldItemIdentifierLocalServiceUtil;
import com.stpl.app.parttwo.service.IvldItemMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.IvldItemPricingLocalServiceUtil;
import com.stpl.app.parttwo.service.SlaCalendarDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.SlaCalendarMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.StAccClosureDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.StAdjustmentGtnDetailLocalServiceUtil;
import com.stpl.app.parttwo.service.StAdjustmentReserveDetailLocalServiceUtil;
import com.stpl.app.parttwo.service.StArpOutboundLocalServiceUtil;
import com.stpl.app.parttwo.service.StCffOutboundMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.VwAdjustDemandForecastActLocalServiceUtil;
import com.stpl.app.parttwo.service.VwCompanyIdentifierLocalServiceUtil;
import com.stpl.app.parttwo.service.VwCompanyMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.VwCompanyParentDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.VwCompanyTradeClassLocalServiceUtil;
import com.stpl.app.parttwo.service.VwCustomerGtsForecastLocalServiceUtil;
import com.stpl.app.parttwo.service.VwItemIdentifierLocalServiceUtil;
import com.stpl.app.parttwo.service.VwItemMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.VwItemPricingLocalServiceUtil;
import com.stpl.app.parttwo.service.VwIvldAdjDemandForeActualLocalServiceUtil;
import com.stpl.app.parttwo.service.VwIvldReturnReserveLocalServiceUtil;
import com.stpl.app.parttwo.service.VwReturnReserveLocalServiceUtil;

import com.stpl.portal.kernel.messaging.BaseMessageListener;
import com.stpl.portal.kernel.messaging.Message;


public class ClpMessageListener extends BaseMessageListener {
    public static String getServletContextName() {
        return ClpSerializer.getServletContextName();
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        String command = message.getString("command");
        String servletContextName = message.getString("servletContextName");

        if (command.equals("undeploy") &&
                servletContextName.equals(getServletContextName())) {
            AcBaseRateBaselineDetailsLocalServiceUtil.clearService();

            AcBrMethodologyDetailsLocalServiceUtil.clearService();

            AccClosureDetailsLocalServiceUtil.clearService();

            AccClosureMasterLocalServiceUtil.clearService();

            AccClosureViewMasterLocalServiceUtil.clearService();

            AcFdAdjustmentSelectionLocalServiceUtil.clearService();

            AdjustedDemandForecastLocalServiceUtil.clearService();

            AhTempDetailsLocalServiceUtil.clearService();

            ArpOutboundLocalServiceUtil.clearService();

            CffAdditionalInfoLocalServiceUtil.clearService();

            CffApprovalDetailsLocalServiceUtil.clearService();

            CffCustHierarchyLocalServiceUtil.clearService();

            CffCustomViewDetailsLocalServiceUtil.clearService();

            CffCustomViewMasterLocalServiceUtil.clearService();

            CffDetailsLocalServiceUtil.clearService();

            CffDocDetailsLocalServiceUtil.clearService();

            CffMasterLocalServiceUtil.clearService();

            CffProdHierarchyLocalServiceUtil.clearService();

            CffViewMasterLocalServiceUtil.clearService();

            CustomerGtsActualLocalServiceUtil.clearService();

            CustomerGtsForecastLocalServiceUtil.clearService();

            IvldAccrualInboundLocalServiceUtil.clearService();

            IvldCompanyIdentifierLocalServiceUtil.clearService();

            IvldCompanyMasterLocalServiceUtil.clearService();

            IvldCompanyParentLocalServiceUtil.clearService();

            IvldCompanyTradeClassLocalServiceUtil.clearService();

            IvldCustomerGtsActualLocalServiceUtil.clearService();

            IvldCustomerGtsForecastLocalServiceUtil.clearService();

            IvldItemIdentifierLocalServiceUtil.clearService();

            IvldItemMasterLocalServiceUtil.clearService();

            IvldItemPricingLocalServiceUtil.clearService();

            SlaCalendarDetailsLocalServiceUtil.clearService();

            SlaCalendarMasterLocalServiceUtil.clearService();

            StAccClosureDetailsLocalServiceUtil.clearService();

            StAdjustmentGtnDetailLocalServiceUtil.clearService();

            StAdjustmentReserveDetailLocalServiceUtil.clearService();

            StArpOutboundLocalServiceUtil.clearService();

            StCffOutboundMasterLocalServiceUtil.clearService();

            VwAdjustDemandForecastActLocalServiceUtil.clearService();

            VwCompanyIdentifierLocalServiceUtil.clearService();

            VwCompanyMasterLocalServiceUtil.clearService();

            VwCompanyParentDetailsLocalServiceUtil.clearService();

            VwCompanyTradeClassLocalServiceUtil.clearService();

            VwCustomerGtsForecastLocalServiceUtil.clearService();

            VwItemIdentifierLocalServiceUtil.clearService();

            VwItemMasterLocalServiceUtil.clearService();

            VwItemPricingLocalServiceUtil.clearService();

            VwIvldAdjDemandForeActualLocalServiceUtil.clearService();

            VwIvldReturnReserveLocalServiceUtil.clearService();

            VwReturnReserveLocalServiceUtil.clearService();
        }
    }
}
