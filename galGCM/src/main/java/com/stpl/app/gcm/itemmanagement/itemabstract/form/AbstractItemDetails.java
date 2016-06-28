/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.SummaryDTO;
import com.stpl.app.gcm.itemmanagement.add.logic.SummaryLogic;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic.ItemDetailsTableLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.security.SecurityConstants;
import com.stpl.app.gcm.security.SecurityLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;

/**
 *
 * @author hazi.s
 */
public class AbstractItemDetails extends CustomComponent {

    Panel itemDetailsSummary = new Panel();
    VerticalLayout lay = new VerticalLayout();
    ItemDetailsTableLogic itemdetailstableLogic = new ItemDetailsTableLogic();
    public ExtPagedTable itemdetailstable = new ExtPagedTable(itemdetailstableLogic);
    ItemDetailsTableLogic itemTransferTableLogic = new ItemDetailsTableLogic();
    public ExtPagedTable itemTransferTable = new ExtPagedTable(itemTransferTableLogic);
    Object[] visibleColumn = {Constants.CHECK_RECORD, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "cfp", "ifp", "ps", "rs", "rarCategory", "status", "itemstartdate", "itemenddate"};
    String[] columnHeader = {"", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "CFP Name", Constants.IfpNAME, "PS Name", "RS Name", "RAR Category", "Status", Constants.ITEM_START_DATE, Constants.ITEM_END_DATE};
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    SelectionDTO selection = new SelectionDTO();
    public AbstractLogic logic = AbstractLogic.getInstance();
    public SummaryLogic summaryLogic = new SummaryLogic();
    Button cancelremove = new Button("CANCEL REMOVE");
    Button cancelremoveForTransfer = new Button("CANCEL REMOVE");
    List<SummaryDTO> selectedItemList = new ArrayList<SummaryDTO>();
    List<SummaryDTO> selectedTransferItemList = new ArrayList<SummaryDTO>();
    ExtCustomTable contractExcelTable;
    BeanItemContainer<SummaryDTO> contractExcelResultBean;
    BeanItemContainer<SummaryDTO> container = new BeanItemContainer<SummaryDTO>(SummaryDTO.class);
    BeanItemContainer<SummaryDTO> transferContainer = new BeanItemContainer<SummaryDTO>(SummaryDTO.class);
    final StplSecurity stplSecurity = new StplSecurity();
    Map<String, AppPermission> functionHM = new HashMap<String, AppPermission>();
    private String userId = VaadinSession.getCurrent().getAttribute(Constants.USER_ID).toString();
    public static final Logger LOGGER = Logger.getLogger(AbstractItemDetails.class);

