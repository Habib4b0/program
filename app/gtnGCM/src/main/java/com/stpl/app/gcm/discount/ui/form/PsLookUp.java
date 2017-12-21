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
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author vigneshkanna
 */
public class PsLookUp extends Window {

    @UiField("psTableLayout")
    public VerticalLayout psTableLayout;
    @UiField("psNo")
    public TextField psNo;
    @UiField("psName")
    public TextField psName;
    @UiField("psStatus")
    public ComboBox psStatus;
    @UiField("psType")
    public ComboBox psType;
    @UiField("psStartDate")
    public PopupDateField psStartDate;
    @UiField("psEndDate")
    public PopupDateField psEndDate;
    @UiField("psCategory")
    public ComboBox psCategory;

    LookUpTableLogic tableLogic = new LookUpTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<>(LookupDTO.class);
    private BeanItemContainer<String> psStatusBean = new BeanItemContainer<>(String.class);
    private CustomFieldGroup binder;
    private CustomTextField parentPsName;
    public boolean flag;
    CommonUtil commonUtil=CommonUtil.getInstance();

    public PsLookUp(CustomTextField parentPsName, boolean flag) {
        setContent(Clara.create(getClass().getResourceAsStream("/discount/ps.xml"), this));
        this.parentPsName = parentPsName;
        this.flag = flag;
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        setModal(true);
        setCaption(" PS Lookup ");
        getBinder();
        try {
            configureFields();
        } catch (SystemException ex) {
             LOGGER.error(ex);

        }
    }

    public void configureFields() throws SystemException {
        psTableLayout.addComponent(resultsTable);
        psTableLayout.addComponent(tableLogic.createControls());
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setSelectable(true);
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setVisibleColumns(Constants.getInstance().psSearchColumns);
        resultsTable.setColumnHeaders(Constants.getInstance().psSearchHeaders);
        resultsTable.setColumnAlignment(Constants.PS_START_DATE, ExtCustomTable.Align.CENTER);
        resultsTable.setColumnAlignment(Constants.PS_END_DATE, ExtCustomTable.Align.CENTER);
        resultsTable.setConverter(Constants.PS_START_DATE, new DateToStringConverter());
        resultsTable.setConverter(Constants.PS_END_DATE, new DateToStringConverter());
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, NumericConstants.ONE_TWO_ZERO);
        }
        Object[] objColumn = Constants.getInstance().psSearchColumns;
        for (Object objColumn1 : objColumn) {
            String value = objColumn1.toString();
            if (value.endsWith("Date")) {
                resultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
            }
        }
        resultsTable.setFilterBarVisible(Boolean.TRUE);
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
                try{
                    CustomComboBox comboBox = new CustomComboBox();
                if (propertyId.equals("psType")) {
                    comboBox.setImmediate(true);
                    commonUtil.loadComboBox(comboBox, UiUtils.PS_TYPE, true);
                    return comboBox;
                }
                if (propertyId.equals("psCategory")) {
                    comboBox.setImmediate(true);
                    commonUtil.loadComboBox(comboBox, UiUtils.PS_CATEGORY, true);
                    return comboBox;
                }
                if (propertyId.equals("psTradeClass")) {
                    comboBox.setImmediate(true);
                    commonUtil.loadComboBox(comboBox, UiUtils.PS_TRADE_CLASS, true);
                    return comboBox;
                }
                if (propertyId.equals("psDesignation")) {
                    comboBox.setImmediate(true);
                    commonUtil.loadComboBox(comboBox, UiUtils.PS_DESIGNATION, true);
                    return comboBox;
                }
                if (propertyId.equals("psStatus")) {
                    comboBox.setImmediate(true);
                    commonUtil.loadComboBox(comboBox, UiUtils.STATUS, true);
                    return comboBox;
                }
                }catch(Exception ex){
                    LOGGER.error(ex);
                }
                return null;
            }

            public void filterRemoved(Object propertyId) {
                return;
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }
        });
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.addStyleName("filterbar");
        psStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        psStatus.setImmediate(true);
        BeanItemContainer<HelperDTO> temp = new BeanItemContainer<>(HelperDTO.class);
        temp.addAll(new DiscountLogic().getDropDownList(Constants.STATUS, Constants.HELPER_DTO));
        psStatus.setContainerDataSource(temp);
        psStatus.setNullSelectionItemId(Constants.HELPER_DTO);
        psStatus.setValue(Constants.HELPER_DTO);
        psStatus.setItemCaptionPropertyId("description");
        psType.setImmediate(true);
        temp = new BeanItemContainer<>(HelperDTO.class);
        temp.addAll(new DiscountLogic().getDropDownList("PS_TYPE", Constants.HELPER_DTO));
        psType.setContainerDataSource(temp);
        psType.setNullSelectionItemId(Constants.HELPER_DTO);
        psType.setNullSelectionAllowed(true);
        psType.setValue(Constants.HELPER_DTO);
        psType.setItemCaptionPropertyId("description");
        psEndDate.setImmediate(true);
        psEndDate.setDateFormat(Constants.DATE_FORMAT);
        psEndDate.addStyleName(Constants.DATE_FIEILD_CENTER);
        psEndDate.addStyleName(Constants.DATE_FIELD_CENTERED);
        psStartDate.setImmediate(true);
        psStartDate.setDateFormat(Constants.DATE_FORMAT);
        psStartDate.addStyleName(Constants.DATE_FIEILD_CENTER);
         try {
            commonUtil.loadComboBoxForGCM(psCategory, UiUtils.PS_CATEGORY, false);
        } catch (Exception ex) {
            Logger.getLogger(IfpLookUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        psStartDate.addStyleName(Constants.DATE_FIELD_CENTERED);
        psNo.focus();

    }

    private CustomFieldGroup getBinder() {
        binder = new CustomFieldGroup(new BeanItem<>(new LookupDTO()));
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        return binder;
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        binder.commit();
        if (!tableLogic.loadSetData(binder, Constants.PS)) {
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
                parentPsName.setValue(resultsContainer.getItem(resultsTable.getValue()).getBean().getPsNo());
            } else {
                parentPsName.setValue(resultsContainer.getItem(resultsTable.getValue()).getBean().getPsName());
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
                resultsTable.resetFilters();
                binder.setItemDataSource(new BeanItem<>(new LookupDTO()));
            }
        }.getConfirmationMessage("Reset confirmation", "Are you sure you want to reset the page to default/previous values? ");
    }
}
