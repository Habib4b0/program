
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.copycontract.logic.tablelogic.ContractSearchTableLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.transfercontract.dto.ContractSearchDTO;
import com.stpl.app.gcm.transfercontract.logic.ContractSearchLogic;
import com.stpl.app.gcm.transfercontract.util.Constant;
import com.stpl.app.gcm.transfercontract.util.HeaderUtil;
import com.stpl.app.gcm.ui.errorhandling.ErrorLabel;
import com.stpl.app.gcm.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import org.vaadin.teemu.clara.Clara;

/**
 *
 * @author kasiammal.m
 */
public class CopyContractindex extends VerticalLayout {

    SessionDTO session;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CopyContractindex.class);
    private final BeanItemContainer<ContractSearchDTO> resultContainer = new BeanItemContainer<>(ContractSearchDTO.class);
    @UiField("copycontractTableLayout")
    public VerticalLayout copycontractTableLayout;
    @UiField("copyBtn")
    public Button copyBtn;
    @UiField("Btnsearch")
    public Button Btnsearch;
    @UiField("btnreset")
    public Button btnreset;
    @UiField("aliasType")
    public ComboBox aliastypecc;
    @UiField("errorMsg")
    public ErrorLabel errorMsg;
    @UiField(Constants.CONTRACT_HOLDER)
    public CustomTextField contractHolder;
    @UiField("contractNo")
    public TextField contractNo;
    @UiField("contractName")
    public TextField contractName;
    @UiField("marketType")
    public ComboBox marketType;
    @UiField("startDate")
    public PopupDateField startDate;
    @UiField("endDate")
    public PopupDateField endDate;
    @UiField("companyFamilyPlan")
    public TextField companyFamilyPlan;
    @UiField("itemFamilyPlan")
    public TextField itemFamilyPlan;
    @UiField("priceSchedule")
    public TextField priceSchedule;
    @UiField("aliasNumber")
    public TextField aliasNumber;
    @UiField("aliasStartDate")
    public PopupDateField aliasStartDate;
    @UiField("aliasEndDate")
    public PopupDateField aliasEndDate;
    private final ContractSearchLogic logic = new ContractSearchLogic();
    private final ErrorfulFieldGroup binder;
    TextField hiddenId = new TextField();
    CommonUtil commonUtils = CommonUtil.getInstance();
    ContractSearchTableLogic tablelogic = new ContractSearchTableLogic();
    public ExtPagedTable copycontractResultsTable = new ExtPagedTable(tablelogic);
    final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
    ContractSearchDTO binderDTO;
    List<ContractSearchDTO> selectionList = new ArrayList<>();
    public CopyContractindex() throws SystemException {
        addComponent(Clara.create(getClass().getResourceAsStream("/CopyContract.xml"), this));
        configuretable();
        binder = getBinder();
        configureFields();
        configureSecurityPermissions();
        
    }
    
    protected void configureFields() {
        try {
            contractHolder.focus();
            commonUtils.loadComboBox(aliastypecc, UiUtils.CONTRACT_ALIAS_TYPE, false);
            commonUtils.loadComboBox(marketType, UiUtils.CONTRACT_TYPE, false);
            marketType.setValidationVisible(true);
            aliastypecc.setValidationVisible(true);
            hiddenId.setVisible(false);
            aliasStartDate.setStyleName(Constants.DATE_FIELD_CENTER);
            aliasEndDate.setStyleName(Constants.DATE_FIELD_CENTER);
            aliasStartDate.setDateFormat(Constants.MM_DD_YYYY);
            aliasEndDate.setDateFormat(Constants.MM_DD_YYYY);
            startDate.setStyleName(Constants.DATE_FIELD_CENTER);
            startDate.setDateFormat(Constants.MM_DD_YYYY);
            endDate.setStyleName(Constants.DATE_FIELD_CENTER);
            endDate.setDateFormat(Constants.MM_DD_YYYY);
            
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        
    }
    
    public final ErrorfulFieldGroup getBinder() {
        final ErrorfulFieldGroup binderLocal = new ErrorfulFieldGroup(new BeanItem<>(new ContractSearchDTO()));
        try {
            binderLocal.setBuffered(true);
            binderLocal.bindMemberFields(this);
            binderLocal.getFields().iterator();
            binderLocal.setErrorDisplay(errorMsg);
        } catch (Exception e) {
          LOGGER.error(e);
        }
        return binderLocal;
    }

    public void configuretable() {
        try {
            copycontractResultsTable.setImmediate(true);
            copycontractResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            copycontractResultsTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
            copycontractResultsTable.setHeight(NumericConstants.FOUR_HUNDRED, Sizeable.Unit.PIXELS);
            copycontractResultsTable.setPageLength(10);
            copycontractResultsTable.setItemsPerPage(10);
            copycontractResultsTable.setEditable(Boolean.TRUE);
            tablelogic.setContainerDataSource(resultContainer);
            copycontractTableLayout.addComponent(copycontractResultsTable);
            copycontractTableLayout.addComponent(tablelogic.createControls());
            copycontractResultsTable.setVisibleColumns(HeaderUtil.getInstance().contractSearchColumn);
            copycontractResultsTable.setColumnHeaders(HeaderUtil.getInstance().contractSearchHeader);
            copycontractResultsTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            copycontractResultsTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
            copycontractResultsTable.setColumnCheckBox(HeaderUtil.getInstance().contractSearchColumn[0], Boolean.TRUE);
            copycontractResultsTable.setTableFieldFactory(new TableFieldFactory() {
                @Override
                public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                    Field field;
                    if (String.valueOf(HeaderUtil.getInstance().contractSearchColumn[0]).equals(propertyId)) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();
                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            @Override
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                insertIntoTable(itemId, check.getValue());
                                if (check.getValue()) {
                                    selectionList.add((ContractSearchDTO) itemId);
                                } else {
                                    selectionList.remove(itemId);
                                }
                            }

                        });
                        return check;

                    } else {
                        field = null;
                    }
                    return field;
                }
            });

            copycontractResultsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                @Override
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    for (ContractSearchDTO temp : resultContainer.getItemIds()) {
                        resultContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                    }
                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    @UiHandler("copyBtn")
    public void copyBtnClickLogic(Button.ClickEvent event) {
        List<ContractSelectionDTO> selectedList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int chechvalue = 0;
        for (ContractSearchDTO temp : selectionList) {
            ContractSelectionDTO dto = new ContractSelectionDTO();
            dto.setContractSid(temp.getContractSid());
            dto.setSessionId(String.valueOf(binderDTO.getSessionId()));
            dto.setUserid(String.valueOf(binderDTO.getUserId()));
            dto.setContractHolder(temp.getContractHolder());
            dto.setContractName(temp.getContractName());
            dto.setContractNo(temp.getContractNo());
            set.add(temp.getContractNo());
            dto.setMarketType(temp.getMarketTypeValue());
            dto.setStartDate(String.valueOf(temp.getStartDate()));
            dto.setEndDate(String.valueOf(temp.getEndDate()));
            dto.setCFPname(temp.getCompanyFamilyPlan());
            dto.setIFPname(temp.getItemFamilyPlan());
            dto.setPSname(temp.getPriceSchedule());
            dto.setRSname(temp.getRebateSchedule());
            if (!temp.getRsContractSid().equals(StringUtils.EMPTY)) {
                dto.setRSId(Integer.valueOf(temp.getRsContractSid()));
            } else {
                dto.setRSId(0);
            }
            if (!temp.getCfpContractSid().equals(StringUtils.EMPTY)) {
                dto.setCFPId(Integer.valueOf(temp.getCfpContractSid()));
            } else {
                dto.setCFPId(0);
            }
            if (!temp.getIfpContractSid().equals(StringUtils.EMPTY)) {
                dto.setIFPId(Integer.valueOf(temp.getIfpContractSid()));
            } else {
                dto.setIFPId(0);
            }
            if (!temp.getPsContractSid().equals(StringUtils.EMPTY)) {
                dto.setPSId(Integer.valueOf(temp.getPsContractSid()));
            } else {
                dto.setPSId(0);
            }
            selectedList.add(dto);
            chechvalue++;

        }

        if (chechvalue == 0) {
            AbstractNotificationUtils.getErrorNotification("Copy", "Please checkmark a Contract to copy.");
        } else {
            if (set.size() > 1) {
                AbstractNotificationUtils.getErrorNotification("Copy", "Selected contracts should have same contract No .");
                return;
            }
            Contractselectionpopup ContractselectionpopupWindow = new Contractselectionpopup(selectedList);
            UI.getCurrent().addWindow(ContractselectionpopupWindow);
        }
    }

    @UiHandler("Btnsearch")
    public void searchBtnClickLogic(Button.ClickEvent event) {
        try {
            binder.commit();
            binderDTO = ((BeanItem<ContractSearchDTO>) (binder.getItemDataSource())).getBean();
            createSessionID(binderDTO);
            if (endDate.getValue() != null && startDate.getValue().after(endDate.getValue())) {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Contract End date should be after Contract Start Date.");
            } else if (endDate.getValue() != null && startDate.getValue().getTime() == endDate.getValue().getTime()) {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Contract Start date and Contract End date are equal.");

            } else if (aliasEndDate.getValue() != null && aliasStartDate.getValue().after(aliasEndDate.getValue())) {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Contract Alias End date should be after Contract Alias Start Date.");
            } else if (aliasEndDate.getValue() != null && aliasStartDate.getValue().getTime() == aliasEndDate.getValue().getTime()) {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Contract Alias Start date and Contract Alias End date are equal.");

            } else if (logic.isSearch(binder)) {
                copycontractResultsTable.setColumnCheckBox(HeaderUtil.getInstance().contractSearchColumn[0], Boolean.TRUE);
                if (tablelogic.loadSetDate(binderDTO, true)) {
                    CommonUIUtils.successNotification(Constants.MessageConstants.SEARCH_COMPLETED.getConstant());

                } else {
                    CommonUIUtils.successNotification(Constants.MessageConstants.NO_SEARCH_RESULTS.getConstant());
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(), Constants.MessageConstants.NO_SEARCH_CRITERIA_BODY.getConstant());
            }

        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("btnreset")
    public void resetButtonClickLogic(Button.ClickEvent event
    ) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                try {
                    binder.setItemDataSource(new BeanItem<>(new ContractSearchDTO()));
                    tablelogic.loadSetDate(binderDTO, false);
                    copycontractResultsTable.resetFilters();
                    aliastypecc.setValue(Constant.HELPER_DTO);

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage("Reset", "Are you sure you want to reset the page to default/previous values?");
    }

    @UiHandler(Constants.CONTRACT_HOLDER)
    public void contractHolderLookup(CustomTextField.ClickEvent event
    ) {
        try {
            ComponentLookUp chLookup = new ComponentLookUp("Contract Holder", "Contract Holder Lookup", contractHolder);
            chLookup.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    if (contractHolder.getData() != null) {
                        ComponentLookUpDTO object = (ComponentLookUpDTO) contractHolder.getData();
                        hiddenId.setValue(object.getMasterSid());
                        contractHolder.setValue(object.getComponentName());
                    }
                }
            });

            getUI().addWindow(chLookup);

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void enter() {
        return;
    }

    private void insertIntoTable(Object itemId, Boolean value) {
        ContractSearchDTO dto = (ContractSearchDTO) itemId;
        dto.setSessionId(binderDTO.getSessionId());
        dto.setUserId(binderDTO.getUserId());
        String queryName = "Copy Contract-Check Record Delete";
        if (value) {
            queryName = "Copy Contract-Check Record Insert";
        }
        ItemQueries.updateDataByDTO(queryName, dto);
    }

    private void createSessionID(ContractSearchDTO dto) {
        Date sessionDate = new Date();
        dto.setSessionId(Integer.valueOf(fmtID.format(sessionDate)));
        dto.setUserId(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute("userId"))));
    }
     /**
     * Configure security
     *
     */
    private void configureSecurityPermissions() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "GCM-Contract Management", "Copy Contract", "Landing screen");
            Btnsearch.setVisible(CommonLogic.isButtonVisibleAccess("Btnsearch", functionHM));
            btnreset.setVisible(CommonLogic.isButtonVisibleAccess("btnreset", functionHM));
            copyBtn.setVisible(CommonLogic.isButtonVisibleAccess("copyBtn", functionHM));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
