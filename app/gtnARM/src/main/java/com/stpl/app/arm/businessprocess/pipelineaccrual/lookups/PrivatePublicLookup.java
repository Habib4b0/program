
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.lookups;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.tablelogic.PublicPrivateLookupTableLogic;
import com.stpl.app.arm.dataselection.dto.ViewFilterGenerator;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.converter.StringToDateConverter;
import com.vaadin.ui.Button;

import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author 
 */
public class PrivatePublicLookup extends Window {

    /**
     * Options will be: Public, Private
     */
    /**
     * Search by View Name, results table will filter based on viewNameEpl
     */
    @UiField("viewNameEpl")
    private TextField privateViewNameEpl;

    /**
     * To execute the search, will populate views
     */
    @UiField("searchBtn")
    private Button privateSearchBtn;
    /**
     * To reset the View Search section
     */
    @UiField("resetBtn")
    private Button privateResetBtn;
    /**
     * Returns the View the user selected back to the main screen that called
     * the lookup. Closes the pop-up
     */
    @UiField("selectBtn")
    private Button privateSelectBtn;
    /**
     * Closes the pop-up without returning a selction.
     */
    @UiField("closeBtn")
    private Button privateCloseBtn;
    @UiField("resultsTableLayoutEpl")
    private VerticalLayout resultsTableLayoutEplPrivate;
    private PublicPrivateLookupTableLogic tableLogic = new PublicPrivateLookupTableLogic();
    private ExtPagedTable resultsTablePrivate = new ExtPagedTable(tableLogic);
    private final BeanItemContainer<ViewLookupDTO> availableResultsContainerPrivate = new BeanItemContainer<>(ViewLookupDTO.class);
    private ViewLookupDTO dtoValue = new ViewLookupDTO();
    private int userIdPrivate;
    private String detailsNamePrivate = StringUtils.EMPTY;
    private String viewTypePrivate = StringUtils.EMPTY;
    private String viewCategoryPrivate = StringUtils.EMPTY;
    private boolean selectFlag = false;
    public static final Logger LOGGER = LoggerFactory.getLogger(PrivatePublicLookup.class);

    public PrivatePublicLookup(String viewValue, int userId, String detailsName, String viewType, String viewCategory) {
        super();
        this.userIdPrivate = userId;
        this.detailsNamePrivate = detailsName;
        this.viewTypePrivate = viewType;
        this.viewCategoryPrivate = viewCategory;
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_rate_config/private_public_lookup.xml"), this));
        configureFields();
    }

    private void configureFields() {
        try {
            setCaption("View Look Up");
            setDraggable(true);
            setClosable(false);
            center();
            setWidth("900px");
            setHeight("600px");
            setModal(true);
            setResizable(false);
            resultsTableLayoutEplPrivate.addComponent(resultsTablePrivate);
            configureTable();
            dtoValue.setViewName(StringUtils.EMPTY);
            privateViewNameEpl.setValue(StringUtils.EMPTY);
            privateViewNameEpl.focus();

        } catch (Exception e) {
            LOGGER.error("Error in configureFields :", e);
        }
    }

    private void configureTable() {
        resultsTablePrivate.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTablePrivate.setFilterGenerator(new ViewFilterGenerator());
        tableLogic.setContainerDataSource(availableResultsContainerPrivate);
        resultsTablePrivate.setSelectable(true);
        resultsTablePrivate.setWidth("870px");
        resultsTablePrivate.setHeight("290px");
        resultsTablePrivate.setImmediate(true);
        resultsTablePrivate.setSizeFull();
        resultsTablePrivate.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTablePrivate.setColumnAlignment("createdDate", ExtCustomTable.Align.CENTER);
        resultsTablePrivate.setColumnAlignment("modifiedDate", ExtCustomTable.Align.CENTER);
        resultsTablePrivate.setFilterBarVisible(true);
        resultsTablePrivate.addStyleName("table-header-normal");
        resultsTablePrivate.addStyleName("filterbar");
        resultsTablePrivate.addStyleName("filtertable");
        if ("Customer".equals(viewCategoryPrivate) || "Customer Group".equals(viewCategoryPrivate)) {
            resultsTablePrivate.setVisibleColumns(ARMUtils.getInventoryViewLookupColumns());
            resultsTablePrivate.setColumnHeaders(ARMUtils.getInventoryViewLookupHeaders());
        } else {
            resultsTablePrivate.setVisibleColumns(ARMUtils.getViewLookupColumns());
            resultsTablePrivate.setColumnHeaders(ARMUtils.getViewLookupHeaders());
        }
        resultsTablePrivate.setConverter("createdDate", new StringToDateConverter() {
            @Override
            public DateFormat getFormat(Locale locale) {
                return new SimpleDateFormat("MM/dd/YYYY hh:mm:ss");
            }
        });
        resultsTablePrivate.setConverter("modifiedDate", new StringToDateConverter() {
            @Override
            public DateFormat getFormat(Locale locale) {
                return new SimpleDateFormat("MM/dd/YYYY hh:mm:ss");
            }
        });

    }

