/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.lookups;

import com.stpl.app.arm.dataselection.dto.ViewDTO;
import com.stpl.app.arm.dataselection.dto.ViewFilterGenerator;
import com.stpl.app.arm.dataselection.logic.tablelogic.PrivatePublicViewtableLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import static com.stpl.app.arm.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.asi.ui.extfilteringtable.ExtCustomTable;

/**
 *
 * @author
 */
public class PrivatePublicLookUp extends Window {

    @UiField("viewNameEpl")
    private TextField viewNameEpl;

    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("selectBtn")
    private Button selectBtn;

    @UiField("resultsTableLayout")
    private VerticalLayout resultsTableLayout;

    private PrivatePublicViewtableLogic tableLogic = new PrivatePublicViewtableLogic();

    private BeanItemContainer<ViewDTO> resultsContainer = new BeanItemContainer<>(ViewDTO.class);

    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private String indicator = StringUtils.EMPTY;
    private ViewDTO viewDTO = new ViewDTO();
    public static final Logger DS_PUBLIC_LOGGER = LoggerFactory.getLogger(PrivatePublicLookUp.class);
    private boolean selected;
    private String screenName;

    public PrivatePublicLookUp(String indicator, String screenName) {
        this.indicator = indicator;
        this.screenName = screenName;
        init();
        configureLookUp();
        configureTable();
    }

    private void init() {
        setContent(Clara.create(getClass().getResourceAsStream("/data_selection/private_public_lookup.xml"), this));
    }

    private void configureLookUp() {
        viewNameEpl.focus();
        setDraggable(true);
        setClosable(true);
        center();
        setModal(true);
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        if (indicator.equalsIgnoreCase(ARMUtils.PRIVATE)) {
            setCaption(ARMUtils.PRIVATE_VIEW);
        } else if (indicator.equalsIgnoreCase(ARMUtils.PUBLIC)) {
            setCaption(ARMUtils.PUBLIC_VIEW);
        }
        setWidth(NumericConstants.FLOAT_SEVENTY, Sizeable.Unit.PERCENTAGE);
        setHeight(NumericConstants.FLOAT_SIXTY, Sizeable.Unit.PERCENTAGE);
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) {
        loadResultsTable();
        if (StringUtils.isBlank(String.valueOf(viewNameEpl.getValue())) || resultsTable.size() == 0) {
            AbstractNotificationUtils.getErrorNotification("Error",
                    "There are no Views that match the search criteria. Please try again");
            resultsTable.removeAllItems();
        } else {
            CommonUtils.successNotification("Search Completed");
        }
    }

    @UiHandler("resetBtn")
    public void resetpulblicButtonLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the search criteria to its default state", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId resetButtonId) {
                if (resetButtonId.name().equalsIgnoreCase("yes")) {
                    DS_PUBLIC_LOGGER.debug("Entering Reset operation");
                    resetFields();
                    DS_PUBLIC_LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("selectBtn")
    public void selectButtonLogic(Button.ClickEvent event) {
        try {
            if (resultsTable.getValue() != null) {
                viewDTO = (ViewDTO) resultsTable.getValue();
                setSelected(true);
                close();
            }
        } catch (Exception ex) {
            DS_PUBLIC_LOGGER.error(ex.getMessage());
        }

    }

    @UiHandler("closeBtn")
    public void closeButtonLogic(Button.ClickEvent event) {
        setSelected(false);
        close();
    }

    private void configureTable() {
        tableLogic.setTempPageLength(NumericConstants.TEN);
        tableLogic.setItemsPerPage(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.setContainerDataSource(resultsContainer);
        if (screenName.equals(ARMConstants.getAdjustmentSummary())) {
            resultsTable.setVisibleColumns(ARMUtils.getPrivateViewLookupColumnsAdjSumm());
            resultsTable.setColumnHeaders(ARMUtils.getPrivateViewLookupHeadersAdjSumm());
            for (Object propertyId : resultsTable.getVisibleColumns()) {
                if (propertyId.equals("createdDate") || propertyId.equals("modifiedDate")) {
                    resultsTable.setColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
                }
            }
        } else {
            resultsTable.setVisibleColumns(ARMUtils.getPrivateViewLookupColumns());
            resultsTable.setColumnHeaders(ARMUtils.getPrivateViewLookupHeaders());
        }
        resultsTable.setSelectable(true);
        resultsTable.addStyleName(ARMUtils.FILTER_TABLE);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new ViewFilterGenerator());
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();
        resultsTable.setConverter("createdDate", new DateToStringConverter());
        resultsTable.setConverter("modifiedDate", new DateToStringConverter());
        resultsTable.setConverter("fromPeriod", new DateToStringConverter());
        resultsTable.setConverter("toPeriod", new DateToStringConverter());
        selectBtn.setEnabled(false);

        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.getItem() != null) {
                    selectBtn.setEnabled(true);
                }
            }
        });
        resultsTableLayout.addComponent(resultsTable);
        resultsTableLayout.addComponent(getResponsiveControls(tableLogic.createControls()));
        tableLogic.configureSearchData(false, StringUtils.EMPTY, indicator, screenName);
    }

    public ViewDTO getViewDTO() {
        return viewDTO;
    }

    public void setViewDTO(ViewDTO viewDTO) {
        this.viewDTO = viewDTO;
    }

    public void resetFields() {
        viewNameEpl.setValue(StringUtils.EMPTY);
        resultsContainer.removeAllItems();
        resultsTable.removeAllItems();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    void loadResultsTable() {
        DS_PUBLIC_LOGGER.debug("Entering Load Results Table");
        tableLogic.configureSearchData(true, viewNameEpl.getValue(), indicator, screenName);
        resultsTable.addStyleName(ARMUtils.FILTER_TABLE);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new ViewFilterGenerator());
        resultsTable.setImmediate(true);

        resultsTable.setSelectable(true);
        resultsTable.markAsDirtyRecursive();
        DS_PUBLIC_LOGGER.debug("Ending Load Results Table");

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
