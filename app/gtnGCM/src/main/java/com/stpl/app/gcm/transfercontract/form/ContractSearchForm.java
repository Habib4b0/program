/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.form;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.transfercontract.dto.ContractSearchDTO;
import com.stpl.app.gcm.transfercontract.logic.ContractSearchLogic;
import com.stpl.app.gcm.transfercontract.util.HeaderUtil;
import com.stpl.app.gcm.ui.errorhandling.ErrorLabel;
import com.stpl.app.gcm.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.Constants.MessageConstants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Harlin
 */
public class ContractSearchForm extends VerticalLayout {

    @UiField("searchBtn")
    public Button searchBtn;

    @UiField("resetBtn")
    public Button resetBtn;

    @UiField("transferBtn")
    public Button transferBtn;

    @UiField("resultTable")
    public ExtFilterTable resultTable;

    @UiField("errorMsg")
    public ErrorLabel errorMsg;

    @UiField("contractHolder")
    public CustomTextField contractHolder;

    @UiField("contractNo")
    public TextField contractNo;

    @UiField("contractName")
    public TextField contractName;

    @UiField("customerNo")
    public TextField customerNo;

    @UiField("customerName")
    public TextField customerName;

    @UiField("marketType")
    public ComboBox marketType;

    @UiField("startDate")
    public PopupDateField startDate;

    @UiField("endDate")
    public PopupDateField endDate;

    @UiField("itemNo")
    public TextField itemNo;

    @UiField("itemName")
    public TextField itemName;

    @UiField("companyFamilyPlan")
    public TextField companyFamilyPlan;

    @UiField("itemFamilyPlan")
    public TextField itemFamilyPlan;

    @UiField("priceSchedule")
    public TextField priceSchedule;

    @UiField("rebateSchedule")
    public TextField rebateSchedule;

    @UiField("aliasType")
    public ComboBox aliasType;

    @UiField("aliasNumber")
    public TextField aliasNumber;

    @UiField("aliasStartDate")
    public PopupDateField aliasStartDate;

    @UiField("aliasEndDate")
    public PopupDateField aliasEndDate;

    private final BeanItemContainer<ContractSearchDTO> resultContainer = new BeanItemContainer<ContractSearchDTO>(ContractSearchDTO.class);
    private final ContractSearchLogic logic = new ContractSearchLogic();
    public static final Logger LOGGER = Logger.getLogger(ContractSearchLogic.class);
    CommonUtil commonUtil=CommonUtil.getInstance();
    UiUtils UIUtils=new UiUtils();
    /**
     * The binder.
     */
    private final ErrorfulFieldGroup binder;

    public ContractSearchForm() {
        addComponent(Clara.create(getClass().getResourceAsStream("/TransferContract/contractSearch.xml"), this));
        configureTable();
        binder = getBinder();
        configureFields();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public final ErrorfulFieldGroup getBinder() {
        final ErrorfulFieldGroup binderLocal = new ErrorfulFieldGroup(new BeanItem<ContractSearchDTO>(new ContractSearchDTO()));
        binderLocal.setBuffered(true);
        binderLocal.bindMemberFields(this);
        binderLocal.setErrorDisplay(errorMsg);
        return binderLocal;
    }

    private void configureFields() {
        try {
            commonUtil.loadComboBox(aliasType, UIUtils.CONTRACT_ALIAS_TYPE, false);
            commonUtil.loadComboBox(marketType, UIUtils.CONTRACT_TYPE, false);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void configureTable() {
        resultTable.setImmediate(true);
        resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        resultTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        resultTable.setPageLength(NumericConstants.FIVE);
        resultTable.setEditable(Boolean.TRUE);
        resultTable.setContainerDataSource(resultContainer);
        resultTable.setVisibleColumns(HeaderUtil.CONTRACT_SEARCH_COLUMN);
        resultTable.setColumnHeaders(HeaderUtil.CONTRACT_SEARCH_HEADER);
        resultTable.setColumnCheckBox(HeaderUtil.CONTRACT_SEARCH_COLUMN[0], Boolean.TRUE);
        resultTable.setTableFieldFactory(new TableFieldFactory() {

            public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                Field field;
                if (String.valueOf(HeaderUtil.CONTRACT_SEARCH_COLUMN[0]).equals(propertyId)) {
                    field = new CheckBox();
                } else {
                    field = null;
                }
                return field;
            }
        });

        resultTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (ContractSearchDTO temp : resultContainer.getItemIds()) {
                    resultContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                }
            }
        });
    }

    @UiHandler("transferBtn")
    public void transferButtonClickLogic(Button.ClickEvent event) {
        try {
            Map<String, Set<String>> resultMap = new HashMap<String, Set<String>>();
            Set<String> contractMap = new HashSet();
            Set<String> psMap = new HashSet();
            Set<String> rsMap = new HashSet();
            Set<String> cfpMap = new HashSet();
            Set<String> ifpMap = new HashSet();
            Boolean flage = Boolean.FALSE;
            for (ContractSearchDTO temp : resultContainer.getItemIds()) {
                if (temp.getCheck()) {
                    flage = Boolean.TRUE;
                    contractMap.add(temp.getContractSid());
                    cfpMap.add(temp.getCfpContractSid());
                    ifpMap.add(temp.getIfpContractSid());
                    psMap.add(temp.getPsContractSid());
                    rsMap.add(temp.getRsContractSid());
                }
            }

            if (flage) {
                resultMap.put("contract", contractMap);
                resultMap.put("ps", psMap);
                resultMap.put("rs", rsMap);
                resultMap.put("cfp", cfpMap);
                resultMap.put("ifp", ifpMap);
                UI.getCurrent().addWindow(new TransferContractWindow(resultMap));
            } else {
                AbstractNotificationUtils.getErrorNotification(MessageConstants.SELECT_CONTRACT_TITLE.getConstant(), MessageConstants.SELECT_CONTRACT_BODY.getConstant());
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("resetBtn")
    public void resetButtonClickLogic(Button.ClickEvent event) {
        try {

            binder.setItemDataSource(new BeanItem<ContractSearchDTO>(new ContractSearchDTO()));
            resultContainer.removeAllItems();
            resultTable.resetFilters();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("searchBtn")
    public void searchButtonClickLogic(Button.ClickEvent event) {
        try {
            binder.commit();
            if (logic.isSearch(binder)) {
                resultContainer.removeAllItems();
                if (resultContainer.size() > Constants.ZERO) {
                    CommonUIUtils.successNotification(MessageConstants.SEARCH_COMPLETED.getConstant());
                } else {
                    CommonUIUtils.successNotification(MessageConstants.NO_SEARCH_RESULTS.getConstant());
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(), MessageConstants.NO_SEARCH_CRITERIA_BODY.getConstant());
            }

        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}