    @UiHandler("searchBtn")
    public void searchButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside searchBtn :");
        try {
            if (StringUtils.EMPTY.equals(privateViewNameEpl.getValue())) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getSaveMessageID005());
            } else {
                availableResultsContainerPrivate.removeAllItems();
                dtoValue.setViewTypeFlag("privateView".equalsIgnoreCase(viewTypePrivate));
                dtoValue.setCreatedBy(String.valueOf(userIdPrivate));
                dtoValue.setViewName(String.valueOf(privateViewNameEpl.getValue()));
                dtoValue.setViewType(viewTypePrivate);
                dtoValue.setDetailsValue(detailsNamePrivate);
                tableLogic.configureSearchData(dtoValue, true, viewCategoryPrivate);
                if (resultsTablePrivate.size() == 0) {
                    AbstractNotificationUtils.getErrorNotification("Invalid Search", "There are no Views that match the search criteria.  Please try again.");
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error in searchButtonClick :", e);
        }
    }

    @UiHandler("resetBtn")
    public void resetButtonClick(Button.ClickEvent event) {
        try {
            if ("Customer Group".equals(viewCategoryPrivate)) { //GAL-7223
                notifier.getConfirmationMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getResetMessage_view_CG());
            } else {
                notifier.getOkCancelMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getResetMessage_views());
            }
        } catch (Exception e) {
            LOGGER.error("Error in resetButtonClick :", e);
        }
    }

    @UiHandler("selectBtn")
    public void selectButtonClick(Button.ClickEvent event) {
        try {
            if (resultsTablePrivate.size() != 0 && resultsTablePrivate.getValue() != null) {
                ViewLookupDTO viewDTO = (ViewLookupDTO) resultsTablePrivate.getValue();
                viewDTO.setCheckFlag(true);
                setSelectFlag(true);
                setDtoValue(viewDTO);
                this.close();
            } else {
                setSelectFlag(false);
                AbstractNotificationUtils.getErrorNotification("No View Selected", "There is no view selected. Please select a saved view and try again.");
            }
        } catch (Exception e) {
            LOGGER.error("Error in selectButtonClick :", e);
        }
    }

    @UiHandler("closeBtn")
    public void closeButtonClick(Button.ClickEvent event) {
        try {
            setSelectFlag(false);
            this.close();
            privateViewNameEpl.setValue(StringUtils.EMPTY);
        } catch (Exception e) {
            LOGGER.error("Error in closeButtonClick :", e);
        }
    }
    private final PublicPrivateCustomNotification notifier = new PublicPrivateCustomNotification();

    class PublicPrivateCustomNotification extends AbstractNotificationUtils {

        @Override
        public void noMethod() {
            LOGGER.debug("Inside CustomNotification NO Method");

        }

        @Override
        public void yesMethod() {
            privateViewNameEpl.setValue(StringUtils.EMPTY);
        }

    }

    public ViewLookupDTO getDtoValue() {
        return dtoValue;
    }

    public void setDtoValue(ViewLookupDTO dtoValue) {
        this.dtoValue = dtoValue;
    }

    public void reloadScreen(String viewValue, int userId, String detailsName, String viewName) {
        availableResultsContainerPrivate.removeAllItems();
        privateViewNameEpl.setValue(StringUtils.EMPTY);
        privateViewNameEpl.focus(); // GAL-7222
        this.userIdPrivate = userId;
        this.detailsNamePrivate = detailsName;
        this.viewTypePrivate = viewName;
    }

    public boolean isSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(boolean selectFlag) {
        this.selectFlag = selectFlag;
    }

    @Override
    public boolean equals(Object privateObj) {
        return super.equals(privateObj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream privateObj) throws IOException {
        privateObj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream privateObj) throws IOException, ClassNotFoundException {
        privateObj.defaultReadObject();
    }
}
