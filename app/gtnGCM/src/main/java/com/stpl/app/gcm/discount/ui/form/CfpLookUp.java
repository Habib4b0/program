/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.discount.dto.LookupDTO;
import com.stpl.app.gcm.discount.logic.LookUpTableLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author vigneshkanna
 */
public class CfpLookUp extends Window {

    private static final Logger LOGGER = LoggerFactory.getLogger(CfpLookUp.class);
    @UiField("cfpTableLayout")
    public VerticalLayout cfpTableLayout;
    @UiField("cfpId")
    public TextField cfpId;
    @UiField("cfpNo")
    public TextField cfpNo;
    @UiField("cfpName")
    public TextField cfpName;
    @UiField("cfpStatus")
    public ComboBox cfpStatus;
    @UiField("cfpType")
    public ComboBox cfpType;
    private ErrorfulFieldGroup binder;
    @UiField("cfpStartDate")
    public PopupDateField cfpStartDate;
    @UiField("cfpEndDate")
    public PopupDateField cfpEndDate;
    @UiField("cfpCategory")
    public ComboBox cfpCategory;
    private final CommonUtil commonUtil = CommonUtil.getInstance();
    private final LookUpTableLogic tableDataLogic = new LookUpTableLogic();
    private final ExtPagedTable resultsDataTable = new ExtPagedTable(tableDataLogic);
    private final BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<>(LookupDTO.class);
    private final CustomTextField parentCfpName;
    private final boolean flag;

    public CfpLookUp(CustomTextField parentCfpName, boolean flag) {
        setContent(Clara.create(getClass().getResourceAsStream("/discount/cfp.xml"), this));
        this.parentCfpName = parentCfpName;
        this.flag = flag;
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        setModal(true);
        setCaption("CFP Lookup");
        getBinder();
        configureFields();
    }

