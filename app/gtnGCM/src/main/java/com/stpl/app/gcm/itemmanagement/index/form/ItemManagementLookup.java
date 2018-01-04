/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.index.form;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.add.form.AddContractSelection;
import com.stpl.app.gcm.itemmanagement.add.form.Summary;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.index.logic.ItemLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.itemmanagement.remove.form.RemoveContractSelection;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.summary.SummaryLookUp;
import com.stpl.app.gcm.itemmanagement.remove.logic.RemoveItemLogic;
import com.stpl.app.gcm.itemmanagement.transfer.TransferContract;
import com.stpl.app.gcm.itemmanagement.transfer.CurrentContractTransfer;
import com.stpl.app.gcm.itemmanagement.transfer.ItemSelection;
import com.stpl.app.gcm.itemmanagement.update.UpdateItem;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar
 */
public class ItemManagementLookup extends CustomWindow {

    private TabSheet mainTab = new TabSheet();
    private int tabPosition;
    private final List<Integer> tabList = new ArrayList<>();
    private List<ItemIndexDto> itemList = new ArrayList<>();
    private final SelectionDTO selection;
    public Button closeBtn = new Button("CLOSE");
    private final Button nextBtn = new Button("NEXT");
    private final Button previousBtn = new Button("PREVIOUS");
    private final Button removeBtn = new Button("REMOVE");
    private final Button addBtn = new Button("PROCESS");
    private final Button editBtn = new Button("UPDATE");
    private final Button transferBtn = new Button("TRANSFER");
    private final AddContractSelection contractSelection = new AddContractSelection();
    private final Summary itemsummary = new Summary(itemList);
    private UpdateItem updateitem;
    private RemoveContractSelection removeContractSelection;
    private final ItemLogic logic = new ItemLogic();
    private CurrentContractTransfer contractTransfer;
    private SummaryLookUp removeSummary;
    private TransferContract transferContract;
    private ItemSelection itemSelectionTransfer;
    private Integer lasttabPosition = 0;
    private boolean valueChange = false;
    public static final Logger LOGGER = LoggerFactory.getLogger(ItemManagementLookup.class);
    private final List<Integer> addedTabList = new ArrayList<>();
    private boolean addSummaryFlag = false;
    private final SessionDTO session = new SessionDTO();
    private SelectionDTO transferSelection = new SelectionDTO();

    public ItemManagementLookup(List<ItemIndexDto> itemList, SelectionDTO selection) {
        super(selection.getWindowName());
        this.selection = selection;
        this.itemList = itemList;
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(0);
        setPositionY(0);
        setMinimizeToTray();
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        configureFields();
    }