    public AbstractItemDetails(SelectionDTO selection) {
        try {
            this.selection = selection;
            lay.setMargin(true);
            lay.setSpacing(true);
            ConfigureItemTable();
            if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                ConfigureTransferItemTable();
            }
            itemDetailsSummary.setContent(lay);
            setCompositionRoot(itemDetailsSummary);
            itemDetailsSummary.setSizeFull();
        } catch (Exception e) {
           LOGGER.error(e);
        }
    }

    Component ConfigureItemTable() throws FieldGroup.CommitException {
        itemdetailstableLogic.setContainerDataSource(container);
        itemdetailstable.setVisibleColumns(visibleColumn);
        itemdetailstable.setColumnHeaders(columnHeader);
        itemdetailstable.setPageLength(3);
        itemdetailstable.setSelectable(false);
        itemdetailstable.setSizeFull();
        itemdetailstable.setEditable(Boolean.TRUE);
        itemdetailstable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        itemdetailstable.setColumnCheckBox(Constants.CHECK_RECORD, Boolean.TRUE);
        itemdetailstable.setCaption("Current- Item Details");
        for (Object object : itemdetailstable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                itemdetailstable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                itemdetailstable.setConverter(object, new DateToStringConverter());
            }
            if (String.valueOf(object).contains("date")) {
                itemdetailstable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
        lay.addComponent(itemdetailstable);
        HorizontalLayout controls = itemdetailstableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        lay.addComponent(controlLayout);
        HorizontalLayout hlayout = new HorizontalLayout();
        Button export = new Button();
        export.setPrimaryStyleName("link");
        export.setIcon(excelExportImage, "Excel Export");
        if (selection.getButtonMode().equals(ConstantsUtil.EDIT)) {
            cancelremove.setCaption("CANCEL UPDATE");
        } else if (selection.getButtonMode().equals(ConstantsUtil.DELETE)) {
            cancelremove.setCaption("REMOVE");
        } else if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
            cancelremove.setCaption("REMOVE");
        } else if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            cancelremove.setCaption("REMOVE");
        }
        hlayout.addComponent(cancelremove);
        hlayout.addComponent(export);
        lay.addComponent(hlayout);

        itemdetailstable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = check.getValue();

                            if (isCheck) {
                                selectedItemList.add((SummaryDTO) itemId);
                            } else {
                                if (selectedItemList.contains(itemId)) {
                                    selectedItemList.remove(itemId);
                                }
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });
        itemdetailstable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = itemdetailstable.getItemIds();
                for (Object obj : itemList) {
                    SummaryDTO dto = (SummaryDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                    dto.setCheckRecord(event.isChecked());
                    if (event.isChecked()) {
                        selectedItemList.add(dto);
                    } else {
                        selectedItemList.clear();
                    }
                    itemdetailstable.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                }
            }
        });
        cancelremove.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                if (!selectedItemList.isEmpty()) {
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            if (selection.getButtonMode().equals(ConstantsUtil.DELETE) || selection.getButtonMode().equals(ConstantsUtil.EDIT)) {
                                updateRemovedContract(selection.getButtonMode(), ConstantsUtil.SUMMARY);
                            } else if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                                updateRemovedContract(ConstantsUtil.CURRENT_COONTRACT, ConstantsUtil.CURRENT_SUMMARY);
                            }
                            selection.getLookup().callLoadData(selection.getButtonMode());
                            itemdetailstableLogic.loadSetData(selection, false);
                            selection.getLookup().changeToCurrentContract();
                        }

                        @Override
                        public void noMethod() {
                        }
                    }.getConfirmationMessage("Confirmation", "Are you sure you want to remove the selected Contract from the Transfer Item process? It will be removed and added back to the Available List of Contracts in the Current Contract Selection screen.");
                } else {
                    AbstractNotificationUtils.getErrorNotification("Error", "Please select a value to remove. Then try again");
                }
            }
        });
        export.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                try {
                    selection.setOperation("CURRENT SUMMARY");
                    createWorkSheet("Current_Item_Details", itemdetailstable);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                   
                }
            }
        });
        return itemdetailstable;
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {

        List visibleList = Arrays.asList(itemdetailstable.getVisibleColumns()).subList(1, itemdetailstable.getVisibleColumns().length);
        try {
            if (end != 0) {
                List<SummaryDTO> list = summaryLogic.getContractResults(selection, 0, 1000000000);
                ExcelExportforBB.createFileContent(visibleList.toArray(), list, printWriter);
            }
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
        }
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) throws SystemException, PortalException, Exception {
        List<String> visibleList = Arrays.asList(itemdetailstable.getColumnHeaders()).subList(1, itemdetailstable.getVisibleColumns().length);
        List<SummaryDTO> list = null;
        if (resultTable.size() != 0) {
            list = summaryLogic.getContractResults(selection, 0, 1000000000);
        }
        ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), list.size(), this, UI.getCurrent(), moduleName.toUpperCase());
    }

    Component ConfigureTransferItemTable() throws FieldGroup.CommitException {

        itemTransferTableLogic.setContainerDataSource(transferContainer);
        itemTransferTable.setVisibleColumns(visibleColumn);
        itemTransferTable.setColumnHeaders(columnHeader);
        itemTransferTable.setPageLength(3);
        itemTransferTable.setSelectable(false);
        itemTransferTable.setSizeFull();
        itemTransferTable.setEditable(Boolean.TRUE);
        itemTransferTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        itemTransferTable.setColumnCheckBox(Constants.CHECK_RECORD, Boolean.TRUE);
        itemTransferTable.setCaption("Transfer- Item Details");
        for (Object object : itemTransferTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                itemTransferTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                itemTransferTable.setConverter(object, new DateToStringConverter());
            }
            if (String.valueOf(object).contains("date")) {
                itemTransferTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
        lay.addComponent(itemTransferTable);
        HorizontalLayout controls = itemTransferTableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        lay.addComponent(controlLayout);
        HorizontalLayout hlayout = new HorizontalLayout();
        Button export = new Button();
        export.setPrimaryStyleName("link");

        export.setIcon(excelExportImage, "Excel Export");
        if (selection.getButtonMode().equals(ConstantsUtil.EDIT)) {
            cancelremoveForTransfer.setCaption("CANCEL UPDATE");
        } else if (selection.getButtonMode().equals(ConstantsUtil.DELETE)) {
            cancelremoveForTransfer.setCaption("REMOVE");
        } else if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
            cancelremoveForTransfer.setCaption("REMOVE");
        }
        else if(selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)){
           cancelremoveForTransfer.setCaption("REMOVE"); 
        }
        hlayout.addComponent(cancelremoveForTransfer);
        hlayout.addComponent(export);
        lay.addComponent(hlayout);
        itemTransferTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = check.getValue();

                            if (isCheck) {
                                selectedTransferItemList.add((SummaryDTO) itemId);
                            } else {
                                if (selectedTransferItemList.contains(itemId)) {
                                    selectedTransferItemList.remove(itemId);
                                }
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });
        itemTransferTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = itemTransferTable.getItemIds();
                for (Object obj : itemList) {
                    SummaryDTO dto = (SummaryDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                    if (event.isChecked()) {
                        selectedTransferItemList.add(dto);
                    } else {
                        selectedTransferItemList.clear();
                    }
                    itemTransferTable.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                }
            }
        });
        cancelremoveForTransfer.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                if (!selectedTransferItemList.isEmpty()) {
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            updateRemovedContract(ConstantsUtil.TRANSFER_CONTRACT, ConstantsUtil.TRANSFER_SUMMARY);
                            selection.getLookup().callLoadData("TransferContract");
                            itemTransferTableLogic.loadSetData(selection, false);
                            selection.getLookup().changeToTransferContract();
                        }

                        @Override
                        public void noMethod() {
                        }
                    }.getConfirmationMessage("Confirmation", "Are you sure you want to remove the selected Contract from the Transfer Item process? It will be removed and added back to the Available List of Contracts in the Transfer Contract Selection screen.");
                } else {
                    AbstractNotificationUtils.getErrorNotification("Error", "Please select a value to remove. Then try again");
                }
            }
        });
        export.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                try {
                    selection.setOperation("TRANSFER SUMMARY");
                    createWorkSheet("Transfer_Item_Details", itemdetailstable);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                  
                }
            }
        });
        return itemTransferTable;
    }

    public void loadData() throws FieldGroup.CommitException {
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            selection.setOperation("CURRENT SUMMARY");
        }
        itemdetailstableLogic.loadSetData(selection, Boolean.TRUE);
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            selection.setOperation("TRANSFER SUMMARY");
            itemTransferTableLogic.loadSetData(selection, Boolean.TRUE);
        }
    }

    private void setSecurityMap() throws Exception {
        if (selection.getButtonMode().equals(ConstantsUtil.EDIT)) {
            functionHM = stplSecurity.getBusinessFunctionPermission(userId, SecurityConstants.ITEM_UPDATE, SecurityConstants.SUMMARY_ITEM_DETAILS);
            SecurityLogic.isPermitted(functionHM, "cancelremove", cancelremove);
        } else if (selection.getButtonMode().equals(ConstantsUtil.DELETE)) {
            functionHM = stplSecurity.getBusinessFunctionPermission(userId, SecurityConstants.ITEM_REMOVE, SecurityConstants.SUMMARY_ITEM_DETAILS);
            SecurityLogic.isPermitted(functionHM, "cancelremove", cancelremove);
        } else if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
            functionHM = stplSecurity.getBusinessFunctionPermission(userId, SecurityConstants.ITEM_TRANSFER, SecurityConstants.SUMMARY_ITEM_DETAILS);
            SecurityLogic.isPermitted(functionHM, "cancelremove", cancelremove);
            SecurityLogic.isPermitted(functionHM, "cancelremoveForTransfer", cancelremoveForTransfer);
        }
    }

    public BeanItemContainer getIemDetailsContainer(Boolean transfer) {
        if (transfer) {
            return transferContainer;
        } else {
            return container;
        }
    }

    public void updateRemovedContract(final Object tobeUpdated, final Object whereOperation) {
        List updateInput = new ArrayList();
        updateInput.add("OPERATION");
        updateInput.add(tobeUpdated);
        updateInput.add(selection.getSessionId());
        updateInput.add(whereOperation);
        ItemQueries.itemUpdate(updateInput, "Abstract Mass update"); // To Change The operation from Current transfer to Current summary
    }
}