    public final void configureFields() {
        try {
            cfpId.focus();
            cfpTableLayout.addComponent(resultsDataTable);
            cfpTableLayout.addComponent(tableDataLogic.createControls());
            tableDataLogic.setContainerDataSource(resultsContainer);
            tableDataLogic.setPageLength(NumericConstants.TEN);
            tableDataLogic.sinkItemPerPageWithPageLength(false);
            resultsDataTable.setSelectable(true);
            resultsDataTable.setContainerDataSource(resultsContainer);
            resultsDataTable.setVisibleColumns(Constants.getInstance().cfpSearchColumns);
            resultsDataTable.setColumnHeaders(Constants.getInstance().cfpSearchHeaders);
            resultsDataTable.setColumnAlignment(Constants.CFP_START_DATE, ExtCustomTable.Align.CENTER);
            resultsDataTable.setColumnAlignment(Constants.CFP_END_DATE, ExtCustomTable.Align.CENTER);
            resultsDataTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            resultsDataTable.setFilterBarVisible(true);
            resultsDataTable.setConverter(Constants.CFP_START_DATE, new DateToStringConverter());
            resultsDataTable.setConverter(Constants.CFP_END_DATE, new DateToStringConverter());
            for (Object propertyId : resultsDataTable.getVisibleColumns()) {
                resultsDataTable.setColumnWidth(propertyId, NumericConstants.ONE_TWO_ZERO);
            }
            Object[] objColumn = Constants.getInstance().cfpSearchColumns;
            for (Object objColumn1 : objColumn) {
                String value = objColumn1.toString();
                if (value.endsWith("Date")) {
                    resultsDataTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                }
            }
            resultsDataTable.setFilterGenerator(new ExtFilterGenerator() {
                @Override
                public Container.Filter generateFilter(Object propertyId, Object value) {
                    return null;
                }

                @Override
                public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                    if (originatingField instanceof ComboBox) {
                        if (originatingField.getValue() != null) {
                            return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                        } else {
                            return null;
                        }
                    }
                    return null;
                }

                @Override
                public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                     CustomComboBox comboCustomBox = new CustomComboBox();
                    if (propertyId.equals("cfpType")) {
                        try {
                            commonUtil.loadComboBox(comboCustomBox, UiUtils.CFP_TYPE, true);
                            return comboCustomBox;
                        } catch (Exception ex) {
                            LOGGER.error("",ex);
                        }
                    }
                    if (propertyId.equals("cfpCategory")) {
                        try {
                            commonUtil.loadComboBox(comboCustomBox, UiUtils.CFP_CATEGORY, true);
                            return comboCustomBox;
                        } catch (Exception ex) {
                            LOGGER.error("",ex);
                        }
                    }
                if (propertyId.equals("cfpDesignation")) {
                         try {
                            commonUtil.loadComboBox(comboCustomBox, UiUtils.CFP_DESIGNATION, true);                          
                             return comboCustomBox;
                             } catch (Exception ex) {
                            LOGGER.error("",ex);
                             }
                          
                }
                    if (propertyId.equals("cfpStatus")) {
                        try {
                            commonUtil.loadComboBox(comboCustomBox, UiUtils.STATUS, true);
                            return comboCustomBox;
                        } catch (Exception ex) {
                            LOGGER.error("",ex);
                        }
                    }
                    if (propertyId.equals("cfpTradeClass")) {
                        try {
                            commonUtil.loadComboBox(comboCustomBox, UiUtils.CFP_TRADE_CLASS, true);
                            return comboCustomBox;
                        } catch (Exception ex) {
                            LOGGER.error("",ex);
                        }
                    }
                    return null;
                }

                @Override
                public void filterRemoved(Object propertyId) {
                //empty
                }

                @Override
                public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
//                empty
                }

                @Override
                public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                    return null;
                }
            });
            resultsDataTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultsDataTable.addStyleName("filterbar");
            CommonUtil.loadComboBoxForGCM(cfpStatus, UiUtils.STATUS, false);
            CommonUtil.loadComboBoxForGCM(cfpType, UiUtils.CFP_TYPE, false);
            CommonUtil.loadComboBoxForGCM(cfpCategory, UiUtils.CFP_CATEGORY, false);
            cfpEndDate.setDateFormat(Constants.MM_DD_YYYY);
            cfpEndDate.addStyleName(Constants.DATE_FIELD_CENTER);
            cfpEndDate.addStyleName(Constants.DATE_FIELD_CENTERED);
            cfpStartDate.setDateFormat(Constants.MM_DD_YYYY);
            cfpStartDate.addStyleName(Constants.DATE_FIELD_CENTER);
            cfpStartDate.addStyleName(Constants.DATE_FIELD_CENTERED);
            cfpId.focus();
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    private ErrorfulFieldGroup getBinder() {
        binder = new ErrorfulFieldGroup(new BeanItem<>(new LookupDTO()));
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        return binder;
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        binder.commit();
        if (!tableDataLogic.loadSetData(binder, Constants.CFP)) {
            AbstractNotificationUtils.getErrorNotification("No Matching Records",
                    "There were no records matching the search criteria.  Please try again.");
        } else {
            Notification.show("Search Completed");
        }
    }

    @UiHandler("selectBtn")
    public void selectButtonLogic(Button.ClickEvent event) {
        if (resultsDataTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("Select Error",
                    "Please select a record. Then try again.");
        } else {
            if (flag) {
                parentCfpName.setValue(resultsContainer.getItem(resultsDataTable.getValue()).getBean().getCfpNo());
            } else {
                parentCfpName.setValue(resultsContainer.getItem(resultsDataTable.getValue()).getBean().getCfpName());
            }
            close();
        }
    }

    @UiHandler("closeBtn")
    public void closeButtonLogic(Button.ClickEvent event) {
        close();
    }

    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                binder.setItemDataSource(new BeanItem<>(new LookupDTO()));
                resultsDataTable.resetFilters();
            }
        }.getConfirmationMessage("Reset confirmation", "Are you sure you want to reset the page to default/previous values? ");

    }
}
