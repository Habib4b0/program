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
import static com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractContractSearch.LOGGER;
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
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author vigneshkanna
 */
public class IfpLookUp extends Window {

    @UiField("ifpTableLayout")
    public VerticalLayout ifpTableLayout;
    @UiField("ifpNo")
    public TextField ifpNo;
    @UiField("ifpName")
    public TextField ifpName;
    @UiField("ifpStatus")
    public ComboBox ifpStatus;
    @UiField("ifpType")
    public ComboBox ifpType;
    @UiField("ifpCategory")
    public ComboBox ifpCategory;
    @UiField("ifpStartDate")
    public PopupDateField ifpStartDate;
    @UiField("ifpEndDate")
    public PopupDateField ifpEndDate;
    private LookUpTableLogic tableLogic = new LookUpTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<>(LookupDTO.class);
    private BeanItemContainer<String> ifpStatusBean = new BeanItemContainer<>(String.class);
    private ErrorfulFieldGroup binder;
    private CustomTextField parentIfpName;
    private boolean flag;
    private CommonUtil commonUtil = CommonUtil.getInstance();

    public IfpLookUp(CustomTextField parentIfpName, boolean flag) {
        setContent(Clara.create(getClass().getResourceAsStream("/discount/ifp.xml"), this));
        this.parentIfpName = parentIfpName;
        this.flag = flag;
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        setModal(true);
        setCaption(" IFP Lookup ");
        ifpNo.focus();
        getBinder();
        try {
            configureFields();
        } catch (SystemException ex) {
             LOGGER.error("",ex);
        }
    }

    public void configureFields()  {
        ifpTableLayout.addComponent(resultsTable);
        ifpTableLayout.addComponent(tableLogic.createControls());
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setSelectable(true);
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setVisibleColumns(Constants.getInstance().ifpSearchColumns);
        resultsTable.setColumnHeaders(Constants.getInstance().ifpSearchHeaders);
        resultsTable.setColumnAlignment(Constants.IFP_START_DATE, ExtCustomTable.Align.CENTER);
        resultsTable.setColumnAlignment(Constants.IFP_END_DATE, ExtCustomTable.Align.CENTER);
        resultsTable.setConverter(Constants.IFP_START_DATE, new DateToStringConverter());
        resultsTable.setConverter(Constants.IFP_END_DATE, new DateToStringConverter());

        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, NumericConstants.ONE_TWO_ZERO);
        }
        Object[] objColumn = Constants.getInstance().ifpSearchColumns;
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
                     CustomComboBox comboBox = new CustomComboBox();
                    if (propertyId.equals("ifpType")) {
                        commonUtil.loadComboBox(comboBox, UiUtils.IFP_TYPE, true);
                        return comboBox;
                    }
                    if (propertyId.equals("ifpCategory")) {
                        commonUtil.loadComboBox(comboBox, UiUtils.IFP_CATEGORY, true);
                        return comboBox;
                    }
                    if (propertyId.equals("ifpDesignation")) {
                        commonUtil.loadComboBox(comboBox, UiUtils.IFP_DESIGNATION, true);
                        return comboBox;
                    }
                    if (propertyId.equals("ifpStatus")) {
                       
                        commonUtil.loadComboBox(comboBox, UiUtils.STATUS, true);
                        return comboBox;
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
        ifpStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        ifpStatus.setImmediate(true);
        BeanItemContainer<HelperDTO> temp = new BeanItemContainer<>(HelperDTO.class);
        temp.addAll(new DiscountLogic().getDropDownList(Constants.STATUS, Constants.HELPER_DTO));
        ifpStatus.setContainerDataSource(temp);
        ifpStatus.setNullSelectionItemId(Constants.HELPER_DTO);
        ifpStatus.setValue(Constants.HELPER_DTO);
        ifpStatus.setItemCaptionPropertyId("description");
        ifpType.setImmediate(true);
        temp = new BeanItemContainer<>(HelperDTO.class);
        temp.addAll(new DiscountLogic().getDropDownList("IFP_TYPE", Constants.HELPER_DTO));
        ifpType.setContainerDataSource(temp);
        ifpType.setNullSelectionItemId(Constants.HELPER_DTO);
        ifpType.setValue(Constants.HELPER_DTO);
        ifpType.setItemCaptionPropertyId("description");
        ifpEndDate.setImmediate(true);
        ifpEndDate.setDateFormat(Constants.MM_DD_YYYY);
        ifpEndDate.addStyleName(Constants.DATE_FIELD_CENTER);
        ifpEndDate.addStyleName(Constants.DATE_FIELD_CENTERED);
        ifpStartDate.setImmediate(true);
        ifpStartDate.setDateFormat(Constants.MM_DD_YYYY);
        ifpStartDate.addStyleName(Constants.DATE_FIELD_CENTER);
        ifpStartDate.addStyleName(Constants.DATE_FIELD_CENTERED);
        ifpNo.focus();
        try {
            CommonUtil.loadComboBoxForGCM(ifpCategory, UiUtils.IFP_CATEGORY, false);
        } catch (Exception ex) {
            LoggerFactory.getLogger(IfpLookUp.class.getName()).error("", ex);
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
        if (!tableLogic.loadSetData(binder, Constants.IFP)) {
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
                parentIfpName.setValue(resultsContainer.getItem(resultsTable.getValue()).getBean().getIfpNo());
            } else {
                parentIfpName.setValue(resultsContainer.getItem(resultsTable.getValue()).getBean().getIfpName());
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
                resultsTable.resetFilters();
                binder.setItemDataSource(new BeanItem<>(new LookupDTO()));
            }
        }.getConfirmationMessage("Reset confirmation", "Are you sure you want to reset the page to default/previous values? ");



    }
}
