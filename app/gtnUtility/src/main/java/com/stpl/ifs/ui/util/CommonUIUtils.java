/**
 * 
 */
package com.stpl.ifs.ui.util;

import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.HeaderUtils;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;

/**
 * @author manikanta
 * 
 */
public class CommonUIUtils {
    private static final Logger LOGGER = Logger.getLogger(CommonUIUtils.class);
	static{
		
	}

	public final Object[] rebatePlanColumns = new Object[] {
			"companyFamilyplanSystemId", "selfGrowthIndicator",
			"rebateStructure", "marketShareFrom", HeaderUtils.REBATE_SCHEDULE_ID,
			"secondaryRebatePlanNo", "rebateRangeBasedOn",
			"itemFamilyplanSystemId", "rebateRule", "rebateBasedOn",
			"rebatePlanType", "rebatePlanId" };
	
	public final String[] rebatePlanHeader = new String[] {
			"companyFamilyplanSystemId", "selfGrowthIndicator",
			"rebateStructure", "marketShareFrom", HeaderUtils.REBATE_SCHEDULE_ID,
			"secondaryRebatePlanNo", "rebateRangeBasedOn",
			"itemFamilyplanSystemId", "rebateRule", "rebateBasedOn",
			"rebatePlanType", "rebatePlanId" };
        
	public final Object[] rebateTierColumns = new Object[] {
			"tierFrom", "tierTo", "tierValue", "tierOperator" };
        
	public final String[] rebateTierHeader = new String[] {
			"tierFrom", "tierTo", "tierValue", "tierOperator" };
        
	public final Object[] rebateScheduleColumns = new Object[] {
			HeaderUtils.REBATE_SCHEDULE_ID, "rebateScheduleSystemId", "rebateScheduleName",
			"rebateScheduleNo", "rebateScheduleStatus", "rebateScheduleType",
			"rebateProgramType" };
        
	public final String[] rebateScheduleHeader = new String[] {
			"rebate Schedule Id", "rebate Schedule SystemId", "rebate Schedule Name",
			"rebate Schedule No", "rebate Schedule Status", "rebate Schedule Type",
			"rebate Program Type" };
        
	public final Object[] ifpColumnsInRs = new Object[] {
			"itemFamilyplanNo", "itemFamilyplanName", "itemFamilyplanType" };
        
	public final String[] ifpHeaderInRs = new String[] {
			"Item Familyplan No", "Item Familyplan Name",
			"Item Familyplan Type" };
        
	public final Object[] itemDetailsColumnsInRs = new Object[] {
			HeaderUtils.CHECK_BOX, "recordType",HeaderUtils.ITEM_NO, HeaderUtils.ITEM_NAME, HeaderUtils.ITEM_TYPE,"formulaId","formulaName", "rebatePlanName",
			 "rebateStartDate", "rebateEndDate", "rebateRevisionDate" };
        
	public final String[] itemDetailsHeaderInRs = new String[] {
			HeaderUtils.CHECK_BOX, "item No", "item Name", "item Type","formula Id","formula Name", "rebate Plan Name",
			 "start Date", "End Date", "revision Date" };
        
        public final Object[] itemDetailsColumnsInRsWithBundle = new Object[] {
			HeaderUtils.CHECK_BOX, HeaderUtils.ITEM_NO, HeaderUtils.ITEM_NAME, HeaderUtils.ITEM_TYPE,"formulaId","formulaName", "bundleNo","rebatePlanName",
			 "rebateStartDate", "rebateEndDate", "rebateRevisionDate" };
        
	public final String[] itemDetailsHeaderInRsWithBundle = new String[] {
			HeaderUtils.CHECK_BOX, "item No", "item Name", "item Type","formula Id","formula Name", "Bundle No","rebate Plan Name",
			 "start Date", "end Date", "revision Date" };

        
        public final Object[] itemGroupResultsColumns = new Object[] {
			 "itemGroupName","itemGroupDesc", "itemGroupNo", "company","createdDate","modifiedDate","createdBy" };

	public final String[] itemGroupResultsHeader = new String[] {
			"Item Group Name","Item Group Description", "Item Group #", "Company","Creation Date","Modified Date","Created By" };        
        
        public final Object[] itemResultsColumns = new Object[] {
			 "itemId",HeaderUtils.ITEM_NO,"itemCode",HeaderUtils.ITEM_NAME,"itemDesc","itemStartDate","itemEndDate","itemStatus",
                         "therapeuticClass","brand","form","strength","packageSizeCode","packageSizeIntroDate","upps",
                         "manufacturerId","manufacturerNo","manufacturerName","labelerCode","organizationKey",
                         "acquisitionDate","authorizedGeneric","authorizedGenericStartDate","authorizedGenericEndDate",
                         "firstSaleDate","itemTypeIndicator","itemClass",HeaderUtils.ITEM_TYPE,"marketTerminationDate","newFormulationIndicator",
                         "newFormulation","newFormulationStartDate","newFormulationEndDate","pediatricExclusiveIndicator",
                         "pediatricExclusiveStartDate","pediatricExclusiveEndDate","clottingFactorIndicator","clottingFactorStartDate",
                         "clottingFactorEndDate","primaryUom","secondaryUom","shelfLife","shelfLifeType","dualPricingIndicator",
                         "itemFamilyId","udc1","udc2","udc3","udc4","udc5","udc6","acquiredAmp","acquiredBamp","obraBamp","dra",
                         "dosesPerUnit","discontinuationDate","lastLotExpirationDate","ndc9","ndc8","displayBrand","innovatorCode",
                         "baselineAmp","baseYearCpi" };

