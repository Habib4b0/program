/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.gcm.promotetptocontract.logic.CurrentContractTableLogic;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.ui.UI;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;

/**
 *
 * @author alok.v
 */
public class SummaryTPDetails extends CustomComponent implements View {

    /**
     * View name for navigation.
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferComponents.class);
    
    private static final BooleanConstant BOOLEAN_CONSTANT = new BooleanConstant();
    
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    private SessionDTO session;
    @UiField("currentTradingPartnerTableLayout")
    public VerticalLayout currentTradingPartnerTableLayout;
    @UiField("transferTradingPartnerTableLayout")
    public VerticalLayout transferTradingPartnerTableLayout;
    @UiField("excelBtn1")
    public Button excelBtn1;
    @UiField("excelBtn2")
    public Button excelBtn2;
    @UiField("currentRemove")
    public Button currentRemove;
    @UiField("transferRemove")
    public Button transferRemove;
    private ExtPagedFilterTable transferTradingPartnerTable = new ExtPagedFilterTable();
    private BeanItemContainer<CurrentContractDTO> transferTpResultsContainer = new BeanItemContainer<>(CurrentContractDTO.class);
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private CurrentContractDTO currentContractDTO = new CurrentContractDTO();
    private CurrentContractTableLogic tableLogic = new CurrentContractTableLogic();
    private ExtPagedTable currentTradingPartnerTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<CurrentContractDTO> currentTradingPartnerResultsContainer = new BeanItemContainer<>(CurrentContractDTO.class);
    private List<Object> selecteditemList = new ArrayList<>();
    private List<Object> selItemList = new ArrayList<>();
    private ExtFilterTable resultTable;
    private boolean isLoad = false;
    private boolean isRebateLoad = false;
    private PromoteTPLogic logic = new PromoteTPLogic();
    private List<CurrentContractDTO> transTpInfoList = new ArrayList<>();
    private boolean contractExcelFlag = false;
    private boolean infoExcelFlag = false;
    private final StplSecurity stplSecurity = new StplSecurity();

    public SummaryTPDetails() {
    }

    public SummaryTPDetails(SessionDTO session, ExtFilterTable resultTable) {
        try {
            this.session = session;
            this.setResultTable(resultTable);
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/promoteTpSummaryTPDetails.xml"), this));
            configureFields();
            configureSecurityPermissions();
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    protected void configureFields() {
        excelBtn1.setIcon(excelExportImage);
        excelBtn2.setIcon(excelExportImage);
        currentTradingPartnerTableLayout.addComponent(currentTradingPartnerTable);
        HorizontalLayout controls = tableLogic.createControls();
        currentTradingPartnerTableLayout.addComponent(controls);
        configureCurrentTradingPartnerTable();
        transferTradingPartnerTableLayout.addComponent(transferTradingPartnerTable);
        configureTransferTradingPartnerTable();
    }

    public void configureCurrentTradingPartnerTable() {
        tableLogic.setContainerDataSource(currentTradingPartnerResultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(true);

        currentTradingPartnerTable.setVisibleColumns(Constants.getInstance().currentTradingPartnerColumns);
        currentTradingPartnerTable.setColumnHeaders(Constants.getInstance().currentTradingPartnerHeaders);
        currentTradingPartnerTable.setSizeFull();
        currentTradingPartnerTable.setEditable(true);
        currentTradingPartnerTable.setFilterBarVisible(false);
        currentTradingPartnerTable.markAsDirty();
        currentTradingPartnerTable.setSelectable(true);
        currentTradingPartnerTable.setWidth("1660px");
        currentTradingPartnerTable.setHeight("290px");
        currentTradingPartnerTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        currentTradingPartnerTable.setColumnCheckBox(Constants.CHECK_RECORD, true);

        currentTradingPartnerTable.setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {

                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = check.getValue();
                            if (isCheck) {
                                selecteditemList.add(itemId);
                            } else if (selecteditemList.contains(itemId)) {
                                selecteditemList.remove(itemId);
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });

        currentTradingPartnerTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = currentTradingPartnerTable.getItemIds();
                for (Object obj : itemList) {
                    CurrentContractDTO dto = (CurrentContractDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                }
            }
        });

        currentTradingPartnerTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                return;
            }
        });
    }

    public void configureTransferTradingPartnerTable() {
        transferTradingPartnerTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        transferTradingPartnerTable.setWidth("1630px");
        transferTradingPartnerTable.setHeight("290px");
        transferTradingPartnerTable.setPageLength(NumericConstants.FIVE);
        transferTradingPartnerTable.setSelectable(true);
        transferTradingPartnerTable.setEditable(true);
        transferTradingPartnerTable.setContainerDataSource(transferTpResultsContainer);
        transferTradingPartnerTable.setVisibleColumns(Constants.getInstance().summaryTpResultsColumns);
        transferTradingPartnerTable.setColumnHeaders(Constants.getInstance().summaryTpResultsHeaders);
        transferTradingPartnerTable.setColumnCheckBox(Constants.CHECK_RECORD, true);

        transferTradingPartnerTable.setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {

                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = check.getValue();
                            if (isCheck) {
                                selItemList.add(itemId);
                            } else if (selItemList.contains(itemId)) {
                                selItemList.remove(itemId);
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });
        transferTradingPartnerTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = transferTradingPartnerTable.getItemIds();
                for (Object obj : itemList) {
                    CurrentContractDTO dto = (CurrentContractDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                }
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // empty
    }

    @UiHandler("excelBtn1")
    public void excelExport(Button.ClickEvent event) {
        try {

            contractExcelFlag = true;
            int recordCount = logic.getSelectedTPContractCount(currentContractDTO, session.getUserId(), session.getSessionId());

            if (recordCount > 0) {
                createWorkSheet("Current Customer Details", currentTradingPartnerTable, recordCount);
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        } finally {
            contractExcelFlag = false;
        }
    }

    @UiHandler("excelBtn2")
    public void excelExport2(Button.ClickEvent event) {
        try {
            infoExcelFlag = true;
            final int recordCount = transferTpResultsContainer.size();
            if (recordCount > 0) {
                createWorkSheet("Transfer Customer Details", transferTradingPartnerTable, recordCount);
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        } finally {
            infoExcelFlag = false;
        }
    }

    public void loadCurrentTradingPartnerDetails() {

        currentContractDTO.setIsCustomerDetailsTab(Boolean.TRUE);
        currentContractDTO.setReset(Boolean.FALSE);
        currentContractDTO.setCompanySystemId(session.getCompanyMasterSid());
        currentContractDTO.setSearch(BOOLEAN_CONSTANT.getTrueFlag());
        tableLogic.loadSetData(currentContractDTO, session);
    }

    public void loadSelectedTradingPartner(String contractId, String companyId) {
        transferTpResultsContainer.removeAllItems();
        List<CurrentContractDTO> selctContractList = logic.getSelectedTPContractSummary(CommmonLogic.getPromotedContractDetails(contractId, companyId));
        transferTpResultsContainer.addAll(selctContractList);
        transTpInfoList.addAll(selctContractList);
        setLoad(true);
        setRebateLoad(true);
    }

    @UiHandler("currentRemove")
    public void currentRemoveLogic(Button.ClickEvent event) {
        if (!selecteditemList.isEmpty()) {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    // do nothing
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    try {
                        for (int i = 0; i < selecteditemList.size(); i++) {
                            currentTradingPartnerTable.removeItem(selecteditemList.get(i));
                        }
                        selecteditemList.clear();
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }
                }
            }.getConfirmationMessage("Remove Confirmation", "Are you sure you want to remove the selected Contract from the Promote COMPANY process? It will be removed and added back to the Available List of Contracts in the Current Contract Selection screen.");

        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select a row to Remove.");
        }
    }

    @UiHandler("transferRemove")
    public void transferRemoveLogic(Button.ClickEvent event) {
        if (!selItemList.isEmpty()) {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    // do nothing
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    try {
                        for (int i = 0; i < selItemList.size(); i++) {
                            transferTradingPartnerTable.removeItem(selItemList.get(i));
                        }
                        selItemList.clear();
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }
                }
            }.getConfirmationMessage("Remove Confirmation", "Are you sure you want to remove the selected Contract from the Promote COMPANY process? It will be removed and added back to the Available List of Contracts in the Transfer Contract Selection screen.");

        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please Select a row to Remove.");
        }
    }

    public void createWorkSheet(String moduleName, ExtCustomTable resultTable, int count) throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] header = resultTable.getColumnHeaders();
        header = (String[]) ArrayUtils.removeElement(header, StringUtils.EMPTY);
        ExcelExportforBB.createWorkSheet(header, count, this, UI.getCurrent(), moduleName.replace(' ', '_').toUpperCase());

    }

    public void createWorkSheetContent(final Integer end, final PrintWriter printWriter) {
        try {
            if (end != 0) {
                if (contractExcelFlag) {
                    int recordCount = logic.getSelectedTPContractCount(currentContractDTO, session.getUserId(), session.getSessionId());
                    final List<CurrentContractDTO> searchList = logic.getSelectedTPContractResults(logic.getContractQuery(currentContractDTO, session.getUserId(), session.getSessionId(), 0, recordCount, false));
                    Object[] columns = currentTradingPartnerTable.getVisibleColumns();
                    columns = ArrayUtils.removeElement(columns, Constants.CHECK_RECORD);
                    ExcelExportforBB.createFileContent(columns, searchList, printWriter);
                } else if (infoExcelFlag) {
                    List<CurrentContractDTO> searchList = transferTpResultsContainer.getItemIds();
                    Object[] columns = transferTradingPartnerTable.getVisibleColumns();
                    columns = ArrayUtils.removeElement(columns, Constants.CHECK_RECORD);
                    ExcelExportforBB.createFileContent(columns, searchList, printWriter);
                }
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(session.getUserId()), "GCM-Customer Management", "Promote Customer", "SumaryTab");
            transferRemove.setVisible(CommonLogic.isButtonVisibleAccess("transferRemove", functionHM));
            currentRemove.setVisible(CommonLogic.isButtonVisibleAccess("currentRemove", functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

	public ExtFilterTable getResultTable() {
		return resultTable;
	}

	public void setResultTable(ExtFilterTable resultTable) {
		this.resultTable = resultTable;
	}

	public boolean isLoad() {
		return isLoad;
	}

	public void setLoad(boolean isLoad) {
		this.isLoad = isLoad;
	}

	public boolean isRebateLoad() {
		return isRebateLoad;
	}

	public void setRebateLoad(boolean isRebateLoad) {
		this.isRebateLoad = isRebateLoad;
	}
}
