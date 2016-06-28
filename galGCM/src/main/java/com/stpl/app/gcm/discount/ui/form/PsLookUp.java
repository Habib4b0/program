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
import com.stpl.app.gcm.tp.dto.IdDescriptionDTO;
import com.stpl.app.gcm.tp.logic.CompanySearchLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
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
import java.util.ArrayList;
import java.util.List;
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
    private BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<LookupDTO>(LookupDTO.class);
    private BeanItemContainer<String> psStatusBean = new BeanItemContainer<String>(String.class);
    private BeanItemContainer<String> psTypeBean = new BeanItemContainer<String>(String.class);
    private CustomFieldGroup binder;
    private CustomTextField parentPsName;
    public boolean flag;
    CommonUtil commonUtil=CommonUtil.getInstance();
    UiUtils UIUtils=new UiUtils();

    public PsLookUp(CustomTextField parentPsName, boolean flag) {
        setContent(Clara.create(getClass().getResourceAsStream("/discount/ps.xml"), this));
        this.parentPsName = parentPsName;
        this.flag = flag;
        addStyleName("bootstrap-ui");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
        setClosable(true);
        setModal(true);
        setCaption(" PS Lookup ");
        getBinder();
        try {
            configureFields();
        } catch (SystemException ex) {

        }
    }

    public void configureFields() throws SystemException {
        psTableLayout.addComponent(resultsTable);
        psTableLayout.addComponent(tableLogic.createControls());
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(10);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setSelectable(true);
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setVisibleColumns(Constants.PS_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(Constants.PS_SEARCH_HEADERS);
        resultsTable.setColumnAlignment(Constants.PS_START_DATE, ExtCustomTable.Align.CENTER);
        resultsTable.setColumnAlignment(Constants.PS_END_DATE, ExtCustomTable.Align.CENTER);
        resultsTable.setConverter(Constants.PS_START_DATE, new DateToStringConverter());
        resultsTable.setConverter(Constants.PS_END_DATE, new DateToStringConverter());
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, 120);
        }
        Object[] objColumn = Constants.PS_SEARCH_COLUMNS;
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
                CompanySearchLogic logic = new CompanySearchLogic();
                List<IdDescriptionDTO> list = new ArrayList<IdDescriptionDTO>();
                if (propertyId.equals("psType")) {
                    CustomComboBox psType = new CustomComboBox();
                    psType.setImmediate(true);
                    commonUtil.loadComboBox(psType, UIUtils.PS_TYPE, true);
                    return psType;
                }
                if (propertyId.equals("psCategory")) {
                    CustomComboBox psCategory = new CustomComboBox();
                    psCategory.setImmediate(true);
                    commonUtil.loadComboBox(psCategory, UIUtils.PS_CATEGORY, true);
                    return psCategory;
                }
                if (propertyId.equals("psTradeClass")) {
                    CustomComboBox psTradeClass = new CustomComboBox();
                    psTradeClass.setImmediate(true);
                    commonUtil.loadComboBox(psTradeClass, UIUtils.PS_TRADE_CLASS, true);
                    return psTradeClass;
                }
                if (propertyId.equals("psDesignation")) {
                    CustomComboBox psDesignation = new CustomComboBox();
                    psDesignation.setImmediate(true);
                    commonUtil.loadComboBox(psDesignation, UIUtils.PS_DESIGNATION, true);
                    return psDesignation;
                }
                if (propertyId.equals("psStatus")) {
                    CustomComboBox ifpStatus = new CustomComboBox();
                    ifpStatus.setImmediate(true);
                    commonUtil.loadComboBox(ifpStatus, UIUtils.STATUS, true);
                    return ifpStatus;
                }
                }catch(Exception ex){
                    LOGGER.error(ex.getMessage());
                }
                return null;
            }

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }
        });
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.addStyleName("filterbar");
        psStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        psStatus.setImmediate(true);
        BeanItemContainer<HelperDTO> temp = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        temp.addAll(new DiscountLogic().getDropDownList(Constants.STATUS, Constants.HELPER_DTO));
        psStatus.setContainerDataSource(temp);
        psStatus.setNullSelectionItemId(Constants.HELPER_DTO);
        psStatus.setValue(Constants.HELPER_DTO);
        psStatus.setItemCaptionPropertyId("description");
        psType.setImmediate(true);
        temp = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        temp.addAll(new DiscountLogic().getDropDownList("PS_TYPE", Constants.HELPER_DTO));
        psType.setContainerDataSource(temp);
        psType.setNullSelectionItemId(Constants.HELPER_DTO);
        psType.setNullSelectionAllowed(true);
        psType.setValue(Constants.HELPER_DTO);
        psType.setItemCaptionPropertyId("description");
        psEndDate.setImmediate(true);
        psEndDate.setDateFormat(Constants.DATE_FORMAT);
        psEndDate.addStyleName(Constants.DATE_FIEILD_CENTER);
        psEndDate.addStyleName("datefieldcentered");
        psStartDate.setImmediate(true);
        psStartDate.setDateFormat(Constants.DATE_FORMAT);
        psStartDate.addStyleName(Constants.DATE_FIEILD_CENTER);
         try {
            commonUtil.loadComboBoxForGCM(psCategory, UIUtils.PS_CATEGORY, false);
        } catch (Exception ex) {
            Logger.getLogger(IfpLookUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        psStartDate.addStyleName("datefieldcentered");
        psNo.focus();

    }

    private CustomFieldGroup getBinder() {
        binder = new CustomFieldGroup(new BeanItem<LookupDTO>(new LookupDTO()));
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        return binder;
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) throws SystemException, PortalException, Exception {
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
                binder.setItemDataSource(new BeanItem<LookupDTO>(new LookupDTO()));
            }
        }.getConfirmationMessage("Reset confirmation", "Are you sure you want to reset the page to default/previous values? ");
    }
}
