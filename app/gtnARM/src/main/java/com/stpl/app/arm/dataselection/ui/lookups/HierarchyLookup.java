/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.lookups;

import com.stpl.app.arm.dataselection.dto.HierarchyLookupDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.app.arm.dataselection.logic.tablelogic.HierarchyLookupTableLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.CommonUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.vaadin.data.validator.StringLengthValidator;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.util.NumericConstants;

/**
 *
 * @author sathyaseelan.v
 */
public class HierarchyLookup extends Window {

    @UiField("resultsTableLayoutDS")
    private VerticalLayout resultsTableLayoutDS;

    @UiField("hierarchyTypeOpgDS")
    private OptionGroup hierarchyTypeOpgDS;

    @UiField("hierarchyNameDS")
    private TextField hierarchyNameDS;

    @UiField("selectButtonDS")
    private Button selectButtonDS;

    HierarchyLookupTableLogic tableLogic = new HierarchyLookupTableLogic();

    ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);

    BeanItemContainer<HierarchyLookupDTO> resultsContainer = new BeanItemContainer<>(HierarchyLookupDTO.class);

    DataSelectionLogic logic = new DataSelectionLogic();

    CustomTextField.ClickEvent ctfEvent;

    public String customerProductHier;

    public String lookupName;

    public HierarchyLookupDTO hierarchyDto = new HierarchyLookupDTO();

    private static final Logger LOGGER = Logger.getLogger(HierarchyLookup.class);

    public HierarchyLookup(CustomTextField.ClickEvent event) {
        super();
        this.ctfEvent = event;
        init();
        configureFields();
        configureTable();
    }

    public HierarchyLookup() {
        super();
        setContent(Clara.create(getClass().getResourceAsStream("/data_selection/hierarchy-lookup.xml"), this));
        configureFields();
        configureTable();

    }

    private void init() {
        setContent(Clara.create(getClass().getResourceAsStream("/data_selection/hierarchy-lookup.xml"), this));
        captionOnValueChange(ctfEvent);
    }

    private void configureFields() {

        setDraggable(true);
        setClosable(true);
        center();
        setModal(true);
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        setWidth(NumericConstants.FLOAT_SEVENTY, Sizeable.Unit.PERCENTAGE);
        setHeight(NumericConstants.FLOAT_SIXTY, Sizeable.Unit.PERCENTAGE);
        hierarchyNameDS.focus();
        hierarchyNameDS.setImmediate(true);
        hierarchyNameDS.setValidationVisible(true);
        hierarchyNameDS.addValidator(new StringLengthValidator("View Name Should be less than 200 characters", 0, NumericConstants.TWO_HUNDRED, true));
        hierarchyTypeOpgDS.addItem(ARMUtils.PRIMARY);
        hierarchyTypeOpgDS.addItem(ARMUtils.SECONDARY);
        hierarchyTypeOpgDS.select(ARMUtils.PRIMARY);
    }

    private void configureTable() {
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(ARMUtils.HIERARCHY_LOOKUP_COLUMNS);
        resultsTable.setColumnHeaders(ARMUtils.HIERARCHY_LOOKUP_HEADERS);
        for (Object objColumn1 : resultsTable.getVisibleColumns()) {
            String value = objColumn1.toString();
            if (value.endsWith("Date")) {
                resultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
            }
        }
        resultsTable.setSelectable(true);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();
        resultsTable.setPageLength(NumericConstants.TEN);
        selectButtonDS.setEnabled(false);

        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.getItem() != null) {
                    selectButtonDS.setEnabled(true);
                }
            }
        });
        resultsTableLayoutDS.addComponent(resultsTable);
        resultsTableLayoutDS.addComponent(getResponsiveControls(tableLogic.createControls()));

    }

    public void setEvent(CustomTextField.ClickEvent event) {
        this.ctfEvent = event;
        captionOnValueChange(event);
    }

    public void captionOnValueChange(CustomTextField.ClickEvent event) {
        customerProductHier = event.getComponent().getId().trim();
        if (customerProductHier.equals("productHierarchy")) {
            setCaption("Product Hierarchy Lookup");
            lookupName = "Product Hierarchy";
        } else {
            setCaption("Customer Hierarchy Lookup");
            lookupName = "Customer Hierarchy";
        }
    }

    @UiHandler("searchButtonDS")
    public void loadTableValues(Button.ClickEvent event) {
        resultsContainer.removeAllItems();
        hierarchyDto.setHierarchySearchName(hierarchyNameDS.getValue().trim());
        hierarchyDto.setHierarchySearchType(hierarchyTypeOpgDS.getValue().toString().trim());
        hierarchyDto.setLookupSearchName(lookupName);
        tableLogic.configureSearchData(hierarchyDto, true);
        if (tableLogic.isResultsEmpty()) {
            AbstractNotificationUtils.getErrorNotification("Error","There are no Hierarchies that match the search criteria.");
        } else {
            CommonUtils.successNotification("Search Completed");
        }
    }

    @UiHandler("resetButtonDS")
    public void resetFields(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the search criteria to its default state", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase("yes")) {
                    LOGGER.debug("Entering Reset operation");
                    resetFields();
                    LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("selectButtonDS")
    public void selectFields(Button.ClickEvent event) {
        if (resultsTable.getValue() != null) {
            hierarchyDto = (HierarchyLookupDTO) resultsTable.getValue();
            ((CustomTextField) ctfEvent.getComponent()).setValue(hierarchyDto.getHierarchyName());
            try {
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            close();
        }
    }

    @UiHandler("resetTableButtonDS")
    public void resetTableFields(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the search criteria to its default state", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase("yes")) {
                    LOGGER.debug("Entering Reset operation");
                    resetTable();
                    LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("closeButtonDS")
    public void closeLookup(Button.ClickEvent event) {
        close();
    }

    public HierarchyLookupDTO getHierarchyDto() {
        return hierarchyDto;
    }

    public void setHierarchyDto(HierarchyLookupDTO hierarchyDto) {
        this.hierarchyDto = hierarchyDto;
    }

    public CustomTextField.ClickEvent getCtfEvent() {
        return ctfEvent;
    }

    public void resetFields() {
        hierarchyNameDS.setValue(StringUtils.EMPTY);
        hierarchyTypeOpgDS.select(ARMUtils.PRIMARY);
    }

    public void resetTable() {
        resultsContainer.removeAllItems();
    }

    public TextField getHierarchyNameDS() {
        return hierarchyNameDS;
    }
    
}
