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
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.Constants.MessageConstants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final BeanItemContainer<ContractSearchDTO> resultContainer = new BeanItemContainer<>(ContractSearchDTO.class);
    private final ContractSearchLogic logic = new ContractSearchLogic();
    public static final Logger LOGGER = LoggerFactory.getLogger(ContractSearchLogic.class);
    private static final BooleanConstant BOOLEAN_CONSTANT = new BooleanConstant();
    private final CommonUtil commonUtil=CommonUtil.getInstance();
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
        final ErrorfulFieldGroup binderLocal = new ErrorfulFieldGroup(new BeanItem<>(new ContractSearchDTO()));
        binderLocal.setBuffered(true);
        binderLocal.bindMemberFields(this);
        binderLocal.setErrorDisplay(errorMsg);
        return binderLocal;
    }

    private void configureFields() {
        try {
            commonUtil.loadComboBox(aliasType, UiUtils.CONTRACT_ALIAS_TYPE, false);
            commonUtil.loadComboBox(marketType, UiUtils.CONTRACT_TYPE, false);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    private void configureTable() {
        resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        resultTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        resultTable.setPageLength(NumericConstants.FIVE);
        resultTable.setEditable(BOOLEAN_CONSTANT.getTrueFlag());
        resultTable.setContainerDataSource(resultContainer);
        resultTable.setVisibleColumns(HeaderUtil.getInstance().contractSearchColumn);
        resultTable.setColumnHeaders(HeaderUtil.getInstance().contractSearchHeader);
        resultTable.setColumnCheckBox(HeaderUtil.getInstance().contractSearchColumn[0], BOOLEAN_CONSTANT.getTrueFlag());
        resultTable.setTableFieldFactory(new TableFieldFactory() {

            @Override
            public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                Field field;
                if (String.valueOf(HeaderUtil.getInstance().contractSearchColumn[0]).equals(propertyId)) {
                    field = new CheckBox();
                } else {
                    field = null;
                }
                return field;
            }
        });

        resultTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            @Override
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
            Map<String, Set<String>> resultMap = new HashMap<>();
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
            LOGGER.error("",ex);
        }
    }

    @UiHandler("resetBtn")
    public void resetButtonClickLogic(Button.ClickEvent event) {
        try {

            binder.setItemDataSource(new BeanItem<>(new ContractSearchDTO()));
            resultContainer.removeAllItems();
            resultTable.resetFilters();
        } catch (Exception ex) {
            LOGGER.error("",ex);
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
            LOGGER.error("",ex);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
}