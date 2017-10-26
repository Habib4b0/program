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
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author vigneshkanna
 */
public class CfpLookUp extends Window {

    private static final Logger LOGGER = Logger.getLogger(CfpLookUp.class);
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
    private CustomFieldGroup binder;
    @UiField("cfpStartDate")
    public PopupDateField cfpStartDate;
    @UiField("cfpEndDate")
    public PopupDateField cfpEndDate;
    @UiField("cfpCategory")
    public ComboBox cfpCategory;
    CommonUtil commonUtil = CommonUtil.getInstance();
    UiUtils UIUtil = new UiUtils();
    LookUpTableLogic tableLogic = new LookUpTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<LookupDTO>(LookupDTO.class);
    private CustomTextField parentCfpName;
    boolean flag;

    public CfpLookUp(CustomTextField parentCfpName, boolean flag) {
        setContent(Clara.create(getClass().getResourceAsStream("/discount/cfp.xml"), this));
        this.parentCfpName = parentCfpName;
        this.flag = flag;
        addStyleName("bootstrap-ui");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
        setClosable(true);
        setModal(true);
        setCaption("CFP Lookup");
        getBinder();
        try {
            configureFields();
        } catch (SystemException ex) {
             LOGGER.error(ex);
        }
    }

    public void configureFields() throws SystemException {
        try {
            cfpId.focus();
            cfpTableLayout.addComponent(resultsTable);
            cfpTableLayout.addComponent(tableLogic.createControls());
            tableLogic.setContainerDataSource(resultsContainer);
            tableLogic.setPageLength(NumericConstants.TEN);
            tableLogic.sinkItemPerPageWithPageLength(false);
            resultsTable.setSelectable(true);
            resultsTable.setContainerDataSource(resultsContainer);
            resultsTable.setVisibleColumns(Constants.CFP_SEARCH_COLUMNS);
            resultsTable.setColumnHeaders(Constants.CFP_SEARCH_HEADERS);
            resultsTable.setColumnAlignment(Constants.CFP_START_DATE, ExtCustomTable.Align.CENTER);
            resultsTable.setColumnAlignment(Constants.CFP_END_DATE, ExtCustomTable.Align.CENTER);
            resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            resultsTable.setFilterBarVisible(true);
            resultsTable.setConverter(Constants.CFP_START_DATE, new DateToStringConverter());
            resultsTable.setConverter(Constants.CFP_END_DATE, new DateToStringConverter());
            for (Object propertyId : resultsTable.getVisibleColumns()) {
                resultsTable.setColumnWidth(propertyId, NumericConstants.ONE_TWO_ZERO);
            }
            Object[] objColumn = Constants.CFP_SEARCH_COLUMNS;
            for (Object objColumn1 : objColumn) {
                String value = objColumn1.toString();
                if (value.endsWith("Date")) {
                    resultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                }
            }
            resultsTable.setFilterGenerator(new ExtFilterGenerator() {
                public Container.Filter generateFilter(Object propertyId, Object value) {
                    return null;
                }

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

                public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                    if (propertyId.equals("cfpType")) {
                        try {
                            CustomComboBox cfpType = new CustomComboBox();
                            cfpType.setImmediate(true);
                            commonUtil.loadComboBox(cfpType, UIUtil.CFP_TYPE, true);
                            return cfpType;
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                    if (propertyId.equals("cfpCategory")) {
                        try {
                            CustomComboBox cfpCategory = new CustomComboBox();
                            cfpCategory.setImmediate(true);
                            commonUtil.loadComboBox(cfpCategory, UIUtil.CFP_CATEGORY, true);
                            return cfpCategory;
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                if (propertyId.equals("cfpDesignation")) {
                         try {
                    CustomComboBox cfpDesignation = new CustomComboBox();
                    cfpDesignation.setImmediate(true);
                            commonUtil.loadComboBox(cfpDesignation, UIUtil.CFP_DESIGNATION, true);                          
                             return cfpDesignation;
                             } catch (Exception ex) {
                            LOGGER.error(ex);
                             }
                          
                }
                    if (propertyId.equals("cfpStatus")) {
                        try {
                            CustomComboBox cfpStatus = new CustomComboBox();
                            cfpStatus.setImmediate(true);
                            commonUtil.loadComboBox(cfpStatus, UIUtil.STATUS, true);
                            return cfpStatus;
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                    if (propertyId.equals("cfpTradeClass")) {
                        try {
                            CustomComboBox cfpTradeClass = new CustomComboBox();
                            cfpTradeClass.setImmediate(true);
                            commonUtil.loadComboBox(cfpTradeClass, UIUtil.CFP_TRADE_CLASS, true);
                            return cfpTradeClass;
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                    return null;
                }

                public void filterRemoved(Object propertyId) {
                //empty
                }

                public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
//                empty
                }

                public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                    return null;
                }
            });
            resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultsTable.addStyleName("filterbar");
            commonUtil.loadComboBoxForGCM(cfpStatus, UIUtil.STATUS, false);
            commonUtil.loadComboBoxForGCM(cfpType, UIUtil.CFP_TYPE, false);
            commonUtil.loadComboBoxForGCM(cfpCategory, UIUtil.CFP_CATEGORY, false);
            cfpEndDate.setImmediate(true);
            cfpEndDate.setDateFormat(Constants.DATE_FORMAT);
            cfpEndDate.addStyleName(Constants.DATE_FIEILD_CENTER);
            cfpEndDate.addStyleName("datefieldcentered");
            cfpStartDate.setImmediate(true);
            cfpStartDate.setDateFormat(Constants.DATE_FORMAT);
            cfpStartDate.addStyleName(Constants.DATE_FIEILD_CENTER);
            cfpStartDate.addStyleName("datefieldcentered");
            cfpId.focus();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private CustomFieldGroup getBinder() {
        binder = new CustomFieldGroup(new BeanItem<LookupDTO>(new LookupDTO()));
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        return binder;
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        binder.commit();
        if (!tableLogic.loadSetData(binder, Constants.CFP)) {
            AbstractNotificationUtils.getErrorNotification("No Matching Records",
                    "There were no records matching the search criteria.  Please try again.");
        } else {
            Notification.show("Search Completed");
        }
    }

    @UiHandler("selectBtn")
    public void selectButtonLogic(Button.ClickEvent event) {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("Select Error",
                    "Please select a record. Then try again.");
        } else {
            if (flag) {
                parentCfpName.setValue(resultsContainer.getItem(resultsTable.getValue()).getBean().getCfpNo());
            } else {
                parentCfpName.setValue(resultsContainer.getItem(resultsTable.getValue()).getBean().getCfpName());
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
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                binder.setItemDataSource(new BeanItem<LookupDTO>(new LookupDTO()));
                resultsTable.resetFilters();
            }
        }.getConfirmationMessage("Reset confirmation", "Are you sure you want to reset the page to default/previous values? ");

    }
}