	public final String[] itemResultsHeader = new String[] {
			"Item ID","Item #","Item Code","Item Name","Item Desc","Item Start Date","Item End Date",
                        "Item Status","Therapeutic Class","Brand","Form","Strength","Package Size Code",
                        "Package Size Intro Date","UPPS","Manufacturer ID","Manufacturer No","Manufacturer Name",
                        "Labeler Code","Organization Key","Acquisition Date","Authorized Generic","Authorized Generic Start Date",
                        "Authorized Generic End Date","First Sale Date","Item Type Indicator","Item Class ","Item Type ",
                        "Market Termination Date","New Formulation Indicator","New Formulation","New Formulation Start Date",
                        "New Formulation End Date","Pediatric Exclusive Indicator","Pediatric Exclusive Start Date",
                        "Pediatric Exclusive End Date","Clotting Factor Indicator","Clotting Factor Start Date",
                        "Clotting Factor End Date","Primary UOM","Secondary UOM","Shelf Life","Shelf Life Type",
                        "Dual Pricing Indicator","Item Family ID","UDC 1","UDC 2","UDC 3","UDC 4","UDC 5","UDC 6","Acquired AMP",
                        "Acquired BAMP","OBRA BAMP","DRA","Doses per Unit","Discontinuation Date","Last Lot Expiration Date",
                        "NDC9","NDC8","Display Brand","Innovator Code","Baseline AMP","Base Year CPI" };        
        
        public final Object[] customerGroupResultsColumns = new Object[] {
			 "customerGroupName","customerGroupDesc", "customerGroupNo","createdDate","modifiedDate","createdBy" };

	public final String[] customerGroupResultsHeader = new String[] {
			"Customer Group Name","Customer Group Description", "Customer Group #","Creation Date","Modified Date","Created By"  };        
        
        public final Object[] customerResultsColumns = new Object[] {
			 "organizationKey","customerId","customerNo","customerName","tradeClass","tradeClassStartDate",
                         "tradeClassEndDate","customerType","customerStatus","lives","customerEndDate","udc1","udc2",
                         "udc3","udc4","udc5","udc6","customerGroup","financialSystem","address1","address2","city",
                         "state","zipCode","country","regionCode","parentCustomerNo","parentStartDate","parentEndDate",
                         "customerStartDate","priorParentStartDate","priorParentCustomerNo" };

	public final String[] customerResultsHeader = new String[] {
			"Organization Key ","Customer ID","Customer No ","Customer Name ","Trade Class ",
                        "Trade Class Start Date ","Trade Class End Date","Customer Type","Customer Status",
                        "Lives","Customer End Date","UDC1","UDC2","UDC3","UDC4","UDC5","UDC6","Customer Group",
                        "Financial System","Address 1","Address 2","City","State","Zip Code","Country","Region Code",
                        "Parent Customer No","Parent Start Date","Parent End Date","Customer Start Date",
                        "Prior Parent Start Date","Prior Parent Customer No" };        
        
	public static void successNotification(String message) {
		Notification notif = new Notification(message,
				Notification.Type.HUMANIZED_MESSAGE);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.setStyleName("mystyle");
		notif.show(Page.getCurrent());
	}
        
        public static void getMessageNotification(String message) {
        Notification notif = new Notification(message,
        Notification.Type.HUMANIZED_MESSAGE);
        notif.setPosition(Position.MIDDLE_CENTER);
        notif.setDelayMsec(NumericConstants.THREE_THOUSAND);
        notif.show(Page.getCurrent());

    }
        public void removeComponentOnPermission(List<Object> resultList, CssLayout cssLayout,Map<String, AppPermission> fieldIfpHM, String mode, CustomFieldGroup binder){
        int listSize = resultList.size();
        for (int i = 0; i < listSize; i++) {
            Object[] obj = (Object[]) resultList.get(i);
            getPermissionAndRemoveComponent(cssLayout, String.valueOf(obj[0]), String.valueOf(obj[1]), fieldIfpHM, binder, mode);
            
        }
    }
        
         private void getPermissionAndRemoveComponent(CssLayout cssLayout, String labelStr, String fieldStr, Map<String, AppPermission> fieldHM,
             CustomFieldGroup binder, String mode) {
        boolean appPermission = true;
        try {
            if (((fieldStr != null) && (appPermission == false)) && (labelStr != null)) {
                        for (java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
                            if ((labelStr.equals(String.valueOf(field))) && (field.get(labelStr) instanceof Label)) {
                                    cssLayout.removeComponent((Label) field.get(labelStr));
                            }
                        }
                    cssLayout.removeComponent(binder.getField(fieldStr));
            }
        } catch (Exception e) {
            LOGGER.error(e);
           
        }
    }
    public static String getDBinput(String identifier) {
            return identifier.replace(DataFormatConverter.INDICATOR_PERCENT, "[%]").replace("*", DataFormatConverter.INDICATOR_PERCENT).replace("_", "[_]");
        }
    public static void getSelectErrorMessage(){
        final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Select error", "Please select a record.", new MessageBoxListener() {

            /**
             * The method is triggered when a button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                return;
            }
        }, ButtonId.OK);
        msg.getButton(ButtonId.OK).focus();
    }
}
