/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.ui.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.app.model.AccrualMaster;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.transactional.common.abstractForm.AbstractSearch;
import com.stpl.app.transactional.common.dto.ARPOutboundDTO;
import com.stpl.app.transactional.common.dto.AccrualdetailsDTO;
import com.stpl.app.transactional.common.dto.ArmOutboundDTO;
import com.stpl.app.transactional.common.dto.CFFOutBoundDTO;
import com.stpl.app.transactional.common.dto.DetailsDTO;
import com.stpl.app.transactional.common.dto.FilterGenerator;
import com.stpl.app.transactional.common.logic.CommonLogic;
import com.stpl.app.transactional.common.logic.SearchLogic;
import com.stpl.app.transactional.common.ui.view.DisplayView;
import com.stpl.app.transactional.common.util.SearchCriteria;
import com.stpl.app.util.AbstractNotificationUtils;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.util.HelperListUtil;
import com.stpl.app.util.TableResultCustom;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTable.ColumnCheckListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Reindeer;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 *
 * @author sooriya.lakshmanan
 */
public class SearchForm extends AbstractSearch {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SearchForm.class);

    List<DetailsDTO> moduleDetails;

    CommonLogic commonLogic = new CommonLogic();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    Map<Integer, Boolean> SelectedAccrualsId = new LinkedHashMap<>();
    SearchLogic searchLogic = new SearchLogic();

    final String USERID = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
    final String SESSIONID = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));

	boolean tableCheckAllValue = false;

    public SearchForm(final List<DetailsDTO> moduleDetails, final List<DetailsDTO> primaryValueList) {
        super(moduleDetails, primaryValueList);
        this.moduleDetails = moduleDetails;
    }

    /**
     * Item Click Listner
     *
     * @param event
     */
    @Override
    protected void itemClickListener(ItemClickEvent event) {
        try {
            BeanItem searchForecastDTO = (BeanItem) table.getContainerDataSource().getItem(event.getItemId());
            Object id = new Object();
            if (ConstantUtil.ADJUSTED_DEMAND_VIEW.equals(tableName)) {
                primaryDto.setPropertyName("adjustedDemandForecastId");
            } else if (ConstantUtil.DEMANDVIEW_TABLE.equals(tableName)) {
                primaryDto.setPropertyName("demandForecastActualSid");
            }
            if (searchValues != null && searchValues.get(ConstantUtil.ACCRUAL_TYPE) != null
                    && !searchValues.get(ConstantUtil.ACCRUAL_TYPE).equals("Other")
                    && !tableName.equals(ConstantUtil.INVALID_ACCURAL_INBOUND)) {
                primaryDto.setPropertyName("accrualDetailsSid");
                VaadinSession.getCurrent().setAttribute(ConstantUtil.ACCRUAL_DETAILS, "true");
            }

            if (searchForecastDTO.getItemProperty(primaryDto.getPropertyName()) != null) {
                id = searchForecastDTO.getItemProperty(primaryDto.getPropertyName()).getValue();
                removeId = String.valueOf(searchForecastDTO.getItemProperty(primaryDto.getPropertyName()).getValue());
            }
            VaadinSession.getCurrent().setAttribute(ConstantUtil.VIEW_SID, removeId);
            VaadinSession.getCurrent().setAttribute(ConstantUtil.VIEW_SID_NAME, viewNameSid);
            if (id != null) {
                String id1 = "";
                id1 = id.toString();
                final StplSecurity stplSecurity = new StplSecurity();
                final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
                final Map<String, AppPermission> functionForecastSalesHM = stplSecurity.getBusinessFunctionPermission(userId, primaryDto.getValidation());
                if (functionForecastSalesHM.get(ConstantUtil.FUNCTIONAL_VIEW) != null && functionForecastSalesHM.get(ConstantUtil.FUNCTIONAL_VIEW).isFunctionFlag()) {
                    if (tableName.equalsIgnoreCase("Invalidrecordcount")) {
                        VaadinSession.getCurrent().setAttribute(ConstantUtil.VIEW_NAME, Integer.valueOf(id1));
                    } else {
                        VaadinSession.getCurrent().setAttribute("ForecastMasterId", Integer.valueOf(id1));
                        getUI().getNavigator().navigateTo(DisplayView.NAME);
                    }

                } else {
                    table.setSelectable(false);
                    CommonUIUtils.successNotification("You dont have permission to view this selected record");
                }
            }
            LOGGER.debug("Ends itemClick");
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    protected void resetButtonLogic() throws PortalException,ClassNotFoundException{
        LOGGER.debug("Entering btnResetLogic");
        List<Object> collapsedColumns = new ArrayList<>();
        for (Object item : table.getVisibleColumns()) {
            if (table.isColumnCollapsed(item)) {
                collapsedColumns.add(item);
            }
        }
        Object[] visibleCol = table.getVisibleColumns();
        String[] colHeaders = table.getColumnHeaders();
        if (!tableName.equalsIgnoreCase("InvalidRecordCount")) {

            if (tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST) || tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW) || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) || tableName.equals(ConstantUtil.IVLD_ITEM_MASTER) || tableName.equals(ConstantUtil.IVLD_ITEM_IDENTIFIER) || tableName.equals(ConstantUtil.IVLD_ITEM_PRICING) || ConstantUtil.ST_CFF_OUTBOUND.equals(tableName) || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || ConstantUtil.RETURN_RESERVE.equals(tableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)) {
                tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName)));
            } else if (tableName.equals(ConstantUtil.ARP_OUTBOUND)) {
                tableLogic.setContainerDataSource(new BeanItemContainer(ARPOutboundDTO.class));
            } else if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                tableLogic.setContainerDataSource(new BeanItemContainer(ArmOutboundDTO.class));
			} else if (!tableName.equals(ConstantUtil.ACCURAL_MASTER) && !tableName.equals(ConstantUtil.ACCRUAL_DETAILS)
					&& !tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)
					&& !tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL)) {
                tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName)));
			} else if (tableName.equals(ConstantUtil.ACCRUAL_MASTER)) {
				tableLogic.setContainerDataSource(new BeanItemContainer(AccrualMaster.class));
            }else {
                tableLogic.setContainerDataSource(new BeanItemContainer(AccrualdetailsDTO.class));
            }
        } else if ((ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(invalidTableName)) || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_MASTER.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_PARENT.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(invalidTableName) || invalidTableName.equals(ConstantUtil.IVLD_ITEM_MASTER) || invalidTableName.equals(ConstantUtil.IVLD_ITEM_IDENTIFIER) || invalidTableName.equals(ConstantUtil.IVLD_ITEM_PRICING)
                || ConstantUtil.INVALID_GTS_CUSTOMER.equals(invalidTableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName)
                || ConstantUtil.IVLD_RETURN_RESERVE.equals(invalidTableName)) {
            tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + invalidTableName)));
        } else {
            tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + invalidTableName)));
        }
        tableLogic.configureSearchData(null, tableName, moduleDetails, SelectedAccrualsId, true, false);
        table.removeAllItems();
        tableLogic.setItemsPerPage(10);
        table.setVisibleColumns(visibleCol);
        table.setColumnHeaders(colHeaders);
        table.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
        searchCriteria.setCustomDirty(false);
        for (Object propertyId : collapsedColumns) {
            table.setColumnCollapsed(propertyId, true);
        }
        if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
            table.setFilterFieldVisible("checkRecord", false);
            table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true, false);
        } else if (ConstantUtil.ARP_OUTBOUND.equals(tableName)) {
            table.setFilterFieldVisible(ConstantUtil.CHECKED_RECORD, false);
            table.setColumnCheckBox(ConstantUtil.CHECKED_RECORD, true, false);
        }

        searchCriteria.setCustomDirty(true);
        LOGGER.debug("Ends btnResetLogic");
    }

	@SuppressWarnings("unchecked")
	@Override
    protected void btnGenerateLogic() {
        LOGGER.debug("Entering btnGenerateLogic");
		List accrualId = new ArrayList();
		List itemIdContainer = getItemId();
		for (int i = 0; i < itemIdContainer.size(); i++) {
			if (SelectedAccrualsId.size() > 0
					&& SelectedAccrualsId.get(Integer.valueOf(
							table.getContainerProperty(itemIdContainer.get(i), ConstantUtil.ACCRUAL_DETAILS_SID)
									.getValue().toString())) != null
					&& SelectedAccrualsId.get(Integer.valueOf(
							table.getContainerProperty(itemIdContainer.get(i), ConstantUtil.ACCRUAL_DETAILS_SID)
									.getValue().toString()))
							.equals(true)) {
				accrualId.add(table.getContainerProperty(itemIdContainer.get(i), ConstantUtil.ACCRUAL_DETAILS_SID)
						.getValue());

            }

        }
		if (!accrualId.isEmpty()) {
			commonLogic.generateOutbound(accrualId, selectedRecords, viewNameSid, stagingTable);
            CommonUIUtils.successNotification("Accruals have been successfully created and will be sent to the General Ledger");

        } else {
            AbstractNotificationUtils.getErrorNotification("Generate", "Please select at least one accrual from the Results list view");

        }

        LOGGER.debug("Ending btnGenerateLogic");

    }

    @Override
    protected void btnSearchLogic() {
        try {
               LOGGER.debug("Entering btnSearchLogic" + tableName);
            tableLogic.setTableName(null);
            if (demandTypeChange == false) {
                table.clearFilters();
            }
            List<Object> collapsedColumns = new ArrayList<>();
            for (Object item : table.getVisibleColumns()) {
                if (table.isColumnCollapsed(item)) {
                    collapsedColumns.add(item);
                }
            }
            table.markAsDirty();
            searchCriteria = new SearchCriteria();
            searchCriteria.setCustomDirty(true);
            if (tableName.equals(ConstantUtil.ACCRUAL_MASTER) || tableName.equals(ConstantUtil.ACCRUAL_DETAILS)) {
                if (!"Other".equals(String.valueOf(searchValues.get(ConstantUtil.ACCRUAL_TYPE)))) {
                    tableName = ConstantUtil.ACCRUAL_DETAILS;
                } else {
                    tableName = ConstantUtil.ACCRUAL_MASTER;
                }
            }
            if (!tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                if (tableName.equals(ConstantUtil.ACCRUAL_MASTER)) {
                    searchResultbeans = new BeanItemContainer(AccrualMaster.class);
                } else if (tableName.equals(ConstantUtil.ACCRUAL_DETAILS)){
                    searchResultbeans = new BeanItemContainer(AccrualdetailsDTO.class);
                } else if (tableName.equals(ConstantUtil.ARP_OUTBOUND)) {
                    searchResultbeans = new BeanItemContainer(ARPOutboundDTO.class);
                }else if (tableName.equals(ConstantUtil.ST_CFF_OUTBOUND)) {
                    searchResultbeans = new BeanItemContainer(CFFOutBoundDTO.class);
                }else if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                    searchResultbeans = new BeanItemContainer(ArmOutboundDTO.class);
                }                else if (tableName.equals("VwCustomerGtsForecast") || tableName.equals("VwAdjustDemandForecastAct") || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL)
                        || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                        || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                        || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || ConstantUtil.RETURN_RESERVE.equals(tableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)) {
                    searchResultbeans = new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName));
                } else {
                    searchResultbeans = new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName));
                }       
                tableLogic.setContainerDataSource(searchResultbeans);
                if (searchResultbeans.getContainerPropertyIds().contains(ConstantUtil.MODEL_PROPERTY_ID)) {
                    searchResultbeans.removeContainerProperty(ConstantUtil.MODEL_PROPERTY_ID);
                }

                final StplSecurity stplSecurity = new StplSecurity();
                final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
                final Map<String, AppPermission> fieldForecastSalesHM = stplSecurity.getBusinessFieldPermission(userId, primaryDto.getValidation());
                final com.stpl.ifs.util.TableResultCustom tableResultCustom = commonSecurityLogic.modifyTableResult(tableColumnArr, tableHeaderArr, fieldForecastSalesHM);
                 if (tableName.equals(ConstantUtil.ACCURAL_MASTER) && super.Addcheckbox.equals("1")) {

                    final List<Object> objResultList = new ArrayList();
                    final List<String> objResultHeaderList = new ArrayList();
                    objResultList.add(ConstantUtil.RECORD_LOCK_STATUS);
                    objResultHeaderList.add("");
                    Object[] obj = tableResultCustom.getObjResult();
                    String[] header = tableResultCustom.getObjResultHeader();
                    for (int i = 0; i < obj.length; i++) {
                        objResultList.add(obj[i]);
                        objResultHeaderList.add(header[i]);
                    }
                    Object[] objResult = new Object[objResultList.size()];
                    String[] objResultHeader = new String[objResultHeaderList.size()];
                    for (int i = 0; i < objResultList.size(); i++) {
                        objResult[i] = objResultList.get(i);
                        objResultHeader[i] = objResultHeaderList.get(i);
                    }
                    com.stpl.ifs.util.TableResultCustom tblResultCustom1 = new com.stpl.ifs.util.TableResultCustom();
                    tblResultCustom1.setObjResult(objResult);
                    tblResultCustom1.setObjResultHeader(objResultHeader);
                    table.setEditable(true);
                    table.setImmediate(true);
                    table.setRefresh(true);

                    table.setColumnCheckBox(ConstantUtil.RECORD_LOCK_STATUS, true);
                    table.addColumnCheckListener(new ColumnCheckListener() {

                        @Override
						public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                            SelectedAccrualsId.clear();
							List itemId = getItemId();
							for (int i = 0; i < itemId.size(); i++) {

								searchResultbeans.getContainerProperty(itemId.get(i), ConstantUtil.RECORD_LOCK_STATUS)
										.setValue(event.isChecked());
								table.getContainerProperty(itemId.get(i), ConstantUtil.RECORD_LOCK_STATUS)
										.setValue(event.isChecked());
								SelectedAccrualsId.put(
										Integer.valueOf(table
												.getContainerProperty(itemId.get(i), ConstantUtil.ACCRUAL_DETAILS_SID)
												.getValue().toString()),
										(Boolean) table
												.getContainerProperty(itemId.get(i), ConstantUtil.RECORD_LOCK_STATUS)
												.getValue());
                            }

                        }
                    });

                    table.setTableFieldFactory(new TableFieldFactory() {
                        String indicator = StringUtils.EMPTY;
                        String glsSatus = StringUtils.EMPTY;

                        @Override
						public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                            Field field = null;
                            if (ConstantUtil.POSTING_STATUS.equals(propertyId)) {
                                glsSatus = table.getContainerProperty(itemId, propertyId).getValue().toString();

                            }
                            if (ConstantUtil.POSTING_INDICATOR.equals(propertyId)) {
                                indicator = table.getContainerProperty(itemId, propertyId).getValue().toString();
                            }

                            if (ConstantUtil.RECORD_LOCK_STATUS.equals(propertyId.toString())) {
                                final CheckBox obj = new CheckBox();
                                obj.setImmediate(true);

                                if (glsSatus.equals(StringUtils.EMPTY) || (indicator.equals(ConstantUtil.YES) && (glsSatus.equals(ConstantUtil.YES)))) {
                                    obj.setEnabled(false);

                                } else {
                                    obj.setEnabled(true);

                                }
                                obj.addValueChangeListener(new Property.ValueChangeListener() {
                                    @Override
                                    public void valueChange(Property.ValueChangeEvent event) {
                                        SelectedAccrualsId.put(Integer.valueOf(table.getContainerProperty(itemId, ConstantUtil.ACCRUAL_DETAILS_SID).getValue().toString()), (Boolean) event.getProperty().getValue());
                                        table.getContainerProperty(itemId, ConstantUtil.RECORD_LOCK_STATUS).setValue(event.getProperty().getValue());

                                    }
                                });
                                field = obj;

                            }
                            return field;
                        }
                    });
                    table.setVisibleColumns(tblResultCustom1.getObjResult());
                    table.setColumnHeaders(tblResultCustom1.getObjResultHeader());

                } else if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                    table.setFilterFieldVisible(ConstantUtil.CHECK_RECORD, false);
                    table.setEditable(Boolean.TRUE);
                    
                }else if(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)){
                     table.setFilterFieldVisible(ConstantUtil.CHECK_RECORD, false);
                     table.setEditable(Boolean.TRUE);                    
                    table.addColumnCheckListener(new ColumnCheckListener() {

                        @Override
						public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                            
                            List Item_id = getItemId();
                            for (int i = 0; i < Item_id.size(); i++) {

                                searchResultbeans.getContainerProperty(Item_id.get(i), ConstantUtil.CHECK_RECORD).setValue(event.isChecked());
                                if(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)){
                                    updateAllGTNCheckRecord(String.valueOf(event.isChecked()).equals("true")?"1":"0",USERID); 
                                } else if(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)){
                                     updateAllReserveCheckRecord(String.valueOf(event.isChecked()).equals("true")?"1":"0",USERID);
                                }
                            }

                        }
                    });
                    table.setTableFieldFactory(new TableFieldFactory() {
                         final SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        @Override
						public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                            Field field = null;
                          
                            if (ConstantUtil.CHECK_RECORD.equals(propertyId.toString())) {
                                final CheckBox obj = new CheckBox();
                                obj.setImmediate(true);
                                obj.setEnabled(true);
                                obj.addValueChangeListener(new Property.ValueChangeListener() {
                                    @Override
                                    public void valueChange(Property.ValueChangeEvent event) {
                                        String check;
                                        if(event.getProperty().getValue().equals(true)){
                                            check="1";
                                        } else{
                                            check="0";
                                        }
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                                        if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                                            String account = String.valueOf(table.getContainerProperty(itemId, "account").getValue());
                                            String adjustmentType = String.valueOf(
                                            HelperListUtil.getInstance().getIdByDesc(ConstantUtil.ARM_ADJUSTMENT_TYPE, String.valueOf(table.getContainerProperty(itemId, "adjustmentType").getValue())));
                                            String companyId = String.valueOf(table.getContainerProperty(itemId, "companyId").getValue());
                                            String accountType =String.valueOf(
                                            HelperListUtil.getInstance().getIdByDesc("ARM_ACCOUNT_TYPE", String.valueOf(table.getContainerProperty(itemId, "accountType").getValue())));
                                            String itemIdProperty = String.valueOf(table.getContainerProperty(itemId, "itemId").getValue());
                                            String workflowId = String.valueOf(table.getContainerProperty(itemId, "workflowId").getValue());
                                            String accountCategory = String.valueOf(
                                            HelperListUtil.getInstance().getIdByDesc("ACCOUNT_CATEGORY", String.valueOf(table.getContainerProperty(itemId, "accountCategory").getValue())));
                                            String contractId = String.valueOf(table.getContainerProperty(itemId, "contractId").getValue());
                                            String glCompanyId = String.valueOf(table.getContainerProperty(itemId, "glCompanyId").getValue());
                                            String businessUnitId = String.valueOf(table.getContainerProperty(itemId, "businessUnitId").getValue());
                                            String deductionId = String.valueOf(table.getContainerProperty(itemId, "deductionId").getValue());
                                            Date calculationPeriodDate = (Date) table.getContainerProperty(itemId, "calculationPeriod").getValue();
                                            String calculationPeriod = format.format(calculationPeriodDate);
                                            updateGTNCheckRecord(check, account, adjustmentType, companyId, accountType, itemIdProperty, workflowId, accountCategory, contractId, glCompanyId, calculationPeriod, businessUnitId, deductionId);
                                            if (noCheckedRecordGTN().size() != primaryDto.getTotalCount()) {
                                                table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true, false);
                                            } else {
                                                table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true, true);
                                            }
                                            
                                        } else if (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                                            String account = String.valueOf(table.getContainerProperty(itemId, "account").getValue());
                                            String adjustmentType = String.valueOf(
                                            HelperListUtil.getInstance().getIdByDesc(ConstantUtil.ARM_ADJUSTMENT_TYPE, String.valueOf(table.getContainerProperty(itemId, "adjustmentType").getValue())));
                                            String company = String.valueOf(table.getContainerProperty(itemId, "glCompanyName").getValue());
                                            String accountType = String.valueOf(
                                            HelperListUtil.getInstance().getIdByDesc("ARM_ACCOUNT_TYPE", String.valueOf(table.getContainerProperty(itemId, "accountType").getValue())));
                                            String businessUnit = String.valueOf(table.getContainerProperty(itemId, "businessUnitId").getValue());
                                            String workflowId = String.valueOf(table.getContainerProperty(itemId, "workflowId").getValue());
                                            String accountCategory = String.valueOf(
                                            HelperListUtil.getInstance().getIdByDesc("ACCOUNT_CATEGORY", String.valueOf(table.getContainerProperty(itemId, "accountCategory").getValue())));
                                            String brand = String.valueOf(table.getContainerProperty(itemId, "brand").getValue());
                                            Date calculationPeriodDate = (Date) table.getContainerProperty(itemId, "calculationPeriod").getValue();
                                            String calculationPeriod = format.format(calculationPeriodDate);
                                            updateReserveCheckRecord(check, account, adjustmentType, calculationPeriod, accountType, brand, workflowId, accountCategory, businessUnit, company);
                                            if (noCheckedRecordReserveDetails().size() != primaryDto.getTotalCount()) {
                                                table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true, false);
                                            } else {
                                                table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true, true);
                                            }
                                            
                                        }
                                        }
                                });
                                field = obj;
                            }
                            return field;
                        }
                    });
                    table.setVisibleColumns(tableResultCustom.getObjResult());
                    table.setColumnHeaders(tableResultCustom.getObjResultHeader());
                    
                }  else {
                    table.setVisibleColumns(tableResultCustom.getObjResult());
                    if (tableName.equals(ConstantUtil.ARP_OUTBOUND)) {
                        String[] header = commonLogic.getArpOutBoundHeader(tableResultCustom.getObjResultHeader());
                        table.setColumnHeaders(header);
                    } else {
                        table.setColumnHeaders(tableResultCustom.getObjResultHeader());
                    }

                }
                switch (tableName) {
                    case ConstantUtil.ARP_OUTBOUND:
                        table.setEditable(Boolean.TRUE);
                        searchLogic.deleteARPTempTable(); 
                        break;
                    case ConstantUtil.ST_CFF_OUTBOUND:
                        searchLogic.deleteCFFTempTable();
                        break;
                    case ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL:
                        commonLogic.dataForAdjustmentGTNoutbound(USERID, SESSIONID);
                        break;
                    case ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL:
                        commonLogic.dataForAdjustmentReserveoutbound(USERID, SESSIONID);
                        break;
                    default:
                        break;
                }
                tableLogic.setIsFirstSearch(Boolean.TRUE);
                tableLogic.setIsFirstTimeSearch(Boolean.TRUE);
                tableLogic.configureSearchData(searchValues, tableName, moduleDetails, SelectedAccrualsId, false, true);
                tableLogic.setIsFirstSearch(Boolean.FALSE);
                tableLogic.setIsFirstTimeSearch(Boolean.FALSE);
                }
            if (tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {

                if (invalidTableName.equals("IvldCustomerGtsForecast") || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(invalidTableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(invalidTableName)
                        || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(invalidTableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_MASTER.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_PARENT.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(invalidTableName) || invalidTableName.equals(ConstantUtil.IVLD_ITEM_MASTER) || invalidTableName.equals(ConstantUtil.IVLD_ITEM_IDENTIFIER) || invalidTableName.equals("IvldItemPricing")
                        || ConstantUtil.IVLD_RETURN_RESERVE.equals(invalidTableName)) {
                    tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + invalidTableName)));
                } else {
                    tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + invalidTableName)));
                }
                if (invalidTableName.equalsIgnoreCase(ConstantUtil.INVALID_GTS_CUSTOMER) || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(invalidTableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(invalidTableName)
                        || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(invalidTableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_MASTER.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_PARENT.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(invalidTableName) || invalidTableName.equals(ConstantUtil.IVLD_ITEM_MASTER) || invalidTableName.equals(ConstantUtil.IVLD_ITEM_IDENTIFIER) || invalidTableName.equals(ConstantUtil.IVLD_ITEM_PRICING)) {
                    searchResultbeans = new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + invalidTableName));

                } else {
                    searchResultbeans = new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + invalidTableName));
                }
                tableLogic.setContainerDataSource(searchResultbeans);
                tableOnChange();

                primaryDto.setPropertyName(viewName);
                if (searchResultbeans != null && searchResultbeans.getContainerPropertyIds().contains(ConstantUtil.MODEL_PROPERTY_ID)) {
                        searchResultbeans.removeContainerProperty(ConstantUtil.MODEL_PROPERTY_ID);
                }
                //This line is added to check whether all the records is unchecked before loading the screen\
                String invalidTableDemand = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME));
                if (invalidTableName.equals(ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW)) {
                    invalidTableDemand = ConstantUtil.INVALID_ADJUST_DEMAND_VIEW;
                }
                
                commonLogic.updateAllInInvalidTable(invalidTableDemand, "0");
               
                tableLogic.configureSearchData(searchValues, invalidTableName, moduleDetails, SelectedAccrualsId, false, false);
            }

            if (!tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                final StplSecurity stplSecurity = new StplSecurity();
                final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
                final Map<String, AppPermission> fieldForecastSalesHM = stplSecurity.getBusinessFieldPermission(userId, primaryDto.getValidation());
                final TableResultCustom tableResultCustom = UISecurityUtil.modifyTableResult(tableColumnArr, tableHeaderArr, fieldForecastSalesHM);
                table.setVisibleColumns(tableResultCustom.getObjResult());
                if (tableName.equals(ConstantUtil.ARP_OUTBOUND)) {
                    String[] header = commonLogic.getArpOutBoundHeader(tableResultCustom.getObjResultHeader());
                    table.setColumnHeaders(header);
                    table.setColumnCheckBox(ConstantUtil.CHECKED_RECORD, true, false);
                } else {
                    table.setColumnHeaders(tableResultCustom.getObjResultHeader());
                    }
                }

            if (tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                Object[] tableColumnArr1 = null;
                String[] tableHeaderArr1 = null;
                if (invalidTableName.equalsIgnoreCase("IvldActualMaster")) {
                    List<Object> list = new ArrayList<>(Arrays.asList(tableColumnArr));
                    list.removeAll(Arrays.asList("actualsMasterSid"));
                    tableColumnArr1 = list.toArray();

                    List<String> list1 = new ArrayList<>(Arrays.asList(tableHeaderArr));
                    list1.removeAll(Arrays.asList("Actuals Master ID"));
                    tableHeaderArr1 = list1.toArray(new String[list1.size()]);
                    table.setVisibleColumns(tableColumnArr1);
                    table.setColumnHeaders(tableHeaderArr1);
                } else if ("IvldSalesMaster".equalsIgnoreCase(invalidTableName)) {
                    for (int i = 0; i < tableColumnArr.length; i++) {
                        if (tableColumnArr[i].toString().equals("itemParentNo")) {
                            tableColumnArr[i] = "parentItemNo";
                        }
                    }
                    table.setVisibleColumns(tableColumnArr);
                    table.setColumnHeaders(tableHeaderArr);
                } else {
                    table.setVisibleColumns(tableColumnArr);
                    table.setColumnHeaders(tableHeaderArr);
                }
                table.removeGeneratedColumn(generatedColumn);
                table.addGeneratedColumn(generatedColumn, new ExtCustomTable.ColumnGenerator() {

                    @Override
                    public Object generateCell(final ExtCustomTable source, final Object itemId, Object columnId) {
                        String caption = "";
                        Object caption1 = new Object();
                        if (source.getContainerProperty(itemId, generatedColumn) != null) {
                            caption1 = source.getContainerProperty(itemId, generatedColumn).getValue();
                        }
                        if (caption1 != null) {
                            caption = caption1.toString();
                        }
                        BeanItem searcForecastDTO = new BeanItem(itemId);
                        long masterid = Long.valueOf(String.valueOf(searcForecastDTO.getItemProperty(generatedColumn).getValue()));
                        VaadinSession.getCurrent().setAttribute(ConstantUtil.VIEW_NAME, masterid);

                        Button ndcLink = new Button(caption);
                        ndcLink.addStyleName(Reindeer.BUTTON_LINK);
                        ndcLink.setImmediate(true);
                        ndcLink.addClickListener(new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                int systemId = Integer.valueOf(String.valueOf(source.getContainerProperty(itemId, viewName).getValue()));
                                if (!"AverageShelfLife".equals(VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME))
                                        && !"GlCostCenterMaster".equals(VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME))
                                        && !"ItemHierarchyDefinition".equals(VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME))
                                        && !"LotMaster".equals(VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME))
                                        && !ConstantUtil.RETURN_RESERVE_ACTUAL.equals(VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME))) {
                                    VaadinSession.getCurrent().setAttribute(ConstantUtil.VIEW_SID_NAME, viewNameSid);
                                    String tableName = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME)).equals(ConstantUtil.CUSTOMERSALES) ? ConstantUtil.GTS_FORECAST : String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME));
                                    InformationPopup infoPopup = new InformationPopup(systemId, tableName);
                                    UI.getCurrent().addWindow(infoPopup);
                                }
                            }
                        });
                        return ndcLink;
                    }
                });
            }

            if (generatedColumn.equalsIgnoreCase("actualIntfid")) {
                table.setColumnHeader(generatedColumn, "Invalid Record Id");
            }
            table.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
            table.addStyleName("filterbar");
            if (!tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
            if (searchResultbeans.size() > ConstantUtil.TAB_VALUE) {
                CommonUIUtils.successNotification("Search Completed");
            }  
                else if (!(searchResultbeans.size() > ConstantUtil.TAB_VALUE) && "VwReturnReserve".equalsIgnoreCase(tableName)) {
                    if (searchValues.isEmpty()) {
                        AbstractNotificationUtils.getErrorNotification("Search", " There are no Accrual Please enter/select search criteria.");
                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Records Found", "There are no Accrual Records that match the entered search criteria. Please enter new search criteria and try again");
                    }
                } else {
                    CommonUIUtils.successNotification("No results found");
                }
            } else if (table.getItemIds().size() > ConstantUtil.TAB_VALUE) {
                CommonUIUtils.successNotification("Search Completed");
            } else {
                CommonUIUtils.successNotification("No results found");
            }
            searchCriteria.setCustomDirty(false);
            for (Object propertyId : collapsedColumns) {
                table.setColumnCollapsed(propertyId, true);
            }

            searchCriteria.setCustomDirty(true);
            table.setFilterBarVisible(true);
            table.setFilterDecorator(new ExtDemoFilterDecorator());
            table.setImmediate(true);
            
            // Unchecks CFF check all checkbox after search
                 table.setFilterFieldVisible(ConstantUtil.CHECK_RECORD, false);
            table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true, false);
                
            // Unchecks ARP check all checkbox after search
            if (ConstantUtil.ARP_OUTBOUND.equals(tableName)) {
            table.setFilterFieldVisible(ConstantUtil.CHECKED_RECORD, false);
            table.setColumnCheckBox(ConstantUtil.CHECKED_RECORD, true, false);
            }
            if (primaryDto.getValidation().equals(ConstantUtil.GL_SPACE_BALANCE) || primaryDto.getValidation().equals(ConstantUtil.ACCRUAL_MASTER_UPPER) || primaryDto.getValidation().equals(ConstantUtil.CPI_INDEX)) {
                table.setFilterGenerator(new FilterGenerator(tableName));
            }
            LOGGER.debug("Ends btnSearchLogic");
        } catch (Exception e) {
            LOGGER.error(e);

        }

        LOGGER.debug("search ends " + invalidTableName);
    }

     @UiHandler("reprocessBtn")
    public void reprocessBtn(Button.ClickEvent event) {
         
        if (!tableName.equalsIgnoreCase(ConstantUtil.ST_CFF_OUTBOUND) && !tableName.equalsIgnoreCase(ConstantUtil.ARP_OUTBOUND) && !tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) && !tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
            if (commonLogic.isRecordChecked(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME)))) {
                String reasonForFailure = "";
                boolean isNotMissing = true;
                if (tableName.equalsIgnoreCase("Invalidrecordcount")) {
                    try {

                        for (Object se : (Set<Object>) table.getValue()) {
                            Class cls = null;
                            if (invalidTableName.equalsIgnoreCase(ConstantUtil.INVALID_GTS_CUSTOMER) || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(invalidTableName)
                                    || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(invalidTableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName)
                                    || ConstantUtil.IVLD_RETURN_RESERVE.equals(invalidTableName)) {
                                cls = Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + invalidTableName);
                            } else {
                                cls = Class.forName(ConstantUtil.TABLE_MODEL_PATH + invalidTableName);
                            }
                            java.lang.reflect.Field field = cls.cast(se).getClass().getDeclaredField(ConstantUtil.PROPERTY_ID);
                            field.setAccessible(true);
                            reasonForFailure = String.valueOf(field.get(se));
                            if (StringUtils.isNotBlank(reasonForFailure) && reasonForFailure.equalsIgnoreCase(ConstantUtil.MISSING_REQUIRED_FIELD)) {
                                AbstractNotificationUtils.getErrorNotification(ConstantUtil.REPROCESS, "The selected lines cannot be re-processed as they are missing required fields");
                                isNotMissing = false;
                                break;
                            }

                        }

                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }

                }
                if (isNotMissing) {
                    MessageBox.showPlain(Icon.QUESTION, ConstantUtil.CONFIRMATION, "Are you sure you want to Reprocess the selected record(s) " + " ?", new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @Override
						@SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals("YES")) {

                                try {
                                    reprocessButtonLogic();
                                    tableLogic.groupChange();
                                    final Notification notif = new Notification("Selected records have been successfully Reprocessed", Notification.Type.HUMANIZED_MESSAGE);
                                    notif.setPosition(Position.MIDDLE_CENTER);
                                    notif.setStyleName(ConstantsUtils.MY_STYLE);
                                    notif.show(Page.getCurrent());
                                    notif.setDelayMsec(2000);
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }

                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(ConstantUtil.REPROCESS, "Please select at least one row from the Results list view");
            }
            table.setValue(null);
        } else if (tableName.equalsIgnoreCase(ConstantUtil.ARP_OUTBOUND) || tableName.equalsIgnoreCase(ConstantUtil.ST_CFF_OUTBOUND) || tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
            boolean isNotMissing = true;
            try {
                int checkRecord = 0;
                if (tableName.equalsIgnoreCase(ConstantUtil.ARP_OUTBOUND)) {
                    checkRecord = noCheckedRecordArp();
                } else if (tableName.equalsIgnoreCase(ConstantUtil.ST_CFF_OUTBOUND)) {
                    checkRecord = noCheckedRecord().size();
                } else if (tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL)) {
                    checkRecord = noCheckedRecordGTN().size();
                } else if (tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                    checkRecord = noCheckedRecordReserveDetails().size();
                }
                if (checkRecord == 0) {
                    AbstractNotificationUtils.getErrorNotification(ConstantUtil.REPROCESS, "Please select a record to Reprocess");
                    isNotMissing = false;

                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            if (isNotMissing) {
                MessageBox.showPlain(Icon.QUESTION, ConstantUtil.CONFIRMATION, "Are you sure you want to Reprocess the selected record(s) " + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @Override
					@SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {

                            try {
                                if (tableName.equalsIgnoreCase(ConstantUtil.ARP_OUTBOUND)) {
                                    stagingTable = "ARP_OUTBOUND";
                                    commonLogic.reprocessSelectedRecords("StArpOutbound",stagingTable,selectedRecords,viewNameSid);
                                    table.setColumnCheckBox(ConstantUtil.CHECKED_RECORD, true, false);
                                    tableLogic.configureSearchData(searchValues, tableName, moduleDetails, SelectedAccrualsId, false, false);
                                } else if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                                    commonLogic.reprocessSelectedRecords(ConstantUtil.ST_CFF_OUTBOUND,stagingTable,selectedRecords,viewNameSid);
                                    table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true, false);
                                    tableLogic.configureSearchData(searchValues, tableName, moduleDetails, SelectedAccrualsId, false, false);
                                } else if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                                    commonLogic.reprocessSelectedRecords(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL,stagingTable,selectedRecords,viewNameSid);
                                    searchValues.put("transactionLevel", NumericConstants.ONE);
                                    commonLogic.dataForAdjustmentGTNoutbound(USERID, SESSIONID);
                                    table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true, false);
                                    tableLogic.setIsFirstTimeSearch(Boolean.TRUE);
                                    tableLogic.configureSearchData(searchValues, tableName, moduleDetails, SelectedAccrualsId, false, false);
                                    tableLogic.setIsFirstTimeSearch(Boolean.FALSE);
                                    searchLogic.adjustmentDetailsSearchRestriction(tableName, searchValues);
                                } else if (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                                    commonLogic.reprocessSelectedRecords(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL,stagingTable,selectedRecords,viewNameSid);
                                    searchValues.put("transactionLevel", 0);
                                    commonLogic.dataForAdjustmentReserveoutbound(USERID, SESSIONID);
                                    table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true, false);
                                    tableLogic.setIsFirstTimeSearch(Boolean.TRUE);
                                    tableLogic.configureSearchData(searchValues, tableName, moduleDetails, SelectedAccrualsId, false, false);
                                    tableLogic.setIsFirstTimeSearch(Boolean.FALSE);
                                    searchLogic.adjustmentDetailsSearchRestriction(tableName, searchValues);
                                }
                                final Notification notif = new Notification(" has been successfully Reprocessed", Notification.Type.HUMANIZED_MESSAGE);
                                notif.setPosition(Position.MIDDLE_CENTER);
                                notif.setStyleName(ConstantsUtils.MY_STYLE);
                                notif.show(Page.getCurrent());
                                
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }

                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }

        }
    } 

    public void reprocessButtonLogic() {
        String invalidTable = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME));
        if ((ConstantUtil.VW_IVLD_DEMAND_FPRECAST).equals(invalidTable)) {
            invalidTable = ConstantUtil.INVALID_ADJUST_DEMAND_VIEW;
        }
        commonLogic.reprocessSelectedRecords(invalidTable, stagingTable, selectedRecords, viewNameSid);
    }
    public List getItemId() {
        List Item_id = searchResultbeans.getItemIds(0, searchResultbeans.size());
        return Item_id;
    }

    @UiHandler("reset")
    public void resetListview(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, ConstantUtil.CONFIRMATION, "Are you sure you want to reset the page to default/previous values " + " ?", new MessageBoxListener() {
            /**
             *
             * Called when a Button has been clicked .
             *
             */
            @Override
			@SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {

                    try {
                        resetButtonLogic();
                        resetFields();

                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }

                }
            }

        }, ButtonId.YES, ButtonId.NO);

        table.setValue(null);
    }

    private void resetFields() {
        LOGGER.debug("Inside resetFields");
        searchValues.clear();
        errorMsg.clearError();
        try {
            Iterator<Component> componentIterator = null;
            Iterator<Component> componentIterator2 = null;

            componentIterator2 = cssLayout1.getComponentIterator();
            componentIterator = cssLayout.getComponentIterator();

            if (componentIterator2 != null) {
                while (componentIterator2.hasNext()) {
                    Component component = componentIterator2.next();
                    if (component instanceof TextField) {
                        ((TextField) component).setValue(StringUtils.EMPTY);
                    } else if (component instanceof PopupDateField) {
                        ((PopupDateField) component).setValue(null);
                    } else if (component instanceof ComboBox && !((ComboBox) component).getData().equals("table")) {
                            ((ComboBox) component).select(ConstantUtil.SELECT_ONE);
                    }
                }
            }
            while (componentIterator.hasNext()) {
                Component component = componentIterator.next();
                if (component instanceof TextField) {
                    ((TextField) component).setValue(StringUtils.EMPTY);
                } else if (component instanceof PopupDateField) {
                    ((PopupDateField) component).setValue(null);
                } else if (component instanceof ComboBox && !((ComboBox) component).getData().equals("table")) {
                        if ("glCompanyMasterSid".equals(((ComboBox) component).getData())) {
                            ((ComboBox) component).select(0);
                        } else {
                            ((ComboBox) component).select(ConstantUtil.SELECT_ONE);
                        }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
     }
    
 private void updateAllGTNCheckRecord(String check,String userId) {
     String query = "UPDATE ST_ADJUSTMENT_GTN_DETAIL SET CHECK_RECORD="+check+ConstantUtil.WHERE
             + ConstantUtil.USER_ID_EQ_TO+userId+ConstantUtil.AND_SESSION_ID_WITH_QUOTES1+SESSIONID+"'";
           HelperTableLocalServiceUtil.executeUpdateQuery(query);
        }
 private void updateGTNCheckRecord(String check, String account,
            String adjustmentType, String companyId, String accountType, String itemIdProperty, String workflowId,
            String accountCategory, String contractId, String glCompanyId, String calculationPeriod, String businessUnitId,String deductionId) {
        String query = "UPDATE ST_ADJUSTMENT_GTN_DETAIL SET CHECK_RECORD=" + check + " WHERE"
                + " ACCOUNT='" + account + ConstantUtil.AND_WITH_QUOTES
                + " ADJUSTMENT_TYPE='" + adjustmentType + ConstantUtil.AND_WITH_QUOTES
                + " COMPANY_ID='" + companyId + ConstantUtil.AND_WITH_QUOTES
                + " ACCOUNT_TYPE='" + accountType + ConstantUtil.AND_WITH_QUOTES
                + " ITEM_ID='" + itemIdProperty + ConstantUtil.AND_WITH_QUOTES
                + " WORKFLOW_ID='" + workflowId + ConstantUtil.AND_WITH_QUOTES
                + " ACCOUNT_CATEGORY='" + accountCategory + ConstantUtil.AND_WITH_QUOTES
                + " CONTRACT_ID='" + contractId + ConstantUtil.AND_WITH_QUOTES
                + " GL_COMPANY_ID='" + glCompanyId + ConstantUtil.AND_WITH_QUOTES
                + " DEDUCTION_ID='" + deductionId + ConstantUtil.AND_WITH_QUOTES
                + " CALCULATION_PERIOD >='" + calculationPeriod + ConstantUtil.AND_WITH_QUOTES
                + " BUSINESS_UNIT_ID='" + businessUnitId + ConstantUtil.AND_WITH_QUOTES
                + ConstantUtil.USER_ID_EQ_TO + USERID + ConstantUtil.AND_SESSION_ID_WITH_QUOTES1 + SESSIONID + "'";
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }
 
    private void updateReserveCheckRecord(String check, String account, String adjustmentType,
            String calculationPeriod,
            String accountType, String brand,
            String workflowId, String accountCategory,
            String businessUnit, String company) {

        String query = "UPDATE ST_ADJUSTMENT_RESERVE_DETAIL SET CHECK_RECORD=" + check + ConstantUtil.WHERE
                + " ACCOUNT='" + account + ConstantUtil.AND_WITH_QUOTES
                + " ADJUSTMENT_TYPE='" + adjustmentType + ConstantUtil.AND_WITH_QUOTES
                + " CALCULATION_PERIOD='" + calculationPeriod + ConstantUtil.AND_WITH_QUOTES
                + " ACCOUNT_TYPE='" + accountType + ConstantUtil.AND_WITH_QUOTES
                + " BRAND='" + brand + ConstantUtil.AND_WITH_QUOTES
                + " WORKFLOW_ID='" + workflowId + ConstantUtil.AND_WITH_QUOTES
                + " ACCOUNT_CATEGORY='" + accountCategory + ConstantUtil.AND_WITH_QUOTES
                + " BUSINESS_UNIT='" + businessUnit + ConstantUtil.AND_WITH_QUOTES
                + " COMPANY='" + company + ConstantUtil.AND_WITH_QUOTES
                + ConstantUtil.USER_ID_EQ_TO + USERID + ConstantUtil.AND_SESSION_ID_WITH_QUOTES1 + SESSIONID + "'";
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }

    private void updateAllReserveCheckRecord(String check, String userId) {
        String query = "UPDATE ST_ADJUSTMENT_RESERVE_DETAIL SET CHECK_RECORD=" + check + ConstantUtil.WHERE
                + ConstantUtil.USER_ID_EQ_TO + userId + ConstantUtil.AND_SESSION_ID_WITH_QUOTES1 + SESSIONID + "'";
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }
    

    private int noCheckedRecordArp() {
        int size = 0;
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID_ARP));
        String query = "SELECT COUNT(1) FROM ST_ARP_OUTBOUND WHERE CHECK_RECORD=1"
                + ConstantUtil.AND_USER_ID + userId + ConstantUtil.AND_SESSION_ID + sessionId + "";
        List<Object> result = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (result != null && result.size() > 0) {
            size = Integer.valueOf(String.valueOf(result.get(0)));
        }
        return size;
    }

    private List<Object> noCheckedRecord() {
        String query = "SELECT * FROM ST_CFF_OUTBOUND_MASTER WHERE CHECK_RECORD=1"
                + ConstantUtil.AND_USER_ID + USERID + "' AND SESSION_ID = '" + SESSIONID + "'";
        List<Object> result = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return result;
    }

    private List<Object> noCheckedRecordReserveDetails() {
        String query = "SELECT WORKFLOW_ID FROM ST_ADJUSTMENT_RESERVE_DETAIL WHERE CHECK_RECORD=1"
                + ConstantUtil.AND_USER_ID + USERID + "' AND SESSION_ID='" + SESSIONID + "'";
        List<Object> result = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return result;
    }

    private List<Object> noCheckedRecordGTN() {
        String query = "SELECT WORKFLOW_ID FROM ST_ADJUSTMENT_GTN_DETAIL WHERE CHECK_RECORD=1"
                + ConstantUtil.AND_USER_ID + USERID + "' AND SESSION_ID='" + SESSIONID + "'";
        List<Object> result = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return result;
    }
}
