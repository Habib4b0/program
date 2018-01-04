/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.discount.dto.LookupDTO;
import com.stpl.app.gcm.discount.logic.DiscountLogic;
import com.stpl.app.gcm.discount.logic.LookUpTableLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.SystemException;
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
public class RsLookUp extends Window {
 private static final Logger LOGGER = LoggerFactory.getLogger(RsLookUp.class);
    @UiField("rsTableLayout")
    public VerticalLayout rsTableLayout;
    @UiField("rsId")
    public TextField rsId;
    @UiField("rsNo")
    public TextField rsNo;
    @UiField("rsName")
    public TextField rsName;
    @UiField("rsStatus")
    public ComboBox rsStatus;
    @UiField("rsType")
    public ComboBox rsType;
    @UiField("rsStartDate")
    public PopupDateField rsStartDate;
    @UiField("rsEndDate")
    public PopupDateField rsEndDate;
    @UiField("rebateProgramType")
    public ComboBox rebateProgramType;
    @UiField("rsCategory")
    public ComboBox rsCategory;    
    private CommonUtil commonUtil=CommonUtil.getInstance();
    
    private LookUpTableLogic tableLogic = new LookUpTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<>(LookupDTO.class);
    private ErrorfulFieldGroup binder;
    private CustomTextField parentRsName;

    public RsLookUp(CustomTextField parentRsName) {
          super(" RS Lookup ");
        setContent(Clara.create(getClass().getResourceAsStream("/discount/rs.xml"), this));
        this.parentRsName = parentRsName;
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);      
        setClosable(true);
        setModal(true);
        getBinder();
        try {
            configureFields();
        } catch (Exception ex) {
         LOGGER.error("",ex);
        }
    }

    public void configureFields() throws SystemException {
        rsTableLayout.addComponent(resultsTable);
        rsTableLayout.addComponent(tableLogic.createControls());
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setSelectable(true);
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setVisibleColumns(Constants.getInstance().rsSearchColumns);
        resultsTable.setColumnHeaders(Constants.getInstance().rsSearchHeaders);
        resultsTable.setColumnAlignment(Constants.RS_START_DATE, ExtCustomTable.Align.CENTER);
        resultsTable.setColumnAlignment(Constants.RS_END_DATE, ExtCustomTable.Align.CENTER);
        resultsTable.setConverter(Constants.RS_START_DATE, new DateToStringConverter());
        resultsTable.setConverter(Constants.RS_END_DATE, new DateToStringConverter());
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, NumericConstants.ONE_TWO_ZERO);
        }
        Object[] objColumn = Constants.getInstance().rsSearchColumns;
        for (Object objColumn1 : objColumn) {
            String value = objColumn1.toString();
            if (value.endsWith("Date")) {
                resultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
            }
        }
        resultsTable.setFilterBarVisible(Boolean.TRUE);
        resultsTable.setFilterGenerator(new ExtFilterGenerator() {

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
                try {
                if (propertyId.equals("rsType")) {
                    CustomComboBox rsType = new CustomComboBox();
                    commonUtil.loadComboBox(rsType, UiUtils.RS_TYPE, true);
                    return rsType;
                }
                if (propertyId.equals("rsCategory")) {
                    CustomComboBox rsCategory = new CustomComboBox();
                    
                        commonUtil.loadComboBox(rsCategory, UiUtils.RS_CATEGORY, true);
                   
                    return rsCategory;
                }
                if (propertyId.equals("rsTradeClass")) {
                    CustomComboBox rsTradeClass = new CustomComboBox();
                     commonUtil.loadComboBox(rsTradeClass, UiUtils.RS_TRADE_CLASS, true);
                    return rsTradeClass;
                }
                if (propertyId.equals("rsDesignation")) {
                    CustomComboBox rsDesignation = new CustomComboBox();
                      commonUtil.loadComboBox(rsDesignation, UiUtils.RS_DESIGNATION, true);
                    return rsDesignation;
                }
                if (propertyId.equals("rsStatus")) {
                    CustomComboBox rsStatus = new CustomComboBox();
                    commonUtil.loadComboBox(rsStatus, UiUtils.STATUS, true);
                    return rsStatus;
                }
                if (propertyId.equals("rebateProgramType")) {
                    CustomComboBox rebateProgramType = new CustomComboBox();
                     commonUtil.loadComboBox(rebateProgramType, UiUtils.REBATE_PROGRAM_TYPE, true);
                    return rebateProgramType;
                }
                 } catch (Exception ex) {
                     LOGGER.error("",ex);
                    }
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
                return;
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }
        });
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.addStyleName("filterbar");

        BeanItemContainer<HelperDTO> temp = new BeanItemContainer<>(HelperDTO.class);
        temp.addAll(new DiscountLogic().getDropDownList(Constants.STATUS, Constants.HELPER_DTO));
        rsStatus.setContainerDataSource(temp);
        rsStatus.setNullSelectionItemId(Constants.HELPER_DTO);
        rsStatus.setNullSelectionAllowed(true);
        rsStatus.setValue(Constants.HELPER_DTO);
        rsStatus.setItemCaptionPropertyId(Constants.DESCRIPTION);
        temp = new BeanItemContainer<>(HelperDTO.class);
        temp.addAll(new DiscountLogic().getDropDownList("RS_TYPE", Constants.HELPER_DTO));
        rsType.setContainerDataSource(temp);
        rsType.setNullSelectionItemId(Constants.HELPER_DTO);
        rsType.setValue(Constants.HELPER_DTO);
        rsType.setItemCaptionPropertyId(Constants.DESCRIPTION);
        rsStartDate.setDateFormat(Constants.DATE_FORMAT);
        rsStartDate.addStyleName(Constants.DATE_FIEILD_CENTER);
        rsEndDate.setDateFormat(Constants.DATE_FORMAT);
        rsEndDate.addStyleName(Constants.DATE_FIEILD_CENTER);
        temp = new BeanItemContainer<>(HelperDTO.class);
        temp.addAll(new DiscountLogic().getDropDownList("REBATE_PROGRAM_TYPE", Constants.HELPER_DTO));
        rebateProgramType.setContainerDataSource(temp);
        rebateProgramType.setNullSelectionItemId(Constants.HELPER_DTO);
        rebateProgramType.setValue(Constants.HELPER_DTO);
        rebateProgramType.setItemCaptionPropertyId(Constants.DESCRIPTION);
        try {
            CommonUtil.loadComboBoxForGCM(rsCategory, UiUtils.RS_CATEGORY, false);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        rsId.focus();
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
        if (!tableLogic.loadSetData(binder, Constants.RS)) {
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
                    "Please Select an item and try again.");
        } else {
            parentRsName.setValue(resultsContainer.getItem(resultsTable.getValue()).getBean().getRsName());
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
                resultsTable.resetFilters();
                binder.setItemDataSource(new BeanItem<>(new LookupDTO()));

            }

        }.getConfirmationMessage("Reset confirmation", "Are you sure you want to reset the page to default/previous values? ");

    }
}
