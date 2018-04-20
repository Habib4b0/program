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
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.utils.CommonUtils;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
 * @author Mohamed.Shahul
 */
public class ViewSearchLookUp extends Window {

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

    @UiField("viewTypeOptionGroup")
    private OptionGroup viewTypeOptionGroup;

    private PrivatePublicViewtableLogic tableLogic = new PrivatePublicViewtableLogic();

    private BeanItemContainer<ViewDTO> resultsContainer = new BeanItemContainer<>(ViewDTO.class);

    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private ViewDTO viewDTO = new ViewDTO();
    public static final Logger LOGGER = LoggerFactory.getLogger(PrivatePublicLookUp.class);
    private boolean selected;
    private String screenName;

    public ViewSearchLookUp(String screenName) {
        this.screenName = screenName;
        init();
        configureLookUp();
        configureTable();
        configuerFields();
    }

    private void init() {
        setContent(Clara.create(getClass().getResourceAsStream("/data_selection/viewSearchLookup.xml"), this));
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
        setCaption("View Search");
        setWidth(70f, Sizeable.Unit.PERCENTAGE);
        setHeight(60f, Sizeable.Unit.PERCENTAGE);
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
    public void resetButtonLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the search criteria to its default state", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase("yes")) {
                    LOGGER.debug("Entering Reset operation");
                    resetFields();
                    LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("selectBtn")
    public void selectButtonLogic(Button.ClickEvent event) {
        try {
            if (resultsTable.getValue() != null) {
                viewDTO = (ViewDTO) resultsTable.getValue();
                setSelected(Boolean.TRUE);
                close();
            }
        } catch (Exception ex) {
            LOGGER.error("Error in selectButtonLogic" + ex);
        }

    }

    @UiHandler("closeBtn")
    public void closeButtonLogic(Button.ClickEvent event) {
        setSelected(Boolean.FALSE);
        close();
    }

    private void configureTable() {
        tableLogic.setTempPageLength(10);
        tableLogic.setItemsPerPage(10);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.setContainerDataSource(resultsContainer);
        resultsTable.setVisibleColumns(new Object[]{"viewName", CommonConstant.CREATED_DATE, CommonConstant.MODIFIED_DATE, "createdByString"});
        resultsTable.setColumnHeaders(new String[]{"View Name", "Created Date", "Modified Date", "Created By"});
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            if (propertyId.equals(CommonConstant.CREATED_DATE) || propertyId.equals(CommonConstant.MODIFIED_DATE)) {
                resultsTable.setColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
            }
        }
        resultsTable.setSelectable(true);
        resultsTable.addStyleName(ARMUtils.FILTER_TABLE);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new ViewFilterGenerator());
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();
        resultsTable.setConverter(CommonConstant.CREATED_DATE, new DateToStringConverter("MM/dd/yyyy hh:mm:ss"));
        resultsTable.setConverter(CommonConstant.MODIFIED_DATE, new DateToStringConverter("MM/dd/yyyy hh:mm:ss"));
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
        tableLogic.configureSearchData(false, StringUtils.EMPTY, "", screenName);
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
        viewTypeOptionGroup.select(ARMUtils.PUBLIC);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    void loadResultsTable() {
        LOGGER.debug("Entering Load Results Table");
        tableLogic.configureSearchData(true, viewNameEpl.getValue(), viewTypeOptionGroup.getValue().toString(), screenName);
        resultsTable.addStyleName(ARMUtils.FILTER_TABLE);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new ViewFilterGenerator());
        resultsTable.setImmediate(true);

        resultsTable.setSelectable(true);
        resultsTable.markAsDirtyRecursive();
        LOGGER.debug("Ending Load Results Table");

    }

    private void configuerFields() {
        viewTypeOptionGroup.addItem(ARMUtils.PUBLIC);
        viewTypeOptionGroup.addItem(ARMUtils.PRIVATE);
        viewTypeOptionGroup.select(ARMUtils.PUBLIC);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