    private void configureFields() {
        mainTab = new TabSheet();
        VerticalLayout baseLayout = new VerticalLayout();
        baseLayout.setSpacing(true);
        baseLayout.setMargin(true);
        mainTab.addStyleName(ValoTheme.TABSHEET_FRAMED);
        mainTab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        mainTab.markAsDirty();
        selection.setLookup(this);
        mainTab.markAsDirtyRecursive();
        removeSummary = new SummaryLookUp(itemList, selection);
        if (!selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            insertToTempTable();
        }
        if (selection.isIsIFP()) {
        } else if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
            addBtn.setVisible(false);
            mainTab.addTab(contractSelection.getContent(itemList, selection), "Contract Selection", null, 0);
            removeSummary.getContent(itemList, selection);
            mainTab.addTab(itemsummary.getContent(itemList, selection), StringConstantsUtil.SUMMARY_FIELD, null, 1);
        } else if (selection.getButtonMode().equals(ConstantsUtil.DELETE)) {
            removeContractSelection = new RemoveContractSelection(selection, itemList);
            mainTab.addTab(removeContractSelection.getContent(), Constants.CURRENT_CONTRACT_LABEL, null, 0);
            removeSummary.getContent(itemList, selection);
            removeSummary.configureSecurityPermissions(ConstantsUtil.DELETE);
            mainTab.addTab(removeSummary, StringConstantsUtil.SUMMARY_FIELD, null, 1);
        } else if (selection.getButtonMode().equals(ConstantsUtil.EDIT)) {
            updateitem = new UpdateItem(selection, itemList);
            mainTab.addTab(updateitem.getContent(), Constants.CURRENT_CONTRACT_LABEL, null, 0);
            removeSummary.getContent(itemList, selection);
            removeSummary.configureSecurityPermissions(ConstantsUtil.EDIT);
            mainTab.addTab(removeSummary, StringConstantsUtil.SUMMARY_FIELD, null, 1);
        } else if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
            try {
                transferBtn.setVisible(false);
                transferSelection = selection.clone();
                contractTransfer = new CurrentContractTransfer(selection, itemList);
                transferContract = new TransferContract(transferSelection, itemList);
                mainTab.addTab(contractTransfer.getContent(), Constants.CURRENT_CONTRACT_LABEL, null, 0);
                mainTab.addTab(transferContract.getContent(), "Transfer Contract", null, 1);
                removeSummary.getContent(itemList, selection);
                removeSummary.configureSecurityPermissions(ConstantsUtil.TRANSFER);
                mainTab.addTab(removeSummary, StringConstantsUtil.SUMMARY_FIELD, null, NumericConstants.TWO);
            } catch (CloneNotSupportedException ex) {
                LOGGER.debug("",ex);
            }
        } else if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            try {
                transferSelection = selection.clone();
                contractTransfer = new CurrentContractTransfer(selection, itemList);
                transferContract = new TransferContract(transferSelection, itemList);
                itemSelectionTransfer = new ItemSelection(selection, itemList);
                mainTab.addTab(itemSelectionTransfer.getContent(), "Item Selection", null, 0);
                mainTab.addTab(contractTransfer.getContent(), Constants.CURRENT_CONTRACT_LABEL, null, 1);
                mainTab.addTab(transferContract.getContent(), "Transfer Contract", null, NumericConstants.TWO);
                removeSummary.getContent(itemList, selection);
                removeSummary.configureSecurityPermissions(ConstantsUtil.PROJECTIONTRANSFER);
                mainTab.addTab(removeSummary, StringConstantsUtil.SUMMARY_FIELD, null, NumericConstants.THREE);
            } catch (CloneNotSupportedException ex) {
                LOGGER.debug("",ex);
            }

        }

        mainTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                if (!valueChange) {
                    final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                    try {
                        tabPosition = event.getTabSheet().getTabPosition(tab);
                        if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER) && lasttabPosition == 0 && tabPosition == 1) {
                            saveDetailsToTempTable();
                        }

                        if (!tabList.contains(tabPosition)) {
                            tabLazyLoad(tabPosition);
                        }
                        if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
                            itemsummary.loadItemResults();
                        }
                        tabChangeSubmitCheck();
                        lasttabPosition = tabPosition;
                        enableActionButton(tabPosition);
                        if (addSummaryFlag) {
                            enableActionButton(1);
                            addSummaryFlag = false;
                        }

                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }
                }
            }
        });

        mainTab.setSizeFull();
        baseLayout.addComponent(mainTab);
        baseLayout.addComponent(getActionButton());
        previousBtn.setVisible(false);
        transferBtn.setVisible(false);
        addBtn.setVisible(false);
        removeBtn.setVisible(false);
        editBtn.setVisible(false);
        setContent(baseLayout);
        nextBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                tabChangeLogic();
            }
        });
        previousBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnPreviousLogic();
            }
        });
        addBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!itemsummary.isItemSelected()) {
                    addButtonLogic();
                } else {
                    AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS_SUBMITTED, "No Valid records found in Add Item Results.\n Please submit any record to Continue.");
                }
            }
        });
        editBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (removeSummary.getItemDetails(Boolean.FALSE)==null || (removeSummary.getItemDetails(Boolean.FALSE) != null && removeSummary.getItemDetails(Boolean.FALSE).size() == 0)) {
                    AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS_SUBMITTED, StringConstantsUtil.NO_VALID_RECORDS);
                    return;
                }
             
                    editButtonLogic();
               
            }
           
        });
        removeBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
               
                if (removeSummary.getItemDetails(Boolean.FALSE) == null || (removeSummary.getItemDetails(Boolean.FALSE) != null && removeSummary.getItemDetails(Boolean.FALSE).size() == 0)) {
                    AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS_SUBMITTED, StringConstantsUtil.NO_VALID_RECORDS_FOUND_IN_CURRENT_ITEM);
                    return;
                }
                    removeButtonLogic();
                
            }
        });
        transferBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                transferButtonLogic();
            }
        });

    }

    private void tabLazyLoad(int tabPosition) {
        LOGGER.debug("tabLazyLoad inside");

        if (tabPosition == 1) {
            if (selection.isIsIFP()) {
            } else if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
                mainTab.replaceComponent(itemsummary, itemsummary.getContent(itemList, selection));
                addSummaryFlag = true;
            } else {
                mainTab.replaceComponent(removeSummary, removeSummary);
            }
            tabList.add(tabPosition);
        }
        LOGGER.debug("tabLazyLoad outside");

    }

    public void enableActionButton(int tabPosition) {
        if (tabPosition == 0) {
            previousBtn.setVisible(false);
            transferBtn.setVisible(false);
            nextBtn.setVisible(true);
            nextBtn.setVisible(true);
            if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
                addBtn.setVisible(false);
            }

        } else if (tabPosition == 1) {
            previousBtn.setVisible(true);
            transferBtn.setVisible(false);
            if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                nextBtn.setVisible(true);
            } else if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
                nextBtn.setVisible(false);
                addBtn.setVisible(true);
                previousBtn.setVisible(true);
                addBtn.setVisible(CommonLogic.isButtonVisibleAccess("addBtn", itemsummary.getFunctionHM()));
                closeBtn.setVisible(CommonLogic.isButtonVisibleAccess(StringConstantsUtil.CLOSE_BTN, itemsummary.getFunctionHM()));
            } else if (selection.getButtonMode().equals(ConstantsUtil.EDIT)) {
                nextBtn.setVisible(false);
                editBtn.setVisible(true);
                editBtn.setVisible(CommonLogic.isButtonVisibleAccess("updatebtn", removeSummary.getFunctionHM()));
                closeBtn.setVisible(CommonLogic.isButtonVisibleAccess("closeBtn", removeSummary.getFunctionHM()));
            } else {
                nextBtn.setVisible(false);
                removeBtn.setVisible(true);
            }
        } else if (tabPosition == NumericConstants.TWO) {
            if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
                previousBtn.setVisible(true);
                transferBtn.setVisible(true);
                transferBtn.setVisible(CommonLogic.isButtonVisibleAccess("transferBtn", removeSummary.getFunctionHM()));
                nextBtn.setVisible(false);
            }
            if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                previousBtn.setVisible(true);
                transferBtn.setVisible(false);
                nextBtn.setVisible(true);
            }
        } else {
            previousBtn.setVisible(true);
            transferBtn.setVisible(true);
            transferBtn.setVisible(CommonLogic.isButtonVisibleAccess("transferBtn", removeSummary.getFunctionHM()));
            closeBtn.setVisible(CommonLogic.isButtonVisibleAccess("closeBtn", removeSummary.getFunctionHM()));
            nextBtn.setVisible(false);
        }
    }

    HorizontalLayout getActionButton() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(previousBtn);
        horizontalLayout.addComponent(closeBtn);
        horizontalLayout.addComponent(transferBtn);
        horizontalLayout.addComponent(addBtn);
        horizontalLayout.addComponent(removeBtn);
        horizontalLayout.addComponent(nextBtn);
        horizontalLayout.addComponent(editBtn);
        horizontalLayout.setSpacing(true);
        horizontalLayout.setMargin(true);
        return horizontalLayout;
    }

    public void btnPreviousLogic() {
        if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
            mainTab.setSelectedTab(0);
        } else if (tabPosition != 0) {
            mainTab.setSelectedTab(tabPosition - 1);
        }
    }

    protected void btnNextLogic() {
        if (tabPosition == NumericConstants.TWO) {
            transferContract.contractSearch.submitLogic();
            if (transferContract.contractSearch.isnext) {
                mainTab.setSelectedTab(tabPosition + 1);
            }
        } else if (tabPosition == 0 || tabPosition == 1) {
            mainTab.setSelectedTab(tabPosition + 1);
        }
    }

    public void addButtonLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                selection.setReset(true);

                List input = getInputForInsert();
                List ccpInsert = new ArrayList();
                ccpInsert.add(itemList.get(0).getSystemId());
                ccpInsert.add(selection.getSessionId());
                ItemQueries.itemUpdate(input, "IFP Add Insert");
                ItemQueries.itemUpdate(input, "PS Add Insert");
                ItemQueries.itemUpdate(input, "RS Add Insert");
                AbstractLogic.callProcedure("PRC_CCP_POPULATION", new String[]{});
                AbstractLogic.callProcedure("PRC_ACTUAL_DETAILS_POPULATION", new String[]{});
                close();
                List list = getCloneProjectionParametrs(ConstantsUtil.SUMMARY);
                CommonLogic logic = new CommonLogic();
                String builderType = logic.getRelationBuilderType((Integer) list.get(0));

                if (!builderType.equals(StringUtils.EMPTY) && !builderType.equalsIgnoreCase(StringConstantsUtil.AUTOMATIC_LABEL)) {
                    AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_PROJECTION_CREATED, StringConstantsUtil.SOME_ERROR_IN_CREATING_PROJECTION
                            + StringConstantsUtil.CHECK_WHETHER_THE_SELECTED_PROJECTION_HAS_RELATION);
                } else {
                    List<String> tempTransferList = logic.generateNewProjection(String.valueOf(selection.getUserId()), String.valueOf(selection.getSessionId()), (Integer) list.get(0), (List<String>) list.get(1), Boolean.FALSE, Boolean.TRUE, session);
                    int newProjectionId = logic.getNewProjectionId();
                    String msgContent = "The selected Item has been added successfully";

                    if (tempTransferList != null && !tempTransferList.isEmpty()) {
                        msgContent = msgContent + StringConstantsUtil.NEW_PROJECTION_CREATED_WITH_FORECASTING + tempTransferList.get(0)
                                + StringConstantsUtil.AND_PROJECTION_NAME + tempTransferList.get(1) + " ";
                    }
                    Object[] orderedArgs = {list.get(0), newProjectionId, selection.getForecastingType()};
                    AbstractLogic.callProcedure("PRC_FE_ADD_EVENT", orderedArgs);
                    MessageBox.showPlain(Icon.INFO, "Added Successfully", msgContent, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed.
                         */
                        @SuppressWarnings("PMD")
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            close();
                        }
                    }, ButtonId.OK);
                }
            }
            
            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, "Are you sure you want to Add the Selected Item to the selected Contract(s)?");

    }
    
    private void editButtonLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {

                selection.setReset(true);
                List input = new ArrayList();
                input.add(selection.getSessionId());
                ItemQueries.itemUpdate(input, "IFP Upadte query");
                ItemQueries.itemUpdate(input, "PS Upadte query");
                ItemQueries.itemUpdate(input, "RS Upadte query");
                close();
                List list = getCloneProjectionParametrs(ConstantsUtil.SUMMARY);
                CommonLogic logic = new CommonLogic();
                String builderType = logic.getRelationBuilderType((Integer) list.get(0));
                if (!builderType.equals(StringUtils.EMPTY) && !builderType.equalsIgnoreCase("Automatic")) {
                    AbstractNotificationUtils.getErrorNotification("No Projection Created", "There is some error in creating projection. "
                            + "\n kindly check whether the selected Projection has relationship that is set to Automatic");
                } else {
                    List<String> tempTransferList = logic.createNewProjection((Integer) list.get(0), (List<String>) list.get(1), selection.getSessionDTO());
                    int projectionId = logic.getNewProjectionId();
                    String forecastType = logic.getForecastingType();
                    List updateInput = new ArrayList();
                    if ("Mandated".equalsIgnoreCase(forecastType)) {
                        updateInput.add("M_SALES_PROJECTION");
                    } else {
                        updateInput.add("NM_SALES_PROJECTION");
                    }
                    updateInput.add(projectionId);
                    updateInput.add(selection.getSessionId());
                    updateInput.add(ConstantsUtil.SUMMARY);
                    ItemQueries.itemUpdate(updateInput, "Update sales in projection");
                    String msgContent = "The selected Item has been Updated successfully";
                    if (tempTransferList != null && !tempTransferList.isEmpty()) {
                        msgContent = msgContent + StringConstantsUtil.NEW_PROJECTION_CREATED_WITH_FORECASTING + tempTransferList.get(0)
                                + StringConstantsUtil.AND_PROJECTION_NAME + tempTransferList.get(1) + " ";
                    }
                    MessageBox.showPlain(Icon.INFO, "Updated Successfully", msgContent, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed.
                         */
                        @SuppressWarnings("PMD")
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            close();
                        }
                    }, ButtonId.OK);
                }
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, "Are you sure you want to Update the Selected Item (or Items) on the selected Contract(s)");
    }

    private void removeButtonLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                selection.setReset(true);
                List input = new ArrayList();
                input.add(selection.getSessionId());
                ItemQueries.itemUpdate(input, "Item Remove1");
                close();
                List list = getCloneProjectionParametrs(ConstantsUtil.SUMMARY);
                CommonLogic logic = new CommonLogic();
                String builderType = logic.getRelationBuilderType((Integer) list.get(0));
                if (!builderType.equals(StringUtils.EMPTY) && !builderType.equalsIgnoreCase("Automatic")) {
                    AbstractNotificationUtils.getErrorNotification("No Projection Created", "There is some error in creating projection. "
                            + "\n kindly check whether the selected Projection has relationship that is set to Automatic");
                } else {
                    List<String> tempTransferList = logic.createNewProjection((Integer) list.get(0), (List<String>) list.get(1), selection.getSessionDTO());
                    int projectionId = logic.getNewProjectionId();
                    String forecastType = logic.getForecastingType();
                    List updateInput = new ArrayList();
                    if ("Mandated".equalsIgnoreCase(forecastType)) {
                        updateInput.add("M_SALES_PROJECTION");
                    } else {
                        updateInput.add("NM_SALES_PROJECTION");
                    }
                    updateInput.add(projectionId);
                    updateInput.add(selection.getSessionId());
                    updateInput.add(ConstantsUtil.SUMMARY);
                    ItemQueries.itemUpdate(updateInput, "Remove sales in projection");
                    String msgContent = "The selected Item has been Removed successfully";
                    if (tempTransferList != null && !tempTransferList.isEmpty()) {
                        msgContent = msgContent + StringConstantsUtil.NEW_PROJECTION_CREATED_WITH_FORECASTING + tempTransferList.get(0)
                                + StringConstantsUtil.AND_PROJECTION_NAME + tempTransferList.get(1) + " ";
                    }
                    MessageBox.showPlain(Icon.INFO, "Removed Successfully", msgContent, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed.
                         */
                        @SuppressWarnings("PMD")
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            close();
                        }
                    }, ButtonId.OK);
                }
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, "Are you sure you want to Remove the Selected Items in the selected Contract(s)?");
    }

    private void transferButtonLogic() {
        if (removeSummary.getItemDetails(Boolean.FALSE) != null && removeSummary.getItemDetails(Boolean.FALSE).size() > 0) {
            if (removeSummary.getItemDetails(Boolean.TRUE) != null && removeSummary.getItemDetails(Boolean.TRUE).size() == 0) {
                AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS_SUBMITTED, StringConstantsUtil.NO_VALID_RECORDS_FOUND_IN_CURRENT_ITEM);
                return;
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS_SUBMITTED, StringConstantsUtil.NO_VALID_RECORDS_FOUND_IN_CURRENT_ITEM);
            return;
        }
        
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    selection.setReset(true);
                    session.setProcessName("Item Management");
                    List input = new ArrayList();
                    input.add(selection.getSessionId());
                    input.add(logic.getItemIds(itemList));
                    input.add(selection.getUserId());
                    ItemQueries.itemUpdate(input, "TransferIFP");
                    ItemQueries.itemUpdate(input, "TransferPS");
                    ItemQueries.itemUpdate(input, "TransferRS");
                    List inputForCurrent = new ArrayList();
                    inputForCurrent.add(selection.getSessionId());
                    ItemQueries.itemUpdate(inputForCurrent, "UPDATING END DATE FOR CURRENT CONTRACT");
                    AbstractLogic.callProcedure("PRC_CCP_POPULATION", new String[]{});
                    AbstractLogic.callProcedure("PRC_ACTUAL_DETAILS_POPULATION", new String[]{});
                    List correntTransfer = getCloneProjectionParametrs(ConstantsUtil.CURRENT_SUMMARY);
                    List transferContract = getCloneProjectionParametrs(ConstantsUtil.TRANSFER_SUMMARY);
                    CommonLogic logic = new CommonLogic();
                    int fromProjection = Integer.valueOf(correntTransfer.get(0).toString());
                    int toProjection = Integer.valueOf(transferContract.get(0).toString());                    
                    session.setFromProjectionId(fromProjection);
                    session.setToProjectionId(toProjection);
                    List<String> tempTransferList = new ArrayList<>();
                    List<String> tempTransferList1 = new ArrayList<>();
                    if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
                        tempTransferList = logic.generateNewProjection(String.valueOf(selection.getUserId()), String.valueOf(selection.getSessionId()), (Integer) correntTransfer.get(0), (List<String>) correntTransfer.get(1), Boolean.FALSE, Boolean.FALSE, session);
                    }
                    if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                        tempTransferList = logic.copyProjection(fromProjection, false, null, null, null, session);
                    }

                    int transferFromProjection = Integer.parseInt(tempTransferList.get(NumericConstants.TWO).toString());

                    if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
                        tempTransferList1 = logic.generateNewProjection(String.valueOf(selection.getUserId()), String.valueOf(selection.getSessionId()), toProjection, (List<String>) transferContract.get(1), Boolean.FALSE, Boolean.FALSE, session);
                    }
                    if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                        tempTransferList1 = logic.copyProjection(toProjection, false, null, null, null, session);
                    }
                    int transferToProjection = Integer.parseInt(tempTransferList1.get(NumericConstants.TWO));
                    List inputForInputTable = getTransferInput(fromProjection, transferFromProjection, toProjection, transferToProjection);
                    ItemQueries.itemUpdate(inputForInputTable, "Transfer Input table Insert");
                    if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
                        CommonLogic.callPrcFeSalesTransfer(String.valueOf(selection.getSessionId()));
                    }
                    if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                        CommonLogic.callPrcFeProjectionDetailsTransfer(String.valueOf(selection.getSessionId()));
                    }

                    close();
                    String msgContent = "The selected Item has been Transfered successfully";
                    if (!tempTransferList.isEmpty()) {
                        String forecastType;
                        if (tempTransferList.get(0).equals(tempTransferList1.get(0))) {
                            forecastType = tempTransferList.get(0);
                        } else {
                            forecastType = tempTransferList.get(0) + " , " + tempTransferList1.get(0);
                        }
                        msgContent = msgContent + StringConstantsUtil.NEW_PROJECTION_CREATED_WITH_FORECASTING + forecastType
                                + StringConstantsUtil.AND_PROJECTION_NAME + tempTransferList1.get(1) + " ," + tempTransferList.get(1);
                    }
                    MessageBox.showPlain(Icon.INFO, "Transfered Successfully", msgContent, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed.
                         */
                        @SuppressWarnings("PMD")
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            close();
                        }
                    }, ButtonId.OK);
                   }

                @Override
                public void noMethod() {
                    return;
                }
            }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, "Are you sure you want to Transfer the Selected Item from the selected Contract(s)to the selected Contract(s)?");
    
    }

    private List getInputForInsert() {
        List input = new ArrayList();
        input.add(selection.getUserId());
        input.add(selection.getSessionId());
        return input;
    }

    public void closeBtnLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                close();
                List input = new ArrayList();
                input.add(selection.getSessionId());
                ItemQueries.itemUpdate(input, "Close button delete");
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, "Are you sure you want to close out ?  No values will be saved.");

    }

    public void changeTab() throws FieldGroup.CommitException {
        if ((selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER) && tabPosition != 0)
                || (!selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER) && (tabPosition == 0
                || tabPosition == 1 || tabPosition == NumericConstants.TWO))) {

            mainTab.setSelectedTab(tabPosition + 1);
            removeSummary.loadSummaryTable();
            ItemLogic.getIdAndForecastingType(selection.getTabSelection(), selection);
        }
    }

    public void changeToCurrentContract() {
        if (tabPosition == 0 || tabPosition == 1 || tabPosition == NumericConstants.TWO) {
            mainTab.setSelectedTab(0);
        }
    }

    public void changeToTransferContract() {
        if (tabPosition == 0 || tabPosition == 1 || tabPosition == NumericConstants.TWO) {
            mainTab.setSelectedTab(1);
        }
    }

    public void insertToTempTable() {

        Thread t = new Thread() {
            @Override
            public void run() {
                synchronized (this) {
                    List input = new ArrayList();
                    if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
                        input.add(AbstractLogic.getItemIds(itemList));
                        input.add(selection.getSessionId());
                        input.add(selection.getButtonMode());
                        input.add(ConstantsUtil.ITEM_ADD);
                        input.add(AbstractLogic.getItemIds(itemList));
                        ItemQueries.itemUpdate(input, "Contract Insert Add");
                    } else if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {

                        input.add(selection.getSessionId());
                        input.add(ConstantsUtil.CURRENT_COONTRACT);
                        input.add(ConstantsUtil.ITEM_TRANSFER);
                        input.add(AbstractLogic.getItemIds(itemList));
                        ItemQueries.itemUpdate(input, "Insert Contract For Search1");
                        List input1 = new ArrayList();
                        input1.add(selection.getSessionId());
                        input1.add(ConstantsUtil.TRANSFER_CONTRACT);
                        input.add(ConstantsUtil.ITEM_TRANSFER);
                        ItemQueries.itemUpdate(input1, "Insert Contract For Transfer");
                    } else {

                        input = AbstractLogic.getResultsInput(selection);
                        if (selection.getButtonMode().equals(ConstantsUtil.EDIT)) {
                            input.add(ConstantsUtil.ITEM_UPDATE);
                        } else {
                            input.add(ConstantsUtil.ITEM_REMOVE);
                        }
                        input.add(AbstractLogic.getItemIds(itemList));
                        ItemQueries.itemUpdate(input, "Insert Contract For Search1");
                    }
                    notify();
                }
            }
        };
        t.start();
        selection.addToThreadList(t);

    }

    public static void waitForSave(SelectionDTO selection) {
        LOGGER.debug("Inside wait For save Method");
        for (Thread saveThread : selection.getThreadList()) {
            synchronized (saveThread) {
                if (saveThread.isAlive()) {
                    try {
                        saveThread.wait();
                    } catch (InterruptedException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        }
        selection.getThreadList().clear();
        LOGGER.debug("End of wait For save Method");
    }

    public List getCloneProjectionParametrs(final String operation) {
        final List finalList = new ArrayList();
        TabSelectionDTO selectionDTO = new TabSelectionDTO();
        selectionDTO.setSessionID(selection.getSessionId());
        selectionDTO.setOperation(operation);
        selectionDTO.setItemList(itemList);
        RemoveItemLogic.getIdAndForecastingType(selectionDTO, selection);
        finalList.add(selectionDTO.getSummaryProjectionId());
        List<String> itemIdList = new ArrayList<>();
        for (ItemIndexDto dto : itemList) {
            itemIdList.add(dto.getSystemId());
        }
        finalList.add(itemIdList);
        return finalList;
    }

    private Boolean tabChangeLogic() {
        boolean isNext = false;
        if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
            isNext = contractSelection.submitButtonLogic();
        } else if (selection.getButtonMode().equals(ConstantsUtil.EDIT)) {
            isNext = updateitem.submitLogic();
        } else if (selection.getButtonMode().equals(ConstantsUtil.DELETE)) {
            isNext = removeContractSelection.submitLogic();
        } else if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
            if (tabPosition == 0) {
                isNext = contractTransfer.submitLogic();
            } else {
                isNext = transferContract.submitLogic();
            }
        } else if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            switch (tabPosition) {
                case 0:
                    isNext = true;
                    break;
                case 1:
                    isNext = true;
                    break;
                case NumericConstants.TWO:
                    if (!transferContract.selectedItemList.isEmpty()) {
                        AbstractNotificationUtils.getErrorNotification("No Item information selected",
                                "Please select a contract to transfer the selected Item to. Then select a Status, and an Item Start Date");
                    } else {
                        MessageBox.showPlain(Icon.ERROR, StringConstantsUtil.NO_ITEM_INFORMATION_SUBMITTED, "Please submit a contract to transfer the selected Item to. ", ButtonId.OK);
                    }
                    break;
                default:
                    break;
            }
        }
        if (isNext) {
            if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER) && tabPosition == 0) {
                saveDetailsToTempTable();
            }
            btnNextLogic();
        } else {
            valueChange = true;
            if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                if (lasttabPosition == 1) {
                    mainTab.setSelectedTab(contractTransfer);
                } else if (lasttabPosition == NumericConstants.TWO) {
                    mainTab.setSelectedTab(transferContract);
                }
            } else if (lasttabPosition == 0) {
                mainTab.setSelectedTab(contractTransfer);
            } else if (lasttabPosition == 1) {
                mainTab.setSelectedTab(transferContract);
            }

            valueChange = false;
        }
        return isNext;
    }

    private List getTransferInput(int fromProjection, int transferFromProjection, int toProjection, int transferToProjection) {
        List input = new ArrayList();
        List<String> fromItemList = new ArrayList<>();
        input.add(fromProjection);
        input.add(transferFromProjection);
        input.add(toProjection);
        input.add(transferToProjection);
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
            input.add(AbstractLogic.getItemIdsAsString(itemList));
        }
        if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            for (int i = 0; i < itemList.size(); i++) {
                fromItemList.add(itemList.get(i).getSystemId());
            }
            input.add(CommonLogic.generateCustomerMappings(fromItemList, itemSelectionTransfer.mappingItems()));
        }
        input.add(contractTransfer.isRemoveProjectionBooleanVal());
        input.add(selection.getSessionId());
        input.add(selection.getSessionId());
        return input;
    }

    private void forceTabRefresh(int tab) {

        valueChange = true;
        mainTab.setSelectedTab(lasttabPosition);
        tabPosition = lasttabPosition;
        valueChange = false;
        mainTab.removeTab(mainTab.getTab(tab));
        mainTab.addTab(removeSummary, StringConstantsUtil.SUMMARY_FIELD, null, tab);
    }

    private void tabChangeSubmitCheck() {
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
            List input = new ArrayList();
            input.add(selection.getSessionId());

            if (lasttabPosition == 0) {
                input.add(ConstantsUtil.CURRENT_SUMMARY);
            } else if (lasttabPosition == 1) {
                input.add(ConstantsUtil.TRANSFER_SUMMARY);
            }
            List<Object[]> list = ItemQueries.getItemData(input, "tabChangeCheck", null);
            if (AbstractLogic.getCount(list) == 0) {
                if (!addedTabList.contains(tabPosition)) {

                    forceTabRefresh(NumericConstants.TWO);

                    if (lasttabPosition == 0) {
                        MessageBox.showPlain(Icon.ERROR, StringConstantsUtil.NO_ITEM_INFORMATION_SUBMITTED, StringConstantsUtil.PLEASE_SUBMIT_A_CONTRACT_FROM, ButtonId.OK);
                    } else if (lasttabPosition == 1) {
                        MessageBox.showPlain(Icon.ERROR, StringConstantsUtil.NO_ITEM_INFORMATION_SUBMITTED, "Please submit a contract to transfer the selected Item to. ", ButtonId.OK);
                    }
                }
            } else {
                addedTabList.add(lasttabPosition);
            }
        } else if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            if (tabPosition >= NumericConstants.TWO) {
                transferContract.contractSearch.setTransferSalesString(String.valueOf(contractTransfer.contractSearch.transferSales.getValue()));
            }

            if (lasttabPosition == 0) {

                if (!itemSelectionTransfer.checkTransferedItems()) {
                    forceTabRefresh(NumericConstants.THREE);
                    MessageBox.showPlain(Icon.ERROR, "No Items transfered",
                            "Please select and transfer Items in Item Selection tab ", ButtonId.OK);
                } else if (!isMatchedAllItems()) {
                    forceTabRefresh(NumericConstants.THREE);
                    MessageBox.showPlain(Icon.ERROR, "All Items transfered",
                            " Please complete the matching of all Items in the ‘Selected Items’ list view before you proceed. with OK command button", ButtonId.OK);
                } else if (!addedTabList.contains(1) && (tabPosition == NumericConstants.TWO || lasttabPosition == NumericConstants.THREE)) {
                    forceTabRefresh(NumericConstants.THREE);
                    MessageBox.showPlain(Icon.ERROR, StringConstantsUtil.NO_ITEM_INFORMATION_SUBMITTED, "Please submit a contract to transfer the selected Item from. ", ButtonId.OK);
                } else {
                    addedTabList.add(lasttabPosition);
                }
            } else {
                List input = new ArrayList();
                input.add(selection.getSessionId());
                if (lasttabPosition == 1) {
                    input.add(ConstantsUtil.CURRENT_SUMMARY);
                } else if (lasttabPosition == NumericConstants.TWO) {
                    input.add(ConstantsUtil.TRANSFER_SUMMARY);
                }
                List<Object[]> list = ItemQueries.getItemData(input, "tabChangeCheckProjTransfer", null);
                if (AbstractLogic.getCount(list) == 0 && lasttabPosition != NumericConstants.TWO) {
                    if (!addedTabList.contains(tabPosition)) {
                        forceTabRefresh(NumericConstants.THREE);
                        if (lasttabPosition == 1) {
                            MessageBox.showPlain(Icon.ERROR, StringConstantsUtil.NO_ITEM_INFORMATION_SUBMITTED, "Please submit a contract to transfer the selected Item from. ", ButtonId.OK);
                        }
                    }
                } else {
                    addedTabList.add(lasttabPosition);
                }
            }
        }
    }

    public void callLoadData(String variable) {
        if (variable.equals(ConstantsUtil.DELETE)) {
            removeContractSelection.loadSetDataCall();
        } else if (variable.equals(ConstantsUtil.EDIT)) {
            updateitem.loadSetDataCall();
        } else if (variable.equals(ConstantsUtil.TRANSFER) || variable.equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            contractTransfer.loadSetDataCall();
        } else if (variable.equals("TransferContract")) {
            transferContract.loadSetDataCall();
        }
    }

    public void saveDetailsToTempTable() {
        if (itemSelectionTransfer.checkTransferedItems()) {
            try {
                List input = new ArrayList();
                input.add(selection.getSessionId());
                input.add(ConstantsUtil.CURRENT_COONTRACT);
                input.add(selection.getButtonMode());
                input.add(AbstractLogic.getItemIds(itemList));
                ItemQueries.itemUpdate(input, "InsertContractDataForProjTransfer");
                input = new ArrayList();
                input.add(selection.getSessionId());
                input.add(ConstantsUtil.TRANSFER_CONTRACT);
                input.add(selection.getButtonMode());
                itemSelectionTransfer.getItemIdsFromTrnasferContainer();
                ItemQueries.itemUpdate(input, "InsertContractDataForProjTransferSearch");

            } catch (Exception e) {
                LOGGER.error("",e);
            }
        }
    }

    Boolean isMatchedAllItems() {
        List<String> sidList = itemSelectionTransfer.getFromIdCount();
        List<String> transferredCount = itemSelectionTransfer.getTransferredCount();
        Collections.sort(sidList);
        Collections.sort(transferredCount);
        if (sidList.equals(transferredCount)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
