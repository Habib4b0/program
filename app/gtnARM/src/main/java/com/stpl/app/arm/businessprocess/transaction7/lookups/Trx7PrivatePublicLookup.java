
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.lookups;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.tablelogic.PublicPrivateLookupTableLogic;
import com.stpl.app.arm.dataselection.dto.ViewFilterGenerator;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.util.converter.StringToDateConverter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.slf4j.LoggerFactory;

/**
 *
 * @author
 */
public class Trx7PrivatePublicLookup extends Window {

    /**
     * Options will be: Public, Private
     */
    /**
     * Search by View Name, results table will filter based on viewNameEpl
     */
    @UiField("viewNameEpl")
    private TextField viewNameEpl;

    /**
     * To execute the search, will populate views
     */
    @UiField("searchBtn")
    private Button searchBtn;
    /**
     * To reset the View Search section
     */
    @UiField("resetBtn")
    private Button resetBtn;
    /**
     * Returns the View the user selected back to the main screen that called
     * the lookup. Closes the pop-up
     */
    @UiField("selectBtn")
    private Button selectBtn;
    /**
     * Closes the pop-up without returning a selction.
     */
    @UiField("closeBtn")
    private Button closeBtn;
    @UiField("resultsTableLayoutEpl")
    private VerticalLayout resultsTableLayoutEpl;
    private PublicPrivateLookupTableLogic tableLogic = new PublicPrivateLookupTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private final BeanItemContainer<ViewLookupDTO> availableResultsContainer = new BeanItemContainer<>(ViewLookupDTO.class);
    private ViewLookupDTO dtoValue = new ViewLookupDTO();
    private int userId;
    private String detailsName = StringUtils.EMPTY;
    private String viewType = StringUtils.EMPTY;
    private String viewCategory = StringUtils.EMPTY;
    private boolean selectFlag = false;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Trx7PrivatePublicLookup.class);
    private static final String HEIGHT = "600px";
    private static final String WIDTH = "900px";
    private static final String VIEW_LOOK_UP = "View Look Up";
    private static final String BOOTSTRAPBB = "bootstrap-bb";
    private static final String BOOTSTRAP = "bootstrap";

    public Trx7PrivatePublicLookup(String viewValue, int userId, String detailsName, String viewType, String viewCategory) {
        super();
        this.userId = userId;
        this.detailsName = detailsName;
        this.viewType = viewType;
        this.viewCategory = viewCategory;
        addStyleName(BOOTSTRAP);
        addStyleName(BOOTSTRAPBB);
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_rate_config/private_public_lookup.xml"), this));
        configureFields();
    }

    private void configureFields() {
        try {
            setCaption(VIEW_LOOK_UP);
            setDraggable(true);
            setClosable(false);
            center();
            setWidth(WIDTH);
            setHeight(HEIGHT);
            setModal(true);
            setResizable(false);
            resultsTableLayoutEpl.addComponent(resultsTable);
            configureTable();
            dtoValue.setViewName(StringUtils.EMPTY);
            viewNameEpl.setValue(StringUtils.EMPTY);
            viewNameEpl.focus();

        } catch (Exception e) {
            LOGGER.error("Error in configureFields :", e);
        }
    }

    private void configureTable() {
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new ViewFilterGenerator());
        tableLogic.setContainerDataSource(availableResultsContainer);
        resultsTable.setSelectable(true);
        resultsTable.setWidth("870px");
        resultsTable.setHeight("290px");
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();
        resultsTable.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setColumnAlignment("createdDate", ExtCustomTable.Align.CENTER);
        resultsTable.setColumnAlignment("modifiedDate", ExtCustomTable.Align.CENTER);
        resultsTable.setFilterBarVisible(true);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.addStyleName("filterbar");
        resultsTable.addStyleName("filtertable");
        if ("Customer".equals(viewCategory) || "Customer Group".equals(viewCategory)) {
            resultsTable.setVisibleColumns(ARMUtils.getInventoryViewLookupColumns());
            resultsTable.setColumnHeaders(ARMUtils.getInventoryViewLookupHeaders());
        } else {
            resultsTable.setVisibleColumns(ARMUtils.getViewLookupColumns());
            resultsTable.setColumnHeaders(ARMUtils.getViewLookupHeaders());
        }
        resultsTable.setConverter("createdDate", new StringToDateConverter() {
            @Override
            public DateFormat getFormat(Locale locale) {
                return new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            }
        });
        resultsTable.setConverter("modifiedDate", new StringToDateConverter() {
            @Override
            public DateFormat getFormat(Locale locale) {
                return new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            }
        });

    }

    @UiHandler("searchBtn")
    public void searchButtonClick(Button.ClickEvent event) {
        try {
            if (StringUtils.EMPTY.equals(viewNameEpl.getValue())) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getSaveMessageID005());
            } else {
                availableResultsContainer.removeAllItems();
                dtoValue.setViewTypeFlag("privateView".equalsIgnoreCase(viewType));
                dtoValue.setCreatedBy(String.valueOf(userId));
                dtoValue.setViewName(viewNameEpl.getValue());
                dtoValue.setViewType(viewType);
                dtoValue.setDetailsValue(detailsName);
                tableLogic.configureSearchData(dtoValue, true, viewCategory);
                if (resultsTable.size() == 0) {
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
            tr7PulblicNotifier.getOkCancelMessage(CommonConstant.CONFIRM_RESET, ARMMessages.getResetMessageID007());
        } catch (Exception e) {
            LOGGER.error("Error in resetButtonClick :", e);
        }
    }

    @UiHandler("selectBtn")
    public void selectButtonClick(Button.ClickEvent event) {
        try {
            boolean selectBtnFlag = false;
            if (resultsTable.size() != 0 && resultsTable.getValue() != null) {
                ViewLookupDTO viewLookUpDTO = (ViewLookupDTO) resultsTable.getValue();
                viewLookUpDTO.setCheckFlag(Boolean.TRUE);
                setSelectFlag(!selectBtnFlag);
                setDtoValue(viewLookUpDTO);
                resultsTable.setValue(null);
                this.close();
            } else {
                setSelectFlag(selectBtnFlag);
                AbstractNotificationUtils.getErrorNotification("No View Selected", "There is no view selected. Please select a saved view and try again.");
            }
        } catch (Exception ex) {
            LOGGER.error("Error in selectButtonClick :", ex);
        }
    }

    @UiHandler("closeBtn")
    public void closeButtonClickAction(Button.ClickEvent event) {
        try {
            boolean selectCloseBtnFlag = false;
            setSelectFlag(selectCloseBtnFlag);
            this.close();
            viewNameEpl.setValue(StringUtils.EMPTY);
        } catch (Exception ex) {
            LOGGER.error("Error in closeButtonClick :", ex);
        }
    }
    private final PrivatePublicCustomNotification tr7PulblicNotifier = new PrivatePublicCustomNotification();

    class PrivatePublicCustomNotification extends AbstractNotificationUtils {

        @Override
        public void noMethod() {
            LOGGER.debug("Inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            viewNameEpl.setValue(StringUtils.EMPTY);
        }

    }

    public ViewLookupDTO getDtoValue() {
        return dtoValue;
    }

    public void setDtoValue(ViewLookupDTO dtoValue) {
        this.dtoValue = dtoValue;
    }

    public void reloadScreen(String viewValue, int userId, String detailsName, String viewName) {
        availableResultsContainer.removeAllItems();
        viewNameEpl.setValue(StringUtils.EMPTY);
        this.userId = userId;
        this.detailsName = detailsName;
        this.viewType = viewName;
        this.viewNameEpl.focus();
    }

    public boolean isSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(boolean selectFlag) {
        this.selectFlag = selectFlag;
    }

    @Override
    public boolean equals(Object publicPrivObj) {
        return super.equals(publicPrivObj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream publicPrivObj) throws IOException {
        publicPrivObj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream publicPrivObj) throws IOException, ClassNotFoundException {
        publicPrivObj.defaultReadObject();
    }
}
