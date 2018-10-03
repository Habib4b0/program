/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lookups;

import com.stpl.app.gtnforecasting.dto.RSFormulaDTO;
import com.stpl.app.gtnforecasting.logic.tablelogic.FormulaTableLogic;
import com.stpl.app.gtnforecasting.ppaprojection.dto.FilterGenerator;
import com.stpl.app.gtnforecasting.queryUtils.PPAQuerys;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Sibi
 */
public class PPAFormulaLookup extends Window {

    private static final Logger LOGGER = LoggerFactory.getLogger(PPAFormulaLookup.class);

    @UiField("formulaID")
    private TextField formulaID;

    @UiField("formulaNo")
    private TextField formulaNo;

    @UiField("formulaName")
    private TextField formulaName;

    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("selectBtn")
    private Button selectBtn;

    @UiField("closeBtn")
    private Button closeBtn;

    @UiField("tablelayout")
    private VerticalLayout tablelayout;

    @UiField("detailsTableLayout")
    private VerticalLayout detailsTableLayout;

    @UiField("controlLayout")
    private HorizontalLayout controlLayout;

    @UiField("detailsControlLayout")
    private HorizontalLayout detailsControlLayout;

    @UiField("formulaType")
    private ComboBox formulaType;

    private final FormulaTableLogic tableLogic = new FormulaTableLogic();

    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);

    private final FormulaTableLogic detailstableLogic = new FormulaTableLogic();

    private final ExtPagedTable detailsTable = new ExtPagedTable(detailstableLogic);

    private final BeanItemContainer<RSFormulaDTO> resultsContainer = new BeanItemContainer<>(RSFormulaDTO.class);
    private final BeanItemContainer<RSFormulaDTO> detailsContainer = new BeanItemContainer<>(RSFormulaDTO.class);

    private RSFormulaDTO rsFormulaDTO = new RSFormulaDTO();

    private CustomFieldGroup binder;

    private boolean isSelected;

    private String propertyId;

    private boolean validatePPA = false;
    
    public final Object[] formulaLookup = new Object[]{
        Constant.FORMULA_TYPE, "formulaID", "formulaNo", "formulaName", "version"};
    public final String[] formulaLookupHeader = new String[]{
        "Formula Type", "Formula ID", "Formula No", "Formula Name", "Version"};
    public final Object[] ruleDetailsColumns = new Object[]{
        Constant.DEDUCTION_TYPE, Constant.DEDUCTION_SUB_TYPE, Constant.DEDUCTION_CATEGORY, Constant.INDICATOR};
    public final String[] ruleDetailsHeaders = new String[]{"Deduction Type", "Deduction Sub Type", "Deduction Category", "+/- Indicator"};

    /**
     * Default Constructor to load the formula for Mass Update
     *
     * @throws Exception
     */
    public PPAFormulaLookup(String propertyId, boolean validatePPA) {
        this.propertyId = propertyId;
        this.validatePPA = validatePPA;
        init();
    }

    /**
     * Configures the Window
     */
    private void init() {
        this.setModal(true);
        this.setClosable(true);
        this.center();
        this.setWidth("1400px");
        setCaption("Formula Lookup");
        addStyleName(Constant.BOOTSTRAP);
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/formula-lookup.xml"), this));
        configureBinder();
        configureTable();
        CommonUtil.getInstance().loadComboBox(formulaType, "NS_FORMULA_TYPE", false);
    }

    /**
     * Binds the search criteria field values to the DTO.
     */
    private void configureBinder() {
        binder = new CustomFieldGroup(new BeanItem(rsFormulaDTO));
        binder.bindMemberFields(this);
    }

    /**
     * Configures the table logic and result table.
     */
    private void configureTable() {
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();
        resultsTable.setVisibleColumns(formulaLookup);
        resultsTable.setColumnHeaders(formulaLookupHeader);
        resultsTable.setFilterBarVisible(true);
        resultsTable.addStyleName(Constant.FILTERBAR);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new FilterGenerator());
        resultsTable.setSelectable(true);
        resultsTable.setWidth("744px");
        tablelayout.addComponent(resultsTable);
        getResponsiveControls(tableLogic.createControls(), controlLayout);
        if (propertyId.equals(Constant.PPAVariables.NEP_FORMULA.getConstant())) {
            resultsTable.setFilterFieldVisible(Constant.FORMULA_TYPE, false);
            formulaType.setEnabled(false);
        }
        detailstableLogic.setContainerDataSource(detailsContainer);
        detailstableLogic.setPageLength(NumericConstants.TEN);
        detailstableLogic.sinkItemPerPageWithPageLength(false);
        detailsTable.setImmediate(true);
        detailsTable.setSizeFull();
        detailsTable.setVisibleColumns(ruleDetailsColumns);
        detailsTable.setColumnHeaders(ruleDetailsHeaders);
        detailsTable.setFilterBarVisible(true);
        detailsTable.addStyleName(Constant.FILTERBAR);
        detailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        detailsTable.setFilterGenerator(new FilterGenerator());
        detailsTable.setSelectable(false);
        detailsTable.setWidth("744px");
        detailsTableLayout.addComponent(detailsTable);

        getResponsiveControls(detailstableLogic.createControls(), detailsControlLayout);
    }

    /**
     * Searches for the formula with the entered criteria.
     *
     * @param event
     */
    @UiHandler("searchBtn")
    public void searchLogic(Button.ClickEvent event) {
        try {
            binder.commit();
            tableLogic.clearFilters();
            resultsTable.removeAllItems();
            tableLogic.clearAll();
            tableLogic.setReset(true);
            detailsTable.removeAllItems();
            detailstableLogic.clearAll();
            detailstableLogic.setReset(true);
            rsFormulaDTO.setPropertyId(propertyId);
            resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            tableLogic.setSearchData(rsFormulaDTO);
            if (propertyId.equals(Constant.PPAVariables.NEP_FORMULA.getConstant())) {
                resultsTable.setFilterFieldVisible(Constant.FORMULA_TYPE, false);
            }
        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Resets the search fields.
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void resetLogic(Button.ClickEvent event) {
        rsFormulaDTO = new RSFormulaDTO();
        binder.setItemDataSource(new BeanItem(rsFormulaDTO));
        resultsTable.removeAllItems();
        tableLogic.clearAll();
        tableLogic.setReset(true);
        tableLogic.getFilters().clear();
        detailsTable.removeAllItems();
        detailstableLogic.clearAll();
        detailstableLogic.setReset(true);
    }

    /**
     * Selects the item in the table and closes the window.
     * @param event
     */
    @UiHandler("selectBtn")
    public void selectLogic(Button.ClickEvent event) {
        if (null != resultsTable.getValue()) {
            final RSFormulaDTO itemId = (RSFormulaDTO) resultsTable.getValue();
            List list = new ArrayList();
            list.add(itemId.getFormulaSystemID());
            List resultList = PPAQuerys.getPPAData(list, "net-sales-ppa-validation", null);
            boolean ppaCheck = resultList.get(0) != null && "1".equals(String.valueOf(resultList.get(0)));
            if (validatePPA && !ppaCheck) {
                AbstractNotificationUtils.getErrorNotification("Error",
                        "Only formulas that have a Contract Selection of 'Existing Contract' \n"
                        + "and a Formula Type of 'Contract Deduction' may be used. \n"
                        + "Please select another formula and try again.");
            } else {
                isSelected = true;
                this.close();
            }
        } else {
            final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Select error", "Please select a record.", new MessageBoxListener() {

                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                @Override
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
    }

    /**
     * Closes the window.
     *
     * @param event
     */
    @UiHandler("closeBtn")
    public void closeLogic(Button.ClickEvent event) {
        isSelected = false;
        this.close();
    }

    /**
     * Returns the selected Item from the table.
     *
     * @return
     */
    public RSFormulaDTO getSelectedItem() {
        return getBeanFromId(resultsTable.getValue());
    }

    /**
     *
     * @param obj
     * @return
     */
    public RSFormulaDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof RSFormulaDTO) {
            targetItem = new BeanItem<>(
                    (RSFormulaDTO) obj);
        }
        if (targetItem != null) {
            return (RSFormulaDTO) targetItem.getBean();
        } else {
            return null;
        }
    }

    /**
     * Returns the flag. true - if item is selected in the table. false - if item is not selected.
     *
     * @return
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Sets the flag as true when an item is selected in the table.
     *
     * @param isSelected
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * Sets the flag as true when an item is selected in the table.
     *
     * @param clearAndRefresh
     */
    public void clearAndRefresh(String propertyId, boolean validatePPA) {
        this.propertyId = propertyId;
        this.validatePPA = validatePPA;
        rsFormulaDTO = new RSFormulaDTO();
        binder.setItemDataSource(new BeanItem(rsFormulaDTO));
        resultsContainer.removeAllItems();
        detailsContainer.removeAllItems();
        resultsTable.removeAllItems();
        tableLogic.clearAll();
        tableLogic.setReset(true);
        tableLogic.setRequiredCount(true);
        tableLogic.setCurrentPage(1);
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(formulaLookup);
        resultsTable.setColumnHeaders(formulaLookupHeader);
    }

    /**
     * Closes the window.
     *
     * @param event
     */
    @UiHandler("detailsBtn")
    public void detailsBtnLogic(Button.ClickEvent event) {
        try {
            if (resultsTable.getValue() != null) {
                detailstableLogic.clearFilters();
                detailstableLogic.setSearchDataForDetails(((RSFormulaDTO) resultsTable.getValue()).getForectastingFormulaSid());
                detailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private void getResponsiveControls(HorizontalLayout tempLayout, HorizontalLayout controlBar) {
        controlBar.setStyleName(Constant.RESPONSIVE_PAGED_TABLE);
        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < NumericConstants.EIGHT; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);
    }
}
