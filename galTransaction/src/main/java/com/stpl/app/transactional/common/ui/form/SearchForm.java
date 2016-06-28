/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.ui.form;

import com.stpl.app.transactional.common.abstractForm.AbstractSearch;
import com.stpl.app.transactional.common.dto.DetailsDTO;

import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.transactional.common.dto.AccrualdetailsDTO;
import com.stpl.app.transactional.common.dto.FilterGenerator;
import com.stpl.app.transactional.common.logic.CommonLogic;
import com.stpl.app.transactional.common.ui.view.DisplayView;
import com.stpl.app.transactional.common.util.SearchCriteria;
import com.stpl.app.util.AbstractNotificationUtils;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.util.TableResultCustom;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTable.ColumnCheckListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Reindeer;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.jboss.logging.Logger;
//import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

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
    TableFieldFactory fieldFactory;
    Map<Integer, Boolean> SelectedAccrualsId = new LinkedHashMap<Integer, Boolean>();

    public SearchForm(final List<DetailsDTO> moduleDetails, final List<DetailsDTO> primaryValueList) throws Exception {
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
            if (searchValues != null && searchValues.get("accrualType") != null 
                    && !searchValues.get("accrualType").equals("Other") 
                    && !tableName.equals(ConstantUtil.INVALID_ACCURAL_INBOUND)) {
                primaryDto.setPropertyName("accrualDetailsSid");
                VaadinSession.getCurrent().setAttribute("AccrualDetails", "true");
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
                if (functionForecastSalesHM.get(ConstantUtil.FUNCTIONAL_VIEW) != null && ((AppPermission) functionForecastSalesHM.get(ConstantUtil.FUNCTIONAL_VIEW)).isFunctionFlag()) {
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
            LOGGER.info("Ends itemClick");
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        } catch (PortalException ex) {
            LOGGER.error(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    protected void resetButtonLogic() throws PortalException, Exception {
        LOGGER.info("Entering btnResetLogic");
        List<Object> collapsedColumns = new ArrayList<Object>();
        for (Object item : table.getVisibleColumns()) {
            if (table.isColumnCollapsed(item)) {
                collapsedColumns.add(item);
            }
        }
        Object[] visibleCol = table.getVisibleColumns();
        String[] colHeaders = table.getColumnHeaders();
        if (!tableName.equalsIgnoreCase("InvalidRecordCount")) {

            if (tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST) || tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW)  || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL)  ) {
                tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName)));
            } else if (!tableName.equals(ConstantUtil.ACCURAL_MASTER) && !tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST) && !tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) ) {
                tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName)));
            }
            else {
                tableLogic.setContainerDataSource(new BeanItemContainer(AccrualdetailsDTO.class));
            }
        } else {
            if ((ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(invalidTableName))  || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(invalidTableName) 
            || ConstantUtil.INVALID_GTS_CUSTOMER.equals(invalidTableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName)|| ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName) ) {
                tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + invalidTableName)));
            } else {
                tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + invalidTableName)));
            }
        }
        tableLogic.configureSearchData(null, tableName, moduleDetails, SelectedAccrualsId,true);
        table.removeAllItems();
        table.setVisibleColumns(visibleCol);
        table.setColumnHeaders(colHeaders);
        table.setWidth(99, UNITS_PERCENTAGE);
        searchCriteria.setCustomDirty(false);
        for (Object propertyId : collapsedColumns) {
            table.setColumnCollapsed(propertyId, true);
        }
        searchCriteria.setCustomDirty(true);
        LOGGER.info("Ends btnResetLogic");
    }

    @Override
    protected void btnGenerateLogic() {
        LOGGER.info("Entering btnGenerateLogic");
        List AccrualId = new ArrayList();
        List Item_id_container = getItemId();
        for (int i = 0; i < Item_id_container.size(); i++) {
            if (SelectedAccrualsId.size() > 0 && SelectedAccrualsId.get(Integer.valueOf(table.getContainerProperty(Item_id_container.get(i), ConstantUtil.ACCRUAL_DETAILS_SID).getValue().toString())) != null && SelectedAccrualsId.get(Integer.valueOf(table.getContainerProperty(Item_id_container.get(i), ConstantUtil.ACCRUAL_DETAILS_SID).getValue().toString())).equals(true)) {
                AccrualId.add(table.getContainerProperty(Item_id_container.get(i), ConstantUtil.ACCRUAL_DETAILS_SID).getValue());

            }

        }
        if (AccrualId.size() != 0) {
            commonLogic.generateOutbound(AccrualId);
            CommonUIUtils.successNotification("Accruals have been successfully created and will be sent to the General Ledger");

            } else {
                AbstractNotificationUtils.getErrorNotification("Generate", "Please select at least one accrual from the Results list view");

            }

        LOGGER.info("Ending btnGenerateLogic");

    }

    @Override
    protected void btnSearchLogic() {
        try {
            LOGGER.info("Entering btnSearchLogic" + tableName);
            tableLogic.setTableName(null);
            if (demandTypeChange == false) {
                table.clearFilters();
            }
            List<Object> collapsedColumns = new ArrayList<Object>();
            for (Object item : table.getVisibleColumns()) {
                if (table.isColumnCollapsed(item)) {
                    collapsedColumns.add(item);
                }
            }
            table.markAsDirty();
            searchCriteria = new SearchCriteria();
            searchCriteria.setCustomDirty(true);
            if (!tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                if (tableName.equals("AccrualMaster") || tableName.equals("AccrualDetails")) {
                    searchResultbeans = new BeanItemContainer(AccrualdetailsDTO.class);
                } else if (tableName.equals("VwCustomerGtsForecast") || tableName.equals("VwAdjustDemandForecastAct")  || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) ) {
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

                        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                            SelectedAccrualsId.clear();
                            List Item_id = getItemId();
                            for (int i = 0; i < Item_id.size(); i++) {

                                searchResultbeans.getContainerProperty(Item_id.get(i), ConstantUtil.RECORD_LOCK_STATUS).setValue(event.isChecked());
                                table.getContainerProperty(Item_id.get(i), ConstantUtil.RECORD_LOCK_STATUS).setValue(event.isChecked());
                                SelectedAccrualsId.put(Integer.valueOf(table.getContainerProperty(Item_id.get(i), ConstantUtil.ACCRUAL_DETAILS_SID).getValue().toString()), (Boolean) table.getContainerProperty(Item_id.get(i), ConstantUtil.RECORD_LOCK_STATUS).getValue());
                            }

                        }
                    });

                    table.setTableFieldFactory(new TableFieldFactory() {
                        String indicator = StringUtils.EMPTY;
                        String glsSatus = StringUtils.EMPTY;

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

                } else {
                    table.setVisibleColumns(tableResultCustom.getObjResult());
                    table.setColumnHeaders(tableResultCustom.getObjResultHeader());

                }
                tableLogic.configureSearchData(searchValues, tableName, moduleDetails, SelectedAccrualsId,false);
            }
            if (tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {

                if (invalidTableName.equals("IvldCustomerGtsForecast") || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(invalidTableName)
                        || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(invalidTableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName)) {
                    tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + invalidTableName)));
                } else {
                    tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + invalidTableName)));
                }
                if (invalidTableName.equalsIgnoreCase(ConstantUtil.INVALID_GTS_CUSTOMER) || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(invalidTableName)
                        || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(invalidTableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName)) {
                    searchResultbeans = new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + invalidTableName));

                } else {
                    searchResultbeans = new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + invalidTableName));
                }
                tableLogic.setContainerDataSource(searchResultbeans);
                tableOnChange();

                primaryDto.setPropertyName(viewName);
                if (searchResultbeans != null) {
                    if (searchResultbeans.getContainerPropertyIds().contains(ConstantUtil.MODEL_PROPERTY_ID)) {
                        searchResultbeans.removeContainerProperty(ConstantUtil.MODEL_PROPERTY_ID);
                    }
                }
                tableLogic.configureSearchData(searchValues, invalidTableName, moduleDetails, SelectedAccrualsId,false);
            }

            if (!tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                final StplSecurity stplSecurity = new StplSecurity();
                final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
                final Map<String, AppPermission> fieldForecastSalesHM = stplSecurity.getBusinessFieldPermission(userId, primaryDto.getValidation());
                final TableResultCustom tableResultCustom = UISecurityUtil.modifyTableResult(tableColumnArr, tableHeaderArr, fieldForecastSalesHM);
                table.setVisibleColumns(tableResultCustom.getObjResult());
                table.setColumnHeaders(tableResultCustom.getObjResultHeader());
            }
      
                if (tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                    Object[] tableColumnArr1 = null;
                    String[] tableHeaderArr1 = null;
                    if (invalidTableName.equalsIgnoreCase("IvldActualMaster")) {
                        List<Object> list = new ArrayList<Object>(Arrays.asList(tableColumnArr));
                        list.removeAll(Arrays.asList("actualsMasterSid"));
                        tableColumnArr1 = list.toArray();

                        List<String> list1 = new ArrayList<String>(Arrays.asList(tableHeaderArr));
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
                                        && !"LotMaster".equals(VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME))) {
                                    VaadinSession.getCurrent().setAttribute(ConstantUtil.VIEW_SID_NAME, viewNameSid);
                                    InformationPopup infoPopup = new InformationPopup(systemId, String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME)));
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
            table.setWidth(99, UNITS_PERCENTAGE);
            table.addStyleName("filterbar");
            if (!tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
            if (searchResultbeans.size() > ConstantUtil.TAB_VALUE) {
                CommonUIUtils.successNotification("Search Completed");
            } else {
                CommonUIUtils.successNotification("No results found");
            }
            } else {
                if (table.getItemIds().size() > ConstantUtil.TAB_VALUE) {
                    CommonUIUtils.successNotification("Search Completed");
                } else {
                    CommonUIUtils.successNotification("No results found");
                }

            }
            searchCriteria.setCustomDirty(false);
            for (Object propertyId : collapsedColumns) {
                table.setColumnCollapsed(propertyId, true);
            }

            searchCriteria.setCustomDirty(true);
            table.setFilterBarVisible(true);
            table.setFilterDecorator(new ExtDemoFilterDecorator());
            table.setImmediate(true);
            if (primaryDto.getValidation().equals(ConstantUtil.GL_SPACE_BALANCE) || primaryDto.getValidation().equals(ConstantUtil.ACCRUAL_MASTER) || primaryDto.getValidation().equals(ConstantUtil.CPI_INDEX)) {
                table.setFilterGenerator(new FilterGenerator());
            }
            LOGGER.info("Ends btnSearchLogic");
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error(e);

        }

        LOGGER.info("search ends " + invalidTableName);
    }

    @UiHandler("reprocessBtn")
    public void reprocessBtn(Button.ClickEvent event) {
        if (table.getValue() != null && !((Set<?>) table.getValue()).isEmpty()) {
            String reasonForFailure = "";
            boolean isNotMissing = true;
            if (tableName.equalsIgnoreCase("Invalidrecordcount")) {
                try {

                    for (Object se : (Set<Object>) table.getValue()) {
                        Class cls= null;
                           if (invalidTableName.equalsIgnoreCase(ConstantUtil.INVALID_GTS_CUSTOMER) || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(invalidTableName)
                        || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(invalidTableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName) ) {
                                cls = Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + invalidTableName);  
                           }else{
                         cls = Class.forName(ConstantUtil.TABLE_MODEL_PATH + invalidTableName);
                           }
                        java.lang.reflect.Field field = cls.cast(se).getClass().getDeclaredField(ConstantUtil.PROPERTY_ID);
                        field.setAccessible(true);
                        reasonForFailure = String.valueOf(field.get(se));
                        if (StringUtils.isNotBlank(reasonForFailure) && reasonForFailure.equalsIgnoreCase(ConstantUtil.MISSING_REQUIRED_FIELD)) {
                            AbstractNotificationUtils.getErrorNotification("Reprocess", "The selected lines cannot be re-processed as they are missing required fileds");
                            isNotMissing = false;
                            break;
                        }

                    }

                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }

            }
            if (isNotMissing) {
            MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to Reprocess the selected record(s) " + " ?", new MessageBoxListener() {
                /**
                 * Called when a Button has been clicked .
                 *
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    if (buttonId.name().equals("YES")) {

                        try {
                            reprocessButtonLogic();
                            tableLogic.groupChange();

                        } catch (Exception ex) {
                            LOGGER.error(ex.getMessage());
                        }

                    }
                }
            }, ButtonId.YES, ButtonId.NO);
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("Reprocess", "Please select at least one row from the Results list view");
        }
        table.setValue(null);
    }

    public void reprocessButtonLogic() {
        long selectedRecordSid = Long.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.VIEW_NAME)));
        String invalidTable = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME));
        commonLogic.reprocessSelectedRecords(selectedRecordSid, invalidTable);
    }


    public List getItemId() {
        List Item_id = searchResultbeans.getItemIds(0, searchResultbeans.size());
        return Item_id;
    }

}
